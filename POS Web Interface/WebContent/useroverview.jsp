<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="container">
		<header>
			<h1>
				<span>User Management System</span>
			</h1>
			<nav>
				<ul>
					<li><a href="Controller">Home</a></li>
					<li><a href="Controller?action=useroverview">Users</a></li>
					<li><a href="Controller?action=productoverview">Products</a></li>
				</ul>
			</nav>
			<h2>Users</h2>

		</header>
		<main>
		<a href="Controller?action=adduser_start">Sign up</a>
		<table>	
			<tr>
				<th>E-mail</th>
				<th>First Name</th>
				<th>Last Name</th>
			</tr>
			<c:forEach var="person" items="${persons}">
				<tr>
				<td><c:out value="${person.userId}" /></td>
				<td><c:out value="${person.firstName}" /></td>
				<td><c:out value="${person.lastName}" /></td>
			</tr>
			</c:forEach>
			<caption>User Overview</caption>
		</table>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>