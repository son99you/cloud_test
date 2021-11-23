package com.eunwoosoft.opro.noti.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.opro.noti.service.OproNotiGnrlService;

/**
 * 
 * com.eunwoosoft.opro.noti.controller
 * OproNotiGnrlController.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 29.
 *
 */
@RequestMapping(value = "/opro/noti")
@Controller
public class OproNotiGnrlController extends AbstractOproMenuController {
	String prefixUrl = "/opro/views/noti/";
	
	@Resource(name="oproNotiGnrlService")
	private OproNotiGnrlService oproNotiGnrlService;

	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 일반공지사항 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.noti.controller.OproNotiController.java
	 * @Method : notiList
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/notiList")
	public String notiList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		parameterMap.put("P_BBS_SECD", "NOTI");
	
		FwkTransferObject trans = oproNotiGnrlService.notiGnrlListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}

	@RequestMapping(value="/notiListExcelDwld")
	public String notiListExcelDwld(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		parameterMap.put("P_BBS_SECD", "NOTI");
		FwkTransferObject trans = oproNotiGnrlService.notiListExcelDwld(parameterMap);
		
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("dataList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","공지사항 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}

	@RequestMapping(value="/faqListExcelDwld")
	public String faqListExcelDwld(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		parameterMap.put("P_BBS_SECD", "FAQ");
		FwkTransferObject trans = oproNotiGnrlService.notiListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("dataList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","FAQ 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}

	/**
	 * 
	 * <pre>
	 * 1.개요 : 일반공지사항 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.noti.controller.OproNotiController.java
	 * @Method : notiDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 29. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/notiDetail")
	public String notiDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproNotiGnrlService.notiGnrlDetail(parameterMap);
		
		model.addAllAttributes(trans);
		//model.addAttribute("trans", trans); 
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰공지사항 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.noti.controller.OproNotiController.java
	 * @Method : bidNotiList
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bidNotiList")
	public String bidNotiList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		parameterMap.put("P_BBS_SECD", "BID_NOTI");
		FwkTransferObject trans = oproNotiGnrlService.notiGnrlListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰공지사항 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.noti.controller.OproNotiController.java
	 * @Method : bidNotiDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 29. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bidNotiDetail")
	public String bidNotiDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproNotiGnrlService.notiGnrlDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAttribute("trans", trans); 
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 계약자료실 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.noti.controller.OproNotiController.java
	 * @Method : rssList
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/rssList")
	public String rssList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		parameterMap.put("P_BBS_SECD", "CONT_BBS");
		FwkTransferObject trans = oproNotiGnrlService.notiGnrlListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 계약자료실 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.noti.controller.OproNotiController.java
	 * @Method : rssDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 29. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/rssDetail")
	public String rssDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproNotiGnrlService.notiGnrlDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAttribute("trans", trans); 
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 자주하는질문 목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.noti.controller.OproNotiController.java
	 * @Method : faqList
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/faqList")
	public String faqList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		parameterMap.put("P_BBS_SECD", "FAQ");
		FwkTransferObject trans = oproNotiGnrlService.notiGnrlListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 자주하는질문 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.noti.controller.OproNotiController.java
	 * @Method : faqDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 29. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/faqDetail")
	public String faqDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproNotiGnrlService.notiGnrlDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAttribute("trans", trans); 
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}

	
}