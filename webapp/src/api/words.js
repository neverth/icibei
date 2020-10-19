import request from '@/utils/request'

export function queryWords(params) {
  return request({
    url: 'http://localhost:8443/organization/words/query',
    method: 'get',
    params: params
  })
}

// 自增单词练习练习，没有用户关系时会自动创建
export function incrementWordExeTimes(params) {
  return request({
    url: 'http://localhost:8443/organization/userWord/exeTimes',
    method: 'put',
    params: params
  })
}

export function updateRelation(params) {
  return request({
    url: 'http://localhost:8443/organization/userWord/relation',
    method: 'put',
    params: params
  })
}

export function addUserWord(params) {
  return request({
    url: 'http://localhost:8443/organization/userWord',
    method: 'post',
    data: params
  })
}

export function getUserWord(params) {
  return request({
    url: 'http://localhost:8443/organization/userWord',
    method: 'get',
    params: params
  })
}

