/*
 * Report Generation
 * Contributor : Dhrubo & Kawser
 */
/*
 * START : Table generation using ajax data and Jquery dataTable 
 * By: Kawser
 */
//document.addEventListener('contextmenu', event => event.preventDefault());
var currentPageNo = 1;
var totalRecord = 0;
var totalPageNo = 0;
var recordPerPage = 0;

var reportTable = $('#reportTable');
var reportForm =  $("#ReportForm");
//init DataTable first time without any data
var reportDataTable = reportTable.DataTable({
    'searching': false,
    'destroy': true,
    'ordering': false,
    'scrollX': true,
    'scrollY': true,
    'paging': false
});

var flashOldReport = function(){
	currentPageNo = 1;
	totalRecord = 0;
	totalPageNo = 0;
	recordPerPage = 0;
	
	$(".navigator").hide();
	reportDataTable.destroy();
    reportTable.empty();
    var createdHTML = "<thead><tr><th></th></tr></thead><tbody></tody>";
    reportTable.append(createdHTML);
    reportDataTable = reportTable.DataTable({
        'searching': false,
        'destroy': true,
        'ordering': false,
        'scrollX': true,
        'scrollY': true,
        'paging': false
    });
};
	
$(document).ready(function () {
	
    var columnDataFromater = {
        exportOptions: {
            format: {
                body: function (data, row, column, node) {
                	/*data.replace( /[$,.]/g, '' )*/ 
                    return data.replace(/(&nbsp;|<([^>]+)>)/ig, "");/*remove html tag from data when exporting*/
                }
            }
        }
    };
    
    var createTable = function (data) {
        var createdHTML = "";
        var isBodyCreatedStart = false;
        var isHeadCreated = false;
        //debugger;
//        data = '[["ID","Username"],[5001,0]]';
        $.each(data, function (index, list) {
            if (index != 0) {
                if (!isBodyCreatedStart) {
                    createdHTML += "<tbody>";
                    isBodyCreatedStart = true;
                }
                createdHTML += "<tr>";
                $.each(list, function (innerIndex, listItem) {
                    createdHTML += "<td class='text-center'>" + listItem + "</td>"
                })
                createdHTML += "</tr>";
            } else {
                if (!isHeadCreated) {
                    createdHTML += "<thead><tr>";
                }
                $.each(list, function (innerIndex, listItem) {
                    createdHTML += "<th class='text-center'>" + listItem + "</th>"
                })
                if (!isHeadCreated) {
                    createdHTML += "</thead></tr>";
                    isHeadCreated = true;
                }
            }
        });
        createdHTML += "</tbody>";
        reportDataTable.destroy();
        reportTable.empty();
        reportTable.attr("width", "100%");
        reportTable.append(createdHTML);
        reportDataTable = reportTable.DataTable({
            'searching': false,
            'destroy': true,
            'ordering': false,
            'scrollX': true,
            'scrollY': true,
            'paging': false,
            'pagingType': 'simple',
            'dom': 'Bfrtip',
            'buttons': [
                $.extend(true, {}, columnDataFromater, {
                    extend: 'copyHtml5'
                }),
                $.extend(true, {}, columnDataFromater, {
                    extend: 'excelHtml5'
                }),
                $.extend(true, {}, columnDataFromater, {
                    extend: 'csvHtml5'
                })
            ]
            
        });
        $("#report-div").show();
        $('html, body').animate({
            scrollTop: parseInt($(".custom-form-action").offset().top-20)
        }, 400);
        
        $("#reportTable_info").html("Total "+totalRecord+" entries");
    }

    function createTableCallback(data) {
    	if (data['responseCode'] == 1) {
    	    createTable(data.payload);
    	}else {
    		toastr.error(data['msg']);
    	}
    }
    
    function isAnyDisplayItemChecked(){
    	var isFoundDisplay =false;
    	var reportFormDataArray = reportForm.serializeArray();
    	$.each(reportFormDataArray,function(index,data){
    		if(data.name.indexOf("display") !== -1){
    			isFoundDisplay = true;			
    		}
    	});
    	return isFoundDisplay;
    }
    
    var updateRecordInfo = function(totalRecord,recordPerPage){
    	totalPageNo = Math.ceil(parseFloat(totalRecord)/parseFloat(recordPerPage));
    	$("input[name=tatalPageNo]").val(totalPageNo);
    	$("input[name=totalRecord]").val(totalRecord);
    	
    }
    
    var getTotalDataCountCallBack = function(data){
    	recordPerPage = $("input[name=RECORDS_PER_PAGE]").val();
    	totalRecord = data.payload;
    	$("#reportTable_info").html("Total "+totalRecord+" entries");
    	updateRecordInfo(totalRecord,recordPerPage);  	
    }
    
    var reportPagination = function(){
    	$("input[name=pageno]").val(currentPageNo);
    	var recordPerPage = $("input[name=RECORDS_PER_PAGE]").val();

    	var currentURL = window.location.href;
		var URL = updateQueryStringParameter(currentURL,"actionType","reportPagination");
    	
    	callAjax(URL+'&recordPerPage='+recordPerPage+"&currentPageNo="+currentPageNo,null,createTableCallback,"GET");
    }
    
    var submit = function(){
        if(isAnyDisplayItemChecked()){ 
        	$(".navigator").show();
        	
        	var currentURL = window.location.href;
			var URL = updateQueryStringParameter(currentURL,"actionType","reportResult");
        	
        	
        	callAjax(URL, reportForm.serialize(), createTableCallback, "GET");
        }else{
        	flashOldReport();
        	alert("Please select one/more display item to generate a report.");
        }
    }
    
    $("#defaultLoad").on("click",function(event){
    	currentPageNo = 1;
    	$("input[name=pageno]").val(currentPageNo);
    	event.preventDefault();
    	
    	var isSinglePageReport = ($("#isSinglePageReport").parent().hasClass("checked"));
    	
    	if(isSinglePageReport){
    		// hide the 
    		$(".custom-form-action").hide();
    	}
    	
    	var currentURL = window.location.href;
		var URL = updateQueryStringParameter(currentURL,"actionType","reportCount");
    	
    	callAjax(URL,reportForm.serialize(),getTotalDataCountCallBack,"GET");
    	submit();
    });
    
    $("#forceLoad").on("click",function(event){
    	event.preventDefault();
    	currentPageNo = 1;
    	updateRecordInfo(totalRecord,$("input[name=RECORDS_PER_PAGE]").val());
    	currentPageNo = $("input[name=pageno]").val(); 
    	reportPagination();
    });
    
    $("#firstLoad").on("click",function(event){
    	currentPageNo = 1;
    	event.preventDefault();
    	
    	reportPagination();
    	
    });
    
    $("#nextLoad").on("click",function(event){
    	if(currentPageNo < totalPageNo){
    		currentPageNo++;
    	}
    	$("input[name=pageno]").val(currentPageNo);
    	event.preventDefault();
    	reportPagination();
    });
    
    $("#previousLoad").on("click",function(event){
    	if(currentPageNo >= 2 ){
    		currentPageNo--;
    	}
    	$("input[name=pageno]").val(currentPageNo);
    	event.preventDefault();
    	reportPagination();
    });
    
    $("#lastLoad").on("click",function(event){
    	currentPageNo = totalPageNo;
    	$("input[name=pageno]").val(totalPageNo);
    	event.preventDefault();
    	reportPagination();
    });
   
});
/*
 * END : Table generation using ajax data
 */
