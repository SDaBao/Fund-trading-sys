import Vue from 'vue'
import Vuex from 'vuex'

import user from './modules/user'
import admin from './modules/admin'
import cstmrTable from './modules/cstmrTable'
import card from './modules/card'

Vue.use(Vuex)

export default new Vuex.Store({
  strict: true,
  modules: {
    user,
    admin,
    cstmrTable,
    card
  }
})
