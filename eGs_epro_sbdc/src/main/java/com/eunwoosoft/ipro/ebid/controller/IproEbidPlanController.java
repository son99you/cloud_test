package com.eunwoosoft.ipro.ebid.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproEbidPlanService;

/**
 * 입찰현황
 * com.eunwoosoft.ipro.ebid.controller
 * IproEbidProconController.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2017. 6. 13.
 *
 */
@RequestMapping(value = "/ebid")
@Controller
public class IproEbidPlanController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";
	String prefixPopUrl = "/ipro/views/ebid/popup/";
	@Resource(name="iproEbidPlanService")
	private IproEbidPlanService iproEbidPlanService;
	
	/**
	 * <pre>
	 * 1.개요 : 입찰작성현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : bidPlanList
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bidPlanList")
	public String bidPlanList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPlanService.bidPlanListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성대기현황 등록 화면
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : bidPlanRegistForm
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bidPlanRegistForm")
	public String bidPlanRegistForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPlanService.bidPlanRegistForm(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성대기현황 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : bidPlanRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bidPlanRegist")
	public String bidPlanRegist(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPlanService.bidPlanRegist(parameterMap);
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰공고 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : bidPlanDetail
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bidPlanDetail")
	public String bidPlanDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkDataEntity user = parameterMap.getLoginResult();
		FwkTransferObject trans = iproEbidPlanService.bidPlanDetailInqire(parameterMap);
		trans.put("SABUN", user.getString("SABUN"));
		model.addAllAttributes(trans);
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰공고 수정 화면
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : bidPlanUpdtForm
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bidPlanUpdtForm")
	public String bidPlanUpdtForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPlanService.bidPlanUpdtForm(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰공고 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : bidPlanUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bidPlanUpdt")
	public String bidPlanUpdt(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPlanService.bidPlanUpdt(parameterMap);
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰공고 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : bidPlanDelete
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bidPlanDelete")
	public String bidPlanDelete(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		iproEbidPlanService.bidPlanDelete(parameterMap);
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 상태 변경
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : bidSttusChange
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bidSttusChange")
	public String bidSttusChange(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		iproEbidPlanService.bidSttusChange(parameterMap);	
		//model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 유찰목록 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : fibList
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/popup/fibList")
	public String fibList(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPlanService.fibListInqireWithPgng(parameterMap);	
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	@RequestMapping(value = "/ebidApprSendPlan")
	public String ebidApprSendPlan(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPlanService.ebidApprSendPlan(parameterMap);	
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/elgbEstmKd")
	public String elgbEstmKd(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPlanService.elgbEstmKd(parameterMap);	
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 입찰 물품 불러오기 - 입찰대기 데이터
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : paItemList
	 * @author : 맹경열
	 * @date : 2019. 03. 08. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/paItemList")
	public String paItemList(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPlanService.selectPaItemList(parameterMap);	
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}	
	
}
