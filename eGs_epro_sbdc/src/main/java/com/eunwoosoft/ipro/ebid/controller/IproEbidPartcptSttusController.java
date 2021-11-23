package com.eunwoosoft.ipro.ebid.controller; 

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.ParameterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.frwk.prl.request.FwkParameterMapInterceptor;
import com.eunwoosoft.ipro.ebid.service.IproEbidPartcptSttusService;


/**
 * 전자입찰 > 입찰참가현황 Controller 
 * <pre>
 * com.eunwoosoft.ipro.ebid.controller 
 *    |_ IproEbidPartcptSttusController
 * 
 * </pre>
 * @date : 2015. 02. 23. 오후 5:00:31
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
@Controller
@RequestMapping(value = "/ebid")
public class IproEbidPartcptSttusController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";
	String prefixPopUrl = "/ipro/views/ebid/popup/";
	
	@Resource(name="iproEbidPartcptSttusService")
	private IproEbidPartcptSttusService iproEbidPartcptSttusService;
	
	private static final Logger LOG = LoggerFactory.getLogger(FwkParameterMapInterceptor.class);

	/**
	 * <pre>
	 * 1. 개요 : 입찰참가현황 목록 
	 * 2. 처리내용 : 
     *      - 입찰참가현황 목록조회 서비스를 호출한다.
     *      - 입찰참가현황 목록조회 결과를 Model에 담는다.
     *      - View(JSP명) 를 반환한다.(elbi/iepElbiBidDcPeoList.jsp)
	 * </pre>
	 * @Method Name : bidPartcptSttusList
	 * @date : 2015. 02. 13.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 13.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/iepElbiBidDcPeoList
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bidPartcptSttusList")
	public String bidPartcptSttusList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPartcptSttusService.bidPartcptSttusListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
/*	*//**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가현황 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptSttusDetail
	 * @date : 2015. 2. 16.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 16.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
 * @throws Exception 
	 */
	
	
	@RequestMapping(value="/bidPartcptSttusDetail")
	public String bidPartcptSttusDetail(final HttpServletRequest request, final Model model) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPartcptSttusService.bidPartcptSttusDetailInqire(parameterMap);
		trans.put("toDayTime", FwkDateUtil.getCurrentDateTimeAsString());
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰포기신청서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidAbandnReqstdocInqire
	 * @date : 2015. 2. 26.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 26.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	
	
	@RequestMapping(value="/popup/bidAbandnReqstdocInqire")
	public String bidAbandnReqstdocInqire(final HttpServletRequest request, final Model model) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPartcptSttusService.bidAbandnReqstdocInqire(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixPopUrl);
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가신청서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptReqstdocInqire
	 * @date : 2015. 2. 26.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 26.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws SDBException 
	 */
	
	@RequestMapping(value="/popup/bidPartcptReqstdocInqire")
	public String bidPartcptReqstdocInqire(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPartcptSttusService.bidPartcptReqstdocInqire(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 제안서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptReqstdocInqire
	 * @date : 2019. 02. 21.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 21.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 * @param model
	 * @return
	 * @throws SDBException 
	 */
	@RequestMapping(value="/popup/bidPartcptPrprcDocInqire")
	public String bidPartcptPrprcDocInqire(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPartcptSttusService.bidPartcptReqstdocInqire(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixPopUrl);
	}	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 청렴이행각서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : intgtyFlflMmrdInqire
	 * @date : 2015. 2. 27.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 27.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
		
	@RequestMapping(value="/popup/intgtyFlflMmrdInqire")
	public String intgtyFlflMmrdInqire(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPartcptSttusService.intgtyFlflMmrdInqire(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  입찰보증정보 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidAssrncInfoInqire
	 * @date : 2015. 3. 26.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 26.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/bidAssrncInfoInqire")
	public String bidAssrncInfoInqire(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPartcptSttusService.bidAssrncInfoInqire(parameterMap);
		model.addAllAttributes(trans);			
		
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰보증정보 상세 팝업 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.controller.IproEbidPartcptSttusController.java
	 * @Method : bidAssrncInfoDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 7. 11. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/bidAssrncInfoDetail")
	public String bidAssrncInfoDetail(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPartcptSttusService.bidAssrncInfoInqire(parameterMap);
		model.addAllAttributes(trans);			
		
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰보증정보 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidAssrncInfoRegist
	 * @date : 2015. 3. 30.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 30.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	
	@RequestMapping(value="/bidAssrncInfoRegist")
	public String bidAssrncInfoRegist(final HttpServletRequest request, final Model model) throws IOException {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		List<MultipartFile> mFileList = multipartRequest.getFiles("P_FILE");
		if(mFileList != null && mFileList.size() != 0){
			
			String dir = File.separator+FwkDateUtil.getCurrentDateAsString("yyyy")+
					File.separator+FwkDateUtil.getCurrentDateAsString("MM")+
					File.separator+FwkDateUtil.getCurrentDateAsString("dd")+
					File.separator+"bid";
			FwkParameterMap[] fileArray =  new FwkParameterMap[mFileList.size()];
			
			
			for(int i = 0 ; i < mFileList.size() ; i++){
				MultipartFile mFile = mFileList.get(i);
				if(mFile != null){
					String atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
					String orgFileName = mFile.getOriginalFilename(); // 원파일명
					String cmprFileName = mFile.getOriginalFilename().toLowerCase(); // 비교파일명
		        
			        if(cmprFileName.endsWith(".zip") || cmprFileName.endsWith(".pdf") || cmprFileName.endsWith(".hwp") || cmprFileName.endsWith(".txt")
			        		 || cmprFileName.endsWith(".doc") || cmprFileName.endsWith(".docx") || cmprFileName.endsWith(".xls") || cmprFileName.endsWith(".xlsx")
			        		 || cmprFileName.endsWith(".ppt") || cmprFileName.endsWith(".pptx") || cmprFileName.endsWith(".jpg") || cmprFileName.endsWith(".png") 
			        		 || cmprFileName.endsWith(".jpeg")  || cmprFileName.endsWith(".gif")){
			        	String ext = "";
			        	int index = orgFileName.lastIndexOf(".");
			        	if (index != -1) {
			        		ext = orgFileName.substring(index + 1);
			        	}
			        	
			        	File f = new File(FwkMessageUtil.getMessage("IEP.DEXT5.PATH") + dir);		
			        	
			        	boolean mkdirSuccess = true;
			    		if(!f.isDirectory()){
			    			f.setExecutable(true);
			    			f.setReadable(true);
			    			f.setWritable(true);
			    			mkdirSuccess = f.mkdirs();
			    		}
		
			    		if(mkdirSuccess){
			    			FwkParameterMap fileParameterMap = new FwkParameterMap();
			    			mFile.transferTo(new File(FwkMessageUtil.getMessage("IEP.DEXT5.PATH") + dir + File.separator + atchFileGroupNo+"."+ext));
			    			// 공통파일정보 셋팅
			    			fileParameterMap.put("P_FILE_CPCTY",  mFile.getSize());
			    			fileParameterMap.put("P_STRE_FILE_NM",  atchFileGroupNo+"."+ext);
			    			fileParameterMap.put("P_ATCHMNFL_NM",   orgFileName);	
			    			fileParameterMap.put("P_ATCHMNFL_COURS_NM",  FwkMessageUtil.getMessage("IEP.DEXT5.PATH") + dir);
			    			fileParameterMap.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");
			    			fileParameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
			    			fileArray[i] = fileParameterMap;
			    		}else{
			    			LOG.debug("폴더 생성에 실패하였습니다.");
			    		}
			        }else{
			        	LOG.debug("제한된 확장자 예외");
			        }
				}else{
					fileArray[i] = null;
				}
			}
			parameterMap.put("P_FILE_ARRAY", fileArray);
			
		}
		
		FwkTransferObject trans = iproEbidPartcptSttusService.bidAssrncInfoRegist(parameterMap);
		trans.put("resultCode", "SUCC");
		model.addAllAttributes(trans);
		
		return "forward:/ebid/popup/bidAssrncInfoInqire.do";
	}

}
