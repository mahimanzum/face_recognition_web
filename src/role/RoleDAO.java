package role;

import databasemanager.DatabaseManager;

import java.sql.*;
import java.util.*;

import javax.management.relation.RoleResult;

import login.LoginDTO;
import permission.ColumnDTO;
import permission.ColumnPermissionDTO;
import permission.MenuDTO;
import repository.RepositoryManager;

import org.apache.log4j.Logger;

import common.RequestFailureException;
import util.ConnectionUtil;
import util.DAOResult;
import util.NavigationService;

public class RoleDAO implements NavigationService
{
	static Logger logger= Logger.getLogger((role.RoleDAO.class).getName());;

    public RoleDAO()
    {
    }

    public Collection getDTOs(Collection recordIDs, LoginDTO loginDTO)  
    {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement stmt = null;
        String sql;
        Collection data = null;
        
        try
        {
	        sql = "select ID, roleName, description from role ";
	        if(recordIDs.size() > 0)
	        {
	            sql = sql + " where  ( ";
	            for(int i = 0; i < recordIDs.size(); i++)
	            {
	                sql = sql + "ID = " + ((ArrayList)recordIDs).get(i);
	                if(i <= recordIDs.size() - 2)
	                    sql = sql + " or ";
	            }
	
	            sql = sql + " ) ";
	        }
	        sql = sql + " order by ID ";
	        //System.out.println("sql:"+sql);
	        data = new ArrayList();
	        connection = DatabaseManager.getInstance().getConnection();
	        stmt = connection.createStatement();
	        RoleDTO row;
	        //System.out.println("kundu");
	        for(resultSet = stmt.executeQuery(sql); resultSet.next(); data.add(row))
	        {
	        	//System.out.println("kundu");
	            row = new RoleDTO();
	            row.ID = resultSet.getInt("ID");
	            row.roleName = resultSet.getString("roleName");
	            row.description =resultSet.getString("description");
	           
	        }
	        resultSet.close();
    	}
        catch(Exception ex)
        {
            logger.fatal("",ex);
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
            catch (Exception ex) {
                logger.fatal("DAO ", ex);
            }
          try{ if (stmt != null) {stmt.close();}} catch (Exception e){}
          try{ if (connection != null){ DatabaseManager.getInstance().freeConnection(connection); } }
          catch (Exception ex){ logger.fatal("",ex); }
        }
        
        return data;
    }

    public Collection<Long> geIDs(LoginDTO loginDTO) 
    {
        String sql;
        Connection connection=null;
        ResultSet resultSet = null;
        Statement stmt=null;
        Collection<Long> data = new ArrayList<Long>();
        try
        {
            sql = "select ID from role ";
	        connection = DatabaseManager.getInstance().getConnection();
	        stmt = connection.createStatement();
	        for(resultSet = stmt.executeQuery(sql); resultSet.next(); data.add(resultSet.getLong("ID")));
	        
	        resultSet.close();
        }
        catch(Exception ex)
        {
            logger.fatal("role.RoleDAO: " + ex.toString());
        }
        finally
        {
          try{ if (stmt != null) {stmt.close();}} catch (Exception e){}
          try{ if (connection != null){ DatabaseManager.getInstance().freeConnection(connection); } }catch (Exception e) {}
        }
        
        return data;
    }

    public Collection getIDs(LoginDTO loginDTO) 
    {
        String sql;
        Connection connection=null;
        ResultSet resultSet = null;
        Statement stmt=null;
        ArrayList data = new ArrayList();
        try
        {
	        sql = "select ID from role ";
	
	        connection = DatabaseManager.getInstance().getConnection();
	        stmt = connection.createStatement();
	        for(resultSet = stmt.executeQuery(sql); resultSet.next(); data.add(resultSet.getString("ID")));
	        
	        resultSet.close();
        }
	    catch(Exception ex)
        {
            logger.fatal("",ex);
        }
	    finally
        {
          try{ if (stmt != null) {stmt.close();}} catch (Exception e){}
          try{ if (connection != null){ DatabaseManager.getInstance().freeConnection(connection); } }
          catch (Exception e){ logger.fatal("role.RoleDAO :" + e.toString()); }
        }
        return data;
    }