/*
 *START : Generate dynamic code from DisplayDiv
 *BY: Dhrubo
 */

function drawCriteriaAndOrderFromDisplay() {
    var criteriaHtml = '<div class="portlet light">' +
        '<div class="portlet-title"><div class="caption"><i class="fa fa-list"></i>Criteria</div></div>' +
        '<div class="portlet-body form" style="height: 30vh; overflow-x: hidden; overflow-y:  scroll;"><div class="form-body"></div></div>' +
        '</div>'
    $("#criteria").html(criteriaHtml);

    var orderByHtml = '<div class="portlet light">' +
        '<div class="portlet-title"><div class="caption"><i class="fa fa-reorder"></i>Order By</div></div>' +
        '<div class="portlet-body form" style="height: 30vh; overflow-x: hidden; overflow-y:  scroll;"><div class="form-body"></div></div>' +
        '</div>'
    $("#orderby").html(orderByHtml);

    $("#display .form-group .display-input")
        .each(
            function (index) {
                var thisName = $(this).attr('name');
                var newValue = thisName.substring(8);

                var newFormGroupCriteria = '<div class="form-group"><div class="col-md-9">' +
                    '<label class="checkbox"><span><input type="checkbox" class="input-checkbox-criteria" value="' +
                    $(this).parent().prev().find("input")
                    .val() +
                    '"></span>' +
                    $(this).val() +
                    '</label></div>' +
                    '<div class="col-md-3" style="position: relative;">' +
                    '<span class="up-down-link" style="position: absolute; top: 50%"><a class="up-link"><span><i class="fa fa-arrow-circle-up"></i></span></a><a class="down-link"><span><i class="fa fa-arrow-circle-down"></i></span></a></span>' +
                    '</div></div>';
                $("#criteria .form-body").append(
                    newFormGroupCriteria);

                var newFormGroupOrderBy = '<div class="form-group">' +
                    '<div class="col-md-9">' +
                    '<label class="checkbox"><span><input type="checkbox" class="input-checkbox-orderby" value="' +
                    newValue +
                    '" name="orderByColumns" data-sequenceno="' +
                    $(this).parent().prev().find("input")
                    .val() +
                    '"></span>' +
                    $(this).val() +
                    '</label>' +
                    '</div>' +
                    '<div class="col-md-3">' +
                    '<span class="up-down-link" style="position: absolute; top: 50%"><a class="up-link"><span><i class="fa fa-arrow-circle-up"></i></span></a><a class="down-link"><span><i class="fa fa-arrow-circle-down"></i></span></a></span>' +
                    '</div></div>';
                $("#orderby .form-body")
                    .append(newFormGroupOrderBy);
            });
}
drawCriteriaAndOrderFromDisplay();
/*
 *END : Generate dynamic code from DisplayDiv
 */

