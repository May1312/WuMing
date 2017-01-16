<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<html xmlns:wb="http://open.weibo.com/wb" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href="${pageContext.request.contextPath}/images/trip.png" type="image/x-icon">
        <title>十年</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ie8.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ie9.css" />

    </head>
        <style type="text/css">
            .login-form {
                width:400px;
                height:300px;
                margin:70px auto 0;
                padding-top:1px;
                position:relative;
                background-image:-*-linear-gradient(top,rgb(255,255,255),rgb(242,242,242));
                box-shadow:0px 0px 2px 2px rgba(21,62,78,0.8);
            }
            /*.login-form:before {
                content:"";
                position:absolute;
                top:-50px;
                left:150px;
                width:102px;
                height:102px;
                padding:2px;
                border:2px solid rgb(21,62,78);
                background:#fff url("../images/trip.png") no-repeat;
                background-size:102px 102px;
            }*/
            .not-registered {
                position:absolute;
                color:rgb(153,153,153);
                font-weight:bold;
                font-size:13px;
                top:calc(100% + 20px);
                background-color:rgb(255,255,255);
                width:400px;
                height:46px;
                margin:0 auto;
                line-height:46px;
                text-align: center;
                box-shadow:0 3px 3px rgba(21,62,78,0.8);
            }
            .not-registered a {
                margin-left:5px;
                text-decoration: none;
                color:rgb(52,119,182);
                cursor: pointer;
            }
            .login-form div {
                width:216px;
                height:28px;
                margin:20px auto;
                position:relative;
                line-height:28px;
                border:none;
            }
            .login-form .user-icon,
            .login-form .password-icon {
                display:inline-block;
                font-family: 'loginform-icon';
                font-size:15px;
                text-align:center;
                line-height:28px;
                color:rgb(153,153,153);
                position:absolute;
                left:1px;
                top:1px;
                background-color:rgb(255,255,255);
                border:none;
                border-right:1px solid rgb(229,229,232);
                width:30px;
                height:28px;
                transition: all 300ms linear;
            }
            .login-form .username input, .login-form .password input {
                height:32px;
                width:calc(218px);
                padding-left:40px;
                border-radius:2px;
                border:1px solid;
                border-color:rgb(229,229,232) rgb(220,220,221) rgb(213,213,213) rgb(220,220,221);
                display:block;
                transition: all 300ms linear;
            }

            .login-form .icon:before, .login-form .icon:after {
                content:"";
                position:absolute;
                top:10px;
                left:30px;
                width:0;
                height:0;
                border:4px solid transparent;
                border-left-color:rgb(255,255,255);
            }
            .login-form .icon:before {
                top:9px;
                border:5px solid transparent;
                border-left-color:rgb(229,229,232);
            }
            .login-form .username input:focus, .login-form .password input:focus {
                border-color:rgb(69,153,228);
                box-shadow:0 0 2px 1px rgb(200,223,244);
            }
            .login-form .username input:focus + span, .login-form .password input:focus + span {
                color:rgb(51,51,51);
            }
            .login-form .username input:focus + span:after, .login-form .password input:focus + span:after {
                border-left-color:rgb(250,250,250);
            }

            .login-form .account-control label {
                margin-left:24px;
                font-size:12px;
                font-family: Arial, Helvetica, sans-serif;
                cursor:pointer;
            }
            .login-form button[type="submit"] {
                color:#fff;
                font-weight:bold;
                float:right;
                width:68px;
                height:30px;
                position:relative;
                background-size:66px 28px,68px 29px;
                border:none;
                border-top:1px solid rgb(52,118,181);
                border-radius:2px;
                box-shadow:inset 0 1px 0 rgb(86,174,251);
                text-shadow:0 1px 1px rgb(51,113,173);
                transition: all 200ms linear;
            }
            .login-form button[type="submit"]:hover {
                text-shadow:0 0 2px rgb(255,255,255);
                box-shadow:inset 0 1px 0 rgb(86,174,251),0 0 10px 3px rgba(74,162,241,0.5);
            }
            .login-form button[type="submit"]:active {
            }

            .login-form .account-control input {
                width:0px;
                height:0px;
            }
            .login-form label.check {
                position:absolute;
                left:0;
                top:50%;
                margin:-8px 0;
                display:inline-block;
                width:16px;
                height:16px;
                line-height: 16px;
                text-align:center;
                border-radius:2px;
                background-size:14px 14px,16px 16px;
            }
            .login-form .account-control input:checked + label.check:before {
                content:attr(data-on);
                font-family:loginform-icon;
            }
            @font-face {
                font-family: 'loginform-icon';
                src: url("../images/font/loginform-icon.eot");
                src: url("../images/font/loginform-icon.eot?#iefix") format('embedded-opentype'),
                url("../images/font/loginform-icon.woff") format('woff'),
                url("../images/font/loginform-icon.ttf") format('truetype'),
                url("../images/font/loginform-icon.svg#loginform-icon") format('svg');
                font-weight: normal;
                font-style: normal;
            }
        </style>
    </head>
    <!-- Scripts -->
      <%--<script src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>--%>

        <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
        <%--<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>--%>
        <script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.jsonp.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.scrolly.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/skel.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/util.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
        <script src="${pageContext.request.contextPath}/js/md5.js"></script>
        <script src="${pageContext.request.contextPath}/js/prefixfree.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/ie/html5shiv.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
        <%--picture 压缩--%>
        <script src="${pageContext.request.contextPath}/js/lrz/lrz.all.bundle.js"></script>
        <script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=4141767579" type="text/javascript" charset="utf-8"></script>
    <body>
	<!-- Header -->
			<section id="header" class="dark" >
				<header>
					<h1>Welcome to MyWeb</h1>
                    <p>你好,你当前的ip为:${ip}.  <a href="http://120.77.169.190:82/psb.jpg">HTML5</a></p>
				</header>
				<footer>
					<a href="#first" class="button scrolly" onclick="changPage('div2','div3')">Proceed to Login</a>
				</footer>
			</section>
         </div>
		<!-- First -->
    <div id="div2">
        <div id="div1">
			<section id="first" class="main">
				<header>
						<h2>Please input your username and password</h2>
                    <h4>我猜着你的名字刻在了墙上,你画了我的摸样对着弯月亮.</h4>
						<center>
                            <form id="form1" action="${pageContext.request.contextPath}/mongo/login" method="post" class="login-form">
                                <div id="show_photot" style="height: 102px;width: 102px;padding: 2px;border: 1px solid #041024">
                                    <%--pictur suituation the div to set the height and width 100%--%>
                                    <img id="show_img" src="/images/sky.jpg" width="100%" height="100%">
                                </div>
                                <div id="mydiv" style="color:#F00;width: 100%" hidden="hidden"></div>
                                <div class="username">
                                    <input id="txt_name" type="text" name="name" autocomplete="on" onblur="checkName(this.value)" placeholder="name"/>
                                    <span class="user-icon icon">u</span>
                                    <input id="mymsg" type="hidden" value="" />
                                </div>
                                <div class="password">
                                    <input id="txt_psd" type="password" name="password" autocomplete="off" placeholder="password"/><br>
                                    <span class="password-icon icon">p</span>
                                </div>
                                <input  type="button" style="width:120px;height: 49px;padding-top: 8px;padding-left: 4px;padding-right: 4px" value="Login" onclick="login()" />
                                <%--<a href="#" class="easyui-linkbutton" iconcls="icon-ok" onclick="login()">Login</a>--%>  <%--ajax重定向302错误--%>
                                <%--<input type="submit" value="login">--%>
                                <%--<a href="#second" onclick="changPage('div3','div2')">Regist</a>--%>
                                <input type="button" src="#second" style="width:120px;height: 49px;padding-top: 8px;padding-left: 4px;padding-right: 4px" value="Regist" onclick="changPage('div3','div2')"/>
                                <wb:login-button type="3,2" onclick="weiboLogin()">登录按钮</wb:login-button>
                               <%-- <a href="https://api.weibo.com/oauth2/authorize?client_id=4141767579&redirect_uri=http://127.0.0.1/login/weibo/code&response_type=code
