package centre;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class CentreRepository implements Repository {
	CentreDAO centreDAO = new CentreDAO();
	
	
	static Logger logger = Logger.getLogger(CentreRepository.class);
	Map<Long, CentreDTO>mapOfCentreDTOToiD;
	Map<String, Set<CentreDTO> >mapOfCentreDTOTonameEn;
	Map<String, Set<CentreDTO> >mapOfCentreDTOTonameBn;
	Map<String, Set<CentreDTO> >mapOfCentreDTOToaddress;
	Map<String, Set<CentreDTO> >mapOfCentreDTOTocontactPerson;
	Map<String, Set<CentreDTO> >mapOfCentreDTOTophoneNumber;
	Map<String, Set<CentreDTO> >mapOfCentreDTOToemail;
	Map<String, Set<CentreDTO> >mapOfCentreDTOTodescription;


	static CentreRepository instance = null;  
	private CentreRepository(){
		mapOfCentreDTOToiD = new ConcurrentHashMap<>();
		mapOfCentreDTOTonameEn = new ConcurrentHashMap<>();
		mapOfCentreDTOTonameBn = new ConcurrentHashMap<>();
		mapOfCentreDTOToaddress = new ConcurrentHashMap<>();
		mapOfCentreDTOTocontactPerson = new ConcurrentHashMap<>();
		mapOfCentreDTOTophoneNumber = new ConcurrentHashMap<>();
		mapOfCentreDTOToemail = new ConcurrentHashMap<>();
		mapOfCentreDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static CentreRepository getInstance(){
		if (instance == null){
			instance = new CentreRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<CentreDTO> centreDTOs = centreDAO.getAllCentre(reloadAll);
			for(CentreDTO centreDTO : centreDTOs) {
				CentreDTO oldCentreDTO = getCentreDTOByID(centreDTO.iD);
				if( oldCentreDTO != null ) {
					mapOfCentreDTOToiD.remove(oldCentreDTO.iD);
				
					if(mapOfCentreDTOTonameEn.containsKey(oldCentreDTO.nameEn)) {
						mapOfCentreDTOTonameEn.get(oldCentreDTO.nameEn).remove(oldCentreDTO);
					}
					if(mapOfCentreDTOTonameEn.get(oldCentreDTO.nameEn).isEmpty()) {
						mapOfCentreDTOTonameEn.remove(oldCentreDTO.nameEn);
					}
					
					if(mapOfCentreDTOTonameBn.containsKey(oldCentreDTO.nameBn)) {
						mapOfCentreDTOTonameBn.get(oldCentreDTO.nameBn).remove(oldCentreDTO);
					}
					if(mapOfCentreDTOTonameBn.get(oldCentreDTO.nameBn).isEmpty()) {
						mapOfCentreDTOTonameBn.remove(oldCentreDTO.nameBn);
					}
					
					if(mapOfCentreDTOToaddress.containsKey(oldCentreDTO.address)) {
						mapOfCentreDTOToaddress.get(oldCentreDTO.address).remove(oldCentreDTO);
					}
					if(mapOfCentreDTOToaddress.get(oldCentreDTO.address).isEmpty()) {
						mapOfCentreDTOToaddress.remove(oldCentreDTO.address);
					}
					
					if(mapOfCentreDTOTocontactPerson.containsKey(oldCentreDTO.contactPerson)) {
						mapOfCentreDTOTocontactPerson.get(oldCentreDTO.contactPerson).remove(oldCentreDTO);
					}
					if(mapOfCentreDTOTocontactPerson.get(oldCentreDTO.contactPerson).isEmpty()) {
						mapOfCentreDTOTocontactPerson.remove(oldCentreDTO.contactPerson);
					}
					
					if(mapOfCentreDTOTophoneNumber.containsKey(oldCentreDTO.phoneNumber)) {
						mapOfCentreDTOTophoneNumber.get(oldCentreDTO.phoneNumber).remove(oldCentreDTO);
					}
					if(mapOfCentreDTOTophoneNumber.get(oldCentreDTO.phoneNumber).isEmpty()) {
						mapOfCentreDTOTophoneNumber.remove(oldCentreDTO.phoneNumber);
					}
					
					if(mapOfCentreDTOToemail.containsKey(oldCentreDTO.email)) {
						mapOfCentreDTOToemail.get(oldCentreDTO.email).remove(oldCentreDTO);
					}
					if(mapOfCentreDTOToemail.get(oldCentreDTO.email).isEmpty()) {
						mapOfCentreDTOToemail.remove(oldCentreDTO.email);
					}
					
					if(mapOfCentreDTOTodescription.containsKey(oldCentreDTO.description)) {
						mapOfCentreDTOTodescription.get(oldCentreDTO.description).remove(oldCentreDTO);
					}
					if(mapOfCentreDTOTodescription.get(oldCentreDTO.description).isEmpty()) {
						mapOfCentreDTOTodescription.remove(oldCentreDTO.description);
					}
					
					
				}
				if(centreDTO.isDeleted == false) 
				{
					
					mapOfCentreDTOToiD.put(centreDTO.iD, centreDTO);
				
					if( ! mapOfCentreDTOTonameEn.containsKey(centreDTO.nameEn)) {
						mapOfCentreDTOTonameEn.put(centreDTO.nameEn, new HashSet<>());
					}
					mapOfCentreDTOTonameEn.get(centreDTO.nameEn).add(centreDTO);
					
					if( ! mapOfCentreDTOTonameBn.containsKey(centreDTO.nameBn)) {
						mapOfCentreDTOTonameBn.put(centreDTO.nameBn, new HashSet<>());
					}
					mapOfCentreDTOTonameBn.get(centreDTO.nameBn).add(centreDTO);
					
					if( ! mapOfCentreDTOToaddress.containsKey(centreDTO.address)) {
						mapOfCentreDTOToaddress.put(centreDTO.address, new HashSet<>());
					}
					mapOfCentreDTOToaddress.get(centreDTO.address).add(centreDTO);
					
					if( ! mapOfCentreDTOTocontactPerson.containsKey(centreDTO.contactPerson)) {
						mapOfCentreDTOTocontactPerson.put(centreDTO.contactPerson, new HashSet<>());
					}
					mapOfCentreDTOTocontactPerson.get(centreDTO.contactPerson).add(centreDTO);
					
					if( ! mapOfCentreDTOTophoneNumber.containsKey(centreDTO.phoneNumber)) {
						mapOfCentreDTOTophoneNumber.put(centreDTO.phoneNumber, new HashSet<>());
					}
					mapOfCentreDTOTophoneNumber.get(centreDTO.phoneNumber).add(centreDTO);
					
					if( ! mapOfCentreDTOToemail.containsKey(centreDTO.email)) {
						mapOfCentreDTOToemail.put(centreDTO.email, new HashSet<>());
					}
					mapOfCentreDTOToemail.get(centreDTO.email).add(centreDTO);
					
					if( ! mapOfCentreDTOTodescription.containsKey(centreDTO.description)) {
						mapOfCentreDTOTodescription.put(centreDTO.description, new HashSet<>());
					}
					mapOfCentreDTOTodescription.get(centreDTO.description).add(centreDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<CentreDTO> getCentreList() {
		List <CentreDTO> centres = new ArrayList<CentreDTO>(this.mapOfCentreDTOToiD.values());
		return centres;
	}
	
	
	public CentreDTO getCentreDTOByID( long ID){
		return mapOfCentreDTOToiD.get(ID);
	}
	
	
	public List<CentreDTO> getCentreDTOByname_en(String name_en) {
		return new ArrayList<>( mapOfCentreDTOTonameEn.getOrDefault(name_en,new HashSet<>()));
	}
	
	
	public List<CentreDTO> getCentreDTOByname_bn(String name_bn) {
		return new ArrayList<>( mapOfCentreDTOTonameBn.getOrDefault(name_bn,new HashSet<>()));
	}
	
	
	public List<CentreDTO> getCentreDTOByaddress(String address) {
		return new ArrayList<>( mapOfCentreDTOToaddress.getOrDefault(address,new HashSet<>()));
	}
	
	
	public List<CentreDTO> getCentreDTOBycontact_person(String contact_person) {
		return new ArrayList<>( mapOfCentreDTOTocontactPerson.getOrDefault(contact_person,new HashSet<>()));
	}
	
	
	public List<CentreDTO> getCentreDTOByphone_number(String phone_number) {
		return new ArrayList<>( mapOfCentreDTOTophoneNumber.getOrDefault(phone_number,new HashSet<>()));
	}
	
	
	public List<CentreDTO> getCentreDTOByemail(String email) {
		return new ArrayList<>( mapOfCentreDTOToemail.getOrDefault(email,new HashSet<>()));
	}
	
	
	public List<CentreDTO> getCentreDTOBydescription(String description) {
		return new ArrayList<>( mapOfCentreDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "centre";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


