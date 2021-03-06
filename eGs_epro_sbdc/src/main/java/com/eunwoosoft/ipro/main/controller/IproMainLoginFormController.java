package com.eunwoosoft.ipro.main.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.code.dao.ComCmcdDetailCdDao;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.ipro.comm.service.IproCommPopupService;
import com.eunwoosoft.ipro.main.service.IproMainLoginFormService;
import com.eunwoosoft.ipro.noti.service.IproFaqService;
import com.eunwoosoft.ipro.noti.service.IproNotiService;
import com.yettiesoft.vestsign.external.SignVerifier;

/**
 * 
 * com.eunwoosoft.ipro.main.controller
 * IproMainLoginFormController.java
 *
 * @Author : SungYoon_Ha
 * @Date   : 2017. 5. 30.
 *
 */
@RequestMapping(value = "/main")
@Controller
public class IproMainLoginFormController extends AbstractFwkController {
	String prefixUrl = "/ipro/views/main/";
	String prefixUrlPopup = "/ipro/views/main/popup/";
	
	@Resource(name="iproMainLoginFormService")
	private IproMainLoginFormService iproMainLoginFormService;

	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	@Resource(name="comCmcdDetailCdDao")
	private ComCmcdDetailCdDao comCmcdDetailCdDao;
	
	@Resource(name="iproNotiService")
	private IproNotiService iproNotiService;
	
	@Resource(name="iproFaqService")
	private IproFaqService iproFaqService;

	@Resource(name="iproCommPopupService")
	private IproCommPopupService iproCommPopupService;
	
	
	
