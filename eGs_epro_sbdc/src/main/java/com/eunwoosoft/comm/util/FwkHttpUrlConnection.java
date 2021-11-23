package com.eunwoosoft.comm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.*;
import org.json.simple.JSONObject;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

import net.sf.json.JSONException;


public class FwkHttpUrlConnection {
 
  public static FwkParameterMap responseGet(FwkParameterMap param) {     
	  
	  FwkParameterMap getParam = new FwkParameterMap();;  
	  
	  JSONObject responseJson = null; 
	  
	  try{
		  	
		  	String strUrl = param.getString("urlParam");
		  	URL url = new URL("https://mate2-dev.meetmate.co.kr"+strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
			conn.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
			conn.setRequestMethod("GET");
			
//			JSONObject commands = new JSONObject();
			
			int responseCode = conn.getResponseCode();
			if(responseCode == 400 || responseCode == 401 || responseCode == 500) {
				System.out.println("responseCode ==> " + responseCode + " Error!!");
			}else {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                //responseJson = new JSONObject();
                //responseJson = sb.toString();
                System.out.println("response Data :::  " + sb.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("not JSON Format response");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	  
	  return getParam;
	  
 }
}