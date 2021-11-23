<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.io.*, java.text.*, java.util.*"%>

<%
	request.setCharacterEncoding("UTF-8");
String exportFileName = request.getParameter("exportfilename");
String printflag = request.getParameter("printflag");
String downfilename = request.getParameter("downfilename");
System.out.println(exportFileName);
System.out.println(printflag);
System.out.println(downfilename);
//downfilename = java.net.URLEncoder.encode(downfilename, "UTF-8");
response.setHeader("Access-Control-Allow-Origin", "*");
response.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type, accept");
response.setHeader("Access-Control-Allow-Credentials", "true");
FileInputStream fis = null;
BufferedOutputStream bos = null;
try {
	String pdfFileName = exportFileName;
	File pdfFile = new File(pdfFileName);
	//클라이언트 브라우져에서 바로 보는 방법(헤더 변경)
	response.setContentType("application/pdf");
	//★ 이 구문이 있으면 [다운로드], 이 구문이 없다면 바로 target 지정된 곳에 view 해줍니다.
	if(printflag.equals("false")) {
		response.addHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(downfilename, "UTF-8") );
	}
	//파일 읽고 쓰는 건 일반적인 Write방식이랑 동일합니다. 다만 reponse 출력 스트림 객체에 write.
	fis = new FileInputStream(pdfFile);
	int size = fis.available(); //지정 파일에서 읽을 수 있는 바이트 수를 반환
	byte[] buf = new byte[size]; //버퍼설정
	int readCount = fis.read(buf);
	response.flushBuffer();
	out.clear();
	out=pageContext.pushBody();
	bos = new BufferedOutputStream(response.getOutputStream());
	bos.write(buf, 0, readCount);
	bos.flush();

} catch (Exception e) {

	e.printStackTrace();

} finally {

	try {

		if (fis != null)
	fis.close(); //close는 꼭! 반드시!

		if (bos != null)
	bos.close();

	} catch (IOException e) {

		e.printStackTrace();

	}

}
%>