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
import com.eunwoosoft.ipro.noti.service.IproTrmService;

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
public class iproTrmController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/noti/";
	
	@Resource(name="iproTrmService")
	private IproTrmService iproTrmService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.noti.controller.IproTrmController.java
	 * @Method : trmList
	 * @author : jane
	 * @date : 2020. 10. 08. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/trmList" , method={RequestMethod.POST, RequestMethod.GET})
	public String trmList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproTrmService.trmListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.noti.controller.IproTrmController.java
	 * @Method : trmDelete
	* @author : jane
	 * @date : 2020. 10. 08. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/trmDelete")
	public String trmDelete(final HttpServletRequest request, final Model model,  RedirectAttributes redirectAttributes) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproTrmService.trmDelete(parameterMap);
		
		//Log 저장
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "NOTI");
		parameterMap.put("P_CONN_CNTN", "용어사전 삭제");
		parameterMap.put("P_CONN_URL", "/noti/trmDetail.do");
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
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.noti.controller.IproTrmController.java
	 * @Method : trmDetail
	 * @author : jane
	 * @date : 2020. 10. 08. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/trmDetail", method={RequestMethod.POST, RequestMethod.GET})
	public String trmDetail(final HttpServletRequest request, final Model model,  RedirectAttributes redirectAttributes){
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		/**
		 * request에 키값이 되는 값이 담겨있지 않으면 redirct로 넘어왔다고 판단하고
		 * request에 담겨있는 parameterMap값을 request.session에 담겨있는 parameterMap값으로  바꿔준다.
		 * 그리고 request.session에 담겨있는 requestUrl값을 먼저담겨있는 request.parameterMap 의 requestUrl으로 바꿔준다.
		 * 그래야지 jsp페이지를 정상적으로 찾을 수 있음. 
		 */
		if(parameterMap.get("P_TRM_SN") == null || parameterMap.get("P_TRM_SN").equals("")){
			String thisRequestUrl = parameterMap.get("requestUrl").toString();
			parameterMap = (FwkParameterMap) request.getSession().getAttribute(PARAMETER_MAP);
			parameterMap.put("requestUrl", thisRequestUrl);
			request.getSession().removeAttribute(PARAMETER_MAP);
		}
		
		FwkTransferObject trans = iproTrmService.trmDetail(parameterMap);
		
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans); 
		
		return parameterMap.getViewName(prefixUrl);
	}
	

	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.noti.controller.IproTrmController.java
	 * @Method : trmRegistForm
	 * @author : jane
	 * @date : 2020. 10. 08. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/trmRegistForm", method=RequestMethod.POST)
	public String trmRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproTrmService.usrInfoDetail(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.noti.controller.IproTrmController.java
	 * @Method : trmRegist
	 * @author : jane
	 * @date : 2020. 10. 08. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/trmRegist", method=RequestMethod.POST)
	public String trmRegist(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
				trans = iproTrmService.trmRegist(parameterMap);

				//Log 저장
				/**
				 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
				 * P_CONN_CNTN : 접속내용 (한글 코멘트)
				 * P_CONN_URL : 접속url
				 * P_MENU_ID : 메뉴ID (호출 함수명)
				 */
				parameterMap.put("P_SYS_CONN_SECD", "NOTI");
				parameterMap.put("P_CONN_CNTN", "용어사전 작성");
				parameterMap.put("P_CONN_URL", "/noti/trmRegistForm.do");
				parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
						
				iproCommDefaultService.sendLOG(parameterMap);
				
				//기존에 사용한 세션이 있는경우 삭제
				if(request.getSession().getAttribute("resourceName") != null){
					request.getSession().removeAttribute("resourceName");
				}
				request.getSession().setAttribute("resourceName", (String)parameterMap.get("resourceName"));
				
//				CommVo commVo = new CommVo();
//				commVo.setpBbsSn(trans.get("P_TRM_SN").toString());
//				
//				//기존에 사용한 세션이 있는경우 삭제
//				if(request.getSession().getAttribute("commVo") != null){
//					request.getSession().removeAttribute("commVo");
//				}
//				request.getSession().setAttribute("commVo", commVo);
//				
//				redirectAttributes.addFlashAttribute("parameterMap",parameterMap);
				
				request.getSession().setAttribute("parameterMap", parameterMap);
				 
			}catch(Exception ex){
				response.setStatus(999); 
				throw new Exception(ex.toString());
			}		 
			return "redirect:/noti/trmDetail.do";
	}
	
	
	@RequestMapping(value="/trmUpdtForm", method=RequestMethod.POST)
	public String trmUpdtForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproTrmService.trmDetail(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	

	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.noti.controller.IproTrmController.java
	 * @Method : trmUpdt
	 * @author : jane
	 * @date : 2020. 10. 08. 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/trmUpdt", method=RequestMethod.POST)
	public String notiUpdt(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {			
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproTrmService.trmUpdt(parameterMap);
			
			//Log 저장
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "NOTI");
			parameterMap.put("P_CONN_CNTN", "용어사전 수정");
			parameterMap.put("P_CONN_URL", "/noti/trmUpdtForm.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
					
			iproCommDefaultService.sendLOG(parameterMap);
			
			//기존에 사용한 세션이 있는경우 삭제
			if(request.getSession().getAttribute("resourceName") != null){
				request.getSession().removeAttribute("resourceName");
			}
			request.getSession().setAttribute("resourceName", (String)parameterMap.get("resourceName"));
			
//			CommVo commVo = new CommVo();
//			commVo.setpBbsSn(trans.get("P_TRM_SN").toString());
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
		
		return "redirect:/noti/trmDetail.do";
	}
	
	@RequestMapping(value = "/trmDataAll")
	public String wodiDataAll(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);		
		
		model.addAllAttributes(iproTrmService.trmDataAll(parameterMap));
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/trmCnt")
	public String trmCnt(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);		
		model.addAllAttributes(iproTrmService.trmCnt(parameterMap));
		return JSON_VIEW;
	}

	@RequestMapping(value = "/trmListExcelDwld")
	public String trmListExcelDwld(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproTrmService.trmListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("estmCmplList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","용어사전 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
	
}
