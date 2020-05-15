<template>
  <div class="about">
    <div class="idd">
		<div style="display: flex;">
			<ul>
			  <li :class="{'active':indexTap == index}" v-for="(item,index) in lists" :key="index" @click="indexTap = index">
			    {{item}}
			  </li>
			  <view>
				  <uni-icons type="plusempty" size="30" @click="open"></uni-icons>
				  <uni-popup ref="popup" type="bottom">
					  <view>
						  <view class="tip">创建班课</view>
						  <view class="tip">使用班课号加入班课</view>
					  </view>
				  </uni-popup>
			  </view>
			  <!-- <li>
				  <uni-icons type="plusempty" size="30"></uni-icons>
			  </li> -->
			</ul>
			<!-- <div class="plus">
				<uni-icons type="plusempty" size="30"></uni-icons>
			</div> -->
		</div>
		<div class="showBox animated bounceInDown" v-if="indexTap == 1">
			<view class="list">
				<view class="flex_col" @click="getcource" v-for="(item,index) in userList" :key="index" :data-index="index">
					<image src="../../static/logo.png" mode="aspectFill" style="width: 100rpx; height: 100rpx;"></image>
					<view class="flex_grow">
						<view class="flex_col">
							<view class="flex_grow">{{item.name}}</view>
							<!-- <view class="time">{{item.time}}</view> -->
						</view>
						<view class="info">{{item.info}}</view>
						<view style="margin-top: 10rpx;">
							<button size="mini" >签到</button>
							<button size="mini" class="butt">举手</button>
							<button size="mini" class="butt">抢答</button>
						</view>
						<!-- <uni-icons type="arrowright" size="10"></uni-icons> -->
					</view>
					<uni-icons type="arrowright" size="20"></uni-icons>
				</view>
			</view>
		</div>
    </div>
  </div>
</template>

<script>
	import uniIcons from "@/components/uni-icons/uni-icons.vue"
	import uniPopup from "@/components/uni-popup/uni-popup.vue"
	export default {
		name: 'about',
		data() {
			return {
				indexTap:1,
				lists:['我创建的','我加入的'],
				userList: [],
				/* 窗口尺寸 */
				winSize: {},
			}
		},
		onLoad() {
					this.getListData();
					this.getWindowSize();
		
					// #ifdef H5
					document.onLong = function(e) {
						var e = e || window.event;
						e.preventDefault();
					};
					// #endif
				},
		methods: {
			/* 获取列表数据 */
			getListData() {
				let list1 = ["工程训练","工程实践","机器学习","图像处理"];
				let list2 = ["池芝标","池芝标","池芝标","池芝标"]
				let list = []
				for (let i = 0; i < list1.length; i++) {
					// let list = []
					list.push({
						"name": list1[i],
						"info": list2[i]
					})
					// this.userList = list;
				}
				this.userList = list;
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
				console.log(e)
				console.log(e.currentTarget.dataset.index)
				if (e.currentTarget.dataset.index == 0) {
					uni.navigateTo({
						url:"../course/course"
					})
				}
			},
			open(){
			    this.$refs.popup.open()
			}
		},
		components:{
			uniPopup,
			uniIcons
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
     // overflow: ;
     padding-left: 0;
     list-style: none;
     li{
       text-align: center;
        font-size: 16px;
        -ms-flex-negative: 0;
        flex-shrink: 0;
		width: 33%;
        padding: 10px;
        margin: 5px;
     }
	 &>view{
		 margin-left: 50%;
		 
	 }
   }

 }
 .butt{
 	margin-left: 60rpx;
 }
 .tip{
	 margin: 10rpx;
	 padding: 20rpx;
	 color: #007AFF;
	 background-color: #FFFFFF;
	 text-align: center;
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
 				height: 80upx;
 				width: 80upx;
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