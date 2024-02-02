<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/admin/common/common.jsp" %>
<%@ include file="/admin/common/layui_head.html" %>
<body>
<form class="layui-form" action="/admin/agent_save" lay-filter="form" onsubmit="return false;"
      style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
        <label class="layui-form-label">代理名称<font color="red">&nbsp;&nbsp;*</font></label>
        <div class="layui-input-inline">
            <input type="text" maxlength="128" name="name" lay-verify="required" placeholder="请输入代理名称" autocomplete="off"
                   class="layui-input">
        </div>
        <label class="layui-form-label">登录名<font color="red">&nbsp;&nbsp;*</font></label>
        <div class="layui-input-inline">
            <input type="text" maxlength="128" name="user_Name" lay-verify="required|userName"  placeholder="请输入登录名" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">登录密码<font color="red">&nbsp;&nbsp;*</font></label>
        <div class="layui-input-inline">
            <input type="password" id="password" name="password" lay-verify="required|password" placeholder="请输入登录密码"
                   autocomplete="off"
                   class="layui-input">
        </div>
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-inline">
            <input type="password" name="loginPassword" lay-verify="required|confirmPassword" placeholder="请输入确认密码"
                   autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-inline" >
            <ht:herocodeselect sortCode="agent_type" valueField="Value" selected="1"  name="type_Code"/>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea type="text" maxlength="2048" name="remark" autocomplete="off" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="submit" id="layuiadmin-app-form-submit" value="确认">
    </div>
</form>
<%@ include file="/admin/common/layui_bottom.jsp" %>
</body>
