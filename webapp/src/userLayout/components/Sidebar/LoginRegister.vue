<template>
  <div class="LoginRegister-container">
    <el-form
      label-position="top"
      :model="registerForm"
      status-icon
      :rules="rules"
      ref="registerForm"
      size="small"
    >
      <el-form-item label="账号：" prop="username">
        <el-input v-model="registerForm.username" placeholder="Enter account" type="text" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="密码：" prop="password">
        <el-input v-model="registerForm.password" placeholder="Enter password" type="password" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="确认密码：" prop="checkPassword">
        <el-input v-model="registerForm.checkPassword" placeholder="Enter password" type="password" autocomplete="off"/>
      </el-form-item>
      <el-form-item>
        <el-button  size="medium" type="primary" @click="handleRegister('registerForm')">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>>

<script>
import {validateUniqueUserName} from '@/api/user'

export default {
  name: 'LoginRegister',
  data() {
    let validateUserName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账号'));
      }
      //
      else if (value.length < 3) {
        callback(new Error('账号必须大于3个字符'));
      }
      //
      else if (!/^\w+$/.test(value)) {
        callback(new Error('账号由数字、26个英文字母或者下划线组成'));
      }
      //
      else {
        new Promise((resolve, reject) => {
          validateUniqueUserName(value).then(response => {
            const {data} = response
            if (!data) {
              reject('该账号已被使用，请更换')
            }
            resolve()
          }).catch(error => {
            reject(error)
          })
        }).then(() => {
          callback();
        }).catch((error) => {
          callback(new Error(error));
        })
      }
    }
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else if (value.length < 6) {
        callback(new Error('密码必须大于等于6位'));
      } else {
        if (this.registerForm.checkPassword !== '') {
          this.$refs.registerForm.validateField('checkPassword');
        }
        callback();
      }
    }
    let validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    }
    return {
      // registerForm中字段和rules中字段名称要对应
      registerForm: {
        username: '',
        password: '',
        checkPassword: '',
      },
      rules: {
        username: [{validator: validateUserName, trigger: 'blur'}],
        password: [{validator: validatePass, trigger: 'blur'}],
        checkPassword: [{validator: validatePass2, trigger: 'blur'}]
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
    handleRegister(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$store.dispatch('user/register', this.registerForm).then(() => {
            this.$message.success('注册成功');
            this.$emit('registerOk', this.registerForm)
          }).catch(() => {
            this.$message.error('注册失败，请稍后再试！');
          })
        } else {
          this.$message.error('请按照规则填写注册信息！');
        }
      });
    }
  }
}
</script>

<style lang="scss">
.LoginRegister-container {
  .el-form-item__label, .el-form-item__error {
    color: whitesmoke;
  }
}
</style>
