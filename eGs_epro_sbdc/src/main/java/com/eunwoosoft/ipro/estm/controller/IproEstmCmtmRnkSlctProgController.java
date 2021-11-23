package com.eunwoosoft.ipro.estm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.ipro.estm.service.IproEstmCmtmRnkSlctProgService;

/**
 * 평가관리 > 평가위원순위선정진행현황 Controller
 * com.eunwoosoft.ipro.estm.controller
 * IproEstmCmtmRnkSlctProgController.java
 *
 * @Author : 
 * @Date   : 2021. 3. 11.
 */
@Controller
@RequestMapping(value = "/estm")
public class IproEstmCmtmRnkSlctProgController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/estm/";
	String prefixPopUrl = "/ipro/views/estm/popup/";
	
	@Resource(name="iproEstmCmtmRnkSlctProgService")
	private IproEstmCmtmRnkSlctProgService iproEstmCmtmRnkSlctProgService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원순위선정진행현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmRnkSlctProgController.java
	 * @Method : cmtmRnkSlctProgList
	 * @author : 
	 * @date : 2021. 3. 11.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmRnkSlctProgList")	
	public String cmtmRnkSlctProgList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproEstmCmtmRnkSlctProgService.cmtmRnkSlctProgList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원순위선정진행현황 목록 - 엑셀다운로드
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmRnkSlctProgController.java
	 * @Method : cmtmRnkSlctProgListExcelDwld
	 * @author : 
	 * @date : 2021. 3. 11.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmRnkSlctProgListExcelDwld")	
	public String cmtmRnkSlctProgListExcelDwld(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproEstmCmtmRnkSlctProgService.cmtmRnkSlctProgListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("cmtmRnkSlctProgList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","평가위원순위선정진행현황 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원순위선정진행현황 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmRnkSlctProgController.java
	 * @Method : cmtmRnkSlctProgDetail
	 * @author : 
	 * @date : 2021. 3. 19.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/cmtmRnkSlctProgDetail")
	public String cmtmRnkSlctProgDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmRnkSlctProgService.cmtmRnkSlctProgDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getViewName(prefixUrl);
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원 우선순위자동선별
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmRnkSlctProgController.java
	 * @Method : cmtmPrioRnkAutoSlct
	 * @author : 
	 * @date : 2021. 4. 15.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmPrioRnkAutoSlct")
	public String cmtmPrioRnkAutoSlct(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		iproEstmCmtmRnkSlctProgService.cmtmPrioRnkAutoSlct(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "ESTM");
		parameterMap.put("P_CONN_CNTN", "평가위원순위선정진행현황 우선순위자동선별");
		parameterMap.put("P_CONN_URL", "/estm/cmtmRnkSlctProgDetail.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));

		iproCommDefaultService.sendLOG(parameterMap);
		
		
		
		return JSON_VIEW;
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원선정차수에 따른 평가위원 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmRnkSlctProgController.java
	 * @Method : estmCmtmSlctNgrDetail
	 * @author : 
	 * @date : 2021. 4. 15.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmSlctNgrDetail")
	public String estmCmtmSlctNgrDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		iproEstmCmtmRnkSlctProgService.estmCmtmSlctNgrDetail(parameterMap);
		return JSON_VIEW;
	}
	
}
