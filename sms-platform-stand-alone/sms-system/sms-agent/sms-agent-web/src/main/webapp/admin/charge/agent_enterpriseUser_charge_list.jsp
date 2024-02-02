<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/layui_common.jsp" %>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <form id="layuiForm" class="layui-form layui-card-header layuiadmin-card-header-auto" onsubmit="return false;">
                    <div class="layui-inline">
                        &nbsp;&nbsp;企业名称&nbsp;
                        <div class="layui-input-inline">
                            <ht:heroenterpriseselect  agentNo="${sessionScope.AGENT_INFO.no}" name="enterprise_No" />
                        </div>
                    </div>&nbsp;
                    <div class="layui-inline">
                        &nbsp;&nbsp;企业用户&nbsp;
                        <div class="layui-inline">
                            <ht:herocustomdataselect param="${sessionScope.AGENT_INFO.no}" paramType="agentNo" dataSourceType="allEnterpriseUsers" name="enterprise_User_Id" />
                        </div>
                    </div>
                    <div class="layui-inline">
                        充值时间&nbsp;
                        <div class="layui-input-inline">
                            <input name="min_Create_Date" id="minCreateDate" class="layui-input layui-input-sm" size="15"/>
                        </div>
                        <div class="layui-inline">--</div>
                        <div class="layui-input-inline">
                            <input name="max_Create_Date" id="maxCreateDate" class="layui-input layui-input-sm" size="15"/>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn layui-btn-sm" type="submit" lay-submit="" lay-filter="reload">搜索
                        </button>
                    </div>
                </form>
                <div class="layui-form layui-border-box layui-table-view">
                    <div class="layui-card-body">
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
    layui.extend({tableExt: '/js/layui-ext/tableExt'}).use(['tableExt','laydate'], function () {
        var table = layui.tableExt;
        $ = layui.$;
        table.render({
            url: '/admin/charge_agentEnterpriseUserChargeList',
            cols: [[
                {checkbox: true},
                {field: 'enterprise_No_ext', title: '企业名称/企业编号',templet:function (d) {
                        return handleData(d.enterprise_No_ext.name)
                            + '<br>' +handleData(d.enterprise_No);
                    }
                },
                {field: 'enterprise_User_Id_ext', title: '用户',templet:function (d) {
                        return handleData(d.enterprise_User_Id_ext.real_Name);
                    }
                },
                {field: 'before_Money', title: '期初金额'},
                {field: 'money', title: '充值金额'},
                {field: 'money_Letter', title: '金额大写'},
                // {field: 'after_Money', title: '充值后金额【￥】'},
                {field: 'remark', title: '备注'},
                {field: 'create_Date', title: '充值时间'},
            ]]
        });
    });
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        var today = new Date();
        laydate.render({     //创建时间选择框
            elem: '#minCreateDate' //指定元素
            ,type:'datetime'
            ,trigger : 'click'
            ,value: new Date(today.getFullYear(),today.getMonth(),today.getDate())
        });
        laydate.render({     //创建时间选择框
            elem: '#maxCreateDate' //指定元素
            ,type:'datetime'
            ,trigger : 'click'
            ,value: new Date(today.getFullYear(),today.getMonth(),today.getDate(),23,59,59)
        });
    });
    function getFormData() {
        return $("#layuiForm").serialize();
    }
</script>