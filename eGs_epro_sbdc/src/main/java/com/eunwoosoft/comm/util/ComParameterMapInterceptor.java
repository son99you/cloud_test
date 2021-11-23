package com.eunwoosoft.comm.util;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eunwoosoft.comm.pki.ewLicensePki;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.exception.FwkSysException;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.google.gson.Gson;

/**
 * FwkParameterMapInterceptor
 * 
 * @author : 
 * @date : 2014. 11. 11.
 * @version : 1.0
 */
public class ComParameterMapInterceptor extends HandlerInterceptorAdapter {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(ComParameterMapInterceptor.class);

	private String parameterName = "parameterMap";	//NOPMD	get, set 처리중
	
	public void setParameterName(final String parameterName) {
		this.parameterName = FwkStringUtil.nvl(parameterName,
				this.parameterName);
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


		HttpSession session = request.getSession();
		
		System.err.println("session :::"  + session.toString());
		
//		ewLicensePki serverRSA	= new ewLicensePki();

//		serverRSA.generatorKey();
//		String publicKeySpec	= serverRSA.getPublicKeySpecStr();
//		String privateKeySpec	= serverRSA.getPrivatekeySpecStr();

		String publicKeySpec =  "129324361548248784621391060780425460677399562730543810730684660881627562093434172560756478382369512864042648326490104582772378490412787229281925620032032403146098808369792598849278455942882517311954086825292312781695613148652722934216891395273269681441241182989751477860088688083764074863895802068188409970733/65537";
		String privateKeySpec = "129324361548248784621391060780425460677399562730543810730684660881627562093434172560756478382369512864042648326490104582772378490412787229281925620032032403146098808369792598849278455942882517311954086825292312781695613148652722934216891395273269681441241182989751477860088688083764074863895802068188409970733/47004078491223065591673940946179020604172567927148840679385822088291629599548377106019795154920759211155467646321837910823474611923533146184528865666158220508976070870860023665918744503905504555565489175017968453842126325731679950731303136189659001634410410883459208102675762321410987922158381252100921931473";
		
		
		// 클라이언트단 공통키로 암호화
		ewLicensePki clientRSA	= new ewLicensePki();
		String originalTxt	= "이 문자열을 보냅니다.";
		clientRSA.setPublicKeySpecStr(publicKeySpec);
		String encodeTxt	= clientRSA.encode(originalTxt);

		// 이미 생성된 서버 자원 사용
//		String decodeTxt	= serverRSA.decode(encodeTxt);
		
//		String privateKeySpec = "129324361548248784621391060780425460677399562730543810730684660881627562093434172560756478382369512864042648326490104582772378490412787229281925620032032403146098808369792598849278455942882517311954086825292312781695613148652722934216891395273269681441241182989751477860088688083764074863895802068188409970733/47004078491223065591673940946179020604172567927148840679385822088291629599548377106019795154920759211155467646321837910823474611923533146184528865666158220508976070870860023665918744503905504555565489175017968453842126325731679950731303136189659001634410410883459208102675762321410987922158381252100921931473";
//		String encodeTxt = "eCTlK32QKp24+jm5/f4f4pV+d3B9ej7h2nTRVzr2ZWvLwpa+/7h7stMAybjf3cuBoeV5AaoCW28Ggms7WBTPThJhFfyscKr9+6VaqjdINwUzf2wkx5aqVT0tGaVKtXQSba2+ygm5aBznrozUy0oqVOGBmZxU20CtN0MOMQyRxQ4=";
		
		// 서버에서 새 자원 생성해서 개인키로 복호화
		ewLicensePki serverRSARe	= new ewLicensePki();
		serverRSARe.setPrivateKeySpecStr(privateKeySpec);
		String decodeTxtRe	= serverRSARe.decode(encodeTxt);

		System.out.println("publicKeySpec : " + publicKeySpec);
		System.out.println("privateKeySpec : " + privateKeySpec);
//		System.out.println("originalTxt : " + originalTxt);
		System.out.println("encodeTxt : " + encodeTxt);
//		System.out.println("decodeTxt : " + decodeTxt);
		System.out.println("decodeTxtRe : " + decodeTxtRe);
		
		
		String url = request.getRequestURI();
		int pos = url.lastIndexOf('.');
		String ext = url.substring(pos + 1);

		if (ext.equalsIgnoreCase("do")) {	
			createParameterMap(request, response);
		} else if (ext.equalsIgnoreCase("dx")) {	
				createParameterMap(request, response);
		} else if (ext.equalsIgnoreCase("json")) {
			createParameterMapByJson(request);
		} else if (ext.equalsIgnoreCase("ws")) {
			createParameterMapByJson(request);
		} else if(ext.equalsIgnoreCase("iframe")) {	
			createParameterMap(request, response);
		}	

		return true;
	}
	
