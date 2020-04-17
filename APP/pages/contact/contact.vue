<template>
	<view class="commom">
		<uni-list>
		    <uni-list-item title="Jack" thumb="../../static/logo.png"></uni-list-item>
		</uni-list>
		<view class="list-top">
			<view class="note">
				<text style="display: inline-block; text-align: center; color: #00ff7f; font-size: 14px;">0</text>
				<text style="display: inline-block; text-align: center; color: #8f8f8f; font-size: 12px;">经验值</text>
			</view>
			<view class="note">
				<text style="display: inline-block; text-align: center; color: #00ff7f; font-size: 14px;">0</text>
				<text style="display: inline-block; text-align: center; color: #8f8f8f; font-size: 12px;">魅力值</text>
			</view>
			<view class="note">
				<text style="display: inline-block; text-align: center; color: #00ff7f; font-size: 14px;">0</text>
				<text style="display: inline-block; text-align: center; color: #8f8f8f; font-size: 12px;">蓝豆</text>
			</view>
			<view class="note-right">
				<text style="display: inline-block; text-align: center; color: #00ff7f; font-size: 14px;">0</text>
				<text style="display: inline-block; text-align: center; color: #8f8f8f; font-size: 12px;">心意</text>
			</view>
		</view>
		<view class="prize">
			<view class="ionlist">
				<view style="width: 50px;height: 50px ;background: #00aaff; border-radius: 60rpx; margin: 5px auto; line-height: 50px;">
					<uni-icons type="pengyouquan" size="30" color="#ffffff"></uni-icons>
				</view>
				<text style="font-size: 15px;">我的空间</text>
			</view>
			<view class="ionlist">
				<view style="width: 50px;height: 50px ;background: #ffaa00; border-radius: 60rpx; margin: 5px auto; line-height: 50px;">
					<uni-icons type="star-filled" size="30" color="#ffffff"></uni-icons>
				</view>
				<text style="font-size: 15px;">我的收藏</text>
			</view>
			<view class="ionlist">
				<view style="width: 50px;height: 50px ;background: #ff557f; border-radius: 60rpx; margin: 5px auto; line-height: 50px;">
					<uni-icons type="shop" size="30" color="#ffffff"></uni-icons>
				</view>
				<text style="font-size: 15px;">心意卡片</text>
			</view>
		</view>
		<uni-list class="top">
		    <uni-list-item title="我的勋章" rightText="快来获取第一个勋章"></uni-list-item>
		</uni-list>
		<uni-list>
		    <uni-list-item title="文件暂存区" ></uni-list-item>
			<uni-list-item title="分享给朋友" ></uni-list-item>
			<uni-list-item title="用户协议" ></uni-list-item>
			<uni-list-item title="隐私政策" ></uni-list-item>
			<uni-list-item title="设置" ></uni-list-item>
		</uni-list>
	</view>
</template>

<script>
	import uniList from "@/components/uni-list/uni-list.vue"
	import uniListItem from "@/components/uni-list-item/uni-list-item.vue"
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
		},
		components: {uniList,uniListItem}
	}
</script>

<style>
	.commom{
		background-color: #f7f7f7;
	}
	.top{
		margin-bottom: 40rpx;
	}
	.list-top{
		display: flex;
		// padding: 10px 15px 0 15px;
		height: 90rpx;
		margin-bottom: 50rpx;
		background-color: #FFFFFF;
	}
	.note{
		display: flex;
		flex-direction: column;
		width: 25%;
		/* border-top-style: ridge;
		border-bottom-style: ridge;
		border-right-style: ridge; */
	}
	.note-right{
		display: flex;
		flex-direction: column;
		width: 25%;
		/* border-top-style: ridge;
		border-bottom-style: ridge; */
	}
	.prize{
		display: flex;
		margin-top: 30px;
		/* border-top-style: ridge;
		border-bottom-style: ridge; */
		margin-bottom: 50rpx;
		background-color: #FFFFFF;
	}
	.ionlist{
		width: 33.3%;
		text-align: center;
	}
</style>