/*
 * START: Store sequence of criteria,display, order by
 * BY: Dhrubo
 */
var criteriaSequenceArray = [];
$("#criteria .input-checkbox-criteria").each(function (index) {
    criteriaSequenceArray.push($(this).val());
});

$(".input-checkbox-criteria")
    .click(
        function () {
        	flashOldReport();
            if (!($(this).parent().hasClass("checked"))) {
                var elementFromDisplay = $("#display .input-checkbox-display[value='" +
                    $(this).val() + "']");
                var elementWithInput = elementFromDisplay.parent()
                    .parent().parent().parent().parent().next()
                    .children().first();
                var newName = elementWithInput.attr("name");
                newName = newName.substring(8);
                if (!("datepicker" == elementWithInput.attr("data-comment"))) {
	                newName = "criteria." + newName + "." + elementWithInput.attr("data-operator");
                }
                if ("select" == elementWithInput
                    .attr("data-comment")) {
                    var newFormGroupSearchCriteria = '<div id="' +
                        $(this).val() +
                        '" class="search-criteria-div"><div class="form-group"><label class="col-sm-3 control-label">' +
                        $(this).parent().parent().parent()
                        .parent().text() +
                        '</label>' +
                        '<div class="col-sm-6"><select class="form-control" name="' + newName + '"><option value="">Select</option></select>' +
                        '</div></div></div>';
                    $("#searchCriteria").append(
                        newFormGroupSearchCriteria);

                    var valueArray = elementWithInput.attr(
                        "data-values").split(",");
                    $.each(valueArray, function (index, value) {
                        var nameValuePair = value.split(":");
                        $(
                            "#searchCriteria select[name='" +
                            newName + "']").append(
                            "<option value=" + nameValuePair[1] + ">" +
                            nameValuePair[0] +
                            "</option>");
                    });
                }
                else {
                    if ("datepicker" == elementWithInput.attr("data-comment")) {
                    	
                    	var newFormGroupSearchCriteria = '<div id="' + $(this).val() + '" class="search-criteria-div">'
                    	
                    	+'<div class="form-group"><label class="col-sm-3 control-label">' +
                        $(this).parent().parent().parent().parent().text() +' From </label>' +
                        '<div class="col-sm-6"><input type="text" class="form-control" name="criteria.' + newName + '.geq" value="">' +
                        '</div></div>'
                        
                        +'<div class="form-group"><label class="col-sm-3 control-label">' +
                        $(this).parent().parent().parent().parent().text() +' To </label>' +
                        '<div class="col-sm-6"><input type="text" class="form-control" name="criteria.' + newName + '.leq" value="">' +
                        '</div></div>'

                        +'</div>';
                    	$("#searchCriteria").append(newFormGroupSearchCriteria);
                    	
                        $("#searchCriteria input[name='criteria." + newName + ".leq']").addClass("datepicker");
                        $("#searchCriteria input[name='criteria." + newName + ".geq']").addClass("datepicker");
                        $('.datepicker').datepicker({
                            orientation: "top",
                            autoclose: true,
                            format: 'dd/mm/yyyy',
                            todayBtn: 'linked',
                            todayHighlight: true
                        });
                    } else{
                    	var newFormGroupSearchCriteria = '<div id="' + $(this).val() + '" class="search-criteria-div"><div class="form-group"><label class="col-sm-3 control-label">' +
                        $(this).parent().parent().parent().parent().text() +'</label>' +
                        '<div class="col-sm-6"><input type="text" class="form-control" name="' + newName + '" value="">' +
                        '</div></div></div>';
                    	$("#searchCriteria").append(newFormGroupSearchCriteria);
                    }
                }
            }
            else {
                if ($("#searchCriteria").has("#" + $(this).val())) {
                    $("#" + $(this).val()).remove();
                }
            }
            sortSearchCriteria();
        });

