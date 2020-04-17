<template>
	<view>
		<view class="serch">
			<view class="ser">
				<uni-search-bar @confirm="search" @input="input" ></uni-search-bar>
			</view>
			<view class="to" style="color: #007AFF;">调序</view>
		</view>
		<view class="list-all">
			<view class="list" @click="cource">
				<view>
					<image style="width: 80px; height: 80px;" src="../../static/logo.png"></image>
				</view>
				<view class="course">
					工程实践
					<view class="name">池芝标</view>
					<view>
						<button size="mini" @click="goGesture" >签到</button>
						<button size="mini" class="butt">举手</button>
						<button size="mini" class="butt">抢答</button>
					</view>
				</view>
				<view class="to">
					<uni-icons type="arrowright" size="30"></uni-icons>
				</view>
			</view>
			<view class="list">
				<view>
					<image style="width: 80px; height: 80px;" src="../../static/logo.png"></image>
				</view>
				<view class="course">
					工程训练
					<view class="name">池芝标</view>
					<view>
						<button size="mini" @click="goGesture" >签到</button>
						<button size="mini" class="butt">举手</button>
						<button size="mini" class="butt">抢答</button>
					</view>
				</view>
				<view class="to">
					<uni-icons type="arrowright" size="30"></uni-icons>
				</view>
				
			</view>
		</view>
	</view>
</template>

<script>
	import uniSearchBar from '@/components/uni-search-bar/uni-search-bar.vue'
	import uniIcons from "@/components/uni-icons/uni-icons.vue"
	export default {
		data() {
			return {
			}
		},
		onLoad() {
		},
		methods: {
			cource() {
				uni.navigateTo({
					url:"../course/course"
				})
			}
			
		},
		goGesture() {
			uni.navigateTo({
				url: '../gesture/gesture',
				success: res => {},
				fail: () => {},
				complete: () => {}
			});
		},
		components: {
			uniSearchBar,
			uniIcons,
			}
	}
</script>

<style>
	.serch{
		display: flex;
	}
	.ser{
		width: 320px;
	}
	.list-all{
		display: flex;
		flex-direction: column;
	}
	.list{
		display: flex;
		padding: 10px 15px 0 15px;
		height: 100px;
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
	.butt{
		margin-left: 10px;
	}
</style>
