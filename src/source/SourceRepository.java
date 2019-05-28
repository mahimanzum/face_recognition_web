package source;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class SourceRepository implements Repository {
	SourceDAO sourceDAO = new SourceDAO();
	
	
	static Logger logger = Logger.getLogger(SourceRepository.class);
	Map<Long, SourceDTO>mapOfSourceDTOToiD;
	Map<String, Set<SourceDTO> >mapOfSourceDTOTonameEn;
	Map<String, Set<SourceDTO> >mapOfSourceDTOTonameBn;
	Map<String, Set<SourceDTO> >mapOfSourceDTOTodescription;


	static SourceRepository instance = null;  
	private SourceRepository(){
		mapOfSourceDTOToiD = new ConcurrentHashMap<>();
		mapOfSourceDTOTonameEn = new ConcurrentHashMap<>();
		mapOfSourceDTOTonameBn = new ConcurrentHashMap<>();
		mapOfSourceDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static SourceRepository getInstance(){
		if (instance == null){
			instance = new SourceRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<SourceDTO> sourceDTOs = sourceDAO.getAllSource(reloadAll);
			for(SourceDTO sourceDTO : sourceDTOs) {
				SourceDTO oldSourceDTO = getSourceDTOByID(sourceDTO.iD);
				if( oldSourceDTO != null ) {
					mapOfSourceDTOToiD.remove(oldSourceDTO.iD);
				
					if(mapOfSourceDTOTonameEn.containsKey(oldSourceDTO.nameEn)) {
						mapOfSourceDTOTonameEn.get(oldSourceDTO.nameEn).remove(oldSourceDTO);
					}
					if(mapOfSourceDTOTonameEn.get(oldSourceDTO.nameEn).isEmpty()) {
						mapOfSourceDTOTonameEn.remove(oldSourceDTO.nameEn);
					}
					
					if(mapOfSourceDTOTonameBn.containsKey(oldSourceDTO.nameBn)) {
						mapOfSourceDTOTonameBn.get(oldSourceDTO.nameBn).remove(oldSourceDTO);
					}
					if(mapOfSourceDTOTonameBn.get(oldSourceDTO.nameBn).isEmpty()) {
						mapOfSourceDTOTonameBn.remove(oldSourceDTO.nameBn);
					}
					
					if(mapOfSourceDTOTodescription.containsKey(oldSourceDTO.description)) {
						mapOfSourceDTOTodescription.get(oldSourceDTO.description).remove(oldSourceDTO);
					}
					if(mapOfSourceDTOTodescription.get(oldSourceDTO.description).isEmpty()) {
						mapOfSourceDTOTodescription.remove(oldSourceDTO.description);
					}
					
					
				}
				if(sourceDTO.isDeleted == false) 
				{
					
					mapOfSourceDTOToiD.put(sourceDTO.iD, sourceDTO);
				
					if( ! mapOfSourceDTOTonameEn.containsKey(sourceDTO.nameEn)) {
						mapOfSourceDTOTonameEn.put(sourceDTO.nameEn, new HashSet<>());
					}
					mapOfSourceDTOTonameEn.get(sourceDTO.nameEn).add(sourceDTO);
					
					if( ! mapOfSourceDTOTonameBn.containsKey(sourceDTO.nameBn)) {
						mapOfSourceDTOTonameBn.put(sourceDTO.nameBn, new HashSet<>());
					}
					mapOfSourceDTOTonameBn.get(sourceDTO.nameBn).add(sourceDTO);
					
					if( ! mapOfSourceDTOTodescription.containsKey(sourceDTO.description)) {
						mapOfSourceDTOTodescription.put(sourceDTO.description, new HashSet<>());
					}
					mapOfSourceDTOTodescription.get(sourceDTO.description).add(sourceDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<SourceDTO> getSourceList() {
		List <SourceDTO> sources = new ArrayList<SourceDTO>(this.mapOfSourceDTOToiD.values());
		return sources;
	}
	
	
	public SourceDTO getSourceDTOByID( long ID){
		return mapOfSourceDTOToiD.get(ID);
	}
	
	
	public List<SourceDTO> getSourceDTOByname_en(String name_en) {
		return new ArrayList<>( mapOfSourceDTOTonameEn.getOrDefault(name_en,new HashSet<>()));
	}
	
	
	public List<SourceDTO> getSourceDTOByname_bn(String name_bn) {
		return new ArrayList<>( mapOfSourceDTOTonameBn.getOrDefault(name_bn,new HashSet<>()));
	}
	
	
	public List<SourceDTO> getSourceDTOBydescription(String description) {
		return new ArrayList<>( mapOfSourceDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "source";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


