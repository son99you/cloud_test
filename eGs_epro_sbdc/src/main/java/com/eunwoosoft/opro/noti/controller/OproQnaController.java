package com.eunwoosoft.opro.noti.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.comm.vo.CommVo;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.opro.noti.service.OproQnaService;

/**
 * 
 * com.eunwoosoft.opro.noti.controller
 * OproQnaController.java
 *
 * @Author : JuYeon_Lee
 * @Date   : 2017. 6. 13.
 *
 */
@RequestMapping(value = "/opro/noti")
@Controller
public class OproQnaController extends AbstractOproMenuController {
	static final String prefixUrl = "/opro/views/noti/";
	 
	@Resource(name="oproQnaService") 
	private OproQnaService oproQnaService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;

	/**
	 * 
	 * <pre>
	 * 1.개요 : 자료실 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.noti.controller.IproNotiController.java
	 * @Method :qnaList
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 14.
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/qnaList", method={RequestMethod.POST, RequestMethod.GET})
	public String qnaList(final HttpServletRequest request, final Model model)throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproQnaService.qnaListWithPgng(parameterMap);
		model.addAllAttributes(trans); 
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl); 
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 내부 공지사항 상세 
	 * 2.처리내용 :  
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.noti.controller.IproNotiController.java
	 * @Method : inNoticeBoardDetail
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 14. 
	 * @param request
	 * @param model
	 * @return
	 */  
	
	@RequestMapping(value="/qnaDetail", method={RequestMethod.POST, RequestMethod.GET})
	public String qnaDetail(final HttpServletRequest request, final Model model){
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
		
		FwkTransferObject trans = oproQnaService.qnaDetail(parameterMap);
		
		model.addAllAttributes(parameterMap); 
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	@RequestMapping(value="/qnaRegistForm", method=RequestMethod.POST)
	public String qnaRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		System.out.println("qna등록컨트롤러 ::" );
		FwkTransferObject trans = oproQnaService.usrInfoDetail(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	@RequestMapping(value="/qnaRegist", method=RequestMethod.POST)
	public String qnaRegist(final HttpServletRequest request, final Model model, HttpServletResponse response,RedirectAttributes redirectAttributes) throws Exception{
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		System.out.println("qna등록컨트롤러 ::" );
		try{
			trans = oproQnaService.qnaRegist(parameterMap, request); 
		
			//Log 저장
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "NOTI");
			parameterMap.put("P_CONN_CNTN", "QNA 작성");
			parameterMap.put("P_CONN_URL", "/opro/noti/qnaRegistForm.do");
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
		
		
		return "redirect:/opro/noti/qnaDetail.do";
	}
	
	@RequestMapping(value="/qnaUpdtForm", method=RequestMethod.POST)
	public String qnaUpdtForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproQnaService.qnaDetail(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	
	@RequestMapping(value = "/qnaUpdt", method=RequestMethod.POST)
	public String qnaUpdt(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {
		
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			
			System.err.println("@@@ qnaUpdt cont start");
			
			trans = oproQnaService.qnaUpdt(parameterMap);
			
			System.err.println("@@@ qnaUpdt cont end");
			
			//Log 저장
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "NOTI");
			parameterMap.put("P_CONN_CNTN", "QNA 수정");
			parameterMap.put("P_CONN_URL", "/opro/noti/qnaUpdtForm.do");
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
		model.addAllAttributes(trans);
		return "redirect:/opro/noti/qnaDetail.do";
		
	}
		
	@RequestMapping(value="/qnaDelete")
	public String rsqnaDeletesDelete(final HttpServletRequest request, final Model model,  RedirectAttributes redirectAttributes) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		 
		FwkTransferObject trans = oproQnaService.qnaDelete(parameterMap);
		
		//Log 저장
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "NOTI");
		parameterMap.put("P_CONN_CNTN", "QNA 삭제");
		parameterMap.put("P_CONN_URL", "/opro/noti/qnaDetail.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAttribute("trans", trans); 
		model.addAllAttributes(parameterMap);
		
		redirectAttributes.addFlashAttribute( "parameterMap", parameterMap );
		
		return JSON_VIEW;
	}
	
	@RequestMapping(value="/qnaListExcelDwld")
	public String qnaListExcelDwld(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = oproQnaService.qnaListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("dataList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","QNA 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
	
}
