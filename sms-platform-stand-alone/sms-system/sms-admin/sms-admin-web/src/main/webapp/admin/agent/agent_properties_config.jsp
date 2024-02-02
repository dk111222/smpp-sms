<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/admin/common/common.jsp" %>
<%@ include file="/admin/common/layui_head.html" %>
<body>
<form class="layui-form" action="/admin/agent_addAgentProperties" onsubmit="return false;"
      style="padding: 20px 30px 0 0;">
    <input type="text" name="id" value="<c:out value="${agentId}"/>" hidden/>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">预充值倍数</label>
            <div class="layui-input-inline">
                <input type="text" name="agentChargeMultiple" lay-verify="required|number" placeholder="1.5"
                       autocomplete="off" class="layui-input" value="<c:out value="${agentChargeMultiple}"/>">
            </div>
        </div>
    </div>
    <div class="layui-form-item" hidden>
        <input type="submit" lay-submit lay-filter="submit" id="layuiadmin-app-form-submit" value="提交">
    </div>
</form>
<%@ include file="/admin/common/layui_bottom.jsp" %>
</body>
