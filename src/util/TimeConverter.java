package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import util.StringUtils;

public class TimeConverter {	
	static Logger logger = Logger.getLogger(TimeConverter.class);
	public static DateFormat  df = new SimpleDateFormat("dd/MM/yyyy");
	public static DateFormat  dfWithTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public static DateFormat meridiemSdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a"); //comment format
	
	public static DateFormat monthSdf = new SimpleDateFormat("MMM"); //month format

	public static DateFormat meridiandf = new SimpleDateFormat("dd/MM/yyyy hh:mm a"); //comment format

	public static DateFormat dateWithTimeFormat = new SimpleDateFormat("dd MMM yyyy - HH:mm");
	
	public static long MILLS_IN_A_DAY =  86400000L;	
	public static final long MILLISECONDS_IN_YEAR = MILLS_IN_A_DAY * 365;
	public static long OffsetInMillis = OffsetDateTime.now().getOffset().getTotalSeconds() * 1000;
	//28 May 2017 - 05:25
	
	public static String getTimeStringByDateFormat(long timeInMillis, String dateFormat) throws ParseException{
		DateFormat df = new SimpleDateFormat(dateFormat);
		df.setLenient(false);
		
		Date applicationDate = new Date(timeInMillis);
		return df.format(applicationDate).toString();
	}
	public static long getTimeInMilliSec(String timeString,String dateFormat) throws ParseException  {
		timeString = StringUtils.trim(timeString);
		if(timeString.matches("[0-9]+")) {
			return Long.parseLong(timeString);
		}else {
			DateFormat df = new SimpleDateFormat(dateFormat);
			df.setLenient(false);
			return df.parse(timeString).getTime();
		}
	}
	
	public static long getDateWithTimeFromDateTimeString(String dateWithTime){
		Date activationDate = null;
		try {
			dateWithTimeFormat.setLenient(false);
			activationDate = dateWithTimeFormat.parse(dateWithTime);
		} catch (Exception e) {
			logger.debug(e);
		}
		return activationDate.getTime();
	}
	public static String getDateTimeStringByMillisecAndDateFormat(long millisec,String dateTimeFormat){
		Date date = new Date(millisec);
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
		dateFormat.setLenient(false);
		return dateFormat.format(date);
	}
	public static String getDateTimeStringFromDateTime(long dateWithTime){
		Date applicationDate = new Date(dateWithTime);
		dateWithTimeFormat.setLenient(false);
		return dateWithTimeFormat.format(applicationDate).toString();
	}
	
	public static String getTimeStringFromLong(long time) {
		Date applicationDate = new Date(time);
		df.setLenient(false);
		return df.format(applicationDate).toString();
	}
	
	public static String getTimeWithTimeStringFromLong(long time) {
		Date applicationDate = new Date(time);
		dfWithTime.setLenient(false);
		return dfWithTime.format(applicationDate).toString();
	}

	public static long getTimeFromString(String timeString) {
		Date activationDate = null;
		try {
			df.setLenient(false);
			activationDate = df.parse(timeString);
		} catch (Exception e) {
			logger.debug(e);
		}
		return activationDate.getTime();
	}
	public static long getStartTimeOfTheDay(long time){
		return (time/MILLS_IN_A_DAY)*MILLS_IN_A_DAY - OffsetInMillis;
	}
	public static long getStartTimeOfTheNextNthDay(long time,int n){
		return getStartTimeOfTheDay(time)+n*MILLS_IN_A_DAY;
	}
	public static String getToDay(){
		Date applicationDate = new Date(System.currentTimeMillis());
		return df.format(applicationDate).toString();
	}
	
	public static String getMeridiemTime(long time){
		Date applicationDate = new Date(time);
		meridiandf.setLenient(false);
		return meridiandf.format(applicationDate).toString();
	}
	
	public static String getMonth(long time){
		return monthSdf.format(time).toString();
	}
	public static void main(String args[]) throws ParseException
	{
		getTimeFromString("01/10/2017");
		System.out.println("Done");
		System.out.println("System.currentTimeMillis() " + System.currentTimeMillis());
		System.out.println(TimeConverter.getStartTimeOfTheDay(System.currentTimeMillis()));
		System.out.println("OffsetDateTime.now().getOffset().getTotalSeconds() * 1000 " + OffsetDateTime.now().getOffset().getTotalSeconds() * 1000);
	}
}
