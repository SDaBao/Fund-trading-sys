import Vue from 'vue'
import Vuex from 'vuex'

import user from './modules/user'
import admin from './modules/admin'
// import cstmrTable from './modules/cstmrTable'

Vue.use(Vuex)

export default new Vuex.Store({
  strict: true,
  modules: {
    user,
    admin
  }
})
