<template name="activity">
	<view>
		<view style="font-size: 30rpx;">未分组</view>
		<uni-list>
		    <uni-list-item title="任务1" note="已完成" thumb="../../static/logo.png"></uni-list-item>
			<uni-list-item title="任务2" note="已完成" thumb="../../static/logo.png"></uni-list-item>
			<uni-list-item title="任务3" note="已完成" thumb="../../static/logo.png"></uni-list-item>
			<uni-list-item title="任务4" note="已完成" thumb="../../static/logo.png"></uni-list-item>
		</uni-list>
		<!-- <view class="list-all">
			<view class="list-top">
				<view>
					<image style="width: 50px; height: 50px; align-self: center;" src="../../static/logo.png"></image>
				</view>
				<view class="course">
					任务1
					<view class="name">已完成</view>
				</view>
				<view class="to">
					<uni-icons type="arrowright" size="30"></uni-icons>
				</view>
				
			</view>
			<view class="list">
				<view>
					<image style="width: 50px; height: 50px; align-self: center;" src="../../static/logo.png"></image>
				</view>
				<view class="course">
					任务2
					<view class="name">已完成</view>
				</view>
				<view class="to">
					<uni-icons type="arrowright" size="30"></uni-icons>
				</view>
			</view>
			<view class="list">
				<view>
					<image style="width: 50px; height: 50px; align-self: center;" src="../../static/logo.png"></image>
				</view>
				<view class="course">
					任务1
					<view class="name">已完成</view>
				</view>
				<view class="to">
					<uni-icons type="arrowright" size="30"></uni-icons>
				</view>
				
			</view>
		</view> -->
	</view>
</template>

<script>
	import uniList from "@/components/uni-list/uni-list.vue"
	import uniListItem from "@/components/uni-list-item/uni-list-item.vue"
	export default {
		name:"activity",
		// data() {
		// }
	}
</script>



<style>
	.list-all{
		display: flex;
		flex-direction: column;
	}
	.list-top{
		display: flex;
		padding: 10px 15px 0 15px;
		height: 60px;
		border-top-style: ridge;
		border-bottom-style: ridge;
	}
	.list{
		display: flex;
		padding: 10px 15px 0 15px;
		height: 60px;
		border-bottom-style: ridge;
	}
	.course{
		width: 300px;
		display: flex;
		flex-direction: column;
		padding-left: 15px;
	}
	.name{
		color: #9f9f9f;
		font-size: 16px;
		margin-bottom: 5px;
	}
	.to{
		align-self: center;
	}
</style>
