<template>
  <div
    id="customerinformation"
    style="height: 60%; width: 60%; transform: translate(30%, 30%)"
  >
    <el-card
      class="box-card"
      style="
        height: calc(100% - 30px);
        height: calc(100% - 40px);
        margin: 20px;
        padding: 30px;
      "
      shadow="always"
    >
      <div>
        <el-row>
          <el-col :span="21">
            <p style="font-size: 27px">客户基本信息</p>
          </el-col>
          <el-col>
            <el-button style="float: right"> 修改 </el-button>
          </el-col>
        </el-row>
      </div>
      <div style="text-align: left; font-size: 20px">
        <p>客户姓名: {{ this.$store.state.user.user_info[0].cstmr_name }}</p>
        <p>
          客户类型:
          {{ cstmrTypeOrm[this.$store.state.user.user_info[0].cstmr_type] }}
        </p>
        <p>
          证件类型:
          {{
            documentTypeOrm[this.$store.state.user.user_info[0].document_type]
          }}
        </p>
        <p>证件号: {{ this.$store.state.user.user_info[0].document_num }}</p>
      </div>
    </el-card>
  </div>
</template>

<script>
import { CSTMR_TYPE_ORM, DOCUMENT_TYPE_ORM } from '../../constant'

export default {
  name: 'CustomerInformation',
  created () {
    this.getUserInfo()
  },
  data () {
    this.cstmrTypeOrm = CSTMR_TYPE_ORM
    this.documentTypeOrm = DOCUMENT_TYPE_ORM
    return {}
  },
  methods: {
    getUserInfo () {
      this.$axios
        .get('/api/user/info', {
          params: {
            cstmr_id: this.$store.state.user.user_id
          }
        })
        .then((res) => {
          this.$store.commit('setUserInfo', res.data)
          console.log(this.$store.state.user.user_info)
        })
        .catch((failResponse) => {})
    },
    headStyle () {
      return 'text-align:center'
    },
    rowStyle () {
      return 'text-align:center'
    }
  }
}
</script>

<style scoped></style>
