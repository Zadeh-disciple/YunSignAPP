
var isReady=false;var onReadyCallbacks=[];
var __uniConfig = {"pages":["pages/index/index","pages/course/course","pages/contact/contact","pages/find/find","pages/my/my"],"window":{"navigationBarTextStyle":"black","navigationBarTitleText":"yunsign","navigationBarBackgroundColor":"#FFFFFF","backgroundColor":"#55aaff"},"tabBar":{"list":[{"text":"班课","pagePath":"pages/index/index","iconPath":"static/tabs/cloud.png","selectedIconPath":"static/tabs/cloud-active.png"},{"text":"发现","pagePath":"pages/find/find","iconPath":"static/tabs/find.png","selectedIconPath":"static/tabs/find-active.png"},{"text":"我的","pagePath":"pages/contact/contact","iconPath":"static/tabs/user.png","selectedIconPath":"static/tabs/user-active.png"}]},"splashscreen":{"alwaysShowBeforeRender":true,"autoclose":false},"appname":"yunsign","compilerVersion":"2.6.5","entryPagePath":"pages/index/index","networkTimeout":{"request":60000,"connectSocket":60000,"uploadFile":60000,"downloadFile":60000}};
var __uniRoutes = [{"path":"/pages/index/index","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"班课","navigationStyle":"custom"}},{"path":"/pages/course/course","meta":{},"window":{"navigationBarTitleText":"工程实践"}},{"path":"/pages/contact/contact","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"我的"}},{"path":"/pages/find/find","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"发现"}},{"path":"/pages/my/my","meta":{},"window":{"backgroundColor":"#757575"}}];
__uniConfig.onReady=function(callback){if(__uniConfig.ready){callback()}else{onReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"ready",{get:function(){return isReady},set:function(val){isReady=val;if(!isReady){return}const callbacks=onReadyCallbacks.slice(0);onReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
service.register("uni-app-config",{create(a,b,c){if(!__uniConfig.viewport){var d=b.weex.config.env.scale,e=b.weex.config.env.deviceWidth,f=Math.ceil(e/d);Object.assign(__uniConfig,{viewport:f,defaultFontSize:Math.round(f/20)})}return{instance:{__uniConfig:__uniConfig,__uniRoutes:__uniRoutes,window:void 0}}}});