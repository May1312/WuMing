<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登陆</title>
        <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/md5.js"></script>
    </head>
    <body>
    <form id="form1" runat="server" action="${pageContext.request.contextPath}/mongo/login" method="post">
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
                        <input id="txt_psd" type="password" name="password" autocomplete="off" />
                        <input id="txt_psd2" type="password2" name="password" autocomplete="off" hidden="hidden" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="#" class="easyui-linkbutton" iconcls="icon-ok" onclick="login()">Login</a>  <%--ajax重定向302错误--%>
                        <%--<input type="submit" value="login">--%>
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

                    if(data.status==200){
                        window.location.href="/mongo/showpage";
                    }
                },
                error: function () {
                    alert("消息", "错误！", "info");
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