<template>
  <div class="keyboard-wrapper">
    <ul>
      <li data-key="1">1</li>
      <li data-key="2">2</li>
      <li data-key="3">3</li>
      <li data-key="4">4</li>
      <li data-key="5">5</li>
      <li data-key="6">6</li>
      <li data-key="7">7</li>
      <li data-key="8">8</li>
      <li data-key="9">9</li>
      <li data-key="0">0</li>
      <li data-key="-">-</li>
    </ul>

    <ul>
      <li data-key="q">q</li>
      <li data-key="w">w</li>
      <li data-key="e">e</li>
      <li data-key="r">r</li>
      <li data-key="t">t</li>
      <li data-key="y">y</li>
      <li data-key="u">u</li>
      <li data-key="i">i</li>
      <li data-key="o">o</li>
      <li data-key="p">p</li>
    </ul>
    <ul>
      <li data-key="a">a</li>
      <li data-key="s">s</li>
      <li data-key="d">d</li>
      <li data-key="f">f</li>
      <li data-key="g">g</li>
      <li data-key="h">h</li>
      <li data-key="j">j</li>
      <li data-key="k">k</li>
      <li data-key="l">l</li>
    </ul>
    <ul>
      <li data-key="z">z</li>
      <li data-key="x">x</li>
      <li data-key="c">c</li>
      <li data-key="v">v</li>
      <li data-key="b">b</li>
      <li data-key="n">n</li>
      <li data-key="m">m</li>
      <li data-key=",">,</li>
    </ul>
    <ul>
      <li class="effect" data-key=" " style="color: #CE6D39">I C I B E I</li>
    </ul>
  </div>
</template>

<script>

export default {
  name: 'KeyBoard',
  mounted() {
    console.log('KeyBoard mounted')
    window.handleKeyDownKeyBoard = this.handleKeyDown
    window.handleKeyUpKeyBoard = this.handleKeyUp
  },
  beforeDestroy() {
    console.log('KeyBoard beforeDestroy')
    window.handleKeyDownKeyBoard = undefined
    window.handleKeyUpKeyBoard = undefined
  },
  methods: {
    handleKeyDown(key) {
      let el = document.querySelector(`li[data-key='${key}']`)
      if (el) {
        el.classList.add('active')
      }
    },
    handleKeyUp(key) {
      let el = document.querySelector(`li[data-key='${key}']`)
      if (el) {
        el.classList.remove('active')
      }
    }
  }
}

</script>

<style scoped>

.keyboard-wrapper {
  text-align: center;
  box-sizing: unset;
}

.keyboard-wrapper ul {
  text-align: center;
  white-space: nowrap;
  margin-top: 0.5em !important;
}

.keyboard-wrapper li {
  display: inline-block;
  margin: -0.5vmin 0.75vmin;
  width: 5vmin;
  height: 5vmin;
  padding: 1vmin;
  border: 0.25vmin solid rgb(75, 75, 75);
  border-radius: 3vmin;
  line-height: 5vmin;
  text-align: center;
  text-transform: uppercase;
  user-select: none;
  transition: 0.3s;
  color: #7f8c8d;
}

.keyboard-wrapper li:hover {
  cursor: pointer;
  color: rgb(255, 255, 255);
}

.keyboard-wrapper [data-key=" "] {
  width: 40vmin;
  margin-top: 10px;
}

.active {
  transition: 0.2s;
  transform: scale(1.10);
  background-color: rgb(75, 75, 75);
}

.effect.active {
  background-color: #CE6D39;
}
</style>
