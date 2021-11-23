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
import com.eunwoosoft.ipro.estm.service.IproEstmCmtmMngProgService;

/**
 * 평가관리 > 평가위원관리 > 평가위원평가진행현황 Controller
 * com.eunwoosoft.ipro.estm.controller
 * IproEstmCmtmMngProgController.java
 *
 * @Author : 
 * @Date   : 2021. 3. 23.
 */
@Controller
@RequestMapping(value = "/estm")
public class IproEstmCmtmMngProgController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/estm/";
	String prefixPopUrl = "/ipro/views/estm/popup/";
	
	@Resource(name="iproEstmCmtmMngProgService")
	private IproEstmCmtmMngProgService iproEstmCmtmMngProgService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가진행현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : cmtmMngProgList
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngProgList")	
	public String cmtmMngProgList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmMngProgService.cmtmMngProgList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	@RequestMapping(value = "/cmtmMngProgListExcelDwld")	
	public String cmtmMngProgListExcelDwld(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproEstmCmtmMngProgService.cmtmMngProgListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("cmtmMngProgList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","평가위원평가진행현황 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
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
	@RequestMapping(value="/estmCmtmMngMstClcRulSave")
	public String estmCmtmMngMstClcRulSave(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmMngProgService.estmCmtmMngMstClcRulSave(parameterMap);
		
		trans.put("resultCode", "Success");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		//return parameterMap.getViewName(prefixUrl);
		return "forward:/estm/cmtmMngProgResultDetail.do";
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가진행현황 상세 - 평가대상불러오기
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : estmCmtmCntcObjList
	 * @author : 
	 * @date : 2021. 4. 01.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmCntcObjList")
	public String estmCmtmCntcObjList(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			
			iproEstmCmtmMngProgService.estmCmtmCntcObjList(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가진행현황 상세 - 평가대상불러오기
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : estmCmtmCntcObjList
	 * @author : 
	 * @date : 2021. 4. 01.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmCmtmCntcObjFileDown")
	public String estmCmtmCntcObjFileDown(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
//		try {
			
			iproEstmCmtmMngProgService.estmCmtmCntcObjFileDown(parameterMap);
			
//		} catch (Exception ex) {
//			response.setStatus(999); 
//			throw new Exception(ex.toString());
//		}
		
		
		return JSON_VIEW;
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가진행현황 작성
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : cmtmMngProgRegistForm
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngProgRegistForm")	
	public String cmtmMngProgRegistForm(final HttpServletRequest request, final Model model) throws Exception {		
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
	 * 1.개요 : 평가위원평가진행현황 작성
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : cmtmMngProgRegist
	 * @author : 
	 * @date : 2021. 4. 28.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/cmtmMngProgRegist", method=RequestMethod.POST)
	public String cmtmMngProgRegist(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			
			MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);		

			trans = iproEstmCmtmMngProgService.cmtmMngProgRegist(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가위원평가진행현황 작성");
			parameterMap.put("P_CONN_URL", "/estm/cmtmMngProgRegistForm.do");
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
			
		}catch(Exception ex){
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "redirect:/estm/cmtmMngProgDetail.do";
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가진행현황 상세 - 기본정보
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : cmtmMngProgDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngProgDetail")	
	public String cmtmMngProgDetail(final HttpServletRequest request, final Model model) throws Exception {		
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
		
		FwkTransferObject trans = iproEstmCmtmMngProgService.cmtmMngProgDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가진행현황 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : cmtmMngProgUpdtForm
	 * @author : 
	 * @date : 2021. 4. 29.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngProgUpdtForm")	
	public String cmtmMngProgUpdtForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmMngProgService.cmtmMngProgDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가진행현황 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : cmtmMngProgUpdt
	 * @author : 
	 * @date : 2021. 4. 29.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/cmtmMngProgUpdt", method=RequestMethod.POST)
	public String estmProgUpdt(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			
			MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);
			
			trans = iproEstmCmtmMngProgService.cmtmMngProgUpdt(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가위원평가진행현황 수정");
			parameterMap.put("P_CONN_URL", "/estm/cmtmMngProgUpdtForm.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
					
			iproCommDefaultService.sendLOG(parameterMap);
			
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return "redirect:/estm/cmtmMngProgDetail.do";
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가진행현황 상세 - 평가대상
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : cmtmMngProgObjDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngProgObjDetail")	
	public String cmtmMngProgObjDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmMngProgService.cmtmMngProgObjDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가진행현황 상세 - 평가위원
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : cmtmMngProgCmtmDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngProgCmtmDetail")	
	public String cmtmMngProgCmtmDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmMngProgService.estmProgCmtmDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가진행현황 상세 - 서류평가
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : cmtmMngProgProcdADetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngProgProcdADetail")	
	public String cmtmMngProgProcdADetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmMngProgService.cmtmMngProgProcdADetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가진행현황 상세 - 정량평가
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : cmtmMngProgProcdBDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngProgProcdBDetail")	
	public String cmtmMngProgProcdBDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원평가진행현황 상세 - 평가결과
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : cmtmMngProgResultDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngProgResultDetail")	
	public String cmtmMngProgResultDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmMngProgService.cmtmMngProgResultDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 상세 - 평가결과 - 엑셀다운로드
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : estmProgResultDetail
	 * @author : 
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngProgResultDetailExcelDwld")	
	public String cmtmMngProgResultDetailExcelDwld(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmMngProgService.cmtmMngProgResultDetailExcelDwld(parameterMap);
		
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
	 * 1.개요 : 평가위원평가진행현황 상세 - 화상회의
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : cmtmMngProgVidoMtngDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngProgVidoMtngDetail", method={RequestMethod.POST, RequestMethod.GET})
	public String cmtmMngProgVidoMtngDetail(final HttpServletRequest request, final Model model) throws Exception {		
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
		
		HttpSession	session	= request.getSession();
		FwkDataEntity loginSession = (FwkDataEntity) session.getAttribute( "loginResult" );
		
		if(loginSession.get("USR_ID") != null) {
			parameterMap.put("P_USR_ID", loginSession.get("USR_ID")); // 세션 사용자아이디
		}else {
			parameterMap.put("P_USR_ID", loginSession.get("USER_ID")); // 세션 사용자아이디
		}
		
		FwkTransferObject trans = iproEstmCmtmMngProgService.cmtmMngProgVidoMtngDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}

	@RequestMapping(value = "/cmtmMngProgVidoMtngSave")	
	public String cmtmMngProgVidoMtngSave(final HttpServletRequest request, HttpServletResponse response, final Model model, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		try{
			FwkTransferObject trans = iproEstmCmtmMngProgService.cmtmMngProgVidoMtngSave(parameterMap);

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
			
//			model.addAllAttributes(trans);
//			model.addAllAttributes(parameterMap);
			
		}catch(Exception ex){
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		return "redirect:/estm/cmtmMngProgVidoMtngDetail.do";
	}

	
	
	/**
	 * <pre>
	 * 1.개요 : 평가종료
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngProgController.java
	 * @Method : cmtmMngProgEstmEnd
	 * @author : 
	 * @date : 2021. 5. 14.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngProgEstmEnd")
	public String cmtmMngProgEstmEnd(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {	
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			
			iproEstmCmtmMngProgService.cmtmMngProgEstmEnd(parameterMap);
			
		} catch(Exception ex){
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		
		return JSON_VIEW;
	}




}