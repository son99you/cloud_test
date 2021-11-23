package com.eunwoosoft.ipro.ebid.controller; 

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.eunwoosoft.comm.mail.service.CommMailService;
import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.comm.vo.CommVo;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproEbidBfStndOpenPrcnService;

/**
 * 입찰관리 > 사전규격공개진행현화 Controller 
 * <pre>
 * oda.iep.elbi.controller 
 *    |_ IproEbidBfStndOpenController.java
 * 
 * </pre>
 */
@Controller
@RequestMapping(value = "/ebid")
public class IproEbidBfStndOpenPrcnController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";
	String prefixPopUrl = "/ipro/views/ebid/popup/";
	String prefixExcelUrl = "/ipro/ebid/";
	
	@Resource(name="iproEbidBfStndOpenPrcnService")
	private IproEbidBfStndOpenPrcnService iproEbidBfStndOpenPrcnService;
	

	@Resource(name = "commMailService")
	private CommMailService commMailService;
	

	@RequestMapping(value = "/bfStndOpenPrcnList")
	public String bfStndOpenPrcnList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		if(!"Y".equals(parameterMap.get("P_SEARCH"))){
			if("1".equals(user.get("AUTH_ID"))){	//관리자
				//전체조회 가능
			}else if("4".equals(user.get("AUTH_ID"))){	//계약관리자
				trans.put("P_ARA_DEPT_CD_S", user.get("ARA_DEPT_CD"));
				parameterMap.put("P_ARA_DEPT_CD_S", user.get("ARA_DEPT_CD"));
			}else{	
				trans.put("P_ARA_DEPT_CD_S", user.get("ARA_DEPT_CD"));
				trans.put("P_RQR_DEPT_NO_S", user.get("DEPT_NO")); 
				trans.put("P_RQR_DEPT_NM_S", user.get("DEPT_NM"));  
				
				parameterMap.put("P_ARA_DEPT_CD_S", user.get("ARA_DEPT_CD"));
				parameterMap.put("P_RQR_DEPT_NO_S", user.get("DEPT_NO")); 
				parameterMap.put("P_RQR_DEPT_NM_S", user.get("DEPT_NM")); 
			}
			parameterMap.put("P_RQR_BEGIN_DT_S", FwkDateUtil.convertToString(FwkDateUtil.addDate(new Date(), -7), "yyyyMMdd"));
			parameterMap.put("P_RQR_END_DT_S", FwkDateUtil.getCurrentDateAsString("yyyyMMdd"));
		}
		
		
		trans = iproEbidBfStndOpenPrcnService.bfStndOpenPrcnList(parameterMap);
		
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 사전규격공개진행현황 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.cont.controller.IproPrpoBfStndOpenController.java
	 * @Method : bfStndOpenPrcnDetail
	 * @author : 
	 * @date : 2020.8.26. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bfStndOpenPrcnDetail" , method={RequestMethod.POST, RequestMethod.GET})
	public String bfStndOpenPrcnDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		// 리다이렉트 상세화면 이동을 위해 분기처리
		Object obj = RequestContextUtils.getInputFlashMap(request);
		if(obj != null) {
			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
		}else {
			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		}
		
		if(parameterMap.get("P_BFAN_NO") == null || parameterMap.get("P_BFAN_NO").equals("")){
			if(request.getSession().getAttribute("commVo") != null){		//NOPMD
				CommVo commVo = (CommVo)request.getSession().getAttribute("commVo");			
				parameterMap.put("P_BFAN_NO", commVo.getpBfanNo());
			}
		}
		
		FwkTransferObject trans = iproEbidBfStndOpenPrcnService.bfStndOpenPrcnDetail(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	@RequestMapping(value="/bfStndOpenUpdtForm")
	public String bfStndOpenUpdtForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEbidBfStndOpenPrcnService.bfStndOpenPrcnDetail(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	@RequestMapping(value="/bfStndOpenUpdt", method=RequestMethod.POST)
	public String bfStndOpenUpdt(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			
			MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);
			
			trans = iproEbidBfStndOpenPrcnService.bfStndOpenUpdt(parameterMap);
			
			//Log 저장
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "BID");
			parameterMap.put("P_CONN_CNTN", "사전규격공개 수정");
			parameterMap.put("P_CONN_URL", "/ebid/bfStndOpenUpdtForm.do");
			parameterMap.put("P_MENU_ID", "bfStndOpenUpdtForm");
			
			//기존에 사용한 세션이 있는경우 삭제
			if(request.getSession().getAttribute("resourceName") != null){
				request.getSession().removeAttribute("resourceName");
			}
			request.getSession().setAttribute("resourceName", (String)parameterMap.get("resourceName"));
			
			CommVo commVo = new CommVo();
			commVo.setpBfanNo((String)trans.get("P_BFAN_NO_TRANS"));
			
			//기존에 사용한 세션이 있는경우 삭제
			if(request.getSession().getAttribute("commVo") != null){
				request.getSession().removeAttribute("commVo");
			}
			request.getSession().setAttribute("commVo", commVo);
			
			redirectAttributes.addFlashAttribute("parameterMap",parameterMap);
			
		}catch(Exception ex){
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}		 
		return "redirect:/ebid/bfStndOpenPrcnDetail.do";
	}
	
	@RequestMapping(value = "/popup/bfanRtnRsnPopup")
	public String bfanRtnRsnPopup(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	@RequestMapping(value = "/bidBfanRtnStatUpdt")
	public String bidBfanRtnStatUpdt(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproEbidBfStndOpenPrcnService.bidBfanStatUpdt(parameterMap);
			if("succ".equals(trans.get("stateUpdt"))){
				commMailService.commSend(parameterMap,"bidBfanStatUpdt");
			}
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/bidBfanStatUpdt")
	public String bidBfanStatUpdt(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproEbidBfStndOpenPrcnService.bidBfanStatUpdt(parameterMap);
			if("succ".equals(trans.get("stateUpdt"))){
				commMailService.commSend(parameterMap,"bidBfanStatUpdt");
			}
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사전규격요청진행현황 엑셀 다운로드
	 * 2. 처리내용 : 
	 * </pre>
	 */
	@RequestMapping(value="/bfanPrcnExcelDwld")
	public String bfanPrcnExcelDwld(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEbidBfStndOpenPrcnService.bfStndOpenPrcnExcelList(parameterMap);
		
		model.addAttribute("dataList",trans.get(iproEbidBfStndOpenPrcnService.BF_STND_OPEN_PRCN_LIST));
		model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("destFileName","사전규격공개진행현황 목록");		
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
}