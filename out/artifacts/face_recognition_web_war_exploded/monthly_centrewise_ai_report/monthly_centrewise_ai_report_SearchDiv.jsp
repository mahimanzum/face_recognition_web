<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="monthly_centrewise_ai_report.Monthly_centrewise_ai_reportAnotherDBDAO"%>
<%
String Language = LM.getText(LC.MONTHLY_CENTREWISE_AI_REPORT_EDIT_LANGUAGE, loginDTO);
String Options;
int i = 0;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>
















<div id = "centreType" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.MONTHLY_CENTREWISE_AI_REPORT_WHERE_CENTRETYPE, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<select class='form-control'  name='centreType' id = 'centreType_select_<%=i%>' onChange="ajaxSubmit();"
>
<%		
Options = Monthly_centrewise_ai_reportAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType", "any" );			
out.print(Options);
%>
		</select>
		
        	</div>
        </div>
</div>
<div id = "bullType" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.MONTHLY_CENTREWISE_AI_REPORT_WHERE_BULLTYPE, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<select class='form-control'  name='bullType' id = 'bullType_select_<%=i%>' onChange="ajaxSubmit();"
>
<%		
Options = Monthly_centrewise_ai_reportAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType", "any" );			
out.print(Options);
%>
		</select>
		
        	</div>
        </div>
</div>
