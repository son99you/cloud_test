package com.eunwoosoft.comm.erro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.frwk.prl.request.FwkParameterMapInterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestMapping(value = "/opro/erro") 
@Controller
public class OproCommErrorController extends AbstractFwkController {
	String prefixUrl = "/opro/views/erro/";
	
	private static final Logger LOG = LoggerFactory.getLogger(FwkParameterMapInterceptor.class);

	/**
	 * <pre>
	 * 1. 개요 : 에러처리
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : error
	 * @date : 2019. 03. 11.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 11.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 *    요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명
	 * @throws Exception 
	 */
	@RequestMapping(value = "/error")
	public String error(final HttpServletRequest request, HttpServletResponse response) throws Exception {				
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 세션 에러처리
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : ssoError
	 * @date : 2019. 07. 11.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 07. 11.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 *    요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명
	 * @throws Exception 
	 */
	@RequestMapping(value = "/sessionError")
	public String ssoError(final HttpServletRequest request, HttpServletResponse response) throws Exception {				
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 승인대기 처리
	 * </pre>
	 * @Method Name : ssoError
	 * @date : 2020. 03. 17.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2020. 03. 17.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 *    요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명
	 * @throws Exception 
	 */
	@RequestMapping(value = "/vendStcdError")
	public String vendStcdError(final HttpServletRequest request, HttpServletResponse response) throws Exception {				
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
}
