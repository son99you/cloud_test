package com.eunwoosoft.ipro.ebid.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 기술평가총괄표
 * com.eunwoosoft.ipro.ebid.controller
 * IproEbidTcevGeneController.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2017. 6. 29.
 *
 */
@RequestMapping(value = "/ebid")
@Controller
public class IproEbidTcevGeneController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";

	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가위원 총괄표 목록/ 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevGeneController.java
	 * @Method : tchqvlnList
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="evlMfcmmAcctoSmrizeFormList")
	public String tchqvlnList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체별 총괄표 목록 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevGeneController.java
	 * @Method : entrpsAcctoSmrizeFormList
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="entrpsAcctoSmrizeFormList")
	public String entrpsAcctoSmrizeFormList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가위원평가 총괄표 목록 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidTcevGeneController.java
	 * @Method : evlMfcmmEvlSmrizeFormList
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="evlMfcmmEvlSmrizeFormList")
	public String evlMfcmmEvlSmrizeFormList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
}
