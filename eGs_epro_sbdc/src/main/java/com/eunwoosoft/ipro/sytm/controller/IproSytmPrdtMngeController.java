package com.eunwoosoft.ipro.sytm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.ipro.sytm.service.IproSytmPrdtMngeService;

@Controller
@RequestMapping(value = "/sytm")
public class IproSytmPrdtMngeController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/sytm/";
	String prefixUrl2 = "/ipro/views/sytm/popup/";
	
	@Resource(name="iproSytmPrdtMngeService")
	private IproSytmPrdtMngeService iproSytmPrdtMngeService;

	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	/**
	 * 
	 * <pre>
	 * 1.개요 : 메뉴관리 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.sytm.controller.IproSytmUserMngeController.java
	 * @Method : menuMgrList
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 14. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	
	//목록
	@RequestMapping(value="/prdtMngList")
	public String prdtMngList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmPrdtMngeService.prdtMngeListInqireWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getViewName(prefixUrl);
	}
	
	//상세
	@RequestMapping(value="/prdtMngDetail" , method={RequestMethod.POST, RequestMethod.GET})
	public String prdtMngDetail(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
	
		FwkTransferObject trans = iproSytmPrdtMngeService.prdtMngDetailInqire(parameterMap);

		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	@RequestMapping(value = "/prdtMngListExcelDwld")	
	public String prdtMngListExcelDwld(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproSytmPrdtMngeService.prdtMngListExcelDwld(parameterMap);
		
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("dataList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","상품관리 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
}
