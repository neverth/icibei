<template>
  <div class="keyboard-wrapper">
    <ul>
      <li class="drum" data-key="1" data-code="49">1</li>
      <li class="drum" data-key="2" data-code="50">2</li>
      <li class="drum" data-key="3" data-code="51">3</li>
      <li class="drum" data-key="4" data-code="52">4</li>
      <li class="drum" data-key="5" data-code="53">5</li>
      <li class="drum" data-key="6" data-code="54">6</li>
      <li class="drum" data-key="7" data-code="55">7</li>
      <li class="drum" data-key="8" data-code="56">8</li>
      <li class="drum" data-key="9" data-code="57">9</li>
      <li class="drum" data-key="0" data-code="48">0</li>
      <li class="drum" data-key="-" data-code="173">-</li>
    </ul>

    <ul>
      <li class="drum" data-key="q" data-code="81">q</li>
      <li class="drum" data-key="w" data-code="87">w</li>
      <li class="drum" data-key="e" data-code="69">e</li>
      <li class="drum" data-key="r" data-code="82">r</li>
      <li class="drum" data-key="t" data-code="84">t</li>
      <li class="drum" data-key="y" data-code="89">y</li>
      <li class="drum" data-key="u" data-code="85">u</li>
      <li class="drum" data-key="i" data-code="73">i</li>
      <li class="drum" data-key="o" data-code="79">o</li>
      <li class="drum" data-key="p" data-code="80">p</li>
    </ul>
    <ul>
      <li class="drum" data-key="a" data-code="65">a</li>
      <li class="drum" data-key="s" data-code="83">s</li>
      <li class="drum" data-key="d" data-code="68">d</li>
      <li class="drum" data-key="f" data-code="70">f</li>
      <li class="drum" data-key="g" data-code="71">g</li>
      <li class="drum" data-key="h" data-code="72">h</li>
      <li class="drum" data-key="j" data-code="74">j</li>
      <li class="drum" data-key="k" data-code="75">k</li>
      <li class="drum" data-key="l" data-code="76">l</li>
    </ul>
    <ul>
      <li class="drum" data-key="z" data-code="90">z</li>
      <li class="drum" data-key="x" data-code="88">x</li>
      <li class="drum" data-key="c" data-code="67">c</li>
      <li class="drum" data-key="v" data-code="86">v</li>
      <li class="drum" data-key="b" data-code="66">b</li>
      <li class="drum" data-key="n" data-code="78">n</li>
      <li class="drum" data-key="m" data-code="77">m</li>
      <li class="drum" data-key="," data-code="188">,</li>
    </ul>
    <ul>
      <li class="effect" data-key="SPACE" data-code="32" style="color: #CE6D39">I C I B E I</li>
    </ul>
  </div>
</template>

<script>
import $ from 'jquery'

export default {
  name: 'KeyBoard'
}
// only for display
$(window).keydown(function (event) {
  const code = (event.keyCode ? event.keyCode : event.which)
  $('li[data-code=\'' + code + '\']').addClass('active')
})

$(window).keyup(function (event) {
  const code = (event.keyCode ? event.keyCode : event.which)
  $('li[data-code=\'' + code + '\']').removeClass('active')
})
</script>

<style scoped>
/* 引入bootstrap之后会会变形 */
*,
::after,
::before {
  box-sizing: unset;
}

.keyboard-wrapper {
  text-align: center;
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
  transition: 0.5s;
  color: #7f8c8d;
}

.keyboard-wrapper li:hover {
  cursor: pointer;
  color: rgb(255, 255, 255);
}

.keyboard-wrapper [data-code="32"] {
  width: 40vmin;
  margin-top: 10px;
}

.active {
  transition: 0s;
  transform: scale(1.10);
  background-color: rgb(75, 75, 75);
}

.effect.active {
  background-color: #CE6D39;
}
</style>
