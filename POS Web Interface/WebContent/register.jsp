<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Register</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${style}.css">
</head>
<body>
<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Register"/>
		</jsp:include>
		<main>
		<form method="POST" action="Controller">
			<p>
				<label for="first">Firstname:</label>
				<input type="text" id="first" name="first" required>
			</p>
			<p>
				<label for="last">Lastname:</label>
				<input type="text" id="last" name="last" required>
			</p>
			<p>
				<label for="mail">E-mail:</label>
				<input type="text" id="mail" name="mail" required>
			</p>
			<p>
				<label for="passwd">Password:</label>
				<input type="text" id="passwd" name="passwd" required>
			</p>
			<input type="hidden" name="action" value="addperson_complete">
			<input type="submit" value="Register">
		</form>
		</main>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>