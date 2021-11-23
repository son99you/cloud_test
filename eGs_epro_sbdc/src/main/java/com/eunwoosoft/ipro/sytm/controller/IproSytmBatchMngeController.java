package com.eunwoosoft.ipro.sytm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.service.IproSytmBatchMngeService;


/**
 * 
 * com.eunwoosoft.ipro.sytm.controller
 * IproSytmBatchMngeController.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 4. 2.
 *
 */
@Controller
@RequestMapping(value = "/sytm")
public class IproSytmBatchMngeController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/sytm/";
	
	@Resource(name="iproSytmBatchMngeService")
	private IproSytmBatchMngeService iproSytmBatchMngeService;
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 배치조회 목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.controller.IproSytmBatchMngeController.java
	 * @Method : sytmBatchForm
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/sytmBatchForm")
	public String sytmBatchForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmBatchMngeService.sytmBatchFormListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}
	
}