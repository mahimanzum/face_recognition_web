package searchform;

import login.LoginDTO;


import javax.servlet.http.HttpSession;
import language.LM;


public class SearchForm {
	public static boolean isNumeric(String s) 
	{  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	public static String GetFormBody(String searchFieldInfo[][], LoginDTO loginDTO, HttpSession session)
	{
		String sFormBody = "";
		if(searchFieldInfo != null && searchFieldInfo.length > 0)
		{
			for(int i = 0; i < searchFieldInfo.length;i++)
			{
				if(i % 2 == 0)
				{
					sFormBody += ("<div class='row'>");
				} 				
				{
					sFormBody += ("<div class='col-md-6 col-sm-6'>");
					{
						if (searchFieldInfo[i][0].endsWith(".jsp"))
						{
							sFormBody += ("<jsp:include page='" + searchFieldInfo[i][0] + "' flush='true'/>");		
						}
						else
						{
							sFormBody += ("<div class='form-group'>");
							{
								String fieldName =  searchFieldInfo[i][0];
								if(isNumeric(fieldName))
								{
									fieldName = LM.getText(Long.parseLong( searchFieldInfo[i][0]), loginDTO);
								}
								
								sFormBody += ("<div class='form-group'>");
								{
								
									sFormBody += ("<label for='' class='control-label col-md-4 col-sm-4 col-xs-4'>" + fieldName + "</label>");
									
									sFormBody += ("<div class='col-md-8 col-sm-8 col-xs-8'>");
									{

										sFormBody += ("<input type='text' class='form-control' id='"+searchFieldInfo[i][1]+"' placeholder='' name='"+ searchFieldInfo[i][1] +"' ");
											
					
										String value = (String)session.getAttribute(searchFieldInfo[i][1]);
					
										if( value != null)
										{
											sFormBody += ("value = '" + value + "'");
										}
										
										sFormBody += (" />");
										
									}
									sFormBody += ("</div>");
								}
								sFormBody += ("</div>");
							}
							sFormBody += ("</div>");
						}
					}
					sFormBody += ("</div>");
				}

				if(i % 2 == 1 || i == searchFieldInfo.length - 1)
				{
					sFormBody += ("</div>");
				}
			}

		}						

		
		return sFormBody;			
	}

}
