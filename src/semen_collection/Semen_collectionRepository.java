package semen_collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Semen_collectionRepository implements Repository {
	Semen_collectionDAO semen_collectionDAO = new Semen_collectionDAO();
	
	
	static Logger logger = Logger.getLogger(Semen_collectionRepository.class);
	Map<Long, Semen_collectionDTO>mapOfSemen_collectionDTOToiD;
	Map<Integer, Set<Semen_collectionDTO> >mapOfSemen_collectionDTOTobullType;
	Map<Integer, Set<Semen_collectionDTO> >mapOfSemen_collectionDTOTonoOfDose;
	Map<Integer, Set<Semen_collectionDTO> >mapOfSemen_collectionDTOTovolume;
	Map<Integer, Set<Semen_collectionDTO> >mapOfSemen_collectionDTOTodensity;
	Map<Integer, Set<Semen_collectionDTO> >mapOfSemen_collectionDTOToprogressiveMortality;
	Map<Integer, Set<Semen_collectionDTO> >mapOfSemen_collectionDTOTocolorType;
	Map<Long, Set<Semen_collectionDTO> >mapOfSemen_collectionDTOTotransactionDate;
	Map<String, Set<Semen_collectionDTO> >mapOfSemen_collectionDTOTodescription;


	static Semen_collectionRepository instance = null;  
	private Semen_collectionRepository(){
		mapOfSemen_collectionDTOToiD = new ConcurrentHashMap<>();
		mapOfSemen_collectionDTOTobullType = new ConcurrentHashMap<>();
		mapOfSemen_collectionDTOTonoOfDose = new ConcurrentHashMap<>();
		mapOfSemen_collectionDTOTovolume = new ConcurrentHashMap<>();
		mapOfSemen_collectionDTOTodensity = new ConcurrentHashMap<>();
		mapOfSemen_collectionDTOToprogressiveMortality = new ConcurrentHashMap<>();
		mapOfSemen_collectionDTOTocolorType = new ConcurrentHashMap<>();
		mapOfSemen_collectionDTOTotransactionDate = new ConcurrentHashMap<>();
		mapOfSemen_collectionDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Semen_collectionRepository getInstance(){
		if (instance == null){
			instance = new Semen_collectionRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Semen_collectionDTO> semen_collectionDTOs = semen_collectionDAO.getAllSemen_collection(reloadAll);
			for(Semen_collectionDTO semen_collectionDTO : semen_collectionDTOs) {
				Semen_collectionDTO oldSemen_collectionDTO = getSemen_collectionDTOByID(semen_collectionDTO.iD);
				if( oldSemen_collectionDTO != null ) {
					mapOfSemen_collectionDTOToiD.remove(oldSemen_collectionDTO.iD);
				
					if(mapOfSemen_collectionDTOTobullType.containsKey(oldSemen_collectionDTO.bullType)) {
						mapOfSemen_collectionDTOTobullType.get(oldSemen_collectionDTO.bullType).remove(oldSemen_collectionDTO);
					}
					if(mapOfSemen_collectionDTOTobullType.get(oldSemen_collectionDTO.bullType).isEmpty()) {
						mapOfSemen_collectionDTOTobullType.remove(oldSemen_collectionDTO.bullType);
					}
					
					if(mapOfSemen_collectionDTOTonoOfDose.containsKey(oldSemen_collectionDTO.noOfDose)) {
						mapOfSemen_collectionDTOTonoOfDose.get(oldSemen_collectionDTO.noOfDose).remove(oldSemen_collectionDTO);
					}
					if(mapOfSemen_collectionDTOTonoOfDose.get(oldSemen_collectionDTO.noOfDose).isEmpty()) {
						mapOfSemen_collectionDTOTonoOfDose.remove(oldSemen_collectionDTO.noOfDose);
					}
					
					if(mapOfSemen_collectionDTOTovolume.containsKey(oldSemen_collectionDTO.volume)) {
						mapOfSemen_collectionDTOTovolume.get(oldSemen_collectionDTO.volume).remove(oldSemen_collectionDTO);
					}
					if(mapOfSemen_collectionDTOTovolume.get(oldSemen_collectionDTO.volume).isEmpty()) {
						mapOfSemen_collectionDTOTovolume.remove(oldSemen_collectionDTO.volume);
					}
					
					if(mapOfSemen_collectionDTOTodensity.containsKey(oldSemen_collectionDTO.density)) {
						mapOfSemen_collectionDTOTodensity.get(oldSemen_collectionDTO.density).remove(oldSemen_collectionDTO);
					}
					if(mapOfSemen_collectionDTOTodensity.get(oldSemen_collectionDTO.density).isEmpty()) {
						mapOfSemen_collectionDTOTodensity.remove(oldSemen_collectionDTO.density);
					}
					
					if(mapOfSemen_collectionDTOToprogressiveMortality.containsKey(oldSemen_collectionDTO.progressiveMortality)) {
						mapOfSemen_collectionDTOToprogressiveMortality.get(oldSemen_collectionDTO.progressiveMortality).remove(oldSemen_collectionDTO);
					}
					if(mapOfSemen_collectionDTOToprogressiveMortality.get(oldSemen_collectionDTO.progressiveMortality).isEmpty()) {
						mapOfSemen_collectionDTOToprogressiveMortality.remove(oldSemen_collectionDTO.progressiveMortality);
					}
					
					if(mapOfSemen_collectionDTOTocolorType.containsKey(oldSemen_collectionDTO.colorType)) {
						mapOfSemen_collectionDTOTocolorType.get(oldSemen_collectionDTO.colorType).remove(oldSemen_collectionDTO);
					}
					if(mapOfSemen_collectionDTOTocolorType.get(oldSemen_collectionDTO.colorType).isEmpty()) {
						mapOfSemen_collectionDTOTocolorType.remove(oldSemen_collectionDTO.colorType);
					}
					
					if(mapOfSemen_collectionDTOTotransactionDate.containsKey(oldSemen_collectionDTO.transactionDate)) {
						mapOfSemen_collectionDTOTotransactionDate.get(oldSemen_collectionDTO.transactionDate).remove(oldSemen_collectionDTO);
					}
					if(mapOfSemen_collectionDTOTotransactionDate.get(oldSemen_collectionDTO.transactionDate).isEmpty()) {
						mapOfSemen_collectionDTOTotransactionDate.remove(oldSemen_collectionDTO.transactionDate);
					}
					
					if(mapOfSemen_collectionDTOTodescription.containsKey(oldSemen_collectionDTO.description)) {
						mapOfSemen_collectionDTOTodescription.get(oldSemen_collectionDTO.description).remove(oldSemen_collectionDTO);
					}
					if(mapOfSemen_collectionDTOTodescription.get(oldSemen_collectionDTO.description).isEmpty()) {
						mapOfSemen_collectionDTOTodescription.remove(oldSemen_collectionDTO.description);
					}
					
					
				}
				if(semen_collectionDTO.isDeleted == false) 
				{
					
					mapOfSemen_collectionDTOToiD.put(semen_collectionDTO.iD, semen_collectionDTO);
				
					if( ! mapOfSemen_collectionDTOTobullType.containsKey(semen_collectionDTO.bullType)) {
						mapOfSemen_collectionDTOTobullType.put(semen_collectionDTO.bullType, new HashSet<>());
					}
					mapOfSemen_collectionDTOTobullType.get(semen_collectionDTO.bullType).add(semen_collectionDTO);
					
					if( ! mapOfSemen_collectionDTOTonoOfDose.containsKey(semen_collectionDTO.noOfDose)) {
						mapOfSemen_collectionDTOTonoOfDose.put(semen_collectionDTO.noOfDose, new HashSet<>());
					}
					mapOfSemen_collectionDTOTonoOfDose.get(semen_collectionDTO.noOfDose).add(semen_collectionDTO);
					
					if( ! mapOfSemen_collectionDTOTovolume.containsKey(semen_collectionDTO.volume)) {
						mapOfSemen_collectionDTOTovolume.put(semen_collectionDTO.volume, new HashSet<>());
					}
					mapOfSemen_collectionDTOTovolume.get(semen_collectionDTO.volume).add(semen_collectionDTO);
					
					if( ! mapOfSemen_collectionDTOTodensity.containsKey(semen_collectionDTO.density)) {
						mapOfSemen_collectionDTOTodensity.put(semen_collectionDTO.density, new HashSet<>());
					}
					mapOfSemen_collectionDTOTodensity.get(semen_collectionDTO.density).add(semen_collectionDTO);
					
					if( ! mapOfSemen_collectionDTOToprogressiveMortality.containsKey(semen_collectionDTO.progressiveMortality)) {
						mapOfSemen_collectionDTOToprogressiveMortality.put(semen_collectionDTO.progressiveMortality, new HashSet<>());
					}
					mapOfSemen_collectionDTOToprogressiveMortality.get(semen_collectionDTO.progressiveMortality).add(semen_collectionDTO);
					
					if( ! mapOfSemen_collectionDTOTocolorType.containsKey(semen_collectionDTO.colorType)) {
						mapOfSemen_collectionDTOTocolorType.put(semen_collectionDTO.colorType, new HashSet<>());
					}
					mapOfSemen_collectionDTOTocolorType.get(semen_collectionDTO.colorType).add(semen_collectionDTO);
					
					if( ! mapOfSemen_collectionDTOTotransactionDate.containsKey(semen_collectionDTO.transactionDate)) {
						mapOfSemen_collectionDTOTotransactionDate.put(semen_collectionDTO.transactionDate, new HashSet<>());
					}
					mapOfSemen_collectionDTOTotransactionDate.get(semen_collectionDTO.transactionDate).add(semen_collectionDTO);
					
					if( ! mapOfSemen_collectionDTOTodescription.containsKey(semen_collectionDTO.description)) {
						mapOfSemen_collectionDTOTodescription.put(semen_collectionDTO.description, new HashSet<>());
					}
					mapOfSemen_collectionDTOTodescription.get(semen_collectionDTO.description).add(semen_collectionDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Semen_collectionDTO> getSemen_collectionList() {
		List <Semen_collectionDTO> semen_collections = new ArrayList<Semen_collectionDTO>(this.mapOfSemen_collectionDTOToiD.values());
		return semen_collections;
	}
	
	
	public Semen_collectionDTO getSemen_collectionDTOByID( long ID){
		return mapOfSemen_collectionDTOToiD.get(ID);
	}
	
	
	public List<Semen_collectionDTO> getSemen_collectionDTOBybull_type(int bull_type) {
		return new ArrayList<>( mapOfSemen_collectionDTOTobullType.getOrDefault(bull_type,new HashSet<>()));
	}
	
	
	public List<Semen_collectionDTO> getSemen_collectionDTOByno_of_dose(int no_of_dose) {
		return new ArrayList<>( mapOfSemen_collectionDTOTonoOfDose.getOrDefault(no_of_dose,new HashSet<>()));
	}
	
	
	public List<Semen_collectionDTO> getSemen_collectionDTOByvolume(int volume) {
		return new ArrayList<>( mapOfSemen_collectionDTOTovolume.getOrDefault(volume,new HashSet<>()));
	}
	
	
	public List<Semen_collectionDTO> getSemen_collectionDTOBydensity(int density) {
		return new ArrayList<>( mapOfSemen_collectionDTOTodensity.getOrDefault(density,new HashSet<>()));
	}
	
	
	public List<Semen_collectionDTO> getSemen_collectionDTOByprogressive_mortality(int progressive_mortality) {
		return new ArrayList<>( mapOfSemen_collectionDTOToprogressiveMortality.getOrDefault(progressive_mortality,new HashSet<>()));
	}
	
	
	public List<Semen_collectionDTO> getSemen_collectionDTOBycolor_type(int color_type) {
		return new ArrayList<>( mapOfSemen_collectionDTOTocolorType.getOrDefault(color_type,new HashSet<>()));
	}
	
	
	public List<Semen_collectionDTO> getSemen_collectionDTOBytransaction_date(long transaction_date) {
		return new ArrayList<>( mapOfSemen_collectionDTOTotransactionDate.getOrDefault(transaction_date,new HashSet<>()));
	}
	
	
	public List<Semen_collectionDTO> getSemen_collectionDTOBydescription(String description) {
		return new ArrayList<>( mapOfSemen_collectionDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "semen_collection";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


