package semen_distribution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Semen_distributionRepository implements Repository {
	Semen_distributionDAO semen_distributionDAO = new Semen_distributionDAO();
	
	
	static Logger logger = Logger.getLogger(Semen_distributionRepository.class);
	Map<Long, Semen_distributionDTO>mapOfSemen_distributionDTOToiD;
	Map<Integer, Set<Semen_distributionDTO> >mapOfSemen_distributionDTOTobullType;
	Map<Integer, Set<Semen_distributionDTO> >mapOfSemen_distributionDTOTonoOfDose;
	Map<Long, Set<Semen_distributionDTO> >mapOfSemen_distributionDTOTorequisitionId;
	Map<Integer, Set<Semen_distributionDTO> >mapOfSemen_distributionDTOTogroupId;
	Map<Long, Set<Semen_distributionDTO> >mapOfSemen_distributionDTOTotransactionDate;
	Map<String, Set<Semen_distributionDTO> >mapOfSemen_distributionDTOTodescription;


	static Semen_distributionRepository instance = null;  
	private Semen_distributionRepository(){
		mapOfSemen_distributionDTOToiD = new ConcurrentHashMap<>();
		mapOfSemen_distributionDTOTobullType = new ConcurrentHashMap<>();
		mapOfSemen_distributionDTOTonoOfDose = new ConcurrentHashMap<>();
		mapOfSemen_distributionDTOTorequisitionId = new ConcurrentHashMap<>();
		mapOfSemen_distributionDTOTogroupId = new ConcurrentHashMap<>();
		mapOfSemen_distributionDTOTotransactionDate = new ConcurrentHashMap<>();
		mapOfSemen_distributionDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Semen_distributionRepository getInstance(){
		if (instance == null){
			instance = new Semen_distributionRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Semen_distributionDTO> semen_distributionDTOs = semen_distributionDAO.getAllSemen_distribution(reloadAll);
			for(Semen_distributionDTO semen_distributionDTO : semen_distributionDTOs) {
				Semen_distributionDTO oldSemen_distributionDTO = getSemen_distributionDTOByID(semen_distributionDTO.iD);
				if( oldSemen_distributionDTO != null ) {
					mapOfSemen_distributionDTOToiD.remove(oldSemen_distributionDTO.iD);
				
					if(mapOfSemen_distributionDTOTobullType.containsKey(oldSemen_distributionDTO.bullType)) {
						mapOfSemen_distributionDTOTobullType.get(oldSemen_distributionDTO.bullType).remove(oldSemen_distributionDTO);
					}
					if(mapOfSemen_distributionDTOTobullType.get(oldSemen_distributionDTO.bullType).isEmpty()) {
						mapOfSemen_distributionDTOTobullType.remove(oldSemen_distributionDTO.bullType);
					}
					
					if(mapOfSemen_distributionDTOTonoOfDose.containsKey(oldSemen_distributionDTO.noOfDose)) {
						mapOfSemen_distributionDTOTonoOfDose.get(oldSemen_distributionDTO.noOfDose).remove(oldSemen_distributionDTO);
					}
					if(mapOfSemen_distributionDTOTonoOfDose.get(oldSemen_distributionDTO.noOfDose).isEmpty()) {
						mapOfSemen_distributionDTOTonoOfDose.remove(oldSemen_distributionDTO.noOfDose);
					}
					
					if(mapOfSemen_distributionDTOTorequisitionId.containsKey(oldSemen_distributionDTO.requisitionId)) {
						mapOfSemen_distributionDTOTorequisitionId.get(oldSemen_distributionDTO.requisitionId).remove(oldSemen_distributionDTO);
					}
					if(mapOfSemen_distributionDTOTorequisitionId.get(oldSemen_distributionDTO.requisitionId).isEmpty()) {
						mapOfSemen_distributionDTOTorequisitionId.remove(oldSemen_distributionDTO.requisitionId);
					}
					
					if(mapOfSemen_distributionDTOTogroupId.containsKey(oldSemen_distributionDTO.groupId)) {
						mapOfSemen_distributionDTOTogroupId.get(oldSemen_distributionDTO.groupId).remove(oldSemen_distributionDTO);
					}
					if(mapOfSemen_distributionDTOTogroupId.get(oldSemen_distributionDTO.groupId).isEmpty()) {
						mapOfSemen_distributionDTOTogroupId.remove(oldSemen_distributionDTO.groupId);
					}
					
					if(mapOfSemen_distributionDTOTotransactionDate.containsKey(oldSemen_distributionDTO.transactionDate)) {
						mapOfSemen_distributionDTOTotransactionDate.get(oldSemen_distributionDTO.transactionDate).remove(oldSemen_distributionDTO);
					}
					if(mapOfSemen_distributionDTOTotransactionDate.get(oldSemen_distributionDTO.transactionDate).isEmpty()) {
						mapOfSemen_distributionDTOTotransactionDate.remove(oldSemen_distributionDTO.transactionDate);
					}
					
					if(mapOfSemen_distributionDTOTodescription.containsKey(oldSemen_distributionDTO.description)) {
						mapOfSemen_distributionDTOTodescription.get(oldSemen_distributionDTO.description).remove(oldSemen_distributionDTO);
					}
					if(mapOfSemen_distributionDTOTodescription.get(oldSemen_distributionDTO.description).isEmpty()) {
						mapOfSemen_distributionDTOTodescription.remove(oldSemen_distributionDTO.description);
					}
					
					
				}
				if(semen_distributionDTO.isDeleted == false) 
				{
					
					mapOfSemen_distributionDTOToiD.put(semen_distributionDTO.iD, semen_distributionDTO);
				
					if( ! mapOfSemen_distributionDTOTobullType.containsKey(semen_distributionDTO.bullType)) {
						mapOfSemen_distributionDTOTobullType.put(semen_distributionDTO.bullType, new HashSet<>());
					}
					mapOfSemen_distributionDTOTobullType.get(semen_distributionDTO.bullType).add(semen_distributionDTO);
					
					if( ! mapOfSemen_distributionDTOTonoOfDose.containsKey(semen_distributionDTO.noOfDose)) {
						mapOfSemen_distributionDTOTonoOfDose.put(semen_distributionDTO.noOfDose, new HashSet<>());
					}
					mapOfSemen_distributionDTOTonoOfDose.get(semen_distributionDTO.noOfDose).add(semen_distributionDTO);
					
					if( ! mapOfSemen_distributionDTOTorequisitionId.containsKey(semen_distributionDTO.requisitionId)) {
						mapOfSemen_distributionDTOTorequisitionId.put(semen_distributionDTO.requisitionId, new HashSet<>());
					}
					mapOfSemen_distributionDTOTorequisitionId.get(semen_distributionDTO.requisitionId).add(semen_distributionDTO);
					
					if( ! mapOfSemen_distributionDTOTogroupId.containsKey(semen_distributionDTO.groupId)) {
						mapOfSemen_distributionDTOTogroupId.put(semen_distributionDTO.groupId, new HashSet<>());
					}
					mapOfSemen_distributionDTOTogroupId.get(semen_distributionDTO.groupId).add(semen_distributionDTO);
					
					if( ! mapOfSemen_distributionDTOTotransactionDate.containsKey(semen_distributionDTO.transactionDate)) {
						mapOfSemen_distributionDTOTotransactionDate.put(semen_distributionDTO.transactionDate, new HashSet<>());
					}
					mapOfSemen_distributionDTOTotransactionDate.get(semen_distributionDTO.transactionDate).add(semen_distributionDTO);
					
					if( ! mapOfSemen_distributionDTOTodescription.containsKey(semen_distributionDTO.description)) {
						mapOfSemen_distributionDTOTodescription.put(semen_distributionDTO.description, new HashSet<>());
					}
					mapOfSemen_distributionDTOTodescription.get(semen_distributionDTO.description).add(semen_distributionDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Semen_distributionDTO> getSemen_distributionList() {
		List <Semen_distributionDTO> semen_distributions = new ArrayList<Semen_distributionDTO>(this.mapOfSemen_distributionDTOToiD.values());
		return semen_distributions;
	}
	
	
	public Semen_distributionDTO getSemen_distributionDTOByID( long ID){
		return mapOfSemen_distributionDTOToiD.get(ID);
	}
	
	
	public List<Semen_distributionDTO> getSemen_distributionDTOBybull_type(int bull_type) {
		return new ArrayList<>( mapOfSemen_distributionDTOTobullType.getOrDefault(bull_type,new HashSet<>()));
	}
	
	
	public List<Semen_distributionDTO> getSemen_distributionDTOByno_of_dose(int no_of_dose) {
		return new ArrayList<>( mapOfSemen_distributionDTOTonoOfDose.getOrDefault(no_of_dose,new HashSet<>()));
	}
	
	
	public List<Semen_distributionDTO> getSemen_distributionDTOByrequisition_id(long requisition_id) {
		return new ArrayList<>( mapOfSemen_distributionDTOTorequisitionId.getOrDefault(requisition_id,new HashSet<>()));
	}
	
	
	public List<Semen_distributionDTO> getSemen_distributionDTOBygroup_id(int group_id) {
		return new ArrayList<>( mapOfSemen_distributionDTOTogroupId.getOrDefault(group_id,new HashSet<>()));
	}
	
	
	public List<Semen_distributionDTO> getSemen_distributionDTOBytransaction_date(long transaction_date) {
		return new ArrayList<>( mapOfSemen_distributionDTOTotransactionDate.getOrDefault(transaction_date,new HashSet<>()));
	}
	
	
	public List<Semen_distributionDTO> getSemen_distributionDTOBydescription(String description) {
		return new ArrayList<>( mapOfSemen_distributionDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "semen_distribution";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


