<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="bull_report.Bull_reportAnotherDBDAO"%>
<%
String Language = LM.getText(LC.BULL_REPORT_EDIT_LANGUAGE, loginDTO);
String Options;
int i = 0;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
%>
















<div id = "bullTag" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.BULL_REPORT_WHERE_BULLTAG, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<input type='text' class='form-control'  name='bullTag' id = 'bullTag_text_<%=i%>' value=""  onkeyup="ajaxSubmit();"
/>					
        	</div>
        </div>
</div>
<div id = "breedType" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.BULL_REPORT_WHERE_BREEDTYPE, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<select class='form-control'  name='breedType' id = 'breedType_select_<%=i%>' onChange="ajaxSubmit();"
>
<%		
Options = Bull_reportAnotherDBDAO.getOptions(Language, "select", "breed", "breedType_select_" + i, "form-control", "breedType", "any" );			
out.print(Options);
%>
		</select>
		
        	</div>
        </div>
</div>
<div id = "statusType" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.BULL_REPORT_WHERE_STATUSTYPE, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<select class='form-control'  name='statusType' id = 'statusType_select_<%=i%>' onChange="ajaxSubmit();"
>
<%		
Options = Bull_reportAnotherDBDAO.getOptions(Language, "select", "status", "statusType_select_" + i, "form-control", "statusType", "any" );			
out.print(Options);
%>
		</select>
		
        	</div>
        </div>
</div>
<div id = "dam" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.BULL_REPORT_WHERE_DAM, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<input type='text' class='form-control'  name='dam' id = 'dam_text_<%=i%>' value=""  onkeyup="ajaxSubmit();"
/>					
        	</div>
        </div>
</div>
<div id = "sire" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.BULL_REPORT_WHERE_SIRE, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<input type='text' class='form-control'  name='sire' id = 'sire_text_<%=i%>' value=""  onkeyup="ajaxSubmit();"
/>					
        	</div>
        </div>
</div>
<div id = "maternalGrandDam" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.BULL_REPORT_WHERE_MATERNALGRANDDAM, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<input type='text' class='form-control'  name='maternalGrandDam' id = 'maternalGrandDam_text_<%=i%>' value=""  onkeyup="ajaxSubmit();"
/>					
        	</div>
        </div>
</div>
<div id = "maternalGrandSire" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.BULL_REPORT_WHERE_MATERNALGRANDSIRE, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<input type='text' class='form-control'  name='maternalGrandSire' id = 'maternalGrandSire_text_<%=i%>' value=""  onkeyup="ajaxSubmit();"
/>					
        	</div>
        </div>
</div>
<div id = "paternalGrandDam" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.BULL_REPORT_WHERE_PATERNALGRANDDAM, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<input type='text' class='form-control'  name='paternalGrandDam' id = 'paternalGrandDam_text_<%=i%>' value=""  onkeyup="ajaxSubmit();"
/>					
        	</div>
        </div>
</div>
<div id = "paternalGrandSire" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.BULL_REPORT_WHERE_PATERNALGRANDSIRE, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<input type='text' class='form-control'  name='paternalGrandSire' id = 'paternalGrandSire_text_<%=i%>' value=""  onkeyup="ajaxSubmit();"
/>					
        	</div>
        </div>
</div>
