package report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.log4j.Logger;
import databasemanager.DatabaseManager;
import util.ConnectionUtil;

public class ReportTemplateDAO {
	Logger logger = Logger.getLogger(getClass());
	public void insert(ReportTemplate reportTemplate){
		Connection connection = null;
		PreparedStatement ps = null;


		try{
			connection = DatabaseManager.getInstance().getConnection();

			
			
			String sql ="insert into reportTemplate(menuID,reportCriteria,reportDisplay,reportOrder,isSinglePageReport) VALUES(?,?,?,?,?)";

			ps = connection.prepareStatement(sql);

			int index = 1;

			ps.setObject(index++,reportTemplate.menuID);
			ps.setObject(index++,reportTemplate.reportCriteria);
			ps.setObject(index++,reportTemplate.reportDisplay);
			ps.setObject(index++,reportTemplate.reportOrder);
			ps.setObject(index++,reportTemplate.isSinglePageReport);
			ps.execute();
			ps.close();
			
			
		}catch(Exception ex){
			logger.fatal("",ex);
			throw new RuntimeException(ex);
		}finally{
			ConnectionUtil.closeConnection(connection, ps, null);
		}
	}
	public int updateReport(ReportTemplate reportTemplate){
		Connection connection = null;
		PreparedStatement ps = null;

		int count = 0;
		
		try{
			connection = DatabaseManager.getInstance().getConnection();
			

			String sql ="UPDATE reportTemplate SET reportCriteria=?,reportDisplay=?,reportOrder=?,isSinglePageReport=? WHERE menuID = ?";

			ps = connection.prepareStatement(sql);

			int index = 1;

			ps.setObject(index++,reportTemplate.reportCriteria);
			ps.setObject(index++,reportTemplate.reportDisplay);
			ps.setObject(index++,reportTemplate.reportOrder);
			ps.setObject(index++,reportTemplate.isSinglePageReport);
			ps.setObject(index++,reportTemplate.menuID);
			count = ps.executeUpdate();
			
			ps.close();
			return count;
		}catch(Exception ex){
			logger.fatal("",ex);
			throw new RuntimeException(ex);
		}finally{
			try{
				if (ps != null) {
					ps.close();
				}
			} catch(Exception e){}
			try{
				if(connection != null){
					DatabaseManager.getInstance().freeConnection(connection);
				}
			}catch(Exception ex2){}
		}
		
	}
	public ReportTemplate getReportTemplateByMenuID(int menuID){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		ReportTemplate reportTemplate = null;
		try{
			
			String sql = "SELECT * FROM reportTemplate WHERE menuID="+menuID;

			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				reportTemplate = new ReportTemplate();
				
				reportTemplate.menuID = rs.getInt("menuID");
				reportTemplate.reportCriteria = rs.getString("reportCriteria");
				reportTemplate.reportDisplay = rs.getString("reportDisplay");
				reportTemplate.reportOrder = rs.getString("reportOrder");
				reportTemplate.isSinglePageReport = rs.getBoolean("isSinglePageReport");
			}
						
			
			return reportTemplate;
			
		}catch(Exception ex){
			logger.fatal("",ex);
			throw new RuntimeException(ex);
		}finally{
			ConnectionUtil.closeConnection(connection, null, stmt);
		}

	}
}
