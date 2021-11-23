package com.eunwoosoft.ipro.comm.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eunwoosoft.comm.code.service.ComCmcdDetailCdInqireService;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.comm.util.ComAtmaAtchFileUtil;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.frwk.prl.request.FwkParameterMapInterceptor;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;

import oracle.xml.parser.v2.DOMParser;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

/**
 * 팝업
 * com.eunwoosoft.ipro.comm.controller
 * IproCommDefaultController.java
 *
 * @Author : SungYoon_Ha
 * @Date   : 2017. 6. 15.
 *
 */
@RequestMapping(value = "/comm")
@Controller
public class IproCommDefaultController extends AbstractFwkController {

	@Resource(name="iproCommDefaultService")
	IproCommDefaultService iproCommDefaultService;

	@Resource(name="comCmcdDetailCdInqireService")
	ComCmcdDetailCdInqireService comCmcdDetailCdInqireService;

	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;	//NOPMD

	private static final Logger LOG = LoggerFactory.getLogger(FwkParameterMapInterceptor.class);
	private static DOMParser	parser		= new DOMParser();		//오라클 파서

	String prefixUrl = "/ipro/views/comm/";
	/**
	 * <pre>
	 * 1. 개요 : 파일 다운로드
	 * 2. 처리내용 :
	 *      - 첨부파일을 다운로드 한다.
	 * 3. 입출력 :
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 *
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : download
	 * @date : 2015. 07. 03.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 03.		은우소프트 하성윤			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param request
	 * @param model
	 * @return JSP 화면명
	 * @throws Exception
	 */
	@RequestMapping(value = "/download")
	public String download(final HttpServletRequest request, HttpServletResponse response) throws Exception {

		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		// 공통 파일 정보 조회
		FwkTransferObject trans = iproCommDefaultService.cmmnFileInfoInqire(parameterMap);
		FwkDataEntity fileInfo = (FwkDataEntity) trans.get(IproCommDefaultService.CMMN_FILE_INFO);

		String root = "";
		String fullPath = root + fileInfo.getString("FILE_LCTN") + File.separator + fileInfo.getString("SV_FILE_NM");
		String fileName = fileInfo.getString("SYS_FILE_NM");

		// 윈도우 파일명으로 사용할수 없는 문자 제거
		if(fileName != null && !"".equals(fileName)){
			fileName = CmmnUtil.exceptCharReplace(fileName);
		}

		String agentType = request.getHeader("Accept-Encoding");

        boolean flag = false;
        if ( agentType != null && agentType.indexOf("gzip") >= 0 ) {
            flag = true;
        }
        OutputStream	out	= null;
        InputStream	in	= null;

        //파일 인코딩
        if(fileName != null){
	        if(agentType != null){
	        	if( agentType.contains("Firefox") || agentType.contains("Chrome")){
	        		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	        	} else {
	        		fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	        	}
	        }else{
	    		fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	        }
        }
		if (flag) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Encoding", "gzip");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
            out	= new GZIPOutputStream( response.getOutputStream() );
        } else {
        	response.setContentType("application/octet-stream");
        	response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
			out	= response.getOutputStream();
        }
		try{
			in	= new BufferedInputStream( new FileInputStream( fullPath ) );
			FileCopyUtils.copy(in, out );
		}catch(Exception e){
		//	LOG.debug("파일 다운로드 예외 발생");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}finally{
			if(in != null){
				in.close();
			}
			if(out != null){
				out.close();
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/download_tmp")
	public String download_tmp(final HttpServletRequest request, HttpServletResponse response) throws Exception {

		String referer = request.getHeader("REFERER");//REFERER 가져오기
		if( referer != null && referer.length() > 0) //REFERER 체크(브라우저에서 직접 호출 방지용)
		{
			String urf = request.getParameter("urf");

			BufferedInputStream fin = null;
			BufferedOutputStream outs = null;

			//String rFile = "D:/edata1/2020/11/02/cont/" + urf + ".xml";//xml Path 설정
			String rFile = urf + ".xml";//xml Path 설정
			//예) rFile = "/nas/upload/2020/11/02/20201102.xml";

			File file = new File(rFile);
			
			//파일 존재 여부
			if(file.exists()){	
				
				try{
					byte b[] = new byte[(int)file.length()];
					fin = new BufferedInputStream(new FileInputStream(file));
					outs = new BufferedOutputStream(response.getOutputStream());
					int read = 0;
					outs.write(b,0,0);
					while ((read = fin.read(b)) != -1) {
						outs.write(b,0,read);
					}
				}catch (Exception e) {
					System.out.println(e.toString());
				}
				if(outs != null){
					outs.flush();
					outs.close();
				}
				if(fin != null){
					fin.close();
				}
			}
			else{
				//System.out.println("해당 파일이 존재하지 않습니다.");
				System.out.print("ER0612#Urf file not found");
			}
		}else{
			System.out.print( "<Error>잘못된 경로로 호출되었습니다.</Error>");
		}
		return null;
	}
	
	@RequestMapping(value = "/downloadZip")
	public String downloadZip(final HttpServletRequest request, HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		// 공통 파일 정보 조회
		FwkTransferObject trans = iproCommDefaultService.downloadZip(parameterMap);
		String fullPath = (String)trans.get(IproCommDefaultService.CMMN_FILE_INFO);
		FwkDataEntity vendDetail = (FwkDataEntity)trans.get("vendDetail");
		String fileName = parameterMap.getString("P_ZIP_FILE_NM")+vendDetail.getString("VEND_NM")+".zip";

		// 윈도우 파일명으로 사용할수 없는 문자 제거
		if(fileName != null && !"".equals(fileName)){
			fileName = CmmnUtil.exceptCharReplace(fileName);
		}

		String agentType = request.getHeader("Accept-Encoding");

        boolean flag = false;
        if ( agentType != null && agentType.indexOf("gzip") >= 0 ) {
            flag = true;
        }
        OutputStream	out	= null;
        InputStream	in	= null;

        //파일 인코딩
        if(agentType != null){
        	if( agentType.contains("Firefox") || agentType.contains("Chrome")){
        		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        	} else {
        		fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
        	}
        }else{
    		fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
        }
		if (flag) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Encoding", "gzip");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
            out	= new GZIPOutputStream( response.getOutputStream() );
        } else {
        	response.setContentType("application/octet-stream");
        	response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
			out	= response.getOutputStream();
        }
		try{
			in	= new BufferedInputStream( new FileInputStream( fullPath ) );
			FileCopyUtils.copy(in, out );
		}catch(Exception e){
		//	LOG.debug("파일 다운로드 예외 발생");
			System.err.println(e.getMessage());
		}finally{
			File zipFile = new File(fullPath);
			zipFile.delete();
			if(in != null){
				in.close();
			}
			if(out != null){
				out.close();
			}
		}
		return null;
	}

	/**
	 * <pre>
	 * 1. 개요 : 공통코드 조회
	 * 2. 처리내용 :
	 * 3. 입출력 :
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 *
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : comCmcdDetailCdInqire
	 * @date : 2016. 11. 18.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 11. 18.		은우소프트 하성윤			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param request
	 * @param model
	 * @return JSP 화면명
	 * @throws Exception
	 */
	@RequestMapping("/comCmcdDetailCdInqire")
	public String comCmcdDetailCdInqire(final HttpServletRequest request, final Model model) {

		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = comCmcdDetailCdInqireService.cdValueListInqireByCdId(parameterMap);
		List<FwkDataEntity> cdValueList = (List<FwkDataEntity>) trans.get(comCmcdDetailCdInqireService.CD_VALUE_LIST);

		model.addAttribute("cdValueList", cdValueList);
		return JSON_VIEW;
	}



	@RequestMapping(value = "/comCmcdScsCdInqire")
	public String comCmcdScsCdInqire(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		inputValueValidCeck(parameterMap);

		model.addAllAttributes(iproCommDefaultService.comCmcdScsCdInqireByCdId(parameterMap));
		return JSON_VIEW;
	}


	/**
	 * <pre>
	 * 1. 개요 : 입력값유효성체크
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : inputValueValidCeck
	 * @date : 2014. 12. 10.
	 * @author :
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 10.						최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 */
	private void inputValueValidCeck(final FwkParameterMap parameterMap) {
	    List<String> ceckKeys = Arrays.asList("P_CD_ID");
	    parameterMap.essntlIputValueCeck(ceckKeys);
	}

	/**
	 * <pre>
	 * 1. 개요 : 사용자 조회 - JSON_VIEW
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : entrpsInfoJson
	 * @date : 2018. 11. 28.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 11. 28.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 * @param request
	 * @param model
	 * @return JSP 화면명
	 * @throws Exception
	 */
	@RequestMapping("/entrpsInfoJson")
	public String entrpsInfoJson(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproCommDefaultService.entrpsInqireDetail(parameterMap);
		model.addAllAttributes(trans);

		return JSON_VIEW;
	}

	/**
	 *
	 * <pre>
	 * 1.개요 :
	 * 2.처리내용 : 덱스트 fileUpload
	 * </pre>
	 * @Method : fileInfoRegist
	 * @author : FIC04266
	 * @date : 2018. 11. 22.
	 * @history
	 * =============================================
	 *  date           name           description
	 * ---------------------------------------------
	 * 2018. 11. 22.        FIC04266          최초 생성
	 * =============================================
	 */
	@RequestMapping(value="/fileInfoRegist", method=RequestMethod.POST)
	public String fileInfoRegist(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = comAtmaAtchFileService.fileInfoRegist(parameterMap, request);
		if(trans.get("fileUploadCode").equals("fileFail")){
			throw new Exception();
		}else{
			trans.put("resultCode", "Success");

			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}
		return JSON_VIEW;
	}
	
	@RequestMapping(value="/fileInfoEtcRegist", method=RequestMethod.POST)
	public String fileInfoEtcRegist(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = comAtmaAtchFileService.fileInfoEtcRegist(parameterMap, request);
		if(trans.get("fileUploadCode").equals("fileFail")){
			throw new Exception();
		}else{
			trans.put("resultCode", "Success");

			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}
		return JSON_VIEW;
	}

	
	//라온케이
	//파일 복사 및 추가등록
	@RequestMapping(value="/fileInfoEtcCopyRegist", method=RequestMethod.POST)
	public String fileInfoEtcCopyRegist(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = comAtmaAtchFileService.fileInfoEtcCopyRegist(parameterMap, request);
		if(trans.get("fileUploadCode").equals("fileFail")){
			throw new Exception();
		}else{
			trans.put("resultCode", "Success");
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}
		return JSON_VIEW;
	}	

	@RequestMapping(value = "/fileContInfoRegist", method=RequestMethod.POST)
	public String fileContInfoRegist(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		String fileGroupFlag = parameterMap.getString("P_FILE_GROUP_FLAG");
		String atchFileGroupNo = "";
		/*if(parameterMap.get("P_FILE_GRP_NO") != null && !parameterMap.get("P_FILE_GRP_NO").equals("")) {
			atchFileGroupNo = parameterMap.get("P_FILE_GRP_NO").toString();
		}else if(parameterMap.get("P_VEND_FILE_GRP_NO") != null && !parameterMap.get("P_VEND_FILE_GRP_NO").equals("")){
			atchFileGroupNo = parameterMap.get("P_VEND_FILE_GRP_NO").toString();
		}*/
		parameterMap.put("fileGroupFlag", fileGroupFlag);
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("newFileList", ComAtmaAtchFileUtil.getAtchFileInfo(parameterMap.getListOfMap("ATCH_FILE")));
		parameterMap.put("deleteFileList", ComAtmaAtchFileUtil.getAtchFileInfo(parameterMap.getListOfMap("DELETE_ATCH_FILE")));
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = comAtmaAtchFileService.fileContInfoRegist(parameterMap);
		
		
		//trans.put("atchFileGroupNo", atchFileGroupNo);
		model.addAllAttributes(trans);

		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/fileContInfoChngRegist", method=RequestMethod.POST)
	public String fileContInfoChngRegist(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		String fileGroupFlag = parameterMap.getString("P_FILE_GROUP_FLAG");
		String atchFileGroupNo = "";
		/*if(parameterMap.get("P_FILE_GRP_NO") != null && !parameterMap.get("P_FILE_GRP_NO").equals("")) {
			atchFileGroupNo = parameterMap.get("P_FILE_GRP_NO").toString();
		}else if(parameterMap.get("P_VEND_FILE_GRP_NO") != null && !parameterMap.get("P_VEND_FILE_GRP_NO").equals("")){
			atchFileGroupNo = parameterMap.get("P_VEND_FILE_GRP_NO").toString();
		}*/
		parameterMap.put("fileGroupFlag", fileGroupFlag);
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("newFileList", ComAtmaAtchFileUtil.getAtchFileInfo(parameterMap.getListOfMap("ATCH_FILE")));
		parameterMap.put("deleteFileList", ComAtmaAtchFileUtil.getAtchFileInfo(parameterMap.getListOfMap("DELETE_ATCH_FILE")));
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = comAtmaAtchFileService.fileContInfoChngRegist(parameterMap);
		
		
		//trans.put("atchFileGroupNo", atchFileGroupNo);
		model.addAllAttributes(trans);

		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/fileContInfoUpdt", method=RequestMethod.POST)
	public String fileContInfoUpdt(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		String fileGroupFlag = parameterMap.getString("P_FILE_GROUP_FLAG");
		String atchFileGroupNo = "";
		/*if(parameterMap.get("P_FILE_GRP_NO") != null && !parameterMap.get("P_FILE_GRP_NO").equals("")) {
			atchFileGroupNo = parameterMap.get("P_FILE_GRP_NO").toString();
		}else if(parameterMap.get("P_VEND_FILE_GRP_NO") != null && !parameterMap.get("P_VEND_FILE_GRP_NO").equals("")){
			atchFileGroupNo = parameterMap.get("P_VEND_FILE_GRP_NO").toString();
		}*/
		parameterMap.put("fileGroupFlag", fileGroupFlag);
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("newFileList", ComAtmaAtchFileUtil.getAtchFileInfo(parameterMap.getListOfMap("ATCH_FILE")));
		parameterMap.put("deleteFileList", ComAtmaAtchFileUtil.getAtchFileInfo(parameterMap.getListOfMap("DELETE_ATCH_FILE")));
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = comAtmaAtchFileService.fileContInfoUpdt(parameterMap);
		
		
		//trans.put("atchFileGroupNo", atchFileGroupNo);
		model.addAllAttributes(trans);

		return JSON_VIEW;
	}

	/**
	 *
	 * <pre>
	 * 1.개요 :
	 * 2.처리내용 : 덱스트 fileView
	 * </pre>
	 * @Method : fileListInqireByFileGrpNo
	 * @author : FIC04266
	 * @date : 2018. 11. 21.
	 * @history
	 * =============================================
	 *  date           name           description
	 * ---------------------------------------------
	 * 2018. 11. 21.        FIC04266          최초 생성
	 * =============================================
	 */
	@RequestMapping(value="/fileListInqireByFileGrpNo", method=RequestMethod.POST)
	public String fileListInqireByFileGrpNo(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = comAtmaAtchFileService.fileListInqireByFileGrpNo(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return JSON_VIEW;
	}
	
	@RequestMapping(value="/fileEtcListByFileGrpNo", method=RequestMethod.POST)
	public String fileEtcListByFileGrpNo(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = comAtmaAtchFileService.fileEtcListByFileGrpNo(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return JSON_VIEW;
	}

	/**
	 *
	 * <pre>
	 * 1.개요 : 계약자 계약상세화면에서 업체첨부파일은 계약번호화 차수를 이용하여 첨부파일 목록을 조회
	 * 2.처리내용 :
	 * </pre>
	 * @Method : fileListInqireByFileGrpNoAll
	 * @author : sjKim
	 * @date : 2019. 2. 25.
	 * @history
	 * =============================================
	 *  date           name           description
	 * ---------------------------------------------
	 * 2019. 2. 25.        sjKim          최초 생성
	 * =============================================
	 */
	@RequestMapping(value="/fileListInqireByFileGrpNoAll", method=RequestMethod.POST)
	public String fileListInqireByFileGrpNoAll(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = comAtmaAtchFileService.fileListInqireByFileGrpNoAll(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return JSON_VIEW;
	}

	@RequestMapping(value="/fileInfoUpt", method=RequestMethod.POST)
	public String fileInfoUpt(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = comAtmaAtchFileService.fileInfoUpt(parameterMap, request);
		if(trans.get("fileUploadCode").equals("fileFail")){
			throw new Exception();
		}else{
			trans.put("resultCode", "Success");

			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}
		return JSON_VIEW;
	}
	
	@RequestMapping(value="/fileInfoEtcUpt", method=RequestMethod.POST)
	public String fileInfoEtcUpt(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = comAtmaAtchFileService.fileInfoEtcUpt(parameterMap, request);
		if(trans.get("fileUploadCode").equals("fileFail")){
			throw new Exception();
		}else{
			trans.put("resultCode", "Success");

			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}
		return JSON_VIEW;
	}
	
	//사용자메뉴얼
	@RequestMapping(value = "/userManual", method=RequestMethod.POST)
	public void userManual(final HttpServletRequest request, final HttpServletResponse response) throws Exception {		
		
		String root = "";
		//String fullPath = root + fileInfo.getString("FILE_LCTN") + File.separator + fileInfo.getString("SV_FILE_NM");
		String fullPath = FwkMessageUtil.getMessage("IPRO.SERVER.WEBAPP.PATH") + File.separator +"menual" + File.separator + "manual_ipro.pdf";
		String fileName = "비대면평가시스템 사용자 매뉴얼.pdf";

		// 윈도우 파일명으로 사용할수 없는 문자 제거
		if(fileName != null && !"".equals(fileName)){
			fileName = CmmnUtil.exceptCharReplace(fileName);
		}

		String agentType = request.getHeader("Accept-Encoding");

        boolean flag = false;
        if ( agentType != null && agentType.indexOf("gzip") >= 0 ) {
            flag = true;
        }
        OutputStream	out	= null;
        InputStream	in	= null;

        //파일 인코딩
        if(fileName != null){
	        if(agentType != null){
	        	if( agentType.contains("Firefox") || agentType.contains("Chrome")){
	        		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	        	} else {
	        		fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	        	}
	        }else{
	    		fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	        }
        }
		/*if (flag) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Encoding", "gzip");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
            out	= new GZIPOutputStream( response.getOutputStream() );
        } else {*/
        	response.setContentType("application/octet-stream");
        	response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
			out	= response.getOutputStream();
        /*}*/
		try{
			in	= new BufferedInputStream( new FileInputStream( fullPath ) );
			FileCopyUtils.copy(in, out );
		}catch(Exception e){
		//	LOG.debug("파일 다운로드 예외 발생");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}finally{
			if(in != null){
				in.close();
			}
			if(out != null){
				out.close();
			}
		}
		
	}
	
	
	//사용자메뉴얼
	@RequestMapping(value = "/sampleDownload", method=RequestMethod.POST)
	public void sampleDownload(final HttpServletRequest request, final HttpServletResponse response) throws Exception {		
		
		String root = "";
		//String fullPath = root + fileInfo.getString("FILE_LCTN") + File.separator + fileInfo.getString("SV_FILE_NM");
		String fullPath = FwkMessageUtil.getMessage("IPRO.SERVER.WEBAPP.PATH") + File.separator +"sample" + File.separator + "grntSampleForm.hwp";
		String fileName = "계약보증금 납부확인서(서식).hwp";

		// 윈도우 파일명으로 사용할수 없는 문자 제거
		if(fileName != null && !"".equals(fileName)){
			fileName = CmmnUtil.exceptCharReplace(fileName);
		}

		String agentType = request.getHeader("Accept-Encoding");

        boolean flag = false;
        if ( agentType != null && agentType.indexOf("gzip") >= 0 ) {
            flag = true;
        }
        OutputStream	out	= null;
        InputStream	in	= null;

        //파일 인코딩
        if(fileName != null){
	        if(agentType != null){
	        	if( agentType.contains("Firefox") || agentType.contains("Chrome")){
	        		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	        	} else {
	        		fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	        	}
	        }else{
	    		fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	        }
        }
		/*if (flag) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Encoding", "gzip");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
            out	= new GZIPOutputStream( response.getOutputStream() );
        } else {*/
        	response.setContentType("application/octet-stream");
        	response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
			out	= response.getOutputStream();
        /*}*/
		try{
			in	= new BufferedInputStream( new FileInputStream( fullPath ) );
			FileCopyUtils.copy(in, out );
		}catch(Exception e){
		//	LOG.debug("파일 다운로드 예외 발생");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}finally{
			if(in != null){
				in.close();
			}
			if(out != null){
				out.close();
			}
		}
		
	}
	
	
	// 아임스타즈 연계 첨부파일 다운로드
	@RequestMapping(value = "/imstarsDownload")
	public String imstarsDownload(final HttpServletRequest request, HttpServletResponse response) throws Exception {

		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		// 공통 파일 정보 조회
		FwkTransferObject trans = iproCommDefaultService.cmmnImstarsFileInfo(parameterMap);   // SPORT_REQST_FILEDETAILINFO
		FwkDataEntity fileInfo = (FwkDataEntity) trans.get(IproCommDefaultService.CMMN_IMSTARS_FILE_INFO);

		String root = FwkMessageUtil.getMessage("CMTM.ORIGFILE.PATH");
		String fullPath = root + fileInfo.getString("FILE_STRE_COURS");
		//String fileName = fileInfo.getString("STRE_FILE_NM");
		String fileName = fileInfo.getString("WON_FILE_NM");
		
		// 윈도우 파일명으로 사용할수 없는 문자 제거
		if(fileName != null && !"".equals(fileName)){
			fileName = CmmnUtil.exceptCharReplace(fileName);
		}

		String agentType = request.getHeader("Accept-Encoding");

        boolean flag = false;
        if ( agentType != null && agentType.indexOf("gzip") >= 0 ) {
            flag = true;
        }
        OutputStream	out	= null;
        InputStream	in	= null;

        //파일 인코딩
        if(fileName != null){
	        if(agentType != null){
	        	if( agentType.contains("Firefox") || agentType.contains("Chrome")){
	        		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
	        	} else {
	        		fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	        	}
	        }else{
	    		fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
	        }
        }
		if (flag) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Encoding", "gzip");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
            out	= new GZIPOutputStream( response.getOutputStream() );
        } else {
        	response.setContentType("application/octet-stream");
        	response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
			out	= response.getOutputStream();
        }
		try{
			in	= new BufferedInputStream( new FileInputStream( fullPath ) );
			FileCopyUtils.copy(in, out );
		}catch(Exception e){
		//	LOG.debug("파일 다운로드 예외 발생");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}finally{
			if(in != null){
				in.close();
			}
			if(out != null){
				out.close();
			}
		}
		return null;
	}
	
	
	// 아임스타즈 연계 첨부파일 다운로드
	@RequestMapping(value = "/imstarsDetailDownload")
	public String imstarsDetailDownload(final HttpServletRequest request, HttpServletResponse response) throws Exception {

		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		// 공통 파일 정보 조회
		FwkTransferObject trans = iproCommDefaultService.cmmnImstarsDetailFileInfo(parameterMap);   // CMMN_FILEDETAILINFO
		FwkDataEntity fileInfo = (FwkDataEntity) trans.get(IproCommDefaultService.CMMN_IMSTARS_DETAIL_FILE_INFO);
		
		if(fileInfo != null){
			String root = FwkMessageUtil.getMessage("CMTM.ORIGFILE.PATH");
			String fullPath = root + fileInfo.getString("FILE_STRE_COURS");
			//String fileName = fileInfo.getString("STRE_FILE_NM");
			String fileName = fileInfo.getString("WON_FILE_NM");
			
			// 윈도우 파일명으로 사용할수 없는 문자 제거
			if(fileName != null && !"".equals(fileName)){
				fileName = CmmnUtil.exceptCharReplace(fileName);
			}
			
			String agentType = request.getHeader("Accept-Encoding");

	        boolean flag = false;
	        if ( agentType != null && agentType.indexOf("gzip") >= 0 ) {
	            flag = true;
	        }
	        OutputStream	out	= null;
	        InputStream	in	= null;

	        //파일 인코딩
	        if(fileName != null){
		        if(agentType != null){
		        	if( agentType.contains("Firefox") || agentType.contains("Chrome")){
		        		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		        	} else {
		        		fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
		        	}
		        }else{
		    		fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
		        }
	        }
			if (flag) {
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Encoding", "gzip");
				response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
	            out	= new GZIPOutputStream( response.getOutputStream() );
	        } else {
	        	response.setContentType("application/octet-stream");
	        	response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
				out	= response.getOutputStream();
	        }
			
			try{
				in	= new BufferedInputStream( new FileInputStream( fullPath ) );
				FileCopyUtils.copy(in, out );
			}catch(Exception e){
			//	LOG.debug("파일 다운로드 예외 발생");
				e.printStackTrace();
				System.err.println(e.getMessage());
			}finally{
				if(in != null){
					in.close();
				}
				if(out != null){
					out.close();
				}
			}
			
		}
		
		return null;
	}

	
	// 기존 POOL 첨부파일 다운로드
	@RequestMapping(value = "/poolDownload")
	public String poolDownload(final HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		// 공통 파일 정보 조회
		/*FwkTransferObject trans = iproCommDefaultService.cmmnImstarsDetailFileInfo(parameterMap);   // CMMN_FILEDETAILINFO
		FwkDataEntity fileInfo = (FwkDataEntity) trans.get(IproCommDefaultService.CMMN_IMSTARS_DETAIL_FILE_INFO);*/
		
		String root = FwkMessageUtil.getMessage("CMTM.ORIGFILE.PATH");
		String fullPath = root + parameterMap.getString("P_FILE_STRE_COURS");
		//String fileName = fileInfo.getString("STRE_FILE_NM");
		String fileName = parameterMap.getString("P_WON_FILE_NM");
		
		// 윈도우 파일명으로 사용할수 없는 문자 제거
		if(fileName != null && !"".equals(fileName)){
			fileName = CmmnUtil.exceptCharReplace(fileName);
		}
		
		String agentType = request.getHeader("Accept-Encoding");
		
		boolean flag = false;
		if ( agentType != null && agentType.indexOf("gzip") >= 0 ) {
			flag = true;
		}
		OutputStream	out	= null;
		InputStream	in	= null;
		
		//파일 인코딩
		if(fileName != null){
			if(agentType != null){
				if( agentType.contains("Firefox") || agentType.contains("Chrome")){
					fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				} else {
					fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
				}
			}else{
				fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
			}
		}
		if (flag) {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Encoding", "gzip");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
			out	= new GZIPOutputStream( response.getOutputStream() );
		} else {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ";" );
			out	= response.getOutputStream();
		}
		
		try{
			in	= new BufferedInputStream( new FileInputStream( fullPath ) );
			FileCopyUtils.copy(in, out );
		}catch(Exception e){
			//	LOG.debug("파일 다운로드 예외 발생");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}finally{
			if(in != null){
				in.close();
			}
			if(out != null){
				out.close();
			}
		}
		
		return null;
	}
		
}
