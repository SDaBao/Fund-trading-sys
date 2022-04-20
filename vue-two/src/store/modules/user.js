export default {
  state: {
    user: '', // 登录的用户
    showLogin: false, // 用于控制是否显示登录组件
    tableData: [
      // {
      //   name: 'Gu Wei',
      //   type: '个人',
      //   card_type: '身份证',
      //   card_id: '330102200103077256'
      // }
    ]
  },
  getters: {
    getUser (state) {
      return state.user
    },
    getShowLogin (state) {
      return state.showLogin
    },
    getTable (state) {
      return state.tableData
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
    setcstmrTable (state, data) {
      // state.tableData[0][data.key] = data.name
      // console.log(state.tableData[0][data.key])
      state.tableData.push({
        name: data.name,
        type: data.type,
        card_type: data.card_type,
        card_id: data.card_id
      })
    },
    setTable (state) {
      state.tableData = []
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
    actTable ({ commit }, data) {
      commit('setTable', data)
    }
  }
}
