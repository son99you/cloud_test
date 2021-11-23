package com.eunwoosoft.ipro.ebid.controller; 

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.ParameterMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproEbidOpengManageService;
import com.eunwoosoft.ipro.ebid.service.IproEbidPartcptSttusService;

/**
 * 개찰관리 > 개찰관리 Controller 
 * <pre>
 * com.eunwoosoft.ipro.ebid.controller 
 *    |_ IproEbidOpengManageController.java
 * 
 * </pre>
 * @date : 2015. 01. 27. 오후 06:02:42
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
@Controller
@RequestMapping(value = "/ebid")
public class IproEbidOpengManageController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";
	String prefixPopUrl = "/ipro/views/ebid/popup/";
	
	@Resource(name="iproEbidOpengManageService")
	private IproEbidOpengManageService iproEbidOpengManageService;
	
	@Resource(name="iproEbidPartcptSttusService")
	private IproEbidPartcptSttusService iproEbidPartcptSttusService;

	/**
	 * <pre>
	 * 1. 개요 : 개찰관리 목록
	 * 2. 처리내용 : 
     *      - 개찰관리 목록 조회 서비스를 호출한다.
     *      - 개찰관리 목록 조회 결과를 Model에 담는다.
     *      - View(JSP명) 를 반환한다.(elbi/iepElbiOpengManageList.jsp)
	 * </pre>
	 * @Method Name : opengManageList
	 * @date : 2015. 02. 27.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 27.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/iepElbiOpengManageList
	 * @throws Exception 
	 */
	@RequestMapping(value = "/opengManageList")
	public String opengManageList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidOpengManageService.opengManageListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 개찰관리 상세
	 * 2. 처리내용 : 
	 *      - 개찰관리 상세 조회 서비스를 호출한다.
	 *      - 개찰관리 상세 조회 결과를 Model에 담는다.
	 *      - View(JSP명) 를 반환한다.(elbi/iepElbiOpengManageDetail.jsp)
	 * </pre>
	 * @Method Name : opengManageDetail
	 * @date : 2015. 02. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/iepElbiOpengManageDetail
	 * @throws Exception 
	 */
	@RequestMapping(value = "/opengManageDetail")
	public String opengManageDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkDataEntity user = parameterMap.getLoginResult();
		FwkTransferObject trans = iproEbidOpengManageService.opengManageDetailInqire(parameterMap);
		trans.put("SABUN", user.getString("SABUN"));
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 개찰처리
	 * 2. 처리내용 : 
	 *      - 개찰 서비스를 호출한다.
	 *      - View(JSP명) 를 반환한다.(elbi/iepElbiOpengManageDetail.jsp)
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : openg
	 * @date : 2015. 03. 03.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 03.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/iepElbiOpengManageDetail
	 * @throws Exception 
	 */
	@RequestMapping(value = "/openg")
	public String openg(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidOpengManageService.opengInfoRegist(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAttribute("resultCode", trans.getResultCode());
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 적격검토 등록 폼
	 * 2. 처리내용 : 
     *      - 업체 적격검토 등록 팝업 페이지로 이동한다.
     *      - View(JSP명) 를 반환한다.(elbi/popup/iepElbiPopupEntrpsProperExmntRegistForm.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : entrpsProperExmntRegistForm
	 * @date : 2015. 03. 31.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 31.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/popup/iepElbiPopupCanclPblancRegistForm.jsp
	 */
	@RequestMapping(value = "/popup/entrpsProperExmntRegistForm")
	public String entrpsProperExmntRegistForm(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidOpengManageService.entrpsProperExmntRegistForm(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}

	/**
	 * <pre>
	 * 1. 개요 :업체 적격검토 등록
	 * 2. 처리내용 : 
	 *     - 업체 적격검토 등록 서비스를 호출한다.
     *     - 업체 적격검토 등록 결과를 Model에 담는다.
     *     - JSON_VIEW로 반환한다.
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : entrpsProperExmntRegist
	 * @date : 2015. 03. 31.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 31.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/entrpsProperExmntRegist")
	public String entrpsProperExmntRegist(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		iproEbidOpengManageService.entrpsProperExmntRegist(parameterMap);
		return JSON_VIEW;
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체 적격검토 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.controller.IproEbidOpengManageController.java
	 * @Method : entrpsProperExmntDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 7. 25. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/popup/entrpsProperExmntDetail")
	public String entrpsProperExmntDetail(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidOpengManageService.entrpsProperExmntRegistForm(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 제안평가 등록 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : entrpsProperPrprcRegistForm
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
	 */
	@RequestMapping(value = "/popup/entrpsProperPrprcRegistForm")
	public String entrpsProperPrprcRegistForm(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidOpengManageService.entrpsProperExmntRegistForm(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체 제안평가 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.controller.IproEbidOpengManageController.java
	 * @Method : entrpsProperPrprcDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 7. 11. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/popup/entrpsProperPrprcDetail")
	public String entrpsProperPrprcDetail(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidOpengManageService.entrpsProperExmntRegistForm(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	
	/**
	 * <pre>
	 * 1. 개요 :업체 정보
	 * 2. 처리내용 : 
    *      - 업체 정보를 보여주는 팝업 페이지로 이동한다.
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : ccpyManageDetail
	 * @date : 2016. 02. 23.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 31.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return
	 * @throws Exception 
	 * @throws SDBException 
	 */
	@RequestMapping(value = "/popup/ccpyManageDetail")
	public String ccpyManageDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidOpengManageService.ccpyManageDetail(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 기술점수저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.controller.IproEbidOpengManageController.java
	 * @Method : tchnScrRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 9. 3. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tchnScrRegist")
	public String tchnScrRegist(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		iproEbidOpengManageService.tchnScrRegist(parameterMap);
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/popup/opengSetVal")
	public String opengSatVal(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	@RequestMapping(value = "/ebidApprSendEval")
	public String ebidApprSendEval(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidOpengManageService.ebidApprSendEval(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 기술평가합격여부
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.controller.IproEbidOpengManageController.java
	 * @Method : estmElcdRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 11. 14. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmElcdRegist")
	public String estmElcdRegist(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		iproEbidOpengManageService.estmElcdRegist(parameterMap);
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 역경매 개찰 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.controller.IproEbidOpengManageController.java
	 * @Method : datpOpeng
	 * @author : sanghoon_joen
	 * @date : 2018. 12. 14. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/datpOpeng")
	public String datpOpeng(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidOpengManageService.datpOpengInfoRegist(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAttribute("resultCode", trans.getResultCode());
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 협상결과 등록 팝업
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : negoRsltRegistPopup
	 * @date : 2019. 02. 27.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 27.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- 
	 */
	@RequestMapping(value = "/popup/negoRsltRegistPopup")
	public String negoRsltRegistPopup(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidOpengManageService.negoRsltDetailInquire(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 협상결과 상세 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.controller.IproEbidOpengManageController.java
	 * @Method : negoRsltDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 7. 11. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/popup/negoRsltDetail")
	public String negoRsltDetail(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidOpengManageService.negoRsltDetailInquire(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 협상결과 업데이트
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : negoRsltRegist
	 * @date : 2019. 02. 27.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 27.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- 
	 */
	@RequestMapping(value = "/negoRsltRegist")
	public String negoRsltRegist(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		iproEbidOpengManageService.updateNegoRsltInfo(parameterMap);
		
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 투찰내역서 팝업
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptReqstdocInqire
	 * @date : 2019. 03. 05.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 05.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 * @param model
	 * @return
	 * @throws SDBException 
	 */
	@RequestMapping(value="/popup/vendTndrDocPopup")
	public String vendTndrDocPopup(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPartcptSttusService.bidPartcptReqstdocInqire(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 공동업체 정보 팝업
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : entrpsAssoInfoPopup
	 * @date : 2019. 05. 09.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 05. 09.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 * @param model
	 * @return
	 * @throws SDBException 
	 */
	@RequestMapping(value="/popup/entrpsAssoInfoPopup")
	public String entrpsAssoInfoPopup(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPartcptSttusService.bidPartcptReqstdocInqire(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixPopUrl);
	}	
}
