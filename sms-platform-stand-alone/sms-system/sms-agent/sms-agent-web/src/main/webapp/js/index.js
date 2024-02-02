layui.config({base: '/'+document.getElementById('indexJs').getAttribute('data')+'/'}).extend({index: 'lib/index'}).use('index', 'sample');
window.onload = function () {
        //企业发送柱形图
        enterpriseSendMap();
        //头部数据
        currentDataMap();
        //地图统计
        //currentEchartsMapData();
    }
    //头部数据
    function currentDataMap(){
        $.ajax({
            cache : false,
            url: '/admin/index_currentDataMap',
            data: {},
            dataType: 'json',
            success: function (res) {
                if(res.code == '301'){
                    window.parent.parent.location.href=res.url;
                    return ;
                }
                $('#submitTotal').html(res.submitTotal);
                $('#profits_Total').html(res.profitsTotal);
                $('#newEnterpriseTotal').html(res.newEnterpriseCount);
                $('#newEnterpriseUserTotal').html(res.newEnterpriseUserCount);
            }
        });
    }

    //企业发送柱形图
    function loadingEcharts(){
        var myChart = echarts.init(document.getElementById('myChart'));
        myChart.showLoading({
            text: '加载中...',
            color: '#4cbbff',
            textStyle: { color: '#444' },
            effectOption: {backgroundColor: 'rgba(0, 0, 0, 0.2)'}
        });
        return false;
    }
    function  enterpriseSendMap(){
        loadingEcharts();
        $.ajax({
            cache : false,
            url: '/admin/index_enterpriseSendMap',
            data: {},
            dataType: 'json',
            success: function (res) {
                if(res.code == '301'){
                    window.parent.parent.location.href="/";
                    return ;
                }
                var list = res.data;
                if (list && list.length > 0){
                    list.sort(function(a,b){
                        return b.countTotal - a.countTotal
                    })
                }
                var xArray = []; //x轴数据
                var yArray1 = [];   //y轴1数据
                if (list && list.length > 0){
                    for(var j=0;j<list.length;j++){
                        if (list[j].enterpriseName != '' && list[j].countTotal != '') {
                            if(xArray.length < 10){
                                xArray[j] = handleData(list[j].countTotal);
                                yArray1[j] = beautySub(handleData(list[j].enterpriseName),4);
                            }
                        }
                    }
                }

                var myChart = echarts.init(document.getElementById('myChart'));
                var option = {
                    tooltip: {
                        trigger: 'axis'
                    },
                    grid:{
                        top:60,
                        right:70,
                        bottom:30,
                        left:60
                    },
                    xAxis: {
                        type: 'value',
                        minInterval: 1,
                        splitNumber:5,
                        boundaryGap: [0, 0.01],
                        splitLine:{					//---grid 区域中的分隔线
                            show:true,
                            lineStyle: {
                                width: 1,
                                type: 'dashed'
                            }
                        },
                        axisLine: {
                            show: true,
                            lineStyle: {
                                width: 1,
                                type: 'solid'
                            }
                        }
                    },
                    yAxis: {
                        type: 'category',
                        data: yArray1,
                        z:1000,
                        axisLabel: {
                            interval: 0,//横轴信息全部显示
                            rotate: 0,//-15度角倾斜显示
                            color:'#350d4b',
                            show: true,
                            showMinLabel: true,
                            showMaxLabel: true,
                            formatter: function (value) {
                                return value;
                            },
                            inside: true
                        },
                        axisLine: {//显示坐标轴线
                            show: true,
                            lineStyle: {
                                width: 1,
                                type: 'solid'
                            }
                        }
                    },
                    series: [
                        {
                            type: 'bar',
                            data: xArray,
                            itemStyle: {
                                normal: {
                                    color: "#6FCAC3" //柱状图颜色
                                }
                            },
                            barWidth:30,
                            showBackground: true,
                            backgroundStyle: {
                                color: 'rgba(180, 180, 180, 0.2)'
                            }
                        }
                    ]
                };
                myChart.setOption(option);
                window.onresize = myChart.resize;  //自适应浏览器窗口的大小
            }
        });
    }