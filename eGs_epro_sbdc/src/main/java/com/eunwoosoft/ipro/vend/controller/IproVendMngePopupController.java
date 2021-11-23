package com.eunwoosoft.ipro.vend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.vend.controller
 * IproVendMngePopupController.java
 *
 * @Author : chanil_Hong
 * @Date   : 2017. 6. 15.
 *
 */
@RequestMapping(value = "/vend/popup")
@Controller
public class IproVendMngePopupController extends AbstractFwkController{
	String prefixUrl = "/ipro/views/vend/popup/";
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : sg설명 팝업창 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendMngeController.java
	 * @Method : sgPopup
	 * @author : chanil_Hong
	 * @date : 2017. 6. 15. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/sgPopup")
	public String sgPopup(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : vendEvalReviewPopup 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendMngeController.java
	 * @Method : vendEvalReviewPopup
	 * @author : chanil_Hong
	 * @date : 2017. 6. 15. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendEvalReviewPopup")
	public String vendEvalReviewPopup(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
}
