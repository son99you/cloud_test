package com.eunwoosoft.ipro.appr.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.appr.service.IproApprMngService;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;

@Controller
@RequestMapping(value = "/appr")
public class IproApprMngController extends AbstractIproMenuController {
	private static final String prefixUrl = "/ipro/views/appr/";
	private static final String prefixUrlPopup = "/ipro/views/appr/popup/";
	
	@Resource(name="iproApprMngService")
	private IproApprMngService iproApprMngService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	/**
	 * <pre>
	 * 1.개요 : 결재등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.appr.controller.IproApprMngController.java
	 * @Method : apprRegList
	 * @author :
	 * @date : 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/apprRegList",method = RequestMethod.POST )
	public String apprRegList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		FwkDataEntity user = (FwkDataEntity)parameterMap.get("loginResult");
		
		if("1".equals(user.get("AUTH_ID"))){	//관리자
			//전체조회 가능
			parameterMap.put("USR_ID", "");
		}else if("2".equals(user.get("AUTH_ID"))){	//일반사용자
			parameterMap.put("USR_ID", user.get("USR_ID"));
		}else if("3".equals(user.get("AUTH_ID"))){	//평가위원순위선정자(감사실)
			parameterMap.put("USR_ID", user.get("USR_ID"));
		}else if("4".equals(user.get("AUTH_ID"))){	//평가위원담당자(마케팅기획팀)
			parameterMap.put("USR_ID", user.get("USR_ID"));
		}
		parameterMap.put("P_MBR_REG_NO", FwkMessageUtil.getMessage("EW.EPRO.VEND_REG_NO"));
		
		trans = iproApprMngService.apprRegList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 결재선 - 결재자정보 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.appr.controller.IproApprMngController.java
	 * @Method : apprAprpList
	 * @author : 맹경열
	 * @date : 2018. 03. 09. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/apprAprpList", method = RequestMethod.POST)
	public String infoApprlineDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("USR_ID", session.get("USR_ID"));
		parameterMap.put("P_MBR_REG_NO", FwkMessageUtil.getMessage("EW.EPRO.VEND_REG_NO"));
		
		trans = iproApprMngService.apprAprpList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return JSON_VIEW;
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 결재선등록 화면
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.appr.controller.IproApprMngController.java
	 * @Method : apprRegistForm
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/apprRegistForm", method = {RequestMethod.POST})
	public String infoApprlineRegForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 결재선관리 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.appr.controller.IproApprMngController.java
	 * @Method : apprRegist
	 * @author : 맹경열
	 * @date : 2018. 03. 08. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/apprRegist", method = {RequestMethod.POST})
	public String apprRegist(final HttpServletRequest request, final Model model){
	
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);

		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		parameterMap.put("P_MBR_REG_NO", FwkMessageUtil.getMessage("EW.EPRO.VEND_REG_NO"));
		parameterMap.put("USR_NM", session.get("USR_NM"));
		parameterMap.put("CONN_IP", session.get("CONN_IP"));
	
		iproApprMngService.apprRegist(parameterMap);
		
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "APPR");
		parameterMap.put("P_CONN_CNTN", "결재선 등록");
		parameterMap.put("P_CONN_URL", "/appr/apprRegistForm.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
		
		iproCommDefaultService.sendLOG(parameterMap);
	
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 결재선 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.appr.controller.IproApprMngController.java
	 * @Method : applMstDelete
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/applMstDelete", method = RequestMethod.POST)
	public String infoApprlineDelete(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("USR_ID", session.get("USR_ID"));
		parameterMap.put("P_MBR_REG_NO", FwkMessageUtil.getMessage("EW.EPRO.VEND_REG_NO"));
			
		iproApprMngService.applMstDelete(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "APPR");
		parameterMap.put("P_CONN_CNTN", "결재선 삭제");
		parameterMap.put("P_CONN_URL", "/appr/apprRegList.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
		
		iproCommDefaultService.sendLOG(parameterMap);
		
		model.addAllAttributes(parameterMap);
		
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 결재대상 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.appr.controller.IproApprMngController.java
	 * @Method : apprObjList
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/apprObjList", method = RequestMethod.POST)
	public String apprObjList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		parameterMap.put("P_USR_NM", session.get("USR_NM"));
		parameterMap.put("P_CONN_IP", session.get("CONN_IP"));
		parameterMap.put("P_VEND_REG_NO", session.get("VEND_REG_NO"));
		parameterMap.put("P_APPR_USER_ID", session.get("EMPL_NO"));
		
		trans = iproApprMngService.apprObjList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 결재대상 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.appr.controller.IproApprMngController.java
	 * @Method : apprObjDetail
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/apprObjDetail", method = RequestMethod.POST)
	public String apprObjDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		parameterMap.put("P_USR_NM", session.get("USR_NM"));
		parameterMap.put("P_CONN_IP", session.get("CONN_IP"));
		parameterMap.put("P_VEND_REG_NO", session.get("VEND_REG_NO"));
		parameterMap.put("P_MBR_REG_NO", FwkMessageUtil.getMessage("EW.EPRO.VEND_REG_NO"));
		parameterMap.put("P_APPR_USER_ID", session.get("EMPL_NO"));
		
		trans = iproApprMngService.apprObjDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
				
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 결재완료 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : apprCmplList
	 */
	@RequestMapping(value="/apprCmplList", method = RequestMethod.POST)
	public String apprCmplList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		parameterMap.put("P_USR_NM", session.get("USR_NM"));
		parameterMap.put("P_CONN_IP", session.get("CONN_IP"));
		parameterMap.put("P_VEND_REG_NO", session.get("VEND_REG_NO"));
		parameterMap.put("P_APPR_USER_ID", session.get("EMPL_NO"));
		
