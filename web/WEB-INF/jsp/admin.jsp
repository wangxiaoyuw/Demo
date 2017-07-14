<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/14
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>后台</title>
</head>
<body>
<c:forEach items="${roleset}" var="role">
    <td>角色 ${role}</td>
</c:forEach><br>
<c:forEach items="${menuset}" var="menu">
    <td>权限 ${menu}</td>
</c:forEach><br>
<shiro:hasRole name="admin">
    这是admin角色登录：<shiro:principal></shiro:principal>
</shiro:hasRole>

<shiro:hasRole name="teacher">
    这是teacher角色登录：<shiro:principal></shiro:principal>
</shiro:hasRole>

<shiro:hasPermission name="student1">
    有student:*权限信息
</shiro:hasPermission>

<shiro:hasPermission name="user">
    有user权限信息
</shiro:hasPermission>

<shiro:hasPermission name="qq">
    有qq权限信息
</shiro:hasPermission>
<br>
登录成功
</body>
</html>
