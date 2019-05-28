package color;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class ColorRepository implements Repository {
	ColorDAO colorDAO = new ColorDAO();
	
	
	static Logger logger = Logger.getLogger(ColorRepository.class);
	Map<Long, ColorDTO>mapOfColorDTOToiD;
	Map<String, Set<ColorDTO> >mapOfColorDTOTonameEn;
	Map<String, Set<ColorDTO> >mapOfColorDTOTonameBn;
	Map<String, Set<ColorDTO> >mapOfColorDTOTodescription;


	static ColorRepository instance = null;  
	private ColorRepository(){
		mapOfColorDTOToiD = new ConcurrentHashMap<>();
		mapOfColorDTOTonameEn = new ConcurrentHashMap<>();
		mapOfColorDTOTonameBn = new ConcurrentHashMap<>();
		mapOfColorDTOTodescription = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static ColorRepository getInstance(){
		if (instance == null){
			instance = new ColorRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<ColorDTO> colorDTOs = colorDAO.getAllColor(reloadAll);
			for(ColorDTO colorDTO : colorDTOs) {
				ColorDTO oldColorDTO = getColorDTOByID(colorDTO.iD);
				if( oldColorDTO != null ) {
					mapOfColorDTOToiD.remove(oldColorDTO.iD);
				
					if(mapOfColorDTOTonameEn.containsKey(oldColorDTO.nameEn)) {
						mapOfColorDTOTonameEn.get(oldColorDTO.nameEn).remove(oldColorDTO);
					}
					if(mapOfColorDTOTonameEn.get(oldColorDTO.nameEn).isEmpty()) {
						mapOfColorDTOTonameEn.remove(oldColorDTO.nameEn);
					}
					
					if(mapOfColorDTOTonameBn.containsKey(oldColorDTO.nameBn)) {
						mapOfColorDTOTonameBn.get(oldColorDTO.nameBn).remove(oldColorDTO);
					}
					if(mapOfColorDTOTonameBn.get(oldColorDTO.nameBn).isEmpty()) {
						mapOfColorDTOTonameBn.remove(oldColorDTO.nameBn);
					}
					
					if(mapOfColorDTOTodescription.containsKey(oldColorDTO.description)) {
						mapOfColorDTOTodescription.get(oldColorDTO.description).remove(oldColorDTO);
					}
					if(mapOfColorDTOTodescription.get(oldColorDTO.description).isEmpty()) {
						mapOfColorDTOTodescription.remove(oldColorDTO.description);
					}
					
					
				}
				if(colorDTO.isDeleted == false) 
				{
					
					mapOfColorDTOToiD.put(colorDTO.iD, colorDTO);
				
					if( ! mapOfColorDTOTonameEn.containsKey(colorDTO.nameEn)) {
						mapOfColorDTOTonameEn.put(colorDTO.nameEn, new HashSet<>());
					}
					mapOfColorDTOTonameEn.get(colorDTO.nameEn).add(colorDTO);
					
					if( ! mapOfColorDTOTonameBn.containsKey(colorDTO.nameBn)) {
						mapOfColorDTOTonameBn.put(colorDTO.nameBn, new HashSet<>());
					}
					mapOfColorDTOTonameBn.get(colorDTO.nameBn).add(colorDTO);
					
					if( ! mapOfColorDTOTodescription.containsKey(colorDTO.description)) {
						mapOfColorDTOTodescription.put(colorDTO.description, new HashSet<>());
					}
					mapOfColorDTOTodescription.get(colorDTO.description).add(colorDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<ColorDTO> getColorList() {
		List <ColorDTO> colors = new ArrayList<ColorDTO>(this.mapOfColorDTOToiD.values());
		return colors;
	}
	
	
	public ColorDTO getColorDTOByID( long ID){
		return mapOfColorDTOToiD.get(ID);
	}
	
	
	public List<ColorDTO> getColorDTOByname_en(String name_en) {
		return new ArrayList<>( mapOfColorDTOTonameEn.getOrDefault(name_en,new HashSet<>()));
	}
	
	
	public List<ColorDTO> getColorDTOByname_bn(String name_bn) {
		return new ArrayList<>( mapOfColorDTOTonameBn.getOrDefault(name_bn,new HashSet<>()));
	}
	
	
	public List<ColorDTO> getColorDTOBydescription(String description) {
		return new ArrayList<>( mapOfColorDTOTodescription.getOrDefault(description,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "color";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


