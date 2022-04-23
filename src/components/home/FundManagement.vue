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
            <el-button size="medium" @click="FormVisible=true"> 银行卡绑定 </el-button>
            <el-dialog title="银行卡绑定"
                       :visible.sync="FormVisible"
                       center
                       :append-to-body="true"
                       :lock-scroll="false"
                       width="30%">
              <attachcard :isShow1="'true'"
                          @close-form1="closeform1()"></attachcard>
            </el-dialog>
            <el-button size="medium" @click="Visible=true"> 充值 </el-button>
            <el-dialog title="充值"
                       :visible.sync="Visible"
                       center
                       :append-to-body="true"
                       :lock-scroll="false"
                       width="30%">
              <deposittocard :isShow="'true'" @close-form="closeform()"></deposittocard>
            </el-dialog>
          </el-col>
        </el-row>
        <el-row>
          <el-descriptions class="margin-top" :column="2" border>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user"></i>
                客户名称
              </template>
              {{ this.cstmrName }}
            </el-descriptions-item>
          </el-descriptions>
        </el-row>
        <el-row v-for="(item, i) in this.$store.state.card.card_info" :key="i">
          <el-descriptions class="margin-top" :column="2" border>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-mobile-phone"></i>
                银行卡{{ i+1 }}卡号
              </template>
              {{ item.credit_id }}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-location-outline"></i>
                银行卡{{ i+1 }}余额
              </template>
              {{ item.credit_balance }}
            </el-descriptions-item>
          </el-descriptions>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
import attachcard from './AttachCard'
import deposittocard from './depositToCard'

export default {
  name: 'FundManagement',
  components: {
    attachcard,
    deposittocard
  },
  inject: ['setLocation'],
  created () {
    this.$store.commit('setUserID', sessionStorage.getItem('cstmr_id'))
    this.getCardInfo()
    this.setLocation()
  },
  data () {
    return {
      cstmrName: sessionStorage.getItem('cstmr_name'),
      FormVisible: false,
      Visible: false
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
          this.$store.commit('cleanCardInfo')
          this.$store.commit('setCardInfo', res.data.card_info)
          this.$store.commit('setAccountB', res.data.account_balance)
          this.$store.commit('setCardNum', res.data.n)
        })
        .catch((failResponse) => {})
    },
    headStyle () {
      return 'text-align:center'
    },
    rowStyle () {
      return 'text-align:center'
    },
    closeform () {
      this.Visible = false
    },
    closeform1 () {
      this.FormVisible = false
    }
  }
}
</script>

<style scoped>
.el-button {
  margin-top: 40px;
}
</style>
