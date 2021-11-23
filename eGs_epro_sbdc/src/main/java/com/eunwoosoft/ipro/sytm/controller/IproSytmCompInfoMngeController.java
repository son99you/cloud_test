package com.eunwoosoft.ipro.sytm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.service.IproSytmCompInfoMngeService;

@RequestMapping(value = "/sytm")
@Controller
public class IproSytmCompInfoMngeController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/sytm/";
	
	@Resource(name="iproSytmCompInfoMngeService")
	private IproSytmCompInfoMngeService iproSytmCompInfoMngeService;

	/**
	 * 
	 * <pre>
	 * 1.개요 : 본사정보관리 목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.sytm.controller.IproSytmCompInfoMngeController.java
	 * @Method : compInfoMngeList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/compInfoMngeList")
	public String compInfoMngeList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);

		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 본사정보관리 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.sytm.controller.IproSytmCompInfoMngeController.java
	 * @Method : compInfoMngeDetail
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/compInfoMngeDetail")
	public String compInfoMngeDetail(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		System.err.println("compInfoMngeDetail START ================");
		FwkTransferObject trans = iproSytmCompInfoMngeService.compInfoMngeDetail(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 본사정보관리 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.controller.IproSytmCompInfoMngeController.java
	 * @Method : compInfoMngeRegist
	 * @author : jandi_Eun
	 * @date : 2018. 2. 27. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/compInfoMngeRegist")
	public String compInfoMngeRegist(final HttpServletRequest request, final HttpServletResponse response, final Model model) throws Exception{
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			FwkTransferObject trans = iproSytmCompInfoMngeService.compInfoMngeRegist(parameterMap);
			model.addAttribute("trans", trans);
		} catch (Exception ex) {
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
		
	}
}
