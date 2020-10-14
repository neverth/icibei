import Cookies from 'js-cookie'

const TokenKey = 'icibei-token'
const ReTokenKey = 'icibei-reToken'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function getReToken() {
  return Cookies.get(ReTokenKey)
}

export function setReToken(token) {
  return Cookies.set(ReTokenKey, token)
}

export function removeReToken() {
  return Cookies.remove(ReTokenKey)
}

