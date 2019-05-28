package pbReport;

import geolocation.GeoLocationDAO2;;

public class GeoConvertor implements ColumnConvertor{

	@Override
	public String convert(Object address) {		
		return (String) GeoLocationDAO2.parseTextAndDetails((String)address);
	}

}
