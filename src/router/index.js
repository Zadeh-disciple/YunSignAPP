import Vue from 'vue'
import VueRouter from 'vue-router'
import Err500 from '../components/errorpages/500.vue'
import Err404 from '../components/errorpages/404.vue'
import Err408 from '../components/errorpages/408.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/404', component: Err404 },
  { path: '/500', component: Err500 },
  { path: '/408', component: Err408 }
]

const router = new VueRouter({
  routes
})

export default router
