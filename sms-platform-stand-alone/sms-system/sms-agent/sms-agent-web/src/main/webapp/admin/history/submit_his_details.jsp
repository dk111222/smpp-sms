<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/layui_common.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8">
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-control" content="no-cache">
    <meta http-equiv="Cache" content="no-cache">
</head>
<body>

<div style="padding: 5px 20px 5px 20px;">
    <div class="layui-card" style="padding: 10px 20px 10px 20px;">
        <fieldset class="layui-elem-field">
            <legend>提交信息</legend>
            <table class="layui-table" lay-filter="table">
                <thead>
                <tr>
                    <td width="30%">企业用户</td>
                    <td width="30%">手机号码</td>
                    <td width="30%">运营商</td>
                </tr>
                </thead>
                <tbody>
                <td><c:out value="${details.enterpriseUserName}"/></td>
                <td><c:out value="${details.submit.phone_No}"/></td>
                <td>
                    <c:out value="${details.countryName}"/>/<c:out value="${details.submit.operator}"/><br/>
                    <c:out value="${empty details.submit.area_Name?'---':details.submit.area_Name}"/><br/>
                    <c:out value="${empty details.submit.SP_Number?'---':details.submit.SP_Number}"/>
                </td>
                </tbody>
            </table>
            <table class="layui-table" lay-filter="table">
                <thead>
                <tr>
                    <td width="30%">短信属性</td>
                    <td width="30%">批次号</td>
                    <td width="30%">提交状态</td>
                </tr>
                </thead>
                <tbody>
                <td>
                    <c:out value="${details.messageTypeName}"/>
                    <c:out value="${details.submit.content_Length}字"/>
                    <c:if test="${details.isLMSName == '否'}">
                        短消息 <c:out value="${details.submit.sequence}"/>条
                    </c:if>
                    <c:if test="${details.isLMSName == '是'}">
                        长消息 第<c:out value="${details.submit.sequence}"/>条
                    </c:if>
                </td>
                <td><c:out value="${details.submit.msg_Batch_No}"/></td>
                <td>
                    <c:out value="${details.submiteStatusName}"/>(<c:out value="${details.submit.submit_Description}"/>)/<c:out value="${empty details.submitResponseTime?0:details.submitResponseTime}"/>ms<br/>
                    <fmt:formatDate value="${details.submit.submit_Date}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                </tbody>
            </table>
        </fieldset>
    </div>
</div>


<div style="padding: 5px 20px 5px 20px;">
    <div class="layui-card" style="padding: 10px 20px 10px 20px;">
        <fieldset class="layui-elem-field">
            <legend>回执信息</legend>
            <table class="layui-table" lay-filter="table">
                <thead>
                <tr>
                    <td width="30%">回执时间</td>
                    <td width="30%">回执状态</td>
                    <td width="30%">回执耗时</td>
                </tr>
                </thead>
                <tbody>
                <c:if test="${empty details.reportStatusInfo}">
                    <tr><td colspan="3" align="center">无数据</td></tr>
                </c:if>
                <c:forEach var="item" items="${details.reportStatusInfo}">
                    <tr>
                        <td><c:out value="${item.statusDate}"/></td>
                        <td><c:out value="${item.reportStatusName}"/></td>
                        <td><c:out value="${item.costTime}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </fieldset>
    </div>
</div>

</body>
