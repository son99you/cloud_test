package com.eunwoosoft.ipro.sytm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.service.IproSytmG2bCntcService;


/**
 * 
 * com.eunwoosoft.ipro.sytm.controller
 * IproSytmG2bCntcController.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 4. 22.
 *
 */
@Controller
@RequestMapping(value = "/sytm")
public class IproSytmG2bCntcController extends AbstractIproMenuController {
	
	String prefixUrl = "/ipro/views/sytm/";
	String popupPrefixUrl = "/ipro/views/sytm/popup/";
	
	@Resource(name="iproSytmG2bCntcService")
	private IproSytmG2bCntcService iproSytmG2bCntcService;
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 나라장터연계이력 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.controller.IproSytmG2bCntcController.java
	 * @Method : g2bCntcHstyList
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 22. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/g2bCntcHstyList")
	public String g2bCntcHstyList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmG2bCntcService.g2bCntcHstyList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 나라장터연계이력 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.controller.IproSytmG2bCntcController.java
	 * @Method : g2bCntcHstyDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 22. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/popup/g2bCntcHstyDetail")
	public String g2bCntcHstyDetail(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproSytmG2bCntcService.g2bCntcHstyDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(popupPrefixUrl);
	}

}
