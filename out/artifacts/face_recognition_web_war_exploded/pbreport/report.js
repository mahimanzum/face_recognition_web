
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


function isNumeric(n)
{
	return !isNaN(parseFloat(n)) && isFinite(n);
}

function comparator(x, y, dir)
{
	if(dir == 1)
	{
		if(isNumeric(x.innerHTML) && isNumeric(y.innerHTML))
  	    {
			if(parseInt(x.innerHTML) < parseInt(y.innerHTML))
			{
				return true;
			}
	    	 
  	  	}
		else if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) 
		{
			return true;
		}
	}
	else
	{
		if(isNumeric(x.innerHTML) && isNumeric(y.innerHTML))
  	    {
			if(parseInt(x.innerHTML) > parseInt(y.innerHTML))
			{
				return true;
			}
	    	 
  	  	}
		else if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) 
		{
			return true;
		}
	}
}

function customadd(x, y)
{
	var z;
	if(isNumeric(x) && isNumeric(y))
	{
		z = parseInt(x) + parseInt(y);
	}
	else
	{
		z = "";
	}
	console.log("x = " + x + " y = " + y + " z = " + z)
	return z;
}

function sortTable(row, dir) 
{
	console.log("sorting");
	var table, rows, switching, i, x, y, shouldSwitch;
	table = document.getElementById("reportTable");
	switching = true;
	/* Make a loop that will continue until
	no switching has been done: */
	while (switching) 
	{
		// Start by saying: no switching is done:
		switching = false;
		rows = table.rows;
		/* Loop through all table rows (except the
		first, which contains table headers): */
		for (i = 1; i < (rows.length - 1); i++) 
		{
			// Start by saying there should be no switching:
			shouldSwitch = false;
			/* Get the two elements you want to compare,
			one from current row and one from the next: */
			x = rows[i].getElementsByTagName("TD")[row];
			y = rows[i + 1].getElementsByTagName("TD")[row];
			// Check if the two rows should switch place:

			if(comparator(x, y, dir) == true)
			{
				shouldSwitch = true;
				break;
			}
	  
		}

		if (shouldSwitch) 
		{
		  /* If a switch has been marked, make the switch
		  and mark that a switch has been done: */
		  rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
		  switching = true;
		}
	}
	console.log("sorting done");
}


function getArrows(j)
{
	var arrows = "<span class=\"up-down-link\"> " + 
	"<a class=\"up-link\"><span><i onclick='sortTable(" + j + ", 2)' class=\"fa fa-arrow-circle-up\"></i></span></a>" +
	"<a class=\"down-link\"><span><i onclick='sortTable(" + j + ", 1)' class=\"fa fa-arrow-circle-down\"></i></span></a>" +
	"</span>";
	
	return arrows;
}

function formatpagenoAndRPP()
{
	var pageno = document.getElementById("pageno").value;
	var RECORDS_PER_PAGE = document.getElementById("RECORDS_PER_PAGE").value;
	if(pageno == "" || pageno < 1)
	{
		document.getElementById("pageno").value = 1;
	}
	if(RECORDS_PER_PAGE == "" || RECORDS_PER_PAGE < 1)
	{
		document.getElementById("RECORDS_PER_PAGE").value = 100;
	}
}

function ajaxSubmit()
{
	console.log("Ajax default submitting");
	formatpagenoAndRPP();
	var pageno = document.getElementById("pageno").value;
	var RECORDS_PER_PAGE = document.getElementById("RECORDS_PER_PAGE").value;
	if(pageno > document.getElementById("tatalPageNo").innerHTML)
	{
		pageno = 1;
	}
	$("input[name=pageno]").val(pageno);
	$("input[name=RECORDS_PER_PAGE]").val(RECORDS_PER_PAGE);

	ajaxMiniSubmit();
}

function ajaxMiniSubmit()
{
	console.log("Ajax submitting 2");
	

	callAjax(context+$("#countURL").val(),reportForm.serialize(),getTotalDataCountCallBack,"GET");
	submit();
}

function firstloadSubmit()
{
	console.log("Ajax firstload submitting");
	formatpagenoAndRPP();
	var pageno = 1;
	var RECORDS_PER_PAGE = document.getElementById("RECORDS_PER_PAGE").value;
	$("input[name=pageno]").val(pageno);
	$("input[name=RECORDS_PER_PAGE]").val(RECORDS_PER_PAGE);

	ajaxMiniSubmit();
}

function lastloadSubmit()
{
	console.log("Ajax lastload submitting");
	formatpagenoAndRPP();
	var RECORDS_PER_PAGE = document.getElementById("RECORDS_PER_PAGE").value;
	var pageno = document.getElementById("tatalPageNo").innerHTML;
	$("input[name=pageno]").val(pageno);
	$("input[name=RECORDS_PER_PAGE]").val(RECORDS_PER_PAGE);

	ajaxMiniSubmit();
}

function nextloadSubmit()
{
	console.log("Ajax nextload submitting");
	formatpagenoAndRPP();
	var pageno = parseInt(document.getElementById("pageno").value) + 1;
	var RECORDS_PER_PAGE = document.getElementById("RECORDS_PER_PAGE").value;
	if(pageno > document.getElementById("tatalPageNo").innerHTML)
	{
		pageno = 1;
	}
	$("input[name=pageno]").val(pageno);
	$("input[name=RECORDS_PER_PAGE]").val(RECORDS_PER_PAGE);

	ajaxMiniSubmit();
}

