<%@ taglib prefix="ht" uri="/hero-tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>
        <ht:heropageconfigurationtext code="platform_name" defaultValue=""/>
        代理平台
        <ht:heropageconfigurationtext code="platform_version" defaultValue=""/>
    </title>
    <meta name="description" content="">
    <meta name="viewport" content="width=1200">
    <link href="/public/lanse/css/login.css" type="text/css" rel="stylesheet">
</head>
<body onkeydown="on_return(event)">
<div class="login">
    <div class="message">
        <ht:heropageconfigurationtext code="platform_name" defaultValue=""/>
        代理平台
        <ht:heropageconfigurationtext code="platform_version" defaultValue=""/>
    </div>
    <div id="darkbannerwrap"></div>

    <form action="/admin/agent_Login" method="post" id="dataForm">

        <input type="text" class="input required" placeholder="账号" id="J_username"
               name="user_Name">
        <hr class="hr15">
        <input type="password" class="input required" placeholder="密码" id="J_password"
               name="password">
        <input type="hidden" name="urlFlag" value="/public/lanse/login.jsp">
        <hr class="hr15">

        <input type="text" class="input required" autocomplete="off" placeholder="验证码"
               id="J_captcha" name="validateCode" style="width:80%;" >
        <img class="yzm" src="/Kaptcha.do" id="imgsrc" name="imgsrc"
             onclick="javaScript:change()"/>

        <hr class="hr15">

        <span style="color: red;">${msg}<hr class="hr15"></span>
        <button class="login-btn">登录</button>
    &nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

    </form>

</div>

<div class="copyright">
    <ht:heropageconfigurationtext code="agent_home_information" defaultValue="请设置信息"/>
</div>
<script type="text/javascript">

    function change() {
        document.getElementById("imgsrc").setAttribute("src", "/Kaptcha.do?" + Math.floor(Math.random() * 100));
    }
    function on_return() {
        if (window.event.keyCode == 13) {
            $("form").submit();
        }
    }

</script>

</body>
</html>
