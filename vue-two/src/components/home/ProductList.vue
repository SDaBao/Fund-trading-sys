<template>
  <div id="Productlist"
       style="height: 100%; width: 100%">
    <el-card class="box-card"
             style="height: calc(100% - 40px); height: calc(100% - 40px); margin: 20px"
             shadow="hover">
      <div>
        <el-row>
          <h3>上架产品</h3>
        </el-row>
        <el-row>
          <el-col :span="10"
                  style="text-align: left">
            <el-input placeholder="请输入内容"
                      prefix-icon="el-icon-search"
                      v-model="input"
                      style="width: 400px">
            </el-input>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <div>
              <el-table :data="ProductData"
                        style="width: 80%"
                        :header-cell-style="headerStyle"
                        :cell-style="innerStyle">
                <el-table-column label="产品代码"
                                 width="200"
                                 prop="id">
                </el-table-column>
                <el-table-column label="产品名称"
                                 width="300"
                                 prop="name">
                </el-table-column>
                <el-table-column label="参考净值"
                                 width="150"
                                 prop="value">
                </el-table-column>
                <el-table-column label="操作"
                                 width="200">
                  <div>
                    <el-button size="mini"
                               @click="dialogFormVisible = true">
                      加购
                    </el-button>
                  </div>
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
    this.ProductData = this.ProductData
  },
  data () {
    return {
      input: '',
      dialogFormVisible: false,
      ProductData: [
        {
          id: 'bing',
          name: '氵',
          value: '2'
        }
      ]
    }
  },
  methods: {
    headerStyle () {
      return 'text-align:center'
    },
    innerStyle () {
      return 'text-align:center'
    }
  }
}
</script>

<style scoped>
.el-table-column {
  font-size: 24px;
}
</style>
