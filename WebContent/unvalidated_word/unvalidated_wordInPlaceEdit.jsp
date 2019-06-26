<%@page pageEncoding="UTF-8" %>

<%@page import="sessionmanager.SessionConstants"%>
<%@page import="unvalidated_word.Unvalidated_wordDTO"%>
<%@page import="java.util.UUID"%>
<%@page import="language.LanguageTextDTO"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>

<%
Unvalidated_wordDTO unvalidated_wordDTO = (Unvalidated_wordDTO)request.getAttribute("unvalidated_wordDTO");
LoginDTO loginDTO = (LoginDTO) request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);

if(unvalidated_wordDTO == null)
{
	unvalidated_wordDTO = new Unvalidated_wordDTO();
	
}
System.out.println("unvalidated_wordDTO = " + unvalidated_wordDTO);

String actionName = "edit";


String ID = request.getParameter("ID");
if(ID == null || ID.isEmpty())
{
	ID = "0";
}
System.out.println("ID = " + ID);
int i = Integer.parseInt(request.getParameter("rownum"));
String deletedStyle = request.getParameter("deletedstyle");

String value = "";
Unvalidated_wordDTO row = unvalidated_wordDTO;
%>



























	














<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%@ page import="pb.*"%>

<%
String Language = LM.getText(LC.UNVALIDATED_WORD_EDIT_LANGUAGE, loginDTO);
String Options;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>


			
<%=("<td id = '" + i + "_iD" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='iD' id = 'iD_text_<%=i%>' value='<%=ID%>'/>
	
												
<%=("</td>")%>
			
<%=("<td id = '" + i + "_word'>")%>
			
	
	<div class="form-inline" id = 'word_div_<%=i%>'>
		<input type='file' class='form-control'  name='word' id = 'word_database_<%=i%>' value=<%=actionName.equals("edit")?("'" + unvalidated_wordDTO.word + "'"):("'" + " " + "'")%>  />	
						
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_verdictCount'>")%>
			
	
	<div class="form-inline" id = 'verdictCount_div_<%=i%>'>
		<input type='text' class='form-control'  name='verdictCount' id = 'verdictCount_text_<%=i%>' value=<%=actionName.equals("edit")?("'" + unvalidated_wordDTO.verdictCount + "'"):("'" + "0" + "'")%>   />					
	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isCorrect'>")%>
			
	
	<div class="form-inline" id = 'isCorrect_div_<%=i%>'>
		<input type='checkbox' class='form-control'  name='isCorrect' id = 'isCorrect_checkbox_<%=i%>' value='true' <%=(actionName.equals("edit") && String.valueOf(unvalidated_wordDTO.isCorrect).equals("true"))?("checked"):""%>  ><br>						


	</div>
				
<%=("</td>")%>
			
<%=("<td id = '" + i + "_isDeleted" +  "' style='display:none;'>")%>
			

		<input type='hidden' class='form-control'  name='isDeleted' id = 'isDeleted_checkbox_<%=i%>' value= <%=actionName.equals("edit")?("'" + unvalidated_wordDTO.isDeleted + "'"):("'" + "false" + "'")%>/>
											
												
<%=("</td>")%>
					
		