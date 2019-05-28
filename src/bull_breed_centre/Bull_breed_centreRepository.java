package bull_breed_centre;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Bull_breed_centreRepository implements Repository {
	Bull_breed_centreDAO bull_breed_centreDAO = new Bull_breed_centreDAO();
	
	
	static Logger logger = Logger.getLogger(Bull_breed_centreRepository.class);
	Map<Long, Bull_breed_centreDTO>mapOfBull_breed_centreDTOToiD;
	Map<Integer, Set<Bull_breed_centreDTO> >mapOfBull_breed_centreDTOTobullType;
	Map<Integer, Set<Bull_breed_centreDTO> >mapOfBull_breed_centreDTOTobreedType;
	Map<Integer, Set<Bull_breed_centreDTO> >mapOfBull_breed_centreDTOTocentreType;
	Map<Integer, Set<Bull_breed_centreDTO> >mapOfBull_breed_centreDTOTogrsOffice;
	Map<Long, Set<Bull_breed_centreDTO> >mapOfBull_breed_centreDTOTogrsOfficer;
	Map<String, Set<Bull_breed_centreDTO> >mapOfBull_breed_centreDTOToinfoFile;
	Map<String, Set<Bull_breed_centreDTO> >mapOfBull_breed_centreDTOTobullImage;
	Map<String, Set<Bull_breed_centreDTO> >mapOfBull_breed_centreDTOTodescription;


	static Bull_breed_centreRepository instance = null;  
	private Bull_breed_centreRepository(){
		mapOfBull_breed_centreDTOToiD = new ConcurrentHashMap<>();
		mapOfBull_breed_centreDTOTobullType = new ConcurrentHashMap<>();
		mapOfBull_breed_centreDTOTobreedType = new ConcurrentHashMap<>();
		mapOfBull_breed_centreDTOTocentreType = new ConcurrentHashMap<>();
		mapOfBull_breed_centreDTOTogrsOffice = new ConcurrentHashMap<>();
		mapOfBull_breed_centreDTOTogrsOfficer = new ConcurrentHashMap<>();
		mapOfBull_breed_centreDTOToinfoFile = new ConcurrentHashMap<>();
		mapOfBull_breed_centreDTOTobullImage = new ConcurrentHashMap<>();
		mapOfBull_breed_centreDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Bull_breed_centreRepository getInstance(){
		if (instance == null){
			instance = new Bull_breed_centreRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Bull_breed_centreDTO> bull_breed_centreDTOs = bull_breed_centreDAO.getAllBull_breed_centre(reloadAll);
			for(Bull_breed_centreDTO bull_breed_centreDTO : bull_breed_centreDTOs) {
				Bull_breed_centreDTO oldBull_breed_centreDTO = getBull_breed_centreDTOByID(bull_breed_centreDTO.iD);
				if( oldBull_breed_centreDTO != null ) {
					mapOfBull_breed_centreDTOToiD.remove(oldBull_breed_centreDTO.iD);
				
					if(mapOfBull_breed_centreDTOTobullType.containsKey(oldBull_breed_centreDTO.bullType)) {
						mapOfBull_breed_centreDTOTobullType.get(oldBull_breed_centreDTO.bullType).remove(oldBull_breed_centreDTO);
					}
					if(mapOfBull_breed_centreDTOTobullType.get(oldBull_breed_centreDTO.bullType).isEmpty()) {
						mapOfBull_breed_centreDTOTobullType.remove(oldBull_breed_centreDTO.bullType);
					}
					
					if(mapOfBull_breed_centreDTOTobreedType.containsKey(oldBull_breed_centreDTO.breedType)) {
						mapOfBull_breed_centreDTOTobreedType.get(oldBull_breed_centreDTO.breedType).remove(oldBull_breed_centreDTO);
					}
					if(mapOfBull_breed_centreDTOTobreedType.get(oldBull_breed_centreDTO.breedType).isEmpty()) {
						mapOfBull_breed_centreDTOTobreedType.remove(oldBull_breed_centreDTO.breedType);
					}
					
					if(mapOfBull_breed_centreDTOTocentreType.containsKey(oldBull_breed_centreDTO.centreType)) {
						mapOfBull_breed_centreDTOTocentreType.get(oldBull_breed_centreDTO.centreType).remove(oldBull_breed_centreDTO);
					}
					if(mapOfBull_breed_centreDTOTocentreType.get(oldBull_breed_centreDTO.centreType).isEmpty()) {
						mapOfBull_breed_centreDTOTocentreType.remove(oldBull_breed_centreDTO.centreType);
					}
					
					if(mapOfBull_breed_centreDTOTogrsOffice.containsKey(oldBull_breed_centreDTO.grsOffice)) {
						mapOfBull_breed_centreDTOTogrsOffice.get(oldBull_breed_centreDTO.grsOffice).remove(oldBull_breed_centreDTO);
					}
					if(mapOfBull_breed_centreDTOTogrsOffice.get(oldBull_breed_centreDTO.grsOffice).isEmpty()) {
						mapOfBull_breed_centreDTOTogrsOffice.remove(oldBull_breed_centreDTO.grsOffice);
					}
					
					if(mapOfBull_breed_centreDTOTogrsOfficer.containsKey(oldBull_breed_centreDTO.grsOfficer)) {
						mapOfBull_breed_centreDTOTogrsOfficer.get(oldBull_breed_centreDTO.grsOfficer).remove(oldBull_breed_centreDTO);
					}
					if(mapOfBull_breed_centreDTOTogrsOfficer.get(oldBull_breed_centreDTO.grsOfficer).isEmpty()) {
						mapOfBull_breed_centreDTOTogrsOfficer.remove(oldBull_breed_centreDTO.grsOfficer);
					}
					
					if(mapOfBull_breed_centreDTOToinfoFile.containsKey(oldBull_breed_centreDTO.infoFile)) {
						mapOfBull_breed_centreDTOToinfoFile.get(oldBull_breed_centreDTO.infoFile).remove(oldBull_breed_centreDTO);
					}
					if(mapOfBull_breed_centreDTOToinfoFile.get(oldBull_breed_centreDTO.infoFile).isEmpty()) {
						mapOfBull_breed_centreDTOToinfoFile.remove(oldBull_breed_centreDTO.infoFile);
					}
					
					if(mapOfBull_breed_centreDTOTobullImage.containsKey(oldBull_breed_centreDTO.bullImage)) {
						mapOfBull_breed_centreDTOTobullImage.get(oldBull_breed_centreDTO.bullImage).remove(oldBull_breed_centreDTO);
					}
					if(mapOfBull_breed_centreDTOTobullImage.get(oldBull_breed_centreDTO.bullImage).isEmpty()) {
						mapOfBull_breed_centreDTOTobullImage.remove(oldBull_breed_centreDTO.bullImage);
					}
					
					if(mapOfBull_breed_centreDTOTodescription.containsKey(oldBull_breed_centreDTO.description)) {
						mapOfBull_breed_centreDTOTodescription.get(oldBull_breed_centreDTO.description).remove(oldBull_breed_centreDTO);
					}
					if(mapOfBull_breed_centreDTOTodescription.get(oldBull_breed_centreDTO.description).isEmpty()) {
						mapOfBull_breed_centreDTOTodescription.remove(oldBull_breed_centreDTO.description);
					}
					
					
				}
				if(bull_breed_centreDTO.isDeleted == false) 
				{
					
					mapOfBull_breed_centreDTOToiD.put(bull_breed_centreDTO.iD, bull_breed_centreDTO);
				
					if( ! mapOfBull_breed_centreDTOTobullType.containsKey(bull_breed_centreDTO.bullType)) {
						mapOfBull_breed_centreDTOTobullType.put(bull_breed_centreDTO.bullType, new HashSet<>());
					}
					mapOfBull_breed_centreDTOTobullType.get(bull_breed_centreDTO.bullType).add(bull_breed_centreDTO);
					
					if( ! mapOfBull_breed_centreDTOTobreedType.containsKey(bull_breed_centreDTO.breedType)) {
						mapOfBull_breed_centreDTOTobreedType.put(bull_breed_centreDTO.breedType, new HashSet<>());
					}
					mapOfBull_breed_centreDTOTobreedType.get(bull_breed_centreDTO.breedType).add(bull_breed_centreDTO);
					
					if( ! mapOfBull_breed_centreDTOTocentreType.containsKey(bull_breed_centreDTO.centreType)) {
						mapOfBull_breed_centreDTOTocentreType.put(bull_breed_centreDTO.centreType, new HashSet<>());
					}
					mapOfBull_breed_centreDTOTocentreType.get(bull_breed_centreDTO.centreType).add(bull_breed_centreDTO);
					
					if( ! mapOfBull_breed_centreDTOTogrsOffice.containsKey(bull_breed_centreDTO.grsOffice)) {
						mapOfBull_breed_centreDTOTogrsOffice.put(bull_breed_centreDTO.grsOffice, new HashSet<>());
					}
					mapOfBull_breed_centreDTOTogrsOffice.get(bull_breed_centreDTO.grsOffice).add(bull_breed_centreDTO);
					
					if( ! mapOfBull_breed_centreDTOTogrsOfficer.containsKey(bull_breed_centreDTO.grsOfficer)) {
						mapOfBull_breed_centreDTOTogrsOfficer.put(bull_breed_centreDTO.grsOfficer, new HashSet<>());
					}
					mapOfBull_breed_centreDTOTogrsOfficer.get(bull_breed_centreDTO.grsOfficer).add(bull_breed_centreDTO);
					
					if( ! mapOfBull_breed_centreDTOToinfoFile.containsKey(bull_breed_centreDTO.infoFile)) {
						mapOfBull_breed_centreDTOToinfoFile.put(bull_breed_centreDTO.infoFile, new HashSet<>());
					}
					mapOfBull_breed_centreDTOToinfoFile.get(bull_breed_centreDTO.infoFile).add(bull_breed_centreDTO);
					
					if( ! mapOfBull_breed_centreDTOTobullImage.containsKey(bull_breed_centreDTO.bullImage)) {
						mapOfBull_breed_centreDTOTobullImage.put(bull_breed_centreDTO.bullImage, new HashSet<>());
					}
					mapOfBull_breed_centreDTOTobullImage.get(bull_breed_centreDTO.bullImage).add(bull_breed_centreDTO);
					
					if( ! mapOfBull_breed_centreDTOTodescription.containsKey(bull_breed_centreDTO.description)) {
						mapOfBull_breed_centreDTOTodescription.put(bull_breed_centreDTO.description, new HashSet<>());
					}
					mapOfBull_breed_centreDTOTodescription.get(bull_breed_centreDTO.description).add(bull_breed_centreDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Bull_breed_centreDTO> getBull_breed_centreList() {
		List <Bull_breed_centreDTO> bull_breed_centres = new ArrayList<Bull_breed_centreDTO>(this.mapOfBull_breed_centreDTOToiD.values());
		return bull_breed_centres;
	}
	
	
	public Bull_breed_centreDTO getBull_breed_centreDTOByID( long ID){
		return mapOfBull_breed_centreDTOToiD.get(ID);
	}
	
	
	public List<Bull_breed_centreDTO> getBull_breed_centreDTOBybull_type(int bull_type) {
		return new ArrayList<>( mapOfBull_breed_centreDTOTobullType.getOrDefault(bull_type,new HashSet<>()));
	}
	
	
	public List<Bull_breed_centreDTO> getBull_breed_centreDTOBybreed_type(int breed_type) {
		return new ArrayList<>( mapOfBull_breed_centreDTOTobreedType.getOrDefault(breed_type,new HashSet<>()));
	}
	
	
	public List<Bull_breed_centreDTO> getBull_breed_centreDTOBycentre_type(int centre_type) {
		return new ArrayList<>( mapOfBull_breed_centreDTOTocentreType.getOrDefault(centre_type,new HashSet<>()));
	}
	
	
	public List<Bull_breed_centreDTO> getBull_breed_centreDTOBygrs_office(int grs_office) {
		return new ArrayList<>( mapOfBull_breed_centreDTOTogrsOffice.getOrDefault(grs_office,new HashSet<>()));
	}
	
	
	public List<Bull_breed_centreDTO> getBull_breed_centreDTOBygrs_officer(long grs_officer) {
		return new ArrayList<>( mapOfBull_breed_centreDTOTogrsOfficer.getOrDefault(grs_officer,new HashSet<>()));
	}
	
	
	public List<Bull_breed_centreDTO> getBull_breed_centreDTOByinfo_file(String info_file) {
		return new ArrayList<>( mapOfBull_breed_centreDTOToinfoFile.getOrDefault(info_file,new HashSet<>()));
	}
	
	
	public List<Bull_breed_centreDTO> getBull_breed_centreDTOBybull_image(String bull_image) {
		return new ArrayList<>( mapOfBull_breed_centreDTOTobullImage.getOrDefault(bull_image,new HashSet<>()));
	}
	
	
	public List<Bull_breed_centreDTO> getBull_breed_centreDTOBydescription(String description) {
		return new ArrayList<>( mapOfBull_breed_centreDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "bull_breed_centre";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


