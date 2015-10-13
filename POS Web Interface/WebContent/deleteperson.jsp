<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Person</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${style}.css">
</head>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Delete person"/>
		</jsp:include>
		<main>
		
		<dl>
		<dt>Name:</dt>
		<dd>${person.firstName} ${person.lastName}</dd>
		<dt>Email:</dt>
		<dd>${person.userId}</dd>
		</dl>
		
		<form method="POST" action="Controller">
		<input type="hidden" name="action" value="deleteperson_complete">
		<input type="hidden" name="mail" value="${person.userId}">
		<input type="submit" value="Delete">
		</form>		
		<a href="Controller?action=personoverview"><button type="button">Cancel</button></a>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param name="origin" value="deleteperson_start"/>
		</jsp:include>
	</div>
</body>
</html>