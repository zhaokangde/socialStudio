<template>
<view class="container">
  <image class="background-image" src="/static/static/assets/background.svg"></image>
  <view class="counter-warp">
    <view class="header-content">
      <image src="/static/static/images/im.png" class="icon"></image>
      <view class="text">
        <view class="text-header">登录 · 即时通信</view>
        <view class="text-content">体验群组聊天，视频对话等IM功能</view>
      </view>
    </view>
    <view class="box">
      <view class="login">
          <button type="default" open-type="getUserInfo" @getuserinfo="getUserInfo">一键登录微信小程序</button>
            <!-- <button class="loginBtn" :disabled="!privateAgree" @tap="login">登录</button> -->
      </view>

    </view>
  </view>
</view>
</template>

<script>
import { setTokenStorage } from '../../utils/token';
import logger from '../../utils/logger';
import { genTestUserSig } from '../../debug/GenerateTestUserSig.js'
const {
  getTokenStorage
} = require("../../utils/token.js");
const app = getApp();

export default {
  data() {
    return {
      userID: '',
      hidden: false,
      btnValue: '获取验证码',
      btnDisabled: false,
      privateAgree: false,
      phone: '',
      code: '',
      sessionID: '',
      second: 60,
      path: '',
      lastTime: 0,
      countryIndicatorStatus: false,
      country: '86',
      indicatorValue: 46,
      headerHeight: app.globalData.headerHeight,
      statusBarHeight: app.globalData.statusBarHeight,
      showlogin: false
    };
  },

  components: {},
  props: {},

  onLoad(option) {
    const that = this;
    this.path=option.path;
    uni.getStorage({
      // 获取本地缓存
      key: 'sessionID',

      success(res) {
        that. sessionID=res.data;
      }

    });
    uni.setStorage({
      key: 'path',
      data: option.path
    });
    debugger
    // 开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
				wx.getUserProfile({
					desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
					success: (res) => {
						console.log(res);
						console.log(res.userInfo.avatarUrl);//获取用户微信头像
						console.log(res.userInfo.nickName);//获取用户微信名
            this.userID=res.userInfo.nickName
					}
				})
  },

  onShow() {

  },

  methods: {
    getUserInfo(res) {
           console.log(res);
           uni.login({
               provider: 'weixin',
               success: function(loginRes) {
                   console.log(loginRes);
                   // 获取用户信息
                   uni.getUserInfo({
                       provider: 'weixin',
                       success: function(infoRes) {
                           console.log('用户昵称为：' + infoRes.userInfo.nickName);
                       }
                   });
               }
           });
        
       },
    loginWithToken() {
      uni.switchTab({
        url: '../TUI-Index/index'
      });
    },

    onBack() {
      uni.navigateTo({
        url: '../TUI-Index/TUI-Index'
      });
    },

  // 输入userID
  bindUserIDInput(e) {
	const val = e.detail.value
	this.userID=val
  },

    onAgreePrivateProtocol() {
      this.privateAgree=!this.privateAgree;
    },

    linkToPrivacyTreaty() {
      const url = 'https://web.sdk.qcloud.com/document/Tencent-IM-Privacy-Protection-Guidelines.html';
      uni.navigateTo({
        url: `../TUI-User-Center/webview/webview?url=${url}&nav=Privacy-Protection`
      });
    },

    linkToUserAgreement() {
      const url = 'https://web.sdk.qcloud.com/document/Tencent-IM-User-Agreement.html';
      uni.navigateTo({
        url: `../TUI-User-Center/webview/webview?url=${url}&nav=User-Agreement`
      });
    },

    login() {
	  const userID = this.userID
	  const userSig = genTestUserSig(userID).userSig
	  logger.log(`TUI-login | login  | userSig:${userSig} userID:${userID}`)
	  app.globalData.userInfo = {
		userSig,
		userID,
	  }
	  setTokenStorage({
		userInfo: app.globalData.userInfo,
	  })
	  if (this.path && this.path !== 'undefined') {
		uni.redirectTo({
		  url: this.path,
		})
	  } else {
		uni.switchTab({
		  url: '../TUI-Index/index',
		})
	  }
    },
  }
};
</script>
<style scoped>
@import "./login.css";
</style>
