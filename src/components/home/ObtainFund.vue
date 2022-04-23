<template>
  <div id="ObtainFund" style="height: 100%; width: 100%">
    <el-card
      class="box-card"
      style="
        height: calc(100% - 40px);
        height: calc(100% - 40px);
        margin: 20px;
        padding: 30px;
      "
      shadow="always"
    >
      <div>
        <el-row>
          <h1>持有产品</h1>
        </el-row>
        <el-row>
          <el-col>
            <template>
              <el-table
                :data="fundList"
                style="width: 100%"
                :header-cell-style="headStyle"
                :cell-style="rowStyle"
              >
                <el-table-column label="产品代码" min-width="200px">
                  <template slot-scope="scope">
                    <span>{{ scope.row.prdct_id }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="产品名称" min-width="200px">
                  <template slot-scope="scope">
                    <span>{{ scope.row.prdct_name }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="持有份额" min-width="200px">
                  <template slot-scope="scope">
                    <span>{{ scope.row.prdct_shares.toFixed(2) }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="操作" min-width="200px">
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      @click="
                        dialogFormVisible = true
                        fundRedemptionForm(scope.row.prdct_id, scope.row.prdct_name, scope.row.prdct_shares)
                      "
                    >
                      赎 回
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
            </template>
          </el-col>
        </el-row>
      </div>
    </el-card>
    <!-- 赎回对话框 -->
    <el-dialog title="赎回" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="产品ID" :label-width="formLabelWidth">
          <template>
            <span>{{ prdct_id }}</span>
          </template>
        </el-form-item>
        <el-form-item label="产品名称" :label-width="formLabelWidth">
          <template>
            <span>{{ prdct_name }}</span>
          </template>
        </el-form-item>
        <el-form-item label="持有份额" :label-width="formLabelWidth">
          <template>
            <span>{{ Number(prdct_shares).toFixed(2) }}</span>
          </template>
        </el-form-item>
        <el-form-item label="赎回份额" :inline="true" :label-width="formLabelWidth">
          <el-input placeholder="赎回份额(0.01)" v-model="redempVal">
            <el-button slot="append" type="primary" :loading="tradeLoding" @click="fundRedemption()">赎 回</el-button>
          </el-input>
        </el-form-item>
        <el-alert
          center
          :title="this.alTitle"
          :type="this.alType"
          :description="this.alDescription"
          :visible.sync="alertVisible"
          >
        </el-alert>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'ObtainFund',
  created () {
    this.getFundOwnList()
  },
  data () {
    return {
      dialogFormVisible: false,
      alertVisible: false,
      alType: '',
      alTitle: '',
      alDescription: '',
      redempVal: '',
      fundList: [],
      prdct_id: '',
      prdct_name: '',
      prdct_shares: '',
      formLabelWidth: '100px',
      tradeLoding: false
    }
  },
  methods: {
    getFundOwnList () {
      console.log(this.$store.state.user.user_id)
      this.$axios
        .get('/api/user/own', {
          params: {
            cstmr_id: this.$store.state.user.user_id
          }
        })
        .then((res) => {
          console.log(res.data)
          this.fundList = res.data.product_info
          console.log(this.fundList)
        })
        .catch((failResponse) => {})
    },
    headStyle () {
      return 'text-align:center'
    },
    rowStyle () {
      return 'text-align:center'
    },
    alert (type, title, description) {
      this.alertVisible = true
      this.alType = type
      this.alTitle = title
      this.alDescription = description
    },
    fundRedemptionForm (prdctId, prdctName, shares) {
      this.dialogFormVisible = true
      this.prdct_id = prdctId
      this.prdct_name = prdctName
      this.prdct_shares = shares
    },
    fundRedemption () {
      this.tradeLoding = true
      let patten = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/
      console.log(this.redempVal)
      if (typeof this.redempVal === 'undefined' || this.redempVal == null || this.redempVal <= 0 || this.redempVal === '') {
        console.log('数额为空')
        this.alert('warning', '输入有误', '数额为空')
        this.redempVal = ''
        this.tradeLoding = false
        return
      } else if (!patten.test(this.redempVal)) {
        console.log('数额不符合规范')
        this.alert('warning', '输入有误', this.redempVal + ' 数额不符合规范')
        this.redempVal = ''
        this.tradeLoding = false
        return
      } else if (this.redempVal > this.prdct_shares) {
        console.log('数额大于持有份额')
        this.alert('warning', '输入有误', '数额大于持有份额')
        this.redempVal = ''
        this.tradeLoding = false
        return
      }
      const temp = this.prdct_shares - this.redempVal
      this.$axios
        .get('/api/trade/sale', {
          params: {
            prdct_id: this.prdct_id,
            cstmr_id: this.$store.state.user.user_id,
            trans_share: this.redempVal
          }
        })
        .then((res) => {
          console.log(res.data)
          console.log(temp)
          this.tradeLoding = false
          this.tradeVal = ''
          if (res.data === 'value false') {
            this.alert('error', '交易失败', '持有份额不足，请重试！')
            return
          } else if (res.data === 'false') {
            this.alert('error', '交易失败', '系统错误')
            return
          }
          this.alert('success', '赎回成功', res.data)
          this.prdct_shares = temp
          // this.dialogFormVisible = false
        })
        .catch((failResponse) => {
          console.log('交易失败')
          this.alert('error', '交易失败', failResponse)
          this.tradeVal = ''
          this.tradeLoding = false
        })
      this.redempVal = ''
      this.tradeLoding = false
      this.getFundOwnList()
    }
  }
}
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}
</style>