function sortSearchCriteria() {
    var $searchCriteriaDivs = $('#searchCriteria').children(
        '.search-criteria-div');

    $searchCriteriaDivs
        .sort(function (div1, div2) {
            var index1 = criteriaSequenceArray.indexOf(div1
                .getAttribute('id'));
            var index2 = criteriaSequenceArray.indexOf(div2
                .getAttribute('id'));

            if (index1 > index2) {
                return 1;
            }
            if (index1 < index2) {
                return -1;
            }
            return 0;
        });

    $searchCriteriaDivs.detach().appendTo('#searchCriteria');
}

$(function () {
    $('.up-link')
        .on(
            'click',
            function (e) {
            	flashOldReport();
                var thisRow = $(this).closest('.form-group');
                var hook = thisRow.prev('.form-group');
                if (hook.length) {
                    var elementToMove = thisRow.detach();
                    hook.before(elementToMove);

                    var thisVal = thisRow.find(
                        ".input-checkbox-criteria").val();
                    var thisValIndex = criteriaSequenceArray
                        .indexOf(thisVal);
                    criteriaSequenceArray[thisValIndex] = criteriaSequenceArray[thisValIndex - 1];
                    criteriaSequenceArray[thisValIndex - 1] = thisVal;

                    sortSearchCriteria();
                }
                return false;
            });
    $('.down-link')
        .on(
            'click',
            function () {
            	flashOldReport();
                var thisRow = $(this).closest('.form-group');
                var hook = thisRow.next('.form-group');
                if (hook.length) {
                    var elementToMove = thisRow.detach();
                    hook.after(elementToMove);

                    var thisVal = thisRow.find(
                        ".input-checkbox-criteria").val();
                    var thisValIndex = criteriaSequenceArray
                        .indexOf(thisVal);
                    criteriaSequenceArray[thisValIndex] = criteriaSequenceArray[thisValIndex + 1];
                    criteriaSequenceArray[thisValIndex + 1] = thisVal;

                    sortSearchCriteria();
                }
                return false;
            });
});
/*
 * END: Store sequence of criteria,display, order by
 */

