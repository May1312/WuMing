<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%--        easyui 获取当前页，每页显示条数
            var currentPage = $('#dg').datagrid('options').pageNumber;
            var pagerSize = $('#dg').datagrid('options').pageSize;
            alert("当前页:"+currentPage+",显示条数:"+pagerSize);
--%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="keywords" content="jquery,ui,easy,easyui,web">
    <meta name="description" content="easyui help you build your web page easily!">
    <link rel="icon" href="${pageContext.request.contextPath}/images/123.ico" type="image/x-icon">
    <title>welcome</title>
    <link rel="stylesheet" type="text/css" href="../css/easyui.css">
    <link rel="stylesheet" type="text/css" href="../css/icon.css">
    <link rel="stylesheet" type="text/css" href="../css/demo.css">
    <style type="text/css">
        #fm {
            margin: 0;
            padding: 10px 30px;
        }

        .ftitle {
            font-size: 14px;
            font-weight: bold;
            color: #666;
            padding: 5px 0;
            margin-bottom: 10px;
            border-bottom: 1px solid #ccc;
        }

        .fitem {
            margin-bottom: 5px;
        }

        .fitem label {
            display: inline-block;
            width: 80px;
        }
    </style>
    <script type="text/javascript" src="../assets/js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <%--picture 压缩--%>
    <script src="${pageContext.request.contextPath}/js/lrz/lrz.all.bundle.js"></script>
    <script type="text/javascript">
        var url;
        function newUser() {
            $('#dlg').dialog('open').dialog('setTitle', 'New User');
            $('#fm').form('clear');
            /*单选默认选中*/
            $('input[name=sex]').get(0).checked = true;
        }
        /* 方法 表单数据转成json对象 */
        $.fn.serializeObject = function () {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function () {
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
        function editUser() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                /*表格中的性别属性如何转换为数值,会修改表格数据---*/
                if (row['sex'] == 'Male' || row['sex'] == 1) {
                    row['sex'] = 1;
                } else
                    row['sex'] = 0;
                $('#fm').form('clear');
                $('#dlg').dialog('open').dialog('setTitle', 'Edit User');
                $('#fm').form('load', row);
                url = 'update_user.php?id=' + row.id;
            }
        }
        function saveUser() {
            //为空不提交
            if ($('#fm').form('validate')) {
                var data = $('#fm').serializeObject();
                $.ajax({
                    async : true,
                    dataType: 'json', /* 请求数据类型 */
                    type: 'post',
                    contentType: 'application/json; charset=utf-8', /* 定义返回数据类型 */
                    data: JSON.stringify(data), /* 格式化json */
                    url: '/mongo/receive',
                    success: function (result) {
                        if (result.status == 200) {
                            $('#dlg').dialog('close');		// close the dialog
                            window.location.reload()// reload the user data
                        } else {
                            $.messager.show({
                                title: 'Error',
                                msg: result.msg
                            });
                        }
                    }
                });
                /*input框缓存*/
                $('#fm').form('clear');
            }
        }
        function removeUser() {
            var row = $('#dg').datagrid('getSelected');
            if (row) {
                $.messager.confirm('Confirm', 'Are you sure you want to remove this user?', function (r) {
                    if (!r) {
                    } else {
                        $.ajax({
                            type: 'post',
                            dataType: 'json',
                            url: '${pageContext.request.contextPath}/mongo/remove/' + row.userId + '?_method=DELETE',
                            success: function (msg) {
                                $('#dlg').dialog('close');		// close the dialog
                                window.location.reload()
                            },
                            error: function () {
                                alert("系统繁忙，请稍后再试!！");
                                return false;
                            }
                        })
                    }
                })
            }
        }
        function checkName(name){
            if(name) {
                $.ajax({
                    contentType: 'application/json',
                    type: 'get',
                    async: true,
                    dataType: 'json',
                    url: '/mongo/checkName?' + "name=" + name,
                    success: function (dataResult, textStatus) {
                        if (dataResult['count'] > 0) {
                            $.messager.alert('warning', 'The name already exist!');
                            //置空input value值
                            $('#name').val("");
                        }
                    },
                    error: function () {
                        alert("系统繁忙，请稍后再试！");
                    }
                })
            }
        }
        $(function(){
            var dlg = $("#dialog").dialog({
                /* resizable: true,
                 autoOpen: false,
                 modal: true,*/
                title: '设置头像',
                width: 400,
                height: 200,
                closed: true,
                cache: false,
                modal: true

            });
            $("input[name='dialoginput']").click(function () {
                $("#dialog").dialog('open');
            });
        });
        var file;
        function showphoto(e){
            file = e.target.files.item(0);
            var freader = new FileReader();
            freader.readAsDataURL(file);
            freader.onload = function(e) {
                //回显图片
                document.getElementById('ajaxdiv').removeAttribute('hidden');
                $("#myImg").attr("src",e.target.result);
                /*document.getElementById('myImg').setAttribute('src',submitData.img);*/
            }
        }
        function uploadphoto(){
            if ($('#uploadform').form('validate')){
                //yasuo.....
                lrz(file,{width:300})
                        .then(function (rst) {
                            var submitData={
                                img:rst.base64,
                                photoname:rst.origin.name,
                                fileLength:rst.base64.length
                            };
                            $.ajax({
                                type: "POST",
                                url: "http://localhost/mongo/photo",
                                data: submitData,
                                beforeSend: function(XMLHttpRequest){
                                    //showLoader();
                                },
                                success: function(data){

                                    if (data && data.code==201) {
                                        $.messager.alert("消息","上传成功！");
                                        clearform();

                                    }else{
                                        $.messager.alert("警告","上传失败！");
                                        clearform();
                                    }
                                }
                            })
                        })
            }
        }
        function clearform(){
            $("#uploadform").form("clear");
            //隐藏图片
            document.getElementById('ajaxdiv').setAttribute('hidden','hidden');
            $('#dialog').dialog('close');
        }
    </script>
