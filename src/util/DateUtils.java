package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
public class DateUtils {
	
	private static SimpleDateFormat dayMonthYearDateFormat = new SimpleDateFormat( "dd/MM/yyyy" );
	
	public static int getDaysInCurrentMonth() {
		
		Calendar c = Calendar.getInstance();
		
		return getDaysInMonth( c.get(Calendar.MONTH), c.get(Calendar.YEAR) );
	}
	
	public static int getDaysInMonth(int month,int year){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		int numDays = calendar.getActualMaximum(Calendar.DATE);
		System.out.println("number of days in month "+month+" and year "+year+" is "+numDays);
		return numDays;
	}
	
	public static long getStartTimeOfMonth(int month,int year){
		
		//jan means 0, feb means 1
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTimeInMillis();
	}
	
	public static long getEndTimeOfMonth(int month,int year){
		int numberOfDaysInMonth = getDaysInMonth(month,year);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, numberOfDaysInMonth);
		calendar.set(Calendar.HOUR_OF_DAY,23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND,59);
		calendar.set(Calendar.MILLISECOND,999);
		return calendar.getTimeInMillis();
	}
	/**
	 * @author Alam
	 * @return 
	 * @throws ParseException 
	 */
	public static int getMonthFromDateString( String dateStr ) throws ParseException {
		
		Date date = dayMonthYearDateFormat.parse( dateStr );
		
		Calendar c = Calendar.getInstance();
		c.setTime( date );
		return c.get( Calendar.MONTH );
	}
	
	public static int getYearFromDateString( String dateStr ) throws ParseException {
		
		Date date = dayMonthYearDateFormat.parse( dateStr );
		
		Calendar c = Calendar.getInstance();
		c.setTime( date );
		return c.get( Calendar.YEAR );
	}
	/**
	 * @author Alam
	 * @param parameter
	 * @return 
	 * @throws ParseException 
	 */
	public static long getTimestampFromDateStr(String dateStr) throws ParseException {
		
		return dayMonthYearDateFormat.parse(dateStr).getTime();
	}
	
	public static long getEndTimeOfDayByDateString(String dateString) throws Exception{
		long timeInMills = getTimestampFromDateStr(dateString);
		timeInMills+=TimeConverter.MILLS_IN_A_DAY;
		timeInMills--;
		return timeInMills;
	}
}
