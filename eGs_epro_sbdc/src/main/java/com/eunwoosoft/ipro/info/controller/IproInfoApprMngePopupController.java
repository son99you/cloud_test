package com.eunwoosoft.ipro.info.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

@RequestMapping(value = "/info/popup")
@Controller
public class IproInfoApprMngePopupController extends AbstractFwkController{
	static final String prefixUrl = "/ipro/views/info/popup/";
	
	@RequestMapping(value="/infoSetUserListPopup", method = RequestMethod.POST)
	public String infoSetUserListPopup(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);

		return parameterMap.getPopupViewName(prefixUrl);
	}

}
