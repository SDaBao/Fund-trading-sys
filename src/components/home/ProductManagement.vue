<template>
  <div id="ProductManagement"
       style="height: 100%; width: 100%">
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
        <el-col :span="20"
                style="text-align: left">
          <el-input placeholder="请输入内容"
                    prefix-icon="el-icon-search"
                    v-model="searchInput"
                    @input="searchProductData()"
                    style="width: 600px">
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button :type="primary"
                     @click="addFormVisible=true">新增产品
          </el-button>
          <el-dialog title="基金信息"
                     :visible.sync="addFormVisible"
                     center
                     :append-to-body="true"
                     :lock-scroll="false"
                     width="30%">
            <el-form label-width="100px"
                     class="cstmrForm"
                     :model="product"
                     ref="product">
              <el-form-item label="基金编号"
                            prop="prdct_id">
                <el-input placeholder="请输入产品编号"
                          v-model="product.prdct_id">
                </el-input>
              </el-form-item>
              <el-form-item label="基金名称"
                            prop="prdct_name">
                <el-input placeholder="请输入产品名称"
                          v-model="product.prdct_name">
                </el-input>
              </el-form-item>
              <el-form-item label="基金公司"
                            prop="prdct_comp">
                <el-input placeholder="请输入产品公司"
                          v-model="product.prdct_comp">
                </el-input>
              </el-form-item>
              <el-form-item label="基金风险"
                            prop="prdct_risk">
                <el-select placeholder="请选择产品风险"
                           v-model="product.prdct_risk">
                  <el-option label="低"
                             value="0">
                  </el-option>
                  <el-option label="中"
                             value="1">
                  </el-option>
                  <el-option label="高"
                             value="2">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="基金简介"
                            prop="prdct_prfl">
                <el-input placeholder="请输入产品简介"
                          v-model="product.prdct_prfl">
                </el-input>
              </el-form-item>
              <el-form-item label="基金状态"
                            prop="prdct_sale">
                <el-select placeholder="请选择基金状态"
                           v-model="product.prdct_sale">
                  <el-option label="下架"
                             :value="false">
                  </el-option>
                  <el-option label="上架"
                             :value="true">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-button type="primary"
                           @click="productCreate()">确定
                </el-button>
                <el-button @click="addFormVisible=false">取消
                </el-button>
              </el-form-item>
              <el-alert center
                        :title="this.alTitle"
                        :type="this.alType"
                        :description="this.alDescription"
                        :visible.sync="this.alertVisible">
              </el-alert>
            </el-form>
          </el-dialog>
        </el-col>
      </el-row>
      <el-row>
        <div style="margin-top:20px">
          <el-table :data="productData"
                    style="width: 100%"
                    :header-cell-style="headStyle"
                    :cell-style="rowStyle">
            <el-table-column label="基金编号"
                             width="400px">
              <template slot-scope="scope">
                <span>{{ scope.row.prdct_id }}</span>
              </template>
            </el-table-column>
            <el-table-column label="基金名称"
                             width="400px">
              <template slot-scope="scope">
                <span>{{ scope.row.prdct_name }}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button plain
                           @click="fundDetail(scope.row);alertVisible=false;updateFormVisible=true">修改</el-button>
              </template>
              <el-dialog title="修改基金信息"
                         :visible.sync="updateFormVisible"
                         center
                         :append-to-body="true"
                         :lock-scroll="false"
                         width="30%">
                <el-form label-width="100px"
                         class="cstmrForm"
                         :model="product"
                         ref="product">
                  <el-form-item label="基金编号"
                                prop="prdct_id">
                    <el-input placeholder="请输入新的产品编号"
                              v-model="product.prdct_id"
                              :disabled="true">
                    </el-input>
                  </el-form-item>
                  <el-form-item label="基金名称"
                                prop="prdct_name">
                    <el-input placeholder="请输入新的产品名称"
                              v-model="product.prdct_name">
                    </el-input>
                  </el-form-item>
                  <el-form-item label="基金公司"
                                prop="prdct_comp">
                    <el-input placeholder="请输入新的产品公司"
                              v-model="product.prdct_comp">
                    </el-input>
                  </el-form-item>
                  <el-form-item label="基金风险"
                                prop="prdct_risk">
                    <el-select placeholder="请选择新的产品风险"
                               v-model="product.prdct_risk">
                      <el-option label="低"
                                 value="0">
                      </el-option>
                      <el-option label="中"
                                 value="1">
                      </el-option>
                      <el-option label="高"
                                 value="2">
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="基金简介"
                                prop="prdct_prfl">
                    <el-input placeholder="请输入新的产品简介"
                              v-model="product.prdct_prfl">
                    </el-input>
                  </el-form-item>
                  <el-form-item label="基金状态"
                                prop="prdct_sale">
                    <el-select placeholder="请选择新的基金状态"
                               v-model="product.prdct_sale">
                      <el-option label="下架"
                                 :value="false">
                      </el-option>
                      <el-option label="上架"
                                 :value="true">
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary"
                               @click="productUpdate()">确定
                    </el-button>
                    <el-button @click="updateFormVisible=false">取消
                    </el-button>
                  </el-form-item>
                  <el-alert center
                            :title="this.alTitle"
                            :type="this.alType"
                            :description="this.alDescription"
                            :visible.sync="this.alertVisible">
                  </el-alert>
                </el-form>
              </el-dialog>
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
      updateFormVisible: false,
      addFormVisible: false,
      detailFormVisible: false,
      tradeFormVisible: false,
      tradeLoding: false,
      alertVisible: false,
      alType: '',
      alTitle: '',
      alDescription: '',
      productData: [],
      product: {},
      tradeVal: ''
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
        .catch((failResponse) => { })
    },
    searchProductData () {
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
    productUpdate () {
      this.alertVisible = false
      let params = this.product
      this.$axios
        .get('/api/fund/update', {
          params
        })
        .then((res) => {
          console.log(res.data)
          console.log(this.productData)
          this.alert('success', '修改产品信息成功', res.data)
        })
        .catch((failResponse) => { })
    },
    productCreate () {
      this.alertVisible = false
      let params = this.product
      this.$axios
        .get('/api/fund/add', {
          params
        })
        .then((res) => {
          console.log(res.data)
          console.log(this.productData)
          this.alert('success', '新增产品成功', res.data)
        })
        .catch((failResponse) => { })
    },
    alert (type, title, description) {
      this.alertVisible = true
      this.alType = type
      this.alTitle = title
      this.alDescription = description
    },
    fundDetail (data) {
      this.product = data
    }
  }
}
</script>
