package bull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class BullRepository implements Repository {
	BullDAO bullDAO = new BullDAO();
	
	
	static Logger logger = Logger.getLogger(BullRepository.class);
	Map<Long, BullDTO>mapOfBullDTOToiD;
	Map<String, Set<BullDTO> >mapOfBullDTOTonameEn;
	Map<String, Set<BullDTO> >mapOfBullDTOTonameBn;
	Map<Long, Set<BullDTO> >mapOfBullDTOTodateOfBirth;
	Map<Long, Set<BullDTO> >mapOfBullDTOTobreedType;
	Map<Long, Set<BullDTO> >mapOfBullDTOTostatusType;
	Map<String, Set<BullDTO> >mapOfBullDTOTodam;
	Map<String, Set<BullDTO> >mapOfBullDTOTosire;
	Map<String, Set<BullDTO> >mapOfBullDTOTomaternalGrandDam;
	Map<String, Set<BullDTO> >mapOfBullDTOTomaternalGrandSire;
	Map<String, Set<BullDTO> >mapOfBullDTOTopaternalGrandDam;
	Map<String, Set<BullDTO> >mapOfBullDTOTopaternalGrandSire;
	Map<Integer, Set<BullDTO> >mapOfBullDTOTomilkYieldOfDam;
	Map<Integer, Set<BullDTO> >mapOfBullDTOTomilkYieldOfSiresDam;
	Map<Integer, Set<BullDTO> >mapOfBullDTOToprogenyMilkYield;
	Map<String, Set<BullDTO> >mapOfBullDTOTodescription;


	static BullRepository instance = null;  
	private BullRepository(){
		mapOfBullDTOToiD = new ConcurrentHashMap<>();
		mapOfBullDTOTonameEn = new ConcurrentHashMap<>();
		mapOfBullDTOTonameBn = new ConcurrentHashMap<>();
		mapOfBullDTOTodateOfBirth = new ConcurrentHashMap<>();
		mapOfBullDTOTobreedType = new ConcurrentHashMap<>();
		mapOfBullDTOTostatusType = new ConcurrentHashMap<>();
		mapOfBullDTOTodam = new ConcurrentHashMap<>();
		mapOfBullDTOTosire = new ConcurrentHashMap<>();
		mapOfBullDTOTomaternalGrandDam = new ConcurrentHashMap<>();
		mapOfBullDTOTomaternalGrandSire = new ConcurrentHashMap<>();
		mapOfBullDTOTopaternalGrandDam = new ConcurrentHashMap<>();
		mapOfBullDTOTopaternalGrandSire = new ConcurrentHashMap<>();
		mapOfBullDTOTomilkYieldOfDam = new ConcurrentHashMap<>();
		mapOfBullDTOTomilkYieldOfSiresDam = new ConcurrentHashMap<>();
		mapOfBullDTOToprogenyMilkYield = new ConcurrentHashMap<>();
		mapOfBullDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static BullRepository getInstance(){
		if (instance == null){
			instance = new BullRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<BullDTO> bullDTOs = bullDAO.getAllBull(reloadAll);
			for(BullDTO bullDTO : bullDTOs) {
				BullDTO oldBullDTO = getBullDTOByID(bullDTO.iD);
				if( oldBullDTO != null ) {
					mapOfBullDTOToiD.remove(oldBullDTO.iD);
				
					if(mapOfBullDTOTonameEn.containsKey(oldBullDTO.nameEn)) {
						mapOfBullDTOTonameEn.get(oldBullDTO.nameEn).remove(oldBullDTO);
					}
					if(mapOfBullDTOTonameEn.get(oldBullDTO.nameEn).isEmpty()) {
						mapOfBullDTOTonameEn.remove(oldBullDTO.nameEn);
					}
					
					if(mapOfBullDTOTonameBn.containsKey(oldBullDTO.nameBn)) {
						mapOfBullDTOTonameBn.get(oldBullDTO.nameBn).remove(oldBullDTO);
					}
					if(mapOfBullDTOTonameBn.get(oldBullDTO.nameBn).isEmpty()) {
						mapOfBullDTOTonameBn.remove(oldBullDTO.nameBn);
					}
					
					if(mapOfBullDTOTodateOfBirth.containsKey(oldBullDTO.dateOfBirth)) {
						mapOfBullDTOTodateOfBirth.get(oldBullDTO.dateOfBirth).remove(oldBullDTO);
					}
					if(mapOfBullDTOTodateOfBirth.get(oldBullDTO.dateOfBirth).isEmpty()) {
						mapOfBullDTOTodateOfBirth.remove(oldBullDTO.dateOfBirth);
					}
					
					if(mapOfBullDTOTobreedType.containsKey(oldBullDTO.breedType)) {
						mapOfBullDTOTobreedType.get(oldBullDTO.breedType).remove(oldBullDTO);
					}
					if(mapOfBullDTOTobreedType.get(oldBullDTO.breedType).isEmpty()) {
						mapOfBullDTOTobreedType.remove(oldBullDTO.breedType);
					}
					
					if(mapOfBullDTOTostatusType.containsKey(oldBullDTO.statusType)) {
						mapOfBullDTOTostatusType.get(oldBullDTO.statusType).remove(oldBullDTO);
					}
					if(mapOfBullDTOTostatusType.get(oldBullDTO.statusType).isEmpty()) {
						mapOfBullDTOTostatusType.remove(oldBullDTO.statusType);
					}
					
					if(mapOfBullDTOTodam.containsKey(oldBullDTO.dam)) {
						mapOfBullDTOTodam.get(oldBullDTO.dam).remove(oldBullDTO);
					}
					if(mapOfBullDTOTodam.get(oldBullDTO.dam).isEmpty()) {
						mapOfBullDTOTodam.remove(oldBullDTO.dam);
					}
					
					if(mapOfBullDTOTosire.containsKey(oldBullDTO.sire)) {
						mapOfBullDTOTosire.get(oldBullDTO.sire).remove(oldBullDTO);
					}
					if(mapOfBullDTOTosire.get(oldBullDTO.sire).isEmpty()) {
						mapOfBullDTOTosire.remove(oldBullDTO.sire);
					}
					
					if(mapOfBullDTOTomaternalGrandDam.containsKey(oldBullDTO.maternalGrandDam)) {
						mapOfBullDTOTomaternalGrandDam.get(oldBullDTO.maternalGrandDam).remove(oldBullDTO);
					}
					if(mapOfBullDTOTomaternalGrandDam.get(oldBullDTO.maternalGrandDam).isEmpty()) {
						mapOfBullDTOTomaternalGrandDam.remove(oldBullDTO.maternalGrandDam);
					}
					
					if(mapOfBullDTOTomaternalGrandSire.containsKey(oldBullDTO.maternalGrandSire)) {
						mapOfBullDTOTomaternalGrandSire.get(oldBullDTO.maternalGrandSire).remove(oldBullDTO);
					}
					if(mapOfBullDTOTomaternalGrandSire.get(oldBullDTO.maternalGrandSire).isEmpty()) {
						mapOfBullDTOTomaternalGrandSire.remove(oldBullDTO.maternalGrandSire);
					}
					
					if(mapOfBullDTOTopaternalGrandDam.containsKey(oldBullDTO.paternalGrandDam)) {
						mapOfBullDTOTopaternalGrandDam.get(oldBullDTO.paternalGrandDam).remove(oldBullDTO);
					}
					if(mapOfBullDTOTopaternalGrandDam.get(oldBullDTO.paternalGrandDam).isEmpty()) {
						mapOfBullDTOTopaternalGrandDam.remove(oldBullDTO.paternalGrandDam);
					}
					
					if(mapOfBullDTOTopaternalGrandSire.containsKey(oldBullDTO.paternalGrandSire)) {
						mapOfBullDTOTopaternalGrandSire.get(oldBullDTO.paternalGrandSire).remove(oldBullDTO);
					}
					if(mapOfBullDTOTopaternalGrandSire.get(oldBullDTO.paternalGrandSire).isEmpty()) {
						mapOfBullDTOTopaternalGrandSire.remove(oldBullDTO.paternalGrandSire);
					}
					
					if(mapOfBullDTOTomilkYieldOfDam.containsKey(oldBullDTO.milkYieldOfDam)) {
						mapOfBullDTOTomilkYieldOfDam.get(oldBullDTO.milkYieldOfDam).remove(oldBullDTO);
					}
					if(mapOfBullDTOTomilkYieldOfDam.get(oldBullDTO.milkYieldOfDam).isEmpty()) {
						mapOfBullDTOTomilkYieldOfDam.remove(oldBullDTO.milkYieldOfDam);
					}
					
					if(mapOfBullDTOTomilkYieldOfSiresDam.containsKey(oldBullDTO.milkYieldOfSiresDam)) {
						mapOfBullDTOTomilkYieldOfSiresDam.get(oldBullDTO.milkYieldOfSiresDam).remove(oldBullDTO);
					}
					if(mapOfBullDTOTomilkYieldOfSiresDam.get(oldBullDTO.milkYieldOfSiresDam).isEmpty()) {
						mapOfBullDTOTomilkYieldOfSiresDam.remove(oldBullDTO.milkYieldOfSiresDam);
					}
					
					if(mapOfBullDTOToprogenyMilkYield.containsKey(oldBullDTO.progenyMilkYield)) {
						mapOfBullDTOToprogenyMilkYield.get(oldBullDTO.progenyMilkYield).remove(oldBullDTO);
					}
					if(mapOfBullDTOToprogenyMilkYield.get(oldBullDTO.progenyMilkYield).isEmpty()) {
						mapOfBullDTOToprogenyMilkYield.remove(oldBullDTO.progenyMilkYield);
					}
					
					if(mapOfBullDTOTodescription.containsKey(oldBullDTO.description)) {
						mapOfBullDTOTodescription.get(oldBullDTO.description).remove(oldBullDTO);
					}
					if(mapOfBullDTOTodescription.get(oldBullDTO.description).isEmpty()) {
						mapOfBullDTOTodescription.remove(oldBullDTO.description);
					}
					
					
				}
				if(bullDTO.isDeleted == false) 
				{
					
					mapOfBullDTOToiD.put(bullDTO.iD, bullDTO);
				
					if( ! mapOfBullDTOTonameEn.containsKey(bullDTO.nameEn)) {
						mapOfBullDTOTonameEn.put(bullDTO.nameEn, new HashSet<>());
					}
					mapOfBullDTOTonameEn.get(bullDTO.nameEn).add(bullDTO);
					
					if( ! mapOfBullDTOTonameBn.containsKey(bullDTO.nameBn)) {
						mapOfBullDTOTonameBn.put(bullDTO.nameBn, new HashSet<>());
					}
					mapOfBullDTOTonameBn.get(bullDTO.nameBn).add(bullDTO);
					
					if( ! mapOfBullDTOTodateOfBirth.containsKey(bullDTO.dateOfBirth)) {
						mapOfBullDTOTodateOfBirth.put(bullDTO.dateOfBirth, new HashSet<>());
					}
					mapOfBullDTOTodateOfBirth.get(bullDTO.dateOfBirth).add(bullDTO);
					
					if( ! mapOfBullDTOTobreedType.containsKey(bullDTO.breedType)) {
						mapOfBullDTOTobreedType.put(bullDTO.breedType, new HashSet<>());
					}
					mapOfBullDTOTobreedType.get(bullDTO.breedType).add(bullDTO);
					
					if( ! mapOfBullDTOTostatusType.containsKey(bullDTO.statusType)) {
						mapOfBullDTOTostatusType.put(bullDTO.statusType, new HashSet<>());
					}
					mapOfBullDTOTostatusType.get(bullDTO.statusType).add(bullDTO);
					
					if( ! mapOfBullDTOTodam.containsKey(bullDTO.dam)) {
						mapOfBullDTOTodam.put(bullDTO.dam, new HashSet<>());
					}
					mapOfBullDTOTodam.get(bullDTO.dam).add(bullDTO);
					
					if( ! mapOfBullDTOTosire.containsKey(bullDTO.sire)) {
						mapOfBullDTOTosire.put(bullDTO.sire, new HashSet<>());
					}
					mapOfBullDTOTosire.get(bullDTO.sire).add(bullDTO);
					
					if( ! mapOfBullDTOTomaternalGrandDam.containsKey(bullDTO.maternalGrandDam)) {
						mapOfBullDTOTomaternalGrandDam.put(bullDTO.maternalGrandDam, new HashSet<>());
					}
					mapOfBullDTOTomaternalGrandDam.get(bullDTO.maternalGrandDam).add(bullDTO);
					
					if( ! mapOfBullDTOTomaternalGrandSire.containsKey(bullDTO.maternalGrandSire)) {
						mapOfBullDTOTomaternalGrandSire.put(bullDTO.maternalGrandSire, new HashSet<>());
					}
					mapOfBullDTOTomaternalGrandSire.get(bullDTO.maternalGrandSire).add(bullDTO);
					
					if( ! mapOfBullDTOTopaternalGrandDam.containsKey(bullDTO.paternalGrandDam)) {
						mapOfBullDTOTopaternalGrandDam.put(bullDTO.paternalGrandDam, new HashSet<>());
					}
					mapOfBullDTOTopaternalGrandDam.get(bullDTO.paternalGrandDam).add(bullDTO);
					
					if( ! mapOfBullDTOTopaternalGrandSire.containsKey(bullDTO.paternalGrandSire)) {
						mapOfBullDTOTopaternalGrandSire.put(bullDTO.paternalGrandSire, new HashSet<>());
					}
					mapOfBullDTOTopaternalGrandSire.get(bullDTO.paternalGrandSire).add(bullDTO);
					
					if( ! mapOfBullDTOTomilkYieldOfDam.containsKey(bullDTO.milkYieldOfDam)) {
						mapOfBullDTOTomilkYieldOfDam.put(bullDTO.milkYieldOfDam, new HashSet<>());
					}
					mapOfBullDTOTomilkYieldOfDam.get(bullDTO.milkYieldOfDam).add(bullDTO);
					
					if( ! mapOfBullDTOTomilkYieldOfSiresDam.containsKey(bullDTO.milkYieldOfSiresDam)) {
						mapOfBullDTOTomilkYieldOfSiresDam.put(bullDTO.milkYieldOfSiresDam, new HashSet<>());
					}
					mapOfBullDTOTomilkYieldOfSiresDam.get(bullDTO.milkYieldOfSiresDam).add(bullDTO);
					
					if( ! mapOfBullDTOToprogenyMilkYield.containsKey(bullDTO.progenyMilkYield)) {
						mapOfBullDTOToprogenyMilkYield.put(bullDTO.progenyMilkYield, new HashSet<>());
					}
					mapOfBullDTOToprogenyMilkYield.get(bullDTO.progenyMilkYield).add(bullDTO);
					
					if( ! mapOfBullDTOTodescription.containsKey(bullDTO.description)) {
						mapOfBullDTOTodescription.put(bullDTO.description, new HashSet<>());
					}
					mapOfBullDTOTodescription.get(bullDTO.description).add(bullDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<BullDTO> getBullList() {
		List <BullDTO> bulls = new ArrayList<BullDTO>(this.mapOfBullDTOToiD.values());
		return bulls;
	}
	
	
	public BullDTO getBullDTOByID( long ID){
		return mapOfBullDTOToiD.get(ID);
	}
	
	
	public List<BullDTO> getBullDTOByname_en(String name_en) {
		return new ArrayList<>( mapOfBullDTOTonameEn.getOrDefault(name_en,new HashSet<>()));
	}
	
	
	public List<BullDTO> getBullDTOByname_bn(String name_bn) {
		return new ArrayList<>( mapOfBullDTOTonameBn.getOrDefault(name_bn,new HashSet<>()));
	}
	
	
	public List<BullDTO> getBullDTOBydate_of_birth(long date_of_birth) {
		return new ArrayList<>( mapOfBullDTOTodateOfBirth.getOrDefault(date_of_birth,new HashSet<>()));
	}
	
	
	public List<BullDTO> getBullDTOBybreed_type(long breed_type) {
		return new ArrayList<>( mapOfBullDTOTobreedType.getOrDefault(breed_type,new HashSet<>()));
	}
	
	
	public List<BullDTO> getBullDTOBystatus_type(long status_type) {
		return new ArrayList<>( mapOfBullDTOTostatusType.getOrDefault(status_type,new HashSet<>()));
	}
	
	
	public List<BullDTO> getBullDTOBydam(String dam) {
		return new ArrayList<>( mapOfBullDTOTodam.getOrDefault(dam,new HashSet<>()));
	}
	
	
	public List<BullDTO> getBullDTOBysire(String sire) {
		return new ArrayList<>( mapOfBullDTOTosire.getOrDefault(sire,new HashSet<>()));
	}
	
	
	public List<BullDTO> getBullDTOBymaternal_grand_dam(String maternal_grand_dam) {
		return new ArrayList<>( mapOfBullDTOTomaternalGrandDam.getOrDefault(maternal_grand_dam,new HashSet<>()));
	}
	
	
	public List<BullDTO> getBullDTOBymaternal_grand_sire(String maternal_grand_sire) {
		return new ArrayList<>( mapOfBullDTOTomaternalGrandSire.getOrDefault(maternal_grand_sire,new HashSet<>()));
	}
	
	
	public List<BullDTO> getBullDTOBypaternal_grand_dam(String paternal_grand_dam) {
		return new ArrayList<>( mapOfBullDTOTopaternalGrandDam.getOrDefault(paternal_grand_dam,new HashSet<>()));
	}
	
	
	public List<BullDTO> getBullDTOBypaternal_grand_sire(String paternal_grand_sire) {
		return new ArrayList<>( mapOfBullDTOTopaternalGrandSire.getOrDefault(paternal_grand_sire,new HashSet<>()));
	}
	
	
	public List<BullDTO> getBullDTOBymilk_yield_of_dam(int milk_yield_of_dam) {
		return new ArrayList<>( mapOfBullDTOTomilkYieldOfDam.getOrDefault(milk_yield_of_dam,new HashSet<>()));
	}
	
	
	public List<BullDTO> getBullDTOBymilk_yield_of_sires_dam(int milk_yield_of_sires_dam) {
		return new ArrayList<>( mapOfBullDTOTomilkYieldOfSiresDam.getOrDefault(milk_yield_of_sires_dam,new HashSet<>()));
	}
	
	
	public List<BullDTO> getBullDTOByprogeny_milk_yield(int progeny_milk_yield) {
		return new ArrayList<>( mapOfBullDTOToprogenyMilkYield.getOrDefault(progeny_milk_yield,new HashSet<>()));
	}
	
	
	public List<BullDTO> getBullDTOBydescription(String description) {
		return new ArrayList<>( mapOfBullDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "bull";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


