<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shoppingcart</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${style}.css">
</head>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Shoppingcart" />
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
		<table>
			<tr>
				<th>ID</th>
				<th>otherID</th>
				<th>Name</th>
				<th>Description</th>
				<th>Quantity</th>
				<th>Price</th>
			</tr>
			<c:forEach var="product" items="${products}">
				<tr>
					<td><c:out value="${product.product.id}" /></td>
					<td><c:out value="${product.id}" /></td>
					<td><c:out value="${product.product.name}" /></td>
					<td><c:out value="${product.product.description}" /></td>
					<td><c:out value="${product.qty}"></c:out></td>
					<td><c:out value="${product.total}" /></td>
					<td><form method="post" action="Controller">
					<input type="number" id="quantity" name="quantity" min=0 value="${product.qty}">
							<input type="hidden" name="action" value="updateshoppingcart">
							<input type="hidden" name="id" value="${product.id}"> <input
								type="submit" value="update">
						</form></td>
					<td><form method="post" action="Controller">
							<input type="hidden" name="action" value="deleteshoppingcart">
							<input type="hidden" name="id" value="${product.id}"> <input
								type="submit" value="delete">
						</form></td>
				</tr>
			</c:forEach>
			<caption>Shoppingcart</caption>
		</table>
		<c:if test="${not empty cartamount}">
			<p>Shopping Cart (${cartamount})</p>
		</c:if> </main>
		<jsp:include page="footer.jsp">
			<jsp:param name="origin" value="cartoverview" />
		</jsp:include>
	</div>
</body>
</html>