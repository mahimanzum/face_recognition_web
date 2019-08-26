package report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import org.apache.log4j.Logger;

import common.RequestFailureException;
import databasemanager.DatabaseManager;
import login.LoginDTO;
import user.UserDTO;
import user.UserRepository;
import util.ConnectionUtil;
import util.NavigationService;

public class PerformanceLogSummaryReportDAO {
	
	Logger logger = Logger.getLogger(getClass());
	
	private String selectPart = " select userName,URI,count(requestTime) ";
	private String fromPart =  "from performance_log join `user` on (userID = `user`.ID) ";
	private String groupByPart = " group by userID,URI ";
	
	
	public List<List<Object>> getResultByOffsetAndLimit(int offset,int limit) throws Exception{
		
		List<List<Object>> resultList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;


		try{
			connection = DatabaseManager.getInstance().getConnection();

			
			String sql = selectPart+fromPart+groupByPart+" limit "+limit+" offset "+offset;

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			
			resultList.add(Arrays.asList("User Name","URI","Hit Count"));
			
			while(rs.next()){
				List<Object> resultRow = Arrays.asList(rs.getObject(1),rs.getObject(2),rs.getObject(3));
				resultList.add(resultRow);
			}
			
			ps.close();
			
		}catch(Exception ex){
			logger.fatal("",ex);
			throw ex;
		}finally{
			ConnectionUtil.closeConnection(connection, ps, null);
		}
		
		
		return resultList;
	}
	public int getResultCount() throws Exception{
		List<List<Object>> resultList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		int count = 0;

		try{
			connection = DatabaseManager.getInstance().getConnection();

			
			String sql = "select count(*) from ("+selectPart+fromPart+groupByPart+") tmp";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
			
			ps.close();
			
		}catch(Exception ex){
			logger.fatal("",ex);
			throw ex;
		}finally{
			ConnectionUtil.closeConnection(connection, ps, null);
		}
		return count;
	}
}