/*
 * START : Display Check-box on click handler
 * BY: Dhrubo
 */
$(".input-checkbox-display").click(
	    function() {
	    	flashOldReport();
	        if ($(this).parent().hasClass("checked")) {
	            $(this).parent().parent().parent().parent().parent().next()
	                .find("input").prop('disabled', true);
	        }
	        else {
	            $(this).parent().parent().parent().parent().parent().next()
	                .find("input").prop('disabled', false);
	        }
});
/*
 * END : Display Check-box on click handler
 */
/*
 * START : Load template List
 * BY: Dhrubo
 */
$(document).ready(function () {
	var urlLoadTemplateList = context + "ReportTemplate.do";
	var loadTemplateListData = {};
	loadTemplateListData.mode = "loadTemplateList";

	function loadTemplateListCallback(data) {
		$.each(data, function (index, item) {
			$(".load-template").append($('<option>', {
				value: item.id,
				text: item.name
			}));
		});
	}

	function loadTemplateList() {
		$(".load-template").html("<option value='-1'>Load a Template</option>");
		callAjax(urlLoadTemplateList, loadTemplateListData, loadTemplateListCallback, "GET");
	}
//	loadTemplateList();
	//save template
	
	loadDefaultTemplate();
	$(".save-template").on('keyup', function () {
		/*if ($(this).val().length > 0) {
			$(this).parent().next().find("button").prop('disabled', false);
		}
		else {
			$(this).parent().next().find("button").prop('disabled', true);
		}*/
	});

	function saveTemplateCallback(data) {
		if (data.responseCode == 1) {
			toastr.success("Template is saved successfully.");
			//loadTemplateList();
			loadDefaultTemplate();
		}else{
			toastr.error(data.msg);
		}
	}
	$("#save-template-button").click(function () {
		var serializedFormData = $("#ReportForm").serialize();
		if (serializedFormData.length > 0) {
			//var urlSaveTemplate = context + "ReportTemplate.do";
			serializedFormData += "&mode=saveTemplate";
			serializedFormData += "&reportTemplateName=" + $(this).parent().prev().find("input").val();
			var currentURL = window.location.href;
			currentURL = updateQueryStringParameter(currentURL,"actionType","reportTemplate");
			callAjax(currentURL, serializedFormData, saveTemplateCallback, "POST");
		}
		else {
			toastr.error("Nothing is selected.");
		}
	});
});
/*
 * END : Load template List
 */

/*
 * START : Redraw all criteria 
 * BY: Dhrubo
 **/
function redrawAllCriterias() {
	var $displayDivs = $('#display .form-body').children('.form-group');
	$displayDivs.sort(function (div1, div2) {
		var index1 = div1.firstElementChild.firstElementChild.firstElementChild.firstElementChild.firstElementChild.firstElementChild.getAttribute("value");
		var index2 = div2.firstElementChild.firstElementChild.firstElementChild.firstElementChild.firstElementChild.firstElementChild.getAttribute("value");
		console.log("index1 " + index1);
		if (index1 > index2) {
			return 1;
		}
		if (index1 < index2) {
			return -1;
		}
		return 0;
	});
	$displayDivs.detach().appendTo('#display .form-body');
	$("#display .form-group .input-checkbox-display").each(function (index) {
		if (($(this).parent().hasClass("checked"))) {
			$(this).trigger("click");
		}
	});
	var $criteriaDivs = $('#criteria .form-body').children('.form-group');
	$criteriaDivs.sort(function (div1, div2) {
		var index1 = div1.firstElementChild.firstElementChild.firstElementChild.firstElementChild.firstElementChild.firstElementChild.getAttribute("value");
		var index2 = div2.firstElementChild.firstElementChild.firstElementChild.firstElementChild.firstElementChild.firstElementChild.getAttribute("value");
		if (index1 > index2) {
			return 1;
		}
		if (index1 < index2) {
			return -1;
		}
		return 0;
	});
	$criteriaDivs.detach().appendTo('#criteria .form-body');
	$("#criteria .form-group .input-checkbox-criteria").each(function (index) {
		if (($(this).parent().hasClass("checked"))) {
			$(this).trigger("click");
		}
	});
	var $orderDivs = $('#orderby .form-body').children('.form-group');
	$orderDivs.sort(function (div1, div2) {
		var index1 = div1.firstElementChild.firstElementChild.firstElementChild.firstElementChild.firstElementChild.firstElementChild.getAttribute("data-sequenceno");
		var index2 = div2.firstElementChild.firstElementChild.firstElementChild.firstElementChild.firstElementChild.firstElementChild.getAttribute("data-sequenceno");
		if (index1 > index2) {
			return 1;
		}
		if (index1 < index2) {
			return -1;
		}
		return 0;
	});
	$orderDivs.detach().appendTo('#orderby .form-body');
	$("#orderby .form-group .input-checkbox-orderby").each(function (index) {
		if (($(this).parent().hasClass("checked"))) {
			$(this).trigger("click");
		}
	});
}
/*
 * END : Redraw all criteria
 */

