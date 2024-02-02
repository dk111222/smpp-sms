<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href='<ht:heropageconfigurationtext code="agent_webpage_css" defaultValue="/layuiadmin"/>/layui/css/layui.css' media="all">
    <link rel="stylesheet" href='<ht:heropageconfigurationtext code="agent_webpage_css" defaultValue="/layuiadmin"/>/style/admin.css' media="all">
    <script src='<ht:heropageconfigurationtext code="agent_webpage_css" defaultValue="/layuiadmin"/>/layui/layui.js' charset="utf-8"></script>
    <script src="/js/date-fmt.js" charset="utf-8"></script>
    <script src="/js/jquery-3.4.1.min.js"></script>

</head>
<script>
    //处理字段null、undefined 等
    function handleData(data) {
        if(data == '0'){//处理数字0异常显示
            return data;
        }
        if(!data){
            return '---';
        }
        if(data == 'null' || data == 'undefined'){
            return '---';
        }
        return data;
    }
    //字符串超长作固定长度加省略号（...）处理
    function beautySub(str, len) {
        var reg = /[\u4e00-\u9fa5]/g,    //专业匹配中文
            slice = str.substring(0, len),
            chineseCharNum = (~~(slice.match(reg) && slice.match(reg).length)),
            realen = slice.length * 2 - chineseCharNum;
        return str.substr(0, realen) + (realen < str.length ? "..." : "");
    }
    //获取请求参数
    function getRequestParams() {
        var url = location.search;//获取url中"?"符后的字串
        url = decodeURI(url);
        var params = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                params[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
            }
        }
        return params;
    }
</script>