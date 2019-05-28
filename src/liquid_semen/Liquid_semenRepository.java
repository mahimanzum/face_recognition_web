package liquid_semen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Liquid_semenRepository implements Repository {
	Liquid_semenDAO liquid_semenDAO = new Liquid_semenDAO();
	
	
	static Logger logger = Logger.getLogger(Liquid_semenRepository.class);
	Map<Long, Liquid_semenDTO>mapOfLiquid_semenDTOToiD;
	Map<Integer, Set<Liquid_semenDTO> >mapOfLiquid_semenDTOTocentreType;
	Map<Integer, Set<Liquid_semenDTO> >mapOfLiquid_semenDTOTobullType;
	Map<Integer, Set<Liquid_semenDTO> >mapOfLiquid_semenDTOTonoOfDose;
	Map<Integer, Set<Liquid_semenDTO> >mapOfLiquid_semenDTOTovolume;
	Map<Integer, Set<Liquid_semenDTO> >mapOfLiquid_semenDTOTodensity;
	Map<Integer, Set<Liquid_semenDTO> >mapOfLiquid_semenDTOToprogressiveMotility;
	Map<Integer, Set<Liquid_semenDTO> >mapOfLiquid_semenDTOTocolorType;
	Map<Long, Set<Liquid_semenDTO> >mapOfLiquid_semenDTOTocollectionDistributionDate;
	Map<String, Set<Liquid_semenDTO> >mapOfLiquid_semenDTOTodescription;


	static Liquid_semenRepository instance = null;  
	private Liquid_semenRepository(){
		mapOfLiquid_semenDTOToiD = new ConcurrentHashMap<>();
		mapOfLiquid_semenDTOTocentreType = new ConcurrentHashMap<>();
		mapOfLiquid_semenDTOTobullType = new ConcurrentHashMap<>();
		mapOfLiquid_semenDTOTonoOfDose = new ConcurrentHashMap<>();
		mapOfLiquid_semenDTOTovolume = new ConcurrentHashMap<>();
		mapOfLiquid_semenDTOTodensity = new ConcurrentHashMap<>();
		mapOfLiquid_semenDTOToprogressiveMotility = new ConcurrentHashMap<>();
		mapOfLiquid_semenDTOTocolorType = new ConcurrentHashMap<>();
		mapOfLiquid_semenDTOTocollectionDistributionDate = new ConcurrentHashMap<>();
		mapOfLiquid_semenDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Liquid_semenRepository getInstance(){
		if (instance == null){
			instance = new Liquid_semenRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Liquid_semenDTO> liquid_semenDTOs = liquid_semenDAO.getAllLiquid_semen(reloadAll);
			for(Liquid_semenDTO liquid_semenDTO : liquid_semenDTOs) {
				Liquid_semenDTO oldLiquid_semenDTO = getLiquid_semenDTOByID(liquid_semenDTO.iD);
				if( oldLiquid_semenDTO != null ) {
					mapOfLiquid_semenDTOToiD.remove(oldLiquid_semenDTO.iD);
				
					if(mapOfLiquid_semenDTOTocentreType.containsKey(oldLiquid_semenDTO.centreType)) {
						mapOfLiquid_semenDTOTocentreType.get(oldLiquid_semenDTO.centreType).remove(oldLiquid_semenDTO);
					}
					if(mapOfLiquid_semenDTOTocentreType.get(oldLiquid_semenDTO.centreType).isEmpty()) {
						mapOfLiquid_semenDTOTocentreType.remove(oldLiquid_semenDTO.centreType);
					}
					
					if(mapOfLiquid_semenDTOTobullType.containsKey(oldLiquid_semenDTO.bullType)) {
						mapOfLiquid_semenDTOTobullType.get(oldLiquid_semenDTO.bullType).remove(oldLiquid_semenDTO);
					}
					if(mapOfLiquid_semenDTOTobullType.get(oldLiquid_semenDTO.bullType).isEmpty()) {
						mapOfLiquid_semenDTOTobullType.remove(oldLiquid_semenDTO.bullType);
					}
					
					if(mapOfLiquid_semenDTOTonoOfDose.containsKey(oldLiquid_semenDTO.noOfDose)) {
						mapOfLiquid_semenDTOTonoOfDose.get(oldLiquid_semenDTO.noOfDose).remove(oldLiquid_semenDTO);
					}
					if(mapOfLiquid_semenDTOTonoOfDose.get(oldLiquid_semenDTO.noOfDose).isEmpty()) {
						mapOfLiquid_semenDTOTonoOfDose.remove(oldLiquid_semenDTO.noOfDose);
					}
					
					if(mapOfLiquid_semenDTOTovolume.containsKey(oldLiquid_semenDTO.volume)) {
						mapOfLiquid_semenDTOTovolume.get(oldLiquid_semenDTO.volume).remove(oldLiquid_semenDTO);
					}
					if(mapOfLiquid_semenDTOTovolume.get(oldLiquid_semenDTO.volume).isEmpty()) {
						mapOfLiquid_semenDTOTovolume.remove(oldLiquid_semenDTO.volume);
					}
					
					if(mapOfLiquid_semenDTOTodensity.containsKey(oldLiquid_semenDTO.density)) {
						mapOfLiquid_semenDTOTodensity.get(oldLiquid_semenDTO.density).remove(oldLiquid_semenDTO);
					}
					if(mapOfLiquid_semenDTOTodensity.get(oldLiquid_semenDTO.density).isEmpty()) {
						mapOfLiquid_semenDTOTodensity.remove(oldLiquid_semenDTO.density);
					}
					
					if(mapOfLiquid_semenDTOToprogressiveMotility.containsKey(oldLiquid_semenDTO.progressiveMotility)) {
						mapOfLiquid_semenDTOToprogressiveMotility.get(oldLiquid_semenDTO.progressiveMotility).remove(oldLiquid_semenDTO);
					}
					if(mapOfLiquid_semenDTOToprogressiveMotility.get(oldLiquid_semenDTO.progressiveMotility).isEmpty()) {
						mapOfLiquid_semenDTOToprogressiveMotility.remove(oldLiquid_semenDTO.progressiveMotility);
					}
					
					if(mapOfLiquid_semenDTOTocolorType.containsKey(oldLiquid_semenDTO.colorType)) {
						mapOfLiquid_semenDTOTocolorType.get(oldLiquid_semenDTO.colorType).remove(oldLiquid_semenDTO);
					}
					if(mapOfLiquid_semenDTOTocolorType.get(oldLiquid_semenDTO.colorType).isEmpty()) {
						mapOfLiquid_semenDTOTocolorType.remove(oldLiquid_semenDTO.colorType);
					}
					
					if(mapOfLiquid_semenDTOTocollectionDistributionDate.containsKey(oldLiquid_semenDTO.collectionDistributionDate)) {
						mapOfLiquid_semenDTOTocollectionDistributionDate.get(oldLiquid_semenDTO.collectionDistributionDate).remove(oldLiquid_semenDTO);
					}
					if(mapOfLiquid_semenDTOTocollectionDistributionDate.get(oldLiquid_semenDTO.collectionDistributionDate).isEmpty()) {
						mapOfLiquid_semenDTOTocollectionDistributionDate.remove(oldLiquid_semenDTO.collectionDistributionDate);
					}
					
					if(mapOfLiquid_semenDTOTodescription.containsKey(oldLiquid_semenDTO.description)) {
						mapOfLiquid_semenDTOTodescription.get(oldLiquid_semenDTO.description).remove(oldLiquid_semenDTO);
					}
					if(mapOfLiquid_semenDTOTodescription.get(oldLiquid_semenDTO.description).isEmpty()) {
						mapOfLiquid_semenDTOTodescription.remove(oldLiquid_semenDTO.description);
					}
					
					
				}
				if(liquid_semenDTO.isDeleted == false) 
				{
					
					mapOfLiquid_semenDTOToiD.put(liquid_semenDTO.iD, liquid_semenDTO);
				
					if( ! mapOfLiquid_semenDTOTocentreType.containsKey(liquid_semenDTO.centreType)) {
						mapOfLiquid_semenDTOTocentreType.put(liquid_semenDTO.centreType, new HashSet<>());
					}
					mapOfLiquid_semenDTOTocentreType.get(liquid_semenDTO.centreType).add(liquid_semenDTO);
					
					if( ! mapOfLiquid_semenDTOTobullType.containsKey(liquid_semenDTO.bullType)) {
						mapOfLiquid_semenDTOTobullType.put(liquid_semenDTO.bullType, new HashSet<>());
					}
					mapOfLiquid_semenDTOTobullType.get(liquid_semenDTO.bullType).add(liquid_semenDTO);
					
					if( ! mapOfLiquid_semenDTOTonoOfDose.containsKey(liquid_semenDTO.noOfDose)) {
						mapOfLiquid_semenDTOTonoOfDose.put(liquid_semenDTO.noOfDose, new HashSet<>());
					}
					mapOfLiquid_semenDTOTonoOfDose.get(liquid_semenDTO.noOfDose).add(liquid_semenDTO);
					
					if( ! mapOfLiquid_semenDTOTovolume.containsKey(liquid_semenDTO.volume)) {
						mapOfLiquid_semenDTOTovolume.put(liquid_semenDTO.volume, new HashSet<>());
					}
					mapOfLiquid_semenDTOTovolume.get(liquid_semenDTO.volume).add(liquid_semenDTO);
					
					if( ! mapOfLiquid_semenDTOTodensity.containsKey(liquid_semenDTO.density)) {
						mapOfLiquid_semenDTOTodensity.put(liquid_semenDTO.density, new HashSet<>());
					}
					mapOfLiquid_semenDTOTodensity.get(liquid_semenDTO.density).add(liquid_semenDTO);
					
					if( ! mapOfLiquid_semenDTOToprogressiveMotility.containsKey(liquid_semenDTO.progressiveMotility)) {
						mapOfLiquid_semenDTOToprogressiveMotility.put(liquid_semenDTO.progressiveMotility, new HashSet<>());
					}
					mapOfLiquid_semenDTOToprogressiveMotility.get(liquid_semenDTO.progressiveMotility).add(liquid_semenDTO);
					
					if( ! mapOfLiquid_semenDTOTocolorType.containsKey(liquid_semenDTO.colorType)) {
						mapOfLiquid_semenDTOTocolorType.put(liquid_semenDTO.colorType, new HashSet<>());
					}
					mapOfLiquid_semenDTOTocolorType.get(liquid_semenDTO.colorType).add(liquid_semenDTO);
					
					if( ! mapOfLiquid_semenDTOTocollectionDistributionDate.containsKey(liquid_semenDTO.collectionDistributionDate)) {
						mapOfLiquid_semenDTOTocollectionDistributionDate.put(liquid_semenDTO.collectionDistributionDate, new HashSet<>());
					}
					mapOfLiquid_semenDTOTocollectionDistributionDate.get(liquid_semenDTO.collectionDistributionDate).add(liquid_semenDTO);
					
					if( ! mapOfLiquid_semenDTOTodescription.containsKey(liquid_semenDTO.description)) {
						mapOfLiquid_semenDTOTodescription.put(liquid_semenDTO.description, new HashSet<>());
					}
					mapOfLiquid_semenDTOTodescription.get(liquid_semenDTO.description).add(liquid_semenDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Liquid_semenDTO> getLiquid_semenList() {
		List <Liquid_semenDTO> liquid_semens = new ArrayList<Liquid_semenDTO>(this.mapOfLiquid_semenDTOToiD.values());
		return liquid_semens;
	}
	
	
	public Liquid_semenDTO getLiquid_semenDTOByID( long ID){
		return mapOfLiquid_semenDTOToiD.get(ID);
	}
	
	
	public List<Liquid_semenDTO> getLiquid_semenDTOBycentre_type(int centre_type) {
		return new ArrayList<>( mapOfLiquid_semenDTOTocentreType.getOrDefault(centre_type,new HashSet<>()));
	}
	
	
	public List<Liquid_semenDTO> getLiquid_semenDTOBybull_type(int bull_type) {
		return new ArrayList<>( mapOfLiquid_semenDTOTobullType.getOrDefault(bull_type,new HashSet<>()));
	}
	
	
	public List<Liquid_semenDTO> getLiquid_semenDTOByno_of_dose(int no_of_dose) {
		return new ArrayList<>( mapOfLiquid_semenDTOTonoOfDose.getOrDefault(no_of_dose,new HashSet<>()));
	}
	
	
	public List<Liquid_semenDTO> getLiquid_semenDTOByvolume(int volume) {
		return new ArrayList<>( mapOfLiquid_semenDTOTovolume.getOrDefault(volume,new HashSet<>()));
	}
	
	
	public List<Liquid_semenDTO> getLiquid_semenDTOBydensity(int density) {
		return new ArrayList<>( mapOfLiquid_semenDTOTodensity.getOrDefault(density,new HashSet<>()));
	}
	
	
	public List<Liquid_semenDTO> getLiquid_semenDTOByprogressive_motility(int progressive_motility) {
		return new ArrayList<>( mapOfLiquid_semenDTOToprogressiveMotility.getOrDefault(progressive_motility,new HashSet<>()));
	}
	
	
	public List<Liquid_semenDTO> getLiquid_semenDTOBycolor_type(int color_type) {
		return new ArrayList<>( mapOfLiquid_semenDTOTocolorType.getOrDefault(color_type,new HashSet<>()));
	}
	
	
	public List<Liquid_semenDTO> getLiquid_semenDTOBycollection_distribution_date(long collection_distribution_date) {
		return new ArrayList<>( mapOfLiquid_semenDTOTocollectionDistributionDate.getOrDefault(collection_distribution_date,new HashSet<>()));
	}
	
	
	public List<Liquid_semenDTO> getLiquid_semenDTOBydescription(String description) {
		return new ArrayList<>( mapOfLiquid_semenDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "liquid_semen";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


