<template>
  <div id="AppIndex">
    <!-- <h1>理财交易销售系统</h1>
    <router-link to="/customersearch">客户检索</router-link>
    <router-link to="/customerinformation">客户信息</router-link>
    <router-link to="/fundmanagement">资金管理</router-link> -->
    <el-container>
      <el-header class="topbar" style="background-color: #409eff">
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
                  <el-button size="mini" type="text" @click="visible = false"
                    >取消</el-button
                  >
                  <el-button type="primary" size="mini" @click="logout"
                    >确定</el-button
                  >
                </div>
                <!-- <el-button type="text" slot="reference">{{this.$store.getters.getUser.userName}}</el-button> -->
                <el-button type="goon" slot="reference">{{
                  this.$store.getters.getOperatorName
                }}</el-button>
              </el-popover>
            </li>
          </ul>
        </div>
      </el-header>
      <el-container>
        <el-aside width="250px">
          <el-menu
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            @select="handleSelect"
            background-color="#FFFFFF"
            text-color="#303133"
            active-text-color="#409EFF"
            style="text-align: left; padding-top: 10px"
            router
            :default-active="$route.path"
          >
            <el-menu-item-group title="柜台">
              <el-menu-item
                index="/index/customersearch"
                @click="isfirst = false"
              >
                <i class="el-icon-s-custom"></i>
                <span slot="title">客户检索</span>
              </el-menu-item>
              <el-submenu index="1" v-if="getShowLogin">
                <template slot="title" @click="isfirst = false">
                  <i class="el-icon-location"></i>
                  <span>{{ this.$store.getters.getUser }}</span>
                </template>
                <el-menu-item-group>
                  <el-menu-item index="/index/customerinformation">
                    <i class="el-icon-info"></i>
                    <span slot="title">客户信息</span>
                  </el-menu-item>
                  <el-menu-item index="/index/fundmanagement">
                    <i class="el-icon-s-finance"></i>
                    <span slot="title">资金管理</span>
                  </el-menu-item>
                  <el-menu-item index="/index/obtainfund">
                    <i class="el-icon-star-on"></i>
                    <span slot="title">持仓</span>
                  </el-menu-item>
                  <el-menu-item index="/index/productlist">
                    <i class="el-icon-s-marketing"></i>
                    <span slot="title">申购</span>
                  </el-menu-item>
                  <el-menu-item index="/index/businesslisttoperson">
                    <i class="el-icon-search"></i>
                    <span slot="title">订单查询</span>
                  </el-menu-item>
                </el-menu-item-group>
              </el-submenu>
            </el-menu-item-group>
            <div v-if="this.$store.state.operator.isAdmin">
              <el-divider style="margin: 10px"></el-divider>
              <el-menu-item-group title="运维">
                <el-menu-item
                  index="2-1"
                  :disabled="false"
                  @click="isfirst = false"
                >
                  <i class="el-icon-setting"></i>
                  <span slot="title">清算</span>
                </el-menu-item>
                <el-menu-item
                  index="2-2"
                  :disabled="false"
                  @click="isfirst = false"
                >
                  <i class="el-icon-setting"></i>
                  <span slot="title">回退</span>
                </el-menu-item>
                <el-menu-item
                  index="/index/productmanagement"
                  :disabled="false"
                  @click="isfirst = false"
                >
                  <i class="el-icon-setting"></i>
                  <span slot="title">产品管理</span>
                </el-menu-item>
                <el-menu-item
                  index="/index/businesslisttoall"
                  :disabled="false"
                  @click="isfirst = false"
                >
                  <i class="el-icon-setting"></i>
                  <span slot="title">订单查询</span>
                </el-menu-item>
              </el-menu-item-group>
            </div>
          </el-menu>
        </el-aside>
        <el-main style="overflow: hidden; padding: 0">
          <customer-search
            v-if="isfirst"
            @close-page="closepage()"
          ></customer-search>
            <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import CustomerSearch from './CustomerSearch.vue'
import CustomerInformation from './CustomerInformation.vue'
import { mapActions, mapGetters, mapState, mapMutations } from 'vuex'

export default {
  components: { CustomerInformation, CustomerSearch },
  name: 'AppIndex',
  data () {
    return {
      is_admin: false,
      visible: false,
      isfirst: true
    }
  },
  created () {
    // 获取浏览器localStorage，判断用户是否已经登录
    // if (localStorage.getItem('admin')) {
    //   // 如果已经登录，设置vuex登录状态
    //   this.actUser(JSON.parse(localStorage.getItem('admin')))
    // }
    sessionStorage.setItem('detail', '')
  },
  computed: {
    ...mapGetters(['getUser', 'getShowLogin']),
    ...mapState(['user', 'admin', 'showLogin']),
    ...mapMutations(['setUser', 'setShowLogin']),
    ...mapActions(['actUser', 'actShowLogin'])
  },
  methods: {
    logout () {
      this.visible = false
      // 清空本地登录信息
      sessionStorage.setItem('token', '')
      // 清空vuex登录信息
      // this.setAdmin('')
      // this.notifySucceed('成功退出登录')
      this.$router.push({ path: '/login' })
    },
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
    },
    closepage () {
      this.isfirst = false
    },
    isShowDetail () {
      if (sessionStorage.getItem('cstmr_id')) {
        this.isfirst = false
        this.$store.commit('setUser', sessionStorage.getItem('cstmr_name'))
        this.$store.commit('setUserID', sessionStorage.getItem('cstmr_id'))
      }
    }
  }
}
</script>

<style>
.el-container {
  height: 100vh;
}
.el-header,
.el-footer {
  background-color: #8bc4fd;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.el-aside {
  background-color: #ffffff;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #b8dbff;
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
  background: #409eff;
  border-color: #409eff;
  color: #303133;
}

.el-button--goon:focus,
.el-button--goon:hover {
  background: #8bc4fd;
  border-color: #409eff;
  color: #fff;
}

.el-button--goon {
  color: #fff;
  background-color: #409eff;
  border-color: #409eff;
}
</style>
