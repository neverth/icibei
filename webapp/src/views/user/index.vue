<template>
  <div class="user-container">
    <div style="font-size: 26px; font-weight: bold">账户设置</div>
    <div style="width: 80%; margin: 0 auto; padding: 10px">
      <div style="float: left; width: 60%">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="昵称">
            <el-input v-model="form.nickName" placeholder="设置您喜欢的昵称"></el-input>
          </el-form-item>
          <el-form-item label="用户名">
            <el-input disabled v-model="form.accountId"></el-input>
          </el-form-item>
          <el-form-item label="我的签名">
            <el-input type="textarea" v-model="form.signature"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="form.sex">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
              <el-radio label="保密"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item>
            <el-button :loading="updateInfoLoading" type="primary" @click="onSubmit">保存</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div style="float: left; width: 40%; padding-left: 20px">
        <label class="el-form-item__label">头像</label>
        <div style="width: fit-content; position: relative; margin-left: 10px">
          <el-popover placement="bottom" trigger="click">
            <div class="avatar-edit" slot="reference">
              <i class="el-icon-edit"></i>
              <span style="margin-left: 2px">编辑</span>
            </div>
            <ul>
              <li><span>删除头像</span></li>
              <li @click="myUploadShow = !myUploadShow"><span>更换头像</span></li>
            </ul>
          </el-popover>

          <el-avatar :size="150" :src="avatarUrl"></el-avatar>
        </div>
      </div>
      <div style="clear: both"></div>
    </div>
    <my-upload :url="uploadUrl"
               img-format="jpg" img-bgc="#fff"
               v-model="myUploadShow"
               ki="0"
               @crop-success="cropSuccess"
               @crop-upload-success="cropUploadSuccess"
               @crop-upload-fail="cropUploadFail"
               :no-rotate="false"
               :width="200"
               :height="200"
    />
  </div>
</template>

<script>
import myUpload from 'vue-image-crop-upload';

export default {
  name: "user",
  components: {
    myUpload
  },
  inject: ['reloadApp', 'reloadAppMain'],
  data() {
    return {
      form: {
        nickName: '',
        accountId: '',
        signature: '',
        sex: '保密',
      },
      avatarUrl: '',
      updateInfoLoading: false,
      myUploadShow: false,
      uploadUrl: `${process.env.VUE_APP_ICIBEI_GATEWAY}/organization/userInfo/${this.$store.getters.tokenInfo['userId']}/avatar/upload`,
    }
  },
  created() {
    let tokenInfo = this.$store.getters.tokenInfo
    if (!tokenInfo) {
      return
    }
    // 更新userInfo
    this.$store.dispatch('user/getInfo', tokenInfo['userId'])
    let userInfo = this.$store.getters.userInfo
    this.form.nickName = userInfo.nickName
    this.form.accountId = userInfo.accountId
    this.form.signature = userInfo.signature
    this.avatarUrl = userInfo.avatar
  },
  methods: {
    onSubmit() {
      this.updateInfoLoading = true
      this.$store.dispatch('user/updateInfo',
        {
          userId: this.$store.getters.userInfo['userId'],
          nickName: this.form.nickName,
          signature: this.form.signature,
          avatar: this.avatarUrl
        }
      ).then(() => {
        // 更新userInfo
        this.$store.dispatch('user/getInfo', this.$store.getters.tokenInfo['userId'])
        this.$message({
          message: '修改个人信息成功',
          type: 'success'
        });
        this.updateInfoLoading = false
      }).catch(() => {
        this.$message({
          message: '修改个人信息失败，请稍后再试！',
          type: 'error'
        });
        this.updateInfoLoading = false
      })
    },
    cropSuccess(imgDataUrl, field) {},
    cropUploadSuccess(jsonData, field) {
      this.reloadAppMain()
    },
    cropUploadFail(status, field) {}
  }
}
</script>

<style scoped>
.user-container {
  width: 100%;
  min-height: calc(100vh);
  background-color: white;
  padding: 20px;
  color: black;
}

.avatar-edit {
  position: absolute;
  bottom: 10px;
  border: 1px #e1e4e8 solid;
  border-radius: 10px;
  background-color: white;
  padding: 4px;
  font-size: 14px;
  cursor: pointer;
}

ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

li {
  padding: 3px 20px 3px 10px;
  text-align: center;
  cursor: pointer;
}

li:hover {
  background-color: #ce6d39;

}


</style>
