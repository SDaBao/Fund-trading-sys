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
                        fundRedemption(scope.row.prdct_id, scope.row.shares)
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
        <el-form-item label="产品名称：">
          <span>{{ prdct_name }}</span>
        </el-form-item>
        <el-form-item label="持有份额：">
          <span>{{ prdct_shares.toFixed(2) }}</span>
        </el-form-item>
        <el-form-item label="赎回份额" :inline="true">
          <el-input placeholder="赎回份额" v-model="input"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
      </div>
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
      input: '',
      fundList: []
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
    fundRedemption (prdctId, shares) {
      this.dialogFormVisible = true
      // this.$axios
      //   .get('/api/trade/sale', {
      //     params: {
      //       prdct_id: prdctId,
      //       cstmr_id: this.$store.state.user.user_id,
      //       trans_share: shares
      //     }
      //   })
      //   .then((res) => {
      //     console.log(res.data)
      //   })
      //   .catch((failResponse) => {})
    }
  }
}
</script>
