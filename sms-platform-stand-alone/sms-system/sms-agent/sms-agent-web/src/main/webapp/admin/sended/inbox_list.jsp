<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/layui_common.jsp" %>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <form class="layui-form layui-card-header layuiadmin-card-header-auto" onsubmit="return false;">
                    <div class="layui-inline">
                        &nbsp;&nbsp;企业名称&nbsp;
                        <div class="layui-input-inline">
                           <ht:heroenterpriseselect  agentNo="${sessionScope.AGENT_INFO.no}" name="enterprise_No" />
                        </div>
                    </div>&nbsp;&nbsp;
                    <div class="layui-inline">
                        &nbsp;&nbsp;企业用户&nbsp;
                        <div class="layui-inline">
                            <ht:herocustomdataselect param="${sessionScope.AGENT_INFO.no}" paramType="agentNo" dataSourceType="allEnterpriseUsers" name="enterprise_User_Id" />
                        </div>
                    </div>
                    <div class="layui-inline">
                        &nbsp;&nbsp;手机号码&nbsp;
                        <div class="layui-input-inline">
                            <input name="phone_No" class="layui-input"/>
                        </div>
                    </div>&nbsp;&nbsp;
                    <div class="layui-inline">
                        &nbsp;&nbsp;内容&nbsp;
                        <div class="layui-input-inline">
                            <input name="content" class="layui-input"/>
                        </div>
                    </div>&nbsp;&nbsp;
                    <div class="layui-inline">
                        &nbsp;&nbsp;创建时间&nbsp;
                        <div class="layui-inline">
                            <input name="minSubmitDate" id="minSubmitDate" class="layui-input layui-input-sm"/>
                        </div>
                        <div class="layui-inline">--</div>
                        <div class="layui-inline">
                            <input name="maxSubmitDate" id="maxSubmitDate" class="layui-input layui-input-sm"/>
                        </div>
                    </div>&nbsp;&nbsp;
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
    layui.extend({tableExt: '/js/layui-ext/tableExt'}).use(['tableExt','laydate'], function () {
        var table = layui.tableExt;
        var $ = layui.$;
        var laydate = layui.laydate;
        var today = new Date();
        laydate.render({     //创建时间选择框
            elem: '#minSubmitDate' //指定元素
            ,type:'datetime'
            ,trigger : 'click'
            ,value: new Date(today.getFullYear(),today.getMonth(),today.getDate())
        });
        laydate.render({     //创建时间选择框
            elem: '#maxSubmitDate' //指定元素
            ,type:'datetime'
            ,trigger : 'click'
            ,value: new Date(today.getFullYear(),today.getMonth(),today.getDate(),23,59,59)
        });
        table.render({
            url: '/admin/sended_inboxList',
            height: 'full-160',
            extField: 'input_Content',
            even: false,
            where:{
                'minSubmitDate':$("#minSubmitDate").val(),
                'maxSubmitDate':$("#maxSubmitDate").val()
            },
            cols: [[
                {checkbox: true},
                {title: '企业名称/企业用户', width:180,templet:function (d) {
                    return !d.enterprise_No_ext?'---':handleData(d.enterprise_No_ext.name)
                        +"<br>"+handleData(d.enterprise_User_Id_ext.real_Name)
                        +"("+handleData(d.enterprise_User_Id_ext.user_Name)+")";
                }},
                {field: 'sp_Number', title: '上行码号', width: 120},
                {field: 'phone_No', title: '手机号码', width: 120},
                {field: 'content', title: '上行内容', minWidth:300},
                {field: 'create_Date', title: '创建时间', width: 170},
            ]]
        });
    });

</script>