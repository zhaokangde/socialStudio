/**
 * 用户信息相关
 */

 import socialBackend from './commonContant';

 export function getUserInfo(userId) {
    var userInfo ;
    uni.request({  
      url: socialBackend.url+"/",  
      method:'GET',  
      data: {  
        userId: userId
      },  
      success: (cts) => { 
        debugger
        userInfo = cts
      },
      fail:(cts) =>{
      
      }  
  });  
  return userInfo;
  }