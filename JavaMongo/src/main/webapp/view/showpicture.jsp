<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="${pageContext.request.contextPath}/images/123.ico" type="image/x-icon">
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
</head>
<body style="background-image: url('/images/sky.jpg');background-repeat:no-repeat;background-size: 100%">
        <form id="pictureform" method="post" enctype="multipart/form-data" hidden="hidden">
            <input type="button" value="submit">
        </form>
    <div>
        <input type="button" value="UPLOAD" onclick="showform()" />
    </div>
</body>
<script type="text/javascript">
    function showform(){
        document.getElementById('pictureform').removeAttribute('hidden');
    }
</script>
</html>
