<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC -HelloWorld</title>
</head>
<body>
	<div align="center">
		<h1>Pattern List</h1>
		<table border="1">
			<th>Id</th>
			<th>Pattern Name</th>
			<th>Group</th>
			<th>Implementation</th>
			<th>Edit</th>
			<th>Delete</th>
			<c:forEach var="pattern" items="${patterns}" varStatus="status">
				<tr>
					<td>${pattern.id}</td>
					<td>${pattern.name}</td>
					<td>${pattern.group}</td>
					<td>${pattern.implementation}</td>
					<td><a href="update/${pattern.id }">Edit</a></td>
					<td><a href="delete/${pattern.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		<br></br>
		<a href="add">Add New Pattern Link</a>
	</div>



</body>
</html>