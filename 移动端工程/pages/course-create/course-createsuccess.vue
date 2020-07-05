<template>
	<view style="background-color: #e6e6e6;text-align: center;">
		<view>班课创建成功！</view>
		<image src="../../static/success.gif"></image>
		<view>班课号：{{bankenum}}</view>
		<view>
			<tki-qrcode 
			v-if="ifShow" 
			cid="qrcode1" 
			ref="qrcode" 
			:val="val" 
			:size="size" 
			:unit="unit" 
			:background="background" 
			:foreground="foreground"  
			:lv="lv" :onval="onval" 
			:loadMake="loadMake" 
			:usingComponents="true" 
			@result="qrR" />
		</view>
		<button type="primary" @click="back">完成</button>
	</view>
</template>

<script>
	import tkiQrcode from "../../components/tki-qrcode/tki-qrcode.vue"
	export default {
		data() {
			return {
				bankenum: '',
				ifShow: true,
				size: 200, // 二维码大小
				unit: 'upx', // 单位
				val: '7872',
				background: '#ffffff', // 背景色
				foreground: '#000000', // 前景色
				pdground: '#32dbc6', // 角标色
				icon: '', // 二维码图标
				iconsize: 40, // 二维码图标大小
				lv: 3, // 二维码容错级别 ， 一般不用设置，默认就行
				onval: false, // val值变化时自动重新生成二维码
				loadMake: true, // 组件加载完成后自动生成二维码
				src: '' // 二维码生成后的图片地址或base64
			}
		},
		onLoad: function (option) {
			console.log(option.id);
			this.bankenum = option.id;
			// this.getnum();
			this.creatQrcode()
		},
		methods: {
			getnum() {
				// const num = Math.random()
				this.bankenum = Math.floor(Math.random() * 9000 + 1000)
				this.val = this.bankenum.toString()
			},
			back() {
				uni.switchTab({
					url:'../index/index'
				})
			},
			creatQrcode() {
				this.$refs.qrcode._makeCode()
			},
			qrR(res) {
				this.src = res
			}
		},
		components: {tkiQrcode}
	}
</script>

<style>

</style>
