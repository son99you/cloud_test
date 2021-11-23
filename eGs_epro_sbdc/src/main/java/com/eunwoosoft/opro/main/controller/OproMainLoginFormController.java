package com.eunwoosoft.opro.main.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.code.dao.ComCmcdDetailCdDao;
import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.opro.main.service.OproMainLoginFormService;
import com.eunwoosoft.opro.noti.service.OproNotiGnrlService;
import com.yettiesoft.vestsign.external.SignVerifier;

import tradesign.crypto.provider.JeTS;
import tradesign.pki.pkix.Login;
import tradesign.pki.pkix.X509Certificate;
import tradesign.pki.util.JetsUtil;

/**
 * 로그인/메인
 * com.eunwoosoft.opro.main.controller
 * OproMainLoginFormController.java
 *
 * @Author : SungYoon_Ha 
 * @Date   : 2017. 6. 14.
 *
 */
@RequestMapping(value = "/opro/main")
@Controller
public class OproMainLoginFormController extends AbstractOproMenuController {
	String prefixUrl = "/opro/views/main/"; 
	String prefixUrlPopup = "/opro/views/main/popup/"; 
	
	@Resource(name="oproMainLoginFormService")
	private OproMainLoginFormService oproMainLoginFormService;
	
	@Resource(name="oproNotiGnrlService")    
	private OproNotiGnrlService oproNotiGnrlService;