	private void createParameterMap(final HttpServletRequest request, final HttpServletResponse response) {

		FwkParameterMap parameterMap = new FwkParameterMap();

		Enumeration<?> e1 = request.getParameterNames();

		String key;

		while (e1.hasMoreElements()) {
			key = (String) e1.nextElement();
			if ((request.getParameterValues(key)).length > 1) {
				String[] values = request.getParameterValues(key);				
				for(int i=0; i<values.length;i++){
					values[i] = (String) FwkStringUtil.castEuc2Iso(values[i]);					
				}
				parameterMap.put(key, values);
			} else {
				parameterMap.put(key, FwkStringUtil.castEuc2Iso(request.getParameter(key)));
			}
		}
		
		if("/fbm".equals(request.getContextPath())){
			parameterMap.put("P_PAGE_SIZE", 5);
		}
		
		if(request.getSession().getAttribute("loginResult")  != null && !request.getSession().getAttribute("loginResult").equals("")) {
			FwkDataEntity dataEntity = (FwkDataEntity) request.getSession().getAttribute("loginResult");
			parameterMap.put("USR_ID", dataEntity.get("USR_ID"));
			parameterMap.put("USR_NM", dataEntity.get("USR_NM"));
			parameterMap.put("CONN_IP", dataEntity.get("CONN_IP"));
		}
		

		parameterMap.put("requestId", CmmnUtil.getRemoteAddr(request));
		parameterMap.put("requestUrl", request.getRequestURI());
		parameterMap.put("queryString", request.getQueryString());
		parameterMap.put("contextName", FwkStringUtil.replace(request.getContextPath(), "/", ""));
		//페이지당 로우
		if("/fbm".equals(request.getContextPath())){
			parameterMap.put("P_PAGE_SIZE", 5);
		}

		createSessionMap(request, parameterMap);

		request.setAttribute(parameterName, parameterMap);

//		LOG.info(parameterMap.toString());
		
		String rqstUrl = (String)parameterMap.get("requestUrl");
		
		if(rqstUrl.indexOf(".dx") >= 0 ){
			return;
		}
		
		String firstStr = "";
		String lastStr = "";
		String[] rqstUrlArr = rqstUrl.split("/");
		
		if(rqstUrlArr.length < 1){
			return;
		}
		
		if(rqstUrlArr[0].equals("")){
			firstStr = rqstUrlArr[1];
		}else{
			firstStr = rqstUrlArr[0];
		}
		
		lastStr = rqstUrlArr[rqstUrlArr.length-1];
		
		lastStr = lastStr.replace(".dx", "");
		lastStr = lastStr.replace(".do", "");
		lastStr = lastStr.replace(".json", "");
		
		FwkParameterMap parameterMapLog = new FwkParameterMap();
		
		parameterMapLog.put("P_SYS_CONN_SECD", "EVNT");
		parameterMapLog.put("P_CONN_CNTN", FwkStringUtil.castEuc2Iso("이벤트 발생"));
		parameterMapLog.put("P_CONN_URL", parameterMap.get("requestUrl"));
		parameterMapLog.put("P_MENU_ID", lastStr);
		parameterMapLog.put("P_USR_ID", parameterMap.get("USR_ID"));
		parameterMapLog.put("P_USR_NM", parameterMap.get("USR_NM"));
		parameterMapLog.put("P_CONN_IP", parameterMap.get("CONN_IP"));
		
	}

