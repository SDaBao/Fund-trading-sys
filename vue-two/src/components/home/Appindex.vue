<template>
  <div id="AppIndex">
    <!-- <h1>理财交易销售系统</h1>
    <router-link to="/customersearch">客户检索</router-link>
    <router-link to="/customerinformation">客户信息</router-link>
    <router-link to="/fundmanagement">资金管理</router-link> -->
    <el-container>
      <el-header class="topbar" style="background-color: #409EFF">
        <div class="nav">
          <span style="color: #fff; font-size: 25px; position: absolute"
            >理财交易销售系统</span
          >
          <ul>
            <li>
              欢迎
              <el-popover placement="top" width="180" v-model="visible">
                <p>确定退出登录吗？</p>
                <div style="text-align: right; margin: 10px 0 0">
                  <el-button size="mini" type="text" @click="visible = false">取消</el-button>
                  <el-button type="primary" size="mini" @click="logout">确定</el-button>
                </div>
                <!-- <el-button type="text" slot="reference">{{this.$store.getters.getUser.userName}}</el-button> -->
                <el-button type="goon" slot="reference">小明</el-button>
              </el-popover>
            </li>
          </ul>
        </div>
      </el-header>
      <el-container>
        <el-aside width="250px">
          <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            @select="handleSelect"
            background-color="#FFFFFF"
            text-color="#303133"
            active-text-color="#409EFF"
            style="text-align: left; padding-top: 10px"
          >
            <el-menu-item-group title="柜台">
              <el-menu-item index="1-1">
                <i class="el-icon-s-custom"></i>
                <span slot="title">客户检索</span>
              </el-menu-item>
              <el-menu-item index="1-2">
                <i class="el-icon-info"></i>
                <span slot="title">客户信息</span>
              </el-menu-item>
              <el-menu-item index="1-3">
                <i class="el-icon-s-finance"></i>
                <span slot="title">资金管理</span>
              </el-menu-item>
              <el-menu-item index="1-4">
                <i class="el-icon-star-on"></i>
                <span slot="title">持仓</span>
              </el-menu-item>
              <el-menu-item index="1-5">
                <i class="el-icon-s-marketing"></i>
                <span slot="title">申购</span>
              </el-menu-item>
              <el-menu-item index="1-6">
                <i class="el-icon-search"></i>
                <span slot="title">订单查询</span>
              </el-menu-item>
            </el-menu-item-group>
            <el-divider style="margin: 10px"></el-divider>
            <el-menu-item-group title="运维">
              <el-menu-item index="2-1" :disabled="!is_admin">
                <i class="el-icon-setting"></i>
                <span slot="title">清算</span>
              </el-menu-item>
              <el-menu-item index="2-2" :disabled="!is_admin">
                <i class="el-icon-setting"></i>
                <span slot="title">回退</span>
              </el-menu-item>
              <el-menu-item index="2-3" :disabled="!is_admin">
                <i class="el-icon-setting"></i>
                <span slot="title">产品管理</span>
              </el-menu-item>
              <el-menu-item index="2-4" :disabled="!is_admin">
                <i class="el-icon-setting"></i>
                <span slot="title">订单查询</span>
              </el-menu-item>
            </el-menu-item-group>
          </el-menu>
        </el-aside>
        <el-main style="overflow: hidden; padding: 0">
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'AppIndex',
  data () {
    return {
      is_admin: false
    }
  },
  methods: {
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
      if (key === '1-1') {
        this.$router.push({ path: '/index/customersearch' })
      } else if (key === '1-2') {
        this.$router.push({ path: '/index/customerinformation' })
      } else if (key === '1-4') {
        this.$router.push({ path: '/index/fundmanagement' })
      } else if (key === '1-3') {
        this.$router.push({ path: '/index/fundmanagement' })
      } else if (key === '1-5') {
        this.content = 'product_list.html'
      } else if (key === '1-6') {
        this.content = 'deal_list.html'
      } else if (key === '2-3') {
        this.content = 'product_manage_list.html'
      } else if (key === '2-4') {
        this.content = 'deal_list.html'
      }
    }
  }
}
</script>

<style>
.el-container {
  height: 100vh;
}
.el-header ,
.el-footer {
  background-color: #8bc4fd;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.el-aside {
  background-color: #FFFFFF;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #B8DBFF;
  color: #333;
  /* text-align: left; */
  /* line-height: 160px; */
}

.topbar .nav li {
  float: left;
  color: #fff;
  font-size: 14px;
  text-align: center;
  line-height: 40px;
  margin-left: 20px;
}
.topbar .nav {
  margin: 0 auto;
}
.topbar .nav ul {
  float: right;
  color: #fff;
  list-style-type: none;
  height: 60px;
  margin: 10px auto;
}

.el-button--goon.is-active,
.el-button--goon:active {
  background: #409EFF;
  border-color: #409EFF;
  color: #303133;
}

.el-button--goon:focus,
.el-button--goon:hover {
  background: #8bc4fd;
  border-color: #409EFF;
  color: #fff;
}

.el-button--goon {
  color: #FFF;
  background-color: #409EFF;
  border-color: #409EFF;
}
</style>
