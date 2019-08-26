<%-- <%@page import="file.FileTypeConstants"%> --%>
<%@page import="java.util.ArrayList"%>
<%@page import="report.ReportParameterDTO"%>
<%@page import="java.util.List"%>
<div class="portlet light">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-tv"></i>Display
		</div>
	</div>
	<!-- /.box-header -->
	<div class="portlet-body form" style="height: 30vh; overflow-x: hidden; overflow-y:  scroll;">
		<div class="form-body">
		
			
		<%
			int index = 0;
			List<ReportParameterDTO> reportParameterDTOList = (List<ReportParameterDTO>)request.getAttribute("reportFieldList");
			if(reportParameterDTOList==null){
				reportParameterDTOList = new ArrayList<ReportParameterDTO>();
			}
			for(ReportParameterDTO reportParameterDTO: reportParameterDTOList){
				index++;
				%>
				
			<div class="form-group">
				<div class="col-md-5">
					<label class="checkbox"><span><input type="checkbox" class="input-checkbox-display" value="<%=index%>"></span><%=reportParameterDTO.userDefinedFullName%></label>
				</div>
				<div class="col-md-5">
					<input type="text" class="display-input" name="<%=reportParameterDTO.getDisplayDivName()%>" value="<%=reportParameterDTO.userDefinedFullName%>" disabled
					data-operator="<%=reportParameterDTO.operatorType%>" data-comment="<%=reportParameterDTO.dataComment%>" data-values="<%=reportParameterDTO.dataValue%>"  >
				</div>
				<div class="col-md-2" style="position: relative;"><%@include file="upDownArrow.jsp"%></div>
			</div>
				<%
			}
		
		%>
		
			
			
			
		</div>
	</div>
	<!-- /.box-body -->
</div>
