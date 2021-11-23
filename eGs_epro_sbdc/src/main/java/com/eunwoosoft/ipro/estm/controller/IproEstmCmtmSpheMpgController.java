package com.eunwoosoft.ipro.estm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.ipro.estm.service.IproEstmCmtmSpheMpgService;

/**
 * 평가관리 > 평가위원분야매핑 Controller
 * com.eunwoosoft.ipro.estm.controller
 * IproEstmCmtmSpheMpgController.java
 *
 * @Author : 
 * @Date   : 2021. 3. 22.
 */
@Controller
@RequestMapping(value = "/estm")
public class IproEstmCmtmSpheMpgController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/estm/";
	String prefixPopUrl = "/ipro/views/estm/popup/";
	
	@Resource(name="iproEstmCmtmSpheMpgService")
	private IproEstmCmtmSpheMpgService iproEstmCmtmSpheMpgService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원분야매핑 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmSpheMpgController.java
	 * @Method : estmCmtmSpheMpgList
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmSpheMpgList")	
	public String estmCmtmSpheMpgList(final HttpServletRequest request, final Model model) throws Exception {		
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

		FwkTransferObject trans = iproEstmCmtmSpheMpgService.estmCmtmSpheMpgList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원분야매핑 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmSpheMpgController.java
	 * @Method : estmCmtmSpheMpgDetail
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmSpheMpgDetail")	
	public String estmCmtmSpheMpgDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproEstmCmtmSpheMpgService.estmCmtmSpheMpgDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원분야매핑 수정폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmSpheMpgController.java
	 * @Method : estmCmtmSpheMpgUpdtForm
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmSpheMpgUpdtForm")	
	public String estmCmtmSpheMpgUpdtForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproEstmCmtmSpheMpgService.estmCmtmSpheMpgItemList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원분야매핑 등록폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmSpheMpgController.java
	 * @Method : estmCmtmSpheMpgRegForm
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmSpheMpgRegForm")	
	public String estmCmtmSpheMpgRegForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproEstmCmtmSpheMpgService.estmCmtmSpheMpgItemList(parameterMap);
		
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
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmSpheMpgController.java
	 * @Method : estmCmtmSpheMpgRegist
	 * @author : 손연우
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmCmtmSpheMpgRegist")
	public String estmSeMngRegist(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

			
		trans = iproEstmCmtmSpheMpgService.estmCmtmSpheMpgRegist(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "ESTM");
		parameterMap.put("P_CONN_CNTN", "평가위원분야매핑 작성");
		parameterMap.put("P_CONN_URL", "/estm/estmCmtmSpheMpgRegist.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
			
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "forward:/estm/estmCmtmSpheMpgDetail.do";
		
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
	@RequestMapping(value="/estmCmtmSpheMpgUpdt")
	public String estmCmtmSpheMpgUpdt(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

			
		//trans = 
				iproEstmCmtmSpheMpgService.estmCmtmSpheMpgUpdt(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "ESTM");
		parameterMap.put("P_CONN_CNTN", "평가위원분야매핑 수정");
		parameterMap.put("P_CONN_URL", "/estm/estmCmtmSpheMpgUpdtForm.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
			
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		//return "forward:/estm/estmCmtmSpheMpgDetail.do";
		return JSON_VIEW;
		
	}
	
	
	@RequestMapping(value= "/cmtmSpheMpgListExcelDwld")
	public String cmtmSpheMpgListExcelDwld(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		System.out.println("controllerExcelDwld::test중");
		FwkTransferObject trans = iproEstmCmtmSpheMpgService.cmtmSpheMpgListExcelDwld(parameterMap);
		
		//엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("estmCmtmSpheMpgList")); // trans에서 뽑아내는 데이터를 넣어준다.
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","평가위원분야매핑 목록"); // 각자에 맞는 리스트명을 넣는다.
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return EXCEL_VIEW;
	}
	
}
