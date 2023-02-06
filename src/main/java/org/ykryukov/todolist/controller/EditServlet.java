package org.ykryukov.todolist.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ykryukov.todolist.model.*;
import org.ykryukov.todolist.model.todo.Dao;
import org.ykryukov.todolist.model.todo.Todo;
import org.ykryukov.todolist.model.todo.TodoHibernateDao;

@WebServlet("/edit")
public class EditServlet extends MyServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Dao<Todo> todoDao = new TodoHibernateDao();

	public EditServlet() {
		super();
	}

	/**
	 * @override
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer id = new Integer(request.getParameter("id"));
			Todo todo = todoDao.getById(id);
			if (todo != null) {
				request.setAttribute("todo", todo);
				getServletContext().getRequestDispatcher("/view/edit.jsp").forward(request, response);
			} else {
				getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
			}
		} catch (Exception ex) {
			getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
		}
	}

	/**
	 * @override
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Timestamp dateTimeAction = Timestamp.valueOf(request.getParameter("dateTimeAction"));
			String textAction = request.getParameter("textAction");
			boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
			Integer id = new Integer(request.getParameter("id"));
			
			Todo todo = todoDao.getById(id);
			if (todo == null) {
				getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
				return;
			}
			
			if (dateTimeAction == null || textAction.isEmpty()) {
				doGet(request, response);
				return;
			}
			
			todo.setDateTimeAction(dateTimeAction);
			todo.setDone(isDone);
			todo.setTextAction(textAction);
			todoDao.update(todo);
			
			response.sendRedirect(request.getContextPath() + "/index");
		} catch (Exception ex) {
			getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
		}
	}

}
