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
                        :cell-style="rowStyle"
                        max-height="600px">
                <el-table-column label="流水编号"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ scope.row.deal_id }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="交易类型"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ typeorm[scope.row.deal_type] }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="交易状态"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ dealorm[scope.row.deal_status] }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="产品代号"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ scope.row.prdct_id }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="产品名称"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ scope.row.prdct_name }}</span>
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
                    <span>{{ scope.row.ac_time }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="金额"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ scope.row.trans_val }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="份额"
                                 width="150">
                  <template slot-scope="scope">
                    <span>{{ scope.row.trans_share }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="操作"
                                 min-width="100px"
                                 fixed="right">
                  <template slot-scope="scope">
                    <el-button size="mini"
                               @click="businessCancelForm(scope.row.deal_id)"
                               :disabled="scope.row.deal_status!=0">取消</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </template>
          </el-col>
        </el-row>
      </div>
    </el-card>
    <el-dialog title="你确定要取消这笔交易吗，这是一项不可逆的操作"
               :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item>
          <el-button type="primary"
                     @click="calcelDeal()">确认</el-button>
          <el-button @click="
                      dialogFormVisible = false;
                    ">取 消</el-button>
        </el-form-item>
        <el-alert center
                  :title="this.alTitle"
                  :type="this.alType"
                  :description="this.alDescription"
                  :visible.sync="alertVisible">
        </el-alert>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>

import { DEAL_STATUS_ORM, DEAL_TYPE_ORM } from '../../constant'

export default {
  name: 'BusinessList',
  created () {
    this.getBusinessList()
  },
  data () {
    this.dealorm = DEAL_STATUS_ORM
    this.typeorm = DEAL_TYPE_ORM
    return {
      input: '',
      dialogFormVisible: false,
      BusinessData: [],
      deal_id: '',
      cstmr_id: '',
      prdct_id: '',
      prdct_name: '',
      deal_type: '',
      deal_status: '',
      deal_time: '',
      ac_time: '',
      trans_val: '',
      trans_share: '',
      tmpData: [],
      alertVisible: false,
      alType: '',
      alTitle: '',
      alDescription: ''
    }
  },
  methods: {
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
    SearchInput () {
      this.tmpData = this.BusinessData.filter(
        data =>
          !this.input ||
          data.name.toLowerCase().includes(this.input.toLowerCase()) ||
          data.pro_name.toLowerCase().includes(this.input.toLowerCase())
      )
    },
    getBusinessList () {
      console.log(sessionStorage.getItem('cstmr_id'))
      this.$axios
        .get('./api/trade/history', {
          params: {
            cstmr_id: sessionStorage.getItem('cstmr_id')
          }
        })
        .then((res) => {
          console.log(res.data)
          this.BusinessData = res.data.trade_info
        })
    },
    calcelDeal () {
      this.$axios
        .get('./api/trade/cancel', {
          params: {
            deal_id: this.deal_id
          }
        })
        .then((res) => {
          console.log(res.data)
          this.alert('success', '成功', res.data)
        })
    },
    businessCancelForm (DealId) {
      this.dialogFormVisible = true
      this.deal_id = DealId
    }
  }
}
</script>
