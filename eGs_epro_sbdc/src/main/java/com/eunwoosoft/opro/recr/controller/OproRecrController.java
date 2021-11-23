package com.eunwoosoft.opro.recr.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.opro.recr.service.OproRecrService;

/**
 * 
 * com.eunwoosoft.opro.recr.controller
 * OproRecrController.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 29.
 *
 */
@RequestMapping(value = "/opro/recr")
@Controller
public class OproRecrController extends AbstractOproMenuController {
	String prefixUrl = "/opro/views/recr/";
	
	@Resource(name="oproRecrService")
	private OproRecrService oproRecrService;

	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가공고(모집) 목록 - 크리에이터만 모집할 예정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.noti.controller.OproRecrController.java
	 * @Method : recrAnncList
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/recrAnncList" , method={RequestMethod.POST, RequestMethod.GET})
	public String recrAnncList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = oproRecrService.recrGnrlListWithPgng(parameterMap);
		
		model.addAllAttributes(trans); //데이터베이스 가져오기 위해서 필요
		model.addAllAttributes(parameterMap); //조회하기 위해서 필요
	
		return parameterMap.getOproViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가공고(모집) 목록 - 크리에이터만 모집할 예정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.noti.controller.OproRecrController.java
	 * @Method : recrAnncList
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/recrAnncListExcelDwld" , method={RequestMethod.POST, RequestMethod.GET})
	public String recrAnncListExcelDwld(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = oproRecrService.recrAnncListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("recrGnrlList")); // trans에서 뽑아내는 데이터를 넣어준다.
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","평가공고 목록"); // 각자에 맞는 리스트명을 넣는다.
		model.addAttribute("excelGbn","POI");
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return EXCEL_VIEW;
	}
	
	
	
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가공고(모집) 상세 - 크리에이터만 모집할 예정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.noti.controller.OproNotiController.java
	 * @Method : recrAnncDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 29. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/recrAnncDetail")
	public String recrAnncDetail(final HttpServletRequest request, final Model model)throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);	
		FwkTransferObject trans = oproRecrService.recrGnrlDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAttribute("trans", trans); 
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가공고(모집) 등록 - 크리에이터만 모집할 예정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.noti.controller.OproRecrController.java
	 * @Method : recrAnncRegForm
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 29. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	//평가공고  신청  폼 - 크리에이터  
	@RequestMapping(value="/recrAnncRegForm" , method=RequestMethod.POST)
	public String recrAnncRegForm(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);	
		FwkTransferObject trans = oproRecrService.recrGnrlDetail(parameterMap);

		model.addAllAttributes(trans); 
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	//평가공고 신청 중복확인 - 크리에이터 
	@RequestMapping(value="/recrAnncCheckReg", method=RequestMethod.POST)
	public String recrAnncCheckReg(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception{
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);	
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = oproRecrService.recrAnncCheck(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
			
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}
	
	//평가공고 신청 기능 - 크리에이터  
	@RequestMapping(value="/recrAnncReg", method=RequestMethod.POST)
	public String recrAnncReg(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes )throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		trans = oproRecrService.recrAnncReg(parameterMap);
		System.out.println("시작controller trans::" + trans);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		//접속 IP 처리
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		parameterMap.put("CONN_IP", ip);
		parameterMap.put("P_CONN_IP", ip);
		parameterMap.put("P_SYS_CONN_SECD", "RECR");
		parameterMap.put("P_CONN_CNTN", "평가공고 신청");
		parameterMap.put("P_CONN_URL","/opro/recr/recrAnncRegForm.do");
		parameterMap.put("P_MENU_ID", "recrAnncRegForm");
		
		iproCommDefaultService.sendLOG(parameterMap);
		
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		System.out.println("최종 controller parameterMap::" + parameterMap);
		
		return "redirect:/opro/recr/recrAnncList.do";
	}


	//평가공고 신청 폼 - 업체
	@RequestMapping(value="recrAnncEstmRegForm", method=RequestMethod.POST)
	public String recrAnncEstmRegForm(final HttpServletRequest request, final Model model) throws Exception{

		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);	
		FwkTransferObject trans = oproRecrService.recrGnrlDetail(parameterMap);
		
		model.addAllAttributes(trans); 
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	//평가공고 신청 기능 - 업체 
	@RequestMapping(value="recrAnncEstmReg", method=RequestMethod.POST)
	public String recrAnncEstmReg(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes)throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = oproRecrService.recrAnncEstmReg(parameterMap);
		
		parameterMap.put("P_SYS_CONN_SECD", "RECR");
		parameterMap.put("P_CONN_CNTN", "평가공고 업체 신청");
		parameterMap.put("P_CONN_URL", "/opro/recr/recrAnncEstmReg.do");
		parameterMap.put("P_MENU_ID", "recrAnncEstmRegForm");
		
		iproCommDefaultService.sendLOG(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "redirect:/opro/recr/recrAnncList.do";
	}
	
	//평가공고 수정 - 크리에이터 
	@RequestMapping(value="recrUpdt", method=RequestMethod.POST)
	public String recrUpdt(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes )throws Exception{
		 FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		 FwkTransferObject trans = new FwkTransferObject();
		
		 trans = oproRecrService.recrUpdt(parameterMap);
		
		 parameterMap.put("P_SYS_CONN_SECD", "RECR");
		 parameterMap.put("P_CONN_CNTN", "평가공고 수정");
		 parameterMap.put("P_CONN_URL","/opro/recr/recrUpdt.do");
		 parameterMap.put("P_MENU_ID", "recrAnncRegForm");
		
		iproCommDefaultService.sendLOG(parameterMap);
		 
		 model.addAllAttributes(trans);
		 model.addAllAttributes(parameterMap);
		 
		 return "redirect:/opro/recr/recrAnncList.do";
				 
	}
}