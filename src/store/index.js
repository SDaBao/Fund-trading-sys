import Vue from 'vue'
import Vuex from 'vuex'

import user from './modules/user'
import operator from './modules/operator'
import cstmrTable from './modules/cstmrTable'
import card from './modules/card'

Vue.use(Vuex)

export default new Vuex.Store({
  strict: true,
  modules: {
    user,
    operator,
    cstmrTable,
    card
  }
})
