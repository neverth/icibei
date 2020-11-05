import {login, logout, getInfo, updateInfo, register} from '@/api/user'
import {getTokenInfo, getUserInfo, setTokenInfo, removeTokenInfo, setUserInfo, removeUserInfo} from '@/utils/auth'
import {resetRouter} from '@/router'
import da from "element-ui/src/locale/lang/da";
import {Message, MessageBox} from "element-ui";
import store from "@/store";

const getDefaultState = () => {
  return {
    tokenInfo: getTokenInfo(),
    userInfo: getUserInfo()
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN_INFO: (state, e) => {
    state.tokenInfo = e
  },
  SET_USER_INFO: (state, e) => {
    state.userInfo = e
  }
}

const actions = {
  // user login
  login({commit, state}, loginInfo) {
    const {username, password, grant_type} = loginInfo
    return new Promise((resolve, reject) => {
      // 立即执行
      login({username: username.trim(), password: password, grant_type}).then(response => {
        const {data} = response

        if (!data['access_token']) {
          Message({
            message: "用户不存在或密码错误",
            type: 'error',
            duration: 2 * 1000
          })
          reject('登录失败')
        }

        setTokenInfo(data)
        commit('RESET_STATE')
        // 将状态改为fulfilled 并将参数传递给then中的回调函数
        resolve(data)
      }).catch(error => {
        // 将状态改为rejected 并将参数传递给then/catch中的回调函数
        reject(error)
      })
    })
  },

  register({commit, state}, registerForm) {
    const {username, password} = registerForm
    return new Promise((resolve, reject) => {
      // 立即执行
      register({username: username.trim(), password: password}).then(response => {
        const {data} = response

        if (!data) {
          reject('注册失败')
        }

        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({commit, state}, userId) {
    return new Promise((resolve, reject) => {
      getInfo(userId).then(response => {
        const {data} = response
        if (!data) {
          reject('Verification failed, please Login again.')
        }
        setUserInfo(data)
        commit('RESET_STATE')
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // update user info
  updateInfo({commit, state}, userInfo) {
    return new Promise((resolve, reject) => {
      updateInfo(userInfo['userId'], userInfo).then(response => {
        const {data} = response
        if (!data) {
          reject('Verification failed, please Login again.')
        }
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({commit, state}) {

    return new Promise((resolve, reject) => {
      removeTokenInfo() // must remove  token  first
      removeUserInfo()
      resetRouter()
      commit('RESET_STATE')
      resolve()
      // MessageBox.confirm('您确定要退出登录吗？', 'Confirm logout', {
      //   confirmButtonText: 'Re-Login',
      //   cancelButtonText: 'Cancel',
      //   type: 'warning'
      // }).then(() => {
      // }).catch(() => {
      // });
    })
  },

  // remove token
  resetToken({commit}) {
    return new Promise(resolve => {
      removeTokenInfo() // must remove  token  first
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

