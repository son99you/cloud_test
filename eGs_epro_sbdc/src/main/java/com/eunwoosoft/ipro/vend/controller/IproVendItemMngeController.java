package com.eunwoosoft.ipro.vend.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.vend.service.IproVendItemMngeService;

@Controller
@RequestMapping(value = "/vend")
public class IproVendItemMngeController extends AbstractIproMenuController{
	String prefixUrl = "/ipro/views/vend/";
	
	@Resource(name="iproVendItemMngeService")
	private IproVendItemMngeService iproVendItemMngeService;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 협력사별품목관리
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendItemMngeController.java
	 * @Method : vendItemMngeList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 25. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/vendItemMngeList")
	public String vendItemMngeList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproVendItemMngeService.vendItemMngeListInqireWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}

}
