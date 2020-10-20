<template>
  <div>
    <div v-if="loading">
      正在加载...
    </div>
    <div v-if="!loading" style="width: fit-content; margin: 20px auto;">
      <div style="font-size: 30px; text-align: center" :class="needPracticeStringsIndex === 0 ? 'active_line': ''">
        <span
          v-for="(e, idx) in words['wordsCet4List'][needPracticeStringsArrIndex]['word']"
          :class="isActive(idx, words['wordsCet4List'][needPracticeStringsArrIndex]['word'])"
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
                  <div :class="isActiveLine(e1['liju'][0]['enText'])" class="liju-english">
                    <template v-for="(ens, ensI) in e1['liju'][0]['enText']">
                      <span :class="isActive(ensI, e1['liju'][0]['enText'])">{{ ens === ' ' ? '_' : ens }}</span>
                    </template>
                  </div>
                  <div class="liju-chinese">{{ e1['liju'][0]['chText'] }}</div>
                </div>
              </div>
            </template>
          </div>
        </template>
      </div>
    </div>
    <KeyBoard v-if="!loading"/>
  </div>
</template>

<script>
import KeyBoard from '@/views/index/user/components/KeyBoard/KeyBoard'
import { queryWords, incrementWordExeTimes } from '@/api/words'
import store from '@/store'
import { wordsCet4ListParse } from '@/utils'

export default {
  name: 'WordDisplay',
  components: {
    KeyBoard
  },
  data() {
    return {
      loading: true,

      practiceWordIndex: 0,
      practiceWords: undefined,
      wordSplitIndex: 0,

      mustSpaceKey: false,

      // 正在练习的单词或句子
      practiceString: '',
      // 正在练习的单词或句子的单个字母为单位的数组
      practiceStringArr: [],
      // 当前匹配practiceStringArr的下标
      practiceStringArrIndex: 0,

      // 需要练习的单词数组
      needPracticeStringsArr: [],
      // 当前正在练习的单词下标
      needPracticeStringsArrIndex: 0,
      // 当前正在练习的单词的例句下标
      needPracticeStringsIndex: 0,

      // 从后端获取的单词列表
      words: undefined,
      // 解析之后的单词列表
      wordsParsed: undefined,
    }
  },
  computed: {
    isActive() {
      return (index, word) => {
        // 让前面一条例句也在active状态
        let wordIndex = this.needPracticeStringsArr[this.needPracticeStringsArrIndex].indexOf(word)
        if (wordIndex !== -1 && wordIndex < this.needPracticeStringsIndex) {
          return 'active'
        }
        if (word === this.practiceString) {
          if (index <= this.practiceStringArrIndex - 1) {
            return 'active'
          }
        }
      }
    },
    isActiveLine() {
      return (word) => {
        debugger
        let wordIndex = this.needPracticeStringsArr[this.needPracticeStringsArrIndex].indexOf(word)
        if (wordIndex !== -1 && wordIndex === this.needPracticeStringsIndex) {
          return 'active_line'
        }
      }
    }
  },
  created() {
    console.log('created')
    queryWords({ size: 3 }).then(response => {

      this.practiceWords = response.data

      this.words = response.data
      this.wordsParsed = wordsCet4ListParse(this.words['wordsCet4List'])
      this.needPracticeStringsArr = this.$options.methods.genNeedPracticeStrings(this.wordsParsed)
      // 将单词插入第一位，后面是例句
      this.needPracticeStringsArr.forEach((e, idx) => {
        e.unshift(this.words['wordsCet4List'][idx]['word'])
      })
      this.practiceString = this.needPracticeStringsArr[this.needPracticeStringsArrIndex][this.needPracticeStringsIndex]
      this.practiceStringArr = this.$options.methods.splitString(this.practiceString)
      this.loading = false
    })
  },
  mounted() {
    console.log('mounted')
    window.handleKeyDown = this.handleKeyDown
  },
  beforeDestroy() {
    console.log('beforeDestroy')
  },
  methods: {
    handleKeyDown(key) {
      // 下一个输入必须为空格并且输入已经是空格
      if (this.mustSpaceKey && key === ' ') {
        // 切换到下一个单词并重置状态并处理练习完成
        // this.$options.methods.handleWordExeOk(this)
        this.mustSpaceKey = false
        this.needPracticeStringsIndex++
        this.practiceStringArrIndex = 0
        this.practiceString = this.needPracticeStringsArr[this.needPracticeStringsArrIndex][this.needPracticeStringsIndex]
        this.practiceStringArr = this.$options.methods.splitString(this.practiceString)
      }
      // 正确的匹配字母
      if (this.practiceStringArr[this.practiceStringArrIndex].toLowerCase() === key) {
        this.practiceStringArrIndex++
      }
      // 匹配特殊字符，直接空格就可以
      else {
        if (['.', '\'', '"', '(', ')'].indexOf(this.practiceStringArr[this.practiceStringArrIndex]) !== -1) {
          if (key === ' ') {
            this.practiceStringArrIndex++
          }
        }
      }

      // 单词已经匹配完成，等待输入空格后切换到下一个单词
      if (this.practiceStringArrIndex === this.practiceStringArr.length) {
        this.mustSpaceKey = true
      }
      // // 单词list用完，请求下一个单词list并重置状态
      // if (this.practiceWordIndex === this.practiceWords['size']) {
      //   queryWords({ current: this.practiceWords['current'] + 1, size: 3 }).then(response => {
      //     this.practiceWordIndex = 0
      //     this.practiceWords = response.data
      //   })
      // }
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
    },
    // 生成NeedPracticeStrings
    genNeedPracticeStrings(wordsParsed) {
      let needPracticeStrings = []
      wordsParsed.forEach(e => {
        let a = []
        e.forEach(e1 => {
          e1['ciyi'].every((e2, idx) => {
            // 只拿前3条词义
            if (idx >= 3) {
              return false
            } else {
              // 只拿一条例句
              a.push(e2['liju'][0]['enText'])
              return true
            }
          })
        })
        needPracticeStrings.push(a)
      })
      return needPracticeStrings
    },
    splitString(str) {
      // str.replace('.', '')
      return str.split('')
    }
  }
}

document.onkeydown = function(event) {
  handleKeyDown(event.key)
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
  margin-left: 0.5px;
}

</style>
