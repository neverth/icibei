<template>
  <div class="app-wrapper">
    <UserSiderbar />
  </div>
</template>>

<script>
import { UserSiderbar } from './components/UserSiderbar'
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

<style lang="style" scope>

</style>
