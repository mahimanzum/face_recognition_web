<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="semem_indent_immediate_report.Semem_indent_immediate_reportAnotherDBDAO"%>
<%
String Language = LM.getText(LC.SEMEM_INDENT_IMMEDIATE_REPORT_EDIT_LANGUAGE, loginDTO);
String Options;
int i = 0;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
String requisitionID = request.getParameter("requisitionID");
System.out.println("requisitionID = " + requisitionID);
%>
















<div id = "groupId" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.SEMEM_INDENT_IMMEDIATE_REPORT_WHERE_GROUPID, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<input type='text' class='form-control'  name='groupId' id = 'groupId_text_<%=i%>' value="<%=requisitionID%>"  onkeyup="ajaxSubmit();"
/>					
        	</div>
        </div>
</div>
<div id = "centreType" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.SEMEM_INDENT_IMMEDIATE_REPORT_WHERE_CENTRETYPE, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<select class='form-control'  name='centreType' id = 'centreType_select_<%=i%>' onChange="ajaxSubmit();"
>
<%		
Options = Semem_indent_immediate_reportAnotherDBDAO.getOptions(Language, "select", "centre", "centreType_select_" + i, "form-control", "centreType", "any" );			
out.print(Options);
%>
		</select>
		
        	</div>
        </div>
</div>
<div id = "breedType" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.SEMEM_INDENT_IMMEDIATE_REPORT_WHERE_BREEDTYPE, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<select class='form-control'  name='breedType' id = 'breedType_select_<%=i%>' onChange="ajaxSubmit();"
>
<%		
Options = Semem_indent_immediate_reportAnotherDBDAO.getOptions(Language, "select", "breed", "breedType_select_" + i, "form-control", "breedType", "any" );			
out.print(Options);
%>
		</select>
		
        	</div>
        </div>
</div>
<div id = "requisitionDate" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.SEMEM_INDENT_IMMEDIATE_REPORT_WHERE_REQUISITIONDATE, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<input type='date' class='form-control'  name='requisitionDate_Date_<%=i%>' id = 'requisitionDate_date_Date_<%=i%>' value="<%=datestr%>" onchange="ajaxSubmit();"
>
		<input type='hidden' class='form-control'  name='requisitionDate' id = 'requisitionDate_date_<%=i%>' value="0" >
						
        	</div>
        </div>
</div>
