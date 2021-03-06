<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html class="no-js">
<head>
  <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Amaze UI Admin index Examples</title>
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="../../assets01/i/favicon.png">
<link rel="apple-touch-icon-precomposed" href="../../assets01/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="../../assets01/css/amazeui.min.css"/>
<link rel="stylesheet" href="../../assets01/css/admin.css">
<script src="../../assets01/js/jquery.min.js"></script>
<script src="../../assets01/js/app.js"></script>
</head>
<body>

<div class="am-cf admin-main"> 

<div class="nav-navicon admin-main admin-sidebar">
    
    
    <div class="sideMenu am-icon-dashboard" style="color:#aeb2b7; margin: 10px 0 0 0;"> 欢迎系统管理员：${sessionScope.user.username}</div>
    <div class="sideMenu">
    <%-- <c:forEach items="${map}" var="menu">
      <h3 class="am-icon-flag"><em></em> <a href="">${menu.key.description}</a></h3>
      <ul>
        <c:forEach items="${menu.value}" var="son">
        <li><a href="${son.permission}">${son.description}</a></li>
        </c:forEach>
      </ul>
     </c:forEach>--%>
    </div>
</div>

<div class=" admin-content">

<div class="admin">
   <div class="admin-index" align="center">
     <from > <input style="" type="hidden" id="id" value="${role.id}"/>
         角色名： <input  id = "name" ctype="text" value="${role.rolename}"><br>
       角色描述：<input  id = "desc" ctype="text" value="${role.roledesc}"><br>
         <Button onclick="saveRole()">保存 </Button>
     </from>
    </div>
</div>
</div>
</div>
</body>
</html>

<script type="text/javascript">
    function tuichu() {
        window.location = "/logout";
    }

    function saveRole() {
        var rolename = $("#name").val();
        var roledesc = $("#desc").val();

        var data = {
            "rolename" :rolename,
            "roledesc":roledesc
        }

        $.ajax({
            url:"/role/saverole",
            type:'POST',
            data:data,
            success:function () {
                alert("success")
            },
            error:function () {
                alert("false")
            }

        })
    }

</script>