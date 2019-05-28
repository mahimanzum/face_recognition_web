package artificial_insemenation_record;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Artificial_insemenation_recordRepository implements Repository {
	Artificial_insemenation_recordDAO artificial_insemenation_recordDAO = new Artificial_insemenation_recordDAO();
	
	
	static Logger logger = Logger.getLogger(Artificial_insemenation_recordRepository.class);
	Map<Long, Artificial_insemenation_recordDTO>mapOfArtificial_insemenation_recordDTOToiD;
	Map<Integer, Set<Artificial_insemenation_recordDTO> >mapOfArtificial_insemenation_recordDTOTocentreType;
	Map<Integer, Set<Artificial_insemenation_recordDTO> >mapOfArtificial_insemenation_recordDTOTobullType;
	Map<Integer, Set<Artificial_insemenation_recordDTO> >mapOfArtificial_insemenation_recordDTOTonoOfAI;
	Map<Long, Set<Artificial_insemenation_recordDTO> >mapOfArtificial_insemenation_recordDTOToentryDate;
	Map<String, Set<Artificial_insemenation_recordDTO> >mapOfArtificial_insemenation_recordDTOTodescription;


	static Artificial_insemenation_recordRepository instance = null;  
	private Artificial_insemenation_recordRepository(){
		mapOfArtificial_insemenation_recordDTOToiD = new ConcurrentHashMap<>();
		mapOfArtificial_insemenation_recordDTOTocentreType = new ConcurrentHashMap<>();
		mapOfArtificial_insemenation_recordDTOTobullType = new ConcurrentHashMap<>();
		mapOfArtificial_insemenation_recordDTOTonoOfAI = new ConcurrentHashMap<>();
		mapOfArtificial_insemenation_recordDTOToentryDate = new ConcurrentHashMap<>();
		mapOfArtificial_insemenation_recordDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Artificial_insemenation_recordRepository getInstance(){
		if (instance == null){
			instance = new Artificial_insemenation_recordRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Artificial_insemenation_recordDTO> artificial_insemenation_recordDTOs = artificial_insemenation_recordDAO.getAllArtificial_insemenation_record(reloadAll);
			for(Artificial_insemenation_recordDTO artificial_insemenation_recordDTO : artificial_insemenation_recordDTOs) {
				Artificial_insemenation_recordDTO oldArtificial_insemenation_recordDTO = getArtificial_insemenation_recordDTOByID(artificial_insemenation_recordDTO.iD);
				if( oldArtificial_insemenation_recordDTO != null ) {
					mapOfArtificial_insemenation_recordDTOToiD.remove(oldArtificial_insemenation_recordDTO.iD);
				
					if(mapOfArtificial_insemenation_recordDTOTocentreType.containsKey(oldArtificial_insemenation_recordDTO.centreType)) {
						mapOfArtificial_insemenation_recordDTOTocentreType.get(oldArtificial_insemenation_recordDTO.centreType).remove(oldArtificial_insemenation_recordDTO);
					}
					if(mapOfArtificial_insemenation_recordDTOTocentreType.get(oldArtificial_insemenation_recordDTO.centreType).isEmpty()) {
						mapOfArtificial_insemenation_recordDTOTocentreType.remove(oldArtificial_insemenation_recordDTO.centreType);
					}
					
					if(mapOfArtificial_insemenation_recordDTOTobullType.containsKey(oldArtificial_insemenation_recordDTO.bullType)) {
						mapOfArtificial_insemenation_recordDTOTobullType.get(oldArtificial_insemenation_recordDTO.bullType).remove(oldArtificial_insemenation_recordDTO);
					}
					if(mapOfArtificial_insemenation_recordDTOTobullType.get(oldArtificial_insemenation_recordDTO.bullType).isEmpty()) {
						mapOfArtificial_insemenation_recordDTOTobullType.remove(oldArtificial_insemenation_recordDTO.bullType);
					}
					
					if(mapOfArtificial_insemenation_recordDTOTonoOfAI.containsKey(oldArtificial_insemenation_recordDTO.noOfAI)) {
						mapOfArtificial_insemenation_recordDTOTonoOfAI.get(oldArtificial_insemenation_recordDTO.noOfAI).remove(oldArtificial_insemenation_recordDTO);
					}
					if(mapOfArtificial_insemenation_recordDTOTonoOfAI.get(oldArtificial_insemenation_recordDTO.noOfAI).isEmpty()) {
						mapOfArtificial_insemenation_recordDTOTonoOfAI.remove(oldArtificial_insemenation_recordDTO.noOfAI);
					}
					
					if(mapOfArtificial_insemenation_recordDTOToentryDate.containsKey(oldArtificial_insemenation_recordDTO.entryDate)) {
						mapOfArtificial_insemenation_recordDTOToentryDate.get(oldArtificial_insemenation_recordDTO.entryDate).remove(oldArtificial_insemenation_recordDTO);
					}
					if(mapOfArtificial_insemenation_recordDTOToentryDate.get(oldArtificial_insemenation_recordDTO.entryDate).isEmpty()) {
						mapOfArtificial_insemenation_recordDTOToentryDate.remove(oldArtificial_insemenation_recordDTO.entryDate);
					}
					
					if(mapOfArtificial_insemenation_recordDTOTodescription.containsKey(oldArtificial_insemenation_recordDTO.description)) {
						mapOfArtificial_insemenation_recordDTOTodescription.get(oldArtificial_insemenation_recordDTO.description).remove(oldArtificial_insemenation_recordDTO);
					}
					if(mapOfArtificial_insemenation_recordDTOTodescription.get(oldArtificial_insemenation_recordDTO.description).isEmpty()) {
						mapOfArtificial_insemenation_recordDTOTodescription.remove(oldArtificial_insemenation_recordDTO.description);
					}
					
					
				}
				if(artificial_insemenation_recordDTO.isDeleted == false) 
				{
					
					mapOfArtificial_insemenation_recordDTOToiD.put(artificial_insemenation_recordDTO.iD, artificial_insemenation_recordDTO);
				
					if( ! mapOfArtificial_insemenation_recordDTOTocentreType.containsKey(artificial_insemenation_recordDTO.centreType)) {
						mapOfArtificial_insemenation_recordDTOTocentreType.put(artificial_insemenation_recordDTO.centreType, new HashSet<>());
					}
					mapOfArtificial_insemenation_recordDTOTocentreType.get(artificial_insemenation_recordDTO.centreType).add(artificial_insemenation_recordDTO);
					
					if( ! mapOfArtificial_insemenation_recordDTOTobullType.containsKey(artificial_insemenation_recordDTO.bullType)) {
						mapOfArtificial_insemenation_recordDTOTobullType.put(artificial_insemenation_recordDTO.bullType, new HashSet<>());
					}
					mapOfArtificial_insemenation_recordDTOTobullType.get(artificial_insemenation_recordDTO.bullType).add(artificial_insemenation_recordDTO);
					
					if( ! mapOfArtificial_insemenation_recordDTOTonoOfAI.containsKey(artificial_insemenation_recordDTO.noOfAI)) {
						mapOfArtificial_insemenation_recordDTOTonoOfAI.put(artificial_insemenation_recordDTO.noOfAI, new HashSet<>());
					}
					mapOfArtificial_insemenation_recordDTOTonoOfAI.get(artificial_insemenation_recordDTO.noOfAI).add(artificial_insemenation_recordDTO);
					
					if( ! mapOfArtificial_insemenation_recordDTOToentryDate.containsKey(artificial_insemenation_recordDTO.entryDate)) {
						mapOfArtificial_insemenation_recordDTOToentryDate.put(artificial_insemenation_recordDTO.entryDate, new HashSet<>());
					}
					mapOfArtificial_insemenation_recordDTOToentryDate.get(artificial_insemenation_recordDTO.entryDate).add(artificial_insemenation_recordDTO);
					
					if( ! mapOfArtificial_insemenation_recordDTOTodescription.containsKey(artificial_insemenation_recordDTO.description)) {
						mapOfArtificial_insemenation_recordDTOTodescription.put(artificial_insemenation_recordDTO.description, new HashSet<>());
					}
					mapOfArtificial_insemenation_recordDTOTodescription.get(artificial_insemenation_recordDTO.description).add(artificial_insemenation_recordDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Artificial_insemenation_recordDTO> getArtificial_insemenation_recordList() {
		List <Artificial_insemenation_recordDTO> artificial_insemenation_records = new ArrayList<Artificial_insemenation_recordDTO>(this.mapOfArtificial_insemenation_recordDTOToiD.values());
		return artificial_insemenation_records;
	}
	
	
	public Artificial_insemenation_recordDTO getArtificial_insemenation_recordDTOByID( long ID){
		return mapOfArtificial_insemenation_recordDTOToiD.get(ID);
	}
	
	
	public List<Artificial_insemenation_recordDTO> getArtificial_insemenation_recordDTOBycentre_type(int centre_type) {
		return new ArrayList<>( mapOfArtificial_insemenation_recordDTOTocentreType.getOrDefault(centre_type,new HashSet<>()));
	}
	
	
	public List<Artificial_insemenation_recordDTO> getArtificial_insemenation_recordDTOBybull_type(int bull_type) {
		return new ArrayList<>( mapOfArtificial_insemenation_recordDTOTobullType.getOrDefault(bull_type,new HashSet<>()));
	}
	
	
	public List<Artificial_insemenation_recordDTO> getArtificial_insemenation_recordDTOByno_of_AI(int no_of_AI) {
		return new ArrayList<>( mapOfArtificial_insemenation_recordDTOTonoOfAI.getOrDefault(no_of_AI,new HashSet<>()));
	}
	
	
	public List<Artificial_insemenation_recordDTO> getArtificial_insemenation_recordDTOByentry_date(long entry_date) {
		return new ArrayList<>( mapOfArtificial_insemenation_recordDTOToentryDate.getOrDefault(entry_date,new HashSet<>()));
	}
	
	
	public List<Artificial_insemenation_recordDTO> getArtificial_insemenation_recordDTOBydescription(String description) {
		return new ArrayList<>( mapOfArtificial_insemenation_recordDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "artificial_insemenation_record";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


