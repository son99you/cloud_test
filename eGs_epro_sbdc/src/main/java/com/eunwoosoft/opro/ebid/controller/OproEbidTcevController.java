package com.eunwoosoft.opro.ebid.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 기술평가
 * com.eunwoosoft.opro.ebid.controller
 * OproEbidTcevController.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2017. 7. 3.
 *
 */
@RequestMapping(value = "/opro/ebid")
@Controller
public class OproEbidTcevController extends AbstractOproMenuController {
	String prefixUrl = "/opro/views/ebid/";
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 정성평가 목록 / 코이카 참조 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.opro.ebid.controller.OproEbidTcevController.java
	 * @Method : tchqvlnTrgetList
	 * @author : sanghoon_joen
	 * @date : 2017. 7. 3. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/tchqvlnTrgetList")
	public String tchqvlnTrgetList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 정성평가 등록 폼 / 코이카 참조 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.opro.ebid.controller.OproEbidTcevController.java
	 * @Method : qualEvlScoreRegistForm
	 * @author : sanghoon_joen
	 * @date : 2017. 7. 3. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/qualEvlScoreRegistForm")
	public String qualEvlScoreRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 정성평가 의견서 등록 폼 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.opro.ebid.controller.OproEbidTcevController.java
	 * @Method : qualEvlWrtopnRegistForm
	 * @author : sanghoon_joen
	 * @date : 2017. 7. 3. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/qualEvlWrtopnRegistForm")
	public String qualEvlWrtopnRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 정성평가 상세 / 코이카 참조 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.opro.ebid.controller.OproEbidTcevController.java
	 * @Method : qualEvlScoreDetail
	 * @author : sanghoon_joen
	 * @date : 2017. 7. 3. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/qualEvlScoreDetail")
	public String qualEvlScoreDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 정성평가 의견서 상세 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.opro.ebid.controller.OproEbidTcevController.java
	 * @Method : qualEvlWrtopnDetail
	 * @author : sanghoon_joen
	 * @date : 2017. 7. 3. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/qualEvlWrtopnDetail")
	public String qualEvlWrtopnDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가등급 기준 팝업 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.opro.ebid.controller.OproEbidTcevController.java
	 * @Method : evlGradAlwncStdrInqire
	 * @author : sanghoon_joen
	 * @date : 2017. 7. 3. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/evlGradAlwncStdrInqire")
	public String evlGradAlwncStdrInqire(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
}
