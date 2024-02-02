<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/admin/common/common.jsp" %>
<%@ include file="/admin/common/layui_head.html" %>
<body>
<form class="layui-form" id="layui-form" action="/admin/platform_edit" lay-filter="form" onsubmit="return false;"
      style="padding: 20px 30px 0 0;">
    <input hidden name="id" value="<c:out value="${platform.id}"/>"/>
    <div class="layui-form-item">
        <label class="layui-form-label">企业编码<font color="red">&nbsp;&nbsp;*</font></label>
        <div class="layui-input-block"  style="width: 70%">
            <input type="text" name="platform_No" value="<c:out value="${platform.platform_No}"/>" autocomplete="off"  class="layui-input layui-disabled" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">平台名称 <font color="red">*</font></label>
        <div class="layui-input-inline">
            <input type="text" maxlength="128" name="platform_Name" value="<c:out value="${platform.platform_Name}"/>" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">秘钥<font color="red">&nbsp;&nbsp;*</font></label>
        <div class="layui-input-block"  style="width: 70%">
            <input type="text" name="sign_Key" value="<c:out value="${platform.sign_Key}"/>" autocomplete="off"  class="layui-input layui-disabled" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否统计</label>
        <div class="layui-input-inline">
            <ht:herocodeselect sortCode="006" selected="${platform.statistics_Status==null?ture:platform.statistics_Status}" name="statistics_Status"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea type="text" maxlength="2048" name="remark" autocomplete="off" class="layui-textarea">${platform.remark}</textarea>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="submit" id="layuiadmin-app-form-submit" value="确认">
    </div>
</form>
<%@ include file="/admin/common/layui_bottom.jsp" %>
</body>