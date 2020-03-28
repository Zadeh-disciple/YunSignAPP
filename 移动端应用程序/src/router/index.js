import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Err404 from '../components/error-pages/404.vue'
import Err403 from '../components/error-pages/403.vue'
import Err500 from '../components/error-pages/500.vue'
import Forgetpwd from '../components/forgetpwd.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  { path: '/home', component: Home },
  { path: '/404', component: Err404 },
  { path: '/401', component: Err403 },
  { path: '/500', component: Err500 },
  { path: '/forgetpwd', component: Forgetpwd },
  { path: '*', redirect: '/404' }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  // to 将要访问的路径
  // from 代表从哪个路径跳转而来
  // next 是一个函数，表示放行  next() 放行  next('/login') 强制跳转
  if (to.path === '/login' || to.path === '/forgetpwd') return next()
  const tokenStr = window.sessionStorage.getItem('token')
  if (!tokenStr) return next('/login')
  next()
})

export default router
