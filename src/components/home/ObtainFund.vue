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
          <p style="font-size: 30px">持有产品</p>
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
                      赎回
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
            </template>
          </el-col>
        </el-row>
      </div>
    </el-card>
    <el-dialog title="赎回" :visible.sync="dialogFormVisible">
      <el-form :model="form">
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
            <!-- <span>{{ scope.row.prdct_shares.toFixed(2) }}</span> -->
            <span>{{ prdct_shares }}</span>
          </template>
        </el-form-item>
        <el-form-item label="赎回份额" :inline="true" :label-width="formLabelWidth">
          <el-input placeholder="赎回份额(0.00)" v-model="redempVal">
            <!-- <el-button slot="append" @click="dialogFormVisible = false">取 消</el-button> -->
            <el-button slot="append" type="primary" @click="fundRedemption()">赎 回</el-button>
          </el-input>
        </el-form-item>
      </el-form>
      <!-- <div slot="footer" class="dialog-footer" :inline="true">
        <p>赎回份额</p>
        <el-input placeholder="赎回份额" v-model="input">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </el-input>
      </div> -->
    </el-dialog>
    <el-alert
      title="错误提示的文案"
      type="error"
      show-icon>
    </el-alert>
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
      redempVal: '',
      fundList: [],
      prdct_id: '',
      prdct_name: '',
      prdct_shares: '',
      formLabelWidth: '100px'
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
    fundRedemptionForm (prdctId, prdctName, shares) {
      this.dialogFormVisible = true
      this.prdct_id = prdctId
      this.prdct_name = prdctName
      this.prdct_shares = shares
    },
    fundRedemption () {
      let patten = /^(([1-9][0-9]{0,14})|([0]{1})|(([0]\\.\\d{1,2}|[1-9][0-9]{0,14}\\.\\d{1,2})))$/
      if (typeof this.redempVal === 'undefined' || this.redempVal == null || this.redempVal > 0 || this.redempVal === '') {
        console.log('数额为空')
        alert('数额为空')
        return
      } else if (patten.test(this.redempVal)) {
        console.log('数额不符合正则规范')
        alert('数额不符合正则规范')
        return
      } else if (this.redempVal > this.prdct_shares) {
        console.log('数额大于持有份额')
        alert('数额大于持有份额')
        return
      }
      this.$axios
        .get('/api/trade/sale', {
          params: {
            prdct_id: this.prdct_id,
            cstmr_id: this.$store.state.user.user_id,
            trans_share: this.redempVal
          }
        })
        .then((res) => {
          console.log(this.redempVal)
          console.log(res.data)
        })
        .catch((failResponse) => {})
      this.redempVal = ''
      this.dialogFormVisible = false
      this.getFundOwnList()
    }
  }
}
</script>
