import Vue from 'vue'
import App from './App'

import activity from './pages/course/activity.vue'
Vue.component('activity',activity)

import resource from './pages/course/resource.vue'
Vue.component('resource',resource)

import member from './pages/course/member.vue'
Vue.component('member',member)

import message from './pages/course/message.vue'
Vue.component('message',message)

import detail from './pages/course/detail.vue'
Vue.component('detail',detail)

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
