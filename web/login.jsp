<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>登录</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="assets/css/reset.css">
        <link rel="stylesheet" href="assets/css/supersized.css">
        <link rel="stylesheet" href="assets/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>

        <div class="page-container">
            <h1>Login</h1>
            <form action="${pageContext.request.contextPath}/login" method="post" onsubmit="checkForm()">
                <input type="text" id="username" name="userName" class="username" placeholder="姓名" value="${user.userName}">
                <input type="password" id="password" name="password" class="password" placeholder="密码" value="${user.password}">
               <input type="submit" value="登录"> <br>
                <input type="checkbox" name="rememberMe" value=""/>记住密码
                <div class="error"><span>+</span></div><br>
                <span><font color="red" id="error">${errorInfo}</font> </span>
            </form>
            <div class="connect">
                <p>Or connect with:</p>
                <p>
                    <a class="facebook" href=""></a>
                    <a class="twitter" href=""></a>
                </p>
            </div>
        </div>
        <div align="center">帮助 <a href="http://www.cssmoban.com/" target="_blank" title="">天津</a></div>

        <!-- Javascript -->
        <script src="assets/js/jquery-1.8.2.min.js"></script>
        <script src="assets/js/supersized.3.2.7.min.js"></script>
        <script src="assets/js/supersized-init.js"></script>
        <script src="assets/js/scripts.js"></script>

    </body>

</html>
<script type="text/javascript">
          function checkForm() {
              var username = $("#username").val();
              var password = $("#password").val();
              if (username==null||username==""){
                  $("#error").html("姓名不能为空");
                  return false;
              }
              if (password==null||password==""){
                  $("#error").html("密码不能为空");
                  return false;
              }return true;
          }
</script>
