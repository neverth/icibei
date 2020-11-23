<template>
  <div class="word-display">
    <div v-if="loading">
      正在加载...
    </div>
    <!--  tabindex用于支持 @focus和 @blur-->
    <div id="word-practice" tabindex="999" @focus="myOnfocus" @blur="myOnblur" v-if="!loading" :class="{twinkle: isTwinkle}">
      <audio ref="audio">
        <source type="audio/mpeg">
      </audio>
      <div style="font-size: 30px; text-align: center" :class="wordStringArrIndex[1] === 0 ? 'active_line': ''">
        <span
          v-for="(e, idx) in words['wordsCet4List'][wordsStringArrIndex]['word']"
          :class="isActive(idx, words['wordsCet4List'][wordsStringArrIndex]['word'])"
        >
          {{ e }}
        </span>
      </div>
      <div class="liju-container">
        <template v-for="(e, idx) in wordsParsed[wordsStringArrIndex]">
          <!-- 词型选前两个 -->
          <div class="cixing_wrapper" v-if="idx < 2">
            <div class="liju-cixing">
              {{ e['cixing']['p_text'] }}
            </div>
            <template v-for="(e1, idx1) in e['ciyi']">
              <!-- 有多个词义的话选前3个 -->
              <div class="ciyi-wrapper" v-if="idx1 < 3">
                <div class="liju-word-mean">{{ idx1 + 1 + '. ' + e1['chText'] }}</div>
                <div class="liju-wrapper">
                  <!-- 只显示一句 -->
                  <template v-for="(e2, idx2) in e1['liju']">
                    <div v-if="idx2 < 1" :class="isActiveLine(e2['enText'])" class="liju-english">
                      <template v-for="(ens, ensI) in e2['enText']">
                        <span
                          :class="isActive(ensI, e2['enText'])"
                          v-bind:style="{margin: ens === ' ' ? '0 3px': '' }"
                        >{{ ens }}</span>
                      </template>
                    </div>
                    <div v-if="idx2 < 1" class="liju-chinese">{{ e2['chText'] }}</div>
                  </template>

                </div>
              </div>
            </template>
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
import {queryWords, incrementWordExeTimes, getWordArrayData} from '@/api/words'
import store from '@/store'
import {wordsCet4ListParse} from '@/utils'

