<template>
  <div class="multiplayer-container">
    <div style="font-size: 26px; font-weight: bold">
      <span>多人竞速</span>
      <span style="font-size: 12px; margin-left: 10px">{{ wsLogin ? '已连接服务器' : '未连接服务器' }}</span>
    </div>
    <el-button @click="wsSend(`${command.CreateRoom}`)">创建房间</el-button>
    <el-button @click="wsSend(`${command.GameStart} ${room.roomId}`)">游戏开始</el-button>
    <el-button type="text" @click="enterRoom">加入房间</el-button>
    <div>房间号：{{ room.roomId }}</div>
    <template v-for="word in room.wordList">
      <div>单词：{{ word }}</div>
    </template>

    <div style="width: 80%; margin: 0 auto; padding: 10px">
      <div class="track">
        <div class="track-ticker"></div>
        <div class="player" v-for="player in room.players">
          <div class="player-name"><span>{{ player.userId }}</span></div>
          <div class="player-lane">{{ player.percentage }}</div>
          <div class="player-details">sdfdsf</div>
        </div>
      </div>
    </div>
    <word-practice
      v-if="room.wordList.length !== 0"
      :practice-words="room.wordList"
      @pending="wpPending"
      @practiceWordsOk="wpPracticeWordsOk"
    />
  </div>
</template>

<script>
import WordPractice from './WordPractice'

export default {
  name: "Multiplayer",
  components: {
    WordPractice
  },
  data() {
    return {
      // websocket实例
      ws: null,
      // 命令
      command: {
        PendingResp: 1008,
        GameWordListResp: 1007,
        duplicateLoginResp: 1006,
        LoginResp: 1005,
        UserEnterRoomResp: 1004,
        EnterRoomResp: 1003,
        CreateRoomResp: 1002,
        GameStartResp: 1001,

        Pending: 5,
        GameStart: 4,
        CreateRoom: 3,
        EnterRoom: 2,
        Unknown: 999,
        Login: 1
      },
      // ws是否已经登录
      wsLogin: false,
      room: {
        roomId: null,
        players: [],
        wordList: []
      }
    }
  },
  created() {
    this.initWebSocket();
  },
  destroyed() {
    this.ws.close();
    this.ws = null
  },
  computed: {
  },
  methods: {
    initWebSocket() {
      this.ws = new WebSocket("ws://localhost:8899/ws");
      this.ws.onmessage = this.wsOnMessage;
      this.ws.onopen = this.wsOnopen;
      this.ws.onerror = this.wsOnerror;
      this.ws.onclose = this.wsOnclose;
    },
    wsOnopen() {
      // [type, userId] 登陆服务器
      this.wsSend(`${this.command.Login} ${this.$store.getters.tokenInfo['userId']}`);
    },
    wsOnerror() {
      this.wsLogin = false
    },
    wsOnMessage(e) {
      let msg = e.data.split(" ")
      console.log(msg)
      switch (Number(msg[0])) {
        case this.command.LoginResp:
          this.wsLogin = true
          break

        case this.command.CreateRoomResp:
          this.room.roomId = msg[1]

          this.room.players.push({userId: this.$store.getters.tokenInfo['userId'], percentage: 0})
          break

        case this.command.UserEnterRoomResp:
          this.room.players.forEach(e => {
            if (e.userId !== msg[1]){
              this.room.players.push({userId: msg[1], percentage: 0})
            }
          })
          break

        case this.command.GameWordListResp:
          this.room.wordList.push(msg[1])
          break

        case this.command.EnterRoomResp:
          msg[1].split(',').forEach(e => {
            this.room.players.push({userId: e, percentage: 0})
          })
          break

        case this.command.PendingResp:
          this.room.players.forEach(e => {
            if (e.userId === msg[1]){
              e.percentage = msg[2]
            }
          })
          break

        default:
          console.log()
      }
    },
    wsSend(Data) {
      this.ws.send(Data);
    },
    wsOnclose(e) {
      this.wsLogin = false
      console.log('ws断开连接', e);
    },
    wpPending(data) {
      let percentage = data.practicedCharsLen / data.totalCharsLen * 100
      this.wsSend(`${this.command.Pending} ${this.room.roomId} ${percentage.toFixed(2)}`)
    },
    wpPracticeWordsOk() {

    },
    enterRoom() {
      this.$prompt('请输入房间号', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({value}) => {
        this.wsSend(`${this.command.EnterRoom} ${value}`)
        this.room.roomId = value
      }).catch(() => {

      });
    }
  }
}
</script>

<style scoped>
.multiplayer-container {
  width: 100%;
  min-height: calc(100vh);
  background-color: #2c3e50;
  padding: 20px;
  color: white;
}

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
