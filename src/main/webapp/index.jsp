<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Список дел</title>
</head>
<body>
	<h2>Список дел</h2>
	<p><a href='<c:url value="/create" />'>Новое дело</a></p>
	<table border="1" cellpadding="5" style="border-collapse:collapse">
	<tr>
		<th>Ид</th>
		<th>Запланированное дата и время</th>
		<th>Дело</th>
		<th>Выпадает на праздник</th>
		<th>Сделано?</th>
	</tr>
	<c:forEach var="todo" items="${todoList}">
	 <tr>
	 	<td align="center">${todo.id}</td>
	 	<td>${todo.dateTimeAction}</td>
	    <td>${todo.textAction}</td>
	   	<td align="center">${todo.isHoliday() ? "да": "нет"}</td>
	    <td align="center">${todo.isDone ? "да": "нет"}</td>
	    <td>
		    <a href='<c:url value="/edit?id=${todo.id}" />'>Изменить</a> 
		    <c:if test="${!todo.isDone && todo.getTodoFiles().size() == 0}">|
			    <form method="post" action='<c:url value="/delete" />' style="display:inline;">
			        <input type="hidden" name="id" value="${todo.id}">
			        <input type="submit" value="Удалить">
			    </form>
		    </c:if>
	 	</td>
	 </tr>
	</c:forEach>
	</table>
</body>
</html>