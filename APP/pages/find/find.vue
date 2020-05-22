<template>
	<view>
		find
		<text>{{userList}}</text>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userList:[]
			}
		},
		onLoad() {
			this.getUserList()
		},
		methods: {
			getUserList(){
				console.log('获取user数据')
				uni.request({
					url:'http://47.112.242.4:8181/user/findAll',
					success:res=>{
						console.log(res)
						if(res.data.length == 0){
							return uni.showToast({
								title:'无数据'
							})
						}
						this.userList = res.data
					}
				})
			}
		}
	}
</script>

<style>

</style>
