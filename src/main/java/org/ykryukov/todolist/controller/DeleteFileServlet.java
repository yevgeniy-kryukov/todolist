package org.ykryukov.todolist.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ykryukov.todolist.model.*;
import org.ykryukov.todolist.model.todofile.TodoFile;
import org.ykryukov.todolist.model.todofile.TodoFileHibernateDao;

@WebServlet("/delete/file")
public class DeleteFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final org.ykryukov.todolist.model.todofile.Dao<TodoFile> todoFileDao = new TodoFileHibernateDao();

	public DeleteFileServlet() {
		super();
	}

	/**
	 * @override
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/view/index.jsp").forward(request, response);
	}

	private boolean deleteFile(String fileName, String filePath) throws IOException {
		final String relativeWebPath = ""; // "/WEB-INF";
		final String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);

		final File fullFilePath = new File(absoluteDiskPath + filePath + "/" + fileName);
		if (fullFilePath.exists()) {
			if (fullFilePath.delete())
				return true;
		}

		return false;
	}

	/**
	 * @throws IOException
	 * @override
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			TodoFile todofile = todoFileDao.getById(new Integer(request.getParameter("todoFileId")));

			if (deleteFile(todofile.getFileName(), todofile.getFilePath()))
				todoFileDao.delete(todofile);

			response.sendRedirect(request.getContextPath() + "/index");

		} catch (Exception ex) {
			ex.printStackTrace();
			response.getWriter().print(ex.getMessage());
		}
	}

	/**
	 * @override
	 */
	public void destroy() {
		ConnHibernate.closeAll();
	}

}
