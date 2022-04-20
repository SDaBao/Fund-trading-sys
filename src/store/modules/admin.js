export default {
  state: {
    admin: 'ming' // 登录的操作人员
  },
  getters: {
    getAdmin (state) {
      return state.admin
    }
  },
  mutations: {
    setAdmin (state, data) {
      state.admin = data
    }
  },
  actions: {
    setAdmin ({ commit }, data) {
      commit('setAdmin', data)
    }
  }
}
