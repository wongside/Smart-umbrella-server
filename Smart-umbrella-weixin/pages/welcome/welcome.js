const app = getApp();

Page({
    bindViewTap: function(event) {
        wx.switchTab({
            url: "../index/index"
        });
    },
    onReady: function() {
        setTimeout(function() {
            wx.switchTab({
                url: app.globalData.online ? 
                    "../index/index" : "../user/user"
            });
        }, 1500);
    }
})