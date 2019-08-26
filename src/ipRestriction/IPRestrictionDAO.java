package ipRestriction;

import static util.ModifiedSqlGenerator.*;

import java.util.List;

import annotation.Transactional;
import config.GlobalConfigConstants;
import websecuritylog.WebSecurityLogDTO;

public class IPRestrictionDAO {
	


	@Transactional
	public void add(IPRestrictionDTO ipRestrictionDTO) throws Exception
	{
		insert(ipRestrictionDTO);
	}
		
	
	@Transactional
	public void update(IPRestrictionDTO ipRestrictionDTO) throws Exception
	{
		updateEntity(ipRestrictionDTO);
	}
	
	public IPRestrictionDTO getIPRestrictionDTOByIPAndUser(String ip, String username) throws Exception
	{
		List<IPRestrictionDTO> ipList = getObjectFullyPopulatedByString(IPRestrictionDTO.class, new String[] {ip, username}, new String[] {"IP", "username"});
		if(ipList != null && ipList.size() > 0) return ipList.get(0);
		else return null;
	}
	
	public void removeIPRestrictionByIPAndUser(String ip, String username) throws Exception
	{
		IPRestrictionDTO ipRestrictionDTO = getIPRestrictionDTOByIPAndUser(ip, username);
		if(ipRestrictionDTO != null) deleteHardEntityByID(IPRestrictionDTO.class, ipRestrictionDTO.getID());
	}
	
	@Transactional
	public void delete(String ip, String username) throws Exception
	{
		IPRestrictionDTO ipRestrictionDTO = getIPRestrictionDTOByIPAndUser(ip, username);
		if(ipRestrictionDTO != null)
		{
			deleteHardEntityByID(IPRestrictionDTO.class, ipRestrictionDTO.getID());
		}
	}
}
