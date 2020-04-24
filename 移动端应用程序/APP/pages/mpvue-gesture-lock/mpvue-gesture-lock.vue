<template>
	<view>
        <view>
            <mpvue-gesture-lock :containerWidth="590" :cycleRadius="70" @end="onEnd" :password="password"></mpvue-gesture-lock>
        </view>
        <view class="uni-text">{{text}}</view>
		<view class="inputArea">
			<view class="inputArea">
				<input v-model="signuptime" placeholder="设置签到时间(秒)" type="number" maxlength="11" class="inputClass" />
			</view>
			<button style="border-radius:22px;" type="primary" @click="goLogin">设置手势</button>
		</view>
		<view style="padding: 0 10%;">
			<text style="float:right;color: green;">{{message}}</text>
		</view>
	</view>
</template>

<script>
    import mpvueGestureLock from '@/components/mpvueGestureLock';

    export default {
        components: {
            mpvueGestureLock
        },
        data() {
            return {
				title : "手势图案",
                password: [],
                text: '请设定手势',
				status:'',
				message:'',
				signuptime:'',
            }
        },
        methods: {
            onEnd(data) {
				let signuptime = this.signuptime
                if (this.password.length) {
                    if (this.password.join('') === data.join('')) {
                        this.text = '手势设定完成'
						if (this.status!=0){
							this.message=''
						}
						this.status=1
                        this.password = []
                    } else {
                        this.text = '两次手势设定不一致'
						if (this.status!=0){
							this.message=''
						}
						this.status=-2
                        this.password = []
                    }
                } else {
                    this.text = '请确认手势设定'
					if (this.status!=0){
						this.message=''
					}
                    this.password = data
					this.status=-1
                }
            },
			goLogin() {
				if (this.status == -2){
					this.message = "两次手势设定不一致!请重新设置！"
					return false
				}
				if (this.status == -1){
					this.message = "请确认手势设定！"
					return false
				}
				if (!this.signuptime){
					this.message = "请设置签到时间！"
					return false
				}
				uni.navigateTo({
					url: '../login/login',
					success: res => {},
					fail: () => {},
					complete: () => {}
				});
			}
        }
    }
</script>

<style>
    .uni-text{
        font-size: 30upx;
        text-align: center;
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
