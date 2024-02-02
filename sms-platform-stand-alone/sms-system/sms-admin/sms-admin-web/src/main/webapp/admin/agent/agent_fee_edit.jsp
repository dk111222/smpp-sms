<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/admin/common/common.jsp" %>
<%@ include file="/admin/common/layui_head.html" %>
<%@ include file="/admin/common/country_operator.jsp" %>
<script src="/js/jquery-3.4.1.min.js"></script>

<body>
<form class="layui-form" action="/admin/agent_editAgentFee" lay-filter="form" onsubmit="return false;"
      style="padding: 20px 30px 0 0;">
    <input name="id" value="<c:out value="${agentFee.id}"/>"  type="hidden" />
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 20%">国家<font color="red">&nbsp;&nbsp;*</font></label>
        <div class="layui-input-inline" style="width: 60%">
            <ht:herocodeselect sortCode="country" name="country_Number" id="country_Number" selected="${agentFee.country_Number}" valueField="Value" layVerify="required"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 20%">运营商<font color="red">&nbsp;&nbsp;*</font></label>
        <div class="layui-input-inline" style="width: 60%">
            <ht:countryoperatorselect id="operator" layVerify="required" name="operator"
                                      countryNumber="${agentFee.country_Number}"
                                      selected="${agentFee.operator}" />
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 20%">单价(元)<font color="red">&nbsp;&nbsp;*</font></label>
        <div class="layui-input-inline" style="width: 60%">
            <input name="unit_Price" type="number" step="0.0001" min="0" max="99999999.9999"  oninput="value=value.replace('-')" layVerify="required" value="<c:out value="${agentFee.unit_Price}"/>" class="layui-input layui-input-sm"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 20%">行业<font color="red">&nbsp;&nbsp;*</font></label>
        <div class="layui-input-inline" style="width: 60%">
            <ht:herocodeselect sortCode="trade" selected="${agentFee.trade_Type_Code}" layVerify="required" name="trade_Type_Code"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 20%">消息类型<font color="red">&nbsp;&nbsp;*</font></label>
        <div class="layui-input-inline" style="width: 60%">
            <ht:herocodeselect sortCode="message_type_code" selected="${agentFee.message_Type_Code}" layVerify="required" name="message_Type_Code"/>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="submit" lay-submit lay-filter="submit" id="layuiadmin-app-form-submit" value="确认">
    </div>
</form>
<%@ include file="/admin/common/layui_bottom.jsp" %>
</body>
