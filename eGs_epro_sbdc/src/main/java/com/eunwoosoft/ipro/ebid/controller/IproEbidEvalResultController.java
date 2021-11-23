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
 * 평가결과현황
 * com.eunwoosoft.ipro.ebid.controller
 * IproEbidEvalResultController.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2017. 6. 22.
 *
 */
@RequestMapping(value = "/ebid")
@Controller
public class IproEbidEvalResultController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";

	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가의견서 팝업 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidEvalResultController.java
	 * @Method : qualEvlWrtopnInqire
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 22. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/qualEvlWrtopnInqire")
	public String qualEvlWrtopnInqire(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰결과 목록 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidEvalResultController.java
	 * @Method : tchqvlnResultList
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="tchqvlnResultList")
	public String tchqvlnResultList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 결과 상세 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidEvalResultController.java
	 * @Method : tchqvlnResultGnrlzResultDetail
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="tchqvlnResultGnrlzResultDetail")
	public String tchqvlnResultGnrlzResultDetail(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체별 평가 결과 팝업 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidEvalResultController.java
	 * @Method : tchqvlnResultTotEvlScoreInqire
	 * @author : sanghoon_joen
	 * @date : 2017. 7. 11. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/tchqvlnResultTotEvlScoreInqire")
	public String tchqvlnResultTotEvlScoreInqire(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가위원 평가 결과 팝업 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidEvalResultController.java
	 * @Method : qualEvlScoreDetail
	 * @author : sanghoon_joen
	 * @date : 2017. 7. 11. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/qualEvlScoreDetail")
	public String qualEvlScoreDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
}
