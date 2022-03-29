package com.ehcare.ehcare.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	public Date parse(String dateString)
	{
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		try {
			return new java.sql.Date(formatter.parse(dateString).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}
