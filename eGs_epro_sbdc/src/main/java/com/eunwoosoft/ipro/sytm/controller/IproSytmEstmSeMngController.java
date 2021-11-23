package com.eunwoosoft.ipro.sytm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.ipro.sytm.service.IproSytmEstmSeMngService;


/**
 * 
 * com.eunwoosoft.ipro.sytm.controller
 * IproSytmEstmSeMngController.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 28.
 *
 */
@RequestMapping(value = "/sytm")
@Controller
public class IproSytmEstmSeMngController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/sytm/";
	String popupPrefixUrl = "/ipro/views/sytm/popup/";
	
	@Resource(name="iproSytmEstmSeMngService")
	private IproSytmEstmSeMngService iproSytmEstmSeMngService;

	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmSeMngController.java
	 * @Method : estmSeMngList
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmSeMngList")
	public String estmSeMngList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		//<input type="hidden" id="P_ESTM_CHRG_DEPT_NO_S" name="P_ESTM_CHRG_DEPT_NO_S" value="${param.P_ESTM_CHRG_DEPT_NO_S}">
		//loginResult={USR_ID=user2, USR_NM=사용자2, EMPL_NO=user2, DEPT_NO=421100, DEPT_NM=공공구매지원센터, ARA_DEPT_CD=42, OFPS_CD=1A, OFPS_NM=1급, EMAL_ADDR=user2@sbdc.or.kr, AUTH_ID=2, STORE=42, ORGAN=1101, P_SSO_STATUS=OK, CONN_IP=0:0:0:0:0:0:0:1, roleList=[ABID_MNGR, GBID_MNGR], P_CONN_IP=0:0:0:0:0:0:0:1, resourceName=IEP13002}
		if(parameterMap.get("P_ESTM_CHRG_DEPT_NO_S")== null && !"1".equals(session.get("AUTH_ID"))) {
			parameterMap.put("P_ESTM_CHRG_DEPT_NO_S", session.get("DEPT_NO"));
			parameterMap.put("P_ESTM_CHRG_DEPT_NM_S", session.get("DEPT_NM"));
		}
		
		FwkTransferObject trans = iproSytmEstmSeMngService.estmSeMngListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가구분관리 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmSeMngController.java
	 * @Method : estmSeMngDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmSeMngDetail")
	public String estmSeMngDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmEstmSeMngService.estmSeMngDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmSeMngController.java
	 * @Method : estmSeMngUpdtForm
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmSeMngUpdtForm")
	public String estmSeMngUpdtForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmEstmSeMngService.estmSeMngDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmSeMngController.java
	 * @Method : estmSeMngDelete
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmSeMngDelete")
	public String estmSeMngDelete(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmEstmSeMngService.estmSeMngDelete(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "SYTM");
		parameterMap.put("P_CONN_CNTN", "평가구분관리 삭제");
		parameterMap.put("P_CONN_URL", "/estm/estmSeMngList.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
		
		trans.put("resultCode", "Success");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return "forward:/sytm/estmSeMngList.do";
		
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmSeMngController.java
	 * @Method : estmSeMngClcRulSave
	 * @author : 손연우
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmSeMngClcRulSave")
	public String estmSeMngClcRulSave(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmEstmSeMngService.estmSeMngClcRulSave(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "SYTM");
		parameterMap.put("P_CONN_CNTN", "평가구분관리 산술저장");
		parameterMap.put("P_CONN_URL", "/estm/estmSeMngDetail.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
		
		trans.put("resultCode", "Success");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		//return parameterMap.getViewName(prefixUrl);
		return "forward:/sytm/estmSeMngDetail.do";
	}
	
	
	
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmSeMngController.java
	 * @Method : estmSeMngRegForm
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmSeMngRegForm")
	public String estmSeMngRegForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmEstmSeMngService.estmSeMngRegForm(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmContFormController.java
	 * @Method : estmSeMngRegist
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmSeMngRegist")
	public String estmSeMngRegist(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_APPC_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
		parameterMap.put("multipartRequest", multipartRequest);
			
		trans = iproSytmEstmSeMngService.estmSeMngRegist(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "SYTM");
		parameterMap.put("P_CONN_CNTN", "평가구분관리 등록");
		parameterMap.put("P_CONN_URL", "/sytm/estmSeMngRegForm.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
			
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		//return "forward:/sytm/estmSeMngDetail.do";
		return "redirect:/sytm/estmSeMngDetail.do";
		
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmContFormController.java
	 * @Method : estmSeMngRegist
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmSeMngUpdt")
	public String estmSeMngUpdt(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_APPC_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
		parameterMap.put("multipartRequest", multipartRequest);
			
		trans = iproSytmEstmSeMngService.estmSeMngUpdt(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "SYTM");
		parameterMap.put("P_CONN_CNTN", "평가구분관리 수정");
		parameterMap.put("P_CONN_URL", "/sytm/estmSeMngUpdtForm.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
		
		iproCommDefaultService.sendLOG(parameterMap);
			
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "redirect:/sytm/estmSeMngDetail.do";
		
	}
	
	@RequestMapping(value="/estmSeMngListExcelDwld")
	public String estmSeMngListExcelDwld(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproSytmEstmSeMngService.estmSeMngListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("dataList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","평가구분관리 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
	
	
	
}