		trans = iproApprMngService.apprCmplList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 결재진행 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : apprCmplDetail
	 */
	@RequestMapping(value="/apprCmplDetail", method = RequestMethod.POST)
	public String apprCmplDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		parameterMap.put("P_USR_NM", session.get("USR_NM"));
		parameterMap.put("P_CONN_IP", session.get("CONN_IP"));
		parameterMap.put("P_VEND_REG_NO", session.get("VEND_REG_NO"));
		parameterMap.put("P_APPR_USER_ID", session.get("EMPL_NO"));
		
		trans = iproApprMngService.apprObjDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 결재자정보 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.appr.controller.IproApprMngController.java
	 * @Method : updateApplAprp
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateApplAprp", method = RequestMethod.POST)
	public String updateApplAprp(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		parameterMap.put("USR_NM", session.get("USR_NM"));
		parameterMap.put("CONN_IP", session.get("CONN_IP"));
		parameterMap.put("P_MBR_REG_NO", FwkMessageUtil.getMessage("EW.EPRO.VEND_REG_NO"));
		
		iproApprMngService.updateApplAprp(parameterMap);
		
		model.addAllAttributes(parameterMap);		
		
		return JSON_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 결재선의견 작성(팝업)
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.appr.controller.IproApprMngController.java
	 * @Method : infoApprRsnRegist
	 * @author : 맹경열
	 * @date : 2018. 03. 23. 
	 * @param request
	 * @param model
	 * @return
	 */	
	@RequestMapping(value="/popup/infoApprRsnForm", method = RequestMethod.POST)
	public String infoApprRsnRegist(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USER_ID", session.get("USER_ID"));
		parameterMap.put("P_USER_NM", session.get("USER_NM"));
		
		trans = iproApprMngService.getApprTarget(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixUrlPopup);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 결재여부 업데이트
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.appr.controller.IproApprMngController.java
	 * @Method : apprYnUpdate
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/apprYnUpdate", method = RequestMethod.POST)
	public String apprYnUpdate(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USER_ID", session.get("USER_ID"));
		parameterMap.put("P_USER_NM", session.get("USER_NM"));
		
		iproApprMngService.apprYnUpdate(parameterMap);
		
		//model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return JSON_VIEW;
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 결재내역 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.appr.controller.IproApprMngController.java
	 * @Method : getApplResultList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getApplResultList", method = RequestMethod.POST)
	public String getApplResultList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		parameterMap.put("P_USR_NM", session.get("USR_NM"));
		parameterMap.put("P_CONN_IP", session.get("CONN_IP"));
		parameterMap.put("P_VEND_REG_NO", session.get("VEND_REG_NO"));
		
		trans = iproApprMngService.apprCmplList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
				
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	@RequestMapping(value="/popup/apprRtnRsnForm", method = RequestMethod.POST)
	public String apprRtnRsnForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_USER_ID", session.get("USER_ID"));
		parameterMap.put("P_USER_NM", session.get("USER_NM"));
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixUrlPopup);
	}
}
