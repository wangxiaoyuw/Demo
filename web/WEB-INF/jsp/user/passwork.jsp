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
<header class="am-topbar admin-header">
  <div class="am-topbar-brand"><img src="../../assets01/i/logo.png"></div>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
    <ul class="am-nav am-nav-pills am-topbar-nav admin-header-list">

   <li class="am-dropdown tognzhi" data-am-dropdown><button onclick="tuichu()">退出</button>
  <button class="am-btn am-btn-primary am-dropdown-toggle am-btn-xs am-radius am-icon-bell-o" data-am-dropdown-toggle> 消息管理<span class="am-badge am-badge-danger am-round">6</span></button>
  <ul class="am-dropdown-content">

    <li class="am-dropdown-header">所有消息都在这里</li>
    <li><a href="#">未激活会员 <span class="am-badge am-badge-danger am-round">556</span></a></li>
    <li><a href="#">未激活代理 <span class="am-badge am-badge-danger am-round">69</span></a></li>
    <li><a href="#">未处理汇款</a></li>
    <li><a href="#">未发放提现</a></li>
    <li><a href="#">未发货订单</a></li>
    <li><a href="#">低库存产品</a></li>
    <li><a href="#">信息反馈</a></li>
    
  </ul>
</li>

 <li class="kuanjie">
 	
 	<a href="#">会员管理</a>          
 	<a href="#">奖金管理</a> 
 	<a href="#">订单管理</a>   
 	<a href="#">产品管理</a> 
 	<a href="#">个人中心</a> 
 	 <a href="#">系统设置</a>
 </li>

 <li class="soso">
 	
<p>   
	
	<select data-am-selected="{btnWidth: 70, btnSize: 'sm', btnStyle: 'default'}">
          <option value="b">全部</option>
          <option value="o">产品</option>
          <option value="o">会员</option>
          
        </select>

</p>

<p class="ycfg"><input type="text" class="am-form-field am-input-sm" placeholder="圆角表单域" /></p>
<p><button class="am-btn am-btn-xs am-btn-default am-xiao"><i class="am-icon-search"></i></button></p>
 </li>




      <li class="am-hide-sm-only" style="float: right;"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
    </ul>
  </div>
</header>

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
     <from ><input id="userid" type="hidden" value="${sessionScope.user.id}"/>
       原密码： <input  id = "oldpassword" ctype="text"><br>
         新密码： <input  id = "newpassword" ctype="text"><br>
         确认密码： <input  id = "password" ctype="text"><br>
         <Button onclick="save()">保存 </Button>
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

    function save() {
        var id = $("#userid").val();
        var oldpassword = $("#oldpassword").val();
        var newpassword = $("#newpassword").val();
        var password = $("#password").val();

        var data = {
            "oldpassword" :oldpassword,
            "id":id
        }

        $.ajax({
            url:"/user/oldpassword",
            type:'POST',
            data:{"oldpassword":oldpassword,"id":id},
            success:function (result) {

                if (result=="dui"){
                    alert("原密码正确")
                    if (newpassword==password&newpassword!=""&password!=""){
                        $.post("/user/savep",{"newpassword":newpassword, "id":id},function (success) {
                            alert(success);
                        })
                    }else {
                        alert("两次密码不一致")
                    }
                }else {
                    alert("erro")
                }
            }
        })
    }

</script>