<template>
	<view style="background-color: #F1F1F1">
		<view style="margin-top: 30rpx;">
			<view class="inputList">
				<text>班级</text>
				<input placeholder="未获取" v-model="cass" />
			</view>
			<view class="inputList">
				<text>课程</text>
				<input placeholder="未获取" v-model="course_name" />
			</view>
			<view class="inputList">
				<text>课程详细信息</text>
				<input placeholder="未获取" v-model="course_info" />
			</view>
			<view class="inputList">
				<text>学校</text>
				<input placeholder="未获取" v-model="school.schoolname" />
			</view>
			<view class="inputList">
				<text>学院</text>
				<input placeholder="未获取" v-model="school.xueyuan.xueyuanname" />
			</view>
			<view class="inputList">
				<text>专业</text>
				<input placeholder="未获取" v-model="school.zhuanye.zhuanyename" />
			</view>
			<view class="inputList">
				<text>学期</text>
				<input placeholder="未获取" v-model="period" />
			</view>
		</view>
		<view style="position: absolute; bottom: 10rpx; width: 100%;">
			<button type="primary" @click="join">确认加入班课</button>
		</view>
	</view>
</template>
<script>
	import uniIcons from "@/components/uni-icons/uni-icons.vue"
	import uniCollapse from '@/components/uni-collapse/uni-collapse.vue'
	import uniCollapseItem from '@/components/uni-collapse-item/uni-collapse-item.vue'
	// import service  from "../../service.js"
	export default {
		data() {
			return {
				courseid: "",//课程号
				cass:"",//班级
				course_name:"",//课程名称
				period:"",//学期
				course_info:"",//课程详细信息 对应数据库字段courseinfo
				school: {
					"schoolid": "",
					"schoolname": "",
					"xueyuan": {
						"xueyuanid": "",
						"xueyuanname": ""
					},
					"zhuanye": {
						"zhuanyeid": "",
						"zhuanyename": ""
					}
				}
			}
		},
		onLoad(option) {
			console.log(option.courseid)
			this.courseid=option.courseid
			this.getcourse();
		},
		methods: {
			getcourse() {
				const token = uni.getStorageSync('token')
				uni.request({
					url: `http://47.112.242.4:8181/api/parsejwt`,
					header: {
						'Authorization': token //将token放入请求头
					},
					success: (res) => {
						console.log(res.data.message.success)
						if (res.data.message.success !== true) {
							setTimeout(() =>{
							    uni.showToast({
							        title: '登录过期，请重新登录！',
							    	positin: top,
							        duration: 2000
							    })
							},2)
						  uni.removeStorageSync('user')
						  uni.removeStorageSync('token')
						  uni.reLaunch({
						      url: '../login/login'
						  })
						}
					}
				})
				uni.request({
					url:'http://47.112.242.4:8181/course/findById',
					data:{
						courseid:this.courseid
					},
					method:'POST',
					success: (res) => {
						console.log(res)
						this.courseid=res.data.courseid
						this.cass=res.data.classid
						this.course_info=res.data.courseinfo
						this.course_name=res.data.coursename
						this.period=res.data.semester
						this.school.schoolname=res.data.school.schoolname
						this.school.schoolid = res.data.school.schoolid
						this.school.xueyuan.xueyuanname=res.data.school.xueyuan.xueyuanname
						this.school.zhuanye.zhuanyename=res.data.school.zhuanye.zhuanyename
					}
				})
			},
			//加入班课
			join() {
				const token = uni.getStorageSync('token')
				uni.request({
					url: `http://47.112.242.4:8181/api/parsejwt`,
					header: {
						'Authorization': token //将token放入请求头
					},
					success: (res) => {
						console.log(res.data.message.success)
						if (res.data.message.success !== true) {
							setTimeout(() =>{
							    uni.showToast({
							        title: '登录过期，请重新登录！',
							    	positin: top,
							        duration: 2000
							    })
							},2)
						  uni.removeStorageSync('user')
						  uni.removeStorageSync('token')
						  uni.reLaunch({
						      url: '../login/login'
						  })
						}
					}
				})
				const user = uni.getStorageSync('user')
				const pdata ={
					        "totalexp": 0,
					        "course": {
					            "courseid": this.courseid,
					            "coursename": this.course_name,
					            "courseinfo": this.course_info,
					            "classid": this.cass,
					            "semester": this.period,
					            "school": {
					                "schoolid": this.school.schoolid,
					                "schoolname": this.school.schoolname,
					                "schoolxueyuan": this.school.xueyuan.xueyuanname,
					                "schoolzhuanye": this.school.zhuanye.zhuanyename
					            }
					        },
					        "user": {
					            "userid": user.userid,
					            "username": user.username,
					            "password": user.password,
					            "realname": user.realname,
					            "email": user.email,
					            "telephone": user.telephone,
					            "role": {
					                "roleid": user.role.roleid,
					                "roledetail": user.role.roledetail,
					                "rolename": user.rolename
					            },
					            "school": {
					                "schoolid": this.school.schoolid,
					                "schoolname": this.school.schoolname,
					                "schoolxueyuan": this.school.xueyuan.xueyuanname,
					                "schoolzhuanye": this.school.zhuanye.zhuanyename
					            }
					        }
				}
				uni.request({
					url: 'http://47.112.242.4:8181/Course_user/addCourse_user',
					method: 'POST',
					data: pdata,
					success: (res) => {
						console.log(res)
						uni.showToast({
							title: '班课加入成功',
						})
						//跳出班课加入成功2s后跳转课程详情页。
						setTimeout(function(){
							uni.switchTab({
								url: '../index/index'
							})
						},2000)
						
					}
				})
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

