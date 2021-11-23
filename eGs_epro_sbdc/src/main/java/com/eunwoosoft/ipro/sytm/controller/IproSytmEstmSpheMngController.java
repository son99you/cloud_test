package com.eunwoosoft.ipro.sytm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.ipro.sytm.service.IproSytmEstmSpheMngService;

/**
 * 정보관리 > 평가분야관리 Controller
 * com.eunwoosoft.ipro.sytm.controller
 * IproSytmEstmSpheMngController.java
 *
 * @Author : 
 * @Date   : 2021. 3. 23.
 */
@Controller
@RequestMapping(value = "/sytm")
public class IproSytmEstmSpheMngController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/sytm/";
	
	@Resource(name="iproSytmEstmSpheMngService")
	private IproSytmEstmSpheMngService iproSytmEstmSpheMngService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	/**
	 * <pre>
	 * 1.개요 : 평가분야관리 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmSpheMngController.java
	 * @Method : estmSpheMngList
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmSpheMngList")
	public String estmSpheMngList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
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
		
		FwkTransferObject trans = iproSytmEstmSpheMngService.estmSpheMngList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가분야관리 작성
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmSpheMngController.java
	 * @Method : estmSpheMngRegistForm
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmSpheMngRegistForm")
	public String estmSpheMngRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가분야관리 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmSpheMngController.java
	 * @Method : estmSpheMngDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmSpheMngDetail")
	public String estmSpheMngDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmEstmSpheMngService.estmSpheMngDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가분야관리 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmSpheMngController.java
	 * @Method : estmSpheMngUpdtForm
	 * @author : 
	 * @date : 2021. 3. 24.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmSpheMngUpdtForm")
	public String estmSpheMngUpdtForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmEstmSpheMngService.estmSpheMngDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가분야관리 작성
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmSpheMngController.java
	 * @Method : estmSpheMngRegist
	 * @author : 
	 * @date : 2021. 4. 09.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmSpheMngRegist")
	public String estmSpheMngRegist(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			
			iproSytmEstmSpheMngService.estmSpheMngRegist(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "SYTM");
			parameterMap.put("P_CONN_CNTN", "평가분야관리 작성");
			parameterMap.put("P_CONN_URL", "/sytm/estmSpheMngRegistForm.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
			
			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가분야관리 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmSpheMngController.java
	 * @Method : estmSpheMngUpdt
	 * @author : 
	 * @date : 2021. 4. 12.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmSpheMngUpdt")
	public String estmSpheMngUpdt(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			
			iproSytmEstmSpheMngService.estmSpheMngUpdt(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "SYTM");
			parameterMap.put("P_CONN_CNTN", "평가분야관리 수정");
			parameterMap.put("P_CONN_URL", "/sytm/estmSpheMngUpdtForm.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));

			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가분야관리 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmSpheMngController.java
	 * @Method : estmSpheMngDelete
	 * @author : 
	 * @date : 2021. 4. 12.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmSpheMngDelete")
	public String estmSpheMngDelete(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try {
			
			iproSytmEstmSpheMngService.estmSpheMngDelete(parameterMap);
			
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "SYTM");
			parameterMap.put("P_CONN_CNTN", "평가분야관리 삭제");
			parameterMap.put("P_CONN_URL", "/sytm/estmSpheMngDetail.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));

			iproCommDefaultService.sendLOG(parameterMap);
			
		} catch (Exception ex) {
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
	}
	
}
