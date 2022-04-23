export default {
  state: {
    user: '', // 登录的用户
    user_id: '',
    showLogin: false, // 用于控制是否显示登录组件
    user_info: []
  },
  getters: {
    getUser (state) {
      return state.user
    },
    getShowLogin (state) {
      return state.showLogin
    },
    getUserID (state) {
      return state.user_id
    },
    getUserInfo (state) {
      return state.user_info
    }
  },
  mutations: {
    setUser (state, data) {
      state.user = data
      console.log(state.user)
    },
    setShowLogin (state, data) {
      state.showLogin = data
      console.log(state.showLogin)
    },
    setUserID (state, data) {
      state.user_id = data
      console.log(state.user_id)
    },
    setUserInfo (state, data) {
      state.user_info.push(data)
      console.log(state.user_info)
    },
    setAccountBalance (state, data) {
      state.user_info[0].account_balance = data
    },
    cleanUserInfo (state) {
      state.user_info = []
      console.log(state.user_info)
    },
    cleanUser (state) {
      state.user = ''
      console.log(state.user)
    },
    cleanUserID (state) {
      state.user_id = ''
      console.log(state.user_id)
    }
  },
  actions: {
    actUser ({ commit }, data) {
      commit('setUser', data)
    },
    actShowLogin ({ commit }, data) {
      console.log('actShowLogin')
      commit('setShowLogin', data)
    },
    actUserID ({ commit }, data) {
      commit('setUserID', data)
    },
    actUserInfo ({ commit }, data) {
      commit('setUserInfo', data)
    }
  }
}
