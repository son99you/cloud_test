<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.ubireport.viewer.report.preview.UbiViewer" %>
<%
	String appUrl		= "http://localhost:8080";
	String fileURL		= appUrl + "/ubi4";	
	String ubiServerURL	= appUrl + "/UbiServer";							 

	String ds		= "Tutorial";
	String jrfFileDir	= "D:/kto/workspace/eGs_epro_kto_dev/src/main/webapp/ubi4/work/";
	String jrfFileName	= "ubi_sample.jrf";//"RMProcedureDocument.jrf";
	String arg		= "user#홍길동#";
	
	String exportPath	= "D:/kto/workspace/eGs_epro_kto_dev/src/main/webapp/ubi4/export/";
	//String exportFileName	= "ubi_sampl.pdf";
	String exportFileName	= "ubi_sample";
	String exportFilePath	= exportPath + java.io.File.separator + exportFileName;
	String exportfileType	= "URFIA"; // EXCEL_XML,URFIA

	try {

		UbiViewer ubi = new UbiViewer(false, false);

		ubi.fileURL = fileURL;
		ubi.ubiServerURL = ubiServerURL;
		ubi.isLocalFile = true;
		ubi.jrfFileDir = jrfFileDir;
		ubi.jrfFileName = jrfFileName;
		ubi.arg = arg;
		ubi.dataSource = ds;
		ubi.setExportParams(exportfileType, exportFilePath);
		//ubi.setFontPath("C:/UbiService/fonts/");
		ubi.exectype = "TYPE6";
		ubi.fontRevision = true;
		ubi.isDebug = true;


		boolean isSuccess = ubi.loadReport();
		ubi.exportFileName = "D:/kto/workspace/eGs_epro_kto_dev/src/main/webapp/ubi4/export/ubi_sample.pdf";
		ubi.exportReport("PDF");
	

		if( isSuccess ) 
			out.println("Export Success!");

		else
			out.println("Export Fail!");
	}
	catch(Exception e) {

		out.println(e.getMessage());
		e.printStackTrace();
	}
%>