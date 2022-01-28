<template>
  <view>
    <view>
      <!-- 第一部分 背景-->
      <view class="view" >
        <image
          class="image-radius"
          :mode="'aspectFill'"
          :src="'/static/static/images/背景图片.png'"
        ></image>
      </view>
      <!-- 第二部分 纵向布局 头像 粉丝 关注 局数-->
      <view class="view-item">
        <image
          class="view-item-image"
          :src="'/static/static/images/背景图片.png'"
        ></image>
        <view class="view-user">
          <image
            class="view-icon"
            :src="'/static/static/social/粉丝.png'"
          ></image>
          <text class="view-icon-text">粉丝</text>
        </view>

        <view class="view-user">
          <image
            class="view-icon"
            :src="'/static/static/social/关注.png'"
          ></image>
          <text class="view-icon-text">关注</text>
        </view>
        <view class="view-user">
          <image
            class="view-icon"
            :src="'/static/static/social/局数.png'"
          ></image>
          <text class="view-icon-text">局数</text>
        </view>
      </view>
      <!-- 第三部分  用户信息+编辑按钮-->
      <view class="view-user-info-and-edit">
        <view class="view-user-info">
          <text class="user-info-name">比尔盖茨东北</text>
          <text class="user-info-sign-name"
            >这是一句个性xxxxxxxxxXXXXXXXXXXXXXXX</text
          >

          <view class="view-user-lable">
            <view class="view-user-lable-item">
              <image
                class="view-lable"
                :src="'/static/static/social/地址.png'"
              ></image>
              <text class="view-lable-text">杭州市</text>
            </view>
            <view class="view-user-lable-item">
              <image
                class="view-lable"
                :src="'/static/static/social/星座.png'"
              ></image>
              <text class="view-lable-text">双鱼座</text>
            </view>
          </view>
        </view>
        <view class="view-user-info-edit">
          <text style="font-size: 22upx; margin-left: 30upx">编辑</text>
        </view>
      </view>
      <!-- 第四部分  我发起的，参与的 动态-->
      <view class="view-user-game">
      
<v-tabs v-model="current" :scroll=true  :fontSize="'32upx'" :lineColor="' rgb(8, 8, 8)'" :color="'#rgb(77, 71, 71)'" :activeColor="'#rgb(77, 71, 71)'" :bold=true :tabs="tabs" @change="changeTab"></v-tabs>
      </view>
   
    </view>
  </view>
</template>

<script>
import logger from "../../../utils/logger";
import { getUserInfo } from "../../../common/userInfo";
const app = getApp();

export default {
  data() {
    return {
       current: 0,
      tabs: ['我发起的', '我参与的'],
      userInfo: {
        //用户ID
        userID: "",
        //用户名称
        userName: "",
        //用户头像地址
        userFaceUrl: "",
        //年龄
        age: "",
        //性别
        sex: "",
        //学校
        school: "",
        //星座
        constellation: "",
        //粉丝
        fans: "",
        //关注
        flows: "",
      },
    };
  },

  components: {},
  props: {},

  onShow() {
    console.log();
    // this.userInfo = getUserInfo(app.globalData.userInfo.userID);
  },

  methods: {
     changeTab(index) {
      console.log('当前选中索引：' + index)
    },
    setData(obj) {
      let that = this;
      let keys = [];
      let val, data;
      Object.keys(obj).forEach(function (key) {
        keys = key.split(".");
        val = obj[key];
        data = that.$data;
        keys.forEach(function (key2, index) {
          if (index + 1 == keys.length) {
            that.$set(data, key2, val);
          } else {
            if (!data[key2]) {
              that.$set(data, key2, {});
            }
          }
          data = data[key2];
        });
      });
    },
    onload() {},

    personal() {
      // TUIKit xxxx | mine | personal | xxxx
      uni.navigateTo({
        url: "../personal/personal",
      });
    },
  },
};
</script>
<style>
@import "./mine.css";
</style>
