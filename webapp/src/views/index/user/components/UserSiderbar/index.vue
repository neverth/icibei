<template>
  <div class="sidebar-wrapper" :class="{toggled: toggled}">
    <!-- 侧边栏切换按钮 -->
    <a class="toggle-sidebar" style="cursor:pointer" @click="toggled = !toggled">
      <i class="el-icon-menu" />
    </a>
    <!-- 侧边栏商标 -->
    <div class="sidebar-brand">
      <a href="#">IcIbeI</a>
    </div>
    <!-- 侧边栏实际内容区 -->
    <Logined v-if="userInfo" />
    <Login v-if="!userInfo" />
    <!-- 底部说明 -->
    <div class="siderbar-footer">
      <p>
        © 2020<a href="#">NEVERTH.FUN</a> . 赣ICP备19000765号.
      </p>
      <p>TRY YOU DO.</p>
    </div>
  </div>
</template>

<script>
import Logined from './Logined'
import Login from './Login'

export default {
  name: 'UserSiderbar',
  components: {
    Logined,
    Login
  },
  data() {
    return {
      logined: false,
      toggled: false
    }
  },
  computed: {
    userInfo() {
      return this.$store.getters.userInfo
    }
  },
  created() {
  },
  methods: {
  }
}
</script>

<style scoped>
  /* 整个侧边栏 */
  .sidebar-wrapper {
    width: 300px;
    background: #ce6d39;
    height: 100%;
    position: fixed;
    top: 0;
    left: -300px;
    transition: all 0.3s;
  }

  /* 侧边栏切换按钮 */
  .toggle-sidebar {
    position: fixed;
    top: 23px;
    left: 25px;
    color: #fff;
    font-size: 18px;
  }

  /* 侧边栏弹出弹回 */
  .sidebar-wrapper.toggled {
    left: 0px;
  }

  .sidebar-wrapper .sidebar-brand {
    padding: 15px 20px;
    text-align: center;
  }

  .sidebar-wrapper .sidebar-brand>a {
    color: #fff;
    text-transform: uppercase;
    font-weight: bold;
    font-size: 30px;
  }

  /* 侧边栏尾部 */
  .siderbar-footer {
    position: absolute;
    bottom: 2px;
    text-align: center;
    width: 300px;
    font-size: 12px;
    font-family: sofia, "Microsoft YaHei";
  }

  .siderbar-footer p {
    color: white;
    margin-bottom: 2px;
  }
</style>
