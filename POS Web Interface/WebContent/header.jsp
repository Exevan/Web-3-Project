<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<header>
	<h1>
		<span>User Management System</span>
	</h1>
	<nav>
	<ul>
		<li><a href="Controller">Home</a></li>
		<li><a href="Controller?action=personoverview">Users</a></li>
		<li><a href="Controller?action=productoverview">Products</a></li>
	</ul>
	</nav>
	<h2>${param.title}</h2>
	</header>
</body>
</html>