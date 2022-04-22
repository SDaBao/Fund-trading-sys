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
          <!-- <el-col :span="4">
            <el-button @click="gettable()">get</el-button>
          </el-col> -->
        </el-row>
        <el-row>
          <el-col :span="24">
            <template>
              <el-table
                :data="this.$store.state.cstmrTable.tableData"
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
                        userLogin(scope.row.name, scope.row.user_id);
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
    this.getlist()
    // this.tmpData = this.tableData
  },
  data () {
    return {
      input: '',
      tmpData: [],
      dialogFormVisible: false
      // myObj: {
      //   cstmr_name: 'Gu',
      //   cstmr_type: 1,
      //   doctument_type: 0,
      //   doctument_num: '330102200103077256'
      // }
    }
  },
  methods: {
    getlist () {
      this.$axios
        .get('/api/user/search', {
          params: {
            keyword: ''
          }
        })
        .then((res) => {
          console.log(res.data.n)
          this.$store.commit('setTable')
          for (var i = 0; i < res.data.n; ++i) {
            this.gettable(res.data.user_info[i])
          }
          console.log(this.$store.state.cstmrTable.tableData)
        })
        .catch((failResponse) => {})
    },
    gettable (myObj) {
      // console.log(this.$store.state.user)
      // console.log(this.$store.state.user.tableData)
      // console.log(this.myObj.name)
      this.$store.commit('setcstmrTable', {
        key0: 'user_id',
        user_id: myObj.cstmr_id,
        key1: 'name',
        name: myObj.cstmr_name,
        key2: 'type',
        type: myObj.cstmr_type,
        key3: 'card_type',
        card_type: myObj.document_type,
        key4: 'card_id',
        card_id: myObj.document_num
      })
      // console.log(this.$store.state.user.tableData)
    },
    submitForm () {
      this.dialogFormVisible = true
    },
    SearchInput () {
      // this.tmpData = this.tableData.filter(
      //   (data) =>
      //     !this.input ||
      //     data.name.toLowerCase().includes(this.input.toLowerCase()) ||
      //     data.card_id.toLowerCase().includes(this.input.toLowerCase())
      // )
      this.$axios
        .get('/api/user/search', {
          params: {
            keyword: this.input
          }
        })
        .then((res) => {
          console.log(res.data.n)
          this.$store.commit('setTable')
          for (var i = 0; i < res.data.n; ++i) {
            this.gettable(res.data.user_info[i])
          }
          console.log(this.$store.state.cstmrTable.tableData)
        })
        .catch((failResponse) => {})
    },
    userLogin (cstmrname, cstmrId) {
      this.$store.commit('cleanUser')
      this.$store.commit('cleanUserID')
      this.$store.commit('cleanUserInfo')
      this.$store.commit('setUser', cstmrname)
      this.$store.commit('setUserID', cstmrId)
      this.$store.commit('setShowLogin', true)
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
