package org.ykryukov.holidaysws;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.ykryukov.holidaysws.HolidaysWebService;

public class HolidaysWebServiceClient {
	private static HolidaysWebServiceClient instance;

	private HolidaysWebServiceImplService holidaysService;
	private HolidaysWebService holidays;

	private HolidaysWebServiceClient() {
		// подключаемся к тегу service в wsdl описании
		holidaysService = new HolidaysWebServiceImplService();

		// получив информацию из тега service подключаемся к самому веб-сервису
		holidays = holidaysService.getHolidaysWebServiceImplPort();
	};

	public static synchronized HolidaysWebServiceClient getInstance() {
		if (instance == null) {
			synchronized (HolidaysWebServiceClient.class) {
				if (instance == null) {
					instance = new HolidaysWebServiceClient();
				}	
			}
		}
		return instance;
	}

	public boolean isHoliday(String dateStr) {
		// обращаемся к веб-сервису
		return holidays.isHoliday(dateStr);
	}
}
