package common;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionDTO {
	
	public ExceptionDTO(Exception ex){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String sStackTrace = sw.toString(); // stack trace as a string
		this.stacktrace = sStackTrace;
	}
	
	public long ID;
	public String stacktrace;
}
