package com.eunwoosoft.frwk.prl.request;

import javax.security.auth.login.LoginException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;

public class FwkParameterSessionInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(FwkParameterSessionInterceptor.class);
	
	protected static final String PARAMETER_MAP = "parameterMap";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getRequestURI().indexOf("opro/") > -1 || request.getRequestURI().indexOf("/com/atfi/") > -1 
				|| request.getRequestURI().indexOf("/comm/popup/") > -1 || request.getRequestURI().indexOf("/cont/emptyPopup/") > -1 
				|| request.getRequestURI().indexOf("/error") > -1 || request.getRequestURI().indexOf("/com/atma/") > -1 
				|| request.getRequestURI().indexOf("/ssoError") > -1
				|| request.getRequestURI().indexOf("/sessionError") > -1
				|| request.getRequestURI().indexOf("opro/main/") > -1
				) {
			
			if(request.getRequestURI().indexOf("emgncLoginForm.do") > -1 ||  request.getRequestURI().indexOf("prdprcRegistForm.do") > -1
					|| request.getRequestURI().indexOf("prdprcRegist.json") > -1 || request.getRequestURI().indexOf("download.do") > -1 
					|| request.getRequestURI().indexOf("downloadZip.do") > -1 || request.getRequestURI().indexOf("cldrCreatHtml.json") > -1
					|| request.getRequestURI().indexOf("loginPage.do") > -1 || request.getRequestURI().indexOf("login.do") > -1 || request.getRequestURI().indexOf("loginIdPw.do") > -1
					|| request.getRequestURI().indexOf("/sessionError") > -1 || request.getRequestURI().indexOf("/error") > -1
					|| request.getRequestURI().indexOf("atchFileListInqireByAtchFileGroupNo.json") > -1
					|| request.getRequestURI().indexOf("estiMngeRegstrList.json") > -1
					|| request.getRequestURI().indexOf("bidPblancList.json") > -1
					|| request.getRequestURI().indexOf("bidPblancOffList.json") > -1
					|| request.getRequestURI().indexOf("bidResultList.json") > -1
					|| request.getRequestURI().indexOf("yearOrderPlanList.json") > -1
					|| request.getRequestURI().indexOf("bereNotiMngeList.json") > -1
					|| request.getRequestURI().indexOf("mberSbscrbForm.do") > -1
					|| request.getRequestURI().indexOf("joinFormPage.do") > -1
					|| request.getRequestURI().indexOf("toDoListPopup.do") > -1
					|| request.getRequestURI().indexOf("estiDetailPopup.do") > -1
					|| request.getRequestURI().indexOf("bidPblancPopup.do") > -1
					|| request.getRequestURI().indexOf("bidResultPopup.do") > -1
					|| request.getRequestURI().indexOf("orderPlanPopup.do") > -1
					|| request.getRequestURI().indexOf("bereNotiPopup.do") > -1
					|| request.getRequestURI().indexOf("noticePopup.do") > -1
					|| request.getRequestURI().indexOf("deptGuidList.do") > -1
					|| request.getRequestURI().indexOf("faqList.do") > -1
					|| request.getRequestURI().indexOf("bidNotiList.do") > -1
					|| request.getRequestURI().indexOf("notiList.do") > -1
					|| request.getRequestURI().indexOf("usrPCSet.do") > -1
					|| request.getRequestURI().indexOf("trmList.do") > -1
					|| request.getRequestURI().indexOf("joinEnpaCheck.json") > -1
					|| request.getRequestURI().indexOf("zipList.do") > -1
					|| request.getRequestURI().indexOf("mjrHndlItemList.do") > -1
					|| request.getRequestURI().indexOf("joinRegist.do") > -1
					) {
				return true;
			}else {
				FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
				FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
				
				if(user != null) {
					return true;
				}else {
					return true;
					//LOG.debug("### 1 세션이 종료되었습니다. 로그인 화면으로 이동합니다.");
					//throw new LoginException("세션이 종료되었습니다. 로그인 화면으로 이동합니다.");
				}				
			}
		}else {
			FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
			FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
			
			LOG.debug("### FwkParameterSessionInterceptor ::: user ==> " + user);
			if(user == null) {
				if(request.getRequestURI().indexOf("emgncLoginForm.do") > -1 || request.getRequestURI().indexOf("ssoLoginForm") > -1 
						|| request.getRequestURI().indexOf("emgncEmplyrLogin.do") > -1 || request.getRequestURI().indexOf("prdprcRegistForm.do") > -1
						|| request.getRequestURI().indexOf("prdprcRegist.json") > -1 || request.getRequestURI().indexOf("download.do") > -1 
						|| request.getRequestURI().indexOf("downloadZip.do") > -1 || request.getRequestURI().indexOf("ssoEmplyrLogin") > -1) {
					return true;
				}else {
					LOG.debug("### 4 미로그인 상태입니다. 그룹웨어 로그인으로 이동합니다.");
					throw new LoginException("미로그인 상태입니다. 그룹웨어 로그인으로 이동합니다.");
				}	
			}else {
				return true;
			}
		}
	}
}