    public Collection getIDsWithSearchCriteria(Hashtable p_searchCriteria, LoginDTO loginDTO) throws Exception
    {
    	Hashtable<String, String> searchCriteria = (Hashtable<String, String>)p_searchCriteria;
    	List<Long> idList = new ArrayList<>();
    	Connection connection = null;
    	PreparedStatement ps = null;
    	try{
    		boolean conditionAdded = false;
    		List<Object> objectList = new ArrayList<>();
    		String sql = "SELECT ID FROM role";
    		String roleNameInputFromUI = searchCriteria.get("roleName");
    		if(roleNameInputFromUI!=null && !roleNameInputFromUI.trim().equals("")){
    			if(conditionAdded){
    	       		sql+= " AND  ";  
    			}else{
    		    	sql+= " WHERE  ";  
    			}
    			conditionAdded = true;
    			sql+="roleName LIKE ? ";
    			objectList.add("%"+roleNameInputFromUI+"%");
    		}
    	
    		String descriptionInputFromUI = searchCriteria.get("description");
    		if(descriptionInputFromUI!=null && !descriptionInputFromUI.trim().equals("")){
    			if(conditionAdded){
    	       		sql+= " AND  ";  
    			}else{
    		    	sql+= " WHERE  ";  
    			}
    			conditionAdded = true;
    			sql+="description LIKE ? ";
    			objectList.add("%"+descriptionInputFromUI+"%");
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

    public DAOResult addRole(RoleDTO roleDTO)
    {
    	String sql = null;
        DAOResult daoResult = new DAOResult();
        Connection cn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        
        try
        {
        	cn = DatabaseManager.getInstance().getConnection();
            
            sql = "select ID from role where roleName = '" + roleDTO.roleName + "'";
        	stmt = cn.createStatement();
        	resultSet = stmt.executeQuery(sql);
        	if (resultSet.next())
            {
              resultSet.close();
              daoResult.setResult("error.roleName.role", false, DAOResult.VALIDATION_ERROR);
              return daoResult;
            }
        	
        	long roleID = DatabaseManager.getInstance().getNextSequenceId("role");
        	roleDTO.ID = roleID;
            sql = "insert into role(ID,roleName,description) values(" + roleID + ",'" + roleDTO.roleName + "','" + roleDTO.description + "'" + ")";
            logger.debug("sql " + sql);
            stmt.executeUpdate(sql);

            
          
	        daoResult.setResult("", true, DAOResult.DONE);

	    }
	    catch (Exception e)
	    {
	      daoResult.setResult(e.toString(), false, DAOResult.DB_EXCEPTION);
	      logger.fatal("DAO ", e);
	      try
	      {
	        if (cn != null)
	        {
	          cn.rollback();
	        }
	      }
	      catch (Exception ex)
	      {
	        logger.fatal("DAO ", ex);
	      }
	
	    }
	    finally
	    {
	      try
	      {
	        if (stmt != null)
	        {
	          stmt.close();
	        }
	      }
	      catch (Exception e)
	      {
	        logger.fatal("DAO finally :" + e.toString());
	      }
	
	      try
	      {
	        if (cn != null)
	        {
	          cn.setAutoCommit(true);
	          DatabaseManager.getInstance().freeConnection(cn);
	        }
	      }
	      catch (Exception e)
	      {
	        logger.fatal("DAO finally :" + e.toString());
	      }
	    }
        
        PermissionRepository.reload();
        
	    return daoResult;
	}

    public RoleDTO getRole(long p_id) 
    {
    	String sql = "select ID, roleName, description from role where ID = " + p_id;
    	Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
        //logger.debug(sql);

        RoleDTO row = null;
        
        try
        {
        	
	        connection = DatabaseManager.getInstance().getConnection();
	        stmt = connection.createStatement();
	        resultSet = stmt.executeQuery(sql);
	
	        if (resultSet.next())
	        {
	          row = new RoleDTO();
	          row.ID= resultSet.getInt("ID");
	          row.roleName = resultSet.getString("roleName");
	          row.description = resultSet.getString("description");
	        }
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
            catch (Exception ex) {
                logger.fatal("DAO ", ex);
            }
          try{if (stmt != null){stmt.close();}}catch (Exception e){}
          try{if (connection != null){DatabaseManager.getInstance().freeConnection(connection);}}
          catch (Exception e){logger.fatal("DAO finally :" + e.toString());}
        }
        
        return row;
    }

 
    
    public void updateRoleDTO(RoleDTO roleDTO){
		Connection connection = null;
		PreparedStatement ps = null;

		long currentTime = System.currentTimeMillis();
		try{
			connection = DatabaseManager.getInstance().getConnection();

			String sql ="UPDATE role SET roleName=?,description=?,isDeleted=?,lastModificationTime=? WHERE ID = ?";

			ps = connection.prepareStatement(sql);

			int index = 1;

			ps.setObject(index++,roleDTO.roleName);
			ps.setObject(index++,roleDTO.description);
			ps.setObject(index++,roleDTO.isDeleted);
			ps.setObject(index++,currentTime);
			ps.setObject(index++,roleDTO.ID);

			ps.executeUpdate();

			PermissionRepository.reload();
			
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
    

    public DAOResult dropRoles(long[] p_ids)
    {
      DAOResult daoResult = new DAOResult();
      DatabaseManager dm = null;
      Connection cn = null;
      Statement stmt = null;
      long currentTime = System.currentTimeMillis();
      
      String sql = "delete from role where ID =";
      String permissionDelSql = "delete from menu_permission where roleID=";
      String userDelSql = "delete from user where roleID=";
      String columnDelSql= "delete from column_permission where roleID=";
      if (p_ids == null || p_ids.length == 0)
      {
        daoResult.setResult("", true, DAOResult.DONE);
        return daoResult;
      }

      try
      {
        dm = DatabaseManager.getInstance();
        cn = dm.getConnection();
        cn.setAutoCommit(false);
        stmt = cn.createStatement();

        for (int i = 0; i < p_ids.length; i++)
        {
          String fullQuery = sql+p_ids[i];
          stmt.executeUpdate(fullQuery);
          fullQuery = permissionDelSql + p_ids[i];
          stmt.executeUpdate(fullQuery);
          fullQuery = userDelSql + p_ids[i];
          int numberOfDeletedUser = stmt.executeUpdate(fullQuery);
          if(numberOfDeletedUser!=0){
        	  throw new RequestFailureException("User exists with role ID "+p_ids[i]);
          }
          fullQuery = columnDelSql + p_ids[i];
          stmt.executeUpdate(fullQuery);
        }

        ConnectionUtil.updateVbSequencer("role", currentTime, stmt);
        
        cn.commit();
        daoResult.setResult("", true, DAOResult.DONE);
        PermissionRepository.reload();
      }
      catch (Exception e)
      {
    	  try {
			cn.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        daoResult.setResult(e.toString(), false, DAOResult.DB_EXCEPTION);
        logger.fatal("DAO " + e);
        if(e instanceof RequestFailureException){
        	throw (RequestFailureException)e;
        }
      }
      finally
      {
          try
          {
            stmt.close();
          }catch(Throwable th)
          {
            logger.fatal("Failed to close Statement",th);
          }
        try
        {
          if (cn != null)
          {
            cn.setAutoCommit(true);
            dm.freeConnection(cn);
          }
        }
        catch (Exception e)
        {
          logger.fatal("DAO finally :" + e.toString());
        }
      }

      return daoResult;
    }

	public List<RoleDTO> getRolesByPartialMatch(String roleName) 
    {
    	String sql = "select ID, roleName, description from role where roleName like '" + roleName + "%'";
    	Connection connection=null;
    	Statement stmt=null;
    	ResultSet resultSet = null;
        //logger.debug(sql);
    	List<RoleDTO> roleList = new ArrayList<RoleDTO>();
        
        
        try
        {
	        connection = DatabaseManager.getInstance().getConnection();
	        stmt = connection.createStatement();
	        resultSet = stmt.executeQuery(sql);
	
	        while (resultSet.next())
	        {
	        	RoleDTO roleDTO = new RoleDTO();
	        	roleDTO.ID= resultSet.getInt("ID");
	        	roleDTO.roleName = resultSet.getString("roleName");
	        	roleDTO.description = resultSet.getString("description");
	        	roleList.add(roleDTO);
	        }
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
            catch (Exception ex) {
                logger.fatal("DAO ", ex);
            }
          try{if (stmt != null){stmt.close();}}catch (Exception e){}
          try{if (connection != null){DatabaseManager.getInstance().freeConnection(connection);}}
          catch (Exception e){logger.fatal("DAO finally :" + e.toString());}
        }
        
        return roleList;
    }




 }