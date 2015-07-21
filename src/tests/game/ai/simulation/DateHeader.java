package tests.game.ai.simulation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Simple utility code to provide a header for (random) test files
 * 
 * @author J Paul Gibson
 * @version 1
 *
 */
public class DateHeader {

	public  static String dateString(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new java.util.Date();
		String str = "\n********************************************************************";
		str = str+"\nExecution Date/Time "+dateFormat.format(date);
		str = str + "\n********************************************************************";
		return str;
	}
	
}
