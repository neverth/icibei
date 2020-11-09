<template>
  <div id="app">
    <router-view v-if="isRouterAlive"/>
  </div>
</template>

<script>
export default {
  name: 'App',
  provide() {
    return {
      reloadApp: this.reloadApp
    }
  },
  data() {
    return {
      isRouterAlive: true
    }
  },
  methods: {
    reloadApp() {
      this.isRouterAlive = false
      this.$nextTick(() => {
        this.isRouterAlive = true
      })
    }
  }
}

// 全局监听键盘事件，因为在两个页面中只有一个document.onkeydown会被触发
document.onkeydown = function (event) {
  // 可能还未被渲染或被销毁
  if (window.handleKeyDownWordDisplay !== undefined){
    handleKeyDownWordDisplay(event.key)
  }
  if (window.handleKeyDownKeyBoard !== undefined){
    handleKeyDownKeyBoard(event.key)
  }
}

document.onkeyup = function (event) {
  if (window.handleKeyUpKeyBoard !== undefined){
    handleKeyUpKeyBoard(event.key)
  }
}
</script>
