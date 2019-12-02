<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生选课系统|login</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/signin.css" rel="stylesheet">
</head>
<body>
<form method="post" action="SigninAction">
	<input type="text" name="id" id="inputId">
    <input type="password" name="password" id="inputPassword">
    <button type="submit">Sign in</button>
</form>
</body>
</html>