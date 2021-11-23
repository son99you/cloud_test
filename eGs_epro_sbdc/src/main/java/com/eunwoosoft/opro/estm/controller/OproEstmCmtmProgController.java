package com.eunwoosoft.opro.estm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.opro.estm.service.OproEstmCmtmProgService;

/**
 * 평가관리 > 평가진행현황 Controller
 * com.eunwoosoft.opro.estm.controller
 * OproEstmCmtmProgController.java
 *
 * @Author : 
 * @Date   : 2021. 4. 16.
 */
@Controller
@RequestMapping(value = "/opro/estm")
public class OproEstmCmtmProgController extends AbstractOproMenuController  {
	String prefixUrl = "/opro/views/estm/";
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	@Resource(name="oproEstmCmtmProgService")
	private OproEstmCmtmProgService oproEstmCmtmProgService;
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.estm.controller.OproEstmCmtmProgController.java
	 * @Method : estmCmtmProgList
	 * @author : 
	 * @date : 2021. 4. 16.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmProgList" , method={RequestMethod.POST, RequestMethod.GET})
	public String estmCmtmProgList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		
		FwkTransferObject trans = oproEstmCmtmProgService.estmCmtmProgList(parameterMap);

		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 기본정보
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.estm.controller.OproEstmCmtmProgController.java
	 * @Method : estmCmtmProgDetail
	 * @author : 
	 * @date : 2021. 4. 16.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmProgDetail", method={RequestMethod.POST, RequestMethod.GET})
	public String estmCmtmProgDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans  = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_ESTM_CMTM_NO", session.get("USR_ID"));
		
		/**
		 * request에 키값이 되는 값이 담겨있지 않으면 redirct로 넘어왔다고 판단하고
		 * request에 담겨있는 parameterMap값을 request.session에 담겨있는 parameterMap값으로  바꿔준다.
		 * 그리고 request.session에 담겨있는 requestUrl값을 먼저담겨있는 request.parameterMap 의 requestUrl으로 바꿔준다.
		 * 그래야지 jsp페이지를 정상적으로 찾을 수 있음. 
		 */
		if(parameterMap.get("P_ESTM_NO") == null || parameterMap.get("P_ESTM_NO").equals("")){
			String thisRequestUrl = parameterMap.get("requestUrl").toString();
			parameterMap = (FwkParameterMap) request.getSession().getAttribute(PARAMETER_MAP);
			parameterMap.put("requestUrl", thisRequestUrl);
			request.getSession().removeAttribute(PARAMETER_MAP);
		}
		
		trans = oproEstmCmtmProgService.estmCmtmProgDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	//서류평가 및 품명회 
	@RequestMapping(value = "/estmCmtmProgProcdADetail")	
	public String estmCmtmProgProcdADetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEstmCmtmProgService.estmCmtmProgProcdADetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}

	
	//화상회의
	@RequestMapping(value = "/estmCmtmProgVidoMtngDetail")	
	public String estmCmtmProgVidoMtngDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEstmCmtmProgService.estmCmtmProgVidoMtngDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}

	//심사위원 첨부파일 저장
	@RequestMapping(value = "/estmCmtmfileRegist")	
	public String estmCmtmfileRegist(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
//		try {
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);
			
			FwkTransferObject trans = oproEstmCmtmProgService.estmCmtmfileRegist(parameterMap);
					
			//기존에 사용한 세션이 있는경우 삭제
			if(request.getSession().getAttribute("resourceName") != null){
				request.getSession().removeAttribute("resourceName");
			}
			request.getSession().setAttribute("resourceName", (String)parameterMap.get("resourceName"));
			
//			CommVo commVo = new CommVo();
//			commVo.setpEstmNo((String)trans.get("P_ESTM_NO_TRANS"));
//			//기존에 사용한 세션이 있는경우 삭제
//			if(request.getSession().getAttribute("commVo") != null){
//				request.getSession().removeAttribute("commVo");
//			}
//			request.getSession().setAttribute("commVo", commVo);
//			
//			redirectAttributes.addFlashAttribute("parameterMap", parameterMap);
			
			request.getSession().setAttribute("parameterMap", parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "심사위원 첨부파일 저장");
			parameterMap.put("P_CONN_URL", "/opro/estm/estmCmtmProgDetail.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
					
			iproCommDefaultService.sendLOG(parameterMap);
			
//		} catch (Exception ex) {
//			response.setStatus(999); 
//			throw new Exception(ex.toString());
//		}
		
		return "redirect:/opro/estm/estmCmtmProgDetail.do";
	}

	//심사위원 첨부파일 수정
	@RequestMapping(value = "/estmCmtmfileUpdt")	
	public String estmCmtmfileUpdt(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
//		try {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		parameterMap.put("multipartRequest", multipartRequest);
		
		FwkTransferObject trans = oproEstmCmtmProgService.estmCmtmfileUpdt(parameterMap);
		
		//기존에 사용한 세션이 있는경우 삭제
		if(request.getSession().getAttribute("resourceName") != null){
			request.getSession().removeAttribute("resourceName");
		}
		request.getSession().setAttribute("resourceName", (String)parameterMap.get("resourceName"));
		
//		CommVo commVo = new CommVo();
//		commVo.setpEstmNo((String)trans.get("P_ESTM_NO_TRANS"));
//		//기존에 사용한 세션이 있는경우 삭제
//		if(request.getSession().getAttribute("commVo") != null){
//			request.getSession().removeAttribute("commVo");
//		}
//		request.getSession().setAttribute("commVo", commVo);
//		
//		redirectAttributes.addFlashAttribute("parameterMap", parameterMap);
		
		request.getSession().setAttribute("parameterMap", parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "ESTM");
		parameterMap.put("P_CONN_CNTN", "심사위원 첨부파일 수정");
		parameterMap.put("P_CONN_URL", "/opro/estm/estmCmtmProgDetail.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
		
//		} catch (Exception ex) {
//			response.setStatus(999); 
//			throw new Exception(ex.toString());
//		}
		
		return "redirect:/opro/estm/estmCmtmProgDetail.do";
	}
	
	/**
	 * <pre>
	 * 1.개요 : 외부 평가완료현황 목록 - 엑셀다운로드
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmProgController.java
	 * @Method : estmCmtmCmplListExcelDwld
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmProgListExcelDwld")	
	public String estmCmtmProgListExcelDwld(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEstmCmtmProgService.estmCmtmProgListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("dataList")); // trans에서 뽑아내는 데이터를 넣어준다.
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","평가진행현황 목록"); // 각자에 맞는 리스트명을 넣는다.
		model.addAttribute("excelGbn","POI");
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return EXCEL_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 심사위원 서명파일 전자서명
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.estm.controller.OproEstmCmtmProgController.java
	 * @Method : estmCmtmSign
	 * @author : 
	 * @date : 2021. 5. 12.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmSign")	
	public String estmCmtmSign(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try {
			trans =  oproEstmCmtmProgService.estmCmtmSign(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "심사위원 서명파일 전자서명");
			parameterMap.put("P_CONN_URL", "/opro/estm/estmCmtmProgDetail.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}
}
