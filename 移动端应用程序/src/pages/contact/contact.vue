<template>
	<view class="list-all">
		<view class="list-top">
			<view>
				<image style="width: 50px; height: 50px; padding-left: 10px;" src="../../static/logo.png"></image>
			</view>
			<view class="name">Jack</view>
			<view class="to">
				<uni-icons type="arrowright" size="30"></uni-icons>
			</view>
		</view>
		<view class="list-top">
			<view class="note">
				<text style="display: inline-block; text-align: center; color: #00ff7f;">0</text>
				<text style="display: inline-block; text-align: center; color: #8f8f8f; font-size: 15px;">经验值</text>
			</view>
			<view class="note">
				<text style="display: inline-block; text-align: center; color: #00ff7f;">0</text>
				<text style="display: inline-block; text-align: center; color: #8f8f8f; font-size: 15px;">经验值</text>
			</view>
			<view class="note">
				<text style="display: inline-block; text-align: center; color: #00ff7f;">0</text>
				<text style="display: inline-block; text-align: center; color: #8f8f8f; font-size: 15px;">经验值</text>
			</view>
			<view class="note-right">
				<text style="display: inline-block; text-align: center; color: #00ff7f;">0</text>
				<text style="display: inline-block; text-align: center; color: #8f8f8f; font-size: 15px;">经验值</text>
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
		<view class="list" style="margin-top: 15px; margin-bottom: 15px; border-bottom-style: ridge;">
			<text style="margin-top: 8px;">我的勋章</text>
			<text style="color: #999999; margin-left: 60px; margin-top: 8px; margin-right: 20px;">快来获取第一个勋章</text>
			<view class="to">
				<uni-icons type="arrowright" size="30"></uni-icons>
			</view>
		</view>
		<view class="list">
			<text style="margin-right:230px ; margin-top: 8px;">文件暂存区</text>
			<view class="to">
				<uni-icons type="arrowright" size="30"></uni-icons>
			</view>
		</view>
		<view class="list">
			<text style="margin-right:230px ; margin-top: 8px;">分享给朋友</text>
			<view class="to">
				<uni-icons type="arrowright" size="30"></uni-icons>
			</view>
		</view>
		<view class="list">
			<text style="margin-right:250px ; margin-top: 8px;">用户协议</text>
			<view class="to">
				<uni-icons type="arrowright" size="30"></uni-icons>
			</view>
		</view>
		<view class="list">
			<text style="margin-right:250px ; margin-top: 8px;">隐私政策</text>
			<view class="to">
				<uni-icons type="arrowright" size="30"></uni-icons>
			</view>
		</view>
		<view class="list" style="border-bottom-style:ridge ;">
			<text style="margin-right:285px ; margin-top: 8px;">设置</text>
			<view class="to">
				<uni-icons type="arrowright" size="30"></uni-icons>
			</view>
		</view>
	</view>
</template>
	
<script>
	import uniIcons from "@/components/uni-icons/uni-icons.vue"
	export default {
	    components: {uniIcons}
	}
</script>

<style>
	.list-all{
		display: flex;
		flex-direction: column;
	}
	.list-top{
		display: flex;
		// padding: 10px 15px 0 15px;
		height: 55px;
	}
	.name{
		width: 300px;
		padding-left: 15px;
		align-self: center;
	}
	.to{
		align-self: center;
		margin-right: 5px;
	}
	.note{
		display: flex;
		flex-direction: column;
		width: 25%;
		border-top-style: ridge;
		border-bottom-style: ridge;
		border-right-style: ridge;
	}
	.note-right{
		display: flex;
		flex-direction: column;
		width: 25%;
		border-top-style: ridge;
		border-bottom-style: ridge;
	}
	.prize{
		display: flex;
		margin-top: 30px;
		border-top-style: ridge;
		border-bottom-style: ridge;
	}
	.ionlist{
		width: 33.3%;
		text-align: center;
	}
	.list{
		display: flex;
		height: 40px;
		text-align: center;
		padding-left: 15px;
		border-top-style: ridge;
	}
</style>
