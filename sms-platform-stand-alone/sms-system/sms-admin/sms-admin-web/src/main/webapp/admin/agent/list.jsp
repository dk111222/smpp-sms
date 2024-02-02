<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/admin/common/common.jsp" %>
<%@ include file="/admin/common/layui_head.html" %>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <form id="layuiForm" class="layui-form layui-card-header layuiadmin-card-header-auto" onsubmit="return false;">
                    <table>
                        <tr>
                            <td class="layui-inline">
                                代理商名称：
                                <td class="layui-inline" style="width: 200px">
                                    <ht:herocustomdataselect dataSourceType="allAgent" name="no"></ht:herocustomdataselect>
                                </td>
                            </td>
                            <td class="layui-inline">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td class="layui-inline">
                                可用金额：
                                <td class="layui-inline">
                                    <input type="number" step="0.0001" min="-999999999.9999" max="999999999.9999" name="min_Available_Money" class="layui-input layui-input-sm" size="5" />
                                </td>
                                <td class="layui-inline">--</td>
                                <td class="layui-inline">
                                    <input type="number" step="0.0001" min="-999999999.9999" max="999999999.9999" name="max_Available_Money" class="layui-input layui-input-sm" size="5"/>
                                </td>
                            </td>
                            <td class="layui-inline">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td class="layui-inline">
                                状态：
                                <td class="layui-inline" style="width: 100px">
                                    <ht:herocodeselect sortCode="004" name="status"/>
                                </td>
                            </td>
                            <td class="layui-inline">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td class="layui-inline">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td class="layui-inline">
                                <button class="layui-btn layui-btn-sm" type="submit" lay-submit="" lay-filter="reload">搜索
                                </button>
                            </td>
                        </tr>
                    </table>
                </form>
                <div class="layui-form layui-border-box layui-table-view">
                    <div class="layui-card-body">
                        <table class="layui-hide" id="list_table" lay-filter="list_table"></table>
                        <script type="text/html" id="table-toolbar">
                            <div class="layui-btn-container">
                                <%@include file="/admin/common/button_action_list.jsp" %>
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
    layui.extend({tableExt: '/layuiadmin/extends/tableExt'}).use(['tableExt'], function () {
        var table = layui.tableExt;
        $ = layui.$;
        table.render({
            url: '/admin/agent_list'
            ,cols: [[
                  {field: 'id', title: 'ID', width: 50, sort: true, fixed: 'left', type: 'checkbox'}
                , {field: 'name', title: '代理名称', width: 190}
                , {field: 'user_Name', title: '登录账号', width: 150}
                , {field: 'no', title: '编号', width: 180}
                , {field: 'status_name', title: '状态', width: 100}
                , {title: '余额', width:180,templet:function (d) {
                        return "¥ "+handleData(d.available_Amount);
                    }}
                , {title: '已消费', width:180,templet:function (d) {
                        return "¥ "+handleData(d.used_Amount) + "=" + handleData(d.sent_Count) +"条";
                    }}
                , {field: 'create_Date', title: '创建时间', minWidth: 165}
            ]]
        });
    });

</script>