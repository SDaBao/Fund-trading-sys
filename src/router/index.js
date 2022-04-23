import Vue from 'vue'
import Router from 'vue-router'

import AppIndex from '@/components/home/Appindex'
import Login from '@/components/Login'
import FundManagement from '@/components/home/FundManagement.vue'
import CustomerSearch from '@/components/home/CustomerSearch.vue'
import CustomerInformation from '@/components/home/CustomerInformation.vue'
import ObtainFund from '@/components/home/ObtainFund.vue'
import ProductList from '@/components/home/ProductList.vue'
import BusinessListToPerson from '@/components/home/BusinessListToPerson.vue'
import ProductManagement from '@/components/home/productManagement.vue'
import BusinessListToAll from '@/components/home/BusinessListToAll.vue'

Vue.use(Router)

const originalPush = Router.prototype.push
// 修改 原型对象中的push方法
Router.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'AppIndex',
      redirect: '/customersearch',
      component: AppIndex,
      children: [
        {
          path: 'fundmanagement',
          name: 'fundmanagement',
          component: FundManagement
        },
        {
          path: 'customersearch',
          name: 'customersearch',
          component: CustomerSearch
        },
        {
          path: 'customerinformation',
          name: 'customerinformation',
          component: CustomerInformation
        },
        {
          path: 'obtainfund',
          name: 'obtainfund',
          component: ObtainFund
        },
        {
          path: 'productlist',
          name: 'productlist',
          component: ProductList
        },
        {
          path: 'businesslisttoperson',
          name: 'businesslisttoperson',
          component: BusinessListToPerson
        },
        {
          path: 'productmanagement',
          name: 'productmanagement',
          component: ProductManagement,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'businesslisttoall',
          name: 'businesslisttoall',
          component: BusinessListToAll,
          meta: {
            requireAuth: true
          }
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
  // to 将要访问的路径  from 从哪个路径跳转过来  next 一个函数，表示放行
  if (to.path === '/login') return next()
  // 获取token+
  const operatorToken = sessionStorage.getItem('token')
  if (!operatorToken) return next('/login')
  else if (operatorToken !== 'AdminToken' && to.meta.requireAuth) return next('/login')
  next()
})
export default router
/*
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/index',
      name: 'AppIndex',
      component: AppIndex,
      meta: {
        requireAuth: false
      },
      children: [
        {
          path: 'fundmanagement',
          name: 'fundmanagement',
          component: FundManagement,
          meta: {
            requireAuth: false
          }
        },
        {
          path: 'customersearch',
          name: 'customersearch',
          component: CustomerSearch,
          meta: {
            requireAuth: false
          }
        },
        {
          path: 'customerinformation',
          name: 'customerinformation',
          component: CustomerInformation,
          meta: {
            requireAuth: false
          }
        },
        {
          path: 'obtainfund',
          name: 'obtainfund',
          component: ObtainFund,
          meta: {
            requireAuth: false
          }
        },
        {
          path: 'productlist',
          name: 'productlist',
          component: ProductList,
          meta: {
            requireAuth: false
          }
        },
        {
          path: 'businesslist',
          name: 'businesslist',
          component: BusinessList,
          meta: {
            requireAuth: false
          }
        },
        {
          path: 'productManagement',
          name: 'productManagement',
          component: ProductManagement,
          meta: {
            requireAuth: false
          }
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
}) */
