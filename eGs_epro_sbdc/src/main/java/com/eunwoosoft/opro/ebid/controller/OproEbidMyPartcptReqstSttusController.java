package com.eunwoosoft.opro.ebid.controller; 

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.ParameterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.code.dao.ComCmcdDetailCdDao;
import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.ebid.service.OproEbidMyPartcptReqstSttusService;
import com.yettiesoft.vestsign.external.CertificateInfo;
import com.yettiesoft.vestsign.external.SignVerifier;

/**
 * 전자입찰 > 나의 참가신청 현황 Controller 
 * <pre>
 * com.eunwoosoft.opro.ebid.controller 
 *    |_ OproEbidMyPartcptReqstSttusController.java
 * 
 * </pre>
 * @date : 2015. 03. 10. 오후 04:31:21
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
@Controller
@RequestMapping(value = "/opro/ebid")
public class OproEbidMyPartcptReqstSttusController extends AbstractOproMenuController { 
	String prefixUrl = "/opro/views/ebid/";
	String prefixPopUrl = "/opro/views/ebid/popup/";
	
	private static final Logger LOG = LoggerFactory.getLogger(OproEbidMyPartcptReqstSttusController.class);
	
	@Resource(name="oproEbidMyPartcptReqstSttusService")
	private OproEbidMyPartcptReqstSttusService oproEbidMyPartcptReqstSttusService;

	@Resource(name="comCmcdDetailCdDao")
    private ComCmcdDetailCdDao comCmcdDetailCdDao;
	
	/**
	 * <pre>
	 * 1. 개요 : 나의 참가신청 현황 목록 
	 * 2. 처리내용 : 
     *      - 나의 입찰골고 목록조회 서비스를 호출한다.
     *      - 나의 참가신청 현황 목록조회 결과를 Model에 담는다.
     *      - View(JSP명) 를 반환한다.(ebid/oepElbiMyPartcptReqstSttusList.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : myPartcptReqstSttusList
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- ebid/myPartcptReqstSttusList
	 * @throws Exception 
	 */
	@RequestMapping(value = "/myPartcptReqstSttusList")
	public String myPartcptReqstSttusList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidMyPartcptReqstSttusService.myPartcptReqstSttusListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}

	/**
	 * <pre>
	 * 1. 개요 : 나의 참가신청 현황 상세 페이지 이동
	 * 2. 처리내용 : 
     *      - 나의 참가신청 현황 상세 페이지로 이동한다.
     *      - View(JSP명) 를 반환한다.(ebid/oepElbiMyPartcptReqstSttusDetail.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : myPartcptReqstSttusDetail
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- ebid/oepElbiMyPartcptReqstSttusDetail
	 * @throws Exception 
	 */
	@RequestMapping(value = "/myPartcptReqstSttusDetail")
	public String myPartcptReqstSttusDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidMyPartcptReqstSttusService.myPartcptReqstSttusDetailInqire(parameterMap);
		
		//버튼 히든여부
		FwkDataEntity detailEntity = (FwkDataEntity) trans.get("myPartcptReqstSttusDetail");
		
		int pareEndDt = detailEntity.getInt("PARE_END_DT");
		int intServerTime = detailEntity.getInt("SERVER_TIME");
		
		//String serverTime = FwkDateUtil.getCurrentDateTimeAsString();
		//double intServerTime = Double.parseDouble(serverTime);
		
		if( intServerTime > pareEndDt) {
			trans.put("hiddnButton", "Y");
		}else{
			trans.put("hiddnButton", "N");
		}
		
		//trans.put("serverTime", FwkDateUtil.getCurrentDateTimeAsString());
