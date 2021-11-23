package com.eunwoosoft.ipro.noti.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.main.service.IproMainLoginFormService;
import com.eunwoosoft.ipro.noti.service.IproInNotiService;

/**
 * 
 * com.eunwoosoft.ipro.noti.controller
 * IproNotiController.java
 *
 * @Author : JuYeon_Lee
 * @Date   : 2017. 6. 13.
 *
 */
@RequestMapping(value = "/noti")
@Controller
public class IproInNotiController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/noti/";
	
	@Resource(name="iproInNotiService")
	private IproInNotiService iproInNotiService;

	/**
	 * 
	 * <pre>
	 * 1.개요 : 내부 공지사항 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.noti.controller.IproNotiController.java
	 * @Method : inNoticeBoardList
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 14.
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/inNoticeBoardList")
	public String inNoticeBoardList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		/*	게시판 분류 번호 	
	 		A: 공지사항
			B: 자료실
			C: 자주하는질문 
			D: 내부 공지사항
			bid: 입찰첨부문서관리
			cont: 계약첨부문서관리
		 */
		
		parameterMap.put("P_ELPR_BSNO", "D");
		//FwkTransferObject trans = iproInNotiService.inNoticeBoardListWithPgng(parameterMap);
		//model.addAllAttributes(trans); 
		
		return parameterMap.getViewName(prefixUrl); 
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 내부 공지사항 상세 
	 * 2.처리내용 :  
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.noti.controller.IproNotiController.java
	 * @Method : inNoticeBoardDetail
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 14. 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/inNoticeBoardDetail")
	public String inNoticeBoardDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		parameterMap.put("P_NTT_SN", parameterMap.get("P_NTT_SN"));
		
		FwkTransferObject trans = iproInNotiService.inNoticeBoardDetail(parameterMap);
		
		model.addAttribute("trans", trans); 
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 내부 공지사항 등록 폼
	 * 2.처리내용 :  
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.noti.controller.IproNotiController.java
	 * @Method : inNoticeBoardRegistForm
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 14.
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/inNoticeBoardRegistForm")
	public String inNoticeBoardRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 내부 공지사항 등록 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.noti.controller.IproInNotiController.java
	 * @Method : inNoticeBoardRegist
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 19. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/inNoticeBoardRegist")
	public String inNoticeBoardRegist(final HttpServletRequest request, final Model model) throws Exception{
		
		/*	게시판 분류 번호 	
	 		A: 공지사항
			B: 자료실
			C: 자주하는질문 
			D: 내부 공지사항 
			bid: 입찰첨부문서관리
			cont: 계약첨부문서관리
		 */
		System.err.println("========= 일반 공지사항 등록 start ========");
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		List<MultipartFile> fileInfoList = multipartRequest.getFiles("P_FILE");
		 
		String url = "";
		String atchFileGroupNo =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		List<Map<String, Object>> fileList = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < fileInfoList.size(); i++) {
			MultipartFile file = fileInfoList.get(i); 
			if(file.getSize() > 0 ){
				String streFileNm = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
				String orgFileName = file.getOriginalFilename(); // 원파일명
				String baseDir = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");	// /edata1
				String dir = 	File.separator+FwkDateUtil.getCurrentDateAsString("yyyy")+
						File.separator+FwkDateUtil.getCurrentDateAsString("MM")+
						File.separator+FwkDateUtil.getCurrentDateAsString("dd")+
						File.separator; 
				String fileFullPath = "";
				String name = "";
				String ext = "";
				
				int index = orgFileName.lastIndexOf(".");
				if (index != -1) {
					name = orgFileName.substring(0, index);
					ext = orgFileName.substring(index + 1);
				}
				
				File f = new File(baseDir + dir);		
				
				boolean mkdirSuccess = true;
				if(!f.isDirectory()){
					f.setExecutable(true);
					f.setReadable(true);
					f.setWritable(true);
					mkdirSuccess = f.mkdirs();
				}
				
				fileFullPath = baseDir + dir + File.separator + streFileNm+"."+ext;
				
				if(mkdirSuccess){ 
					InputStream	stream		= null;
					stream	= file.getInputStream();
					OutputStream	bos	= new FileOutputStream( fileFullPath );
					int	byteread	= 0;
					byte[]	buffer	= new byte[8192];
					while( ( byteread = stream.read( buffer, 0, 8192 ) ) != -1 ) {
						bos.write(buffer, 0, byteread );
					}
					bos.close();
					stream.close();
					FwkParameterMap pmap = new FwkParameterMap();
					pmap.put("P_FILE_CPCTY",  file.getSize());
					pmap.put("P_STRE_FILE_NM",  streFileNm+"."+ext);
					pmap.put("P_ATCHMNFL_NM",   orgFileName);	
					pmap.put("P_ATCHMNFL_COURS_NM", baseDir + dir);
					pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
					fileList.add(pmap);
				}else{
					trans.put("resultCode", "ERR");
					trans.put("msg","시스템 오류 입니다.");
					trans.put("sMsg","폴더가 생성되지 않습니다.");
					model.addAllAttributes(trans);
					return url;
				}
			}
		}
		
		if(fileList.size() > 0){
			parameterMap.put("atchFileGroupNo", atchFileGroupNo);
			parameterMap.put("fileList", fileList);
		}
		/*	게시판 분류 번호 	
	 		A: 공지사항
			B: 자료실 
			C: 자주하는질문 
			D: 내부 공지사항 
			bid: 입찰첨부문서관리
			cont: 계약첨부문서관리
		 */
		parameterMap.put("P_ELPR_BSNO", "D");
		parameterMap.put("P_REGIST_DT", FwkDateUtil.getCurrentDateTimeAsString()); 
		
		//진행상태 추가 - NTT_STTUS_CD
		//parameterMap.put("P_NTT_STTUS_CD", "A");	//작성상태
		iproInNotiService.inNoticeBoardRegist(parameterMap, request);  
		trans.put("resultCode", "Success");
		 
		System.err.println("parameterMap === > " + parameterMap);
		model.addAllAttributes(trans);
		
		return "forward:/noti/inNoticeBoardList.do";
	}
	
	/** 
	 * 
	 * <pre>
	 * 1.개요 : 내부 공지사항 수정 폼
	 * 2.처리내용 :  
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.noti.controller.IproNotiController.java
	 * @Method : inNoticeBoardUpdtForm
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 14.
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/inNoticeBoardUpdtForm")
	public String inNoticeBoardUpdtForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproInNotiService.inNoticeBoardDetail(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	// 일반 공지사항 수정 
	@RequestMapping(value = "/inNoticeBoardUpdate")
	public String inNoticeBoardUpdate(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {			
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
			List<MultipartFile> fileInfoList = multipartRequest.getFiles("P_FILE");
			
			System.err.println("size :::: "+ fileInfoList.size());
			
			String url = "";
			//첨부파일(물품규격서)
			String atchFileGroupNo = "";
			
			if(parameterMap.get("P_ATCHMNFL_GROUP_NO") != null && !"".equals(parameterMap.get("P_ATCHMNFL_GROUP_NO"))) {
				System.err.println("P_ATCHMNFL_GROUP_NO != null");
				atchFileGroupNo = parameterMap.getString("P_ATCHMNFL_GROUP_NO");
			}else{
				System.err.println("P_ATCHMNFL_GROUP_NO == null"); 
				atchFileGroupNo =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");   // 그룹번호 생성(수의계약)
				parameterMap.put("P_ATCH_NEW", "Y");
			}
			
			List<Map<String, Object>> fileList = new ArrayList<Map<String,Object>>();
			
			
			for (int i = 0; i < fileInfoList.size(); i++) {
				MultipartFile file = fileInfoList.get(i);
				if(file.getSize() > 0 ){
					String streFileNm = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
					String orgFileName = file.getOriginalFilename();
					String baseDir = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");
					String dir = File.separator+FwkDateUtil.getCurrentDateAsString("yyyy") + File.separator+FwkDateUtil.getCurrentDateAsString("MM")+
							File.separator+FwkDateUtil.getCurrentDateAsString("dd") + 	File.separator;
					String fileFullPath = "";
					String name = "";
					String ext = "";
					
					int index = orgFileName.lastIndexOf(".");
					if (index != -1) {
						name = orgFileName.substring(0, index);
						ext = orgFileName.substring(index + 1);
					}
					
					File f = new File(baseDir + dir);		
					
					boolean mkdirSuccess = true;
					if(!f.isDirectory()){
						f.setExecutable(true);
						f.setReadable(true);
						f.setWritable(true);
						mkdirSuccess = f.mkdirs();
					}
					
					fileFullPath = baseDir + dir + File.separator + streFileNm +"." + ext;
					
					if(mkdirSuccess){
						InputStream	stream		= null;
						stream	= file.getInputStream();
						OutputStream	bos	= new FileOutputStream( fileFullPath );
						int	byteread	= 0;
						byte[]	buffer	= new byte[8192];
						while( ( byteread = stream.read( buffer, 0, 8192 ) ) != -1 ) {
							bos.write(buffer, 0, byteread );
						}
						bos.close();
						stream.close();
						FwkParameterMap pmap = new FwkParameterMap();
						pmap.put("P_FILE_CPCTY",  file.getSize());
						pmap.put("P_STRE_FILE_NM",  streFileNm + "." + ext);
						pmap.put("P_ATCHMNFL_NM",   orgFileName);	
						pmap.put("P_ATCHMNFL_COURS_NM", baseDir + dir);
						pmap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
						fileList.add(pmap);
					}else{
						trans.put("resultCode", "ERR");
						trans.put("msg", "시스템 오류 입니다.");
						trans.put("sMsg", "폴더가 생성되지 않습니다."); 
						model.addAllAttributes(trans);
						return url;
					}
				}
			} 
			
			parameterMap.put("atchFileGroupNo", atchFileGroupNo);
			
			if(fileList.size() > 0){
				parameterMap.put("fileList", fileList); 
			}
			
			System.err.println("updatePrpoThngUpdt START ================");
			trans = iproInNotiService.inNoticeBoardUpdt(parameterMap);
			model.addAttribute("trans", trans);
			
		}catch(Exception ex){
			ex.printStackTrace();
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		model.addAllAttributes(trans);
		
		return "forward:/noti/inNoticeBoardDetail.do";
		
	}
		
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 공지사항 삭제 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.noti.controller.IproInNotiController.java
	 * @Method : inNoticeBoardDelete
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 20. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/inNoticeBoardDelete")
	public String inNoticeBoardDelete(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		/*	게시판 분류 번호 	
			A: 공지사항
			B: 자료실
			C: 자주하는질문 
			D: 내부 공지사항
			bid: 입찰첨부문서관리
			cont: 계약첨부문서관리
		 */
		 
		parameterMap.put("P_ELPR_BSNO", "D");
		parameterMap.put("P_UPDT_DT", FwkDateUtil.getCurrentDateTimeAsString()); 

		FwkTransferObject trans = iproInNotiService.inNoticeBoardDelete(parameterMap);
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}
	
}
