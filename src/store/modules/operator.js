export default {
  state: {
    isAdmin: false, // 是否是管理员
    operator: 'ming' // 登录的操作人员
  },
  getters: {
    getOperatorName (state) {
      return state.operator
    },
    getAdmin (state) {
      return state.isAdmin
    }
  },
  mutations: {
    setOperatorName (state, data) {
      state.operator = data
    },
    setAdmin (state, data) {
      state.isAdmin = data
    }
  },
  actions: {
    setOperatorName ({ commit }, data) {
      commit('setOperatorName', data)
    },
    setAdmin ({ commit }, data) {
      commit('setAdmin', data)
    }
  }
}
