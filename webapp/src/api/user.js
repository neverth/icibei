import request from '@/utils/request'
import qs from 'qs'

export function login(data) {
  // 转为x-www-form-urlencoded
  data = qs.stringify(data)
  return request({
    url: '/authorization-server/oauth/token',
    method: 'post',
    data,
    auth: {
      username: 'client',
      password: '123'
    }
  })
}

export function register(data) {
  return request({
    url: '/organization/user',
    method: 'post',
    data
  })
}

export function validateUniqueUserName(userName) {
  return request({
    url: `/organization/user/validateUniqueUserName/${userName}`,
    method: 'get',
  })
}

export function getInfo(userId) {
  return request({
    url: '/organization/userInfo/' + userId,
    method: 'get'
  })
}

export function updateInfo(userId, userInfo) {
  return request({
    url: '/organization/userInfo/' + userId,
    method: 'put',
    data: userInfo
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}
