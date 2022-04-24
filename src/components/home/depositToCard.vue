<template>
  <div>
    <el-form :model="ruleForm"
             :rules="rules"
             ref="ruleForm"
             label-width="100px"
             class="demo-ruleForm">
      <el-form-item label="银行卡选择"
                    prop="card">
        <el-select v-model="ruleForm.card"
                   placeholder="请选择你要充值的银行卡">
          <el-option label="银行卡1"
                     value="card_1"></el-option>
          <el-option label="银行卡2"
                     value="card_2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="充值金额"
                    prop="money">
        <el-input placeholder="金额"
                  v-model="ruleForm.money"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="priamry"
                   @click="submitForm()">充值</el-button>
        <el-button @click="close()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'deposittocard',
  props: { isShow: String },
  data () {
    return {
      cstmrID: '',
      ruleForm: {
        money: '',
        card: ''
      },
      rules: {
        card: [
          { required: true, message: '请选择银行卡', trigger: 'blur' }
        ],
        money: [
          { required: true, message: '输入充值金额', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm () {
      let url = '/api/bank_trade'
      this.cstmrID = sessionStorage.getItem('cstmr_id')
      this.$axios
        .get(url, { params: {
          trade_type: 1,
          cstmr_id: this.cstmrID,
          credit_id: this.ruleForm.card,
          trade_value: this.ruleForm.money
        } })
        .then((res) => {
          console.log('请求成功')
          alert('submit!')
          this.close1()
          console.log(res)
        })
        .catch((error) => {
          console.log('请求失败')
          console.log(error)
        })
    },
    close () {
      console.log('close')
      this.$emit('close-form')
    }
  }
}
</script>
