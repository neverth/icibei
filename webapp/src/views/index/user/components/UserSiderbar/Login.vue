<template>
  <div class="sidebar-header">
    <p>抱歉您还没有登录哦！</p>
    <p>没有账号？<a href="#" @click="toggledView">点击注册</a></p>
    <transition name="component-fade" mode="out-in">
      <component :is="view" />
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
      view: 'LoginLogin'
    }
  },
  methods: {
    toggledView() {
      this.view = 'LoginRegister'
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
