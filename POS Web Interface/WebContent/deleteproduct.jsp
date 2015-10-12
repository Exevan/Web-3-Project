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
		
		<form>
		<input type="hidden" name="action" value="deleteproduct_complete">
		<input type="submit" value="Delete">
		</form>		
		<button type="button" formaction="Controller?action=productoverview">Cancel</button>
		</main>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>