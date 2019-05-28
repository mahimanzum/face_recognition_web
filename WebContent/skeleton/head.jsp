<%@page import="theme.ThemeRepository"%>
<%@page import="theme.ThemeDTO"%>
<%@page import="java.util.*"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="<%=context%>/assets/global/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet"
	type="text/css" />
<link href="<%=context%>assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="<%=context%>assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=context%>assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="<%=context%>assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
<link href="<%=context%>assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=context%>assets/global/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
<link href="<%=context%>/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
<link href="<%=context%>/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN THEME GLOBAL STYLES --> 

<link href="<%=context%>assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
<link href="<%=context%>assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
<!-- END THEME GLOBAL STYLES -->
<!-- BEGIN THEME LAYOUT STYLES -->
<link href="<%=context%>assets/layouts/layout4/css/layout.min.css" rel="stylesheet" type="text/css" />

<link href="<%=context%>assets/pages/css/blog.min.css" rel="stylesheet" type="text/css" />
<link href="<%=context%>assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css" />
<link href="<%=context%>assets/dashboard_files/customField.css" rel="stylesheet" type="text/css">
<link href="<%=context%>assets/dashboard_files/grs_dashboard.css" rel="stylesheet" type="text/css">




<!-- Applying custom templates -->

<% 
String themeDescription = ThemeRepository.getInstance().getCurrentAppliedThemeDescriprion();
if(themeDescription!=null){
%>
 <link href="<%=context%>assets/Templates/<%=themeDescription%>/style.css" rel="stylesheet" type="text/css" />
<%} %>
<script src="<%=context%>assets/scripts/menu.js" type="text/javascript"></script>
<!-- END THEME LAYOUT STYLES -->
<script src="<%=context%>assets/global/plugins/jquery.min.js" type="text/javascript"></script>

<!-- CUSTOM TAGS-->
<script src="<%=context%>assets/global/scripts/customTag/portlet-div-tag.js" type="text/javascript"></script>
<script src="<%=context%>assets/global/scripts/customTag/client-autocomplete-tag.js" type="text/javascript"></script>
<script src="<%=context%>assets/global/scripts/customTag/default-form-group-tag.js" type="text/javascript"></script>



 
<link rel="stylesheet" href="<%=context%>assets/common_component/css/ionicons.min.css">
 <link href="<%=context%>assets/common_component/jvectormap/jquery-jvectormap.css" rel="stylesheet" type="text/css" />
 <link href="<%=context%>assets/common_component/css/AdminLTE.css" rel="stylesheet" type="text/css" />
<link href="<%=context%>/assets/common_component/css/_all-skins.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
  
<!-- DataTables -->
  <link rel="stylesheet" href="<%=context%>assets/common_component/css/dataTables.bootstrap.min.css">