package com.eunwoosoft.opro.estm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.opro.estm.service.OproEstmCmtmCmplService;

/**
 * 평가관리 > 평가완료현황 Controller
 * com.eunwoosoft.opro.estm.controller
 * OproEstmCmtmCmplController.java
 *
 * @Author : 
 * @Date   : 2021. 4. 29.
 */
@RequestMapping(value = "/opro/estm")
@Controller
public class OproEstmCmtmCmplController extends AbstractOproMenuController  {
	String prefixUrl = "/opro/views/estm/";
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	@Resource(name="oproEstmCmtmCmplService")
	private OproEstmCmtmCmplService oproEstmCmtmCmplService;
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가완료현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.estm.controller.OproEstmCmtmCmplController.java
	 * @Method : estmCmtmCmplList
	 * @author : 
	 * @date : 2021. 4. 29.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmCmplList" )
	public String estmCmtmCmplList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = oproEstmCmtmCmplService.estmCmtmCmplList(parameterMap);

		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 기본정보
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.estm.controller.OproEstmCmtmCmplController.java
	 * @Method : estmCmtmCmplDetail
	 * @author : 
	 * @date : 2021. 4. 29.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmCmplDetail" )
	public String estmCmtmCmplDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEstmCmtmCmplService.estmCmtmCmplDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	//서류평가 및 품명회 
	@RequestMapping(value = "/estmCmtmCmplProcdADetail")	
	public String estmCmtmCmplProcdADetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEstmCmtmCmplService.estmCmtmCmplProcdADetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}

	@RequestMapping(value = "/estmCmtmCmplProcdBDetail")	
	public String estmCmtmCmplProcdBDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEstmCmtmCmplService.estmCmtmCmplProcdBDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	//평가결과
	@RequestMapping(value = "/estmCmtmCmplResultDetail")	
	public String estmCmtmCmplResultDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		//FwkTransferObject trans = new FwkTransferObject();
		
		//model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	
	//화상회의
	@RequestMapping(value = "/estmCmtmCmplVidoMtngDetail")	
	public String estmCmtmCmplVidoMtngDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEstmCmtmCmplService.estmCmtmCmplVidoMtngDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
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
	@RequestMapping(value = "/estmCmtmCmplListExcelDwld")	
	public String estmCmtmCmplListExcelDwld(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEstmCmtmCmplService.estmCmtmCmplListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("dataList")); // trans에서 뽑아내는 데이터를 넣어준다.
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","평가완료현황 목록"); // 각자에 맞는 리스트명을 넣는다.
		model.addAttribute("excelGbn","POI");
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return EXCEL_VIEW;
	}
}
