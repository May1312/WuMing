<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1" />
        <script src="${pageContext.request.contextPath}/assets/js/ie/html5shiv.js"></script>
        <link rel="icon" href="${pageContext.request.contextPath}/images/123.ico" type="image/x-icon">
        <title>登陆</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ie8.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ie9.css" />
        <script src="${pageContext.request.contextPath}/js/md5.js"></script>
    </head>
    <!-- Scripts -->
        <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.scrolly.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/skel.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/util.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
    <body>
	<!-- Header -->
        <div id="div1">
			<section id="header" class="dark" >
				<header>
					<h1>Welcome to MyWeb</h1>
                    <p>你好,你当前的ip为:${ip}.  <a href="${pageContext.request.contextPath}/images/psb.jpg">HTML5</a></p>
				</header>
				<footer>
					<a href="#first" class="button scrolly" onclick="changPage('div2','div3')">Proceed to Login</a>
				</footer>
			</section>
         </div>
		<!-- First -->
    <div id="div2" >
			<section id="first" class="main">
				<header>
						<h2>Please input your username and password</h2>
                    <h4>我猜着你的名字刻在了墙上,你画了我的摸样对着弯月亮.</h4>
						<center>
						<form id="form1" action="${pageContext.request.contextPath}/mongo/login" method="post" a>
							<!--<div id="win" style="background: url('../picture/rain.gif') ">-->

                                            <div id="mydiv" style="color:#F00" ></div>
											<input id="txt_name" type="text" name="name" style="width:120px;height: 30px" onblur="checkName(this.value)" placeholder="name"/><br>
                                            <input id="mymsg" type="hidden" value="" style="width:120px;height: 30px" />

											<input id="txt_psd" type="password" name="password" autocomplete="off" style="width:120px;height: 30px" placeholder="password"/><br>

                                            <input  type="button" style="width:120px;height: 49px;padding-top: 8px;padding-left: 4px;padding-right: 4px" value="Login" onclick="login()" />
											<%--<a href="#" class="easyui-linkbutton" iconcls="icon-ok" onclick="login()">Login</a>--%>  <%--ajax重定向302错误--%>
											<%--<input type="submit" value="login">--%>
											<%--<a href="#second" onclick="changPage('div3','div2')">Regist</a>--%>
                                            <input type="button" src="#second" style="width:120px;height: 49px;padding-top: 8px;padding-left: 4px;padding-right: 4px" value="Regist" onclick="changPage('div3','div2')"/>

						</form>
						</center>
				</header>
			</section>
    </div>
    <!-- Second -->
    <div id="div3" hidden="hidden">
    <section id="second" class="main">
        <%--<header>
            <div class="container">
                <h2>Welcome to Regist As Member</h2>
            </div>
        </header>--%>
        <div class="content dark style2">
            <div class="container">
                <div class="row">
                    <div class="4u 12u(narrow)">
                        <section>
                            <h3>Augue vivamus sed ipsum commodo lorem dolor</h3>
                            <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
                                 closed="true" buttons="#dlg-buttons">
                                <div class="ftitle">Pleasu Input Your Information</div>
                                <form id="fm" novalidate>
                                    <div id="mydiv2" style="color:#F00" ></div>
                                    <div class="fitem" hidden>
                                        <label>UserId:</label>
                                        <input name="userId" class="easyui-validatebox">
                                    </div>
                                    <div class="fitem">
                                        <input id="name" name="name" type="text" placeholder="Name" required="true" validType="equals['#pwd']" onblur="checkName(this.value)" />
                                    </div>
                                    <div class="fitem">
                                        <input id="psd" name="password" placeholder="Password" type="password" required="true" />
                                    </div>
                                    <div class="fitem">
                                        <input id="age" name="age" placeholder="Age" type="text" required="true"/>
                                    </div>
                                    <div class="fitem">
                                        <label>Sex:</label>
                                        <input type="radio" name="sex" value="1"/>Male
                                        <input type="radio" name="sex" value="0" checked />Female<br>
                                    </div>
                                    <div class="fitem" hidden>
                                        <label>RegistTime:</label>
                                        <input name="registTime" class="easyui-validatebox">
                                    </div>
                                </form>
                                </div>
                            <div align="center">
                            <footer>
                                <input  type="button" style="width:120px;height: 49px;padding-top: 8px;padding-left: 4px;padding-right: 4px" value="Save" onclick="saveUser()"  />
                                <%--<a href="#" onclick="saveUser()">Save</a>--%>
                                <%--<a href="#">Cancel</a>--%>
                                <input  type="button" value="Cancel" style="width:120px;height: 49px;padding-top: 8px;padding-left: 4px;padding-right: 4px" />
                            </footer>
                            </div>
                        </section>
                    </div>
                    <div class="8u 12u(narrow)">
                        <div class="row">
                            <div class="6u"><a href="#" class="image fit"><img src="${pageContext.request.contextPath}/images/pic01.jpg" alt="" /></a></div>
                            <div class="6u"><a href="#" class="image fit"><img src="${pageContext.request.contextPath}/images/pic02.jpg" alt="" /></a></div>
                            <div class="6u"><a href="#" class="image fit"><img src="${pageContext.request.contextPath}/images/pic03.jpg" alt="" /></a></div>
                            <div class="6u"><a href="#" class="image fit"><img src="${pageContext.request.contextPath}/images/pic04.jpg" alt="" /></a></div>
                            <%--<div class="6u"><a href="#" class="image fit"><img src="images/pic05.jpg" alt="" /></a></div>
                            <div class="6u"><a href="#" class="image fit"><img src="images/pic06.jpg" alt="" /></a></div>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    </div>
		<!-- Footer-->
			<section id="footer" >
					<ul class="menu">
						<li>&copy; It's My First Web.</li><li>Design: <a href="${pageContext.request.contextPath}/images/psb.jpg">HANG</a></li>
					</ul>
			</section>
	</body>
    <script type="text/javascript">
        function login() {
            document.getElementById('div1').setAttribute('hidden','hidden')
            var name = $("#txt_name").val();
            var psd = $("#txt_psd").val();
            if(name==null || name == ""){
                document.getElementById('mydiv').innerHTML = 'Please input your name！'
               /* alert("Please input your name");*/
                return;
            }
            if(psd==null || psd == ""){
                document.getElementById('mydiv').innerHTML = 'Please input your password！'
                /*alert("Please input your password");*/
                return;
            }

           // $('#form1').submit();

             var datauser = GetJsonData1();
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
                            window.location.href="${pageContext.request.contextPath}/mongo/showpage";
                            return false
                        }else{
                            alert(data.msg);
                        }
                    }
                },
                error: function (data) {
                    //将json字符串转对象
                    var data1 = JSON.parse(data.responseText);
                    alert(data1.msg);
                    /*window.location.reload();*/
                    $('#txt_psd').val('');
                    /*$('#mymsg').val('密码错误');
                    $('#mymsg').prop('type','text')*/
                    document.getElementById('mydiv').innerHTML = '密码错误哦！'

                }
            });
        }
        function GetJsonData1() {
            var name = $("#txt_name").val();
            var psd = $("#txt_psd").val();
            psd = hex_md5(psd);
            var json = {
                "name": name,
                "password": psd
            };
            return json;
        }
        function checkName(name){
            //首行   尾行的 空格替除
            name = name.replace(/^\s+|\s+$/g, '');
            if(name) {
                $.ajax({
                    contentType: 'application/json',
                    type: 'get',
                    async: true,
                    dataType: 'json',
                    url: '/login/checkName?' + "name=" + name,
                    success: function (dataResult, textStatus) {
                        if (dataResult['count'] == 0) {
                            document.getElementById('mydiv').innerHTML = 'The name is not exist!';
                            //置空input value值
                            $('#txt_name').val("");
                        }else{
                            if (dataResult['count'] > 0) {
                                document.getElementById('mydiv2').innerHTML = 'The name is exist,Please login!';
                                //置空input value值
                                $('#name').val("");
                            }
                            yourname=true;
                        }
                    },
                    error: function () {
                        alert("系统繁忙，请稍后再试！");
                    }
                })
            }
        }
