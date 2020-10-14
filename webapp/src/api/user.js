import request from '@/utils/request'
import qs from 'qs'

export function login(data) {
  // 转为x-www-form-urlencoded
  data = qs.stringify(data)
  return request({
    url: 'http://localhost:8443/authorization-server/oauth/token',
    method: 'post',
    data,
    auth: {
      username: 'client',
      password: '123'
    }
  })
}

export function getInfo(token) {
  return request({
    url: 'http://localhost:8443/organization/userInfo/103',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}
