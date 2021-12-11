<template>
<view class="TUI-faceMessage" @tap="previewImage">
  <image class="face-message" :src="renderDom.src"></image>
</view>
</template>

<script>

export default {
  data() {
    return {
      renderDom: [],
      percent: 0,
      faceUrl: 'https://web.sdk.qcloud.com/im/assets/face-elem/'
    };
  },

  components: {},
  props: {
    message: {
      type: Object,
    },
    isMine: {
      type: Boolean,
      default: true
    }
  },
  watch: {
    message: {
      handler: function (newVal) {
        this.setData({
          renderDom: this.parseFace(newVal)
        });
      },
      immediate: true,
      deep: true
    }
  },
  methods: {
    	  setData(obj){    
	  let that = this;    
	  let keys = [];    
	  let val,data;    
	  Object.keys(obj).forEach(function(key){    
	   keys = key.split('.');    
	   val = obj[key];    
	   data = that.$data;    
	   keys.forEach(function(key2,index){    
	       if(index+1 == keys.length){    
	           that.$set(data,key2,val);    
	       }else{    
	           if(!data[key2]){    
	              that.$set(data,key2,{});    
	           }    
	       }    
	       data = data[key2];    
	   })    
	  });    
	  }  ,
    // 解析face 消息
    parseFace(message) {
      const renderDom = {
        src: `${this.faceUrl + message.payload.data}@2x.png`
      };
      return renderDom;
    },

    previewImage() {
      uni.previewImage({
        current: this.renderDom[0].src,
        // 当前显示图片的http链接
        urls: [this.renderDom[0].src]
      });
    }

  }
};
</script>
<style>
@import "./index.css";
</style>
