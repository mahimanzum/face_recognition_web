package common;

import annotation.ColumnName;
import annotation.PrimaryKey;
import annotation.TableName;
import report.DateConvertor;
import report.Display;
import report.Join;
import report.ReportCriteria;
import report.SubqueryBuilderForDate;
import user.UserDTO;

@TableName("performance_log")
public class PerformanceLog {
	@PrimaryKey
	@ColumnName("performance_log.ID")
	public long ID;
	@ColumnName("URI")
	public String URI;
	@Display(DateConvertor.class)
	@ReportCriteria(SubqueryBuilderForDate.class)
	@ColumnName("requestTime")
	public long requestTime;
	@ColumnName("totalServiceTime")
	public int totalServiceTime;
	@Join(UserDTO.class)
	@ColumnName("userID")
	public long userID;
	@ColumnName("ipAddress")
	public String ipAddress;
	@ColumnName("portNumber")
	public int portNumber;
}
