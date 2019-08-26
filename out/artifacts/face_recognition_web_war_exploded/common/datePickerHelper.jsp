<%@page import="util.TimeFormat"%>

<link href="${context}assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet" type="text/css" />
<link href="${context}assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
<script src="${context}assets/global/plugins/moment.min.js" type="text/javascript"></script>
<script src="${context}assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
	    $('.datepicker').datepicker({
           	orientation: "top",
            autoclose: true,
            format: 'dd/mm/yyyy',
            todayBtn: 'linked',
            todayHighlight:	true
        });
	})
</script>