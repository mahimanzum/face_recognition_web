package stick;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class StickRepository implements Repository {
	StickDAO stickDAO = new StickDAO();
	
	
	static Logger logger = Logger.getLogger(StickRepository.class);
	Map<Long, StickDTO>mapOfStickDTOToiD;
	Map<String, Set<StickDTO> >mapOfStickDTOTonameEn;
	Map<String, Set<StickDTO> >mapOfStickDTOTonameBn;
	Map<Long, Set<StickDTO> >mapOfStickDTOTosemenCollectionId;


	static StickRepository instance = null;  
	private StickRepository(){
		mapOfStickDTOToiD = new ConcurrentHashMap<>();
		mapOfStickDTOTonameEn = new ConcurrentHashMap<>();
		mapOfStickDTOTonameBn = new ConcurrentHashMap<>();
		mapOfStickDTOTosemenCollectionId = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static StickRepository getInstance(){
		if (instance == null){
			instance = new StickRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<StickDTO> stickDTOs = stickDAO.getAllStick(reloadAll);
			for(StickDTO stickDTO : stickDTOs) {
				StickDTO oldStickDTO = getStickDTOByID(stickDTO.iD);
				if( oldStickDTO != null ) {
					mapOfStickDTOToiD.remove(oldStickDTO.iD);
				
					if(mapOfStickDTOTonameEn.containsKey(oldStickDTO.nameEn)) {
						mapOfStickDTOTonameEn.get(oldStickDTO.nameEn).remove(oldStickDTO);
					}
					if(mapOfStickDTOTonameEn.get(oldStickDTO.nameEn).isEmpty()) {
						mapOfStickDTOTonameEn.remove(oldStickDTO.nameEn);
					}
					
					if(mapOfStickDTOTonameBn.containsKey(oldStickDTO.nameBn)) {
						mapOfStickDTOTonameBn.get(oldStickDTO.nameBn).remove(oldStickDTO);
					}
					if(mapOfStickDTOTonameBn.get(oldStickDTO.nameBn).isEmpty()) {
						mapOfStickDTOTonameBn.remove(oldStickDTO.nameBn);
					}
					
					if(mapOfStickDTOTosemenCollectionId.containsKey(oldStickDTO.semenCollectionId)) {
						mapOfStickDTOTosemenCollectionId.get(oldStickDTO.semenCollectionId).remove(oldStickDTO);
					}
					if(mapOfStickDTOTosemenCollectionId.get(oldStickDTO.semenCollectionId).isEmpty()) {
						mapOfStickDTOTosemenCollectionId.remove(oldStickDTO.semenCollectionId);
					}
					
					
				}
				if(stickDTO.isDeleted == false) 
				{
					
					mapOfStickDTOToiD.put(stickDTO.iD, stickDTO);
				
					if( ! mapOfStickDTOTonameEn.containsKey(stickDTO.nameEn)) {
						mapOfStickDTOTonameEn.put(stickDTO.nameEn, new HashSet<>());
					}
					mapOfStickDTOTonameEn.get(stickDTO.nameEn).add(stickDTO);
					
					if( ! mapOfStickDTOTonameBn.containsKey(stickDTO.nameBn)) {
						mapOfStickDTOTonameBn.put(stickDTO.nameBn, new HashSet<>());
					}
					mapOfStickDTOTonameBn.get(stickDTO.nameBn).add(stickDTO);
					
					if( ! mapOfStickDTOTosemenCollectionId.containsKey(stickDTO.semenCollectionId)) {
						mapOfStickDTOTosemenCollectionId.put(stickDTO.semenCollectionId, new HashSet<>());
					}
					mapOfStickDTOTosemenCollectionId.get(stickDTO.semenCollectionId).add(stickDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<StickDTO> getStickList() {
		List <StickDTO> sticks = new ArrayList<StickDTO>(this.mapOfStickDTOToiD.values());
		return sticks;
	}
	
	
	public StickDTO getStickDTOByID( long ID){
		return mapOfStickDTOToiD.get(ID);
	}
	
	
	public List<StickDTO> getStickDTOByname_en(String name_en) {
		return new ArrayList<>( mapOfStickDTOTonameEn.getOrDefault(name_en,new HashSet<>()));
	}
	
	
	public List<StickDTO> getStickDTOByname_bn(String name_bn) {
		return new ArrayList<>( mapOfStickDTOTonameBn.getOrDefault(name_bn,new HashSet<>()));
	}
	
	
	public List<StickDTO> getStickDTOBysemen_collection_id(long semen_collection_id) {
		return new ArrayList<>( mapOfStickDTOTosemenCollectionId.getOrDefault(semen_collection_id,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "stick";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


