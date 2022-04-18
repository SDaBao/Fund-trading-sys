<template>
  <div id="fundmanagement"
       style="height:80%; width:80%; transform:translate(12%,12%)">
    <el-card class="box-card"
             style="height: calc(100% - 40px); height: calc(100% - 40px); margin: 20px"
             shadow="hover">
      <div>
        <el-row>
          <el-col :span="18">
            <p style="font-size:30px">资金信息</p>
          </el-col>
          <el-col :span="6">
            <el-button size="medium">
              银行卡绑定
            </el-button>
            <el-button size="medium">
              充值
            </el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-descriptions class="margin-top"
                           :column="2"
                           :size="size"
                           border>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user"></i>
                客户名称
              </template>
              顾威
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-mobile-phone"></i>
                银行卡1卡号
              </template>
              1234567887654321
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-location-outline"></i>
                银行卡1余额
              </template>
              5011234.00
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-tickets"></i>
                银行卡2卡号
              </template>
              未绑定
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-office-building"></i>
                银行卡2余额
              </template>
              ***
            </el-descriptions-item>
          </el-descriptions>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'FundManagement',
  data () {
    return {
      size: 'medium'
    }
  }
}
</script>

<style scoped>
.el-button {
  margin-top: 40px;
}
</style>
