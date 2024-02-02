<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/admin/common/common.jsp" %>
<%@ include file="/admin/common/layui_head.html" %>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <form class="layui-form layui-card-header layuiadmin-card-header-auto" onsubmit="return false;">
                    <div class="layui-inline">
                        产品名称&nbsp;
                        <div class="layui-input-inline">
                            <input name="name" class="layui-input"/>
                        </div>
                    </div>&nbsp;&nbsp;

                    <div class="layui-inline">
                        <button class="layui-btn layui-btn-sm" type="submit" lay-submit="" lay-filter="reload">搜索
                        </button>
                    </div>
                </form>
                <div class="layui-form layui-border-box layui-table-view">
                    <div class="layui-form-item layui-hide">
                        <input type="submit" lay-submit lay-filter="submit" id="layuiadmin-app-form-submit" value="确认">
                    </div>
                    <div class="layui-card-body">
                        <table class="layui-hide" id="list_table" lay-filter="list_table"></table>
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
            url: '/admin/product/productList?status_Code=start',
            page: false,
            limit: 9999,
            toolbar:false,
            cols: [[
                {checkbox: true,width: 50},
                {title: '产品名称(编号)', minWidth:300,templet:function (d) {
                        return d.name + "("+d.no+")";
                    }}
            ]]
            ,done: function(res, page, count){
                var agentProduct = "${agent.product_No}";
                if(count>0){
                    var productList = res.data;
                    for(var i=0;i<productList.length;i++){
                        var product = productList[i];
                        if(agentProduct.indexOf(product.no) != -1){
                            //选中，通过设置关键字LAY_CHECKED为true选中
                            res.data[i]["LAY_CHECKED"]='true';
                            //更改css来实现选中的效果
                            $('tr[data-index=' + i + '] input[type="checkbox"]').next().addClass('layui-form-checked');
                        }
                    }
                }
            }
        });
        $(document).on('click', '#layuiadmin-app-form-submit', function () {
            var checkStatus = table.checkStatus('list_table'); //idTest 即为基础参数 id 对应的值

            if(checkStatus.data.length==0){
                layer.msg("请选择一条数据");
                return;
            }
            var productArray = new Array();
            var productListChecked = checkStatus.data;
            for(var i=0;i<productListChecked.length;i++){
                productArray.push(productListChecked[i].no);
            }

            $.ajax({
                type: 'post', // 提交方式 get/post
                url: "/admin/agent_batchUpdateProduct",
                dataType: 'json',
                data: {
                    "agentId":"${agent.id}",
                    "productNo":productArray.join(','),
                },
                success: function (data) {
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
                }
            })
        });
    });
</script>