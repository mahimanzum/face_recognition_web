<!DOCTYPE html>
<%@page import="user.*"%>
<%@page import="org.apache.commons.lang3.ArrayUtils"%>
<%@page contentType="text/html;charset=utf-8" %>
<script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js'></script>
<%
	String context = "../../.."  + request.getContextPath() + "/";
	String pluginsContext = context +"assets/global/plugins/";   
    request.setAttribute("context", context);
    request.setAttribute("pluginsContext",pluginsContext);   

	List<Integer> menuIDPath = (List<Integer>)request.getAttribute("menuIDPath");
	if(menuIDPath == null){
		menuIDPath = new ArrayList<Integer>();
	}
	
	LoginDTO loginDTO = (LoginDTO)request.getSession(true).getAttribute(SessionConstants.USER_LOGIN);
	UserDTO userDTO = UserRepository.getInstance().getUserDTOByUserID(loginDTO.userID);
	
	String pageTitle="";
	
	if(menuIDPath.isEmpty()){
		pageTitle = ((HttpServletRequest)request).getRequestURL().toString();
		pageTitle=(LM.getLanguageIDByUserDTO(userDTO)==CommonConstant.Language_ID_English?"Dashboard":"ড্যাশবোর্ড");;
	}else{

		int currentMenuID = menuIDPath.get(menuIDPath.size()-1);
		MenuDTO currentMenu = MenuRepository.getMenuDTOByMenuID(currentMenuID);
		pageTitle=(LM.getLanguageIDByUserDTO(userDTO)==CommonConstant.Language_ID_English?currentMenu.menuName:currentMenu.menuNameBangla);
	}
	
%>

<html lang="en">


<head>

<link rel="icon" href="<%=request.getContextPath()%>/images/favicon-icon.png"/>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="shortcut icon" type="image/x-icon" href="${context}favicon.ico" />
<title><%=pageTitle %></title>
<%@ include file="../skeleton/head.jsp"%>
<%
String[] cssStr = request.getParameterValues("css");
for(int i = 0; ArrayUtils.isNotEmpty(cssStr) &&i < cssStr.length;i++)
{
	%>
<link href="${context}<%=cssStr[i]%>" rel="stylesheet" type="text/css" />
	<%
}
%>

<script type="text/javascript">
 var context = '${context}';
 var pluginsContext = '${pluginsContext}';
 
 function activateMenu(){
	
	 alert( arguments.length)
		for (var i = 0; i < arguments.length; i++) {
			if (typeof arguments[i] !== 'undefined' && arguments[i].trim()) {
				$("#" + arguments[i].trim()).addClass("active menu-open");
		    }
		  }
	}
 
</script>
</head>
<!-- END HEAD -->
<%
String  fullMenu="'";


		

for(int i = 0;i<menuIDPath.size();i++){
	
	int menuID = menuIDPath.get(i);
	if(i!=0){
		fullMenu+="','";
	}
	fullMenu+=menuID;
}

	fullMenu+="'";
	int j;
%>
<body class="hold-transition skin-blue sidebar-mini">
	
	<div class="wrapper">
	 <div id="fakeLoader"></div>
	
	<!-- BEGIN HEADER INNER -->
	<%@ include file="../skeleton/header.jsp"%>
	<!-- END HEADER INNER -->
		
	 <%@ include file="../skeleton/menu.jsp"%>
	<!-- BEGIN CONTAINER -->   
	 <div class="content-wrapper">
	  <!-- Main content -->
    	<section class="content position-fixed">
     	 <!-- Info boxes -->
      		<div class="row">
      		   <div class="col-xs-12 col-sm-12  col-md-12  body-open">
      		     <jsp:include page='<%=request.getParameter("body")%>' />
      		   </div>
      			
      		</div>
      </section>
	 </div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<%@ include file="../skeleton/footer.jsp"%>
	<!-- END FOOTER -->
	<%@ include file="../skeleton/includes.jsp"%>
	
	</div>
	<%
	String[] helpers = request.getParameterValues("helpers");
	for(int i = 0; ArrayUtils.isNotEmpty(helpers)&& i < helpers.length;i++)
	{
	%>
		<jsp:include page="<%=helpers[i] %>" flush="true">
			<jsp:param name="helper" value="<%=i %>" />
		</jsp:include>
	<% } %>
<%
String[] jsStr = request.getParameterValues("js");
for(int i = 0; ArrayUtils.isNotEmpty(jsStr)&& i < jsStr.length;i++)
{
%>
<script src="${context}<%=jsStr[i]%>" type="text/javascript"></script>
<%
}
%>

<script type="text/javascript">

var isMenuvisible = 0;

function showmenu() 
{
	console.log("in showmenu");
	if(isMenuvisible == 0)
	{
		console.log("in showmenu expanding");
		document.getElementById("menudiv").setAttribute("style", "width: 100% !important; height :100% !important; opacity: 1 !important;");
		isMenuvisible = 1;
	}
	else
	{
		console.log("in showmenu shrinking");
		document.getElementById("menudiv").setAttribute("style", "width: 0% !important;");
		isMenuvisible = 0;
	}
}

</script>
</body>
</html>