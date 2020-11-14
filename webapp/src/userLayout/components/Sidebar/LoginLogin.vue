<template>
  <div class="LoginLogin-wrapper">
    <el-form
      label-position="top"
      :model="loginForm"
      status-icon
      :rules="rules"
      ref="loginForm"
      size="small"
    >
      <el-form-item label="账号：" prop="username">
        <el-input v-model="loginForm.username" placeholder="Enter account" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="密码：" prop="password">
        <el-input v-model="loginForm.password" placeholder="Enter password" type="password" autocomplete="off"/>
      </el-form-item>
      <el-form-item>
        <el-button :loading="loading" size="medium" type="primary" @click="handleLogin('loginForm')">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>>

<script>
export default {
  name: 'LoginLogin',
  props: {
    username: String
  },
  data() {
    let validateUsername = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账号'));
      } else {
        callback();
      }
    }
    let validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    }
    return {
      loginForm: {
        username: this.username,
        password: '',
        grant_type: 'password'
      },
      rules: {
        username: [{validator: validateUsername, trigger: 'blur'}],
        password: [{validator: validatePassword, trigger: 'blur'}],
      },
      isRouterAlive: true,
      loading: false,
    }
  },
  methods: {
    handleLogin(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm).then((tokenInfo) => {
            this.$store.dispatch('user/getInfo', tokenInfo['userId']).then((userInfo) => {
              this.$router.push({path: this.redirect || '/'})
              this.loading = false
            }).catch((data) => {
              this.$message.error(data);
              this.loading = false
            })
          }).catch((data) => {
            this.$message.error(data);
            this.loading = false
            this.loginForm.password = ''
          })
        } else {
          this.$message.error('请填写登陆信息！');
        }
      });
    },
    reload() {
      this.isRouterAlive = false
      this.$nextTick(() => (this.isRouterAlive = true))
    }
  }
}
</script>

<style lang="scss">
.LoginLogin-wrapper {
  .el-form-item__label, .el-form-item__error {
    color: whitesmoke;
  }
}
</style>