	@Resource(name="comCmcdDetailCdDao")
    private ComCmcdDetailCdDao comCmcdDetailCdDao;

	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	/**
	 * <pre>
	 * 1.개요 : INDEX 화면
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : emgncLoginForm
	 * @author : SungYoon_Ha
	 * @date : 2017. 6. 14. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/emgncLoginForm")
	public String emgncLoginForm(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_CD_ID", "ESTM_SECD");
		List<FwkDataEntity> estmSecdCodeList = comCmcdDetailCdDao.selectCdValueListByCdId(parameterMap);
		trans.put("estmSecdCodeList", estmSecdCodeList);
		model.addAllAttributes(trans);
		
		
		// 평가공고
		parameterMap.put("P_PAGE_NO", "1");
		parameterMap.put("P_PAGE_SIZE", "3");
		trans = oproMainLoginFormService.estmAnncList(parameterMap);
		trans.put("estmAnncList", trans.get("estmAnncList"));
		trans.put("estmAnncListTotCnt", trans.get("estmAnncListTotCnt"));
		model.addAllAttributes(trans);
		
		
		// 공지
		parameterMap.put("P_PAGE_NO", "1");
		parameterMap.put("P_PAGE_SIZE", "4");
		parameterMap.put("P_BBS_SECD", "NOTI");
		trans = oproNotiGnrlService.notiGnrlListWithPgng(parameterMap);
		trans.put("notiList", trans.get("notiGnrlList"));
		trans.put("notiListTotCnt", trans.get("notiGnrlListTotCnt"));
		model.addAllAttributes(trans);
		
		// 자주하는 질문
		parameterMap.put("P_PAGE_NO", "1");
		parameterMap.put("P_PAGE_SIZE", "5");
		parameterMap.put("P_BBS_SECD", "FAQ");
		trans = oproNotiGnrlService.notiGnrlListWithPgng(parameterMap);
		trans.put("faqList", trans.get("notiGnrlList"));
		trans.put("faqListTotCnt", trans.get("notiGnrlListTotCnt"));
		model.addAllAttributes(trans);
		
		// 인증서정보
		//System.err.println("###################");
		//System.err.println(FwkMessageUtil.getMessage("EW.SERV.PKI.PROP")+"tradesign3280.properties");
		//System.err.println("###################");
		JeTS.installProvider(FwkMessageUtil.getMessage("EW.SERV.PKI.PROP")+"tradesign3280.properties");
		trans.put("loginDataKmCert", JeTS.getPemKmCert(0));
		request.setAttribute("loginDataKmCert", JeTS.getPemKmCert(0));
		
		model.addAllAttributes(trans);
		//System.err.println("@@@ trans ==> " + trans);
		
		model.addAttribute("trans", trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원 우회 로그인 화면 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sdbc.com.eunwoosoft.ipro.main.controller.IproMainLoginFormController.java
	 * @Method : emgncCmtmLoginForm
	 * @author : SonYeonWoo
	 * @date : 2021. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/emgncCmtmLoginForm")
	public String emgncCmtmLoginForm(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_CD_ID", "ESTM_SECD");
		List<FwkDataEntity> estmSecdCodeList = comCmcdDetailCdDao.selectCdValueListByCdId(parameterMap);
		trans.put("estmSecdCodeList", estmSecdCodeList);
		model.addAllAttributes(trans);
		
		
		// 평가공고
		parameterMap.put("P_PAGE_NO", "1");
		parameterMap.put("P_PAGE_SIZE", "3");
		trans = oproMainLoginFormService.estmAnncList(parameterMap);
		trans.put("estmAnncList", trans.get("estmAnncList"));
		trans.put("estmAnncListTotCnt", trans.get("estmAnncListTotCnt"));
		model.addAllAttributes(trans);
		
		
		// 공지
		parameterMap.put("P_PAGE_NO", "1");
		parameterMap.put("P_PAGE_SIZE", "4");
		parameterMap.put("P_BBS_SECD", "NOTI");
		trans = oproNotiGnrlService.notiGnrlListWithPgng(parameterMap);
		trans.put("notiList", trans.get("notiGnrlList"));
		trans.put("notiListTotCnt", trans.get("notiGnrlListTotCnt"));
		model.addAllAttributes(trans);
		
		// 자주하는 질문
		parameterMap.put("P_PAGE_NO", "1");
		parameterMap.put("P_PAGE_SIZE", "5");
		parameterMap.put("P_BBS_SECD", "FAQ");
		trans = oproNotiGnrlService.notiGnrlListWithPgng(parameterMap);
		trans.put("faqList", trans.get("notiGnrlList"));
		trans.put("faqListTotCnt", trans.get("notiGnrlListTotCnt"));
		model.addAllAttributes(trans);
		
		// 인증서정보
		//System.err.println("###################");
		//System.err.println(FwkMessageUtil.getMessage("EW.SERV.PKI.PROP")+"tradesign3280.properties");
		//System.err.println("###################");
//		JeTS.installProvider(FwkMessageUtil.getMessage("EW.SERV.PKI.PROP")+"tradesign3280.properties");
//		trans.put("loginDataKmCert", JeTS.getPemKmCert(0));
//		request.setAttribute("loginDataKmCert", JeTS.getPemKmCert(0));
		
		model.addAllAttributes(trans);
		//System.err.println("@@@ trans ==> " + trans);
		
		model.addAttribute("trans", trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getOproViewName(prefixUrl);
	}
	
	
	@RequestMapping(value="/innerLoginForm")
	public String innerLoginForm(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_PAGE_NO", "1");
		parameterMap.put("P_PAGE_SIZE", "5");
		
		// 일반공지사항
		parameterMap.put("P_BBS_SECD", "NOTI");
		trans = oproNotiGnrlService.notiGnrlListWithPgng(parameterMap);
		trans.put("notiList", trans.get("notiGnrlList"));
		trans.put("notiListTotCnt", trans.get("notiGnrlListTotCnt"));
		model.addAllAttributes(trans);
		
		// FAQ
		parameterMap.put("P_BBS_SECD", "FAQ");
		trans = oproNotiGnrlService.notiGnrlListWithPgng(parameterMap);
		trans.put("faqList", trans.get("notiGnrlList"));
		trans.put("faqListTotCnt", trans.get("notiGnrlListTotCnt"));
		model.addAllAttributes(trans);
		
		
		model.addAttribute("trans", trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 로그인 페이지
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : loginPage
	 * @author : SungYoon_Ha
	 * @date : 2017. 6. 15. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loginPage")
	public String loginPage(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	@RequestMapping(value="/loginIdPw_dev")
	public String loginIdPw_dev(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		//접속 IP 처리
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		parameterMap.put("CONN_IP", ip);				 
		
		FwkTransferObject trans = oproMainLoginFormService.loginByIdPw_dev(parameterMap);
		parameterMap = (FwkParameterMap) trans.get(OproMainLoginFormService.LOGIN_RESULT);
		FwkDataEntity dataEntity = (FwkDataEntity) parameterMap.get(OproMainLoginFormService.RESULT_DATA);
		
		if(dataEntity != null){
			dataEntity.put("CONN_IP", ip);
			request.getSession().setAttribute(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
		}
		
		model.addAttribute(OproMainLoginFormService.LOGIN_RESULT, parameterMap);
		
		return JSON_VIEW ;
	}
	
	
	
	
	@RequestMapping(value="/loginByRsdnNo")
	public String loginByRsdnNo(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		//접속 IP 처리
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		parameterMap.put("CONN_IP", ip);
		parameterMap.put("P_CONN_IP", ip);
		
		if (parameterMap.get("P_LOGIN_ID") != null && !parameterMap.getString("P_LOGIN_ID").equals("")) {
			trans = oproMainLoginFormService.loginByRsdnNo(parameterMap);
			FwkDataEntity dataEntity = (FwkDataEntity) trans.get(OproMainLoginFormService.LOGIN_RESULT);
			
			if(dataEntity == null){
				trans.put("code", "fail");
				trans.put("msg", "등록되지 않은 평가위원입니다.");
			}else{
				//로그인아이디에 업체등록번호 셋팅
				dataEntity.put("LOGIN_ID", dataEntity.get("ESTM_CMTM_NO"));
				dataEntity.put("CONN_IP", ip);
				dataEntity.put("LOGIN_GBN", parameterMap.get("P_LOGIN_GBN"));   // 평가위원(CMTM), 평가업체(VEND)
				dataEntity.put("LOGIN_MTHD", parameterMap.get("P_LOGIN_MTHD"));   // 주민등록번호(RSDNNO), 공동인증서(ASSO), 브라우저인증서(BROWSER)
				dataEntity.put("LOGIN_DN", parameterMap.get("P_LOGIN_DN"));   // 인증서DN값
				dataEntity.put("CERT_POLICY", parameterMap.get("P_CERT_POLICY"));   // 로그인했던 인증서정책
				
				parameterMap.put(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
				request.getSession().setAttribute(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
				trans.put("code", "success");
				trans.put(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
				
				//Log 저장
				/**
				 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
				 * P_CONN_CNTN : 접속내용 (한글 코멘트)
				 * P_CONN_URL : 접속url
				 * P_MENU_ID : 메뉴ID (호출 함수명)
				 */
				parameterMap.put("P_SYS_CONN_SECD", "LOGIN");
				parameterMap.put("P_CONN_CNTN", "외부평가위원 로그인");
				parameterMap.put("P_CONN_URL", "/opro/main/emgncLoginForm.do");
				parameterMap.put("P_MENU_ID", "emgncLoginForm");
				parameterMap.put("P_USR_ID", dataEntity.get("USR_ID"));
				parameterMap.put("P_USR_NM", dataEntity.get("USR_NM"));
						
