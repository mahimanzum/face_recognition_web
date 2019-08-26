package approval_status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Approval_statusRepository implements Repository {
	Approval_statusDAO approval_statusDAO = new Approval_statusDAO();
	
	
	static Logger logger = Logger.getLogger(Approval_statusRepository.class);
	Map<Long, Approval_statusDTO>mapOfApproval_statusDTOToiD;
	Map<String, Set<Approval_statusDTO> >mapOfApproval_statusDTOTonameEn;
	Map<String, Set<Approval_statusDTO> >mapOfApproval_statusDTOTonameBn;


	static Approval_statusRepository instance = null;  
	private Approval_statusRepository(){
		mapOfApproval_statusDTOToiD = new ConcurrentHashMap<>();
		mapOfApproval_statusDTOTonameEn = new ConcurrentHashMap<>();
		mapOfApproval_statusDTOTonameBn = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Approval_statusRepository getInstance(){
		if (instance == null){
			instance = new Approval_statusRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Approval_statusDTO> approval_statusDTOs = approval_statusDAO.getAllApproval_status(reloadAll);
			for(Approval_statusDTO approval_statusDTO : approval_statusDTOs) {
				Approval_statusDTO oldApproval_statusDTO = getApproval_statusDTOByID(approval_statusDTO.iD);
				if( oldApproval_statusDTO != null ) {
					mapOfApproval_statusDTOToiD.remove(oldApproval_statusDTO.iD);
				
					if(mapOfApproval_statusDTOTonameEn.containsKey(oldApproval_statusDTO.nameEn)) {
						mapOfApproval_statusDTOTonameEn.get(oldApproval_statusDTO.nameEn).remove(oldApproval_statusDTO);
					}
					if(mapOfApproval_statusDTOTonameEn.get(oldApproval_statusDTO.nameEn).isEmpty()) {
						mapOfApproval_statusDTOTonameEn.remove(oldApproval_statusDTO.nameEn);
					}
					
					if(mapOfApproval_statusDTOTonameBn.containsKey(oldApproval_statusDTO.nameBn)) {
						mapOfApproval_statusDTOTonameBn.get(oldApproval_statusDTO.nameBn).remove(oldApproval_statusDTO);
					}
					if(mapOfApproval_statusDTOTonameBn.get(oldApproval_statusDTO.nameBn).isEmpty()) {
						mapOfApproval_statusDTOTonameBn.remove(oldApproval_statusDTO.nameBn);
					}
					
					
				}
				if(approval_statusDTO.isDeleted == false) 
				{
					
					mapOfApproval_statusDTOToiD.put(approval_statusDTO.iD, approval_statusDTO);
				
					if( ! mapOfApproval_statusDTOTonameEn.containsKey(approval_statusDTO.nameEn)) {
						mapOfApproval_statusDTOTonameEn.put(approval_statusDTO.nameEn, new HashSet<>());
					}
					mapOfApproval_statusDTOTonameEn.get(approval_statusDTO.nameEn).add(approval_statusDTO);
					
					if( ! mapOfApproval_statusDTOTonameBn.containsKey(approval_statusDTO.nameBn)) {
						mapOfApproval_statusDTOTonameBn.put(approval_statusDTO.nameBn, new HashSet<>());
					}
					mapOfApproval_statusDTOTonameBn.get(approval_statusDTO.nameBn).add(approval_statusDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Approval_statusDTO> getApproval_statusList() {
		List <Approval_statusDTO> approval_statuss = new ArrayList<Approval_statusDTO>(this.mapOfApproval_statusDTOToiD.values());
		return approval_statuss;
	}
	
	
	public Approval_statusDTO getApproval_statusDTOByID( long ID){
		return mapOfApproval_statusDTOToiD.get(ID);
	}
	
	
	public List<Approval_statusDTO> getApproval_statusDTOByname_en(String name_en) {
		return new ArrayList<>( mapOfApproval_statusDTOTonameEn.getOrDefault(name_en,new HashSet<>()));
	}
	
	
	public List<Approval_statusDTO> getApproval_statusDTOByname_bn(String name_bn) {
		return new ArrayList<>( mapOfApproval_statusDTOTonameBn.getOrDefault(name_bn,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "approval_status";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


