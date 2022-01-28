/**
 * 登录social后台请求方法
 */

import socialBackend from './commonContant';

let app = getApp();
/**
 * 登录social后台
 */

export function loginSocial(jsCode,userName,userFaceUrl) {
  var userInfo ;
  uni.request({  
    url: socialBackend.url+"/",  
    method:'GET',  
    data: {  
        jsCode: jsCode,        //微信登陆状态code  
        userName: userName,       //微信用户名,  
        userFaceUrl: userFaceUrl              //头像url
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