</head>
<body>
<h2>Test CRUD Databash</h2>
<div class="demo-info" style="margin-bottom:10px">
    <div class="demo-tip icon-tip">&nbsp;</div>
    <h2>点击new user</h2>
</div>
<div id="showphoto" style="margin-bottom:10px;">
    <img src="${pageContext.request.contextPath}/images/trip.png" id="user_photo" width="50px" height="50px">
</div>
<div>
    <input type="button" value="设置头像" id="showdialog" name="dialoginput">
</div>
<%--弹窗ajax图片上传--%>
<div id="dialog" title="UPLOAD" style="height: 200px;width: 1024px" buttons="#show-button">
    <form id="uploadform" method="post" >
        <div id="ajaxdiv" hidden="hidden" align="center" style="margin-bottom:10px;">
            <img src="" id="myImg" width="50px" height="50px"></div>
        <div style="margin-bottom:10px;margin-top: 20px" align="center" >
            <%--class="easyui-validatebox"  设置required=true 才会生效--%>
            <input type="file" id="ajaxfile" accept="image/*" name="myfile" onchange="showphoto(event)" class="easyui-validatebox" required="true" />
        </div>
    </form>
</div>

<table id="dg" title="My Users" class="easyui-datagrid" style="width:700px;height:250px"
       toolbar="#toolbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true" data-options="total:114"
<%--url="${pageContext.request.contextPath}/mongo/show"--%>>
    <thead>
    <tr>
        <th field="userId" width="50" hidden="hidden">userId</th>
        <th field="name" width="50">name</th>
        <th field="age" width="50">age</th>
        <th field="sex" width="50">sex</th>
        <th field="registTime" width="90">registTime</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td hidden="hidden">${user.userId}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <c:if test="${user.sex==1}">
                <td id="sex">Male</td>
            </c:if>
            <c:if test="${user.sex==0}">
                <td id="sex">Female</td>
            </c:if>
            <td>${user.registTime}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">Remove User</a>
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">User Information</div>
    <form id="fm" novalidate>
        <div class="fitem" hidden>
            <label>UserId:</label>
            <input name="userId" class="easyui-validatebox">
        </div>
        <div class="fitem">
            <label>Name:</label>
            <input id="name" name="name" class="easyui-validatebox" required="true" validType="equals['#pwd']" onblur="checkName(this.value)" />
        </div>
        <div class="fitem">
            <label>Password:</label>
            <input id="password" name="password" class="easyui-validatebox" required="true" validType="equals['#pwd']" />
        </div>
        <div class="fitem">
            <label>Age:</label>
            <input id="age" name="age" class="easyui-validatebox" required="true"/>
        </div>
        <div class="fitem">
            <label>Sex:</label>
            <input type="radio" name="sex" value="1"/>Male
            <input type="radio" name="sex" value="0"/>Female<br>
        </div>
        <div class="fitem" hidden>
            <label>RegistTime:</label>
            <input name="registTime" class="easyui-validatebox">
        </div>
    </form>
</div>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
</div>
<div id="show-button">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="uploadphoto()">Upload</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearform()">Cancel</a>
</div>
<%--<jsp:include page="foot.jsp" />--%>
</body>
</html>