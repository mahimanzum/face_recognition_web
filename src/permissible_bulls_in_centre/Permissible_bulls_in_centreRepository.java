package permissible_bulls_in_centre;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Permissible_bulls_in_centreRepository implements Repository {
	Permissible_bulls_in_centreDAO permissible_bulls_in_centreDAO = new Permissible_bulls_in_centreDAO();
	
	
	static Logger logger = Logger.getLogger(Permissible_bulls_in_centreRepository.class);
	Map<Long, Permissible_bulls_in_centreDTO>mapOfPermissible_bulls_in_centreDTOToiD;
	Map<Integer, Set<Permissible_bulls_in_centreDTO> >mapOfPermissible_bulls_in_centreDTOTobullType;
	Map<Integer, Set<Permissible_bulls_in_centreDTO> >mapOfPermissible_bulls_in_centreDTOTocentreType;
	Map<Long, Set<Permissible_bulls_in_centreDTO> >mapOfPermissible_bulls_in_centreDTOTodateOfEntry;
	Map<Long, Set<Permissible_bulls_in_centreDTO> >mapOfPermissible_bulls_in_centreDTOTodateOfExpiration;
	Map<String, Set<Permissible_bulls_in_centreDTO> >mapOfPermissible_bulls_in_centreDTOTodescription;


	static Permissible_bulls_in_centreRepository instance = null;  
	private Permissible_bulls_in_centreRepository(){
		mapOfPermissible_bulls_in_centreDTOToiD = new ConcurrentHashMap<>();
		mapOfPermissible_bulls_in_centreDTOTobullType = new ConcurrentHashMap<>();
		mapOfPermissible_bulls_in_centreDTOTocentreType = new ConcurrentHashMap<>();
		mapOfPermissible_bulls_in_centreDTOTodateOfEntry = new ConcurrentHashMap<>();
		mapOfPermissible_bulls_in_centreDTOTodateOfExpiration = new ConcurrentHashMap<>();
		mapOfPermissible_bulls_in_centreDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Permissible_bulls_in_centreRepository getInstance(){
		if (instance == null){
			instance = new Permissible_bulls_in_centreRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Permissible_bulls_in_centreDTO> permissible_bulls_in_centreDTOs = permissible_bulls_in_centreDAO.getAllPermissible_bulls_in_centre(reloadAll);
			for(Permissible_bulls_in_centreDTO permissible_bulls_in_centreDTO : permissible_bulls_in_centreDTOs) {
				Permissible_bulls_in_centreDTO oldPermissible_bulls_in_centreDTO = getPermissible_bulls_in_centreDTOByID(permissible_bulls_in_centreDTO.iD);
				if( oldPermissible_bulls_in_centreDTO != null ) {
					mapOfPermissible_bulls_in_centreDTOToiD.remove(oldPermissible_bulls_in_centreDTO.iD);
				
					if(mapOfPermissible_bulls_in_centreDTOTobullType.containsKey(oldPermissible_bulls_in_centreDTO.bullType)) {
						mapOfPermissible_bulls_in_centreDTOTobullType.get(oldPermissible_bulls_in_centreDTO.bullType).remove(oldPermissible_bulls_in_centreDTO);
					}
					if(mapOfPermissible_bulls_in_centreDTOTobullType.get(oldPermissible_bulls_in_centreDTO.bullType).isEmpty()) {
						mapOfPermissible_bulls_in_centreDTOTobullType.remove(oldPermissible_bulls_in_centreDTO.bullType);
					}
					
					if(mapOfPermissible_bulls_in_centreDTOTocentreType.containsKey(oldPermissible_bulls_in_centreDTO.centreType)) {
						mapOfPermissible_bulls_in_centreDTOTocentreType.get(oldPermissible_bulls_in_centreDTO.centreType).remove(oldPermissible_bulls_in_centreDTO);
					}
					if(mapOfPermissible_bulls_in_centreDTOTocentreType.get(oldPermissible_bulls_in_centreDTO.centreType).isEmpty()) {
						mapOfPermissible_bulls_in_centreDTOTocentreType.remove(oldPermissible_bulls_in_centreDTO.centreType);
					}
					
					if(mapOfPermissible_bulls_in_centreDTOTodateOfEntry.containsKey(oldPermissible_bulls_in_centreDTO.dateOfEntry)) {
						mapOfPermissible_bulls_in_centreDTOTodateOfEntry.get(oldPermissible_bulls_in_centreDTO.dateOfEntry).remove(oldPermissible_bulls_in_centreDTO);
					}
					if(mapOfPermissible_bulls_in_centreDTOTodateOfEntry.get(oldPermissible_bulls_in_centreDTO.dateOfEntry).isEmpty()) {
						mapOfPermissible_bulls_in_centreDTOTodateOfEntry.remove(oldPermissible_bulls_in_centreDTO.dateOfEntry);
					}
					
					if(mapOfPermissible_bulls_in_centreDTOTodateOfExpiration.containsKey(oldPermissible_bulls_in_centreDTO.dateOfExpiration)) {
						mapOfPermissible_bulls_in_centreDTOTodateOfExpiration.get(oldPermissible_bulls_in_centreDTO.dateOfExpiration).remove(oldPermissible_bulls_in_centreDTO);
					}
					if(mapOfPermissible_bulls_in_centreDTOTodateOfExpiration.get(oldPermissible_bulls_in_centreDTO.dateOfExpiration).isEmpty()) {
						mapOfPermissible_bulls_in_centreDTOTodateOfExpiration.remove(oldPermissible_bulls_in_centreDTO.dateOfExpiration);
					}
					
					if(mapOfPermissible_bulls_in_centreDTOTodescription.containsKey(oldPermissible_bulls_in_centreDTO.description)) {
						mapOfPermissible_bulls_in_centreDTOTodescription.get(oldPermissible_bulls_in_centreDTO.description).remove(oldPermissible_bulls_in_centreDTO);
					}
					if(mapOfPermissible_bulls_in_centreDTOTodescription.get(oldPermissible_bulls_in_centreDTO.description).isEmpty()) {
						mapOfPermissible_bulls_in_centreDTOTodescription.remove(oldPermissible_bulls_in_centreDTO.description);
					}
					
					
				}
				if(permissible_bulls_in_centreDTO.isDeleted == false) 
				{
					
					mapOfPermissible_bulls_in_centreDTOToiD.put(permissible_bulls_in_centreDTO.iD, permissible_bulls_in_centreDTO);
				
					if( ! mapOfPermissible_bulls_in_centreDTOTobullType.containsKey(permissible_bulls_in_centreDTO.bullType)) {
						mapOfPermissible_bulls_in_centreDTOTobullType.put(permissible_bulls_in_centreDTO.bullType, new HashSet<>());
					}
					mapOfPermissible_bulls_in_centreDTOTobullType.get(permissible_bulls_in_centreDTO.bullType).add(permissible_bulls_in_centreDTO);
					
					if( ! mapOfPermissible_bulls_in_centreDTOTocentreType.containsKey(permissible_bulls_in_centreDTO.centreType)) {
						mapOfPermissible_bulls_in_centreDTOTocentreType.put(permissible_bulls_in_centreDTO.centreType, new HashSet<>());
					}
					mapOfPermissible_bulls_in_centreDTOTocentreType.get(permissible_bulls_in_centreDTO.centreType).add(permissible_bulls_in_centreDTO);
					
					if( ! mapOfPermissible_bulls_in_centreDTOTodateOfEntry.containsKey(permissible_bulls_in_centreDTO.dateOfEntry)) {
						mapOfPermissible_bulls_in_centreDTOTodateOfEntry.put(permissible_bulls_in_centreDTO.dateOfEntry, new HashSet<>());
					}
					mapOfPermissible_bulls_in_centreDTOTodateOfEntry.get(permissible_bulls_in_centreDTO.dateOfEntry).add(permissible_bulls_in_centreDTO);
					
					if( ! mapOfPermissible_bulls_in_centreDTOTodateOfExpiration.containsKey(permissible_bulls_in_centreDTO.dateOfExpiration)) {
						mapOfPermissible_bulls_in_centreDTOTodateOfExpiration.put(permissible_bulls_in_centreDTO.dateOfExpiration, new HashSet<>());
					}
					mapOfPermissible_bulls_in_centreDTOTodateOfExpiration.get(permissible_bulls_in_centreDTO.dateOfExpiration).add(permissible_bulls_in_centreDTO);
					
					if( ! mapOfPermissible_bulls_in_centreDTOTodescription.containsKey(permissible_bulls_in_centreDTO.description)) {
						mapOfPermissible_bulls_in_centreDTOTodescription.put(permissible_bulls_in_centreDTO.description, new HashSet<>());
					}
					mapOfPermissible_bulls_in_centreDTOTodescription.get(permissible_bulls_in_centreDTO.description).add(permissible_bulls_in_centreDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Permissible_bulls_in_centreDTO> getPermissible_bulls_in_centreList() {
		List <Permissible_bulls_in_centreDTO> permissible_bulls_in_centres = new ArrayList<Permissible_bulls_in_centreDTO>(this.mapOfPermissible_bulls_in_centreDTOToiD.values());
		return permissible_bulls_in_centres;
	}
	
	
	public Permissible_bulls_in_centreDTO getPermissible_bulls_in_centreDTOByID( long ID){
		return mapOfPermissible_bulls_in_centreDTOToiD.get(ID);
	}
	
	
	public List<Permissible_bulls_in_centreDTO> getPermissible_bulls_in_centreDTOBybull_type(int bull_type) {
		return new ArrayList<>( mapOfPermissible_bulls_in_centreDTOTobullType.getOrDefault(bull_type,new HashSet<>()));
	}
	
	
	public List<Permissible_bulls_in_centreDTO> getPermissible_bulls_in_centreDTOBycentre_type(int centre_type) {
		return new ArrayList<>( mapOfPermissible_bulls_in_centreDTOTocentreType.getOrDefault(centre_type,new HashSet<>()));
	}
	
	
	public List<Permissible_bulls_in_centreDTO> getPermissible_bulls_in_centreDTOBydate_of_entry(long date_of_entry) {
		return new ArrayList<>( mapOfPermissible_bulls_in_centreDTOTodateOfEntry.getOrDefault(date_of_entry,new HashSet<>()));
	}
	
	
	public List<Permissible_bulls_in_centreDTO> getPermissible_bulls_in_centreDTOBydate_of_expiration(long date_of_expiration) {
		return new ArrayList<>( mapOfPermissible_bulls_in_centreDTOTodateOfExpiration.getOrDefault(date_of_expiration,new HashSet<>()));
	}
	
	
	public List<Permissible_bulls_in_centreDTO> getPermissible_bulls_in_centreDTOBydescription(String description) {
		return new ArrayList<>( mapOfPermissible_bulls_in_centreDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "permissible_bulls_in_centre";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


