<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<c:if test="${empty style}">
	<c:set var="style" value="yellow" />
</c:if>
<link rel="stylesheet" type="text/css" href="css/${style}.css">
</head>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Home" />
		</jsp:include>
		<main>
		<c:if test="${not empty errors}">
			<div class="alert-danger">
				<ul>
					<c:forEach items="${errors}" var="error">
						<li>${error}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem
			accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae
			ab illo inventore veritatis et quasi architecto beatae vitae dicta
			sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
			aspernatur aut odit aut fugit, sed quia consequuntur magni dolores
			eos qui ratione voluptatem sequi nesciunt.</p>
		<c:if test="${empty username}">
			<a href="Controller?action=login_start">login</a>
		</c:if>
		<c:if test="${not empty username}">
			<p>Welcome ${username}!</p>
			<p><a href="Controller?action=logout">logout</a></p>
		</c:if>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param name="origin" value="home" />
		</jsp:include>
	</div>
</body>
</html>