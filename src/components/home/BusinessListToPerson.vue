<template>
  <div id="BusinessList"
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
          <p style="font-size:30px">交易记录</p>
        </el-row>
        <el-row>
          <el-col :span="24"
                  style="text-align:left">
            <el-input placeholder="请输入内容"
                      prefix-icon="el-icon-search"
                      v-model="input"
                      style="width: 400px"
                      @input="SearchInput">
            </el-input>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <template>
              <el-table :data="BusinessData"
                        style="width: 100%"
                        :header-cell-style="headStyle"
                        :cell-style="rowStyle">
                <el-table-column label="流水编号"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ scope.row.bsns_id }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="交易类型"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ scope.row.type }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="客户名称"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ scope.row.name }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="产品代号"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ scope.row.pro_id }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="产品名称"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ scope.row.pro_name }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="下单时间"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ scope.row.deal_time }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="确认时间"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ scope.row.acc_time }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="金额"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ scope.row.money }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="份额"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ scope.row.shares }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="操作"
                                 min-width="200px">
                  <template>
                    <el-button size="mini"
                               @click="dialogFormVisible=true ">取消</el-button>
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
  name: 'BusinessListToPerson',
  inject: ['setLocation'],
  created () {
    this.getBusinessList()
    this.setLocation()
  },
  data () {
    return {
      input: '',
      BusinessData: [
        {
          dialogFormVisible: false,
          deal_id: '',
          cstmr_id: '',
          prdct_id: '',
          deal_type: '',
          deal_status: '',
          deal_time: '',
          ac_time: '',
          trans_val: '',
          trans_share: '',
          status: ''
        }
      ],
      tmpData: []
    }
  },
  methods: {
    headStyle () {
      return 'text-align:center'
    },
    rowStyle () {
      return 'text-align:center'
    },
    SearchInput () {
      this.tmpData = this.BusinessData.filter(
        data =>
          !this.input ||
          data.name.toLowerCase().includes(this.input.toLowerCase()) ||
          data.pro_name.toLowerCase().includes(this.input.toLowerCase())
      )
    },
    getBusinessList () {
      console.log(this.$store.state.user.user_id)
      this.$axios
        .get('./api/trade/history', {
          params: {
            cstmr_id: this.$store.state.user.user_id
          }
        })
        .then((res) => {
          console.log(res.data)
          this.BusinessData = res.data.trade_info
        })
    }
  }
}
</script>
