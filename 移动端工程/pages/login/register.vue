<template>
	<view class="content">
		<view class="input-group">
			<view class="input-row border">
				<text class="title">输入账号：</text>
				<m-input type="text" focus clearable v-model="username" placeholder="请输入账号"></m-input>
			</view>
			<view class="input-row border">
				<text class="title">输入密码：</text>
				<m-input type="password" displayable v-model="password" placeholder="请输入密码"></m-input>
			</view>
			<view class="input-row border">
				<text class="title">确认密码：</text>
				<m-input type="password" displayable v-model="confirm" placeholder="请确认密码"></m-input>
			</view>
			<view class="input-row">
				<text class="title">真实姓名：</text>
				<m-input type="text" clearable v-model="realname" placeholder="请输入真实姓名"></m-input>
			</view>
			<view class="input-row">
				<text class="title">个人邮箱：</text>
				<m-input type="text" clearable v-model="email" placeholder="请输入个人邮箱"></m-input>
			</view>
			<view class="input-row">
				<text class="title">手机号码：</text>
				<m-input type="number" clearable v-model="telephone" placeholder="请输入手机号码"></m-input>
			</view>
		</view>
		<view class="btn-row">
			<button type="primary" class="primary" @tap="register">注册</button>
		</view>
	</view>
</template>

<script>
	import service from '../../service.js';
	import mInput from '../../components/m-input.vue';

	export default {
		components: {
			mInput
		},
		data() {
			return {
				username: '',
				password: '',
				confirm: '',
				realname: '',
				email: '',
				telephone: ''
			}
		},
		methods: {
			register() {
				/**
				 * 客户端对账号信息进行一些必要的校验。
				 * 实际开发中，根据业务需要进行处理，这里仅做示例。
				 */
				if (this.username.length < 3) {
					uni.showToast({
						icon: 'none',
						title: '账号最短为 3 个字符'
					});
					return;
				}
				if (this.password.length < 6) {
					uni.showToast({
						icon: 'none',
						title: '密码最短为 6 个字符'
					});
					this.password = ''
					return;
				}
				if (this.confirm.length < 6) {
					uni.showToast({
						icon: 'none',
						title: '密码最短为 6 个字符'
					});
					this.confirm = ''
					return;
				}
				if (this.realname.length < 3) {
					uni.showToast({
						icon: 'none',
						title: '真实姓名最短为 3 个字符'
					});
					return;
				}
				if (this.email.length < 3 || !~this.email.indexOf('@')) {
					uni.showToast({
						icon: 'none',
						title: '邮箱地址不合法'
					});
					return;
				}
				if (this.telephone.length !== 11) {
					uni.showToast({
						icon: 'none',
						title: '请输入11位的手机号码'
					});
					return;
				}
				if (this.password !== this.confirm) {
					uni.showToast({
						icon: 'none',
						title: '两次输入的密码不一致'
					});
					this.password = ''
					this.confirm = ''
					return;
				}

				const data = {
					username: this.username,
					password: this.password,
					email: this.email,
					telephone: this.telephone
				}
				uni.request({
					url:'http://47.112.242.4:8181/user/addUser',
					method:'POST',
				    data: {
						username: this.username,
						telephone: this.telephone,
						email: this.email,
						password: this.password,
						realname: this.realname,
						role: {
							roleid: 4
						}
					},
					success:(res)=>{
						console.log(res)
						setTimeout(() =>{
						    uni.showToast({
						        title: '注册成功',
						    	positin: top,
						        duration: 2000
						    })
						},2)
						// uni.showToast({
						// 	title: '注册成功',
						// 	positin: top,
						// 	duration: 2000
						// })
						uni.navigateTo({
							url: '../login/login'
						})
					}
				})
			}
		}
	}
</script>

<style>

</style>
