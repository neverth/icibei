<template>
  <div>
    <div class="sidebar-header">
      <div class="user-pic">
        <img :src="userInfo['avatar']" alt="">
      </div>
      <div class="user-info">
        <div class="user-nick-name">
          <span>{{ userInfo['nickName'] ? userInfo['nickName'] : userInfo['accountId'] }}</span>
        </div>
        <div class="user-signature"><span>{{ userInfo['signature'] }}</span></div>
      </div>
      <div style="clear:both;"/>
    </div>
    <div class="sidebar-menu">
      <ul>
        <li class="header-menu">
          <span>action</span>
        </li>
        <li>
          <a href="#/home">
            <i class="el-icon-user-solid"/>
            <span>练习</span>
          </a>
        </li>
        <li>
          <a href="#/statistics">
            <i class="el-icon-user-solid"/>
            <span>练习统计</span>
          </a>
        </li>
        <li class="sidebar-dropdown">
          <a href="#">
            <i class="el-icon-user-solid"/>
            <span>单词本</span>
          </a>
          <div class="sidebar-submenu" style="display: none;">
            <ul>
              <li>
                <a href="#">全部单词</a>
              </li>
              <li>
                <a href="#">己练单词</a>
              </li>
              <li>
                <a href="#">未学单词</a>
              </li>
              <li>
                <a href="#">收藏单词</a>
              </li>
              <li>
                <a href="#">已斩单词</a>
              </li>
            </ul>
          </div>
        </li>

        <li>
          <a href="#/user">
            <i class="el-icon-user-solid"/>
            <span>我的</span>
          </a>
        </li>

        <li>
          <a href="#" @click="logout">
            <i class="el-icon-user-solid"/>
            <span>注销</span>
          </a>
        </li>
      </ul>
    </div>
  </div>

</template>

<script>
export default {
  name: 'Logined',
  computed: {
    userInfo() {
      return this.$store.getters.userInfo
    }
  },
  methods: {
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/`)
    }
  }
}
</script>

<style scoped>
.sidebar-header {
  padding: 10px 20px;
  overflow: hidden;
  border-top: 1px solid #b46236;
}

.sidebar-header .user-pic {
  float: left;
  width: 70px;
  height: 70px;
  border: 1px solid #585858;
  border-radius: 6px;
}

.sidebar-header .user-info {
  margin-left: 15px;
  float: left;
  color: #f8f1f1;
  width: 150px;
}

.sidebar-header .user-info .user-nick-name{
  font-weight: bold;
}

.sidebar-header .user-info .user-signature{
  margin-top: 10px;
  font-size: 12px;
}

.sidebar-header .user-pic img {
  border-radius: 6px;
  width: 100%;
  height: 100%;
}

.sidebar-wrapper .sidebar-header .user-info span {
  display: block;
}

.sidebar-menu {
  border-top: 1px solid #b46236;
}

.sidebar-menu .header-menu span {
  font-size: 14px;
  text-transform: uppercase;
  font-weight: bold;
  color: #ffc5c5;
  padding: 10px 20px;
  display: inline-block;
}

.sidebar-menu ul li a {
  display: block;
  width: 100%;
  color: #ffffff;
  text-decoration: none;
  position: relative;
  padding: 5px 30px 5px 20px;
  border-bottom: solid 1px #b46236;
  transition: color .2s linear, background .2s linear;
  font-size: 14px;
}

.sidebar-menu ul li a:hover {
  background-color: #b46236;
}

.sidebar-menu ul li a i {
  margin-right: 10px;
  font-size: 14px;
  /* background: #94582c; */
  width: 30px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  border-radius: 4px;
}
</style>
