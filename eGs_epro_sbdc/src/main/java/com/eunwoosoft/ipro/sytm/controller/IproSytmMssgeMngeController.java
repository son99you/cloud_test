package com.eunwoosoft.ipro.sytm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.service.IproSytmMssgeMngeService;


/**
 * 
 * com.eunwoosoft.ipro.sytm.controller
 * IproSytmMssgeMngeController.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 4. 2.
 *
 */
@Controller
@RequestMapping(value = "/sytm")
public class IproSytmMssgeMngeController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/sytm/";
	String popupPrefixUrl = "/ipro/views/sytm/popup/";
	
	@Resource(name="iproSytmMssgeMngeService")
	private IproSytmMssgeMngeService iproSytmMssgeMngeService;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 메세지관리 목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.controller.IproSytmMssgeMngeController.java
	 * @Method : logMngeList
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mssgeLogList")
	public String mssgeLogList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmMssgeMngeService.mssgeLogListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 메세지관리 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.controller.IproSytmMssgeMngeController.java
	 * @Method : logMngeDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/popup/mssgeLogDetail")
	public String logMngeDetail(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproSytmMssgeMngeService.mssgeLogDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(popupPrefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 메시지 발송 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.controller.IproSytmMssgeMngeController.java
	 * @Method : mssgeSend
	 * @author : MKY
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mssgeSend")
	public String mssgeSend(final HttpServletRequest request, final Model model) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		if(parameterMap.get("pageNo") == null || parameterMap.get("pageNo").equals("")) {
			model.addAttribute("pageNo", "1");
		}
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}
	
}
