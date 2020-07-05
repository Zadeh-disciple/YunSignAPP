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
	<view style="background-color: #F1F1F1">
		<view style="text-align: center;">
			<uni-icons type="plus" size="60" @click="uploadimg"></uni-icons>
			<view>班课封面</view>
		</view>
		<view style="margin-top: 30rpx;">
			<view class="inputList">
				<text>班级</text>
				<input placeholder="未设置" v-model="cass" />
				<!-- <input v-model="cass" placeholder="请输入班级"></m-input> -->
			</view>
			<view class="inputList">
				<text>课程</text>
				<input placeholder="未设置" v-model="course" />
			</view>
			<view class="inputList">
				<text>描述</text>
				<input placeholder="未设置" v-model="course_detail" />
			</view>
			<view class="inputList">
				<text>学期</text>
				<input placeholder="未设置" v-model="period" />
			</view>
		</view>
		<view class="inputList" style="margin-top: 50rpx;margin-bottom: 50rpx;">
			<text>云教材</text>
			<input placeholder="未设置" v-model="book" />
		</view>
		<text style="font-size: 32rpx;color: #bcbcbc;">设置学校、院系等其它信息</text>
		<view>
			<uni-collapse accordion="true">
				<uni-collapse-item title="设置班级详情">
					<view>
						<view class="inputList">
							<text>学校课表</text>
							<!-- <input v-model="schedule" /> -->
							<view style="position: absolute;right: 0rpx;">
								<switch @change="switchchange" />
							</view>
						</view>
						<view class="inputList">
							<view class="inputList">
								<view>
									学校院系
								</view>
								<view style="align-items: center;">
									<picker mode="multiSelector" @columnchange="bindMultiPickerColumnChange" :value="multiIndex" :range="schoollist">
										<text style="position: absolute;right: 0rpx;font-size: 32rpx;color: #9F9F9F;">{{schoollist[0][multiIndex[0]]}}/{{schoollist[1][multiIndex[1]]}}/{{schoollist[2][multiIndex[2]]}}</text>
									</picker>
								</view>
							</view>
						</view>
						<view class="inputList">
							<text>学习要求</text>
							<input placeholder="未设置" v-model="learn_require" />
						</view>
						<view class="inputList">
							<text>考试安排</text>
							<input placeholder="未设置" v-model="plan" />
						</view>
					</view>
				</uni-collapse-item>
			</uni-collapse>
		</view>
		<!-- <view style="margin-top: 10rpx;">
			<view class="inputList">
				<text>班课详情</text>
				<input v-model="course_detail" />
			</view>
			<view class="inputList">
				<text>学校课表</text>
				<view style="position: absolute;right: 0rpx;">
					<switch @change="switchchange" />
				</view>
			</view>
			<view class="inputList">
				<view class="inputList">
					<view class="uni-list-cell-left">
						学校院系
					</view>
					<view style="align-items: center;">
						<picker mode="multiSelector" @columnchange="bindMultiPickerColumnChange" :value="multiIndex" :range="schoollist">
							<text style="position: absolute;right: 0rpx;font-size: 32rpx;color: #9F9F9F;">{{schoollist[0][multiIndex[0]]}}/{{schoollist[1][multiIndex[1]]}}/{{schoollist[2][multiIndex[2]]}}</text>
 						</picker>
					</view>
				</view>
			</view>
			<view class="inputList">
				<text>学习要求</text>
				<input v-model="learn_require" />
			</view>
			<view class="inputList">
				<text>考试安排</text>
				<input v-model="plan" />
			</view>
		</view>-->
		<view style="position: absolute; bottom: 10rpx; width: 100%;">
			<button type="primary" @click="create">创建班课</button>
		</view>
	</view>
</template>

