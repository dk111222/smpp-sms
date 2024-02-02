<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/admin/common/common.jsp" %>
<%@ include file="/admin/common/layui_head.html" %>
<body>
<form class="layui-form" id="layui-form" action="/admin/enterprise_editUser" lay-filter="form" onsubmit="return false;"
      style="padding: 20px 30px 0 0;">
    <input hidden name="id" value="<c:out value="${enterpriseUser.id}"/>"/>
    <div class="layui-form-item">
        <label class="layui-form-label">用户名称<font color="red">&nbsp;&nbsp;*</font></label>
        <div class="layui-input-inline" style="width: 400px">
            <input type="text"  value="<c:out value="${enterpriseUser.real_Name}"/>"  disabled="disabled" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">查询报告</label>
        <div class="layui-input-inline">
            <ht:herocodeselect sortCode="006" selected="${enterpriseUser.is_Query_Report==null?enterpriseUser.is_Query_Report==true:enterpriseUser.is_Query_Report}" name="is_Query_Report"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">推送报告</label>
        <div class="layui-input-inline">
            <ht:herocodeselect sortCode="006" selected="${enterpriseUser.is_Notify_Report==null?enterpriseUser.is_Notify_Report==true:enterpriseUser.is_Notify_Report}" name="is_Notify_Report"/>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="submit" id="layuiadmin-app-form-submit" value="确认">
    </div>
</form>
<%@ include file="/admin/common/layui_bottom.jsp" %>
</body>