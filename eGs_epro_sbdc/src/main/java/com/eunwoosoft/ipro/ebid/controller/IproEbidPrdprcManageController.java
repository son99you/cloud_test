package com.eunwoosoft.ipro.ebid.controller; 

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.ParameterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.frwk.prl.request.FwkParameterMapInterceptor;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidPrdprcManageService;
import com.eunwoosoft.ipro.main.service.IproMainLoginFormService;

/**
 * 예가관리 > 예가관리 Controller 
 * <pre>
 * oda.iep.elbi.controller 
 *    |_ IproEbidPrdprcManageController.java
 * 
 * </pre>
 * @date : 2015. 01. 23. 오후 06:02:42
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
@Controller
@RequestMapping(value = "/ebid")
public class IproEbidPrdprcManageController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";
	String prefixPopUrl = "/ipro/views/ebid/popup/";
	
	@Resource(name="iproEbidPrdprcManageService")
	private IproEbidPrdprcManageService iproEbidPrdprcManageService;
	
	@Resource(name="iproMainLoginFormService")
	private IproMainLoginFormService iproMainLoginFormService;
	
	@Resource(name="iproEbidPlanDao")
	private IproEbidPlanDao iproEbidPlanDao;

	private static final Logger LOG = LoggerFactory.getLogger(FwkParameterMapInterceptor.class);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가등록요청 목록
	 * 2. 처리내용 : 
     *      - 예가등록요청 목록 조회 서비스를 호출한다.
     *      - 예가등록요청 목록 조회 결과를 Model에 담는다.
	 * </pre>
	 * @Method Name : prdprcRegReqList
	 * @date : 2019. 02. 13.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 13.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
     * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/prdprcRegReqList")
	public String prdprcRegReqList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPrdprcManageService.prdprcReqRegListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가관리 목록
	 * 2. 처리내용 : 
     *      - 예가관리 목록 조회 서비스를 호출한다.
     *      - 예가관리 목록 조회 결과를 Model에 담는다.
	 * </pre>
	 * @Method Name : prdprcManageList
	 * @date : 2019. 02. 13.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 13.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
     * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/prdprcManageList")
	public String prdprcManageList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPrdprcManageService.prdprcManageListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}	
	
	/**
	 * <pre>
	 * 1. 개요 : 예가관리 등록 폼
	 * 2. 처리내용 : 
	 *      - 예가관리 등록 폼 서비스를 호출한다.
	 *      - 예가관리 등록 폼 결과를 Model에 담는다.
	 *      - View(JSP명) 를 반환한다.(elbi/iepElbiPrdprcManageRegistForm.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : prdprcManageRegistForm
	 * @date : 2015. 06. 30.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 06. 30.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/iepElbiPrdprcManageRegistForm
	 * @throws Exception 
	 */
	@RequestMapping(value = "/prdprcManageRegistForm")
	public String prdprcManageRegistForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPrdprcManageService.prdprcManageRegistForm(parameterMap);
		
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가관리 등록
	 * 2. 처리내용 : 
	 *      - 예가관리 등록 폼 서비스를 호출한다.
	 *      - 예가관리 등록 폼 결과를 Model에 담는다.
	 *      - View(JSP명) 를 반환한다.(elbi/iepElbiPrdprcManageDetail.jsp)
	 * </pre>
	 * @Method Name : prdprcManageRegist
	 * @date : 2015. 06. 30.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 06. 30.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/iepElbiPrdprcManageDetail
	 * @throws Exception 
	 */
	@RequestMapping(value = "/prdprcManageRegist")
	public String prdprcManageRegist(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		iproEbidPrdprcManageService.prdprcManageRegist(parameterMap);
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가관리 수정 폼
	 * 2. 처리내용 : 
	 *      - 예가관리 수정 폼 서비스를 호출한다.
	 *      - 예가관리 수정 폼 결과를 Model에 담는다.
	 *      - View(JSP명) 를 반환한다.(elbi/iepElbiPrdprcManageUpdtForm.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : prdprcManageUpdtForm
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/iepElbiPrdprcManageUpdtForm
	 * @throws Exception 
	 */
	@RequestMapping(value = "/prdprcManageUpdtForm")
	public String prdprcManageUpdtForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPrdprcManageService.prdprcManageUpdtForm(parameterMap);
		
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가관리 수정
	 * 2. 처리내용 : 
	 *      - 예가관리 등록 폼 서비스를 호출한다.
	 *      - 예가관리 등록 폼 결과를 Model에 담는다.
	 *      - View(JSP명) 를 반환한다.(elbi/iepElbiPrdprcManageDetail.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : prdprcManageUpdt
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/iepElbiPrdprcManageDetail
	 * @throws Exception 
	 */
	@RequestMapping(value = "/prdprcManageUpdt")
	public String prdprcManageUpdt(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
		parameterMap.put("multipartRequest", multipartRequest);
		iproEbidPrdprcManageService.prdprcManageUpdt(parameterMap);
		
		return "forward:/ebid/prdprcManageDetail.do";
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가관리 상세
	 * 2. 처리내용 : 
	 *      - 예가관리 상세 조회 서비스를 호출한다.
	 *      - 예가관리 상세 조회 결과를 Model에 담는다.
	 *      - View(JSP명) 를 반환한다.(elbi/iepElbiPrdprcManageDetail.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : prdprcManageDetail
	 * @date : 2015. 02. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/iepElbiPrdprcManageDetail
	 * @throws Exception 
	 */
	@RequestMapping(value = "/prdprcManageDetail")
	public String prdprcManageDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkDataEntity user = parameterMap.getLoginResult();
		FwkTransferObject trans = iproEbidPrdprcManageService.prdprcManageDetailInqire(parameterMap);

		trans.put("SABUN", user.getString("SABUN"));
		trans.put("toDayTime", FwkDateUtil.getCurrentDateTimeAsString());
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}

	/**
	 * <pre>
	 * 1. 개요 : 예가생성 등록(복수예가)
	 * 2. 처리내용 : 
    *      - 예가생성 등록 서비스를 호출한다.
     *     - JSON_VIEW로 반환한다.
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : prdprcCreatRegist
	 * @date : 2015. 02. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/prdprcCreatRegist")
	public String prdprcCreatRegist(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPrdprcManageService.prdprcCreatRegist(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 등록(단일예가)
	 * 2. 처리내용 : 
	 *      - 예가 등록 서비스를 호출한다.
	 *     - JSON_VIEW로 반환한다.
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : prdprcRegist
	 * @date : 2015. 02. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "/prdprcRegist")
//	public String prdprcRegist(final HttpServletRequest request, final Model model) throws Exception  {		
//		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
//		FwkTransferObject trans = iproEbidPrdprcManageService.prdprcRegist(parameterMap);
//		model.addAllAttributes(trans);
//		return JSON_VIEW;
//	}
	
	@RequestMapping(value = "/prdprcRegist")
	public String prdprcRegist(final HttpServletRequest request, final Model model) throws Exception  {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		String price = (String)parameterMap.get("P_SCH_PRCE_AMT");
		String tmpPrice = "";
		String strPrice = "";
		int strIdx = 0;
		//숫자형태가 아닌 문자열일경우 공백으로 반환 
        String numberExpr = "^[-+]?(0|[1-9][0-9]*)(\\.[0-9]+)?([eE][-+]?[0-9]+)?$";
        
        for(int idx = 0; idx < price.length(); idx++){
        	tmpPrice = price.substring(strIdx, (strIdx+1));
        	if(!tmpPrice.matches(numberExpr)){
        		// 
        	}else{
        		strPrice = strPrice + tmpPrice;
        	}
        	
        	strIdx++;
        }
        
			
		// 입찰 공고 상세
		FwkDataEntity infoDetail = iproEbidPlanDao.selectBidInfoDetail(parameterMap);
		
		// 복수예가일 경우 최종 담당자가 입력한 금액을 기준으로 예가를 생성해서 key값을 BASE_ESTPC_AMT(기초예가)로 사용했고
		// 단일예가일 경우 최종 담당자가 입력한 금액이 예정가격이므로  key값을 SCH_PRCE_AMT(예정가격)으로 사용했다.
		// 결론은 같은 값이다!!
		
		// 복수예가 일 경우
		if("180000".equals(infoDetail.get("ESTPC_SECD"))){
			parameterMap.put("P_BASE_ESTPC_AMT", strPrice);
			parameterMap.put("P_ESTPC_DCSN_SECD", infoDetail.getString("PLR_ESTPC_RNG_CD")+infoDetail.getString("ESTPC_UP_CNT"));
			iproEbidPrdprcManageService.prdprcCreatRegist(parameterMap);
		// 단일예가 일 경우
		}else if("180001".equals(infoDetail.get("ESTPC_SECD"))){
			parameterMap.put("P_SCH_PRCE_AMT", strPrice);
			iproEbidPrdprcManageService.prdprcRegist(parameterMap);
		}
		
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 기초예가금액 입력 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidPrdprcManageController.java
	 * @Method : prdprcRegistFrom
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 26. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/popup/prdprcRegistForm")
	public String prdprcRegistFrom(final HttpServletRequest request, final Model model) throws Exception  {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		if(!"".equals(parameterMap.getString("ssoId"))){
			parameterMap.put("serverUrl", "https://core.ebs.co.kr/xclick_ebs/XClickApiService"); 
			String ip = request.getHeader("X-FORWARDED-FOR");
			if(ip == null || ip.equals("")) {
				ip = request.getRemoteAddr();
			}
			parameterMap.put("CONN_IP", ip);
			
			FwkTransferObject login = iproMainLoginFormService.login(parameterMap);
			FwkParameterMap loginMap = (FwkParameterMap) login.get(IproMainLoginFormService.LOGIN_RESULT);
			FwkDataEntity loginData = (FwkDataEntity) loginMap.get(IproMainLoginFormService.RESULT_DATA);
			parameterMap.put(IproMainLoginFormService.LOGIN_RESULT, loginData);
			
			String resultCode = parameterMap.getString(IproMainLoginFormService.RESULT_CODE);

			if("0".equals(resultCode)){
				loginData.put("P_SSO_STATUS", "OK");
			}
			
			parameterMap.put(IproMainLoginFormService.RESULT_DATA, loginData);
			
			request.getSession().setAttribute(IproMainLoginFormService.LOGIN_RESULT, loginData);
			
			model.addAttribute(IproMainLoginFormService.LOGIN_RESULT, parameterMap);
			
		}
		
		FwkTransferObject trans = iproEbidPrdprcManageService.prdprcManageDetailInqire(parameterMap);
		model.addAllAttributes(trans);

		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	@RequestMapping(value = "/ebidApprSendPrice")
	public String ebidApprSendPrice(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPrdprcManageService.ebidApprSendPrice(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/rebach")
	public String rebach(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		iproEbidPrdprcManageService.rebach(parameterMap);
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 예가반려
	 * @Method Name : prdprcReject
	 * @date : 2020. 03. 16.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2020. 03. 16.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/iepElbiPrdprcManageDetail
	 * @throws Exception 
	 */
	@RequestMapping(value = "/prdprcReject")
	public String prdprcReject(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		parameterMap.put("P_ESTC_PSCD", "ES04");
		
		iproEbidPrdprcManageService.prdprcReject(parameterMap);
		
		return JSON_VIEW;
	}	
	
}
