package com.eunwoosoft.ipro.info.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.info.service.IproInfoApprSetService;

@RequestMapping(value = "/info")
@Controller
public class IproInfoApprSetController extends AbstractIproMenuController {
	private static final String prefixUrl = "/ipro/views/info/";
	//String prefixUrl = "/ipro/views/info/";
	//private static final String prefixUrlPopup = "/ipro/views/info/popup/";
	
	@Resource(name="iproInfoApprSetService")
	IproInfoApprSetService iproInfoApprSetService;
	
	/**
	 * <pre>
	 * 1.개요 : 결재여부 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.info.controller.IproInfoApprMngeController.java
	 * @Method : infoApprlineList
	 * @author : 맹경열
	 * @date : 2018. 03. 08. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/infoApprSet",method = RequestMethod.POST )
	public String infoApprSet(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); //세션값 가져오기
		
		
		parameterMap.put("USR_ID", session.get("USR_ID"));
		parameterMap.put("VEND_REG_NO", session.get("VEND_REG_NO"));
		
		trans = iproInfoApprSetService.getApprSetYnDetail(parameterMap);
		
		model.addAllAttributes(trans);
		
		
		

		return parameterMap.getViewName(prefixUrl);
	}
	/**
	 * <pre>
	 * 1.개요 : 결재 여부 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.info.controller.IproInfoApprMngeController.java
	 * @Method : infoApprSetRegist
	 * @author : 맹경열
	 * @date : 2018. 03. 08. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/infoApprSetRegist",method = RequestMethod.POST )
	public String infoApprSetRegist(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
//		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); //세션값 가져오기
		
		
		parameterMap.put("USR_ID", session.get("USR_ID"));
		parameterMap.put("VEND_REG_NO", session.get("VEND_REG_NO"));
		
		iproInfoApprSetService.apprSetRegist(parameterMap);
		
		
		
		
		return JSON_VIEW;
	}
	
}
