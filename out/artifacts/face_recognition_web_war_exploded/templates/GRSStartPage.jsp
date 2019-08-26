<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String context = "../../.."  + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<!-- saved from url=(0022)http://localhost:8090/ -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
        
        <title>অভিযোগ প্রতিকার ব্যবস্থা</title>
        
        <meta content="IE=edge" http-equiv="X-UA-Compatible">
        <meta content="width=device-width, initial-scale=1" name="viewport">
        <meta content="Welcome to the online platform of Central Grievance Remedies of the Government of the People&#39;s Republic of Bangladesh. You can report your dissatisfaction or opinion about the government service and the promised service of the underwriters/agencies, service delivery methods and the quality of services or products through this website. After submitting the complaint, the latest status of complaint remedies will be communicated through SMS and e-mail. You can also know about update information by logging in. However, if you file a complaint as anonymity, you will not get any further information about the complaint" name="description">
        <meta content="" name="author">
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="<%=context%>/templates/GRSStartPage_files/css" rel="stylesheet" type="text/css">
        <link href="<%=context%>/assets/global/plugins/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css">
        <link href="<%=context%>/templates/GRSStartPage_files/simple-line-icons.min.css" rel="stylesheet" type="text/css">
        <link href="<%=context%>/assets/global/plugins/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="<%=context%>/templates/GRSStartPage_files/bootstrap-switch.min.css" rel="stylesheet" type="text/css">
        <link href="<%=context%>/templates/GRSStartPage_files/toastr.min.css" rel="stylesheet" type="text/css">

        <link href="<%=context%>/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.css" rel="stylesheet" type="text/css">
        <link href="<%=context%>/templates/GRSStartPage_files/select2.min.css" rel="stylesheet" type="text/css">
        <link href="<%=context%>/templates/GRSStartPage_files/select2-bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="<%=context%>/templates/GRSStartPage_files/summernote.css" rel="stylesheet" type="text/css">
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="<%=context%>/templates/GRSStartPage_files/components.min.css" id="style_components" rel="stylesheet" type="text/css">
        <link href="<%=context%>/templates/GRSStartPage_files/plugins.min.css" rel="stylesheet" type="text/css">
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="<%=context%>/templates/GRSStartPage_files/layout.min.css" rel="stylesheet" type="text/css">
        <link href="<%=context%>/templates/GRSStartPage_files/default.min.css" id="style_color" rel="stylesheet" type="text/css">
        <link href="<%=context%>/templates/GRSStartPage_files/custom.css" rel="stylesheet" type="text/css">
        <link href="<%=context%>/templates/GRSStartPage_files/toastr.min.css" rel="stylesheet" type="text/css">
        <link href="<%=context%>/templates/GRSStartPage_files/sweetalert.css" rel="stylesheet" type="text/css">
        <link href="<%=context%>/templates/GRSStartPage_files/star-rating.min.css" rel="stylesheet" type="text/css">

        <!-- END THEME LAYOUT STYLES -->
        <link href="http://localhost:8090/favicon.ico" rel="shortcut icon">
	

</head><body class="page-container-bg-solid" cz-shortcut-listen="true">
<div class="page-wrapper">
    <div class="page-wrapper-row">
    <div class="page-wrapper-top">
        <div class="page-header">
            <div class="page-header-strip">
                <div class="container">
                    <ul class="header-strip-nav">
                        <li class="bangla">
                            <i class="icon-call-end"></i>
                            <a href="tel:+88029113781">+৮৮০২ ৯১১৩৭৮১</a>
                        </li>
                        <li class="english">
                            <i class="icon-envelope"></i>
                            <a href="mailto:info@grs.gov.bd">info@grs.gov.bd</a>
                        </li>
                        
                    </ul>
                </div>
            </div>
            <div class="page-header-top">
                <div class="container left">
                    <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                    <a class="menu-toggler" href="javascript:;"></a>
                    <!-- END RESPONSIVE MENU TOGGLER -->
                    <a class="home-button" href="http://localhost:8090/"></a>
                    <div class="page-logo">
                        <a href="http://localhost:8090/">
                            <img alt="logo" class="logo-default" src="<%=context%>/templates/GRSStartPage_files/Header-logo2.png">
                        </a>
                    </div>
                    <!-- BEGIN TOP NAVIGATION MENU -->
                    <div class="top-menu">
                        <ul class="nav navbar-nav pull-right">
                            
                            
                            
                                <li class="top-menu-complaint">
                                    <a class="btn btn-default " data-toggle="tooltip" href="http://localhost:8090/login/success" title="" data-original-title="আমার প্যানেল">
                                        <i class="icon-user show-on-mobile">&nbsp;</i>
                                        <span class="hide-on-mobile">আমার প্যানেল</span>
                                    </a>
                                </li>
                                <li class="top-menu-complaint">
                                    <a class="btn btn-default " data-toggle="tooltip" href="http://localhost:8090/logout" title="" data-original-title="বাহির">
                                        <i class="icon-power show-on-mobile">&nbsp;</i>
                                        <span class="hide-on-mobile">বাহির</span>
                                    </a>
                                </li>
                            
                            <li class="droddown dropdown-separator">
                                <span class="separator"></span>
                            </li>
                            <li class="dropdown dropdown-language">
                                <a aria-expanded="true" data-close-others="true" href="javascript:setEnglish()" id="selectedLanguageLink">
                                    <span class="langname" id="selectedLanguageName">English</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <!-- END TOP NAVIGATION MENU -->
                </div>
            </div>
            <div class="page-header-menu">
                <div class="container">
                    <!-- BEGIN MEGA MENU -->
                    <!-- DOC: Apply "hor-menu-light" class after the "hor-menu" class below to have a horizontal menu with white background -->
                    <!-- DOC: Remove data-hover="dropdown" and data-close-others="true" attributes below to disable the dropdown opening on mouse hover -->
                    <div class="hor-menu hor-menu-light">
                        <ul class="nav navbar-nav">
                            <li aria-haspopup="true" class="menu-dropdown classic-menu-dropdown ">
                                <ul class="dropdown-menu pull-left" style=" background-color: seashell;">
                                    <li aria-haspopup="true" id="serviceMenu">
                                        <a href="http://localhost:8090/services.do">সেবা প্রদান প্রতিশ্রুতি</a>
                                    </li>
                                    <li aria-haspopup="true" id="onikMenu">
                                        <a href="http://localhost:8090/groInformation.do">অনিক/আপিল কর্মকর্তার তথ্য</a>
                                    </li>
                                    <li aria-haspopup="true" class="dropdown-submenu" id="complaintSubmissionMenu">
                                        <a class="nav-link nav-toggle" href="javascript:;">অভিযোগ দাখিল</a>
                                        <ul class="dropdown-menu" style=" background-color: seashell;">
                                            <li aria-haspopup="true">
                                                <a class="nav-link " href="javascript:addStaffGrievanceButtonEventHandler()">কর্মকর্তা/কর্মচারী-অভিযোগ</a>
                                            </li>
                                            
                                            <li aria-haspopup="true">
                                                <a class="nav-link " href="http://localhost:8090/anonymousAddGrievance.do">অজ্ঞাতনামা হিসেবে অভিযোগ</a>
                                            </li>
                                            <li aria-haspopup="true">
                                                <a class="nav-link " href="javascript:showComplaintStatusModal()">অভিযোগের সর্বশেষ অবস্থা</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li aria-haspopup="true" class=" ">
                                        <a class="nav-link media-embed" data-asset="true" href="http://localhost:8090/assets/grs_user_manual.pdf" onclick="javascript:previewFile(this, event);">ব্যবহারকারী নির্দেশিকা</a>
                                    </li>
                                    <li aria-haspopup="true" class=" ">
                                        <a class="nav-link media-embed" data-asset="true" href="http://localhost:8090/assets/flow_chart.pdf" onclick="javascript:previewFile(this, event);">পদ্ধতি-চিত্র</a>
                                    </li>
                                    <li aria-haspopup="true" class=" ">
                                        <a class="nav-link media-embed" data-asset="true" href="http://localhost:8090/assets/grs_guideline.pdf" onclick="javascript:previewFile(this, event);">জিআরএস নির্দেশিকা</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <!-- END MEGA MENU -->
                </div>
            </div>
        </div>
    </div>