	private void createParameterMapByJson(final HttpServletRequest request)
			throws Exception {		
		
		FwkParameterMap parameterMap = new FwkParameterMap();
		BufferedReader reader = request.getReader();

		StringBuffer json = new StringBuffer();
		String line = null;

		while ((line = reader.readLine()) != null) {	//NOPMD 계층구조표현시 파라미터에 값이 매핑 안됨
			json.append(line);
		}

		Gson gson = new Gson();

		if (StringUtils.hasText(json)) {

			parameterMap.putAll(gson.fromJson(json.toString(), Map.class));
		}
		
		Object obj = null;
		Object objLstSrting = null;
		String keyName = null;
		List<String> lst = parameterMap.getKeyNames();
		List<String> objLst = null;

		for(int i=0; i< lst.size() ;i++ ){

			keyName = lst.get(i);
						
			try{
				obj = FwkStringUtil.castEuc2Iso(parameterMap.get(keyName));
			}catch(FwkSysException e){
				
				objLst = (List)parameterMap.get(keyName);
				
				List<String> objLstNew = new ArrayList<String>(objLst.size()); 	//NOPMD	해시리스트 생성위해 필요
				objLstNew.clear();
				
				for(int k=0;k<objLst.size();k++){
					objLstSrting = FwkStringUtil.castEuc2Iso(objLst.get(k));
				
					objLstNew.add(k,(String)objLstSrting);
				}
						
				obj = objLstNew;
			}
			parameterMap.put(keyName, obj);			
		}	
		
		//System.out.println("parameterMap.toString() : " + parameterMap.toString());
		
		if(request.getSession().getAttribute("loginResult")  != null && !request.getSession().getAttribute("loginResult").equals("")) {
			FwkDataEntity dataEntity = (FwkDataEntity) request.getSession().getAttribute("loginResult");
			parameterMap.put("USR_ID", dataEntity.get("USR_ID"));
			parameterMap.put("USR_NM", dataEntity.get("USR_NM"));
			parameterMap.put("CONN_IP", dataEntity.get("CONN_IP"));
		}

		parameterMap.put("requestId", CmmnUtil.getRemoteAddr(request));
		parameterMap.put("requestUrl", request.getRequestURI());
		parameterMap.put("queryString", request.getQueryString());
		parameterMap.put("contextName", FwkStringUtil.replace(request.getContextPath(), "/", ""));

		createSessionMap(request, parameterMap);

		request.setAttribute(parameterName, parameterMap);
		
		//LOG.info(parameterMap.toString());
		String rqstUrl = (String)parameterMap.get("requestUrl");
		
		if(rqstUrl.indexOf(".dx") >= 0 ){
			return;
		}
		
		String firstStr = "";
		String lastStr = "";
		String[] rqstUrlArr = rqstUrl.split("/");
		
		if(rqstUrlArr.length < 1){
			return;
		}
		
		if(rqstUrlArr[0].equals("")){
			firstStr = rqstUrlArr[1];
		}else{
			firstStr = rqstUrlArr[0];
		}
		
		lastStr = rqstUrlArr[rqstUrlArr.length-1];
		
		lastStr = lastStr.replace(".dx", "");
		lastStr = lastStr.replace(".do", "");
		lastStr = lastStr.replace(".json", "");
		
		if(!lastStr.equalsIgnoreCase("contpscdchk") && !lastStr.equalsIgnoreCase("bidpscdselectchk")){
			FwkParameterMap parameterMapLog = new FwkParameterMap();
			
			parameterMapLog.put("P_SYS_CONN_SECD", "EVNT");
			parameterMapLog.put("P_CONN_CNTN", FwkStringUtil.castEuc2Iso("이벤트 발생"));
			parameterMapLog.put("P_CONN_URL", parameterMap.get("requestUrl"));
			parameterMapLog.put("P_MENU_ID", lastStr);
			parameterMapLog.put("P_USR_ID", parameterMap.get("USR_ID"));
			parameterMapLog.put("P_USR_NM", parameterMap.get("USR_NM"));
			parameterMapLog.put("P_CONN_IP", parameterMap.get("CONN_IP"));
		}
	}

//	@SuppressWarnings("unchecked")
//	@SuppressFBWarnings("SERVLET_PARAMETER")	//파라미터 생성위한 변수처리
//	private void createParameterMapByWs(final HttpServletRequest request) throws Exception {
//
//		FwkParameterMap parameterMap = new FwkParameterMap();
//
//		Enumeration<?> e1 = request.getParameterNames();
//		String key;
//
//		String value = "";
//
//		while (e1.hasMoreElements()) {
//			key = (String) e1.nextElement();
//
//			if ("callback".equals(key) || "_".equals(key)) {
//				continue;
//			}
//			value = key;
//		}
//
//		Gson gson = new Gson();
//		parameterMap.putAll(gson.fromJson(value, Map.class));
//		createSessionMap(request, parameterMap);
//		request.setAttribute(parameterName, parameterMap);
//
//	}

	private void createSessionMap(final HttpServletRequest request,
			final FwkParameterMap parameterMap) {
		FwkDataEntity dataEntity = (FwkDataEntity) request.getSession()
				.getAttribute("loginResult");

		if (dataEntity != null) {
			parameterMap.put("loginResult", dataEntity);
		} 
	}

//	@Override
//	public void postHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//
//		if (modelAndView != null) {
//			modelAndView.addObject("requestUrl", request.getRequestURI());
//		}
//	}

	

}
