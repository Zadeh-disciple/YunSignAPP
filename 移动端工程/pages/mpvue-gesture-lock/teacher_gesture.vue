<template>
	<view>
		<!-- <view>
			<text>{{location}}</text>
			<button @click="getlo">定位</button>
		</view> -->
        <view>
            <mpvue-gesture-lock :containerWidth="590" :cycleRadius="70" @end="onEnd" :password="password" ref="myges"></mpvue-gesture-lock>
        </view>
        <view class="uni-text">{{text}}</view>
		<view class="inputArea">
			<button style="border-radius:22px;" type="primary" @click="goSign">发起签到</button>
			<button style="border-radius:22px;margin-top: 10rpx;" type="primary" @click="StopSign">结束签到</button>
			<!-- <button type="primary" @click="refre">重置</button> -->
		</view>
		<view style="padding: 0 10%;">
			<text style="float:right;color: green;">{{message}}</text>
		</view>
	</view>
</template>

<script>
    import mpvueGestureLock from '@/components/mpvueGestureLock';
	// import gesture from '../../components/mpvueGestureLock/index.vue'
    export default {
        components: {
            mpvueGestureLock
        },
        data() {
            return {
				title : "手势图案",
                password: [],
                text: '请设定手势',
				status:0,
				message:'',
				location:'定位中。。。',
				courseid: '',
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
				},
				longitude: '',
				latitude: '',
				gesture: '',
				signid: 0,
				newdata: {}
            }
        },
		onLoad(option) {
			console.log(option.courseid)
			this.courseid = option.courseid
			this.getlo()
		},
        methods: {
            onEnd(data) {
				console.log(data)
                if (this.password.length) {
					// console.log(this.password)
      //               if (this.password.join('') === data.join('')) {
      //                   this.text = '手势设定完成'
						// if (this.status!=0){
						// 	this.message=''
						// }
						// this.status=1
      //                   this.password = []
      //               } else {
      //                   this.text = '两次手势设定不一致'
						// if (this.status!=0){
						// 	this.message=''
						// }
						// this.status=-2
      //                   this.password = []
      //               }
                } else {
                    // this.text = '请确认手势设定'
					if (this.status!=0){
						this.message=''
					}
                    this.password = data
					this.status=-1
					this.text = '手势设定完成'
                }
            },
			refre() {
				this.$refs.myges.refresh()
			},
			goSign() {
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
				console.log(this.password)
				for(let i = 0; i< this.password.length; i++) {
					this.gesture += this.password[i].toString()
				}
				console.log(this.longitude + this.latitude)
				uni.request({
					url:'http://47.112.242.4:8181/course/findById',
					data:{
						courseid:this.courseid
					},
					method:'POST',
					success: (res) => {
						console.log(res)
						console.log('1')
						this.courseid = res.data.courseid
						this.cass=res.data.classid
						this.course_info=res.data.courseinfo
						this.course_name=res.data.coursename
						this.period=res.data.semester
						this.school.schoolname=res.data.school.schoolname
						this.school.schoolid = res.data.school.schoolid
						this.school.xueyuan.xueyuanname=res.data.school.xueyuan.xueyuanname
						this.school.zhuanye.zhuanyename=res.data.school.zhuanye.zhuanyename
						this.newdata = {
							longitude: this.longitude,
							latitude: this.latitude,
							gesture: this.gesture,
							"course": {
							    "courseid": this.courseid,
							    "coursename": this.course_name,
							    "courseinfo": this.course_info,
							    "classid": this.cass,
							    "semester": this.period,
							    "school": this.school
							},
						}
						console.log(this.newdata)
						uni.request({
							url:'http://47.112.242.4:8181/putsign/addPutSign',
							method:'POST',
							data: this.newdata,
							success: (res) => {
								console.log('3')
								console.log(res)
								if(res.statusCode == 200) {
									uni.showToast({
										title:'开始签到'
									})
								}
							}
						})
					}
				})
			},
			StopSign() {
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
					url: `http://47.112.242.4:8181/putsign/findsignBycourseId?courseid=${this.courseid}`,
					success: (res) => {
						// console.log(res.data[0].putsignid)
						this.signid = res.data[0].putsignid
						console.log(this.signid)
						uni.request({
							url:`http://47.112.242.4:8181/putsign/deletePutSign?putsignid=${this.signid}`,
							method:'DELETE',
							success: (res) => {
								console.log(res)
								if(res.statusCode == 200) {
									return uni.showToast({
										title: '已停止签到'
									})
								}
							}
						})
					}
				})
			},
			getlo() {
				uni.getLocation({
				    type: 'gcj02',
				    success: (res) => {
						console.log('2')
				        // console.log('当前位置的经度：' + res.longitude);
				        // console.log('当前位置的纬度：' + res.latitude);
						this.longitude = res.longitude;
						this.latitude = res.latitude;
						console.log('当前位置的经度：' + this.longitude);
						console.log('当前位置的纬度：' + this.latitude);
						// this.location = '经度：' + res.longitude + ' 纬度：' + res.latitude;
						// console.log(this.location);
				    },
					fail() {
						return uni.showToast({
							title: '获取地址信息失败'
						})
					}
				})
			}
        }
    }
</script>

<style>
    .uni-text{
        font-size: 30upx;
        text-align: center;
        margin-top: 30upx;
    }
</style>