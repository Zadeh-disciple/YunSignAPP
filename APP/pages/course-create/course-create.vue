<!-- <template>
	<view class="content">
		<view class="input-group">
			<view class="input-row border">
				<text class="title">账号：</text>
				<m-input type="text" focus clearable v-model="account" placeholder="请输入账号"></m-input>
			</view>
			<view class="input-row border">
				<text class="title">密码：</text>
				<m-input type="password" displayable v-model="password" placeholder="请输入密码"></m-input>
			</view>
			<view class="input-row">
				<text class="title">邮箱：</text>
				<m-input type="text" clearable v-model="email" placeholder="请输入邮箱"></m-input>
			</view>
		</view>
		<view class="btn-row">
			<button type="primary" class="primary" @tap="register">注册</button>
		</view>
	</view>
</template>

<script>
	//import service from '../../service.js';
	import mInput from '../../components/m-input.vue';

	export default {
		components: {
			mInput
		},
		data() {
			return {
				account: '',
				password: '',
				email: ''
			}
		},
		methods: {
			register() {
				/**
				 * 客户端对账号信息进行一些必要的校验。
				 * 实际开发中，根据业务需要进行处理，这里仅做示例。
				 */
				if (this.account.length < 5) {
					uni.showToast({
						icon: 'none',
						title: '账号最短为 5 个字符'
					});
					return;
				}
				if (this.password.length < 6) {
					uni.showToast({
						icon: 'none',
						title: '密码最短为 6 个字符'
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

				const data = {
					account: this.account,
					password: this.password,
					email: this.email
				}
				//service.addUser(data);
				uni.showToast({
					title: '注册成功'
				});
				uni.navigateBack({
					delta: 1
				});
			}
		}
	}
</script>

<style>

</style> -->

<template>
	<view>
		<view style="text-align: center;">
			<uni-icons type="plus" size="60"></uni-icons>
			<view>班课封面</view>
		</view>
		<view>
			<view class="inputList">
				<text>班级</text>
				<input v-model="cass" />
				<!-- <input v-model="cass" placeholder="请输入班级"></m-input> -->
			</view>
			<view class="inputList">
				<text>课程</text>
				<input v-model="course" />
			</view>
			<view class="inputList">
				<text>学期</text>
				<input v-model="period" />
			</view>
			<view class="inputList">
				<text>云教材</text>
				<input v-model="book" />
			</view>
			设置学校、院系等其它信息
			<view class="inputList">
				<text>班课详情</text>
				<input v-model="course_detail" />
			</view>
			<view class="inputList">
				<text>学校课表</text>
				<input v-model="schedule" />
			</view>
			<view class="inputList">
				<text>学校院系</text>
				<input v-model="department" />
			</view>
			<view class="inputList">
				<text>学习要求</text>
				<input v-model="learn_require" />
			</view>
			<view class="inputList">
				<text>考试安排</text>
				<input v-model="plan" />
			</view>
		</view>
		<view style="position: absolute; bottom: 50rpx; width: 100%;">
			<button type="primary" @click="create">创建班课</button>
		</view>
	</view>
</template>

<script>
	import uniIcons from "@/components/uni-icons/uni-icons.vue"
	import service  from "../../service.js"
	export default {
		data() {
			return {
				cass:"",
				course:"",
				period:"",
				book:"",
				course_detail:"",
				schedule:"",
				department:"",
				learn_require:"",
				plan:""
			}
		},
		methods: {
			create() {
				if (this.cass == "") {
					uni.showToast({
						icon: 'none',
						title: '班级不能为空'
					});
					return;
				};
				const data = {
					cass: this.cass,
					course: this.course,
					period: this.period
				}
				service.addCourse(data);
				uni.showToast({
					title: '班课创建成功'
				});
				uni.navigateBack({
					delta: 1
				});
			}
		},
		components:{
			uniIcons
		}
	}
</script>

<style lang="scss" scoped>
.inputList{
	display: flex;
	margin: 10rpx;
	padding: 10rpx;
	&>text{
		width: 25%;
		text-align: center;
		font-size: 32rpx;
	}
	&>input{
		border-style: ridge;
		border-width: 1rpx;
		border-color: #C8C7CC;
		width: 75%;
		;
	}
}
</style>
