<template name="member">
	<view>
		<view style="display: flex;flex-direction: row;font-size: 32rpx;height: 50rpx;">
			<text style="margin-left: 10rpx;">成员总数</text>
			<view style="position: absolute;right: 0;">
				<text >{{total}}人|</text>
				<text style="color: #66AAFF;" @click="getuserlist">刷新</text>
			</view>
		</view>
		<view class="list">
			<view class="flex_col" v-for="(item,index) in userList" :key="index" :data-index="index">
				<view style="margin-right: 15rpx;">{{index+1}}</view>
				<image src="../../static/logo.png" mode="aspectFill" style="width: 100rpx; height: 100rpx;"></image>
				<view class="flex_grow" >
					<view class="flex_col">
						<view class="flex_grow">{{item.name}}</view>
						<!-- <view class="time">{{item.time}}</view> -->
					</view>
					<view class="info">{{item.num}}</view>
					<!-- <uni-icons type="arrowright" size="10"></uni-icons> -->
				</view>
				<view style="display: flex;flex-direction: row;">
					<view style="font-size: 40rpx;color: #4CD964;">{{item.exp}}</view>
					<text style="color: #4CD964;font-size: 18rpx;">经验值</text>
					<uni-icons type="arrowright" size="20" style="width: 5%;margin-left: 10rpx;" ></uni-icons>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name:"member",
		data() {
			return {
				userList: [
					// { name: '张三', num: '190327001', exp: 8},
					// { name: '张三', num: '190327001', exp: 6},
					// { name: '张三', num: '190327001', exp: 4}
				],
				total:0
			}
		},
		methods:{
			getuserlist() {
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
				const courseid = uni.getStorageSync('courseid')
				uni.request({
					url: `http://47.112.242.4:8181/Course_user/findexpBycourseId?courseid=${courseid}`,
					success: (res) => {
						console.log(res)
						let list = []
						this.total = res.data.length
						for(let i = 0; i < res.data.length; i++) {
							list.push({
								name: res.data[i].user.realname,
								num: res.data[i].user.telephone,
								exp: res.data[i].totalexp
							})
						}
						for(let i = 0; i < list.length; i++) {
							for(let j = i + 1;j < list.length; j++) {
								let list1 = {}
								if(list[i].exp < list[j].exp) {
									list1 = list[i]
									list[i] = list[j]
									list[j] = list1
								}
							}
						}
						this.userList = list
					}
				})
			}
		},
		onShow() {
			this.getuserlist()
		}
	}
</script>

<style lang="scss">
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
