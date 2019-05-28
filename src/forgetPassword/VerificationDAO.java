package forgetPassword;

import static util.SqlGenerator.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

import annotation.Transactional;
import common.RequestFailureException;
import connection.DatabaseConnection;
import util.ModifiedSqlGenerator;
import util.SqlGenerator;

/**
 * @author Alam
 */
public class VerificationDAO {

	/**
	 * This method add a new row to token table
	 * @author Alam
	 * @param forgetPassword
	 * @throws Exception 
	 */
	public void add(VerificationDTO verificationDTO) throws Exception {		
		ModifiedSqlGenerator.insert(verificationDTO);
	}

	/**
	 * This method returns a which mathces given username from token table
	 * @author Alam
	 * @param username
	 * @return ForgetPassword an ForgetPassword object. Null if not found
	 * @throws Exception 
	 */
	public static VerificationDTO getByUsername(String username) throws Exception {
		
		DatabaseConnection db = new DatabaseConnection();
		List<VerificationDTO> listOfForgetPassword = null;
		try {
			
			String whereClause = " where " + getColumnName(VerificationDTO.class, "username" ) + " = ?";
			
			db.dbOpen();
			db.dbTransationStart();
			
			listOfForgetPassword = (List<VerificationDTO>) getAllObjectList(VerificationDTO.class, db, whereClause, username );
			
			db.dbTransationEnd();	
			
		}
		catch(Exception ex) {
			
			System.out.println( ex.toString() );
			
			try {
				
				db.dbTransationRollBack();
				
			} 
			catch (Exception ex2) {
				
			}
			if(ex instanceof RequestFailureException){
				
				throw (RequestFailureException)ex;
			}
			throw ex;
		} 
		finally {
			
			db.dbClose();
		}
		
		if( listOfForgetPassword.size() == 1 )
			return listOfForgetPassword.get(0);
		else 
			return null;
	}

	public VerificationDTO getVerificationDTOByTokenID(long tokenID) throws Exception
	{
		return ModifiedSqlGenerator.getObjectByID(VerificationDTO.class, tokenID);
	}

	public VerificationDTO getVerificationDTOByToken(String token) throws Exception
	{
		List<VerificationDTO> verificationDTOs = ModifiedSqlGenerator.getAllObjectListFullyPopulated(VerificationDTO.class," where " + getColumnName(VerificationDTO.class, "token"));
		if(verificationDTOs.size() > 0) return verificationDTOs.get(0);
		else return null;		
	}
	
	public List<VerificationDTO> getVerificationDTOsBeforeCurrentTime(String username,int authType) throws Exception
	{
		String[] columnNames = new String[] {getColumnName(VerificationDTO.class, "username"), getColumnName(VerificationDTO.class, "authPurpose")};
		List<VerificationDTO> verificationDTOs = ModifiedSqlGenerator.getObjectFullyPopulatedByString(VerificationDTO.class, new String[] {username, ""+authType}, columnNames, " and " + getColumnName(VerificationDTO.class, "expirationTime") + " < " + System.currentTimeMillis());
		return verificationDTOs;	
	}
	
	public List<VerificationDTO> getVerificationDTOsAfterCurrentTime(String username,int authType) throws Exception
	{
		String[] columnNames = new String[] {getColumnName(VerificationDTO.class, "username"), getColumnName(VerificationDTO.class, "authPurpose")};
		List<VerificationDTO> verificationDTOs = ModifiedSqlGenerator.getObjectFullyPopulatedByString(VerificationDTO.class, new String[] {username, ""+authType}, columnNames, " and " + getColumnName(VerificationDTO.class, "expirationTime") + " >= " + System.currentTimeMillis());
		return verificationDTOs;	
	}
	
	public void updateVerificationDTO(VerificationDTO verificationDTO) throws Exception
	{
		ModifiedSqlGenerator.updateEntity(verificationDTO);
	}
	
	public void deleteVerificationDTO(long id) throws Exception
	{
		ModifiedSqlGenerator.deleteHardEntityByID(VerificationDTO.class, id);
	}
	
	public void deleteVerificationDTO(String username, int authPurpose) throws Exception
	{
		List<VerificationDTO> verificationDTOs = getVerificationDTOsAfterCurrentTime(username, authPurpose);
		for(VerificationDTO verificationDTO: verificationDTOs)
		{
			ModifiedSqlGenerator.deleteHardEntityByID(VerificationDTO.class, verificationDTO.getId());
		}
	}


}