/*
 * START : Load template
 * BY: Dhrubo
 */
$(".load-template").on('change', function () {
	if ($(this).val() > 0) {
		$(this).parent().next().find("button").prop('disabled', false);
	}
	else {
		$(this).parent().next().find("button").prop('disabled', true);
	}
});


$("#get-dto-fields-button").click(function(){
	
	var currentURL = window.location.href;
	currentURL = updateQueryStringParameter(currentURL,"actionType","dtoFieldList");
	
	var dtoName = $("input[name = 'dtoName']").val();
	currentURL += "&dtoName="+dtoName;
	//var dtoFieldListTemplateURL = context+"Report/reportServlet?actionType=dtoFieldList&dtoName="+dtoName;
	callAjax(currentURL,null, loadFieldListCallback, "GET");
});


$(document).on('keyup','.form-control', function () {
	var isSinglePageReport =  ($("#isSinglePageReport").parent().hasClass("checked"));
	if(isSinglePageReport){
		$('#defaultLoad').trigger('click');
	}
});



$('#save-dto-fields-button').click(function(){
	var formData = $('#parameterForm').serialize();
	var currentURL = window.location.href;
	currentURL = updateQueryStringParameter(currentURL,"actionType","defaultFieldList");
	
	var dtoName = $("input[name = 'dtoName']").val();
	formData+=('&dtoName='+dtoName);
	
	callAjax(currentURL,formData, null, "POST");
});


$('#get-saved-dto-fields-button').click(function(){
	
	var currentURL = window.location.href;
	currentURL = updateQueryStringParameter(currentURL,"actionType","defaultFieldList");
	callAjax(currentURL,null,loadSavedFieldListCallBack, "GET");
});




var urlLoadTemplate = context + "ReportTemplate.do";
var loadTemplateData = {};
loadTemplateData.mode = "loadTemplate";

