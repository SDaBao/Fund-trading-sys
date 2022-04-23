<template>
  <div>
    <el-form :model="ruleForm"
             :rules="rules"
             ref="ruleForm"
             label-width="100px"
             class="demo-ruleForm">
      <el-form-item label="银行卡卡号"
                    prop="card">
        <el-input v-model="ruleForm.card"
                  placeholder="请输入银行卡卡号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="priamry"
                   @click="submitForm1()">立即添加</el-button>
        <el-button @click="close1()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'attachcard',
  props: { isShow1: String },
  data () {
    return {
      cstmrID: '',
      ruleForm: {
        card: ''
      },
      rules: {
        card: [
          {
            required: true, message: '请输入银行卡卡号添加', trigger: 'blur'
          }
        ]
      }
    }
  },
  methods: {
    submitForm1 () {
      let url = '/api/addCard'
      this.cstmrID = sessionStorage.getItem('cstmr_id')
      this.$axios
        .get(url, { params: {
          op_type: 1,
          cstmr_id: this.cstmrID,
          credit_id: this.ruleForm.card
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
    close1 () {
      console.log('close')
      this.$emit('close-form1')
    }
  }
}
</script>