				iproCommDefaultService.sendLOG(parameterMap);
			}
		}else{
			trans.put("code", "fail");
			trans.put("msg", "로그인 정보가 없습니다.");
		}
		
		model.addAttribute("code",trans.get("code"));
		model.addAttribute("msg",trans.get("msg"));
		model.addAttribute(OproMainLoginFormService.LOGIN_RESULT,trans.get(OproMainLoginFormService.LOGIN_RESULT));
		
		trans.put("P_LOGIN_ID", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID")));
		trans.put("P_LOGIN_ID_VIEW", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID_VIEW")));
		trans.put("loginData", CmmnUtil.cleanXSS(parameterMap.getString("loginData")));
		trans.put("resourceName", CmmnUtil.cleanXSS(parameterMap.getString("resourceName")));
		parameterMap.put("P_LOGIN_ID", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID")));
		parameterMap.put("P_LOGIN_ID_VIEW", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID_VIEW")));
		parameterMap.put("loginData", CmmnUtil.cleanXSS(parameterMap.getString("loginData")));
		parameterMap.put("resourceName", CmmnUtil.cleanXSS(parameterMap.getString("resourceName")));
		return JSON_VIEW;
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 로그인 처리
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : login
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login")
	public String login(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		//접속 IP 처리
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		parameterMap.put("CONN_IP", ip);
		parameterMap.put("P_CONN_IP", ip);
		
		//String strCert = parameterMap.getString("strCert");
		String loginData = parameterMap.getString("loginData");
		
		parameterMap.put("P_CD_ID", "CERT_STATUS");
		parameterMap.put("P_ETC_VAL1", "Y");
		List<FwkDataEntity> certList = comCmcdDetailCdDao.selectCdValueListByCdId(parameterMap);
		int cert_verify = 0;         
		if( certList != null && certList.size() > 0){
			FwkDataEntity cert = certList.get(0);
			cert_verify = cert.getInt("CD_VALUE_NM");
		}
	     
		
		
	    SignVerifier sv = new SignVerifier(	loginData 			/*전자서명문*/
	            											,cert_verify		/*유효성검증 방법*/
	            											,0);					/*서명문 인코딩 룰
	            																		base64 = 0
	            																		hex = 1          */ 
	    sv.verify();
	     
	    // 0 : 성공
	    // -10 : 만료 
	    // -20 : 폐기
	    // -30 : 기타오류
        
	    int nVerifierResult = sv.getLastErrorCode();
		//int nVerifierResult = 0;
            
	    if(nVerifierResult == 0) {
			
			if (parameterMap.get("P_LOGIN_ID") != null && !parameterMap.getString("P_LOGIN_ID").equals("")) {
				trans = oproMainLoginFormService.loginByBizrno(parameterMap);
				FwkDataEntity dataEntity = (FwkDataEntity) trans.get(OproMainLoginFormService.LOGIN_RESULT);
				
				if(dataEntity == null){
					trans.put("code", "fail");
					trans.put("msg", "회원가입되지 않은 업체입니다.");
				}else{
					//로그인아이디에 업체등록번호 셋팅
					dataEntity.put("LOGIN_ID", dataEntity.get("USR_ID"));
					//dataEntity.put("LOGIN_GBN", "CERT");
					dataEntity.put("CONN_IP",ip);
					
					dataEntity.put("LOGIN_GBN", parameterMap.get("P_LOGIN_GBN"));
					
					parameterMap.put(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
					request.getSession().setAttribute(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
					trans.put("code", "success");
					trans.put(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
					
					//Log 저장
					/**
					 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
					 * P_CONN_CNTN : 접속내용 (한글 코멘트)
					 * P_CONN_URL : 접속url
					 * P_MENU_ID : 메뉴ID (호출 함수명)
					 */
					parameterMap.put("P_SYS_CONN_SECD", "LOGIN");
					parameterMap.put("P_CONN_CNTN", "외부평가위원 로그인");
					parameterMap.put("P_CONN_URL", "/opro/main/emgncLoginForm.do");
					parameterMap.put("P_MENU_ID", "emgncLoginForm");
					parameterMap.put("P_USR_ID", dataEntity.get("USR_ID"));
					parameterMap.put("P_USR_NM", dataEntity.get("USR_NM"));
							
					iproCommDefaultService.sendLOG(parameterMap);
				}
			}else{
				trans.put("code", "fail");
				trans.put("msg", "로그인 정보가 없습니다.");
			}
	    }else if(nVerifierResult == -10){
	    	System.out.println("인증서가 만료되었습니다.");
	    	trans.put("code", sv.getLastErrorCode());
	    	trans.put("msg", sv.getLastErrorMsg());                                                                	
	    }else if(nVerifierResult == -20){
	    	System.out.println("인증서가 폐기되었습니다.");
	    	trans.put("code", sv.getLastErrorCode());
	    	trans.put("msg", sv.getLastErrorMsg());                                                                	
	    }else if(nVerifierResult == -30){ //OCSP. CRL 오류
	    	System.out.println("서명문에 문제(검증)가 있습니다.");
	    	trans.put("code", sv.getLastErrorCode());
	    	trans.put("msg", sv.getLastErrorMsg());                                                                	
	    }else { // VestSign 라이브러리 에러
	    	System.out.println("서명문에 문제(라이브러리)가 있습니다.");
	    	trans.put("code", sv.getLastErrorCode());
	    	trans.put("msg", sv.getLastErrorMsg());
	    }
		
	    
		model.addAttribute("code",trans.get("code"));
		model.addAttribute("msg",trans.get("msg"));
		model.addAttribute(OproMainLoginFormService.LOGIN_RESULT,trans.get(OproMainLoginFormService.LOGIN_RESULT));
		
		trans.put("P_LOGIN_ID", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID")));
		trans.put("P_LOGIN_ID_VIEW", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID_VIEW")));
		trans.put("loginData", CmmnUtil.cleanXSS(parameterMap.getString("loginData")));
		trans.put("resourceName", CmmnUtil.cleanXSS(parameterMap.getString("resourceName")));
		parameterMap.put("P_LOGIN_ID", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID")));
		parameterMap.put("P_LOGIN_ID_VIEW", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID_VIEW")));
		parameterMap.put("loginData", CmmnUtil.cleanXSS(parameterMap.getString("loginData")));
		parameterMap.put("resourceName", CmmnUtil.cleanXSS(parameterMap.getString("resourceName")));
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : ID PW 로 업체 로그인 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : loginIdPw
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loginIdPw")
	public String loginIdPw(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		//접속 IP 처리
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		parameterMap.put("CONN_IP", ip);				
		
		FwkTransferObject trans = oproMainLoginFormService.loginByIdPw(parameterMap);
		parameterMap = (FwkParameterMap) trans.get(OproMainLoginFormService.LOGIN_RESULT);
		FwkDataEntity dataEntity = (FwkDataEntity) parameterMap.get(OproMainLoginFormService.RESULT_DATA);
		
		if(dataEntity != null){
			dataEntity.put("CONN_IP", ip);
			request.getSession().setAttribute(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
		}
		
		model.addAttribute(OproMainLoginFormService.LOGIN_RESULT, parameterMap);
		
		trans.put("P_LOGIN_ID", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID")));
		trans.put("P_PWD", CmmnUtil.cleanXSS(parameterMap.getString("P_PWD")));
		trans.put("P_OTP_NO", CmmnUtil.cleanXSS(parameterMap.getString("P_OTP_NO")));
		trans.put("resourceName", CmmnUtil.cleanXSS(parameterMap.getString("resourceName")));
		parameterMap.put("P_LOGIN_ID", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID")));
		parameterMap.put("P_PWD", CmmnUtil.cleanXSS(parameterMap.getString("P_PWD")));
		parameterMap.put("P_OTP_NO", CmmnUtil.cleanXSS(parameterMap.getString("P_OTP_NO")));
		parameterMap.put("resourceName", CmmnUtil.cleanXSS(parameterMap.getString("resourceName")));
		
		return JSON_VIEW ;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : logout
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logout(final HttpServletRequest request, final Model model){
		
		HttpSession session = request.getSession();		
		session.invalidate();
		
		return JSON_VIEW;
	}

	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 약관동의 페이지
	 *  		  사업자번호 체크 페이지 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : loginPage
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 8. 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/mberSbscrbForm")
	public String mberSbscrbForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		//parameterMap.put("topMenu", "Y");	// 회원가입 약관동의 화면에서는 필요없음 
											// 설정 tiles-def 때문
											// indexHeader.jsp 부분 
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 회원가입 폼 페이지 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : mberSbscrbForm
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 8. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/joinFormPage")
	public String joinFormPage(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 등록되어 있는 업체인지 확인 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : joinEnpaCheck
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 12. 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/joinEnpaCheck")
	public String joinEnpaCheck(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			 trans = oproMainLoginFormService.joinEnpaCheck(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		model.addAllAttributes(trans);
		 
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 회원가입
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : joinRegist
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 9. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/joinRegist")
	public String joinRegist(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		//String strCert = parameterMap.getString("strCert");
		String loginData = parameterMap.getString("loginData");
		
		parameterMap.put("P_CD_ID", "CERT_STATUS");
		parameterMap.put("P_ETC_VAL1", "Y");
		List<FwkDataEntity> certList = comCmcdDetailCdDao.selectCdValueListByCdId(parameterMap);
		int cert_verify = 0;         
		if( certList != null && certList.size() > 0){
			FwkDataEntity cert = certList.get(0);
			cert_verify = cert.getInt("CD_VALUE_NM");
		}

		
	    SignVerifier sv = new SignVerifier(	loginData 			/*전자서명문*/
	            											,0		/*유효성검증 방법*/
	            											,0);					/*서명문 인코딩 룰
	            																		base64 = 0
	            																		hex = 1          */ 
	    sv.verify();
	    
	    // 0 : 성공
	    // -10 : 만료 
	    // -20 : 폐기
	    // -30 : 기타오류
        
	    int nVerifierResult = sv.getLastErrorCode();
            
	    if(nVerifierResult == 0) {
	    	//접속 IP 처리
			String ip = request.getHeader("X-FORWARDED-FOR");
			if(ip == null || ip.equals("")) {
				ip = request.getRemoteAddr();
			}
			parameterMap.put("CONN_IP", ip);		
			
			try{
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
				parameterMap.put("multipartRequest", multipartRequest);
				oproMainLoginFormService.joinRegist(parameterMap);
			}catch(Exception ex){
				response.setStatus(999);
				throw new Exception(ex.toString());
			}
	    }else if(nVerifierResult == -10){
	    	System.err.println("인증서가 만료되었습니다.");
	    	throw new Exception("인증서가 만료되었습니다.");
	    }else if(nVerifierResult == -20){
	    	System.err.println("인증서가 폐기되었습니다.");
	    	throw new Exception("인증서가 폐기되었습니다.");
	    }else if(nVerifierResult == -30){ //OCSP. CRL 오류
	    	System.err.println("서명문에 문제(검증)가 있습니다.");
	    	throw new Exception("서명문에 문제(검증)가 있습니다.");
	    }else { // VestSign 라이브러리 에러
	    	System.err.println("서명문에 문제(라이브러리)가 있습니다.");
	    	throw new Exception("서명문에 문제(라이브러리)가 있습니다.");
	    }
	    
	    trans.put("P_VEND_STCD_MSG", "신규 가입 또는 정보 수정 후에는\n한국전기연구원 구매자산실 담당자의\n승인 후에 입찰/견적 참여 가능.\n승인 후 별도의 알림메시지 발송.\n(문의 : 055-280-1234)");
	    model.addAllAttributes(trans);
	    
		return "forward:/opro/main/emgncLoginForm.do";
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 회원가입 성공화면 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : joinSuccessView
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 9. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/joinSuccessView")
	public String joinSuccessView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		System.err.println("parameterMap==>" + parameterMap);
		System.err.println("parameterMap==>" + parameterMap.get("P_LOGIN_ID"));
		parameterMap.put("loginId", parameterMap.get("P_LOGIN_ID"));
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		// -> /opro/views/main/joinSuccessView.jsp
		// -> /jsp/main/joinSuccessView.jsp
		System.err.println("prefixUrl==>" + prefixUrl);
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : helpPopup
	 * @author : JanDi_Eun
	 * @date : 2019. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/popup/helpPopup")
	public String helpPopup(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		
		return parameterMap.getOproPopupViewName(prefixUrlPopup);		
	}
	
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : noticePopup
	 * @author : JanDi_Eun
	 * @date : 2019. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/popup/noticePopup")
	public String noticePopup(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = oproNotiGnrlService.notiGnrlDetail(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getOproPopupViewName(prefixUrlPopup);		
	}
	
	@RequestMapping(value="/popup/noticeListPopup")
	public String noticeListPopup(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = oproNotiGnrlService.notiGnrlListWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getOproPopupViewName(prefixUrlPopup);		
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 오늘 할 일 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : toDoListPopup
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 14. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/popup/toDoListPopup")
	public String toDoListPopup(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = oproMainLoginFormService.toDoList(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getOproPopupViewName(prefixUrlPopup);		
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 부서안내 팝업 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : deptGuidList
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 17. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/popup/deptGuidList")
	public String deptGuidList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getOproPopupViewName(prefixUrlPopup);		
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 사용자 PC 환경설정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : usrPCSet
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 17. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/popup/usrPCSet")
	public String usrPCSet(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getOproPopupViewName(prefixUrlPopup);		
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 용어정리 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : trmList
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 17. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/popup/trmList")
	public String trmList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getOproPopupViewName(prefixUrlPopup);		
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가구분에 따른 평가공고 조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.controller.OproMainLoginFormController.java
	 * @Method : estmAnncList
	 * @author : JanDi_Eun
	 * @date : 2021. 5. 06. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmAnncList")
	public String estmAnncList(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			 trans = oproMainLoginFormService.estmAnncList(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		model.addAllAttributes(trans);
		 
		return JSON_VIEW;
	}
	
	@RequestMapping(value="/rsdnNoLoginCeck")
	public String rsdnNoLoginCeck(final HttpServletRequest request, final Model model, HttpServletResponse response){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			
			JeTS.installProvider(FwkMessageUtil.getMessage("EW.SERV.PKI.PROP")+"tradesign3280.properties");
			
			//System.err.println("@@@ loginData ==> " + parameterMap.get("loginData") + "");

			String message = new String(parameterMap.getTrimString("loginData").getBytes("ISO-8859-1"), "euc-kr");
					//new String(parameterMap.getString("logindata").getBytes("ISO-8859-1"), "utf-8");
					//new String(request.getParameter("logindata").getBytes("ISO-8859-1"), "utf-8");
			//System.err.println("@@@ message ==> " + message);
		
			// 클라이언트에서 보내온 로그인메시지를 base64디코딩하여 login_request에 저장한다.
			byte[] login_request = tradesign.pki.util.JetsUtil.base64ToBytes(message);
			
			// Login객체 생성
			Login login1 = new Login(login_request);

			login1.setupCipher(JeTS.getServerkmCert(0), JeTS.getServerkmPriKey(0), JeTS.getServerKmKeyPassword(0));
			login1.parseLoginData(true);
			
			// 클라이언트에서 보내온 로그인메시지에서 사용자데이터영역을 userdata에 저장한다.	
//			String userdata = new String(login1.getUserData());	
			String ssnumber = new String(login1.getSsn());

			// 클라이언트에서 보내온 로그인메시지에서 로그인을 시도한 사용자의 인증서를 읽는다.
			X509Certificate[] certs = login1.getSignerCerts();

			String SubjectDNStr[] = null; 
			String NotBeforeStr[] = null;
			String NotAfterStr[] = null; 
			String SerialNumber[] = null;
			String IssuerDNStr[] = null; 
			String SignatureAlgorithm[] = null;
			
			
			if (certs != null)
			{		
				SubjectDNStr  = new String[certs.length];
				NotBeforeStr  = new String[certs.length];
				NotAfterStr  = new String[certs.length];
				SerialNumber  = new String[certs.length];
				IssuerDNStr  = new String[certs.length];
				SignatureAlgorithm  = new String[certs.length];						
				
				for (int i = 0; i < certs.length; i++)
				{
					SubjectDNStr[i] = certs[i].getSubjectDNStr();
					NotBeforeStr[i] = certs[i].getNotBefore().toString();
					NotAfterStr[i] = certs[i].getNotAfter().toString();
					SerialNumber[i] = certs[i].getSerialNumber().toString();
					IssuerDNStr[i] = certs[i].getIssuerDNStr();
					SignatureAlgorithm[i] = certs[i].getSignatureAlgorithm().toString();
					
					
				}
			}
			
			X509Certificate cert = certs[0];
			boolean ret = cert.VerifyIDN(ssnumber, login1.getRandom());
			
			String idconfirm="";
			String resultCode="";
			
			if (ret){
				idconfirm="신원확인 성공";
				resultCode = "success";
			}else{
				idconfirm="신원확인 실패";
				resultCode = "fail";
			}
			
			String strUserData ="", strRandom = "", strSessionId = "";
				
//			strUserData = new String(login1.getUserData());
			
			strRandom = JetsUtil.toBase64String(login1.getRandom());
//			strSessionId = new String(login1.gettid());	
			
			trans.put("idconfirm", idconfirm);
			trans.put("resultCode", resultCode);
			
			model.addAllAttributes(trans);
			
		} catch(Exception e) {
			e.printStackTrace();
			//out.println("에러 발생:" + e.getMessage() + "<br>");
		}
		
		return JSON_VIEW;
	}
	
	@RequestMapping(value="/oproSBDC_sugarmanWorkConn")
	public String oproSBDC_sugarmanWorkConn(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		String ip = request.getHeader("X-FORWARDED-FOR");
		String oproSBDC_PWD = "ewSBDCestm2021!@";
		
		if (parameterMap.get("P_LOGIN_ID") != null && !parameterMap.getString("P_LOGIN_ID").equals("")) {
			trans = oproMainLoginFormService.loginByRsdnNo(parameterMap);
			FwkDataEntity dataEntity = (FwkDataEntity) trans.get(OproMainLoginFormService.LOGIN_RESULT);
			
			if(dataEntity == null){
				trans.put("code", "fail");
				trans.put("msg", "등록되지 않은 평가위원입니다.");
			}else{
				
				String PWD = parameterMap.getString("P_ESTM_PWD");
				
				if(!oproSBDC_PWD.equals(PWD)){
					trans.put("code", "fail");
					trans.put("msg", "비밀번호가 맞지 않습니다.");
				}else{
					
					dataEntity.put("LOGIN_ID", dataEntity.get("ESTM_CMTM_NO"));
					dataEntity.put("CONN_IP", ip);
					dataEntity.put("LOGIN_GBN", parameterMap.get("P_LOGIN_GBN"));   // 평가위원(CMTM), 평가업체(VEND)
					dataEntity.put("LOGIN_MTHD", parameterMap.get("P_LOGIN_MTHD"));   // 주민등록번호(RSDNNO), 공동인증서(ASSO), 브라우저인증서(BROWSER)
					dataEntity.put("LOGIN_DN", parameterMap.get("P_LOGIN_DN"));   // 인증서DN값
					dataEntity.put("CERT_POLICY", parameterMap.get("P_CERT_POLICY"));   // 로그인했던 인증서정책
					
					parameterMap.put(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
					request.getSession().setAttribute(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
					trans.put("code", "success");
					trans.put(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
				}
			}
		}else{
			trans.put("code", "fail");
			trans.put("msg", "로그인 정보가 없습니다.");
		}
		
		model.addAttribute("code",trans.get("code"));
		model.addAttribute("msg",trans.get("msg"));
		model.addAttribute(OproMainLoginFormService.LOGIN_RESULT,trans.get(OproMainLoginFormService.LOGIN_RESULT));
		
		trans.put("P_LOGIN_ID", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID")));
		trans.put("P_LOGIN_ID_VIEW", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID_VIEW")));
		trans.put("loginData", CmmnUtil.cleanXSS(parameterMap.getString("loginData")));
		trans.put("resourceName", CmmnUtil.cleanXSS(parameterMap.getString("resourceName")));
		parameterMap.put("P_LOGIN_ID", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID")));
		parameterMap.put("P_LOGIN_ID_VIEW", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID_VIEW")));
		parameterMap.put("loginData", CmmnUtil.cleanXSS(parameterMap.getString("loginData")));
		parameterMap.put("resourceName", CmmnUtil.cleanXSS(parameterMap.getString("resourceName")));
		
		return JSON_VIEW;
	}
	
	
	@RequestMapping(value="/emgncCmtmEmplyrLogin")
	public String emgncCmtmEmplyrLogin(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		String oproSBDC_PWD = "ewSBDCestm2021!@";
		
		// 평가담당자 ID/PW 체크
		
		
		
		
		if (parameterMap.get("P_LOGIN_ID") != null && !parameterMap.getString("P_LOGIN_ID").equals("")) {
			//trans = oproMainLoginFormService.loginByRsdnNo(parameterMap);
			trans = oproMainLoginFormService.emgncCmtmEmplyrLoginByRsdnNo(parameterMap);
			FwkDataEntity dataEntity = (FwkDataEntity) trans.get(OproMainLoginFormService.LOGIN_RESULT);
			String transResultCode = trans.get("resultCode").toString();
			
			if(dataEntity == null){
				/**
				 * 평가위원 주민번호가 다른경우
				 * 등록이 잘못되었거나 로그인시 입력이 잘못되었거나
				 */
				trans.put("code", "fail");
				trans.put("msg", "등록되지 않은 평가위원입니다.");
			}else{
				/**
				 * 평가담당자 id/pw가 맞지 않는 경우
				 */
				if(!transResultCode.equals("0")){
					trans.put("code", "fail");
					trans.put("msg", "평가담당자 정보가 맞지 않습니다.");
				}else{
					
					dataEntity.put("LOGIN_ID", dataEntity.get("ESTM_CMTM_NO"));
					dataEntity.put("CONN_IP", ip);
					dataEntity.put("LOGIN_GBN", parameterMap.get("P_LOGIN_GBN"));   // 평가위원(CMTM), 평가업체(VEND)
					dataEntity.put("LOGIN_MTHD", parameterMap.get("P_LOGIN_MTHD"));   // 주민등록번호(RSDNNO), 공동인증서(ASSO), 브라우저인증서(BROWSER)
					dataEntity.put("LOGIN_DN", parameterMap.get("P_LOGIN_DN"));   // 인증서DN값
					dataEntity.put("CERT_POLICY", parameterMap.get("P_CERT_POLICY"));   // 로그인했던 인증서정책
					
					parameterMap.put(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
					request.getSession().setAttribute(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
					trans.put("code", "success");
					trans.put(OproMainLoginFormService.LOGIN_RESULT, dataEntity);
					
					//Log 저장
					/**
					 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
					 * P_CONN_CNTN : 접속내용 (한글 코멘트)
					 * P_CONN_URL : 접속url
					 * P_MENU_ID : 메뉴ID (호출 함수명)
					 */
					parameterMap.put("P_SYS_CONN_SECD", "CMTM_EMGNC_LOGIN");
					parameterMap.put("P_CONN_CNTN", "평가위원 비상로그인 [평가담당자사번 : " + parameterMap.get("P_USR_ID") + " , 평가위원명 : " + dataEntity.get("USR_NM") + "]");
					parameterMap.put("P_CONN_URL", "/opro/main/emgncCmtmLoginForm.do");
					parameterMap.put("P_MENU_ID", "emgncCmtmLoginForm");
					parameterMap.put("P_USR_ID", dataEntity.get("USR_ID"));
					parameterMap.put("P_USR_NM", dataEntity.get("USR_NM"));
					parameterMap.put("P_CONN_IP", ip);
							
					iproCommDefaultService.sendLOG(parameterMap);
					
					
					
					
				}
			}
		}else{
			trans.put("code", "fail");
			trans.put("msg", "로그인 정보가 없습니다.");
		}
		
		model.addAttribute("code",trans.get("code"));
		model.addAttribute("msg",trans.get("msg"));
		model.addAttribute(OproMainLoginFormService.LOGIN_RESULT,trans.get(OproMainLoginFormService.LOGIN_RESULT));
		
		trans.put("P_LOGIN_ID", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID")));
		trans.put("P_LOGIN_ID_VIEW", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID_VIEW")));
		trans.put("loginData", CmmnUtil.cleanXSS(parameterMap.getString("loginData")));
		trans.put("resourceName", CmmnUtil.cleanXSS(parameterMap.getString("resourceName")));
		parameterMap.put("P_LOGIN_ID", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID")));
		parameterMap.put("P_LOGIN_ID_VIEW", CmmnUtil.cleanXSS(parameterMap.getString("P_LOGIN_ID_VIEW")));
		parameterMap.put("loginData", CmmnUtil.cleanXSS(parameterMap.getString("loginData")));
		parameterMap.put("resourceName", CmmnUtil.cleanXSS(parameterMap.getString("resourceName")));
		
		return JSON_VIEW;
	}
	
	
}