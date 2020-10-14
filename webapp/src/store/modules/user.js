import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken, getReToken, setReToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    reToken: getReToken(),
    name: '',
    avatar: '',
    roles: [],
    userId: ''
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_RE_TOKEN: (state, reToken) => {
    state.reToken = reToken
  },
  SET_USER_ID: (state, userId) => {
    state.userId = userId
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password, grant_type } = userInfo
    return new Promise((resolve, reject) => {
      // 立即执行
      login({ username: username.trim(), password: password, grant_type }).then(response => {
        debugger
        const { data } = response
        commit('SET_TOKEN', data.access_token)
        commit('SET_RE_TOKEN', data.refresh_token)
        commit('SET_USER_ID', data.userId)
        setToken(data.access_token)
        setReToken(data.refresh_token)
        // 将状态改为fulfilled 并将参数传递给then中的回调函数
        resolve()
      }).catch(error => {
        // 将状态改为rejected 并将参数传递给then/catch中的回调函数
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.userId).then(response => {
        const { data } = response
        debugger
        if (!data) {
          reject('Verification failed, please Login again.')
        }

        const { roles, nickName, avatar, signature } = data

        // roles must be a non-empty array
        if (!roles || roles.length <= 0) {
          reject('getInfo: roles must be a non-null array!')
        }

        commit('SET_ROLES', roles)
        commit('SET_NAME', nickName)
        commit('SET_AVATAR', avatar)
        commit('SET_INTRODUCTION', signature)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

