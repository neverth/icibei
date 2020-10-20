<template>
  <div>
    <div style="width: 60%; margin: 10px auto">
      <div style="font-size: 30px; text-align: center">
        <span v-for="(e, index) in displayWords['word']" :class="displayWordsClass(index)">
        {{ e }}
        </span>
      </div>
      <div class="liju-container" v-if="showWordDisplay">
        <template v-for="(e, idx) in displayWords['baidu']['dict_result']['oxford']['entry'][0]['data']">
          <div
            v-if="(e['tag'] === 'p-g') || (e['tag'] === 'h-g' && displayWords['baidu']['dict_result']['oxford']['entry'][0]['data'].length === 1)">
            <template v-for="(e1, idx1) in e['data']">
              <template v-if="e1['tag'] === 'n-g'">
                <template v-for="(e2, idx2) in e1['data']">
                  <!-- 词型 -->
                  <div style="color: #2ecc71;" v-if="e2['tag'] === 'p'">
                    {{ e2['p_text'] }}
                  </div>
                  <!-- 词义 -->
                  <div class="liju-word-mean" v-if="e2['tag'] === 'd'">
                    {{ e2['chText'] }}
                  </div>
                  <!-- 例句 -->
                  <div class="liju-wrapper" v-if="e2['tag'] === 'x' && showLiju1">
                    <div class="liju-english">{{ e2['enText'] }}</div>
                    <div class="liju-chinese">{{ e2['chText'] }}</div>
                  </div>
                </template>
              </template>
              <!-- 词型 -->
              <div style="color: #2ecc71;" v-if="e1['tag'] === 'p'">
                {{ e1['p_text'] }}
              </div>
              <!-- 词义 -->
              <div v-if="e1['tag'] === 'd'">
                {{ e1['chText'] }}
              </div>
              <!-- 例句 -->
              <div class="liju-wrapper" v-if="e1['tag'] === 'x' && showLiju0">
                <div>{{ e1['enText'] }}</div>
                <div>{{ e1['chText'] }}</div>
              </div>
            </template>
          </div>
        </template>
      </div>
    </div>
    <KeyBoard/>
  </div>
</template>

<script>
import KeyBoard from '@/views/index/user/components/KeyBoard/KeyBoard'
import {queryWords, incrementWordExeTimes} from '@/api/words'
import store from '@/store'

export default {
  name: 'WordDisplay',
  components: {
    KeyBoard
  },
  data() {
    return {
      practiceWordIndex: 0,
      practiceWords: undefined,
      wordSplitIndex: 0,
      mustSpaceKey: false,
      showWordDisplay: false,
      lijuShowNumber: [0, 0]
    }
  },
  computed: {
    displayWords() {
      if (!this.practiceWords || this.practiceWordIndex === this.practiceWords['size']) {
        this.showWordDisplay = false
        return {word: 'wait...'.split('')}
      }
      debugger
      let wordList = this.practiceWords['wordsCet4List']
      let wordDic = wordList[this.practiceWordIndex]
      wordDic['word'] = wordDic['word'].split('')
      this.showWordDisplay = true
      return wordDic
    },
    displayWordsClass() {
      return (index) => {
        if (index <= this.wordSplitIndex - 1) {
          return 'active'
        }
      }
    },
    showLiju0() {
      return this.lijuShowNumber[0]++ !== 1;
    },
    showLiju1() {
      debugger
      return this.lijuShowNumber[1]++ !== 1;
    }
  },
  created() {
    console.log('created')
    queryWords({size: 3}).then(response => {
      this.practiceWords = response.data
      response.data['wordsCet4List'].forEach(e => {
        debugger
        let wordDetail = []
        let a = e['baidu']['dict_result']['oxford']['entry'][0]['data'];
        a.forEach(e1 => {
          if ((a.length === 1 && e1['tag'] === 'h-g') || (e1['tag'] === 'p-g')) {
            let b = e1['data']
            // 词型
            let wp = {}
            wp['ciyi'] = []
            b.forEach(e2 => {
              // 词型，公有
              if (e2['tag'] === 'p') {
                wp['cixing'] = e2
              }
              // 部分有
              if (e2['tag'] === 'd') {
                wp['ciyi'].push(e2)
                wp['ciyi'][0]['liju'] = []
              }
              if (e2['tag'] === 'x') {
                wp['ciyi'][0]['liju'].push(e2)
              }
              // 部分有
              if (e2['tag'] === 'n-g') {
                let c = e2['data']
                // 词义
                let wd = {}
                let wx = []
                wd = {}
                c.forEach(e3 => {
                  if (e3['tag'] === 'd') {
                    wd = e3
                  }
                  if (e3['tag'] === 'x') {
                    wx.push(e3)
                  }
                })
                wd['liju'] = wx
                wp['ciyi'].push(wd)
              }
            })
            wordDetail.push(wp)
            console.log(wordDetail)
          }
        })
      })
      console.log()
    })
  },
  mounted() {
    window.handleKeyDown = this.handleKeyDown
    console.log('mounted')
  },
  beforeDestroy() {
    console.log('beforeDestroy')
  },
  methods: {
    handleKeyDown(key) {
      let wordSplit = this.practiceWords['wordsCet4List'][this.practiceWordIndex]['word']
      // 下一个输入必须为空格并且输入已经是空格
      if (this.mustSpaceKey && key === ' ') {
        // 切换到下一个单词并重置状态并处理练习完成
        this.$options.methods.handleWordExeOk(this)
        this.mustSpaceKey = false
        this.practiceWordIndex++
        this.wordSplitIndex = 0
      }
      // 正确的匹配字母
      if (wordSplit[this.wordSplitIndex] === key) {
        this.wordSplitIndex++
      }
      // 单词已经匹配完成，等待输入空格后切换到下一个单词
      if (this.wordSplitIndex === wordSplit.length) {
        this.mustSpaceKey = true
      }
      // 单词list用完，请求下一个单词list并重置状态
      if (this.practiceWordIndex === this.practiceWords['size']) {
        queryWords({current: this.practiceWords['current'] + 1, size: 3}).then(response => {
          this.practiceWordIndex = 0
          this.practiceWords = response.data
        })
      }
    },
    handleWordExeOk(that) {
      // 当切换单词的时候，自增单词的练习次数
      let word = ''
      that.practiceWords['wordsCet4List'][that.practiceWordIndex]['word'].forEach(e => word += e)
      incrementWordExeTimes({
        userId: store.getters.userInfo['userId'],
        word: word
      }).then(response => {
        console.log(word + '自增' + response.data)
      }).catch(e => {
        console.error(word + '自增' + e.data)
      })
    }
  }
}

document.onkeydown = function (event) {
  handleKeyDown(event.key)
}

</script>

<style scoped>
.active {
  color: #ce6d39;
}

.liju-container {
  width: 100%;
  margin: 0 auto
}

.liju-wrapper {
  margin-top: 20px;
}

.liju-chinese {
  font-size: 14px;
  color: #95a5a6;
}

.liju-english {
  font-size: 18px;
}

.liju-word-mean {
  color: #7f8c8d;
}

</style>
