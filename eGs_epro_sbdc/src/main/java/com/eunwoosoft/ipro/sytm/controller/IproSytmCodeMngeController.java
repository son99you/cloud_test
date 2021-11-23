package com.eunwoosoft.ipro.sytm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.ipro.sytm.service.IproSytmCodeMngeService;

/**
 * 
 * com.eunwoosoft.ipro.sytm.controller
 * IproSytmCodeMngeController.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2017. 6. 14.
 *
 */
@RequestMapping(value = "/sytm")
@Controller
public class IproSytmCodeMngeController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/sytm/";
	
	@Resource(name="iproSytmCodeMngeService")
	private IproSytmCodeMngeService iproSytmCodeMngeService;

	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	/**
	 * <pre>
	 * 1.개요 : 코드관리 목록 
	 * 2.처리내용 :
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.sytm.controller.IproSytmCodeMngeController.java
	 * @Method : codeMngeList
	 * @author : JanDi_Eun
	 * @date : 2017. 6. 14. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/codeMngeList")
	public String codeMngeList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmCodeMngeService.codeMngeListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		
		model.addAttribute("myMenuList", super.myMenuList(request));
		model.addAttribute("myMenuSubList", super.myMenuSubList(request));
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 코드관리 상세 화면
	 * 2.처리내용 : 광해참조
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.sytm.controller.IproSytmCodeMngeController.java
	 * @Method : codeMngeDetail
	 * @author : JanDi_Eun
	 * @date : 2017. 6. 14. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/codeMngeDetail")
	public String codeMngeDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 코드관리 작성 화면
	 * 2.처리내용 : 광해참조
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.sytm.controller.IproSytmCodeMngeController.java
	 * @Method : codeMngeRegForm
	 * @author : JanDi_Eun
	 * @date : 2017. 7. 3. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/codeMngeRegForm")
	public String codeMngeRegForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	@RequestMapping(value="/codeMngeListExcelDwld")
	public String codeMngeListExcelDwld(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmCodeMngeService.codeMngeListExcelDwld(parameterMap);

		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("dataList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","코드관리 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
}
