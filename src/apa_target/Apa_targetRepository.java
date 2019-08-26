package apa_target;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Apa_targetRepository implements Repository {
	Apa_targetDAO apa_targetDAO = new Apa_targetDAO();
	
	
	static Logger logger = Logger.getLogger(Apa_targetRepository.class);
	Map<Long, Apa_targetDTO>mapOfApa_targetDTOToiD;
	Map<Integer, Set<Apa_targetDTO> >mapOfApa_targetDTOTosemenCollection;
	Map<Integer, Set<Apa_targetDTO> >mapOfApa_targetDTOToartificialInsemenation;
	Map<Integer, Set<Apa_targetDTO> >mapOfApa_targetDTOToprogeny;
	Map<Integer, Set<Apa_targetDTO> >mapOfApa_targetDTOTocandidateBullProduction;
	Map<String, Set<Apa_targetDTO> >mapOfApa_targetDTOTodescription;
	Map<Long, Set<Apa_targetDTO> >mapOfApa_targetDTOToentryDate;


	static Apa_targetRepository instance = null;  
	private Apa_targetRepository(){
		mapOfApa_targetDTOToiD = new ConcurrentHashMap<>();
		mapOfApa_targetDTOTosemenCollection = new ConcurrentHashMap<>();
		mapOfApa_targetDTOToartificialInsemenation = new ConcurrentHashMap<>();
		mapOfApa_targetDTOToprogeny = new ConcurrentHashMap<>();
		mapOfApa_targetDTOTocandidateBullProduction = new ConcurrentHashMap<>();
		mapOfApa_targetDTOTodescription = new ConcurrentHashMap<>();
		mapOfApa_targetDTOToentryDate = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Apa_targetRepository getInstance(){
		if (instance == null){
			instance = new Apa_targetRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Apa_targetDTO> apa_targetDTOs = apa_targetDAO.getAllApa_target(reloadAll);
			for(Apa_targetDTO apa_targetDTO : apa_targetDTOs) {
				Apa_targetDTO oldApa_targetDTO = getApa_targetDTOByID(apa_targetDTO.iD);
				if( oldApa_targetDTO != null ) {
					mapOfApa_targetDTOToiD.remove(oldApa_targetDTO.iD);
				
					if(mapOfApa_targetDTOTosemenCollection.containsKey(oldApa_targetDTO.semenCollection)) {
						mapOfApa_targetDTOTosemenCollection.get(oldApa_targetDTO.semenCollection).remove(oldApa_targetDTO);
					}
					if(mapOfApa_targetDTOTosemenCollection.get(oldApa_targetDTO.semenCollection).isEmpty()) {
						mapOfApa_targetDTOTosemenCollection.remove(oldApa_targetDTO.semenCollection);
					}
					
					if(mapOfApa_targetDTOToartificialInsemenation.containsKey(oldApa_targetDTO.artificialInsemenation)) {
						mapOfApa_targetDTOToartificialInsemenation.get(oldApa_targetDTO.artificialInsemenation).remove(oldApa_targetDTO);
					}
					if(mapOfApa_targetDTOToartificialInsemenation.get(oldApa_targetDTO.artificialInsemenation).isEmpty()) {
						mapOfApa_targetDTOToartificialInsemenation.remove(oldApa_targetDTO.artificialInsemenation);
					}
					
					if(mapOfApa_targetDTOToprogeny.containsKey(oldApa_targetDTO.progeny)) {
						mapOfApa_targetDTOToprogeny.get(oldApa_targetDTO.progeny).remove(oldApa_targetDTO);
					}
					if(mapOfApa_targetDTOToprogeny.get(oldApa_targetDTO.progeny).isEmpty()) {
						mapOfApa_targetDTOToprogeny.remove(oldApa_targetDTO.progeny);
					}
					
					if(mapOfApa_targetDTOTocandidateBullProduction.containsKey(oldApa_targetDTO.candidateBullProduction)) {
						mapOfApa_targetDTOTocandidateBullProduction.get(oldApa_targetDTO.candidateBullProduction).remove(oldApa_targetDTO);
					}
					if(mapOfApa_targetDTOTocandidateBullProduction.get(oldApa_targetDTO.candidateBullProduction).isEmpty()) {
						mapOfApa_targetDTOTocandidateBullProduction.remove(oldApa_targetDTO.candidateBullProduction);
					}
					
					if(mapOfApa_targetDTOTodescription.containsKey(oldApa_targetDTO.description)) {
						mapOfApa_targetDTOTodescription.get(oldApa_targetDTO.description).remove(oldApa_targetDTO);
					}
					if(mapOfApa_targetDTOTodescription.get(oldApa_targetDTO.description).isEmpty()) {
						mapOfApa_targetDTOTodescription.remove(oldApa_targetDTO.description);
					}
					
					if(mapOfApa_targetDTOToentryDate.containsKey(oldApa_targetDTO.entryDate)) {
						mapOfApa_targetDTOToentryDate.get(oldApa_targetDTO.entryDate).remove(oldApa_targetDTO);
					}
					if(mapOfApa_targetDTOToentryDate.get(oldApa_targetDTO.entryDate).isEmpty()) {
						mapOfApa_targetDTOToentryDate.remove(oldApa_targetDTO.entryDate);
					}
					
					
				}
				if(apa_targetDTO.isDeleted == false) 
				{
					
					mapOfApa_targetDTOToiD.put(apa_targetDTO.iD, apa_targetDTO);
				
					if( ! mapOfApa_targetDTOTosemenCollection.containsKey(apa_targetDTO.semenCollection)) {
						mapOfApa_targetDTOTosemenCollection.put(apa_targetDTO.semenCollection, new HashSet<>());
					}
					mapOfApa_targetDTOTosemenCollection.get(apa_targetDTO.semenCollection).add(apa_targetDTO);
					
					if( ! mapOfApa_targetDTOToartificialInsemenation.containsKey(apa_targetDTO.artificialInsemenation)) {
						mapOfApa_targetDTOToartificialInsemenation.put(apa_targetDTO.artificialInsemenation, new HashSet<>());
					}
					mapOfApa_targetDTOToartificialInsemenation.get(apa_targetDTO.artificialInsemenation).add(apa_targetDTO);
					
					if( ! mapOfApa_targetDTOToprogeny.containsKey(apa_targetDTO.progeny)) {
						mapOfApa_targetDTOToprogeny.put(apa_targetDTO.progeny, new HashSet<>());
					}
					mapOfApa_targetDTOToprogeny.get(apa_targetDTO.progeny).add(apa_targetDTO);
					
					if( ! mapOfApa_targetDTOTocandidateBullProduction.containsKey(apa_targetDTO.candidateBullProduction)) {
						mapOfApa_targetDTOTocandidateBullProduction.put(apa_targetDTO.candidateBullProduction, new HashSet<>());
					}
					mapOfApa_targetDTOTocandidateBullProduction.get(apa_targetDTO.candidateBullProduction).add(apa_targetDTO);
					
					if( ! mapOfApa_targetDTOTodescription.containsKey(apa_targetDTO.description)) {
						mapOfApa_targetDTOTodescription.put(apa_targetDTO.description, new HashSet<>());
					}
					mapOfApa_targetDTOTodescription.get(apa_targetDTO.description).add(apa_targetDTO);
					
					if( ! mapOfApa_targetDTOToentryDate.containsKey(apa_targetDTO.entryDate)) {
						mapOfApa_targetDTOToentryDate.put(apa_targetDTO.entryDate, new HashSet<>());
					}
					mapOfApa_targetDTOToentryDate.get(apa_targetDTO.entryDate).add(apa_targetDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Apa_targetDTO> getApa_targetList() {
		List <Apa_targetDTO> apa_targets = new ArrayList<Apa_targetDTO>(this.mapOfApa_targetDTOToiD.values());
		return apa_targets;
	}
	
	
	public Apa_targetDTO getApa_targetDTOByID( long ID){
		return mapOfApa_targetDTOToiD.get(ID);
	}
	
	
	public List<Apa_targetDTO> getApa_targetDTOBysemen_collection(int semen_collection) {
		return new ArrayList<>( mapOfApa_targetDTOTosemenCollection.getOrDefault(semen_collection,new HashSet<>()));
	}
	
	
	public List<Apa_targetDTO> getApa_targetDTOByartificial_insemenation(int artificial_insemenation) {
		return new ArrayList<>( mapOfApa_targetDTOToartificialInsemenation.getOrDefault(artificial_insemenation,new HashSet<>()));
	}
	
	
	public List<Apa_targetDTO> getApa_targetDTOByprogeny(int progeny) {
		return new ArrayList<>( mapOfApa_targetDTOToprogeny.getOrDefault(progeny,new HashSet<>()));
	}
	
	
	public List<Apa_targetDTO> getApa_targetDTOBycandidate_bull_production(int candidate_bull_production) {
		return new ArrayList<>( mapOfApa_targetDTOTocandidateBullProduction.getOrDefault(candidate_bull_production,new HashSet<>()));
	}
	
	
	public List<Apa_targetDTO> getApa_targetDTOBydescription(String description) {
		return new ArrayList<>( mapOfApa_targetDTOTodescription.getOrDefault(description,new HashSet<>()));
	}
	
	
	public List<Apa_targetDTO> getApa_targetDTOByentry_date(long entry_date) {
		return new ArrayList<>( mapOfApa_targetDTOToentryDate.getOrDefault(entry_date,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "apa_target";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


