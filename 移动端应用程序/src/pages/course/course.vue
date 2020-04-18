<template>
	<view>
		<view>未分组</view>
		<view class="list-all">
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
		</view>
		<view class="baseView" id="baseView">
			<adTabbar>
				<adTabbarItem text="资源" dataCur="resource" class="maxWidth" @click="navClick" :textColor="curPage=='resource'? '#4adede':'#9B9B9B'" :icon="'../../static/tabs/ziyuan' + [curPage=='resource'?'-active':''] + '.png'"></adTabbarItem>
				<adTabbarItem text="成员" dataCur="member" class="maxWidth" @click="navClick" :textColor="curPage=='member'? '#4adede':'#9B9B9B'" :icon="'../../static/tabs/users' + [curPage=='member'?'-active':''] + '.png'"></adTabbarItem>
				<adTabbarItem text="活动" dataCur="activity" class="maxWidth" @click="navClick" :textColor="curPage=='activity'? '#4adede':'#9B9B9B'" :icon="'../../static/tabs/activity' + [curPage=='activity'?'-active':''] + '.png'"></adTabbarItem>
				<adTabbarItem text="消息" dataCur="message" class="maxWidth" @click="navClick" :textColor="curPage=='message'? '#4adede':'#9B9B9B'" :icon="'../../static/tabs/message' + [curPage=='message'?'-active':''] + '.png'"></adTabbarItem>
				<adTabbarItem text="详情" dataCur="detail" class="maxWidth" @click="navClick" :textColor="curPage=='detail'? '#4adede':'#9B9B9B'" :icon="'../../static/tabs/detail' + [curPage=='detail'?'-active':''] + '.png'"></adTabbarItem>
			</adTabbar>
			<!--把page放在Tabbar下面是因为page中要获取tabbar高度做bottom，需要让tabbar先渲染-->
			<!-- <resource v-if="curPage=='resource'"></resource>
			<member v-if="curPage=='member'"></member>
			<activity v-if="curPage=='activity'"></activity>
			<message v-if="curPage=='message'"></message> -->
		</view>
	</view>
</template>

<script>
	import adTabbar from '@/components/andy-ADTabbar/andy-ADTabbar.vue';
	import adTabbarItem from '@/components/andy-ADTabbar/andy-ADTabbarItem.vue';
	
	export default {
		components: {
		    adTabbar,
			adTabbarItem
		},
		data() {
			return {
				curPage: 'activity',
			}
		},
		onLoad() {
		},
		methods: {
			// 导航栏切换
			navClick: function(e) {
				this.curPage = e.currentTarget.dataset.cur
			}
		}
	}
</script>

<style>
	.buttonView {
		width: 100upx;
		height: 100upx;
		border-radius: 50upx;
		margin-top: -60upx;
		background-color: #349E40;
		margin-bottom: 10upx;
		
		color: #FFFFFF;
		line-height: 100upx;
		text-align: center;
		font-size: 50upx;
	}
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