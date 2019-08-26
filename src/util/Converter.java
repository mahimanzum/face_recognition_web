package util;
/**
 * @author Kayesh Parvez
 *
 */
public class Converter {
	public static long[] StringArrayToLongArray(String[] strings)
	{	
		long[] longArray = new long[strings.length];
		int i = 0;
		for (String str : strings)
		{
			longArray[i++] = Long.parseLong(str);
		}
		return longArray;   
	}
}
