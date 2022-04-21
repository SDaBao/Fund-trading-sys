<template>
  <div
    id="fundmanagement"
    style="height: 80%; width: 80%; transform: translate(12%, 12%)"
  >
    <el-card
      class="box-card"
      style="height: calc(100% - 40px); height: calc(100% - 40px); margin: 20px"
      shadow="hover"
    >
      <div>
        <el-row>
          <el-col :span="18">
            <p style="font-size: 30px">资金信息</p>
          </el-col>
          <el-col :span="6">
            <el-button size="medium"> 银行卡绑定 </el-button>
            <el-button size="medium"> 充值 </el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-descriptions class="margin-top" :column="2" :size="size" border>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user"></i>
                客户名称
              </template>
              {{ this.$store.state.user.user_info[0].cstmr_name }}
            </el-descriptions-item>
          </el-descriptions>
        </el-row>
        <el-row>
          <el-descriptions class="margin-top" :column="2" :size="size" border>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-mobile-phone"></i>
                银行卡1卡号
              </template>
              {{ this.$store.state.card.card_info[0].credit_id }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-location-outline"></i>
                银行卡1余额
              </template>
              {{ this.$store.state.card.card_info[0].credit_balance }}
            </el-descriptions-item>
          </el-descriptions>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'FundManagement',
  created () {
    this.getCardInfo()
  },
  data () {
    return {
      size: 'medium'
    }
  },
  methods: {
    getCardInfo () {
      this.$axios
        .get('/api/user/fund', {
          params: {
            cstmr_id: this.$store.state.user.user_id
          }
        })
        .then((res) => {
          // console.log(res.data.card_info.length)
          // console.log(res.data.card_info[0])
          this.$store.commit('setCardInfo', res.data.card_info)
          this.$store.commit('setAccountB', res.data.account_balance)
          this.$store.commit('setCardNum', res.data.n)
          // console.log(this.$store.state.card.card_info)
        })
        .catch((failResponse) => {})
    },
    headStyle () {
      return 'text-align:center'
    },
    rowStyle () {
      return 'text-align:center'
    }
  }
}
</script>

<style scoped>
.el-button {
  margin-top: 40px;
}
</style>
