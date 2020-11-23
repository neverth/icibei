<template>
  <div class="home-container">
    <word-display
      v-if="!loading"
      :word-array="wordArray"
      style="width: fit-content; margin: 0 auto;"
      @myKeyDown="keyDown"
      @myKeyUp="keyUp"
    />
    <key-board :keyDown="wdkeyDown" :keyUp="wdkeyUp"/>
    <div style="position: absolute;
    bottom: 10px;
    right: 10px;">

      <el-popover
        placement="top"
        width="300"
        trigger="click">
        <el-tabs type="border-card">
          <el-tab-pane label="训练列表">
            <el-table :data="gridData" height="250">
              <el-table-column width="150" property="date" label="单词"></el-table-column>
              <el-table-column width="100" property="name" label="操作"></el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="历史记录">
            <el-table :data="gridData" height="250">
              <el-table-column width="150" property="date" label="单词"></el-table-column>
              <el-table-column width="100" property="name" label="操作"></el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
        <el-button slot="reference">click 激活</el-button>
      </el-popover>
    </div>
  </div>
</template>

<script>
import WordDisplay from './components/WordDisplay'
import KeyBoard from './components/KeyBoard'
import {getWords} from '@/api/words'


export default {
  name: 'Home',
  components: {
    WordDisplay,
    KeyBoard
  },
  data() {
    return {
      wordArray: [],
      wordArrayHistory: [],
      wdkeyDown: [],
      wdkeyUp: [],
      gridData: [{
        date: '2016-05-02',
        name: '王小虎',
      }, {
        date: '2016-05-04',
        name: '王小虎',
      }, {
        date: '2016-05-01',
        name: '王小虎',
      }],
      loading: true,
    }
  },
  created() {
    getWords(10).then((resp) => {
      this.wordArray = resp.data
      this.loading = false
    })

  },
  methods: {
    keyDown(key) {
      this.wdkeyDown = key
    },
    keyUp(key) {
      this.wdkeyUp = key
    }
  }
}
</script>

<style lang="scss" scoped>
.home-container {
  padding-top: 20px;
  max-height: calc(100vh);
}
</style>
