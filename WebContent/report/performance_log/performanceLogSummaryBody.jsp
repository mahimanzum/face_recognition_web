<%@page import="report.PerformanceLogSummaryReportDAO"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String limitString = request.getParameter("limit");
int limit =  (limitString!=null?Integer.parseInt(limitString):10);

String currentPageNoString = request.getParameter("currentPageNo");
int currentPageNo = (currentPageNoString!=null?Integer.parseInt(currentPageNoString):1);
PerformanceLogSummaryReportDAO performanceLogSummaryReportDAO = new PerformanceLogSummaryReportDAO();

int totalRecords = performanceLogSummaryReportDAO.getResultCount();
int offset = (currentPageNo-1)*limit;
int totalPageNo = (totalRecords+limit-1)/limit;



List<List<Object>> result = performanceLogSummaryReportDAO.getResultByOffsetAndLimit(offset, limit);




%>

<div class="portlet box portlet-btcl light navigator">
	<div class="portlet-body">	
		<div class="row text-center">
			<form action="performanceLogServlet?actionType=reportPageSummary">
				<nav aria-label="Page navigation">
					<ul class="pagination" style="margin: 0px;">
						<li style="float:left;"><i class="hidden-xs">Record Per Page</i>&nbsp;&nbsp;
							<input type="hidden" name="actionType" value="reportPageSummary">
							<input type="text" class="custom-form-control black-text"  name="limit" style="height: 34px;" placeholder="" value="<%=limit%>" />&nbsp;&nbsp;&nbsp;&nbsp;
						</li>
						<li class="page-item"><a class="page-link" href="performanceLogServlet?actionType=reportPageSummary&&limit=<%=limit%>&currentPageNo=1" id="firstLoad"
							aria-label="First" title="Left"> <i
								class="fa fa-angle-double-left" aria-hidden="true"></i> <span
								class="sr-only">First</span>
						</a></li>
						<li class="page-item"><a class="page-link" id="previousLoad"
							href="performanceLogServlet?actionType=reportPageSummary&&limit=<%=limit%>&currentPageNo=<%=(currentPageNo==1?1:currentPageNo-1)%>" aria-label="Previous" title="Previous"> <i
								class="fa fa-angle-left" aria-hidden="true"></i> <span
								class="sr-only">Previous</span>
						</a></li>

						<li class="page-item"><a class="page-link" href="performanceLogServlet?actionType=reportPageSummary&&limit=<%=limit%>&currentPageNo=<%=(currentPageNo==totalPageNo?totalPageNo:currentPageNo+1)%>" id="nextLoad"
							aria-label="Next" title="Next"> <i class="fa fa-angle-right"
								aria-hidden="true"></i> <span class="sr-only">Next</span>
						</a></li>
						<li class="page-item"><a class="page-link" href="performanceLogServlet?actionType=reportPageSummary&&limit=<%=limit%>&currentPageNo=<%=totalPageNo%>" id="lastLoad"
							aria-label="Last" title="Last"> <i
								class="fa fa-angle-double-right" aria-hidden="true"></i> <span
								class="sr-only">Last</span>
						</a></li>
						<li>&nbsp;&nbsp;<i class="hidden-xs">Page </i><input
							type="text" class="custom-form-control black-text" name="currentPageNo" value="<%=currentPageNo%>" style="height: 34px;"
							size="15"> <i class="hidden-xs">of</i>&nbsp;&nbsp;<input
							type="text" class="custom-form-control  black-text" name="tatalPageNo" value="<%=totalPageNo%>" style="height: 34px;"
							size="15" disabled><input type="submit" id="forceLoad"
							class="btn btn-success"
							value="GO" />
						</li>
					</ul>
				</nav>
			</form>
		</div>
	</div>
</div>

<button  class=" black-text" onclick="exportTableToCSV('performanceLogSummary.csv')">CSV</button>
<div class="portlet box">
	<div class="portlet-body">
		<form action="" method="POST" id="tableForm">
			
			<div class="table-responsive">
					<table id="tableData" class="table table-bordered table-striped">
						<thead>
							<tr>
								<%
								
								List<Object> row = result.get(0);
								
								for(Object object:row){
									%>
									<td><%=object%></td>
									<%
								}
								%>
							</tr>
						</thead>
						<tbody>
							
							
								<%
								
								for(int index=1;index<result.size();index++){
									
									%>
									<tr>		
									<%
									List<Object> data = result.get(index);
									for(Object object:data){
										%>
										<td><%=object%></td>
										<%
									}
											
									%>  
									
									</tr>
									<%
								}
								
								%>												
							

						</tbody>


					</table>
				</div>			
		</form>
	</div>
</div>





</body>


<script type="text/javascript">
function downloadCSV(csv, filename) {
    var csvFile;
    var downloadLink;

    // CSV file
    csvFile = new Blob([csv], {type: "text/csv"});

    // Download link
    downloadLink = document.createElement("a");

    // File name
    downloadLink.download = filename;

    // Create a link to the file
    downloadLink.href = window.URL.createObjectURL(csvFile);

    // Hide download link
    downloadLink.style.display = "none";

    // Add the link to DOM
    document.body.appendChild(downloadLink);

    // Click download link
    downloadLink.click();
}
function exportTableToCSV(filename) {
    var csv = [];
    var rows = document.querySelectorAll("table tr");
    
    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll("td, th");
        
        for (var j = 0; j < cols.length; j++) 
            row.push(cols[j].innerText);
        
        csv.push(row.join(","));        
    }

    // Download CSV file
    downloadCSV(csv.join("\n"), filename);
}
</script>



</html>


