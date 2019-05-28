package util;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import login.LoginDTO;
//import org.apache.log4j.Logger;

import sessionmanager.SessionConstants;

public class RecordNavigationManager
{
	String m_navigatorName;
	String m_dtoCollectionName;
	HttpServletRequest m_request;
	NavigationService m_service;
	String m_searchFieldInfo[][];

	//static Logger logger = Logger.getLogger(RecordNavigationManager.class.getName());
	
    public RecordNavigationManager(String p_navigatorName, HttpServletRequest p_request, NavigationService p_service, String p_dtoCollectionName, String p_searchFieldInfo[][])
    {
        m_navigatorName = p_navigatorName;
        m_request = p_request;
        m_service = p_service;
        m_dtoCollectionName = p_dtoCollectionName;
        m_searchFieldInfo = p_searchFieldInfo;
    }
    
    public void doJob(LoginDTO loginDTO) throws Exception
    {
    	doJob(loginDTO, null);
    }

    public void doJob(LoginDTO loginDTO, ArrayList<String> table_names) throws Exception
    {
    	
        Collection records = null;
        ArrayList recordIDs = null;
        Collection recordIDToSend = null;
        System.out.println("Doing Job");
        
        HttpSession session = m_request.getSession();
        
        RecordNavigator rn = (RecordNavigator)session.getAttribute(m_navigatorName);
        String goCheck = m_request.getParameter(SessionConstants.GO_CHECK_FIELD);
        String searchCheck = m_request.getParameter(SessionConstants.SEARCH_CHECK_FIELD);
        String htmlsearchCheck = m_request.getParameter(SessionConstants.HTML_SEARCH_CHECK_FIELD);
        String id = m_request.getParameter(SessionConstants.NAVIGATION_LINK);
        String multidao = m_request.getParameter("multidao");

        
        
        //logger.debug(searchCheck);
        
        if(id != null)
        {
            //logger.debug("Navigation links");
            int pageno = rn.getCurrentPageNo();
            if(!id.equals(SessionConstants.ADVANCED_SEARCH))
            {
            	if(id.equals(SessionConstants.NAVIGATION_BAR_FIRST))
                {
                    if(rn.getTotalPages() >= 1)
                        pageno = 1;
                } 
                else if(id.equals(SessionConstants.NAVIGATION_BAR_NEXT))
                {
                    if(pageno < rn.getTotalPages())
                        pageno++;
                }
                else if(id.equals(SessionConstants.NAVIGATION_BAR_PREVIOUS))
                {
                    if(pageno > 1)
                        pageno--;
                }
                else if(id.equals(SessionConstants.NAVIGATION_BAR_LAST))
                    pageno = rn.getTotalPages();
                else if(id.equals(SessionConstants.NAVIGATION_BAR_CURRENT))
                    pageno = rn.getCurrentPageNo();
            }
            rn.setCurrentPageNo(pageno);
        } 
        else if(goCheck != null)
        {
            //logger.debug("Go");
            int tempNumber = Integer.parseInt(m_request.getParameter("pageno"));
            if(tempNumber <= rn.getTotalPages() && tempNumber > 0)
                rn.setCurrentPageNo(tempNumber);
        } 
        else if(searchCheck != null)
        {
            if(htmlsearchCheck != null)
            {
                session.setAttribute(m_navigatorName, null);
                rn = new RecordNavigator();
                rn.setSearchFieldInfo(m_searchFieldInfo);
            }
            //logger.debug("Search");
            Enumeration paramList = m_request.getParameterNames();
            Hashtable searchValues = new Hashtable();
            do
            {
                if(!paramList.hasMoreElements())
                    break;
                String paramName = (String)paramList.nextElement();
                if(!paramName.equalsIgnoreCase(SessionConstants.RECORDS_PER_PAGE) && !paramName.equalsIgnoreCase(SessionConstants.SEARCH_CHECK_FIELD) && !paramName.equalsIgnoreCase(SessionConstants.HTML_SEARCH_CHECK_FIELD))
                {
                    String paramValue = m_request.getParameter(paramName);
                    session.setAttribute(paramName, paramValue);
                    searchValues.put(paramName, paramValue);
                }
            } while(true);
            
            //logger.debug("Search Values (specific search)");
            //logger.debug(searchValues.toString());
            if(multidao != null)
            {
            	//recordIDs = (ArrayList)m_service.getIDsWithSearchCriteria(searchValues, loginDTO, table_names);
            }
            else
            {
            	recordIDs = (ArrayList)m_service.getIDsWithSearchCriteria(searchValues, loginDTO);
            }
            int pageSizeInt = -1;
            String pageSize = m_request.getParameter(SessionConstants.RECORDS_PER_PAGE);
            //logger.debug("Page Size Given : " + pageSize);
            
            if(pageSize != null)
            {
            	try
                {
                    pageSizeInt = Integer.parseInt(pageSize);
                    if(pageSizeInt > 0)
                        rn.setPageSize(pageSizeInt);
                }
                catch(NumberFormatException ex)
                {
                    //logger.fatal("Next page Size is not number ");
                }
            }
            
            int totalPage = recordIDs.size() / rn.getPageSize();
            if(recordIDs.size() % rn.getPageSize() != 0)
                totalPage++;
            if(recordIDs.size() > 0)
                rn.setCurrentPageNo(1);
            else
                rn.setCurrentPageNo(0);
            
            rn.setIDs(recordIDs);
            rn.setTotalRecords(recordIDs.size());
            rn.setTotalPages(totalPage);
        } 
        else if(id == null)
        {
            //logger.debug("From hyperlink");
            session.setAttribute(m_navigatorName, null);
            
            rn = new RecordNavigator();
            rn.setSearchFieldInfo(m_searchFieldInfo);

            if(multidao != null)
            {
            	recordIDs = (ArrayList)m_service.getIDsWithSearchCriteria(null, loginDTO);
            }
            else
            {
            	recordIDs = (ArrayList)m_service.getIDs(loginDTO);
            }
            
            
            int totalPage = recordIDs.size() / rn.getPageSize();
            if(recordIDs.size() % rn.getPageSize() != 0)
                totalPage++;
            if(recordIDs.size() > 0)
                rn.setCurrentPageNo(1);
            else
                rn.setCurrentPageNo(0);
            rn.setIDs(recordIDs);
            rn.setTotalRecords(recordIDs.size());
            rn.setTotalPages(totalPage);
        }

        int nextCollectionSize;
        if(rn.getTotalRecords() == 0)
            nextCollectionSize = 0;
        else if(rn.getTotalRecords() > 0 && rn.getCurrentPageNo() == rn.getTotalPages() && rn.getTotalRecords() % rn.getPageSize() != 0)
            nextCollectionSize = rn.getTotalRecords() % rn.getPageSize();
        else
            nextCollectionSize = rn.getPageSize();
        int initial = nextCollectionSize != 0 ? (rn.getCurrentPageNo() - 1) * rn.getPageSize() + 1 : 0;
        recordIDs = (ArrayList)rn.getIDs();
        recordIDToSend = new ArrayList();
        for(int i = initial; i < initial + nextCollectionSize; i++)
        {
            //logger.debug("recordIDTOSend ID : " + recordIDs.get(i - 1));
            recordIDToSend.add(recordIDs.get(i - 1));
        }

       // logger.debug("Going to call getDTOs");
        if(nextCollectionSize > 0)
        {
        	if(multidao != null)
            {
        		//records = m_service.getDTOs(recordIDToSend, loginDTO, table_names);
            }
        	else
        	{
        		records = m_service.getDTOs(recordIDToSend, loginDTO);
        	}
        }
        //logger.debug("rn.toString is : " + rn.toString());
        session.setAttribute(m_navigatorName, rn);
        session.setAttribute(m_dtoCollectionName, records);
    }    
}