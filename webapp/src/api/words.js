import request from '@/utils/request'

export function queryWords(params) {
  return request({
    url: 'http://localhost:8443/organization/words/query',
    method: 'get',
    params: params
  })
}
