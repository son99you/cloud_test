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
import com.eunwoosoft.ipro.sytm.service.IproSytmAuthMngeService;


/**
 * 
 * com.eunwoosoft.ipro.sytm.controller
 * IproSytmAuthMngeController.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 4. 2.
 *
 */
@Controller
@RequestMapping(value = "/sytm")
public class IproSytmAuthMngeController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/sytm/";
	
	@Resource(name="iproSytmAuthMngeService")
	private IproSytmAuthMngeService iproSytmAuthMngeService;

	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;

	/**
	 * 
	 * <pre>
	 * 1.개요 : 권한관리 목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.controller.IproSytmAuthMngeController.java
	 * @Method : authMgrList
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/authMgrList")
	public String authMgrList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmAuthMngeService.authMgrListWithPgng(parameterMap);
		
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getViewName(prefixUrl);
	}

	@RequestMapping(value="/authMgrListExcelDwld")
	public String authMgrListExcelDwld(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmAuthMngeService.authMgrListExcelDwld(parameterMap);

		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("dataList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","권한관리 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 권한관리 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : authMgrDetail
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/authMgrDetail")
	public String authMgrDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproSytmAuthMngeService.authMenuMgrListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 권한관리 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : authMgrDetail
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/authMgrUpdtForm")
	public String authMgrUpdtForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproSytmAuthMngeService.authMenuMgrListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmSpheMpgController.java
	 * @Method : estmCmtmSpheMpgUpdt
	 * @author : 손연우
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/authMgrUpdt")
	public String authMgrUpdt(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

			
		//trans = 
			iproSytmAuthMngeService.authMgrUpdt(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "SYTM");
		parameterMap.put("P_CONN_CNTN", "권한관리 수정");
		parameterMap.put("P_CONN_URL", "/sytm/authMgrUpdtForm.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
			
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		//return "forward:/estm/estmCmtmSpheMpgDetail.do";
		return JSON_VIEW;
		
	}
	
	
	
}
