package pb_notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import databasemanager.DatabaseManager;
import login.LoginDTO;
import repository.RepositoryManager;
import sessionmanager.SessionConstants;
import util.NavigationService;

import user.UserDTO;
import user.UserDAO;
import user.UserRepository;
import pbReport.DateUtils;


public class Pb_notificationsDAO  implements NavigationService{
	
	Logger logger = Logger.getLogger(getClass());
	
	
	private void printSql(String sql)
	{
		 System.out.println("sql: " + sql);
	}
	


	private void recordUpdateTime(Connection connection, PreparedStatement ps, long lastModificationTime) throws SQLException
	{
		String query = "UPDATE vbSequencer SET table_LastModificationTime=? WHERE table_name=?";
		ps = connection.prepareStatement(query);
		ps.setLong(1,lastModificationTime);
		ps.setString(2,"pb_notifications");
		ps.execute();
		ps.close();
	}
	

	
	
	public void addPb_notifications(Pb_notificationsDTO pb_notificationsDTO) throws Exception{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	

		try{
			connection = DatabaseManager.getInstance().getConnection();
			
			if(connection == null)
			{
				System.out.println("nullconn");
			}

			pb_notificationsDTO.iD = DatabaseManager.getInstance().getNextSequenceId("Pb_notifications");

			String sql = "INSERT INTO pb_notifications";
			
			sql += " (";
			sql += "ID";
			sql += ", ";
			sql += "is_seen";
			sql += ", ";
			sql += "is_hidden";
			sql += ", ";
			sql += "source";
			sql += ", ";
			sql += "destination";
			sql += ", ";
			sql += "from_id";
			sql += ", ";
			sql += "to_id";
			sql += ", ";
			sql += "text";
			sql += ", ";
			sql += "url";
			sql += ", ";
			sql += "entry_date";
			sql += ", ";
			sql += "seen_date";
			sql += ", ";
			sql += "showing_date";
			sql += ", ";
			sql += "send_alarm";
			sql += ", ";
			sql += "send_sms";
			sql += ", ";
			sql += "send_mail";
			sql += ", ";
			sql += "send_push";
			sql += ", ";
			sql += "mail_sent";
			sql += ", ";
			sql += "sms_sent";
			sql += ", ";
			sql += "push_sent";
			sql += ", ";
			sql += "isDeleted";
			sql += ", lastModificationTime)";
			
			
            sql += " VALUES(";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ";
			sql += "?";
			sql += ", ?)";
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			
			

			int index = 1;

			ps.setObject(index++,pb_notificationsDTO.iD);
			ps.setObject(index++,pb_notificationsDTO.isSeen);
			ps.setObject(index++,pb_notificationsDTO.isHidden);
			ps.setObject(index++,pb_notificationsDTO.source);
			ps.setObject(index++,pb_notificationsDTO.destination);
			ps.setObject(index++,pb_notificationsDTO.fromId);
			ps.setObject(index++,pb_notificationsDTO.toId);
			ps.setObject(index++,pb_notificationsDTO.text);
			ps.setObject(index++,pb_notificationsDTO.url);
			ps.setObject(index++,pb_notificationsDTO.entryDate);
			ps.setObject(index++,pb_notificationsDTO.seenDate);
			ps.setObject(index++,pb_notificationsDTO.showingDate);
			ps.setObject(index++,pb_notificationsDTO.sendAlarm);
			ps.setObject(index++,pb_notificationsDTO.sendSms);
			ps.setObject(index++,pb_notificationsDTO.sendMail);
			ps.setObject(index++,pb_notificationsDTO.sendPush);
			ps.setObject(index++,pb_notificationsDTO.mailSent);
			ps.setObject(index++,pb_notificationsDTO.smsSent);
			ps.setObject(index++,pb_notificationsDTO.pushSent);
			ps.setObject(index++,pb_notificationsDTO.isDeleted);
			ps.setObject(index++, lastModificationTime);
			
			System.out.println(ps);
			ps.execute();
			
			
			recordUpdateTime(connection, ps, lastModificationTime);

		}catch(Exception ex){
			System.out.println("ex = " + ex);
			System.out.println("Sql error: " + ex);
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

	public void addPb_notifications(HttpServletRequest request, long toID, int destination, String URL, String text) throws Exception
	{
		Pb_notificationsDTO pb_notificationsDTO = new Pb_notificationsDTO();
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		pb_notificationsDTO.fromId = userDTO.ID;
		pb_notificationsDTO.toId = toID;
		pb_notificationsDTO.destination = destination;
		pb_notificationsDTO.url = URL;
		pb_notificationsDTO.text = text;
		pb_notificationsDTO.entryDate = DateUtils.getToday12AM();
		pb_notificationsDTO.showingDate = DateUtils.getToday12AM();
		addPb_notifications(pb_notificationsDTO);
	}
	
	
	public void addPb_notifications(HttpServletRequest request, long toID, int destination, long showing_date, String URL, String text) throws Exception
	{
		Pb_notificationsDTO pb_notificationsDTO = new Pb_notificationsDTO();
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		pb_notificationsDTO.fromId = userDTO.ID;
		pb_notificationsDTO.toId = toID;
		pb_notificationsDTO.destination = destination;
		pb_notificationsDTO.url = URL;
		pb_notificationsDTO.text = text;
		pb_notificationsDTO.entryDate = DateUtils.getToday12AM();
		pb_notificationsDTO.showingDate = showing_date;
		addPb_notifications(pb_notificationsDTO);
	}
	
	public void addPb_notifications(HttpServletRequest request, long toID, int destination, long showing_date, String URL, String text,
			boolean sendMail, boolean sendSms, boolean sendPush) throws Exception
	{
		Pb_notificationsDTO pb_notificationsDTO = new Pb_notificationsDTO();
		LoginDTO loginDTO = (LoginDTO)request.getSession().getAttribute(SessionConstants.USER_LOGIN);
		UserDTO userDTO = UserRepository.getUserDTOByUserID(loginDTO.userID);
		pb_notificationsDTO.fromId = userDTO.ID;
		pb_notificationsDTO.toId = toID;
		pb_notificationsDTO.destination = destination;
		pb_notificationsDTO.url = URL;
		pb_notificationsDTO.text = text;
		pb_notificationsDTO.entryDate = DateUtils.getToday12AM();
		pb_notificationsDTO.showingDate = showing_date;
		
		pb_notificationsDTO.sendMail = sendMail;
		pb_notificationsDTO.sendSms = sendSms;
		pb_notificationsDTO.sendPush = sendPush;
		addPb_notifications(pb_notificationsDTO);
	}
		
	
	//need another getter for repository
	public Pb_notificationsDTO getPb_notificationsDTOByID (long ID) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Pb_notificationsDTO pb_notificationsDTO = null;
		try{
			
			String sql = "SELECT * ";

			sql += " FROM pb_notifications";
			
            sql += " WHERE ID=" + ID;
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			if(rs.next()){
				pb_notificationsDTO = new Pb_notificationsDTO();

				pb_notificationsDTO.iD = rs.getLong("ID");
				pb_notificationsDTO.isSeen = rs.getBoolean("is_seen");
				pb_notificationsDTO.isHidden = rs.getBoolean("is_hidden");
				pb_notificationsDTO.source = rs.getInt("source");
				pb_notificationsDTO.destination = rs.getInt("destination");
				pb_notificationsDTO.fromId = rs.getLong("from_id");
				pb_notificationsDTO.toId = rs.getLong("to_id");
				pb_notificationsDTO.text = rs.getString("text");
				pb_notificationsDTO.url = rs.getString("url");
				pb_notificationsDTO.entryDate = rs.getLong("entry_date");
				pb_notificationsDTO.seenDate = rs.getLong("seen_date");
				pb_notificationsDTO.showingDate = rs.getLong("showing_date");
				pb_notificationsDTO.sendAlarm = rs.getBoolean("send_alarm");
				pb_notificationsDTO.sendSms = rs.getBoolean("send_sms");
				pb_notificationsDTO.sendMail = rs.getBoolean("send_mail");
				pb_notificationsDTO.sendPush = rs.getBoolean("send_push");
				pb_notificationsDTO.mailSent = rs.getBoolean("mail_sent");
				pb_notificationsDTO.smsSent = rs.getBoolean("sms_sent");
				pb_notificationsDTO.pushSent = rs.getBoolean("push_sent");
				pb_notificationsDTO.isDeleted = rs.getBoolean("isDeleted");

			}			
			
		}catch(Exception ex){
			System.out.println("Sql error: " + ex);
		}finally{
			try{ 
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e){}
			
			try{ 
				if (connection != null){ 
					DatabaseManager.getInstance().freeConnection(connection); 
				} 
			}catch(Exception ex2){}
		}
		return pb_notificationsDTO;
	}
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOsBytoUserID (long toID, int destination, List<Pb_notificationsDTO> pb_notificationsDTOList) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		Pb_notificationsDTO pb_notificationsDTO = null;
		try{

			long today = DateUtils.getToday12AM();
			
			String sql = "SELECT * ";

			sql += " FROM pb_notifications";
			
            sql += " WHERE showing_date <= " + today + " and to_id=" + toID + " and destination = " + destination + " order by id desc";
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			while(rs.next()){
				pb_notificationsDTO = new Pb_notificationsDTO();

				pb_notificationsDTO.iD = rs.getLong("ID");
				pb_notificationsDTO.isSeen = rs.getBoolean("is_seen");
				pb_notificationsDTO.isHidden = rs.getBoolean("is_hidden");
				pb_notificationsDTO.source = rs.getInt("source");
				pb_notificationsDTO.destination = rs.getInt("destination");
				pb_notificationsDTO.fromId = rs.getLong("from_id");
				pb_notificationsDTO.toId = rs.getLong("to_id");
				pb_notificationsDTO.text = rs.getString("text");
				pb_notificationsDTO.url = rs.getString("url");
				pb_notificationsDTO.entryDate = rs.getLong("entry_date");
				pb_notificationsDTO.seenDate = rs.getLong("seen_date");
				pb_notificationsDTO.showingDate = rs.getLong("showing_date");
				pb_notificationsDTO.sendAlarm = rs.getBoolean("send_alarm");
				pb_notificationsDTO.sendSms = rs.getBoolean("send_sms");
				pb_notificationsDTO.sendMail = rs.getBoolean("send_mail");
				pb_notificationsDTO.sendPush = rs.getBoolean("send_push");
				pb_notificationsDTO.mailSent = rs.getBoolean("mail_sent");
				pb_notificationsDTO.smsSent = rs.getBoolean("sms_sent");
				pb_notificationsDTO.pushSent = rs.getBoolean("push_sent");
				pb_notificationsDTO.isDeleted = rs.getBoolean("isDeleted");
				
				pb_notificationsDTOList.add(pb_notificationsDTO);

			}			
			
		}catch(Exception ex){
			System.out.println("Sql error: " + ex);
		}finally{
			try{ 
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e){}
			
			try{ 
				if (connection != null){ 
					DatabaseManager.getInstance().freeConnection(connection); 
				} 
			}catch(Exception ex2){}
		}
		return pb_notificationsDTOList;
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOsBytoUserID (long toUserID, long toRoleID, long toCentreID, List<Pb_notificationsDTO> pb_notificationsDTOList) throws Exception{
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		Pb_notificationsDTO pb_notificationsDTO = null;
		try{
			long today = DateUtils.getToday12AM();
			
			String sql = "SELECT * ";

			sql += " FROM pb_notifications";
			
            sql += " WHERE showing_date <= " + today + " and ("
            		+ " (to_id=" + toUserID + " and destination = 0) or "
            		+ " (to_id=" + toRoleID + " and destination = 1) or "
            		+ " (to_id=" + toCentreID + " and destination = 3)  "            		
            		+ ") order by id desc";
			
			printSql(sql);
			
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			while(rs.next()){
				pb_notificationsDTO = new Pb_notificationsDTO();

				pb_notificationsDTO.iD = rs.getLong("ID");
				pb_notificationsDTO.isSeen = rs.getBoolean("is_seen");
				pb_notificationsDTO.isHidden = rs.getBoolean("is_hidden");
				pb_notificationsDTO.source = rs.getInt("source");
				pb_notificationsDTO.destination = rs.getInt("destination");
				pb_notificationsDTO.fromId = rs.getLong("from_id");
				pb_notificationsDTO.toId = rs.getLong("to_id");
				pb_notificationsDTO.text = rs.getString("text");
				pb_notificationsDTO.url = rs.getString("url");
				pb_notificationsDTO.entryDate = rs.getLong("entry_date");
				pb_notificationsDTO.seenDate = rs.getLong("seen_date");
				pb_notificationsDTO.showingDate = rs.getLong("showing_date");
				pb_notificationsDTO.sendAlarm = rs.getBoolean("send_alarm");
				pb_notificationsDTO.sendSms = rs.getBoolean("send_sms");
				pb_notificationsDTO.sendMail = rs.getBoolean("send_mail");
				pb_notificationsDTO.sendPush = rs.getBoolean("send_push");
				pb_notificationsDTO.mailSent = rs.getBoolean("mail_sent");
				pb_notificationsDTO.smsSent = rs.getBoolean("sms_sent");
				pb_notificationsDTO.pushSent = rs.getBoolean("push_sent");
				pb_notificationsDTO.isDeleted = rs.getBoolean("isDeleted");
				
				pb_notificationsDTOList.add(pb_notificationsDTO);

			}			
			
		}catch(Exception ex){
			System.out.println("Sql error: " + ex);
		}finally{
			try{ 
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e){}
			
			try{ 
				if (connection != null){ 
					DatabaseManager.getInstance().freeConnection(connection); 
				} 
			}catch(Exception ex2){}
		}
		return pb_notificationsDTOList;
	}
	
	public void seePb_notification(long id) throws Exception
	{
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE pb_notifications SET is_seen = true , seen_date = " + DateUtils.getToday12AM() + " where ID = " + id;
			
			
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			ps.executeUpdate();
			

		
			
			recordUpdateTime(connection, ps, lastModificationTime);
			// recordUpdateTimeInUserTable(connection, ps, lastModificationTime);
						
			
		}catch(Exception ex){
			System.out.println("Sql error: " + ex);
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
	
	public void updatePb_notifications(Pb_notificationsDTO pb_notificationsDTO) throws Exception
	{
		
		Connection connection = null;
		PreparedStatement ps = null;

		long lastModificationTime = System.currentTimeMillis();	
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql = "UPDATE pb_notifications";
			
			sql += " SET ";
			sql += "ID=?";
			sql += ", ";
			sql += "is_seen=?";
			sql += ", ";
			sql += "is_hidden=?";
			sql += ", ";
			sql += "source=?";
			sql += ", ";
			sql += "destination=?";
			sql += ", ";
			sql += "from_id=?";
			sql += ", ";
			sql += "to_id=?";
			sql += ", ";
			sql += "text=?";
			sql += ", ";
			sql += "url=?";
			sql += ", ";
			sql += "entry_date=?";
			sql += ", ";
			sql += "seen_date=?";
			sql += ", ";
			sql += "showing_date=?";
			sql += ", ";
			sql += "send_alarm=?";
			sql += ", ";
			sql += "send_sms=?";
			sql += ", ";
			sql += "send_mail=?";
			sql += ", ";
			sql += "send_push=?";
			sql += ", ";
			sql += "mail_sent=?";
			sql += ", ";
			sql += "sms_sent=?";
			sql += ", ";
			sql += "push_sent=?";
			sql += ", ";
			sql += "isDeleted=?";
			sql += ", lastModificationTime = "	+ lastModificationTime + "";
            sql += " WHERE ID = " + pb_notificationsDTO.iD;
				
			printSql(sql);

			ps = connection.prepareStatement(sql);
			
			

			int index = 1;
			
			ps.setObject(index++,pb_notificationsDTO.iD);
			ps.setObject(index++,pb_notificationsDTO.isSeen);
			ps.setObject(index++,pb_notificationsDTO.isHidden);
			ps.setObject(index++,pb_notificationsDTO.source);
			ps.setObject(index++,pb_notificationsDTO.destination);
			ps.setObject(index++,pb_notificationsDTO.fromId);
			ps.setObject(index++,pb_notificationsDTO.toId);
			ps.setObject(index++,pb_notificationsDTO.text);
			ps.setObject(index++,pb_notificationsDTO.url);
			ps.setObject(index++,pb_notificationsDTO.entryDate);
			ps.setObject(index++,pb_notificationsDTO.seenDate);
			ps.setObject(index++,pb_notificationsDTO.showingDate);
			ps.setObject(index++,pb_notificationsDTO.sendAlarm);
			ps.setObject(index++,pb_notificationsDTO.sendSms);
			ps.setObject(index++,pb_notificationsDTO.sendMail);
			ps.setObject(index++,pb_notificationsDTO.sendPush);
			ps.setObject(index++,pb_notificationsDTO.mailSent);
			ps.setObject(index++,pb_notificationsDTO.smsSent);
			ps.setObject(index++,pb_notificationsDTO.pushSent);
			ps.setObject(index++,pb_notificationsDTO.isDeleted);
			System.out.println(ps);
			ps.executeUpdate();
			


			
			recordUpdateTime(connection, ps, lastModificationTime);

						
			
		}catch(Exception ex){
			System.out.println("Sql error: " + ex);
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
	
	public void deletePb_notificationsByID(long ID) throws Exception{
		Connection connection = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		
		long lastModificationTime = System.currentTimeMillis();	
		try{
			String sql = "UPDATE pb_notifications";
			
			sql += " SET isDeleted=1,lastModificationTime="+ lastModificationTime +" WHERE ID = " + ID;
			
			printSql(sql);

			connection = DatabaseManager.getInstance().getConnection();
			stmt  = connection.createStatement();
			stmt.execute(sql);
			

			
			recordUpdateTime(connection, ps, lastModificationTime);

			
		}catch(Exception ex){
			System.out.println("Sql error: " + ex);
		}finally{
			try{ 
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e){}
			
			try{ 
				if (connection != null){ 
					DatabaseManager.getInstance().freeConnection(connection); 
				} 
			}catch(Exception ex2){}
		}
	}

	
	
	public List<Pb_notificationsDTO> getDTOs(Collection recordIDs, LoginDTO loginDTO){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		Pb_notificationsDTO pb_notificationsDTO = null;
		List<Pb_notificationsDTO> pb_notificationsDTOList = new ArrayList<>();
		if(recordIDs.isEmpty()){
			return pb_notificationsDTOList;
		}
		try{
			
			String sql = "SELECT * ";

			sql += " FROM pb_notifications";
            
            sql += " WHERE ID IN ( ";

			for(int i = 0;i<recordIDs.size();i++){
				if(i!=0){
					sql+=",";
				}
				sql+=((ArrayList)recordIDs).get(i);
			}
			sql+=")  order by ID desc";
			
			printSql(sql);
			
			logger.debug("sql " + sql);
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();


			rs = stmt.executeQuery(sql);

			while(rs.next()){
				pb_notificationsDTO = new Pb_notificationsDTO();
				pb_notificationsDTO.iD = rs.getLong("ID");
				pb_notificationsDTO.isSeen = rs.getBoolean("is_seen");
				pb_notificationsDTO.isHidden = rs.getBoolean("is_hidden");
				pb_notificationsDTO.source = rs.getInt("source");
				pb_notificationsDTO.destination = rs.getInt("destination");
				pb_notificationsDTO.fromId = rs.getLong("from_id");
				pb_notificationsDTO.toId = rs.getLong("to_id");
				pb_notificationsDTO.text = rs.getString("text");
				pb_notificationsDTO.url = rs.getString("url");
				pb_notificationsDTO.entryDate = rs.getLong("entry_date");
				pb_notificationsDTO.seenDate = rs.getLong("seen_date");
				pb_notificationsDTO.showingDate = rs.getLong("showing_date");
				pb_notificationsDTO.sendAlarm = rs.getBoolean("send_alarm");
				pb_notificationsDTO.sendSms = rs.getBoolean("send_sms");
				pb_notificationsDTO.sendMail = rs.getBoolean("send_mail");
				pb_notificationsDTO.sendPush = rs.getBoolean("send_push");
				pb_notificationsDTO.mailSent = rs.getBoolean("mail_sent");
				pb_notificationsDTO.smsSent = rs.getBoolean("sms_sent");
				pb_notificationsDTO.pushSent = rs.getBoolean("push_sent");
				pb_notificationsDTO.isDeleted = rs.getBoolean("isDeleted");
				System.out.println("got this DTO: " + pb_notificationsDTO);
				
				pb_notificationsDTOList.add(pb_notificationsDTO);

			}			
			
		}catch(Exception ex){
			System.out.println("got this database error: " + ex);
			System.out.println("Sql error: " + ex);
		}finally{
			try{ 
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e){}
			
			try{ 
				if (connection != null){ 
					DatabaseManager.getInstance().freeConnection(connection); 
				} 
			}catch(Exception ex2){}
		}
		return pb_notificationsDTOList;
	
	}
	
	

	
	public Collection getIDs(LoginDTO loginDTO) 
    {
        Collection data = new ArrayList();
        Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
    	
        String sql = "SELECT ID FROM pb_notifications";

		sql += " WHERE isDeleted = 0  order by ID desc ";
		
		printSql(sql);
		
        try
        {
	        connection = DatabaseManager.getInstance().getConnection();
	        stmt = connection.createStatement();
	        
	        for(resultSet = stmt.executeQuery(sql); resultSet.next(); data.add(resultSet.getString("ID")));
	
	        resultSet.close();
        }
        catch (Exception e)
        {
	    	logger.fatal("DAO " + e.toString(), e);
        }
	    finally
        {
	    	try
            {
          	  if(resultSet!= null && !resultSet.isClosed())
          	  {
          		  resultSet.close();
          	  }
            }
            catch(Exception ex)
            {
          	  
            }
          try{if (stmt != null){stmt.close();}}catch (Exception e){}
          try{if (connection != null){DatabaseManager.getInstance().freeConnection(connection);}}
          catch (Exception e){logger.fatal("DAO finally :" + e.toString());}
        }
        return data;
    }
	
	//add repository
	public List<Pb_notificationsDTO> getAllPb_notifications (boolean isFirstReload)
    {
		List<Pb_notificationsDTO> pb_notificationsDTOList = new ArrayList<>();

		String sql = "SELECT * FROM pb_notifications";
		sql += " WHERE ";
	

		if(isFirstReload){
			sql+=" isDeleted =  0";
		}
		if(!isFirstReload){
			sql+=" lastModificationTime >= " + RepositoryManager.lastModifyTime;		
		}
		printSql(sql);
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		
		try{
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			

			while(rs.next()){
				Pb_notificationsDTO pb_notificationsDTO = new Pb_notificationsDTO();
				pb_notificationsDTO.iD = rs.getLong("ID");
				pb_notificationsDTO.isSeen = rs.getBoolean("is_seen");
				pb_notificationsDTO.isHidden = rs.getBoolean("is_hidden");
				pb_notificationsDTO.source = rs.getInt("source");
				pb_notificationsDTO.destination = rs.getInt("destination");
				pb_notificationsDTO.fromId = rs.getLong("from_id");
				pb_notificationsDTO.toId = rs.getLong("to_id");
				pb_notificationsDTO.text = rs.getString("text");
				pb_notificationsDTO.url = rs.getString("url");
				pb_notificationsDTO.entryDate = rs.getLong("entry_date");
				pb_notificationsDTO.seenDate = rs.getLong("seen_date");
				pb_notificationsDTO.showingDate = rs.getLong("showing_date");
				pb_notificationsDTO.sendAlarm = rs.getBoolean("send_alarm");
				pb_notificationsDTO.sendSms = rs.getBoolean("send_sms");
				pb_notificationsDTO.sendMail = rs.getBoolean("send_mail");
				pb_notificationsDTO.sendPush = rs.getBoolean("send_push");
				pb_notificationsDTO.mailSent = rs.getBoolean("mail_sent");
				pb_notificationsDTO.smsSent = rs.getBoolean("sms_sent");
				pb_notificationsDTO.pushSent = rs.getBoolean("push_sent");
				pb_notificationsDTO.isDeleted = rs.getBoolean("isDeleted");
				int i = 0;
				long primaryKey = pb_notificationsDTO.iD;
				while(i < pb_notificationsDTOList.size() && pb_notificationsDTOList.get(i).iD < primaryKey)
				{
					i ++;
				}
				pb_notificationsDTOList.add(i,  pb_notificationsDTO);
				//pb_notificationsDTOList.add(pb_notificationsDTO);
			}			
		}catch(Exception ex){
			System.out.println("Sql error: " + ex);
		}finally{
			try{ 
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e){}
			try{ 
				if (connection != null){ 
					DatabaseManager.getInstance().freeConnection(connection); 
				} 
			}catch(Exception ex2){}
		}

		return pb_notificationsDTOList;
    }
	

	
	public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO) throws Exception
    {
		System.out.println("table: " + p_searchCriteria);
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		
		try{

			String sql = "SELECT distinct pb_notifications.ID as ID FROM pb_notifications ";
			
			
			Enumeration names = p_searchCriteria.keys();
			String str, value;
			
			String AnyfieldSql = "(";
			
			if(p_searchCriteria.get("AnyField")!= null && !p_searchCriteria.get("AnyField").toString().equalsIgnoreCase(""))
			{
				int i = 0;
				Iterator it = Pb_notificationsMAPS.GetInstance().java_anyfield_search_map.entrySet().iterator();
				while(it.hasNext())
				{
					if( i > 0)
		        	{
						AnyfieldSql+= " OR  ";
		        	}
					Map.Entry pair = (Map.Entry)it.next();
					AnyfieldSql+= pair.getKey() + " like '%" + p_searchCriteria.get("AnyField").toString() + "%'";
					i ++;
				}						
			}
			AnyfieldSql += ")";
			System.out.println("AnyfieldSql = " + AnyfieldSql);
			
			String AllFieldSql = "(";
			int i = 0;
			while(names.hasMoreElements())
			{				
				str = (String) names.nextElement();
				value = (String)p_searchCriteria.get(str);
		        System.out.println(str + ": " + value);
		        if(Pb_notificationsMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()) != null &&  !Pb_notificationsMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equalsIgnoreCase("")
		        		&& !str.equalsIgnoreCase("AnyField")
		        		&& value != null && !value.equalsIgnoreCase(""))
		        {
					if(p_searchCriteria.get(str).equals("any"))
		        	{
		        		continue;
		        	}
					
		        	if( i > 0)
		        	{
		        		AllFieldSql+= " AND  ";
		        	}
		        	if(Pb_notificationsMAPS.GetInstance().java_allfield_type_map.get(str.toLowerCase()).equals("String")) 
		        	{
		        		AllFieldSql += "pb_notifications." + str.toLowerCase() + " like '%" + p_searchCriteria.get(str) + "%'";
		        	}
		        	else
		        	{
		        		AllFieldSql += "pb_notifications." + str.toLowerCase() + " = '" + p_searchCriteria.get(str) + "'";
		        	}
		        	i ++;
		        }
			}
			
			AllFieldSql += ")";
			
			System.out.println("AllFieldSql = " + AllFieldSql);
			
			
			sql += " WHERE ";
			sql += " pb_notifications.isDeleted = false";
			
			
			
			if(!AnyfieldSql.equals("()"))
			{
				sql += " AND " + AnyfieldSql;
				
			}
			if(!AllFieldSql.equals("()"))
			{			
				sql += " AND " + AllFieldSql;
			}
			
			sql += " order by pb_notifications.ID desc ";

			printSql(sql);
		
			connection = DatabaseManager.getInstance().getConnection();
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				idList.add(rs.getLong("ID"));
			}
			
			
		}catch(Exception ex){
			System.out.println("Sql error: " + ex);
		}finally{
			try{ 
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e){}
			try{ 
				if (connection != null){
					DatabaseManager.getInstance().freeConnection(connection);
				} 
			}catch(Exception ex2){}
		}
		return idList;
	}

	
		
}
	