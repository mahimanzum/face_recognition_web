<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
String Language = LM.getText(LC.SEMEN_DISTRIBUTION_IMMEDIATE_REPORT_EDIT_LANGUAGE, loginDTO);
String Options;
int i = 0;
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String datestr = dateFormat.format(date);
String DistributionId = request.getParameter("DistributionId");
System.out.println("DistributionId = " + DistributionId);
%>















<div id = "distributionId" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.SEMEN_DISTRIBUTION_IMMEDIATE_REPORT_WHERE_DISTRIBUTIONID, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<input type='text' class='form-control'  name='distributionId' id = 'distributionId_text_<%=i%>' value="<%=DistributionId%>"  onkeyup="ajaxSubmit();"
/>					
        	</div>
        </div>
</div>
<div id = "transactionDate" class="search-criteria-div">
        <div class="form-group">
        	<label class="col-sm-3 control-label">
        		<%=LM.getText(LC.SEMEN_DISTRIBUTION_IMMEDIATE_REPORT_WHERE_TRANSACTIONDATE, loginDTO)%>
        	</label>
        	<div class="col-sm-6">
        				<input type='date' class='form-control'  name='transactionDate_Date_<%=i%>' id = 'transactionDate_date_Date_<%=i%>' value="<%=datestr%>" onchange="ajaxSubmit();"
>
		<input type='hidden' class='form-control'  name='transactionDate' id = 'transactionDate_date_<%=i%>'  >
						
        	</div>
        </div>
</div>