function prevloadSubmit()
{
	console.log("Ajax prevload submitting");
	formatpagenoAndRPP();
	var pageno = parseInt(document.getElementById("pageno").value) - 1;
	var RECORDS_PER_PAGE = document.getElementById("RECORDS_PER_PAGE").value;
	if(pageno < 1)
	{
		pageno = document.getElementById("tatalPageNo").innerHTML;
	}
	$("input[name=pageno]").val(pageno);
	$("input[name=RECORDS_PER_PAGE]").val(RECORDS_PER_PAGE);

	ajaxMiniSubmit();
}

var columnDataFromater = 
{
    exportOptions: 
	{
        format: 
		{
            body: function (data, row, column, node) 
			{
            	/*data.replace( /[$,.]/g, '' )*/ 
                return data.replace(/(&nbsp;|<([^>]+)>)/ig, "");/*remove html tag from data when exporting*/
            }
        }
    }
};

var createTable = function (data) 
{
    var createdHTML = "";
    var isBodyCreatedStart = false;
    var isHeadCreated = false;
    
    
    createdHTML += "<thead><tr>";
    
    var j = 0;
    var list = data[0];
    var sums = [];
    $.each(list, function (innerIndex, listItem) 
	{
    	console.log("j = "+ j + " headItem = " + listItem);
        createdHTML += "<th class='text-center-report'>" + listItem + getArrows(j) + "</th>";
        sums[j] = 0;
        j ++;
    });
   
    createdHTML += "</tr></thead>";
    
    var onlyData = data.slice(1);
    
    
    
    createdHTML += "<tbody>";
    $.each(onlyData, function (index, list) 
    {
    	createdHTML += "<tr>";
    	var j = 0;
        $.each(list, function (innerIndex, listItem) 
		{
        	
            createdHTML += "<td class='text-center-report'>" + listItem + "</td>";
            sums[j] = customadd(sums[j] , listItem);
            console.log("j = "+ j + " listItem = " + listItem + " sums[j] = " + sums[j]);
            j ++;
        })
        console.log(" ");
        createdHTML += "</tr>";
    });
    createdHTML += "</tbody>";
    
    
    createdHTML += "<tfoot><tr>";
    $.each(sums, function (innerIndex, listItem) 
	{
        createdHTML += "<td class='text-center-report'>" + listItem + "</id>";
        j ++;
    });
    createdHTML += "</tr></tfoot>";

    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = today.getFullYear();

    today = mm + '/' + dd + '/' + yyyy;
    
    reportDataTable.destroy();
    reportTable.empty();
    reportTable.attr("width", "100%");
    reportTable.append(createdHTML);
    reportDataTable = reportTable.DataTable(
	{
        'searching': false,
        'destroy': true,
        'ordering': false,
        'scrollX': true,
        'scrollY': true,
        'paging': false,
        'pagingType': 'simple',
        'dom': 'Bfrtip',
        'buttons': [
            $.extend(true, {}, columnDataFromater, 
			{
            	className: 'desktop-only-inline-block',
                extend: 'copyHtml5', 
                footer: true 
            }),
            $.extend(true, {}, columnDataFromater, 
			{
            	className: 'desktop-only-inline-block',
                extend: 'excelHtml5',
                footer: true 
            }),
            $.extend(true, {}, columnDataFromater, 
			{
            	className: 'desktop-only-inline-block',
                extend: 'csvHtml5',
                footer: true 
            }),
			$.extend(true, {}, columnDataFromater, 
			{
				className: 'exportButton',
                extend: 'pdfHtml5',
                footer: true 
            }),
			{
            	className: 'desktop-only-inline-block',
                extend: 'print',
                text: 'Print',
                title: $("#report-name").html() + '<br> ' + today + '<br>',
                autoPrint: true,
                footer: true,
                header: false 
            }
        ]
        
    });
    $("#report-div").show();

    $("#reportTable_info").html("Total "+totalRecord+" entries");
}

function createTableCallback(data) 
{
	if (data['responseCode'] == 1) {
	    createTable(data.payload);
	}else {
		toastr.error(data['msg']);
	}
}


var updateRecordInfo = function(totalRecord,recordPerPage)
{
	totalPageNo = Math.ceil(parseFloat(totalRecord)/parseFloat(recordPerPage));
	$("#tatalPageNo").html(totalPageNo);
	$("#tatalPageNoMobile").html(totalPageNo);
	$("input[name=totalRecord]").val(totalRecord);
	
}

var getTotalDataCountCallBack = function(data)
{
	recordPerPage = $("input[name=RECORDS_PER_PAGE]").val();
	totalRecord = data.payload;
	$("#reportTable_info").html("Total "+totalRecord+" entries");
	updateRecordInfo(totalRecord,recordPerPage);  	
}



var submit = function()
{
	console.log("submitting");
	//flashOldReport();
	$(".navigator").show();
	PreprocessBeforeSubmiting(0, "report");
	callAjax(reportForm.attr('action'), reportForm.serialize(), createTableCallback, "GET");
	console.log("submitting 2");
	PostprocessAfterSubmiting(0);
	if(document.getElementById("searchCriteria").getAttribute("hide") == "true")
	{				
		document.getElementById("searchCriteria").remove();
		document.getElementById("button-div").remove();
	}						      
}
	
$(document).ready(function () 
{
	console.log("we r ready 2");
	init(0);   
    ajaxSubmit();
 
});






