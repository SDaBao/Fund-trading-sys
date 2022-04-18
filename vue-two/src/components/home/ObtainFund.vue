<template>
  <div id="ObtainFund"
       style="height: 100%; width: 100%">
    <el-card class="box-card"
             style="
        height: calc(100% - 40px);
        height: calc(100% - 40px);
        margin: 20px;
        padding: 30px;
      "
             shadow="always">
      <div>
        <el-row>
          <p style="font-size:30px">持有产品列表</p>
        </el-row>
        <el-row>
          <el-col>
            <template>
              <el-table :data="ObtainData"
                        style="width: 100%"
                        :header-cell-style="headStyle"
                        :cell-style="rowStyle">
                <el-table-column label="产品代码"
                                 min-width="200px">
                  <template slot-scope="scope">
                    <span>{{ scope.row.pro_id}}</span>
                  </template>
                </el-table-column>
                <el-table-column label="客户编号"
                                 min-width="200px">
                  <template slot-scope="scope">
                    <span>{{ scope.row.cstmr_id}}</span>
                  </template>
                </el-table-column>
                <el-table-column label="持有份额"
                                 min-width="200px">
                  <template slot-scope="scope">
                    <span>{{ scope.row.shares}}</span>
                  </template>
                </el-table-column>
              </el-table>
            </template>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'ObtainFund',
  data () {
    return {
      ObtainData: [
        {
          pro_id: 123,
          cstmr_id: 12,
          shares: 99
        }
      ]
    }
  },
  methods: {
    headStyle () {
      return 'text-align:center'
    },
    rowStyle () {
      return 'text-align:center'
    }
  }
}
</script>
