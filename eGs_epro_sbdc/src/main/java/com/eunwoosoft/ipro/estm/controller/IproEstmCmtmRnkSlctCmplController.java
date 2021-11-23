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
import com.eunwoosoft.ipro.estm.service.IproEstmCmtmRnkSlctCmplService;

/**
 * 평가관리 > 평가위원순위선정완료현황 Controller
 * com.eunwoosoft.ipro.estm.controller
 * IproEstmCmtmRnkSlctCmplController.java
 *
 * @Author : 
 * @Date   : 2021. 3. 11.
 */
@Controller
@RequestMapping(value = "/estm")
public class IproEstmCmtmRnkSlctCmplController extends AbstractIproMenuController {
	
	String prefixUrl = "/ipro/views/estm/";
	String prefixPopUrl = "/ipro/views/estm/popup/";
	
	@Resource(name="iproEstmCmtmRnkSlctCmplService")
	private IproEstmCmtmRnkSlctCmplService iproEstmCmtmRnkSlctCmplService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원순위선정완료현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmRnkSlctCmplController.java
	 * @Method : cmtmRnkSlctCmplList
	 * @author : 
	 * @date : 2021. 3. 11.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmRnkSlctCmplList")	
	public String cmtmRnkSlctCmplList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmRnkSlctCmplService.cmtmRnkSlctCmplList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원순위선정완료현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmRnkSlctCmplController.java
	 * @Method : cmtmRnkSlctCmplList
	 * @author : 
	 * @date : 2021. 3. 11.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmRnkSlctCmplListExcelDwld")	
	public String cmtmRnkSlctCmplListExcelDwld(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmRnkSlctCmplService.cmtmRnkSlctCmplListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("cmtmRnkSlctCmplList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","평가위원순위선정완료현황 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원순위선정완료현황 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmRnkSlctProgController.java
	 * @Method : cmtmRnkSlctCmplDetail
	 * @author : 
	 * @date : 2021. 3. 19.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/cmtmRnkSlctCmplDetail")
	public String cmtmRnkSlctCmplDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmRnkSlctCmplService.cmtmRnkSlctCmplDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getViewName(prefixUrl);
	}

}