	/**
	 * <pre>
	 * 1.?????? : sso ????????? ??? ?????? 
	 * 2.???????????? : 
	 * </pre>
	 * @Location : eGs_epro_sdbc.com.eunwoosoft.ipro.main.controller.IproMainLoginFormController.java
	 * @Method : ssoLoginForm
	 * @author : ?????????
	 * @date : 2019. 03. 27. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ssoLoginForm")
	public String ssoLoginForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		return parameterMap.getViewName(prefixUrl);
	}	

	
	@RequestMapping(value="/adminByCertLoginForm")
	public String adminByCertLoginForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.?????? : ????????? ??????
	 * 2.???????????? : 
	 * </pre>
	 * @Location : eGs_epro_sdbc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : login
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	@RequestMapping(value="/certLogin")
	public String certLogin(final HttpServletRequest request, final Model model) throws SQLException, IOException, ParseException{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		//String strCert = parameterMap.getString("strCert");
		String loginData = parameterMap.getString("loginData");
		
		parameterMap.put("P_CD_ID", "CERT_STATUS");
		parameterMap.put("P_ETC_VAL1", "Y");
		List<FwkDataEntity> certList = comCmcdDetailCdDao.selectCdValueListByCdId(parameterMap);
		int cert_verify = 0;         
		if( certList != null && certList.size() > 0){
			FwkDataEntity cert = certList.get(0);
			//cert_verify = cert.getInt("CD_VALUE_NM");
			cert_verify = 2;	//????????????
		}
	     
		
		
	    SignVerifier sv = new SignVerifier(	loginData 			/*???????????????*/
	            											,cert_verify		/*??????????????? ??????*/
	            											,0);					/*????????? ????????? ???
	            																		base64 = 0
	            																		hex = 1          */ 
	    sv.verify();
	     
	    // 0 : ??????
	    // -10 : ?????? 
	    // -20 : ??????
	    // -30 : ????????????
        
	    int nVerifierResult = sv.getLastErrorCode();
		//int nVerifierResult = 0;
            
	    if(nVerifierResult == 0) {
	    	//?????? IP ??????
			String ip = request.getHeader("X-FORWARDED-FOR");
			if(ip == null || ip.equals("")) {
				ip = request.getRemoteAddr();
			}
			parameterMap.put("CONN_IP", ip);		
			
			if (parameterMap.get("P_BIZRNO") != null && !parameterMap.getString("P_BIZRNO").equals("")) {
				parameterMap.put("P_LOGIN_ID", parameterMap.get("P_BIZRNO"));
				trans = iproMainLoginFormService.loginByBizrno(parameterMap);
				FwkDataEntity dataEntity = (FwkDataEntity) trans.get(IproMainLoginFormService.LOGIN_RESULT);
				
				if(dataEntity == null){
					trans.put("code", "fail");
					trans.put("msg", "?????????????????? ?????? ???????????????.");
				}else{
					//????????????????????? ?????????????????? ??????
					dataEntity.put("LOGIN_ID", dataEntity.get("USR_ID"));
					dataEntity.put("LOGIN_GBN", "CERT");
					dataEntity.put("CONN_IP",ip);
					
					parameterMap.put(IproMainLoginFormService.LOGIN_RESULT, dataEntity);
					request.getSession().setAttribute(IproMainLoginFormService.LOGIN_RESULT, dataEntity);
					trans.put("code", "success");
					trans.put(IproMainLoginFormService.LOGIN_RESULT, dataEntity);
				}
			}else{
				trans.put("code", "fail");
				trans.put("msg", "????????? ????????? ????????????.");
			}
	    }else if(nVerifierResult == -10){
	    	System.out.println("???????????? ?????????????????????.");
	    	trans.put("code", sv.getLastErrorCode());
	    	trans.put("msg", sv.getLastErrorMsg());                                                                	
	    }else if(nVerifierResult == -20){
	    	System.out.println("???????????? ?????????????????????.");
	    	trans.put("code", sv.getLastErrorCode());
	    	trans.put("msg", sv.getLastErrorMsg());                                                                	
	    }else if(nVerifierResult == -30){ //OCSP. CRL ??????
	    	System.out.println("???????????? ??????(??????)??? ????????????.");
	    	trans.put("code", sv.getLastErrorCode());
	    	trans.put("msg", sv.getLastErrorMsg());                                                                	
	    }else { // VestSign ??????????????? ??????
	    	System.out.println("???????????? ??????(???????????????)??? ????????????.");
	    	trans.put("code", sv.getLastErrorCode());
	    	trans.put("msg", sv.getLastErrorMsg());
	    }
		
	    
		model.addAttribute("code",trans.get("code"));
		model.addAttribute("msg",trans.get("msg"));
		model.addAttribute(IproMainLoginFormService.LOGIN_RESULT,trans.get(IproMainLoginFormService.LOGIN_RESULT));
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.?????? : ????????? ?????? 
	 * 2.???????????? : ????????????
	 * </pre>
	 * @Location : eGs_epro_sdbc.com.eunwoosoft.ipro.main.controller.IproMainLoginFormController.java
	 * @Method : emgncLoginForm
	 * @author : SungYoon_Ha
	 * @date : 2017. 5. 30. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/emgncLoginForm")
	public String emgncLoginForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		// ????????????
		parameterMap.put("P_PAGE_NO", "1");
		parameterMap.put("P_PAGE_SIZE", "4");
		trans = iproNotiService.notiListWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		// FAQ
		parameterMap.put("P_PAGE_NO", "1");
		parameterMap.put("P_PAGE_SIZE", "5");
		trans = iproFaqService.faqListWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		System.out.println("EW.EPRO.VEND_REG_NO   ::::   "+ FwkMessageUtil.getMessage("EW.EPRO.VEND_REG_NO"));
		
		model.addAllAttributes(trans);
		return parameterMap.getViewName(prefixUrl);
	}
	
	@RequestMapping(value="/emgncLogoutForm")
	public String emgncLogoutForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.?????? : ????????? ??????
	 * 2.???????????? : 
	 * </pre>
	 * @Location : eGs_epro_sdbc.com.eunwoosoft.ipro.main.controller.IproMainLoginFormController.java
	 * @Method : emgncEmplyrLogin
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 12. 
	 * @param request
	 * @param model
	 * @return
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	@RequestMapping(value="/emgncEmplyrLogin")
	public String emgncEmplyrLogin(final HttpServletRequest request, final Model model) throws SQLException, IOException, ParseException{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproMainLoginFormService.login(parameterMap);
		parameterMap = (FwkParameterMap) trans.get(IproMainLoginFormService.LOGIN_RESULT);
		FwkDataEntity dataEntity = (FwkDataEntity) parameterMap.get(IproMainLoginFormService.RESULT_DATA);
		
		String resultCode = parameterMap.getString(IproMainLoginFormService.RESULT_CODE);
		
		/**
		 * P_SYS_CONN_SECD : ????????????(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : ???????????? (?????? ?????????)
		 * P_CONN_URL : ??????url
		 * P_MENU_ID : ??????ID (?????? ?????????)
		 */
		//?????? IP ??????
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		
		System.err.println("ip    :::: :" + ip);
		
		parameterMap.put("CONN_IP", ip);
		parameterMap.put("P_CONN_IP", ip);
		parameterMap.put("P_USR_ID", dataEntity.get("USR_ID"));
		parameterMap.put("P_USR_NM", dataEntity.get("USR_NM"));
		parameterMap.put("P_SYS_CONN_SECD", "LOGIN");
		parameterMap.put("P_CONN_CNTN", "?????????");
		parameterMap.put("P_CONN_URL", "/main/emgncLoginForm.do");
		parameterMap.put("P_MENU_ID", "emgncLoginForm");
		
		
		
		iproCommDefaultService.sendLOG(parameterMap);

		dataEntity.put("CONN_IP", ip);
		dataEntity.put("P_CONN_IP", ip);
		
		parameterMap.put(IproMainLoginFormService.RESULT_DATA, dataEntity);
		
		request.getSession().setAttribute(IproMainLoginFormService.LOGIN_RESULT, dataEntity);
		
		System.err.println("parameterMap    :::: :" + parameterMap.toString());
		
		//System.err.println("getSession    :::: :" + );
		
		model.addAttribute(IproMainLoginFormService.LOGIN_RESULT, parameterMap);
		return JSON_VIEW ;
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.?????? : 
	 * 2.???????????? : 
	 * </pre>
	 * @Location : eGs_epro_sdbc.com.eunwoosoft.ipro.main.controller.IproMainLoginFormController.java
	 * @Method : emgncEmplyrLogout
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/emgncEmplyrLogout")
	public String emgncEmplyrLogout(final HttpServletRequest request, final Model model){
		
		HttpSession session = request.getSession();		
		session.invalidate();
		
		return JSON_VIEW;
	} 

	//?????? detail popup
	@RequestMapping(value="/popup/noticePopup")
	public String noticePopup(final HttpServletRequest request, final Model model){

		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.noticePopup(parameterMap);
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrlPopup);
	} 

	//?????? List popup
	@RequestMapping(value="/popup/noticeListPopup")
	public String noticeListPopup(final HttpServletRequest request, final Model model){
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.noticeListPopup(parameterMap);
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrlPopup);
	} 
	
	
	
	//help popup
	@RequestMapping(value="/popup/helpPopup")
	public String helpPopup(final HttpServletRequest request, final Model model){

		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		//FwkTransferObject trans = iproCommPopupService.noticePopup(parameterMap);
		model.addAllAttributes(parameterMap);
		//model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrlPopup);
	} 
	
	/**
	 * <pre>
	 * 1.?????? : SSO ?????????
	 * 2.???????????? : 
	 * </pre>
	 * @Location : eGs_epro_sdbc.com.eunwoosoft.ipro.main.controller.IproMainLoginFormController.java
	 * @Method : ssoEmplyrLogin
	 * @author : ?????????
	 * @date : 2019. 03. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ssoEmplyrLogin")
	public String ssoEmplyrLogin(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		parameterMap.put("request", request);
		//?????? IP ??????
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		parameterMap.put("CONN_IP", ip);
		parameterMap.put("P_SSO_FLAG", "Y");
		parameterMap.put("P_USR_ID", request.getSession().getAttribute("SSO_ID"));
				
		FwkTransferObject trans = iproMainLoginFormService.login(parameterMap);
		parameterMap = (FwkParameterMap) trans.get(IproMainLoginFormService.LOGIN_RESULT);
		FwkDataEntity dataEntity = (FwkDataEntity) parameterMap.get(IproMainLoginFormService.RESULT_DATA);
		
		String resultCode = parameterMap.getString(IproMainLoginFormService.RESULT_CODE);
		
		//Log ??????
		/**
		 * P_SYS_CONN_SECD : ????????????(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : ???????????? (?????? ?????????)
		 * P_CONN_URL : ??????url
		 * P_MENU_ID : ??????ID (?????? ?????????)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "SSO_LOGIN");
		parameterMap.put("P_CONN_CNTN", "SSO ?????????");
		parameterMap.put("P_CONN_URL", "/main/ssoEmplyrLogin.do");
		parameterMap.put("P_MENU_ID", "ssoEmplyrLogin");
		parameterMap.put("P_CONN_IP", ip);
		parameterMap.put("P_USR_ID", request.getSession().getAttribute("SSO_ID"));
						
		iproCommDefaultService.sendLOG(parameterMap);

		/* ????????? ?????? ?????? ??????
		 * 
		 * */ 
		if("0".equals(resultCode)){
			String orcCd = (String) dataEntity.get("DEPT_NO");
			if("611211".equals(orcCd)){
				dataEntity.put("PRCURE_BSNS_SE_CD", "ABID");
			}else if("611217".equals(orcCd)){
				dataEntity.put("PRCURE_BSNS_SE_CD", "GBID");
			}else if("611221".equals(orcCd)){
				dataEntity.put("PRCURE_BSNS_SE_CD", "TBID");
			}else{
				dataEntity.put("PRCURE_BSNS_SE_CD", "BBID");
			}
			/*
			 * BID_SYS : ???????????? ????????? ??????
			 * */
			/*
			ArrayList roleList = (ArrayList)dataEntity.get("roleList");
			dataEntity.put("BID_SYS", "N");
			if(roleList.indexOf("BID_SYS") > 0){
				dataEntity.put("BID_SYS_AT", "Y");
			}
			*/
			
			parameterMap.put(IproMainLoginFormService.RESULT_DATA, dataEntity);
			
			request.getSession().setAttribute(IproMainLoginFormService.LOGIN_RESULT, dataEntity);
			
			model.addAttribute(IproMainLoginFormService.LOGIN_RESULT, parameterMap);
			return "forward:/main/mainPage.do";
		}else{
			return "forward:/main/emgncLogoutForm.do";
		}
	}
	
	
	
	
	@RequestMapping(value="/SBDC_sugarmanWork")
	public String SBDC_sugarmanWork(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();

		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.?????? : ????????? ??????
	 * 2.???????????? : 
	 * </pre>
	 * @Location : eGs_epro_sdbc.com.eunwoosoft.ipro.main.controller.IproMainLoginFormController.java
	 * @Method : iproSBDC_sugarmanWorkConn
	 * @param request
	 * @param model
	 * @return
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	@RequestMapping(value="/iproSBDC_sugarmanWorkConn")
	public String SBDC_sugarmanWorkConn(final HttpServletRequest request, final Model model) throws SQLException, IOException, ParseException{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproMainLoginFormService.iproSBDC_sugarmanWorkConn(parameterMap);
		parameterMap = (FwkParameterMap) trans.get(IproMainLoginFormService.LOGIN_RESULT);
		FwkDataEntity dataEntity = (FwkDataEntity) parameterMap.get(IproMainLoginFormService.RESULT_DATA);
		
		String resultCode = parameterMap.getString(IproMainLoginFormService.RESULT_CODE);

		parameterMap.put(IproMainLoginFormService.RESULT_DATA, dataEntity);
		
		request.getSession().setAttribute(IproMainLoginFormService.LOGIN_RESULT, dataEntity);
		
		model.addAttribute(IproMainLoginFormService.LOGIN_RESULT, parameterMap);
		return JSON_VIEW ;
	}
	
	
	
	
}
