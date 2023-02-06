package org.ykryukov.todolist.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ykryukov.todolist.model.*;
import org.ykryukov.todolist.model.todo.Dao;
import org.ykryukov.todolist.model.todo.Todo;
import org.ykryukov.todolist.model.todo.TodoHibernateDao;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Dao<Todo> TodoDao = new TodoHibernateDao();

	public DeleteServlet() {
		super();
	}

	/**
	 * @override
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer id = new Integer(request.getParameter("id"));
			TodoDao.deleteById(id);
			response.sendRedirect(request.getContextPath() + "/index");
		} catch (Exception ex) {
			getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
		}
	}

	/**
	 * @override
	 */
	public void destroy() {
		ConnHibernate.getInstance().closeAll();
	}

}
