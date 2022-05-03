<template>
  <div id="Productlist" style="height: 100%; width: 100%">
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
          <h1>产品列表</h1>
        </el-row>
        <el-row>
          <el-col :span="22" style="text-align: left">
            <el-input
              placeholder="请输入内容"
              prefix-icon="el-icon-search"
              v-model="searchInput"
              @input="getProductData()"
              style="width: 400px"
            >
            </el-input>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <div>
              <el-table
                :data="productData"
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
                <el-table-column label="基金公司" min-width="200px">
                  <template slot-scope="scope">
                    <span>{{ scope.row.prdct_comp }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="产品风险" min-width="200px">
                  <template slot-scope="scope">
                    <span>{{ riskRatingOrm[scope.row.prdct_risk] }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="操作" min-width="200px">
                  <template slot-scope="scope">
                    <el-button
                      size="small"
                      @click="
                        detailFormVisible = true;
                        fundDetail(
                          scope.row
                        );
                      "
                    >
                      产品详情
                    </el-button>
                    <el-button
                      size="small"
                      :disabled="enableSale(scope.row.prdct_sale)"
                      style="width: 80px"
                      @click="
                        tradeFormVisible = true;
                        fundDetail(
                          scope.row
                        );
                      "
                    >
                      <span v-show="scope.row.prdct_sale">申 购</span>
                      <span v-show="enableSale(scope.row.prdct_sale)">暂停销售</span>
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-card>
    <!-- 申购对话框 -->
    <el-dialog :title="productName+' 基金代号：'+productID" :visible.sync="tradeFormVisible">
      <el-form>
        <el-form-item label="产品风险" :label-width="formLabelWidth">
          <template>
            <el-tag size='large' :type="riskWarning(productRisk)">
              {{ riskRatingOrm[productRisk] }}
            </el-tag>
          </template>
        </el-form-item>
        <el-form-item label="账户余额" :label-width="formLabelWidth">
          <template>
            <span>{{ Number(accountBalance).toFixed(2) }}</span>
          </template>
        </el-form-item>
        <el-form-item label="申购金额" :inline="true" :label-width="formLabelWidth">
          <el-input placeholder="申购金额(0.01)" style="width: 250px" v-model="tradeVal">
            <el-button slot="append" type="primary" :loading="tradeLoding" @click="fundTrade()">申 购</el-button>
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
    <!-- 产品详情对话框 -->
    <el-dialog :title="productName+' 基金代号：'+productID" :visible.sync="detailFormVisible" @open="openChart()">
      <el-form>
        <el-form-item label="基金公司" :label-width="formLabelWidth">
          <template>
            <span>{{ productCOMP }}</span>
          </template>
        </el-form-item>
        <el-form-item label="产品风险" :label-width="formLabelWidth">
          <template>
            <span>{{ riskRatingOrm[productRisk] }}</span>
          </template>
        </el-form-item>
        <el-form-item label="销售状态" :label-width="formLabelWidth">
          <template>
            <span>{{ saleConditionOrm[productSale] }}</span>
          </template>
        </el-form-item>
        <el-form-item label="产品简介" :label-width="formLabelWidth">
          <template>
            <span>{{ productPRFL }}</span>
          </template>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" id="valueEcharts" style="width: 100%;height: 300px;" center></div>
    </el-dialog>
  </div>
</template>

<script>
import { RISK_RATING_ORM, SALE_CONDITION_ORM } from '../../constant'

export default {
  name: 'ProductList',
  inject: ['setLocation'],
  created () {
    this.setLocation()
    this.getProductData()
  },
  data () {
    this.riskRatingOrm = RISK_RATING_ORM
    this.saleConditionOrm = SALE_CONDITION_ORM
    return {
      searchInput: '',
      searchType: '',
      formLabelWidth: '100px',
      detailFormVisible: false,
      tradeFormVisible: false,
      tradeLoding: false,
      alertVisible: false,
      userRisk: this.$store.state.user.user_info[0].risk_rating,
      accountBalance: this.$store.state.user.user_info[0].account_balance,
      alType: '',
      alTitle: '',
      alDescription: '',
      productData: [],
      tradeVal: '',
      productID: '',
      productName: '',
      productCOMP: '',
      productRisk: '',
      productPRFL: '',
      productSale: ''
    }
  },
  methods: {
    headStyle () {
      return 'text-align:center'
    },
    innerStyle () {
      return 'text-align:center'
    },
    rowStyle () {
      return 'text-align:center'
    },
    enableSale (prdctSale) {
      return !prdctSale
    },
    getProductData () {
      console.log(this.searchInput)
      this.$axios
        .get('/api/fund/search', {
          params: {
            keyWord: this.searchInput,
            search_type: this.searchType
          }
        })
        .then((res) => {
          console.log(res.data)
          this.productData = res.data.product_info
          console.log(this.productData)
        })
        .catch((failResponse) => { alert('search ERR!') })
    },
    riskWarning (productRisk) {
      console.log(productRisk + this.userRisk)
      if (productRisk + this.userRisk <= 2) {
        return 'success'
      } else if (productRisk + this.userRisk > 2) {
        return 'danger'
      }
    },
    alert (type, title, description) {
      this.alertVisible = true
      this.alType = type
      this.alTitle = title
      this.alDescription = description
    },
    fundDetail (data) {
      this.productID = data.prdct_id
      this.productName = data.prdct_name
      this.productCOMP = data.prdct_comp
      this.productRisk = data.prdct_risk
      this.productPRFL = data.prdct_prfl
      this.productSale = data.prdct_sale
    },
    fundTrade () {
      this.tradeLoding = true
      let patten = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/
      console.log(this.tradeVal)
      if (typeof this.tradeVal === 'undefined' || this.tradeVal == null || this.tradeVal <= 0 || this.tradeVal === '') {
        console.log('数额为空')
        this.alert('warning', '输入有误', '数额为空')
        this.tradeVal = ''
        this.tradeLoding = false
        return
      } else if (!patten.test(this.tradeVal)) {
        console.log('数额不符合规范')
        this.alert('warning', '输入有误', this.tradeVal + ' 数额不符合规范')
        this.tradeVal = ''
        this.tradeLoding = false
        return
      }
      const temp = this.accountBalance - Number(this.tradeVal)
      console.log(temp)
      this.$axios
        .get('/api/trade/buy', {
          params: {
            prdct_id: this.productID,
            cstmr_id: this.$store.state.user.user_id,
            trans_val: this.tradeVal
          }
        })
        .then((res) => {
          console.log(res.data)
          this.tradeLoding = false
          if (res.data === 'value false') {
            this.alert('error', '交易失败', '账户余额不足，请充值！')
            return
          } else if (res.data === 'false') {
            this.alert('error', '交易失败', '系统错误')
            return
          }
          this.alert('success', '申购成功', res.data)
          this.accountBalance = temp
          this.$store.commit('setAccountBalance', temp)
          this.tradeVal = ''
          // this.dialogFormVisible = false
        })
        .catch((failResponse) => {
          console.log('交易失败')
          this.alert('error', '交易失败', failResponse)
          this.tradeVal = ''
          this.tradeLoding = false
        })
      this.tradeVal = ''
      this.tradeLoding = false
    },
    openChart () {
      // 打开加载图表
      this.$nextTick(() => {
        this.drawChart()
      })
    },
    drawChart () {
      // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
      let myChart = this.$echarts.init(document.getElementById('valueEcharts'))
      // 指定图表的配置项和数据
      this.$nextTick(() => {
        // 自适应宽度
        myChart.resize()
      })
      var datetime = sessionStorage.getItem('systemTime')
      const dataList = []
      const valueList = []
      if (datetime === null) {
        datetime = '2022-04-28'
      }
      this.$axios
        .get('/api/fund/value_search', {
          params: {
            prdct_id: this.productID,
            datetime: datetime,
            search_type: 2
          }
        })
        .then((res) => {
          console.log(datetime)
          console.log(res.data)
          if (res.data === 'err datetime') {
            this.$message({
              type: 'warning',
              message: `${res.data}`
            })
            return
          }
          for (var i = 0; i < res.data.n; i++) {
            dataList.push(res.data.valueInfo[i].mrkt_time)
            valueList.push(res.data.valueInfo[i].prdct_val)
          }
          console.log(dataList, valueList)
          let option = {
            title: {
              left: 'center',
              text: datetime + '净值'
            },
            xAxis: {
              type: 'category',
              data: dataList
            },
            yAxis: {
              type: 'value'
            },
            series: [
              {
                data: valueList,
                type: 'line'
              }
            ]
          }
          // 使用刚指定的配置项和数据显示图表。
          myChart.setOption(option)
        })
        .catch((failResponse) => {
          console.log(datetime)
          console.log(failResponse)
          this.$message({
            type: 'warning',
            message: `${failResponse}`
          })
        })
    }
  }
}
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}
</style>
