<template>
  <div>
    <User/>
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

<style lang="style" scope>

</style>
