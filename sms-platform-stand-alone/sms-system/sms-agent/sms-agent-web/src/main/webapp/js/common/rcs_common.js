//判断对象是否为空
function isEmpty(obj) {
	if (typeof obj == "undefined" || obj == null || obj == "") {
		return true;
	} else {
		return false;
	}
}
//增加一个名为 IsPicture 的函数作为String构造函数的原型对象的一个方法。
String.prototype.IsPicture = function() {
	//判断是否是图片 - strFilter必须是小写列举
	var strFilter=".jpeg|.gif|.jpg|.png|.bmp|.pic|.svg|"
	if(this.indexOf(".")>-1)
	{
		var p = this.lastIndexOf(".");
		var strPostfix=this.substring(p,this.length) + '|';
		strPostfix = strPostfix.toLowerCase();
		if(strFilter.indexOf(strPostfix)>-1)
		{
			return true;
		}
	}
	return false;
}

//查看详情
function contentDetail(content) {
    layer.open({
        area: ['750px', '550px'],
        title:'详情',
        maxmin: true,
        closeBtn: 1,
        shadeClose: true,
        type: 2,
        content: "/admin/rcs/rcs_records_detail.jsp?content="+encodeURIComponent(content),
    });
}