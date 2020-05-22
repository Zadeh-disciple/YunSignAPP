<template>
	<view style="padding-top: 40upx;">
		<view class="inputArea">
			<input v-model="loginPhone" placeholder="请输入手机号/学工号" type="number" maxlength="11" class="inputClass" />
		</view>
		<view class="inputArea">
			<input v-model="loginPassword" placeholder="请输入登录密码" type="password" class="inputClass" />
		</view>
		<view style="padding: 0 10%;">
			<text style="color: red;">{{message}}</text>
		</view>
		<view class="inputArea">
			<button style="border-radius:22px;" type="primary" @click="goLogin">登录</button>
		</view>
		<view class="inputArea">
			<button style="border-radius:22px;" type="primary" @click="openRegisterPage">注册</button>
		</view>
		<view class="inputArea">
			<text style="float:right;color:blue;" @click="forgetpassport">忘记密码？</text>
		</view>
	</view>
</template>

<script>
	export default {
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
				wx.switchTab({
					url: "../index/index"
					// success: res => {},
					// fail: () => {},
					// complete: () => {}
				});
				this.$message.success('登录成功！！')
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