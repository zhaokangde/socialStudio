<template>
<view class="TUI-Emoji">
  <view v-for="(item, index) in emojiList" :key="index" class="TUI-emoji-image">
    <image :data-name="item.emojiName" class="emoji-image" :src="item.url" @tap="handleEnterEmoji"></image>
  </view>
</view>
</template>

<script>
import { emojiName, emojiUrl, emojiMap } from '../../../base/emojiMap';

export default {
  data() {
    return {
      emojiList: []
    };
  },

  components: {},
  props: {},

  beforeMount() {
    for (let i = 0; i < emojiName.length; i++) {
      this.emojiList.push({
        emojiName: emojiName[i],
        url: emojiUrl + emojiMap[emojiName[i]]
      });
    }

    this.setData({
      emojiList: this.emojiList
    });
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
    handleEnterEmoji(event) {
      this.$emit('enterEmoji', {
        detail: {
          message: event.currentTarget.dataset.name
        }
      });
    }

  }
};
</script>
<style>
@import "./index.css";
</style>
