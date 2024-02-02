<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/layui_common.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8"/>

    <link rel="stylesheet" href="/css/preview/preview.css">
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/swiper/swiper.min.js"></script>
</head>

<body>
<div class="phone">
    <div class="layui-panel phone_head">
        &nbsp;&nbsp;<i class="layui-icon">&#xe603;</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1069************&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i
            class="layui-icon">&#xe671;</i>
    </div>
    <div id="nowTime" style="margin-left: 25px"></div>
    <div class="container">

    </div>
    <div class="layui-panel phone_bottom">
        &nbsp;&nbsp;&nbsp;<i class="layui-icon">&#xe654;</i>&nbsp;
        <input type="text" autocomplete="off"
               style="width: 145px;height:25px;border-radius: 8px">
        发送
    </div>
</div>
<input type="text" id="content" name="content" hidden>
</body>
<script type="text/javascript">
    var date = new Date();
    $("#nowTime").html(date.getMonth() + 1 + "月" + date.getDate() + "日 " + date.getHours() + ":" + (date.getMinutes() > 9 ? date.getMinutes() : "0" + date.getMinutes()))

    var params = getRequestParams();
    var mmsContent = JSON.parse(params['mmsContent']);

    if(mmsContent.templateCode){
        $.ajax({
            type: 'post',
            url: '/admin/business_mmsTemplateList?pagination.pageIndex=1&pagination.pageSize=1',
            dataType: 'json',
            data:{
                template_Code:mmsContent.templateCode
            },
            success: function (res) {
                if (res.data.length > 0){
                    var template_Content = JSON.parse(res.data[0].template_Content);

                    var mmsRealContent = template_Content.data;
                    var variableKeys = getVariableKeys(mmsRealContent);

                    $.each(variableKeys,function(i, val) {
                        mmsRealContent = mmsRealContent.replace("\${"+val+"}",mmsContent[val]);
                    })
                    var template_Content_data = JSON.parse(mmsRealContent);

                    $.each(template_Content_data, function (i, val) {
                        if (val.type == "txt") {
                            var div = '<div class="slide_text">' + val.content + '</div>'
                            $(".container").append(div);
                        } else {
                            var slide = '';
                            var url = val.url;
                            if (val.type == "video") {
                                slide = '<video class="slide"  controls  preload="" src="' + url + '"></video>'
                            } else if (val.type == "audio") {
                                slide = '<audio class="slide"  controls  preload="" src="' + url + '"></audio>'
                            } else {
                                slide = '<img class="slide" src="' + url + '">';
                            }
                            $(".container").append(slide);
                        }
                    });
                    var container = $(".container").html();
                    if (container == null || container.length == 0) {
                        $(".container").append('<div class="slide_text">这是一段测试文本</div>');
                    }
                }
            }, error: function (d) {
                layer.alert("操作失败" + d.msg)
            }
        })
    }
    //处理变量
    function getVariableKeys(content) {
        var retArr = new Array();
        var arrMatch = null;
        var rePattern = new RegExp("\\\\$\\{(.+?)\\}", "ig");
        while (arrMatch = rePattern.exec(content)) {
            retArr.push(arrMatch[1]);
        }
        return retArr;
    }
</script>
</html>
