<template>
  <div class="app-wrapper">
    <el-form ref="ruleForm" status-icon class="demo-ruleForm" size="small">
      <p>请输入账号</p>
      <el-form-item prop="username">
        <el-input v-model="loginForm.username" placeholder="Enter account" autocomplete="off" />
      </el-form-item>
      <p>请输入密码</p>
      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          placeholder="Enter password"
          type="password"
          autocomplete="off"
        />
      </el-form-item>
      <el-form-item>
        <el-button size="medium" type="primary" @click.native.prevent="handleLogin">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>>

<script>
export default {
  name: 'LoginLogin',
  components: {
  },
  data() {
    return {
      loginForm: {
        username: '123',
        password: '123',
        grant_type: 'password'
      },
      isRouterAlive: true
    }
  },
  created() {
    console.log('created')
  },
  mounted() {
    console.log('mounted')
  },
  beforeDestroy() {
    console.log('beforeDestroy')
  },
  methods: {
    handleLogin() {
      debugger
      this.$store.dispatch('user/login', this.loginForm).then((tokenInfo) => {
        this.$store.dispatch('user/getInfo', tokenInfo['userId']).then((userInfo) => {
          this.$router.push({ path: this.redirect || '/' })
          this.loading = false
        }).catch(() => {
          this.loading = false
        })
      }).catch(() => {
        this.loading = false
      })
    },
    reload() {
      this.isRouterAlive = false
      this.$nextTick(() => (this.isRouterAlive = true))
    }
  }
}
</script>

<style scope>
</style>