<script>
	import uniIcons from "@/components/uni-icons/uni-icons.vue"
	import uniCollapse from '@/components/uni-collapse/uni-collapse.vue'
	import uniCollapseItem from '@/components/uni-collapse-item/uni-collapse-item.vue'
	import service  from "../../service.js"
	export default {
		data() {
			return {
				Array: [],
				id: 0,
				schoollist:[
					[],
					[],
					[]
				],
				multiIndex: [0, 0, 0],
				courseid: 30010,
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
		onLoad() {
			this.getschool();
		},
		methods: {
			switchchange: function(e) {
				console.log(e.target.value)
				this.schedule = e.target.value
			},
			uploadimg() {
				uni.chooseImage({
					count:1,
					success:function(res) {
						console.log(JSON.stringify(res.tempFilePaths))
					}
				})
			},
			getschool() {
				uni.request({
					url:'http://47.112.242.4:8181/school/findAll',
					data:'',
					success: (res) => {
						console.log(res.data)
						this.Array = res.data
						const list1 = []
						for(let i = 0; i < res.data.length; i++ ) {
							list1[i] = res.data[i].schoolname
						}
						this.schoollist[0] = list1
						this.schoollist[1][0] = res.data[0].xueyuan.xueyuanname
						this.schoollist[2][0] = res.data[0].zhuanye.zhuanyename
						console.log(this.schoollist)
					}
				})
			},
			bindMultiPickerColumnChange: function(e) {
				console.log('修改的列为：' + e.detail.column + '，值为：' + e.detail.value)
				this.multiIndex[e.detail.column] = e.detail.value
				switch (e.detail.column) {
					case 0: //拖动第1列
						switch (this.multiIndex[0]) {
							case 0:
								this.schoollist[1] = ['数计学院']
								this.schoollist[2] = ['计算机技术']
								break
							case 1:
								this.schoollist[1] = ['环资学院']
								this.schoollist[2] = ['生物工程']
								break
							case 2:
								this.schoollist[1] = ['生工学院']
								this.schoollist[2] = ['土木工程']
								break
							case 3:
								this.schoollist[1] = ['化学学院']
								this.schoollist[2] = ['生物工程']
								break
							case 4:
								this.schoollist[1] = ['经管学院']
								this.schoollist[2] = ['生物工程']
								break
						}
						this.multiIndex.splice(1, 1, 0)
						this.multiIndex.splice(2, 1, 0)
						break
					case 1: //拖动第2列
						switch (this.multiIndex[0]) { //判断第一列是什么
							case 0:
								switch (this.multiIndex[1]) {
									case 0:
										this.multiArray[2] = ['北京', '上海', '广州']
										break
									case 1:
										this.multiArray[2] = ['东京','北海道']
										break
								}
								break
							case 1:
								switch (this.multiIndex[1]) {
									case 0:
										this.multiArray[2] = ['伦敦', '曼彻斯特']
										break
									case 1:
										this.multiArray[2] = ['巴黎', '马赛']
										break
								}
								break
						}
						this.multiIndex.splice(2, 1, 0)
						break
				}
				this.$forceUpdate()
			},
			create() {
				if (this.cass == "") {
					uni.showToast({
						icon: 'none',
						title: '班级不能为空'
					});
					return;
				};
				// const id = 0
				for(let i = 0; i < this.Array.length; i++) {
					if(this.schoollist[0][this.multiIndex[0]] === this.Array[i].schoolname) {
						console.log(this.Array[i].schoolname)
						this.id = this.Array[i].schoolid
					}
				}
				const data = {
					coursename: this.course,
					courseinfo: this.course_detail,
					classid: this.cass,
					semester: this.period,
					school: {
						schoolid: this.id,
						schoolname: this.schoollist[0][this.multiIndex[0]],
						schoolxueyuan: this.schoollist[0][this.multiIndex[1]],
						schoolzhuanye: this.schoollist[0][this.multiIndex[2]]
					}
				}
				uni.request({
					url: 'http://47.112.242.4:8181/course/addCourse',
					method: 'POST',
					data: data,
					success: (res) => {
						console.log(res)
						console.log(this.id)
						// uni.showToast({
						// 	title: '班课创建成功'
						// });
						if(res.data == 'addSuccess') {
							uni.request({
								url: 'http://47.112.242.4:8181/course/findAll',
								success: (res) => {
									this.courseid = res.data[res.data.length - 1].courseid
									uni.navigateTo({
										// url: './course-createsuccess'
										url: `../course-create/course-createsuccess?id=${this.courseid}`
									})
								}
							})
						}
					}
				})
				// service.addCourse(data);
				// uni.showToast({
				// 	title: '班课创建成功'
				// });
				// uni.navigateBack({
				// 	delta: 1
				// });
			}
		},
		components:{
			uniIcons,
			uniCollapse,
			uniCollapseItem
		}
	}
</script>

<style lang="scss" scoped>
.inputList{
	display: flex;
	background-color: #FFFFFF;
	height: 80rpx;
	margin-bottom: 5rpx;
	align-items: center;
	// margin: 10rpx;
	// padding: 10rpx;
	&>text{
		// width: 25%;
		// text-align: right;
		position: absolute;
		left: 10rpx;
		font-size: 36rpx;
	}
	&>input{
		// border-style: ridge;
		// border-width: 1rpx;
		// border-color: #C8C7CC;
		// width: 75%;
		text-align: right;
		position: absolute;
		right: 0rpx;
		font-size: 32rpx;
		color: #999999;
	}
}
</style>
