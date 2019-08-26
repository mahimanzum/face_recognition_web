package facial_recognization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Facial_recognizationRepository implements Repository {
	Facial_recognizationDAO facial_recognizationDAO = new Facial_recognizationDAO();
	
	
	static Logger logger = Logger.getLogger(Facial_recognizationRepository.class);
	Map<Long, Facial_recognizationDTO>mapOfFacial_recognizationDTOToiD;
	Map<String, Set<Facial_recognizationDTO> >mapOfFacial_recognizationDTOToname;
	Map<String, Set<Facial_recognizationDTO> >mapOfFacial_recognizationDTOToaddress;
	Map<String, Set<Facial_recognizationDTO> >mapOfFacial_recognizationDTOTophone;
	Map<String, Set<Facial_recognizationDTO> >mapOfFacial_recognizationDTOToemail;
	Map<String, Set<Facial_recognizationDTO> >mapOfFacial_recognizationDTOToimage;


	static Facial_recognizationRepository instance = null;  
	private Facial_recognizationRepository(){
		mapOfFacial_recognizationDTOToiD = new ConcurrentHashMap<>();
		mapOfFacial_recognizationDTOToname = new ConcurrentHashMap<>();
		mapOfFacial_recognizationDTOToaddress = new ConcurrentHashMap<>();
		mapOfFacial_recognizationDTOTophone = new ConcurrentHashMap<>();
		mapOfFacial_recognizationDTOToemail = new ConcurrentHashMap<>();
		mapOfFacial_recognizationDTOToimage = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Facial_recognizationRepository getInstance(){
		if (instance == null){
			instance = new Facial_recognizationRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Facial_recognizationDTO> facial_recognizationDTOs = facial_recognizationDAO.getAllFacial_recognization(reloadAll);
			for(Facial_recognizationDTO facial_recognizationDTO : facial_recognizationDTOs) {
				Facial_recognizationDTO oldFacial_recognizationDTO = getFacial_recognizationDTOByID(facial_recognizationDTO.iD);
				if( oldFacial_recognizationDTO != null ) {
					mapOfFacial_recognizationDTOToiD.remove(oldFacial_recognizationDTO.iD);
				
					if(mapOfFacial_recognizationDTOToname.containsKey(oldFacial_recognizationDTO.name)) {
						mapOfFacial_recognizationDTOToname.get(oldFacial_recognizationDTO.name).remove(oldFacial_recognizationDTO);
					}
					if(mapOfFacial_recognizationDTOToname.get(oldFacial_recognizationDTO.name).isEmpty()) {
						mapOfFacial_recognizationDTOToname.remove(oldFacial_recognizationDTO.name);
					}
					
					if(mapOfFacial_recognizationDTOToaddress.containsKey(oldFacial_recognizationDTO.address)) {
						mapOfFacial_recognizationDTOToaddress.get(oldFacial_recognizationDTO.address).remove(oldFacial_recognizationDTO);
					}
					if(mapOfFacial_recognizationDTOToaddress.get(oldFacial_recognizationDTO.address).isEmpty()) {
						mapOfFacial_recognizationDTOToaddress.remove(oldFacial_recognizationDTO.address);
					}
					
					if(mapOfFacial_recognizationDTOTophone.containsKey(oldFacial_recognizationDTO.phone)) {
						mapOfFacial_recognizationDTOTophone.get(oldFacial_recognizationDTO.phone).remove(oldFacial_recognizationDTO);
					}
					if(mapOfFacial_recognizationDTOTophone.get(oldFacial_recognizationDTO.phone).isEmpty()) {
						mapOfFacial_recognizationDTOTophone.remove(oldFacial_recognizationDTO.phone);
					}
					
					if(mapOfFacial_recognizationDTOToemail.containsKey(oldFacial_recognizationDTO.email)) {
						mapOfFacial_recognizationDTOToemail.get(oldFacial_recognizationDTO.email).remove(oldFacial_recognizationDTO);
					}
					if(mapOfFacial_recognizationDTOToemail.get(oldFacial_recognizationDTO.email).isEmpty()) {
						mapOfFacial_recognizationDTOToemail.remove(oldFacial_recognizationDTO.email);
					}
					
					if(mapOfFacial_recognizationDTOToimage.containsKey(oldFacial_recognizationDTO.image)) {
						mapOfFacial_recognizationDTOToimage.get(oldFacial_recognizationDTO.image).remove(oldFacial_recognizationDTO);
					}
					if(mapOfFacial_recognizationDTOToimage.get(oldFacial_recognizationDTO.image).isEmpty()) {
						mapOfFacial_recognizationDTOToimage.remove(oldFacial_recognizationDTO.image);
					}
					
					
				}
				if(facial_recognizationDTO.isDeleted == false) 
				{
					
					mapOfFacial_recognizationDTOToiD.put(facial_recognizationDTO.iD, facial_recognizationDTO);
				
					if( ! mapOfFacial_recognizationDTOToname.containsKey(facial_recognizationDTO.name)) {
						mapOfFacial_recognizationDTOToname.put(facial_recognizationDTO.name, new HashSet<>());
					}
					mapOfFacial_recognizationDTOToname.get(facial_recognizationDTO.name).add(facial_recognizationDTO);
					
					if( ! mapOfFacial_recognizationDTOToaddress.containsKey(facial_recognizationDTO.address)) {
						mapOfFacial_recognizationDTOToaddress.put(facial_recognizationDTO.address, new HashSet<>());
					}
					mapOfFacial_recognizationDTOToaddress.get(facial_recognizationDTO.address).add(facial_recognizationDTO);
					
					if( ! mapOfFacial_recognizationDTOTophone.containsKey(facial_recognizationDTO.phone)) {
						mapOfFacial_recognizationDTOTophone.put(facial_recognizationDTO.phone, new HashSet<>());
					}
					mapOfFacial_recognizationDTOTophone.get(facial_recognizationDTO.phone).add(facial_recognizationDTO);
					
					if( ! mapOfFacial_recognizationDTOToemail.containsKey(facial_recognizationDTO.email)) {
						mapOfFacial_recognizationDTOToemail.put(facial_recognizationDTO.email, new HashSet<>());
					}
					mapOfFacial_recognizationDTOToemail.get(facial_recognizationDTO.email).add(facial_recognizationDTO);
					
					if( ! mapOfFacial_recognizationDTOToimage.containsKey(facial_recognizationDTO.image)) {
						mapOfFacial_recognizationDTOToimage.put(facial_recognizationDTO.image, new HashSet<>());
					}
					mapOfFacial_recognizationDTOToimage.get(facial_recognizationDTO.image).add(facial_recognizationDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Facial_recognizationDTO> getFacial_recognizationList() {
		List <Facial_recognizationDTO> facial_recognizations = new ArrayList<Facial_recognizationDTO>(this.mapOfFacial_recognizationDTOToiD.values());
		return facial_recognizations;
	}
	
	
	public Facial_recognizationDTO getFacial_recognizationDTOByID( long ID){
		return mapOfFacial_recognizationDTOToiD.get(ID);
	}
	
	
	public List<Facial_recognizationDTO> getFacial_recognizationDTOByname(String name) {
		return new ArrayList<>( mapOfFacial_recognizationDTOToname.getOrDefault(name,new HashSet<>()));
	}
	
	
	public List<Facial_recognizationDTO> getFacial_recognizationDTOByaddress(String address) {
		return new ArrayList<>( mapOfFacial_recognizationDTOToaddress.getOrDefault(address,new HashSet<>()));
	}
	
	
	public List<Facial_recognizationDTO> getFacial_recognizationDTOByphone(String phone) {
		return new ArrayList<>( mapOfFacial_recognizationDTOTophone.getOrDefault(phone,new HashSet<>()));
	}
	
	
	public List<Facial_recognizationDTO> getFacial_recognizationDTOByemail(String email) {
		return new ArrayList<>( mapOfFacial_recognizationDTOToemail.getOrDefault(email,new HashSet<>()));
	}
	
	
	public List<Facial_recognizationDTO> getFacial_recognizationDTOByimage(String image) {
		return new ArrayList<>( mapOfFacial_recognizationDTOToimage.getOrDefault(image,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "facial_recognization";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