</div>
    <div class="page-wrapper-row full-height">
        <div class="page-wrapper-middle">
            <div class="page-container">
                <div class="page-content-wrapper">
                    <div class="page-body">
                        <div class="container">
                            <div class="row">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="portlet light bordered text-align-justify homepage-first-block">
                                        <div class="portlet-body">
                                            <div class="row">
                                                <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                                                    <p class="fontSize16">
                                                        <span>গণপ্রজাতন্ত্রী বাংলাদেশ সরকারের কেন্দ্রীয় অভিযোগ প্রতিকার ব্যবস্থার অনলাইন প্ল্যাটফর্মে আপনাকে স্বাগতম। সরকারি দপ্তর এবং আওতাধীন দপ্তর/সংস্থার</span>
                                                        <span class="more-text">
                                                            <span>প্রতিশ্রুত সেবা, সেবা প্রদান পদ্ধতি এবং সেবা অথবা পণ্যের মান সম্পর্কে আপনার অসন্তোষ বা মতামত এই ওয়েবসাইটের মাধ্যমে জানাতে পারেন। অভিযোগ দাখিল করার পর SMS ও ই-মেইলের মাধ্যমে অভিযোগ প্রতিকারের সর্বশেষ অবস্থা সম্পর্কে জানানো হবে। এ ছাড়া লগইন করেও হালনাগাদ তথ্য জানা যাবে। তবে অজ্ঞাতনামা হিসেবে অভিযোগ দাখিল করলে অভিযোগ সম্পর্কে পরবর্তী কোনো তথ্য পাওয়া যাবে না। অভিযোগ দাখিলের পদ্ধতি সম্পর্কে বিস্তারিত জানতে</span>
                                                            <a class="media-embed" data-toggle="modal" href="http://localhost:8090/#modalUserManual">
                                                                <span class="blueLink">এখানে ক্লিক করুন</span>...
                                                            </a>
                                                            
                                                        </span>
                                                        
                                                    </p>
                                                </div>
                                                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                                                    <div class="text-align-center">
                                                        <a class="" data-toggle="modal" href="http://localhost:8090/#modalSelectComplaintType">
                                                            <img id="btn_obhijog_dakhil" onmouseenter="loadHoverState(this)" onmouseleave="loadNormalState(this)" src="<%=context%>/templates/GRSStartPage_files/Ovijog-dakhil.png">
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row middle-part">
                                <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
                                    <a class="mt-widget-link" data-toggle="modal" href="http://localhost:8090/#modalFrequentlyAskedQuestions">
                                        <div class="mt-widget-1">
                                            <div class="mt-img mt-img-2">
                                                &nbsp;
                                            </div>
                                            <div class="mt-body">
                                                <h3 class="mt-username">সচরাচর জিজ্ঞাস্য</h3>
                                                <p class="mt-user-title">অভিযোগ দাখিল ও প্রতিকার-সম্পর্কিত প্রশ্নোত্তর</p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
                                    <a class="mt-widget-link" href="javascript:showComplaintStatusModal()">
                                        <div class="mt-widget-1">
                                            <div class="mt-img mt-img-4">
                                                &nbsp;
                                            </div>
                                            <div class="mt-body">
                                                <h3 class="mt-username">অভিযোগের সর্বশেষ অবস্থা</h3>
                                                <p class="mt-user-title">অভিযোগ দাখিলের পর প্রাপ্ত ট্র্যাকিং নম্বর প্রদান করে অভিযোগ প্রতিকারের সর্বশেষ অবস্থা জানতে পারেন</p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
                                    <a class="mt-widget-link" href="javascript:showComplaintFeedbackModal();">
                                        <div class="mt-widget-1">
                                            <div class="mt-img mt-img-3">
                                                &nbsp;
                                            </div>
                                            <div class="mt-body">
                                                <h3 class="mt-username">অভিযোগ নিষ্পত্তিতে প্রতিক্রিয়া</h3>
                                                <p class="mt-user-title">দাখিলকৃত অভিযোগ নিষ্পত্তিতে আপনার প্রতিক্রিয়া (সন্তুষ্টি/অসন্তুষ্টি) জানাতে পারেন</p>
                                            </div>
                                        </div>
                                    </a>
                                </div>

                                <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
                                    <a class="mt-widget-link" href="http://localhost:8090/suggestion.do">
                                        <div class="mt-widget-1">
                                            <div class="mt-img mt-img-1">
                                                &nbsp;
                                            </div>
                                            <div class="mt-body x">
                                                <h3 class="mt-username">সেবার মানোন্নয়নে পরামর্শ</h3>
                                                <p class="mt-user-title">সেবার গুণগত মান বৃদ্ধি, সেবা সহজিকরণ, সংশ্লিষ্ট আইন/বিধি সংস্কার ইত্যাদি বিষয়ে আপনার পরামর্শ জানাতে পারেন</p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="row bottom-part">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="portlet light ">
                                        <div class="portlet-body">
                                            <div class="row">
                                                <div class="col-md-2 col-sm-12 col-xs-12">
                                                    <a class="media-embed" data-asset="true" href="http://localhost:8090/assets/grs_user_manual.pdf" onclick="javascript:previewFile(this, event);">
                                                        <div class="mt-widget-1">
                                                            <div class="mt-img">
                                                                <img src="<%=context%>/templates/GRSStartPage_files/user_manual.png">
                                                            </div>
                                                            <div class="mt-body">
                                                                <p class="mt-user-title">অনলাইনে অভিযোগ/আপিল দাখিল এবং পরবর্তী কার্যক্রমের পদ্ধতি</p>
                                                                <div class="mt-stats">
                                                                    <div class="btn-group btn-group-justified">
                                                                        <button class="btn a2ipurple">
                                                                            <span>ব্যবহারকারী নির্দেশিকা</span>
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="col-md-2 col-sm-12 col-xs-12">
                                                    <a class="media-embed" data-asset="true" href="http://localhost:8090/assets/flow_chart.pdf" onclick="javascript:previewFile(this, event);">
                                                        <div class="mt-widget-1">
                                                            <div class="mt-img">
                                                                <img src="<%=context%>/templates/GRSStartPage_files/flowchart.png">
                                                            </div>
                                                            <div class="mt-body">
                                                                <p class="mt-user-title">অভিযোগ প্রতিকারের কার্যপদ্ধতির প্রবাহ চিত্র</p>
                                                                <div class="mt-stats">
                                                                    <div class="btn-group btn-group-justified">
                                                                        <button class="btn a2igreen">
                                                                            <span>পদ্ধতি-চিত্র</span>
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="col-md-2 col-sm-12 col-xs-12">
                                                    <a class="" href="http://localhost:8090/services.do">
                                                        <div class="mt-widget-1">
                                                            <div class="mt-img">
                                                                <img src="<%=context%>/templates/GRSStartPage_files/citizen_charter.png">
                                                            </div>
                                                            <div class="mt-body">
                                                                <p class="mt-user-title">সরকারি দপ্তর ও আওতাধীন দপ্তরের সেবার তালিকা</p>
                                                                <div class="mt-stats">
                                                                    <div class="btn-group btn-group-justified">
                                                                        <button class="btn a2ipurple">
                                                                            <span>সেবা প্রদান প্রতিশ্রুতি</span>
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="col-md-2 col-sm-12 col-xs-12">
                                                    <a data-toggle="modal" href="http://localhost:8090/#grievanceFormsList">
                                                        <div class="mt-widget-1">
                                                            <div class="mt-img">
                                                                <img src="<%=context%>/templates/GRSStartPage_files/forms.png">
                                                            </div>
                                                            <div class="mt-body">
                                                                <p class="mt-user-title">অফলাইনে অভিযোগ দাখিলের জন্য প্রয়োজনীয় ফরমস ডাউনলোড</p>
                                                                <div class="mt-stats">
                                                                    <div class="btn-group btn-group-justified">
                                                                        <button class="btn a2igreen">
                                                                            <span>ডাউনলোডস</span>
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </a>
                                                </div>

                                                <div class="col-md-2 col-sm-12 col-xs-12">
                                                    <a class="media-embed" data-asset="true" href="http://localhost:8090/assets/grs_guideline.pdf" onclick="javascript:previewFile(this, event);">
                                                        <div class="mt-widget-1">
                                                            <div class="mt-img">
                                                                <img src="<%=context%>/templates/GRSStartPage_files/guidline.png">
                                                            </div>
                                                            <div class="mt-body">
                                                                <p class="mt-user-title">অভিযোগ প্রতিকার ব্যবস্থা-সংক্রান্ত নির্দেশিকা ২০১৫ (পরিমার্জিত ২০১৮)</p>
                                                                <div class="mt-stats">
                                                                    <div class="btn-group btn-group-justified">
                                                                        <button class="btn a2ipurple">
                                                                            <span>জিআরএস নির্দেশিকা</span>
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </a>
                                                </div>
                                                <div class="col-md-2 col-sm-12 col-xs-12">
                                                    <a data-toggle="modal" href="http://localhost:8090/#modalContactInformation">
                                                        <div class="mt-widget-1">
                                                            <div class="mt-img">
                                                                <img src="<%=context%>/templates/GRSStartPage_files/contact.png">
                                                            </div>
                                                            <div class="mt-body">
                                                                <p class="mt-user-title">অনলাইন অভিযোগ প্রতিকার ব্যবস্থায় কোনো টেকনিক্যাল সমস্যার সম্মুখীন হলে যোগাযোগ</p>
                                                                <div class="mt-stats">
                                                                    <div class="btn-group btn-group-justified">
                                                                        <button class="btn a2igreen">
                                                                            <span>যোগাযোগ</span>
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="page-wrapper-row">
    <div class="page-wrapper-bottom">
        <!-- BEGIN FOOTER -->
        <!-- BEGIN PRE-FOOTER -->
        <div class="page-prefooter">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-6 col-xs-12 footer-block">
                        <h4>গুরুত্বপূর্ণ তথ্য</h4>
                        <ul class="fa-ul text-left">
                            <li><a href="javascript:featureComingSoon();">ব্যবহারের শর্তাবলি</a></li>
                            <li><a data-toggle="modal" href="http://localhost:8090/#modalOverallCollaborators">সার্বিক সহযোগিতায়</a></li>
                            <li><a data-toggle="modal" href="http://localhost:8090/#modalContactInformation">যোগাযোগ</a></li>
                        </ul>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-12 footer-block">
                        <h4>গুরুত্বপূর্ণ লিঙ্ক</h4>
                        <ul class="fa-ul text-left">
                            <li><a href="http://bangladesh.gov.bd/" target="_blank">জাতীয় তথ্য বাতায়ন</a></li>
                            <li><a href="http://www.cabinet.gov.bd/" target="_blank">মন্ত্রিপরিষদ বিভাগ</a></li>
                            <li><a href="http://a2i.pmo.gov.bd/" target="_blank">এটুআই</a></li>
                            <li><a href="http://online.forms.gov.bd/" target="_blank">অনলাইন আবেদনপত্র</a></li>
                        </ul>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-12 footer-block">
                        <h4>সামাজিক যোগাযোগ</h4>
                        <a class="social-icon facebook" data-original-title="facebook" href="javascript:featureComingSoon();"> </a>
                        <a class="social-icon twitter" data-original-title="twitter" href="javascript:featureComingSoon();"> </a>
                        <a class="social-icon youtube" data-original-title="youtube" href="javascript:featureComingSoon();"> </a>
                        <a class="social-icon googleplus" data-original-title="Goole Plus" href="javascript:featureComingSoon();"> </a>

                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-12 footer-block">
                        <h4>পরিকল্পনা ও বাস্তবায়নে</h4>
                        <ul class="footer_logos">
                            <li>
                                <a href="http://a2i.pmo.gov.bd/" target="_blank">
                                    <img height="40" src="<%=context%>/templates/GRSStartPage_files/a2i.png">
                                </a>
                            </li>
                            <li>
                                <a href="http://cabinet.gov.bd/" target="_blank">
                                    <img height="40" src="<%=context%>/templates/GRSStartPage_files/bd_gov.png">
                                </a>
                            </li>
                        </ul>
                        <div class="cleardiv">&nbsp;</div>
                        <div class="copy-right-title">
                            <p>স্বর্বসত্ত্ব © ২০১৭ মন্ত্রিপরিষদ বিভাগ</p>
                            <p>গণপ্রজাতন্ত্রী বাংলাদেশ সরকার</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="scroll-to-top">
            <i class="icon-arrow-up"></i>
        </div>
        <!-- END INNER FOOTER -->
        <!-- END FOOTER -->
    </div>

    <div aria-hidden="true" class="modal fade bs-modal-lg" id="modalSelectComplaintType" role="basic" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button aria-hidden="true" class="close" data-dismiss="modal" type="button"></button>
                    <h4 class="modal-title fontSize20">
                        <span class="font-purple bold">অভিযোগের ধরন বাছাই করুন</span>
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="horizontal-form">
                        <div class="form-body">
                            <div class="row">
                                
                                <div class="col-md-6">
                                    <div class="portlet portlet-fit light bg-inverse">
                                        <div class="portlet-title">
                                            <div class="caption">
                                                <i class="fa fa-bank"></i> <span>কর্মকর্তা/কর্মচারী-অভিযোগ</span>
                                            </div>

                                        </div>
                                        <div class="portlet-body">
                                            <span>কর্মকর্তা/কর্মচারী হিসেবে নিজ দপ্তর হতে প্রাপ্য সেবা-সংশ্লিষ্ট অভিযোগ</span>
                                        </div>
                                        <div class="portlet-footer text-align-right">
                                            <a class="btn btn-abbhontorin" href="javascript:addStaffGrievanceButtonEventHandler()">
                                                <span>কর্মকর্তা/কর্মচারী-অভিযোগ</span>
                                                <i class="fa fa-caret-right"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="portlet portlet-fit light bg-inverse">
                                        <div class="portlet-title">
                                            <div class="caption">
                                                <i class="fa fa-user-secret"></i> <span>অজ্ঞাতনামা হিসেবে অভিযোগ</span>
                                            </div>
                                        </div>
                                        <div class="portlet-body">
                                            <span>ব্যক্তিগত তথ্য গোপন রেখে অভিযোগ দাখিল করতে পারেন</span>
                                        </div>
                                        <div class="portlet-footer text-align-right">
                                            <a class="btn btn-daptorik" href="http://localhost:8090/anonymousAddGrievance.do">
                                                <span>অজ্ঞাতনামা হিসেবে অভিযোগ</span>
                                                <i class="fa fa-caret-right"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <p class="fontSize14 margin0 text-danger bold">বি.দ্র. ধর্মীয়, কোনো আদালতে বিচারাধীন, তথ্য অধিকার, সরকারি কর্মকর্তা-কর্মচারীদের বিরুদ্ধে গৃহীত বিভাগীয় মামলা অথবা আইন বা বিধির আওতায় রিভিউ/আপিলের সুযোগ রয়েছে এরূপ বিষয়-সংশ্লিষ্ট অভিযোগ গ্রহণযোগ্য হবে না।</p>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div aria-hidden="true" class="modal fade" data-backdrop="static" data-keyboard="false" id="complaintStatusModal" role="basic" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <span class="font-lg font-green bold">অভিযোগের অবস্থা জানুন</span>
                    <button class="close" data-dismiss="modal" onclick="javascript:hideComplaintStatusModal()" type="button">×</button>
                </div>
                <div class="modal-body form">
                    <form action="http://localhost:8090/#" class="form-horizontal">
                        <div align="center" class="form-body font-md">
                            <div class="form-group">
                                <label class="control-label">
                                    <span class="font-purple">অভিযোগের অবস্থা জানতে হলে মোবাইল নম্বর এবং ট্রাকিং নম্বর দিয়ে বাটনে ক্লিক করুন ।</span>
                                </label>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4">
                                    <span>মোবাইল নম্বর</span>
                                    <span class="required">*</span>
                                </label>
                                <div class="col-md-6">
                                    <div class="input-group has-success">
                                        <span class="input-group-addon">
                                            <i class="fa fa-tablet"></i>
                                        </span>
                                        <input autocomplete="off" class="form-control" name="mobileNumber" type="text">
                                        <div class="form-control-focus"> </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4">
                                    <span>ট্র্যাকিং নম্বর</span>
                                    <span class="required">*</span>
                                </label>
                                <div class="col-md-6">
                                    <div class="input-group has-success">
                                        <span class="input-group-addon">
                                            <i class="fa fa-commenting-o"></i>
                                        </span>
                                        <input autocomplete="off" class="form-control" name="trackingNumber" type="text">
                                        <div class="form-control-focus"> </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary" onclick="javascript:getStatus(&#39;grievanceStatus&#39;, &#39;mobileNumber&#39;, &#39;trackingNumber&#39;)" type="button">
                                    <span>অবস্থা জানুন </span>
                                </button>
                            </div>
                            <div class="form-group">
                                <table class="table table-hover table-bordered" id="grievanceStatus" style="display: none">
                                    <thead>
                                        <tr>
                                            <td>অভিযোগের তারিখ</td>
                                            <td>সংশ্লিষ্ট সেবা</td>
                                            <td>অভিযোগের অবস্থা</td>
                                            <td>নিষ্পত্তির সম্ভাব্য তারিখ</td>
                                        </tr>
                                    </thead>
                                    <tbody></tbody>
                                </table>
                                <div id="grievanceStatusMore"></div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div aria-hidden="true" class="modal fade" data-backdrop="static" data-keyboard="false" id="complaintFeedbackModal" role="basic" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <span class="font-lg font-green bold">অভিযোগের অবস্থা জানুন</span>
                    <button class="close" data-dismiss="modal" onclick="javascript:hideComplaintStatusModal()" type="button">×</button>
                </div>
                <div class="modal-body form">
                    <form action="http://localhost:8090/#" class="form-horizontal">
                        <div align="center" class="form-body font-md">
                            <div class="form-group">
                                <label class="control-label">
                                    <span class="font-purple">অভিযোগের অবস্থা জানতে হলে মোবাইল নম্বর এবং ট্রাকিং নম্বর দিয়ে বাটনে ক্লিক করুন ।</span>
                                </label>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4">
                                    <span>মোবাইল নম্বর</span>
                                    <span class="required">*</span>
                                </label>
                                <div class="col-md-6">
                                    <div class="input-group has-success">
                                        <span class="input-group-addon">
                                            <i class="fa fa-tablet"></i>
                                        </span>
                                        <input autocomplete="off" class="form-control" name="mobileNumberFeedback" type="text">
                                        <div class="form-control-focus"> </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4">
                                    <span>ট্র্যাকিং নম্বর</span>
                                    <span class="required">*</span>
                                </label>
                                <div class="col-md-6">
                                    <div class="input-group has-success">
                                        <span class="input-group-addon">
                                            <i class="fa fa-commenting-o"></i>
                                        </span>
                                        <input autocomplete="off" class="form-control" name="trackingNumberFeedback" type="text">
                                        <div class="form-control-focus"> </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary" onclick="javascript:getStatus(&#39;grievanceFeedback&#39;, &#39;mobileNumberFeedback&#39;, &#39;trackingNumberFeedback&#39;)" type="button">
                                    <span>অবস্থা জানুন </span>
                                </button>
                            </div>
                            <div class="form-group">
                                <table class="table table-hover table-bordered" id="grievanceFeedback" style="display: none">
                                    <thead>
                                    <tr>
                                        <td>অভিযোগের তারিখ</td>
                                        <td>সংশ্লিষ্ট সেবা</td>
                                        <td>অভিযোগের অবস্থা</td>
                                        <td>নিষ্পত্তির সম্ভাব্য তারিখ</td>
                                    </tr>
                                    </thead>
                                    <tbody></tbody>
                                </table>
                                <div id="grievanceFeedbackMore"></div>
                            </div>

                            <div id="feedback-div" role="document" style="display: none">
                                <h5 class="modal-title">আপনার প্রতিক্রিয়া</h5>
                                
                                    <div class="form-group">
                                        <label>রেটিং</label>
                                        <div class="input-group">
                                            <div class="rating-container rating-sm rating-animate"><div class="clear-rating clear-rating-active" title="Clear"><i class="glyphicon glyphicon-minus-sign"></i></div><div class="rating-stars"><span class="empty-stars"><span class="star"><i class="glyphicon glyphicon-star-empty"></i></span><span class="star"><i class="glyphicon glyphicon-star-empty"></i></span><span class="star"><i class="glyphicon glyphicon-star-empty"></i></span><span class="star"><i class="glyphicon glyphicon-star-empty"></i></span><span class="star"><i class="glyphicon glyphicon-star-empty"></i></span></span><span class="filled-stars" style="width: 0%;"><span class="star"><i class="glyphicon glyphicon-star"></i></span><span class="star"><i class="glyphicon glyphicon-star"></i></span><span class="star"><i class="glyphicon glyphicon-star"></i></span><span class="star"><i class="glyphicon glyphicon-star"></i></span><span class="star"><i class="glyphicon glyphicon-star"></i></span></span><input class="form-control rating rating-input" data-bind="value: isRated" data-size="sm" id="rating" type="text"></div><div class="caption"><span class="label label-default">Not Rated</span></div></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>আপনার মন্তব্য</label>
                                        <div class="input-group" style="text-align: left !important;">
                                            <div class="summernote form-control" id="txtaFeedback"></div>
                                        </div>
                                    </div>
                                
                                <button class="btn btn-secondary" data-dismiss="modal" type="button">বাতিল</button>
                                <button class="btn btn-primary btn-feedback-send" data-bind="enable: isRated" data-dismiss="modal" id="feedbackButton" type="button">প্রেরণ করুন</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" data-backdrop="static" data-keyboard="false" id="fileViewerModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button aria-hidden="true" class="close" data-dismiss="modal" type="button"></button>
                </div>
                <div class="modal-body"></div>
                <div class="modal-footer">
                    <button class="btn dark btn-outline" data-dismiss="modal" type="button">বন্ধ করুন</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" data-backdrop="static" data-keyboard="false" id="modalOverallCollaborators" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button aria-hidden="true" class="close" data-dismiss="modal" type="button"></button>
                    <h4 class="modal-title text-center font-lg font-green bold">সার্বিক সহযোগিতায়</h4>
                </div>
                <div class="modal-body">
                    
    <div class="faq-section">
        <h2 class=" font-blue bold font-lg">পরিকল্পনা, নকশা ও উন্নয়ন</h2>
        <div class="panel-group faq-content">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <i class="fa fa-circle font-purple"></i>
                        <span>এন এম জিয়াউল আলম, সচিব (সমন্বয় ও সংস্কার), মন্ত্রিপরিষদ বিভাগ</span>
                    </h4>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <i class="fa fa-circle font-purple"></i>
                        <span>ড. মো: আব্দুল মান্নান, ডিরেক্টর (ই-সার্ভিস), এটুআই প্রোগ্রাম</span>
                    </h4>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <i class="fa fa-circle font-purple"></i>
                        <span>মোহাম্মদ জাহেদুর রহমান, উপসচিব, অভিযোগ ব্যবস্থাপনা শাখা, মন্ত্রিপরিষদ বিভাগ</span>
                    </h4>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <i class="fa fa-circle font-purple"></i>
                        <span>মু. ইকরামুল ইসলাম, ই-সার্ভিস এক্সপার্ট (সিনিয়র সহকারী সচিব), এটুআই প্রোগ্রাম</span>
                    </h4>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <i class="fa fa-circle font-purple"></i>
                        <span>শামীম আরা নিপা, সিনিয়র সহকারী সচিব, এটুআই প্রোগ্রাম</span>
                    </h4>
                </div>
            </div>
        </div>
        <h2 class=" font-blue bold font-lg">প্রকৌশল উদ্ভাবন এবং প্রযুক্তিগত উন্নয়ন</h2>
        <div class="panel-group faq-content">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <i class="fa fa-circle font-purple"></i>
                        <span>আরফে এলাহি মানিক, আইটি ম্যানেজার, এটুআই প্রোগ্রাম</span>
                    </h4>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <i class="fa fa-circle font-purple"></i>
                        <span>মুহাম্মদ ইউনুস আলী সরদার, টেকনোলজি এক্সপার্ট, এটুআই প্রোগ্রাম</span>
                    </h4>
                </div>
            </div>
        </div>
    </div>

                </div>
                <div class="modal-footer">
                    <button class="btn dark btn-outline" data-dismiss="modal" type="button">বন্ধ করুন</button>
                </div>
            </div>
        </div>
    </div>
    <div aria-hidden="false" class="modal fade" data-attention-animation="false" data-backdrop="static" data-keyboard="false" id="modalContactInformation" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button aria-hidden="true" class="close" data-dismiss="modal" type="button"></button>
                    <h4 class="modal-title text-center font-lg font-green bold">যোগাযোগ</h4>
                </div>
                <div class="modal-body text-center font-purple">
                    <div><span>ই-মেইল:</span>&nbsp;<a class="blueLink" href="mailto:info@grs.gov.bd">info@grs.gov.bd</a></div>
                    <br>
                    <div><span>হেল্পডেস্ক ফোন নং:</span>&nbsp;<a class="blueLink" href="tel:+88029113781">+৮৮০২ ৯১১৩৭৮১</a></div>
                </div>
                <div class="modal-footer">
                    <button class="btn dark btn-outline" data-dismiss="modal" type="button">বন্ধ করুন</button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<div aria-hidden="true" class="modal fade" data-backdrop="static" data-keyboard="false" id="grievanceFormsList" role="basic" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button aria-hidden="true" class="close" data-dismiss="modal" type="button"></button>
                <h4 class="modal-title text-center font-lg font-green bold">ডাউনলোড করার জন্য ফরম বাছাই করুন</h4>
            </div>
            <div class="modal-body">
                <ul>
                    <li>
                        <span><i class="fa fa-arrow-circle-o-down"></i></span>
                        <a class="media-embed" data-asset="true" href="http://localhost:8090/assets/grievance_submission_form.pdf" onclick="javascript:previewFile(this, event);">
                            <span class="blueLink">অভিযোগ দাখিল ফরম</span>
                        </a>
                    </li>
                    <li>
                        <span><i class="fa fa-arrow-circle-o-down"></i></span>
                        <a class="media-embed" data-asset="true" href="http://localhost:8090/assets/appeal_submission_form.pdf" onclick="javascript:previewFile(this, event);">
                            <span class="blueLink">আপিলের ফরম</span>
                        </a>
                    </li>
                    <li>
                        <span><i class="fa fa-arrow-circle-o-down"></i></span>
                        <a class="media-embed" data-asset="true" href="http://localhost:8090/assets/cell_grievance_submission_form.pdf" onclick="javascript:previewFile(this, event);">
                            <span class="blueLink">সেল-এ অভিযোগের ফরম</span>
                        </a>
                    </li>
                    <li>
                        <span><i class="fa fa-arrow-circle-o-down"></i></span>
                        <a class="media-embed" data-asset="true" href="http://localhost:8090/assets/cell_appeal_submission_form.pdf" onclick="javascript:previewFile(this, event);">
                            <span class="blueLink">সেল-এ আপিলের ফরম</span>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="modal-footer">
                <button class="btn dark btn-outline" data-dismiss="modal" type="button"><span>বন্ধ করুন</span></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" data-backdrop="static" data-keyboard="false" id="modalFrequentlyAskedQuestions" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button aria-hidden="true" class="close" data-dismiss="modal" type="button"></button>
                <h4 class="modal-title text-center font-lg font-green bold">সচরাচর জিজ্ঞাস্য</h4>
            </div>
            <div class="modal-body">
                
    <div class="faq-section ">
        <div class="panel-group faq-content" id="accordion_faq">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_1">অভিযোগ কি?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_1" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md"> সরকারি দপ্তর অথবা আইনের আওতায় নিবন্ধিত সেবা প্রদানকারী প্রতিষ্ঠান কর্তৃক প্রতিশ্রুত সেবা বা পণ্য এবং/অথবা সেবাপদ্ধতি সম্পর্কে সেবাপ্রত্যাশীদের অসন্তুষ্টি অথবা প্রদেয়/প্রদত্ত সেবার সঙ্গে সম্পর্কিত বিধি-বহির্ভূত কাজ অথবা সেবাপ্রত্যাশীদের বৈধ অধিকার প্রদানে অস্বীকৃতির বিষয়ে ইলেকট্রনিক বা প্রচলিত পদ্ধতিতে (নির্ধারিত ফরমে) দায়েরকৃত দরখাস্ত অভিযোগ হিসেবে বিবেচিত হবে। </p>
                    </div>

                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_2"> সেবা প্রদান প্রতিশ্রুতি (Citizen’s Charter) কি?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_2" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md"> বিভিন্ন সরকারি দপ্তর কর্তৃক সরাসরি অথবা স্বীকৃত সেবা-প্রদানকারী প্রতিষ্ঠানের মাধ্যমে নাগরিকগণকে সেবা প্রদান-সম্পর্কিত বিষয়ে লিখিত প্রতিশ্রুতি।</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_3"> অভিযোগ প্রতিকার ব্যবস্থা কি?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_3">
                    <div class="panel-body">
                        <p class="font-md"> সেবাপ্রত্যাশীদের অসন্তুষ্টি সংক্রান্ত বিষয়ে প্রতিকার চাওয়া বা বক্তব্য প্রদানের একটি কার্যকর ক্ষেত্র বা  প্ল্যাটফরম থাকলে তাঁদের অসন্তোষ বা ক্ষোভ প্রশমনের সুযোগ সৃষ্টি হয়। এ লক্ষ্যে অভিযোগ প্রতিকার ব্যবস্থার অনলাইন প্ল্যাটফরম প্রস্তুত করা হয়েছে।</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_4"> কী ধরনের বিষয়ে অভিযোগ দাখিল করা যাবে?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_4">
                    <div class="panel-body">
                        <p class="font-md"> জনসেবার সঙ্গে সংশ্লিষ্ট দপ্তরসমূহের প্রতিশ্রুত সেবা, সেবা প্রদান পদ্ধতি এবং সেবা ও পণ্যের মান সম্পর্কে সেবাপ্রার্থীদের অসন্তুষ্টি বা সংক্ষুব্ধতা বিষয়ে অভিযোগ দাখিল করা যেতে পারে।</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_5"> কী ধরনের বিষয়ে অভিযোগ দাখিল করা যাবে না?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_5">
                    <div class="panel-body">
                        <div class="font-md"> নিম্নোক্ত বিষয়সমূহের ক্ষেত্রে অভিযোগ দাখিল করা যাবে না-</div>
                        <ul class="font-md">
                            <li>কোনো আদালতে বিচারাধীন ;</li>
                            <li>তথ্য অধিকার-সংক্রান্ত; এবং</li>
                            <li>আদেশ প্রাপ্তির পর সংশ্লিষ্ট আইন বা বিধির আওতায় রিভিউ/আপিলের সুযোগ রয়েছে।</li>
                            <li>সেবা প্রদান প্রতিশ্রুতিতে বর্ণিত সংশ্লিষ্ট সেবা প্রদানের নির্ধারিত সময়সীমা অতিক্রান্ত হওয়ার পূর্বে অভিযোগ দাখিল করা যাবে না। তবে নির্ধারিত সময়ের পূর্বেই সেবার আবেদনের বিষয়ে সিদ্ধান্ত গৃহীত হয়ে থাকলে অভিযোগ দাখিল করা যাবে</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_6"> কোন কোন দপ্তরের ক্ষেত্রে অভিযোগ দাখিল করা যাবে?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_6">
                    <div class="panel-body">
                        <p class="font-md"> সকল সরকারি দপ্তর এবং আওতাধীন প্রতিষ্ঠান/সংস্থার জন্য প্রযোজ্য হবে। আইনের আওতায় নিবন্ধিত সেবা প্রদানকারী প্রতিষ্ঠানের ক্ষেত্রে নিয়ন্ত্রণকারী সরকারি দপ্তর অভিযোগ প্রতিকারের ব্যবস্থা গ্রহণ করবে।</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_7"> নাগরিক অভিযোগ (Public Grievance) কী?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_7">
                    <div class="panel-body">
                        <p class="font-md"> সরকারি দপ্তর অথবা আইনের আওতায় নিবন্ধিত সেবা প্রদানকারী প্রতিষ্ঠানের প্রতিশ্রুত সেবা বা পণ্য এবং/অথবা সেবা প্রদান পদ্ধতি সম্পর্কে কোনো নাগরিকের অসন্তুষ্টি অথবা প্রদেয়/প্রদত্ত সেবার সঙ্গে সম্পর্কিত বিধি-বহির্ভূত কাজ অথবা নাগরিকের বৈধ অধিকার প্রদানে অস্বীকৃতির বিষয়ে দাখিলকৃত অভিযোগ নাগরিক অভিযোগ হিসেবে বিবেচিত হবে।</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_8"> কর্মকর্তা-কর্মচারী অভিযোগ (Staff Grievance) কী?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_8">
                    <div class="panel-body">
                        <p class="font-md"> সরকারি দপ্তরে কর্মরত অথবা অবসরপ্রাপ্ত কোনো কর্মকর্তা-কর্মচারী সংশ্লিষ্ট কর্তৃপক্ষের নিকট হতে কর্মকর্তা-কর্মচারী হিসেবে তাঁর প্রাপ্য যেকোনো সেবা বা বৈধ অধিকার প্রাপ্তির ক্ষেত্রে অসন্তুষ্ট বা সংক্ষুব্ধ হয়ে প্রতিকারের জন্য আবেদন দাখিল করলে তা কর্মকর্তা-কর্মচারী অভিযোগ হিসেবে গণ্য হবে। অবসরপ্রাপ্ত কর্মকর্তা-কর্মচারীগণের পেনশন, আনুতোষিক, আর্থিক সুবিধা-সংক্রান্ত যেকোনো অভিযোগও এ শ্রেণির অভিযোগের  অন্তর্ভুক্ত হবে।</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_9"> দাপ্তরিক অভিযোগ (Organizational Grievance) কী?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_9">
                    <div class="panel-body">
                        <p class="font-md"> কোনো সরকারি দপ্তরের প্রতিশ্রুত সেবা বা পণ্য এবং/অথবা সেবা প্রদান পদ্ধতি বা বৈধ অধিকার-সংক্রান্ত বিষয়ে কোনো দপ্তর কর্তৃক দাখিলকৃত অভিযোগ দাপ্তরিক অভিযোগ হিসেবে বিবেচিত হবে।</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_10"> কে অভিযোগ দাখিল করতে পারবেন?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_10">
                    <div class="panel-body">
                        <p class="font-md"> সংক্ষুব্ধ ব্যক্তি, প্রতিষ্ঠান অথবা তাঁদের মনোনীত প্রতিনিধি অভিযোগ দায়ের করতে পারবেন। প্রতিষ্ঠানের ক্ষেত্রে প্রতিষ্ঠান প্রধান কর্তৃক মনোনীত প্রতিনিধি এবং ব্যক্তির ক্ষেত্রে নিকটাত্মীয় (বাবা-মা, স্বামী-স্ত্রী অথবা সন্তান) প্রতিনিধি হিসেবে অভিযোগ দাখিল করতে পারবেন। দাপ্তরিক-অভিযোগের ক্ষেত্রে সংশ্লিষ্ট দপ্তর প্রধান অভিযোগ দাখিল করতে পারবেন।</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_11"> পরিচয় গোপন রেখে অভিযোগ দাখিল করা যাবে কি-না?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_11">
                    <div class="panel-body">
                        <p class="font-md"> ব্যক্তিগত তথ্য প্রদান না করলে অভিযোগকারীকে অজ্ঞাতনামা হিসেবে গণ্য করা হবে। অজ্ঞাতনামা হিসেবে অভিযোগ দায়ের করলে অভিযোগটির ওপর কার্যক্রম গ্রহণ করা হবে কিনা, সে বিষয়ে অভিযোগের ধরন / গুরুত্ব অনুযায়ী অনিক যথাযথ পদক্ষেপ গ্রহণ করবেন। এক্ষেত্রে অভিযোগ চূড়ান্ত নিষ্পত্তি করা সম্ভব নাও হতে পারে।</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_12"> কার নিকট অভিযোগ দাখিল করতে হবে?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_12">
                    <div class="panel-body">
                        <div class="font-md"> সকল সরকারি দপ্তরে সেবা-সংক্রান্ত অভিযোগ গ্রহণ এবং তা প্রতিকারের জন্য একজন অভিযোগ নিষ্পত্তি কর্মকর্তা (অনিক) দায়িত্ব পালন করবেন:</div>
                        <ul class="font-md">
                            <li> জেলা, বিভাগীয়/আঞ্চলিক পর্যায়ের দপ্তর এবং অধিদপ্তর/সংস্থা/অন্যান্য দপ্তরের অভিযোগ নিষ্পত্তি কর্মকর্তা হবেন দপ্তর প্রধান অথবা তাঁর মনোনীত একজন জ্যেষ্ঠ কর্মকর্তা। জেলা পর্যায়ের দপ্তরের অনিক আওতাধীন ইউনিয়ন ও উপজেলা পর্যায়ের দপ্তরসমূহের অনিক হিসেবে গণ্য হবেন; এবং</li>
                            <li> মন্ত্রণালয়/বিভাগের অভিযোগ নিষ্পত্তি কর্মকর্তা হবেন একজন যুগ্মসচিব। অভিযোগ নিষ্পত্তি কর্মকর্তার তথ্য জানতে <a class="blueLink" href="http://localhost:8090/groInformation.do" target="_blank">এখানে ক্লিক করুন</a>।</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_13"> কীভাবে অভিযোগ দাখিল করবেন?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_13">
                    <div class="panel-body">
                        <p class="font-md"> অভিযোগ প্রতিকার ব্যবস্থা-সংক্রান্ত ওয়েবসাইট (<a class="blueLink" href="http://www.grs.gov.bd/">www.grs.gov.bd</a>)-এর মাধ্যমে অভিযোগ দাখিল করা যাবে। এ ছাড়া ইলেক্ট্রনিক পদ্ধতিতে (ইমেইল, ই-ফাইল অথবা কল সেন্টারের মাধ্যমে) অথবা প্রচলিত পদ্ধতিতে (সংশ্লিষ্ট দপ্তরে উপস্থিত হয়ে অথবা ডাকযোগে) অভিযোগ দাখিল করা যাবে। মন্ত্রণালয়/বিভাগ এবং অভিযোগ ব্যবস্থাপনা সেলে দাখিলযোগ্য অভিযোগের ক্ষেত্রে প্রচলিত পদ্ধতিতে সচিবালয়ের গেটে অবস্থিত মন্ত্রিপরিষদ বিভাগের অভিযোগ গ্রহণ কেন্দ্রে দাখিল করা যাবে। অভিযোগ দাখিলের ক্ষেত্রে নির্ধারিত ফরম (সংযোজনী ‘খ-১’) ব্যবহার করতে হবে।</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_14"> অভিযোগ নিষ্পত্তির সময়সীমা কত?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_14">
                    <div class="panel-body">
                        <ul class="font-md">
                            <li>
                                <div>অভিযোগ নিষ্পত্তি কর্মকর্তা কর্তৃক অভিযোগ নিষ্পত্তির সর্বোচ্চ সময়সীমা নিম্নরূপ- </div>
                                <ol>
                                    <li type="i"> সাধারনভাবে অভিযোগ নিষ্পত্তির সর্বোচ্চ সময়সীমা ৪০ কার্যদিবস;</li>
                                    <li type="i"> তদন্তের উদ্যোগ গৃহীত হলে অতিরিক্ত ২০ কার্যদিবস সময়ের মধ্যে অভিযোগ নিষ্পত্তি করতে হবে।</li>
                                </ol>
                            </li>
                            <li> আপিল নিষ্পত্তির সর্বোচ্চ সময়সীমা আপিল দাখিলের তারিখ থেকে অনধিক ২০ কার্যদিবস;</li>
                            <li> অভিযোগ ব্যবস্থাপনা সেল কর্তৃক অভিযোগ/আপিল নিষ্পত্তির সর্বোচ্চ সময়সীমা অনধিক ৬০ কার্যদিবস।</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_15"> অভিযোগ নিষ্পত্তির সর্বশেষ অবস্থা কীভাবে জানা যাবে?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_15">
                    <div class="panel-body">
                        <p class="font-md"> ওয়েব-ভিত্তিক অভিযোগ প্রতিকার ব্যবস্থায় প্রাপ্ত অথবা আপলোডকৃত অভিযোগসমূহের ট্র্যাকিং নম্বর সংবলিত এসএমএস এবং/অথবা ইমেইল অভিযোগকারীকে প্রদান করতে হবে। ওয়েব-ভিত্তিক সিস্টেম চালু না থাকলে এসএমএস, ইমেইল অথবা ডাকযোগে প্রাপ্তিস্বীকার প্রদান করতে হবে।</p>
                        <p class="font-md"> অনলাইন অভিযোগ প্রতিকার ব্যবস্থার ওয়েবসাইটে (<a class="blueLink" href="http://www.grs.gov.bd/">www.grs.gov.bd</a>) ‘অভিযোগের অবস্থা জানুন’ বাটনে ক্লিক করে প্রাপ্ত ট্র্যাকিং নম্বর ব্যবহার করে অভিযোগ নিষ্পত্তির সর্বশেষ অবস্থা জানা যাবে। এছাড়া ব্যবহারকারী হিসেবে লগইন করে অভিযোগের তালিকা থেকে অভিযোগ নিষ্পত্তির সর্বশেষ অবস্থা জানতে পারবেন।</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_16"> কালো তালিকা কী?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_16">
                    <div class="panel-body">
                        <p class="font-md"> কোনো অভিযোগকারী অভ্যাসগতভাবে অসত্য এবং কাউকে হয়রানি করার জন্য কিংবা অন্য কোনো অসৎ উদ্দেশ্যে অভিযোগ দাখিল করেন মর্মে প্রমাণিত হলে অনিক-এর সুপারিশের ভিত্তিতে সংশ্লিষ্ট আপিল কর্মকর্তা উক্ত অভিযোগকারীকে কালো তালিকাভুক্ত করতে পারবেন। এরূপ কালো তালিকাভুক্ত ব্যক্তির নিকট থেকে পরবর্তী সময়ে প্রাপ্ত কোনো অভিযোগ কর্তৃপক্ষ বিনা পদক্ষেপে খারিজ করতে পারবেন।</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_17"> আপিল কর্তৃপক্ষ কে?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_17">
                    <div class="panel-body">
                        <div class="font-md"> অভিযোগ প্রতিকার-সংক্রান্ত আপিল নিষ্পত্তির লক্ষ্যে সকল সরকারি দপ্তরে একজন আপিল কর্মকর্তা দায়িত্ব পালন করবেন: </div>
                        <ul class="font-md">
                            <li> মন্ত্রণালয়/বিভাগ ব্যতীত সকল দপ্তরের ক্ষেত্রে সংশ্লিষ্ট দপ্তরের পরবর্তী ঊর্ধ্বতন দপ্তরের অনিক আপিল কর্মকর্তা হিসেবে দায়িত্ব পালন করবেন;</li>
                            <li> মন্ত্রণালয়/বিভাগের আপিল কর্মকর্তা হবেন একজন অতিরিক্ত সচিব অথবা জ্যেষ্ঠ যুগ্ম সচিব। আপিল কর্মকর্তার তথ্য জানতে <a class="blueLink" href="http://localhost:8090/groInformation.do" target="_blank">এখানে ক্লিক করুন</a>।</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_18"> আপিল নিষ্পত্তিতে অসন্তুষ্ট হলে কী করনীয়?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_18">
                    <div class="panel-body">
                        <p class="font-md"> যেকোনো দপ্তরের আপিল কর্মকর্তার সিদ্ধান্তে সংক্ষুব্ধ হলে অনলাইনে (<a class="blueLink" href="http://www.grs.gov.bd/">www.grs.gov.bd</a>) অথবা প্রচলিত পদ্ধতিতে (সংযোজনী ‘খ-৪’-এ নির্ধারিত ফরমে) অভিযোগ ব্যবস্থাপনা সেলে আপিল করা যাবে।</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_19"> প্রচলিত পদ্ধতিতে/অফলাইনে অভিযোগ দাখিল করা যাবে কি-না?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_19">
                    <div class="panel-body">
                        <p class="font-md"> যে সকল দপ্তরে ওয়েব-ভিত্তিক অভিযোগ প্রতিকার ব্যবস্থা চালু হয়নি সে সকল দপ্তরে ইলেক্ট্রনিক পদ্ধতিতে (ইমেইল, ই-ফাইল অথবা কল সেন্টারের মাধ্যমে) অথবা প্রচলিত পদ্ধতিতে (সংশ্লিষ্ট দপ্তরে উপস্থিত হয়ে অথবা ডাকযোগে) অভিযোগ দাখিল করা যাবে। তবে যে সকল দপ্তরে ওয়েব-ভিত্তিক অভিযোগ প্রতিকার ব্যবস্থা চালু হবে সে সকল দপ্তরে অনলাইনে অভিযোগ দাখিলকে উৎসাহিত করতে হবে।</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_20"> অভিযোগ নিষ্পত্তিতে প্রতিক্রিয়া কীভাবে জানাবেন?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_20">
                    <div class="panel-body">
                        <p class="font-md"> অনলাইন অভিযোগ প্রতিকার ব্যবস্থার ওয়েবসাইটে (<a class="blueLink" href="http://www.grs.gov.bd/">www.grs.gov.bd</a>) ‘অভিযোগ নিষ্পত্তিতে প্রতিক্রিয়া’ বাটনে ক্লিক করে প্রতিক্রিয়া জানা যাবে। এছাড়া ব্যবহারকারী হিসেবে লগইন করে অভিযোগের তালিকা থেকে অভিযোগ নিষ্পত্তির সর্বশেষ অবস্থা জানতে পারবেন।</p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_faq" data-toggle="collapse" href="http://localhost:8090/#collapse_faq_21"> অভিযোগ ব্যবস্থাপনা সেলে কী ধরনের অভিযোগ/আপিল দাখিল?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_faq_21">
                    <div class="panel-body">
                        <ul class="font-md">
                            <li> কোনো অভিযোগ মন্ত্রণালয়/বিভাগের সচিব অথবা আপিল কর্মকর্তা সংশ্লিষ্ট হলে অনলাইনে (<a class="blueLink" href="http://www.grs.gov.bd/">www.grs.gov.bd</a>) অথবা প্রচলিত পদ্ধতিতে (সংযোজনী ‘খ-৩’-এ নির্ধারিত ফরমে) অভিযোগ ব্যবস্থাপনা সেলে অভিযোগ দাখিল করা যাবে।</li>
                            <li> যেকোনো দপ্তরের আপিল কর্মকর্তার সিদ্ধান্তে সংক্ষুব্ধ হলে অনলাইনে (<a class="blueLink" href="http://www.grs.gov.bd/">www.grs.gov.bd</a>) অথবা প্রচলিত পদ্ধতিতে (সংযোজনী ‘খ-৪’-এ নির্ধারিত ফরমে) অভিযোগ ব্যবস্থাপনা সেলে আপিল করা যাবে।</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

            </div>
            <div class="modal-footer">
                <button class="btn dark btn-outline" data-dismiss="modal" type="button">বন্ধ করুন</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" data-backdrop="static" data-keyboard="false" id="modalUserManual" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button aria-hidden="true" class="close" data-dismiss="modal" type="button"></button>
                <h4 class="modal-title text-center font-lg font-green bold">ব্যবহারকারী নির্দেশিকা</h4>
            </div>
            <div class="modal-body">
                
    <div class="userManual-section ">
        <div class="panel-group" id="accordion_user_manual">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_1"> অভিযোগ প্রতিকার ব্যবস্থা কি?</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_1" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            অভিযোগ প্রতিকার ব্যবস্থা (Grievance Redress System), সংক্ষেপে <b>জিআরএস (GRS)</b>, মূলত বিভিন্ন সরকারি দপ্তরকর্তৃক প্রদানকৃত সেবা নিশ্চিতকরণের একটি প্ল্যাটফর্ম। জিআরএস ব্যবস্থার অন্তর্ভুক্ত প্রতিটি দপ্তরে গণপ্রজাতন্ত্রী বাংলাদেশের একজন নাগরিক যে কোনো সেবার বিরুদ্ধে তার অসন্তোষ বা ক্ষোভ জানিয়ে অভিযোগ দাখিল করতে পারেন। অভিযোগকারী নাগরিককে তার অভিযোগের বিষয়ে যেকোনো ধরণের অগ্রগতি বা সিদ্ধান্ত সম্পর্কে অবহিত করা এবং কোনো বিষয়য়ে তাঁর মূল্যবান মতামত বা পরামর্শ মূল্যায়ন করাও এই ব্যবস্থার অন্যতম। সরকারি কর্মকর্তা/কর্মচারীরাও একইভাবে যে কোনো সেবার বিপরীতে তাদের অভিযোগ এখানে জানাতে পারবেন।
                        </p>
                        <p class="font-md">
                            অভিযোগ প্রতিকার ব্যবস্থায় একজন নাগরিক নিবন্ধনপূর্বক তাঁর নাগরিক হিসেবে প্রাপ্য সেবা-সংশ্লিষ্ট অভিযোগ করতে পারেন অথবা ব্যক্তিগত তথ্য গোপন রেখে অজ্ঞাতনামা হিসেবে অভিযোগ করতে পারেন। তবে ধর্মীয়, কোনো আদালতে বিচারাধীন, তথ্য অধিকার, সরকারি কর্মকর্তা-কর্মচারীদের বিরুদ্ধে গৃহীত বিভাগীয় মামলা অথবা আইন বা বিধির আওতায় রিভিউ/আপিলের সুযোগ রয়েছে এরূপ বিষয়-সংশ্লিষ্ট অভিযোগ গ্রহণযোগ্য হবে না।
                        </p>
                    </div>

                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_2"> নিবন্ধন</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_2" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            অভিযোগ প্রতিকার সংক্রান্ত যেকোনো সেবা প্রাপ্তির জন্য আপনাকে অবশ্যই জিআরএস এ নিবন্ধিত হতে হবে। নিবন্ধনের জন্য অভিযোগকারী লগইন পেইজের <b>“নিবন্ধন”</b> চিহ্নিত লিংকে ক্লিক করলে অভিযোগকারী তথ্যের ফরম আসবে। এখানে বাধ্যতামূলকভাবে মোবাইল নম্বর, পূর্ণনাম, জাতীয় পরিচয়পত্র নম্বর / জন্মনিবন্ধন সনদ নম্বর / পাসপোর্ট নম্বর, স্থায়ী ঠিকানা ও বর্তমান ঠিকানার তথ্যসহ অন্যান্য কিছু ঐচ্ছিক তথ্য প্রদান করে ফরমের নিচের নিবন্ধন বাটনে ক্লিক করতে হবে। সফলভাবে নিবন্ধন সম্পন্ন হলে আপনাকে এসএমএস ও ইমেইলের মাধ্যমে তা নিশ্চিত করা হবে এবং আপনার প্রদত্ত মোবাইল নম্বরে একটি পিন নম্বর এসএমএস এর মাধ্যমে প্রেরণ করা হবে। ঊক্ত পিন ব্যবহার করে আপনি অভিযোগকারী হিসেবে লগইন করতে পারবেন।
                        </p>
                        <p class="font-md">
                            উল্লেখ্য যে, নিবন্ধনের জন্য যে মোবাইল নম্বরটি প্রদান করা হবে, সেটি যদি ইতোপূর্বে জিআরএস-এ নিবন্ধনের জন্য ব্যবহৃত হয়ে থাকে, তবে আপনার নিকট অন্য কোনো তথ্য চাওয়ার পরিবর্তে, শুধুমাত্র লগইনের জন্য পিন নম্বর চাওয়া হবে। কোনো মোবাইল নম্বর দিয়ে নিবন্ধন করা হলে পরবর্তিতে ঐ নম্বর ব্যবহার করে আর কোনো নিবন্ধন করা যাবেনা। অর্থাৎ একই নম্বর একাধিক নিবন্ধের জন্য ব্যবহার করা যাবেনা।
                        </p>
                    </div>

                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_3"> লগইন করা</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_3" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            জিআরএস হোমপেইজের উপরে ডানপাশে <b>“অভিযোগকারী লগইন”</b> বাটনে ক্লিক করলে লগইন পেইজ আসবে। আপনি যদি ইতোপূর্বে নিবন্ধন করে থাকেন, তাহলে আপনার ইউজারনেম এবং পিন নম্বর সঠিকভাবে প্রদান করে লগইন বাটনে ক্লিক করার পর সকল তথ্য প্রদান সঠিক হয়ে থাকলে আপনি আপনার অভিযোগ তালিকার পেইজটি দেখতে পাবেন। অন্যথায় স্ক্রিনে সতর্কতামূলক মেসেজ দেখা যাবে।
                        </p>
                        <p class="font-md">
                            আর যদি পূর্বে থেকেই লগইন করা থাকে তাহলে হোমপেইজের লগইন বাটনগুলোর পরিবর্তে <b>“আমার প্যানেল”</b> ও <b>“লগ আউট”</b> নামের বাটন দেখা যাবে। সেক্ষেত্রে প্রথমোক্ত বাটনে ক্লিক করলেই আপনি আপনার অভিযোগ তালিকার পেইজটি দেখতে পাবেন।
                        </p>
                    </div>

                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_4"> পিনকোড ভুলে গেলে করণীয় (পিন পরিবর্তন)</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_4" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            আপনি যদি পিন নম্বরটি ভুলে যান বা কোনো কারনে পিন নম্বরটি হারিয়ে যায় অথবা পিন পরিবর্তন করতে চান, তবে অভিযোগকারী লগইন ফরমের নিচে <b>“পিনকোড পুনরুদ্ধার”</b> নামের অপশনটিতে ক্লিক করতে হবে। তাহলে আপনার নিকট মোবাইল নম্বর চাওয়া হবে এবং সঠিকভাবে মোবাইল নম্বরটি প্রদান করলে উক্ত নম্বরে নতুন একটি পিনকোড প্রেরণ করা হবে। নতুন পিনকোড ব্যবহার করে আপনি সহজেই লগইন করতে পারবেন।
                        </p>
                    </div>

                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_5"> লগআউট</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_5" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            লগইন করার পর উপরের মেনুবারের ডানদিকে যেখানে ইউজারনেম প্রদর্শিত হয়, সেখানে ক্লিক করলে <b>“লগ আউট”</b> নামের একটি অপশন দেখা যাবে। উক্ত অপশনে ক্লিক করলে আপনি আপনার বর্তমান লগইন অবস্থা থেকে বের হয়ে যাবেন। লগইন থাকা অবস্থায় হোমপেইজে আসলে সেখানেও একটি <b>“লগ আউট”</b> বাটন দেখা যাবে। ঐ বাটনে ক্লিক করেও একইভাবে লগ আউট করা যাবে। অভিযোগকারী হিসেবে সঠিকভাবে লগ আউট করার পর আপনাকে জিআরএস হোমপেইজে নিয়ে যাওয়া হবে।
                        </p>
                    </div>

                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_6"> নাগরিক অভিযোগ দাখিল</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_6" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            নাগরিক অভিযোগ দাখিলের জন্য অভিযোগ প্রতিকার ব্যবস্থায় লগইন করার পর প্রথমেই যে অভিযোগ তালিকা দেখা যায় তার উপরের ডানদিকে <b>“নতুন অভিযোগ”</b> বাটনে ক্লিক করলে একটি ফরম আসবে। অথবা অভিযোগ প্রতিকার ব্যবস্থার হোমপেইজের <b>“অভিযোগ দাখিল”</b> বাটনে ক্লিক করে <b>“নাগরিক অভিযোগ”</b> অপশনটি নির্বাচন করেও অভিযোগ দায়ের করা যাবে। এক্ষেত্রে যদি ইতোমধ্যে লগইন করা হয়ে থাকে তবে অভিযোগ দাখিলের জন্য একটি ফরম আসবে। আর যদি লগইন করা না থাকে তবে অভিযোগকারী নিবন্ধন ফরম আসবে। এখান থেকে নিবন্ধন ও লগইন করার পর নাগরিক অভিযোগ দাখিলের ফরমটি পাওয়া যাবে।
                        </p>
                        <p class="font-md">
                            অভিযোগ দাখিল ফরমে অভিযোগ সংশ্লিষ্ট দপ্তর ও সেবা নির্বাচন, সেবা আবেদনের তারিখ, অভিযোগকারীর নাম, অভিযোগের বিষয়, বর্ণনা ও প্রয়োজনীয় সংযুক্তি প্রদান করে অভিযোগ প্রেরণ বাটনে ক্লিক করলে অভিযোগটি ঐ দপ্তরের অভিযোগ নিষ্পত্তি কর্মকর্তার নিকট প্রেরিত হয়ে যাবে। তবে ফরম পূরন করার পর প্রেরণ করার পূর্বে অভিযোগের প্রিভিউ দেখা ও প্রিন্ট নেওয়া যাবে। অভিযোগটি সফলভাবে প্রেরিত হলে উক্ত অভিযোগের একটি রিসিপ্ট দেখা যাবে, যেখানে অভিযোগের ট্র্যাকিং নম্বর ও অভিযোগ নিষ্পত্তি কর্মকর্তার তথ্য দেখা যাবে ও প্রিন্ট করা যাবে। অভিযোগের অগ্রগতি ও সর্বশেষ অবস্থা জানার জন্য এই ট্র্যাকিং নম্বরটি অবশ্যই সংরক্ষণ করুন। উল্লেখ্য যে, অভিযোগ দাখিল হওয়ার পর নিষ্পন্ন হওয়ার সময়সীমা <b>৬০ দিন</b> পর্যন্ত।
                        </p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_7"> অজ্ঞাতনামা হিসেবে অভিযোগ দাখিল</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_7" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            আপনি যদি কোনো কারনে অজ্ঞাতনামা হিসেবে অভিযোগ দাখিল করতে চান তবে অভিযোগ প্রতিকার ব্যবস্থার হোমপেইজের <b>“অভিযোগ দাখিল”</b> বাটনে ক্লিক করে <b>“অজ্ঞাতনামা হিসেবে অভিযোগ”</b> অপশনটি নির্বাচন করতে হবে। তাহলে নাগরিক অভিযোগ দাখিল ফরমের ন্যায় অজ্ঞাতনামা হিসেবে অভিযোগ দাখিলের একটি ফরম দেখা যাবে। এই ফরমে অভিযোগ সংশ্লিষ্ট দপ্তর ও সেবা নির্বাচন করে সেবা আবেদনের তারিখ, অভিযোগকারীর নাম, অভিযোগের বিষয়, বর্ণনা ও প্রয়োজনীয় সংযুক্তি প্রদান করার পর অভিযোগ প্রেরণ বাটনে ক্লিক করলে অভিযোগটি নিশ্চিত করার জন্য প্রিভিউ দেখানো হবে। এখান থেকে অভিযোগের প্রিভিউ প্রিন্ট করা যাবে এবং অভিযোগটি প্রেরণ করা যাবে।
                        </p>
                        <p class="font-md">
                            অজ্ঞাতনামা হিসেবে অভিযোগ প্রেরণের ক্ষেত্রে অভিযোগ প্রেরণ বাটনে ক্লিক করলে একটি সতর্কীকরণ বার্তা দেখতে পাবেন যেখানে বলা থাকবে যে, <b>“অজ্ঞাতনামা হিসেবে দাখিল করলে অভিযোগ এর অবস্থা অবগত হওয়া সম্ভব হবে না এবং প্রতিকার করা হতেও পারে, নাও হতে পারে। তবে কাউকে হয়রানি করার উদ্দেশ্যে অজ্ঞাতনামা অভিযোগ দাখিল করলে প্রয়োজনে বিশেষ প্রক্রিয়ায় তথ্য বের করে আইনানুগ ব্যবস্থা নেয়া হতে পারে”</b>। অতঃপর নিশ্চিত করা হলে অভিযোগটি সংশ্লিষ্ট দপ্তরের অভিযোগ নিষ্পত্তি কর্মকর্তার নিকট প্রেরিত হয়ে যাবে। উল্লেখ্য যে, অজ্ঞাতনামা হিসেবে অভিযোগ দাখিল করলে আপনাকে অভিযোগ নিষ্পত্তি কর্মকর্তার কোনো তথ্য জানানো হবে না এবং কোনো ট্র্যাকিং নম্বরও প্রদান করা হবেনা। ফলে পরবর্তিতে উক্ত অভিযোগের সর্বশেষ অগ্রগতি ও অবস্থা সম্পর্কিত কোনো তথ্য জানা সম্ভব হবেনা।
                        </p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_8"> অভিযোগ তালিকা</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_8" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            অভিযোগ প্রতিকার ব্যবস্থায় একজন অভিযোগকারী হিসেবে সফলভাবে লগইন করার পর আপনাকে অভিযোগের তালিকা প্রদর্শন করা হবে। অভিযোগ তালিকায় আপনি আপনার দায়েরকৃত অভিযোগসমূহ দেখতে পাবেন। তালিকায় সর্বশেষ দায়েরকৃত নির্দিষ্ট সংখ্যক (সাধারণত ১০টি) অভিযোগ একসাথে দেখা যাবে।
                        </p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_9"> অভিযোগের বিস্তারিত</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_9" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            কোনো অভিযোগের বিস্তারিত দেখতে চাইলে তালিকা থেকে উক্ত অভিযোগ সম্পর্কিত তথ্যের উপর ক্লিক করতে হবে। তাহলে <b>বিবরণ </b>অংশে অভিযোগের বিস্তারিত তথ্য দেখা যাবে। এখানে অভিযোগকারীর তথ্য, সেবার বিবরণ, অভিযোগের বর্ণনা, ট্র্যাকিং নম্বর, সর্বশেষ অবস্থা, সংযুক্ত ফাইলসমূহ সহ অন্যান্য তথ্যাদি জানা যাবে এবং প্রয়োজনে অভিযোগের বিস্তারিত প্রিন্ট করা যাবে।
                        </p>
                        <p class="font-md">
                            অভিযোগ দায়ের করার পর হতে বর্তমান সময় পর্যন্ত যেসকল পদক্ষেপ নেওয়া হয়েছে তা <b>গৃহীত কার্যক্রম</b> অংশে ক্লিক করলে দেখা যাবে।
                        </p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_10"> আপিল</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_10" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            আপনার দাখিলকৃত অভিযোগটি নিষ্পত্তি হয়ে গেলে আপনাকে এসএমএস ও ইমেইল এর মাধ্যমে জানানো হবে। অভিযোগ নিষ্পত্তি কর্মকর্তা কর্তৃক গৃহীত ব্যবস্থার উপর আপনি যদি সন্তষ্ট না হয়ে থাকেন তবে আপনি আপিল করতে পারেন। আপনার অভিযোগটি যদি কোনো কারণে নথিজাত করা হয়ে থাকলেও আপনি আপিল করতে পারবেন। এক্ষেত্রে অভিযোগের বিস্তারিত বিবরণের উপরে ডানপাশে <b>“আপিল করুন”</b> বাটন দেখা যাবে। উক্ত বাটনে ক্লিক করলে আপনার অভিযোগটি স্বয়ংক্রিয়ভাবে আপিল হয়ে সংশ্লিষ্ট আপিল কর্মকর্তার নিকট প্রেরিত হবে এবং আপনাকে আপিল কর্মকর্তার তথ্য দেখানো হবে। আপিল কার্যক্রম সম্পন্ন হওয়ার সময়সীমা <b>৩০ দিন</b>।
                        </p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_11"> সেল বরাবর অভিযোগ ও আপিল</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_11" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            সেল বরাবর অভিযোগ করার ক্ষেত্রে অভিযোগ দাখিলের সময় দপ্তর বাছাই অংশে <b>“সেল”</b> নির্বাচন করতে হবে। তাহলে আপনার অভিযোগটি সরাসরি সেলে দাখিল হয়ে যাবে। আবার আপিল করার পর আপিল কর্মকর্তার সিদ্ধান্তে সংক্ষুব্ধ হলে অথবা সন্তুষ্ট না হয়ে থাকলে আপনি পুনরায় আপিল করতে পারেন। পুনরায় আপিলের ক্ষেত্রে আপনার আপিলটি সরাসরি সেলের নিকট প্রেরিত হয়ে যাবে। উল্লেখ্য যে, সেল কর্তৃক অভিযোগ নিষ্পত্তির সময়সীমা <b>৯০ দিন </b>এবং আপিল নিষ্পত্তির সময়সীমা <b>৩০ দিন</b>।
                        </p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_12"> অভিযোগ ট্র্যাকিং</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_12" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            অভিযোগ প্রতিকার ব্যবস্থায় দাখিলকৃত কোনো অভিযোগের অগ্রগতি বা সর্বশেষ অবস্থা জানার জন্য সহজ উপায় হলো অভিযোগ ট্র্যাকিং। একজন নিবন্ধিত অভিযোগকারী হিসেবে অভিযোগ দাখিল করে পরবর্তীতে সিস্টেমে লগইন না করে এবং অভিযোগের বিস্তারিত না দেখেও সহজেই আপনি আপনার অভিযোগটি ট্র্যাক করতে পারেন। এক্ষেত্রে হোমপেইজে <b>“অভিযোগের সর্বশেষ অবস্থা”</b> অপশনটতে ক্লিক করলে অভিযোগ ট্র্যাকিংয়ের একটি ফরম দেখা যাবে। উক্ত ফরমে আপনার মোবাইল নম্বর ও যে অভিযোগটির বিস্তারিত জানতে চাচ্ছেন সেটির ট্র্যাকিং নম্বর প্রদান করে <b>“অবস্থা জানুন”</b> বাটনে ক্লিক করলে অভিযোগটির সর্বশেষ অবস্থার পাশাপাশি দাখিলের সময় ও নিষ্পত্তির সম্ভাব্য তারিখ সম্পর্কে জানতে পারবেন। তবে অজ্ঞাতনামা হিসেবে অভিযোগ দাখিল করলে আপনি আপনার অভিযোগটি ট্র্যাক করতে পারবেন না।
                        </p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_13"> রেটিং প্রদান</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_13" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            কোনো অভিযোগ বা আপিল নিষ্পত্তি হয়ে গেলে আপনি উক্ত অভিযোগ বা আপিল নিষ্পত্তি প্রক্রিয়া ও গৃহীত ব্যবস্থার উপর আপনার সন্তুষ্টি প্রকাশ করার জন্য <b>“প্রতিক্রিয়া প্রদান”</b> অংশে ক্লিক করে রেটিং প্রদান করতে পারেন। রেটিং এর মান ৫ এর মধ্যে ০.৫ ব্যবধানে যেকোনো মান হতে পারে। অভিযোগ নিষ্পত্তি ও আপিল নিষ্পত্তি উভয়ক্ষেত্রেই রেটিং প্রদান করা যায়। রেটিং এর পাশাপাশি আপনি চাইলে আপনার প্রদত্ত রেটিং সম্পর্কে মতামতও প্রদান করতে পারেন।
                        </p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_14"> সেবার মানোন্নয়নে পরামর্শ প্রদান</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_14" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            অভিযোগ প্রতিকার ব্যবস্থায় আপনি যেকোনো সেবার গুণগত মান বৃদ্ধি, সেবা সহজিকরণ, সংশ্লিষ্ট আইন/বিধি সংস্কার ইত্যাদি বিষয়ে আপনার সুচিন্তিত মতামত বা মূল্যবান পরামর্শ প্রদান করতে পারেন। এজন্য হোমপেইজের <b>“সেবার মানোন্নয়নে পরামর্শ”</b> লিংকে ক্লিক করলে একটি ফরম আসবে। সেখানে আনুষঙ্গিক তথ্য সহকারে আপনার মতামত বা পরামর্শ প্রদান করতে পারেন।
                        </p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_15"> সেবা প্রদান প্রতিশ্রুতি ব্যবহার</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_15" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            জিআরএস ব্যবস্থার আওতাধীন যেকোনো দপ্তরের সেবা প্রদান প্রতিশ্রুতি তথা সিটিজেন চার্টার দেখার জন্য হোমপেইজের <b>“সেবা প্রদান প্রতিশ্রুতি”</b> লিংকে ক্লিক করতে হবে অথবা উপরের বামপাশে মেনুবার হতে <b>“সেবা প্রদান প্রতিশ্রুতি”</b> নির্বাচন করতে হবে। তারপর সিটিজেন চার্টার দেখার জন্য যে পেইজ আসবে সেখানে দপ্তর বাছাই করলে ঐ দপ্তরের সেবা সমূহের তালিকা, সেবাগ্রহীতার নিকট প্রত্যাশা, অভিযোগ নিষ্পত্তি কর্মকর্তা ও আপিল কর্মকর্তার তথ্য সংবলিত সিটিজেন চার্টার দেখা যাবে। সিটিজেন চার্টারের প্রতিটি সেবার পাশে ঐ সেবার বিরুদ্ধে অভিযোগ করার অপশনও থাকবে, যেখান থেকে উক্ত সেবার বিরুদ্ধে সহজেই অভিযোগ দাখিল করা যাবে।
                        </p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_16">কালোতালিকাভুক্তি</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_16" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            কোনো অভিযোগকারী অভ্যাসগতভাবে অসত্য এবং কাউকে হয়রানি করার জন্য কিংবা অন্য কোনো অসৎ উদ্দেশ্যে অভিযোগ দাখিল করেন মর্মে প্রমাণিত হলে অনিক-এর সুপারিশের ভিত্তিতে সংশ্লিষ্ট আপিল কর্মকর্তা উক্ত অভিযোগকারীকে কালো তালিকাভুক্ত করতে পারবেন। এরূপ কালো তালিকাভুক্ত ব্যক্তির নিকট থেকে প্রাপ্ত কোনো অভিযোগ কর্তৃপক্ষ বিনা পদক্ষেপে খারিজ করতে পারবেন এবং পরবর্তী সময়ে ঐ ব্যক্তিকে কালোতালিকা হতে অব্যহতি না দেওয়া পর্যন্ত আর কোনো অভিযোগ দাখিল করতে পারবেন না।
                        </p>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">
                        <i class="fa fa-question-circle font-red"></i>
                        <a aria-expanded="false" class="accordion-toggle collapsed" data-parent="#accordion_user_manual" data-toggle="collapse" href="http://localhost:8090/#collapse_17">গুরুত্বপূর্ণ তথ্য ও যোগাযোগ</a>
                    </div>
                </div>
                <div aria-expanded="false" class="panel-collapse collapse" id="collapse_17" style="height: 0px;">
                    <div class="panel-body">
                        <p class="font-md">
                            অভিযোগ প্রতিকার ব্যবস্থা সংক্রান্ত যেকোনো প্রশ্নোত্তরের জন্য হোমপেইজের <b>“সচরাচর জিজ্ঞাস্য”</b> অংশ দেখতে পারেন। অভিযোগ প্রতিকারের কার্যপদ্ধতির প্রবাহচিত্র দেখতে পারেন <b>“পদ্ধতি-চিত্র”</b> অংশে। <b>“জিআরএস নির্দেশিকা”</b> অংশে অভিযোগ প্রতিকার ব্যবস্থা সংক্রান্ত নির্দেশিকা পড়তে ও সংরক্ষণ করতে পারেন। অফলাইনে অভিযোগ/আপিল দাখিল করতে প্রয়োজনীয় ফরমস ডাউনলোড করতে পারেন <b>“ডাউনলোডস”</b> অংশ থেকে।
                        </p>
                        <p class="font-md">
                            যেকোনো সহযোগিতা বা প্রয়োজনে <b>“যোগাযোগ”</b> অংশে উল্লেখিত নম্বরের মাধ্যমে হেল্পডেস্কে কল করতে পারেন, অথবা ইমেইল করতে পারেন প্রদর্শিত ইমেইল ঠিকানায়। এছাড়াও ফেসবুক, টুইটার, ইউটিউব ও গুগলপ্লাস এর মতো সামাজিক যোগাযোগ ব্যবস্থার মাধ্যমে যুক্ত থাকতে হোমপেইজের নিচে প্রদর্শিত লিংকগুলোতে ভিজিট করতে পারেন।
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>

            </div>
            <div class="modal-footer">
                <button class="btn dark btn-outline" data-dismiss="modal" type="button">বন্ধ করুন</button>
            </div>
        </div>
    </div>
</div>


    <!--[if lt IE 9]>
    <script src="/assets/global/plugins/respond.min.js"></script>
    <script src="/assets/global/plugins/excanvas.min.js"></script>
    <script src="/assets/global/plugins/ie8.fix.min.js"></script>
    <![endif]-->

    <script src="<%=context%>/templates/GRSStartPage_files/jquery.min.js.download" type="text/javascript"></script>
    <script src="<%=context%>/templates/GRSStartPage_files/jquery.cookie.js.download" type="text/javascript"></script>
    <script src="<%=context%>/templates/GRSStartPage_files/bootstrap.min.js.download" type="text/javascript"></script>
    <script src="<%=context%>/templates/GRSStartPage_files/toastr.min.js.download" type="text/javascript"></script>
    <script src="<%=context%>/templates/GRSStartPage_files/js.cookie.min.js.download" type="text/javascript"></script>
    <script src="<%=context%>/templates/GRSStartPage_files/jquery.slimscroll.min.js.download" type="text/javascript"></script>
    <script src="<%=context%>/templates/GRSStartPage_files/jquery.blockui.min.js.download" type="text/javascript"></script>
    <script src="<%=context%>/templates/GRSStartPage_files/bootstrap-switch.min.js.download" type="text/javascript"></script>
    <script src="<%=context%>/templates/GRSStartPage_files/jquery-ui.min.js.download" type="text/javascript"></script>
    <script src="<%=context%>/templates/GRSStartPage_files/app.min.js.download" type="text/javascript"></script>
    <script src="<%=context%>/templates/GRSStartPage_files/knockout-3.4.2.js.download" type="text/javascript"></script>
    <script src="<%=context%>/templates/GRSStartPage_files/star-rating.min.js.download" type="text/javascript"></script>
    <script src="<%=context%>/templates/GRSStartPage_files/summernote.min.js.download" type="text/javascript"></script>
    <script>
        String.prototype.toBanglaNumber = function () {
            var engNum = this;
            var bngNum = '';
            var bngDigits = ['০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯'];
            engNum.split('').forEach(function (digit) {
                var index = parseInt(digit);
                bngNum += isNaN(index) ? digit : bngDigits[index];
            });
            return bngNum;
        };

        String.prototype.toEnglishNumber = function () {
            var bngNum = this;
            var engNum = '';
            var bngDigits = ['০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯'];
            bngNum.split('').forEach(function (digit) {
                var index = bngDigits.indexOf(digit);
                engNum += index >= 0 ? index : digit;
            });
            return engNum;
        };

        function blockUI() {
            var isEnglish = (languageCode == "en");
            var msg = isEnglish ? "Loading... Just a moment please" : "লোড হচ্ছে... দয়া করে অপেক্ষা করুন" ;
            $.blockUI({
                message: '<h1>' + msg + '</h1>'
            });
        }

        function unblockUI() {
            $.unblockUI();
        }
    </script>
    <script charset="UTF-8" src="<%=context%>/templates/GRSStartPage_files/moment-with-locales.js.download" type="text/javascript"></script>
    <script charset="UTF-8" src="<%=context%>/templates/GRSStartPage_files/bootstrap-datepicker-1.6.4.js.download" type="text/javascript"></script>
    <!-- Bengali for datepicker -->
    <!--<script src="/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker-bn.js" type="text/javascript" charset="UTF-8"></script>-->
    <script src="<%=context%>/templates/GRSStartPage_files/select2.full.min.js.download" type="text/javascript"></script>
    <!-- Bengali for select2 -->
    <script src="<%=context%>/templates/GRSStartPage_files/bn.js.download" type="text/javascript"></script>
    <script>
        var languageCode = $.cookie("lang");
        var jsLangCodeForLibs = 'bn';
        if (languageCode == "en") {
            jsLangCodeForLibs = 'en';
        }

        if (jsLangCodeForLibs == "bn") {
            $.fn.datepicker.dates['bn'] = {
                months : 'জানুয়ারী_ফেব্রুয়ারি_মার্চ_এপ্রিল_মে_জুন_জুলাই_আগস্ট_সেপ্টেম্বর_অক্টোবর_নভেম্বর_ডিসেম্বর'.split('_'),
                monthsShort : 'জানু_ফেব_মার্চ_এপ্র_মে_জুন_জুল_আগ_সেপ্ট_অক্টো_নভে_ডিসে'.split('_'),
                days : 'রবিবার_সোমবার_মঙ্গলবার_বুধবার_বৃহস্পতিবার_শুক্রবার_শনিবার'.split('_'),
                daysShort : 'রবি_সোম_মঙ্গল_বুধ_বৃহস্পতি_শুক্র_শনি'.split('_'),
                daysMin : 'রবি_সোম_মঙ্গ_বুধ_বৃহঃ_শুক্র_শনি'.split('_'),
                longDateFormat : {
                    LT : 'A h:mm সময়',
                    LTS : 'A h:mm:ss সময়',
                    L : 'DD/MM/YYYY',
                    LL : 'D MMMM YYYY',
                    LLL : 'D MMMM YYYY, A h:mm সময়',
                    LLLL : 'dddd, D MMMM YYYY, A h:mm সময়'
                },
                calendar : {
                    sameDay : '[আজ] LT',
                    nextDay : '[আগামীকাল] LT',
                    nextWeek : 'dddd, LT',
                    lastDay : '[গতকাল] LT',
                    lastWeek : '[গত] dddd, LT',
                    sameElse : 'L'
                },
                relativeTime : {
                    future : '%s পরে',
                    past : '%s আগে',
                    s : 'কয়েক সেকেন্ড',
                    ss : '%d সেকেন্ড',
                    m : 'এক মিনিট',
                    mm : '%d মিনিট',
                    h : 'এক ঘন্টা',
                    hh : '%d ঘন্টা',
                    d : 'এক দিন',
                    dd : '%d দিন',
                    M : 'এক মাস',
                    MM : '%d মাস',
                    y : 'এক বছর',
                    yy : '%d বছর'
                },
                meridiemParse: /রাত|সকাল|দুপুর|বিকাল|রাত/,
                meridiemHour : function (hour, meridiem) {
                    if (hour === 12) {
                        hour = 0;
                    }
                    if ((meridiem === 'রাত' && hour >= 4) ||
                            (meridiem === 'দুপুর' && hour < 5) ||
                            meridiem === 'বিকাল') {
                        return hour + 12;
                    } else {
                        return hour;
                    }
                },
                meridiem : function (hour, minute, isLower) {
                    if (hour < 4) {
                        return 'রাত';
                    } else if (hour < 10) {
                        return 'সকাল';
                    } else if (hour < 17) {
                        return 'দুপুর';
                    } else if (hour < 20) {
                        return 'বিকাল';
                    } else {
                        return 'রাত';
                    }
                },
                week : {
                    dow : 0, // Sunday is the first day of the week.
                    doy : 6  // The week that contains Jan 1st is the first week of the year.
                }
            };
            moment.locale("bn");
        }

        function setBangla() {
            $.cookie("lang", "fr");
            location.reload();
        }
        function setEnglish() {
            $.cookie("lang", "en");
            location.reload();
        }
        function enableMenu(){
            var url = document.location.href;
            if (url.includes("services.do")){
                $("#serviceMenu").addClass("active");
                $("#homeMenu, #suggestionMenu, #statusMenu").removeClass("active");
            } else if (url.includes("suggestion.do")){
                $("#suggestionMenu").addClass("active");
                $("#homeMenu, #serviceMenu, #statusMenu").removeClass("active");
            } else {
                $("#homeMenu").addClass("active");
                $("#serviceMenu, #suggestionMenu, #statusMenu").removeClass("active");
            }
        }

        function ajaxPut(url, accept, send, postData, onSuccess) {
            $.ajax({
                type: "PUT",
                url: url,
                dataType: accept,
                contentType: send,
                data: postData,
                success: onSuccess
            });
        }

        $(document).ready(function() {
            enableMenu();
            if (languageCode==null || languageCode=="fr"){
                //console.log("case 1 ");
                //$("#selectedLanguageLogoLink").attr("src","/assets/global/img/flags/bd.png");
                $("#selectedLanguageName").text("English");
                //$("#alternateLanguageLogoLink").attr("src","/assets/global/img/flags/bd.png");
                //$("#alternateLanguageName").text("বাংলা");
                $("a#selectedLanguageLink").attr('href', 'javascript:setEnglish()');

            }
            if (languageCode=="en"){
                //console.log("case 2 ");
                //$("#selectedLanguageLogoLink").attr("src","/assets/global/img/flags/bd.png");
                $("#selectedLanguageName").text("বাংলা");
                //$("#alternateLanguageLogoLink").attr("src","/assets/global/img/flags/bd.png");
                //$("#alternateLanguageName").text("English");
                $("a#selectedLanguageLink").attr('href', 'javascript:setBangla()');

                // Change font size if language is English
                //$(".page-banner").css("font-size", "19px");
            }

            /* Overriding conditional clicking on menu toggler for responsive only to all viewports */
            /* $(".page-header .menu-toggler").on("click", function(event) { // Adding clicking on all viewports
                var menu = $(".page-header .page-header-menu");
                if (menu.is(":visible")) {
                    menu.slideUp(300);
                } else {
                    menu.slideDown(300);
                }

                if ($('body').hasClass('page-header-top-fixed')) {
                    App.scrollTop();
                }
            }); */
            $(".page-header .menu-toggler").unbind("click"); // Removing already bound event handler for responsive only
            $(".page-header .menu-toggler").on("click", function(e){
                $(".page-header-menu").css("display", "block");
                $(".menu-dropdown.classic-menu-dropdown > .dropdown-menu").css("display", "block");
            });

            $(".menu-dropdown.classic-menu-dropdown > .dropdown-menu").on("mouseleave", function(e){
                if ($(".menu-dropdown.classic-menu-dropdown > .dropdown-menu").is(":visible")) {
                    $(".page-header-menu").css("display", "none");
                    $(".menu-dropdown.classic-menu-dropdown > .dropdown-menu").css("display", "none");
                }
            });

            $('[data-toggle="tooltip"]').tooltip();
        });

        function showComplaintFeedbackModal() {
            $("input[name='mobileNumber']").val("");
            $("input[name='trackingNumber']").val("")
            $("#txtaFeedback").summernote({
                height: 100,
                width: 500,
                toolbar: [
                    ['style', ['bold', 'italic', 'underline', 'clear', 'fontname']],
                    ['font', ['strikethrough', 'superscript', 'subscript']],
                    ['fontsize', ['fontsize']],
                    ['color', ['color']],
                    ['para', ['ul', 'ol', 'paragraph']],
                    ['Insert', ['table', 'hr']],
                    ['Misc', ['undo', 'redo']]
                ]
            });
            $("#complaintFeedbackModal").modal('show');
        }

        function hideComplaintFeedbackModal() {
            $('#grievanceStatus').empty();
            $("#complaintFeedbackModal").modal('hide');
        }

        function ratingViewModel() {
            this.isRated = ko.observable(0);
        }

        function initRating(id) {
            ko.applyBindings(new ratingViewModel());
            $('#feedbackButton').on('click', function() {
                $("#ajaxProcessingModal").modal('show');
                var url = "/api/grievance/feedback";
                var putObj = JSON.stringify({
                    grievanceId: id,
                    rating: $('#rating').val(),
                    userComments: $('#txtaFeedback').summernote('code')
                });
                ajaxPut(url, "json", "application/json", putObj, function (data) {
                    $("#ajaxProcessingModal").modal('hide');
                    if(data.success) {
                        swal({
                                title: "আপনার প্রতিক্রিয়া!",
                                text: data.message,
                                type: "success"
                            },
                            function() {
                                window.location.reload();
                            }
                        );
                    }
                    else {
                        swal("স্থগিত", data.message, "error");
                    }
                });

            });
        }

        function showComplaintStatusModal(){
            $("input[name='mobileNumber']").val("");
            $("input[name='trackingNumber']").val("")
            $("#complaintStatusModal").modal('show');
        }

        function hideComplaintStatusModal(){
            $('#grievanceStatus').empty();
            $("#complaintStatusModal").modal('hide');
        }

        function getStatus(id, mobileNumberFieldName, trackingNumberFieldName){
            var mobileNumber = $("input[name=" + mobileNumberFieldName + "]").val();
            var trackingNumber = $("input[name=" + trackingNumberFieldName + "]").val();
            var isEnglish = (languageCode == "en");
            if(mobileNumber.startsWith("+88") || mobileNumber.startsWith("+৮৮")) {
                mobileNumber = mobileNumber.substr(3);
            }
            if(mobileNumber.trim().length == 0) {
                $('#' + id).html("<p class='error font-red bold'>" + (isEnglish ? "Please provide mobile number" : "অনুগ্রহ করে মোবাইল নম্বর প্রদান করুন") + "</p>");
            } else if(trackingNumber.trim().length == 0) {
                $('#' + id).html("<p class='error font-red bold'>" + (isEnglish ? "Please provide tracking number" : "অনুগ্রহ করে ট্র্যাকিং নম্বর প্রদান করুন") + "</p>");
            } else if(mobileNumber.length < 11 || !(/^\d+$/.test(mobileNumber.toEnglishNumber())) || !(mobileNumber.startsWith("01") || mobileNumber.startsWith("০১"))) {
                $('#' + id).html("<p class='error font-red bold'>" + (isEnglish ? "Please provide correct mobile number" : "অনুগ্রহ করে সঠিক মোবাইল নম্বর প্রদান করুন") + "</p>");
            } else {
                $.ajax({
                    type: 'GET',
                    url: '/api/grievance/status',
                    data: { phoneNumber: mobileNumber, trackingNumber: trackingNumber },
                    /*beforeSend:function() {
                        $("#grievanceStatus").removeClass('hidden');
                        if (languageCode === "en") {
                            $('#' + id).html('<span>Loading, Please Wait</span>');
                        } else {
                            $('#' + id).html('<span>দয়া করে অপেক্ষা করুন</span>');
                        }
                    },*/
                    success:function(data) {
                        if (id !== 'grievanceFeedback') {
                            populateGrievanceStatus(data, id);
                        } else{
                            populateGrievanceStatus(data, id);
                            if(data.statusEng === 'Closed' || data.statusEng === 'Rejected'){
                                initRating(data.id);
                                $("#feedback-div").show();
                            } else {
                                $("#feedback-div").hide();
                            }
                        }
                    },
                    error:function(){
                        populateErrorToStatus(id);
                    }
                });
            }
        }

        function populateErrorToStatus(id) {
            if (languageCode == "en") {
                $('#' + id).html('<p class="error font-red"><strong>Oops!</strong> Try that again in a few moments.</p>');
            } else {
                $('#' + id).html('<p class="error font-red"><strong>দুঃখিত</strong>, একটু পরে আবার চেষ্টা করুন।</p>');
            }
        }

        function populateGrievanceStatus(data, id){
            if (jQuery.isEmptyObject(data)){
                if (languageCode == "en") {
                    $('#' + id).html('<p class="error font-red"><strong>Sorry</strong>  Could not find the grievance.</p>');
                } else {
                    $('#' + id).html('<p class="error font-red"><strong>দুঃখিত</strong>, অভিযোগটি পাওয়া যায় নি</p>');
                }
            } else {
                var str;
                var item = document.createElement('tr');
                var date = document.createElement('td');
                var service = document.createElement('td');
                var complaintStatus = document.createElement('td');
                var closureDate = document.createElement('td');
                var dateText = document.createElement('span');
                var serviceText = document.createElement('span');
                var complaintStatusText = document.createElement('span');
                var closureDateText = document.createElement('span');
                if (languageCode == "en") {
                    dateText.innerHTML = data.submissionDateEng;
                    serviceText.innerHTML = data.serviceNameEng;
                    complaintStatusText.innerHTML = data.statusEng;
                    closureDateText.innerHTML = data.closeDateEng;

                    str = '<p>to know details, please <a href="/login?a=0" class="blueLink">click here</a></p>';
                } else {
                    dateText.innerHTML = data.submissionDateBng;
                    serviceText.innerHTML = data.serviceNameBng;
                    complaintStatusText.innerHTML = data.statusBng;
                    closureDateText.innerHTML = data.closeDateBng;

                    str = '<p>বিস্তারিত জানতে <a href="/login?a=0" class="blueLink">এখানে ক্লিক করুন </a></p>';
                }
                date.append(dateText);
                service.append(serviceText);
                complaintStatus.append(complaintStatusText);
                closureDate.append(closureDateText);
                item.append(date);
                item.append(service);
                item.append(complaintStatus);
                item.append(closureDate);
                var selector = '#' + id;
                $( selector + "More").html(str);
                $(selector).find("tr:gt(0)").remove();
                $(selector + " tbody").append(item);
                $(selector).show();
            }
        }

        function previewFile(clickedLink, e) {
            if (e) {
                e.preventDefault();
            }
            var domainURL = "http://" + document.location.host;
            if ($(clickedLink).attr("href").startsWith("http://")) {
                domainURL = "";
            }

            if ($(clickedLink).hasClass("media-embed")) {
                var isEmbed = false;
                if($(clickedLink).attr("data-asset") == "true"){
                    isEmbed = true;
                }
                if(isEmbed){
                    $("#fileViewerModal .modal-body").html('<embed src="' + domainURL + $(clickedLink).attr("href") + '" style="width:100%; height:800px;"></embed>');
                } else {
                    $("#fileViewerModal .modal-body").html('<iframe src="http://docs.google.com/gview?embedded=true&url=' + domainURL + $(clickedLink).attr("href") + '" style="width:100%; height:800px;" frameborder="0"></iframe>');
                }
                $("#fileViewerModal a").attr("href", domainURL + $(clickedLink).attr("href"));
                $("#fileViewerModal").modal("show");
                return false;
            } else if ($(clickedLink).hasClass("media-audio")) {
                $("#fileViewerModal .modal-body").html('<audio style="width:100%;" controls><source src="' + $(clickedLink).attr("href") + '" type="audio/mpeg">Your browser does not support the audio element.</audio>');
                $("#fileViewerModal a").attr("href", domainURL + $(clickedLink).attr("href"));
                $("#fileViewerModal").modal("show");
                return false;
            } else if ($(clickedLink).hasClass("media-video")) {
                $("#fileViewerModal .modal-body").html('<video style="width:100%;height:480px;" controls><source src="' + $(clickedLink).attr("href") + '" type="video/mp4">Your browser does not support the video tag.</video>');
                $("#fileViewerModal a").attr("href", domainURL + $(clickedLink).attr("href"));
                $("#fileViewerModal").modal("show");
                return false;
            } else {
                return true;
            }
        }

        function addPublicGrievanceButtonEventHandler() {
            var authToken = $.cookie("Authorization");
            var uri = authToken != null ? "/addPublicGrievances.do" : "/register.do";
            $(location).attr('href', uri);
        }

        function addStaffGrievanceButtonEventHandler() {
            var authToken = $.cookie("Authorization");
            var uri = authToken != null ? "/addStaffGrievances.do" : "/login.do?a=1";
            $(location).attr('href', uri);
        }

        function seeMore(seeMoreLink, e) {
            if(e) {
                e.preventDefault();
            }
            $(seeMoreLink).siblings('.more-text').show();
            $(seeMoreLink).hide();
        }

        function squeezeMore(squeezeMoreLink, e) {
            if(e) {
                e.preventDefault();
            }
            $(squeezeMoreLink).closest('.more-text').siblings(".show-text").show();
            $(squeezeMoreLink).closest('.more-text').hide();
        }

        function featureComingSoon (){
            if (languageCode == "en") {
                swal({
                    title: "Under construction",
                    text: "Sorry, we are currently building this",
                    type: "info",
                    showCancelButton: false,
                    confirmButtonClass: "btn-info",
                    confirmButtonText: "OK",
                    closeOnConfirm: true
                });
            } else {
                swal({
                    title: "নির্মাণাধীন",
                    text: "দুঃখিত, এই ফীচারটি এখন নির্মাণাধীন",
                    type: "info",
                    showCancelButton: false,
                    confirmButtonClass: "btn-info",
                    confirmButtonText: "বন্ধ করুন",
                    closeOnConfirm: true
                });
            }
        }
    </script>
    <script src="<%=context%>/templates/GRSStartPage_files/layout.js.download" type="text/javascript"></script>
    <script src="<%=context%>/templates/GRSStartPage_files/toastr.min.js.download" type="text/javascript"></script>
    <script src="<%=context%>/templates/GRSStartPage_files/sweetalert.min.js.download" type="text/javascript"></script>
    <script src="<%=context%>/templates/GRSStartPage_files/jquery.easy-autocomplete.min.js.download" type="text/javascript"></script>

<script type="text/javascript">
    $(document).ready(function() {
        if (languageCode == "en") {
            $("#btn_obhijog_dakhil").attr("src", "/assets/layouts/layout3/img/Submit-Grievance.png");
        } else {
            $("#btn_obhijog_dakhil").attr("src", "/assets/layouts/layout3/img/Ovijog-dakhil.png");
        }
    });

    function loadHoverState(obj) {
        if (languageCode == "en") {
            obj.src = "/assets/layouts/layout3/img/Submit-Grievance-pressed.png";
        } else {
            obj.src = "/assets/layouts/layout3/img/Ovijog-dakhil-Pressed.png";
        }
    }

    function loadNormalState(obj) {
        if (languageCode == "en") {
            obj.src = "/assets/layouts/layout3/img/Submit-Grievance.png";
        } else {
            obj.src = "/assets/layouts/layout3/img/Ovijog-dakhil.png";
        }
    }

    function appealButtonClickEventHandler(){
        var authToken = $.cookie("Authorization")
        if ( authToken!=null ){
            $(location).attr('href', '/viewGrievances.do');
        } else {
            $(location).attr('href', '/login?a=0');
        }
    }



</script>


</body></html>