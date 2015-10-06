<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
					<li id="actual"><a href="Controller?action=productoverview">Products</a></li>
					<li><a href="Controller?action=signUp">Sign up</a></li>
				</ul>
			</nav>
			<h2>Products</h2>

		</header>
		<main>
		<table>	
			<tr>
				<th>Product</th>
				<th>Description</th>
				<th>Price</th>
			</tr>
			<c:forEach var="product" items="${products}">
				<tr>
				<td><c:out value="${product.name}" /></td>
				<td><c:out value="${product.description}" /></td>
				<td><c:out value="${product.price}" /></td>
			</tr>
			</c:forEach>
			<caption>Product Overview</caption>
		</table>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>