package com.eunwoosoft.comm.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.main.service.impl.IproMainLoginFormServiceImpl;
import com.yettiesoft.common.json.simple.JSONObject;
import com.yettiesoft.common.json.simple.parser.JSONParser;

public class FwkSSOUtil {
	private static final Logger LOG = LoggerFactory.getLogger(IproMainLoginFormServiceImpl.class);
	
	public HashMap<String, String> getUserInfo(FwkParameterMap parameterMap) {
		String userName = "";
		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		if(parameterMap.get("P_ACCESS_TOKEN") == null 
				|| "".equals(parameterMap.get("P_ACCESS_TOKEN"))
				|| parameterMap.get("P_CLIENT_IP") == null 
				|| "".equals(parameterMap.get("P_CLIENT_IP"))) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("grant_type=").append("access_token_identify").append("&");
		sb.append("client_id=").append(parameterMap.getString("P_CLIENT_ID")).append("&");
		sb.append("client_secret=").append(parameterMap.getString("P_CLIENT_SECRET")).append("&");
		sb.append("access_token=").append(parameterMap.getString("P_ACCESS_TOKEN")).append("&");
		sb.append("scope=").append(parameterMap.getString("P_SCOPE")).append("&");
		sb.append("sys_url=").append(parameterMap.getString("P_SYS_URL")).append("&");
		sb.append("client_ip=").append(parameterMap.getString("P_CLIENT_IP"));
		
		LOG.debug("send : " + sb.toString());
		
		try {
			System.out.println(sb.toString());
			LOG.debug("responseStr >>>>>>>>>>>");
			String responseStr = send(sb.toString());	
			LOG.debug("<<<<<<<<<<<responseStr :: " + responseStr);
			resultMap = parsingData(responseStr);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

	public String checkCookie(String name, HttpServletRequest request) {
		String result = "";
		
		if(name == null || name == "" || request == null) {
			return result;
		}
		
		try {
			Cookie[] cookieArr = request.getCookies();
			
			if(cookieArr != null) {
				for(int i=0;i<cookieArr.length;i++) {
					if(name.equals(cookieArr[i].getName())) {
						result = cookieArr[i].getValue();
					}
				}				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public HashMap<String, String> parsingData(String jsonStr) {
		HashMap<String, String> result = new HashMap<String, String>();

		try {
			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject)parser.parse(jsonStr);
					
			for(Object key : object.keySet()) {
				result.put(key.toString(), object.get(key).toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
			result = null;
		}
		
		return result;
	}	
	
	public static String send(String jsonData) throws Exception {
		String result = null;
		
		try{
	        URL url = new URL(FwkMessageUtil.getMessage("IPRO.SSO.SSO_URL"));
	        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	        connection.setRequestMethod("POST");
	        connection.setDoOutput(true); 
	        connection.setDoInput(true);
	        connection.setUseCaches(false); 
	        
	        PrintWriter  out = new PrintWriter(connection.getOutputStream()); 
	        
	        out.print(jsonData);
	        out.flush();
	        out.close();
	        
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
	        String inputLine = "";
	
	        while( (inputLine = in.readLine()) != null ){
	        	result = inputLine;
	        }
	        in.close();

		}catch( Exception e ){
			e.printStackTrace();
			result = null;
		}
		
        return result;
	}	
	
}
