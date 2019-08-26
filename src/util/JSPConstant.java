package util;
/**
 * @author Kayesh Parvez
 *
 */
public class JSPConstant {
	public static final String USER_SERVLET = "UserServlet";
	public static final String ERROR_GLOBAL = "/common/error_page.jsp";
	public static final String USER_EDIT = "users/userEdit.jsp";
	public static final String USER_SEARCH_SERVLET = USER_SERVLET + "?" + ActionTypeConstant.ACTION_TYPE + "=" + ActionTypeConstant.USER_SEARCH;
	public static final String USER_SEARCH = "users/userSearch.jsp";
	
	public static final String ROLE_SERVLET = "RoleServlet";
	public static final String ROLE_EDIT = "roles/roleEdit.jsp";
	public static final String ROLE_GET_EDIT_PAGE_SERVLET = ROLE_SERVLET + "?" + ActionTypeConstant.ACTION_TYPE + "=" + ActionTypeConstant.ROLE_GET_EDIT_PAGE;
	public static final String ROLE_SEARCH_SERVLET = ROLE_SERVLET + "?" + ActionTypeConstant.ACTION_TYPE + "=" + ActionTypeConstant.ROLE_SEARCH;
	public static final String ROLE_SEARCH = "roles/roleSearch.jsp";
	
	public static final String LANGUAGE_SERVLET = "LanguageServlet";
	public static final String LANGUAGE_SEARCH = "language/languageSearch.jsp";
	public static final String LANGUAGE_SEARCH_SERVLET = LANGUAGE_SERVLET + "?" + ActionTypeConstant.ACTION_TYPE + "=" + ActionTypeConstant.LANGUAGE_SEARCH;
	
	public static final String IMAGE_SETTING_PAGE = "image/imageSetting.jsp";
	public static final String PERFORMANCE_LOG_REPORT = "/report/performance_log/newPerformanceLogReport.jsp";
	
	public static final String REPORT = "/report/report.jsp";
	
	public static final String PERFORMANCE_LOG_SUMMARY_REPORT = "/report/performance_log/performanceLogSummary.jsp";
	
	public static final String SUPER_HERO_TABLE_REPORT = "/super_hero_table_report/super_hero_table_report.jsp";
	public static final String SUPER_HERO_TABLE_REPORT_SUMMARY = "/super_hero_table_report/super_hero_table_report_Summary.jsp";
}
