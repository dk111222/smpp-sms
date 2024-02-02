<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/layui_common.jsp" %>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-form layui-border-box layui-table-view">
                    <div class="layui-card-body">

                        <form id="layuiForm"  class="layui-form layui-card-header layuiadmin-card-header-auto" id="layuiForm" onsubmit="return false">
                            <div class="layui-inline">
                                &nbsp;&nbsp;充值时间&nbsp;
                                <div class="layui-inline" style="width: 200px">
                                    <input type="text" class="layui-input" id="min" placeholder="yyyy-MM-dd"
                                           name="min_Create_Date">
                                </div>-
                                <div class="layui-inline" style="width: 200px">
                                    <input type="text" class="layui-input" id="max" placeholder="yyyy-MM-dd"
                                           name="max_Create_Date">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button class="layui-btn layui-btn-sm" type="submit" lay-submit="" lay-filter="reload">搜索
                                </button>
                            </div>
                        </form>
                        <table class="layui-hide" id="list_table" lay-filter="list_table"></table>
                        <script type="text/html" id="table-toolbar">
                            <div class="layui-btn-container">
                                <%@include file="/common/button_action_list.jsp" %>
                            </div>
                        </script>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script>
    var $;
    layui.extend({tableExt: '/js/layui-ext/tableExt'}).use(['tableExt', 'laydate'], function () {
        var table = layui.tableExt;
        $ = layui.$;
        var laydate = layui.laydate;
        var nowTime=new Date();
        laydate.render({
            elem: '#min'
            , format: "yyyy-MM-dd"
            , max:'nowTime' //默认最大值为当前日期
        });
        laydate.render({
            elem: '#max'
            , format: "yyyy-MM-dd"
            , max:'nowTime' //默认最大值为当前日期
        });
        table.render({
            url: '/admin/charge_agentChargeOrderList',
            cols: [[
                {checkbox: true},
                {field: 'agent_No_ext', title: '代理名称',minWidth: 180, width:180,templet:function (d) {
                        return !d.agent_No_ext?'---':handleData(d.agent_No_ext.name);
                    }},
                {field: 'before_Money', title: '期初金额'},
                {field: 'money', title: '充值金额'},
                {field: 'money_Letter', title: '金额大写'},
                // {field: 'after_Money', title: '充值后金额【￥】'},
                {field: 'remark', title: '备注'},
                {field: 'create_Date', title: '时间', width: 200,sort: true},
            ]]
        });
    });

    function getFormData() {
        return $("#layuiForm").serialize();
    }
</script>