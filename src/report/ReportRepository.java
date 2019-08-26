package report;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import repository.Repository;
import repository.RepositoryManager;


public class ReportRepository implements Repository {
	ReportDAO reportDAO = new ReportDAO();
	
	
	static Logger logger = Logger.getLogger(ReportRepository.class);
	Map<Long, ReportDTO>mapOfReportDTOToiD;
	Map<Long, Set<ReportDTO> >mapOfReportDTOToreportingDate;
	Map<Long, Set<ReportDTO> >mapOfReportDTOToreporterId;
	Map<Long, Set<ReportDTO> >mapOfReportDTOTovehicleId;
	Map<Long, Set<ReportDTO> >mapOfReportDTOTolostDate;
	Map<Long, Set<ReportDTO> >mapOfReportDTOTofoundDate;
	Map<Long, Set<ReportDTO> >mapOfReportDTOTostatusId;
	Map<Integer, Set<ReportDTO> >mapOfReportDTOTothanaAddress;
	Map<String, Set<ReportDTO> >mapOfReportDTOToblog;
	Map<String, Set<ReportDTO> >mapOfReportDTOToimage1;


	static ReportRepository instance = null;  
	private ReportRepository(){
		mapOfReportDTOToiD = new ConcurrentHashMap<>();
		mapOfReportDTOToreportingDate = new ConcurrentHashMap<>();
		mapOfReportDTOToreporterId = new ConcurrentHashMap<>();
		mapOfReportDTOTovehicleId = new ConcurrentHashMap<>();
		mapOfReportDTOTolostDate = new ConcurrentHashMap<>();
		mapOfReportDTOTofoundDate = new ConcurrentHashMap<>();
		mapOfReportDTOTostatusId = new ConcurrentHashMap<>();
		mapOfReportDTOTothanaAddress = new ConcurrentHashMap<>();
		mapOfReportDTOToblog = new ConcurrentHashMap<>();
		mapOfReportDTOToimage1 = new ConcurrentHashMap<>();

		RepositoryManager.getInstance().addRepository(this);
	}

	public synchronized static ReportRepository getInstance(){
		if (instance == null){
			instance = new ReportRepository();
		}
		return instance;
	}