function loadTemplateCallback(data) {
	if(data.responseCode!=1){
		toastr.error(data['msg']);
		return;
	}
	data = data.payload;
	var criteriaKeys = data.reportCriteria.split(',');
	var displayKeyValuePairs = data.reportDisplay.split(',');
	var orderValues = data.reportOrder.split(',');
	redrawAllCriterias();
	$.each(displayKeyValuePairs.reverse(), function (index, item) {
		var displayName = item.split("=")[0];
		var displayValue = item.split("=")[1];
		$("input[name='" + displayName + "']").val(displayValue);
		var displayItem = $("input[name='" + displayName + "']").parent().parent();
		displayItem.detach().prependTo("#display .form-body");
		$("input[name='" + displayName + "']").parent().prev().find("input").trigger("click");
	});
	$.each(criteriaKeys.reverse(), function (index, item) {
		var nameOfItemSplitted = item.split(".");
		nameOfItemSplitted[0] = "display";
		nameOfItemSplitted.pop();
		var displayNameOfCriteria = nameOfItemSplitted.join(".");
		var sequenceNoOfItem = $("input[name='" + displayNameOfCriteria + "']").parent().prev().find(".input-checkbox-display").val();
		var criteriaItem = $("#criteria input[value='" + sequenceNoOfItem + "']").parent().parent().parent().parent().parent().parent();
		criteriaItem.detach().prependTo("#criteria .form-body");
		var indexOfSequenceNoInCriteriaSequenceArray = criteriaSequenceArray.indexOf(sequenceNoOfItem);
		criteriaSequenceArray.splice(indexOfSequenceNoInCriteriaSequenceArray, 1);
		criteriaSequenceArray.unshift(sequenceNoOfItem);
		$("#criteria input[value='" + sequenceNoOfItem + "']").trigger("click");
	});
	$.each(orderValues.reverse(), function (index, item) {
		var orderItem = $("#orderby input[value='" + item + "']").parent().parent().parent().parent().parent().parent();
		orderItem.detach().prependTo("#orderby .form-body");
		$("#orderby input[value='" + item + "']").trigger("click");
	});
	if(data.isSinglePageReport &&  ($("#isSinglePageReport").parent().hasClass("checked"))==false){
		$("#isSinglePageReport").trigger("click");
	}
	if($("#isSinglePageReport").parent().hasClass("checked")){
		$('#defaultLoad').trigger('click');
	}
	//toastr.success("Template Loaded Successfully");
}
$("#load-template-button").click(function () {
	loadTemplateData.reportTemplateID = $(this).parent().prev().find("select").val();
	callAjax(urlLoadTemplate, loadTemplateData, loadTemplateCallback, "GET");
});

function loadDefaultTemplate(){
	//callAjax(context+"loadDefaultTemplate", null, loadTemplateCallback, "GET");
	var currentURL = window.location.href;
	currentURL = updateQueryStringParameter(currentURL,"actionType","reportTemplate");
	callAjax(currentURL, null, loadTemplateCallback, "GET");
}

function updateQueryStringParameter(uri, key, value) {
	  var re = new RegExp("([?&])" + key + "=.*?(&|$)", "i");
	  var separator = uri.indexOf('?') !== -1 ? "&" : "?";
	  if (uri.match(re)) {
	    return uri.replace(re, '$1' + key + "=" + value + '$2');
	  }
	  else {
	    return uri + separator + key + "=" + value;
	  }
	}
/*
* END :Load template
*/


function loadFieldListCallback(data) {
	if(data.responseCode!=1){
		toastr.error(data['msg']);
		return;
	}
	var data = data.payload;
	//var fieldNames = data.reportCriteria.split(',');
	
	
	var dtoNames = $("input[name = 'dtoName']").val();
	
    var sqlHtml = '<form id="parameterForm"><div class="portlet light">' +//'<input type="hidden" name="dtoName" value="'+dtoNames+'">'+
    '<div class="portlet-title"><div class="caption"><i class="fa fa-list"></i>Columns</div></div>' +
    '<div class="portlet-body form"><div class="form-body"></div></div>' +
    '</div></form>'
    
    
    $("#sql").html(sqlHtml);
	
	
	
//	var divItem = '<div class="portlet light" >  <div class="portlet-body form"><div class="form-body">';
	$.each(data, function (index, item) {
		
		var itemTokens = item.split(".");
		
		var regularFormFieldName=     itemTokens[2].replace(/([^A-Z])([A-Z])/g, '$1 $2')
	    .replace(/^./, function(str){ return str.toUpperCase(); });
		
		var itemNameTokens = item.split(".");
		var className = itemNameTokens[0]+"."+itemNameTokens[1];
		
        var tmp = '<div class="form-group col-md-12"><div class="col-md-3">' +
        '<label>' +
        className +
        '</label></div>' 
        
        
        +'<div class="col-md-2" >' 
    	+'<input type="text" name="'+item+'" placeholder="Name of the field" value="'+regularFormFieldName+'">'
        
        
	    + '</div>'
	    +'<div class="col-md-2" >' 
    	+'<input type="text" name="'+item+'" placeholder="Data comment" >'
        
        
	    + '</div>'
	    
	    +'<div class="col-md-2" >' 
    	+'<input type="text" name="'+item+'" placeholder="Data value" >'
	    + '</div>'
	    
	    
	    
        +'<div class="col-md-3" style="position: relative;">' 
			+'<select  class="form-control pull-right" name="' +item+'">'
	        +'<option value="" > Discard This field </option>'
	        +'<option value="eq" > Equal  </option>'
	        +'<option value="geq" > Greater That Equal  </option>'
	        +'<option value="neq" > Not Equal </option>'
	        +'<option value="g" > Greater Than  </option>'
	        +'<option value="l" > Less Than  </option>'
	        +'<option value="like" > like  </option>'
	        +'<option value="in" > IN  </option>'
	        +'<option value="null" > Is Null  </option>'
	        +'</select>'
        
        
	    + '</div>'
	    
      +'</div>';
		
		$("#sql .form-body").append(tmp);
		
	});
	//divItem=divItem+'</div></div></div>';
	
	
}

