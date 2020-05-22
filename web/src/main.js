import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'

import axios from 'axios'
// 配置请求的根路径
// axios.defaults.baseURL = 'http://47.112.242.4:8181/'
axios.defaults.baseURL = 'http://localhost:8181/'
axios.interceptors.request.use(config => {
  // NProgress.start()
  console.log(config)
  config.headers.Authorization = window.sessionStorage.getItem('token')
  return config
})

Vue.prototype.$http = axios

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
