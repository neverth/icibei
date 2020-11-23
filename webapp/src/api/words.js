import request from '@/utils/request'

export function queryWords(params) {
  return request({
    url: '/organization/words/query',
    method: 'get',
    params: params
  })
}

export function getWordArrayData(wordsArray) {
  let s = wordsArray[0]
  if (wordsArray.length > 1){
    for (let i = 1; i < wordsArray.length; i++) {
      s += `,${wordsArray[i]}`
    }
  }
  return request({
    url: '/organization/words',
    method: 'get',
    params: {
      words: s
    }
  })
}

// 自增单词练习练习，没有用户关系时会自动创建
export function incrementWordExeTimes(params) {
  return request({
    url: '/organization/userWord/exeTimes',
    method: 'put',
    params: params
  })
}

export function updateRelation(params) {
  return request({
    url: '/organization/userWord/relation',
    method: 'put',
    params: params
  })
}

export function addUserWord(params) {
  return request({
    url: '/organization/userWord',
    method: 'post',
    data: params
  })
}

export function getUserWord(params) {
  return request({
    url: '/organization/userWord',
    method: 'get',
    params: params
  })
}

export function getWords(size) {
  let params = {
    size: size
  }
  return request({
    url: '/organization/words/random',
    method: 'get',
    params: params
  })
}

export function getWordDatas(size) {
  let params = {
    size: size
  }
  return request({
    url: '/organization/words/wordDatas/random',
    method: 'get',
    params: params
  })
}

