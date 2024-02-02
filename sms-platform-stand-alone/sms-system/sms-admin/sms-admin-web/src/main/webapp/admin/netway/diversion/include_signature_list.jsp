<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/admin/common/common.jsp" %>
<%@ include file="/admin/common/layui_head.html" %>
<script src="/js/jquery-3.4.1.min.js"></script>
<body>
<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<form class="layui-form layui-card-header layuiadmin-card-header-auto" onsubmit="return false;">
					<input value="${channelNo}" name="channel_No" id="channelNo" hidden />
					<input value="${limitCode}" name="limitCode" id="limitCode" hidden />
					<div class="layui-inline">
						&nbsp;&nbsp;签名&nbsp;
						<div class="layui-input-inline">
							<input name="signature" id="signature" class="layui-input"/>
						</div>
					</div>&nbsp;&nbsp;
					<div class="layui-inline">
						&nbsp;&nbsp;子编码
						<div class="layui-input-inline">
							<input name="subCode" id="subCode" class="layui-input"/>
						</div>
					</div>&nbsp;&nbsp;
					<div class="layui-inline">
						<button class="layui-btn layui-btn-sm" type="submit" lay-submit="" lay-filter="reload">搜索
						</button>
					</div>
				</form>
				<div class="layui-form layui-border-box layui-table-view">
					<div class="layui-card-body">
						<table class="layui-hide" id="list_table" lay-filter="list_table"></table>
						<script type="text/html" id="table-toolbar">
							<div class="layui-btn-container">
								<%@include file="/admin/common/button_action_list.jsp" %>
							</div>
						</script>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
<script>
	layui.extend({tableExt: '/layuiadmin/extends/tableExt'}).use(['tableExt'], function () {
		var table = layui.tableExt;
		table.render({
			url: '/admin/channelSignatureList',
			where: {
				"channel_No":'${channelNo}',
				"strategy_Rule": function () {
					// 签名和子编码都是精确匹配查询
					var result = '';
					var signature = $("#signature").val();
					var subCode = $("#subCode").val();
					if(!!signature) {
						result += '"' + signature + '",';
					}
					if(!!subCode) {
						result += '"subCode":"' + subCode + '"';
					}
					return result;
				}
			},
			cols: [[
				{checkbox: true, width: 150},
				{title: '签名',minWidth: 150,templet:function (d) {
						var json = JSON.parse(d.strategy_Rule);
						return json.signature;
					}},
				{title: '子编码', minWidth: 150, templet: function(d){
						var json = JSON.parse(d.strategy_Rule);
						return json.subCode;
					}},
				{title: '状态', minWidth: 150, templet: function(d){
						return handleData(d.status_Code_name);
					}},
				{field: 'create_Date', title: '创建日期', width: 200}
			]]
		});
	});
	//导出用的勿删
	function getFormData() {
		return 'channelNo='+$("#channelNo").val();
	}
</script>