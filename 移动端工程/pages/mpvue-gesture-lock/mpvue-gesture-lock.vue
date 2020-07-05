<template>
	<view>
        <view>
            <mpvue-gesture-lock :containerWidth="590" :cycleRadius="70" @end="onEnd" :password="password"></mpvue-gesture-lock>
        </view>
        <view class="uni-text">{{text}}</view>
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
                text: '请设定手势'
            }
        },
        methods: {
            onEnd(data) {
                if (this.password.length) {
                    if (this.password.join('') === data.join('')) {
                        this.text = '手势设定完成'
                        this.password = []
                    } else {
                        this.text = '两次手势设定不一致'
                        this.password = []
                    }
                } else {
                    this.text = '请确认手势设定'
                    this.password = data
                }
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
</style>
