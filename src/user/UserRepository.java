package user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


/**
 * @author Kayesh Parvez
 *
 */
public class UserRepository implements Repository {
	UserDAO userDAO = new UserDAO();
	
	
	static Logger logger = Logger.getLogger(UserRepository.class);
	static Map<String, UserDTO>mapOfUserDTOToUserName;
	static Map<Long, UserDTO>mapOfUserDTOToUserID;
	static Map<Long, Set<UserDTO> >mapOfUserDTOsToRoleID;

	static UserRepository instance = new UserRepository();  
	private UserRepository(){
		mapOfUserDTOToUserName = new ConcurrentHashMap<>();
		mapOfUserDTOToUserID = new ConcurrentHashMap<>();
		mapOfUserDTOsToRoleID = new ConcurrentHashMap<>();
		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static UserRepository getInstance(){
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<UserDTO> userDTOs = userDAO.getAllUsers(reloadAll);
			for(UserDTO userDTO : userDTOs) {
				UserDTO oldUserDTO = getUserDTOByUserID(userDTO.ID);
				if( oldUserDTO != null ) {
					
					mapOfUserDTOToUserID.remove(oldUserDTO.ID);
					mapOfUserDTOToUserName.remove(oldUserDTO.userName);
					if(mapOfUserDTOsToRoleID.containsKey(oldUserDTO.roleID)) {
						mapOfUserDTOsToRoleID.get(oldUserDTO.roleID).remove(oldUserDTO);
					}
					if(mapOfUserDTOsToRoleID.get(oldUserDTO.roleID).isEmpty()) {
						mapOfUserDTOsToRoleID.remove(oldUserDTO.roleID);
					}
				}
				if(userDTO.isDeleted == false) {
					mapOfUserDTOToUserID.put(userDTO.ID, userDTO);
					mapOfUserDTOToUserName.put(userDTO.userName, userDTO);
					if( ! mapOfUserDTOsToRoleID.containsKey(userDTO.roleID)) {
						mapOfUserDTOsToRoleID.put(userDTO.roleID, new HashSet<>());
					}
					mapOfUserDTOsToRoleID.get(userDTO.roleID).add(userDTO);
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<UserDTO> getUserList() {
		List <UserDTO> users = new ArrayList<UserDTO>(this.mapOfUserDTOToUserID.values());
		return users;
	}
	public static UserDTO getUserDTOByUserID( long userID){
		return mapOfUserDTOToUserID.get(userID);
	}
	public static UserDTO getUserDTOByUserName( String userName){
		return mapOfUserDTOToUserName.get(userName);
	}
	@SuppressWarnings("unchecked")
	public List<UserDTO> getUsersByRoleID(long roleID) {
		return (List<UserDTO>) mapOfUserDTOsToRoleID.get(roleID);
	}
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "user";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
	public static void main(String args[])throws Exception{
		UserRepository.getInstance();
	}

}

