<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${style}.css">
</head>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Users"/>
		</jsp:include>
		<main> <a href="Controller?action=addperson_start">Sign up</a>
		<table>
			<tr>
				<th>E-mail</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Hashed Password</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="person" items="${persons}">
				<tr>
					<td><c:out value="${person.userId}" /></td>
					<td><c:out value="${person.firstName}" /></td>
					<td><c:out value="${person.lastName}" /></td>
					<td><c:out value="${person.hashedPassword}" /></td>
					<td><form method="post" action="Controller">
							<input type="hidden" name="action" value="deleteperson_start">
							<input type="hidden" name="mail" value="${person.userId}">
							<input type="submit" value="delete">
						</form></td>
					<td><form method="post" action="Controller">
							<input type="hidden" name="action" value="updateperson_start">
							<input type="hidden" name="mail" value="${person.userId}">
							<input type="submit" value="update">
						</form></td>
				</tr>
			</c:forEach>
			<caption>User Overview</caption>
		</table>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param name="origin" value="personoverview"/>
		</jsp:include>
	</div>
</body>
</html>