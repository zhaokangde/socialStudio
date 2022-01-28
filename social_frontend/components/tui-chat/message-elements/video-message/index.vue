<template>
<view :class="'video-box ' + (isMine?'my-video':'')" @click="playerHander">
  <image class="video-icon" src="../../../../static/static/images/video-play.png"></image>
</view>
</template>

<script>
import  videoIcon from '../../../../static/static/images/video-play.png'
export default {
  data() {
    return {
      isPlay: false,
      videoIcon: videoIcon
    };
  },

  components: {},
  props: {
    message: {
      type: Object,
      default: () => {}
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
          message: newVal,
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
    playerHander() {
      uni.$emit('videoPlayerHandler',{
        isPlay: true,
        message: this.message
      })
    },
    stopHander() {
      this.isPlay = false
    }
  }
};
</script>
<style>
@import "./index.css";
</style>
