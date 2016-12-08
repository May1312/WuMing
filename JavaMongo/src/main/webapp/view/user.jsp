<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add user</title>
<!-- 引入js类库 -->
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.js"></script>
</head>
<body>
	<h1>add user</h1>
	<form id="form1" method="post" enctype="application/x-www-form-urlencoded">
		&nbsp;name:<input type="text" name="name"/><br><hr color="white" />
		&nbsp;&nbsp;age:<input type="text" name="age"/><br><hr color="white" />
	     sex: <input type="radio" name="sex" value="1" checked>Male
			  <input type="radio" name="sex" value="0">Female<br>
		<!-- <input type="submit" value="提交"/> -->
		<input type="button" onclick="getajax()" value="提交" />
	</form>
</body>

<script type="text/javascript">
	function getajax(){
		var data = $('#form1').serializeObject();
		$.ajax({
				dataType: 'json',/* 请求数据类型 */
				type: 'post',
          	 	contentType: 'application/json; charset=utf-8', /* 定义返回数据类型 */
           		data: JSON.stringify(data),/* 格式化json */
			    url: '/mongo/receive',
			    success: function(msg){
			    		alert(msg['message']);
			   },
          	 	error:function(){
          	 		alert("System error!")
          	 	}
			}); 
	};
	/* 方法 表单数据转成json对象 */
	$.fn.serializeObject = function()    
	{    
	   var o = {};    
	   var a = this.serializeArray();    
	   $.each(a, function() {    
	       if (o[this.name]) {    
	           if (!o[this.name].push) {    
	               o[this.name] = [o[this.name]];    
	           }    
	           o[this.name].push(this.value || '');    
	       } else {    
	           o[this.name] = this.value || '';    
	       }    
	   });    
	   return o;    
	};
</script>
</html>
