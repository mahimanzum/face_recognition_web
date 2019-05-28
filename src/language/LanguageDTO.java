/**
 * @(#)LanguageDTO.java
 * @author <>
 * @history
 *          created : <>  Date : MMM-DD-YYYY <venue>
 * @version <>
 *
 * Copyright <YYYY> <company>.
 *
 * All rights reserved.
 *
 * This software is the confidential and proprietdary information
 * of <company>("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with <company>.
 *
 */

package language;

import java.util.*;

public class LanguageDTO
{
	public long ID;
	public String languageName = "";
	public String description = "";  

	public ArrayList<LanguageTextDTO> languageIDTextList;

	public LanguageDTO()
	{
		languageIDTextList  = new ArrayList<LanguageTextDTO>();
	}

	@Override
	public String toString() {
		return "LanguageDTO [ID=" + ID + ", languageName=" + languageName + ", description=" + description
				+ ", languageIDTextList=" + languageIDTextList + "]";
	}

}
