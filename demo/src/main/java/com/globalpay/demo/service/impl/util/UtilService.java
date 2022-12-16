package com.globalpay.demo.service.impl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilService {
	
	/**
	 * 
	 * @return infinito representado como 31 de Diciembre de 9999
	 * @throws ParseException
	 */
	public static Date returnInfinito() throws ParseException {
		  String sDate1="31/12/9999";  
		  return new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
	}
	
	/**
	 * 
	 * @param days
	 * @return Suma de X dias a la fecha de hoy
	 */
	public static Date SumDaysToDate(long days) {
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, (int) days);
		dt = c.getTime();
		
		return dt;
	}
}
