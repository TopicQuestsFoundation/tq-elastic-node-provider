/**
 * 
 */
package org.topicquests.es.util;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * @author jackpark
 * <p>For use with timestamps other than <em>now</em>
 * @see http://www.joda.org/joda-time/apidocs/org/joda/time/format/DateTimeFormat.html
 * ElasticSearch uses JodaTime. We can use that for a wide range of years
 * @see http://joda-time.sourceforge.net/field.html
 */
public class UniversalTimeStamp {

	/**
	 * 
	 */
	public UniversalTimeStamp() {
	}

	public static long getTimeStamp(boolean isBCE, int year, int month, int day, int hour, int minute, int second)
		throws Exception {
		StringBuilder buf = new StringBuilder();
		if (isBCE) buf.append("AD "); else buf.append("BC ");
		String x = Integer.toString(year);
		buf.append(x+"-");
		x = Integer.toString(month);
		buf.append(x+"-");
		x = Integer.toString(day);
		buf.append(x+" ");
		x = Integer.toString(hour);
		buf.append(x+":");
		x = Integer.toString(minute);
		buf.append(x+":");
		x = Integer.toString(second);
		buf.append(x);
		
		return getTimeStamp(buf.toString());
	}
	
	
	public static long getTimeStamp(String formattedDateTimeString) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat( "G yyyy-MM-dd HH:mm:ss" );
		Date d = df.parse(formattedDateTimeString);
		return d.getTime();
	}
	
	public static String timeStampToString(long timestamp) {
		SimpleDateFormat df = new SimpleDateFormat( "G yyyy-MM-dd HH:mm:ss" );
		return df.format(new Date(timestamp));
	}
}
