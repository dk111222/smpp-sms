<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp" %>
<%@ include file="/common/layui_common.jsp" %>
<%@ include file="/common/country_operator.jsp" %>
<body>
<div class="layui-fluid">
    <div class="layui-col-md12">
        <div class="layui-card">
            <form class="layui-form layui-card-header layuiadmin-card-header-auto" onsubmit="return false;"
                  id="layuiForm">
                <input hidden name="pagingState"/>
                <div class="layui-inline">
                    &nbsp;&nbsp;企业名称&nbsp;
                    <div class="layui-inline" style="width: 70%">
                        <ht:heroenterpriseselect agentNo="${sessionScope.AGENT_INFO.no}" id="enterprise_No_Head" name="enterprise_No" />
                    </div>
                </div>
                <div class="layui-inline">
                    &nbsp;&nbsp;企业用户<font color="red">&nbsp;*</font>
                    <div class="layui-inline" style="width: 70%">
                        <ht:herocustomdataselect param="${sessionScope.AGENT_INFO.no}" layVerify="required" paramType="agentNo"
                                                 dataSourceType="allEnterpriseUsers" name="enterprise_User_Id" />
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
                    &nbsp;&nbsp;手机号码&nbsp;
                    <div class="layui-input-inline">
                        <input name="phone_No" id = "phone_No" class="layui-input"/>
                    </div>
                </div>&nbsp;&nbsp;
                <div class="layui-inline">
                    &nbsp;&nbsp;批次号&nbsp;
                    <div class="layui-input-inline">
                        <input name="msg_Batch_No" id="msg_Batch_No" class="layui-input"/>
                    </div>
                </div>&nbsp;
                <div class="layui-inline">
                    &nbsp;&nbsp;消息类型&nbsp;
                    <div class="layui-inline" style="width: 200px">
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
                    &nbsp;&nbsp;提交描述&nbsp;
                    <div class="layui-input-inline" style="width: 100px">
                        <input name="submit_Description" class="layui-input"/>
                    </div>
                </div>&nbsp;&nbsp;

                <div class="layui-inline">
                    &nbsp;&nbsp;短信内容&nbsp;
                    <div class="layui-input-inline">
                        <input name="content" id = "content" class="layui-input"/>
                    </div>
                </div>&nbsp;&nbsp;
                <div class="layui-inline">
                    &nbsp;&nbsp;创建时间&nbsp;
                    <div class="layui-inline">
                        <input name="minCreateDate" id="minCreateDate" class="layui-input layui-input-sm"/>
                    </div>
                    <div class="layui-inline">--</div>
                    <div class="layui-input-inline">
                        <input name="maxCreateDate" id="maxCreateDate" lay-verify="createDate" class="layui-input layui-input-sm"/>
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
    var pagingStateArray = new Array();
    var pageNum = 1;
    var pageSize = 20;
    var totalCount = pageSize;
    var pagingState;
    layui.extend({tableExt: '/js/layui-ext/tableExt'}).use(['tableExt'], function () {
        var table = layui.tableExt;
        $ = layui.$;
        table.render({
            url: '/admin/history_submitLogList',
            height: 'full-200',
            where:{
                pageSize: pageSize,
                pagingState: $('input[name="pagingState"]').val(),
                enterprise_User_Id: $("select[name='enterprise_User_Id']").val(),
                minCreateDate: $("#minCreateDate").val()==''?getFormatDateBefore(4)+' 00:00:00':$("#minCreateDate").val(),
                maxCreateDate: $("#maxCreateDate").val()==''?getFormatDateBefore(4)+' 23:59:59':$("#maxCreateDate").val()
            },
            cols: [[
                {type: 'numbers', title:'序号',width:60},
                {title: '企业名称/企业用户', width:180,templet:function (d) {
                        return !d.enterprise_No_ext?'---':handleData(d.enterprise_No_ext.name)
                            +"<br>"+handleData(d.enterprise_User_Id_ext.real_Name)
                            +"("+handleData(d.enterprise_User_Id_ext.user_Name)+")";
                    }},
                {title: '手机号/批次号',width: 175,templet:function (d) {
                        return handleData(d.phone_No)+"<br><a href='javascript:;' lay-event='{\"type\":\"dialogNoBtnCustomerArea\",\"url\":\"/admin/history_submitDetails?" +
                            "id="+d.id+ "&enterprise_User_Id="+d.enterprise_User_Id+ "&submitDateStr="+d.submit_Date+
                            "\",\"width\":\"70%\",\"height\":\"90%\",\"title\":\"详情\"}' style='color: #01aaed;text-decoration-line: underline;'>"+handleData(d.msg_Batch_No)+"</a>";
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
                    }, width: 170},
                {field: 'content', title: '内容',width: 300},
                { title: '运营商',templet:function (d) {
                        var a = handleData(d.country_Number_name)+'/'+handleData(d.operator)+'<br>';
                        a += handleData(d.area_Name);
                        return a;
                    }, width: 160},
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
                {field: 'submit_Date', title: '创建时间',minWidth: 170}
            ]],
            done: function (res) {
                layui.use(['laypage', 'layer'], function(){
                    var laypage = layui.laypage
                        ,layer = layui.layer;
                    if(res.code!="0") return;
                    //保存下一页的pagingState,增加totalCount
                    if(res.map.pagingState != null && pageNum > pagingStateArray.length){
                        totalCount = totalCount+pageSize;
                        pagingStateArray.push(res.map.pagingState)
                    }
                    //自定义分页,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
                    laypage.render({
                        elem: 'layui-table-page1'
                        ,count: totalCount
                        ,curr: pageNum
                        ,limit: pageSize
                        ,layout: ['prev', 'page', 'next', 'limit']
                        ,jump: function(obj, first){
                            if(obj.curr > pageNum ){
                                if(obj.curr -2 >= pagingStateArray.length){
                                    pageNum = pagingStateArray.length +1;
                                }else{
                                    pageNum = obj.curr;
                                }
                                pagingState = pagingStateArray[pageNum-2];
                            }else if(obj.curr < pageNum){
                                if(obj.curr <= 1){
                                    pageNum = 1;
                                    pagingState = null;
                                }else{
                                    pageNum = obj.curr;
                                    pagingState = pagingStateArray[pageNum-2];
                                }
                            }
                            if(obj.limit != pageSize){
                                pageSize = obj.limit;
                                pagingStateArray = new Array();
                                pageNum = 1;
                                totalCount = pageSize;
                                pagingState = null;
                            }
                            if(!first){
                                table.reload('list_table',{
                                    where: {
                                        pageSize: pageSize,
                                        pagingState:pagingState
                                    }
                                })
                            }
                        }
                    });
                });
            }
        });
    });

    layui.use(['form', 'laydate'], function () {
        var laydate = layui.laydate;
        var form = layui.form;
        laydate.render({     //创建时间选择框
            elem: '#minCreateDate' //指定元素
            , type: 'datetime'
            , trigger: 'click'
            , value: getFormatDateBefore(4)+' 00:00:00'
        });
        laydate.render({     //创建时间选择框
            elem: '#maxCreateDate' //指定元素
            , type: 'datetime'
            , trigger: 'click'
            , value: getFormatDateBefore(4)+' 23:59:59'
        });
        //自定义验证规则
        form.verify({
            createDate: function(value){
                var minCreateDate = document.getElementById("minCreateDate").value,
                    maxCreateDate = document.getElementById("maxCreateDate").value;
                if(minCreateDate.substr(0,10) != maxCreateDate.substr(0,10)){
                    return"查询时间只能为同一天";
                };
                //查询条件改变重置分页数据
                pagingStateArray = new Array();
                pageNum = 1;
                totalCount = pageSize;
                pagingState = null;
            }
        });
    });
    function getFormData() {
        return $("#layuiForm").serialize();
    }
</script>