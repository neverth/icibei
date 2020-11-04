<template>
  <div class="app-wrapper">
    <el-form ref="ruleForm" status-icon class="demo-ruleForm" size="small">
      <p>请输入账号</p>
      <el-form-item prop="username">
        <el-input v-model="registerForm.username" placeholder="Enter account" type="text" autocomplete="off" />
      </el-form-item>
      <p>请输入密码</p>
      <el-form-item prop="password">
        <el-input v-model="registerForm.password" placeholder="Enter password" type="password" autocomplete="off" />
      </el-form-item>
      <p>请确认密码</p>
      <el-form-item prop="password">
        <el-input v-model="registerForm.password" placeholder="Enter password" type="password" autocomplete="off" />
      </el-form-item>
      <el-form-item>
        <el-button size="medium" type="primary" @click.native.prevent="handleRegister">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>>

<script>
export default {
  name: 'LoginRegister',
  components: {
  },
  data() {
    return {
      registerForm: {
        username: '123',
        password: '123',
      }
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
    handleRegister() {
      this.$store.dispatch('user/register', this.registerForm).then(() => {

        this.registerForm.grant_type = 'password'
        this.$store.dispatch('user/login', this.registerForm).then((tokenInfo) => {
          this.$store.dispatch('user/getInfo', tokenInfo['userId']).then((userInfo) => {
            this.$router.push({ path: this.redirect || '/' })
            this.loading = false
          }).catch(() => {
            this.loading = false
          })
        }).catch(() => {
          this.loading = false
        })
      }).catch(() => {
        this.loading = false
      })
    }
  }
}
</script>

<style scope>
</style>
