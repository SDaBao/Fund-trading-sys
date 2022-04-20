// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import store from './store'
import 'element-ui/lib/theme-chalk/index.css'
// import Global from './Global'
// Vue.use(Global)
// 设置反向代理，前端请求默认发送到 http://localhost:8443/api
var axios = require('axios')
// axios.defaults.baseURL = 'http://10.20.100.71:13333/'
// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
Vue.prototype.$axios = axios
Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(VueRouter)
// 全局请求拦截器
// axios.interceptors.request.use(
//   config => {
//     return config
//   },
//   error => {
//     // 跳转error页面
//     router.push({ path: '/error' })
//     return Promise.reject(error)
//   }
// )
// // 全局响应拦截器
// axios.interceptors.response.use(
//   res => {
//     if (res.data.code === '401') {
//       // 401表示没有登录
//       // 提示没有登录
//       Vue.prototype.notifyError(res.data.msg)
//       // 修改vuex的showLogin状态,显示登录组件
//       store.dispatch('setShowLogin', true)
//     }
//     if (res.data.code === '500') {
//       // 500表示服务器异常
//       // 跳转error页面
//       router.push({ path: '/error' })
//     }
//     return res
//   },
//   error => {
//     // 跳转error页面
//     router.push({ path: '/error' })
//     return Promise.reject(error)
//   }
// )

// // 全局拦截器,在进入需要用户权限的页面前校验是否已经登录
// router.beforeResolve((to, from, next) => {
//   const loginUser = store.state.user.user
//   // 判断路由是否设置相应校验用户权限
//   if (to.meta.requireAuth) {
//     if (!loginUser) {
//       // 没有登录，显示登录组件
//       store.dispatch('setShowLogin', true)
//       if (from.name == null) {
//         // 此时，是在页面没有加载，直接在地址栏输入链接，进入需要登录验证的页面
//         next('/')
//         return
//       }
//       // 终止导航
//       next(false)
//       return
//     }
//   }
//   next()
// })

// router.beforeEach((to, from, next) => {
//   if (to.meta.requireAuth) {
//     if (store.state.user.username) {
//       next()
//     } else {
//       next({
//         path: 'login',
//         query: {redirect: to.fullPath}
//       })
//     }
//   } else {
//     next()
//   }
// }
// )

// // 相对时间过滤器,把时间戳转换成时间
// // 格式: 2020-02-25 21:43:23
// Vue.filter('dateFormat', (dataStr) => {
//   var time = new Date(dataStr)
//   function timeAdd0 (str) {
//     if (str < 10) {
//       str = '0' + str
//     }
//     return str
//   }
//   var y = time.getFullYear()
//   var m = time.getMonth() + 1
//   var d = time.getDate()
//   var h = time.getHours()
//   var mm = time.getMinutes()
//   var s = time.getSeconds()
//   return y + '-' + timeAdd0(m) + '-' + timeAdd0(d) + ' ' + timeAdd0(h) + ':' + timeAdd0(mm) + ':' + timeAdd0(s)
// })

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>'
})
