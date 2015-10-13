<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Add Product</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${style}.css">
</head>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Add product" />
		</jsp:include>
		<main>
		<form method="POST" action="Controller">
			<p>
				<label for="name">Product name:</label> <input type="text" id="name"
					name="name" required>
			</p>
			<p>
				<label for="desc">Description:</label> <input type="text" id="desc"
					name="desc" required>
			</p>
			<p>
				<label for="price">Price:</label> <input type="text" id="price"
					name="price" required>
			</p>
			<input type="hidden" name="action" value="addproduct_complete">
			<input type="submit" value="Add">
		</form>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param name="origin" value="addproduct_start"/>
		</jsp:include>
	</div>
</body>
</html>