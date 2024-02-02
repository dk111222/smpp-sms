<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/money_letter.jsp" %>
<%@ include file="/common/layui_common.jsp"%>

<body>
<form class="layui-form" action="/admin/charge_charge" id="subForm" onsubmit="return ajaxSubmitForm()"
      method="post" style="padding: 20px 30px 0 0;">
    <input hidden name="enterprise_User_Id" value="<c:out value="${enterpriseUser.id}"/>"/>
    <input hidden name="enterprise_No" value="<c:out value="${enterpriseUser.enterprise_No}"/>"/>
    <div class="layui-form-item">
        <label class="layui-form-label">充值账户</label>
        <div class="layui-input-inline">
            <input type="text" name="" value="<c:out value="${enterpriseUser.real_Name}"/>" readonly="true" autocomplete="off" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">账户余额</label>
        <div class="layui-input-inline">
            <input type="text" readonly value="<c:out value="${enterpriseUser.available_Amount}"/>" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">充值金额</label>
        <div class="layui-input-inline">
            <input type="text" maxlength="12" name="money" lay-verify="required|number"
                   onblur="letterValue('money_Letter','money')" alt="(单位:元)"
                   placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">金额大写</label>
        <div class="layui-input-inline">
            <input type="text" name="money_Letter" placeholder="请输入" autocomplete="off" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-inline" style="width: 300px">
            <textarea type="text" maxlength="2048" name="remark" autocomplete="off" class="layui-textarea"></textarea>
        </div>
    </div>
    </div>
    <div class="layui-form-item layui-hide">
        <button class="layui-btn" id="layuiadmin-app-form-submit">提交</button>
    </div>

</form>
<%@ include file="/common/layui_bottom.jsp"%>

<script >
    layui.use(['form'], function () {
        var form = layui.form
        form.render();
    });

    function ajaxSubmitForm() {
        var data = $("#subForm").val();
        var money = $("input[name='money']").val();
        if (!money){
            layer.msg('充值金额不能为空')
            return false;
        }
        var loading= parent.layer.load('', {time: 10*1000}); //遮罩层
        // $("#subForm").ajaxSubmit({
        $.ajax({
            type: 'post', // 提交方式 get/post
            url: '/admin/charge_charge', // 需要提交的 url
            dataType: 'json',
            data: {
                "money":money,
                "enterprise_User_Id":$("input[name='enterprise_User_Id']").val(),
                "enterprise_No":$("input[name='enterprise_No']").val(),
                "money_Letter":$("input[name='money_Letter']").val(),
                "remark":$("textarea[name='remark']").val()
            },
            success: function (d) {
                parent.layer.close(loading);//关闭遮罩层
                if(d.code = '0'){
                    layer.msg(d.msg, {icon: 1, time: 2000}, function () {
                        $('#subForm')[0].reset();
                        var currentIndex = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.close(currentIndex);
                        window.parent.location.reload();//刷新父页面
                    });
                    $('#layuiadmin-app-form-submit').attr('disabled',true);

                }else{
                    layer.error(d.msg, {icon: 2, time: 3000}, function () {
                        var currentIndex = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.close(currentIndex);
                        $('#layuiadmin-app-form-submit').attr('disabled',false);
                    });
                }

            },
            error: function (d) {
                parent.layer.close(loading);//关闭遮罩层
                layer.msg('提交失败', {icon: 2, time: 2000}, function () {
                    var currentIndex = parent.layer.getFrameIndex(window.name); //获取窗口索引
                    parent.layer.close(currentIndex);
                    window.parent.location.reload();//刷新父页面
                });
            }
        })
        return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
    }
</script>
</body>