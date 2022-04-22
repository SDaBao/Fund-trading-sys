<template>
  <div>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="客户类型" prop="userType">
        <el-select v-model="ruleForm.userType" placeholder="请选择客户类型">
          <el-option label="个人" value="Personal"></el-option>
          <el-option label="机构" value="Organization"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="客户姓名" prop="name">
        <el-input v-model="ruleForm.name" placeholder="点击输入姓名"></el-input>
      </el-form-item>
      <el-form-item label="证件类型" prop="credentialsType">
        <el-select
          v-model="ruleForm.credentialsType"
          placeholder="请选择证件类型"
        >
          <el-option label="身份证" value="IdentityCard"></el-option>
          <el-option label="护照" value="Passport"></el-option>
          <el-option label="港澳台居住证" value="HMT"></el-option>
          <el-option
            label="组织机构代码证"
            value="OrganizationCode"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="证件号码" prop="credentialsNum">
        <el-input
          v-model="ruleForm.credentialsNum"
          placeholder="点击输入证件号码"
        ></el-input>
      </el-form-item>
      <el-form-item label="银行卡卡号" prop="cardId">
        <el-input
          v-model="ruleForm.cardId"
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
        name: '',
        userType: '',
        credentialsType: '',
        credentialsNum: '',
        cardId: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入客户姓名', trigger: 'blur' },
          { min: 2, message: '长度至少2个字符', trigger: 'blur' }
        ],
        userType: [
          { required: true, message: '请选择客户类型', trigger: 'change' }
        ],
        credentialsType: [
          { required: true, message: '请选择证件类型', trigger: 'change' }
        ],
        credentialsNum: [
          { required: true, message: '请输入证件号', trigger: 'blur' },
          {
            min: 18,
            max: 18,
            message: '身份证号码长度必须是18位',
            trigger: 'blur'
          }
        ],
        cardId: [
          { required: true, message: '请输入银行卡卡号添加', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
          this.close()
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
