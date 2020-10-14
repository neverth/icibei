<template>
  <div class="sidebar-header">
    <p>抱歉您还没有登录哦！</p>
    <p style="opacity: 1">没有账号？<a href="#" @click="registerTips">点击注册</a></p>
    <div class="form-group">
      <label>请输入账号：</label>
      <input id="username" required="required" type="text" class="form-control" placeholder="account" name="account">
    </div>
    <div class="form-group" style="position: relative">
      <label id="passw-err">密码：</label>
      <div class="safety-factor-div">
        <div id="safe-d1" style="background-color:red;" />
        <div id="safe-d2" style="background-color:#F0F028;" />
        <div id="safe-d3" style="background-color:green;" />
      </div>
      <input id="passw" required="required" type="password" class="form-control" placeholder="Password" name="password">
    </div>
    <div class="form-group registerInput">
      <label id="repassw-err">确认密码：</label>
      <input id="repassw" type="password" class="form-control" placeholder="Password" name="password1">
    </div>
    <div class="form-group registerInput">
      <label>滑动验证</label>
      <div id="box" onselectstart="return false;" class="form-control">
        <div class="bgColor txt">滑动验证</div>
        <!--给i标签添加上相应字体图标的类名即可-->
        <div class="slider"><i class="fa fa-angle-double-right" /></div>
      </div>
    </div>
    <button id="registerButton" type="submit" class="btn btn-primary" @click="submitE">登录
    </button>
  </div>
</template>

<script>
import $ from 'jquery'
export default {
  name: 'NavSiderBarLogined',
  data: function() {
    return {
      isLogin: true
    }
  },
  methods: {
    registerTips: function() {
      if (this.isLogin) {
        $('.registerInput').css('opacity', '1')
        $('#registerButton').css('top', '0')
        this.isLogin = false
      } else {
        $('.registerInput').css('opacity', '0')
        $('#registerButton').css('top', '-160px')
        this.isLogin = true
      }
    },
    submitE: function() {
      this.$axios.post('')
    }
  }
}
</script>

 <style scoped>
   .sidebar-header {
     padding: 20px;
     overflow: hidden;
     border-top: 1px solid #b46236;
   }

   .registerInput, #registerButton {
     transition: all 0.5s;
   }

   .sidebar-header p {
     margin-bottom: 1rem;
   }

   .registerInput {
     opacity: 0;
   }
   #registerButton {
    top: -160px;
    position: relative;
   }
 </style>
