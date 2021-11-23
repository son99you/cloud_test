package com.eunwoosoft.ipro.estm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.eunwoosoft.ipro.estm.service.IproEstmCmtmMngPoolService;

/**
 * 평가관리 > 평가위원관리 > 평가위원POOL현황 Controller
 * com.eunwoosoft.ipro.estm.controller
 * IproEstmCmtmMngPoolController.java
 *
 * @Author : 
 * @Date   : 2021. 3. 23.
 */
@Controller
@RequestMapping(value = "/estm")
public class IproEstmCmtmMngPoolController extends AbstractIproMenuController {
	
	String prefixUrl = "/ipro/views/estm/";
	String prefixPopUrl = "/ipro/views/estm/popup/";
	
	@Resource(name="iproEstmCmtmMngPoolService")
	private IproEstmCmtmMngPoolService iproEstmCmtmMngPoolService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원POOL현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngPoolController.java
	 * @Method : cmtmMngPoolList
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngPoolList")	
	public String cmtmMngPoolList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmMngPoolService.cmtmMngPoolList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원POOL현황 목록 - 엑셀다운로드
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngPoolController.java
	 * @Method : cmtmMngPoolList
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngPoolListExcelDwld")	
	public String cmtmMngPoolListExcelDwld(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmMngPoolService.cmtmMngPoolListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("cmtmMngPoolList")); // trans에서 뽑아내는 데이터를 넣어준다.
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","평가위원POOL현황 목록"); // 각자에 맞는 리스트명을 넣는다.
		model.addAttribute("excelGbn","POI");
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return EXCEL_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원POOL현황 작성
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngPoolController.java
	 * @Method : cmtmMngPoolRegistForm
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngPoolRegistForm")	
	public String cmtmMngPoolRegistForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
		parameterMap.put("P_ESTM_CMTM_REGR_NM", user.getString("USR_NM"));   // 평가담당자명
		parameterMap.put("P_ESTM_CMTM_REGR_ID", user.getString("USR_ID"));   // 평가담당자아이디
		parameterMap.put("P_ESTM_CMTM_REG_DEPT_NM", user.getString("DEPT_NM"));   // 평가담당부서명
		parameterMap.put("P_ESTM_CMTM_REG_DEPT_NO", user.getString("DEPT_NO"));   // 평가담당부서번호
		
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원POOL현황 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngPoolController.java
	 * @Method : cmtmMngPoolDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/cmtmMngPoolDetail", method={RequestMethod.POST, RequestMethod.GET})
	public String cmtmMngPoolDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		/**
		 * request에 키값이 되는 값이 담겨있지 않으면 redirct로 넘어왔다고 판단하고
		 * request에 담겨있는 parameterMap값을 request.session에 담겨있는 parameterMap값으로  바꿔준다.
		 * 그리고 request.session에 담겨있는 requestUrl값을 먼저담겨있는 request.parameterMap 의 requestUrl으로 바꿔준다.
		 * 그래야지 jsp페이지를 정상적으로 찾을 수 있음. 
		 */
		if(parameterMap.get("P_ESTM_CMTM_NO") == null || parameterMap.get("P_ESTM_CMTM_NO").equals("")){
			String thisRequestUrl = parameterMap.get("requestUrl").toString();
			parameterMap = (FwkParameterMap) request.getSession().getAttribute(PARAMETER_MAP);
			parameterMap.put("requestUrl", thisRequestUrl);
			request.getSession().removeAttribute(PARAMETER_MAP);
		}
		
