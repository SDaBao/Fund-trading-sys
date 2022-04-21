import { CSTMR_TYPE_ORM, DOCUMENT_TYPE_ORM } from '../../constant'

var cstmrTypeOrm = CSTMR_TYPE_ORM
var documentTypeOrm = DOCUMENT_TYPE_ORM

export default {
  state: {
    tableData: [
      // {
      //   user_id: '10000'
      //   name: 'Gu Wei',
      //   type: '个人',
      //   card_type: '身份证',
      //   card_id: '330102200103077256'
      // },
    ]
  },
  getters: {
    getTable (state) {
      return state.tableData
    }
  },
  mutations: {
    setcstmrTable (state, data) {
      // state.tableData[0][data.key] = data.name
      // console.log(state.tableData[0][data.key])
      state.tableData.push({
        user_id: data.user_id,
        name: data.name,
        type: cstmrTypeOrm[data.type],
        card_type: documentTypeOrm[data.card_type],
        card_id: data.card_id
      })
    },
    setTable (state) {
      state.tableData = []
    }
  },
  actions: {
    actTable ({ commit }, data) {
      commit('setTable', data)
    }
  }
}
