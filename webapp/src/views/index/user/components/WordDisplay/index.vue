<template>
  <div>
    <div style="width: 60%; margin: 10px auto">
      <div style="font-size: 30px; text-align: center">
        <span v-for="(e, index) in displayWords['word']" :class="displayWordsClass(index)">
        {{ e }}
      </span>
      </div>
      <div class="liju-container">
        <div>verb</div>
        <div class="liju-wrapper">
          <div class="liju-word-mean">（不顾责任、义务等）离弃，遗弃，抛弃</div>
          <div class="liju-english">The baby had been abandoned by its mother.</div>
          <div class="liju-chinese">这个婴儿被母亲遗弃了。</div>
        </div>
        <div class="liju-wrapper">
          <div class="liju-word-mean">（不顾责任、义务等）离弃，遗弃，抛弃</div>
          <div class="liju-english">The baby had been abandoned by its mother.</div>
          <div class="liju-chinese">这个婴儿被母亲遗弃了。</div>
        </div>
        <div class="liju-wrapper">
          <div class="liju-word-mean">（不顾责任、义务等）离弃，遗弃，抛弃</div>
          <div class="liju-english">The baby had been abandoned by its mother.</div>
          <div class="liju-chinese">这个婴儿被母亲遗弃了。</div>
        </div>
        <div class="liju-wrapper">
          <div class="liju-word-mean">（不顾责任、义务等）离弃，遗弃，抛弃</div>
          <div class="liju-english">The baby had been abandoned by its mother.</div>
          <div class="liju-chinese">这个婴儿被母亲遗弃了。</div>
        </div>
        <div class="liju-wrapper">
          <div class="liju-word-mean">（不顾责任、义务等）离弃，遗弃，抛弃</div>
          <div class="liju-english">The baby had been abandoned by its mother.</div>
          <div class="liju-chinese">这个婴儿被母亲遗弃了。</div>
        </div>
      </div>
    </div>
    <KeyBoard/>
  </div>
</template>

<script>
import KeyBoard from '@/views/index/user/components/KeyBoard/KeyBoard'
import { queryWords, incrementWordExeTimes } from '@/api/words'
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
      mustSpaceKey: false
    }
  },
  computed: {
    displayWords() {
      if (!this.practiceWords || this.practiceWordIndex === this.practiceWords['size']) {
        return { word: 'wait...'.split('') }
      }
      let wordList = this.practiceWords['wordsCet4List']
      let wordDic = wordList[this.practiceWordIndex]
      wordDic['word'] = wordDic['word'].split('')
      return wordDic
    },
    displayWordsClass() {
      return (index) => {
        if (index <= this.wordSplitIndex - 1) {
          return 'active'
        }
      }
    }
  },
  created() {
    console.log('created')
    queryWords({ size: 3 }).then(response => {
      this.practiceWords = response.data
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
        queryWords({ current: this.practiceWords['current'] + 1, size: 3 }).then(response => {
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

document.onkeydown = function(event) {
  handleKeyDown(event.key)
}

</script>

<style scoped>
.active {
  color: #ce6d39;
}

.liju-container {
  width: 60%;
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
