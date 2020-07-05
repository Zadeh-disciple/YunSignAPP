<template>
  <div class="about">
    <div class="idd">
        <ul>
          <li :class="{'active':indexTap == index}" v-for="(item,index) in lists" :key="index" @click="indexTap = index">
            {{item}}
          </li>
		  <view>
		  	<uni-icons type="plusempty" size="30" @click="open"></uni-icons>
		  </view>
        </ul>
		<div class="showBox animated bounceInDown" v-if="indexTap == 0">
			<view class="list">
				<view class="flex_col" @click="getcource" v-for="(item,index) in courseList" :key="index" :data-index="item.id">
					<image src="../../static/logo.png" mode="aspectFill" style="width: 100rpx; height: 100rpx;"></image>
					<view class="flex_grow">
						<view class="flex_col">
							<view class="flex_grow">{{item.name}}</view>
							<!-- <view class="time">{{item.time}}</view> -->
						</view>
						<!-- <view class="info">{{item.info}}</view> -->
						<view style="margin-top: 10rpx;">
							<button size="mini" v-on:click.stop="sign" :data-index="item.id">签到</button>
							<button size="mini" class="butt">提问</button>
							<button size="mini" class="butt">回复</button>
						</view>
						<!-- <uni-icons type="arrowright" size="10"></uni-icons> -->
					</view>
					<uni-icons type="arrowright" size="20" style="width: 5%;"></uni-icons>
				</view>
			</view>
		</div>
		<div class="showBox animated bounceInDown" v-if="indexTap == 1">
			<view class="serch">
				<view class="ser">
					<uni-search-bar @confirm="search" @input="input" ></uni-search-bar>
				</view>
				<view class="to" style="color: #007AFF; font-size: 30rpx;">调序</view>
			</view>
			<view class="list">
				<view class="flex_col" @click="getcource" v-for="(item,index) in userList" :key="index" :data-index="item.id">
					<image src="../../static/logo.png" mode="aspectFill" style="width: 100rpx; height: 100rpx;"></image>
					<view class="flex_grow" >
						<view class="flex_col">
							<view class="flex_grow">{{item.name}}</view>
							<!-- <view class="time">{{item.time}}</view> -->
						</view>
						<view class="info">{{item.info}}</view>
						<view style="margin-top: 10rpx;">
							<button size="mini" v-on:click.stop="sign" :data-index="item.id">签到</button>
							<button size="mini" class="butt">举手</button>
							<button size="mini" class="butt">抢答</button>
						</view>
						<!-- <uni-icons type="arrowright" size="10"></uni-icons> -->
					</view>
					<uni-icons type="arrowright" size="20" style="width: 5%;" ></uni-icons>
				</view>
			</view>
		</div>
    </div>
	<uni-popup ref="popup" type="bottom" :mask-click="false">
		<view style="background-color: #FFFFFF;">
			<view class="tip" @click="create_course">创建班课</view>
			<view class="tip" @click="jump_course">使用班课号加入班课</view>
			<view class="tip" @click="scan">扫一扫加入班课</view>
			<view class="tip" @click="cancel">取消</view>
		</view>
	</uni-popup>
  </div>
</template>

