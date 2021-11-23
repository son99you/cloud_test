package com.eunwoosoft.ipro.ebid.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproEbidProconService;

/**
 * 입찰현황
 * com.eunwoosoft.ipro.ebid.controller
 * IproEbidProconController.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2017. 6. 13.
 *
 */
@RequestMapping(value = "/ebid")
@Controller
public class IproEbidProconController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";
	
	@Resource(name="iproEbidProconService")
	IproEbidProconService iproEbidProconService;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰계획품의 목록 / 코이카 참조
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : /bidPlanAprvlList
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/bidPlanAprvlList")
	public String bidPlanAprvlList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		trans = iproEbidProconService.bidPlanAprvlListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰계획품의 상세 / 코이카 참조 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : bidPlanAprvlDetail
	 * @author : sanghoon_joen
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/bidPlanAprvlDetail")
	public String bidPlanAprvlDetail(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		trans = iproEbidProconService.bidPlanAprvlDetailInqire(parameterMap);
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 수정폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : bidPlanAprvlUpdtForm
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 2. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bidPlanAprvlUpdtForm")
	public String bidPlanAprvlUpdtForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidProconService.bidPlanAprvlUpdtForm(parameterMap);
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : bidPlanAprvlUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 2. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bidPlanAprvlUpdt")
	public String bidPlanAprvlUpdt(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
		parameterMap.put("multipartRequest", multipartRequest);
		iproEbidProconService.bidPlanAprvlUpdt(parameterMap);
		
		return "forward:/ebid/bidPlanAprvlDetail.do";
	}
}
