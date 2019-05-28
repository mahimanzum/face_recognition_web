package bull_transfer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Bull_transferRepository implements Repository {
	Bull_transferDAO bull_transferDAO = new Bull_transferDAO();
	
	
	static Logger logger = Logger.getLogger(Bull_transferRepository.class);
	Map<Long, Bull_transferDTO>mapOfBull_transferDTOToiD;
	Map<Long, Set<Bull_transferDTO> >mapOfBull_transferDTOTodateOfTransfer;
	Map<Integer, Set<Bull_transferDTO> >mapOfBull_transferDTOTobullType;
	Map<Integer, Set<Bull_transferDTO> >mapOfBull_transferDTOTofromCentreType;
	Map<Integer, Set<Bull_transferDTO> >mapOfBull_transferDTOTotoCentreType;
	Map<Long, Set<Bull_transferDTO> >mapOfBull_transferDTOToentryDate;
	Map<Long, Set<Bull_transferDTO> >mapOfBull_transferDTOToexitDate;
	Map<String, Set<Bull_transferDTO> >mapOfBull_transferDTOTodescription;
	Map<Integer, Set<Bull_transferDTO> >mapOfBull_transferDTOToapprovalStatusType;


	static Bull_transferRepository instance = null;  
	private Bull_transferRepository(){
		mapOfBull_transferDTOToiD = new ConcurrentHashMap<>();
		mapOfBull_transferDTOTodateOfTransfer = new ConcurrentHashMap<>();
		mapOfBull_transferDTOTobullType = new ConcurrentHashMap<>();
		mapOfBull_transferDTOTofromCentreType = new ConcurrentHashMap<>();
		mapOfBull_transferDTOTotoCentreType = new ConcurrentHashMap<>();
		mapOfBull_transferDTOToentryDate = new ConcurrentHashMap<>();
		mapOfBull_transferDTOToexitDate = new ConcurrentHashMap<>();
		mapOfBull_transferDTOTodescription = new ConcurrentHashMap<>();
		mapOfBull_transferDTOToapprovalStatusType = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Bull_transferRepository getInstance(){
		if (instance == null){
			instance = new Bull_transferRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Bull_transferDTO> bull_transferDTOs = bull_transferDAO.getAllBull_transfer(reloadAll);
			for(Bull_transferDTO bull_transferDTO : bull_transferDTOs) {
				Bull_transferDTO oldBull_transferDTO = getBull_transferDTOByID(bull_transferDTO.iD);
				if( oldBull_transferDTO != null ) {
					mapOfBull_transferDTOToiD.remove(oldBull_transferDTO.iD);
				
					if(mapOfBull_transferDTOTodateOfTransfer.containsKey(oldBull_transferDTO.dateOfTransfer)) {
						mapOfBull_transferDTOTodateOfTransfer.get(oldBull_transferDTO.dateOfTransfer).remove(oldBull_transferDTO);
					}
					if(mapOfBull_transferDTOTodateOfTransfer.get(oldBull_transferDTO.dateOfTransfer).isEmpty()) {
						mapOfBull_transferDTOTodateOfTransfer.remove(oldBull_transferDTO.dateOfTransfer);
					}
					
					if(mapOfBull_transferDTOTobullType.containsKey(oldBull_transferDTO.bullType)) {
						mapOfBull_transferDTOTobullType.get(oldBull_transferDTO.bullType).remove(oldBull_transferDTO);
					}
					if(mapOfBull_transferDTOTobullType.get(oldBull_transferDTO.bullType).isEmpty()) {
						mapOfBull_transferDTOTobullType.remove(oldBull_transferDTO.bullType);
					}
					
					if(mapOfBull_transferDTOTofromCentreType.containsKey(oldBull_transferDTO.fromCentreType)) {
						mapOfBull_transferDTOTofromCentreType.get(oldBull_transferDTO.fromCentreType).remove(oldBull_transferDTO);
					}
					if(mapOfBull_transferDTOTofromCentreType.get(oldBull_transferDTO.fromCentreType).isEmpty()) {
						mapOfBull_transferDTOTofromCentreType.remove(oldBull_transferDTO.fromCentreType);
					}
					
					if(mapOfBull_transferDTOTotoCentreType.containsKey(oldBull_transferDTO.toCentreType)) {
						mapOfBull_transferDTOTotoCentreType.get(oldBull_transferDTO.toCentreType).remove(oldBull_transferDTO);
					}
					if(mapOfBull_transferDTOTotoCentreType.get(oldBull_transferDTO.toCentreType).isEmpty()) {
						mapOfBull_transferDTOTotoCentreType.remove(oldBull_transferDTO.toCentreType);
					}
					
					if(mapOfBull_transferDTOToentryDate.containsKey(oldBull_transferDTO.entryDate)) {
						mapOfBull_transferDTOToentryDate.get(oldBull_transferDTO.entryDate).remove(oldBull_transferDTO);
					}
					if(mapOfBull_transferDTOToentryDate.get(oldBull_transferDTO.entryDate).isEmpty()) {
						mapOfBull_transferDTOToentryDate.remove(oldBull_transferDTO.entryDate);
					}
					
					if(mapOfBull_transferDTOToexitDate.containsKey(oldBull_transferDTO.exitDate)) {
						mapOfBull_transferDTOToexitDate.get(oldBull_transferDTO.exitDate).remove(oldBull_transferDTO);
					}
					if(mapOfBull_transferDTOToexitDate.get(oldBull_transferDTO.exitDate).isEmpty()) {
						mapOfBull_transferDTOToexitDate.remove(oldBull_transferDTO.exitDate);
					}
					
					if(mapOfBull_transferDTOTodescription.containsKey(oldBull_transferDTO.description)) {
						mapOfBull_transferDTOTodescription.get(oldBull_transferDTO.description).remove(oldBull_transferDTO);
					}
					if(mapOfBull_transferDTOTodescription.get(oldBull_transferDTO.description).isEmpty()) {
						mapOfBull_transferDTOTodescription.remove(oldBull_transferDTO.description);
					}
					
					if(mapOfBull_transferDTOToapprovalStatusType.containsKey(oldBull_transferDTO.approvalStatusType)) {
						mapOfBull_transferDTOToapprovalStatusType.get(oldBull_transferDTO.approvalStatusType).remove(oldBull_transferDTO);
					}
					if(mapOfBull_transferDTOToapprovalStatusType.get(oldBull_transferDTO.approvalStatusType).isEmpty()) {
						mapOfBull_transferDTOToapprovalStatusType.remove(oldBull_transferDTO.approvalStatusType);
					}
					
					
				}
				if(bull_transferDTO.isDeleted == false) 
				{
					
					mapOfBull_transferDTOToiD.put(bull_transferDTO.iD, bull_transferDTO);
				
					if( ! mapOfBull_transferDTOTodateOfTransfer.containsKey(bull_transferDTO.dateOfTransfer)) {
						mapOfBull_transferDTOTodateOfTransfer.put(bull_transferDTO.dateOfTransfer, new HashSet<>());
					}
					mapOfBull_transferDTOTodateOfTransfer.get(bull_transferDTO.dateOfTransfer).add(bull_transferDTO);
					
					if( ! mapOfBull_transferDTOTobullType.containsKey(bull_transferDTO.bullType)) {
						mapOfBull_transferDTOTobullType.put(bull_transferDTO.bullType, new HashSet<>());
					}
					mapOfBull_transferDTOTobullType.get(bull_transferDTO.bullType).add(bull_transferDTO);
					
					if( ! mapOfBull_transferDTOTofromCentreType.containsKey(bull_transferDTO.fromCentreType)) {
						mapOfBull_transferDTOTofromCentreType.put(bull_transferDTO.fromCentreType, new HashSet<>());
					}
					mapOfBull_transferDTOTofromCentreType.get(bull_transferDTO.fromCentreType).add(bull_transferDTO);
					
					if( ! mapOfBull_transferDTOTotoCentreType.containsKey(bull_transferDTO.toCentreType)) {
						mapOfBull_transferDTOTotoCentreType.put(bull_transferDTO.toCentreType, new HashSet<>());
					}
					mapOfBull_transferDTOTotoCentreType.get(bull_transferDTO.toCentreType).add(bull_transferDTO);
					
					if( ! mapOfBull_transferDTOToentryDate.containsKey(bull_transferDTO.entryDate)) {
						mapOfBull_transferDTOToentryDate.put(bull_transferDTO.entryDate, new HashSet<>());
					}
					mapOfBull_transferDTOToentryDate.get(bull_transferDTO.entryDate).add(bull_transferDTO);
					
					if( ! mapOfBull_transferDTOToexitDate.containsKey(bull_transferDTO.exitDate)) {
						mapOfBull_transferDTOToexitDate.put(bull_transferDTO.exitDate, new HashSet<>());
					}
					mapOfBull_transferDTOToexitDate.get(bull_transferDTO.exitDate).add(bull_transferDTO);
					
					if( ! mapOfBull_transferDTOTodescription.containsKey(bull_transferDTO.description)) {
						mapOfBull_transferDTOTodescription.put(bull_transferDTO.description, new HashSet<>());
					}
					mapOfBull_transferDTOTodescription.get(bull_transferDTO.description).add(bull_transferDTO);
					
					if( ! mapOfBull_transferDTOToapprovalStatusType.containsKey(bull_transferDTO.approvalStatusType)) {
						mapOfBull_transferDTOToapprovalStatusType.put(bull_transferDTO.approvalStatusType, new HashSet<>());
					}
					mapOfBull_transferDTOToapprovalStatusType.get(bull_transferDTO.approvalStatusType).add(bull_transferDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Bull_transferDTO> getBull_transferList() {
		List <Bull_transferDTO> bull_transfers = new ArrayList<Bull_transferDTO>(this.mapOfBull_transferDTOToiD.values());
		return bull_transfers;
	}
	
	
	public Bull_transferDTO getBull_transferDTOByID( long ID){
		return mapOfBull_transferDTOToiD.get(ID);
	}
	
	
	public List<Bull_transferDTO> getBull_transferDTOBydate_of_transfer(long date_of_transfer) {
		return new ArrayList<>( mapOfBull_transferDTOTodateOfTransfer.getOrDefault(date_of_transfer,new HashSet<>()));
	}
	
	
	public List<Bull_transferDTO> getBull_transferDTOBybull_type(int bull_type) {
		return new ArrayList<>( mapOfBull_transferDTOTobullType.getOrDefault(bull_type,new HashSet<>()));
	}
	
	
	public List<Bull_transferDTO> getBull_transferDTOByfrom_centre_type(int from_centre_type) {
		return new ArrayList<>( mapOfBull_transferDTOTofromCentreType.getOrDefault(from_centre_type,new HashSet<>()));
	}
	
	
	public List<Bull_transferDTO> getBull_transferDTOByto_centre_type(int to_centre_type) {
		return new ArrayList<>( mapOfBull_transferDTOTotoCentreType.getOrDefault(to_centre_type,new HashSet<>()));
	}
	
	
	public List<Bull_transferDTO> getBull_transferDTOByentry_date(long entry_date) {
		return new ArrayList<>( mapOfBull_transferDTOToentryDate.getOrDefault(entry_date,new HashSet<>()));
	}
	
	
	public List<Bull_transferDTO> getBull_transferDTOByexit_date(long exit_date) {
		return new ArrayList<>( mapOfBull_transferDTOToexitDate.getOrDefault(exit_date,new HashSet<>()));
	}
	
	
	public List<Bull_transferDTO> getBull_transferDTOBydescription(String description) {
		return new ArrayList<>( mapOfBull_transferDTOTodescription.getOrDefault(description,new HashSet<>()));
	}
	
	
	public List<Bull_transferDTO> getBull_transferDTOByapproval_status_type(int approval_status_type) {
		return new ArrayList<>( mapOfBull_transferDTOToapprovalStatusType.getOrDefault(approval_status_type,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "bull_transfer";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


