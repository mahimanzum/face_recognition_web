/**
 * @(#)LanguageDAO.java
 * @author Shuvo
 * @history
 *          created : <>  Date : MMM-DD-YYYY <venue>
 * @version <>
 *
 * Copyright <YYYY> <company>.
 *
 * All rights reserved.
 *
 * This software is the confidential and proprietdary information
 * of <company>("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with <company>.
 *
 */

package language;

import java.util.*;


import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.*;
import databasemanager.*;
import login.LoginDTO;
import util.*;
import org.apache.log4j.Logger;

public class LanguageDAO implements NavigationService
{
  static Logger logger = Logger.getLogger(LanguageDAO.class.getName());

  public LanguageDAO()
  {
  }

  /**
   * This method only returns DTOs
   * specified in collection recordIDs
   * @param recordIDs
   * @return Collection
   */
	public Collection getDTOs(Collection recordIDs, LoginDTO loginDTO){

		if(recordIDs.isEmpty()) return new ArrayList();

		List resultList = new ArrayList();

		String sql = "SELECT * FROM language_text WHERE ID IN (";

		for(int i = 0;i<recordIDs.size();i++){
			if(i!=0) sql += ",";
			sql += ((ArrayList)recordIDs).get(i);
		}
		sql += ")";

		Connection connection = null;
		Statement stmt = null;
		try{
			connection = DatabaseManager.getInstance().getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				LanguageTextDTO languageTextDTO = new LanguageTextDTO();
				languageTextDTO.ID = rs.getLong("ID");
				languageTextDTO.menuID = rs.getLong("menuID");
				languageTextDTO.languageTextEnglish = rs.getString("languageTextEnglish");
				languageTextDTO.languageTextBangla = rs.getString("languageTextBangla");
				languageTextDTO.languageConstantPrefix = rs.getString("languageConstantPrefix");
				languageTextDTO.languageConstant = rs.getString("languageConstant");
				resultList.add(languageTextDTO);
			}
		}catch(Exception ex){
			logger.fatal("",ex);
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
		return resultList;
	}



  public Collection getAllLanguages() throws Exception
  {
	  Collection data = new ArrayList();
	  Connection connection = null;
	  Statement stmt = null;
	  try{
		  connection = DatabaseManager.getInstance().getConnection();
		  stmt = connection.createStatement();
		  String sql = "select ID, languageName, description from language order by languageName ";
		  ResultSet resultSet = stmt.executeQuery(sql);

		  while (resultSet.next())
		  {
			  LanguageDTO row = new LanguageDTO();

			  try{
				  row.ID = resultSet.getLong("ID");
				  row.languageName = resultSet.getString("languageName");
				  row.description = resultSet.getString("description");
				  data.add(row);

			  }catch(Exception ex){
				  logger.debug("Exception at getting language",ex);
			  }
		  }
	  }
	  catch(Exception e)
	  {
		  logger.fatal("",e);
	  }
	  finally{
		  try{ if (stmt != null) {stmt.close();}} catch (Exception e){}
		  try{ if (connection != null){ DatabaseManager.getInstance().freeConnection(connection); }}catch (Exception e){}
	  }

	  return data;
  }

  /**
   * Return all IDs from db
   * @return Collection
   */

