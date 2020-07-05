<template>
   <div class="gestureUnlock">
     <div class="gesture">
       <ul>
         <li ref="selectLi" v-for="(item, index) in list" :key="item.id"
             :class="{'selectedOuter': password.indexOf(index) !== -1 ? true : false,
             'selectedOuter2': password.indexOf(index) !== -1 && redStyle ? true : false}">
           <span :class="{'selectedInside': password.indexOf(index) !== -1 ? true : false,
                 'selectedInside2': password.indexOf(index) !== -1 && redStyle ? true : false}">
             <!--圆心-->
             <i ref="selectLiO"></i>
           </span>
         </li>
       </ul>
     </div>
      <div class="canvasDiv">
       <!-- <canvas id="canvasClearTop">此浏览器不支持canvas</canvas> -->
 18       <canvas id="canvas"  @touchstart="start" @touchmove="move" @touchend="end">此浏览器不支持canvas</canvas>
 19     </div>
 20     <div class='incorrectTip'><span v-show="tips">incorrect pattern</span></div>
 21   </div>
 22 </template>
 23 
 24 <script>
 25   export default {
 26     name: "GestureUnlock",
 27     data () {
 28       return {
 29         list: [
 30           {id:0, top: 0, left: 0, isSelected: false},
 31           {id:1, top: 0, left: 0, isSelected: false},
 32           {id:2, top: 0, left: 0, isSelected: false},
 33           {id:3, top: 0, left: 0, isSelected: false},
 34           {id:4, top: 0, left: 0, isSelected: false},
 35           {id:5, top: 0, left: 0, isSelected: false},
 36           {id:6, top: 0, left: 0, isSelected: false},
 37           {id:7, top: 0, left: 0, isSelected: false},
 38           {id:8, top: 0, left: 0, isSelected: false},
 39         ],
 40         left: [], // 圆心x坐标
 41         top: [], // 圆心y坐标
 42         password: [], // 用来存储创建密码，从上到下，从左到右依次是123,456,789
 43         cas: '', // 画笔
 44         casClearTop:'', // 上部清除线条的画布对象
 45         clientWidth: 0,
 46         clientHeight: 0,
 47         isCorrect: true, // 密码是否且是否正确
 48         redStyle: false, // li样式是否为红色
 49         createPassword: Array, // 这个用来存一下父组件传过来的fatherPassword，因为子组件不能直接修改父组件传过来的值
 50         radius: Number, // 半径
 51         tips: false // 错误提示是否显示
 52       }
 53     },
 54     props: {
 55       // 存储确认密码，变成组件后由父组件传过来，默认是空数组
 56       fatherPassword: {
 57         default: ()=>[], // 这个地方不能写成default: []
 58         type: Array
 59       }
 60     },
 61     created () {
 62       // 存一下父组件传过来的fatherPassword，因为子组件不能直接修改父组件传过来的值
 63       this.createPassword = this.fatherPassword
 64     },
 65     mounted() {
 66       // 获取到的是每个方块中心i标签的位置，
 67       for (let i = 0; i < this.$refs.selectLiO.length; i++) {
 68         this.left.push(this.$refs.selectLiO[i].getBoundingClientRect().left)
 69         this.top.push(this.$refs.selectLiO[i].getBoundingClientRect().top)
 70       }
 71       this.radius = this.$refs.selectLiO[0].getBoundingClientRect().left - this.$refs.selectLi[0].getBoundingClientRect().left
 72       console.log('半径为：', this.radius)
 73       console.log(this.left)
 74       console.log(this.top)
 75       this.clientWidth = document.documentElement.clientWidth
 76       this.clientHeight = document.documentElement.clientHeight
 77       console.log('设备宽高：', this.clientWidth, this.clientHeight)
 78       this.cas = document.getElementById('canvas').getContext('2d');
 79       document.getElementById('canvas').width = this.clientWidth;
 80       // canvas高度为最后一个圆的圆心加半径乘以1.5，就是大于最后一行多一点
 81       document.getElementById('canvas').height = this.top[this.top.length-1] + this.radius*1.5;
 82       // this.casClearTop = document.getElementById('canvasClearTop').getContext('2d');
 83       // document.getElementById('canvasClearTop').width = this.clientWidth;
 84       // document.getElementById('canvasClearTop').height = this.top[0] - this.radius*1.5;
 85     },
 86     methods: {
 87       // 手指点下
 88       start (e) {
 89         if(e.touches.length > 1 || e.scale && e.scale !== 1) { // 多点触碰或者缩放
 90           console.log('这样不行', e)
 91         } else {
 92           console.log('start', e.touches[0].pageX , e.touches[0].pageY)
 93         }
 94       },
 95       // 手指移动
 96       move (e) {
 97         // this.casClearTop.clearRect(0,0,200,200);
 98         let nowLeft = e.touches[0].pageX
 99         let nowTop = e.touches[0].pageY
100         for (var i = 0; i < this.left.length; i++) {
101           // 圆心坐标
102           let oLeft = this.left[i]
103           let oTop = this.top[i]
104           if((oLeft - this.radius) <= nowLeft && nowLeft <= (oLeft + this.radius) && (oTop - this.radius) <= nowTop && nowTop <= (oTop + this.radius)) {
105             if (this.password.length === 0 && this.password.indexOf(i) === -1) {
106               this.password.push(i) // 直接存进密码
107             } else if(this.password.indexOf(i) === -1){
108               console.log('连中的值：', this.password[this.password.length - 1])
109               let value = this.password[this.password.length - 1] // 根据此值(下标)找出对应的this.left和this.top
110               // value是上一个点的值，i是当前连接点的值
111               // 1-9 9-1、3-7 7-3、2-8 8-2、4-6 6-4
112               if (i === 0 && value === 8 || i === 8 && value === 0 ||
113                 i === 2 && value === 6 || i === 6 && value === 2 ||
114                 i === 1 && value === 7 || i === 7 && value === 1 ||
115                 i === 3 && value === 5 || i === 5 && value === 3) {
116                 // this.password中存的是下标
117                 if (this.password.indexOf(4) === -1) {this.password.push(4)}
118               } else if(i === 2 && value === 0 || i === 0 && value === 2) { // 1-3  3-1
119                 if (this.password.indexOf(1) === -1) {this.password.push(1)}
120               } else if(i === 6 && value === 8 || i === 8 && value === 6){ // 7-9  9-7
121                 if (this.password.indexOf(7) === -1) {this.password.push(7)}
122               }else if(i === 0 && value === 6 || i === 6 && value === 0){ // 1-7  7-1
123                 if (this.password.indexOf(3) === -1) {this.password.push(3)}
124               }else if(i === 2 && value === 8 || i === 8 && value === 2){ // 3-9  9-3
125                 if (this.password.indexOf(5) === -1) {this.password.push(5)}
126               }
127               // 存密码
128               this.password.push(i)
129             }
130           }
131         }
132         this.paint(nowLeft, nowTop, true)
133       },
134       // 画线的方法
135       paint (nowX, nowY, color) {
136         // console.log('paint')
137         // this.casClearTop.clearRect(0,0,200,200); // 因为不是在这个canvas上画的，所以清了也没用
138         this.cas.clearRect(0,0,this.clientWidth,this.clientHeight); // 每次画都清空整个画布
139         this.cas.beginPath();
140         for (var i = 0; i < this.password .length; i++) {
141           this.cas.lineTo(this.left[this.password [i]], this.top[this.password [i]]); // 从这个开始
142         }
143         this.cas.lineTo(nowX, nowY);
144         if (!color) {
145           this.cas.strokeStyle = '#ff4b4b'
146         } else {
147           this.cas.strokeStyle = '#498bcb'
148         }
149         this.cas.lineJoin = "round"
150         this.cas.lineWidth = 2;
151         this.cas.stroke();
152         // 清除li内圆形区域的线条
153         this.password.forEach((item) => {
154           this.clearArcFun(this.left[item], this.top[item], this.radius)
155         })
156       },
157       // 清除li内的圆形区域
158       clearArcFun (centerX, centerY, radius) {
159         var stepClear = 1; //别忘记这一步
160         var _this = this
161         clearArc(centerX, centerY, radius);
162         function clearArc(x, y, radius){ // 圆心x,y，半径radius
163           var calcWidth = radius - stepClear;
164           var calcHeight = Math.sqrt(radius * radius - calcWidth * calcWidth);
165           var posX = x - calcWidth;
166           var posY = y - calcHeight;
167           var widthX = 2 * calcWidth;
168           var heightY = 2 * calcHeight;
169           if(stepClear <= radius){
170             _this.cas.clearRect(posX, posY, widthX, heightY);
171             stepClear += 1;
172             clearArc(x, y, radius);
173           }
174         }
175       },
176       // 手指松开
177       end () {
178         console.log('end', this.password)
179         if (this.createPassword.length === 0) { // 创建密码的第一次
180           if(this.password.length >= 4) {
181             this.tips = false
182             // 此时再调用一次paint，传undefined, undefined,避免最后一条多余的线出现
183             this.paint(undefined, undefined, true)
184             // 不变红
185             this.redStyle = false
186             this.createPassword = this.password
187             this.$emit('firstDown', {success: true})
188             // 500ms后清空样式
189             console.log('第一次设置密码createPassword：', this.createPassword)
190             console.log('第一次设置密码password：', this.password)
191             setTimeout(() => {
192               this.password = []
193               this.cas.clearRect(0,0,this.clientWidth,this.clientHeight);
194             }, 500)
195           } else if(this.password.length < 4 && this.password.length !== 0) {
196             console.log('创建密码时长度小于4')
197             this.tips = true
198             this.paint(undefined, undefined, false)
199             // 长度小于4样式为红色
200             this.redStyle = true
201             // 清空画布，颜色变正常，不然下次输入还是红色
202             setTimeout(() => {
203               this.password = []
204               this.cas.clearRect(0,0,this.clientWidth,this.clientHeight);
205               this.redStyle = false // 颜色变蓝，不然下次输入还是红色
206             }, 500)
207           }
208         } else { // 创建密码的第二次 或者 登录，不管是啥反正都是拿password和createPassword（第一次输入的密码或者父组件传过来的密码）比较
209           console.log('createPassword.length不为0，进入密码比较环节')
210           console.log('createPassword:', this.createPassword)
211           console.log('password:', this.password)
212           if (this.password.toString() === this.createPassword.toString()) {
213             this.tips = false
214             // 设置/登录成功
215             console.log('设置/登录成功')
216             this.$emit('onDrawDone', {success: true, pwd: this.password})
217             setTimeout(() => {
218               this.password = []
219               this.cas.clearRect(0,0,this.clientWidth,this.clientHeight);
220               this.redStyle = false // 没true好像就可以没有false，加上吧保险一点
221             }, 500)
222           } else if(this.password.length !== 0){ // 两次输入不一致/密码不正确    这里写this.password.length !== 0是为了防止点一下canvas也会出现输入错误的提示
223             this.tips = true
224             this.paint(undefined, undefined, false)
225             // 两次输入不一致/密码不正确 样式为红色
226             this.redStyle = true // 有true下面必得有false
227             console.log('失败')
228             // 清空画布，颜色变蓝
229             setTimeout(() => {
230               this.password = [] // 还有蓝色是因为前几个存在于那个数组，得把password清空
231               this.cas.clearRect(0,0,this.clientWidth,this.clientHeight);
232               this.redStyle = false
233               console.log(this.redStyle)
234             }, 500)
235           }
236         }
237       }
238     }
239   }
240 </script>
241 
242 <style lang="less" scoped>
243   .incorrectTip{
244     height: .5rem;
245     span{
246       /*line-height: .8rem;*/
247       color: #ff4b4b;
248     }
249   }
250   .gestureUnlock{
251     margin: 0 auto;
252   }
253   .gesture{
254     margin: 1.0rem auto 0;
255     ul{
256       margin: auto;
257       display: flex;
258       width: 8.88rem;
259       height: 8.88rem;
260       justify-content: space-between;
261       align-content: space-between;
262       flex-wrap: wrap;
263       li{
264         display: flex;
265         align-items:center;
266         justify-content:center;
267         margin: 0.45rem 0.45rem;
268         border-radius: 50%;
269         width: 1.2rem;
270         height: 1.2rem;
271         border: 0.08rem solid #e0e0e0;
272         /*宽度是1.2rem,边框是0.08rem,所以半径是0.68rem,1rem=37.5px，所以0.68x37.5 = 25.5px*/
273         span{
274           display: flex;
275           align-items:center;
276           justify-content:center;
277           width: 0.40rem;
278           height: 0.40rem;
279           border-radius: 50%;
280           i{
281             display: inline-block;
282             width: 1px;
283             height: 1px;
284           }
285         }
286       }
287       /*被选中的样式*/
288       .selectedOuter{
289         border: 0.08rem solid #498bcb;
290         .selectedInside{
291           background: #498bcb;
292         }
293       }
294       .selectedOuter2{
295         border: 0.08rem solid #ff4b4b;
296         .selectedInside2{
297           background: #ff4b4b;
298         }
299       }
300     }
301   }
302   .canvasDiv{
303     position: fixed;
304     top:0;
305     left: 0;
306     // background: rgba(0,0,0,0.1);
307     z-index: 100;
308     #canvasClearTop{
309       position: absolute;
310       top: 0;
311       left: 0;
312       background: rgba(255,0,0,0.2)
313     }
314   }
315 </style>