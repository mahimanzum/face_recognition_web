package pb_notifications;
import java.util.*; 


public class Pb_notificationsMAPS 
{

	public HashMap<String, String> java_allfield_type_map = new HashMap<String, String>();
	public HashMap<String, String> java_anyfield_search_map = new HashMap<String, String>();
	public HashMap<String, String> java_DTO_map = new HashMap<String, String>();
	public HashMap<String, String> java_SQL_map = new HashMap<String, String>();
	
	private static Pb_notificationsMAPS self = null;
	
	private Pb_notificationsMAPS()
	{
		
		java_allfield_type_map.put("is_seen".toLowerCase(), "Boolean");
		java_allfield_type_map.put("is_hidden".toLowerCase(), "Boolean");
		java_allfield_type_map.put("source".toLowerCase(), "Integer");
		java_allfield_type_map.put("destination".toLowerCase(), "Integer");
		java_allfield_type_map.put("from_id".toLowerCase(), "Long");
		java_allfield_type_map.put("to_id".toLowerCase(), "Long");
		java_allfield_type_map.put("text".toLowerCase(), "String");
		java_allfield_type_map.put("url".toLowerCase(), "String");
		java_allfield_type_map.put("entry_date".toLowerCase(), "Long");
		java_allfield_type_map.put("seen_date".toLowerCase(), "Long");
		java_allfield_type_map.put("showing_date".toLowerCase(), "Long");
		java_allfield_type_map.put("send_alarm".toLowerCase(), "Boolean");
		java_allfield_type_map.put("send_sms".toLowerCase(), "Boolean");
		java_allfield_type_map.put("send_mail".toLowerCase(), "Boolean");
		java_allfield_type_map.put("send_push".toLowerCase(), "Boolean");
		java_allfield_type_map.put("mail_sent".toLowerCase(), "Boolean");
		java_allfield_type_map.put("sms_sent".toLowerCase(), "Boolean");
		java_allfield_type_map.put("push_sent".toLowerCase(), "Boolean");

		java_anyfield_search_map.put("pb_notifications.is_seen".toLowerCase(), "Boolean");
		java_anyfield_search_map.put("pb_notifications.is_hidden".toLowerCase(), "Boolean");
		java_anyfield_search_map.put("pb_notifications.source".toLowerCase(), "Integer");
		java_anyfield_search_map.put("pb_notifications.destination".toLowerCase(), "Integer");
		java_anyfield_search_map.put("pb_notifications.from_id".toLowerCase(), "Long");
		java_anyfield_search_map.put("pb_notifications.to_id".toLowerCase(), "Long");
		java_anyfield_search_map.put("pb_notifications.text".toLowerCase(), "String");
		java_anyfield_search_map.put("pb_notifications.url".toLowerCase(), "String");
		java_anyfield_search_map.put("pb_notifications.entry_date".toLowerCase(), "Long");
		java_anyfield_search_map.put("pb_notifications.seen_date".toLowerCase(), "Long");
		java_anyfield_search_map.put("pb_notifications.showing_date".toLowerCase(), "Long");
		java_anyfield_search_map.put("pb_notifications.send_alarm".toLowerCase(), "Boolean");
		java_anyfield_search_map.put("pb_notifications.send_sms".toLowerCase(), "Boolean");
		java_anyfield_search_map.put("pb_notifications.send_mail".toLowerCase(), "Boolean");
		java_anyfield_search_map.put("pb_notifications.send_push".toLowerCase(), "Boolean");
		java_anyfield_search_map.put("pb_notifications.mail_sent".toLowerCase(), "Boolean");
		java_anyfield_search_map.put("pb_notifications.sms_sent".toLowerCase(), "Boolean");
		java_anyfield_search_map.put("pb_notifications.push_sent".toLowerCase(), "Boolean");

		java_DTO_map.put("iD".toLowerCase(), "iD".toLowerCase());
		java_DTO_map.put("isSeen".toLowerCase(), "isSeen".toLowerCase());
		java_DTO_map.put("isHidden".toLowerCase(), "isHidden".toLowerCase());
		java_DTO_map.put("source".toLowerCase(), "source".toLowerCase());
		java_DTO_map.put("destination".toLowerCase(), "destination".toLowerCase());
		java_DTO_map.put("fromId".toLowerCase(), "fromId".toLowerCase());
		java_DTO_map.put("toId".toLowerCase(), "toId".toLowerCase());
		java_DTO_map.put("text".toLowerCase(), "text".toLowerCase());
		java_DTO_map.put("url".toLowerCase(), "url".toLowerCase());
		java_DTO_map.put("entryDate".toLowerCase(), "entryDate".toLowerCase());
		java_DTO_map.put("seenDate".toLowerCase(), "seenDate".toLowerCase());
		java_DTO_map.put("showingDate".toLowerCase(), "showingDate".toLowerCase());
		java_DTO_map.put("sendAlarm".toLowerCase(), "sendAlarm".toLowerCase());
		java_DTO_map.put("sendSms".toLowerCase(), "sendSms".toLowerCase());
		java_DTO_map.put("sendMail".toLowerCase(), "sendMail".toLowerCase());
		java_DTO_map.put("sendPush".toLowerCase(), "sendPush".toLowerCase());
		java_DTO_map.put("mailSent".toLowerCase(), "mailSent".toLowerCase());
		java_DTO_map.put("smsSent".toLowerCase(), "smsSent".toLowerCase());
		java_DTO_map.put("pushSent".toLowerCase(), "pushSent".toLowerCase());
		java_DTO_map.put("isDeleted".toLowerCase(), "isDeleted".toLowerCase());

		java_SQL_map.put("is_seen".toLowerCase(), "isSeen".toLowerCase());
		java_SQL_map.put("is_hidden".toLowerCase(), "isHidden".toLowerCase());
		java_SQL_map.put("source".toLowerCase(), "source".toLowerCase());
		java_SQL_map.put("destination".toLowerCase(), "destination".toLowerCase());
		java_SQL_map.put("from_id".toLowerCase(), "fromId".toLowerCase());
		java_SQL_map.put("to_id".toLowerCase(), "toId".toLowerCase());
		java_SQL_map.put("text".toLowerCase(), "text".toLowerCase());
		java_SQL_map.put("url".toLowerCase(), "url".toLowerCase());
		java_SQL_map.put("entry_date".toLowerCase(), "entryDate".toLowerCase());
		java_SQL_map.put("seen_date".toLowerCase(), "seenDate".toLowerCase());
		java_SQL_map.put("showing_date".toLowerCase(), "showingDate".toLowerCase());
		java_SQL_map.put("send_alarm".toLowerCase(), "sendAlarm".toLowerCase());
		java_SQL_map.put("send_sms".toLowerCase(), "sendSms".toLowerCase());
		java_SQL_map.put("send_mail".toLowerCase(), "sendMail".toLowerCase());
		java_SQL_map.put("send_push".toLowerCase(), "sendPush".toLowerCase());
		java_SQL_map.put("mail_sent".toLowerCase(), "mailSent".toLowerCase());
		java_SQL_map.put("sms_sent".toLowerCase(), "smsSent".toLowerCase());
		java_SQL_map.put("push_sent".toLowerCase(), "pushSent".toLowerCase());
			
	}
	
	public static Pb_notificationsMAPS GetInstance()
	{
		if(self == null)
		{
			self = new Pb_notificationsMAPS ();
		}
		return self;
	}
	

}