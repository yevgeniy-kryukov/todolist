
package org.ykryukov.holidaysws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "HolidaysWebService", targetNamespace = "http://holidaysws.ykryukov.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HolidaysWebService {


    /**
     * 
     * @param dateStr
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://holidaysws.ykryukov.org/HolidaysWebService/isHolidayRequest", output = "http://holidaysws.ykryukov.org/HolidaysWebService/isHolidayResponse")
    public boolean isHoliday(
        @WebParam(name = "dateStr", partName = "dateStr")
        String dateStr);

}
