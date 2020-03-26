<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
</head>
<body>
<c:forEach items="${list}" var="item" varStatus="vs">
    <h1>标题：${item.title}</h1>
    <p>作者：${item.name}</p>
    <a>内容：${item.detail}</a>
</c:forEach>
</body>
</html>