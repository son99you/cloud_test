package com.eunwoosoft.ipro.noti.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.ipro.noti.service.IproNotiService;

/**
 * 
 * com.eunwoosoft.ipro.noti.controller
 * IproNotiController.java
 *
 * @Author : JuYeon_Lee
 * @Date   : 2017. 6. 13.
 *
 */
@RequestMapping(value = "/noti")
@Controller
public class IproNotiController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/noti/";
	
	@Resource(name="iproNotiService")
	private IproNotiService iproNotiService;

	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.noti.controller.IproNotiController.java
	 * @Method : notiList
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notiList" , method={RequestMethod.POST, RequestMethod.GET})
	public String notiList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproNotiService.notiListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.noti.controller.IproNotiController.java
	 * @Method : notiDelete
	 * @author : JanDi_Eun 
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/notiDelete")
	public String notiDelete(final HttpServletRequest request, final Model model,  RedirectAttributes redirectAttributes) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproNotiService.notiDelete(parameterMap);
		
		//Log 저장
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "NOTI");
		parameterMap.put("P_CONN_CNTN", "공지사항 삭제");
		parameterMap.put("P_CONN_URL", "/noti/notiDetail.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAttribute("trans", trans); 
		model.addAllAttributes(parameterMap);
		
		redirectAttributes.addFlashAttribute("parameterMap", parameterMap );
		
		return JSON_VIEW;
	}
	
	
	/** 
	 * 
	 * <pre>
	 * 1.개요 :  
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.noti.controller.IproNotiController.java
	 * @Method : notiDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/notiDetail", method={RequestMethod.POST, RequestMethod.GET})
	public String notiDetail(final HttpServletRequest request, final Model model,  RedirectAttributes redirectAttributes){
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		// 리다이렉트 상세화면 이동을 위해 분기처리
//		Object obj = RequestContextUtils.getInputFlashMap(request);
//		if(obj != null) {
//			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
//			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
//		}else {
//			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
//		}
		
		if(parameterMap.get("P_BBS_SN") == null || parameterMap.get("P_BBS_SN").equals("")){
			String thisRequestUrl = parameterMap.get("requestUrl").toString();
			parameterMap = (FwkParameterMap) request.getSession().getAttribute(PARAMETER_MAP);
			parameterMap.put("requestUrl", thisRequestUrl);
			request.getSession().removeAttribute(PARAMETER_MAP);
		}
		
		
		FwkTransferObject trans = iproNotiService.notiDetail(parameterMap);
//		System.out.println("@@@ faq 컨트롤러 시작@@@");
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans); 
//		System.out.println("@@@ faq 컨트롤러 끝@@@");
		return parameterMap.getViewName(prefixUrl);
	}
	

	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.noti.controller.IproNotiController.java
	 * @Method : notiRegistForm
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/notiRegistForm")
	public String notiRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

//		FwkTransferObject trans = iproEstmProgService.estmProgList(parameterMap);
		
//		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.noti.controller.IproNotiController.java
	 * @Method : notiRegist
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notiRegist", method=RequestMethod.POST)
	public String notiRegist(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproNotiService.notiRegist(parameterMap);
			
			//Log 저장
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "NOTI");
			parameterMap.put("P_CONN_CNTN", "공지사항 작성");
			parameterMap.put("P_CONN_URL", "/noti/notiRegistForm.do");
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
//			redirectAttributes.addFlashAttribute("parameterMap",parameterMap);
			
			/**
			 * session에 parameterMap값을 담아둔다.
			 */
			parameterMap.put("P_BBS_SN", trans.get("P_BBS_SN_TRANS").toString());
			request.getSession().setAttribute("parameterMap", parameterMap);
			 
		}catch(Exception ex){
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}	
		model.addAllAttributes(trans);
		return "redirect:/noti/notiDetail.do";
	}
	
	
	@RequestMapping(value="/notiUpdtForm", method=RequestMethod.POST)
	public String notiUpdtForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproNotiService.notiDetail(parameterMap);
		
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
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.noti.controller.IproNotiController.java
	 * @Method : notiUpdt
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/notiUpdt", method=RequestMethod.POST)
	public String notiUpdt(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {			
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproNotiService.notiUpdt(parameterMap);
			
			//Log 저장
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "NOTI");
			parameterMap.put("P_CONN_CNTN", "공지사항 수정");
			parameterMap.put("P_CONN_URL", "/noti/notiUpdtForm.do");
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
			/**
			 * session에 parameterMap값을 담아둔다.
			 */
			request.getSession().setAttribute("parameterMap", parameterMap);
	
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		//model.addAllAttributes(trans);
		return "redirect:/noti/notiDetail.do";
	}
	
	@RequestMapping(value="/notiListExcelDwld")
	public String notiListExcelDwld(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproNotiService.notiListExcelDwld(parameterMap);
		
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("dataList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","공지사항 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
}
