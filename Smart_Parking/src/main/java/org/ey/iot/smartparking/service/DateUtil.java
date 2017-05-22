/**
 * 
 */
package org.ey.iot.smartparking.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.google.api.client.util.DateTime;

public class DateUtil
{
	public static final String GMT_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	
	public static final String IST_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String GOOGLE_CALENDAR_TIME_ZONE = "GMT";
	
	public static DateTime convertDateToDateTime(Date date){
		DateTime dateTime = null;
		DateFormat sdf = new SimpleDateFormat(GMT_DATE_TIME_FORMAT);
	    sdf.setTimeZone(TimeZone.getTimeZone(GOOGLE_CALENDAR_TIME_ZONE));
	    dateTime = new DateTime(sdf.format(date));
		return dateTime;
	}
	public static String currentDateinGMT(){
		DateFormat sdf = new SimpleDateFormat(GMT_DATE_TIME_FORMAT);
	    sdf.setTimeZone(TimeZone.getTimeZone(GOOGLE_CALENDAR_TIME_ZONE));
	    return sdf.format(Calendar.getInstance().getTime());
	}
	
	public static Date convertStringToDate(String dateStr) throws ParseException{
		Date date = null;
		DateFormat sdf = new SimpleDateFormat(IST_DATE_TIME_FORMAT);
	    date = sdf.parse(dateStr);
		return date;
	}
	
	public static DateTime convertDateStrToDateTime(String dateStr) throws ParseException{
		DateTime dateTime = null;
		dateTime = convertDateToDateTime(convertStringToDate(dateStr));
		return dateTime;
	}
	
	
	public static DateTime convertDateStrToDateTime(String dateStr,int bookingHours) throws ParseException{
		DateTime dateTime = null;
		dateTime = convertDateToDateTime(convertStringToDate(dateStr));
		return dateTime;
	}
	/**
	 * 
	 * @param startTime
	 * @param bookingTime
	 * @param timeUnit this should be one of the values(Calendar.HOUR,Calendar.MINUTES,Calendar.SECOND)
	 * @return
	 */
	public static DateTime getBookingEndTime(DateTime startTime, int bookingTime, int timeUnit){
		DateTime endTime = null;
		Calendar cal =  Calendar.getInstance();
		cal.setTimeInMillis(startTime.getValue());
		cal.add(timeUnit, bookingTime);
		endTime = convertDateToDateTime(cal.getTime());
		return endTime;
	}
	
	
	public static void main (String[] args) throws java.lang.Exception
	{
        /*OffsetDateTime odt = OffsetDateTime.parse( "2013-01-09T19:32:49.103+05:30" );
        Instant instant = odt.toInstant();
		ZoneId z = ZoneId.of( "Asia/Kolkata" );
        ZonedDateTime zdt = odt.atZoneSameInstant( z );
 
        System.out.println( "odt.toString(): " + odt );
        System.out.println( "instant.toString(): " + instant );
        System.out.println( "zdt.toString(): " + zdt );
        
        
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = sdf.parse("2013-01-09T19:32:49.103+05:30"); 
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        System.out.println(sdf.format(date));
        
        
        Calendar cal = Calendar.getInstance(); 
        System.out.println(cal.getTime());
        //cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println(sdf.format(cal.getTime()));*/
		System.out.println(DateUtil.convertDateToDateTime(Calendar.getInstance().getTime()));
		System.out.println(DateUtil.getBookingEndTime(DateUtil.convertDateToDateTime(Calendar.getInstance().getTime()),12,Calendar.MINUTE));
		
	}	
}