  public Collection getIDs(LoginDTO loginDTO) throws Exception
  {
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			boolean conditionAdded = false;
			List<Object> objectList = new ArrayList<>();
			String sql = "SELECT ID FROM language_text";
		
			connection = DatabaseManager.getInstance().getConnection();
			ps = connection.prepareStatement(sql);
			for(int i = 0;i<objectList.size();i++){
				ps.setObject(i+1, objectList.get(i));
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				idList.add(rs.getLong(1));
			}
			
			
		}catch(Exception ex){
			logger.fatal("",ex);
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

  /**
   * Returns IDs only matching search criteira
   * @param p_searchCriteria
   * @return Collection
   */
  public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, login.LoginDTO loginDTO) throws Exception
  {
		List<Long> idList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			boolean conditionAdded = false;
			List<Object> objectList = new ArrayList<>();
			String sql = "SELECT ID FROM language_text";
			Hashtable<String, String> searchCriteria = (Hashtable<String, String>)p_searchCriteria; 
			String menuIDInputFromUI = searchCriteria.get("menuID");
			if(menuIDInputFromUI!=null && !menuIDInputFromUI.trim().equals("")){
				if(conditionAdded){
		       		sql+= " AND  ";  
				}else{
			    	sql+= " WHERE  ";  
				}
				conditionAdded = true;
				sql+="menuID = ? ";
				objectList.add(menuIDInputFromUI);
			}
		
			String languageTextEnglishInputFromUI = searchCriteria.get("languageTextEnglish");
			if(languageTextEnglishInputFromUI!=null && !languageTextEnglishInputFromUI.trim().equals("")){
				if(conditionAdded){
		       		sql+= " AND  ";  
				}else{
			    	sql+= " WHERE  ";  
				}
				conditionAdded = true;
				sql+="languageTextEnglish LIKE ? ";
				objectList.add("%"+languageTextEnglishInputFromUI+"%");
			}
		
			String languageTextBanglaInputFromUI = searchCriteria.get("languageTextBangla");
			if(languageTextBanglaInputFromUI!=null && !languageTextBanglaInputFromUI.trim().equals("")){
				if(conditionAdded){
		       		sql+= " AND  ";  
				}else{
			    	sql+= " WHERE  ";  
				}
				conditionAdded = true;
				sql+="languageTextBangla LIKE ? ";
				objectList.add("%"+languageTextBanglaInputFromUI+"%");
			}
		
			String languageConstantPrefixInputFromUI = searchCriteria.get("languageConstantPrefix");
			if(languageConstantPrefixInputFromUI!=null && !languageConstantPrefixInputFromUI.trim().equals("")){
				if(conditionAdded){
		       		sql+= " AND  ";  
				}else{
			    	sql+= " WHERE  ";  
				}
				conditionAdded = true;
				sql+="languageConstantPrefix LIKE ? ";
				objectList.add("%"+languageConstantPrefixInputFromUI+"%");
			}
			
			String languageConstantInputFromUI = searchCriteria.get("languageConstant");
			if(languageConstantInputFromUI!=null && !languageConstantInputFromUI.trim().equals("")){
				if(conditionAdded){
		       		sql+= " AND  ";  
				}else{
			    	sql+= " WHERE  ";  
				}
				conditionAdded = true;
				sql+="languageConstant LIKE ? ";
				objectList.add("%"+languageConstantInputFromUI+"%");
			}
		
		
			connection = DatabaseManager.getInstance().getConnection();
			ps = connection.prepareStatement(sql);
			for(int i = 0;i<objectList.size();i++){
				ps.setObject(i+1, objectList.get(i));
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				idList.add(rs.getLong(1));
			}
			
			
		}catch(Exception ex){
			logger.fatal("",ex);
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



  public LanguageDTO getLanguage(long ID) throws Exception
  {
    LanguageDTO row = new LanguageDTO();
    Connection connection  = null;
    Statement stmt = null;
    
    try
    {
    	connection = DatabaseManager.getInstance().getConnection();
    	stmt = connection.createStatement();
    	
    	String sql =  "select ID, languageName, description from language where ID =  " + ID;
    	ResultSet resultSet = stmt.executeQuery(sql);

    	
    	while (resultSet.next())
    	{
    		try{
    			row.ID = resultSet.getLong("ID");
    			row.languageName = resultSet.getString("languageName");
    			row.description = resultSet.getString("description");
    		}catch(Exception ex){
    			logger.debug("Exception at decoding language");
    		}
    	}

    	sql = "select ID, languageTextEnglish, languageTextBangla, languageConstant from language_text";// where languageID = "+ID;
    	resultSet = stmt.executeQuery(sql);
    	while (resultSet.next())
    	{
    		LanguageTextDTO languageTextDTO = new LanguageTextDTO();
    		try{
    			languageTextDTO.ID = resultSet.getLong("ID");
    			languageTextDTO.languageTextEnglish = resultSet.getString("languageTextEnglish");
    			languageTextDTO.languageTextBangla = resultSet.getString("languageTextBangla");
    			languageTextDTO.languageConstant = resultSet.getString("languageConstant");
    		}catch(Exception ex){
    			logger.debug("Exception at decoding language values");
    		}
    		row.languageIDTextList.add(languageTextDTO);
    	}
    }
    catch(Exception ex)
    {
    	logger.fatal("",ex);
    }
    finally{
    	try{ if (stmt != null) {stmt.close();}} catch (Exception e){}
    	try{ if (connection != null){ DatabaseManager.getInstance().freeConnection(connection); }}catch (Exception e){}
    }   

    return row;
  }

  public DAOResult updateLanguage(ArrayList<LanguageTextDTO> languageTextDTOList)
  {
    String sql = null;
    DAOResult daoResult = new DAOResult();
    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    long currentTime = System.currentTimeMillis();

    try
    {
      cn = DatabaseManager.getInstance().getConnection();
      cn.setAutoCommit(false);

      /*sql = "delete from language_text";
      ps = cn.prepareStatement(sql);
      ps.executeUpdate();
      ps.close();*/

      sql = "update language_text set menuID = ?, languageTextEnglish = ?, languageTextBangla = ?, languageConstantPrefix = ?, languageConstant = ? where ID = ?";
      ps = cn.prepareStatement(sql);
      
      for(LanguageTextDTO languageTextDTO :  languageTextDTOList)
      {        
        int index = 1;        
        ps.setLong(index++, languageTextDTO.menuID);
        ps.setString(index++, languageTextDTO.languageTextEnglish);
        ps.setString(index++, languageTextDTO.languageTextBangla);
        ps.setString(index++, languageTextDTO.languageConstantPrefix);
        ps.setString(index++, languageTextDTO.languageConstant);
        ps.setLong(index++, languageTextDTO.ID);
        ps.addBatch();
      }
      
      if(languageTextDTOList.size() > 0)
    	  ps.executeBatch();
      ps.close();
      
      ConnectionUtil.updateVbSequencer("language_text", currentTime, cn, ps);    
     
      cn.commit();
      daoResult.setResult("", true, DAOResult.DONE);

    }
    catch (Exception e)
    {
    	daoResult.setResult(e.toString(), false, DAOResult.DB_EXCEPTION);
    	logger.fatal("", e);
    	try {
    		cn.rollback();
    	} catch (SQLException e1) {		
    		logger.fatal("",e);
    	}
    }
    finally
    {
      ConnectionUtil.closeConnection(cn, ps, null);
    }

    return daoResult;
  }

  public void dropLanguages(String[] ids)
  {
	  if(ids == null || ids.length == 0)
	  {
		  return;
	  }
	  String sql = "delete from language_text where ID in ";	  
	  sql = sql + StringUtils.getCommaSeparatedString(ids);
	  
	  logger.debug("sql " + sql);
	  
	  Connection cn = null;
	  Statement stmt = null;

	  long currentTime = System.currentTimeMillis();
	  try
	  {
		  cn = DatabaseManager.getInstance().getConnection();
		  cn.setAutoCommit(false);

		  stmt = cn.createStatement();
		  stmt.executeUpdate(sql);

		  ConnectionUtil.updateVbSequencer("language_text", currentTime, stmt);

		  cn.commit();
	  }
	  catch (Exception e)
	  {
		  logger.fatal("DAO ",e);
	  }
	  finally
	  {
		 ConnectionUtil.closeConnection(cn, null, stmt);
	  }

  }

  public void addLanguageText(LanguageTextDTO languageTextDTO) {
	  // TODO Auto-generated method stub
	  Connection connection = null;
	  PreparedStatement ps = null;

	  try{
		  connection = DatabaseManager.getInstance().getConnection();

		  languageTextDTO.ID = DatabaseManager.getInstance().getNextSequenceId("language_text");

		  String sql ="insert into language_text(ID,menuID,languageTextEnglish,languageTextBangla,languageConstantPrefix,languageConstant) VALUES(?,?,?,?,?,?)";

		  ps = connection.prepareStatement(sql);

		  int index = 1;

		  ps.setObject(index++,languageTextDTO.ID);
		  ps.setObject(index++,languageTextDTO.menuID);
		  ps.setObject(index++,languageTextDTO.languageTextEnglish);
		  ps.setObject(index++,languageTextDTO.languageTextBangla);
		  ps.setObject(index++,languageTextDTO.languageConstantPrefix);
		  ps.setObject(index++,languageTextDTO.languageConstant);

		  ps.execute();

	  }catch(Exception ex){
		  logger.fatal("",ex);
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




}
