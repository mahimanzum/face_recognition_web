package pb_notifications;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Pb_notificationsRepository implements Repository {
	Pb_notificationsDAO pb_notificationsDAO = new Pb_notificationsDAO();
	
	
	static Logger logger = Logger.getLogger(Pb_notificationsRepository.class);
	Map<Long, Pb_notificationsDTO>mapOfPb_notificationsDTOToiD;
	Map<Boolean, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOToisSeen;
	Map<Boolean, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOToisHidden;
	Map<Integer, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOTosource;
	Map<Integer, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOTodestination;
	Map<Long, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOTofromId;
	Map<Long, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOTotoId;
	Map<String, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOTotext;
	Map<String, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOTourl;
	Map<Long, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOToentryDate;
	Map<Long, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOToseenDate;
	Map<Long, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOToshowingDate;
	Map<Boolean, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOTosendAlarm;
	Map<Boolean, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOTosendSms;
	Map<Boolean, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOTosendMail;
	Map<Boolean, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOTosendPush;
	Map<Boolean, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOTomailSent;
	Map<Boolean, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOTosmsSent;
	Map<Boolean, Set<Pb_notificationsDTO> >mapOfPb_notificationsDTOTopushSent;


	static Pb_notificationsRepository instance = null;  
	private Pb_notificationsRepository(){
		mapOfPb_notificationsDTOToiD = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOToisSeen = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOToisHidden = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOTosource = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOTodestination = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOTofromId = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOTotoId = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOTotext = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOTourl = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOToentryDate = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOToseenDate = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOToshowingDate = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOTosendAlarm = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOTosendSms = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOTosendMail = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOTosendPush = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOTomailSent = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOTosmsSent = new ConcurrentHashMap<>();
		mapOfPb_notificationsDTOTopushSent = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Pb_notificationsRepository getInstance(){
		if (instance == null){
			instance = new Pb_notificationsRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Pb_notificationsDTO> pb_notificationsDTOs = pb_notificationsDAO.getAllPb_notifications(reloadAll);
			for(Pb_notificationsDTO pb_notificationsDTO : pb_notificationsDTOs) {
				Pb_notificationsDTO oldPb_notificationsDTO = getPb_notificationsDTOByID(pb_notificationsDTO.iD);
				if( oldPb_notificationsDTO != null ) {
					mapOfPb_notificationsDTOToiD.remove(oldPb_notificationsDTO.iD);
				
					if(mapOfPb_notificationsDTOToisSeen.containsKey(oldPb_notificationsDTO.isSeen)) {
						mapOfPb_notificationsDTOToisSeen.get(oldPb_notificationsDTO.isSeen).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOToisSeen.get(oldPb_notificationsDTO.isSeen).isEmpty()) {
						mapOfPb_notificationsDTOToisSeen.remove(oldPb_notificationsDTO.isSeen);
					}
					
					if(mapOfPb_notificationsDTOToisHidden.containsKey(oldPb_notificationsDTO.isHidden)) {
						mapOfPb_notificationsDTOToisHidden.get(oldPb_notificationsDTO.isHidden).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOToisHidden.get(oldPb_notificationsDTO.isHidden).isEmpty()) {
						mapOfPb_notificationsDTOToisHidden.remove(oldPb_notificationsDTO.isHidden);
					}
					
					if(mapOfPb_notificationsDTOTosource.containsKey(oldPb_notificationsDTO.source)) {
						mapOfPb_notificationsDTOTosource.get(oldPb_notificationsDTO.source).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOTosource.get(oldPb_notificationsDTO.source).isEmpty()) {
						mapOfPb_notificationsDTOTosource.remove(oldPb_notificationsDTO.source);
					}
					
					if(mapOfPb_notificationsDTOTodestination.containsKey(oldPb_notificationsDTO.destination)) {
						mapOfPb_notificationsDTOTodestination.get(oldPb_notificationsDTO.destination).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOTodestination.get(oldPb_notificationsDTO.destination).isEmpty()) {
						mapOfPb_notificationsDTOTodestination.remove(oldPb_notificationsDTO.destination);
					}
					
					if(mapOfPb_notificationsDTOTofromId.containsKey(oldPb_notificationsDTO.fromId)) {
						mapOfPb_notificationsDTOTofromId.get(oldPb_notificationsDTO.fromId).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOTofromId.get(oldPb_notificationsDTO.fromId).isEmpty()) {
						mapOfPb_notificationsDTOTofromId.remove(oldPb_notificationsDTO.fromId);
					}
					
					if(mapOfPb_notificationsDTOTotoId.containsKey(oldPb_notificationsDTO.toId)) {
						mapOfPb_notificationsDTOTotoId.get(oldPb_notificationsDTO.toId).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOTotoId.get(oldPb_notificationsDTO.toId).isEmpty()) {
						mapOfPb_notificationsDTOTotoId.remove(oldPb_notificationsDTO.toId);
					}
					
					if(mapOfPb_notificationsDTOTotext.containsKey(oldPb_notificationsDTO.text)) {
						mapOfPb_notificationsDTOTotext.get(oldPb_notificationsDTO.text).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOTotext.get(oldPb_notificationsDTO.text).isEmpty()) {
						mapOfPb_notificationsDTOTotext.remove(oldPb_notificationsDTO.text);
					}
					
					if(mapOfPb_notificationsDTOTourl.containsKey(oldPb_notificationsDTO.url)) {
						mapOfPb_notificationsDTOTourl.get(oldPb_notificationsDTO.url).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOTourl.get(oldPb_notificationsDTO.url).isEmpty()) {
						mapOfPb_notificationsDTOTourl.remove(oldPb_notificationsDTO.url);
					}
					
					if(mapOfPb_notificationsDTOToentryDate.containsKey(oldPb_notificationsDTO.entryDate)) {
						mapOfPb_notificationsDTOToentryDate.get(oldPb_notificationsDTO.entryDate).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOToentryDate.get(oldPb_notificationsDTO.entryDate).isEmpty()) {
						mapOfPb_notificationsDTOToentryDate.remove(oldPb_notificationsDTO.entryDate);
					}
					
					if(mapOfPb_notificationsDTOToseenDate.containsKey(oldPb_notificationsDTO.seenDate)) {
						mapOfPb_notificationsDTOToseenDate.get(oldPb_notificationsDTO.seenDate).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOToseenDate.get(oldPb_notificationsDTO.seenDate).isEmpty()) {
						mapOfPb_notificationsDTOToseenDate.remove(oldPb_notificationsDTO.seenDate);
					}
					
					if(mapOfPb_notificationsDTOToshowingDate.containsKey(oldPb_notificationsDTO.showingDate)) {
						mapOfPb_notificationsDTOToshowingDate.get(oldPb_notificationsDTO.showingDate).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOToshowingDate.get(oldPb_notificationsDTO.showingDate).isEmpty()) {
						mapOfPb_notificationsDTOToshowingDate.remove(oldPb_notificationsDTO.showingDate);
					}
					
					if(mapOfPb_notificationsDTOTosendAlarm.containsKey(oldPb_notificationsDTO.sendAlarm)) {
						mapOfPb_notificationsDTOTosendAlarm.get(oldPb_notificationsDTO.sendAlarm).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOTosendAlarm.get(oldPb_notificationsDTO.sendAlarm).isEmpty()) {
						mapOfPb_notificationsDTOTosendAlarm.remove(oldPb_notificationsDTO.sendAlarm);
					}
					
					if(mapOfPb_notificationsDTOTosendSms.containsKey(oldPb_notificationsDTO.sendSms)) {
						mapOfPb_notificationsDTOTosendSms.get(oldPb_notificationsDTO.sendSms).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOTosendSms.get(oldPb_notificationsDTO.sendSms).isEmpty()) {
						mapOfPb_notificationsDTOTosendSms.remove(oldPb_notificationsDTO.sendSms);
					}
					
					if(mapOfPb_notificationsDTOTosendMail.containsKey(oldPb_notificationsDTO.sendMail)) {
						mapOfPb_notificationsDTOTosendMail.get(oldPb_notificationsDTO.sendMail).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOTosendMail.get(oldPb_notificationsDTO.sendMail).isEmpty()) {
						mapOfPb_notificationsDTOTosendMail.remove(oldPb_notificationsDTO.sendMail);
					}
					
					if(mapOfPb_notificationsDTOTosendPush.containsKey(oldPb_notificationsDTO.sendPush)) {
						mapOfPb_notificationsDTOTosendPush.get(oldPb_notificationsDTO.sendPush).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOTosendPush.get(oldPb_notificationsDTO.sendPush).isEmpty()) {
						mapOfPb_notificationsDTOTosendPush.remove(oldPb_notificationsDTO.sendPush);
					}
					
					if(mapOfPb_notificationsDTOTomailSent.containsKey(oldPb_notificationsDTO.mailSent)) {
						mapOfPb_notificationsDTOTomailSent.get(oldPb_notificationsDTO.mailSent).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOTomailSent.get(oldPb_notificationsDTO.mailSent).isEmpty()) {
						mapOfPb_notificationsDTOTomailSent.remove(oldPb_notificationsDTO.mailSent);
					}
					
					if(mapOfPb_notificationsDTOTosmsSent.containsKey(oldPb_notificationsDTO.smsSent)) {
						mapOfPb_notificationsDTOTosmsSent.get(oldPb_notificationsDTO.smsSent).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOTosmsSent.get(oldPb_notificationsDTO.smsSent).isEmpty()) {
						mapOfPb_notificationsDTOTosmsSent.remove(oldPb_notificationsDTO.smsSent);
					}
					
					if(mapOfPb_notificationsDTOTopushSent.containsKey(oldPb_notificationsDTO.pushSent)) {
						mapOfPb_notificationsDTOTopushSent.get(oldPb_notificationsDTO.pushSent).remove(oldPb_notificationsDTO);
					}
					if(mapOfPb_notificationsDTOTopushSent.get(oldPb_notificationsDTO.pushSent).isEmpty()) {
						mapOfPb_notificationsDTOTopushSent.remove(oldPb_notificationsDTO.pushSent);
					}
					
					
				}
				if(pb_notificationsDTO.isDeleted == false) 
				{
					
					mapOfPb_notificationsDTOToiD.put(pb_notificationsDTO.iD, pb_notificationsDTO);
				
					if( ! mapOfPb_notificationsDTOToisSeen.containsKey(pb_notificationsDTO.isSeen)) {
						mapOfPb_notificationsDTOToisSeen.put(pb_notificationsDTO.isSeen, new HashSet<>());
					}
					mapOfPb_notificationsDTOToisSeen.get(pb_notificationsDTO.isSeen).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOToisHidden.containsKey(pb_notificationsDTO.isHidden)) {
						mapOfPb_notificationsDTOToisHidden.put(pb_notificationsDTO.isHidden, new HashSet<>());
					}
					mapOfPb_notificationsDTOToisHidden.get(pb_notificationsDTO.isHidden).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOTosource.containsKey(pb_notificationsDTO.source)) {
						mapOfPb_notificationsDTOTosource.put(pb_notificationsDTO.source, new HashSet<>());
					}
					mapOfPb_notificationsDTOTosource.get(pb_notificationsDTO.source).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOTodestination.containsKey(pb_notificationsDTO.destination)) {
						mapOfPb_notificationsDTOTodestination.put(pb_notificationsDTO.destination, new HashSet<>());
					}
					mapOfPb_notificationsDTOTodestination.get(pb_notificationsDTO.destination).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOTofromId.containsKey(pb_notificationsDTO.fromId)) {
						mapOfPb_notificationsDTOTofromId.put(pb_notificationsDTO.fromId, new HashSet<>());
					}
					mapOfPb_notificationsDTOTofromId.get(pb_notificationsDTO.fromId).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOTotoId.containsKey(pb_notificationsDTO.toId)) {
						mapOfPb_notificationsDTOTotoId.put(pb_notificationsDTO.toId, new HashSet<>());
					}
					mapOfPb_notificationsDTOTotoId.get(pb_notificationsDTO.toId).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOTotext.containsKey(pb_notificationsDTO.text)) {
						mapOfPb_notificationsDTOTotext.put(pb_notificationsDTO.text, new HashSet<>());
					}
					mapOfPb_notificationsDTOTotext.get(pb_notificationsDTO.text).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOTourl.containsKey(pb_notificationsDTO.url)) {
						mapOfPb_notificationsDTOTourl.put(pb_notificationsDTO.url, new HashSet<>());
					}
					mapOfPb_notificationsDTOTourl.get(pb_notificationsDTO.url).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOToentryDate.containsKey(pb_notificationsDTO.entryDate)) {
						mapOfPb_notificationsDTOToentryDate.put(pb_notificationsDTO.entryDate, new HashSet<>());
					}
					mapOfPb_notificationsDTOToentryDate.get(pb_notificationsDTO.entryDate).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOToseenDate.containsKey(pb_notificationsDTO.seenDate)) {
						mapOfPb_notificationsDTOToseenDate.put(pb_notificationsDTO.seenDate, new HashSet<>());
					}
					mapOfPb_notificationsDTOToseenDate.get(pb_notificationsDTO.seenDate).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOToshowingDate.containsKey(pb_notificationsDTO.showingDate)) {
						mapOfPb_notificationsDTOToshowingDate.put(pb_notificationsDTO.showingDate, new HashSet<>());
					}
					mapOfPb_notificationsDTOToshowingDate.get(pb_notificationsDTO.showingDate).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOTosendAlarm.containsKey(pb_notificationsDTO.sendAlarm)) {
						mapOfPb_notificationsDTOTosendAlarm.put(pb_notificationsDTO.sendAlarm, new HashSet<>());
					}
					mapOfPb_notificationsDTOTosendAlarm.get(pb_notificationsDTO.sendAlarm).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOTosendSms.containsKey(pb_notificationsDTO.sendSms)) {
						mapOfPb_notificationsDTOTosendSms.put(pb_notificationsDTO.sendSms, new HashSet<>());
					}
					mapOfPb_notificationsDTOTosendSms.get(pb_notificationsDTO.sendSms).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOTosendMail.containsKey(pb_notificationsDTO.sendMail)) {
						mapOfPb_notificationsDTOTosendMail.put(pb_notificationsDTO.sendMail, new HashSet<>());
					}
					mapOfPb_notificationsDTOTosendMail.get(pb_notificationsDTO.sendMail).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOTosendPush.containsKey(pb_notificationsDTO.sendPush)) {
						mapOfPb_notificationsDTOTosendPush.put(pb_notificationsDTO.sendPush, new HashSet<>());
					}
					mapOfPb_notificationsDTOTosendPush.get(pb_notificationsDTO.sendPush).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOTomailSent.containsKey(pb_notificationsDTO.mailSent)) {
						mapOfPb_notificationsDTOTomailSent.put(pb_notificationsDTO.mailSent, new HashSet<>());
					}
					mapOfPb_notificationsDTOTomailSent.get(pb_notificationsDTO.mailSent).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOTosmsSent.containsKey(pb_notificationsDTO.smsSent)) {
						mapOfPb_notificationsDTOTosmsSent.put(pb_notificationsDTO.smsSent, new HashSet<>());
					}
					mapOfPb_notificationsDTOTosmsSent.get(pb_notificationsDTO.smsSent).add(pb_notificationsDTO);
					
					if( ! mapOfPb_notificationsDTOTopushSent.containsKey(pb_notificationsDTO.pushSent)) {
						mapOfPb_notificationsDTOTopushSent.put(pb_notificationsDTO.pushSent, new HashSet<>());
					}
					mapOfPb_notificationsDTOTopushSent.get(pb_notificationsDTO.pushSent).add(pb_notificationsDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Pb_notificationsDTO> getPb_notificationsList() {
		List <Pb_notificationsDTO> pb_notificationss = new ArrayList<Pb_notificationsDTO>(this.mapOfPb_notificationsDTOToiD.values());
		return pb_notificationss;
	}
	
	
	public Pb_notificationsDTO getPb_notificationsDTOByID( long ID){
		return mapOfPb_notificationsDTOToiD.get(ID);
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOByis_seen(boolean is_seen) {
		return new ArrayList<>( mapOfPb_notificationsDTOToisSeen.getOrDefault(is_seen,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOByis_hidden(boolean is_hidden) {
		return new ArrayList<>( mapOfPb_notificationsDTOToisHidden.getOrDefault(is_hidden,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOBysource(int source) {
		return new ArrayList<>( mapOfPb_notificationsDTOTosource.getOrDefault(source,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOBydestination(int destination) {
		return new ArrayList<>( mapOfPb_notificationsDTOTodestination.getOrDefault(destination,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOByfrom_id(long from_id) {
		return new ArrayList<>( mapOfPb_notificationsDTOTofromId.getOrDefault(from_id,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOByto_id(long to_id) {
		return new ArrayList<>( mapOfPb_notificationsDTOTotoId.getOrDefault(to_id,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOBytext(String text) {
		return new ArrayList<>( mapOfPb_notificationsDTOTotext.getOrDefault(text,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOByurl(String url) {
		return new ArrayList<>( mapOfPb_notificationsDTOTourl.getOrDefault(url,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOByentry_date(long entry_date) {
		return new ArrayList<>( mapOfPb_notificationsDTOToentryDate.getOrDefault(entry_date,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOByseen_date(long seen_date) {
		return new ArrayList<>( mapOfPb_notificationsDTOToseenDate.getOrDefault(seen_date,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOByshowing_date(long showing_date) {
		return new ArrayList<>( mapOfPb_notificationsDTOToshowingDate.getOrDefault(showing_date,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOBysend_alarm(boolean send_alarm) {
		return new ArrayList<>( mapOfPb_notificationsDTOTosendAlarm.getOrDefault(send_alarm,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOBysend_sms(boolean send_sms) {
		return new ArrayList<>( mapOfPb_notificationsDTOTosendSms.getOrDefault(send_sms,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOBysend_mail(boolean send_mail) {
		return new ArrayList<>( mapOfPb_notificationsDTOTosendMail.getOrDefault(send_mail,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOBysend_push(boolean send_push) {
		return new ArrayList<>( mapOfPb_notificationsDTOTosendPush.getOrDefault(send_push,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOBymail_sent(boolean mail_sent) {
		return new ArrayList<>( mapOfPb_notificationsDTOTomailSent.getOrDefault(mail_sent,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOBysms_sent(boolean sms_sent) {
		return new ArrayList<>( mapOfPb_notificationsDTOTosmsSent.getOrDefault(sms_sent,new HashSet<>()));
	}
	
	
	public List<Pb_notificationsDTO> getPb_notificationsDTOBypush_sent(boolean push_sent) {
		return new ArrayList<>( mapOfPb_notificationsDTOTopushSent.getOrDefault(push_sent,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "pb_notifications";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


