<template>
  <div id="app">
    <NavSiderbar/>
    <KeyBoard/>
  </div>
</template>

<script>
import KeyBoard from './components/KeyBoard.vue'
import NavSiderbar from './components/NavSiderbar.vue'

export default {
  name: 'App',
  components: {
    KeyBoard,
    NavSiderbar
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
/* @font-face {
    font-family: 'sofia';
    src: url('../assets/ttf/sofiaprolight.woff2-webfont.woff') format('woff2'),
    url('../assets/ttf/sofiaprolight.woff2-webfont.woff') format('woff');
    font-weight: normal;
    font-style: normal;
} */
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
.display_i {
    margin: 80px auto 50px;
    text-align: center;
    width: 900px;
    font-family: sofia;
}
.chin {
    margin-top: 10px;
    font-size: 40px;
    color: #F2ECE4;
    font-weight: lighter;
    transition: 0.2s ;
}
.engli {
    font-size: 110px;
    color: #F2ECE4;
}
.engli span {
    transition: 0.2s ;
}

.registerInput {
          opacity: 0;
          transition: all 500ms ease-out 0ms;
      }

      #box {
          position: relative;
          overflow: hidden;
      }

      .bgColor {
          position: absolute;
          left: 0;
          top: 0;
          width: 40px;
          height: 35px;
          background-color: #F44336;
          border: #F44336;
          border-radius: 2.75rem;
      }

      .txt {
          position: absolute;
          height: 40px;
          line-height: 40px;
          font-size: 1rem;
          font-weight: 400;
          font-size: 14px;
          color: #73808b;
          text-align: center;
      }

      .slider {
          position: absolute;
          left: 0;
          top: -1px;
          width: 50px;
          height: 38px;
          background: #fff;
          text-align: center;
          cursor: move;
          border: 1px solid #ced4da;
          border-radius: 0.25rem;
      }

      .slider > i {
          position: absolute;
          top: 50%;
          left: 50%;
          font-size: 24px;
          color: #afa6a6;
          transform: translate(-50%, -50%);
      }

      .slider.active > i {
          color: green;
      }

      .safety-factor-div {
          position: absolute;
          top: 32px;
          left: 211px;
          width: 70px;
      }

      .safety-factor-div div {
          float: left;
          width: 21px;
          height: 39px;
          border-radius: 30px;
          margin: 0px -3px;
          visibility: visible;
          border: 1px solid #ced4da;
          opacity: 0;
          transition: all 500ms ease-out 0ms;
      }
      .modal-content {
          background-color: #ce6d39;
      }

</style>
