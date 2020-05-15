<template>
	<view>
		杜宏庆20200514测试
		<!-- <view class="text-box"> -->
		<text>{{testData}}</text>
		<text>{{testData.length}}</text>
		<!-- </view> -->
	</view>
</template>

<script>
	export default {
		data() {
			return {
				testData: []
			}
		},
		 // dataType: 'jsonp',  
		 // crossDomain: true,
		onLoad() {
			this.getTestData()
		},
		methods: {
			// 获取测试数据
			getTestData(){
				console.log('获取测试数据')
				uni.request({
					url: 'http://localhost:8080/demo/list',
					success:res=>{
						console.log(res)
						// if(res.data.status !== 0){
						if(res.data.length == 0){
							return uni.showToast({
								title: '获取数据失败'
							})
						}
						this.testData = res.data
						console.log(this.testData)
					}
				})
			}
		}
	}
</script>

<style>

</style>
