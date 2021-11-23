package com.eunwoosoft.opro.ebid.controller; 

import java.io.File;
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

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.frwk.prl.request.FwkParameterMapInterceptor;
import com.eunwoosoft.opro.ebid.service.OproEbidPblancService;

/**
 * 전자입찰 > 입찰공고 Controller 
 * <pre>
 * com.eunwoosoft.opro.ebid.controller 
 *    |_ OproEbidPblancController.java
 * 
 * </pre>
 * @date : 2015. 02. 16. 오후 04:31:21
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
@Controller
@RequestMapping(value = "/opro/ebid")
public class OproEbidPblancController extends AbstractOproMenuController {
	String prefixUrl = "/opro/views/ebid/";
	String prefixPopUrl = "/opro/views/ebid/popup/";
	
	@Resource(name="oproEbidPblancService")
	private OproEbidPblancService oproEbidPblancService;
	
	private static final Logger LOG = LoggerFactory.getLogger(FwkParameterMapInterceptor.class);

	/**
	 * <pre>
	 * 1. 개요 : 진행중인 입찰공고 목록 
	 * 2. 처리내용 : 
     *      - 진행중인 입찰골고 목록조회 서비스를 호출한다.
     *      - 진행중인 입찰공고 목록조회 결과를 Model에 담는다.
     *      - View(JSP명) 를 반환한다.(elbi/oepElbiInProgrsBidPblancList.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : inProgrsBidPblancList
	 * @date : 2015. 02. 16.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 16.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/inProgrsBidPblancList
	 * @throws Exception 
	 */
	@RequestMapping(value = "/inProgrsBidPblancList")
	public String inProgrsBidPblancList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidPblancService.inProgrsBidPblancListInqireWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}

	/**
	 * <pre>
	 * 1. 개요 : 진행중인 입찰공고 상세 페이지 이동
	 * 2. 처리내용 : 
     *      - 진행중인 입찰공고 상세 페이지로 이동한다.
     *      - View(JSP명) 를 반환한다.(elbi/oepElbiInProgrsBidPblancDetail.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : inProgrsBidPblancDetail
	 * @date : 2015. 02. 16.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 16.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/oepElbiInProgrsBidPblancDetail
	 * @throws Exception 
	 */
	@RequestMapping(value = "/inProgrsBidPblancDetail")
	public String inProgrsBidPblancDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidPblancService.inProgrsBidPblancDetailInqire(parameterMap);
		trans.put("serverTime", FwkDateUtil.getCurrentDateTimeAsString());
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 관심입찰 등록
	 * 2. 처리내용 : 
	 *      - 관심입찰을 등록한다.
	 *      - View(JSP명) 를 반환한다.(elbi/oepElbiInProgrsBidPblancDetail.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : intrstBidRegist
	 * @date : 2015. 02. 16.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 16.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/oepElbiInProgrsBidPblancDetail
	 * @throws Exception 
	 */
	@RequestMapping(value = "/intrstBidRegist")
	public String intrstBidRegist(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		

		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		// 로그인 업체 등록번호
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID"));
		
		FwkTransferObject trans = oproEbidPblancService.intrstBidRegist(parameterMap);
		
		model.addAllAttributes(trans);
		

		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 관심입찰 삭제 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : intrstBidDelete
	 * @date : 2015. 9. 4.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 9. 4.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/intrstBidDelete")
	public String intrstBidDelete(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		

		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		// 로그인 업체 등록번호
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID"));
		
		FwkTransferObject trans = oproEbidPblancService.intrstBidDelete(parameterMap);
		
		model.addAllAttributes(trans);
		
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰서 제출 폼 이동
	 * 2. 처리내용 : 
     *      - 입찰서 제출 페이지로 이동한다.
     *      - View(JSP명) 를 반환한다.(elbi/oepElbiBipaPresentnForm.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : fngprtRecogImtBipaPresentnForm
	 * @date : 2015. 08. 11.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 08. 11.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/oepElbiFngprtRecogImtBipaPresentnForm
	 * @throws Exception 
	 */
	@RequestMapping(value = "/fngprtRecogImtBipaPresentnForm")
	public String fngprtRecogImtBipaPresentnForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
//		FwkTransferObject trans = oepElbiMyPartcptReqstSttusService.bipaPresentnForm(parameterMap);
//		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}

	/**
	 * <pre>
	 * 1. 개요 : 지문인식 모의공고 상세 페이지 이동
	 * 2. 처리내용 : 
     *      - 지문인식 모의공고 상세 페이지로 이동한다.
     *      - View(JSP명) 를 반환한다.(elbi/oepElbiFngprtRecogImtPblancDetail.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : fngprtRecogImtPblancDetail
	 * @date : 2015. 02. 23.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 23.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/oepElbiFngprtRecogImtPblancDetail
	 * @throws Exception 
	 */
	@RequestMapping(value = "/fngprtRecogImtPblancDetail")
	public String fngprtRecogImtPblancDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		

		FwkTransferObject trans = oproEbidPblancService.inProgrsBidPblancDetailInqire(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 지문인식 모의 공고 입찰 참가 신청서 작성 폼 페이지 이동
	 * 2. 처리내용 : 
	 *      - 지문인식 모의공고 상세 페이지로 이동한다.
	 *      - View(JSP명) 를 반환한다.(elbi/oepElbiFngprtRecogImtPblancPartcptReqestForm.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : fngprtRecogImtPblancPartcptReqestForm
	 * @date : 2015. 08. 11.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 08. 11.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/oepElbiBidFngprtRecogImtPblancPartcptReqestForm
	 * @throws Exception 
	 */
	@RequestMapping(value = "/fngprtRecogImtPblancPartcptReqestForm")
	public String fngprtRecogImtPblancPartcptReqestForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		
		FwkTransferObject trans = oproEbidPblancService.bidPartcptReqstdocWritngForm(parameterMap);
		trans.put("today", FwkDateUtil.getCurrentDateAsString("yyyy-MM-dd"));
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
		
	}
	
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰 참가 신청서 작성 폼 페이지 이동
	 * 2. 처리내용 : 
	 *      - 지문인식 모의공고 상세 페이지로 이동한다.
	 *      - View(JSP명) 를 반환한다.(elbi/oepElbiFngprtRecogImtPblancDetail.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : bidPartcptReqstdocWritngForm
	 * @date : 2015. 04. 09.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 09.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/oepElbiBidPartcptReqstdocWritngForm
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bidPartcptReqstdocWritngForm")
	public String bidPartcptReqstdocWritngForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidPblancService.bidPartcptReqstdocWritngForm(parameterMap);
		trans.put("serverTime", FwkDateUtil.getCurrentDateTimeAsString());
		trans.put("today", FwkDateUtil.getCurrentDateAsString("yyyy-MM-dd"));
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰 참가 신청 등록
	 * 2. 처리내용 : 
	 *      - 입찰 참가 신청 등록 한다.
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : bidPartcptReqstRegist
	 * @date : 2015. 04. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- 
	 */
	@RequestMapping(value = "/bidPartcptReqstRegist")
	public String bidPartcptReqstRegist(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		MultipartHttpServletRequest careEssntlMultipartRequest =  (MultipartHttpServletRequest)request;
		parameterMap.put("multipartRequest", careEssntlMultipartRequest);
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		parameterMap.put("P_PARTCPTN_IP_ADRES", ip);
		
		String url = "";
		
		for (int i = 0; i < parameterMap.getInt("rowCount") ; i++) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
			MultipartFile mFile = multipartRequest.getFile("P_FILE"+i);
			if(mFile != null){
			
				String atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		        String orgFileName = mFile.getOriginalFilename(); // 원파일명
		        String cmprFileName = mFile.getOriginalFilename().toLowerCase(); // 비교파일명
		        String dir = File.separator+FwkDateUtil.getCurrentDateAsString("yyyy")+
						File.separator+FwkDateUtil.getCurrentDateAsString("MM")+
						File.separator+FwkDateUtil.getCurrentDateAsString("dd")+
						File.separator+"bid";
		        
		        if(cmprFileName.endsWith(".zip") || cmprFileName.endsWith(".pdf") || cmprFileName.endsWith(".hwp") || cmprFileName.endsWith(".txt")
		        		 || cmprFileName.endsWith(".doc") || cmprFileName.endsWith(".docx") || cmprFileName.endsWith(".xls") || cmprFileName.endsWith(".xlsx")
		        		 || cmprFileName.endsWith(".ppt") || cmprFileName.endsWith(".pptx") || cmprFileName.endsWith(".jpg") || cmprFileName.endsWith(".png") 
		        		 || cmprFileName.endsWith(".jpeg")  || cmprFileName.endsWith(".gif")){
		        	String ext = "";
		        	int index = orgFileName.lastIndexOf(".");
		        	if (index != -1) {
		        		ext = orgFileName.substring(index + 1);
		        	}
		        	
		        	File f = new File(FwkMessageUtil.getMessage("EW.SYS.FILE.DIR") + dir);		
		        	
		        	boolean mkdirSuccess = true;
		    		if(!f.isDirectory()){
		    			f.setExecutable(true);
		    			f.setReadable(true);
		    			f.setWritable(true);
		    			mkdirSuccess = f.mkdirs();
		    		}

		    		if(mkdirSuccess){
		    			 mFile.transferTo(new File(FwkMessageUtil.getMessage("EW.SYS.FILE.DIR") + dir + File.separator + atchFileGroupNo+"."+ext));
		    			// 공통파일정보 셋팅
		    			parameterMap.put("P_FILE_CPCTY"+i,  mFile.getSize());
		    			parameterMap.put("P_STRE_FILE_NM"+i,  atchFileGroupNo+"."+ext);
		    			parameterMap.put("P_ATCHMNFL_NM"+i,   orgFileName);	
		    			parameterMap.put("P_ATCHMNFL_COURS_NM"+i,  FwkMessageUtil.getMessage("EW.SYS.FILE.DIR") + dir);
		    			parameterMap.put("P_ATCHMNFL_EXTSN_NM"+i,  "application/octet-stream");
		    			
		    			parameterMap.put("P_BIGU_FILE_GRP_NO"+i, atchFileGroupNo);
		    		}else{
		    			LOG.debug("폴더 생성에 실패하였습니다.");
		    		}
		        }else{
		        	LOG.debug("제한된 확장자 예외");
		        }
			}
		}
			
		FwkTransferObject trans = oproEbidPblancService.bidPartcptReqstRegist(parameterMap);
		trans.put("today", FwkDateUtil.getCurrentDateAsString("yyyy-MM-dd"));
		
		
		String resultCode = trans.get("resultCode")+"";
		LOG.debug("resultCode :: " + resultCode );
		if("ERR".equals(resultCode)){
			url = "forward:/opro/ebid/inProgrsBidPblancList.do";
		}else{
			// 단독이행일 경우에는 바로 상세페이지 이동
			if("240001".equals(parameterMap.getString("P_COPERTN_SPLDMD_DUTY_SE_CD"))){
				url = "forward:/opro/ebid/myPartcptReqstSttusDetail.do";
				request.setAttribute("registAt", "Y");
				
			}else{
				url = "forward:/opro/ebid/bidPartcptReqstCompt.do";
				request.setAttribute("registAt", "Y");
			}
		}
		
		model.addAllAttributes(trans);
		
		return url;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 개인정보활용동의서 팝업
	 * 2. 처리내용 : 
	 *      - 개인정보활용동의서 팝업 페이지로 이동한다.
	 *      - View(JSP명) 를 반환한다.(elbi/oepElbiPopupPersonalInfoUseAgree.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : personalInfoUseAgree
	 * @date : 2016. 03. 15.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 03. 15.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/oepElbiPopupPersonalInfoUseAgree
	 * @throws Exception 
	 */
	@RequestMapping(value = "/popup/personalInfoUseAgree")
	public String personalInfoUseAgree(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		return parameterMap.getOproPopupViewName(prefixPopUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 작업완료 공동수급협정서 숙지 팝업창
	 * 2. 처리내용 : 
	 *      - 숙지팝업창으로 이동한다.
	 *      - View(JSP명) 를 반환한다.(elbi/oepElbiBidPopupPartcptReqstComptUseAgree.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : partcptReqstComptUseAgree
	 * @date : 2017. 03. 09
	 * @author : 은우소프트 홍찬일
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2017. 03. 09.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/oepElbiBidPopupPartcptReqstComptUseAgree
	 * @throws Exception 
	 */
	@RequestMapping(value = "/popup/bidPartcptReqstComptUseAgree")
	public String partcptReqstComptUseAgree(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		model.addAllAttributes(parameterMap);
		return parameterMap.getOproPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 업체 제재 여부 확인 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : entrpsPunshAtCnfirm
	 * @date : 2015. 9. 2.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 9. 2.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/entrpsPunshAtCnfirm")
	public String entrpsPunshAtCnfirm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidPblancService.entrpsPunshAtCnfirm(parameterMap);
		
		model.addAllAttributes(trans);
		
		return JSON_VIEW; 
	}
	
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰 참가 신청 완료
	 * 2. 처리내용 : 
	 *      - 입찰참가신청완료 페이지로 이동한다.
	 *      - View(JSP명) 를 반환한다.(elbi/oepElbiBidPartcptReqstCompt.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : bidPartcptReqstCompt
	 * @date : 2015. 04. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/oepElbiBidPartcptReqstCompt
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bidPartcptReqstCompt")
	public String bidPartcptReqstCompt(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		if("Y".equals(request.getAttribute("registAt"))) {
			parameterMap.put("registAt","Y");
		}
		request.removeAttribute("registAt");
		
		System.err.println("parameterMap ::: " + parameterMap);
		
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 공동수급협정서 작성 폼 
	 * 2. 처리내용 : 
	 *      - 입찰참가신청완료 페이지로 이동한다.
	 *      - View(JSP명) 를 반환한다.(elbi/oepElbiCopertnSpldmdTrtyOfeWritngForm.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfeWritngForm
	 * @date : 2015. 04. 15.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 15.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/oepElbiCopertnSpldmdTrtyOfeWritngForm
	 * @throws Exception 
	 */
	@RequestMapping(value = "/copertnSpldmdTrtyOfeWritngForm")
	public String copertnSpldmdTrtyOfeWritngForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidPblancService.copertnSpldmdTrtyOfeWritngForm(parameterMap);
		trans.put("today", FwkDateUtil.getCurrentDateAsString("yyyy-MM-dd"));
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 공동수급협정서 제출
	 * 2. 처리내용 : 
	 *      - 공동수급 정보를 저장한다.
	 *      - View(JSP명) 를 반환한다.(elbi/oepElbiCopertnSpldmdTrtyOfeWritngForm.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfePresentn
	 * @date : 2015. 04. 15.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 15.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/oepElbiMyPartcptReqstSttusDetail
	 */
	@RequestMapping(value = "/copertnSpldmdTrtyOfePresentn")
	public String copertnSpldmdTrtyOfePresentn(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = oproEbidPblancService.copertnSpldmdTrtyOfePresentn(parameterMap);
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}

	/**
	 * <pre>
	 * 1. 개요 : 공동수급협정서 수정 폼 
	 * 2. 처리내용 : 
	 *      - 공동수급협정서 페이지로 이동한다.
	 *      - View(JSP명) 를 반환한다.(elbi/oepElbiCopertnSpldmdTrtyOfeUpdtForm.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfeUpdtForm
	 * @date : 2015. 04. 15.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 15.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/copertnSpldmdTrtyOfeUpdtForm
	 * @throws Exception 
	 */
	@RequestMapping(value = "/copertnSpldmdTrtyOfeUpdtForm")
	public String copertnSpldmdTrtyOfeUpdtForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidPblancService.copertnSpldmdTrtyOfeInqire(parameterMap);
		trans.put("today", FwkDateUtil.getCurrentDateAsString("yyyy-MM-dd"));
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 공동수급협정서 수정
	 * 2. 처리내용 : 
	 *      - 공동수급 정보를 저장한다.
	 *      - View(JSP명) 를 반환한다.(elbi/oepElbiCopertnSpldmdTrtyOfeWritngForm.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfeUpdt
	 * @date : 2015. 04. 15.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 15.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/oepElbiMyPartcptReqstSttusDetail
	 */
	@RequestMapping(value = "/copertnSpldmdTrtyOfeUpdt")
	public String copertnSpldmdTrtyOfeUpdt(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidPblancService.copertnSpldmdTrtyOfeUpdt(parameterMap);
		model.addAllAttributes(trans);
		
		return "forward:/opro/ebid/myPartcptReqstSttusDetail.do";
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
	 */
	
	@RequestMapping(value="/popup/bidPartcptReqstdocInqire")
	public String bidPartcptReqstdocInqire(final HttpServletRequest request, final Model model) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidPblancService.bidPartcptReqstdocInqire(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getOproPopupViewName(prefixPopUrl);
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 공동수급협정서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfeInqire
	 * @date : 2015. 4. 16.
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 16.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/popup/copertnSpldmdTrtyOfeInqire") 
	public String copertnSpldmdTrtyOfeInqire(final HttpServletRequest request, final Model model) throws Exception  {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidPblancService.copertnSpldmdTrtyOfeInqire(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getOproPopupViewName(prefixPopUrl);
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 조달청 연계 체크
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : g2bCeck
	 * @date : 2015. 4. 16.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 16.		은우소프트 전상훈			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/g2bCeck") 
	public String g2bCeck(final HttpServletRequest request, final Model model) throws Exception  {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		model.addAttribute("g2bAt", oproEbidPblancService.g2bCeck(parameterMap));
		return JSON_VIEW;
	}
	
	@RequestMapping(value="/popup/bidNotiDetail") 
	public String bidNotiDetail(final HttpServletRequest request, final Model model) throws Exception  {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = oproEbidPblancService.bidNotiDetail(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getOproPopupViewName(prefixPopUrl);
	}
	
	@RequestMapping(value="/popup/bidNotiList") 
	public String bidNotiList(final HttpServletRequest request, final Model model) throws Exception  {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = oproEbidPblancService.bidNotiList(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getOproPopupViewName(prefixPopUrl);
	}
	
	@RequestMapping(value="/chckOk") 
	public String chckOk(final HttpServletRequest request, final Model model) throws Exception  {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkDataEntity user = parameterMap.getLoginResult();
		parameterMap.put("P_VEND_REG_NO", user.getString("USR_ID"));
		oproEbidPblancService.chckOk(parameterMap);
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 제안서 제출 폼 이동
	 * 2. 처리내용 : 
     * 3. 입출력 : 
	 * </pre>
	 * @Method Name : prprcRegistForm
	 * @date : 2019. 02. 21.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 21.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/prprcRegistForm")
	public String prprcRegistForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = oproEbidPblancService.prprcPresentnForm(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}	
	
}