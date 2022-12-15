package com.globalpay.demo.service.impl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilService {
	public static Date returnInfinito() throws ParseException {
		  String sDate1="31/12/9999";  
		  return new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
	}
}
