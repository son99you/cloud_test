package com.eunwoosoft.ipro.noti.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.comm.vo.CommVo;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.ipro.noti.service.IproBidNotiService;
import com.eunwoosoft.ipro.noti.service.IproRssService;

/**
 * 
 * com.eunwoosoft.ipro.rss.controller
 * IproRssController.java
 *
 * @Author : JuYeon_Lee
 * @Date   : 2017. 6. 13.
 *
 */
@RequestMapping(value = "/noti")
@Controller
public class IproRssController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/noti/";
	
	@Resource(name="iproRssService")
	private IproRssService iproRssService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.rss.controller.IprorssController.java
	 * @Method : rssList
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/rssList", method={RequestMethod.POST, RequestMethod.GET})
	public String rssList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproRssService.rssListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.rss.controller.IprorssController.java
	 * @Method : rssDelete
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/rssDelete", method=RequestMethod.POST)
	public String rssDelete(final HttpServletRequest request, final Model model,  RedirectAttributes redirectAttributes) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproRssService.rssDelete(parameterMap);
		
		//Log 저장
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "NOTI");
		parameterMap.put("P_CONN_CNTN", "계약자료실 삭제");
		parameterMap.put("P_CONN_URL", "/noti/rssDetail.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAttribute("trans", trans); 
		model.addAllAttributes(parameterMap);
		
		redirectAttributes.addFlashAttribute( "parameterMap", parameterMap );
		
		return JSON_VIEW;
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.rss.controller.IprorssController.java
	 * @Method : rssDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/rssDetail", method={RequestMethod.POST,RequestMethod.GET})
	public String rssDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		/**
		 * request에 키값이 되는 값이 담겨있지 않으면 redirct로 넘어왔다고 판단하고
		 * request에 담겨있는 parameterMap값을 request.session에 담겨있는 parameterMap값으로  바꿔준다.
		 * 그리고 request.session에 담겨있는 requestUrl값을 먼저담겨있는 request.parameterMap 의 requestUrl으로 바꿔준다.
		 * 그래야지 jsp페이지를 정상적으로 찾을 수 있음. 
		 */
		if(parameterMap.get("P_BBS_SN") == null || parameterMap.get("P_BBS_SN").equals("")){
			String thisRequestUrl = parameterMap.get("requestUrl").toString();
			parameterMap = (FwkParameterMap) request.getSession().getAttribute(PARAMETER_MAP);
			parameterMap.put("requestUrl", thisRequestUrl);
			request.getSession().removeAttribute(PARAMETER_MAP);
		}
		
		
		
		FwkTransferObject trans = iproRssService.rssDetail(parameterMap);
		
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
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.rss.controller.IprorssController.java
	 * @Method : rssRegistForm
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/rssRegistForm")
	public String rssRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproRssService.usrInfoDetail(parameterMap);
		
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
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.rss.controller.IprorssController.java
	 * @Method : rssRegist
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/rssRegist", method=RequestMethod.POST)
	public String rssRegist(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);

		try{
			FwkTransferObject trans = iproRssService.rssRegist(parameterMap);
			
			//Log 저장
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "NOTI");
			parameterMap.put("P_CONN_CNTN", "계약자료실 작성");
			parameterMap.put("P_CONN_URL", "/noti/rssRegistForm.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
					
			iproCommDefaultService.sendLOG(parameterMap);
			
			//기존에 사용한 세션이 있는경우 삭제
			if(request.getSession().getAttribute("resourceName") != null){
				request.getSession().removeAttribute("resourceName");
			}
			request.getSession().setAttribute("resourceName", (String)parameterMap.get("resourceName"));
			
//			CommVo commVo = new CommVo();
//			commVo.setpBbsSn(trans.get("P_BBS_SN_TRANS").toString());
//			
//			//기존에 사용한 세션이 있는경우 삭제
//			if(request.getSession().getAttribute("commVo") != null){
//				request.getSession().removeAttribute("commVo");
//			}
//			request.getSession().setAttribute("commVo", commVo);
//			
//			redirectAttributes.addFlashAttribute( "parameterMap", parameterMap );

			request.getSession().setAttribute("parameterMap", parameterMap);
			
		}catch(Exception ex){
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		return "redirect:/noti/rssDetail.do";
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.noti.controller.IproRssController.java
	 * @Method : rssUpdtForm
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/rssUpdtForm")
	public String rssUpdtForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproRssService.rssDetail(parameterMap);
		
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
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.rss.controller.IprorssController.java
	 * @Method : rssUpdt
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rssUpdt", method=RequestMethod.POST)
	public String rssUpdt(final HttpServletRequest request, final Model model, HttpServletResponse response,RedirectAttributes redirectAttributes) throws Exception {			
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);

		try{
			FwkTransferObject trans = iproRssService.rssUpdt(parameterMap);
			
			//Log 저장
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "NOTI");
			parameterMap.put("P_CONN_CNTN", "계약자료실 수정");
			parameterMap.put("P_CONN_URL", "/noti/rssUpdtForm.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
					
			iproCommDefaultService.sendLOG(parameterMap);
			
			//기존에 사용한 세션이 있는경우 삭제
			if(request.getSession().getAttribute("resourceName") != null){
				request.getSession().removeAttribute("resourceName");
			}
			request.getSession().setAttribute("resourceName", (String)parameterMap.get("resourceName"));
			
//			CommVo commVo = new CommVo();
//			commVo.setpBbsSn(trans.get("P_BBS_SN_TRANS").toString());
//			
//			//기존에 사용한 세션이 있는경우 삭제
//			if(request.getSession().getAttribute("commVo") != null){
//				request.getSession().removeAttribute("commVo");
//			}
//			request.getSession().setAttribute("commVo", commVo);
//			
//			redirectAttributes.addFlashAttribute( "parameterMap", parameterMap );
			
			request.getSession().setAttribute("parameterMap", parameterMap);
			
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return "redirect:/noti/rssDetail.do";
		
	}
}
