package com.eunwoosoft.frwk.prl.request;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.google.gson.Gson;

/**
 * FwkParameterMapInterceptor
 * 
 * @author : 
 * @date : 2014. 11. 11.
 * @version : 1.0
 */
public class FwkParameterMapInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOG = LoggerFactory
			.getLogger(FwkParameterMapInterceptor.class);

	private String parameterName = "parameterMap";
	
	public void setParameterName(final String parameterName) {
		this.parameterName = FwkStringUtil.nvl(parameterName,
				this.parameterName);
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String url = request.getRequestURI();
		int pos = url.lastIndexOf(".");
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

	private void createParameterMap(final HttpServletRequest request,
			final HttpServletResponse response) {

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
		
		//if(request.getSession().getAttribute("loginResult")  != null && request.getSession().getAttribute("loginResult") != "") {
		if(!"".equals(request.getSession().getAttribute("loginResult"))) {
			FwkDataEntity dataEntity = (FwkDataEntity) request.getSession().getAttribute("loginResult");
			parameterMap.put("USR_ID", dataEntity.get("USR_ID"));
			parameterMap.put("USR_NM", dataEntity.get("USR_NM"));
			parameterMap.put("CONN_IP", dataEntity.get("CONN_IP"));
		}
		

		parameterMap.put("requestId", request.getRemoteAddr());
		parameterMap.put("requestUrl", request.getRequestURI());
		parameterMap.put("queryString", request.getQueryString());
		parameterMap.put("contextName",
				FwkStringUtil.replace(request.getContextPath(), "/", ""));
		//페이지당 로우
		if("/fbm".equals(request.getContextPath())){
			parameterMap.put("P_PAGE_SIZE", 5);
		}

		createSessionMap(request, parameterMap);

		request.setAttribute(parameterName, parameterMap);

//		LOG.info(parameterMap.toString());
		
	}

	@SuppressWarnings("unchecked")
	private void createParameterMapByJson(final HttpServletRequest request)
			throws Exception {		
		
		FwkParameterMap parameterMap = new FwkParameterMap();
		BufferedReader reader = request.getReader();

		StringBuffer json = new StringBuffer();
		String line = null;

		while ((line = reader.readLine()) != null) {
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
			}catch(Exception e){
				
				objLst = (List)parameterMap.get(keyName);
				
				List<String> objLstNew = new ArrayList<String>(objLst.size()); 
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
		
		//if(request.getSession().getAttribute("loginResult")  != null && request.getSession().getAttribute("loginResult") != "") {
		if(!"".equals(request.getSession().getAttribute("loginResult"))) {
			FwkDataEntity dataEntity = (FwkDataEntity) request.getSession().getAttribute("loginResult");
			parameterMap.put("USR_ID", dataEntity.get("USR_ID"));
			parameterMap.put("USR_NM", dataEntity.get("USR_NM"));
			parameterMap.put("CONN_IP", dataEntity.get("CONN_IP"));
		}

		parameterMap.put("requestId", request.getRemoteAddr());
		parameterMap.put("requestUrl", request.getRequestURI());
		parameterMap.put("queryString", request.getQueryString());
		parameterMap.put("contextName",
				FwkStringUtil.replace(request.getContextPath(), "/", ""));

		createSessionMap(request, parameterMap);

		request.setAttribute(parameterName, parameterMap);
		LOG.info(parameterMap.toString());

	}

	@SuppressWarnings("unchecked")
	private void createParameterMapByWs(final HttpServletRequest request)
			throws Exception {

		FwkParameterMap parameterMap = new FwkParameterMap();

		Enumeration<?> e1 = request.getParameterNames();
		String key;

		String value = "";

		while (e1.hasMoreElements()) {
			key = (String) e1.nextElement();

			if ("callback".equals(key) || "_".equals(key)) {
				continue;
			}
			value = key;
		}

		Gson gson = new Gson();
		parameterMap.putAll(gson.fromJson(value, Map.class));
		createSessionMap(request, parameterMap);
		request.setAttribute(parameterName, parameterMap);

	}

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
