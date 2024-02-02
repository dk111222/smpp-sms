<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/layui_common.jsp" %>
<%@ include file="/common/country_operator.jsp" %>
<body>
<div class="layui-fluid">
    <div class="layui-col-md12">
        <div class="layui-card">
            <form id="layuiForm" class="layui-form layui-card-header layuiadmin-card-header-auto" onsubmit="return false;">
                <div class="layui-inline">
                    &nbsp;&nbsp;企业名称&nbsp;
                    <div class="layui-inline" style="width: 200px">
                        <ht:heroenterpriseselect agentNo="${sessionScope.AGENT_INFO.no}" name="enterprise_No" />
                    </div>
                </div>
                <div class="layui-inline">
                    &nbsp;&nbsp;企业用户&nbsp;
                    <div class="layui-inline" style="width: 200px">
                        <ht:herocustomdataselect param="${sessionScope.AGENT_INFO.no}" paramType="agentNo" dataSourceType="allEnterpriseUsers" name="enterprise_User_Id" />
                    </div>
                </div>
                <div class="layui-inline">
                    &nbsp;&nbsp;国家&nbsp;
                    <div class="layui-input-inline">
                        <ht:herocodeselect sortCode="country" selected="cn" name="country_Number" id="country_Number" valueField="Value"/>
                    </div>
                </div>&nbsp;&nbsp;
                <div class="layui-inline">
                    &nbsp;&nbsp;运营商&nbsp;
                    <div class="layui-input-inline">
                        <ht:countryoperatorselect countryNumber="cn" id="operator" name="operator" />
                    </div>
                </div>&nbsp;&nbsp;
                <div class="layui-inline">
                    &nbsp;&nbsp;消息类型&nbsp;
                    <div class="layui-inline">
                        <ht:herocodeselect sortCode="message_type_code"  name="message_Type_Code"/>
                    </div>
                </div>
                <div class="layui-inline">
                    &nbsp;&nbsp;提交状态&nbsp;
                    <div class="layui-input-inline" style="width: 100px">
                        <ht:herocodeselect sortCode="034" name="submit_Status_Code"/>
                    </div>
                </div>&nbsp;&nbsp;
                <div class="layui-inline">
                    &nbsp;&nbsp;短信内容&nbsp;
                    <div class="layui-input-inline">
                        <input name="content" id = "content" class="layui-input"/>
                    </div>
                </div>&nbsp;&nbsp;
                <div class="layui-inline">
                    &nbsp;&nbsp;手机号码&nbsp;
                    <div class="layui-input-inline">
                        <input name="phone_Nos" id = "phone_Nos" class="layui-input"/>
                    </div>
                </div>&nbsp;&nbsp;
                <div class="layui-inline">
                    &nbsp;&nbsp;批次号&nbsp;
                    <div class="layui-input-inline">
                        <input name="msg_Batch_No" id="msg_Batch_No" class="layui-input"/>
                    </div>
                </div>&nbsp;&nbsp;
                <div class="layui-inline">
                    &nbsp;&nbsp;创建时间&nbsp;
                    <div class="layui-inline">
                        <input name="minSubmitDate" id="minSubmitDate" class="layui-input layui-input-sm"/>
                    </div>
                    <div class="layui-inline">--</div>
                    <div class="layui-inline">
                        <input name="maxSubmitDate" id="maxSubmitDate"  class="layui-input layui-input-sm"/>
                    </div>
                </div>&nbsp;&nbsp;
                <div class="layui-inline">
                    <button class="layui-btn layui-btn-sm" type="submit" lay-submit="" lay-filter="reload">搜索
                    </button>
                </div>
            </form>
            <div class="layui-form layui-border-box layui-table-view">
                <div class="layui-card-body">
<%--                    <blockquote class="layui-elem-quote" id="statistics" style="padding: 10px;"></blockquote>--%>
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
    layui.extend({tableExt: '/js/layui-ext/tableExt'}).use(['tableExt','laydate'], function () {
        var table = layui.tableExt;
        $ = layui.$;
        var laydate = layui.laydate;
        var today = new Date();
        var minSubmitDate = '<c:out value="${minSubmitDate}"/>';
        laydate.render({     //创建时间选择框
            elem: '#minSubmitDate',
            type:'datetime',
            trigger : 'click',
            value: minSubmitDate?minSubmitDate:new Date(today.getFullYear(),today.getMonth(),today.getDate())
        });
        laydate.render({     //创建时间选择框
            elem: '#maxSubmitDate',
            type:'datetime',
            trigger : 'click',
            value: new Date(today.getFullYear(),today.getMonth(),today.getDate(),23,59,59)
        });
        table.render({
            url: '/admin/sended_submitList',
            height: 'full-200',
            extField: 'content',
            even: false,
            where:{
                'minSubmitDate':$("#minSubmitDate").val()==''? minSubmitDate:$("#minSubmitDate").val(),
                'maxSubmitDate':$("#maxSubmitDate").val()
            },
            cols: [[
            	{title: '企业名称/企业用户', width:180,templet:function (d) {
                    return !d.enterprise_No_ext?'---':handleData(d.enterprise_No_ext.name)
                        +"<br>"+handleData(d.enterprise_User_Id_ext.real_Name)
                        +"("+handleData(d.enterprise_User_Id_ext.user_Name)+")";
                	}},
                 {title: '手机号/批次号',width: 175,templet:function (d) {
                  return handleData(d.phone_No)+"<br><a href='javascript:;' lay-event='{\"type\":\"dialogNoBtnCustomerArea\",\"url\":\"/admin/sended_submitDetails?" +
                      "id="+d.id+"&submit_Date_Str="+d.submit_Date+"\",\"width\":\"70%\",\"height\":\"90%\",\"title\":\"详情\"}' style='color: #01aaed;text-decoration-line: underline;'>"+handleData(d.msg_Batch_No)+"</a>";
                }},
              { title: '提交状态',templet:function (d) {
                  var resTime = d.submitResponseTime||0;
                  var color = "#06b832";
                  if(resTime>100 && resTime<=200){
                    color="#ebc207";
                  }
                  if(resTime>200){
                    color="red";
                  }
                  var a = handleData(d.submit_Status_Code_name);
                  a += '/<font color='+color+'>'+resTime+'ms</font><br>';
                  a += handleData(d.submit_Date);
                  return a;
                }, width: 152},
                { title: '运营商',templet:function (d) {
                  return handleData(d.operator)+'/'+handleData(d.area_Name);
                }, width: 220},
                { title: '短信属性',templet:function (d) {
                        var a = handleData(d.message_Type_Code_name);
                        a += '/'+handleData(d.content_Length)+"字";
                        if(d.is_LMS){
                            a += '/长'+"/第"+handleData(d.sequence)+"条";
                        }else{
                            a += '/短/1条';
                        }
                        if(d.sp_Number){
                            a += '<br>'+handleData(d.sp_Number);
                        }
                        return a;
                    }, width: 170},
                {field: 'agent_Profits', title: '代理商利润',width: 120},
                {field: 'create_Date', title: '创建时间',minWidth: 170}
            ]],
            done:function (res) {
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