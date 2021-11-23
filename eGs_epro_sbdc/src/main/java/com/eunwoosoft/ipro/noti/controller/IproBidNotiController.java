package com.eunwoosoft.ipro.noti.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.noti.service.IproBidNotiService;

/**
 * 
 * com.eunwoosoft.ipro.noti.controller
 * IproBidNotiController.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 24.
 *
 */
@RequestMapping(value = "/noti")
@Controller
public class IproBidNotiController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/noti/";
	
	@Resource(name="iproBidNotiService")
	private IproBidNotiService iproBidNotiService;
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.noti.controller.IproBidNotiController.java
	 * @Method : bidNotiList
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 24. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bidNotiList")
	public String bidNotiList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproBidNotiService.bidNotiListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		
		model.addAttribute("myMenuList", super.myMenuList(request));
		model.addAttribute("myMenuSubList", super.myMenuSubList(request));
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.noti.controller.IproBidNotiController.java
	 * @Method : bidNotiDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 24. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bidNotiDetail")
	public String bidNotiDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproBidNotiService.bidNotiDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAttribute("trans", trans); 
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.noti.controller.IproBidNotiController.java
	 * @Method : bidNotiRegistForm
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 24. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bidNotiRegistForm")
	public String bidNotiRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproBidNotiService.usrInfoDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAttribute("trans", trans); 
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.noti.controller.IproBidNotiController.java
	 * @Method : bidNotiRegist
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bidNotiRegist")
	public String bidNotiRegist(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);

		try{
			FwkTransferObject trans = iproBidNotiService.bidNotiRegist(parameterMap);
			
			model.addAttribute("trans", trans);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
			
		}catch(Exception ex){
			//response.setStatus(999);
			//throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.noti.controller.IproBidNotiController.java
	 * @Method : bidNotiDelete
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bidNotiDelete")
	public String bidNotiDelete(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproBidNotiService.bidNotiDelete(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAttribute("trans", trans); 
		model.addAllAttributes(parameterMap);
		
		return "forward:/noti/bidNotiList.do";
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.noti.controller.IproBidNotiController.java
	 * @Method : bidNotiUpdtForm
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bidNotiUpdtForm")
	public String bidNotiUpdtForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproBidNotiService.bidNotiDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAttribute("trans", trans); 
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.noti.controller.IproBidNotiController.java
	 * @Method : bidNotiUpdt
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bidNotiUpdt")
	public String bidNotiUpdt(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);

		try{
			FwkTransferObject trans = iproBidNotiService.bidNotiUpdt(parameterMap);
			
			model.addAttribute("trans", trans);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
			
		}catch(Exception ex){
			//response.setStatus(999);
			//throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
	}

}
