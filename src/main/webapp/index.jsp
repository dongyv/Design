<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/inc/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="<%=contextPath%>/AmazeUI-2.4.2/assets/js/jquery.min.js"></script>
<title>注册</title>
<script>
	function register(){
		var user = document.getElementById("user").value;

		$.ajax({
			type : "POST",  //提交方式  
            url : "${path}/user/register.dongyv",//路径  
            dataType:"text",
            data : {  
                "user" : user
            },//数据，这里使用的是Json格式进行传输  
            success : function(data) {//返回数据根据结果进行相应的处理 
            	alert(data);
//             	var result = jQuery.parseJSON(data);
//             	if(result.code==0){
//             		alert(result.msg);
// 	            	window.location.href="${path}/home/toIndex.dongyv";
//             	}else{
//             		alert(result.msg);
//             		window.location.reload();
//             	}
            	
            }
		});
// 		document.getElementById("form1").submit();
	}
</script>
</head>
<body>
<h2>注册</h2>
<form id="form1" method="post">
	<input type="text" id="user"/>
	<input type="button" value="注册" onclick="register()"/>
</form>
</body>
</html>
