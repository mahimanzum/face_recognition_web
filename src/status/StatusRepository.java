package status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class StatusRepository implements Repository {
	StatusDAO statusDAO = new StatusDAO();
	
	
	static Logger logger = Logger.getLogger(StatusRepository.class);
	Map<Long, StatusDTO>mapOfStatusDTOToiD;
	Map<String, Set<StatusDTO> >mapOfStatusDTOTonameEn;
	Map<String, Set<StatusDTO> >mapOfStatusDTOTonameBn;
	Map<String, Set<StatusDTO> >mapOfStatusDTOTodescription;


	static StatusRepository instance = null;  
	private StatusRepository(){
		mapOfStatusDTOToiD = new ConcurrentHashMap<>();
		mapOfStatusDTOTonameEn = new ConcurrentHashMap<>();
		mapOfStatusDTOTonameBn = new ConcurrentHashMap<>();
		mapOfStatusDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static StatusRepository getInstance(){
		if (instance == null){
			instance = new StatusRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<StatusDTO> statusDTOs = statusDAO.getAllStatus(reloadAll);
			for(StatusDTO statusDTO : statusDTOs) {
				StatusDTO oldStatusDTO = getStatusDTOByID(statusDTO.iD);
				if( oldStatusDTO != null ) {
					mapOfStatusDTOToiD.remove(oldStatusDTO.iD);
				
					if(mapOfStatusDTOTonameEn.containsKey(oldStatusDTO.nameEn)) {
						mapOfStatusDTOTonameEn.get(oldStatusDTO.nameEn).remove(oldStatusDTO);
					}
					if(mapOfStatusDTOTonameEn.get(oldStatusDTO.nameEn).isEmpty()) {
						mapOfStatusDTOTonameEn.remove(oldStatusDTO.nameEn);
					}
					
					if(mapOfStatusDTOTonameBn.containsKey(oldStatusDTO.nameBn)) {
						mapOfStatusDTOTonameBn.get(oldStatusDTO.nameBn).remove(oldStatusDTO);
					}
					if(mapOfStatusDTOTonameBn.get(oldStatusDTO.nameBn).isEmpty()) {
						mapOfStatusDTOTonameBn.remove(oldStatusDTO.nameBn);
					}
					
					if(mapOfStatusDTOTodescription.containsKey(oldStatusDTO.description)) {
						mapOfStatusDTOTodescription.get(oldStatusDTO.description).remove(oldStatusDTO);
					}
					if(mapOfStatusDTOTodescription.get(oldStatusDTO.description).isEmpty()) {
						mapOfStatusDTOTodescription.remove(oldStatusDTO.description);
					}
					
					
				}
				if(statusDTO.isDeleted == false) 
				{
					
					mapOfStatusDTOToiD.put(statusDTO.iD, statusDTO);
				
					if( ! mapOfStatusDTOTonameEn.containsKey(statusDTO.nameEn)) {
						mapOfStatusDTOTonameEn.put(statusDTO.nameEn, new HashSet<>());
					}
					mapOfStatusDTOTonameEn.get(statusDTO.nameEn).add(statusDTO);
					
					if( ! mapOfStatusDTOTonameBn.containsKey(statusDTO.nameBn)) {
						mapOfStatusDTOTonameBn.put(statusDTO.nameBn, new HashSet<>());
					}
					mapOfStatusDTOTonameBn.get(statusDTO.nameBn).add(statusDTO);
					
					if( ! mapOfStatusDTOTodescription.containsKey(statusDTO.description)) {
						mapOfStatusDTOTodescription.put(statusDTO.description, new HashSet<>());
					}
					mapOfStatusDTOTodescription.get(statusDTO.description).add(statusDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<StatusDTO> getStatusList() {
		List <StatusDTO> statuss = new ArrayList<StatusDTO>(this.mapOfStatusDTOToiD.values());
		return statuss;
	}
	
	
	public StatusDTO getStatusDTOByID( long ID){
		return mapOfStatusDTOToiD.get(ID);
	}
	
	
	public List<StatusDTO> getStatusDTOByname_en(String name_en) {
		return new ArrayList<>( mapOfStatusDTOTonameEn.getOrDefault(name_en,new HashSet<>()));
	}
	
	
	public List<StatusDTO> getStatusDTOByname_bn(String name_bn) {
		return new ArrayList<>( mapOfStatusDTOTonameBn.getOrDefault(name_bn,new HashSet<>()));
	}
	
	
	public List<StatusDTO> getStatusDTOBydescription(String description) {
		return new ArrayList<>( mapOfStatusDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "status";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


