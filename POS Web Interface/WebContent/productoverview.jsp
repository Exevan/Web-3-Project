<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${style}.css">
</head>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Products" />
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
		<a href="Controller?action=addproduct_start">New
			product</a>
		<table>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
				<c:if test="${not empty username}">
					<th>Quantity</th>
				</c:if>
			</tr>
			<c:forEach var="product" items="${products}">
				<tr>
					<td><c:out value="${product.id}" /></td>
					<td><c:out value="${product.name}" /></td>
					<td><c:out value="${product.description}" /></td>
					<td><c:out value="${product.price}" /></td>
					<td><form method="post" action="Controller">
							<input type="hidden" name="action" value="deleteproduct_start">
							<input type="hidden" name="id" value="${product.id}"> <input
								type="submit" value="delete">
						</form></td>
					<td><form method="post" action="Controller">
							<input type="hidden" name="action" value="updateproduct_start">
							<input type="hidden" name="id" value="${product.id}"> <input
								type="submit" value="update">
						</form></td>
					<c:if test="${not empty username}">
						<td><form method="post" action="Controller">
								<input type="number" id="quantity" name="quantity" min=1 value=1> <input
									type="hidden" name="action" value="addtocart"> <input
									type="hidden" name="id" value="${product.id}"> <input
									type="submit" value="Add to cart">
							</form></td>
					</c:if>
				</tr>
			</c:forEach>
			<caption>Product Overview</caption>
		</table>
		<c:if test="${not empty cartamount}">
			<p>Shopping Cart (${cartamount})</p>
		</c:if> </main>
		<jsp:include page="footer.jsp">
			<jsp:param name="origin" value="productoverview" />
		</jsp:include>
	</div>
</body>
</html>