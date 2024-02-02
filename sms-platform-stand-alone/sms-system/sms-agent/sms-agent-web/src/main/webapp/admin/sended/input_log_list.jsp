<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/layui_common.jsp" %>
<script src="/js/jquery-3.4.1.min.js"></script>
<body>
<div class="layui-fluid">
	<div class="layui-col-md12">
		<div class="layui-card">
			<form class="layui-form layui-card-header layuiadmin-card-header-auto" id="layuiForm"
				  onsubmit="return false;">
				<div class="layui-input-item">
					<div class="layui-input-item">
						<div class="layui-inline">
							&nbsp;&nbsp;企业名称&nbsp;
							<div class="layui-inline" style="width: 70%">
								<ht:heroenterpriseselect agentNo="${sessionScope.AGENT_INFO.no}" name="enterprise_No" />
							</div>
						</div>
						<div class="layui-inline">
							&nbsp;&nbsp;企业用户&nbsp;
							<div class="layui-inline" style="width: 70%">
								<ht:herocustomdataselect param="${sessionScope.AGENT_INFO.no}" paramType="agentNo"
														 dataSourceType="allEnterpriseUsers" name="enterprise_User_Id" />
							</div>
						</div>
						<div class="layui-inline">
							&nbsp;&nbsp;消息类型&nbsp;
							<div class="layui-inline">
								<ht:herocodeselect sortCode="message_type_code"  name="message_Type_Code"/>
							</div>
						</div>
						<div class="layui-inline">&nbsp;&nbsp;手机号码&nbsp;&nbsp;&nbsp;</div>
						<div class="layui-inline">
							<input name="phone_Nos" class="layui-input"
								   value="<c:out value="${inputLogExt.phone_Nos}"/>" />
						</div>
						<div class="layui-inline">&nbsp;&nbsp;批次号&nbsp;&nbsp;&nbsp;</div>
						<div class="layui-inline">
							<input name="msg_Batch_No" class="layui-input"  />
						</div>
						<div class="layui-inline">&nbsp;&nbsp;内容&nbsp;&nbsp;&nbsp;</div>
						<div class="layui-inline">
							<input name="content" class="layui-input" value="<c:out value="${inputLogExt.content}"/>" />
						</div>
						<div class="layui-inline">&nbsp;&nbsp;提交时间&nbsp;</div>
						<div class="layui-inline">
							<input name='minCreateDate' class="layui-input layui-input-sm" id="minCreateDate"
								   value="<c:out value="${conditionMap['minCreateDate']}"></c:out>">
						</div>
						<div class="layui-inline">-</div>
						<div class="layui-inline">
							<input name='maxCreateDate' class="layui-input layui-input-sm" id="maxCreateDate"
								   value="<c:out value="${conditionMap['maxCreateDate']}"></c:out>">
						</div>
						<div class="layui-inline">
							<button class="layui-btn layui-btn-sm" type="submit" lay-submit="" lay-filter="reload">
								搜索</button>
						</div>
					</div>
				</div>
			</form>
			<div class="layui-form layui-border-box layui-table-view">
				<div class="layui-card-body">
					<%--						<blockquote class="layui-elem-quote" id="statistics" style="padding: 10px;"></blockquote>--%>
					<table class="layui-hide" id="list_table" lay-filter="list_table"></table>
					<script type="text/html" id="table-toolbar">
						<div class="layui-btn-container">
							<%@include file="/common/button_action_list.jsp" %>
						</div>
					</script>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script>
	var $;
	var params = getRequestParams();
	var minCreateDate = params['minCreateDate'];
	var maxCreateDate = params['maxCreateDate'];
	var msg_Batch_No = params['msg_Batch_No'];
	$("#msg_Batch_No").val(msg_Batch_No);
	minCreateDate = minCreateDate?minCreateDate:'<c:out value="${minCreateDate}"/>';

	layui.extend({tableExt: '/js/layui-ext/tableExt'}).use(['tableExt','laydate'], function () {
		var table = layui.tableExt;
		$ = layui.$;
		var laydate = layui.laydate;
		var today = new Date();
		laydate.render({ //创建时间选择框
			elem : '#minCreateDate', //指定元素
			type : 'datetime',
			trigger : 'click',
			value: minCreateDate?minCreateDate:new Date(today.getFullYear(),today.getMonth(),today.getDate())
		});
		laydate.render({ //创建时间选择框
			elem : '#maxCreateDate', //指定元素
			type : 'datetime',
			trigger : 'click',
			value : maxCreateDate?maxCreateDate:new Date(today.getFullYear(), today.getMonth(), today.getDate(), 23, 59, 59)
		});
		table.render({
			url: '/admin/sended_inputLogList',
			height: 'full-160',
			extField: 'content',
			even: false,
			where:{
				minCreateDate : $("#minCreateDate").val(),
				maxCreateDate : $("#maxCreateDate").val(),
				msg_Batch_No : msg_Batch_No
			},
			cols: [[
				{field: 'id', title: 'ID', width: 60, type: 'checkbox'},
				{field: 'enterprise_No_ext', title: '企业名称/企业用户',templet:function (d) {
						return !d.enterprise_No_ext?'---':handleData(d.enterprise_No_ext.name)
								+"<br>"+handleData(d.enterprise_User_Id_ext.real_Name)
								+"("+handleData(d.enterprise_User_Id_ext.user_Name)+")";
					}, width: 180},
				{title: '批次号',width: 175,templet:function (d) {
						var a = "<a href='javascript:;' lay-event='{\"type\":\"tagTodo\",\"url\":\"/admin/sended_reportIndex?limitCode=006012002&flag=Input_Log_Flag&minCreateDate="+d.create_Date+"&msg_Batch_No="+d.msg_Batch_No+"\",\"title\":\"详情\"}' style='color: #01aaed;text-decoration-line: underline;'>"+handleData(d.msg_Batch_No)+"</a>";
						return a;
					}},
				{title: '手机号',width: 155,field:'phone_Nos'},
				{ title: '短信属性',templet:function (d) {
						var a = handleData(d.message_Type_Code_name);
						a += '/'+handleData(d.content_Length)+"字";
						a += '/'+handleData(d.phone_Nos_Count)+'个<br>';
						a += '计费:'+handleData(d.fee_Count)+'条='+ handleData(d.sale_Amount)+'元';
						return a;
					}, width: 190},
				{ title: '提交时间/分拣时间',templet:function (d) {
						return d.input_Date + '<br>' + d.create_Date;
					}, minWidth: 170},
			]],done: function(res){
				layui.$("#statistics").html("");
				layui.use('element', function() {
					var element = layui.element;
					element.init();
				});
			}
		});
	});

	function getFormData() {
		return $("#layuiForm").serialize();
	}
</script>