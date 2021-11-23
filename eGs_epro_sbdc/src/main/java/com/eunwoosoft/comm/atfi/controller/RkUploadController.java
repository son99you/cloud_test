package com.eunwoosoft.comm.atfi.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.esotericsoftware.minlog.Log;
import com.eunwoosoft.frwk.fol.exception.CustomException;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.raonwiz.kupload.RAONKHandler;
import com.raonwiz.kupload.event.AfterDownloadEventListener;
import com.raonwiz.kupload.event.BeforeDownloadEventListener;
import com.raonwiz.kupload.event.EventClass;
import com.raonwiz.kupload.util.EventVo;

/**
 *
 * com.eunwoosoft.ipro.main.controller
 * IproMainLoginFormController.java
 *
 * @Author : SungYoon_Ha
 * @Date   : 2017. 5. 30.
 *
 */
@Controller
public class RkUploadController extends AbstractFwkController {
	static final String prefixRaonUrl = "/raonkupload/handler/";

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(RkUploadController.class);

	@RequestMapping(value="raonkupload/handler/raonkhandler", method = {RequestMethod.POST, RequestMethod.GET})
	public void raonKHandler(final HttpServletRequest request, final HttpServletResponse response, final Model model) throws IOException{
//		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
//		return parameterMap.getLastViewName(prefixRaonUrl);

		//request.setCharacterEncoding("UTF-8");
		

		RAONKHandler upload = new RAONKHandler();
		//디버깅
	    //2번째 파라미터의 Log Mode 설명
	    //- C : 일반로그 출력(System.out.println 로그 출력)
	    //- L : Log4j 모듈에 의한 로그 출력(/handler/JAVA 폴더의 log4j.properties 파일을 WEB-INF/classes에 적용)
		String[] pFileExtensionList = {"xls","xlsx","doc","docx","ppt","pptx","hwp","pdf","jpg","jpeg","png","gif","bmp","dwg","zip","webm","mp4","mkv","XLS","XLSX","DOC","DOCX","PPT","PPTX","HWP","PDF","JPG","JPEG","PNG","GIF","BMP","DWG","ZIP","WEBM","MP4","MKV"};

		upload.settingVo.setUploadCheckFileExtension(1, pFileExtensionList);  

		//upload.settingVo.setDebugMode(true, "C");
	    //upload.settingVo.setDebugMode(true, "L");

	    ///////////////////////////////
		//        이벤트를 등록 처리          //
		///////////////////////////////
		EventClass event = new EventClass();

/*		event.addBeforeUploadEventListener(new BeforeUploadEventListener() {
			public void beforeUploadEvent(EventVo eventVo) {
				 //등록전 이벤트
			}
	    });*/



/*		event.addAfterUploadEventListener(new AfterUploadEventListener() {
			public void afterUploadEvent(EventVo eventVo) {
				//등록후 이벤트
			}
	    });*/


		event.addBeforeDownloadEventListener(new BeforeDownloadEventListener(){
			 public void beforeDownloadEvent(EventVo eventVo){
				//파일을 다운로드하기 전에 발생하는 이벤트 입니다.
		        //파일에 대한 다운로드 경로를 변경하거나 서버에서 구해지는 Stream 다운로드로 처리할 경우 사용합니다.
		/*        String[] aryDownloadFilePath = eventVo.getDownloadFilePath(); //DownloadFilePath Value
		        String[] aryDownloadCustomValue = eventVo.getDownloadCustomValue();  //DownloadCustomValue

		        int iCustomValueLength = aryDownloadCustomValue.length;

		        String[] decryptpath = new String[iCustomValueLength];

				for (int i = 0; i < iCustomValueLength; i++) {
					// 해당 값을 이용하여 파일의 물리적 경로 생성 (실제 물리적 파일 경로를 설정해주세요.)
					File file = new File(aryDownloadFilePath[i]);
			        decryptpath[i] = aryDownloadFilePath[i]+".tmp";

			        if(aryDownloadCustomValue[i].equals("N")){
			        	try {
//							EcCipher.seedFileDecrypt(file, decryptpath[i]);
			        		EwCipher.seedFileDecrypt(file, decryptpath[i], FwkMessageUtil.getMessageAdd("ESTT.SYMM.KEY"));
						} catch (Exception e) {						//NOPMD
							Log.info(e.getMessage());
						}
			        }else{
			        	try {
							EwCipher.seedFileDecrypt(file, decryptpath[i], FwkMessageUtil.getMessageAdd("ESTT.SYMM.KEY"));
						} catch (Exception e1) {			//NOPMD
							Log.info(e1.getMessage());
						}
			        }
				}
				eventVo.setDownloadFilePath(decryptpath); //Setting DownloadFileStream Value*/
			}
	    });



/*		event.addExecuteDownloadEventListener(new ExecuteDownloadEventListener() {
			public void executeDownloadEvent(EventVo eventVo) {
				//파일 다운로드 수행시
			}
	    });*/

		event.addAfterDownloadEventListener(new AfterDownloadEventListener() {
			public void afterDownloadEvent(EventVo eventVo) {
				//파일을 다운로드한 후에 발생하는 이벤트 입니다.
		        //다운로드 받을 파일에 대한 정보를 얻은 후 해당 정보에 대하여 로그출력을 하려는 경우 사용합니다.

		        //HttpServletRequest request = eventVo.getRequest(); //Request Value
		        //HttpServletResponse response = eventVo.getResponse(); //Response Value
		       /* String[] aryDownloadFilePath = eventVo.getDownloadFilePath(); //DownloadFilePath Value
		        String[] aryDownloadCustomValue = eventVo.getDownloadCustomValue();  //DownloadCustomValue

	 			int iCustomValueLength = aryDownloadCustomValue.length;

		        //String[] decryptpath = new String[iCustomValueLength];

				for (int i = 0; i < iCustomValueLength; i++) {
					// 해당 값을 이용하여 파일의 물리적 경로 생성 (실제 물리적 파일 경로를 설정해주세요.)
					//decryptpath[i] = aryDownloadFilePath[i]+".tmp";
					//File file = new File(aryDownloadFilePath[i]);

					FwkFileUtil.deleteFile(aryDownloadFilePath[i]);

				}*/
			}
	    });

		///////////////////////////////
		//         서버모듈 설정              //
		///////////////////////////////
		//실제 업로드 할 기본경로 설정 (가상경로와 물리적 경로로 설정 가능)
	    //폴더명 제일 뒤에 .과 공백이 있다면 제거하시고 설정해 주세요.(운영체제에서 지원되지 않는 문자열입니다.)
		//-------------------- [설정방법1] 물리적 경로 설정 시작 --------------------//
		//해당 설정은 PhysicalPath를 RAONK Upload 제품폴더\raonkuploaddata\ 를 저장 Root 경로로 설정하는 내용입니다.
	    //String strPathChar = com.raonwiz.kupload.util.StaticVariables.strPathChar;
	    //String strRootFolder = request.getServletPath();
		//strRootFolder = strRootFolder.substring(0,strRootFolder.lastIndexOf('/'));
		//strRootFolder = request.getSession().getServletContext().getRealPath(strRootFolder.substring(0,strRootFolder.lastIndexOf('/')));

		//upload.settingVo.setPhysicalPath(strRootFolder + strPathChar + "raonkuploaddata");

		//임시파일 물리적 경로설정 ( setPhysicalPath에 설정된 경로 + raonktemp )
	    //upload.settingVo.setTempPath(FwkMessageUtil.getMessage("EW.SYS.FILE.DIR") + strPathChar + "raonktemp");

		upload.settingVo.setPhysicalPath(FwkMessageUtil.getMessage("EW.SYS.FILE.DIR"));
		//-------------------- [설정방법1] 물리적 경로 설정 끝 --------------------//

		//-------------------- [설정방법2] 가상경로 설정 시작 ---------------------//
	    //upload.settingVo.setVirtualPath("/raonkuploaddata");

	    //임시파일 물리적 경로설정 ( setVirtualPath에 설정된 경로 + raonktemp )
	    //upload.settingVo.setTempPath(request.getSession().getServletContext().getRealPath("/raonkuploaddata") + java.io.File.separator + "raonktemp");
		//-------------------- [설정방법2] 가상경로 설정 끝 --------------------//

		//위에 설정된 임시파일 물리적 경로에 불필요한 파일을 삭제 처리하는 설정 (단위: 일)
	    upload.settingVo.setGarbageCleanDay(1);

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

//	    String result = upload.Process(request, response, application, event);
//	    if(!result.equals("")) {
//			out.print(result);
//		}

	 	// Servlet으로 handler 작업을 하시려면 다음과 같이 작성해 주세요.
		// Servlet으로 구성하실 때 해당 Function의 Return Type은 void로 선언 후 return 되는 값은 반드시 없어야합니다.
	 	// 만일 getServletContext()가 undefined 이면 request.getSession().getServletContext(); 으로 하시면 됩니다.
		ServletContext application = request.getSession().getServletContext();

		String result = "";
		try {
			result = upload.Process(request, response, application, event);
		} catch (Exception e) {		//NOPMD  라이브러리에서 처리하는 내용
			Log.info(e.getMessage());
	 	}
		if(!result.equals("")) {
			try(ServletOutputStream out = response.getOutputStream()){
				out.print(result);
			}
			//out.close();
		}
	}

	@RequestMapping(value="raonkupload/handler/raonkviewer", method = {RequestMethod.POST, RequestMethod.GET})
	public void raonKViewer(final HttpServletRequest request, final HttpServletResponse response, final Model model) throws IOException{
//		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
//		System.out.println("parameterMap9 : " + parameterMap);
//		System.err.println("parameterMap10 : " + parameterMap);
//		return parameterMap.getLastViewName(prefixRaonUrl);

		request.setCharacterEncoding("UTF-8");

		RAONKHandler raonk = new RAONKHandler();

 		String result = "";
 		try {

 			result = raonk.Viewer(request, response);

 		} catch (Exception e) {		//NOPMD  라이브러리에서 처리하는 내용
 			Log.info(e.getMessage());
 	 	}

 		if(!result.equals("")) {
 			try(ServletOutputStream out = response.getOutputStream()){
 				out.print(result);
 			}catch(CustomException ex){
 				Log.info(ex.getMessage());
 			}
 		}
	}
}
