<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${style}.css">
</head>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Sign Up" />
		</jsp:include>
		<main> <c:if test="${not empty errors}">
			<div class="alert-danger">
				<ul>
					<c:forEach items="${errors}" var="error">
						<li>${error}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<form method="POST" action="Controller">
			<p>
				<label for="first">Firstname:</label>
				<input type="text" id="firstName" name="first" value="${values[0]}">
			</p>
			<p>
				<label for="last">Lastname:</label>
				<input type="text" id="lastName" name="last"  value="${values[1]}">
			</p>
			<p>
				<label for="mail">E-mail:</label>
				<input type="text" id="email" name="mail"  value="${values[2]}">
			</p>
			<p>
				<label for="passwd">Password:</label>
				<input type="text" id="password" name="passwd">
			</p>
			<input type="hidden" name="action" value="addperson_complete">
			<input type="submit" value="Register" id="signUp">
		</form>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param name="origin" value="addperson_start" />
		</jsp:include>
	</div>
</body>
</html>