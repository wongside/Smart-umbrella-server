// pages/user/user.js
const app = getApp();
Page({
    data: {
        name: '',
        pwd: ''
    
    },
    onLoad: function(options) {
        this.setData({
            name: wx.getStorageSync('name'),
            pwd: wx.getStorageSync('pwd')
        });
        if(this.canLogin(this.data.name,this.data.pwd)){
            this.setData({
                online:true
            })
        }
    },
    formLogin(e) {
        var name = e.detail.value.name;
        var pwd = e.detail.value.pwd;
        if (this.canLogin(name, pwd)) {
            wx.setStorage({
                key: 'name',
                data: name,
            });
            wx.setStorage({
                key: 'pwd',
                data: pwd,
            });
            this.setData({
                name: name,
                pwd: pwd,
                online: true
            });
        }
    },
    formLogon() {
        console.log('form发生了reset事件');
    },
    canLogin(name, pwd) {
        wx.request({
            url: app.globalData.loginUrl,
            data: {
                method: 'login',
                name: name,
                pwd: pwd
            },
            success(res) {
                console.log(res);
            }
        });
        return true;
    },
    onLogoffTap: function(event) {
        wx.removeStorageSync('name');
        wx.removeStorageSync('pwd');
        this.setData({
            name: '',
            pwd: '',
            online: false
        })
    },
    onBackTap:function(event){
        wx.switchTab({
            url: '/pages/index/index',
        })
    },
    onScanTap:function(event){
        wx.scanCode({
            success(res){
                console.log(res);
            }
        })
    },
    getUserInfo:function(res){
        console.log(res);
    }
})