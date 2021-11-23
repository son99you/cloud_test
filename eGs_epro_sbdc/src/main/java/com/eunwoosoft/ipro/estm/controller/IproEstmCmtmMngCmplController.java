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
import com.eunwoosoft.ipro.estm.service.IproEstmCmplService;
import com.eunwoosoft.ipro.estm.service.IproEstmCmtmMngCmplService;

/**
 * 평가관리 > 평가위원관리 > 평가위원평가완료현황 Controller
 * com.eunwoosoft.ipro.estm.controller
 * IproEstmCmtmMngCmplController.java
 *
 * @Author : 
 * @Date   : 2021. 3. 23.
 */
@Controller
@RequestMapping(value = "/estm")
public class IproEstmCmtmMngCmplController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/estm/";
	String prefixPopUrl = "/ipro/views/estm/popup/";
	
	@Resource(name="iproEstmCmtmMngCmplService")
	private IproEstmCmtmMngCmplService iproEstmCmtmMngCmplService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	@Resource(name="iproEstmCmplService")
	private IproEstmCmplService iproEstmCmplService;
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가완료현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmcmtmMngCmplController.java
	 * @Method : cmtmMngCmplList
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngCmplList")	
	public String cmtmMngCmplList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmMngCmplService.cmtmCmplList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가완료현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmcmtmMngCmplController.java
	 * @Method : cmtmMngCmplListExcelDwld
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngCmplListExcelDwld")	
	public String cmtmMngCmplListExcelDwld(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmMngCmplService.cmtmCmplListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("cmtmCmplList")); // trans에서 뽑아내는 데이터를 넣어준다.
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","평가위원평가완료현황 목록"); // 각자에 맞는 리스트명을 넣는다.
		model.addAttribute("excelGbn","POI");
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return EXCEL_VIEW;
	}
	
	
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가완료현황 상세 - 기본정보
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmcmtmMngCmplController.java
	 * @Method : cmtmMngCmplDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngCmplDetail")	
	public String cmtmMngCmplDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = iproEstmCmtmMngCmplService.cmtmMngCmplDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가완료현황 상세 - 평가대상
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmcmtmMngCmplController.java
	 * @Method : cmtmMngCmplObjDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngCmplObjDetail")	
	public String cmtmMngCmplObjDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = iproEstmCmtmMngCmplService.cmtmMngCmplObjDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가완료현황 상세 - 평가위원
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmcmtmMngCmplController.java
	 * @Method : cmtmMngCmplCmtmDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngCmplCmtmDetail")	
	public String cmtmMngCmplCmtmDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = iproEstmCmtmMngCmplService.cmtmMngCmplCmtmDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가완료현황 상세 - 서류평가
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmcmtmMngCmplController.java
	 * @Method : cmtmMngCmplProcdADetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngCmplProcdADetail")	
	public String cmtmMngCmplProcdADetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = iproEstmCmtmMngCmplService.cmtmMngCmplProcdADetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가완료현황 상세 - 정량평가
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmcmtmMngCmplController.java
	 * @Method : cmtmMngCmplProcdBDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngCmplProcdBDetail")	
	public String cmtmMngCmplProcdBDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = iproEstmCmtmMngCmplService.cmtmMngCmplProcdBDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가완료현황 상세 - 평가결과
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmcmtmMngCmplController.java
	 * @Method : cmtmMngCmplResultDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngCmplResultDetail")	
	public String cmtmMngCmplResultDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = iproEstmCmtmMngCmplService.cmtmMngCmplResultDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 :평가위원평가완료현황 상세  - 평가결과 - 엑셀다운로드
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : cmtmMngCmplResultDetailExcelDwld
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngCmplResultDetailExcelDwld")	
	public String cmtmMngCmplResultDetailExcelDwld(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmMngCmplService.cmtmMngCmplResultDetailExcelDwld(parameterMap);
		
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
	 * 1.개요 : 평가위원평가완료현황 상세 - 화상회의
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmcmtmMngCmplController.java
	 * @Method : cmtmMngCmplVidoMtngDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngCmplVidoMtngDetail")	
	public String cmtmMngCmplVidoMtngDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = iproEstmCmtmMngCmplService.cmtmMngCmplVidoMtngDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가완료현황 상세 - 수당지급
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmplController.java
	 * @Method : estmCmplExbePayDetail
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngCmplExbePayDetail")	
	public String cmtmMngCmplExbePayDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmplService.estmCmplExbePayDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가완료현황 상세 - 수당지급
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmplController.java
	 * @Method : estmCmplExbePayDetail
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngCmplExbePaySave")	
	public String cmtmMngCmplExbePaySave(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmplService.estmCmplExbePaySave(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		//return parameterMap.getViewName(prefixUrl);
		return "forward:/estm/cmtmMngCmplExbePayDetail.do";
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가완료현황 상세 - 평가종료취소
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmplController.java
	 * @Method : estmCmplExbePayDetail
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/cmtmCmplEndCnclUpdt")
	public String cmtmCmplEndCnclUpdt(final HttpServletRequest request, final Model model)throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		iproEstmCmplService.estmCmplEndCnclUpdt(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "ESTM");
		parameterMap.put("P_CONN_CNTN", "평가대상진행상태 변경");
		parameterMap.put("P_CONN_URL", "/estm/cmtmMngCmplList.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
		
		iproCommDefaultService.sendLOG(parameterMap);
		
		return JSON_VIEW;
	}
	
	
}
