package pbReport;

import java.util.Calendar;

public class DateUtils 
{
	
	public static long get1stDayOfMonth()
	{
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		long l1stDayOfMonth = c.getTimeInMillis() - 6 * 60 * 60 * 1000;
		
		return l1stDayOfMonth;
	}
	
	public static long getToday12AM()
	{
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		long lToday12AM = c.getTimeInMillis() - 6 * 60 * 60 * 1000;
		
		return lToday12AM;
	}
	
	public static long get1stDayOfJuly()
	{
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		
		if(c.get(Calendar.MONTH) >= 6)
		{
			c.set(Calendar.MONTH, 6);
		}
		else
		{
			c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 1);
			c.set(Calendar.MONTH, 6);
		}
		
		long l1stDayOfJuly = c.getTimeInMillis() - 6 * 60 * 60 * 1000;
		
		
		return l1stDayOfJuly;
	}
	
	public static long addMonth(long millis, int count)
	{
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + count);
		
		long time = c.getTimeInMillis();
		return time;
	}
	
	public static long get1stDayOfNextMonth()
	{
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		
		if(c.get(Calendar.MONTH) >= 11)
		{
			c.set(Calendar.MONTH, 1);
			c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
		}
		else
		{
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		}
		
		long l1stDayOfNextMonth = c.getTimeInMillis() - 6 * 60 * 60 * 1000;
		
		return l1stDayOfNextMonth;
	}
	

}
