package ipRestriction;

import annotation.DAO;
import annotation.Transactional;
import config.GlobalConfigConstants;
import config.GlobalConfigurationRepository;
import websecuritylog.WebSecurityLogDTO;

public class IPRestrictionService {
	
	@DAO
	IPRestrictionDAO ipRestrictionDAO;

	@Transactional
	public void updateOrInsert(WebSecurityLogDTO webSecurityLogDTO) throws Exception
	{
		IPRestrictionDTO ipRestrictionDTO = new IPRestrictionDTO();
		ipRestrictionDTO.setIP(webSecurityLogDTO.getUrl());
		ipRestrictionDTO.setUsername(webSecurityLogDTO.getUsername());
		ipRestrictionDTO.setLastHitTime(webSecurityLogDTO.getAttemptTime());
		updateOrInsert(ipRestrictionDTO);
	}
	@Transactional
	public void updateOrInsert(IPRestrictionDTO ipRestrictionDTOArg) throws Exception
	{
		IPRestrictionDTO ipRestrictionDTO = ipRestrictionDAO.getIPRestrictionDTOByIPAndUser(ipRestrictionDTOArg.getIP(), ipRestrictionDTOArg.getUsername());
		if(ipRestrictionDTO != null)
		{
			
			int hitCountToBlock = GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.HIT_COUNT_TO_BLOCK_LOGIN).value;
			int hitCountToDelay = GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.HIT_COUNT_TO_START_DELAY_LOGIN).value;
			if(ipRestrictionDTO.getHitCount() + 1 >=  hitCountToBlock + hitCountToDelay)
			{
				//make next login time to long max and change status of ip
				ipRestrictionDTO.setNextHitTimeAfter(Long.MAX_VALUE);			
				ipRestrictionDTO.setHitCount(ipRestrictionDTO.getHitCount() + 1);
			}
			else if(ipRestrictionDTO.getHitCount() + 1 >= hitCountToDelay)
			{
				//make delay
				int delayDurationInMillis = GlobalConfigurationRepository.getGlobalConfigDTOByID(GlobalConfigConstants.LOGIN_DELAY_TIME_IN_SECONDS).value * 1000;
				long nextHitTime = System.currentTimeMillis() + delayDurationInMillis;
				ipRestrictionDTO.setNextHitTimeAfter(nextHitTime);
				ipRestrictionDTO.setHitCount(ipRestrictionDTO.getHitCount() + 1);
			}
			else
			{
				//update hitcount
				ipRestrictionDTO.setHitCount(ipRestrictionDTO.getHitCount() + 1);
			}
			ipRestrictionDAO.update(ipRestrictionDTO);
		}
		else
		{
			ipRestrictionDTO = new IPRestrictionDTO();
			ipRestrictionDTO.setUsername(ipRestrictionDTOArg.getUsername());
			ipRestrictionDTO.setLastHitTime(System.currentTimeMillis());
			ipRestrictionDTO.setIP(ipRestrictionDTOArg.getIP());
			ipRestrictionDTO.setLastModificationTime(System.currentTimeMillis());
			ipRestrictionDAO.add(ipRestrictionDTO);
		}
	}
	
	@Transactional
	public IPRestrictionDTO getIPRestrictionDTOByIPAndUser(String ip, String username) throws Exception
	{
		return ipRestrictionDAO.getIPRestrictionDTOByIPAndUser(ip, username);
	}
	@Transactional
	public void deleteIPRestrictionDTOByIPAndUser(String loginSourceIP, String username) throws Exception {
		// TODO Auto-generated method stub
		ipRestrictionDAO.delete(loginSourceIP, username);
	}

}
