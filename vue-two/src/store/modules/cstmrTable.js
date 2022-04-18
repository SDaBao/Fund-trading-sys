// 这个没用，别看！！！！！！
// 这个没用，别看！！！！！！
// 这个没用，别看！！！！！！
// 这个没用，别看！！！！！！
// 这个没用，别看！！！！！！
// 这个没用，别看！！！！！！
export default {
  state: {
    tableData: [
      {
        name: 'Gu Wei',
        type: '个人',
        card_type: '身份证',
        card_id: '330102200103077256'
      }
    ]
  },
  getters: {
    getTable (state) {
      return state.tableData
    }
  },
  mutations: {
    setTable (state, data) {
      state.tableData.name = data
    }
  },
  actions: {
    actTable ({ commit }, data) {
      commit('setTable', data)
    }
  }
}
