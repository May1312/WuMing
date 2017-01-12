<%--
  Created by IntelliJ IDEA.
  User: yhang
  Date: 2017/1/12
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>weibo</title>
    <link rel="icon" href="${pageContext.request.contextPath}/images/123.ico" type="image/x-icon">
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
</head>
<body>
    <h3>welcome to here</h3>
</body>
<script type="application/javascript">
    $(function(){
        /*$.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/mongo/showpage",
            success: function(data){
                alert(data);
            }
        })*/
        window.location.href="${pageContext.request.contextPath}/mongo/showpage";
    })
</script>
</html>
