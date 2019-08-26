<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="liquid_semen_report.Liquid_semen_reportAnotherDBDAO"%>
<%
String Language = LM.getText(LC.LIQUID_SEMEN_REPORT_EDIT_LANGUAGE, loginDTO);
String Options;
int i = 0;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>
















<div id = "centreType" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.LIQUID_SEMEN_REPORT_WHERE_CENTRETYPE, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<select class='form-control'  name='centreType' id = 'centreType_select_<%=i%>' onChange="ajaxSubmit();"
>
<%		
Options = Liquid_semen_reportAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType", "any" );			
out.print(Options);
%>
		</select>
		
        	</div>
        </div>
</div>
<div id = "collectionDate" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.LIQUID_SEMEN_REPORT_WHERE_COLLECTIONDATE, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<input type='date' class='form-control'  name='collectionDate_Date_<%=i%>' id = 'collectionDate_date_Date_<%=i%>' value="1970-01-01" onchange="ajaxSubmit();"
>
		<input type='hidden' class='form-control'  name='collectionDate' id = 'collectionDate_date_<%=i%>' value="0" >
						
        	</div>
        </div>
</div>
