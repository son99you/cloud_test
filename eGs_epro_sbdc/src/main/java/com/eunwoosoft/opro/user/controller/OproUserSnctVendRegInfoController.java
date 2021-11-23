package com.eunwoosoft.opro.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.user.service.OproUserSnctVendRegInfoService;


/**
 * 
 * com.eunwoosoft.opro.user.controller
 * OproUserSnctVendRegInfoController.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 3. 7.
 *
 */
@RequestMapping(value = "/opro/user")
@Controller
public class OproUserSnctVendRegInfoController extends AbstractOproMenuController {
	String prefixUrl = "/opro/views/user/";
	String prefixUrlPopup = "/opro/views/user/popup/";
	
	@Resource(name="oproUserSnctVendRegInfoService")
	private OproUserSnctVendRegInfoService oproUserSnctVendRegInfoService;
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 :  부정당업체등록정보 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.user.controller.OproUserSnctVendRegInfoController.java
	 * @Method : snctVendRegInfoList
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 7. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/snctVendRegInfoList")
	public String snctVendRegInfoList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = oproUserSnctVendRegInfoService.snctVendRegInfoList(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 부정당업체등록정보 상세 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.user.controller.OproUserSnctVendRegInfoController.java
	 * @Method : snctVendRegInfoDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 7. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/popup/snctVendRegInfoDetail")
	public String snctVendRegInfoDetail(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = oproUserSnctVendRegInfoService.snctVendRegInfoDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproPopupViewName(prefixUrlPopup);
	}
	
}
