<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/admin/common/common.jsp" %>
<%@ include file="/admin/common/layui_head.html" %>
<body>
<form class="layui-form" action="/admin/agent_editAgent" lay-filter="form" onsubmit="return false;"
      style="padding: 20px 30px 0 0;">
    <input type="hidden" name="id" value="<c:out value="${agent.id}"/>"/>
    <div class="layui-form-item">
        <label class="layui-form-label">代理名称<font color="red">&nbsp;&nbsp;*</font></label>
        <div class="layui-input-inline">
            <input type="text" maxlength="128" name="name" lay-verify="required" placeholder="请输入代理名称" autocomplete="off"
                   value="<c:out value="${agent.name}"/>" class="layui-input">
        </div>
        <label class="layui-form-label">登录账号<font color="red">&nbsp;&nbsp;*</font></label>
        <div class="layui-input-inline">
            <input type="text" maxlength="128" name="user_Name" lay-verify="required|userName" placeholder="请输入登录账号" autocomplete="off"
                   value="<c:out value="${agent.user_Name}"/>" class="layui-input" readonly>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-inline">
            <input type="password"  maxlength="12" name="password" lay-verify="editPassword" placeholder="建议:修改后及时更新密码"  autocomplete="off"
                  class="layui-input">
        </div>
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-inline">
            <input type="password"  maxlength="12" name="confirmPassword" lay-verify="editPassword" placeholder="建议:修改后及时更新密码"  autocomplete="off"
                class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">类型</label>
        <div class="layui-input-inline" >
            <ht:herocodeselect sortCode="agent_type" valueField="Value" selected="${agent.type_Code}"   name="type_Code"/>
        </div>
        <label class="layui-form-label">编码</label>
        <div class="layui-input-inline">
            <input type="text" name="no"  placeholder="请输入代码" autocomplete="off"
                   value="<c:out value="${agent.no}"/>" class="layui-input" readonly>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea type="text" maxlength="2048" name="remark" autocomplete="off" class="layui-textarea">${agent.remark}</textarea>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="submit" lay-submit lay-filter="submit" id="layuiadmin-app-form-submit" value="确认">
    </div>
</form>
<%@ include file="/admin/common/layui_bottom.jsp" %>
</body>