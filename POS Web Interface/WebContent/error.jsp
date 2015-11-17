<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Something went wrong</title>
</head>
<body>
	<main> <article>
	<h1>Oh dear...</h1>
	<p>You caused a ${pageContext.exception} on the server</p>
	<p>
		<a href="Controller">home</a>
	</p>
	</article> </main>
</body>
</html>