<script>
	import uniIcons from "@/components/uni-icons/uni-icons.vue"
	import uniSearchBar from '@/components/uni-search-bar/uni-search-bar.vue'
	import uniPopup from "@/components/uni-popup/uni-popup.vue"
	import service  from "../../service.js"
	export default {
		name: 'about',
		data() {
			return {
				indexTap:1,
				lists:['我创建的','我加入的'],
				userList: [],
				courseList: [],
				/* 窗口尺寸 */
				winSize: {},
			}
		},
		onLoad() {
			// this.getListData();
			// this.getCourseList();
			this.getWindowSize();
			// #ifdef H5
			document.onLong = function(e) {
				var e = e || window.event;
				e.preventDefault();
			};
			// #endif
		},
		onShow() {
			console.log('show')
			const user = uni.getStorageSync('user')
			if (user.role.rolename === '教师') {
				console.log(user.role.rolename)
				this.indexTap = 0
				this.getCourseList()
			} else if(user.role.rolename === '学生'){
				console.log(user.role.rolename)
				this.indexTap = 1
				this.getListData()
			}
		},
		onPullDownRefresh() {
			setTimeout(function () {
			    uni.stopPullDownRefresh();
			}, 1000);
		},
		methods: {
			search(res) {
				uni.showToast({
					title: '搜索：' + res.value,
					icon: 'none'
				})
			},
			input(res) {
				this.searchVal = res.value
			},
			getCourseList() {
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
				if (user.role.rolename === '学生') {
					this.courseList = []
					return uni.showToast({
						title: '还未创建班课'
					})
				}
				uni.request({
					url: 'http://47.112.242.4:8181/course/findAll',
					success: (res) => {
						// console.log(res)
						let arr = []
						for (let i = 0; i < res.data.length; i++) {
							// console.log(cur[i].course)
							arr.push({
								"id":res.data[i].courseid,
								"name":res.data[i].coursename
							})
						}
						this.courseList = arr;
					}
				})
				// let cur = service.getCourse()
				// console.log(cur.length)
				// let arr = []
				// for (let i = 0; i < cur.length; i++) {
				// 	console.log(cur[i].course)
				// 	arr.push({
				// 		"name":cur[i].course
				// 	})
				// }
				
				// this.courseList = arr;
			},
			// cancel(res) {
			// 	uni.showToast({
			// 		title: '点击取消，输入值为：' + res.value,
			// 		icon: 'none'
			// 		})
			// 	}
			// },
			/* 获取列表数据 */
			getListData() {
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
				if (user.role.rolename === '教师') {
					this.userList = []
					return uni.showToast({
						title: '还未加入班课'
					})
				}
				uni.request({
					url: `http://47.112.242.4:8181/Course_user/findcourseByuserId?userid=${user.userid}`,
					success: (res) => {
						console.log(res)
						let list = []
						for (let i = 0; i < res.data.length; i++) {
							// let list = []
							list.push({
								"id": res.data[i].course.courseid,
								"name": res.data[i].course.coursename,
								"info": res.data[i].user.realname
							})
							// this.userList = list;
						}
						this.userList = list;
					}
				})
				// this.userList = list;
			},
			getWindowSize() {
				uni.getSystemInfo({
					success: (res) => {
						//console.log(res)
						this.winSize = {
						"witdh": res.windowWidth,
						"height": res.windowHeight,
						}
					}
				})
			},
			getcource(e) {
				// console.log(e)
				console.log(e.currentTarget.dataset.index)
				uni.navigateTo({
					url:`../course/course?courseid=${e.currentTarget.dataset.index}`
				})
			},
			open(){
			    this.$refs.popup.open(),
				uni.hideTabBar({
					success() {
						console.log("success hide")
					}
				})
			},
			cancel() {
				this.$refs.popup.close(),
				uni.showTabBar({
					success() {
						console.log("success show")
					}
				})
			},
			create_course() {
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
				if (user.role.rolename === '学生') {
					this.courseList = []
					return uni.showToast({
						title: '您是学生，无法创建班课'
					})
				}
				uni.navigateTo({
					url:"../course-create/course-create"
				});
				this.cancel()
				// this.$refs.popup.close()
			},
			jump_course() {
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
				if (user.role.rolename === '教师') {
					this.userList = []
					return uni.showToast({
						title: '您是教师，无法加入班课'
					})
				}
				uni.navigateTo({
					url:"../course-join/course-number",
				});
				this.cancel()
			},
			sign(e) {
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
				console.log(e)
				const user = uni.getStorageSync('user')
				if (user.role.rolename === '教师') {
					uni.navigateTo({
						url:`../mpvue-gesture-lock/teacher_gesture?courseid=${e.currentTarget.dataset.index}`
					})
				} else {
					uni.navigateTo({
						url:`../mpvue-gesture-lock/student_gesture?courseid=${e.currentTarget.dataset.index}`
					})
				}
			},
			//扫一扫功能
			scan() {
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
				if (user.role.rolename === '教师') {
					this.userList = []
					return uni.showToast({
						title: '您是教师，无法加入班课'
					})
				}
				uni.scanCode({
					success: function (res) {
						console.log('条码内容：' + res.result);
						const courseid = res.result		
						uni.showToast({
							title: courseid,
						})
						//跳转课程详情页面
						uni.navigateTo({
							url: "../course-join/course-detail?courseid="+courseid,
						});
					},
					fail: function(){
						uni.showToast({
							title: '二维码有问题',
						})
					},
					//最终跳转课程详情页面
					// complete:function(res){
					// 	uni.showToast({
					// 		title: res.result,
					// 	})
					// 	var courseid = res.result
					// 	uni.navigateTo({
					// 		url: "../course-join/course-detail?courseid="+courseid,
					// 	});
					// }
				});
				this.cancel()
		}
		},
		components:{
			uniPopup,
			uniIcons,
			uniSearchBar
		}
	}
