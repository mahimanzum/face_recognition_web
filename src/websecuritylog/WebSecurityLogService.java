package websecuritylog;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import annotation.Transactional;
import connection.DatabaseConnection;
import ipRestriction.IPRestrictionService;
import login.LoginDTO;
//import sun.util.calendar.CalendarUtils;
import util.NavigationService;
import util.ServiceDAOFactory;
import util.TimeConverter;

public class WebSecurityLogService implements NavigationService {
	WebSecurityLogDAO webSecurityDAO = new WebSecurityLogDAO();
	Logger logger = Logger.getLogger(WebSecurityLogService.class);
	
	
	public WebSecurityLogDTO getDomainByID(long id) {
		WebSecurityLogDTO webSecurityDTO = new WebSecurityLogDTO();
		DatabaseConnection databaseConnection = new DatabaseConnection();
		try {
			databaseConnection.dbOpen();
			databaseConnection.dbTransationStart();

			webSecurityDTO = webSecurityDAO.getWebSecurityLogById(id, databaseConnection);

			databaseConnection.dbTransationEnd();
		} catch (Exception ex) {
			logger.fatal("Fatal", ex);
			try {
				databaseConnection.dbTransationRollBack();
			} catch (Exception ex2) {
			}
		} finally {
			databaseConnection.dbClose();
		}
		return webSecurityDTO;
	}

	public WebSecurityLogDTO addNewLog(WebSecurityLogDTO webSecurityDTO) throws Exception {
		DatabaseConnection databaseConnection = new DatabaseConnection();
		try {
			databaseConnection.dbOpen();
			databaseConnection.dbTransationStart();

			//escaping characters before insert
			webSecurityDTO.setUsername(escapeHtml4(webSecurityDTO.getUsername()));
			webSecurityDTO.setPassword(escapeHtml4(webSecurityDTO.getPassword()));
			webSecurityDTO.setUrl(escapeHtml4(webSecurityDTO.getUrl()));
			webSecurityDTO.setDescription(escapeHtml4(webSecurityDTO.getDescription()));
			
			webSecurityDAO.insertWebSecurityLog(webSecurityDTO, databaseConnection);			
			
			databaseConnection.dbTransationEnd();
		} catch (Exception ex) {
			logger.fatal("Fatal", ex);
			try {
				databaseConnection.dbTransationRollBack();
			} catch (Exception ex2) {
				logger.fatal("Fatal", ex2);
			}
			throw ex;
		} finally {
			databaseConnection.dbClose();
		}
		return webSecurityDTO;
	}

	@Override
	public Collection getIDs(LoginDTO loginDTO) throws Exception {
		List<Long> logList = new ArrayList<Long>();

		DatabaseConnection databaseConnection = new DatabaseConnection();
		try {
			databaseConnection.dbOpen();
			databaseConnection.dbTransationStart();

			logList = webSecurityDAO.getIDs(databaseConnection);

			databaseConnection.dbTransationEnd();
		} catch (Exception ex) {
			logger.fatal("Fatal", ex);
			try {
				databaseConnection.dbTransationRollBack();
			} catch (Exception ex2) {
			}
		} finally {
			databaseConnection.dbClose();
		}

		return logList;
	}

	@Override
	public Collection getIDsWithSearchCriteria(Hashtable searchCriteria, LoginDTO loginDTO)
			throws Exception {
		// TODO Auto-generated method stub
		List<Long> logList = new ArrayList<Long>();
		DatabaseConnection databaseConnection = new DatabaseConnection();
		try {
			databaseConnection.dbOpen();
			databaseConnection.dbTransationStart();
			logList = /* (List<Long>) getIDs(loginDTO, objects); **/ webSecurityDAO
					.getLogListBySearchCriteri(searchCriteria, databaseConnection);
			databaseConnection.dbTransationEnd();
		} catch (Exception ex) {
			logger.fatal("Fatal", ex);
			try {
				databaseConnection.dbTransationRollBack();
			} catch (Exception ex2) {
			}
		} finally {
			databaseConnection.dbClose();
		}
		return logList;
	}

