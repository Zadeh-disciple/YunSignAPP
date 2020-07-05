<template>
	<view>
		<view>
			<text>班课号：</text>
			<input  style="text-align: center;" placeholder="请输入5位班课号" v-model="courseid" />
		</view>
		<view style="position: absolute; bottom: 10rpx; width: 100%;">
			<button type="primary" @click="join(courseid)">加入班课</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				courseid:""
			}
		},
		methods: {
			join(courseid) 
			{
				 uni.navigateTo({
				    url: "../course-join/course-detail?courseid="+courseid,
				 });
			}
		}
	}
</script>

<style>

</style>