</script>

<style lang="scss" scoped>
ul::-webkit-scrollbar {
        display: none;
}
.active{
  color:#00aaff;
  font-weight: bolder;
  border-bottom-style: ridge;
  border-color: #007AFF;
}
 .idd{
   width: 100%;
   overflow: hidden;
   ul{
     display: flex;
     align-items: center;
     // overflow:auto;
     padding-left: 0;
     list-style: none;
     li{
       text-align: center;
        font-size: 16px;
        -ms-flex-negative: 0;
        flex-shrink: 0;
        padding: 10px;
        margin: 5px;
     }
	&>view{
		position: absolute;
		right: 5%;	 
	}
   }

 }
 .butt{
 	margin-left: 5%;
 }
 .tip{
 	 margin: 10rpx;
 	 padding: 20rpx;
 	 color: #007AFF;
 	 background-color: #FFFFFF;
 	 text-align: center;
 }
 .serch{
 	display: flex;
 }
 .ser{
 	width: 90%;
 }
 .to{
 	align-self: center;
 }
 .flex_col {
 		display: flex;
 		flex-direction: row;
 		flex-wrap: nowrap;
 		justify-content: flex-start;
 		align-items: center;
 		align-content: center;
 	}
 
 	/* 弹性盒子弹性容器 */
 	.flex_col .flex_grow {
 		width: 0;
 		-webkit-box-flex: 1;
 		-ms-flex-positive: 1;
 		flex-grow: 1;
 	}
 
 	.flex_row .flex_grow {
 		-webkit-box-flex: 1;
 		-ms-flex-positive: 1;
 		flex-grow: 1;
 	}
 .list {
		display: flex;
		flex-direction: column;
		height: 80%;
 		background-color: #fff;
 		font-size: 28upx;
 		color: #333;
 		user-select: none;
 		touch-callout: none;
 
 		&>view {
 			padding: 24upx 30upx;
 			position: relative;
 
 			&:active,
 			&.active {
 				background-color: #f3f3f3;
 			}
 
 			image {
 				height: 80rpx;
 				width: 80rpx;
 				border-radius: 4px;
 				margin-right: 20upx;
 			}
 
 			&>view {
 				line-height: 40upx;
 
 				.time,
 				.info {
 					color: #999;
 					font-size: 24upx;
 				}
 
 				.time {
 					width: 150upx;
 					text-align: right;
 				}
 
 				.info {
 					overflow: hidden;
 					text-overflow: ellipsis;
 					white-space: nowrap;
 				}
 			}
 		}
 
 		&>view:not(:first-child) {
 			margin-top: 1px;
 
 			&::after {
 				content: '';
 				display: block;
 				height: 0;
 				border-top: #CCC solid 1px;
 				width: 620upx;
 				position: absolute;
 				top: -1px;
 				right: 0;
 				transform:scaleY(0.5);	/* 1px像素 */
 			}
 		}
 	}
</style>