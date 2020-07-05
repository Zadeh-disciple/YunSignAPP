<template>
  <div class="login_container">
    <div class="login_box">
      <div class="avatar_box">
        <img src="../assets/logo.png" alt="">
      </div>
      <!-- 表单 -->
      <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" label-width="0px" size="small" class="login_form">
        <el-form-item prop="username">
          <el-input placeholder="请输入账号" v-model="loginForm.username" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="请输入密码" v-model="loginForm.password" prefix-icon="el-icon-lock" type="password"></el-input>
        </el-form-item>
        <el-form-item >
          <el-checkbox v-model="keeplogin">30天免登陆</el-checkbox>
        </el-form-item>
        <el-form-item>
          <div class="btns" >
            <div>
              <el-button type="primary" @click="login">登录</el-button>
              <el-button type="info" @click="resetLoginForm">重置</el-button>
            </div>
          </div>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      // 数据绑定
      loginForm: {
        username: '',
        password: ''
      },
      keeplogin: false,
      // 登录规则
      loginFormRules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    resetLoginForm () {
      this.$refs.loginFormRef.resetFields()
    },
    login () {
      this.$refs.loginFormRef.validate(async valid => {
        const userLogin = {
          username: this.loginForm.username,
          telephone: this.loginForm.username,
          email: this.loginForm.username,
          password: this.loginForm.password
        }
        if (!valid) return
        const { data } = await this.$http.post('api/login', userLogin)
        console.log(data)
        // console.log(data.meta.status)
        if (data.status !== 'success') {
          this.resetLoginForm()
          return this.$message.error(data.message)
        }
        this.$message.success(data.message)
        // 将token存储在本地缓存中
        window.sessionStorage.setItem('token', data.token)
        window.sessionStorage.setItem('username', this.loginForm.username)
        window.sessionStorage.setItem('role', data.user.role.rolename)
        console.log(data.token)
        console.log(this.loginForm.username)
        console.log(data.user.role.rolename)
        this.$router.push('/home')
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
  // margin-top: 30px;
  width: 100%;
  padding: 10px;
  box-sizing: border-box;
}

.btns{
  display: flex;
  flex-direction: row;
  justify-content:center;
}

.link{
  padding-right: 19px;
}
</style>