//		trans.put("serverTime", detailEntity.getString("SERVER_TIME"));
		trans.put("serverTime", FwkDateUtil.getCurrentDateTimeAsString());
		model.addAllAttributes(trans);
		
		if("Y".equals(request.getAttribute("registAt"))) {
			parameterMap.put("registAt","Y");
		}
		request.removeAttribute("registAt");
		
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가포기신청서 등록 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptAbandnReqstdocRegistForm
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/popup/bidPartcptAbandnReqstdocRegistForm")
	public String bidPartcptAbandnReqstdocRegistForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = oproEbidMyPartcptReqstSttusService.bidAbndRegistForm(parameterMap);
		trans.put("today", FwkDateUtil.getCurrentDateAsString());
		model.addAllAttributes(trans);
		return parameterMap.getOproPopupViewName(prefixPopUrl);
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가포기신청서 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptAbandnReqstdocRegist
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/bidPartcptAbandnReqstdocRegist")
	public String bidPartcptAbandnReqstdocRegist(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		parameterMap.put("P_IP_ADRES", ip); // 접속아이피 주소
		
		oproEbidMyPartcptReqstSttusService.bidPartcptAbandnReqstdocRegist(parameterMap);
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰서 제출 폼 이동
	 * 2. 처리내용 : 
     *      - 입찰서 제출 페이지로 이동한다.
     *      - View(JSP명) 를 반환한다.(ebid/bipaPresentnForm.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : bipaPresentnForm
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- ebid/bipaPresentnForm
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bipaPresentnForm")
	public String bipaPresentnForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidMyPartcptReqstSttusService.bipaPresentnForm(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
		
	}
	
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰서 제출 등록
	 * 2. 처리내용 : 
	 *      - 입찰서 제출 등록을 한다.
	 *      - View(JSP명) 를 반환한다.(ebid/bipaPresentnForm.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : bipaPresentnRegist
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- ebid/bipaPresentnForm
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bipaPresentnRegist")
	public String bipaPresentnRegist(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		parameterMap.put("P_IP_ADRES", ip); // 접속아이피 주소
		 
		String url = "";
		
		/** 파일 암호화 **/
		/*
	     * 	public static final int CERT_STATUS_CRL	= 0;
	     *  public static final int CERT_STATUS_OCSP = 1;
	     *  public static final int CERT_STATUS_NONE = 2;
	     */
		parameterMap.put("P_CD_ID", "CERT_STATUS");
		parameterMap.put("P_ETC_VAL1", "Y");
//		List<FwkDataEntity> certList = comCmcdDetailCdDao.selectCdValueListByCdId(parameterMap);
		int cert_verify = 0;         
//		if( certList != null && certList.size() > 0){
//			FwkDataEntity cert = certList.get(0);
//			cert_verify = cert.getInt("CD_VALUE_NM");
//		}
//		cert_verify = 2;
		String sm = parameterMap.getString("P_TNDR_AMT_ENC");
		
		//arg1 = 0:CRL검증,1:OCSP검증,2:검증안함 , arg2 = 0:base64,1:hex
		String cert_verify_str = FwkMessageUtil.getMessage("YETTIE.CERT_STATUS");
		
		cert_verify = Integer.parseInt(cert_verify_str); 
				
	    SignVerifier sv = new SignVerifier(	sm 					/*전자서명문*/
	            											,cert_verify		/*유효성검증 방법 */	 
	            											,0);					/*서명문 인코딩 룰
	            																		base64 = 0
	            																		hex = 1          */ 
	    sv.verify();
	    
	    // 0 : 성공
	    // -10 : 만료 
	    // -20 : 폐기
	    // -30 : 기타오류
	    int nVerifierResult = sv.getLastErrorCode();
	    
	    System.err.println("@@@ nVerifierResult ==> " + nVerifierResult);
	    
	    
	    if(cert_verify == 2)
		{
	    	CertificateInfo  cert = sv.getSignerCertificate();
	    	parameterMap.put("P_CERT_DN", cert.getSubject());
	    	
			trans = oproEbidMyPartcptReqstSttusService.bipaPresentnRegist(parameterMap);
			
			trans.put("code", sv.getLastErrorCode());
			trans.put("msg", sv.getLastErrorMsg());  
		}else if(nVerifierResult == 0) {
			CertificateInfo  cert = sv.getSignerCertificate();
	    	parameterMap.put("P_CERT_DN", cert.getSubject());
	    	
			trans = oproEbidMyPartcptReqstSttusService.bipaPresentnRegist(parameterMap);
			
			trans.put("code", sv.getLastErrorCode());
			trans.put("msg", sv.getLastErrorMsg());  
		}
		else if(nVerifierResult == -10){
	    	System.out.println("인증서가 만료되었습니다.");
	    	trans.put("resultCode", "ERR");
	    	trans.put("code", sv.getLastErrorCode());
	    	trans.put("msg", sv.getLastErrorMsg());                                                                	
	    }else if(nVerifierResult == -20){
	    	System.out.println("인증서가 폐기되었습니다.");
	    	trans.put("resultCode", "ERR");
	    	trans.put("code", sv.getLastErrorCode());
	    	trans.put("msg", sv.getLastErrorMsg());                                                                	
	    }else if(nVerifierResult == -30){ //OCSP. CRL 오류
	    	System.out.println("서명문에 문제(검증)가 있습니다.");
	    	trans.put("resultCode", "ERR");
	    	trans.put("code", sv.getLastErrorCode());
	    	trans.put("msg", sv.getLastErrorMsg());                                                                	
	    }else { // VestSign 라이브러리 에러
	    	System.out.println("서명문에 문제(라이브러리)가 있습니다.");
	    	trans.put("resultCode", "ERR");
	    	trans.put("code", sv.getLastErrorCode());
	    	trans.put("msg", sv.getLastErrorMsg());
	    }
		
	    System.err.println("@@@ resultCode ==> " + trans.get("resultCode"));
	    System.err.println("@@@ code ==> " + trans.get("code"));
	    System.err.println("@@@ msg ==> " + trans.get("msg"));
	    
	    if("ERR".equals( trans.get("resultCode"))){
			url = "forward:/opro/ebid/bipaPresentnForm.do";
		}else{
			if(!"".equals(parameterMap.getString("P_RETURN_URL"))){
				url = "forward:/opro/ebid/"+parameterMap.getString("P_RETURN_URL")+".do";
			}else{
				url = "forward:/opro/ebid/inProgrsBidPblancDetail.do";
			}
		}
	    
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}

	/**
	 * <pre>
	 * 1. 개요 : 입찰 가능 여부 검증
	 * 2. 처리내용 : 
    *      - 입찰 가능 조건을 검증하는 서비스를 호출한다.
     *     - JSON_VIEW로 반환한다.
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : bidPosblAtVrify
	 * @date : 2015. 04. 17.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 17.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bidPosblAtVrify")
	public String bidPosblAtVrify(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		parameterMap.put("P_IP_ADRES", ip); // 접속아이피 주소
		FwkTransferObject trans = oproEbidMyPartcptReqstSttusService.bidPosblAtVrify(parameterMap);
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 제안서 가능 여부 검증
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPosblPrprcVrify
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
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bidPosblPrprcVrify")
	public String bidPosblPrprcVrify(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		parameterMap.put("P_IP_ADRES", ip); // 접속아이피 주소
		FwkTransferObject trans = oproEbidMyPartcptReqstSttusService.bidPosblPrprcVrify(parameterMap);
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 가격서제출 안내 팝업
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bipaPresentnUseAgree
	 * @date : 2017. 03. 10
	 * @author : 은우소프트 홍찬일
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2017. 03. 10.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/popup/bipaPresentnUseAgree")
	public String bipaPresentnUseAgree(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		model.addAllAttributes(parameterMap);
		return parameterMap.getOproPopupViewName(prefixPopUrl);
	}
	
	@RequestMapping(value="/popup/grntDetail")
	public String grntDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		model.addAllAttributes(parameterMap);
		return parameterMap.getOproPopupViewName(prefixPopUrl);
	}
	
	@RequestMapping(value="/popup/cleanDetail")
	public String cleanDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		model.addAllAttributes(parameterMap);
		return parameterMap.getOproPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 역경매 화면
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.opro.ebid.controller.OproEbidMyPartcptReqstSttusController.java
	 * @Method : datpRegistForm
	 * @author : sanghoon_joen
	 * @date : 2018. 12. 14. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/datpRegistForm")
	public String datpRegistForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidMyPartcptReqstSttusService.datpRegistForm(parameterMap);
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getOproViewName(prefixUrl);
		
	}
	
	/**
	 * <pre>
	 * 1.개요 : 역경매 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.opro.ebid.controller.OproEbidMyPartcptReqstSttusController.java
	 * @Method : datpRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 12. 14. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/datpRegist")
	public String datpRegist(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = oproEbidMyPartcptReqstSttusService.datpRegist(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 신청인 및 보증정보 업데이트
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.opro.ebid.controller.OproEbidMyPartcptReqstSttusController.java
	 * @Method : bipaPresentnUpdate
	 * @author : 맹경열
	 * @date : 2019. 02. 20. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bipaPresentnUpdate")
	public String grntInfoUpdate(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = oproEbidMyPartcptReqstSttusService.grntInfoUpdate(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 제안서 제출 정보등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prprcPresentnRegist
	 * @date : 2019. 03. 15.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 15.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- ebid/oepElbiBipaPresentnForm
	 * @throws Exception 
	 */
	@RequestMapping(value = "/prprcPresentnRegist")
	public String prprcPresentnRegist(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
    	trans = oproEbidMyPartcptReqstSttusService.prprcPresentnRegist(parameterMap);
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}	
	
}
