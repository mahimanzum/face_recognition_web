<jsp:include page="../common/layout.jsp" flush="true">
	<jsp:param name="title" value="New Report" /> 
	<jsp:param name="body" value="../report/reportBody.jsp" />
	<jsp:param name="css" value="assets/pages/css/profile.min.css" />
	<jsp:param name="css" value="report/report.css" />
	<jsp:param name="js" value="assets/report/jquery.dataTables.min.js" />
	<jsp:param name="js" value="assets/report/dataTables.buttons.min.js" />
	<jsp:param name="js" value="assets/report/jszip.min.js" />
	<jsp:param name="js" value="assets/report/pdfmake.min.js" />
	<jsp:param name="js" value="assets/report/vfs_fonts.js" />
	<jsp:param name="js" value="assets/report/buttons.html5.min.js" />
	<jsp:param name="css" value="assets/global/plugins/datatables/datatables.min.css" />
	<jsp:param name="js" value="assets/global/scripts/datatable.js" />
	<jsp:param name="js" value="assets/pages/scripts/table-datatables-scroller.min.js" />
	<jsp:param name="js" value="report/report.js" />
	<jsp:param name="helpers" value="datePickerHelper.jsp" />
</jsp:include>