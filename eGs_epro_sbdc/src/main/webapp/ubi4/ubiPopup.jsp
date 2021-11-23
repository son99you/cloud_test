<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>UbiReport4 HTMLViewer</title>
<!--[if IE]><script src='./js/ubiexcanvas.js'></script><![endif]-->
<script src='./js/ubihtml.js'></script>
<script src='./js/msg.js'></script>
<script src='./js/ubinonax.js'></script>
<script src='./js/ubi_print.js'></script>
<script src='./js/ubi_print.js'></script>

<script language='javascript'>
<!--
/*-----------------------------------------------------------------------------------
htmlViewer.setUserSaveList('Image,Pdf,Docx,Xls,Pptx,Hml,Cell');
htmlViewer.setUserPrintList('Ubi,Html,Pdf');
htmlViewer.setVisibleToolbar('INFO', false);
htmlViewer.HmlExtension='hwp';
htmlViewer.printHTML();		// HTML PrintSet
htmlViewer.printPDF();		// PDF PrintSet
htmlViewer.export('PDF');	// PDF/EXCEL/EXCEL_NO/HWP/PPTX/HML/DOCX/CELL/IMAGE
htmlViewer.print();		// Direct Print(WS VIEWER)
htmlViewer.printSet();		// PrintSet(WS VIEWER)
//htmlViewer.events.printEnd = UbiPrintEnd;
//htmlViewer.events.exportEnd = UbiExportEnd;
-----------------------------------------------------------------------------------*/
	/* URL 정보 */
	var app = '';
	var appUrl = self.location.protocol + '//' + self.location.host + (app==''?'':('/' + app));
	
	/* Viewer Object */
	var htmlViewer = null;

	/* Viewer Param */
	var pKey = '<%= session.getId() %>';
	//var pServerUrl = '';
	//var pServerUrl = 'http://localhost:8080/ubi4/export/073f4202-3099-413d-ba2b-bc61089a7b4d.xml';
	var pServerUrl = "";
	
	var pResUrl = appUrl + '/ubi4/js/';
	var pDivId = 'UbiHTMLViewer';
	var pScale = 'WholePage';	//WholePage/PageWidth/60~300
	var pState = "";
	var downfilename = "";
	var pPdf = "";
	/* Modify for your environment */
	var pJrf = "";
	var pArg = "";
	var pResId = "UBIHTML";
	/* Report Preview */
	function UbiLoadReport() {
		
		pPdf = 	document.getElementById("P_PDF").value;
		pUrf =  document.getElementById("P_URF").value;
		pState = document.getElementById("P_STATE").value;
		downfilename = document.getElementById("P_CONT_NM").value + ".pdf";
		//downfilename ="test.pdf"
		//alert(downfilename);
		pServerUrl = appUrl + '/comm/download_tmp.do?urf='+pUrf;
		
		UbiResize();
		htmlViewer = new UbiViewer( {

			key : pKey,
			ubiserverurl : pServerUrl,
			resource : pResUrl,
			resid : pResId,
			divid : pDivId,
			scale : pScale,
			jrffile : pJrf,
			isStreaming : 'false',
			arg : pArg
		});
		htmlViewer.showReport(UbiPreviewEnd);
		htmlViewer.events.printEnd = UbiPrintEnd;
		htmlViewer.events.exportEnd = UbiExportEnd;
		htmlViewer.events.printClicked = Ubi_PrintClicked;
		htmlViewer.events.exportClicked = Ubi_ExportClicked;
	}

	/* Preview Callback */
	function UbiPreviewEnd() {
		if(pState == 'C006' || pState == 'C007' || pState == 'C008'){
			htmlViewer.setEnableToolbar("PRINT", false);
			htmlViewer.setEnableToolbar("SAVE", false);
		}	
		htmlViewer.setVisibleToolbar('PRINT_PDF',false);
		
		htmlViewer.setVisibleToolbar('PRINT_HTML',false);

		htmlViewer.setVisibleToolbar('SAVE_PDF',false);
		htmlViewer.setVisibleToolbar('SAVE_EXCEL',false);
		htmlViewer.setVisibleToolbar('SAVE_DOCX',false);
		htmlViewer.setVisibleToolbar('SAVE_PPTX',false);
		htmlViewer.setVisibleToolbar('SAVE_IMAGE',false);
		htmlViewer.setVisibleToolbar('SAVE_CELL',false);
		htmlViewer.setVisibleToolbar('SAVE_HML',false);



		htmlViewer.setPrintMenu('PDF');
		htmlViewer.setSaveMenu('PDF');
		           

		// 전용뷰어 사용 기준 페이지 : 50페이지 이상이면 전용뷰어 인쇄만 활성화 됩니다.
		var basePageNum = 50;
		try {
						
			if( basePageNum <= htmlViewer.totalPage ) {

				htmlViewer.setEnableToolbar("PRINT_PDF", false);
				
				htmlViewer.setEnableToolbar("PRINT_HTML", false);
				htmlViewer.setEnableToolbar("PRINT_UBI", true);
				htmlViewer.setPluginprogress(true);
			}
			else {

				htmlViewer.setEnableToolbar("PRINT_PDF", true);
				htmlViewer.setEnableToolbar("PRINT_HTML", true);
				htmlViewer.setEnableToolbar("PRINT_UBI", false);
				htmlViewer.setPluginprogress(false);
			} 
		}
		catch (e) {}
	}

	/* Print Callback */
	function UbiPrintEnd(flag) {

	}

	/* Export Callback */
	function UbiExportEnd(flag, msg) {


	}

	/* print click */
	function Ubi_PrintClicked(flag) {
		console.log("####################Print Click#####################################");
		printflag = true;
		//file = "D:/edata1/2020/10/21/cont/073f4202-3099-413d-ba2b-bc61089a7b4d.pdf";
		file = pPdf;
		pdfPrint(file, printflag, downfilename);

		
	}

	/* save click */
	function Ubi_ExportClicked(flag) {
		printflag = false;
		//file = "D:/kto/workspace/eGs_epro_kto_dev/src/main/webapp/ubi4/export/20201016.pdf";
		//file = "D:/edata1/2020/10/21/cont/073f4202-3099-413d-ba2b-bc61089a7b4d.pdf";
		file = pPdf;
		pdfPrint(file, printflag, downfilename);

	}

	/* Viewer Object Resize */
	function UbiResize() {

		/* Size Gap */
		var gap = 6;
		var w = ((self.innerWidth || (document.documentElement && document.documentElement.clientWidth) || document.body.clientWidth)) - gap;
		var h = ((self.innerHeight || (document.documentElement && document.documentElement.clientHeight) || document.body.clientHeight)) - gap;
		document.getElementById(pDivId).style.width = w + 'px';
		document.getElementById(pDivId).style.height = h + 'px';
	}

//-->
</script>
</head>
<body style='margin:1px' onload='UbiLoadReport()' onresize='UbiResize()'>
	<input type="hidden" id="P_PDF" value="${param.P_PDF }">
	<input type="hidden" id="P_URF" value="${param.P_URF }">
	<input type="hidden" id="P_STATE" value="${param.P_STATE }">
	<input type="hidden" id="P_CONT_NM" value="${param.P_CONT_NM }">
	
	<div id='UbiHTMLViewer' style='border:1px solid #767676; border-bottom-width:2px;'></div>
	<div id='UbiHTMLViewerDiv_print'></div>
</body>
</html>
<form id="updtFrm" method="POST">
</form>
