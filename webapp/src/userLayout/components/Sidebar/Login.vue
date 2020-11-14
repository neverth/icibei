<template>
  <div class="sidebar-header">
    <p>抱歉您还没有登录哦！</p>
    <p>{{viewText[0]}}<el-button size="mini" @click="toggledView" round>{{viewText[1]}}</el-button></p>
    <transition name="component-fade" mode="out-in">
      <component :username="username" @registerOk="registerOk" :is="view"/>
    </transition>
  </div>
</template>

<script>
import LoginLogin from './LoginLogin'
import LoginRegister from './LoginRegister'

export default {
  name: 'Login',
  components: {
    LoginRegister,
    LoginLogin
  },
  data() {
    return {
      view: 'LoginLogin',
      viewText: ['没有账号？', '点击注册'],
      username: ''
    }
  },
  computed: {
  },
  methods: {
    toggledView() {
      if (this.viewText[0] === '没有账号？') {
        this.viewText = ['已经有账号？', '点击登录']
      } else {
        this.viewText = ['没有账号？', '点击注册']
      }
      this.view = this.view === 'LoginLogin' ? 'LoginRegister' : 'LoginLogin'
    },
    registerOk(registerForm){
      this.toggledView()
      this.username = registerForm.username
    }
  }
}
</script>

<style scoped>
.component-fade-enter-active, .component-fade-leave-active {
  transition: opacity .3s ease;
}

.component-fade-enter, .component-fade-leave-to {
  opacity: 0;
}

.sidebar-header {
  padding: 20px;
  overflow: hidden;
  border-top: 1px solid #b46236;
}

.registerInput, #registerButton {
  transition: all 0.5s;
}

.sidebar-header p {
  margin-bottom: 1rem;
}

.registerInput {
  opacity: 0;
}

#registerButton {
  top: -160px;
  position: relative;
}
</style>
