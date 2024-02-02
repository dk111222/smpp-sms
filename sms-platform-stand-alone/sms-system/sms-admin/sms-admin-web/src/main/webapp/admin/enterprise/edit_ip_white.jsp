<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/admin/common/common.jsp" %>
<%@ include file="/admin/common/layui_head.html"%>
<body>
<form class="layui-form" id="layui-form" action="/admin/enterprise_editUser" lay-filter="form" onsubmit="return false;"
	  style="padding: 20px 30px 0 0;">
	<input hidden name="id" value="<c:out value="${enterpriseUser.id}"/>"/>
	<div class="layui-form-item">
		<div class="layui-form-item">
			<label class="layui-form-label">用户名称<font color="red">&nbsp;&nbsp;*</font></label>
			<div class="layui-input-block" >
				<input type="text"  value="<c:out value="${enterpriseUser.real_Name}"/>"  disabled="disabled" class="layui-input" >
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">TCP发送速度</label>
			<div class="layui-input-inline" style="width: 200px;">
				<input type="text" name="tcp_Submit_Speed" autocomplete="off"  value="<c:out value="${enterpriseUser.tcp_Submit_Speed}"/>" class="layui-input">
			</div>
			<label class="layui-form-label">TCP连接数</label>
			<div class="layui-input-inline"  style="width: 200px">
				<input type="text" name="tcp_Max_Channel" autocomplete="off"  value="<c:out value="${enterpriseUser.tcp_Max_Channel}"/>" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">TCP密码</label>
			<div class="layui-input-block" >
				<input type="text" name="tcp_Password" autocomplete="off"  value="<c:out value="${enterpriseUser.tcp_Password}"/>" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">HTTP签名密钥</label>
			<div class="layui-input-block" >
				<input type="text" name="http_Sign_Key" autocomplete="off"  value="<c:out value="${enterpriseUser.http_Sign_Key}"/>" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">Ip白名单</label>
			<div class="layui-input-block">
				<textarea type="text" name="api_Ip" autocomplete="off" class="layui-textarea">${enterpriseUser.api_Ip}</textarea>
				多个ip地址用","号分割。
			</div>
		</div>
	</div>
	<div class="layui-form-item layui-hide">
		<input type="button" lay-submit lay-filter="submit" id="layuiadmin-app-form-submit" value="确认">
	</div>
</form>
<%@ include file="/admin/common/layui_bottom.jsp"%>
</body>
<script type="text/javascript">
	layui.use('form', function(){
		var form = layui.form;
		form.on('submit(submit)', function(data){
			//连接数
			var max_Concurrent_Total = parseInt($("input[name='tcp_Max_Channel']").val());
			//提交速度
			var submit_Speed = parseInt($("input[name='tcp_Submit_Speed']").val());
			if(max_Concurrent_Total > submit_Speed){
				layer.alert('连接数大于提交速度，请修改', {
					btn: ['关闭'],
					icon: 2,
					skin: 'layer-ext-moon'
				})
				return false;
			}
		});
	});

	$(function(){
		$("#layui-form").ajaxForm(function(data){
			if (data.code != 0) {
				layer.alert(data.msg, {icon: 2}, function () {
					layer.closeAll();
				});
				return;
			}
			layer.alert(data.msg, {icon: 1}, function (index) {
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			});
		});
	});
</script>
