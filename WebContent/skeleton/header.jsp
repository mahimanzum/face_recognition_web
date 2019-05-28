<%@page import="role.PermissionRepository"%>
<%@page import="permission.MenuConstants"%>
<%@page import="util.JSPConstant"%>
<%@page import="config.GlobalConfigConstants"%>
<%@page import="config.GlobalConfigurationRepository"%>
<%@page import="user.UserDTO"%>
<%@page import="java.util.*"%>
<%@page import="user.UserRepository"%>
<%@page import="language.LC"%>
<%@page import="language.LM"%>
<%@page import="login.LoginDTO"%>
<%@page import="pb_notifications.*"%>
<%@page import="sessionmanager.SessionConstants"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<%
	LoginDTO localLoginDTO = (LoginDTO) request.getSession().getAttribute(SessionConstants.USER_LOGIN);
	UserDTO localUserDTO = UserRepository.getUserDTOByUserID(localLoginDTO.userID);
	
	//System.out.println("localLoginDTO " + localLoginDTO);
	List<Pb_notificationsDTO> pb_notificationsDTOList = new ArrayList();
	Pb_notificationsDAO pb_notificationsDAO = new Pb_notificationsDAO();
	pb_notificationsDTOList= pb_notificationsDAO.getPb_notificationsDTOsBytoUserID(localLoginDTO.userID, localUserDTO.roleID, localUserDTO.centreType, pb_notificationsDTOList);
	
	int countUnSeen = 0;
	
	for(int i = 0; i < pb_notificationsDTOList.size(); i ++)
	{
		//System.out.println("noti: " + pb_notificationsDTOList.get(i).text);
		
		if(pb_notificationsDTOList.get(i).isSeen == false)
		{
			countUnSeen ++;
		}

	}

%>

<header class="main-header">

    <!-- Logo -->
    <a href= "<%=context%>Welcome.do" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini">
      <!-- <b>P</b>B -->
      <img alt="logo" src="<%=context%>assets/static/builder_logo.jpg" style="height:50px ;margin:0px !important">
      	
      </span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg">
      <!-- <b>PROJECT</b>BUILDER -->
       <img alt="logo" src="<%=context%>assets/static/builder_logo.jpg" style="height:50px ;margin:0px !important">
      </span>
    </a>

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
         <ul class="nav navbar-nav">
         <li>
          	<a class="desktop-only" href="<%=context%>languageChangeServlet"> <i class="fa fa-circle"></i><%=LM.getText(LC.GLOBAL_CHANGE_LANGUAGE, localLoginDTO) %></a>
          </li>
          <li>
          	<%if( PermissionRepository.checkPermissionByRoleIDAndMenuID(localUserDTO.roleID, MenuConstants.LANGUAGE_TEXT_EDIT)){ %>
		
		<%
		
			List<Integer> menuIDPathList = (List<Integer>)request.getAttribute("menuIDPath");
			int currentMenuID = ((menuIDPathList!=null && menuIDPathList.size()>0)?menuIDPathList.get(menuIDPathList.size()-1):0);
		%>
			<a class="desktop-only" href="<%=context%>LanguageServlet?actionType=search&menuID=<%=currentMenuID%>&backLinkEnabled=<%=currentMenuID%>&<%=SessionConstants.SEARCH_CHECK_FIELD%>=true&&<%=SessionConstants.HTML_SEARCH_CHECK_FIELD%>=true&<%=SessionConstants.RECORDS_PER_PAGE%>=10000"> <i class="fa fa-circle"></i><%=LM.getText(LC.GLOBAL_CHANGE_LABEL,localLoginDTO) %></a>
		
		<%} %>
          </li> 
          
          <!-- Tasks: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!-- <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image"> -->
              <span><i class="fa fa-circle"></i><%=localUserDTO.userName %></span>
             
            </a>
            <ul class="dropdown-menu" style="width:200px !important">
            
              <li class="user-footer">
                <div class="pull-left">
                  <a href="" class="btn btn-default btn-flat">Dashboard</a>
                </div>
                <div class="pull-right">
                  <a href="<%=context%>LogoutServlet" class="btn btn-default btn-flat"><%=LM.getText(LC.GLOBAL_LOGOUT, localLoginDTO) %></a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Notifications: style can be found in dropdown.less -->
          <li class="dropdown notifications-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" onclick="showNotifications()">
              <i class="fa fa-bell-o"></i>
              <!-- <span class="label label-warning">10</span> -->
            </a>
            <ul class="dropdown-menu">
              <li>
                <!-- inner menu: contains the actual data -->
               <div id="notifications">
	               <ul class="menu">
	                </ul>
               </div>
                
              </li>
              <li class="footer"><a href="#">View all</a></li>
            </ul>
          </li>
        </ul>
      </div>

    </nav>
  </header>
<%
String notify = "";
for(int i = 0; i < pb_notificationsDTOList.size(); i ++)
{
	String classType = "unseen";
	if(pb_notificationsDTOList.get(i).isSeen == true)
	{
		classType = "seen";
	}
	
	 notify = "<li><a class='" + classType + "' onclick = 'return seeNotification(" + pb_notificationsDTOList.get(i).iD + ");' href='" 
			+ pb_notificationsDTOList.get(i).url + "'>" + pb_notificationsDTOList.get(i).text + "</a></li>"; 
	
	/* out.println("<li><a class='" + classType + "' onclick = 'return seeNotification(" + pb_notificationsDTOList.get(i).iD + ");' href='" 
	+ pb_notificationsDTOList.get(i).url + "'>" + pb_notificationsDTOList.get(i).text + "</a></li>"); */
}
%>

<script>

 var notify ="<%=notify%>";

function showNotifications(){
	$("#notifications ul").html(notify);
}
var isNotiShown = 0;

function seeNotification(i)
{
	console.log('seeNotification called');
	var formData = new FormData();
	

	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() 
	{
		if (this.readyState == 4 && this.status == 200) 
		{
			if(this.responseText !='')
			{				
				console.log("Response");
			}
			else
			{
				console.log("No Response");				
			}
		}
		else if(this.readyState == 4 && this.status != 200)
		{
			alert('failed ' + this.status);
		}
	  };
	xhttp.open("POST", 'Pb_notificationsServlet?actionType=makeseen&id=' + i, false);
	xhttp.send(formData);
	return true;
}
</script>