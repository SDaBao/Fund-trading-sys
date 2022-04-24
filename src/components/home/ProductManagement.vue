<template>
    <div id="ProductManagement" style="height: 60%; width: 100%">
        <el-card class="box-card"
      style="
        height: calc(100% - 20px);
        width: calc(100% - 40px);
        margin: 20px;
        padding: 30px;
      "
      shadow="always">
        <el-row>
            <h1>产品管理</h1>
        </el-row>
        <el-row>
          <el-col :span="20" style="text-align: left">
            <el-input
              placeholder="请输入内容"
              prefix-icon="el-icon-search"
              v-model="searchInput"
              style="width: 600px"
            >
            </el-input>
          </el-col>
          <el-col :span="4">
            <el-button :type="primary">新增产品
            </el-button>
          </el-col>
        </el-row>
        <el-row>
            <div style="margin-top:20px">
                <el-table :data="productData"
                style="width: 100%"
                :header-cell-style="headStyle"
                :cell-style="rowStyle">
                <el-table-column label="基金编号" width="400px">
                  <template slot-scope="scope">
                    <span>{{ scope.row.prdct_id }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="基金名称" width="400px">
                  <template slot-scope="scope">
                    <span>{{ scope.row.prdct_id }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="操作" >
                    <template>
                    <el-button plain>上架</el-button>
                    <el-button plain>下架</el-button>
                    <el-button plain>修改</el-button>
                    </template>
                </el-table-column>
                </el-table>
            </div>
        </el-row>
        </el-card>
    </div>
</template>>

<script>

export default {
  name: 'ProductList',
  inject: ['setLocation'],
  created () {
    this.setLocation()
    this.getProductData()
  },
  data () {
    return {
      searchInput: '',
      searchType: '',
      formLabelWidth: '100px',
      detailFormVisible: false,
      tradeFormVisible: false,
      tradeLoding: false,
      alertVisible: false,
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
      this.$axios
        .get('/api/fund/search', {
          params: {
            keyWord: ''
          }
        })
        .then((res) => {
          console.log(res.data)
          this.productData = res.data.product_info
          console.log(this.productData)
        })
        .catch((failResponse) => {})
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
    }
  }
}
</script>
