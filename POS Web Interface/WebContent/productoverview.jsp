<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${style}.css">
</head>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Products"/>
		</jsp:include>
		<main>
		<a href="Controller?action=addproduct_start">New product</a>
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
					<td><form method="post" action="Controller">
							<input type="hidden" name="action" value="deleteproduct_start">
							<input type="hidden" name="name" value="${product.name}">
							<input type="submit" value="delete">
						</form></td>
					<td><form method="post" action="Controller">
							<input type="hidden" name="action" value="updateproduct_start">
							<input type="hidden" name="name" value="${product.name}">
							<input type="submit" value="update">
						</form></td>
			</tr>
			</c:forEach>
			<caption>Product Overview</caption>
		</table>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param name="origin" value="productoverview.jsp"/>
		</jsp:include>
	</div>
</body>
</html>