	public void reload(boolean reloadAll){
		try {
			List<ReportDTO> reportDTOs = reportDAO.getAllReport(reloadAll);
			for(ReportDTO reportDTO : reportDTOs) {
				ReportDTO oldReportDTO = getReportDTOByID(reportDTO.iD);
				if( oldReportDTO != null ) {
					mapOfReportDTOToiD.remove(oldReportDTO.iD);
				
					if(mapOfReportDTOToreportingDate.containsKey(oldReportDTO.reportingDate)) {
						mapOfReportDTOToreportingDate.get(oldReportDTO.reportingDate).remove(oldReportDTO);
					}
					if(mapOfReportDTOToreportingDate.get(oldReportDTO.reportingDate).isEmpty()) {
						mapOfReportDTOToreportingDate.remove(oldReportDTO.reportingDate);
					}
					
					if(mapOfReportDTOToreporterId.containsKey(oldReportDTO.reporterId)) {
						mapOfReportDTOToreporterId.get(oldReportDTO.reporterId).remove(oldReportDTO);
					}
					if(mapOfReportDTOToreporterId.get(oldReportDTO.reporterId).isEmpty()) {
						mapOfReportDTOToreporterId.remove(oldReportDTO.reporterId);
					}
					
					if(mapOfReportDTOTovehicleId.containsKey(oldReportDTO.vehicleId)) {
						mapOfReportDTOTovehicleId.get(oldReportDTO.vehicleId).remove(oldReportDTO);
					}
					if(mapOfReportDTOTovehicleId.get(oldReportDTO.vehicleId).isEmpty()) {
						mapOfReportDTOTovehicleId.remove(oldReportDTO.vehicleId);
					}
					
					if(mapOfReportDTOTolostDate.containsKey(oldReportDTO.lostDate)) {
						mapOfReportDTOTolostDate.get(oldReportDTO.lostDate).remove(oldReportDTO);
					}
					if(mapOfReportDTOTolostDate.get(oldReportDTO.lostDate).isEmpty()) {
						mapOfReportDTOTolostDate.remove(oldReportDTO.lostDate);
					}
					
					if(mapOfReportDTOTofoundDate.containsKey(oldReportDTO.foundDate)) {
						mapOfReportDTOTofoundDate.get(oldReportDTO.foundDate).remove(oldReportDTO);
					}
					if(mapOfReportDTOTofoundDate.get(oldReportDTO.foundDate).isEmpty()) {
						mapOfReportDTOTofoundDate.remove(oldReportDTO.foundDate);
					}
					
					if(mapOfReportDTOTostatusId.containsKey(oldReportDTO.statusId)) {
						mapOfReportDTOTostatusId.get(oldReportDTO.statusId).remove(oldReportDTO);
					}
					if(mapOfReportDTOTostatusId.get(oldReportDTO.statusId).isEmpty()) {
						mapOfReportDTOTostatusId.remove(oldReportDTO.statusId);
					}
					
					if(mapOfReportDTOTothanaAddress.containsKey(oldReportDTO.thanaAddress)) {
						mapOfReportDTOTothanaAddress.get(oldReportDTO.thanaAddress).remove(oldReportDTO);
					}
					if(mapOfReportDTOTothanaAddress.get(oldReportDTO.thanaAddress).isEmpty()) {
						mapOfReportDTOTothanaAddress.remove(oldReportDTO.thanaAddress);
					}
					
					if(mapOfReportDTOToblog.containsKey(oldReportDTO.blog)) {
						mapOfReportDTOToblog.get(oldReportDTO.blog).remove(oldReportDTO);
					}
					if(mapOfReportDTOToblog.get(oldReportDTO.blog).isEmpty()) {
						mapOfReportDTOToblog.remove(oldReportDTO.blog);
					}
					
					if(mapOfReportDTOToimage1.containsKey(oldReportDTO.image1)) {
						mapOfReportDTOToimage1.get(oldReportDTO.image1).remove(oldReportDTO);
					}
					if(mapOfReportDTOToimage1.get(oldReportDTO.image1).isEmpty()) {
						mapOfReportDTOToimage1.remove(oldReportDTO.image1);
					}
					
					
				}
				if(reportDTO.isDeleted == false) 
				{
					
					mapOfReportDTOToiD.put(reportDTO.iD, reportDTO);
				
					if( ! mapOfReportDTOToreportingDate.containsKey(reportDTO.reportingDate)) {
						mapOfReportDTOToreportingDate.put(reportDTO.reportingDate, new HashSet<>());
					}
					mapOfReportDTOToreportingDate.get(reportDTO.reportingDate).add(reportDTO);
					
					if( ! mapOfReportDTOToreporterId.containsKey(reportDTO.reporterId)) {
						mapOfReportDTOToreporterId.put(reportDTO.reporterId, new HashSet<>());
					}
					mapOfReportDTOToreporterId.get(reportDTO.reporterId).add(reportDTO);
					
					if( ! mapOfReportDTOTovehicleId.containsKey(reportDTO.vehicleId)) {
						mapOfReportDTOTovehicleId.put(reportDTO.vehicleId, new HashSet<>());
					}
					mapOfReportDTOTovehicleId.get(reportDTO.vehicleId).add(reportDTO);
					
					if( ! mapOfReportDTOTolostDate.containsKey(reportDTO.lostDate)) {
						mapOfReportDTOTolostDate.put(reportDTO.lostDate, new HashSet<>());
					}
					mapOfReportDTOTolostDate.get(reportDTO.lostDate).add(reportDTO);
					
					if( ! mapOfReportDTOTofoundDate.containsKey(reportDTO.foundDate)) {
						mapOfReportDTOTofoundDate.put(reportDTO.foundDate, new HashSet<>());
					}
					mapOfReportDTOTofoundDate.get(reportDTO.foundDate).add(reportDTO);
					
					if( ! mapOfReportDTOTostatusId.containsKey(reportDTO.statusId)) {
						mapOfReportDTOTostatusId.put(reportDTO.statusId, new HashSet<>());
					}
					mapOfReportDTOTostatusId.get(reportDTO.statusId).add(reportDTO);
					
					if( ! mapOfReportDTOTothanaAddress.containsKey(reportDTO.thanaAddress)) {
						mapOfReportDTOTothanaAddress.put(reportDTO.thanaAddress, new HashSet<>());
					}
					mapOfReportDTOTothanaAddress.get(reportDTO.thanaAddress).add(reportDTO);
					
					if( ! mapOfReportDTOToblog.containsKey(reportDTO.blog)) {
						mapOfReportDTOToblog.put(reportDTO.blog, new HashSet<>());
					}
					mapOfReportDTOToblog.get(reportDTO.blog).add(reportDTO);
					
					if( ! mapOfReportDTOToimage1.containsKey(reportDTO.image1)) {
						mapOfReportDTOToimage1.put(reportDTO.image1, new HashSet<>());
					}
					mapOfReportDTOToimage1.get(reportDTO.image1).add(reportDTO);
					
				}
			}
			
		} catch (Exception e) {
			logger.debug("FATAL", e);
		}
	}
	
