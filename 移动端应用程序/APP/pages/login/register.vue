<template>
	<view style="padding-top: 40upx;">
		<view class="inputArea">
			<input v-model="name" placeholder="姓名" type="String" maxlength="11" class="inputClass" />
		</view>
		<view class="inputArea">
			<input v-model="studentnumber" placeholder="请输入学工号" type="number" maxlength="11" class="inputClass" />
		</view>
		<view class="inputArea">
			<input v-model="registerPhone" placeholder="请输入手机号(国内)" type="number" maxlength="11" class="inputClass" />
		</view>
		<view class="inputArea">
			<input v-model="registerPassword" placeholder="密码(至少符号数字大小写两种)" type="password" class="inputClass" />
		</view>
		<view class="inputArea">
			<input v-model="confirmPassword" placeholder="确认登录密码" type="password" class="inputClass" />
		</view>
		<view class="inputArea">
			<button style="border-radius:22px;" type="primary" @click="goRegister">注 册</button>
		</view>
		<view style="padding: 0 10%;">
			<text style="color: green;">{{message}}</text>
		</view>
		<view class="inputArea">
			<text style="float:right;color:blue;" @click="openAgreement">《用户协议》</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				registerPhone: '',
				registerPassword: '',
				confirmPassword: '',
				studentnumber:'',
				smsbtn: {
					text: '发送',
					status: false,
					codeTime: 60
				},
				timerId: null,
			}
		},
		onLoad() {
			console.log('register页面onLoad');
		},
		methods: {
			getsmscode() {
				//此处写发送验证码逻辑
				if (this.smsbtn.codeTime != 60) {
					return;
				}
				this.timerId = setInterval(() => {//此处直接复制了另一个插件里的计时器，在插件市场里搜索登录，时间最靠前的那位
						let codeTime = this.smsbtn.codeTime;
						codeTime--;
						this.smsbtn.codeTime = codeTime;
						this.smsbtn.text = codeTime + "S";
						if (codeTime < 1) {
							clearInterval(this.timerId);
							this.smsbtn.text = "重试";
							this.smsbtn.codeTime = 60;
							this.smsbtn.status = false;
						}
					},
					1000);
				return false;
			},
			goRegister() {
				let registerPhone = this.registerPhone;
				let registerPassword = this.registerPassword;
				let confirmPassword = this.confirmPassword;
				if (!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(registerPhone))) {
					this.message = "手机号码有误，请重填";
					return false;
				}
				if (!registerPassword) {
					this.message = "密码为空";
					return false;
				}
				let ls = 0;
				if (registerPassword.match(/([a-z])+/)) {
					ls++;
				}
				if (registerPassword.match(/([0-9])+/)) {
					ls++;
				}
				if (registerPassword.match(/([A-Z])+/)) {
					ls++;
				}
				if (registerPassword.match(/[^a-zA-Z0-9]+/)) {
					ls++;
				}
				if (registerPassword.length < 8) {
					ls = 0;
				}
				if (ls < 2) {
					this.message = "密码强度不够，至少8位，大写、小写、字母、符号 其中两种";
					return false;
				}


				if (confirmPassword != registerPassword) {
					this.message = "两次密码不同";
					return false;
				}
				this.message = "注册成功！";
				uni.navigateTo({
					url: '../login/login',
					success: res => {},
					fail: () => {},
					complete: () => {}
				});
				
			},
			openAgreement() {
				uni.navigateTo({
					url: '../login/userAgreement',
					success: res => {},
					fail: () => {},
					complete: () => {}
				});
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
