<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>Update Pattern</h1>
		<table>
			<form:form method="post" modelAttribute="patternForm" action="${pattern.id}">
				<tr>
					<td>Pattern Name:</td>
					<td><form:input path="name" type="text" value="${pattern.name}"/></td>
				</tr>
				<tr>
					<td>Pattern Group:</td>
					<td><form:input path="group" type="text" value="${pattern.group}"/></td>
				</tr>
				<tr>
					<td>Implementation:</td>
					<td><form:input path="implementation" type="text" value="${pattern.implementation}"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Update"></td>
				</tr>
			</form:form>
		</table>
	</div>
</body>
</html>