package com.eunwoosoft.ipro.sytm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

@RequestMapping(value = "/sytm")
@Controller
public class IproSytmUserAuthMngeController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/sytm/";

	/**
	 * 
	 * <pre>
	 * 1.개요 : 사용자별 권한 관리
	 * 2.처리내용 : 광해 참조 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.sytm.controller.IproSytmUserMngeController.java
	 * @Method : userAuthMgrList
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 14. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/userAuthMgrList")
	public String userAuthMgrList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 사용자별 권한 관리 상세 
	 * 2.처리내용 : 광해 참조 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.sytm.controller.IproSytmUserMngeController.java
	 * @Method : userAuthMgrDetail
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 14. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/userAuthMgrDetail")
	public String userAuthMgrDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans); 
		
		return parameterMap.getViewName(prefixUrl);
	}
	
}
