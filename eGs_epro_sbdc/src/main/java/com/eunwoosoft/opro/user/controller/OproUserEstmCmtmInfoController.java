package com.eunwoosoft.opro.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.user.service.OproUserEstmCmtmInfoService;

/**
 * 평가위원정보관리 상세
 */

@RequestMapping(value = "/opro/user")
@Controller
public class OproUserEstmCmtmInfoController  extends AbstractOproMenuController {
	String prefixUrl = "/opro/views/user/";
	String prefixUrlPopup = "/opro/views/user/popup/";
	
	@Resource(name="OproUserEstmCmtmInfoService")
	private OproUserEstmCmtmInfoService OproUserEstmCmtmInfoService;
	
	
	
	@RequestMapping(value = "/estmCmtmInfoDetail" , method={RequestMethod.POST, RequestMethod.GET})
	public String UserEstmCmtmInfoDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = OproUserEstmCmtmInfoService.estmCmtmInfoDetail(parameterMap);
		
		model.addAllAttributes(trans);
//		System.out.println("@@tans@@" + trans);
		model.addAttribute("trans", trans); 
		model.addAllAttributes(parameterMap);
//		System.out.println("@@@Controller@@@");
		
//		System.out.println("parameterMap@@@" + parameterMap);
		return parameterMap.getOproViewName(prefixUrl);
	}
}
