<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${style}.css">
</head>
<body>
<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Login"/>
		</jsp:include>
		<main>
		<form method="POST" action="Controller">
			<p>
				<label for="username">Username:</label>
				<input type="text" id="username" name="username" required>
			</p>
			<p>
				<label for="passwd">Password:</label>
				<input type="password" id="passwd" name="passwd" required>
			</p>
			<input type="hidden" name="action" value="login">
			<input type="hidden" name="prevaction" value="${prevaction}">
			<input type="submit" value="Login">
		</form>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param value="login" name="origin"/>
		</jsp:include>
	</div>
</body>
</html>