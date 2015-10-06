<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Person</title>
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
					<li id="actual"><a href="Controller?action=useroverview">Users</a></li>
					<li><a href="Controller?action=productoverview">Products</a></li>
				</ul>
			</nav>
			<h2>Delete persons</h2>
		</header>
		<main>
		
		<dl>
		<dt>Name:</dt>
		<dd>${person.firstName} ${person.lastName}</dd>
		<dt>Email:</dt>
		<dd>${person.userId}</dd>
		</dl>
		
		<form>
		<input type="hidden" name="action" value="deleteperson_complete">
		<input type="submit" value="Delete">
		</form>		
		<button type="button" formaction="Controller?action=useroverview">Cancel</button>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>