<template>
  <div class="forpwd_container">
    <div class="forpwd_box">
      <div class="avatar_box">
        <img src="../assets/logo.png" alt="">
      </div>
      <!-- 表 单 -->
      <el-form :model="forgetForm" :rules="forgetFormRules" label-width="0px" class="forpwd_form">
        <el-form-item prop="telephone">
          <el-input placeholder="请输入手机号" v-model="forgetForm.telephone" prefix-icon="el-icon-phone"></el-input>
        </el-form-item>
        <el-form-item prop="message">
          <el-input class="msg" placeholder="请输入短信验证码" v-model="forgetForm.message" prefix-icon="el-icon-chat-square">
          </el-input>
          <el-button type="info" @click="send">发送验证码</el-button>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="请输入新密码" v-model="forgetForm.password" prefix-icon="el-icon-lock" type="password"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="请确认新密码" v-model="forgetForm.password" prefix-icon="el-icon-lock" type="password"></el-input>
        </el-form-item>
        <el-form-item class="btns" >
          <el-button type="primary" @click="login">完成</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      forgetForm: {
        telephone: '',
        message: '',
        password: ''
      },
      forgetFormRules: {
        telephone: [
          { required: true, message: '请输入手机号', trigger: 'blur' }
        ],
        message: [
          { required: true, message: '请输入验证码', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  }
}

</script>

<style lang="less" scoped>
.forpwd_container{
  background-color: #2b4b6b;
  height: 100%;
}
.forpwd_box{
  width: 450px;
  height: 450px;
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

.forpwd_form{
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 20px;
  box-sizing: border-box;
}

.btns{
  display: flex;
  justify-content:space-between;
}

.msg{
  width: 250px;
}

</style>
