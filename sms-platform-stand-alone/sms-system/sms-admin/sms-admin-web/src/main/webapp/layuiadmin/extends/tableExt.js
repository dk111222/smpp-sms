//判断对象是否为空
function isEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}
function compareDate(date1,date2){
    var oDate1 = new Date(date1);
    var oDate2 = new Date(date2);
    if(oDate1.getTime() > oDate2.getTime()){
        return true;
    } else {
        return false;
    }
}
// 扩展table
layui.define(['table', 'form'], function (exports) { //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
    var table = layui.table, $ = layui.$, form = layui.form;
    var tableExt = $.extend({}, table);
    tableExt._render = tableExt.render;
    tableExt.render = function (params) {
        // 分页 默认开启 显示10条选择项
        params.page = null == params.page ? {groups: 10} : params.page;
        // 分页显示条数
        params.limit = params.limit ? params.limit : 20;
        // 表格idelem
        params.elem = params.elem ? params.elem : '#list_table';
        // 分页请求数据定义
        params.request = params.request ? params.request : {
            pageName: 'pagination.pageIndex',
            limitName: 'pagination.pageSize'
        };
        // 高度
        params.height = params.height ? params.height : 'full-118';
        // 开启隔行背景
        params.even = null == params.even ? true : params.even;
        // 数据返回回调执行函数
        params.parseData = params.parseData ? params.parseData : function (res) {
            if (res.code == '301') {
                layer.msg(res.msg, {icon: 2, time: 2000}, function () {
                    top.location.href = res.url;
                    return;
                });
            }
        };
        // 工具条ID 绑定
        if(params.toolbar!=false){
            params.toolbar = params.toolbar ? params.toolbar : '#table-toolbar';
        }
        //内容换行字段
        if (params.extField) {
            //原来的done函数
            var done = params.done;
            params.done = function (res, curr, count) {
                //扩展附加字段
                extendData(res.data, params.extField);
                if (done) {
                    done(res, curr, count)
                }
            }
            //加载组件所需样式
            layui.link('/layuiadmin/extends/layuiExt.css?v=1', function () {
                //此处的“openTable”要对应 openTable.css 中的样式： html #layuicss-openTable{}
            }, 'tableExt');
        }
        tableExt._render(params);
        //头工具栏事件-显示按钮
        var tableId = params.elem.replace("#", "");
        table.on('toolbar(' + tableId + ')', function (obj) {
            // 选择中的状态
            var checkStatus = table.checkStatus(obj.config.id);
            // 选择中的参数
            var data = checkStatus.data;
            if(obj.event.indexOf("url")>1){
                buttonEvent(tableId, obj, data);
            }
        });

        //监听工具条
        table.on('tool(' + tableId + ')', function (obj) {
            buttonEvent(tableId, obj, [obj.data]);
        });
        /* 监听查询条件 */
        form.on('submit(reload)', function (data) {
            var minCreateDate = data.field.minCreateDate;
            var maxCreateDate = data.field.maxCreateDate;
            var minSubmitDate = data.field.minSubmitDate;
            var maxSubmitDate = data.field.maxSubmitDate;
            var isBefore = false;
            if(!isEmpty(minCreateDate)&&!isEmpty(maxCreateDate)){
                isBefore = compareDate(minCreateDate,maxCreateDate);
            }else if(!isEmpty(minSubmitDate)&&!isEmpty(maxSubmitDate)){
                isBefore = compareDate(minSubmitDate,maxSubmitDate);
            }
            if(isBefore){
                layer.msg("终止时间必须大于起始时间", {icon: 2, time: 2000});
                return;
            }
            table.reload(tableId, {
                page: {
                    curr: 1,
                },
                where: data.field
            });
        });
        /*// 监听单击行事件 选择复选框
        table.on('row(' + tableId + ')', function(obj){
            var tableIndex = obj.tr[0].dataset.index;
            var child = obj.tr.find('input[type="checkbox"]');
            child.each(function(index, item) {
                item.checked = !item.checked;
                table.cache[tableId][tableIndex]['LAY_CHECKED'] = item.checked;//重要！！
            });
            form.render('checkbox');
        });*/

    }

    function extendData(data, field) {
        var size = data.length;
        for (var i = 0; i < size; i ++) {
            var tr = $("tr[data-index=" + i + "]");
            var classTr = '';
            if (i % 2 == 1) {
                tr.addClass('ext-tr');
                classTr = 'ext-tr';
            }
            // 把添加的 tr 绑定到当前 移除时使用
            this.addTR = $([
                "<tr class='" + classTr + "'><td colspan='100'>"
                , "<div data-content='1' class='openTable-open-item-div overflow-hidden'>"
                + data[i][field] +
                "</div>"
                , "</td></tr>"].join("")
            );
            tr.after(this.addTR);

        }
        $("div[data-content=1]").click(function () {
            var overflow = $(this).css('overflow');
            if ('hidden' == overflow) {
                $(this).removeClass('overflow-hidden');
            }
        });
    }

    /**
     * 按钮触发事件
     * @param tableId 表格id
     * @param obj 总数据
     * @param data 选中数据
     */
    function buttonEvent(tableId, obj, data) {
        // 模板lay-event='{"type":"dialogTodo","url":"/admin/","width":"550","height":"450"}'
        var event = JSON.parse(obj.event);
        // 弹窗大小
        var width = event.width == null ? 500 : event.width;
        var height = event.height == null ? 500 : event.height;
        var area = [width + "px", height + "px"];
        // 跳转地址 lay-url="/admin/account_add?ckId={0}"
        var buttonUrl = event.url;
        // 标题
        var title = event.title == null ? "是否确认继续操作" : event.title;
        switch (event.type) {
            //弹窗有则传参无则传空
            case 'dialogNull':
                if (data.length != 1) {
                    layer.open({
                        type: 2,
                        title: title,
                        content: buttonUrl,
                        maxmin: true,
                        area: area,
                        btn: ['确定', '取消'],

                        yes: function (index, layero) {
                            var submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                            submit.click();
                        }
                    });
                }else {
                    var id = [data[0].id];
                    if (buttonUrl.indexOf("{0}") != -1) {
                        buttonUrl = buttonUrl.replace("{0}", id);
                    } else {
                        if (buttonUrl.indexOf("?") != -1) {
                            buttonUrl += "&ckIds=" + id;
                        } else {
                            buttonUrl += "?ckIds=" + id;
                        }
                    }
                    layer.open({
                        type: 2,
                        title: title,
                        content: buttonUrl,
                        maxmin: true,
                        area: area,
                        btn: ['确定', '取消'],
                        yes: function (index, layero) {
                            //点击确认触发 iframe 内容中的按钮提交
                            var submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                            submit.click();
                        }
                    });}
                break;
            // 弹窗不传递参数
            case 'dialog':
                layer.open({
                    type: 2,
                    title: title,
                    content: buttonUrl,
                    maxmin: true,
                    area: area,
                    btn: ['确定', '取消'],

                yes: function (index, layero) {
                        var submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                        submit.click();
                    }
                });
                break;
             // 弹窗不传递参数 没有保存按钮
            case 'dialogNoBtn':
                layer.open({
                    type: 2,
                    title: title,
                    content: buttonUrl,
                    maxmin: true,
                    area: area
                });
                break;
            // 弹窗不传递参数 没有保存按钮,传过来的参数要带单位，（‘px’或‘%’）
            case 'dialogNoBtnCustomerArea':
                layer.open({
                    type: 2,
                    title: title,
                    content: buttonUrl,
                    maxmin: true,
                    shadeClose: true,
                    area: [width,height]
                });
                break;
            // 弹窗传递参数
            case 'dialogTodo':
                if (data.length != 1) {
                    layer.msg("请选择一条数据！");
                    return;
                }
                var id = [data[0].id];
                if (buttonUrl.indexOf("{0}") != -1) {
                    buttonUrl = buttonUrl.replace("{0}", id);
                } else {
                    if (buttonUrl.indexOf("?") != -1) {
                        buttonUrl += "&ckIds=" + id;
                    } else {
                        buttonUrl += "?ckIds=" + id;
                    }
                }
                layer.open({
                    type: 2,
                    title: title,
                    content: buttonUrl,
                    maxmin: true,
                    area: area,
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        //点击确认触发 iframe 内容中的按钮提交
                        var submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                        submit.click();
                    }
                });
                break;
            // 弹窗传递参数无按钮
            case 'dialogTodoNoBtn':
                if (data.length != 1) {
                    layer.msg("请选择一条数据！");
                    return;
                }
                var id = [data[0].id];
                if (buttonUrl.indexOf("{0}") != -1) {
                    buttonUrl = buttonUrl.replace("{0}", id);
                } else {
                    if (buttonUrl.indexOf("?") != -1) {
                        buttonUrl += "&ckIds=" + id;
                    } else {
                        buttonUrl += "?ckIds=" + id;
                    }
                }
                layer.open({
                    type: 2,
                    title: title,
                    content: buttonUrl,
                    maxmin: true,
                    area: area
                });
                break;
            // 批量操作
            case 'selectedTodo':
                if (data.length <= 0) {
                    layer.msg("至少选择一条数据！", function () {
                    });
                    return;
                }
                layer.confirm(title, {
                    btn: ['确认', '取消'] //按钮
                }, function (index) {
                    var loading= layer.load('', {time: 10*1000}); //遮罩层 最长10秒后自动关闭
                    var ids = new Array();
                    for (var i in data) {
                        ids.push(data[i].id)
                    }
                    $.ajax({
                        url: buttonUrl,
                        data: {ckIds: ids.join(",")},
                        dataType:'json',
                        success: function (res) {
                            // res = JSON.parse(res);
                            layer.close(loading);
                            if (res.code == '0') {
                                layui.table.reload(tableId);
                            } else {
                                layer.msg(res.msg);
                            }
                            layer.close(index);
                        },
                        error:function(res){
                            layer.close(loading);
                            layer.msg('操作失败');
                        }
                    });
                });
                break;
            case 'selectedToTag':
                if (data.length <= 0) {
                    layer.msg("至少选择一条数据！", function () {
                    });
                    return;
                }
                if (data.length > 5) {
                    layer.msg("最多选择五条数据！", function () {
                    });
                    return;
                }
                var ids = new Array();
                for (var i in data) {
                    ids.push(data[i].id)
                }
                window.open(buttonUrl+"?ckIds="+ids.join(","));
                break;
            // 打开新标签页
            case 'tagTodo':
                if (data.length != 1) {
                    layer.msg("请选择一条数据！", function () {
                    });
                    return;
                }
                var id = [data[0].id];
                if (buttonUrl.indexOf("{0}") != -1) {
                    buttonUrl = buttonUrl.replace("{0}", id);
                } else {
                    if (buttonUrl.indexOf("?") != -1) {
                        buttonUrl += "&ckIds=" + id;
                    } else {
                        buttonUrl += "?ckIds=" + id;
                    }
                }
                parent.layui.index.openTabsPage(buttonUrl,title);
                break;
            case 'directTodo':
                window.location.href = buttonUrl
                break;

            case 'ajaxTodo':
                var loading= layer.load('', {time: 10*1000}); //遮罩层 最长10秒后自动关闭
                $.ajax({
                    url: buttonUrl,
                    dataType:'json',
                    success: function (res) {
                        layer.close(loading);
                        layer.msg(res.msg);
                    },
                    error:function(res){
                        layer.close(loading);
                        layer.msg('操作失败');
                    }
                });
                break;

            case 'exportTodo':
                var loading= layer.load('', {time: 10*1000});
                if (buttonUrl.indexOf("?") == -1) {
                    buttonUrl += "?";
                }
                buttonUrl += getFormData();
                $.ajax({
                    url: buttonUrl,
                    dataType:'json',
                    success: function (res) {
                        layer.close(loading);
                        layer.msg(res.msg);
                    },
                    error:function(res){
                        layer.close(loading);
                        layer.msg('操作失败');
                    }
                });
                break;

            // 弹窗传递参数(No参数)
            case 'dialogTodoByNo':
                if (data.length != 1) {
                    layer.msg("请选择一条数据！", function () {
                    });
                    return;
                }
                var no = [data[0].no];
                if (buttonUrl.indexOf("{0}") != -1) {
                    buttonUrl = buttonUrl.replace("{0}", no);
                } else {
                    if (buttonUrl.indexOf("?") != -1) {
                        buttonUrl += "&ckNos=" + no;
                    } else {
                        buttonUrl += "?ckNos=" + no;
                    }
                }
                layer.open({
                    type: 2,
                    title: title,
                    content: buttonUrl,
                    maxmin: true,
                    area: area,
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        //点击确认触发 iframe 内容中的按钮提交
                        var submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                        submit.click();
                    }
                });
                break;

            // 打开新标签页(No参数)
            case 'tagTodoByNo':
                if (data.length != 1) {
                    layer.msg("请选择一条数据！", function () {
                    });
                    return;
                }
                var no = [data[0].no];
                if (buttonUrl.indexOf("{0}") != -1) {
                    buttonUrl = buttonUrl.replace("{0}", no);
                } else {
                    if (buttonUrl.indexOf("?") != -1) {
                        buttonUrl += "&ckNos=" + no;
                    } else {
                        buttonUrl += "?ckNos=" + no;
                    }
                }
                parent.layui.index.openTabsPage(buttonUrl,title);
                break;
            // 选中整行数据
            case 'selectedStringTodo':
                if (data.length <= 0) {
                    layer.msg("至少选择一条数据！", function () {
                    });
                    return;
                }
                layer.confirm(title, {
                    btn: ['确认', '取消'] //按钮
                }, function (index) {
                    var loading= layer.load('', {time: 10*1000}); //遮罩层 最长10秒后自动关闭
                    var ckStrings = new Array();
                    for (var i in data) {
                        ckStrings.push(data[i][event.field])
                    }
                    $.ajax({
                        type: 'post',
                        url: buttonUrl,
                        data: {ckStrings: ckStrings.join(",")},
                        dataType:'json',
                        success: function (res) {
                            layer.close(loading);
                            if (res.code == '0') {
                                layui.table.reload(tableId);
                            } else {
                                layer.msg(res.msg);
                            }
                            layer.close(index);
                        },
                        error:function(res){
                            layer.close(loading);
                            var se = '操作失败';
                            if(event.errMsg){
                            	se = event.errMsg;
                            	layui.table.reload(tableId);
                            }
                            layer.msg(se);
                        }
                    });
                });
                break;
            // 选中整行数据
            case 'dialogStringTodo':
                if (data.length != 1) {
                    layer.msg("请选择一条数据！", function () {
                    });
                    return;
                }
                var ckStrings = new Array();
                ckStrings.push(data[0][event.field]);
                buttonUrl += "?ckStrings=" + ckStrings;
                layer.open({
                    type: 2,
                    title: title,
                    content: buttonUrl,
                    maxmin: true,
                    area: area,
                    btn: ['确定', '取消'],
                    yes: function (index, layero) {
                        //点击确认触发 iframe 内容中的按钮提交
                        var submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
                        submit.click();
                    }
                });
                break;
        }
    }

    //输出接口
    exports('tableExt', tableExt);
});