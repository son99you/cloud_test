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
import com.eunwoosoft.ipro.estm.service.IproEstmCmtmProgService;

/**
 * 평가관리 > 평가진행현황_평가위원 Controller
 * com.eunwoosoft.ipro.estm.controller
 * IproEstmCmtmProgController.java
 *
 * @Author : 
 * @Date   : 2021. 3. 23.
 */
@Controller
@RequestMapping(value = "/estm")
public class IproEstmCmtmProgController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/estm/";
	String prefixPopUrl = "/ipro/views/estm/popup/";
	
	@Resource(name="iproEstmCmtmProgService")
	private IproEstmCmtmProgService iproEstmCmtmProgService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황_평가위원 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmProgController.java
	 * @Method : estmCmtmProgList
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmProgList")	
	public String estmCmtmProgList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmProgService.estmCmtmProgList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황_평가위원 목록 엑셀다운로드
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmProgController.java
	 * @Method : estmCmtmProgListExcelDwld
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmProgListExcelDwld")	
	public String estmCmtmProgListExcelDwld(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmProgService.estmCmtmProgListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("estmCmtmProgList")); // trans에서 뽑아내는 데이터를 넣어준다.
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","평가진행현황_평가위원 목록"); // 각자에 맞는 리스트명을 넣는다.
		model.addAttribute("excelGbn","POI");
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return EXCEL_VIEW;
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
	@RequestMapping(value = "/cmtmMngCreateMeetingRoom")	
	public String cmtmMngCreateMeetingRoom(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmProgService.cmtmMngCreateMeetingRoom(parameterMap);
		
		/*model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);*/
		
		/**
		 * session에 parameterMap값을 담아둔다.
		 */
		request.getSession().setAttribute("parameterMap", parameterMap);
	
		return "redirect:/estm/cmtmMngProgVidoMtngDetail.do";
		
		
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황_평가위원 상세 - 기본정보
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmProgController.java
	 * @Method : estmCmtmProgDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmProgDetail")	
	public String estmCmtmProgDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans =iproEstmCmtmProgService.estmCmtmProgDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황_평가위원 상세 - 서류평가
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmProgController.java
	 * @Method : estmCmtmProgProcdADetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmProgProcdADetail")	
	public String estmCmtmProgProcdADetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmProgService.estmCmtmProgProcdADetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}

	@RequestMapping(value = "/estmCmtmProgProcdBDetail")	
	public String estmCmtmProgProcdBDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmProgService.estmCmtmProgProcdBDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황_평가위원 상세 - 평가결과
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmProgController.java
	 * @Method : estmCmtmProgResultDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmProgResultDetail")	
	public String estmCmtmProgResultDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmProgService.estmCmtmProgResultDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황_평가위원 상세 - 평가결과 - 엑셀다운로드
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : estmCmtmProgResultDetailExcelDwld
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmProgResultDetailExcelDwld")	
	public String estmCmtmProgResultDetailExcelDwld(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmProgService.estmCmtmProgResultDetailExcelDwld(parameterMap);
		
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
	 * 1.개요 : 평가진행현황_평가위원 상세 - 화상회의
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmProgController.java
	 * @Method : estmCmtmProgVidoMtngDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmProgVidoMtngDetail")	
	public String estmCmtmProgVidoMtngDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmProgService.estmCmtmProgVidoMtngDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
		
}
