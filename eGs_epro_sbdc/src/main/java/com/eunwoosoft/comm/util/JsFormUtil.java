package com.eunwoosoft.comm.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eunwoosoft.comm.atfi.controller.ComAtfiAtchFileController;

public class JsFormUtil {
	private static final Logger LOG = LoggerFactory.getLogger(ComAtfiAtchFileController.class);
	
    private String LINE_FEED = "\r\n";
	private String boundary;
	private String charset;
	private HttpURLConnection hConnection;
	//private OutputStream outputStream;
	private BufferedOutputStream outputStream;
	private PrintWriter writer;
	
	public JsFormUtil(String reqUrl, String charset, String method) throws Exception {
		
		this.charset = charset;
		boundary = "----WebKitFormBoundary";
		
		URL url = new URL(reqUrl);
		hConnection = (HttpURLConnection) url.openConnection();
		if(method.equals("PUT")) {
			hConnection.setRequestMethod("PUT");
			hConnection.setUseCaches(false);
			hConnection.setDoOutput(true);
			hConnection.setDoInput(true);
			hConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary="+boundary);
			hConnection.setRequestProperty("User-Agent", "TEST2");
			//outputStream = hConnection.getOutputStream();
			outputStream = new BufferedOutputStream(hConnection.getOutputStream());
			writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);
		}else if(method.equals("GET")) {
			hConnection.setRequestMethod("GET");
			hConnection.setRequestProperty("User-Agent", "TEST");
		}
		LOG.debug("JsFormUtil HttpURLConnection >>>>>>>>>>>>>>>");
		LOG.debug("JsFormUtil writer >>>>>>>>>>>>>>>>>");
	}
	
	public void addFormField(String name, String val) {
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"" + name + "\"").append(LINE_FEED);
        writer.append("Content-Type: text/plain; charset=" + charset).append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.append(val).append(LINE_FEED);
        writer.flush();
	}
	
    public void addFilePart(String fieldName, File uploadFile, String fileName) throws IOException {
    	//String fileName = uploadFile.getName();
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
        //writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
        writer.append("Content-Type: application/octet-stream;").append(LINE_FEED);
        writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
        
        writer.append(LINE_FEED);
        writer.flush();
        
        //FileInputStream inputStream = new FileInputStream(uploadFile);
        //DataInputStream dis = new DataInputStream(new BufferedInputStream(inputStream));
        InputStream inputStream = new BufferedInputStream(new FileInputStream(uploadFile));
        
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        StringBuilder sb = new StringBuilder();
        while ((bytesRead = inputStream.read(buffer)) != -1) {
        	sb.append((char)inputStream.read(buffer));
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        inputStream.close();
         
        writer.append(LINE_FEED);
        writer.flush();    
    }
    
    public void addHeaderField(String name, String value) {
        writer.append(name + ": " + value).append(LINE_FEED);
        writer.flush();
    }
	
    public Map<String, Object> finish() throws Exception {
    	LOG.debug("JsFormUtil finish >>>>>>>>>>>>>>>>>>>>>");
    	Map<String, Object> rtnMap = new HashMap<String, Object>();
 
        writer.append(LINE_FEED).flush();
        writer.append("--" + boundary + "--").append(LINE_FEED);
        writer.close();
        outputStream.close();
 
        LOG.debug("----------------------------------------------------------------");
        LOG.debug(writer.toString() );
        LOG.debug("----------------------------------------------------------------");
        
        // checks server's status code first
        int status = hConnection.getResponseCode();
        LOG.debug("status :: "+status);
        if (status == HttpURLConnection.HTTP_OK) {
        	for (Map.Entry<String, List<String>> header : hConnection.getHeaderFields().entrySet()) {
				for (String val : header.getValue()) {
					System.out.println(header.getKey() + " : " +  val);
				}
			}
        	
        	//응답내용 BODY
            BufferedReader reader = new BufferedReader(new InputStreamReader(
            		hConnection.getInputStream(), "UTF-8"));
            
            String line = null;
            String trdplcCd = "";
            String busirNo = "";
            while ((line = reader.readLine()) != null) {
            	if(line.indexOf("trdplcCd") > -1) {
            		trdplcCd = line;
            	}else if(line.indexOf("busirNo") > -1) {
            		busirNo = line;
            	}
            }            
                        
            reader.close();
            hConnection.disconnect();
            rtnMap.put("rtnCode", status);
            rtnMap.put("rtnMessageCode", "200");
            rtnMap.put("rtnMessageMsg", "OK");            
            rtnMap.put("trdplcCd", trdplcCd);            
            rtnMap.put("busirNo", busirNo);            
            
        } else {
        	for (Map.Entry<String, List<String>> header : hConnection.getHeaderFields().entrySet()) {
				for (String val : header.getValue()) {
					System.out.println(header.getKey() + " : " +  val);
				}
			}        	
        	
            BufferedReader reader = new BufferedReader(new InputStreamReader(
            		hConnection.getErrorStream(), "UTF-8"));
            
            String line = null;
            String errorCode = "";
            String errorMsg = "";
            while ((line = reader.readLine()) != null) {
            	System.out.println(line);
            	if(line.indexOf("errorCode") > -1) {
            		errorCode = line;
            	}else if(line.indexOf("errorMsg") > -1) {
            		errorMsg = line;
            	}
            }

            reader.close();
            hConnection.disconnect();        	

            rtnMap.put("rtnCode", status);
            rtnMap.put("rtnMessageCode", errorCode);
            rtnMap.put("rtnMessageMsg", errorMsg);
            //throw new IOException("Server returned non-OK status: " + status + " : " + response);
        }
 
        return rtnMap;
    }
    
    public Map<String, Object> finishGet() throws Exception {
    	LOG.debug("JsFormUtil finish >>>>>>>>>>>>>>>>>>>>>");
    	Map<String, Object> rtnMap = new HashMap<String, Object>();
    	
    	// checks server's status code first
    	int status = hConnection.getResponseCode();
    	LOG.debug("status :: "+status);
    	if (status == HttpURLConnection.HTTP_OK) {
    		for (Map.Entry<String, List<String>> header : hConnection.getHeaderFields().entrySet()) {
    			for (String val : header.getValue()) {
    				System.out.println(header.getKey() + " : " +  val);
    			}
    		}
    		
    		//응답내용 BODY
    		BufferedReader reader = new BufferedReader(new InputStreamReader(
    				hConnection.getInputStream(), "UTF-8"));
    		
    		String line = null;
    		String trdplcCd = "";
    		String busirNo = "";
    		while ((line = reader.readLine()) != null) {
    			if(line.indexOf("trdplcCd") > -1) {
    				//          "trdplcCd": "41497",
    				trdplcCd = line.substring(line.indexOf("\"trdplcCd\": \"")+13, line.indexOf("\","));
    			}else if(line.indexOf("busirNo") > -1) {
    				busirNo = line;
    			}
    		}            
    		
    		reader.close();
    		hConnection.disconnect();
    		rtnMap.put("rtnCode", status);
    		rtnMap.put("rtnMessageCode", "200");
    		rtnMap.put("rtnMessageMsg", "OK");            
    		rtnMap.put("trdplcCd", trdplcCd);            
    		rtnMap.put("busirNo", busirNo);            
    		
    	} else {
    		for (Map.Entry<String, List<String>> header : hConnection.getHeaderFields().entrySet()) {
    			for (String val : header.getValue()) {
    				System.out.println(header.getKey() + " : " +  val);
    			}
    		}        	
    		
    		BufferedReader reader = new BufferedReader(new InputStreamReader(
    				hConnection.getErrorStream(), "UTF-8"));
    		
    		String line = null;
    		String errorCode = "";
    		String errorMsg = "";
    		while ((line = reader.readLine()) != null) {
    			System.out.println(line);
    			if(line.indexOf("errorCode") > -1) {
    				errorCode = line;
    			}else if(line.indexOf("errorMsg") > -1) {
    				errorMsg = line;
    			}
    		}
    		
    		reader.close();
    		hConnection.disconnect();        	
    		
    		rtnMap.put("rtnCode", status);
    		rtnMap.put("rtnMessageCode", errorCode);
    		rtnMap.put("rtnMessageMsg", errorMsg);
    		//throw new IOException("Server returned non-OK status: " + status + " : " + response);
    	}
    	
    	return rtnMap;
    }    
}