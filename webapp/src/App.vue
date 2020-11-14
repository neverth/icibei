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
</script>
