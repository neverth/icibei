<template>
  <div class="app-wrapper">
    <User />
  </div>
</template>>

<script>
import User from './user/index'
export default {

  name: 'Index',
  components: {
    User
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

<style lang="style" scope>

</style>
