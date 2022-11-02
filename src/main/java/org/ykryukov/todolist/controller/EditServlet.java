package org.ykryukov.todolist.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ykryukov.todolist.model.*;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditServlet() {
		super();
	}

	/**
	 * @override
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Todo todo = TodoHibernate.getById(id);
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
			int id = Integer.parseInt(request.getParameter("id"));
			Todo todo = new Todo(dateTimeAction, textAction, isDone, id);
			TodoHibernate.update(todo);
			response.sendRedirect(request.getContextPath() + "/index");
		} catch (Exception ex) {
			getServletContext().getRequestDispatcher("/notfound.jsp").forward(request, response);
		}
	}

	/**
	 * @override
	 */
	public void destroy() {
		ConnHibernate.closeAll();
	}

}
