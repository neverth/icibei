<template>
  <div class="home-container">
    <word-display></word-display>
    <key-board/>
  </div>
</template>

<script>
import WordDisplay from './components/WordDisplay'
import KeyBoard from './components/KeyBoard'
export default {
  name: 'Home',
  components: {
    WordDisplay,
    KeyBoard
  }
}
</script>

<style scoped>

</style>
