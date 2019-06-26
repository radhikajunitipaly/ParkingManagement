<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>
<body>
<h1>Login page</h1>
<form action="loginUserController" method="post" enctype="multipart/form-data">
			<p>What's your name?</p>
			<input type="text" name="name" value="Radhika">
			<p></p>
			<input type="file" name="fileToUpload">
			<br/><br/>
			<input type="submit" value="Submit">
</form>

<a href="register_user.jsp">Register here !!</a>

</body>
</html>