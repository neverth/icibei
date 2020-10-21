<template>
  <div class="app-wrapper">
    <UserSiderbar/>
    <WordDisplay/>
  </div>
</template>>

<script>
import UserSiderbar from './components/UserSiderbar/index'
import WordDisplay from './components/WordDisplay'

export default {
  name: 'User',
  components: {
    UserSiderbar,
    WordDisplay
  },
  data() {
    return {}
  },
  computed: {},
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
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  color: white;
  height: auto;
}
</style>