function loadSavedFieldListCallBack(data) {

	if(data.responseCode!=1){
		toastr.error(data['msg']);
		return;
	}
	var data = data.payload;
	
	var fieldList = data.reportParameters;
	var dtoNames = data.dotNames;
	
	$("input[name = 'dtoName']").val(dtoNames);
	//var fieldNames = data.reportCriteria.split(',');
	
    var sqlHtml = '<form id="parameterForm"><div class="portlet light">' +
    '<div class="portlet-title"><div class="caption"><i class="fa fa-list"></i>Columns</div></div>' +
    '<div class="portlet-body form"><div class="form-body"></div></div>' +
    '</div></form>'
    
    
    $("#sql").html(sqlHtml);
	
	
	
//	var divItem = '<div class="portlet light" >  <div class="portlet-body form"><div class="form-body">';
	$.each(fieldList, function (index, item) {
		
		var itemNameTokens = item.canonicalFullName.split(".");
		var className = itemNameTokens[0]+"."+itemNameTokens[1];
		
        var tmp = '<div class="form-group col-md-12"><div class="col-md-3">' +
        '<label >' +
        className +
        '</label></div>' 
        
        
        +'<div class="col-md-2" >' 
    	+'<input type="text" name="'+item.canonicalFullName+'" placeholder="Name of the field" value="'+item.userDefinedFullName+'">'
	    + '</div>'
	    
	    +'<div class="col-md-2" style="position: relative;">' 
    	+'<input type="text" name="'+item.canonicalFullName+'" placeholder="Data comment" value="'+item.dataComment+'">'
	    + '</div>'
	    

	    
	    +'<div class="col-md-2" style="position: relative;">' 
    	+'<input type="text" name="'+item.canonicalFullName+'" placeholder="Data value" value="'+item.dataValue+'">'
	    + '</div>'

	    
	    
        +'<div class="col-md-3">' 
			+'<select  class="form-control" name="' +item.canonicalFullName+'">'
	        +'<option '+ (item.operatorType==""?'selected':'') +' value="" > Discard This field </option>'
	        +'<option '+ (item.operatorType=="eq"?'selected':'') +' value="eq" > Equal  </option>'
	        +'<option '+ (item.operatorType=="geq"?'selected':'') +' value="geq" > Greater That Equal  </option>'
	        +'<option '+ (item.operatorType=="neq"?'selected':'') +' value="neq" > Not Equal </option>'
	        +'<option '+ (item.operatorType=="g"?'selected':'') +' value="g" > Greater Than  </option>'
	        +'<option '+ (item.operatorType=="l"?'selected':'') +' value="l" > Less Than  </option>'
	        +'<option '+ (item.operatorType=="like"?'selected':'') +' value="like" > like  </option>'
	        +'<option '+ (item.operatorType=="in"?'selected':'') +' value="in" > IN  </option>'
	        +'<option '+ (item.operatorType=="null"?'selected':'') +' value="null" > Is Null  </option>'
	        +'</select>'
	    + '</div>'
	    
      +'</div>';
		
		$("#sql .form-body").append(tmp);
		
	});

}