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
                <input hidden name="pagingState"/>
                <div class="layui-input-item">
                    <div class="layui-input-item">
                        <div class="layui-inline">
                            &nbsp;&nbsp;企业名称&nbsp;
                            <div class="layui-inline" style="width: 70%">
                                <ht:heroenterpriseselect agentNo="${sessionScope.AGENT_INFO.no}" id="enterprise_No_Head"  name="enterprise_No" />
                            </div>
                        </div>
                        <div class="layui-inline">
                            &nbsp;&nbsp;企业用户<font color="red">&nbsp;*</font>
                            <div class="layui-inline" style="width: 70%">
                                <ht:herocustomdataselect param="${sessionScope.AGENT_INFO.no}" paramType="agentNo"
                                                         dataSourceType="allEnterpriseUsers" layVerify="required" name="enterprise_User_Id" />
                            </div>
                        </div>
                        <div class="layui-inline">
                            &nbsp;&nbsp;消息类型&nbsp;
                            <div class="layui-inline">
                                <ht:herocodeselect sortCode="message_type_code"  name="message_Type_Code"/>
                            </div>
                        </div>
                        <div class="layui-inline">&nbsp;&nbsp;
                            手机号码&nbsp;&nbsp;&nbsp;
                        <div class="layui-inline">
                            <input name="phone_Nos" class="layui-input" id="phone_Nos"
                                   value="<c:out value="${inputLogExt.phone_Nos}"/>" />
                        </div> </div>
                        <div class="layui-inline">&nbsp;&nbsp;
                            批次号&nbsp;&nbsp;&nbsp;
                        <div class="layui-inline">
                            <input name="msg_Batch_No" id="msg_Batch_No" class="layui-input"  />
                        </div>
                        </div>
                        <div class="layui-inline">&nbsp;&nbsp;内容&nbsp;</div>
                        <div class="layui-inline">
                            <input name="content" id="content" class="layui-input" value="<c:out value="${inputLogExt.content}"/>" />
                        </div>
                        <div class="layui-inline">&nbsp;&nbsp;提交时间&nbsp;</div>
                        <div class="layui-inline">
                            <input name='minCreateDate' class="layui-input layui-input-sm" id="minSubmitDate"
                                   value="<c:out value="${conditionMap['min_Create_Date']}"></c:out>">
                        </div>
                        <div class="layui-inline">-</div>
                        <div class="layui-inline">
                            <input name='maxCreateDate' class="layui-input layui-input-sm" id="maxSubmitDate" lay-verify="submitDate"
                                   value="<c:out value="${conditionMap['max_Create_Date']}"></c:out>">
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
            url: '/admin/history_InputLogList',
            height: 'full-180',
            where: {
                pageSize: pageSize,
                pagingState: $('input[name="pagingState"]').val(),
                enterprise_User_Id: $("select[name='enterprise_User_Id']").val(),
                minCreateDate: $("#minSubmitDate").val()==''?getFormatDateBefore(4)+' 00:00:00':$("#minSubmitDate").val(),
                maxCreateDate: $("#maxSubmitDate").val()==''?getFormatDateBefore(4)+' 23:59:59':$("#maxSubmitDate").val()
            },
            cols: [[
                {type: 'numbers', title:'序号',width:60},
                {field: 'enterprise_No_ext', title: '企业名称/企业用户',templet:function (d) {
                        return !d.enterprise_No_ext?'---':handleData(d.enterprise_No_ext.name)
                            +"<br>"+handleData(d.enterprise_User_Id_ext.real_Name)
                            +"("+handleData(d.enterprise_User_Id_ext.user_Name)+")";
                    }, width: 180},
                {field: 'msg_Batch_No', title: '批次号', width: 220},
                {title: '手机号',width: 155,field:'phone_Nos'},
                {field: 'content', title: '内容', minWidth: 350},
                { title: '短信属性',templet:function (d) {
                        var a = handleData(d.message_Type_Code_name);
                        a += '/'+handleData(d.content_Length)+"字";
                        a += '/'+handleData(d.phone_Nos_Count)+'个<br>';
                        a += '计费:'+handleData(d.fee_Count)+'条='+ handleData(d.sale_Amount)+'元';
                        return a;
                    }, width: 190},
                {field: 'create_Date', title: '分拣时间', width: 170}
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
            elem: '#minSubmitDate' //指定元素
            , type: 'datetime'
            , trigger: 'click'
            , value: getFormatDateBefore(4)+' 00:00:00'
        });
        laydate.render({     //创建时间选择框
            elem: '#maxSubmitDate' //指定元素
            , type: 'datetime'
            , trigger: 'click'
            , value: getFormatDateBefore(4)+' 23:59:59'
        });
        //自定义验证规则
        form.verify({
            submitDate: function(value){
                var minSubmitDate = document.getElementById("minSubmitDate").value,
                    maxSubmitDate = document.getElementById("maxSubmitDate").value;
                if(minSubmitDate.substr(0,10) != maxSubmitDate.substr(0,10)){
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