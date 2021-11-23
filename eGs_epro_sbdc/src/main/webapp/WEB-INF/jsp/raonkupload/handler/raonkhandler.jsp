<%@page import="com.eunwoosoft.frwk.fol.util.FwkFileUtil"%>
<%@page import="com.raonwiz.kupload.RAONKHandler"%>
<%@page import="com.raonwiz.kupload.event.*"%>
<%@page import="com.raonwiz.kupload.util.EventVo"%>
<%@page import="com.raonwiz.kupload.common.ImageApi"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="com.eunwoosoft.cipher.EwCipher"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@ page contentType="text/html;charset=utf-8"%><%
	//response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
	//response.setHeader("Access-Control-Allow-Headers", "content-type, RAONK-Encoded");
	//response.setHeader("Access-Control-Allow-Origin", "*");

	//out.clear(); // Servlet으로 handler 작업을 하시려면 제거해주세요.

	request.setCharacterEncoding("UTF-8");
	RAONKHandler upload = new RAONKHandler();

	//디버깅
    //2번째 파라미터의 Log Mode 설명
    //- C : 일반로그 출력(System.out.println 로그 출력)
    //- L : Log4j 모듈에 의한 로그 출력(/handler/JAVA 폴더의 log4j.properties 파일을 WEB-INF/classes에 적용)
    upload.settingVo.setDebugMode(true, "C");
    //upload.settingVo.setDebugMode(true, "L");

    ///////////////////////////////
	//        이벤트를 등록 처리          //
	///////////////////////////////
	EventClass event = new EventClass();


	event.addBeforeUploadEventListener(new BeforeUploadEventListener() {
		public void beforeUploadEvent(EventVo eventVo) {
			//파일을 업로드하기 전에 발생하는 이벤트 입니다.
	        //파일에 대한 저장 경로를 변경해야 하는 경우 사용합니다.

	        //HttpServletRequest request = eventVo.getRequest(); //Request Value
	        //HttpServletResponse response = eventVo.getResponse(); //Response Value
	        //String strNewFileLocation = eventVo.getNewFileLocation(); //NewFileLocation Value
	        //String strCustomValue = eventVo.getCustomValue(); //CustomValue
	        //String strFileIndex = eventVo.getFileIndex(); //FileIndex Value - 마지막 파일은 index 뒤에 z가 붙습니다.
			//String strOriginalFileName = eventVo.getOriginalFileName(); //Original File Name
			//String strGuid = eventVo.getGuid(); //Guid

	        //String[] aryParameterValue = eventVo.getUpload().getParameterValue("ParameterName"); //클라이언트에서 AddFormData를 이용하여 추가된 파라미터를 얻습니다.

	        //eventVo.setNewFileLocation(strNewFileLocation); //Change NewFileLocation Value

	        //eventVo.setCustomError("사용자오류");
	        //eventVo.setCustomError("999", "사용자오류"); //Error Code를 설정하실 때에는 900이상의 3자리로 설정
		}
    });



	event.addAfterUploadEventListener(new AfterUploadEventListener() {
		public void afterUploadEvent(EventVo eventVo) {
			//파일을 업로드한 후에 발생하는 이벤트 입니다.
	        //업로드된 파일을 변경하는 경우 사용됩니다.(DRM처리, Image API 처리)
	        //경로가 변경된 경우 Response되는 파일경로도 변경해야 합니다.(ResponseFileServerPath)

	        HttpServletRequest request = eventVo.getRequest(); //Request Value
	        HttpServletResponse response = eventVo.getResponse(); //Response Value
	        String strNewFileLocation = eventVo.getNewFileLocation(); //NewFileLocation Value
	        String strResponseFileServerPath = eventVo.getResponseFileServerPath(); //ResponseFileServerPath Value
	        String strCustomValue = eventVo.getCustomValue(); //CustomValue
	        String strFileIndex = eventVo.getFileIndex(); //FileIndex Value - 마지막 파일은 index 뒤에 z가 붙습니다.
			String strOriginalFileName = eventVo.getOriginalFileName(); //Original File Name
			String strGuid = eventVo.getGuid(); //Guid
			String strHashValue = eventVo.getHashValue(); //File Hash(MD5) 값 추출(Client UseHashExtract 1 설정시)

	        System.out.println("strNewFileLocation : " + strNewFileLocation);
	        System.out.println("strOriginalFileName : " + strOriginalFileName);

	        //String[] aryParameterValue = eventVo.getUpload().getParameterValue("ParameterName"); //클라이언트에서 AddFormData를 이용하여 추가된 파라미터를 얻습니다.

	        //eventVo.setResponseFileServerPath(strResponseFileServerPath); //Change ResponseFileServerPath Value
	        //eventVo.setResponseCustomValue("ResponseCustomValue"); //Set ResponseCustomValue

	        //eventVo.setCustomError("사용자오류");
	        //eventVo.setCustomError("999", "사용자오류"); //Error Code를 설정하실 때에는 900이상의 3자리로 설정

	        //이미지 처리 관련 API (SMB Protocol 설정 후 업로드시 사용이 불가능합니다.)
	        try {
				//float fJpegQuality = 100; // JPG 품질 (1 ~ 100)

                //String strTempFilePath = "";
                //String strSourceFileFullPath = strNewFileLocation;

                //동일 폴더에 이미지 썸네일 생성하기
                //strTempFilePath = ImageApi.makeThumbnail(strSourceFileFullPath, "", 200, 0, true, fJpegQuality);

                //특정위치에 이미지 썸네일 생성하기
                //String targetFileFullPath = "c:\\temp\\test_thumb.jpg";
                //strTempFilePath = ImageApi.makeThumbnailEX(strSourceFileFullPath, targetFileFullPath, 200, 0, false, fJpegQuality);

                //이미지 포멧 변경
                //strTempFilePath = ImageApi.convertImageFormat(strSourceFileFullPath, "", "png", false, false, fJpegQuality);

                //이미지 크기 변환
                //ImageApi.convertImageSize(strSourceFileFullPath, 500, 30, fJpegQuality);

                //비율로 이미지 크기 변환
                //ImageApi.convertImageSizeByPercent(strSourceFileFullPath, 50, fJpegQuality);

                //이미지 회전
                //ImageApi.rotate(strSourceFileFullPath, 90, fJpegQuality);

                //이미지 워터마크
                //String strWaterMarkFilePath = "c:\\temp\\raonk_logo.png";
                //ImageApi.setImageWaterMark(strSourceFileFullPath, strWaterMarkFilePath, "TOP", 10, "RIGHT", 10, 0, fJpegQuality);

                //텍스트 워터마크
                //com.raonwiz.kupload.util.TextWaterMarkVo txtWaterMarkVo = new com.raonwiz.kupload.util.TextWaterMarkVo("RAONK Upload", "굴림", 12, "#FF00FF");
                //ImageApi.setTextWaterMark(strSourceFileFullPath, txtWaterMarkVo, "TOP", 10, "CENTER", 10, 0, 0, fJpegQuality);

                //이미지 크기
                //java.awt.Dimension size = ImageApi.getImageSize(strSourceFileFullPath);
                //int _width = size.width;
                //int _height = size.height;

				//EXIF 추출 (Exif standard 2.2, JEITA CP-2451)
         		//jdk 1.6 이상에서만 사용가능합니다.
				//기능 활성화를 원하시면 1.6버전으로 컴파일된 jar를 고객센터로 요청하십시오.
				//com.raonwiz.kupload.common.ImageExif exif = new com.raonwiz.kupload.common.ImageExif();
                //com.raonwiz.kupload.common.exif.ExifEntity exifData = exif.getExifData(strSourceFileFullPath);

				//동일 폴더에 잘라낸 이미지 생성하기 (좌,우,상,하)
                //strTempFilePath = ImageApi.makeCropImage(strSourceFileFullPath, "", 10, 10, 10, 10, true, fJpegQuality);

                //특정위치에 잘라낸 이미지 생성하기 (좌,우,상,하)
                //String targetFileFullPath = "c:\\temp\\test_crop.jpg";
                //strTempFilePath = ImageApi.makeCropImageEX(strSourceFileFullPath, targetFileFullPath, 10, 10, 10, 10, false, fJpegQuality);
            } catch (Exception ex) {
                String errorMsg = ex.getMessage();
            }
		}
    });


	event.addBeforeDownloadEventListener(new BeforeDownloadEventListener(){
		 public void beforeDownloadEvent(EventVo eventVo){
			//파일을 다운로드하기 전에 발생하는 이벤트 입니다.
	        //파일에 대한 다운로드 경로를 변경하거나 서버에서 구해지는 Stream 다운로드로 처리할 경우 사용합니다.
	        String[] aryDownloadFilePath = eventVo.getDownloadFilePath(); //DownloadFilePath Value
	        String[] aryDownloadCustomValue = eventVo.getDownloadCustomValue();  //DownloadCustomValue

	        System.out.println("aryDownloadFilePath : " + aryDownloadFilePath);
	        System.out.println("aryDownloadCustomValue : " + aryDownloadCustomValue);

	        int iCustomValueLength = aryDownloadCustomValue.length;

	        String[] decryptpath = new String[iCustomValueLength];

	        File fileTmp = null;

	       // java.io.InputStream[] aryDownloadFileStream = new java.io.InputStream[iCustomValueLength];

			for (int i = 0; i < iCustomValueLength; i++) {
				// 해당 값을 이용하여 파일의 물리적 경로 생성 (실제 물리적 파일 경로를 설정해주세요.)
				System.out.println("aryDownloadCustomValue[i] : " + aryDownloadCustomValue[i]);
				System.out.println("aryDownloadFilePath[i] : " + aryDownloadFilePath[i]);

				File file = new File(aryDownloadFilePath[i]);

		        decryptpath[i] = aryDownloadFilePath[i]+".tmp";

		        try{
		        	EwCipher.seedFileDecrypt(file, decryptpath[i], "ecDocuEbidSys_18");

				} catch (Exception e) {
					e.printStackTrace();
				}
		        System.out.println("decryptpath[i] : " + decryptpath[i]);
		        //System.out.println("aryDownloadFileStream[i] : " + aryDownloadFileStream[i]);
			}
			//eventVo.setDownloadFileStream(aryDownloadFileStream);
			eventVo.setDownloadFilePath(decryptpath); //Setting DownloadFileStream Value
		}
    });



	event.addExecuteDownloadEventListener(new ExecuteDownloadEventListener() {
		public void executeDownloadEvent(EventVo eventVo) {
			//다운로드 파일을 서버에서 구해지는 Stream 다운로드로 처리할 경우 Stream을 설정할 이벤트 입니다.

	        //HttpServletRequest request = eventVo.getRequest(); //Request Value
	        //HttpServletResponse response = eventVo.getResponse(); //Response Value
	        //String[] aryDownloadFilePath = eventVo.getDownloadFilePath(); //DownloadFilePath Value
	        //String[] aryDownloadCustomValue = eventVo.getDownloadCustomValue();  //DownloadCustomValue

	        //java.io.InputStream[] aryDownloadFileStream; //Download File Stream 설정
        	//eventVo.setDownloadFileStream(aryDownloadFileStream); //Setting DownloadFileStream Value
		}
    });

	event.addAfterDownloadEventListener(new AfterDownloadEventListener() {
		public void afterDownloadEvent(EventVo eventVo) {
			//파일을 다운로드한 후에 발생하는 이벤트 입니다.
	        //다운로드 받을 파일에 대한 정보를 얻은 후 해당 정보에 대하여 로그출력을 하려는 경우 사용합니다.

	        //HttpServletRequest request = eventVo.getRequest(); //Request Value
	        //HttpServletResponse response = eventVo.getResponse(); //Response Value
	        String[] aryDownloadFilePath = eventVo.getDownloadFilePath(); //DownloadFilePath Value
	        String[] aryDownloadCustomValue = eventVo.getDownloadCustomValue();  //DownloadCustomValue

 			int iCustomValueLength = aryDownloadCustomValue.length;

	        //String[] decryptpath = new String[iCustomValueLength];

			for (int i = 0; i < iCustomValueLength; i++) {
				// 해당 값을 이용하여 파일의 물리적 경로 생성 (실제 물리적 파일 경로를 설정해주세요.)
				System.out.println("aryDownloadCustomValue[i] : " + aryDownloadCustomValue[i]);
				System.out.println("aryDownloadFilePath[i] : " + aryDownloadFilePath[i]);

				//decryptpath[i] = aryDownloadFilePath[i]+".tmp";

				//File file = new File(aryDownloadFilePath[i]);

				FwkFileUtil.deleteFile(aryDownloadFilePath[i]);

			}

		}
    });


	///////////////////////////////
	//         서버모듈 설정              //
	///////////////////////////////

	//실제 업로드 할 기본경로 설정 (가상경로와 물리적 경로로 설정 가능)
    //폴더명 제일 뒤에 .과 공백이 있다면 제거하시고 설정해 주세요.(운영체제에서 지원되지 않는 문자열입니다.)

	//-------------------- [설정방법1] 물리적 경로 설정 시작 --------------------//

	//해당 설정은 PhysicalPath를 RAONK Upload 제품폴더\raonkuploaddata\ 를 저장 Root 경로로 설정하는 내용입니다.
    String strPathChar = com.raonwiz.kupload.util.StaticVariables.strPathChar;
    String strRootFolder = request.getServletPath();
	strRootFolder = strRootFolder.substring(0,strRootFolder.lastIndexOf("/"));
	strRootFolder = request.getSession().getServletContext().getRealPath(strRootFolder.substring(0,strRootFolder.lastIndexOf("/")));
	//upload.settingVo.setPhysicalPath(strRootFolder + strPathChar + "raonkuploaddata");

	//임시파일 물리적 경로설정 ( setPhysicalPath에 설정된 경로 + raonktemp )
    upload.settingVo.setTempPath(strRootFolder + strPathChar + "raonkuploaddata" + strPathChar + "raonktemp");

	upload.settingVo.setPhysicalPath("D:\\edata1");


	//-------------------- [설정방법1] 물리적 경로 설정 끝 --------------------//

	//-------------------- [설정방법2] 가상경로 설정 시작 ---------------------//
    //upload.settingVo.setVirtualPath("/raonkuploaddata");

    //임시파일 물리적 경로설정 ( setVirtualPath에 설정된 경로 + raonktemp )
    //upload.settingVo.setTempPath(request.getSession().getServletContext().getRealPath("/raonkuploaddata") + java.io.File.separator + "raonktemp");
	//-------------------- [설정방법2] 가상경로 설정 끝 --------------------//

	//위에 설정된 임시파일 물리적 경로에 불필요한 파일을 삭제 처리하는 설정 (단위: 일)
    upload.settingVo.setGarbageCleanDay(2);

	///////////////////////////////
	//      SMB Protocol 설정        //
	///////////////////////////////

	//1. SMB Protocol 연결 설정
	//SMB Protocol 사용을 위한 연결설정을 해야 합니다.
	//upload.settingVo.setNtlmAuthentication("smb://SMB Domain 또는 IP", "ID", "Password");

	//2. 파일 업로드시 사용되는 Temp Path 설정
	//파일 업로드시 사용되는 Temp Path를 SMB Protocol의 경로로 설정해야 합니다.
	//upload.settingVo.setTempPath("smb://SMB Domain 또는 IP/temp ");

	//3. 파일이 업로드 될 최종 Path 설정
	//파일이 업로드 될 최종 Path를 SMB Protocol의 경로로 설정해야 합니다.
	//upload.settingVo.setPhysicalPath("smb://SMB Domain 또는 IP/savePath");

	///////////////////////////////

	//환경설정파일 물리적 폴더 (서버 환경설정 파일을 사용할 경우)
    //upload.settingVo.setConfigPhysicalPath("D:\\raonkuploaddata\\config");

	//서버 구성정보중 Context Path가 있다면, 아래와 같이 설정해주세요. (SetVirtualPath 사용시만 필요)
	//upload.settingVo.setContextPath("Context Path");

    String result = upload.Process(request, response, application, event);
    if(!result.equals("")) {
		out.print(result);
	}

 	// Servlet으로 handler 작업을 하시려면 다음과 같이 작성해 주세요.
	// Servlet으로 구성하실 때 해당 Function의 Return Type은 void로 선언 후 return 되는 값은 반드시 없어야합니다.

 	// 만일 getServletContext()가 undefined 이면 request.getSession().getServletContext(); 으로 하시면 됩니다.
	/* 	ServletContext application = getServletContext();

	String result = "";
	try {
		result = upload.Process(request, response, application, event);
	} catch (Exception e) {
		e.printStackTrace();
 	}

	if(!result.equals("")) {
		ServletOutputStream out = response.getOutputStream();
		out.print(result);
		out.close();
	} */

%>