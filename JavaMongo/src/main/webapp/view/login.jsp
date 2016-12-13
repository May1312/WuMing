<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>登陆</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />
        <!-- <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>-->
        <script src="${pageContext.request.contextPath}/js/md5.js"></script>
		<!-- Scripts -->
		<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/js/jquery.scrolly.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/js/skel.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/js/util.js"></script>
		<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
    </head>

    <body>
	<!-- Header -->
			<section id="header" class="dark">
				<header>
					<h1>Welcome to Tessellate</h1>
				</header>
				<footer>
					<a href="#first" class="button scrolly">Proceed to Login</a>
				</footer>
			</section>

		<!-- First -->
			<section id="first" class="main">
				<header>
					<div class="container">
						<h2>Please input your username and password</h2><br>
						<center>
						<form id="form1" action="${pageContext.request.contextPath}/mongo/login" method="post" a>
							<!--<div id="win" style="background: url('../picture/rain.gif') ">-->

											<input id="txt_name" type="text" name="name" style="width:120px;height: 30px" /><br>

											<input id="txt_psd" type="password" name="password" autocomplete="off" style="width:120px;height: 30px" />
											<input id="txt_psd2" type="password2" name="password" autocomplete="off" hidden="hidden" />


											<a href="#" class="easyui-linkbutton" iconcls="icon-ok" onclick="login()">Login</a>  <%--ajax重定向302错误--%>
											<%--<input type="submit" value="login">--%>

											<a href="#">Cancel</a>

						</form>
						</center>
					</div>
				</header>
			</section>

		<!-- Footer-->
			<section id="footer">
				<div class="copyright">
					<ul class="menu">
						<li>&copy; It's My First Web.</li><li>Design: <a href="#">HANG</a></li>
					</ul>
				</div>
			</section>

	</body>
    <script type="text/javascript">
        function login() {
            var name = $("#txt_name").val();
            var psd = $("#txt_psd").val();
            if(name==null || name == ""){
                alert("Please input your name");
                return;
            }
            if(psd==null || psd == ""){
                alert("Please input your password");
                return;
            }

           // $('#form1').submit();

             var datauser = GetJsonData();
            $.ajax({

                type: "POST",
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                url: "${pageContext.request.contextPath}/mongo/login",
                data: JSON.stringify(datauser),
                success: function (data) {
                    if(data){
                        var data = eval(data);
                        if(data.status==200){
                            window.location.href="/mongo/showpage";
                        }else{
                            alert(data.msg);
                        }
                    }
                },
                error: function (data) {
                    //将json字符串转对象
                    var data1 = JSON.parse(data.responseText);
                    alert(data1.msg);
                    window.location.reload();
                }
            });
        }
        function GetJsonData() {
            var name = $("#txt_name").val();
            var psd = $("#txt_psd").val();
            psd = hex_md5(psd);
            var json = {
                "name": name,
                "password": psd
            };
            return json;
        }
    </script>
</html>