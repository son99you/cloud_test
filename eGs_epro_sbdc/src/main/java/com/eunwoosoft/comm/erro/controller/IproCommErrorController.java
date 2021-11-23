package com.eunwoosoft.comm.erro.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eunwoosoft.frwk.fol.exception.FwkSysException;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.frwk.prl.request.FwkParameterMapInterceptor;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestMapping(value = "/erro") 
@Controller
public class IproCommErrorController extends AbstractFwkController {
	String prefixUrl = "/ipro/views/erro/";
	
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
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 미로그인 에러처리
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : ssoError
	 * @date : 2019. 06. 11.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 06. 11.		은우소프트 맹경열			최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 *    요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명
	 * @throws Exception 
	 */
	@RequestMapping(value = "/ssoError")
	public String ssoError(final HttpServletRequest request, HttpServletResponse response) throws Exception {				
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	@SuppressWarnings("PMD.AvoidReassigningParameters")
	@SuppressFBWarnings("SPRING_CSRF_UNRESTRICTED_REQUEST_MAPPING")  //에러페이지 설정으로 post,get 모두 사용
	@RequestMapping(value="/error{error_code}",method = {RequestMethod.GET,RequestMethod.POST})
    public String errorDefault(HttpServletRequest request, @PathVariable String error_code, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
        String msg = (String) parameterMap.get("javax.servlet.error.message"); 
         
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("STATUS_CODE", parameterMap.get("javax.servlet.error.status_code"));
        map.put("REQUEST_URI", parameterMap.get("javax.servlet.error.request_uri"));
        map.put("EXCEPTION_TYPE", parameterMap.get("javax.servlet.error.exception_type"));
        map.put("EXCEPTION", parameterMap.get("javax.servlet.error.exception"));
        map.put("SERVLET_NAME", parameterMap.get("javax.servlet.error.servlet_name"));
        
        if(error_code.equals("")){
        	error_code = "999";
        }
        
        try {
            int status_code = Integer.parseInt(error_code);
            switch (status_code) {
	           case 400: msg = "잘못된 요청입니다."; break;
	           case 403: msg = "접근이 금지되었습니다."; break;
	           case 404: msg = "페이지를 찾을 수 없습니다."; break;
	           case 405: msg = "요청된 메소드가 허용되지 않습니다."; break;
	           case 500: msg = "서버에 오류가 발생하였습니다."; break;
	           case 503: msg = "서비스를 사용할 수 없습니다."; break;
	           default: msg = "알 수 없는 오류가 발생하였습니다."; break;
            }
        } catch(FwkSysException e) {
            msg = "기타 오류가 발생하였습니다.";
        } finally {
            map.put("MESSAGE", msg);
        }
         
        //logging
/*        if(map.isEmpty() == false ) {
            Iterator<Entry<String,Object>> iterator = map.entrySet().iterator();
            Entry<String,Object> entry = null;
            while(iterator.hasNext()) {
                entry = iterator.next();
                System.err.println("key : "+entry.getKey()+", value : "+entry.getValue());
            }
        }*/
         
/*        FwkTransferObject trans = new FwkTransferObject();
        trans.put("error", map);
        
        model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);*/
		
        return parameterMap.getViewName(prefixUrl);
    }
}
