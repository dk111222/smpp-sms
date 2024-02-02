<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/common.jsp" %>
<%@ include file="/admin/common/money_letter.jsp" %>
<%@ include file="/admin/common/layui_head.html"%>

<body>
<form class="layui-form" action="/admin/agent_charge" id="subForm" onsubmit="return ajaxSubmitForm()"
      enctype="multipart/form-data" method="post" style="padding: 50px 0px 0px 50px;">
    <input hidden name="agent_No" value="<c:out value="${agent.no}"/>"/>
    <div class="layui-form-item" >
        <label class="layui-form-label">代理商名称</label>
        <div class="layui-input-inline" style="width: 300px">
            <input type="text" name="" value="<c:out value="${agent.name}"/>" readonly="true" autocomplete="off" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">充值金额</label>
        <div class="layui-input-inline" style="width: 300px">
            <input type="text" name="money"
                   onblur="letterValue('money_Letter','money')" alt="(单位:元)"
                   placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">金额大写</label>
        <div class="layui-input-inline" style="width: 300px">
            <input type="text" name="money_Letter" placeholder="请输入" autocomplete="off" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-inline" style="width: 300px">
            <textarea type="text" maxlength="2048" name="remark" autocomplete="off" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <button class="layui-btn" id="layuiadmin-app-form-submit">提交</button>
    </div>

    </form>
<%@ include file="/admin/common/layui_bottom.jsp"%>

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
        var loading= parent.layer.load('', {time: 10*1000});
        $("#subForm").ajaxSubmit({
            type: 'post', // 提交方式 get/post
            url: '/admin/agent_charge', // 需要提交的 url
            dataType: 'json',
            data: data,
            success: function (d) { // data 保存提交后返回的数据，一般为 json 数据
                // 此处可对 data 作相关处理
                layer.msg('提交成功', {icon: 1, time: 2000}, function () {
                    $('#subForm')[0].reset();
                    parent.layer.closeAll();
                });
                $('#layuiadmin-app-form-submit').attr('disabled',true);
                parent.layer.close(loading);
            },
            error: function (d) {
                layer.msg('提交失败', {icon: 2, time: 2000}, function () {
                    $('#layuiadmin-app-form-submit').attr('disabled',false);
                });
            }
        })
        return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
    }
</script>
</body>