<template>
	<view>
		<view class="uni-text">课程名称:{{text1}}</view>
		<view class="uni-text">任课教师:{{text2}}</view>
		<view class="inputArea">
			<button style="border-radius:22px;" type="primary" @click="goSearch">加入</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				text1: '工程实际',
				text2:'池之标'
			}
		},
		methods: {
			
		}
	}
</script>

<style>
	.uni-text{
	    font-size: 40upx;
	    text-align: left;
	    margin-top: 30upx;
	}
	.inputArea {
		padding: 20upx 10%;
	}
	.inputClass {
		border: 2px solid #ccc;
		border-radius: 22px;
		outline: 0;
		padding: 20upx 20%;
	}

</style>
