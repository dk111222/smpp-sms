<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/layui_common.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>首页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <style type="text/css">
        ul {
            width: 90%;
        }

        li {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    </style>
    <script id="indexJs" type="text/javascript"
            data='<ht:heropageconfigurationtext code="agent_webpage_css" defaultValue="/layuiadmin"/>'></script>
    <script src='<ht:heropageconfigurationtext code="agent_webpage_css" defaultValue="/layuiadmin"/>/lib/extend/echarts.js'></script>
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/index.js"></script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md9">
            <div class="layui-row layui-col-space15" >
                <div class="layui-col-sm4 layui-col-md4">
                    <div class="layui-card">
                        <div class="layui-card-body" style="text-align: center; height: 120px; font-weight: bold;">
                            <div class="layui-col-xs12" style="height: 45px;"></div>
                            <div class="layui-col-xs5">
                                当前余额
                                <p style="margin: 10px;"><c:out value="${AGENT_INFO.available_Amount}"/></p>
                            </div>
                            <div class="layui-col-xs2"><img src="/public/admin/images/fgf.png"></div>
                            <div class="layui-col-xs5">
                                <p style="margin-top: 10px;">
                                <button class="layui-btn layui-btn-sm grid-demo" onclick="newTab('/admin/charge/agent_charge_list.jsp','充值明细')">充值明细
                                </button>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-col-sm4 layui-col-md4">
                    <div class="layui-card">
                        <div class="layui-card-body" style="text-align: center; height: 120px; font-weight: bold;">
                            <div class="layui-col-xs12" style="height: 45px;">
                                <span class="layui-badge layui-bg-orange layuiadmin-badge">今日</span>
                            </div>
                            <div class="layui-col-xs5">
                                提交总数<p id="submitTotal" style="margin:10px;"></p>
                            </div>
                            <div class="layui-col-xs2"><img src="/public/admin/images/fgf.png"></div>
                            <div class="layui-col-xs5">
                                利润<p id="profits_Total" style="margin:10px;"></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-col-sm4 layui-col-md4">
                    <div class="layui-card">
                        <div class="layui-card-body" style="text-align: center; height: 120px; font-weight: bold;">
                            <div class="layui-col-xs12" style="height: 45px;">
                                <span class="layui-badge layui-bg-green layuiadmin-badge">今日</span>
                            </div>
                            <div class="layui-col-xs5">
                                新增企业
                                <p id="newEnterpriseTotal" style="margin:10px;"></p>
                            </div>
                            <div class="layui-col-xs2"><img src="/public/admin/images/fgf.png"></div>
                            <div class="layui-col-xs5">
                                新增用户
                                <p id="newEnterpriseUserTotal" style="margin:10px;"></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-col-sm12 layui-col-md12">
                <div class="layui-card">
                    <div id="myChart" style="width: 100%; height: 340px;"></div>
                </div>
            </div>
        </div>
        <div class="layui-col-md3">
            <div class="layui-row" layui-col-space15>
                <div class="layui-col-sm6 layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">
                            企业公告
                        </div>
                        <div class="layui-card-body layuiadmin-card-list">
                            <ul class="layuiadmin-card-status layuiadmin-home2-usernote">
                                <li  class="demo"  data-type="notice1">
                                    <ht:heropageconfigurationtext code="notice_one" defaultValue="请设置信息" />
                                </li>
                                <li class="demo"  data-type="notice2">
                                    <ht:heropageconfigurationtext code="notice_two" defaultValue="请设置信息" />
                                </li>
                                <li class="demo"  data-type="notice3">
                                    <ht:heropageconfigurationtext code="notice_three" defaultValue="请设置信息" />
                                </li>
                                <li class="demo"  data-type="notice4">
                                    <ht:heropageconfigurationtext code="notice_four" defaultValue="请设置信息" />
                                </li>
                                <li class="demo"  data-type="notice5">
                                    <ht:heropageconfigurationtext code="notice_five" defaultValue="请设置信息" />
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="layui-card">
                        <div class="layui-card-body" style="height: 150px; width: 100%; text-align: center;">
                            <div class="layui-col-xs4"
                                 onclick="newTab('/admin/enterprise/enterprise_list.jsp?&limitCode=003001','添加企业')">
                                <img src="/public/admin/images/jqy.svg" width="33%" height="60px">
                                <br>添加企业
                            </div>
                            <div class="layui-col-xs4" onclick="newTab('/admin/enterprise_perUserlist?&limitCode=003002','添加用户')">
                                <img src="/public/admin/images/jyh.svg" width="33%" height="60px">
                                <br>添加用户
                            </div>
                            <div class="layui-col-xs4"
                                 onclick="newTab('/admin/enterprise_perUserlist?&limitCode=003002','企业充值')">
                                <img src="/public/admin/images/qycz.svg" width="33%" height="60px">
                                <br>企业充值
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-col-sm12 layui-col-md12">
        <div class="layui-card">
            <table class="layui-hide" id="agentFeeTable"></table>
            <div style="margin: 10px 0px 0px 50%">
                <span id="showMorefee" onclick="newTab('/admin/agent/agent_fee_list.jsp','代理资费')">查看更多资费信息</span>
            </div>
        </div>

    </div>
    <!-- 资费信息 -->

</div>
<script type="text/javascript">
    layui.use('layer', function(){
        var $ = layui.jquery, layer = layui.layer;
        //触发事件
        var active = {
            notice1: function(){
                layer.msg('<ht:heropageconfigurationtext code="notice_one" defaultValue="请设置信息" />');
            },
            notice2: function(){
                layer.msg('<ht:heropageconfigurationtext code="notice_two" defaultValue="请设置信息" />');
            },
            notice3: function(){
                layer.msg('<ht:heropageconfigurationtext code="notice_three" defaultValue="请设置信息" />');
            },
            notice4: function(){
                layer.msg('<ht:heropageconfigurationtext code="notice_four" defaultValue="请设置信息" />');
            },
            notice5: function(){
                layer.msg('<ht:heropageconfigurationtext code="notice_five" defaultValue="请设置信息" />');
            }
        }
        $('.demo').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });

    function newTab(url,title){
        parent.layui.index.openTabsPage(url,title);
    }
    function popUpTab(url,title){
        layer.open({
            type: 2,
            title: title,
            content: url,
            maxmin: true,
            area: ["70%","80%"],
            btn: ['确定', '取消'],
            yes: function (index, layero) {
                var submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                submit.click();
            }
        });
    }
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#agentFeeTable'
            ,url:'/admin/agent_agentFeeList'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {title: '国家', minWidth: 120, templet: function(d){
                        return handleData(d.country_Number_name);
                    }},
                {title: '运营商', minWidth: 120, templet: function(d){
                        return handleData(d.operator);
                    }},
                {title: '行业', minWidth: 120, templet: function(d){
                        return handleData(d.trade_Type_Code_name);
                    }},
                {title: '消息类型', minWidth: 120, templet: function(d){
                        return handleData(d.message_Type_Code_name);
                    }},
                {field: 'unit_Price', title: '单价(元)', minWidth: 200}
            ]],
            done: function (res) {
                if(res.code!="0") return;
                if(res.data.length>5){
                    $('#showMorefee').show()
                }else{
                    $('#showMorefee').hide()
                }
            }
        });
    });
</script>
</body>
</html>
