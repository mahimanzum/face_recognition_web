package report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import connection.DatabaseConnection;
import util.SqlPair;

public class ReportService {
	Logger logger = Logger.getLogger(getClass());
	
	public List<List<Object>> getResultSet(ReportMetadata reportMetadata,String tableJoinSql
			,int limit,int offset) throws Exception{
		
		
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
	
	
	public List<List<Object>> getResultSet(Map<String, Class> classMap, String tableJoinSql, HttpServletRequest request,
			int limit, int offset) throws Exception {
		ReportMetadata reportMetadata = new ReportMetadata(classMap, request);
		return getResultSet(reportMetadata, tableJoinSql, limit, offset);
	
	}

	Integer getTotalCount(Map<String, Class> classMap, String tableJoinSql, HttpServletRequest request)
			throws Exception {
		ReportMetadata reportMetadata = new ReportMetadata(classMap, request);

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
	
	
	public static String createJoinSql(Class<?>[] prevClassList,Class<?> classObject) throws Exception{
		
		
		
		return null;
	}
	
	
	
	
}