" target="_self">weibo login</a>--%>
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
                                    <form id="fm" novalidate enctype="multipart/form-data">
                                        <div id="mydiv2" style="color:#F00" ></div>
                                        <div class="fitem" hidden>
                                            <label>UserId:</label>
                                            <input name="userId" class="easyui-validatebox">
                                        </div>
                                        <div id="img_show" hidden="hidden" style="margin-bottom:10px;" align="center"><h4>USER PHOTO:</h4><img src="" id="myImg" width="50px" height="50px"></div>
                                        <%--add user picture--%>
                                        <div style="margin-bottom:10px;">
                                            <input id="name" name="name" type="text" placeholder="Name" required="true" validType="equals['#pwd']" onblur="checkName(this.value)" />
                                        </div>
                                        <div style="margin-bottom:10px;">
                                            <input id="psd" name="password" placeholder="Password" type="password" required="true" />
                                        </div>
                                        <div style="margin-bottom:10px;">
                                            <input id="age" name="age" placeholder="Age" type="text" required="true"/>
                                        </div>
                                        <div style="margin-bottom:10px;">
                                            <label>Sex:</label>
                                            <input type="radio" name="sex" value="1"/>Male
                                            <input type="radio" name="sex" value="0" checked />Female<br>
                                        </div>
                                        <div class="fitem" hidden>
                                            <label>RegistTime:</label>
                                            <input name="registTime" class="easyui-validatebox">
                                        </div>
                                        <div style="margin-bottom:10px;box-shadow:0px 0px 2px 2px rgba(95, 97, 174, 0.8);width: 35%;background-color: #a8e776;color: #000000;">
                                            <label for="btnLogin2">上传用户头像</label>
                                            <%--弹框form表单的方式 <input type="button" style="position:absolute;clip:rect(0 0 0 0);" id="btnLogin" name="btnLogin" />--%>
                                            <input type="button" style="position:absolute;clip:rect(0 0 0 0);" id="btnLogin2" name="btnLogin2" />
                                        </div>
                                        <div style="margin-bottom:10px;">
                                            <input  type="button" style="width:120px;height: 49px;padding-top: 8px;padding-left: 4px;padding-right: 4px" value="Save" onclick="saveUser()"  />
                                            <input  type="button" value="Cancel" style="width:120px;height: 49px;padding-top: 8px;padding-left: 4px;padding-right: 4px" />
                                        </div>
                                    </form>
                                <%--弹窗图片上传表单--%>
                                <div id="dialog" title="UPLOAD" style="height: 200px;width: 1024px">
                                    <div id="pdiv" hidden="hidden" align="center" style="margin-bottom:10px;"><img src="" id="myImg2" width="50px" height="50px"></div>
                                    <form name="upload" method="post" enctype="multipart/form-data" action="http://127.0.0.1:81/photo/upload" >
                                        <div style="margin-bottom:10px;">
                                            <input type="file" id="file" accept="image/*" name="myfile" onchange="showphoto2(event)" required />
                                        </div>
                                        <div style="margin-bottom:10px;">
                                            <input type="submit" style="width:50px;height: 30px;" value="提交" />
                                        </div>
                                        <%--<input id="file" type="file" accept="image/*" />--%>
                                    </form>
                                </div>
                                <%--弹窗ajax图片上传--%>
                                <div id="dialog2" title="UPLOAD" style="height: 200px;width: 1024px">
                                    <div id="ajaxdiv" hidden="hidden" align="center" style="margin-bottom:10px;"><img src="" id="myImg3" width="50px" height="50px"></div>
                                    <form name="upload" method="post" enctype="multipart/form-data" action="http://127.0.0.1:81/photo/upload" >
                                        <div style="margin-bottom:10px;">
                                            <input type="file" id="ajaxfile" accept="image/*" name="myfile" onchange="showphoto3(event)" required />
                                        </div>
                                    </form>
                                </div>
                                </div>
                        </section>
                    </div>
                    <div class="8u 12u(narrow)">
                        <div class="row">
                            <div class="6u"><a href="#" class="image fit"><img src="${pageContext.request.contextPath}/images/pic01.jpg" alt="" /></a></div>
                            <div class="6u"><a href="#" class="image fit"><img src="${pageContext.request.contextPath}/images/pic02.jpg" alt="" /></a></div>
                            <div class="6u"><a href="#" class="image fit"><img src="${pageContext.request.contextPath}/images/pic03.jpg" alt="" /></a></div>
                            <div class="6u"><a href="#" class="image fit"><img src="${pageContext.request.contextPath}/images/pic04.jpg" alt="" /></a></div>
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
						<li>&copy; It's My First Web.</li><li>Design: <a href="http://120.77.169.190:82/psb.jpg">HANG</a></li>
					</ul>
			</section>
        <% String code = request.getParameter("code");%>
            <%=code%>

	</body>
    <script type="text/javascript">
        function login() {
            document.getElementById('div1').setAttribute('hidden','hidden')
            var name = $("#txt_name").val();
            var psd = $("#txt_psd").val();
            if(name==null || name == ""){
                document.getElementById('mydiv').removeAttribute('hidden');
                document.getElementById('mydiv').innerHTML = 'Please input your name！'
               /* alert("Please input your name");*/
                return;
            }
            if(psd==null || psd == ""){
                document.getElementById('mydiv').removeAttribute('hidden');
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
                            document.getElementById('mydiv').removeAttribute('hidden');
                            document.getElementById('mydiv').innerHTML = 'The name is not exist!';
                            //置空input value值
                            $('#txt_name').val("");
                        }else{
                            if (dataResult['count'] > 0) {
                                document.getElementById('mydiv2').innerHTML = 'The name is exist,Please login!';
                                //置空input value值
                                $('#name').val("");
                                var photo_url = dataResult['url'];
                                /*var div=document.querySelector('#form1');
                                var background=window.getComputedStyle(div,'::before').getPropertyValue('background');
                                alert(background);*/
                                /*$('#form1').append("<style>.login-form::before{ background:url("+photo_url+")}</style>");*/
                                document.getElementById('show_img').setAttribute('src',photo_url);
                               /* $("#form1").css("background",'url(' + photo_url + ')');*/
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
        function showphoto(e){
            // this.files[0] 是用户选择的文件
            /*for (var i = 0; i < e.target.files.length; i++) {*/
                var file = e.target.files.item(0);
               /* if (!(/^image\/.*$/i.test(file.type))) {
                    continue; //不是图片 就跳出这一次循环
                }*/
                //实例化FileReader API
                var freader = new FileReader();
                //freader.readAsDataURL(file);
                /*freader.onload = function(e) {
                    document.getElementById('img_show').removeAttribute('hidden');
                    $("#myImg").attr("src",e.target.result);
                }*/
           /* }*/
                var a = 0;
            var file2 = e.target.files.length;
            var file = e.target.files.item(0);
            /*var a = this.files[0];*/
            lrz(file, {width: 1024})
                    .then(function (rst) {
                        // 把处理的好的图片给用户看看呗
                        freader.readAsDataURL(file);
                        //压缩图片
                        var src = rst.base64;
                        freader.onload = function(e) {
                         document.getElementById('img_show').removeAttribute('hidden');
                         $("#myImg").attr("src",src);
                         }

                        return rst;
                    })
                    .catch(function (err) {
                        // 万一出错了，这里可以捕捉到错误信息
                        // 而且以上的then都不会执行

                        alert(err);
                    })
                    .always(function () {
                        // 不管是成功失败，这里都会执行

                    });
        }
        function showphoto2(e){
            var freader = new FileReader();
            var file = e.target.files.item(0);
            lrz(file, {width: 1024})
                    .then(function (rst) {
                        // 把处理的好的图片给用户看看呗
                        freader.readAsDataURL(file);
                        //压缩图片
                        var src = rst.base64;
                        freader.onload = function(e) {
                            document.getElementById('pdiv').removeAttribute('hidden');
                            $("#myImg2").attr("src",src);
                        }

                        return rst;
                    })
                    .catch(function (err) {
                        // 万一出错了，这里可以捕捉到错误信息
                        // 而且以上的then都不会执行

                        alert(err);
                    })
                    .always(function () {
                        // 不管是成功失败，这里都会执行

                    });
        }
        function showphoto3(e){
            var freader = new FileReader();
            var file = e.target.files.item(0);
            lrz(file,{width:300})
                    .then(function (rst) {
                        var submitData={
                            img:rst.base64,
                            photoname:rst.origin.name,
                            fileLength:rst.base64.length
                        };
                        $.ajax({
                            type: "POST",
                            url: "http://localhost/login/photo",
                            data: submitData,
                            /*dataType:"jsonp",
                            jsonpCallback:"hang",*/
                            beforeSend: function(XMLHttpRequest){
                                //showLoader();
                            },
                            success: function(data){

                                if ("1" == data.error) {
                                    alert(data.message);
                                    return false;
                                }else{
                                    alert(data.thumbnail);

                                    return false;
                                }
                            }
                        })
                    })}
        $(function(){
           var dlg = $("#dialog").dialog({resizable: true,
               autoOpen:false,
               modal: true,});
            $("input[name='btnLogin']").click(function(){
                /*$( "#dialog" ).dialog( "open" );*/
                /*dlg.dialog('open');
                $("#dialog").dialog({ modal: true, overlay: { opacity: 0.5, background: "black" } });*/

                $("#dialog").dialog('open');
            });

            $("input[name='tj']").click(function(){
                $( "#dialog" ).dialog( "close" );
                window.location.reload();
            });

            var dlg = $("#dialog2").dialog({resizable: true,
                autoOpen:false,
                modal: true,});
            $("input[name='btnLogin2']").click(function(){
                $("#dialog2").dialog('open');
            });

            $("input[name='tj2']").click(function(){
                $( "#dialog" ).dialog( "close" );
                window.location.reload();
            });
        });
        function weiboLogin(){
            $.ajax({
                type:'GET',
                url:"/login/weibo",
                success:function(data){
                    window.location.href=data;
                }
            })
            /*var popup = window.open(location, '_self', '');
            popup.close();*/
        }
    </script>
</html>