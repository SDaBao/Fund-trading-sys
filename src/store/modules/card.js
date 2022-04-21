export default {
  state: {
    account_balance: 0, // 账户余额
    card_info: [
      // credit_id: '11111111'
      // credit_balance: '22000'
    ], // 银行卡信息
    cardNum: 0 // 银行卡个数
  },
  getters: {
    getAccountB (state) {
      return state.account_balance
    },
    getCardInfo (state) {
      return state.card_info
    },
    getCardNum (state) {
      return state.cardNum
    }
  },
  mutations: {
    setAccountB (state, data) {
      state.account_balance = data
    },
    setCardInfo (state, data) {
      for (var i = 0; i < data.length; ++i) {
        state.card_info.push(data[i])
      }
    },
    setCardNum (state, data) {
      state.cardNum = data
    }
  },
  actions: {
    actAccountB ({ commit }, data) {
      commit('setAccountB', data)
    },
    actCardInfo ({ commit }, data) {
      commit('setCardInfo', data)
    },
    actCardNum ({ commit }, data) {
      commit('setCardNum', data)
    }
  }
}
