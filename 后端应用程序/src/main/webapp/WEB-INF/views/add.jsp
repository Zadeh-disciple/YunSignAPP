
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新增信息</title>
    <script type="text/javascript" src="<%=path%>/js/jquery-1.8.3.js"></script>
</head>
<body>
<div>
    <form name="addItems" >
        <input type="text" name="title"><br>
        <input type="text" name="name"><br>
        <input type="text" name="detail"><br>
        <input type="button" value="提交" οnclick="return add()"/>
    </form>
</div>
</body>
<script type="text/javascript">
    function add(){
        var title = addItems.title.value;
        var name = addItems.name.value;
        var detail = addItems.detail.value;
        $(document).ready(function(){
            $.post("<%=path%>/items/add",
                {"title":title,"name":name,"detail":detail},
                function(data){
                    if(data == true){
                        alert("新建成功");
                    }
                    if(data == false){
                        alert("新建失败");
                    }
                });
        });
    }
</script>
</html>