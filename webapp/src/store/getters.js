const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  tokenInfo: state => state.user.tokenInfo,
  userInfo: state => state.user.userInfo,
  permission_routes: state => state.permission.routes,
}
export default getters
