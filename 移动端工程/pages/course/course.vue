<template>
	<view>
		<view>
			<uni-nav-bar left-icon="back" left-text="返回" @clickLeft="goback">
				<view style="position: absolute;left: 40%;top: 0;">{{coursename}}</view>
			</uni-nav-bar>
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
			<resource v-if="curPage=='resource'"></resource>
			<member v-if="curPage=='member'"></member>
			<activity v-if="curPage=='activity'"></activity>
			<message v-if="curPage=='message'"></message>
			<detail v-if="curPage=='detail'"></detail>
		</view>
	</view>
</template>

<script>
	import adTabbar from '@/components/andy-ADTabbar/andy-ADTabbar.vue';
	import adTabbarItem from '@/components/andy-ADTabbar/andy-ADTabbarItem.vue';
	import uniNavBar from "@/components/uni-nav-bar/uni-nav-bar.vue";
	export default {
		components: {
		    adTabbar,
			adTabbarItem,
			uniNavBar
		},
		data() {
			return {
				curPage: 'activity',
				coursename: ''
			}
		},
		onLoad(option) {
			console.log(option)
			uni.setStorageSync('courseid',option.courseid)
			uni.request({
				url:'http://47.112.242.4:8181/course/findById',
				data:{
					courseid:option.courseid
				},
				method:'POST',
				success: (res) => {
					console.log(res)
					this.coursename = res.data.coursename
				}
			})
		},
		methods: {
			// 导航栏切换
			navClick: function(e) {
				this.curPage = e.currentTarget.dataset.cur
			},
			goback() {
				uni.switchTab({
					url:'../index/index'
				})
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