package breed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class BreedRepository implements Repository {
	BreedDAO breedDAO = new BreedDAO();
	
	
	static Logger logger = Logger.getLogger(BreedRepository.class);
	Map<Long, BreedDTO>mapOfBreedDTOToiD;
	Map<String, Set<BreedDTO> >mapOfBreedDTOTonameEn;
	Map<String, Set<BreedDTO> >mapOfBreedDTOTonameBn;
	Map<String, Set<BreedDTO> >mapOfBreedDTOTodescription;


	static BreedRepository instance = null;  
	private BreedRepository(){
		mapOfBreedDTOToiD = new ConcurrentHashMap<>();
		mapOfBreedDTOTonameEn = new ConcurrentHashMap<>();
		mapOfBreedDTOTonameBn = new ConcurrentHashMap<>();
		mapOfBreedDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static BreedRepository getInstance(){
		if (instance == null){
			instance = new BreedRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<BreedDTO> breedDTOs = breedDAO.getAllBreed(reloadAll);
			for(BreedDTO breedDTO : breedDTOs) {
				BreedDTO oldBreedDTO = getBreedDTOByID(breedDTO.iD);
				if( oldBreedDTO != null ) {
					mapOfBreedDTOToiD.remove(oldBreedDTO.iD);
				
					if(mapOfBreedDTOTonameEn.containsKey(oldBreedDTO.nameEn)) {
						mapOfBreedDTOTonameEn.get(oldBreedDTO.nameEn).remove(oldBreedDTO);
					}
					if(mapOfBreedDTOTonameEn.get(oldBreedDTO.nameEn).isEmpty()) {
						mapOfBreedDTOTonameEn.remove(oldBreedDTO.nameEn);
					}
					
					if(mapOfBreedDTOTonameBn.containsKey(oldBreedDTO.nameBn)) {
						mapOfBreedDTOTonameBn.get(oldBreedDTO.nameBn).remove(oldBreedDTO);
					}
					if(mapOfBreedDTOTonameBn.get(oldBreedDTO.nameBn).isEmpty()) {
						mapOfBreedDTOTonameBn.remove(oldBreedDTO.nameBn);
					}
					
					if(mapOfBreedDTOTodescription.containsKey(oldBreedDTO.description)) {
						mapOfBreedDTOTodescription.get(oldBreedDTO.description).remove(oldBreedDTO);
					}
					if(mapOfBreedDTOTodescription.get(oldBreedDTO.description).isEmpty()) {
						mapOfBreedDTOTodescription.remove(oldBreedDTO.description);
					}
					
					
				}
				if(breedDTO.isDeleted == false) 
				{
					
					mapOfBreedDTOToiD.put(breedDTO.iD, breedDTO);
				
					if( ! mapOfBreedDTOTonameEn.containsKey(breedDTO.nameEn)) {
						mapOfBreedDTOTonameEn.put(breedDTO.nameEn, new HashSet<>());
					}
					mapOfBreedDTOTonameEn.get(breedDTO.nameEn).add(breedDTO);
					
					if( ! mapOfBreedDTOTonameBn.containsKey(breedDTO.nameBn)) {
						mapOfBreedDTOTonameBn.put(breedDTO.nameBn, new HashSet<>());
					}
					mapOfBreedDTOTonameBn.get(breedDTO.nameBn).add(breedDTO);
					
					if( ! mapOfBreedDTOTodescription.containsKey(breedDTO.description)) {
						mapOfBreedDTOTodescription.put(breedDTO.description, new HashSet<>());
					}
					mapOfBreedDTOTodescription.get(breedDTO.description).add(breedDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<BreedDTO> getBreedList() {
		List <BreedDTO> breeds = new ArrayList<BreedDTO>(this.mapOfBreedDTOToiD.values());
		return breeds;
	}
	
	
	public BreedDTO getBreedDTOByID( long ID){
		return mapOfBreedDTOToiD.get(ID);
	}
	
	
	public List<BreedDTO> getBreedDTOByname_en(String name_en) {
		return new ArrayList<>( mapOfBreedDTOTonameEn.getOrDefault(name_en,new HashSet<>()));
	}
	
	
	public List<BreedDTO> getBreedDTOByname_bn(String name_bn) {
		return new ArrayList<>( mapOfBreedDTOTonameBn.getOrDefault(name_bn,new HashSet<>()));
	}
	
	
	public List<BreedDTO> getBreedDTOBydescription(String description) {
		return new ArrayList<>( mapOfBreedDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "breed";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


