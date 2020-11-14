<template>
  <div class="home-container">
    <word-display @myKeyDown="keyDown" @myKeyUp="keyUp"></word-display>
    <key-board :keyDown="wdkeyDown" :keyUp="wdkeyUp"/>
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
  },
  data(){
    return {
      wdkeyDown: [],
      wdkeyUp: []
    }
  },
  methods: {
    keyDown(key){this.wdkeyDown = key},
    keyUp(key){this.wdkeyUp = key}
  }
}
</script>

<style lang="scss" scoped>
.home-container{
  padding-top: 20px;
  max-height: calc(100vh);
}
</style>
