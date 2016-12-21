<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登陆</title>
	<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<form id="form1" runat="server">
	<div id="win" class="easyui-window">
		<table>
			<tr>
				<td>
					Name:
				</td>
				<td>
					<input id="txt_name" type="text" name="name" />
				</td>
			</tr>
			<tr>
				<td>
					Password:
				</td>
				<td>
					<input id="txt_psd" type="password" name="password" />
				</td>
			</tr>
			<tr>
				<td>
					<a href="#" class="easyui-linkbutton" iconcls="icon-ok" onclick="login()">Login</a>
				</td>
				<td>
					<a href="#" class="easyui-linkbutton" iconcls="icon-cancel">Cancel</a>
				</td>
			</tr>
		</table>
	</div>
</form>
</body>
<script type="text/javascript">
	function login() {
		var datauser = GetJsonData();
		$.ajax({
			type: "POST",
			contentType: "application/json; charset=utf-8",
			dataType: 'json',
			url: "${pageContext.request.contextPath}/mongo/login",
			data: JSON.stringify(datauser),
			success: function (data) {
				$.messager.alert("消息", data, "info");
			},
			error: function () {
				alert("消息", "错误！", "info");
			}
		});
	}
	function GetJsonData() {
		var name = $("#txt_name").val();
		var psd = $("#txt_psd").val();
		var json = {
			"name": name,
			"password": psd
		};
		return json;
	}
</script>
</html>