package com.eunwoosoft.opro.comm.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.zip.GZIPOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eunwoosoft.comm.code.service.ComCmcdDetailCdInqireService;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.frwk.prl.request.FwkParameterMapInterceptor;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;

/**
 * 팝업
 * com.eunwoosoft.ipro.comm.controller
 * oproCommDefaultController.java
 *
 * @Author : SungYoon_Ha
 * @Date   : 2017. 6. 15.
 *
 */
@RequestMapping(value = "/opro/comm") 
@Controller
public class oproCommDefaultController extends AbstractFwkController {
	
	@Resource(name="iproCommDefaultService")
	IproCommDefaultService iproCommDefaultService;
	
	@Resource(name="comCmcdDetailCdInqireService")
	ComCmcdDetailCdInqireService comCmcdDetailCdInqireService;

	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;	//NOPMD
	
	private static final Logger LOG = LoggerFactory.getLogger(FwkParameterMapInterceptor.class);

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
		//FwkDataEntity fileInfo = (FwkDataEntity) trans.get("cmmnFileInfo");
		
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
	
	@RequestMapping(value = "/downloadZip")
	public String downloadZip(final HttpServletRequest request, HttpServletResponse response) throws Exception {				
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		// 공통 파일 정보 조회
		FwkTransferObject trans = iproCommDefaultService.downloadZip(parameterMap);
		String fullPath = (String)trans.get("cmmnFileInfo");
		String fileName = parameterMap.getString("P_ZIP_FILE_NM");

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
	
	//사용자메뉴얼
	@RequestMapping(value = "/userMenual")
	public void userMenual(final HttpServletRequest request, final HttpServletResponse response) throws Exception {		
		
		String root = "";
		//String fullPath = root + fileInfo.getString("FILE_LCTN") + File.separator + fileInfo.getString("SV_FILE_NM");
		String fullPath = FwkMessageUtil.getMessage("IPRO.SERVER.WEBAPP.PATH") + File.separator +"menual" + File.separator + "manual_opro.pdf";
		String fileName = "비대면평가시스템 외부평가위원 매뉴얼.pdf";

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
}