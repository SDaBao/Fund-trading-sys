<template>
  <div>
    <el-form :model="ruleForm"
             :rules="rules"
             ref="ruleForm"
             label-width="100px"
             class="demo-ruleForm">
      <el-form-item label="银行卡" prop="card">
        <el-select v-model="ruleForm.card" placeholder="选择银行卡" style="width:250px">
          <el-option :label="item.credit_id"
                     :value="item.credit_id"
                     v-for="(item, i) in this.$store.state.card.card_info" :key="i"
                     >
            卡号：{{ item.credit_id }}
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="金额" prop="money">
        <el-input placeholder="金额 0.00" v-model="ruleForm.money" style="width:250px"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="priamry" @click="submitForm(1)">充值</el-button>
      <el-button type="priamry" @click="submitForm(2)">提现</el-button>
      <el-button type="priamry" @click="submitForm1()">解绑</el-button>
      <el-button @click="close()">取消</el-button>
    </div>
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
    submitForm (type) {
      let url = '/api/bankTrade'
      this.cstmrID = sessionStorage.getItem('cstmr_id')
      this.$axios
        .get(url, { params: {
          trade_type: type,
          cstmr_id: this.cstmrID,
          credit_id: this.ruleForm.card,
          trade_value: this.ruleForm.money
        } })
        .then((res) => {
          console.log(res.data.result)
          this.$message({
            type: 'info',
            message: `${res.data.result}`
          })
          console.log(res)
          this.close()
        })
        .catch((error) => {
          console.log(error)
          this.$message({
            type: 'error',
            message: `${error}`
          })
        })
    },
    submitForm1 () {
      let url = '/api/addCard'
      this.cstmrID = sessionStorage.getItem('cstmr_id')
      this.$axios
        .get(url, { params: {
          op_type: 2,
          cstmr_id: this.cstmrID,
          credit_id: this.ruleForm.card
        } })
        .then((res) => {
          console.log('res.data')
          if (res.data === 'false') {
            this.$message({
              type: 'error',
              message: `${res.data}`
            })
            return
          }
          this.$message({
            type: 'info',
            message: `${res.data}`
          })
          this.close()
          console.log(res)
        })
        .catch((error) => {
          this.$message({
            type: 'error',
            message: `解绑失败 ${error}`
          })
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
