package com.eunwoosoft.ipro.info.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.info.service.IproInfoApprMngeService;

@RequestMapping(value = "/info")
@Controller
public class IproInfoApprMngeController extends AbstractIproMenuController {
	private static final String prefixUrl = "/ipro/views/info/";
	private static final String prefixUrlPopup = "/ipro/views/info/popup/";
	
	@Resource(name="iproInfoApprMngeService")
	IproInfoApprMngeService iproInfoApprMngeService;
	
	/**
	 * <pre>
	 * 1.개요 : 결재등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.info.controller.IproInfoApprMngeController.java
	 * @Method : infoApprlineList
	 * @author : 맹경열
	 * @date : 2018. 03. 08. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/infoApprlineList",method = RequestMethod.POST )
	public String infoApprlineList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); //세션값 가져오기
		
		parameterMap.put("USR_ID", session.get("USR_ID"));
		parameterMap.put("P_MBR_REG_NO", FwkMessageUtil.getMessage("EW.EPRO.VEND_REG_NO"));
		
		trans = iproInfoApprMngeService.getMmApplMstList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 결재선관리 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.info.controller.IproInfoApprMngeController.java
	 * @Method : infoApprlineDetail
	 * @author : 맹경열
	 * @date : 2018. 03. 09. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/infoApprlineDetail", method = RequestMethod.POST)
	public String infoApprlineDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); //세션값 가져오기.
		
		parameterMap.put("USR_ID", session.get("USR_ID"));
		parameterMap.put("P_MBR_REG_NO", FwkMessageUtil.getMessage("EW.EPRO.VEND_REG_NO"));
		
		trans = iproInfoApprMngeService.getMmAprpList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return JSON_VIEW;
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 결재선관리 등록 폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.info.controller.IproInfoApprMngeController.java
	 * @Method : infoApprlineRegForm
	 * @author : 맹경열
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/infoApprlineRegForm", method = {RequestMethod.POST})
	public String infoApprlineRegForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		//관계사일경우 블라썸 결재로 고정
		HttpSession	session	= request.getSession();
		FwkDataEntity loginSession = (FwkDataEntity) session.getAttribute( "loginResult" );
		String strMbrSe = loginSession.getString("MBR_SE");
		
		
		if(strMbrSe.equals("R"))
			trans.put("P_APPL_SE", "B");
		else{
			trans.put("P_APPL_SE", "I");
		}
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 결재선관리 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.info.controller.IproInfoApprMngeController.java
	 * @Method : infoApprlineRegist
	 * @author : 맹경열
	 * @date : 2018. 03. 08. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/infoApprlineRegist", method = {RequestMethod.POST})
	public String infoApprlineRegist(final HttpServletRequest request, final Model model){
	
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);

		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); //세션값 가져오기.
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		parameterMap.put("P_MBR_REG_NO", FwkMessageUtil.getMessage("EW.EPRO.VEND_REG_NO"));
		
		parameterMap.put("USR_NM", session.get("USR_NM"));
		parameterMap.put("CONN_IP", session.get("CONN_IP"));
		
	
		iproInfoApprMngeService.infoApprlineRegist(parameterMap);
	
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 결재선 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.info.controller.IproInfoApprMngeController.java
	 * @Method : infoApprlineDelete
	 * @author : 맹경열
	 * @date : 2018. 03. 08. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/infoApprlineDelete", method = RequestMethod.POST)
	public String infoApprlineDelete(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); //세션값 가져오기.
		
		parameterMap.put("USR_ID", session.get("USR_ID"));
		parameterMap.put("P_MBR_REG_NO", FwkMessageUtil.getMessage("EW.EPRO.VEND_REG_NO"));
			
		iproInfoApprMngeService.infoApprlineDelete(parameterMap);
		
		//model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 결재대상 목록 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.info.controller.IproInfoApprMngeController.java
	 * @Method : infoAppConsultList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/infoAppConsultList", method = RequestMethod.POST)
	public String infoAppConsultList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); //세션값 가져오기.
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		parameterMap.put("P_USR_NM", session.get("USR_NM"));
		parameterMap.put("P_CONN_IP", session.get("CONN_IP"));
		parameterMap.put("P_VEND_REG_NO", session.get("VEND_REG_NO"));
		parameterMap.put("P_APPR_USER_ID", session.get("EMPL_NO"));
		
		trans = iproInfoApprMngeService.getApplTargetList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 결재품의 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.info.controller.IproInfoApprMngeController.java
	 * @Method : infoAppConsultDetail
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/infoAppConsultDetail", method = RequestMethod.POST)
	public String infoAppConsultDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); //세션값 가져오기.
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		parameterMap.put("P_USR_NM", session.get("USR_NM"));
		parameterMap.put("P_CONN_IP", session.get("CONN_IP"));
		parameterMap.put("P_VEND_REG_NO", session.get("VEND_REG_NO"));
		parameterMap.put("P_APPR_USER_ID", session.get("EMPL_NO"));
		
		trans = iproInfoApprMngeService.getApprTargetDocu(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
				
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 결재현황 상세화면
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : infoAppResultDetail
	 * @author : sjKim
	 * @date : 2018. 11. 6. 
	 * @history
	 * =============================================
	 *  date           name           description
	 * ---------------------------------------------
	 * 2018. 11. 6.        sjKim          최초 생성
	 * =============================================
	 */
	@RequestMapping(value="/infoAppResultDetail", method = RequestMethod.POST)
	public String infoAppResultDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		//trans = iproInfoApprMngeService.getApprTargetDocu(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 결재현황 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.info.controller.IproInfoApprMngeController.java
	 * @Method : infoAppResultList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/infoAppResultList", method = RequestMethod.POST)
	public String infoAppResultList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);	
				
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 결재완료내역 목록 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : infoAppList
	 * @author : sjKim
	 * @date : 2018. 11. 7. 
	 * @history
	 * =============================================
	 *  date           name           description
	 * ---------------------------------------------
	 * 2018. 11. 7.        sjKim          최초 생성
	 * =============================================
	 */
	@RequestMapping(value="/infoAppList", method = RequestMethod.POST)
	public String infoAppList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); //세션값 가져오기.
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		parameterMap.put("P_USR_NM", session.get("USR_NM"));
		parameterMap.put("P_CONN_IP", session.get("CONN_IP"));
		parameterMap.put("P_VEND_REG_NO", session.get("VEND_REG_NO"));
		parameterMap.put("P_APPR_USER_ID", session.get("EMPL_NO"));
		
		trans = iproInfoApprMngeService.getApplResultList(parameterMap);
		
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
	 * @Method : infoAppDetail
	 * @author : sjKim
	 * @date : 2018. 11. 7. 
	 * @history
	 * =============================================
	 *  date           name           description
	 * ---------------------------------------------
	 * 2018. 11. 7.        sjKim          최초 생성
	 * =============================================
	 */
	@RequestMapping(value="/infoAppDetail", method = RequestMethod.POST)
	public String infoAppDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); //세션값 가져오기.
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		parameterMap.put("P_USR_NM", session.get("USR_NM"));
		parameterMap.put("P_CONN_IP", session.get("CONN_IP"));
		parameterMap.put("P_VEND_REG_NO", session.get("VEND_REG_NO"));
		parameterMap.put("P_APPR_USER_ID", session.get("EMPL_NO"));
		
		trans = iproInfoApprMngeService.getApprTargetDocu(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 결재선관리 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.info.controller.IproInfoApprMngeController.java
	 * @Method : infoApprlineUpdate
	 * @author : 맹경열
	 * @date : 2018. 03. 09. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/infoApprlineUpdate", method = RequestMethod.POST)
	public String infoApprlineUpdate(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); //세션값 가져오기.
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		parameterMap.put("USR_NM", session.get("USR_NM"));
		parameterMap.put("CONN_IP", session.get("CONN_IP"));
		parameterMap.put("P_MBR_REG_NO", FwkMessageUtil.getMessage("EW.EPRO.VEND_REG_NO"));
		
		iproInfoApprMngeService.infoApprlineUpdate(parameterMap);
		
		model.addAllAttributes(parameterMap);		
		
		return JSON_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 결재선의견 작성(팝업)
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.info.controller.IproInfoApprMngeController.java
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
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); //세션값 가져오기.
		
		parameterMap.put("P_USER_ID", session.get("USER_ID"));
		parameterMap.put("P_USER_NM", session.get("USER_NM"));
		
		trans = iproInfoApprMngeService.getApprTarget(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixUrlPopup);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 결재자정보_업데이트
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.info.controller.IproInfoApprMngeController.java
	 * @Method : infoApprTglUpdate
	 * @author : 맹경열
	 * @date : 2018. 03. 26. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/infoApprTglUpdate", method = RequestMethod.POST)
	public String infoApprTglUpdate(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); //세션값 가져오기.
		
		parameterMap.put("P_USER_ID", session.get("USER_ID"));
		parameterMap.put("P_USER_NM", session.get("USER_NM"));
		
		iproInfoApprMngeService.infoApprRsnUpdate(parameterMap);
		
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
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.info.controller.IproInfoApprMngeController.java
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
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); //세션값 가져오기.
		
		parameterMap.put("P_USR_ID", session.get("USR_ID"));
		parameterMap.put("P_USR_NM", session.get("USR_NM"));
		parameterMap.put("P_CONN_IP", session.get("CONN_IP"));
		parameterMap.put("P_VEND_REG_NO", session.get("VEND_REG_NO"));
		
		trans = iproInfoApprMngeService.getApplResultList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
				
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	@RequestMapping(value="/popup/apprRtnRsnForm", method = RequestMethod.POST)
	public String apprRtnRsnForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); //세션값 가져오기.
		
		parameterMap.put("P_USER_ID", session.get("USER_ID"));
		parameterMap.put("P_USER_NM", session.get("USER_NM"));
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixUrlPopup);
	}
}
