<%--
  Created by IntelliJ IDEA.
  User: yhang
  Date: 2017/1/16
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/images/trip.png" type="image/x-icon">
    <title>绑定weibo</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/md5.js"></script>
</head>
<body>
    <form method="post">
       用户名： <input type="text" name="name" id="name"><br><br>
        密码： <input type="password" name="password" id="password"><br><br>
        <input type="button" value="提交" onclick="login()">
    </form>
</body>
<script type="application/javascript">
    function login() {
        var name = $("#name").val();
        var psd = $("#password").val();
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
        var name = $("#name").val();
        var psd = $("#password").val();
        psd = hex_md5(psd);
        var json = {
            "name": name,
            "password": psd
        };
        return json;
    }
</script>
</html>
