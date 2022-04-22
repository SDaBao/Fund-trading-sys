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
              v-model="input"
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
                <el-table-column label="参考净值" min-width="200px">
                  <template slot-scope="scope">
                    <span>{{ scope.row.prdct_shares.toFixed(2) }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="操作" min-width="200px">
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      @click="
                        dialogFormVisible = true;
                        fundRedemptionForm(
                          scope.row.prdct_id,
                          scope.row.prdct_name,
                          scope.row.prdct_shares
                        );
                      "
                    >
                      申购
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'ProductList',
  created () {
    this.ProductData = this.productData
  },
  data () {
    return {
      input: '',
      dialogFormVisible: false,
      productData: []
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
    }
  }
}
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}
</style>
