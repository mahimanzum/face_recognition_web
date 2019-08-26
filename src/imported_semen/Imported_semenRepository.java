package imported_semen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Imported_semenRepository implements Repository {
	Imported_semenDAO imported_semenDAO = new Imported_semenDAO();
	
	
	static Logger logger = Logger.getLogger(Imported_semenRepository.class);
	Map<Long, Imported_semenDTO>mapOfImported_semenDTOToiD;
	Map<String, Set<Imported_semenDTO> >mapOfImported_semenDTOTobullTag;
	Map<Long, Set<Imported_semenDTO> >mapOfImported_semenDTOTodateOfBirth;
	Map<String, Set<Imported_semenDTO> >mapOfImported_semenDTOTodam;
	Map<String, Set<Imported_semenDTO> >mapOfImported_semenDTOTosire;
	Map<String, Set<Imported_semenDTO> >mapOfImported_semenDTOTomaternalGrandDam;
	Map<String, Set<Imported_semenDTO> >mapOfImported_semenDTOTomaternalGrandSire;
	Map<String, Set<Imported_semenDTO> >mapOfImported_semenDTOTopaternalGrandDam;
	Map<String, Set<Imported_semenDTO> >mapOfImported_semenDTOTopaternalGrandSire;
	Map<Integer, Set<Imported_semenDTO> >mapOfImported_semenDTOTomilkYieldOfDam;
	Map<Integer, Set<Imported_semenDTO> >mapOfImported_semenDTOTomilkYieldOfSiresDam;
	Map<Integer, Set<Imported_semenDTO> >mapOfImported_semenDTOToprogenyMilkYield;
	Map<String, Set<Imported_semenDTO> >mapOfImported_semenDTOTobreed;
	Map<Long, Set<Imported_semenDTO> >mapOfImported_semenDTOTodateOfEntry;
	Map<Integer, Set<Imported_semenDTO> >mapOfImported_semenDTOToreceivedAmount;
	Map<Integer, Set<Imported_semenDTO> >mapOfImported_semenDTOTodistributedAmount;
	Map<String, Set<Imported_semenDTO> >mapOfImported_semenDTOTotoWhomDistributed;
	Map<String, Set<Imported_semenDTO> >mapOfImported_semenDTOTodescription;


	static Imported_semenRepository instance = null;  
	private Imported_semenRepository(){
		mapOfImported_semenDTOToiD = new ConcurrentHashMap<>();
		mapOfImported_semenDTOTobullTag = new ConcurrentHashMap<>();
		mapOfImported_semenDTOTodateOfBirth = new ConcurrentHashMap<>();
		mapOfImported_semenDTOTodam = new ConcurrentHashMap<>();
		mapOfImported_semenDTOTosire = new ConcurrentHashMap<>();
		mapOfImported_semenDTOTomaternalGrandDam = new ConcurrentHashMap<>();
		mapOfImported_semenDTOTomaternalGrandSire = new ConcurrentHashMap<>();
		mapOfImported_semenDTOTopaternalGrandDam = new ConcurrentHashMap<>();
		mapOfImported_semenDTOTopaternalGrandSire = new ConcurrentHashMap<>();
		mapOfImported_semenDTOTomilkYieldOfDam = new ConcurrentHashMap<>();
		mapOfImported_semenDTOTomilkYieldOfSiresDam = new ConcurrentHashMap<>();
		mapOfImported_semenDTOToprogenyMilkYield = new ConcurrentHashMap<>();
		mapOfImported_semenDTOTobreed = new ConcurrentHashMap<>();
		mapOfImported_semenDTOTodateOfEntry = new ConcurrentHashMap<>();
		mapOfImported_semenDTOToreceivedAmount = new ConcurrentHashMap<>();
		mapOfImported_semenDTOTodistributedAmount = new ConcurrentHashMap<>();
		mapOfImported_semenDTOTotoWhomDistributed = new ConcurrentHashMap<>();
		mapOfImported_semenDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Imported_semenRepository getInstance(){
		if (instance == null){
			instance = new Imported_semenRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Imported_semenDTO> imported_semenDTOs = imported_semenDAO.getAllImported_semen(reloadAll);
			for(Imported_semenDTO imported_semenDTO : imported_semenDTOs) {
				Imported_semenDTO oldImported_semenDTO = getImported_semenDTOByID(imported_semenDTO.iD);
				if( oldImported_semenDTO != null ) {
					mapOfImported_semenDTOToiD.remove(oldImported_semenDTO.iD);
				
					if(mapOfImported_semenDTOTobullTag.containsKey(oldImported_semenDTO.bullTag)) {
						mapOfImported_semenDTOTobullTag.get(oldImported_semenDTO.bullTag).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOTobullTag.get(oldImported_semenDTO.bullTag).isEmpty()) {
						mapOfImported_semenDTOTobullTag.remove(oldImported_semenDTO.bullTag);
					}
					
					if(mapOfImported_semenDTOTodateOfBirth.containsKey(oldImported_semenDTO.dateOfBirth)) {
						mapOfImported_semenDTOTodateOfBirth.get(oldImported_semenDTO.dateOfBirth).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOTodateOfBirth.get(oldImported_semenDTO.dateOfBirth).isEmpty()) {
						mapOfImported_semenDTOTodateOfBirth.remove(oldImported_semenDTO.dateOfBirth);
					}
					
					if(mapOfImported_semenDTOTodam.containsKey(oldImported_semenDTO.dam)) {
						mapOfImported_semenDTOTodam.get(oldImported_semenDTO.dam).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOTodam.get(oldImported_semenDTO.dam).isEmpty()) {
						mapOfImported_semenDTOTodam.remove(oldImported_semenDTO.dam);
					}
					
					if(mapOfImported_semenDTOTosire.containsKey(oldImported_semenDTO.sire)) {
						mapOfImported_semenDTOTosire.get(oldImported_semenDTO.sire).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOTosire.get(oldImported_semenDTO.sire).isEmpty()) {
						mapOfImported_semenDTOTosire.remove(oldImported_semenDTO.sire);
					}
					
					if(mapOfImported_semenDTOTomaternalGrandDam.containsKey(oldImported_semenDTO.maternalGrandDam)) {
						mapOfImported_semenDTOTomaternalGrandDam.get(oldImported_semenDTO.maternalGrandDam).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOTomaternalGrandDam.get(oldImported_semenDTO.maternalGrandDam).isEmpty()) {
						mapOfImported_semenDTOTomaternalGrandDam.remove(oldImported_semenDTO.maternalGrandDam);
					}
					
					if(mapOfImported_semenDTOTomaternalGrandSire.containsKey(oldImported_semenDTO.maternalGrandSire)) {
						mapOfImported_semenDTOTomaternalGrandSire.get(oldImported_semenDTO.maternalGrandSire).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOTomaternalGrandSire.get(oldImported_semenDTO.maternalGrandSire).isEmpty()) {
						mapOfImported_semenDTOTomaternalGrandSire.remove(oldImported_semenDTO.maternalGrandSire);
					}
					
					if(mapOfImported_semenDTOTopaternalGrandDam.containsKey(oldImported_semenDTO.paternalGrandDam)) {
						mapOfImported_semenDTOTopaternalGrandDam.get(oldImported_semenDTO.paternalGrandDam).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOTopaternalGrandDam.get(oldImported_semenDTO.paternalGrandDam).isEmpty()) {
						mapOfImported_semenDTOTopaternalGrandDam.remove(oldImported_semenDTO.paternalGrandDam);
					}
					
					if(mapOfImported_semenDTOTopaternalGrandSire.containsKey(oldImported_semenDTO.paternalGrandSire)) {
						mapOfImported_semenDTOTopaternalGrandSire.get(oldImported_semenDTO.paternalGrandSire).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOTopaternalGrandSire.get(oldImported_semenDTO.paternalGrandSire).isEmpty()) {
						mapOfImported_semenDTOTopaternalGrandSire.remove(oldImported_semenDTO.paternalGrandSire);
					}
					
					if(mapOfImported_semenDTOTomilkYieldOfDam.containsKey(oldImported_semenDTO.milkYieldOfDam)) {
						mapOfImported_semenDTOTomilkYieldOfDam.get(oldImported_semenDTO.milkYieldOfDam).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOTomilkYieldOfDam.get(oldImported_semenDTO.milkYieldOfDam).isEmpty()) {
						mapOfImported_semenDTOTomilkYieldOfDam.remove(oldImported_semenDTO.milkYieldOfDam);
					}
					
					if(mapOfImported_semenDTOTomilkYieldOfSiresDam.containsKey(oldImported_semenDTO.milkYieldOfSiresDam)) {
						mapOfImported_semenDTOTomilkYieldOfSiresDam.get(oldImported_semenDTO.milkYieldOfSiresDam).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOTomilkYieldOfSiresDam.get(oldImported_semenDTO.milkYieldOfSiresDam).isEmpty()) {
						mapOfImported_semenDTOTomilkYieldOfSiresDam.remove(oldImported_semenDTO.milkYieldOfSiresDam);
					}
					
					if(mapOfImported_semenDTOToprogenyMilkYield.containsKey(oldImported_semenDTO.progenyMilkYield)) {
						mapOfImported_semenDTOToprogenyMilkYield.get(oldImported_semenDTO.progenyMilkYield).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOToprogenyMilkYield.get(oldImported_semenDTO.progenyMilkYield).isEmpty()) {
						mapOfImported_semenDTOToprogenyMilkYield.remove(oldImported_semenDTO.progenyMilkYield);
					}
					
					if(mapOfImported_semenDTOTobreed.containsKey(oldImported_semenDTO.breed)) {
						mapOfImported_semenDTOTobreed.get(oldImported_semenDTO.breed).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOTobreed.get(oldImported_semenDTO.breed).isEmpty()) {
						mapOfImported_semenDTOTobreed.remove(oldImported_semenDTO.breed);
					}
					
					if(mapOfImported_semenDTOTodateOfEntry.containsKey(oldImported_semenDTO.dateOfEntry)) {
						mapOfImported_semenDTOTodateOfEntry.get(oldImported_semenDTO.dateOfEntry).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOTodateOfEntry.get(oldImported_semenDTO.dateOfEntry).isEmpty()) {
						mapOfImported_semenDTOTodateOfEntry.remove(oldImported_semenDTO.dateOfEntry);
					}
					
					if(mapOfImported_semenDTOToreceivedAmount.containsKey(oldImported_semenDTO.receivedAmount)) {
						mapOfImported_semenDTOToreceivedAmount.get(oldImported_semenDTO.receivedAmount).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOToreceivedAmount.get(oldImported_semenDTO.receivedAmount).isEmpty()) {
						mapOfImported_semenDTOToreceivedAmount.remove(oldImported_semenDTO.receivedAmount);
					}
					
					if(mapOfImported_semenDTOTodistributedAmount.containsKey(oldImported_semenDTO.distributedAmount)) {
						mapOfImported_semenDTOTodistributedAmount.get(oldImported_semenDTO.distributedAmount).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOTodistributedAmount.get(oldImported_semenDTO.distributedAmount).isEmpty()) {
						mapOfImported_semenDTOTodistributedAmount.remove(oldImported_semenDTO.distributedAmount);
					}
					
					if(mapOfImported_semenDTOTotoWhomDistributed.containsKey(oldImported_semenDTO.toWhomDistributed)) {
						mapOfImported_semenDTOTotoWhomDistributed.get(oldImported_semenDTO.toWhomDistributed).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOTotoWhomDistributed.get(oldImported_semenDTO.toWhomDistributed).isEmpty()) {
						mapOfImported_semenDTOTotoWhomDistributed.remove(oldImported_semenDTO.toWhomDistributed);
					}
					
					if(mapOfImported_semenDTOTodescription.containsKey(oldImported_semenDTO.description)) {
						mapOfImported_semenDTOTodescription.get(oldImported_semenDTO.description).remove(oldImported_semenDTO);
					}
					if(mapOfImported_semenDTOTodescription.get(oldImported_semenDTO.description).isEmpty()) {
						mapOfImported_semenDTOTodescription.remove(oldImported_semenDTO.description);
					}
					
					
				}
				if(imported_semenDTO.isDeleted == false) 
				{
					
					mapOfImported_semenDTOToiD.put(imported_semenDTO.iD, imported_semenDTO);
				
					if( ! mapOfImported_semenDTOTobullTag.containsKey(imported_semenDTO.bullTag)) {
						mapOfImported_semenDTOTobullTag.put(imported_semenDTO.bullTag, new HashSet<>());
					}
					mapOfImported_semenDTOTobullTag.get(imported_semenDTO.bullTag).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOTodateOfBirth.containsKey(imported_semenDTO.dateOfBirth)) {
						mapOfImported_semenDTOTodateOfBirth.put(imported_semenDTO.dateOfBirth, new HashSet<>());
					}
					mapOfImported_semenDTOTodateOfBirth.get(imported_semenDTO.dateOfBirth).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOTodam.containsKey(imported_semenDTO.dam)) {
						mapOfImported_semenDTOTodam.put(imported_semenDTO.dam, new HashSet<>());
					}
					mapOfImported_semenDTOTodam.get(imported_semenDTO.dam).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOTosire.containsKey(imported_semenDTO.sire)) {
						mapOfImported_semenDTOTosire.put(imported_semenDTO.sire, new HashSet<>());
					}
					mapOfImported_semenDTOTosire.get(imported_semenDTO.sire).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOTomaternalGrandDam.containsKey(imported_semenDTO.maternalGrandDam)) {
						mapOfImported_semenDTOTomaternalGrandDam.put(imported_semenDTO.maternalGrandDam, new HashSet<>());
					}
					mapOfImported_semenDTOTomaternalGrandDam.get(imported_semenDTO.maternalGrandDam).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOTomaternalGrandSire.containsKey(imported_semenDTO.maternalGrandSire)) {
						mapOfImported_semenDTOTomaternalGrandSire.put(imported_semenDTO.maternalGrandSire, new HashSet<>());
					}
					mapOfImported_semenDTOTomaternalGrandSire.get(imported_semenDTO.maternalGrandSire).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOTopaternalGrandDam.containsKey(imported_semenDTO.paternalGrandDam)) {
						mapOfImported_semenDTOTopaternalGrandDam.put(imported_semenDTO.paternalGrandDam, new HashSet<>());
					}
					mapOfImported_semenDTOTopaternalGrandDam.get(imported_semenDTO.paternalGrandDam).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOTopaternalGrandSire.containsKey(imported_semenDTO.paternalGrandSire)) {
						mapOfImported_semenDTOTopaternalGrandSire.put(imported_semenDTO.paternalGrandSire, new HashSet<>());
					}
					mapOfImported_semenDTOTopaternalGrandSire.get(imported_semenDTO.paternalGrandSire).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOTomilkYieldOfDam.containsKey(imported_semenDTO.milkYieldOfDam)) {
						mapOfImported_semenDTOTomilkYieldOfDam.put(imported_semenDTO.milkYieldOfDam, new HashSet<>());
					}
					mapOfImported_semenDTOTomilkYieldOfDam.get(imported_semenDTO.milkYieldOfDam).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOTomilkYieldOfSiresDam.containsKey(imported_semenDTO.milkYieldOfSiresDam)) {
						mapOfImported_semenDTOTomilkYieldOfSiresDam.put(imported_semenDTO.milkYieldOfSiresDam, new HashSet<>());
					}
					mapOfImported_semenDTOTomilkYieldOfSiresDam.get(imported_semenDTO.milkYieldOfSiresDam).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOToprogenyMilkYield.containsKey(imported_semenDTO.progenyMilkYield)) {
						mapOfImported_semenDTOToprogenyMilkYield.put(imported_semenDTO.progenyMilkYield, new HashSet<>());
					}
					mapOfImported_semenDTOToprogenyMilkYield.get(imported_semenDTO.progenyMilkYield).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOTobreed.containsKey(imported_semenDTO.breed)) {
						mapOfImported_semenDTOTobreed.put(imported_semenDTO.breed, new HashSet<>());
					}
					mapOfImported_semenDTOTobreed.get(imported_semenDTO.breed).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOTodateOfEntry.containsKey(imported_semenDTO.dateOfEntry)) {
						mapOfImported_semenDTOTodateOfEntry.put(imported_semenDTO.dateOfEntry, new HashSet<>());
					}
					mapOfImported_semenDTOTodateOfEntry.get(imported_semenDTO.dateOfEntry).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOToreceivedAmount.containsKey(imported_semenDTO.receivedAmount)) {
						mapOfImported_semenDTOToreceivedAmount.put(imported_semenDTO.receivedAmount, new HashSet<>());
					}
					mapOfImported_semenDTOToreceivedAmount.get(imported_semenDTO.receivedAmount).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOTodistributedAmount.containsKey(imported_semenDTO.distributedAmount)) {
						mapOfImported_semenDTOTodistributedAmount.put(imported_semenDTO.distributedAmount, new HashSet<>());
					}
					mapOfImported_semenDTOTodistributedAmount.get(imported_semenDTO.distributedAmount).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOTotoWhomDistributed.containsKey(imported_semenDTO.toWhomDistributed)) {
						mapOfImported_semenDTOTotoWhomDistributed.put(imported_semenDTO.toWhomDistributed, new HashSet<>());
					}
					mapOfImported_semenDTOTotoWhomDistributed.get(imported_semenDTO.toWhomDistributed).add(imported_semenDTO);
					
					if( ! mapOfImported_semenDTOTodescription.containsKey(imported_semenDTO.description)) {
						mapOfImported_semenDTOTodescription.put(imported_semenDTO.description, new HashSet<>());
					}
					mapOfImported_semenDTOTodescription.get(imported_semenDTO.description).add(imported_semenDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Imported_semenDTO> getImported_semenList() {
		List <Imported_semenDTO> imported_semens = new ArrayList<Imported_semenDTO>(this.mapOfImported_semenDTOToiD.values());
		return imported_semens;
	}
	
	
	public Imported_semenDTO getImported_semenDTOByID( long ID){
		return mapOfImported_semenDTOToiD.get(ID);
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOBybull_tag(String bull_tag) {
		return new ArrayList<>( mapOfImported_semenDTOTobullTag.getOrDefault(bull_tag,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOBydate_of_birth(long date_of_birth) {
		return new ArrayList<>( mapOfImported_semenDTOTodateOfBirth.getOrDefault(date_of_birth,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOBydam(String dam) {
		return new ArrayList<>( mapOfImported_semenDTOTodam.getOrDefault(dam,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOBysire(String sire) {
		return new ArrayList<>( mapOfImported_semenDTOTosire.getOrDefault(sire,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOBymaternal_grand_dam(String maternal_grand_dam) {
		return new ArrayList<>( mapOfImported_semenDTOTomaternalGrandDam.getOrDefault(maternal_grand_dam,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOBymaternal_grand_sire(String maternal_grand_sire) {
		return new ArrayList<>( mapOfImported_semenDTOTomaternalGrandSire.getOrDefault(maternal_grand_sire,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOBypaternal_grand_dam(String paternal_grand_dam) {
		return new ArrayList<>( mapOfImported_semenDTOTopaternalGrandDam.getOrDefault(paternal_grand_dam,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOBypaternal_grand_sire(String paternal_grand_sire) {
		return new ArrayList<>( mapOfImported_semenDTOTopaternalGrandSire.getOrDefault(paternal_grand_sire,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOBymilk_yield_of_dam(int milk_yield_of_dam) {
		return new ArrayList<>( mapOfImported_semenDTOTomilkYieldOfDam.getOrDefault(milk_yield_of_dam,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOBymilk_yield_of_sires_dam(int milk_yield_of_sires_dam) {
		return new ArrayList<>( mapOfImported_semenDTOTomilkYieldOfSiresDam.getOrDefault(milk_yield_of_sires_dam,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOByprogeny_milk_yield(int progeny_milk_yield) {
		return new ArrayList<>( mapOfImported_semenDTOToprogenyMilkYield.getOrDefault(progeny_milk_yield,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOBybreed(String breed) {
		return new ArrayList<>( mapOfImported_semenDTOTobreed.getOrDefault(breed,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOBydate_of_entry(long date_of_entry) {
		return new ArrayList<>( mapOfImported_semenDTOTodateOfEntry.getOrDefault(date_of_entry,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOByreceived_amount(int received_amount) {
		return new ArrayList<>( mapOfImported_semenDTOToreceivedAmount.getOrDefault(received_amount,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOBydistributed_amount(int distributed_amount) {
		return new ArrayList<>( mapOfImported_semenDTOTodistributedAmount.getOrDefault(distributed_amount,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOByto_whom_distributed(String to_whom_distributed) {
		return new ArrayList<>( mapOfImported_semenDTOTotoWhomDistributed.getOrDefault(to_whom_distributed,new HashSet<>()));
	}
	
	
	public List<Imported_semenDTO> getImported_semenDTOBydescription(String description) {
		return new ArrayList<>( mapOfImported_semenDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "imported_semen";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


