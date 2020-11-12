<template>
  <div class="word-display">
    <div v-if="loading">
      正在加载...
    </div>
    <div v-if="!loading" style="width: fit-content; margin: 20px auto;" :class="{twinkle: isTwinkle}">
      <audio ref="audio">
        <source type="audio/mpeg">
      </audio>
      <div style="font-size: 30px; text-align: center" :class="needPracticeStringsIndex === 0 ? 'active_line': ''">
        <span
          v-for="(e, idx) in words[needPracticeStringsArrIndex]['word']"
          :class="isActive(idx, words[needPracticeStringsArrIndex]['word'])"
        >
          {{ e }}
        </span>
      </div>
      <div class="liju-container">
        <template v-for="(e, idx) in wordsParsed[needPracticeStringsArrIndex]">
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
import {getWords, incrementWordExeTimes} from '@/api/words'
import store from '@/store'
import {wordsCet4ListParse} from '@/utils'

export default {
  name: 'WordPractice',

  data() {
    return {
      // 加载
      loading: true,

      // 下一个字符必须为空格
      mustSpaceKey: false,
      // 正在练习的单词或句子
      practiceString: '',
      // 正在练习的单词或句子的单个字母为单位的数组
      practiceStringArr: [],
      // 当前匹配practiceStringArr的下标
      practiceStringArrIndex: 0,

      // 需要练习的单词数组 [[], []...]
      needPracticeStringsArr: [],
      // 当前正在练习的单词下标，第一层下标
      needPracticeStringsArrIndex: 0,
      // 当前正在练习的单词的例句下标，第二层下标
      needPracticeStringsIndex: 0,

      // 从后端获取的单词列表
      words: undefined,
      // 解析之后的单词列表
      wordsParsed: undefined,
      // 第一次播放读音
      firstTimeAudioPlay: true,
      // 光标闪烁
      isTwinkle: true,
      // 定时器 object
      twinkleInterval: '',
      // 练习过程的数据
      pending: {
        totalCharsLen: 0,
        practicedCharsLen: 0,
      }
    }
  },
  computed: {
    isActive() {
      return (index, word) => {
        let cls = []
        // 让前面一条例句也在active状态
        let wordIndex = this.needPracticeStringsArr[this.needPracticeStringsArrIndex].indexOf(word)
        if (wordIndex !== -1 && wordIndex < this.needPracticeStringsIndex) {
          cls.push('active')
          return cls
        }
        if (word === this.practiceString) {
          if (index <= this.practiceStringArrIndex - 1) {
            cls.push('active')
          }
          // 光标闪烁class
          if (index === this.practiceStringArrIndex) {
            cls.push('now-practice')
          }
          return cls
        }
      }
    },
    isActiveLine() {
      return (word) => {
        let wordIndex = this.needPracticeStringsArr[this.needPracticeStringsArrIndex].indexOf(word)
        if (wordIndex !== -1 && wordIndex === this.needPracticeStringsIndex) {
          return 'active_line'
        }
      }
    }
  },
  watch: {
    practiceString(newV, oldV) {
      // 第一次不读，等第一次敲击键盘时再读
      this.$nextTick(() => {
        if (oldV !== '') {
          this.$refs.audio.src = `http://localhost:8443/organization/translate/tss/${newV}`
          this.$refs.audio.load()
          this.$refs.audio.play()
        }
      })
    }
  },
  props: ['practiceWords'],
  created() {
    console.log('created')
    this.prepareWords(this)
    this.twinkleInterval = setInterval(() => {
      this.isTwinkle = !this.isTwinkle
    }, 500)
  },
  mounted() {
    console.log('mounted WordPractice')
    window.handleKeyDownWordDisplay = this.handleKeyDown
  },
  beforeDestroy() {
    console.log('beforeDestroy WordPractice')
    // 取消注册到window
    window.handleKeyDownWordDisplay = undefined
  },
  methods: {
    handleKeyDown(key) {
      // 重置光标闪烁状态
      this.resetTwinkleInterval()
      // -----------匹配单词部分-------------
      // 下一个输入必须为空格并且输入已经是空格
      if (this.mustSpaceKey && key === ' ') {
        // 切换到下一个例句并重置状态
        this.needPracticeStringsIndex++
        this.mustSpaceKey = false

        // 当前单词的所有例句已经匹配完成，切换到下一个单词
        if (this.needPracticeStringsIndex === this.needPracticeStringsArr[this.needPracticeStringsArrIndex].length) {
          this.$emit("practiceWordOk")
          this.needPracticeStringsArrIndex++
          this.needPracticeStringsIndex = 0

          // 单词列表已经用完，通知父组件
          if (this.needPracticeStringsArrIndex === this.needPracticeStringsArr.length) {
            this.$emit("practiceWordsOk")
            return
          }
        }
        // 等待切换到下一个单词之后更新状态
        if (!this.loading) {
          this.practiceString = this.needPracticeStringsArr[this.needPracticeStringsArrIndex][this.needPracticeStringsIndex]
          this.practiceStringArr = this.splitString(this.practiceString)
          this.practiceStringArrIndex = 0
        }
      }
      // 正确的匹配字母
      else if (!this.mustSpaceKey && this.practiceStringArr[this.practiceStringArrIndex].toLowerCase() === key) {
        this.practiceStringArrIndex++
        this.pending.practicedCharsLen++
        this.$emit("pending", this.pending)
      }
      // 匹配特殊字符，直接空格就可以
      else {
        if (['.', '\'', '"', '(', ')', ','].indexOf(this.practiceStringArr[this.practiceStringArrIndex]) !== -1) {
          if (key === ' ') {
            this.practiceStringArrIndex++
            this.pending.practicedCharsLen++
            this.$emit("pending", this.pending)
          }
        }
      }

      // 单词已经匹配完成，等待输入空格后切换到下一个单词
      if (this.practiceStringArrIndex === this.practiceStringArr.length) {
        this.mustSpaceKey = true
      }

      // 只用于加载页面之后读第一个单词
      if (this.needPracticeStringsIndex === 0 && this.firstTimeAudioPlay) {
        this.firstTimeAudioPlay = false
        this.$refs.audio.src = `http://localhost:8443/organization/translate/tss/${this.practiceString}`
        this.$refs.audio.load()
        this.$refs.audio.play()
      }
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
      // str.replace('.', '')
      return str.split('')
    },
    // 准备单词
    prepareWords(that) {
      that.loading = true
      getWords(this.practiceWords).then(response => {
        // 重置状态
        that.mustSpaceKey = false
        that.practiceStringArrIndex = 0
        that.needPracticeStringsArrIndex = 0
        that.needPracticeStringsIndex = 0

        that.words = response.data
        that.wordsParsed = wordsCet4ListParse(that.words)
        that.needPracticeStringsArr = that.genNeedPracticeStrings(that.wordsParsed)
        // 将单词插入第一位，后面是例句
        that.needPracticeStringsArr.forEach((e, idx) => {
          e.unshift(that.words[idx]['word'])
        })
        that.practiceString = that.needPracticeStringsArr[that.needPracticeStringsArrIndex][that.needPracticeStringsIndex]
        that.practiceStringArr = that.splitString(that.practiceString)
        this.countTotalChars()
        that.loading = false
      })
    },
    resetTwinkleInterval() {
      this.isTwinkle = true
      clearInterval(this.twinkleInterval)
      this.twinkleInterval = setInterval(() => {
        this.isTwinkle = !this.isTwinkle
      }, 500)
    },
    countTotalChars(){
      this.needPracticeStringsArr.forEach((e1, idx1) => {
        e1.forEach((e2, idx2) => {
          this.pending.totalCharsLen += e2.length
        })
      })
    }
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

.active {
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

.twinkle .now-practice {
  background-color: #ce6d39;
}

</style>
