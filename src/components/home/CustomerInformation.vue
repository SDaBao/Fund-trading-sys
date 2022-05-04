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
            <!-- 修改信息对话框 -->
            <el-dialog
              title="修改信息"
              :visible.sync="dialogFormVisible"
              center
              :append-to-body="true"
              :lock-scroll="false"
              width="500px"
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
                    v-model="cstmrTypeOrm[modifyCstmrForm.cstmr_type]"
                    placeholder="请选择客户类型"
                    style="width:300px"
                  >
                    <el-option label="个人" value="1"></el-option>
                    <el-option label="机构" value="2"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="客户姓名" prop="cstmr_name">
                  <el-input
                    v-model="modifyCstmrForm.cstmr_name"
                    placeholder="点击输入姓名"
                    style="width:300px"
                  ></el-input>
                </el-form-item>
                <el-form-item label="证件类型" prop="document_type">
                  <el-select
                    v-model="documentTypeOrm[modifyCstmrForm.document_type]"
                    placeholder="请选择证件类型"
                    style="width:300px"
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
                    style="width:300px"
                  ></el-input>
                </el-form-item>
                <el-form-item label="风险等级" prop="risk_rating">
                  <el-select
                    v-model="riskRatingOrm[modifyCstmrForm.risk_rating]"
                    placeholder="请选择风险等级"
                    style="width:300px"
                  >
                    <el-option label="低" value="0"></el-option>
                    <el-option label="中" value="1"></el-option>
                    <el-option label="高" value="2"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-button
                    type="primary"
                    @click="
                      submitForm();
                      dialogFormVisible = false;
                      getUserInfo();
                    "
                    >修 改</el-button
                  >
                  <el-button
                    @click="
                      dialogFormVisible = false;
                      getUserInfo();
                    "
                    >取 消</el-button
                  >
                  <el-button @click="riskFormVisible=true"
                    >风险评估</el-button
                  >
                </el-form-item>
              </el-form>
            </el-dialog>
            <!-- 风险评估对话框 -->
            <el-dialog
              title="风险评估"
              :visible.sync="riskFormVisible"
              center
              :append-to-body="true"
              :lock-scroll="false"
              width="600px"
            >
              <el-form
                :rules="rules"
                label-width="100px"
                class="testForm"
              >
                <el-form-item label="风险波动">
                  <el-select
                    v-model="riskForm.wave"
                    placeholder="您在投资中可耐受何种程度的风险波动"
                    style="width:400px"
                  >
                    <el-option label="低" value="1"></el-option>
                    <el-option label="中" value="2"></el-option>
                    <el-option label="高" value="3"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="投资目标">
                  <el-select
                    v-model="riskForm.goal"
                    placeholder="您的投资目标是"
                    style="width:400px"
                  >
                    <el-option label="尽可能保证本金安全，不在乎收益率" value="1"></el-option>
                    <el-option label="追求较多的收益，可以承担一定的风险" value="2"></el-option>
                    <el-option label="实现资产的增长，愿意承担很大的风险" value="3"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="投资经验">
                  <el-select
                    v-model="riskForm.exp"
                    placeholder="您有多少年基金投资经验"
                    style="width:400px"
                  >
                    <el-option label=" 1 年以下" value="1"></el-option>
                    <el-option label=" 1 - 3 年" value="2"></el-option>
                    <el-option label=" 3 年及以上" value="3"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="收入水平" style="margin-bottom: 50px;">
                  <el-slider
                    v-model="riskForm.level"
                    show-input
                    input-size="mini"
                    :min=1
                    :max=500
                    :marks="{1:'1万以下',300:{style:{overflow:ellipsis},label:'300万以上'}}"
                    style="margin-left:20px;margin-right:50px"
                    >
                  </el-slider>
                </el-form-item>
                <el-form-item label="收入来源">
                  <el-select
                    v-model="riskForm.rec"
                    placeholder="您的收入主要来源"
                    style="width:400px"
                  >
                    <el-option label="工资奖金、劳务报酬" value="1"></el-option>
                    <el-option label="生产经营所得" value="2"></el-option>
                    <el-option label="利息、股息、转让等金融资产收入" value="3"></el-option>
                    <el-option label="出租、出售房地产等非金融资产收入" value="4"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-button @click="riskTest()">风险评估</el-button>
                  <el-button @click="valueFormVisible = false;">取 消</el-button>
                </el-form-item>
              </el-form>
            </el-dialog>
          </el-col>
        </el-row>
      </div>
      <div style="text-align: left; font-size: 20px">
        <p>客户姓名：{{ this.$store.state.user.user_info[0].cstmr_name }}</p>
        <p>客户类型：{{ cstmrTypeOrm[this.$store.state.user.user_info[0].cstmr_type] }}</p>
        <p>证件类型：{{ documentTypeOrm[this.$store.state.user.user_info[0].document_type] }}</p>
        <p>证件号：  {{ this.$store.state.user.user_info[0].document_num }}</p>
        <p>账户余额：{{ this.$store.state.user.user_info[0].account_balance }} 元</p>
      </div>
    </el-card>
  </div>
</template>

<script>
import { CSTMR_TYPE_ORM, DOCUMENT_TYPE_ORM, RISK_RATING_ORM } from '../../constant'

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
    this.riskRatingOrm = RISK_RATING_ORM
    return {
      dialogFormVisible: false,
      riskFormVisible: false,
      cstmrID: '',
      modifyCstmrForm: {
        account_balance: Number
      },
      riskForm: {
        wave: '',
        goal: '',
        exp: '',
        level: '',
        rec: ''
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
    riskTest () {
      var result = '中'
      this.$alert(result, '风险评估结果', {
        confirmButtonText: '确定',
        callback: action => {
          this.riskFormVisible = false
        }
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
