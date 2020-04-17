<template>
	<view style="padding-top: 40upx;">
		<view class="inputArea">
			<input v-model="loginPhone" placeholder="请输入班课号" type="number" maxlength="11" class="inputClass" />
		</view>
		<view style="padding: 0 10%;">
			<text style="color: red;">{{message}}</text>
		</view>
		<view class="inputArea">
			<button style="border-radius:22px;" type="primary" @click="goSearch">搜索</button>
		</view>
	</view>
</template>

<script>
	export default {
		methods: {
			goSearch() {
				uni.navigateTo({
					url: './join',
					success: res => {},
					fail: () => {},
					complete: () => {}
				});
			}
		}
	}
</script>

<style>
	.inputArea {
		padding: 20upx 10%;
	}

	.inputClass {
		border: 2px solid #ccc;
		border-radius: 22px;
		outline: 0;
		padding: 8px 15px;
	}
</style>