	@Override
	public Collection getDTOs(Collection recordIDs, LoginDTO loginDTO) throws Exception {
		List<WebSecurityLogDTO> webSecurityLogDTOs = new ArrayList<WebSecurityLogDTO>();

		DatabaseConnection databaseConnection = new DatabaseConnection();
		try {
			databaseConnection.dbOpen();
			databaseConnection.dbTransationStart();
			webSecurityLogDTOs = webSecurityDAO.getLogDTOListByIDList((List<Long>) recordIDs, databaseConnection);

			databaseConnection.dbTransationEnd();
		} catch (Exception ex) {
			logger.fatal("Fatal", ex);
			try {
				databaseConnection.dbTransationRollBack();
			} catch (Exception ex2) {
			}
		} finally {
			databaseConnection.dbClose();
		}

		return webSecurityLogDTOs;
	}
	
	
	//charts begin
	public List<HashMap<String, String>> getAllUserAttemptsByAttemptCategoryAndDuration(int attemptCategory, String start, String end) throws Exception{
		List<HashMap<String, String>> allAttemptsByAttemptCategoryAndDuration = new ArrayList<HashMap<String, String>>();
		
		if(end.equals("-1")){
			long tempDate = TimeConverter.getStartTimeOfTheDay(System.currentTimeMillis());
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(tempDate);
			cal.add(Calendar.DATE, -Integer.parseInt(start));
			long startDate = cal.getTimeInMillis();
			
			long endDate = System.currentTimeMillis();
			allAttemptsByAttemptCategoryAndDuration = webSecurityDAO.getAllUserAttemptsByAttemptCategoryAndDuration(attemptCategory, startDate, endDate);
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			long tempDate = TimeConverter.getStartTimeOfTheDay(sdf.parse(start).getTime());
			long startDate = tempDate;
			
			tempDate = TimeConverter.getStartTimeOfTheDay(sdf.parse(end).getTime());
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(tempDate);
			cal.add(Calendar.DATE, 1);
			long endDate = cal.getTimeInMillis();
			
			allAttemptsByAttemptCategoryAndDuration = webSecurityDAO.getAllUserAttemptsByAttemptCategoryAndDuration(attemptCategory, startDate, endDate);
		}
		return allAttemptsByAttemptCategoryAndDuration;
	}
	
	public List<HashMap<String, String>> getAllIpAttemptsByAttemptCategoryAndDuration(int attemptCategory, String start, String end) throws Exception{
		List<HashMap<String, String>> allAttemptsByAttemptCategoryAndDuration = new ArrayList<HashMap<String, String>>();
		
		if(end.equals("-1")){
			long tempDate = TimeConverter.getStartTimeOfTheDay(System.currentTimeMillis());
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(tempDate);
			cal.add(Calendar.DATE, -Integer.parseInt(start));
			long startDate = cal.getTimeInMillis();
			
			long endDate = System.currentTimeMillis();
			allAttemptsByAttemptCategoryAndDuration = webSecurityDAO.getAllIpAttemptsByAttemptCategoryAndDuration(attemptCategory, startDate, endDate);
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			long tempDate = TimeConverter.getStartTimeOfTheDay(sdf.parse(start).getTime());
			long startDate = tempDate;
			
			tempDate = TimeConverter.getStartTimeOfTheDay(sdf.parse(end).getTime());
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(tempDate);
			cal.add(Calendar.DATE, 1);
			long endDate = cal.getTimeInMillis();
			
			allAttemptsByAttemptCategoryAndDuration = webSecurityDAO.getAllIpAttemptsByAttemptCategoryAndDuration(attemptCategory, startDate, endDate);
		}
		return allAttemptsByAttemptCategoryAndDuration;
	}

}