	public List<ReportDTO> getReportList() {
		List <ReportDTO> reports = new ArrayList<ReportDTO>(this.mapOfReportDTOToiD.values());
		return reports;
	}
	
	
	public ReportDTO getReportDTOByID( long ID){
		return mapOfReportDTOToiD.get(ID);
	}
	
	
	public List<ReportDTO> getReportDTOByreporting_date(long reporting_date) {
		return new ArrayList<>( mapOfReportDTOToreportingDate.getOrDefault(reporting_date,new HashSet<>()));
	}
	
	
	public List<ReportDTO> getReportDTOByreporter_id(long reporter_id) {
		return new ArrayList<>( mapOfReportDTOToreporterId.getOrDefault(reporter_id,new HashSet<>()));
	}
	
	
	public List<ReportDTO> getReportDTOByvehicle_id(long vehicle_id) {
		return new ArrayList<>( mapOfReportDTOTovehicleId.getOrDefault(vehicle_id,new HashSet<>()));
	}
	
	
	public List<ReportDTO> getReportDTOBylost_date(long lost_date) {
		return new ArrayList<>( mapOfReportDTOTolostDate.getOrDefault(lost_date,new HashSet<>()));
	}
	
	
	public List<ReportDTO> getReportDTOByfound_date(long found_date) {
		return new ArrayList<>( mapOfReportDTOTofoundDate.getOrDefault(found_date,new HashSet<>()));
	}
	
	
	public List<ReportDTO> getReportDTOBystatus_id(long status_id) {
		return new ArrayList<>( mapOfReportDTOTostatusId.getOrDefault(status_id,new HashSet<>()));
	}
	
	
	public List<ReportDTO> getReportDTOBythana_address(int thana_address) {
		return new ArrayList<>( mapOfReportDTOTothanaAddress.getOrDefault(thana_address,new HashSet<>()));
	}
	
	
	public List<ReportDTO> getReportDTOByblog(String blog) {
		return new ArrayList<>( mapOfReportDTOToblog.getOrDefault(blog,new HashSet<>()));
	}
	
	
	public List<ReportDTO> getReportDTOByimage1(String image1) {
		return new ArrayList<>( mapOfReportDTOToimage1.getOrDefault(image1,new HashSet<>()));
	}

	
	@Override
	public String getTableName() {
		String tableName = "";
		try{
			tableName = "report";
		}catch(Exception ex){
			logger.debug("FATAL",ex);
		}
		return tableName;
	}
}


