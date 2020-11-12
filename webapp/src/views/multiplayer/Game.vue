<template>
  <div style="width: 80%; margin: 0 auto; padding: 10px">
    <div class="track">
      <div class="track-ticker"></div>
      <div class="player">
        <div class="player-name"><span>neverth</span></div>
        <div class="player-lane">AAA</div>
        <div class="player-details">sdfdsf</div>
      </div>
      <div class="player">
        <div class="player-name">neverth</div>
        <div class="player-lane">AAA</div>
        <div class="player-details">sdfdsf</div>
      </div>
      <div class="player">
        <div class="player-name">neverth</div>
        <div class="player-lane">AAA</div>
        <div class="player-details">sdfdsf</div>
      </div>
      <div class="player">
        <div class="player-name">neverth</div>
        <div class="player-lane">AAA</div>
        <div class="player-details">sdfdsf</div>
      </div>
    </div>
    <word-display/>
  </div>
</template>

<script>
export default {
  name: "Game"
}
</script>

<style scoped>
.track {
  position: relative;
  border: 1px solid white;
}

.player {
  position: relative;
  border: 1px solid white;
  height: 40px;
  padding: 2px;
}

.player-name {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
}

.player-lane {
  position: absolute;
  top: 0;
  right: 0;
  left: 6rem;
  height: 100%;
  border-left: 1px solid rgba(0, 10, 20, .1);
}

.player-details {
  position: absolute;
  right: 0;
  bottom: 0;
  padding: .5em 1em;
  color: #26243d;
  background-color: rgba(233, 222, 222, .8);
  font-size: .8rem;
  line-height: .8rem;
}
</style>
