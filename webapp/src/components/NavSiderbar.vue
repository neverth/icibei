<template>
  <div id="nav-siderbar" class="sidebar-wrapper">
    <a @click="toggleSiderbar" id="toggle-sidebar" style="cursor:pointer">
      <i class="fa fa-bars"></i>
    </a>
    <div class="sidebar-brand">
      <a href="#">IcIbeI</a>
    </div>
    <div class="sidebar-header">
      <div class="user-pic">
        <img
          class="img-responsive img-rounded mCS_img_loaded"
          src="img/defaultLogo.jpeg"
          style="width: 54px; height: 54px;"
        />
      </div>
      <div class="user-info">
        <span class="user-name">name</span>
        <span class="user-role">Welcome</span>
      </div>
    </div>
    <div class="sidebar-menu">
      <ul>
        <li class="header-menu">
          <span>action</span>
        </li>
        <li class="sidebar-dropdown">
          <a href="#">
            <i class="fa fa-align-center" aria-hidden="true"></i>
            <span>单词本</span>
          </a>
          <div class="sidebar-submenu" style="display: none;">
            <ul>
              <li>
                <a href="${request.getContextPath()}/userWordList">全部单词</a>
              </li>
              <li>
                <a href="#">己练单词</a>
              </li>
              <li>
                <a href="#">未学单词</a>
              </li>
              <li>
                <a href="${request.getContextPath()}/wordListLike">收藏单词</a>
              </li>
              <li>
                <a href="#">已斩单词</a>
              </li>
            </ul>
          </div>
        </li>
        <li>
          <a
            href="#"
            title="Warring"
            data-container="body"
            data-toggle="popover"
            data-placement="right"
            data-content="功能还未完成哦！"
          >
            <i class="fa fa-circle-o-notch" aria-hidden="true"></i>
            <span>练习统计</span>
          </a>
        </li>

        <li>
          <a
            href="#"
            title="Warring"
            data-container="body"
            data-toggle="popover"
            data-placement="right"
            data-content="功能还未完成哦！"
          >
            <i class="fa fa-circle-o-notch" aria-hidden="true"></i>
            <span>个性化</span>
          </a>
        </li>

        <li>
          <a
            href="#"
            title="Warring"
            data-container="body"
            data-toggle="popover"
            data-placement="right"
            data-content="功能还未完成哦！"
          >
            <i class="fa fa-circle-o-notch" aria-hidden="true"></i>
            <span>我的</span>
          </a>
        </li>

        <li>
          <a href="${request.getContextPath()}/userLoginOut">
            <i class="fa fa-circle-o-notch" aria-hidden="true"></i>
            <span>注销</span>
          </a>
        </li>
      </ul>
    </div>
    <div class="siderbar-footer">
      <p>
        © 2019
        <a href="${request.getContextPath()}/admin">NEVERTHELESS</a> . 赣ICP备19000765号.
      </p>
      <p>TRY YOU DO.</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "NavSiderbar",
  methods: {
    toggleSiderbar: function () {
      $("#nav-siderbar").toggleClass("toggled");
      $("#content").toggleClass("toggled");
    },
  },
};

import $ from "jquery";


$(".sidebar-dropdown > a").click(function () {
  $(".sidebar-submenu").slideUp(250);
  if ($(this).parent().hasClass("active")) {
    $(".sidebar-dropdown").removeClass("active");
    $(this).parent().removeClass("active");
  } else {
    $(".sidebar-dropdown").removeClass("active");
    $(this).next(".sidebar-submenu").slideDown(250);
    $(this).parent().addClass("active");
  }
});

$(function () {
  $("[data-toggle='popover']").popover();
});
</script>

<style scoped>

.sidebar-wrapper {
  width: 300px;
  background: #ce6d39;
  height: 100%;
  position: fixed;
  top: 0;
  left: -300px;
  /* text-align: center; */
}

#toggle-sidebar {
  position: fixed;
  top: 23px;
  left: 25px;
  color: #fff;
  font-size: 18px;
}

.sidebar-wrapper.toggled {
  left: 0px;
}

.sidebar-wrapper,
.page-content,
.page-content.toggled {
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  -o-transition: all 0.3s;
  -ms-transition: all 0.3s;
  transition: all 0.3s;
}

.page-content.toggled {
  padding-left: 300px;
}

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

.siderbar-header {
  padding: 10px;
}

.hearder-list {
  line-height: 40px;
}

.sidebar-wrapper .sidebar-brand {
  padding: 15px 20px;
  text-align: center;
}

.sidebar-wrapper .sidebar-brand > a {
  color: #fff;
  text-transform: uppercase;
  font-weight: bold;
  font-size: 30px;
}

.sidebar-wrapper .sidebar-header {
  padding: 20px;
  overflow: hidden;
  border-top: 1px solid #b46236;
  border-bottom: 1px solid #b46236;
}

.sidebar-wrapper .sidebar-header .user-pic {
  float: left;
  width: 60px;
  padding: 2px;
  border: 1px solid #585858;
  border-radius: 8px;
  margin-right: 15px;
}

.sidebar-wrapper .sidebar-header .user-info {
  float: left;
  color: #f8f1f1;
}

.img-rounded {
  border-radius: 6px;
}

.sidebar-wrapper .sidebar-header .user-info span {
  display: block;
}

.sidebar-wrapper .sidebar-menu {
  padding-bottom: 10px;
}

.sidebar-wrapper ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.sidebar-wrapper .sidebar-menu .header-menu span {
  font-size: 14px;
  text-transform: uppercase;
  font-weight: bold;
  color: #ffc5c5;
  padding: 10px 20px;
  display: inline-block;
}

.sidebar-wrapper .sidebar-menu ul li a {
  display: inline-block;
  width: 100%;
  color: #ffffff;
  text-decoration: none;
  transition: color 0.3s;
  position: relative;
  padding: 5px 30px 5px 20px;
}

.sidebar-wrapper .sidebar-menu ul li a i {
  margin-right: 10px;
  font-size: 20px;
  /* background: #94582c; */
  width: 30px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  border-radius: 4px;
}

.sidebar-wrapper .sidebar-menu .sidebar-dropdown div {
  background: #082730;
  border-bottom: 1px solid #082730;
  width: 275px;
  margin-bottom: 15px;
}

.sidebar-wrapper .sidebar-menu .sidebar-dropdown .sidebar-submenu ul {
  padding: 5px 0;
}

.sidebar-wrapper .sidebar-menu .sidebar-dropdown .sidebar-submenu li {
  padding-left: 25px;
  font-size: 13px;
}

.sidebar-wrapper .sidebar-menu ul li a {
  display: inline-block;
  width: 100%;
  color: #fff;
  text-decoration: none;
  transition: color 0.3s;
  position: relative;
  padding: 5px 30px 5px 20px;
}

.popover {
  color: #082730;
}
</style>