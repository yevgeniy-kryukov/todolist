package org.ykryukov.todolist.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ykryukov.todolist.model.*;
import org.ykryukov.todolist.model.todo.Dao;
import org.ykryukov.todolist.model.todo.Todo;
import org.ykryukov.todolist.model.todo.TodoHibernateDao;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final Dao<Todo> todoDao = new TodoHibernateDao();

	public CreateServlet() {
		super();
	}

	/**
	 * @override
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("defaultDateTimeAction", Timestamp.valueOf(LocalDateTime.now()));
		getServletContext().getRequestDispatcher("/view/create.jsp").forward(request, response);
	}

	/**
	 * @override
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Timestamp dateTimeAction = Timestamp.valueOf(request.getParameter("dateTimeAction"));
			String textAction = request.getParameter("textAction");
			
			if (dateTimeAction == null || textAction.isEmpty()) {
				doGet(request, response);
				return;
			}
			
			Todo todo = new Todo(dateTimeAction, textAction);
			todoDao.create(todo);
			response.sendRedirect(request.getContextPath() + "/index");
		} catch (Exception ex) {
			getServletContext().getRequestDispatcher("/view/create.jsp").forward(request, response);
		}
	}

	/**
	 * @override
	 */
	public void destroy() {
		ConnHibernate.closeAll();
	}

}
