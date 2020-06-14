<template>
  <div class="login_container">
    <div class="login_box">
      <div class="avatar_box">
        <img src="../assets/logo.png" alt="">
      </div>
      <!-- 表单 -->
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" label-width="0px" class="login_form">
        <el-form-item prop="username">
          <el-input placeholder="请输入账号" v-model="loginForm.username" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="请输入密码" v-model="loginForm.password" prefix-icon="el-icon-lock" type="password"></el-input>
        </el-form-item>
        <el-form-item class="btns" >
          <router-link class="link" to="/forgetpwd">
              忘记密码
          </router-link>
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="resetLoginForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
// import md5 from 'js-md5'
export default {
  data () {
    return {
      // 数据绑定
      loginForm: {
        username: '',
        password: ''
      },
      // 登录规则
      loginFormRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      },
      userList: []
    }
  },
  onLoad () { this.getUserList() },
  methods: {
    resetLoginForm () {
      // console.log(this)
      this.$refs.loginFormRef.resetFields()
    },
    async getUserList () {
      const { data } = await this.$http.get('user/findAll', {
        params: this.queryInfo
      })
      console.log('获取用户信息如下:')
      console.log(data)
      this.userlist = data
      this.total = data.length
    },
    login () {
      this.$refs.loginFormRef.validate(async valid => {
        // this.getUserList()
        console.log(this.loginForm)
        const userLogin = {
          username: this.loginForm.username,
          password: this.loginForm.password
        }
        console.log(this.userLogin)
        if (!valid) return
        const { data } = await this.$http.post('api/login', userLogin)
        console.log(data)
        // console.log(data.meta.status)
        if (data.status !== 'success') return this.$message.error('登录失败！用户名或密码错误')
        this.$message.success('登录成功！')
        window.sessionStorage.setItem('token', data.token)
        console.log(data.token)
        this.$router.push('/home')
        // this.$router.push('/welome')
      })
    },
    findUser () {
      this.$refs.addUserFormRef.validate(async valid => {
        if (!valid) return
        const thisUser = {
          username: this.loginForm.username,
          password: this.loginForm.password
        }
        const { data } = await this.$http.post('user/addUser', thisUser)
        if (data.status !== 201) {
          this.$message.error('添加用户失败！')
        }
        this.$message.success('添加用户成功！')
        this.dialogVisible = false
        this.getUserList()
      })
    }

  }
}

</script>

<style lang="less" scoped>
.login_container{
  background-color: #2b4b6b;
  height: 100%;
}
.login_box{
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%)
}
.avatar_box{
  width: 130px;
  height: 130px;
  border: 1px solid #eee;
  border-radius: 50%;
  padding: 10px;
  box-shadow: 0 0 10px #ddd;
  position: absolute;
  left:50%;
  transform: translate(-50%,-50%);
  background-color: #fff;
  img{
    width: 100%;
    height: 100%;
    border-radius: 50%;
    background-color: #eee;
  }
}

.login_form{
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 20px;
  box-sizing: border-box;
}

.btns{
  display: flex;
  justify-content:flex-end;
}

.link{
  padding-right: 190px;
}
</style>
