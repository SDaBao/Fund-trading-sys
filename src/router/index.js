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
import ProductManagement from '@/components/home/productManagement.vue'

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
})
