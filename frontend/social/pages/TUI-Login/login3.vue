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
            <button open-type="getPhoneNumber" @getphonenumber="onGetPhoneNumber">唤起授权</button>
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
    this.loginGetNum();
  },

  onShow() {

  },

  methods: {
    loginGetNum(){
       uni.login({  
        success: (res) => {  
            if (res.code) {         //微信登录成功 已拿到code  
                this.jsCode=res.code  
                  //保存获取到的code 
                uni.request({  
                    url: 'https://api.weixin.qq.com/sns/jscode2session',  
                    method:'GET',  
                    data: {  
                        appid: 'wx04fa5f1f1c08e98b',        //你的小程序的APPID  
                        secret: 'c475639bc687cb2499be2adc731ad3ec',       //你的小程序的secret,  
                        js_code: res.code              //wx.login 登录成功后的code  
                    },  
                    success: (cts) => { 
                      debugger
                       
                        // 换取成功后 暂存这些数据 留作后续操作  
                        this.openid=cts.data.openid     //openid 用户唯一标识  
                        this.unionid=cts.data.unionid     //unionid 开放平台唯一标识  
                        this.session_key=cts.data.session_key  
                        this.userID=this.openid     //session_key  会话密钥  
                    },
                    fail:(cts) =>{
                    
                    }  
                });  
            } else {  
                console.log('登录失败！' + res.errMsg)  
            }  
        }  
    })
    },
      onGetPhoneNumber(e){  
        debugger
        if(e.detail.errMsg=="getPhoneNumber:fail user deny"){       //用户决绝授权  
            //拒绝授权后弹出一些提示  
        }else{   
              //允许授权  
            console.log(e.detail.encryptedData)  
            e.detail.encryptedData      //加密的用户信息  
            e.detail.iv     //加密算法的初始向量  时要用到  
        }  
    },
    getUserInfo(res) {
            var that =  this
           console.log(res);
          //  uni.login({
          //      provider: 'weixin',
          //      success: function(loginRes) {
          //          console.log(loginRes);
          //          // 获取用户信息
                  
          //      }
          //  });
        uni.showModal({
	title: '温馨提示',
	content: '亲，授权微信登录后才能正常使用小程序功能',
	success(res) {
		console.log(0)
		console.log(res)
		//如果用户点击了确定按钮
		if (res.confirm) {

			uni.getUserProfile({
				desc: '获取你的昵称、头像、地区及性别',
				success: res => {
					console.log(res);
					console.log(1);
           that.login();
				},
				fail: res => {
					console.log(2);
					console.log(res)
					//拒绝授权
					uni.showToast({
						title: '您拒绝了请求,不能正常使用小程序',
						icon: 'error',
						duration: 2000
					});
					return;
				}
			});
		} else if (res.cancel) {
			//如果用户点击了取消按钮
			console.log(3);
			uni.showToast({
				title: '您拒绝了请求,不能正常使用小程序',
				icon: 'error',
				duration: 2000
			});
			return;
		}
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
