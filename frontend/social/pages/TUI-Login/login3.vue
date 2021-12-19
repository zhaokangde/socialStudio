<template>
  <view class="container">
    <image
      class="background-image"
      src="/static/static/assets/background.svg"
    ></image>
    <view class="counter-warp">
      <view class="header-content">
        <!-- <image src="/static/static/images/im.png" class="icon"></image> -->
        <view class="text">
          <view class="text-header">做大做强有限公司</view>
          <view class="text-content">！！！！！</view>
        </view>
      </view>
      <view class="box">
        <view class="login">
          <button
            type="default"
            open-type="getUserInfo"
            @getuserinfo="getUserInfo"
          >
            微信一键登录
          </button>
        </view>
      </view>
    </view>
  </view>
</template>

<script>
import { setTokenStorage } from "../../utils/token";
import logger from "../../utils/logger";
import { genTestUserSig } from "../../debug/GenerateTestUserSig.js";
import { loginSocial } from "../../common/socialLogin.js";
const { getTokenStorage } = require("../../utils/token.js");
const app = getApp();

export default {
  data() {
    return {
      //用户ID
      userID: "",
      //用户登录微信code
      jsCode: "",
      //用户名称
      userName: "",
      //用户头像地址
      userFaceUrl: ""
    };
  },

  components: {},
  props: {},

  onLoad(option) {
    uni.login({
      success: (res) => {
        this.jsCode = res.code;
      },
    });
  },
  onShow() {},

  methods: {
    getUserInfo(res) {
      var that = this;
      uni.showModal({
        title: "温馨提示",
        content: "亲，授权微信登录后才能正常使用小程序功能",
        success(res) {
          console.log(0);
          console.log(res);
          //如果用户点击了确定按钮
          if (res.confirm) {
            uni.getUserProfile({
              desc: "获取你的昵称、头像、地区及性别",
              success: (res) => {
                console.log(res);
                debugger;
                that.login(res.userInfo);
              },
              fail: (res) => {
                console.log(2);
                console.log(res);
                //拒绝授权
                uni.showToast({
                  title: "您拒绝了请求,不能正常使用小程序",
                  icon: "error",
                  duration: 2000,
                });
                return;
              },
            });
          } else if (res.cancel) {
            //如果用户点击了取消按钮
            console.log(3);
            uni.showToast({
              title: "您拒绝了请求,不能正常使用小程序",
              icon: "error",
              duration: 2000,
            });
            return;
          }
        },
      });
    },

    login(userInfo) {
      this.userName = userInfo.nickName;
      this.userFaceUrl = userInfo.avatarUrl;
      //登录social自己的后台，返回用户信息
      var socialUserInfo = loginSocial(
        this.jsCode,
        this.userName,
        this.userFaceUrl
      );
      this.userName = socialUserInfo.userName;
      this.userFaceUrl = socialUserInfo.userFaceUrl;
      this.userID = socialUserInfo.userId;
      //将用户信息存下来
      const userID = this.userID;
      const userName = this.userName;
      const userFaceUrl = this.userFaceUrl;
      const userSig = genTestUserSig(userID).userSig;
      logger.log(`TUI-login | login  | userSig:${userSig} userID:${userID}`);
      app.globalData.userInfo = {
        userSig,
        userID,
        userName,
        userFaceUrl,
      };
      setTokenStorage({
        userInfo: app.globalData.userInfo,
      });
      //登录并且将后台返给前端的数据更新到IM.SDK
      this.loginAndUpdateUserSDK();
      if (this.path && this.path !== "undefined") {
        uni.redirectTo({
          url: this.path,
        });
      } else {
        uni.switchTab({
          url: "../TUI-Index/index",
        });
      }
    },
    loginAndUpdateUserSDK() {
      //登录
      logger.log(
        `| TUI-Login | loginAndUpdateUserSDK  | login |userSig:${app.globalData.userInfo.userSig} userID:${app.globalData.userInfo.userID}`
      );
      uni.$TUIKit
        .login({
          userID: app.globalData.userInfo.userID,
          userSig: app.globalData.userInfo.userSig,
        })
        .then(() => {})
        .catch(() => {});

      //更新
      uni.$TUIKit
        .updateMyProfile({
          avatar: this.userFaceUrl,
          nick: this.userName,
        })
        .then((imResponse) => {
          logger.log(`| TUI-Login | loginAndUpdateUserSDK  | login SUCCESS`);
        })
        .catch(() => {
          logger.log(`| TUI-Login | loginAndUpdateUserSDK  | login FAIL`);
        });
    },
  },
};
</script>
<style scoped>
@import "./login.css";
</style>
