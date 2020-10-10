<template>
  <div id="app">

    <NavSiderbar/>
    <KeyBoard/>
  </div>
</template>

<script>
import KeyBoard from './components/KeyBoard.vue'
import NavSiderbar from './components/NavSiderbar.vue'
import 'bootstrap/dist/css/bootstrap.min.css'

export default {
  name: 'App',
  components: {
    KeyBoard,
    NavSiderbar
  }
}
</script>

<style>
@font-face {
    font-family: 'sofia';
    src: url('./assets/ttf/sofiaprolight.woff2-webfont.woff') format('woff2'),
    url('./assets/ttf/sofiaprolight.woff2-webfont.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

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
