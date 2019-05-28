package grs_top_layer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class Grs_top_layerRepository implements Repository {
	Grs_top_layerDAO grs_top_layerDAO = new Grs_top_layerDAO();
	
	
	static Logger logger = Logger.getLogger(Grs_top_layerRepository.class);
	Map<Long, Grs_top_layerDTO>mapOfGrs_top_layerDTOToiD;
	Map<String, Set<Grs_top_layerDTO> >mapOfGrs_top_layerDTOTonameEn;
	Map<String, Set<Grs_top_layerDTO> >mapOfGrs_top_layerDTOTonameBn;
	Map<Integer, Set<Grs_top_layerDTO> >mapOfGrs_top_layerDTOTolayerNumber;
	Map<String, Set<Grs_top_layerDTO> >mapOfGrs_top_layerDTOTodescription;


	static Grs_top_layerRepository instance = null;  
	private Grs_top_layerRepository(){
		mapOfGrs_top_layerDTOToiD = new ConcurrentHashMap<>();
		mapOfGrs_top_layerDTOTonameEn = new ConcurrentHashMap<>();
		mapOfGrs_top_layerDTOTonameBn = new ConcurrentHashMap<>();
		mapOfGrs_top_layerDTOTolayerNumber = new ConcurrentHashMap<>();
		mapOfGrs_top_layerDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static Grs_top_layerRepository getInstance(){
		if (instance == null){
			instance = new Grs_top_layerRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<Grs_top_layerDTO> grs_top_layerDTOs = grs_top_layerDAO.getAllGrs_top_layer(reloadAll);
			for(Grs_top_layerDTO grs_top_layerDTO : grs_top_layerDTOs) {
				Grs_top_layerDTO oldGrs_top_layerDTO = getGrs_top_layerDTOByID(grs_top_layerDTO.iD);
				if( oldGrs_top_layerDTO != null ) {
					mapOfGrs_top_layerDTOToiD.remove(oldGrs_top_layerDTO.iD);
				
					if(mapOfGrs_top_layerDTOTonameEn.containsKey(oldGrs_top_layerDTO.nameEn)) {
						mapOfGrs_top_layerDTOTonameEn.get(oldGrs_top_layerDTO.nameEn).remove(oldGrs_top_layerDTO);
					}
					if(mapOfGrs_top_layerDTOTonameEn.get(oldGrs_top_layerDTO.nameEn).isEmpty()) {
						mapOfGrs_top_layerDTOTonameEn.remove(oldGrs_top_layerDTO.nameEn);
					}
					
					if(mapOfGrs_top_layerDTOTonameBn.containsKey(oldGrs_top_layerDTO.nameBn)) {
						mapOfGrs_top_layerDTOTonameBn.get(oldGrs_top_layerDTO.nameBn).remove(oldGrs_top_layerDTO);
					}
					if(mapOfGrs_top_layerDTOTonameBn.get(oldGrs_top_layerDTO.nameBn).isEmpty()) {
						mapOfGrs_top_layerDTOTonameBn.remove(oldGrs_top_layerDTO.nameBn);
					}
					
					if(mapOfGrs_top_layerDTOTolayerNumber.containsKey(oldGrs_top_layerDTO.layerNumber)) {
						mapOfGrs_top_layerDTOTolayerNumber.get(oldGrs_top_layerDTO.layerNumber).remove(oldGrs_top_layerDTO);
					}
					if(mapOfGrs_top_layerDTOTolayerNumber.get(oldGrs_top_layerDTO.layerNumber).isEmpty()) {
						mapOfGrs_top_layerDTOTolayerNumber.remove(oldGrs_top_layerDTO.layerNumber);
					}
					
					if(mapOfGrs_top_layerDTOTodescription.containsKey(oldGrs_top_layerDTO.description)) {
						mapOfGrs_top_layerDTOTodescription.get(oldGrs_top_layerDTO.description).remove(oldGrs_top_layerDTO);
					}
					if(mapOfGrs_top_layerDTOTodescription.get(oldGrs_top_layerDTO.description).isEmpty()) {
						mapOfGrs_top_layerDTOTodescription.remove(oldGrs_top_layerDTO.description);
					}
					
					
				}
				if(grs_top_layerDTO.isDeleted == false) 
				{
					
					mapOfGrs_top_layerDTOToiD.put(grs_top_layerDTO.iD, grs_top_layerDTO);
				
					if( ! mapOfGrs_top_layerDTOTonameEn.containsKey(grs_top_layerDTO.nameEn)) {
						mapOfGrs_top_layerDTOTonameEn.put(grs_top_layerDTO.nameEn, new HashSet<>());
					}
					mapOfGrs_top_layerDTOTonameEn.get(grs_top_layerDTO.nameEn).add(grs_top_layerDTO);
					
					if( ! mapOfGrs_top_layerDTOTonameBn.containsKey(grs_top_layerDTO.nameBn)) {
						mapOfGrs_top_layerDTOTonameBn.put(grs_top_layerDTO.nameBn, new HashSet<>());
					}
					mapOfGrs_top_layerDTOTonameBn.get(grs_top_layerDTO.nameBn).add(grs_top_layerDTO);
					
					if( ! mapOfGrs_top_layerDTOTolayerNumber.containsKey(grs_top_layerDTO.layerNumber)) {
						mapOfGrs_top_layerDTOTolayerNumber.put(grs_top_layerDTO.layerNumber, new HashSet<>());
					}
					mapOfGrs_top_layerDTOTolayerNumber.get(grs_top_layerDTO.layerNumber).add(grs_top_layerDTO);
					
					if( ! mapOfGrs_top_layerDTOTodescription.containsKey(grs_top_layerDTO.description)) {
						mapOfGrs_top_layerDTOTodescription.put(grs_top_layerDTO.description, new HashSet<>());
					}
					mapOfGrs_top_layerDTOTodescription.get(grs_top_layerDTO.description).add(grs_top_layerDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<Grs_top_layerDTO> getGrs_top_layerList() {
		List <Grs_top_layerDTO> grs_top_layers = new ArrayList<Grs_top_layerDTO>(this.mapOfGrs_top_layerDTOToiD.values());
		return grs_top_layers;
	}
	
	
	public Grs_top_layerDTO getGrs_top_layerDTOByID( long ID){
		return mapOfGrs_top_layerDTOToiD.get(ID);
	}
	
	
	public List<Grs_top_layerDTO> getGrs_top_layerDTOByname_en(String name_en) {
		return new ArrayList<>( mapOfGrs_top_layerDTOTonameEn.getOrDefault(name_en,new HashSet<>()));
	}
	
	
	public List<Grs_top_layerDTO> getGrs_top_layerDTOByname_bn(String name_bn) {
		return new ArrayList<>( mapOfGrs_top_layerDTOTonameBn.getOrDefault(name_bn,new HashSet<>()));
	}
	
	
	public List<Grs_top_layerDTO> getGrs_top_layerDTOBylayer_number(int layer_number) {
		return new ArrayList<>( mapOfGrs_top_layerDTOTolayerNumber.getOrDefault(layer_number,new HashSet<>()));
	}
	
	
	public List<Grs_top_layerDTO> getGrs_top_layerDTOBydescription(String description) {
		return new ArrayList<>( mapOfGrs_top_layerDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "grs_top_layer";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


