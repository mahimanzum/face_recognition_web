package progeny_record;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Progeny_recordRepository implements Repository {
	Progeny_recordDAO progeny_recordDAO = new Progeny_recordDAO();
	
	
	static Logger logger = Logger.getLogger(Progeny_recordRepository.class);
	Map<Long, Progeny_recordDTO>mapOfProgeny_recordDTOToiD;
	Map<Integer, Set<Progeny_recordDTO> >mapOfProgeny_recordDTOTocentreType;
	Map<Integer, Set<Progeny_recordDTO> >mapOfProgeny_recordDTOTobullType;
	Map<Integer, Set<Progeny_recordDTO> >mapOfProgeny_recordDTOTonumberOfMaleCalfs;
	Map<Integer, Set<Progeny_recordDTO> >mapOfProgeny_recordDTOTonumberOfFemaleCalfs;
	Map<Long, Set<Progeny_recordDTO> >mapOfProgeny_recordDTOTodateOfEntry;
	Map<String, Set<Progeny_recordDTO> >mapOfProgeny_recordDTOTodescription;


	static Progeny_recordRepository instance = null;  
	private Progeny_recordRepository(){
		mapOfProgeny_recordDTOToiD = new ConcurrentHashMap<>();
		mapOfProgeny_recordDTOTocentreType = new ConcurrentHashMap<>();
		mapOfProgeny_recordDTOTobullType = new ConcurrentHashMap<>();
		mapOfProgeny_recordDTOTonumberOfMaleCalfs = new ConcurrentHashMap<>();
		mapOfProgeny_recordDTOTonumberOfFemaleCalfs = new ConcurrentHashMap<>();
		mapOfProgeny_recordDTOTodateOfEntry = new ConcurrentHashMap<>();
		mapOfProgeny_recordDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Progeny_recordRepository getInstance(){
		if (instance == null){
			instance = new Progeny_recordRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Progeny_recordDTO> progeny_recordDTOs = progeny_recordDAO.getAllProgeny_record(reloadAll);
			for(Progeny_recordDTO progeny_recordDTO : progeny_recordDTOs) {
				Progeny_recordDTO oldProgeny_recordDTO = getProgeny_recordDTOByID(progeny_recordDTO.iD);
				if( oldProgeny_recordDTO != null ) {
					mapOfProgeny_recordDTOToiD.remove(oldProgeny_recordDTO.iD);
				
					if(mapOfProgeny_recordDTOTocentreType.containsKey(oldProgeny_recordDTO.centreType)) {
						mapOfProgeny_recordDTOTocentreType.get(oldProgeny_recordDTO.centreType).remove(oldProgeny_recordDTO);
					}
					if(mapOfProgeny_recordDTOTocentreType.get(oldProgeny_recordDTO.centreType).isEmpty()) {
						mapOfProgeny_recordDTOTocentreType.remove(oldProgeny_recordDTO.centreType);
					}
					
					if(mapOfProgeny_recordDTOTobullType.containsKey(oldProgeny_recordDTO.bullType)) {
						mapOfProgeny_recordDTOTobullType.get(oldProgeny_recordDTO.bullType).remove(oldProgeny_recordDTO);
					}
					if(mapOfProgeny_recordDTOTobullType.get(oldProgeny_recordDTO.bullType).isEmpty()) {
						mapOfProgeny_recordDTOTobullType.remove(oldProgeny_recordDTO.bullType);
					}
					
					if(mapOfProgeny_recordDTOTonumberOfMaleCalfs.containsKey(oldProgeny_recordDTO.numberOfMaleCalfs)) {
						mapOfProgeny_recordDTOTonumberOfMaleCalfs.get(oldProgeny_recordDTO.numberOfMaleCalfs).remove(oldProgeny_recordDTO);
					}
					if(mapOfProgeny_recordDTOTonumberOfMaleCalfs.get(oldProgeny_recordDTO.numberOfMaleCalfs).isEmpty()) {
						mapOfProgeny_recordDTOTonumberOfMaleCalfs.remove(oldProgeny_recordDTO.numberOfMaleCalfs);
					}
					
					if(mapOfProgeny_recordDTOTonumberOfFemaleCalfs.containsKey(oldProgeny_recordDTO.numberOfFemaleCalfs)) {
						mapOfProgeny_recordDTOTonumberOfFemaleCalfs.get(oldProgeny_recordDTO.numberOfFemaleCalfs).remove(oldProgeny_recordDTO);
					}
					if(mapOfProgeny_recordDTOTonumberOfFemaleCalfs.get(oldProgeny_recordDTO.numberOfFemaleCalfs).isEmpty()) {
						mapOfProgeny_recordDTOTonumberOfFemaleCalfs.remove(oldProgeny_recordDTO.numberOfFemaleCalfs);
					}
					
					if(mapOfProgeny_recordDTOTodateOfEntry.containsKey(oldProgeny_recordDTO.dateOfEntry)) {
						mapOfProgeny_recordDTOTodateOfEntry.get(oldProgeny_recordDTO.dateOfEntry).remove(oldProgeny_recordDTO);
					}
					if(mapOfProgeny_recordDTOTodateOfEntry.get(oldProgeny_recordDTO.dateOfEntry).isEmpty()) {
						mapOfProgeny_recordDTOTodateOfEntry.remove(oldProgeny_recordDTO.dateOfEntry);
					}
					
					if(mapOfProgeny_recordDTOTodescription.containsKey(oldProgeny_recordDTO.description)) {
						mapOfProgeny_recordDTOTodescription.get(oldProgeny_recordDTO.description).remove(oldProgeny_recordDTO);
					}
					if(mapOfProgeny_recordDTOTodescription.get(oldProgeny_recordDTO.description).isEmpty()) {
						mapOfProgeny_recordDTOTodescription.remove(oldProgeny_recordDTO.description);
					}
					
					
				}
				if(progeny_recordDTO.isDeleted == false) 
				{
					
					mapOfProgeny_recordDTOToiD.put(progeny_recordDTO.iD, progeny_recordDTO);
				
					if( ! mapOfProgeny_recordDTOTocentreType.containsKey(progeny_recordDTO.centreType)) {
						mapOfProgeny_recordDTOTocentreType.put(progeny_recordDTO.centreType, new HashSet<>());
					}
					mapOfProgeny_recordDTOTocentreType.get(progeny_recordDTO.centreType).add(progeny_recordDTO);
					
					if( ! mapOfProgeny_recordDTOTobullType.containsKey(progeny_recordDTO.bullType)) {
						mapOfProgeny_recordDTOTobullType.put(progeny_recordDTO.bullType, new HashSet<>());
					}
					mapOfProgeny_recordDTOTobullType.get(progeny_recordDTO.bullType).add(progeny_recordDTO);
					
					if( ! mapOfProgeny_recordDTOTonumberOfMaleCalfs.containsKey(progeny_recordDTO.numberOfMaleCalfs)) {
						mapOfProgeny_recordDTOTonumberOfMaleCalfs.put(progeny_recordDTO.numberOfMaleCalfs, new HashSet<>());
					}
					mapOfProgeny_recordDTOTonumberOfMaleCalfs.get(progeny_recordDTO.numberOfMaleCalfs).add(progeny_recordDTO);
					
					if( ! mapOfProgeny_recordDTOTonumberOfFemaleCalfs.containsKey(progeny_recordDTO.numberOfFemaleCalfs)) {
						mapOfProgeny_recordDTOTonumberOfFemaleCalfs.put(progeny_recordDTO.numberOfFemaleCalfs, new HashSet<>());
					}
					mapOfProgeny_recordDTOTonumberOfFemaleCalfs.get(progeny_recordDTO.numberOfFemaleCalfs).add(progeny_recordDTO);
					
					if( ! mapOfProgeny_recordDTOTodateOfEntry.containsKey(progeny_recordDTO.dateOfEntry)) {
						mapOfProgeny_recordDTOTodateOfEntry.put(progeny_recordDTO.dateOfEntry, new HashSet<>());
					}
					mapOfProgeny_recordDTOTodateOfEntry.get(progeny_recordDTO.dateOfEntry).add(progeny_recordDTO);
					
					if( ! mapOfProgeny_recordDTOTodescription.containsKey(progeny_recordDTO.description)) {
						mapOfProgeny_recordDTOTodescription.put(progeny_recordDTO.description, new HashSet<>());
					}
					mapOfProgeny_recordDTOTodescription.get(progeny_recordDTO.description).add(progeny_recordDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Progeny_recordDTO> getProgeny_recordList() {
		List <Progeny_recordDTO> progeny_records = new ArrayList<Progeny_recordDTO>(this.mapOfProgeny_recordDTOToiD.values());
		return progeny_records;
	}
	
	
	public Progeny_recordDTO getProgeny_recordDTOByID( long ID){
		return mapOfProgeny_recordDTOToiD.get(ID);
	}
	
	
	public List<Progeny_recordDTO> getProgeny_recordDTOBycentre_type(int centre_type) {
		return new ArrayList<>( mapOfProgeny_recordDTOTocentreType.getOrDefault(centre_type,new HashSet<>()));
	}
	
	
	public List<Progeny_recordDTO> getProgeny_recordDTOBybull_type(int bull_type) {
		return new ArrayList<>( mapOfProgeny_recordDTOTobullType.getOrDefault(bull_type,new HashSet<>()));
	}
	
	
	public List<Progeny_recordDTO> getProgeny_recordDTOBynumber_of_male_calfs(int number_of_male_calfs) {
		return new ArrayList<>( mapOfProgeny_recordDTOTonumberOfMaleCalfs.getOrDefault(number_of_male_calfs,new HashSet<>()));
	}
	
	
	public List<Progeny_recordDTO> getProgeny_recordDTOBynumber_of_female_calfs(int number_of_female_calfs) {
		return new ArrayList<>( mapOfProgeny_recordDTOTonumberOfFemaleCalfs.getOrDefault(number_of_female_calfs,new HashSet<>()));
	}
	
	
	public List<Progeny_recordDTO> getProgeny_recordDTOBydate_of_entry(long date_of_entry) {
		return new ArrayList<>( mapOfProgeny_recordDTOTodateOfEntry.getOrDefault(date_of_entry,new HashSet<>()));
	}
	
	
	public List<Progeny_recordDTO> getProgeny_recordDTOBydescription(String description) {
		return new ArrayList<>( mapOfProgeny_recordDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "progeny_record";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


