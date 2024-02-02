<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ht" uri="/hero-tags" %>
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
    <link rel="stylesheet" href="/public/admin/css/reset.css" />
    <link rel="stylesheet" href="/public/admin/css/login.css" />
    <script src="/js/jquery-3.4.1.min.js"></script>
    <script src="/js/jquery-validation-1.19.0/dist/jquery.validate.js"></script>
    <script src="/js/jquery-validation-1.19.0/dist/localization/messages_zh.js"></script>
    <script src="/public/admin/js/three.min.js"></script>
    <script src="/public/admin/js/d3.js"></script>
    <script src="/js/jsencrypt/jsencrypt.min.js"></script>
    <script src="/js/jsencrypt/rsakey.js"></script>
</head>
<body>
<div id="page">
    <div class="container">
        <div class="login-area no-compatibility">
            <div class="login-box">
                <div class="box">
                    <form action="${pageContext.request.contextPath}/admin/agent_Login" method="post" id="dataForm" onsubmit="return encryptPassword();">
                        <div class="show-login">
                            <div class="title"><ht:heropageconfigurationtext code="platform_name" defaultValue="" />代理平台</div>
                            <div class="vision">
                                <ht:heropageconfigurationtext code="platform_version" defaultValue="V1.0.0" />
                            </div>
                            <div class="input-box">
                                <div class="input-left">
                                    <i class="icon icon-user"></i>
                                </div>
                                <label for="J_username">
                                    <input type="text" class="input required" placeholder="账号" id="J_username"
                                           name="user_Name" />
                                </label>
                            </div>
                            <div class="input-box">
                                <div class="input-left">
                                    <i class="icon icon-password"></i>
                                </div>
                                <label for="J_password">
                                    <input type="password" class="input required" placeholder="密码" id="J_password"
                                           name="password">
                                </label>
                                <div class="input-right" id="J_changeShow">
                                    <i class="icon icon-eye-open icon-eye-close"></i>
                                </div>
                            </div>
                            <div class="input-box">
                                <div class="input-left">
                                    <i class="icon icon-safe"></i>
                                </div>
                                <label for="J_captcha">
                                    <input type="text" class="input required" autocomplete="off" placeholder="验证码"
                                           id="J_captcha" name="validateCode" >
                                </label>
                                <div class="input-right">
                                    <img class="yzm" src="${pageContext.request.contextPath}/Kaptcha.do" id="imgsrc" onclick="change()" alt="验证码"/>
                                </div>
                            </div>
                            <div class="errorMessage">
                                <span id="error"><c:out value="${msg}" /></span>
                            </div>
                            <button class="login-btn">登录</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="copy-right">
                <ht:heropageconfigurationtext code="agent_home_information" defaultValue="请设置信息"/>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    //避免登录页被嵌套
    $(function( ){
        if (top.location.href != location.href){
            top.location.href = location.href;
        }
    });
    function change() {
        document.getElementById("imgsrc").setAttribute("src", "/Kaptcha.do?" + Math.floor(Math.random() * 100));
    }

    function encryptPassword() {
        var encrypt = new JSEncrypt();
        encrypt.setPublicKey(rsaPublicKey);
        var encrypted = encrypt.encrypt($("#J_password").val());
        $("#J_password").val(encrypted);
        return true;
    }

    /*Deprecated*/
    function on_return() {
        if (window.event.keyCode == 13) {
            $("form").submit();
        }
    }
</script>
</body>
</html>