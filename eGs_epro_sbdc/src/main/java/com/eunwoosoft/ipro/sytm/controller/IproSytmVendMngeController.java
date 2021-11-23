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
import com.eunwoosoft.ipro.sytm.service.IproSytmVendMngeService;

/**
 * 
 * com.eunwoosoft.ipro.sytm.controller
 * IproSytmVendMngeController.java
 *
 * @Author : chanil_Hong
 * @Date   : 2017. 6. 13.
 *
 */
@Controller
@RequestMapping(value = "/sytm")
public class IproSytmVendMngeController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/sytm/";
	String prefixUrl2 = "/ipro/views/sytm/popup/";
	
	@Resource(name="iproSytmVendMngeService")
	private IproSytmVendMngeService iproSytmVendMngeService;

	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체정보관리 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmVendMngeController.java
	 * @Method : vendMngeList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 13. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendMngeList")
	public String vendMngeList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		trans = iproSytmVendMngeService.vendMngeListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
//		System.out.println("prefixUrl Controller@@ ::" + prefixUrl);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 업체정보관리 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmVendMngeController.java
	 * @Method : vendMngeDetail
	 * @author : chanil_Hong
	 * @date : 2017. 6. 13. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/vendMngeDetail" , method={RequestMethod.POST, RequestMethod.GET})
	public String vendMngeDetail(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmVendMngeService.vendMngeDetailInqire(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}

	@RequestMapping(value="/vendMngeListExcelDwld" )
	public String vendMngeListExcelDwld(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproSytmVendMngeService.vendMngeExcelList(parameterMap);
		
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("dataList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","업체정보관리 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
	
}
