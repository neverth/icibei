<template>
  <div class="app-wrapper">
    <UserSiderbar/>
    <KeyBoard />
  </div>
</template>>

<script>
import UserSiderbar from './components/UserSiderbar/index'
import KeyBoard from './components/KeyBoard/KeyBoard'

export default {
  name: 'User',
  components: {
    UserSiderbar,
    KeyBoard
  },
  data() {
    return {}
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

* {
  margin: 0;
  padding: 0;
  list-style: none;
}

body, html {
  background-color: #082730;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  color: white;
}
</style>
