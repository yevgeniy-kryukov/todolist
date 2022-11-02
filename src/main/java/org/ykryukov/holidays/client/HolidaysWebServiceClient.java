package org.ykryukov.holidays.client;

import java.util.List;

//подключаем классы заглушки
import org.ykryukov.holidays.ws.*;

public class HolidaysWebServiceClient {
	
	public HolidaysWebService holidaysWS;

//	public static void main(String[] args) {
//		// подключаемся к тегу service в wsdl описании
//		HolidaysWebServiceImplService holidaysService = new HolidaysWebServiceImplService();
//		// получив информацию из тега service подключаемся к самому веб-сервису
//		HolidaysWebService holidaysWS = holidaysService.getHolidaysWebServiceImplPort();
//		// обращаемся к веб-сервису и выводим результат в консоль 
//		List<String> holidays = holidaysWS.getHolidays().getItem();
//		for (String item: holidays) {
//			System.out.println(item);
//		}
//	}
	
	public HolidaysWebServiceClient() {
		// подключаемся к тегу service в wsdl описании
		HolidaysWebServiceImplService holidaysService = new HolidaysWebServiceImplService();
		// получив информацию из тега service подключаемся к самому веб-сервису
		holidaysWS = holidaysService.getHolidaysWebServiceImplPort();
	}
	
	public List<String> getHolidays() {
		return holidaysWS.getHolidays().getItem();
	}

}
