<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp" %>
<c:forEach items="${sessionScope.AGENT_INFO.limitOrderIds}" var="limitOrderId">
    <c:if test="${limitOrderId.type_Code == 'showbutton' && limitOrderId.up_Code == param.limitCode}">
        ${limitOrderId.button_Action}
    </c:if>
</c:forEach>