export default {
  name: 'WordDisplay',

  data() {
    return {
      // 加载
      loading: true,

      // 下一个字符必须为空格
      mustSpaceKey: false,
      // 正在练习的单词或句子
      string: '',
      // this.string.split()
      stringSplit: [],
      // this.string.split() 下标
      stringSplitIndex: 0,

      // 需要练习的单词数组 [[], []...]
      wordsStringArr: [],
      // 当前正在练习的单词下标，第一层下标
      wordsStringArrIndex: 0,
      // 当前正在练习的单词的例句下标，第二层下标，
      // 定义成数组是为了watch能监听相同值，其他index咱不需要
      wordStringArrIndex: [0, 0],

      // 从后端获取的单词列表
      words: undefined,
      // 解析之后的单词列表
      wordsParsed: undefined,
      // 第一次播放读音
      firstTimeAudioPlay: true,
      // 光标闪烁
      isTwinkle: false,
      // 定时器 object
      twinkleInterval: '',
      // 当前按下的key [时间搓, key]
      wdKeyDown: [],
      wdKeyUp: [],
      // 用于空格快捷键
      blankCount: 0,
      blankSetTimeout: undefined,
      // 自增
      increment: 0,
    }
  },
  props: {
    wordArray: {
      type: Array,
      required: true,
    }
  },
  computed: {
    isActive() {
      return (index, word) => {
        let cls = []
        // 让前面一条例句也在active状态
        let wordIndex = this.wordsStringArr[this.wordsStringArrIndex].indexOf(word)
        if (wordIndex !== -1 && wordIndex < this.wordStringArrIndex[1]) {
          cls.push('active-item')
          return cls
        }
        if (word === this.string) {
          if (index <= this.stringSplitIndex - 1) {
            cls.push('active-item')
          }
          if (index === this.stringSplitIndex) {
            cls.push('twinkle-item')
          }
          return cls
        }
      }
    },
    isActiveLine() {
      return (word) => {
        let wordIndex = this.wordsStringArr[this.wordsStringArrIndex].indexOf(word)
        if (wordIndex !== -1 && wordIndex === this.wordStringArrIndex[1]) {
          return 'active_line'
        }
      }
    }
  },
  watch: {
    // 这个例句变了
    string(newV, oldV) {
      this.$nextTick(() => {
        // 第一次不读，等第一次敲击键盘时再读
        if (oldV !== '') {
          this.$refs.audio.src = `${process.env.VUE_APP_ICIBEI_GATEWAY}/organization/translate/tss/${newV}`
          this.$refs.audio.load()
          this.$refs.audio.play()
        }
      })
      this.stringSplit = this.splitString(newV)
      this.stringSplitIndex = 0
    },
    // 这个例句字符串的下标
    stringSplitIndex(n, o) {
      // 这一个例句匹配完
      if (n >= this.stringSplit.length) {
        this.$emit('stringOk')
        this.mustSpaceKey = true
      }
    },
    // 这个单词例句数组下标
    wordStringArrIndex: {
      handler: function (n, o) {
        // 这个单词的例句已经匹配玩啦，切换下一个单词
        if (n[1] >= this.wordsStringArr[this.wordsStringArrIndex].length) {
          this.$emit('wordStringArrOk')
          this.wordsStringArrIndex++
          return
        }
        // 切换到下一个例句并更新状态
        this.string = this.wordsStringArr[this.wordsStringArrIndex][n[1]]
      },
      deep: true
    },
    // 这些单词数组的下标
    wordsStringArrIndex(n, o) {
      if (n >= this.wordsStringArr.length) {
        this.$emit('wordsStringArrOk')
        this.prepareWords()
      }
      this.wordStringArrIndex = [++this.increment, 0]
    },
    wordArray: {
      handler: function (n, o){
        console.log(n, o)
      },
      deep: true,
      immediate: true
    }
  },
  created() {
    this.prepareWords()
    if (!this.$store.getters.tokenInfo) {
      this.$nextTick(() => {
        this.$notify({
          title: '注意',
          message: '为了能保存您的练习数据，请登录！',
          offset: 100,
          type: 'info',
          duration: 2000
        });
      })
    }
  },
  methods: {
    // MAIN
    handleKeyDown(key) {
      // 重置光标闪烁状态
      this.resetTwinkleInterval()
      // 快捷键
      this.shortcutKey(key)
      // 匹配字符
      this.matchChar(key)
      // 只用于加载页面之后读第一个单词
      this.firstKeyDownAudioPlay()
    },
    // 只用于加载页面之后读第一个单词
    firstKeyDownAudioPlay() {
      if (this.wordStringArrIndex[1] === 0 && this.firstTimeAudioPlay) {
        this.firstTimeAudioPlay = false
        this.$refs.audio.src = `${process.env.VUE_APP_ICIBEI_GATEWAY}/organization/translate/tss/${this.string}`
        this.$refs.audio.load()
        this.$refs.audio.play()
      }
    },
    // 匹配字符
    matchChar(key) {
      if (!this.mustSpaceKey) {
        if (
          // 正确的匹配字母
          (this.stringSplit[this.stringSplitIndex].toLowerCase() === key)
          ||
          // 匹配特殊字符，直接空格就可以
          (['.', '\'', '"', '(', ')', ','].indexOf(this.stringSplit[this.stringSplitIndex]) !== -1 && key === ' ')
        ) {
          this.stringSplitIndex++
        }
      }
      // 必须输入空格的时候单独处理
      else {
        // 下一个输入必须为空格并且输入已经是空格
        if (key === ' ') {
          // 切换到下一个例句并重置状态
          this.wordStringArrIndex = [++this.increment, this.wordStringArrIndex[1] + 1]
          this.mustSpaceKey = false
        }
      }
    },
    handleWordExeOk(that) {
      // 当切换单词的时候，自增单词的练习次数
      let word = that.words['wordsCet4List'][that.wordsStringArrIndex]['word']
      incrementWordExeTimes({
        userId: store.getters.userInfo['userId'],
        word: word
      }).then(response => {
        console.log(word + '自增' + response.data)
      }).catch(e => {
        console.error(word + '自增' + e.data)
      })
    },
    // 生成NeedPracticeStrings
    genNeedPracticeStrings(wordsParsed) {
      let needPracticeStrings = []
      wordsParsed.forEach(e => {
        let a = []
        e.every((e1, idx1) => {
          // 值拿前2天词型
          if (idx1 < 2) {
            e1['ciyi'].every((e2, idx2) => {
              // 只拿前3条词义
              if (idx2 >= 3) {
                return false
              } else {
                // 只拿一条例句
                e2['liju'].every((e3, idx3) => {
                  if (idx3 < 1) {
                    a.push(e3['enText'])
                    return true
                  } else {
                    return false
                  }
                })
                return true
              }
            })
            return true
          } else {
            return false
          }
        })
        needPracticeStrings.push(a)
      })
      return needPracticeStrings
    },
    splitString(str) {
      return str.split('')
    },
    // 准备单词
    prepareWords() {
      this.loading = true
      let param = {
        current: 1,
        size: 5
      }
      if (this.words !== undefined) {
        param.current = this.words['current'] + 1
      }
      queryWords(param).then(response => {
        // 重置状态
        this.mustSpaceKey = false
        this.stringSplitIndex = 0
        this.wordsStringArrIndex = 0
        this.wordStringArrIndex = [++this.increment, 0]

        this.words = response.data
        this.wordsParsed = wordsCet4ListParse(this.words['wordsCet4List'])
        this.wordsStringArr = this.genNeedPracticeStrings(this.wordsParsed)
        // 将单词插入第一位，后面是例句
        this.wordsStringArr.forEach((e, idx) => {
          e.unshift(this.words['wordsCet4List'][idx]['word'])
        })
        this.string = this.wordsStringArr[this.wordsStringArrIndex][this.wordStringArrIndex[1]]
        this.stringSplit = this.splitString(this.string)
        this.loading = false
      })
    },
    resetTwinkleInterval() {
      this.isTwinkle = true
      clearInterval(this.twinkleInterval)
      this.twinkleInterval = setInterval(() => {
        this.isTwinkle = !this.isTwinkle
      }, 500)
    },
    myOnfocus() {
      // 元素获取焦点
      // 注册keydown事件
      let el = document.getElementById("word-practice")
      el.onkeydown = (event) => {
        this.wdKeyDown = [new Date().getTime(), event.key]
        this.$emit('myKeyDown', this.wdKeyDown)
        this.handleKeyDown(event.key)
      }
      el.onkeyup = (event) => {
        this.wdKeyUp = [new Date().getTime(), event.key]
        this.$emit('myKeyUp', this.wdKeyUp)
      }
      this.twinkleInterval = setInterval(() => {
        this.isTwinkle = !this.isTwinkle
      }, 500)
    },
    myOnblur() {
      let el = document.getElementById("word-practice")
      el.onkeydown = undefined
      el.onkeyup = undefined
      this.isTwinkle = false
      clearInterval(this.twinkleInterval)
    },
    shortcutKey(key) {
      // 3次空格跳过本句
      // 4次空格跳过这个单词
      if (key === ' ') {
        clearTimeout(this.blankSetTimeout);
        this.blankCount++;
        this.blankSetTimeout = setTimeout(() => {
          if (this.blankCount === 3) {
            this.wordStringArrIndex = [++this.increment, this.wordStringArrIndex[1] + 1]
          }
          //
          else if (this.blankCount === 4) {
            this.wordsStringArrIndex++
          }
          this.blankCount = 0
        }, 300)
      }
    },
    prepare(wordArray) {
      this.loading = true
      getWordArrayData(wordArray).then(response => {
        // 重置状态
        this.mustSpaceKey = false
        this.practiceStringArrIndex = 0
        this.needPracticeStringsArrIndex = 0
        this.needPracticeStringsIndex = 0

        this.words = response.data
        this.wordsParsed = wordsCet4ListParse(this.words)
        this.needPracticeStringsArr = this.genNeedPracticeStrings(this.wordsParsed)
        // 将单词插入第一位，后面是例句
        this.needPracticeStringsArr.forEach((e, idx) => {
          e.unshift(this.words[idx]['word'])
        })
        this.practiceString = this.needPracticeStringsArr[this.needPracticeStringsArrIndex][this.needPracticeStringsIndex]
        this.practiceStringArr = this.splitString(this.practiceString)
        this.countTotalChars()
        this.loading = false
      })
    },
  }
}

</script>

<style scoped>

.liju-container {
  width: 100%;
  margin: 0 auto
}

.liju-wrapper {
  margin-top: 5px;
  padding: 0px 10px;
}

.liju-chinese {
  margin-top: 5px;
  font-size: 14px;
  color: #7f8c8d;
}

.liju-english {
  font-size: 18px;
  color: #bdc3c7;
  padding: 0px 10px;
}

.liju-word-mean {
  color: #7f8c8d;
  font-size: 14px;
}

.liju-cixing {
  color: #2ecc71;
  font-weight: bold;
}

.ciyi-wrapper {
  margin-top: 10px;
  padding: 0px 10px;
}

.cixing_wrapper {
  margin-top: 5px;
}

.cixing_wrapper:last-child {
  margin-top: 10px;
}

.active-item {
  color: #ce6d39;
}

.active_line {
  color: white;
  box-shadow: 0 0 10px #ce6d39;
  border-radius: 20px;

}

span {
  /*margin-left: 1px;*/
  transition: background-color 0.1s ease-out;
}

.word-display {
  transition: all 0.3s;
  font-family: sourceCodePro, Ubuntu Mono, Lucida Console, monospace;
}

.twinkle .twinkle-item {
  background-color: #ce6d39;
}

</style>
