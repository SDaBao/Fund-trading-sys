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
            <el-button
              style="float: right"
              @click="
                dialogFormVisible = true;
                initForm();
              "
            >
              修改
            </el-button>
            <el-dialog
              title="开户"
              :visible.sync="dialogFormVisible"
              center
              :append-to-body="true"
              :lock-scroll="false"
              width="30%"
            >
              <el-form
                :model="modifyCstmrForm"
                :rules="rules"
                ref="modifyCstmrForm"
                label-width="100px"
                class="cstmrForm"
              >
                <el-form-item label="客户类型" prop="cstmr_type">
                  <el-select
                    v-model="modifyCstmrForm.cstmr_type"
                    placeholder="请选择客户类型"
                  >
                    <el-option label="个人" value="1"></el-option>
                    <el-option label="机构" value="2"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="客户姓名" prop="cstmr_name">
                  <el-input
                    v-model="modifyCstmrForm.cstmr_name"
                    placeholder="点击输入姓名"
                  ></el-input>
                </el-form-item>
                <el-form-item label="证件类型" prop="document_type">
                  <el-select
                    v-model="modifyCstmrForm.document_type"
                    placeholder="请选择证件类型"
                  >
                    <el-option label="身份证" value="1"></el-option>
                    <el-option label="护照" value="2"></el-option>
                    <el-option label="港澳台居住证" value="3"></el-option>
                    <el-option label="组织机构代码证" value="4"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="证件号码" prop="document_num">
                  <el-input
                    v-model="modifyCstmrForm.document_num"
                    placeholder="点击输入证件号码"
                  ></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button
                    type="primary"
                    @click="
                      submitForm();
                      dialogFormVisible = false;
                      getUserInfo();
                    "
                    >确 认</el-button
                  >
                  <el-button @click="resetForm('modifyCstmrForm')"
                    >重 置</el-button
                  >
                  <el-button
                    @click="
                      dialogFormVisible = false;
                      getUserInfo();
                    "
                    >取 消</el-button
                  >
                </el-form-item>
              </el-form>
            </el-dialog>
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

// import $ from 'jquery'

export default {
  name: 'CustomerInformation',
  inject: ['setLocation'],
  created () {
    this.cstmrID = sessionStorage.getItem('cstmr_id')
    this.setLocation()
    this.getUserInfo()
  },
  data () {
    this.cstmrTypeOrm = CSTMR_TYPE_ORM
    this.documentTypeOrm = DOCUMENT_TYPE_ORM
    return {
      dialogFormVisible: false,
      cstmrID: '',
      modifyCstmrForm: {
        account_balance: Number
      },
      rules: {
        cstmr_name: [
          { required: true, message: '请输入客户姓名', trigger: 'blur' },
          { min: 2, message: '长度至少2个字符', trigger: 'blur' }
        ],
        cstmr_type: [
          { required: true, message: '请选择客户类型', trigger: 'change' }
        ],
        document_type: [
          { required: true, message: '请选择证件类型', trigger: 'change' }
        ],
        document_num: [
          { required: true, message: '请输入证件号', trigger: 'blur' },
          {
            min: 18,
            max: 18,
            message: '身份证号码长度必须是18位',
            trigger: 'blur'
          }
        ],
        cardId: [
          { required: true, message: '输请入银行卡卡号添加', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    getUserInfo () {
      this.$axios
        .get('/api/user/info', {
          params: {
            cstmr_id: this.cstmrID
          }
        })
        .then((res) => {
          this.$store.commit('cleanUserInfo')
          this.$store.commit('setUserInfo', res.data)
        })
        .catch((failResponse) => {})
    },
    submitForm () {
      // let vm = this
      let params = this.modifyCstmrForm
      let url = '/api/user/update'
      console.log(this.modifyCstmrForm)
      this.$axios
        .get(url, { params })
        .then((res) => {
          console.log('请求成功')
          alert('submit!')
          console.log(res)
        })
        .catch((error) => {
          console.log('请求失败')
          console.log(error)
        })
    },
    headStyle () {
      return 'text-align:center'
    },
    rowStyle () {
      return 'text-align:center'
    },
    initForm () {
      this.modifyCstmrForm = JSON.parse(
        JSON.stringify(this.$store.state.user.user_info[0])
      )
      // if (!$.isEmptyObject(this.modifyCstmrForm.account_balance)) { this.$delete(this.modifyCstmrForm, 'account_balance') }
      this.$delete(this.modifyCstmrForm, 'account_balance')
    }
  }
}
</script>

<style scoped></style>
