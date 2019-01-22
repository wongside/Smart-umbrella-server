const app = getApp();

Page({
    data: {
        charts: [{
            id: 'bar',
            sensor: 'UV',
            name: '紫外线强度',
            value: ''
        }, {
            id: 'scatter',
            sensor: 'PM2.5',
            name: 'PM2.5',
            value: ''
        }, {
            id: 'pie',
            sensor: 'dryness',
            name: '干燥度 ',
            value: ''
        }, {
            id: 'line',
            sensor: 'tempture',
            name: '环境温度',
            value: ''
        }, {
            id: 'funnel',
            sensor: 'AirQuality',
            name: '空气质量',
            value: ''
        }, {
            id: 'gauge',
            sensor: 'Heating',
            name: '加热丝温度',
            value: ''
        }],
        chartsWithoutImg: [{
            id: 'refresh',
            name: '刷新数据'
        }],
    },

    onReady() {
        if (!app.globalData.online) {
            wx.switchTab({
                url: "../user/user"
            });
        }
        setInterval(this.refreshData, 3000);
    },
    refreshData: function() {
        for (var i = 0; i < 6; i++) {
            this.setDataFromServer(this.data.charts[i], i);
        }
        console.log("刷新了一次数据！");
    },
    open: function(e) {
        this.refreshData();
        wx.request({
            url: app.globalData.url,
            data: {
                content: 'data',
                deviceId: 'test',
                dataName: 'humidness',
                count: 5
            },
            success(res) {
                console.log(res);
            }
        });
    },
    setDataFromServer: function(object, n) {
        var that = this;
        wx.request({
            url: app.globalData.url,
            data: {
                content: 'data',
                deviceId: 'test',
                dataName: object.sensor,
                count: 1
            },
            success(res) {
                var cs = that.data.charts;
                cs[n].value = res.data[0].value;
                that.setData({
                    charts: cs
                })
            }
        });
    }
});