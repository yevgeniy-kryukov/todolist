package org.ykryukov.todolist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ykryukov.holidays.client.HolidaysWebServiceClient;

/**
 * Servlet implementation class IndexServletHoliday
 */
@WebServlet("/IndexServletHoliday")
public class IndexServletHoliday extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServletHoliday() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HolidaysWebServiceClient holidaysWebServiceClient = new HolidaysWebServiceClient();

		// обращаемся к веб-сервису и выводим результат в консоль
		List<String> holidays = holidaysWebServiceClient.holidaysWS.getHolidays().getItem();
		for (String item : holidays) {
			response.getWriter().write(item + "<br>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
