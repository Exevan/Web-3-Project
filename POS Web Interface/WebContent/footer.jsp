<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<footer>
		&copy; Webontwikkeling 3, UC Leuven-Limburg
		<form method="POST" action="Controller?action=change_style&origin=${param.origin}">
			<input type="submit" value="Verander stijl"/>
		</form>
	</footer>
</body>
</html>