/*        function screenCtrl(objname) {
            screenWidth = screen.availWidth; //屏幕宽度
            screenHeight = screen.availHeight; //屏幕高度
            $("#divId").css("height",screenHeight);
            $("#divId").css("width",screenWidth);
            $("img").css("height",screenHeight);
            $("img").css("width",screenWidth);
//				document.getElementById(objname).style.height = screenHeight + 'px';
//				document.getElementById(objname).style.width = screenWidth + 'px';
        }
        $(document).ready(function() {
            screenCtrl('div3');
        });*/
        function changPage(first,Second){
            document.getElementById(first).removeAttribute('hidden');
            document.getElementById(Second).setAttribute('hidden','hidden');

        }
        function saveUser() {
            document.getElementById('div1').setAttribute('hidden','hidden')
            //为空不提交
            var name =  $('#name').val();
            //首行   尾行的 空格替除
           name = name.replace(/^\s+|\s+$/g, '');
            if(!name){
                document.getElementById('mydiv2').innerHTML = 'Please input your name!'
                return;
            }
            var password = $('#psd').val();
            if(!password){
                document.getElementById('mydiv2').innerHTML = 'Please input your password!'
                return;
            }
            password =  hex_md5($('#psd').val());
            var age =  $('#age').val();
            var sex =  $('input:radio:checked').val();

                var data = GetJsonData(name,password,age,sex);
                $.ajax({
                    async : true,
                    dataType: 'json', /* 请求数据类型 */
                    type: 'post',
                    contentType: 'application/json; charset=utf-8', /* 定义返回数据类型 */
                    data: JSON.stringify(data), /* 格式化json */
                    url: '/login/regist',
                    success: function (result) {
                        if (result.status == 200) {
                            // redirect to new page
                            window.location.href='${pageContext.request.contextPath}/mongo/showpage';
                            return false;
                        } else {
                            alert("error");
                        }
                    }
                });
                /*input框缓存*/
                $('#fm').reset;
        }
        function GetJsonData(name,password,age,sex) {
            var json = {
                "name": name,
                "password": password,
                "age": age,
                "sex": sex
            };
            return json;
        }
    </script>
</html>