<template>
  <div>
    <div style="text-align: center; font-size: 30px">
        <span v-for="(e, index) in displayWords['word']" :class="displayWordsClass(index)">
        {{ e }}
      </span>
    </div>
    <KeyBoard/>
  </div>
</template>

<script>
import KeyBoard from "@/views/index/user/components/KeyBoard/KeyBoard";
import {queryWords} from '@/api/words'

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
      debugger
      if (!this.practiceWords || this.practiceWordIndex === this.practiceWords['size']) {
        return {word: 'wait...'.split('')}
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
    },
  },
  created() {
    console.log('created')
    queryWords({size: 3}).then(response => {
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
        // 切换到下一个单词并重置状态啊
        this.mustSpaceKey = false
        this.practiceWordIndex++
        this.wordSplitIndex = 0
      }
      // 正确的匹配字母
      if (wordSplit[this.wordSplitIndex] === key) {
        this.wordSplitIndex++;
      }
      // 单词已经匹配完成，等待输入空格后切换到下一个单词
      if (this.wordSplitIndex === wordSplit.length) {
        this.mustSpaceKey = true
      }
      // 单词list用完，请求下一个单词list并重置状态
      if (this.practiceWordIndex === this.practiceWords['size']) {
        queryWords({current: this.practiceWords['current'] + 1, size: 3}).then(response => {
          debugger
          this.practiceWordIndex = 0;
          this.practiceWords = response.data
        })
      }
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
</style>
