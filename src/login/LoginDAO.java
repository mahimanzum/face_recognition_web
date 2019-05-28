package login;


import org.apache.log4j.Logger;

import util.PasswordUtil;
import user.UserDAO;
import user.UserDTO;



public class LoginDAO {

    static Logger logger = Logger.getLogger(LoginDAO.class.getName());

    public LoginDAO() {
    }

    public LoginDTO validateLogin(String userName,String password, LoginDTO loginDTO) throws Exception
    {   
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = userDAO.getUserDTOByUsername(userName);
		if(userDTO == null)
		{
			return null;
		}
    	String encryptedPass = PasswordUtil.getInstance().encrypt(password);
    	logger.debug("encryptedPass " + encryptedPass + " udto.getPassword() " + userDTO.password);
    	if(!userDTO.password.equals(encryptedPass)) 
    	{
    		return null;
    	} 
    	loginDTO.userID = userDTO.ID;
    	
    	LoginDTO loginDTO2 = new LoginDTO();
    	loginDTO2.loginSourceIP = loginDTO.loginSourceIP;
    	loginDTO2.userID = loginDTO.userID;
    	return loginDTO2;
    }
   
}
