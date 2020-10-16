import Cookies from 'js-cookie'

// obj
const TokenInfoKey = 'token'
const UserInfoKey = 'userInfo'

const CookiesOp = {
  // 一天
  expires: 1
}

export function getTokenInfo() {
  if (Cookies.get(TokenInfoKey)) {
    return JSON.parse(Cookies.get(TokenInfoKey))
  }
  return undefined
}

export function setTokenInfo(token) {
  return Cookies.set(TokenInfoKey, JSON.stringify(token), CookiesOp)
}

export function removeTokenInfo() {
  return Cookies.remove(TokenInfoKey)
}

export function setUserInfo(userInfo) {
  return Cookies.set(UserInfoKey, JSON.stringify(userInfo), CookiesOp)
}

export function getUserInfo() {
  if (Cookies.get(UserInfoKey)) {
    return JSON.parse(Cookies.get(UserInfoKey))
  }
  return undefined
}

export function removeUserInfo() {
  return Cookies.remove(UserInfoKey)
}

