package semen_requisition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Semen_requisitionRepository implements Repository {
	Semen_requisitionDAO semen_requisitionDAO = new Semen_requisitionDAO();
	
	
	static Logger logger = Logger.getLogger(Semen_requisitionRepository.class);
	Map<Long, Semen_requisitionDTO>mapOfSemen_requisitionDTOToiD;
	Map<Integer, Set<Semen_requisitionDTO> >mapOfSemen_requisitionDTOTogroupId;
	Map<Integer, Set<Semen_requisitionDTO> >mapOfSemen_requisitionDTOTocentreType;
	Map<Integer, Set<Semen_requisitionDTO> >mapOfSemen_requisitionDTOTobreedType;
	Map<Integer, Set<Semen_requisitionDTO> >mapOfSemen_requisitionDTOTonoOfDose;
	Map<Long, Set<Semen_requisitionDTO> >mapOfSemen_requisitionDTOTorequisitionDate;
	Map<Boolean, Set<Semen_requisitionDTO> >mapOfSemen_requisitionDTOToisDelivered;
	Map<String, Set<Semen_requisitionDTO> >mapOfSemen_requisitionDTOTodescription;


	static Semen_requisitionRepository instance = null;  
	private Semen_requisitionRepository(){
		mapOfSemen_requisitionDTOToiD = new ConcurrentHashMap<>();
		mapOfSemen_requisitionDTOTogroupId = new ConcurrentHashMap<>();
		mapOfSemen_requisitionDTOTocentreType = new ConcurrentHashMap<>();
		mapOfSemen_requisitionDTOTobreedType = new ConcurrentHashMap<>();
		mapOfSemen_requisitionDTOTonoOfDose = new ConcurrentHashMap<>();
		mapOfSemen_requisitionDTOTorequisitionDate = new ConcurrentHashMap<>();
		mapOfSemen_requisitionDTOToisDelivered = new ConcurrentHashMap<>();
		mapOfSemen_requisitionDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Semen_requisitionRepository getInstance(){
		if (instance == null){
			instance = new Semen_requisitionRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Semen_requisitionDTO> semen_requisitionDTOs = semen_requisitionDAO.getAllSemen_requisition(reloadAll);
			for(Semen_requisitionDTO semen_requisitionDTO : semen_requisitionDTOs) {
				Semen_requisitionDTO oldSemen_requisitionDTO = getSemen_requisitionDTOByID(semen_requisitionDTO.iD);
				if( oldSemen_requisitionDTO != null ) {
					mapOfSemen_requisitionDTOToiD.remove(oldSemen_requisitionDTO.iD);
				
					if(mapOfSemen_requisitionDTOTogroupId.containsKey(oldSemen_requisitionDTO.groupId)) {
						mapOfSemen_requisitionDTOTogroupId.get(oldSemen_requisitionDTO.groupId).remove(oldSemen_requisitionDTO);
					}
					if(mapOfSemen_requisitionDTOTogroupId.get(oldSemen_requisitionDTO.groupId).isEmpty()) {
						mapOfSemen_requisitionDTOTogroupId.remove(oldSemen_requisitionDTO.groupId);
					}
					
					if(mapOfSemen_requisitionDTOTocentreType.containsKey(oldSemen_requisitionDTO.centreType)) {
						mapOfSemen_requisitionDTOTocentreType.get(oldSemen_requisitionDTO.centreType).remove(oldSemen_requisitionDTO);
					}
					if(mapOfSemen_requisitionDTOTocentreType.get(oldSemen_requisitionDTO.centreType).isEmpty()) {
						mapOfSemen_requisitionDTOTocentreType.remove(oldSemen_requisitionDTO.centreType);
					}
					
					if(mapOfSemen_requisitionDTOTobreedType.containsKey(oldSemen_requisitionDTO.breedType)) {
						mapOfSemen_requisitionDTOTobreedType.get(oldSemen_requisitionDTO.breedType).remove(oldSemen_requisitionDTO);
					}
					if(mapOfSemen_requisitionDTOTobreedType.get(oldSemen_requisitionDTO.breedType).isEmpty()) {
						mapOfSemen_requisitionDTOTobreedType.remove(oldSemen_requisitionDTO.breedType);
					}
					
					if(mapOfSemen_requisitionDTOTonoOfDose.containsKey(oldSemen_requisitionDTO.noOfDose)) {
						mapOfSemen_requisitionDTOTonoOfDose.get(oldSemen_requisitionDTO.noOfDose).remove(oldSemen_requisitionDTO);
					}
					if(mapOfSemen_requisitionDTOTonoOfDose.get(oldSemen_requisitionDTO.noOfDose).isEmpty()) {
						mapOfSemen_requisitionDTOTonoOfDose.remove(oldSemen_requisitionDTO.noOfDose);
					}
					
					if(mapOfSemen_requisitionDTOTorequisitionDate.containsKey(oldSemen_requisitionDTO.requisitionDate)) {
						mapOfSemen_requisitionDTOTorequisitionDate.get(oldSemen_requisitionDTO.requisitionDate).remove(oldSemen_requisitionDTO);
					}
					if(mapOfSemen_requisitionDTOTorequisitionDate.get(oldSemen_requisitionDTO.requisitionDate).isEmpty()) {
						mapOfSemen_requisitionDTOTorequisitionDate.remove(oldSemen_requisitionDTO.requisitionDate);
					}
					
					if(mapOfSemen_requisitionDTOToisDelivered.containsKey(oldSemen_requisitionDTO.isDelivered)) {
						mapOfSemen_requisitionDTOToisDelivered.get(oldSemen_requisitionDTO.isDelivered).remove(oldSemen_requisitionDTO);
					}
					if(mapOfSemen_requisitionDTOToisDelivered.get(oldSemen_requisitionDTO.isDelivered).isEmpty()) {
						mapOfSemen_requisitionDTOToisDelivered.remove(oldSemen_requisitionDTO.isDelivered);
					}
					
					if(mapOfSemen_requisitionDTOTodescription.containsKey(oldSemen_requisitionDTO.description)) {
						mapOfSemen_requisitionDTOTodescription.get(oldSemen_requisitionDTO.description).remove(oldSemen_requisitionDTO);
					}
					if(mapOfSemen_requisitionDTOTodescription.get(oldSemen_requisitionDTO.description).isEmpty()) {
						mapOfSemen_requisitionDTOTodescription.remove(oldSemen_requisitionDTO.description);
					}
					
					
				}
				if(semen_requisitionDTO.isDeleted == false) 
				{
					
					mapOfSemen_requisitionDTOToiD.put(semen_requisitionDTO.iD, semen_requisitionDTO);
				
					if( ! mapOfSemen_requisitionDTOTogroupId.containsKey(semen_requisitionDTO.groupId)) {
						mapOfSemen_requisitionDTOTogroupId.put(semen_requisitionDTO.groupId, new HashSet<>());
					}
					mapOfSemen_requisitionDTOTogroupId.get(semen_requisitionDTO.groupId).add(semen_requisitionDTO);
					
					if( ! mapOfSemen_requisitionDTOTocentreType.containsKey(semen_requisitionDTO.centreType)) {
						mapOfSemen_requisitionDTOTocentreType.put(semen_requisitionDTO.centreType, new HashSet<>());
					}
					mapOfSemen_requisitionDTOTocentreType.get(semen_requisitionDTO.centreType).add(semen_requisitionDTO);
					
					if( ! mapOfSemen_requisitionDTOTobreedType.containsKey(semen_requisitionDTO.breedType)) {
						mapOfSemen_requisitionDTOTobreedType.put(semen_requisitionDTO.breedType, new HashSet<>());
					}
					mapOfSemen_requisitionDTOTobreedType.get(semen_requisitionDTO.breedType).add(semen_requisitionDTO);
					
					if( ! mapOfSemen_requisitionDTOTonoOfDose.containsKey(semen_requisitionDTO.noOfDose)) {
						mapOfSemen_requisitionDTOTonoOfDose.put(semen_requisitionDTO.noOfDose, new HashSet<>());
					}
					mapOfSemen_requisitionDTOTonoOfDose.get(semen_requisitionDTO.noOfDose).add(semen_requisitionDTO);
					
					if( ! mapOfSemen_requisitionDTOTorequisitionDate.containsKey(semen_requisitionDTO.requisitionDate)) {
						mapOfSemen_requisitionDTOTorequisitionDate.put(semen_requisitionDTO.requisitionDate, new HashSet<>());
					}
					mapOfSemen_requisitionDTOTorequisitionDate.get(semen_requisitionDTO.requisitionDate).add(semen_requisitionDTO);
					
					if( ! mapOfSemen_requisitionDTOToisDelivered.containsKey(semen_requisitionDTO.isDelivered)) {
						mapOfSemen_requisitionDTOToisDelivered.put(semen_requisitionDTO.isDelivered, new HashSet<>());
					}
					mapOfSemen_requisitionDTOToisDelivered.get(semen_requisitionDTO.isDelivered).add(semen_requisitionDTO);
					
					if( ! mapOfSemen_requisitionDTOTodescription.containsKey(semen_requisitionDTO.description)) {
						mapOfSemen_requisitionDTOTodescription.put(semen_requisitionDTO.description, new HashSet<>());
					}
					mapOfSemen_requisitionDTOTodescription.get(semen_requisitionDTO.description).add(semen_requisitionDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Semen_requisitionDTO> getSemen_requisitionList() {
		List <Semen_requisitionDTO> semen_requisitions = new ArrayList<Semen_requisitionDTO>(this.mapOfSemen_requisitionDTOToiD.values());
		return semen_requisitions;
	}
	
	
	public Semen_requisitionDTO getSemen_requisitionDTOByID( long ID){
		return mapOfSemen_requisitionDTOToiD.get(ID);
	}
	
	
	public List<Semen_requisitionDTO> getSemen_requisitionDTOBygroup_id(int group_id) {
		return new ArrayList<>( mapOfSemen_requisitionDTOTogroupId.getOrDefault(group_id,new HashSet<>()));
	}
	
	
	public List<Semen_requisitionDTO> getSemen_requisitionDTOBycentre_type(int centre_type) {
		return new ArrayList<>( mapOfSemen_requisitionDTOTocentreType.getOrDefault(centre_type,new HashSet<>()));
	}
	
	
	public List<Semen_requisitionDTO> getSemen_requisitionDTOBybreed_type(int breed_type) {
		return new ArrayList<>( mapOfSemen_requisitionDTOTobreedType.getOrDefault(breed_type,new HashSet<>()));
	}
	
	
	public List<Semen_requisitionDTO> getSemen_requisitionDTOByno_of_dose(int no_of_dose) {
		return new ArrayList<>( mapOfSemen_requisitionDTOTonoOfDose.getOrDefault(no_of_dose,new HashSet<>()));
	}
	
	
	public List<Semen_requisitionDTO> getSemen_requisitionDTOByrequisition_date(long requisition_date) {
		return new ArrayList<>( mapOfSemen_requisitionDTOTorequisitionDate.getOrDefault(requisition_date,new HashSet<>()));
	}
	
	
	public List<Semen_requisitionDTO> getSemen_requisitionDTOByisDelivered(boolean isDelivered) {
		return new ArrayList<>( mapOfSemen_requisitionDTOToisDelivered.getOrDefault(isDelivered,new HashSet<>()));
	}
	
	
	public List<Semen_requisitionDTO> getSemen_requisitionDTOBydescription(String description) {
		return new ArrayList<>( mapOfSemen_requisitionDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "semen_requisition";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


