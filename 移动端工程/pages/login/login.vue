<template>
	<view style="padding-top: 40upx;">
		<view class="inputArea">
			<input v-model="loginform.account" placeholder="请输入账号" type="text" maxlength="30" class="inputClass" />
		</view>
		<view class="inputArea">
			<input v-model="loginform.password" placeholder="请输入密码" type="password" class="inputClass" />
		</view>
		<!-- <view style="padding: 0 10%;">
			<text style="color: red;">{{message}}</text>
		</view> -->
		<view class="inputArea">
			<button style="border-radius:22px;" type="primary" @click="goLogin">登录</button>
		</view>
		<view class="inputArea">
			<button style="border-radius:22px;" type="primary" @click="openRegisterPage">注册</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				loginform: {
					account: '',
					password: ''
				}
			}
		},
		methods: {
			openRegisterPage() {
				uni.navigateTo({
					url: '../login/register',
					success: res => {},
					fail: () => {},
					complete: () => {}
				});
			},
			goLogin() {
				if (this.loginform.account == "" || this.loginform.password == "") {
					uni.showToast({
						icon: 'none',
						title: '账号或密码不能为空'
					})
					return
				}
				// console.log(this.loginform)
				uni.request({
					url:'http://47.112.242.4:8181/api/login',
					method:'POST',
				    data: {
				    	username: this.loginform.account,
				    	telephone: this.loginform.account,
				    	email: this.loginform.account,
				    	password: this.loginform.password
				    },
					success:(res)=>{
						// console.log(res)
						if (res.data.status !== 'success'){
							uni.showToast({
							    title: res.data.message,
								positin: top,
							    duration: 2000
							})
						} else {
							setTimeout(() =>{
							    uni.showToast({
							        title: res.data.message,
							    	positin: top,
							        duration: 2000
							    })
							},2)
							// 保存用户信息
							uni.setStorageSync('user',res.data.user)
							// console.log(res.data.token)
							// 保存token
							uni.setStorageSync('token',res.data.token)
						// setTimeout(function() {
						// 	uni.switchTab({
						// 		url:'../index/index'
						// 	})
						// },1000)
							uni.switchTab({
								url:'../index/index'
							})
						}
					}
				})
			}
		}
	}
</script>

<style>
	.inputArea {
		padding: 20upx 10%;
	}
	.inputClass {
		border: 2px solid #ccc;
		border-radius: 22px;
		outline: 0;
		padding: 8px 15px;
	}
</style>