		trans = iproEstmCmtmMngPoolService.cmtmMngPoolDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getViewName(prefixUrl);
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
	@RequestMapping(value="/cmtmMngPoolRegist", method=RequestMethod.POST)
	public String cmtmMngPoolRegist(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try {
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);
			
			trans = iproEstmCmtmMngPoolService.cmtmMngPoolRegist(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가위원POOL현황 작성");
			parameterMap.put("P_CONN_URL", "/estm/cmtmMngPoolRegistForm.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
					
			iproCommDefaultService.sendLOG(parameterMap);
			
			//기존에 사용한 세션이 있는경우 삭제
			if(request.getSession().getAttribute("resourceName") != null){
				request.getSession().removeAttribute("resourceName");
			}
			request.getSession().setAttribute("resourceName", (String)parameterMap.get("resourceName"));
			
//			CommVo commVo = new CommVo();
//			commVo.setpEstmNo((String)trans.get("P_ESTM_CMTM_NO_TRANS"));
//			//기존에 사용한 세션이 있는경우 삭제
//			if(request.getSession().getAttribute("commVo") != null){
//				request.getSession().removeAttribute("commVo");
//			}
//			request.getSession().setAttribute("commVo", commVo);
//			
//			redirectAttributes.addFlashAttribute("parameterMap", parameterMap);
			
			parameterMap.put("P_ESTM_CMTM_NO", trans.get("P_ESTM_CMTM_NO_TRANS").toString());
			
			request.getSession().setAttribute("parameterMap", parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		return "redirect:/estm/cmtmMngPoolDetail.do";
	}

	
	/**
	 * <pre>
	 * 1.개요 : 평가진행현황 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : cmtmMngPoolUpdtForm
	 * @author : 
	 * @date : 2021. 4. 19.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngPoolUpdtForm")	
	public String cmtmMngPoolUpdtForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEstmCmtmMngPoolService.cmtmMngPoolDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}

	@RequestMapping(value="/cmtmMngPoolUpdt", method=RequestMethod.POST)
	public String cmtmMngPoolUpdt(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try {
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);
			
			trans = iproEstmCmtmMngPoolService.cmtmMngPoolUpdt(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "ESTM");
			parameterMap.put("P_CONN_CNTN", "평가위원POOL현황 수정");
			parameterMap.put("P_CONN_URL", "/estm/cmtmMngPoolUpdtForm.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
			iproCommDefaultService.sendLOG(parameterMap);
			
			//기존에 사용한 세션이 있는경우 삭제
			if(request.getSession().getAttribute("resourceName") != null){
				request.getSession().removeAttribute("resourceName");
			}
			request.getSession().setAttribute("resourceName", (String)parameterMap.get("resourceName"));
			
//			CommVo commVo = new CommVo();
//			commVo.setpEstmNo((String)trans.get("P_ESTM_CMTM_NO_TRANS"));
//
//			//기존에 사용한 세션이 있는경우 삭제
//			if(request.getSession().getAttribute("commVo") != null){
//				request.getSession().removeAttribute("commVo");
//			}
//			request.getSession().setAttribute("commVo", commVo);
//			
//			redirectAttributes.addFlashAttribute("parameterMap", parameterMap);
			
			request.getSession().setAttribute("parameterMap", parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		return "redirect:/estm/cmtmMngPoolDetail.do";
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원POOL현황 엑셀 양식 다운로드
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : cmtmMngPoolExcelFrmDwld
	 * @author : 
	 * @date : 2021. 6. 11.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/cmtmMngPoolExcelFrmDwld")
	public String cmtmMngPoolExcelFrmDwld(final HttpServletRequest request, final Model model) throws Exception{

		model.addAttribute("templateFileName", "ipro/estm/cmtmMngPoolExcelFrmDwld.xls"); 
		model.addAttribute("destFileName","평가위원POOL양식");
		
		return EXCEL_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원POOL현황 엑셀 양식 업로드
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmProgController.java
	 * @Method : cmtmMngPoolExcelUpld
	 * @author : 
	 * @date : 2021. 6. 11.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmtmMngPoolExcelUpld")
	public String cmtmMngPoolExcelUpld(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		parameterMap.put("multipartRequest", multipartRequest);
		
		trans = iproEstmCmtmMngPoolService.cmtmMngPoolExcelUpld(parameterMap, response);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "redirect:/estm/cmtmMngPoolList.do";
	}
	
	@RequestMapping(value = "/cmtmMngPoolDelete")
	public String cmtmMngPoolDelete(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		iproEstmCmtmMngPoolService.cmtmMngPoolDelete(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "ESTM");
		parameterMap.put("P_CONN_CNTN", "평가위원POOL현황 삭제");
		parameterMap.put("P_CONN_URL", "/estm/cmtmMngPoolDetail.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
		
		return JSON_VIEW;
	}
}

