package com.eunwoosoft.ipro.ebid.controller; 

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.ParameterMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproEbidDcPeoService;

/**
 * 전자입찰 > 입찰설명회 Controller 
 * <pre>
 * oda.iep.elbi.controller 
 *    |_ IepElbiBidDcPeoController.java
 * 
 * </pre>
 * @date : 2015. 02. 13. 오전 10:33:21
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
@Controller
@RequestMapping(value = "/ebid")
public class IproEbidDcPeoController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";
	String prefixPopUrl = "/ipro/views/ebid/popup/";
	
	@Resource(name="iproEbidDcPeoService")
	private IproEbidDcPeoService iproEbidDcPeoService;
	

	/**
	 * <pre>
	 * 1. 개요 : 입찰설명회 목록 
	 * 2. 처리내용 : 
     *      - 입찰설명회 목록조회 서비스를 호출한다.
     *      - 입찰설명회 목록조회 결과를 Model에 담는다.
     *      - View(JSP명) 를 반환한다.(elbi/iepElbiBidDcPeoList.jsp)
	 * </pre>
	 * @Method Name : bidDcPeoList
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
	@RequestMapping(value = "/bidDcPeoList")
	public String bidDcPeoList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidDcPeoService.bidDcPeoListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰설명회 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidDcPeoDetail
	 * @date : 2015. 2. 16.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 16.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/bidDcPeoDetail")
	public String bidDcPeoDetail(final HttpServletRequest request, final Model model) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidDcPeoService.bidDcPeoDetailInqire(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰참가업체 등록 폼 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptEntrpsRegistForm
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
	@RequestMapping(value="/popup/bidPartcptEntrpsRegistForm")
	public String bidPartcptEntrpsRegistForm(final HttpServletRequest request, final Model model) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidDcPeoService.bidPartcptEntrpsRegistForm(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰참가업체 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptEntrpsRegist
	 * @date : 2015. 2. 17.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 17.	은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/bidPartcptEntrpsRegist")
	public String bidPartcptEntrpsRegist(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		iproEbidDcPeoService.bidPartcptEntrpsRegist(parameterMap);
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰 참가업체 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptEntrpsDelete
	 * @date : 2015. 2. 23.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/bidPartcptEntrpsDelete")
	public String bidPartcptEntrpsDelete(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		parameterMap.put("Controller", "Json");
		
		FwkTransferObject trans = iproEbidDcPeoService.bidPartcptEntrpsDelete(parameterMap);
		model.addAttribute("entrpsList", trans.get(IproEbidDcPeoService.BID_DC_PEO_PARTCPT_ENTRPS_LIST));
		
		return JSON_VIEW;
	}
}