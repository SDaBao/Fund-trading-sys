import Vue from 'vue'
import Router from 'vue-router'

import AppIndex from '@/components/home/Appindex'
import Login from '@/components/Login'
import FundManagement from '@/components/home/FundManagement.vue'
import CustomerSearch from '@/components/home/CustomerSearch.vue'
import CustomerInformation from '@/components/home/CustomerInformation.vue'
import ObtainFund from '@/components/home/ObtainFund.vue'
import ProductList from '@/components/home/ProductList.vue'
import BusinessList from '@/components/home/BusinessList.vue'

Vue.use(Router)

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
            requireAuth: true
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
            requireAuth: true
          }
        },
        {
          path: 'obtainfund',
          name: 'obtainfund',
          component: ObtainFund,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'productlist',
          name: 'productlist',
          component: ProductList,
          meta: {
            requireAuth: true
          }
        },
        {
          path: 'businesslist',
          name: 'businesslist',
          component: BusinessList
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
