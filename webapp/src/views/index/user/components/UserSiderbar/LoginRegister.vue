<template>
  <div class="app-wrapper">
    <el-form ref="ruleForm" status-icon class="demo-ruleForm" size="small">
      <p>请输入账号</p>
      <el-form-item prop="username">
        <el-input placeholder="Enter account" type="text" autocomplete="off" />
      </el-form-item>
      <p>请输入密码</p>
      <el-form-item prop="password">
        <el-input placeholder="Enter password" type="password" autocomplete="off" />
      </el-form-item>
      <p>请确认密码</p>
      <el-form-item prop="password">
        <el-input placeholder="Enter password" type="password" autocomplete="off" />
      </el-form-item>
      <el-form-item>
        <el-button size="medium" type="primary">注册</el-button>
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
  },
  created() {
    console.log('created')
  },
  mounted() {
    console.log('mounted')
  },
  beforeDestroy() {
    console.log('beforeDestroy')
  }
}
</script>

<style scope>
</style>
