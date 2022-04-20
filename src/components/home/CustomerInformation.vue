<template>
  <div id="customerinformation"
       style="height:60%; width:60%; transform:translate(30%,30%)">
    <el-card class="box-card"
             style="height: calc(100% - 30px); height: calc(100% - 40px); margin: 20px; padding: 30px;"
             shadow="always">
      <div>
        <el-row>
          <el-col :span="21">
            <p style="font-size:27px">客户基本信息</p>
          </el-col>
          <el-col>
            <el-button style="float:right">
              修改
            </el-button>
          </el-col>
        </el-row>
      </div>
      <div style="text-align:left; font-size:20px">
        <p>客户姓名:</p>
        <p>客户类型:</p>
        <p>证件类型:</p>
        <p>证件号:</p>
        <p>银行卡所属银行:</p>
        <p>银行卡卡号:</p>
      </div>
    </el-card>
  </div>
</template>
<style scoped></style>
