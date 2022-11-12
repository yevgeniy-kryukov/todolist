<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Изменить дело</title>
	<script language="JavaScript">
	function save() {
		if (!formMain.dateTimeAction.value) {
			alert("Ошибка! поле 'Запланированное дата и время дела' не заполнено");
			return false;
		}
		if (!formMain.textAction.value) {
			alert("Ошибка! поле 'Дело' не заполнено");
			return false;
		}
		return true;
	}
	</script>
</head>
<body>
	<h3>Изменить дело</h3>
	<form name="formMain" method="post">
		<input type="hidden" value="${todo.id}" name="id" />
		<label>Запланированное дата и время дела</label><br>
		<input name="dateTimeAction" value="${todo.dateTimeAction}" type="text" size="50" /><br>
		<label>Дело</label><br>
		<input name="textAction" value="${todo.textAction}" type="text" size="150" /><br>
		<label>Сделано?</label><br>
		<input name="isDone" value="${todo.isDone}" type="checkbox" ${todo.isDone ? "checked": ""}  onClick="this.value = this.checked" /><br><br>
		<input type="submit" value="Сохранить" onClick = "return save()"/>&nbsp;&nbsp;&nbsp;
		<input type="button" value="Вернуться в список" onClick = "window.location='<%=request.getContextPath()%>/index'">
	</form>
	<c:if test="${todo!=null}">
	<hr>
	<div>файлы</div>
	<table cellpadding="5">
	<tr>
		<th>ид</th>
		<th>описание</th>
	</tr>
	<c:forEach var="todoFile" items="${todo.getTodoFiles()}">
	 <tr>
	 	<td align="center">${todoFile.id}</td>
	 	<td><a href="upload/${todoFile.fileName}" target="_blank">${todoFile.fileDescription}</a></td>
	 	<td>
	 		 <c:if test="${todoFile.isImage()}">
	 			<img src="upload/${todoFile.fileName}" height="50" width="50"/>
	 		</c:if>
	 	</td>
	 	<td>
 			<form method="post" action='<c:url value="/delete/file" />' style="display:inline;">
		        <input type="hidden" name="todoFileId" value="${todoFile.id}">
		        <input type="submit" value="Удалить">
		    </form>
	 	</td>
	 </tr>
	</c:forEach>
	</table><br>
	<form method="post" action="<c:url value="upload/file" />" enctype="multipart/form-data">
	<fieldset>
	<legend>прикрепить файл</legend>
		<input type="hidden" name="todoId" value="${todo.id}">
		<span>Описание файла&nbsp;</span><input type="text" name="fileDescription"><br><br>
	  	<input type="file" name="file" />
	  	<input type="submit" value="Загрузить" />
	 </fieldset>
	</form>
	</c:if>
</body>
</html>