package com.eunwoosoft.ipro.estm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.ipro.estm.service.IproEstmProgService;

/**
 * 평가관리 > 평가진행현황 Controller
 * com.eunwoosoft.ipro.estm.controller
 * IproEstmProgController.java
 *
 * @Author : 
 * @Date   : 2021. 3. 11.
 */
@Controller
@RequestMapping(value = "/estm")
public class IproEstmProgController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/estm/";
	String prefixPopUrl = "/ipro/views/estm/popup/";
	String prefixExcelUrl = "/ipro/";
	
	@Resource(name="iproEstmProgService")
	private IproEstmProgService iproEstmProgService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgList
	 * @author : 
	 * @date : 2021. 3. 11.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProgList")	
	public String estmProgList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
		if("1".equals(user.get("AUTH_ID"))){	//관리자
			//전체조회 가능
		}else if("2".equals(user.get("AUTH_ID"))){	//일반사용자
			
			parameterMap.put("P_DEPT_NO", user.getString("DEPT_NO"));
			parameterMap.put("P_ESTM_CHRG_DEPT_NM_S", user.getString("DEPT_NM"));
			parameterMap.put("P_ESTM_CHRG_DEPT_NO_S", user.getString("DEPT_NO"));
			
		}else if("3".equals(user.get("AUTH_ID"))){	//평가위원순위선정자(감사실)
			
			parameterMap.put("P_DEPT_NO", user.getString("DEPT_NO"));
			parameterMap.put("P_ESTM_CHRG_DEPT_NM_S", user.getString("DEPT_NM"));
			parameterMap.put("P_ESTM_CHRG_DEPT_NO_S", user.getString("DEPT_NO"));
			
		}else if("4".equals(user.get("AUTH_ID"))){	//평가위원담당자(마케팅기획팀)
			
			parameterMap.put("P_DEPT_NO", user.getString("DEPT_NO"));
			parameterMap.put("P_ESTM_CHRG_DEPT_NM_S", user.getString("DEPT_NM"));
			parameterMap.put("P_ESTM_CHRG_DEPT_NO_S", user.getString("DEPT_NO"));
			
		}
		
		trans = iproEstmProgService.estmProgList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgList
	 * @author : 
	 * @date : 2021. 3. 11.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProgListExcelDwld")	
	public String estmProgListExcelDown(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.estmProgListExcelDwld(parameterMap);
		
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
//		model.addAttribute("EXCEL_X_LIST",trans2.get("EXCEL_X_LIST"));
//		model.addAttribute("EXCEL_Y_LIST",trans2.get("EXCEL_Y_LIST"));
		
		
		
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("estmProgList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","평가진행현황 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가대상 템플릿(양식) 다운로드
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : excelTemplatDwld
	 * @author : 
	 * @date : 2021. 3. 11.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/excelTemplatDwld")	
	public String excelTemplatDwld(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		//FwkTransferObject trans = iproEstmProgService.estmProgListExcelDwld(parameterMap);
		
		
		// 엑셀다운로드시 필수
		//FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
//		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
//		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
//		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
//		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
//		model.addAttribute("EXCEL_X_LIST",trans2.get("EXCEL_X_LIST"));
//		model.addAttribute("EXCEL_Y_LIST",trans2.get("EXCEL_Y_LIST"));
		
		
		
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		//model.addAttribute("dataList",trans.get("estmProgList"));
		if(parameterMap.get("P_ESTM_OBJ_SECD").toString().equals("A")) {
			model.addAttribute("templateFileName", "objTemplateA.xls");
			model.addAttribute("destFileName","평가대상양식다운로드(업체)");
		}else if(parameterMap.get("P_ESTM_OBJ_SECD").toString().equals("B")) {
			model.addAttribute("templateFileName", "objTemplateB.xls");
			model.addAttribute("destFileName","평가대상양식다운로드(상품)");
		}else if(parameterMap.get("P_ESTM_OBJ_SECD").toString().equals("C")) {
			model.addAttribute("templateFileName", "objTemplateC.xls");
			model.addAttribute("destFileName","평가대상양식다운로드(사람)");
		}
				
		
		model.addAttribute("excelGbn","TEMPLATE");
		
//		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 작성
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgRegistForm
	 * @author : 
	 * @date : 2021. 3. 11.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProgRegistForm")	
	public String estmProgRegistForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
		parameterMap.put("P_ESTM_CHRGR_NM", user.getString("USR_NM"));   // 평가담당자명
		parameterMap.put("P_ESTM_CHRGR_ID", user.getString("USR_ID"));   // 평가담당자아이디
		parameterMap.put("P_ESTM_CHRG_DEPT_NM", user.getString("DEPT_NM"));   // 평가담당부서명
		parameterMap.put("P_ESTM_CHRG_DEPT_NO", user.getString("DEPT_NO"));   // 평가담당부서번호
		
		parameterMap.put("P_REAL_ESTM_YN", "Y");   // 실평가여부 : Y
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 기본정보
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgDetail
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProgDetail")	
	public String estmProgDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
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
		
		FwkTransferObject trans = iproEstmProgService.estmProgDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 평가대상
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgObjDetail
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProgObjDetail")	
	public String estmProgObjDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.estmProgObjDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 엑셀업로드
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : excelObjUpload
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/excelObjUpload")	
	public String excelObjUpload(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		parameterMap.put("multipartRequest", multipartRequest);
		
		FwkTransferObject trans = iproEstmProgService.excelObjUpload(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		/**
		 * session에 parameterMap값을 담아둔다.
		 */
		request.getSession().setAttribute("parameterMap", parameterMap);
	
		return "redirect:/estm/estmProgObjDetail.do";
		//return parameterMap.getViewName(prefixUrl);
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 평가위원
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgCmtmDetail
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProgCmtmDetail")	
	public String estmProgCmtmDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.estmProgCmtmDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - Tab 동적화면 통합
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgProcdADetail
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProgProcdADetail")	
	public String estmProgProcdADetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.estmProgProcdADetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 정량평가
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgProcdBDetail
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProgProcdBDetail")	
	public String estmProgProcdBDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.estmProgProcdBDetail(parameterMap);
		
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
	 * @Method : estmSeMngClcRulSave
	 * @author : 손연우
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmMngMstClcRulSave")
	public String estmMngMstClcRulSave(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.estmMngMstClcRulSave(parameterMap);
		
		trans.put("resultCode", "Success");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		//return parameterMap.getViewName(prefixUrl);
		return "forward:/estm/estmProgResultDetail.do";
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 평가결과
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgResultDetail
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProgResultDetail")	
	public String estmProgResultDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.estmProgResultDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 평가결과 - 엑셀다운로드
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgResultDetail
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProgResultDetailExcelDwld")	
	public String estmProgResultDetailExcelDwld(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.estmProgResultDetailExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_X_LIST",trans2.get("EXCEL_X_LIST"));
		model.addAttribute("EXCEL_Y_LIST",trans2.get("EXCEL_Y_LIST"));
		model.addAttribute("estmResultProcdSeqList",trans.get("estmResultProcdSeqList"));
		model.addAttribute("estmResultItemAllList",trans.get("estmResultItemAllList"));
		model.addAttribute("estmResultItemNoList",trans.get("estmResultItemNoList"));
		model.addAttribute("estmResultCmtmAllList",trans.get("estmResultCmtmAllList"));
		model.addAttribute("estmResultObjAllList",trans.get("estmResultObjAllList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","평가결과");		
		model.addAttribute("excelGbn","POI_RESULT");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
	
	
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 화상회의
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgVidoMtngDetail
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProgVidoMtngDetail", method={RequestMethod.POST, RequestMethod.GET})
	public String estmProgVidoMtngDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		/**
		 * request에 키값이 되는 값이 담겨있지 않으면 redirct로 넘어왔다고 판단하고
		 * request에 담겨있는 parameterMap값을 request.session에 담겨있는 parameterMap값으로  바꿔준다.
		 * 그리고 request.session에 담겨있는 requestUrl값을 먼저담겨있는 request.parameterMap 의 requestUrl으로 바꿔준다.
		 * 그래야지 jsp페이지를 정상적으로 찾을 수 있음. 
		 */
		
		if(parameterMap.get("P_ESTM_NO") == null || parameterMap.get("P_ESTM_NO").equals("")){
			String thisRequestUrl = parameterMap.get("requestUrl").toString();
			
			System.err.println("thisRequestUrl :: " + thisRequestUrl);
			
			parameterMap = (FwkParameterMap) request.getSession().getAttribute(PARAMETER_MAP);
			
			System.err.println("parameterMap :: " + parameterMap.toString());
			
			parameterMap.put("requestUrl", thisRequestUrl);
			request.getSession().removeAttribute(PARAMETER_MAP);
		}
		
		HttpSession	session	= request.getSession();
		FwkDataEntity loginSession = (FwkDataEntity) session.getAttribute( "loginResult" );
		
		System.out.println("loginSession.toString() :::: " + loginSession.toString());
		
		System.out.println("loginSession.toString(USR_ID) :::: " + loginSession.getString("USR_ID"));
		
		if(loginSession.get("USR_ID") != null) {
			parameterMap.put("P_USR_ID", loginSession.get("USR_ID")); // 세션 사용자아이디
		}else {
			parameterMap.put("P_USR_ID", loginSession.get("USER_ID")); // 세션 사용자아이디
		}
		
		FwkTransferObject trans = iproEstmProgService.estmProgVidoMtngDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 수당지급 개인정보삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmRsdnNoDelete
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmRsdnNoDelete")	
	public String estmRsdnNoDelete(final HttpServletRequest request, HttpServletResponse response, final Model model, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
//		try{
			iproEstmProgService.estmRsdnNoDelete(parameterMap);
			
			//기존에 사용한 세션이 있는경우 삭제
			if(request.getSession().getAttribute("resourceName") != null){
				request.getSession().removeAttribute("resourceName");
			}
			
			request.getSession().setAttribute("resourceName", (String)parameterMap.get("resourceName"));
			
			/**
			 * session에 parameterMap값을 담아둔다.
			 */
			request.getSession().setAttribute("parameterMap", parameterMap);
//		}catch(Exception ex){
//			response.setStatus(999); 
//			throw new Exception(ex.toString());
//		}
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가위원 개인정보삭제");
			parameterMap.put("P_CONN_URL", "/estm/estmRsdnNoDelete.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
					
			iproCommDefaultService.sendLOG(parameterMap);
			
		
		return "redirect:/estm/estmCmplExbePayDetail.do";
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 화상회의 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgVidoMtngSave
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProgVidoMtngSave")	
	public String estmProgVidoMtngSave(final HttpServletRequest request, HttpServletResponse response, final Model model, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
//		try{
			FwkTransferObject trans = iproEstmProgService.estmProgVidoMtngSave(parameterMap);
			
			//기존에 사용한 세션이 있는경우 삭제
			if(request.getSession().getAttribute("resourceName") != null){
				request.getSession().removeAttribute("resourceName");
			}
			
			request.getSession().setAttribute("resourceName", (String)parameterMap.get("resourceName"));
			
			/**
			 * session에 parameterMap값을 담아둔다.
			 */
			request.getSession().setAttribute("parameterMap", parameterMap);
//		}catch(Exception ex){
//			response.setStatus(999); 
//			throw new Exception(ex.toString());
//		}
		
		return "redirect:/estm/estmProgVidoMtngDetail.do";
	}
	
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 작성
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgRegist
	 * @author : 
	 * @date : 2021. 3. 25.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmProgRegist", method=RequestMethod.POST)
	public String estmProgRegist(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
//		try{
			
			MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);		

			trans = iproEstmProgService.estmProgRegist(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가진행현황 작성");
			parameterMap.put("P_CONN_URL", "/estm/estmProgRegistForm.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
					
			iproCommDefaultService.sendLOG(parameterMap);
			
			
			//기존에 사용한 세션이 있는경우 삭제
			if(request.getSession().getAttribute("resourceName") != null){
				request.getSession().removeAttribute("resourceName");
			}
			request.getSession().setAttribute("resourceName", (String)parameterMap.get("resourceName"));
			
//			CommVo commVo = new CommVo();
//			commVo.setpEstmNo((String)trans.get("P_ESTM_NO_TRANS"));
//			
//			//기존에 사용한 세션이 있는경우 삭제
//			if(request.getSession().getAttribute("commVo") != null){
//				request.getSession().removeAttribute("commVo");
//			}
//			request.getSession().setAttribute("commVo", commVo);
//			
//			redirectAttributes.addFlashAttribute("parameterMap", parameterMap);

			request.getSession().setAttribute("parameterMap", parameterMap);
			
//		}catch(Exception ex){
//			response.setStatus(999); 
//			throw new Exception(ex.toString());
//		}
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "redirect:/estm/estmProgDetail.do";
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 회의실생성
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : createMeetingRoom
	 * @author : 
	 * @date : 2021. 3. 31.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/createMeetingRoom")	
	public String createMeetingRoom(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.createMeetingRoom(parameterMap);
		
		/*model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);*/
		
		/**
		 * session에 parameterMap값을 담아둔다.
		 */
		request.getSession().setAttribute("parameterMap", parameterMap);
	
		return "redirect:/estm/estmProgVidoMtngDetail.do";
		
		
	}
	
	
	
	

	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmDelete
	 * @author : 
	 * @date : 2021. 3. 30.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmDelete")
	public String estmDelete(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		iproEstmProgService.estmDelete(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "ESTM");
		parameterMap.put("P_CONN_CNTN", "평가진행현황 삭제");
		parameterMap.put("P_CONN_URL", "/estm/estmProgDetail.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행상태코드 update
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmPscdUpdt
	 * @author : 
	 * @date : 2021. 3. 31.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmPscdUpdt")
	public String estmPscdUpdt(final HttpServletRequest request, final Model model) throws Exception {	
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		iproEstmProgService.estmPscdUpdt(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "ESTM");
		parameterMap.put("P_CONN_CNTN", "평가대상진행상태 변경");
		parameterMap.put("P_CONN_URL", "/estm/estmProgDetail.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
		
		iproCommDefaultService.sendLOG(parameterMap);
		
		return JSON_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgUpdtForm
	 * @author : 
	 * @date : 2021. 3. 31.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProgUpdtForm")	
	public String estmProgUpdtForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.estmProgDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgUpdt
	 * @author : 
	 * @date : 2021. 4. 01.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmProgUpdt", method=RequestMethod.POST)
	public String estmProgUpdt(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			
			MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);
			
			trans = iproEstmProgService.estmProgUpdt(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가진행현황 수정");
			parameterMap.put("P_CONN_URL", "/estm/estmProgUpdtForm.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
					
			iproCommDefaultService.sendLOG(parameterMap);
			
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		String redirectURL = parameterMap.getString("P_RETURN_URL");
		System.err.println("@@@ redirect:" + parameterMap.get("P_RETURN_URL"));

		return "redirect:" + redirectURL;
		//return "redirect:/estm/estmProgDetail.do";
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 평가대상 작성
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgObjRegist
	 * @author : 
	 * @date : 2021. 4. 01.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProgObjRegist")
	public String estmProgObjRegist(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
//		try {
			
			iproEstmProgService.estmProgObjRegist(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가대상 작성");
			parameterMap.put("P_CONN_URL", "/estm/estmProgObjDetail.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
			iproCommDefaultService.sendLOG(parameterMap);
			
//		} catch (Exception ex) {
//			response.setStatus(999); 
//			throw new Exception(ex.toString());
//		}
		
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 평가대상 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProgObjDelete
	 * @author : 
	 * @date : 2021. 4. 01.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProgObjDelete")
	public String estmProgObjDelete(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			
			iproEstmProgService.estmProgObjDelete(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가대상 삭제");
			parameterMap.put("P_CONN_URL", "/estm/estmProgObjDelete.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		
		return JSON_VIEW;
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 평가대상불러오기
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmCntcObjList
	 * @author : 
	 * @date : 2021. 4. 01.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCntcObjList")
	public String estmCntcObjList(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			
			iproEstmProgService.estmCntcObjList(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가대상불러오기");
			parameterMap.put("P_CONN_URL", "/estm/estmProgObjDetail.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		
		return JSON_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가대상정보 첨부파일 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmObjFileUpdt
	 * @author : 
	 * @date : 2021. 4. 02.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmObjFileUpdt")
	public String estmFileUpdt(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			
			iproEstmProgService.estmObjFileUpdt(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가대상정보 첨부파일 수정");
			parameterMap.put("P_CONN_URL", "/comm/popup/estmObjFileUpdtForm.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가대상정보 첨부파일 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmVidoFileUpdt
	 * @author : 
	 * @date : 2021. 4. 02.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmVidoFileUpdt")
	public String estmVidoFileUpdt(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			
			iproEstmProgService.estmVidoFileUpdt(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "화상회의정보 첨부파일 수정");
			parameterMap.put("P_CONN_URL", "/comm/popup/estmVidoFileUpdtForm.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가절차진행상태코드 update
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmProcdPscdUpdt
	 * @author : 
	 * @date : 2021. 4. 08.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmProcdPscdUpdt")
	public String estmProcdPscdUpdt(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			iproEstmProgService.estmProcdPscdUpdt(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가절차진행상태 변경");
			parameterMap.put("P_CONN_URL", "/estm/estmProgDetail.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		
		return JSON_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가분야구분코드 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : getEstmSpheSecd
	 * @author : 
	 * @date : 2021. 4. 12.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEstmSpheSecd", method=RequestMethod.POST)
	public String getMMCtgrDtlde(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		
		FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
		if("1".equals(user.get("AUTH_ID"))){	//관리자
			//전체조회 가능
		}else if("2".equals(user.get("AUTH_ID"))){	//일반사용자
			
			parameterMap.put("P_REGR_DEPT_NO_S", user.getString("DEPT_NO"));
			
		}else if("3".equals(user.get("AUTH_ID"))){	//평가위원순위선정자(감사실)
			
			parameterMap.put("P_REGR_DEPT_NO_S", user.getString("DEPT_NO"));
			
		}else if("4".equals(user.get("AUTH_ID"))){	//평가위원담당자(마케팅기획팀)
			
			parameterMap.put("P_REGR_DEPT_NO_S", user.getString("DEPT_NO"));
		}
		
		model.addAllAttributes(iproEstmProgService.getEstmSpheSecd(parameterMap));
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원 선정/비선정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmCmtmSlctYnUpdt
	 * @author : 
	 * @date : 2021. 4. 15.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmSlctYnUpdt")
	public String estmCmtmSlctYnUpdt(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			iproEstmProgService.estmCmtmSlctYnUpdt(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가위원 선정/비선정");
			parameterMap.put("P_CONN_URL", "/estm/estmProgCmtmDetail.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		
		return JSON_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 우선순위재선정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmCmtmPrioRnkReSlct
	 * @author : 
	 * @date : 2021. 4. 16.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmPrioRnkReSlct")
	public String estmCmtmPrioRnkReSlct(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "redirect:/estm/estmProgCmtmDetail.do";
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원 자동선별
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmCmtmAutoSlct
	 * @author : 
	 * @date : 2021. 4. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmAutoSlct")
	public String estmCmtmAutoSlct(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			iproEstmProgService.estmCmtmAutoSlct(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가위원 자동선별");
			parameterMap.put("P_CONN_URL", "/estm/estmProgCmtmDetail.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원 재 자동선별
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmCmtmReAutoSlct
	 * @author : 
	 * @date : 2021. 4. 29.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmReAutoSlct")
	public String estmCmtmReAutoSlct(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			iproEstmProgService.estmCmtmReAutoSlct(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가위원 재 자동선별");
			parameterMap.put("P_CONN_URL", "/estm/estmProgCmtmDetail.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원 정보 ajax 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmCmtmSetJson
	 * @author : 
	 * @date : 2021. 4. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmSetJson")
	public String estmCmtmSetJson(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.estmCmtmSetDetail(parameterMap);
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}
	

	
	/**
	 * <pre>
	 * 1.개요 : 평가위원저장 - 수기입력
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmCmtmRegist
	 * @author : 
	 * @date : 2021. 4. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmRegist")
	public String estmCmtmRegist(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			iproEstmProgService.estmCmtmRegist(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가위원저장");
			parameterMap.put("P_CONN_URL", "/estm/estmProgCmtmDetail.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 기본정보 : 평가위원선정완료여부 체크 ( 평가시작가능여부 조회 )
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmStPsblYnChck
	 * @author : 
	 * @date : 2021. 4. 26.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmStPsblYnChck")
	public String estmStPsblYnChck(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = new FwkTransferObject();
		
		try {
			trans = iproEstmProgService.estmStPsblYnChck(parameterMap);	
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 평가위원 : 평가위원선정완료
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmCmtmSlctCmplYnUpdt
	 * @author : 
	 * @date : 2021. 4. 26.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmSlctCmplYnUpdt")
	public String estmCmtmSlctCmplYnUpdt(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			iproEstmProgService.estmCmtmSlctCmplYnUpdt(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가위원선정완료");
			parameterMap.put("P_CONN_URL", "/estm/estmProgCmtmDetail.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 - 평가구분에 따른 평가절차 목록 ajax 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmSecdProcdSetJson
	 * @author : 
	 * @date : 2021. 4. 26.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmSecdProcdSetJson")
	public String estmSecdProcdSetJson(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.estmSecdProcdSetList(parameterMap);
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 - 평가구분에 따른 평가절차 목록 ajax 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmSecdSetJson
	 * @author : 
	 * @date : 2021. 4. 26.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmSecdSetJson")
	public String estmSecdSetJson(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.estmSecdSetList(parameterMap);
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 평가종료
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmEnd
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmAEnd")
	public String estmEnd(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.estmEnd(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		//return parameterMap.getViewName(prefixUrl);
		return "forward:/estm/estmProgProcdADetail.do";
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 평가대상선정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmASlct
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmASlct")
	public String estmASlct(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.estmASlct(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		//return parameterMap.getViewName(prefixUrl);
		return "forward:/estm/estmProgProcdADetail.do";
	}

	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 평가대상선정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmASlct
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmBSlct")
	public String estmBSlct(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmProgService.estmASlct(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		//return parameterMap.getViewName(prefixUrl);
		return "forward:/estm/estmProgProcdBDetail.do";
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 평가위원 : 주민등록번호 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmCmtmRsdnNoRegist
	 * @author : 
	 * @date : 2021. 5. 04.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmRsdnNoRegist")
	public String estmCmtmRsdnNoRegist(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			iproEstmProgService.estmCmtmRsdnNoRegist(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가위원 주민등록번호 저장");
			parameterMap.put("P_CONN_URL", "/estm/estmProgCmtmDetail.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}
	



	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 평가위원 : 재직여부 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmCmtmHldfYnUpdt
	 * @author : 
	 * @date : 2021. 5. 07.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmHldfYnUpdt")
	public String estmCmtmHldfYnUpdt(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			iproEstmProgService.estmCmtmHldfYnUpdt(parameterMap);	
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가위원 재직여부 저장");
			parameterMap.put("P_CONN_URL", "/estm/estmProgCmtmDetail.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가시작
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmStUpdt
	 * @author : 
	 * @date : 2021. 5. 11.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmStUpdt")
	public String estmStUpdt(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try {
			trans =  iproEstmProgService.estmStUpdt(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가시작");
			parameterMap.put("P_CONN_URL", "/estm/estmProgDetail.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
					
			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 평가위원 : 주민등록번호 저장 가능 체크
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : cmtmRsdnNoRegistPsblChck
	 * @author : 
	 * @date : 2021. 5. 17.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmRsdnNoRegistPsblChck")
	public String cmtmRsdnNoRegistPsblChck(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = new FwkTransferObject();
		
		try {
			trans = iproEstmProgService.cmtmRsdnNoRegistPsblChck(parameterMap);
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}
	
}
