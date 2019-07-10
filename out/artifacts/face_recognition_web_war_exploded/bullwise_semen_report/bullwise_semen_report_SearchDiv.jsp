<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="bullwise_semen_report.Bullwise_semen_reportAnotherDBDAO"%>
<%
String Language = LM.getText(LC.BULLWISE_SEMEN_REPORT_EDIT_LANGUAGE, loginDTO);
String Options;
int i = 0;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>
















<div id = "bullType" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.BULLWISE_SEMEN_REPORT_WHERE_BULLTYPE, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<select class='form-control'  name='bullType' id = 'bullType_select_<%=i%>' onChange="ajaxSubmit();"
>
<%		
Options = Bullwise_semen_reportAnotherDBDAO.getOptions(Language, "select", "bull", "bullType_select_" + i, "form-control", "bullType", "any" );			
out.print(Options);
%>
		</select>
		
        	</div>
        </div>
</div>
<div id = "breedType" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.BULLWISE_SEMEN_REPORT_WHERE_BREEDTYPE, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<select class='form-control'  name='breedType' id = 'breedType_select_<%=i%>' onChange="ajaxSubmit();"
>
<%		
Options = Bullwise_semen_reportAnotherDBDAO.getOptions(Language, "select", "breed", "breedType_select_" + i, "form-control", "breedType", "any" );			
out.print(Options);
%>
		</select>
		
        	</div>
        </div>
</div>
