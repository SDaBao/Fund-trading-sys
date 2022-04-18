<template>
  <div id="CustomerSearch" style="height: 100%; width: 100%">
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
          <h1>客户检索</h1>
        </el-row>
        <!-- <el-row>
          <p>{{ this.$store.state.tableData.name }}</p>
        </el-row> -->
        <el-row>
          <el-col :span="22" style="text-align: left">
            <el-input
              placeholder="请输入内容"
              prefix-icon="el-icon-search"
              v-model="input"
              style="width: 400px"
              @input="SearchInput"
            >
            </el-input>
          </el-col>
          <el-col :span="2">
            <el-button @click="dialogFormVisible = true">开户</el-button>
            <el-dialog
              title="开户"
              :visible.sync="dialogFormVisible"
              center
              :append-to-body="true"
              :lock-scroll="false"
              width="30%"
            >
              <createCustomerInformation
                :isShow="'true'"
                @close-form="closeform()"
              ></createCustomerInformation>
            </el-dialog>
          </el-col>
          <el-col :span="4">
            <el-button @click="gettable()">get</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <template>
              <el-table
                :data="this.$store.state.user.tableData"
                style="width: 100%"
                :header-cell-style="headStyle"
                :cell-style="rowStyle"
              >
                <el-table-column label="客户名称" min-width="200">
                  <template slot-scope="scope">
                    <span style="">{{ scope.row.name }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="类型" min-width="100">
                  <template slot-scope="scope">
                    <span style="">{{ scope.row.type }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="证件类型" min-width="150">
                  <template slot-scope="scope">
                    <span style="">{{ scope.row.card_type }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="证件号码" min-width="200">
                  <template slot-scope="scope">
                    <span style="">{{ scope.row.card_id }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="操作" min-width="200">
                  <!-- eslint-disable-next-line -->
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      @click="
                        toCustomerInformation();
                        close();
                        userLogin();
                      "
                      >登入
                    </el-button>
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
import createCustomerInformation from './createCustomerInformation'

export default {
  name: 'CustomerSearch',
  components: {
    createCustomerInformation
  },
  props: { isShow: String },
  created () {
    // this.getlist()
    this.tmpData = this.tableData
  },
  data () {
    return {
      input: '',
      tmpData: [],
      dialogFormVisible: false
    }
  },
  methods: {
    // getlist () {
    //   this.$axios
    //     .get('/api/user/info', {
    //       params: {
    //         cstmr_id: '10000'
    //       }
    //     })
    //     .then((res) => {
    //       console.log(res)
    //       this.$store.state.user.tableData.name = res.data.cstmr_name
    //       this.$store.state.user.tableData.type = res.data.cstmr_type
    //       this.$store.state.user.tableData.card_id = res.data.doctument_num
    //       this.$store.state.user.tableData.card_type = res.data.doctument_type
    //       console.log(this.$store.state.user.tableData)
    //     })
    //     .catch((failResponse) => {})
    // },
    gettable () {
      console.log(this.$store.state.user)
      console.log('test1')
      console.log(this.$store.state.admin)
      console.log(this.$store.state.user.tableData)
    },
    submitForm () {
      this.dialogFormVisible = true
    },
    SearchInput () {
      this.tmpData = this.tableData.filter(
        (data) =>
          !this.input ||
          data.name.toLowerCase().includes(this.input.toLowerCase()) ||
          data.card_id.toLowerCase().includes(this.input.toLowerCase())
      )
    },
    userLogin () {
      this.$store.commit('setShowLogin', true)
      this.$store.commit('setUser', 'Ming')
    },
    headStyle () {
      return 'text-align:center'
    },
    rowStyle () {
      return 'text-align:center'
    },
    closeform () {
      this.dialogFormVisible = false
    },
    close () {
      this.$emit('close-page')
    },
    toCustomerInformation () {
      this.$router.push({ path: '/index/customerinformation' })
    }
  }
}
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}
</style>
