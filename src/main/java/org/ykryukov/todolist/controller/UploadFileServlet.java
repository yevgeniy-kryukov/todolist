package org.ykryukov.todolist.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.ykryukov.todolist.model.*;
import org.ykryukov.todolist.model.todo.Todo;
import org.ykryukov.todolist.model.todo.TodoHibernateDao;
import org.ykryukov.todolist.model.todofile.TodoFile;
import org.ykryukov.todolist.model.todofile.TodoFileHibernateDao;

@WebServlet("/upload/file")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final org.ykryukov.todolist.model.todo.Dao<Todo> todoDao = new TodoHibernateDao();

	private static final org.ykryukov.todolist.model.todofile.Dao<TodoFile> todoFileDao = new TodoFileHibernateDao();

	public UploadFileServlet() {
		super();
	}

	/**
	 * @override
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/view/index.jsp").forward(request, response);
	}

	private String getUploadFileName(Part part) {
		String fileName = null;
		String[] a1 = null;
		String[] a2 = null;
		Collection<String> headers = part.getHeaders("content-disposition");
		if (headers == null)
			return null;
		for (String header : headers) {
			a1 = header.split(";");
			for (int i = 0; i < a1.length; i++) {
				a2 = a1[i].split("=");
				if (a2[0].trim().equals("filename")) {
					fileName = a2[1].replaceAll("\"", "");
				}
			}
		}
		return fileName;
	}

	private String getFileExt(String fileName) {
		if (fileName.isEmpty() || (!fileName.isEmpty() && fileName.indexOf(".") == -1))
			return null;
		return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	}

	private String saveFile(Part part) throws IOException {
		final String relativeWebPath = ""; // "/WEB-INF";
		final String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
		final String uploadDir = "upload";

		final File uploadDirPath = new File(absoluteDiskPath + uploadDir);
		if (!uploadDirPath.exists()) {
			uploadDirPath.mkdirs();
		}

		final String fullFileName = getUploadFileName(part);
		final String fileExtension = getFileExt(fullFileName);
		final String newFullFileName = UUID.randomUUID() + "." + fileExtension;

		part.write(uploadDirPath.getAbsolutePath() + "/" + newFullFileName);

		return uploadDir + "/" + newFullFileName;
	}

	/**
	 * @throws IOException 
	 * @override
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String uploadPath = null;

			for (Part part : request.getParts()) {
				if (part.getContentType() != null) { // it is file
					uploadPath = saveFile(part);

					Todo todo = todoDao.getById(new Integer(request.getParameter("todoId")));

					TodoFile todoFile = new TodoFile(todo, uploadPath.split("/")[1], uploadPath.split("/")[0],
							request.getParameter("fileDescription"));

					todoFileDao.create(todoFile);

					// response.getWriter().print("The file uploaded sucessfully.");

					response.sendRedirect(request.getContextPath() + "/index");
				}

			}
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
