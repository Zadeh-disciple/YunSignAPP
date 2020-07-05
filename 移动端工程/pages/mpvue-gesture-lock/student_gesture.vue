<template>
	<view>
		<!-- <view>
			<text>{{location}}</text>
			<button @click="getlo">定位</button>
		</view> -->
        <view>
            <mpvue-gesture-lock :containerWidth="590" :cycleRadius="70" @end="onEnd" :password="password" ref="myges"></mpvue-gesture-lock>
        </view>
        <view class="uni-text">{{text}}</view>
		<view class="inputArea">
			<button style="border-radius:22px;" type="primary" @click="goLogin">完成签到</button>
			<!-- <button type="primary" @click="refre">重置</button> -->
		</view>
		<view style="padding: 0 10%;">
			<text style="float:right;color: green;">{{message}}</text>
		</view>
	</view>
</template>

<script>
    import mpvueGestureLock from '@/components/mpvueGestureLock';
	// import gesture from '../../components/mpvueGestureLock/index.vue'
    export default {
        components: {
            mpvueGestureLock
        },
        data() {
            return {
				title : "手势图案",
                password: [],
                text: '请设定手势',
				status:0,
				message:'',
				location:'定位中。。。',
				courseid: '',
				gesture: '',
				longitude: '',
				latitude: '',
				newcourse: {}
            }
        },
		onLoad(option) {
			console.log(option.courseid)
			this.courseid = option.courseid
			this.getlo()
		},
        methods: {
            onEnd(data) {
				console.log(data)
                if (this.password.length) {
					// console.log(this.password)
      //               if (this.password.join('') === data.join('')) {
      //                   this.text = '手势设定完成'
						// if (this.status!=0){
						// 	this.message=''
						// }
						// this.status=1
      //                   this.password = []
      //               } else {
      //                   this.text = '两次手势设定不一致'
						// if (this.status!=0){
						// 	this.message=''
						// }
						// this.status=-2
      //                   this.password = []
      //               }
                } else {
                    // this.text = '请确认手势设定'
					if (this.status!=0){
						this.message=''
					}
                    this.password = data
					this.status=-1
					this.text = '手势设定完成'
                }
            },
			refre() {
				this.$refs.myges.refresh()
			},
			goLogin() {
				const token = uni.getStorageSync('token')
				uni.request({
					url: `http://47.112.242.4:8181/api/parsejwt`,
					header: {
						'Authorization': token //将token放入请求头
					},
					success: (res) => {
						console.log(res.data.message.success)
						if (res.data.message.success !== true) {
							setTimeout(() =>{
							    uni.showToast({
							        title: '登录过期，请重新登录！',
							    	positin: top,
							        duration: 2000
							    })
							},2)
						  uni.removeStorageSync('user')
						  uni.removeStorageSync('token')
						  uni.reLaunch({
						      url: '../login/login'
						  })
						}
					}
				})
				uni.request({
					url: `http://47.112.242.4:8181/putsign/findsignBycourseId?courseid=${this.courseid}`,
					success: (res) => {
						console.log(res)
						if(res.data.length == 0) {
							return uni.showToast({
								title:'还未开启签到或签到已经截止'
							})
						}
						const distance = this.getdistance(this.longitude, this.latitude, res.data[0].longitude, res.data[0].latitude)
						// const distance = this.getdistance(118.015, 24.4231, res.data[0].longitude, res.data[0].latitude)
						console.log(distance)
						if(distance > 200) {
							return uni.showToast({
								title:'您不在签到的范围内'
							})
						}
						for(let i = 0; i< this.password.length; i++) {
							this.gesture += this.password[i].toString()
						}
						if(this.gesture !== res.data[0].gesture) {
							return uni.showToast({
								title: '签到手势错误'
							})
						} else {
							this.updateexp()
							return uni.showToast({
								title: '签到成功'
							})
						}
					}
				})
				
			},
			updateexp() {
				const token = uni.getStorageSync('token')
				uni.request({
					url: `http://47.112.242.4:8181/api/parsejwt`,
					header: {
						'Authorization': token //将token放入请求头
					},
					success: (res) => {
						console.log(res.data.message.success)
						if (res.data.message.success !== true) {
							setTimeout(() =>{
							    uni.showToast({
							        title: '登录过期，请重新登录！',
							    	positin: top,
							        duration: 2000
							    })
							},2)
						  uni.removeStorageSync('user')
						  uni.removeStorageSync('token')
						  uni.reLaunch({
						      url: '../login/login'
						  })
						}
					}
				})
				const user = uni.getStorageSync('user')
				uni.request({
					url: `http://47.112.242.4:8181/Course_user/findcourseByuserId?userid=${user.userid}`,
					success: (res) => {
						console.log(res)
						this.newcourse = {}
						for(let i = 0; i < res.data.length; i++) {
							if(res.data[i].course.courseid == this.courseid) {
								this.newcourse = res.data[i]
							}
						}
						this.newcourse.totalexp += 2
						console.log(this.newcourse)
						uni.request({
							url: 'http://47.112.242.4:8181/Course_user/updateCourse_user',
							method: 'POST',
							data: this.newcourse,
							success: (res) => {
								console.log(res)
							}
						})
					}
				})
			},
			getlo() {
				uni.getLocation({
				    type: 'gcj02',
				    success: (res) => {
						console.log('2')
				        // console.log('当前位置的经度：' + res.longitude);
				        // console.log('当前位置的纬度：' + res.latitude);
						this.longitude = res.longitude;
						this.latitude = res.latitude;
						console.log('当前位置的经度：' + this.longitude);
						console.log('当前位置的纬度：' + this.latitude);
						// this.location = '经度：' + res.longitude + ' 纬度：' + res.latitude;
						// console.log(this.location);
				    },
					fail() {
						return uni.showToast({
							title: '获取地址信息失败'
						})
					}
				})
			},
			getdistance(lat1,lng1,lat2,lng2) {
				var EARTH_RADIUS = 6378137.0;    //单位M 
				var PI = Math.PI; 
				var f = (lat1 + lat2)/2*PI/180.0;
				var g = (lat1 - lat2)/2*PI/180.0;
				var l = (lng1 - lng2)/2*PI/180.0;
				        
				var sg = Math.sin(g);
				var sl = Math.sin(l);
				var sf = Math.sin(f);
				        
				var s,c,w,r,d,h1,h2;
				var a = EARTH_RADIUS;
				var fl = 1/298.257;
				        
				sg = sg*sg;
				sl = sl*sl;
				sf = sf*sf;
				        
				s = sg*(1-sl) + (1-sf)*sl;
				c = (1-sg)*(1-sl) + sf*sl;
				        
				w = Math.atan(Math.sqrt(s/c));
				r = Math.sqrt(s*c)/w;
				d = 2*w*a;
				h1 = (3*r -1)/2/c;
				h2 = (3*r +1)/2/s;
				console.log(s,c,w,r,d,h1,h2)
				        
				return d*(1 + fl*(h1*sf*(1-sg) - h2*(1-sf)*sg))/1000;
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