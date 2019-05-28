package pbReport;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import connection.DatabaseConnection;

public class ReportService {
	Logger logger = Logger.getLogger(getClass());
	public List<List<Object>> getResultSet(String tableJoinSql, HttpServletRequest request,
			int limit, int offset, String criteria[][], String display[][], String GroupBy, String OrderBY) throws Exception 
	{
		ReportMetadata reportMetadata = new ReportMetadata(request,criteria,display, GroupBy, OrderBY);

		DatabaseConnection databaseConnection = null;
		List<List<Object>> rows = null;
		try {
			databaseConnection = new DatabaseConnection();
			databaseConnection.dbOpen();

			rows = ReportProcessor.getResult(reportMetadata, tableJoinSql, databaseConnection, limit, offset);
		} catch (Exception ex) {
			logger.debug("Report Generation Failed ", ex);
			throw ex;
		} finally {
			databaseConnection.dbClose();
		}
		return rows;
	}

	public Integer getTotalCount(String tableJoinSql, HttpServletRequest request, String criteria[][], String display[][], String GroupBy, String OrderBY)
			throws Exception {
		ReportMetadata reportMetadata = new ReportMetadata(request,criteria,display, GroupBy, OrderBY);

		DatabaseConnection databaseConnection = null;
		Integer totalCount = 0;
		try {
			databaseConnection = new DatabaseConnection();
			databaseConnection.dbOpen();

			totalCount = ReportProcessor.getTotalResultCount(reportMetadata, tableJoinSql, databaseConnection);
		} catch (Exception ex) {
			logger.debug("Report Generation Failed ", ex);
			throw ex;
		} finally {
			databaseConnection.dbClose();
		}
		return totalCount;
	}
}
