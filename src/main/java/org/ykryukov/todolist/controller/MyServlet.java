package org.ykryukov.todolist.controller;

import javax.servlet.http.HttpServlet;

import org.ykryukov.todolist.model.ConnHibernate;

public class MyServlet extends HttpServlet {
	/**
	 * @override
	 */
	public void destroy() {
		ConnHibernate.getInstance().closeAll();
	}
}
