<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Новое дело</title>
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
	<h3>Новое дело</h3>
	<form name="formMain" method="post">
		<label>Запланированное дата и время дела</label><br>
		<input name="dateTimeAction" value="${defaultDateTimeAction}" type="text" size="50" /><br>
		<label>Дело</label><br>
		<input name="textAction" value="" type="text" size="150" /><br><br>
		<input type="submit" value="Сохранить" onClick = "return save()"/>
		<input type="button" value="Вернуться в список" onClick = "window.location='<%=request.getContextPath()%>/index'">
	</form>
	<p>прикрепление файла доступно после сохранения заметки</p>
</body>
</html>