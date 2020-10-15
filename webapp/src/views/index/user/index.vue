<template>
  <div class="app-wrapper">
    <UserSiderbar />
  </div>
</template>>

<script>
import UserSiderbar from './components/UserSiderbar/index'
export default {
  name: 'User',
  components: {
    UserSiderbar
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

* {
    margin: 0;
    padding: 0;
    list-style: none;
}

body, html {
    background-color: #082730;
    font-family: "Microsoft YaHei";
    color: white;
}
</style>
