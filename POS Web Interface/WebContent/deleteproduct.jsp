<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Product</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${style}.css">
</head>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Delete product"/>
		</jsp:include>
		<main>
		
		<dl>
		<dt>Name:</dt>
		<dd>${product.name}</dd>
		<dt>Description:</dt>
		<dd>${product.description}</dd>
		<dt>Price:</dt>
		<dd>${product.price}</dd>
		</dl>
		
		<form method="POST" action="Controller">
		<input type="hidden" name="action" value="deleteproduct_complete">
		<input type="hidden" name="name" value="${product.name}">
		<input type="submit" value="Delete">
		</form>
		<a href="Controller?action=productoverview"><button type="button">Cancel</button></a>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param name="origin" value="deleteproduct_start"/>
		</jsp:include>
	</div>
</body>
</html>