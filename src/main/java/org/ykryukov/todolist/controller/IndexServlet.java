package org.ykryukov.todolist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ykryukov.todolist.model.*;
import org.ykryukov.todolist.model.todo.Dao;
import org.ykryukov.todolist.model.todo.Todo;
import org.ykryukov.todolist.model.todo.TodoHibernateDao;

@WebServlet({ "/index", "" })
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final Dao<Todo> todoDao = new TodoHibernateDao();

	public IndexServlet() {
		super();
	}

	/**
	 * @override
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Todo> todoList = todoDao.getAll();
		request.setAttribute("todoList", todoList);
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @override
	 */
	public void destroy() {
		ConnHibernate.closeAll();
	}
}
