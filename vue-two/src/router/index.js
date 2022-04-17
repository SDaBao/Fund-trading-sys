import Vue from 'vue'
import Router from 'vue-router'

import AppIndex from '@/components/home/Appindex'
import Login from '@/components/Login'
import FundManagement from '@/components/home/FundManagement.vue'
import CustomerSearch from '@/components/home/CustomerSearch.vue'
import CustomerInformation from '@/components/home/CustomerInformation.vue'

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
