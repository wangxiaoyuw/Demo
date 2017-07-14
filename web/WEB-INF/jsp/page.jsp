<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wangzy
  Date: 2017/7/3
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div align="center">
    <font size="2">共${page.totalPageCount}页</font>
    <font size="2">第${page.pageNow}页</font><a href="${url}?pageNow=1">首页</a>

    <c:choose>
        <c:when test="${page.pageNow -1>0}">
            <a href="${url}?pageNow=${page.pageNow - 1}">上一页</a>
        </c:when>
        <c:when test="${page.pageNow -1<=0}">
            <a href="${url}?pageNow=1">上一页</a>
        </c:when>
    </c:choose>

    <c:choose>
        <c:when test="${page.totalPageCount==0}">
            <a href="${url}?pageNow=${page.pageNow}">下一页</a>
        </c:when>
        <c:when test="${page.pageNow + 1 < page.totalPageCount}">
            <a href="${url}?pageNow=${page.pageNow + 1}">下一页</a>
        </c:when>
        <c:when test="${page.pageNow + 1 >= page.totalPageCount}">
            <a href="${url}?pageNow=${page.totalPageCount}">下一页</a>
        </c:when>
    </c:choose>
    <c:choose>
        <c:when test="${page.totalPageCount==0}">
            <a href="${url}?pageNow=${page.pageNow}">尾页</a>
        </c:when>
        <c:otherwise>
            <a href="${url}?pageNow=${page.totalPageCount}">尾页</a>
        </c:otherwise>
    </c:choose>
</div>
