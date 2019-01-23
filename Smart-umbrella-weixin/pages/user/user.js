var app = getApp();

Page({
    data: {
        userInfo: {},
        hasUserInfo: false,
        canIUse: wx.canIUse('button.open-type.getUserInfo'),
        deviceOline: false,
        hasDevice: false,
        deviceId:null
    },
    onLoad: function() {
        if (app.globalData.userInfo) {
            this.setData({
                userInfo: app.globalData.userInfo,
                hasUserInfo: true
            })
        } else if (this.data.canIUse) {
            app.userInfoReadyCallback = res => {
                this.setData({
                    userInfo: res.userInfo,
                    hasUserInfo: true
                })
            }
        } else {ssd
            // 在没有 open-type=getUserInfo 版本的兼容处理
            wx.getUserInfo({
                success: res => {
                    app.globalData.userInfo = res.userInfo
                    this.setData({
                        userInfo: res.userInfo,
                        hasUserInfo: true
                    })
                }
            })
        }
    },
    getUserInfo: function(e) {
        console.log(e)
        app.globalData.userInfo = e.detail.userInfo
        this.setData({
            userInfo: e.detail.userInfo,
            hasUserInfo: true
        })
    },
    onScanTap: function(event) {
        var that = this;
        wx.scanCode({
            success(res) {
                that.setData({
                    deviceId:res.result
                })
                console.log(that.data.deviceId);
            }
        })
    },
    ondeviceInfoTap: function() {
        wx.showModal({
            title: '我的雨伞ID',
            content: this.data.deviceId == null ? '你还没有设备呢' : this.data.deviceId,
            showCancel:false,
        })
    }

})