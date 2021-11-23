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

import com.eunwoosoft.comm.atfi.controller.ComAtfiAtchFileController;
import com.eunwoosoft.comm.itfc.service.CommItfcService;
import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproEbidPblancService;
import com.eunwoosoft.ipro.ebid.service.IproEbidSucbidrSlctnService;

/**
 * 전자입찰 > 낙찰자선정 Controller 
 * <pre>
 * oda.iep.elbi.controller 
 *    |_ IepElbiSucbidrSlctnController.java
 * 
 * </pre>
 * @date : 2015. 03. 12. 오전 10:00:13
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
@Controller
@RequestMapping(value = "/ebid")
public class IproEbidSucbidrSlctnController extends AbstractIproMenuController {
	private static final Logger LOG = LoggerFactory.getLogger(ComAtfiAtchFileController.class);
	
	String prefixUrl = "/ipro/views/ebid/";
	String prefixPopUrl = "/ipro/views/ebid/popup/";
	
	@Resource(name="iproEbidSucbidrSlctnService")
	private IproEbidSucbidrSlctnService iproEbidSucbidrSlctnService;
	
	@Resource(name="iproEbidPblancService")
	private IproEbidPblancService iproEbidPblancService;
	
	@Resource(name="commItfcService")
	private CommItfcService commItfcService;

	/**
	 * <pre>
	 * 1. 개요 : 낙찰자선정 목록 
	 * 2. 처리내용 : 
     *      - 낙찰자선정 목록조회 서비스를 호출한다.
     *      - 낙찰자선정 목록조회 결과를 Model에 담는다.
     *      - View(JSP명) 를 반환한다.(elbi/iepElbiSucbidrSlctnList.jsp)
	 * </pre>
	 * @Method Name : sucbidrSlctnList
	 * @date : 2015. 03. 12.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 12.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/iepElbiSucbidrSlctnList
	 * @throws Exception 
	 */
	@RequestMapping(value = "/sucbidrSlctnList")
	public String sucbidrSlctnList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidSucbidrSlctnService.sucbidrSlctnListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 낙찰자선정 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidrSlctnDetail
	 * @date : 2015. 03. 17.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 17.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	
	
	@RequestMapping(value="/sucbidrSlctnDetail")
	public String sucbidrSlctnDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkDataEntity user = parameterMap.getLoginResult();
		FwkTransferObject trans = iproEbidSucbidrSlctnService.sucbidrSlctnDetailInqire(parameterMap);
		trans.put("SABUN", user.getString("SABUN"));
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 적격심사 평가등록 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : properJdgmnEvlRegistForm
	 * @date : 2015. 3. 13.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 13.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	
	@RequestMapping(value="/popup/properJdgmnEvlRegistForm")
	public String properJdgmnEvlRegistForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidSucbidrSlctnService.properJdgmnEvlRegistForm(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 낙찰자선정 결격사유 등록 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidrSlctnBrdoResnRegistForm
	 * @date : 2015. 3. 16.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 16.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	
	@RequestMapping(value="/popup/sucbidrSlctnBrdoResnRegistForm")
	public String sucbidrSlctnBrdoResnRegistForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidSucbidrSlctnService.sucbidrSlctnBrdoResnRegistForm(parameterMap);
		trans.put("today", FwkDateUtil.getCurrentDateAsString());
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	@RequestMapping(value="/popup/ntatDspthRegistForm")
	public String ntatDspthRegistForm(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap)  request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidSucbidrSlctnService.ntatDspthRegistForm(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 낙찰자 결격사유 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sucbidrSlctnBrdoResnRegist
	 * @date : 2015. 3. 17.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 17.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/sucbidrSlctnBrdoResnRegist")
	public String sucbidrSlctnBrdoResnRegist(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap =(FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidSucbidrSlctnService.sucbidrSlctnBrdoResnRegist(parameterMap);
		
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 협상 통보 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : ntatDspthRegist
	 * @date : 2015. 9. 8.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 9. 8.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ntatDspthRegist")
	public String ntatDspthRegist(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap =(FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		parameterMap.put("ControllerType", "Json");
		FwkTransferObject trans = iproEbidSucbidrSlctnService.ntatDspthRegist(parameterMap);
		
		return JSON_VIEW;
	}
	
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 이메일전송대상 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : ntatEntrpsTrgetEmailTrnsmis
	 * @date : 2015. 3. 18.
	 * @author :은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 18.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/ntatEntrpsTrgetEmailTrnsmis")
	public String ntatEntrpsTrgetEmailTrnsmis(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap =(FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEbidSucbidrSlctnService.emailTrnsmisTrgetRegist(parameterMap);
		
		return JSON_VIEW;
		
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 적격심사 평가등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : properJdgmnEvlRegist
	 * @date : 2015. 3. 20.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 20.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/properJdgmnEvlRegist")
	public String properJdgmnEvlRegist(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap =(FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
		parameterMap.put("multipartRequest", multipartRequest);
		FwkTransferObject trans = iproEbidSucbidrSlctnService.properJdgmnEvlRegist(parameterMap);
		trans.put("SAVE_YN", "Y");
		model.addAllAttributes(trans);
		return "forward:/ebid/popup/properJdgmnEvlRegistForm.do";
//		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 예가선택업체 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidResultPrdprcChoiseEntrpsInqire
	 * @date : 2015. 3. 23.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/popup/bidResultPrdprcChoiseEntrpsInqire")
	public String bidResultPrdprcChoiseEntrpsInqire(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap =(FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEbidSucbidrSlctnService.bidResultPrdprcChoiseEntrpsInqire(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 낙찰 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : scsbid
	 * @date : 2015. 4. 30.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 30.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/scsbid")
	public String scsbid(final HttpServletRequest request, final Model model) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEbidSucbidrSlctnService.sucbidInfoUpdt(parameterMap);
		FwkTransferObject rtnTrans = new FwkTransferObject();

		// 연계 웹서비스 호출
		try {
			
			// 
			String contSecd = trans.get("CONT_SECD")+"";
			String prchQrqNo = trans.get("PRCH_RQR_NO")+"";
			
			LOG.debug("scsbid ===================="+FwkDateUtil.getCurrentDateTimeAsString());
			LOG.debug("contSecd :: " + contSecd);
			LOG.debug("prchQrqNo :: " + prchQrqNo);
			
			// 매각일 경우와 아닐경우 연계 호출 분기처리
			if("5".equals(contSecd)){
				parameterMap.put("P_CHARSET", "UTF-8");                                //호출수행 인코딩
				parameterMap.put("P_CASE_CODE", "ASTDISP");                        //연계케이스코드
				parameterMap.put("P_KEY", prchQrqNo);                                    //연계키값
				parameterMap.put("P_FILE_BRDN_NO", "");                                //연계파일번호
				
				rtnTrans = commItfcService.callOtherSysURL(parameterMap);
				if(rtnTrans.get("rtnCode").equals(200)) {
					trans.put("chkCode", "succ");	
				}else {
					trans.put("chkCode", "fail");				
				}
			}else{
				parameterMap.put("P_CHARSET", "UTF-8");                                //호출수행 인코딩
				parameterMap.put("P_CASE_CODE", "SCSBID");                         //연계케이스코드
				parameterMap.put("P_KEY", parameterMap.get("P_ANNC_NO"));  //연계키값
				parameterMap.put("P_FILE_BRDN_NO", "");                                //연계파일번호
				
				rtnTrans = commItfcService.callOtherSysURL(parameterMap);
				if(rtnTrans.get("rtnCode").equals(200)) {
					trans.put("chkCode", "succ");	
				}else {
					trans.put("chkCode", "fail");				
				}				
			}
			
			LOG.debug("========================");
		}catch(Exception e) {
			e.printStackTrace();
			trans.put("chkCode", "error");
		}
		
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
		//forward:/elbi/bidPblancDetail.do
	}
	
	@RequestMapping(value="/popup/sbidRsnRegistForm")
	public String sbidRsnRegistForm(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap =(FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	@RequestMapping(value="/popup/sameRsnRegistForm")
	public String sameRsnRegistForm(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap =(FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	@RequestMapping(value="/sameRankLot")
	public String sameRankLot(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap =(FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidSucbidrSlctnService.sameRankLot(parameterMap);
		
		FwkDataEntity vend =  (FwkDataEntity)trans.get("vendInfo");
		
		parameterMap.put("P_VEND_REG_NO", vend.getString("VEND_REG_NO"));
		parameterMap.put("P_TNDR_AMT", vend.getString("TNDR_AMT"));
		
		iproEbidSucbidrSlctnService.sucbidInfoUpdt(parameterMap);
		
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	@RequestMapping(value="/popup/sbidPeNullRegistForm")
	public String sbidPeNullRegistForm(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.canclPblancRegistForm(parameterMap);
		trans.put("today", FwkDateUtil.getCurrentDateAsString());
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	@RequestMapping(value = "/ebidApprSendNego")
	public String ebidApprSendEval(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidSucbidrSlctnService.ebidApprSendNego(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 낙찰 - 오프라인 수동 및 업체 입력
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : scsBidOffline
	 * @date : 2019. 03. 28.
	 * @author : 은우소프트 멩경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 28.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/scsBidOffline")
	public String scsBidOffline(final HttpServletRequest request, final Model model) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEbidSucbidrSlctnService.scsBidOfflineInsert(parameterMap);
		return JSON_VIEW;
	}	
}
