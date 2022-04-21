<template>
  <div>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="客户类型" prop="cstmr_type">
        <el-select v-model="ruleForm.cstmr_type" placeholder="请选择客户类型">
          <el-option label="个人" value="1"></el-option>
          <el-option label="机构" value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="客户姓名" prop="cstmr_name">
        <el-input
          v-model="ruleForm.cstmr_name"
          placeholder="点击输入姓名"
        ></el-input>
      </el-form-item>
      <el-form-item label="证件类型" prop="document_type">
        <el-select
          v-model="ruleForm.document_type"
          placeholder="请选择证件类型"
        >
          <el-option label="身份证" value="1"></el-option>
          <el-option label="护照" value="2"></el-option>
          <el-option label="港澳台居住证" value="3"></el-option>
          <el-option
            label="组织机构代码证"
            value="4"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="证件号码" prop="document_num">
        <el-input
          v-model="ruleForm.document_num"
          placeholder="点击输入证件号码"
        ></el-input>
      </el-form-item>
      <el-form-item label="银行卡卡号" prop="card_id">
        <el-input
          v-model="ruleForm.card_id"
          placeholder="点击输入本人的银行卡号"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')"
          >立即创建</el-button
        >
        <el-button @click="resetForm('ruleForm')">重 置</el-button>
        <el-button @click="close()">取 消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'createCustomerInformation',
  props: { isShow: String },
  data () {
    return {
      ruleForm: {
        // name: '',
        // userType: '',
        // credentialsType: '',
        // credentialsNum: '',
        // cardId: ''
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
        card_id: [
          { required: true, message: '请输入银行卡卡号添加', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      let vm = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let params = vm.formName
          let url = '/api/user/register'
          this.$axios
            .get(url, { params })
            .then((res) => {
              console.log('请求成功')
              alert('submit!')
              this.close()
              console.log(res)
            })
            .catch((error) => {
              console.log('请求失败')
              console.log(error)
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    close () {
      console.log('close')
      this.$emit('close-form')
    }
  }
}
</script>
