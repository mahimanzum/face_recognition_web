package candidate_bull_production;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Candidate_bull_productionRepository implements Repository {
	Candidate_bull_productionDAO candidate_bull_productionDAO = new Candidate_bull_productionDAO();
	
	
	static Logger logger = Logger.getLogger(Candidate_bull_productionRepository.class);
	Map<Long, Candidate_bull_productionDTO>mapOfCandidate_bull_productionDTOToiD;
	Map<Long, Set<Candidate_bull_productionDTO> >mapOfCandidate_bull_productionDTOToproductionDate;
	Map<Integer, Set<Candidate_bull_productionDTO> >mapOfCandidate_bull_productionDTOTonumberOfCandidateBulls;
	Map<Integer, Set<Candidate_bull_productionDTO> >mapOfCandidate_bull_productionDTOTosourceType;
	Map<String, Set<Candidate_bull_productionDTO> >mapOfCandidate_bull_productionDTOTodescription;


	static Candidate_bull_productionRepository instance = null;  
	private Candidate_bull_productionRepository(){
		mapOfCandidate_bull_productionDTOToiD = new ConcurrentHashMap<>();
		mapOfCandidate_bull_productionDTOToproductionDate = new ConcurrentHashMap<>();
		mapOfCandidate_bull_productionDTOTonumberOfCandidateBulls = new ConcurrentHashMap<>();
		mapOfCandidate_bull_productionDTOTosourceType = new ConcurrentHashMap<>();
		mapOfCandidate_bull_productionDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Candidate_bull_productionRepository getInstance(){
		if (instance == null){
			instance = new Candidate_bull_productionRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Candidate_bull_productionDTO> candidate_bull_productionDTOs = candidate_bull_productionDAO.getAllCandidate_bull_production(reloadAll);
			for(Candidate_bull_productionDTO candidate_bull_productionDTO : candidate_bull_productionDTOs) {
				Candidate_bull_productionDTO oldCandidate_bull_productionDTO = getCandidate_bull_productionDTOByID(candidate_bull_productionDTO.iD);
				if( oldCandidate_bull_productionDTO != null ) {
					mapOfCandidate_bull_productionDTOToiD.remove(oldCandidate_bull_productionDTO.iD);
				
					if(mapOfCandidate_bull_productionDTOToproductionDate.containsKey(oldCandidate_bull_productionDTO.productionDate)) {
						mapOfCandidate_bull_productionDTOToproductionDate.get(oldCandidate_bull_productionDTO.productionDate).remove(oldCandidate_bull_productionDTO);
					}
					if(mapOfCandidate_bull_productionDTOToproductionDate.get(oldCandidate_bull_productionDTO.productionDate).isEmpty()) {
						mapOfCandidate_bull_productionDTOToproductionDate.remove(oldCandidate_bull_productionDTO.productionDate);
					}
					
					if(mapOfCandidate_bull_productionDTOTonumberOfCandidateBulls.containsKey(oldCandidate_bull_productionDTO.numberOfCandidateBulls)) {
						mapOfCandidate_bull_productionDTOTonumberOfCandidateBulls.get(oldCandidate_bull_productionDTO.numberOfCandidateBulls).remove(oldCandidate_bull_productionDTO);
					}
					if(mapOfCandidate_bull_productionDTOTonumberOfCandidateBulls.get(oldCandidate_bull_productionDTO.numberOfCandidateBulls).isEmpty()) {
						mapOfCandidate_bull_productionDTOTonumberOfCandidateBulls.remove(oldCandidate_bull_productionDTO.numberOfCandidateBulls);
					}
					
					if(mapOfCandidate_bull_productionDTOTosourceType.containsKey(oldCandidate_bull_productionDTO.sourceType)) {
						mapOfCandidate_bull_productionDTOTosourceType.get(oldCandidate_bull_productionDTO.sourceType).remove(oldCandidate_bull_productionDTO);
					}
					if(mapOfCandidate_bull_productionDTOTosourceType.get(oldCandidate_bull_productionDTO.sourceType).isEmpty()) {
						mapOfCandidate_bull_productionDTOTosourceType.remove(oldCandidate_bull_productionDTO.sourceType);
					}
					
					if(mapOfCandidate_bull_productionDTOTodescription.containsKey(oldCandidate_bull_productionDTO.description)) {
						mapOfCandidate_bull_productionDTOTodescription.get(oldCandidate_bull_productionDTO.description).remove(oldCandidate_bull_productionDTO);
					}
					if(mapOfCandidate_bull_productionDTOTodescription.get(oldCandidate_bull_productionDTO.description).isEmpty()) {
						mapOfCandidate_bull_productionDTOTodescription.remove(oldCandidate_bull_productionDTO.description);
					}
					
					
				}
				if(candidate_bull_productionDTO.isDeleted == false) 
				{
					
					mapOfCandidate_bull_productionDTOToiD.put(candidate_bull_productionDTO.iD, candidate_bull_productionDTO);
				
					if( ! mapOfCandidate_bull_productionDTOToproductionDate.containsKey(candidate_bull_productionDTO.productionDate)) {
						mapOfCandidate_bull_productionDTOToproductionDate.put(candidate_bull_productionDTO.productionDate, new HashSet<>());
					}
					mapOfCandidate_bull_productionDTOToproductionDate.get(candidate_bull_productionDTO.productionDate).add(candidate_bull_productionDTO);
					
					if( ! mapOfCandidate_bull_productionDTOTonumberOfCandidateBulls.containsKey(candidate_bull_productionDTO.numberOfCandidateBulls)) {
						mapOfCandidate_bull_productionDTOTonumberOfCandidateBulls.put(candidate_bull_productionDTO.numberOfCandidateBulls, new HashSet<>());
					}
					mapOfCandidate_bull_productionDTOTonumberOfCandidateBulls.get(candidate_bull_productionDTO.numberOfCandidateBulls).add(candidate_bull_productionDTO);
					
					if( ! mapOfCandidate_bull_productionDTOTosourceType.containsKey(candidate_bull_productionDTO.sourceType)) {
						mapOfCandidate_bull_productionDTOTosourceType.put(candidate_bull_productionDTO.sourceType, new HashSet<>());
					}
					mapOfCandidate_bull_productionDTOTosourceType.get(candidate_bull_productionDTO.sourceType).add(candidate_bull_productionDTO);
					
					if( ! mapOfCandidate_bull_productionDTOTodescription.containsKey(candidate_bull_productionDTO.description)) {
						mapOfCandidate_bull_productionDTOTodescription.put(candidate_bull_productionDTO.description, new HashSet<>());
					}
					mapOfCandidate_bull_productionDTOTodescription.get(candidate_bull_productionDTO.description).add(candidate_bull_productionDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Candidate_bull_productionDTO> getCandidate_bull_productionList() {
		List <Candidate_bull_productionDTO> candidate_bull_productions = new ArrayList<Candidate_bull_productionDTO>(this.mapOfCandidate_bull_productionDTOToiD.values());
		return candidate_bull_productions;
	}
	
	
	public Candidate_bull_productionDTO getCandidate_bull_productionDTOByID( long ID){
		return mapOfCandidate_bull_productionDTOToiD.get(ID);
	}
	
	
	public List<Candidate_bull_productionDTO> getCandidate_bull_productionDTOByproduction_date(long production_date) {
		return new ArrayList<>( mapOfCandidate_bull_productionDTOToproductionDate.getOrDefault(production_date,new HashSet<>()));
	}
	
	
	public List<Candidate_bull_productionDTO> getCandidate_bull_productionDTOBynumber_of_candidate_bulls(int number_of_candidate_bulls) {
		return new ArrayList<>( mapOfCandidate_bull_productionDTOTonumberOfCandidateBulls.getOrDefault(number_of_candidate_bulls,new HashSet<>()));
	}
	
	
	public List<Candidate_bull_productionDTO> getCandidate_bull_productionDTOBysource_type(int source_type) {
		return new ArrayList<>( mapOfCandidate_bull_productionDTOTosourceType.getOrDefault(source_type,new HashSet<>()));
	}
	
	
	public List<Candidate_bull_productionDTO> getCandidate_bull_productionDTOBydescription(String description) {
		return new ArrayList<>( mapOfCandidate_bull_productionDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "candidate_bull_production";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


