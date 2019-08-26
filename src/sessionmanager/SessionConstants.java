/*
 * Created on Oct 27, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package sessionmanager;

import language.LC;
import language.LM;

public class SessionConstants {   
    //navigation bar related
    public final static String NAVIGATION_BAR_FIRST = "first";
    public final static String NAVIGATION_BAR_NEXT = "next";
    public final static String NAVIGATION_BAR_PREVIOUS = "previous";
    public final static String NAVIGATION_BAR_LAST = "last";
    public final static String NAVIGATION_BAR_CURRENT = "current";
    //form check related
    public final static String NAVIGATION_LINK = "id";
    public final static String GO_CHECK_FIELD = "go";
    public final static String SEARCH_CHECK_FIELD = "search";
    public final static String HTML_SEARCH_CHECK_FIELD = "htmlsearch";
    //search quiry related
    public final static String ADVANCED_SEARCH = "AdvancedSearch";
    //record navigation initial parameter
    public final static int CRRENT_PAGE_NO = 1;
    public final static int TOTAL_PAGES = 10;
    public final static int PAGE_SIZE = 50000;
    public final static int NUMBER_OF_RECORDS = 100;
    public final static int FIRST_PAGE = 1;
    public static final String RECORDS_PER_PAGE = "RECORDS_PER_PAGE";
    /*
     * Failure Message
     */
    //Error Message
    public final static String FAILURE_MESSAGE = "failuremessage";
	/*
	 * User Login
	 */
    public final static String USER_LOGIN = "user_login";
    
    public final static String PREFERRED_LANGUAGE = "preferrer_language";
    
    /*
     * Role
     */
    public final static String NAV_ROLE = "navrole";
    public final static String VIEW_ROLE = "viewrole";
    public final static String[][] SEARCH_ROLE = {
            {
                ""+LC.ROLE_SEARCH_ROLE_NAME, "roleName"},
            {
                ""+LC.ROLE_SEARCH_DESCRIPTION, "description"}
        };



    public final static String NAV_USER = "navuser";
    public final static String VIEW_USER = "viewuser";
	public final static String SEARCH_USER[][] = {
			{""+LC.USER_SEARCH_USER_ID,"userName"},
			{".//user//userType.jsp","userType"},
			{""+LC.USER_SEARCH_MAIL_ADDRESS,"mailAddress"},
			{""+LC.USER_SEARCH_FULL_NAME,"fullName"},
			{""+LC.USER_SEARCH_PHONE_NO,"phoneNo"}
		};

	public final static String VIEW_LANGUAGE = "viewlanguage";
	public final static String NAV_LANGUAGE = "navlanguage";
	public final static String SEARCH_LANGUAGE[][] = {
			{".//language//menu.jsp", "menuID"},
			{"English Text","languageTextEnglish"},
			{"Bangla Text","languageTextBangla"},
			{"Constant Prefix","languageConstantPrefix"},
			{"Constant","languageConstant"}
		};
	
	public final static String VIEW_LANGUAGE_GROUP = "viewlanguageGroup";
	public final static String NAV_LANGUAGE_GROUP = "navlanguageGroup";
	public final static String SEARCH_LANGUAGE_GROUP[][] = {
			{"Name", "name"},
			{"Url","url"}			
		};




	public final static String NAV_PUPPIES = "navPUPPIES";
	public final static String VIEW_PUPPIES = "viewPUPPIES";
	public static final String[][] SEARCH_PUPPIES = {
		{ "Name" , "Name" },
		{ "Age" , "Age" },
		{ "Address" , "Address" },
		{ "Gender" , "Gender" },
		{ "Color" , "Color" },
		{ "IsWellBehaved" , "IsWellBehaved" },
		{ "IsDeleted" , "isDeleted" }
	};












	public final static String NAV_THEME = "navTHEME";
	public final static String VIEW_THEME = "viewTHEME";
	public static final String[][] SEARCH_THEME = {
		{ ""+LC.THEME_SEARCH_THEMENAME, "theme_name" },
		{ ""+LC.THEME_SEARCH_DIRECTORY, "directory" },
		{ ""+LC.THEME_SEARCH_ISAPPLIED, "isApplied" },
		{ ""+LC.THEME_SEARCH_ANYFIELD , "AnyField" }
	};












	public final static String NAV_SUPER_HERO_TABLE = "navSUPER_HERO_TABLE";
	public final static String VIEW_SUPER_HERO_TABLE = "viewSUPER_HERO_TABLE";
	public static final String[][] SEARCH_SUPER_HERO_TABLE = {
		{ ""+LC.SUPER_HERO_TABLE_SEARCH_FIRSTNAME, "first_name" },
		{ ""+LC.SUPER_HERO_TABLE_SEARCH_LASTNAME, "last_name" },
		{ ""+LC.SUPER_HERO_TABLE_SEARCH_GENDER, "gender" },
		{ ""+LC.SUPER_HERO_TABLE_SEARCH_RELIGION, "religion" },
		{ ""+LC.SUPER_HERO_TABLE_SEARCH_DATEOFBIRTH, "date_of_birth" },
		{ ""+LC.SUPER_HERO_TABLE_SEARCH_OTHERDATABASE, "other_database" },
		{ ""+LC.SUPER_HERO_TABLE_SEARCH_EDUCATION, "education" },
		{ ""+LC.SUPER_HERO_TABLE_SEARCH_PASSINGYEAR, "passing_year" },
		{ ""+LC.SUPER_HERO_TABLE_SEARCH_ISSTRONG, "is_strong" },
		{ ""+LC.SUPER_HERO_TABLE_SEARCH_EYECOLOR, "eye_color" },
		{ ""+LC.SUPER_HERO_TABLE_SEARCH_HASARADIO, "has_a_radio" },
		{ ""+LC.SUPER_HERO_TABLE_SEARCH_PRECISEWEIGHT, "precise_weight" },
		{ ""+LC.SUPER_HERO_TABLE_SEARCH_MAILRICHTEXT, "mail_richtext" },
		{ ""+LC.SUPER_HERO_TABLE_SEARCH_ANYFIELD , "AnyField" }
	};






	public final static String NAV_TESTRAZIN = "navTESTRAZIN";
	public final static String VIEW_TESTRAZIN = "viewTESTRAZIN";
	public static final String[][] SEARCH_TESTRAZIN = {
		{ ""+LC.TESTRAZIN_SEARCH_FIRSTNAME, "first_name" },
		{ ""+LC.TESTRAZIN_SEARCH_LASTNAME, "last_name" },
		{ ""+LC.TESTRAZIN_SEARCH_GENDER, "gender" },
		{ ""+LC.TESTRAZIN_SEARCH_RELIGION, "religion" },
		{ ""+LC.TESTRAZIN_SEARCH_DATEOFBIRTH, "date_of_birth" },
		{ ""+LC.TESTRAZIN_SEARCH_ANYFIELD , "AnyField" }
	};






	public final static String NAV_CITIZEN = "navCITIZEN";
	public final static String VIEW_CITIZEN = "viewCITIZEN";
	public static final String[][] SEARCH_CITIZEN = {
		{ ""+LC.CITIZEN_SEARCH_NID, "nid" },
		{ ""+LC.CITIZEN_SEARCH_NAME, "name" },
		{ ""+LC.CITIZEN_SEARCH_PHONENUMBER, "phone_number" },
		{ ""+LC.CITIZEN_SEARCH_MAILID, "mail_id" },
		{ ""+LC.CITIZEN_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_VEHICLE_TYPE = "navVEHICLE_TYPE";
	public final static String VIEW_VEHICLE_TYPE = "viewVEHICLE_TYPE";
	public static final String[][] SEARCH_VEHICLE_TYPE = {
		{ ""+LC.VEHICLE_TYPE_SEARCH_NAMEBN, "name_bn" },
		{ ""+LC.VEHICLE_TYPE_SEARCH_NAMEEN, "name_en" },
		{ ""+LC.VEHICLE_TYPE_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_ENGINE_TYPE = "navENGINE_TYPE";
	public final static String VIEW_ENGINE_TYPE = "viewENGINE_TYPE";
	public static final String[][] SEARCH_ENGINE_TYPE = {
		{ ""+LC.ENGINE_TYPE_SEARCH_NAMEBN, "name_bn" },
		{ ""+LC.ENGINE_TYPE_SEARCH_NAMEEN, "name_en" },
		{ ""+LC.ENGINE_TYPE_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_REPORT = "navREPORT";
	public final static String VIEW_REPORT = "viewREPORT";
	public static final String[][] SEARCH_REPORT = {
		{ ""+LC.REPORT_SEARCH_NAME, "name" },
		{ ""+LC.REPORT_SEARCH_NID, "nid" },
		{ ""+LC.REPORT_SEARCH_PHONENUMBER, "phone_number" },
		{ ""+LC.REPORT_SEARCH_MAILID, "mail_id" },
		{ ""+LC.REPORT_SEARCH_TYPEID, "type_id" },
		{ ""+LC.REPORT_SEARCH_MODELNAME, "model_name" },
		{ ""+LC.REPORT_SEARCH_COLOR, "color" },
		{ ""+LC.REPORT_SEARCH_ENGINENUMBER, "engine_number" },
		{ ""+LC.REPORT_SEARCH_ENGINETYPE, "engine_type" },
		{ ""+LC.REPORT_SEARCH_CHASSISNUMBER, "chassis_number" },
		{ ""+LC.REPORT_SEARCH_ENGINECC, "engine_cc" },
		{ ""+LC.REPORT_SEARCH_REGISTRATIONNUMBER, "registration_number" },
		{ ""+LC.REPORT_SEARCH_MANUFACTURER, "manufacturer" },
		{ ""+LC.REPORT_SEARCH_MANUFACTURINGYEAR, "manufacturing_year" },
		{ ""+LC.REPORT_SEARCH_MOREDETAILS, "more_details" },
		{ ""+LC.REPORT_SEARCH_REPORTINGDATE, "reporting_date" },
		{ ""+LC.REPORT_SEARCH_REPORTERID, "reporter_id" },
		{ ""+LC.REPORT_SEARCH_VEHICLEID, "vehicle_id" },
		{ ""+LC.REPORT_SEARCH_LOSTDATE, "lost_date" },
		{ ""+LC.REPORT_SEARCH_FOUNDDATE, "found_date" },
		{ ""+LC.REPORT_SEARCH_BLOG, "blog" },
		{ ""+LC.REPORT_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_VEHICLE = "navVEHICLE";
	public final static String VIEW_VEHICLE = "viewVEHICLE";
	public static final String[][] SEARCH_VEHICLE = {
		{ ""+LC.VEHICLE_SEARCH_TYPEID, "type_id" },
		{ ""+LC.VEHICLE_SEARCH_MODELNAME, "model_name" },
		{ ""+LC.VEHICLE_SEARCH_COLOR, "color" },
		{ ""+LC.VEHICLE_SEARCH_ENGINENUMBER, "engine_number" },
		{ ""+LC.VEHICLE_SEARCH_ENGINETYPE, "engine_type" },
		{ ""+LC.VEHICLE_SEARCH_CHASSISNUMBER, "chassis_number" },
		{ ""+LC.VEHICLE_SEARCH_ENGINECC, "engine_cc" },
		{ ""+LC.VEHICLE_SEARCH_REGISTRATIONNUMBER, "registration_number" },
		{ ""+LC.VEHICLE_SEARCH_MANUFACTURER, "manufacturer" },
		{ ""+LC.VEHICLE_SEARCH_MANUFACTURINGYEAR, "manufacturing_year" },
		{ ""+LC.VEHICLE_SEARCH_MOREDETAILS, "more_details" },
		{ ""+LC.VEHICLE_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_BREED = "navBREED";
	public final static String VIEW_BREED = "viewBREED";
	public static final String[][] SEARCH_BREED = {
		{ ""+LC.BREED_SEARCH_NAMEEN, "name_en" },
		{ ""+LC.BREED_SEARCH_NAMEBN, "name_bn" },
		{ ""+LC.BREED_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_CENTRE = "navCENTRE";
	public final static String VIEW_CENTRE = "viewCENTRE";
	public static final String[][] SEARCH_CENTRE = {
		{ ""+LC.CENTRE_SEARCH_NAMEEN, "name_en" },
		{ ""+LC.CENTRE_SEARCH_NAMEBN, "name_bn" },
		{ ""+LC.CENTRE_SEARCH_ADDRESS, "address" },
		{ ""+LC.CENTRE_SEARCH_CONTACTPERSON, "contact_person" },
		{ ""+LC.CENTRE_SEARCH_PHONENUMBER, "phone_number" },
		{ ""+LC.CENTRE_SEARCH_EMAIL, "email" },
		{ ""+LC.CENTRE_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_STATUS = "navSTATUS";
	public final static String VIEW_STATUS = "viewSTATUS";
	public static final String[][] SEARCH_STATUS = {
		{ ""+LC.STATUS_SEARCH_NAMEEN, "name_en" },
		{ ""+LC.STATUS_SEARCH_NAMEBN, "name_bn" },
		{ ""+LC.STATUS_SEARCH_ANYFIELD , "AnyField" }
	};







	public final static String NAV_PERMISSIBLE_BULLS_IN_CENTRE = "navPERMISSIBLE_BULLS_IN_CENTRE";
	public final static String VIEW_PERMISSIBLE_BULLS_IN_CENTRE = "viewPERMISSIBLE_BULLS_IN_CENTRE";
	public static final String[][] SEARCH_PERMISSIBLE_BULLS_IN_CENTRE = {
		{ ""+LC.PERMISSIBLE_BULLS_IN_CENTRE_SEARCH_BULLTYPE, "bull_type" },
		{ ""+LC.PERMISSIBLE_BULLS_IN_CENTRE_SEARCH_CENTRETYPE, "centre_type" },
		{ ""+LC.PERMISSIBLE_BULLS_IN_CENTRE_SEARCH_DATEOFENTRY, "date_of_entry" },
		{ ""+LC.PERMISSIBLE_BULLS_IN_CENTRE_SEARCH_DATEOFEXPIRATION, "date_of_expiration" },
		{ ""+LC.PERMISSIBLE_BULLS_IN_CENTRE_SEARCH_DESCRIPTION, "description" },
		{ ""+LC.PERMISSIBLE_BULLS_IN_CENTRE_SEARCH_ANYFIELD , "AnyField" }
	};






	public final static String NAV_SEMEN_REQUISITION = "navSEMEN_REQUISITION";
	public final static String VIEW_SEMEN_REQUISITION = "viewSEMEN_REQUISITION";
	public static final String[][] SEARCH_SEMEN_REQUISITION = {
		{ ""+LC.SEMEN_REQUISITION_SEARCH_GROUPID, "group_id" },
		{ ""+LC.SEMEN_REQUISITION_SEARCH_CENTRETYPE, "centre_type" },
		{ ""+LC.SEMEN_REQUISITION_SEARCH_BREEDTYPE, "breed_type" },
		{ ""+LC.SEMEN_REQUISITION_SEARCH_NOOFDOSE, "no_of_dose" },
		{ ""+LC.SEMEN_REQUISITION_SEARCH_REQUISITIONDATE, "requisition_date" },
		{ ""+LC.SEMEN_REQUISITION_SEARCH_ISDELIVERED, "isDelivered" },
		{ ""+LC.SEMEN_REQUISITION_SEARCH_DESCRIPTION, "description" },
		{ ""+LC.SEMEN_REQUISITION_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_SEMEN_DISTRIBUTION = "navSEMEN_DISTRIBUTION";
	public final static String VIEW_SEMEN_DISTRIBUTION = "viewSEMEN_DISTRIBUTION";
	public static final String[][] SEARCH_SEMEN_DISTRIBUTION = {
		{ ""+LC.SEMEN_DISTRIBUTION_SEARCH_BULLTYPE, "bull_type" },
		{ ""+LC.SEMEN_DISTRIBUTION_SEARCH_NOOFDOSE, "no_of_dose" },
		{ ""+LC.SEMEN_DISTRIBUTION_SEARCH_REQUISITIONID, "requisition_id" },
		{ ""+LC.SEMEN_DISTRIBUTION_SEARCH_GROUPID, "group_id" },
		{ ""+LC.SEMEN_DISTRIBUTION_SEARCH_TRANSACTIONDATE, "transaction_date" },
		{ ""+LC.SEMEN_DISTRIBUTION_SEARCH_DESCRIPTION, "description" },
		{ ""+LC.SEMEN_DISTRIBUTION_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_STUDENT = "navSTUDENT";
	public final static String VIEW_STUDENT = "viewSTUDENT";
	public static final String[][] SEARCH_STUDENT = {
		{ ""+LC.STUDENT_SEARCH_FIRSTNAME, "first_name" },
		{ ""+LC.STUDENT_SEARCH_LASTNAME, "last_name" },
		{ ""+LC.STUDENT_SEARCH_EMAIL, "email" },
		{ ""+LC.STUDENT_SEARCH_PHONE, "phone" },
		{ ""+LC.STUDENT_SEARCH_PRESENTADDRESS, "present_address" },
		{ ""+LC.STUDENT_SEARCH_PERMANENTADDRESS, "permanent_address" },
		{ ""+LC.STUDENT_SEARCH_EDUCATION, "education" },
		{ ""+LC.STUDENT_SEARCH_EDUCATIONYEAR, "education_Year" },
		{ ""+LC.STUDENT_SEARCH_ROLLNUMBER, "roll_Number" },
		{ ""+LC.STUDENT_SEARCH_RELIGION, "religion" },
		{ ""+LC.STUDENT_SEARCH_GENDER, "gender" },
		{ ""+LC.STUDENT_SEARCH_DISABILITY, "disability" },
		{ ""+LC.STUDENT_SEARCH_PARENTALSTATUS, "parental_Status" },
		{ ""+LC.STUDENT_SEARCH_DATEOFBIRTH, "date_of_birth" },
		{ ""+LC.STUDENT_SEARCH_REFERENCE, "reference" },
		{ ""+LC.STUDENT_SEARCH_NID, "nID" },
		{ ""+LC.STUDENT_SEARCH_IMAGE, "image" },
		{ ""+LC.STUDENT_SEARCH_VERIFIEDBY, "verified_by" },
		{ ""+LC.STUDENT_SEARCH_ANYFIELD , "AnyField" }
	};






	public final static String NAV_DONOR = "navDONOR";
	public final static String VIEW_DONOR = "viewDONOR";
	public static final String[][] SEARCH_DONOR = {
		{ ""+LC.DONOR_SEARCH_FIRSTNAME, "first_name" },
		{ ""+LC.DONOR_SEARCH_LASTNAME, "last_name" },
		{ ""+LC.DONOR_SEARCH_EMAIL, "email" },
		{ ""+LC.DONOR_SEARCH_PHONE, "phone" },
		{ ""+LC.DONOR_SEARCH_ADDRESS, "address" },
		{ ""+LC.DONOR_SEARCH_DESCRIPTION, "description" },
		{ ""+LC.DONOR_SEARCH_LINK, "link" },
		{ ""+LC.DONOR_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_VOLUNTEER = "navVOLUNTEER";
	public final static String VIEW_VOLUNTEER = "viewVOLUNTEER";
	public static final String[][] SEARCH_VOLUNTEER = {
		{ ""+LC.VOLUNTEER_SEARCH_FIRSTNAME, "first_name" },
		{ ""+LC.VOLUNTEER_SEARCH_LASTNAME, "last_name" },
		{ ""+LC.VOLUNTEER_SEARCH_EMAIL, "email" },
		{ ""+LC.VOLUNTEER_SEARCH_PHONE, "phone" },
		{ ""+LC.VOLUNTEER_SEARCH_PRESENTADDRESS, "present_Address" },
		{ ""+LC.VOLUNTEER_SEARCH_PERMANENTADDRESS, "permanent_Address" },
		{ ""+LC.VOLUNTEER_SEARCH_NID, "nID" },
		{ ""+LC.VOLUNTEER_SEARCH_NIDFRONTIMAGE, "nid_front_image" },
		{ ""+LC.VOLUNTEER_SEARCH_NIDBACKIMAGE, "nid_back_image" },
		{ ""+LC.VOLUNTEER_SEARCH_ADDITIONALIMAGE, "additional_image" },
		{ ""+LC.VOLUNTEER_SEARCH_LINK, "link" },
		{ ""+LC.VOLUNTEER_SEARCH_ANYFIELD , "AnyField" }
	};








	public final static String NAV_COLOR = "navCOLOR";
	public final static String VIEW_COLOR = "viewCOLOR";
	public static final String[][] SEARCH_COLOR = {
		{ ""+LC.COLOR_SEARCH_NAMEEN, "name_en" },
		{ ""+LC.COLOR_SEARCH_NAMEBN, "name_bn" },
		{ ""+LC.COLOR_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_SEMEN_COLLECTION = "navSEMEN_COLLECTION";
	public final static String VIEW_SEMEN_COLLECTION = "viewSEMEN_COLLECTION";
	public static final String[][] SEARCH_SEMEN_COLLECTION = {
		{ ""+LC.SEMEN_COLLECTION_SEARCH_BULLTYPE, "bull_type" },
		{ ""+LC.SEMEN_COLLECTION_SEARCH_NOOFDOSE, "no_of_dose" },
		{ ""+LC.SEMEN_COLLECTION_SEARCH_VOLUME, "volume" },
		{ ""+LC.SEMEN_COLLECTION_SEARCH_DENSITY, "density" },
		{ ""+LC.SEMEN_COLLECTION_SEARCH_PROGRESSIVEMORTALITY, "progressive_mortality" },
		{ ""+LC.SEMEN_COLLECTION_SEARCH_COLORTYPE, "color_type" },
		{ ""+LC.SEMEN_COLLECTION_SEARCH_TRANSACTIONDATE, "transaction_date" },
		{ ""+LC.SEMEN_COLLECTION_SEARCH_DESCRIPTION, "description" },
		{ ""+LC.SEMEN_COLLECTION_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_PROGENY_RECORD = "navPROGENY_RECORD";
	public final static String VIEW_PROGENY_RECORD = "viewPROGENY_RECORD";
	public static final String[][] SEARCH_PROGENY_RECORD = {
		{ ""+LC.PROGENY_RECORD_SEARCH_CENTRETYPE, "centre_type" },
		{ ""+LC.PROGENY_RECORD_SEARCH_BULLTYPE, "bull_type" },
		{ ""+LC.PROGENY_RECORD_SEARCH_NUMBEROFMALECALFS, "number_of_male_calfs" },
		{ ""+LC.PROGENY_RECORD_SEARCH_NUMBEROFFEMALECALFS, "number_of_female_calfs" },
		{ ""+LC.PROGENY_RECORD_SEARCH_DATEOFENTRY, "date_of_entry" },
		{ ""+LC.PROGENY_RECORD_SEARCH_DESCRIPTION, "description" },
		{ ""+LC.PROGENY_RECORD_SEARCH_ANYFIELD , "AnyField" }
	};








	public final static String NAV_STICK = "navSTICK";
	public final static String VIEW_STICK = "viewSTICK";
	public static final String[][] SEARCH_STICK = {
		{ ""+LC.STICK_SEARCH_NAMEEN, "name_en" },
		{ ""+LC.STICK_SEARCH_NAMEBN, "name_bn" },
		{ ""+LC.STICK_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_ARTIFICIAL_INSEMENATION_RECORD = "navARTIFICIAL_INSEMENATION_RECORD";
	public final static String VIEW_ARTIFICIAL_INSEMENATION_RECORD = "viewARTIFICIAL_INSEMENATION_RECORD";
	public static final String[][] SEARCH_ARTIFICIAL_INSEMENATION_RECORD = {
		{ ""+LC.ARTIFICIAL_INSEMENATION_RECORD_SEARCH_CENTRETYPE, "centre_type" },
		{ ""+LC.ARTIFICIAL_INSEMENATION_RECORD_SEARCH_BULLTYPE, "bull_type" },
		{ ""+LC.ARTIFICIAL_INSEMENATION_RECORD_SEARCH_NOOFAI, "no_of_AI" },
		{ ""+LC.ARTIFICIAL_INSEMENATION_RECORD_SEARCH_ENTRYDATE, "entry_date" },
		{ ""+LC.ARTIFICIAL_INSEMENATION_RECORD_SEARCH_DESCRIPTION, "description" },
		{ ""+LC.ARTIFICIAL_INSEMENATION_RECORD_SEARCH_ANYFIELD , "AnyField" }
	};








	public final static String NAV_BULL = "navBULL";
	public final static String VIEW_BULL = "viewBULL";
	public static final String[][] SEARCH_BULL = {
		{ ""+LC.BULL_SEARCH_NAMEEN, "name_en" },
		{ ""+LC.BULL_SEARCH_NAMEBN, "name_bn" },
		{ ""+LC.BULL_SEARCH_DATEOFBIRTH, "date_of_birth" },
		{ ""+LC.BULL_SEARCH_DAM, "dam" },
		{ ""+LC.BULL_SEARCH_SIRE, "sire" },
		{ ""+LC.BULL_SEARCH_MATERNALGRANDDAM, "maternal_grand_dam" },
		{ ""+LC.BULL_SEARCH_MATERNALGRANDSIRE, "maternal_grand_sire" },
		{ ""+LC.BULL_SEARCH_PATERNALGRANDDAM, "paternal_grand_dam" },
		{ ""+LC.BULL_SEARCH_PATERNALGRANDSIRE, "paternal_grand_sire" },
		{ ""+LC.BULL_SEARCH_MILKYIELDOFDAM, "milk_yield_of_dam" },
		{ ""+LC.BULL_SEARCH_MILKYIELDOFSIRESDAM, "milk_yield_of_sires_dam" },
		{ ""+LC.BULL_SEARCH_PROGENYMILKYIELD, "progeny_milk_yield" },
		{ ""+LC.BULL_SEARCH_DESCRIPTION, "description" },
		{ ""+LC.BULL_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_SOURCE = "navSOURCE";
	public final static String VIEW_SOURCE = "viewSOURCE";
	public static final String[][] SEARCH_SOURCE = {
		{ ""+LC.SOURCE_SEARCH_NAMEEN, "name_en" },
		{ ""+LC.SOURCE_SEARCH_NAMEBN, "name_bn" },
		{ ""+LC.SOURCE_SEARCH_ANYFIELD , "AnyField" }
	};














	public final static String NAV_GRS_TOP_LAYER = "navGRS_TOP_LAYER";
	public final static String VIEW_GRS_TOP_LAYER = "viewGRS_TOP_LAYER";
	public static final String[][] SEARCH_GRS_TOP_LAYER = {
		{ ""+LC.GRS_TOP_LAYER_SEARCH_NAMEEN, "name_en" },
		{ ""+LC.GRS_TOP_LAYER_SEARCH_NAMEBN, "name_bn" },
		{ ""+LC.GRS_TOP_LAYER_SEARCH_LAYERNUMBER, "layer_number" },
		{ ""+LC.GRS_TOP_LAYER_SEARCH_DESCRIPTION, "description" },
		{ ""+LC.GRS_TOP_LAYER_SEARCH_ANYFIELD , "AnyField" }
	};







	public final static String NAV_APPROVAL_STATUS = "navAPPROVAL_STATUS";
	public final static String VIEW_APPROVAL_STATUS = "viewAPPROVAL_STATUS";
	public static final String[][] SEARCH_APPROVAL_STATUS = {
		{ ""+LC.APPROVAL_STATUS_SEARCH_NAMEEN, "name_en" },
		{ ""+LC.APPROVAL_STATUS_SEARCH_NAMEBN, "name_bn" },
		{ ""+LC.APPROVAL_STATUS_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_BULL_TRANSFER = "navBULL_TRANSFER";
	public final static String VIEW_BULL_TRANSFER = "viewBULL_TRANSFER";
	public static final String[][] SEARCH_BULL_TRANSFER = {
		{ ""+LC.BULL_TRANSFER_SEARCH_DATEOFTRANSFER, "date_of_transfer" },
		{ ""+LC.BULL_TRANSFER_SEARCH_BULLTYPE, "bull_type" },
		{ ""+LC.BULL_TRANSFER_SEARCH_FROMCENTRETYPE, "from_centre_type" },
		{ ""+LC.BULL_TRANSFER_SEARCH_TOCENTRETYPE, "to_centre_type" },
		{ ""+LC.BULL_TRANSFER_SEARCH_ENTRYDATE, "entry_date" },
		{ ""+LC.BULL_TRANSFER_SEARCH_EXITDATE, "exit_date" },
		{ ""+LC.BULL_TRANSFER_SEARCH_DESCRIPTION, "description" },
		{ ""+LC.BULL_TRANSFER_SEARCH_APPROVALSTATUSTYPE, "approval_status_type" },
		{ ""+LC.BULL_TRANSFER_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_CANDIDATE_BULL_PRODUCTION = "navCANDIDATE_BULL_PRODUCTION";
	public final static String VIEW_CANDIDATE_BULL_PRODUCTION = "viewCANDIDATE_BULL_PRODUCTION";
	public static final String[][] SEARCH_CANDIDATE_BULL_PRODUCTION = {
		{ ""+LC.CANDIDATE_BULL_PRODUCTION_SEARCH_PRODUCTIONDATE, "production_date" },
		{ ""+LC.CANDIDATE_BULL_PRODUCTION_SEARCH_NUMBEROFCANDIDATEBULLS, "number_of_candidate_bulls" },
		{ ""+LC.CANDIDATE_BULL_PRODUCTION_SEARCH_SOURCETYPE, "source_type" },
		{ ""+LC.CANDIDATE_BULL_PRODUCTION_SEARCH_ANYFIELD , "AnyField" }
	};







	public final static String NAV_APA_TARGET = "navAPA_TARGET";
	public final static String VIEW_APA_TARGET = "viewAPA_TARGET";
	public static final String[][] SEARCH_APA_TARGET = {
		{ ""+LC.APA_TARGET_SEARCH_SEMENCOLLECTION, "semen_collection" },
		{ ""+LC.APA_TARGET_SEARCH_ARTIFICIALINSEMENATION, "artificial_insemenation" },
		{ ""+LC.APA_TARGET_SEARCH_PROGENY, "progeny" },
		{ ""+LC.APA_TARGET_SEARCH_CANDIDATEBULLPRODUCTION, "candidate_bull_production" },
		{ ""+LC.APA_TARGET_SEARCH_DESCRIPTION, "description" },
		{ ""+LC.APA_TARGET_SEARCH_ENTRYDATE, "entry_date" },
		{ ""+LC.APA_TARGET_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_LIQUID_SEMEN = "navLIQUID_SEMEN";
	public final static String VIEW_LIQUID_SEMEN = "viewLIQUID_SEMEN";
	public static final String[][] SEARCH_LIQUID_SEMEN = {
		{ ""+LC.LIQUID_SEMEN_SEARCH_CENTRETYPE, "centre_type" },
		{ ""+LC.LIQUID_SEMEN_SEARCH_BULLTYPE, "bull_type" },
		{ ""+LC.LIQUID_SEMEN_SEARCH_NOOFDOSE, "no_of_dose" },
		{ ""+LC.LIQUID_SEMEN_SEARCH_VOLUME, "volume" },
		{ ""+LC.LIQUID_SEMEN_SEARCH_DENSITY, "density" },
		{ ""+LC.LIQUID_SEMEN_SEARCH_PROGRESSIVEMOTILITY, "progressive_motility" },
		{ ""+LC.LIQUID_SEMEN_SEARCH_COLORTYPE, "color_type" },
		{ ""+LC.LIQUID_SEMEN_SEARCH_COLLECTIONDISTRIBUTIONDATE, "collection_distribution_date" },
		{ ""+LC.LIQUID_SEMEN_SEARCH_DESCRIPTION, "description" },
		{ ""+LC.LIQUID_SEMEN_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_IMPORTED_SEMEN = "navIMPORTED_SEMEN";
	public final static String VIEW_IMPORTED_SEMEN = "viewIMPORTED_SEMEN";
	public static final String[][] SEARCH_IMPORTED_SEMEN = {
		{ ""+LC.IMPORTED_SEMEN_SEARCH_BULLTAG, "bull_tag" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_DATEOFBIRTH, "date_of_birth" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_DAM, "dam" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_SIRE, "sire" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_MATERNALGRANDDAM, "maternal_grand_dam" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_MATERNALGRANDSIRE, "maternal_grand_sire" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_PATERNALGRANDDAM, "paternal_grand_dam" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_PATERNALGRANDSIRE, "paternal_grand_sire" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_MILKYIELDOFDAM, "milk_yield_of_dam" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_MILKYIELDOFSIRESDAM, "milk_yield_of_sires_dam" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_PROGENYMILKYIELD, "progeny_milk_yield" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_BREED, "breed" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_DATEOFENTRY, "date_of_entry" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_RECEIVEDAMOUNT, "received_amount" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_DISTRIBUTEDAMOUNT, "distributed_amount" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_TOWHOMDISTRIBUTED, "to_whom_distributed" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_DESCRIPTION, "description" },
		{ ""+LC.IMPORTED_SEMEN_SEARCH_ANYFIELD , "AnyField" }
	};





	public final static String NAV_PB_NOTIFICATIONS = "navPB_NOTIFICATIONS";
	public final static String VIEW_PB_NOTIFICATIONS = "viewPB_NOTIFICATIONS";
	public static final String[][] SEARCH_PB_NOTIFICATIONS = {
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_ISSEEN, "is_seen" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_ISHIDDEN, "is_hidden" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_SOURCE, "source" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_DESTINATION, "destination" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_FROMID, "from_id" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_TOID, "to_id" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_TEXT, "text" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_URL, "url" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_ENTRYDATE, "entry_date" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_SEENDATE, "seen_date" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_SHOWINGDATE, "showing_date" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_SENDALARM, "send_alarm" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_SENDSMS, "send_sms" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_SENDMAIL, "send_mail" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_SENDPUSH, "send_push" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_MAILSENT, "mail_sent" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_SMSSENT, "sms_sent" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_PUSHSENT, "push_sent" },
		{ ""+LC.PB_NOTIFICATIONS_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_BULL_BREED_CENTRE = "navBULL_BREED_CENTRE";
	public final static String VIEW_BULL_BREED_CENTRE = "viewBULL_BREED_CENTRE";
	public static final String[][] SEARCH_BULL_BREED_CENTRE = {
		{ ""+LC.BULL_BREED_CENTRE_SEARCH_BULLTYPE, "bull_type" },
		{ ""+LC.BULL_BREED_CENTRE_SEARCH_BREEDTYPE, "breed_type" },
		{ ""+LC.BULL_BREED_CENTRE_SEARCH_CENTRETYPE, "centre_type" },
		{ ""+LC.BULL_BREED_CENTRE_SEARCH_GRSOFFICE, "grs_office" },
		{ ""+LC.BULL_BREED_CENTRE_SEARCH_GRSOFFICER, "grs_officer" },
		{ ""+LC.BULL_BREED_CENTRE_SEARCH_INFOFILE, "info_file" },
		{ ""+LC.BULL_BREED_CENTRE_SEARCH_BULLIMAGE, "bull_image" },
		{ ""+LC.BULL_BREED_CENTRE_SEARCH_DESCRIPTION, "description" },
		{ ""+LC.BULL_BREED_CENTRE_SEARCH_ANYFIELD , "AnyField" }
	};




	public final static String NAV_VALIDATED_WORD = "navVALIDATED_WORD";
	public final static String VIEW_VALIDATED_WORD = "viewVALIDATED_WORD";
	public static final String[][] SEARCH_VALIDATED_WORD = {
		{ ""+LC.VALIDATED_WORD_SEARCH_VALIDATED, "validated" },
		{ ""+LC.VALIDATED_WORD_SEARCH_ANYFIELD , "AnyField" }
	};











	public final static String NAV_FACIAL_RECOGNIZATION = "navFACIAL_RECOGNIZATION";
	public final static String VIEW_FACIAL_RECOGNIZATION = "viewFACIAL_RECOGNIZATION";
	public static final String[][] SEARCH_FACIAL_RECOGNIZATION = {
		{ ""+LC.FACIAL_RECOGNIZATION_SEARCH_NAME, "name" },
		{ ""+LC.FACIAL_RECOGNIZATION_SEARCH_ADDRESS, "address" },
		{ ""+LC.FACIAL_RECOGNIZATION_SEARCH_PHONE, "phone" },
		{ ""+LC.FACIAL_RECOGNIZATION_SEARCH_EMAIL, "email" },
		{ ""+LC.FACIAL_RECOGNIZATION_SEARCH_IMAGE, "image" },
		{ ""+LC.FACIAL_RECOGNIZATION_SEARCH_ANYFIELD , "AnyField" }
	};

}
