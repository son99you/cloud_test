package com.eunwoosoft.comm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.HtmlUtils;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.exception.CustomException;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.ubireport.viewer.report.preview.UbiViewer;
import com.yettiesoft.vestsign.external.Enveloper;

/**
 * <pre>
 * com.eunwoosoft.comm.util 
 *    |_ ComnUtil.java
 * 
 * </pre>
 * @date : 2015. 5. 11. 오후 4:37:53
 * @version : 1.0
 * @author : 은우소프트 임동일
 */
public class CmmnUtil {
	private static final Logger LOG = LoggerFactory.getLogger(CmmnUtil.class);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  제외문자 replace
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : exceptCharReplace
	 * @date : 2015. 10. 12.
	 * @author : 은우소프트 김봉수
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 10. 12.		은우소프트 김봉수				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param String
	 * @return String
	 */
	public static String exceptCharReplace(String strName){
			String[] invalidName = {"\\\\","/",":","[*]","[?]","\"","<",">","[|]","&","^",";"}; 
			 
			for(int i=0; i < invalidName.length; i++){
				strName = strName.replaceAll(invalidName[i], ""); // 언더바로 치환 
			}
		
		return strName;
	}

	  /**
     * IP정보 획득 L4혹은 Proxy의 헤더에 담겨진 클라이언트 IP정보 취득 추가
     * <pre>
     * 1.개요 : 
     * 2.처리내용 : 
     * </pre>
     * @Method : getRemoteAddr
     * @author : FIC04237
     * @date : 2019. 1. 15. 
     * @history
     * =============================================
     *  date           name           description
     * ---------------------------------------------
     * 2019. 1. 15.        FIC04237          최초 생성
     * =============================================
     */
    public static String getRemoteAddr(HttpServletRequest request){
    	
        String userIp = null;
        String serverIp = null;
        String clientIp = null;
    	
    	try{
//    		userIp = request.getHeader("X-Forwarded-For");
//	    	
//	    	if(userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)){
//	    		userIp = request.getHeader("Proxy-Client-IP");
//	    	}
//	    	if(userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)){
//	    		userIp = request.getHeader("WL-Proxy-Client-IP");
//	    	}
//	    	if(userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)){
//	    		userIp = request.getHeader("HTTP-CLIENT-IP");
//	    	}
//	    	if(userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)){
//	    		userIp = request.getHeader("HTTP_X_FORWARDED_FOR");
//	    	}
//	    	if(userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)){
//	    		userIp = request.getHeader("X-Real-IP");
//	    	}
//	    	if(userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)){
//	    		userIp = request.getHeader("X_RealIP");
//	    	}
//	    	if(userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)){
//	    		userIp = request.getHeader("REMOTE_ADDR");
//	    	}
//	    	if(userIp == null || userIp.length() == 0 || "unknown".equalsIgnoreCase(userIp)){
//	    		userIp = request.getRemoteAddr();
//	    	}
	    	
	    	
	    	InetAddress iadr = InetAddress.getLocalHost();
	    	serverIp = iadr.getHostAddress();
	        serverIp = serverIp.substring( serverIp.lastIndexOf(".")+1);
    		clientIp = request.getRemoteAddr();
    		
    		
	    	String xForwardedFor   = request.getHeader( "X-Forwarded-For" );
	    	String proxyClientIp   = request.getHeader( "Proxy-Client-IP" );
	        String wlProxyClientIp = request.getHeader( "WL-Proxy-Client-IP" );
			String httpClientIp   = request.getHeader( "HTTP-CLIENT-IP" );			
			String httpXForwardFor   = request.getHeader( "HTTP_X_FORWARDED_FOR" );			
			String xRealIp   = request.getHeader( "X-Real-IP" );
			String remoteAddr   = request.getHeader( "REMOTE_ADDR" );
			
			if( wlProxyClientIp != null && !wlProxyClientIp.equals( "" ) ) {
				userIp = wlProxyClientIp;
			}
			else if( proxyClientIp != null && !proxyClientIp.equals( "" ) ) {
				userIp = proxyClientIp;
			}
			else if( xForwardedFor != null && !xForwardedFor.equals( "" ) ) {
				userIp = xForwardedFor;
			}
			else if( httpClientIp != null && !httpClientIp.equals( "" ) ) {
				userIp = httpClientIp;
			}
			else if( httpXForwardFor != null && !httpXForwardFor.equals( "" ) ) {
				userIp = httpXForwardFor;
			}
			else if( xRealIp != null && !xRealIp.equals( "" ) ) {
				userIp = xRealIp;
			}
			else if( remoteAddr != null && !remoteAddr.equals( "" ) ) {
				userIp = remoteAddr;
			} 
			else{
				userIp = request.getRemoteAddr();
			}
 			
    	}catch (Exception e) {
            //Log.info(e.getMessage());
        } 	
    	    	   
    	return userIp;
    	
    }
    
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  dhtmlx 그리드 JSON 작성
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : gridJsonCreate
	 * @date : 2016. 08. 12.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 08. 12.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param List
	 * @return String
	 */
	public static String gridJsonCreate(List list){
		StringBuffer jsonText = new StringBuffer();
		jsonText.append("{\"rows\":[");
		for (int i = 0; i < list.size(); i++) {
			if(i!=0){
				jsonText.append(",");
			}
			LinkedHashMap linkedHashMap = (LinkedHashMap)list.get(i);
			Set keySet = linkedHashMap.keySet();
			int cnt = 0;
			for(Iterator iterator = keySet.iterator(); iterator.hasNext();){
				String key = (String)iterator.next();
				if(cnt == 0){
					jsonText.append("{\"id\":\""+linkedHashMap.get(key)+"\",");
					jsonText.append("\"data\":[");
					cnt++;
				}else if(cnt == 1){
					jsonText.append("\""+linkedHashMap.get(key)+"\"");
					cnt++;
				}else{
					jsonText.append(",\""+linkedHashMap.get(key)+"\"");
				}
			}
			jsonText.append("]}");
		}
		jsonText.append("]}");
		return jsonText.toString();
	}
	
	public static FwkParameterMap fileMultiUpload(MultipartHttpServletRequest multipartRequest, String fileId, String contextPath ){
		FwkParameterMap parameterMap = new FwkParameterMap();
		List<MultipartFile> mFileList = multipartRequest.getFiles(fileId);
		
		if(mFileList != null && mFileList.size() > 0){
			String root = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");
			String fileDirPath = root + FwkDateUtil.getCurrentDateAsString("yyyy") + File.separator + FwkDateUtil.getCurrentDateAsString("MM") + File.separator + FwkDateUtil.getCurrentDateAsString("dd") + File.separator + contextPath; // 파일 경로
			File fileDir = new File(fileDirPath);
			boolean mkdirSuccess = true;
			if(!fileDir.isDirectory()){
				fileDir.setExecutable(true);
				fileDir.setReadable(true);
				fileDir.setWritable(true);
				mkdirSuccess = fileDir.mkdirs();
			}
			// 파일을 저장할 폴더가 생성되었을 경우만 처리
			if(mkdirSuccess){
				
				String atchFileGroupNo =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				
				for (int i = 0; i < mFileList.size(); i++) {
					MultipartFile mFile = mFileList.get(i);
					if(!mFile.isEmpty()){
						String originalFilename = mFile.getOriginalFilename();
						String systemFileName = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
						String ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
						String atchmnflExtsnNm = "";
						
						if("jpg".equals(ext) || "jpeg".equals(ext) || "png".equals(ext) || "gif".equals(ext)){
							atchmnflExtsnNm = "image/"+ext;
						}else{
							atchmnflExtsnNm = "application/"+ext;
						}
						
						try {
							mFile.transferTo(new File(fileDirPath+File.separator+systemFileName+"."+ext));
						} catch (IllegalStateException e) {
//							e.printStackTrace();
							System.out.println(">>>>>파일 업로드 상태 에러<<<<<");
						} catch (IOException e) {
//							e.printStackTrace();
							System.out.println(">>>>>파일 업로드 에러<<<<<");
						}
						
						FwkParameterMap map = new FwkParameterMap();
						map.put("P_FILE_SZ", mFile.getSize()); 										//용량
						map.put("P_SV_FILE_NM",  systemFileName+"."+ext); 					//파일변환명
						map.put("P_SYS_FILE_NM",   mFile.getOriginalFilename()); 			//파일명
						map.put("P_FILE_LCTN",  fileDirPath );										//경로
//						map.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");	//확장자 ?
						
						list.add(map);
					}
				}
				
				if(list.size() > 0){
					parameterMap.put("atchFileGroupNo", atchFileGroupNo);
					parameterMap.put("list", list);
				}else{
					parameterMap = null;
				}
			}
		}else{
			parameterMap = null;
		}
		
		return parameterMap;
	}
	
	public static FwkParameterMap fileMultiUpload2(MultipartHttpServletRequest multipartRequest, String fileId, String contextPath, FwkParameterMap parameterMap){
//		FwkParameterMap parameterMap = new FwkParameterMap();
		List<MultipartFile> mFileList = multipartRequest.getFiles(fileId);
		String atchFileGroupNo ="";
		if(parameterMap.getString("P_FILE_GRP_NO" )!= null && !parameterMap.getString("P_FILE_GRP_NO").equals("")){
			atchFileGroupNo =  parameterMap.getString("P_FILE_GRP_NO"); 
		}else{
			atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		}
		
		if(mFileList != null && mFileList.size() > 0){
			String root = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");
			String fileDirPath = root + FwkDateUtil.getCurrentDateAsString("yyyy") + File.separator + FwkDateUtil.getCurrentDateAsString("MM") + File.separator + FwkDateUtil.getCurrentDateAsString("dd") + File.separator + contextPath; // 파일 경로
			File fileDir = new File(fileDirPath);
			boolean mkdirSuccess = true;
			if(!fileDir.isDirectory()){
				fileDir.setExecutable(true);
				fileDir.setReadable(true);
				fileDir.setWritable(true);
				mkdirSuccess = fileDir.mkdirs();
			}
			// 파일을 저장할 폴더가 생성되었을 경우만 처리
			if(mkdirSuccess){
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				Object fileDocNm = parameterMap.get("P_FILE_DOC_NM");
				
				for (int i = 0; i < mFileList.size(); i++) {
					MultipartFile mFile = mFileList.get(i);
					if(!mFile.isEmpty()){
						String originalFilename = mFile.getOriginalFilename();
						String systemFileName = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
						String ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
						String atchmnflExtsnNm = "";
						
						if("jpg".equals(ext) || "jpeg".equals(ext) || "png".equals(ext) || "gif".equals(ext)){
							atchmnflExtsnNm = "image/"+ext;
						}else{
							atchmnflExtsnNm = "application/"+ext;
						}
						
						try {
							mFile.transferTo(new File(fileDirPath+File.separator+systemFileName+"."+ext));
						} catch (IllegalStateException e) {
//							e.printStackTrace();
							System.out.println(">>>>>파일 업로드 상태 에러<<<<<");
						} catch (IOException e) {
//							e.printStackTrace();
							System.out.println(">>>>>파일 업로드 에러<<<<<");
						}
						
						FwkParameterMap map = new FwkParameterMap();
						
						if(fileDocNm != null){
							if(fileDocNm instanceof String){
								map.put("P_FILE_DOC_NM", fileDocNm); 
							}else if(fileDocNm instanceof String[]){ 
								String[] fileDocNmList = (String[]) fileDocNm;
								map.put("P_FILE_DOC_NM", fileDocNmList[i]);
							}else if(fileDocNm instanceof ArrayList){
								ArrayList fileDocNmList = (ArrayList) fileDocNm;
								map.put("P_FILE_DOC_NM", fileDocNmList.get(i));
							}
						}
						
						map.put("P_FILE_SZ", mFile.getSize()); 										//용량
						map.put("P_SV_FILE_NM",  systemFileName+"."+ext); 					//파일변환명
						map.put("P_SYS_FILE_NM",   mFile.getOriginalFilename()); 			//파일명
						map.put("P_FILE_LCTN",  fileDirPath );										//경로
						list.add(map);
					}
				}
				
				if(list.size() > 0){
					parameterMap.put("atchFileGroupNo", atchFileGroupNo);
					parameterMap.put("list", list);
				}else{
					parameterMap = null;
				}
			}
		}else{
			parameterMap = null;
		}
		
		return parameterMap;
	}
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 계약서식에서 등록
	 *	</pre>
	 *
	 * @method Name : formContUpload
	 * @Author : joo
	 * @Date   : 2020. 10. 28.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 10. 28.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param multipartRequest
	 * @param fileId
	 * @param contextPath
	 * @return
	 *
	 */
	public static FwkParameterMap formContUpload(MultipartHttpServletRequest multipartRequest, String fileId, String contextPath ){
		FwkParameterMap parameterMap = new FwkParameterMap();
		List<MultipartFile> mFileList = multipartRequest.getFiles(fileId);
		
		if(mFileList != null && mFileList.size() > 0){
			String dir = "form"+File.separator;
			String root = FwkMessageUtil.getMessage("EW.UBI.FILE.DIR");
			
			String fileDirPath = root + dir; // 파일 경로
			File fileDir = new File(fileDirPath);
			boolean mkdirSuccess = true;
			if(!fileDir.isDirectory()){
				fileDir.setExecutable(true);
				fileDir.setReadable(true);
				fileDir.setWritable(true);
				mkdirSuccess = fileDir.mkdirs();
			}
			// 파일을 저장할 폴더가 생성되었을 경우만 처리
			if(mkdirSuccess){
				
				String atchFileGroupNo =  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				
				for (int i = 0; i < mFileList.size(); i++) {
					MultipartFile mFile = mFileList.get(i);
					if(!mFile.isEmpty()){
						String originalFilename = mFile.getOriginalFilename();
						String systemFileName = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
						String ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
						String atchmnflExtsnNm = "";
						
						if("jpg".equals(ext) || "jpeg".equals(ext) || "png".equals(ext) || "gif".equals(ext)){
							atchmnflExtsnNm = "image/"+ext;
						}else{
							atchmnflExtsnNm = "application/"+ext;
						}
						
						try {
							mFile.transferTo(new File(fileDirPath+File.separator+systemFileName+"."+ext));
						} catch (IllegalStateException e) {
//							e.printStackTrace();
							System.out.println(">>>>>파일 업로드 상태 에러<<<<<");
						} catch (IOException e) {
//							e.printStackTrace();
							System.out.println(">>>>>파일 업로드 에러<<<<<");
						}
						
						FwkParameterMap map = new FwkParameterMap();
						map.put("P_FILE_SZ", mFile.getSize()); 										//용량
						map.put("P_SV_FILE_NM",  systemFileName+"."+ext); 					//파일변환명
						map.put("P_SYS_FILE_NM",   mFile.getOriginalFilename()); 			//파일명
						map.put("P_FILE_LCTN",  fileDirPath );										//경로
//						map.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");	//확장자 ?
						
						list.add(map);
					}
				}
				
				if(list.size() > 0){
					parameterMap.put("atchFileGroupNo", atchFileGroupNo);
					parameterMap.put("list", list);
				}else{
					parameterMap = null;
				}
			}
		}else{
			parameterMap = null;
		}
		
		return parameterMap;
	}
	
	
	public static FwkParameterMap formAutoUpload(MultipartHttpServletRequest multipartRequest, String fileId, String contextPath, FwkParameterMap parameterMap){
//		FwkParameterMap parameterMap = new FwkParameterMap();
		List<MultipartFile> mFileList = multipartRequest.getFiles(fileId);
		String atchFileGroupNo ="";
		if(parameterMap.getString("P_FILE_GRP_NO")!= null && parameterMap.getString("P_FILE_GRP_NO")!= ""){
			atchFileGroupNo =  parameterMap.getString("P_FILE_GRP_NO"); 
		}else{
			atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		}
		
		if(mFileList != null && mFileList.size() > 0){
			String dir = "form"+File.separator;
			
			String root = FwkMessageUtil.getMessage("EW.UBI.FILE.DIR");
			String fileDirPath = root + dir; // 파일 경로
			File fileDir = new File(fileDirPath);
			boolean mkdirSuccess = true;
			if(!fileDir.isDirectory()){
				fileDir.setExecutable(true);
				fileDir.setReadable(true); 
				fileDir.setWritable(true);
				mkdirSuccess = fileDir.mkdirs();
			}
			// 파일을 저장할 폴더가 생성되었을 경우만 처리
			if(mkdirSuccess){
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				Object fileDocNm = parameterMap.get("P_FILE_DOC_NM");
				
				for (int i = 0; i < mFileList.size(); i++) {
					MultipartFile mFile = mFileList.get(i);
					if(!mFile.isEmpty()){
						String originalFilename = mFile.getOriginalFilename();
						String systemFileName = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
						String ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
						String atchmnflExtsnNm = "";
						
						if("jpg".equals(ext) || "jpeg".equals(ext) || "png".equals(ext) || "gif".equals(ext)){
							atchmnflExtsnNm = "image/"+ext;
						}else{
							atchmnflExtsnNm = "application/"+ext;
						}
						
						try {
							mFile.transferTo(new File(fileDirPath+File.separator+systemFileName+"."+ext));
						} catch (IllegalStateException e) {
//							e.printStackTrace();
							System.out.println(">>>>>파일 업로드 상태 에러<<<<<");
						} catch (IOException e) {
//							e.printStackTrace();
							System.out.println(">>>>>파일 업로드 에러<<<<<");
						}
						
						FwkParameterMap map = new FwkParameterMap();
						
						if(fileDocNm != null){
							if(fileDocNm instanceof String){
								map.put("P_FILE_DOC_NM", fileDocNm); 
							}else if(fileDocNm instanceof String[]){ 
								String[] fileDocNmList = (String[]) fileDocNm;
								map.put("P_FILE_DOC_NM", fileDocNmList[i]);
							}else if(fileDocNm instanceof ArrayList){
								ArrayList fileDocNmList = (ArrayList) fileDocNm;
								map.put("P_FILE_DOC_NM", fileDocNmList.get(i));
							}
						}
						
						map.put("P_FILE_SZ", mFile.getSize()); 										//용량
						map.put("P_SV_FILE_NM",  systemFileName+"."+ext); 					//파일변환명
						map.put("P_SYS_FILE_NM",   mFile.getOriginalFilename()); 			//파일명
						map.put("P_FILE_LCTN",  fileDirPath );										//경로
						list.add(map);
					}
				}
				
				if(list.size() > 0){
					parameterMap.put("atchFileGroupNo", atchFileGroupNo);
					parameterMap.put("list", list);
				}else{
					parameterMap = null;
				}
			}
		}else{
			parameterMap = null;
		}
		
		return parameterMap;
	}
	
	private static String createContractText(String docHtml, FwkDataEntity fde) throws Exception {
		String rtnDocHtml = "";

		rtnDocHtml = docHtml;
		String	keyName	= "";
		Object	value	= "";

		if ( fde != null && fde.keySet().size() > 0 ) {
			for ( Iterator keys = fde.keySet().iterator(); keys.hasNext();) {
				keyName	= (String) keys.next();
				if(fde.get(keyName) != null){
					value	= (Object) fde.get(keyName);
				}else{
					value="&nbsp;";
				}
				rtnDocHtml	= StringUtils.replace( rtnDocHtml, "#" + keyName + "#", String.valueOf( value ) );
			}
		}
		return rtnDocHtml;
	}	
	
	/**
	 * 유비레포트 PDF 파일 생성
	 * 
	 * @param filePath
	 * @param fileName
	 */
	public static FwkParameterMap ubiExportPdf(String jrfNm, String agument, String dirNm) throws CustomException {
		
		FwkParameterMap rtnMap = new FwkParameterMap();
		
		/*** 유비레포트 PDF 파일 생성 ***/
		String appUrl		= FwkMessageUtil.getMessage("IEP.UBI.HOST");
		String fileURL		= appUrl + FwkMessageUtil.getMessage("IPRO.UBI.FILE.URL");	
		String ubiServerURL	= appUrl + FwkMessageUtil.getMessage("IPRO.UBI.SERVER.URL");

		String ds		= FwkMessageUtil.getMessage("IPRO.UBI.DATASOURCE");
		String jrfFileDir	= appUrl + FwkMessageUtil.getMessage("IEP.UBI.WORK.PATH");
		String jrfFileName	= jrfNm;
		String arg		= agument;
		
		String exportPath = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR")+
				File.separator+FwkDateUtil.getCurrentDateAsString("yyyy")+
				File.separator+FwkDateUtil.getCurrentDateAsString("MM")+
				File.separator+FwkDateUtil.getCurrentDateAsString("dd")+
				File.separator+dirNm + File.separator;
		//파일명 정의
		String fileName[] = agument.split("#");
		
		String exportFileName = dirNm + fileName[1]+ "-" + fileName[3]+".pdf";							// 파일명.
		UUID uuid	= UUID.randomUUID();
		String temp = uuid.toString();
		String exportFilePath	= exportPath + temp+".pdf";
		String exportfileType	= "PDF";		
		
		System.out.println(">>>>>>>>>>>>>>>>> CmmnUtil ubiExportPdf");
		System.out.println(">>>>>>>>>>>>>>>>> appUrl ::: "+appUrl);
		System.out.println(">>>>>>>>>>>>>>>>> fileURL ::: "+fileURL);
		System.out.println(">>>>>>>>>>>>>>>>> ubiServerURL ::: "+ubiServerURL);
		System.out.println(">>>>>>>>>>>>>>>>> ds ::: "+ds);
		System.out.println(">>>>>>>>>>>>>>>>> jrfFileDir ::: "+jrfFileDir);
		System.out.println(">>>>>>>>>>>>>>>>> jrfFileName ::: "+jrfFileName);
		System.out.println(">>>>>>>>>>>>>>>>> arg ::: "+arg);
		System.out.println(">>>>>>>>>>>>>>>>> exportPath ::: "+exportPath);
		System.out.println(">>>>>>>>>>>>>>>>> exportFileName ::: "+exportFileName);
		System.out.println(">>>>>>>>>>>>>>>>> exportFilePath ::: "+exportFilePath);
		System.out.println(">>>>>>>>>>>>>>>>> temp ::: "+temp);
		//폴더 생성여부 확인
		File f = new File(exportPath);		
		boolean mkdirSuccess = true;
		if(!f.isDirectory()){
			f.setExecutable(true);
			f.setReadable(true);
			f.setWritable(true);
			mkdirSuccess = f.mkdirs();
		}

		try {
			if(mkdirSuccess){
				UbiViewer ubi = new UbiViewer(false, false);

				ubi.fileURL = fileURL;
				ubi.ubiServerURL = ubiServerURL;
				ubi.isLocalFile = true;
				ubi.jrfFileDir = jrfFileDir;
				ubi.jrfFileName = jrfFileName;
				ubi.arg = arg;
				ubi.dataSource = ds;
				ubi.setExportParams(exportfileType, exportFilePath);
				ubi.exectype = "TYPE6";
				ubi.fontRevision = true;
				ubi.isDebug = true;
				ubi.setFontPath(FwkMessageUtil.getMessage("UBI.FONT"));

				boolean isSuccess = ubi.loadReport();				
				
				System.out.println(">>>>>>>>>>>>>>>>> isSuccess ::: "+isSuccess);
				
				if( !isSuccess ){
					rtnMap.put("result", "fail");
				}else{
					rtnMap.put("result", "succ");
					//rtnMap.put("atfi_dir", export_dir.replace("/", "\\"));
					rtnMap.put("export_dir", exportPath);
					rtnMap.put("export_file", exportFileName);
					rtnMap.put("temp_file", temp+".pdf");
				}
			}else{
				rtnMap.put("result", "fail");
			}
		}
		catch(CustomException e) {
			rtnMap.put("result", "fail");
		}
		
		return rtnMap;
	}
	
	
	
	/**
	 * 유비레포트 URF, PDF 파일 생성
	 * 
	 * @param filePath
	 * @param fileName
	 */
	public static FwkParameterMap ubiExportUrf(String jrfNm, String agument, String dirNm, String jrfPath) throws CustomException {
		
		FwkParameterMap rtnMap = new FwkParameterMap();
		
		/*** 유비레포트 URF, PDF 파일 생성 ***/
		
		
		String appUrl		= FwkMessageUtil.getMessage("IEP.UBI.HOST");
		String fileURL		= appUrl + FwkMessageUtil.getMessage("IPRO.UBI.FILE.URL");
		String ubiServerURL	= appUrl + FwkMessageUtil.getMessage("IPRO.UBI.SERVER.URL");						 

		String ds		= FwkMessageUtil.getMessage("IPRO.UBI.DATASOURCE");
		//String jrfFileDir	= FwkMessageUtil.getMessage("UBI.JRF.PATH");
		String jrfFileDir	= jrfPath ;
		String jrfFileName	= jrfNm;
		String arg		= agument;
		
		String exportPath = FwkMessageUtil.getMessage("EW.UBI.FILE.DIR")+
				FwkDateUtil.getCurrentDateAsString("yyyy")+
				File.separator+FwkDateUtil.getCurrentDateAsString("MM")+
				File.separator+FwkDateUtil.getCurrentDateAsString("dd")+
				File.separator+dirNm ;

		String fileName[] = agument.split("#");
		String exportFileName	= dirNm + fileName[1]+ "-" + fileName[3];
		
		UUID uuid	= UUID.randomUUID();
		String temp = uuid.toString();
		String exportFilePath	= exportPath + java.io.File.separator + temp;
		String exportfileType	= "URFIA"; // EXCEL_XML,URFIA
		
		System.out.println(">>>>>>>>>>>>>>>>> CmmnUtil ubiExportPdf");
		System.out.println(">>>>>>>>>>>>>>>>> appUrl ::: "+appUrl);
		System.out.println(">>>>>>>>>>>>>>>>> fileURL ::: "+fileURL);
		System.out.println(">>>>>>>>>>>>>>>>> ubiServerURL ::: "+ubiServerURL);
		System.out.println(">>>>>>>>>>>>>>>>> ds ::: "+ds);
		System.out.println(">>>>>>>>>>>>>>>>> jrfFileDir ::: "+jrfFileDir);
		System.out.println(">>>>>>>>>>>>>>>>> jrfFileName ::: "+jrfFileName);
		System.out.println(">>>>>>>>>>>>>>>>> arg ::: "+arg);
		System.out.println(">>>>>>>>>>>>>>>>> exportPath ::: "+exportPath);
		System.out.println(">>>>>>>>>>>>>>>>> exportFileName ::: "+exportFileName);
		System.out.println(">>>>>>>>>>>>>>>>> exportFilePath ::: "+exportFilePath);
		System.out.println(">>>>>>>>>>>>>>>>> temp ::: "+temp);
		//폴더 생성여부 확인
		File f = new File(exportPath);		
		boolean mkdirSuccess = true;
		if(!f.isDirectory()){
			f.setExecutable(true);
			f.setReadable(true);
			f.setWritable(true);
			mkdirSuccess = f.mkdirs();
		}

		try {
			if(mkdirSuccess){
				UbiViewer ubi = new UbiViewer(false, false);

				ubi.fileURL = fileURL;
				ubi.ubiServerURL = ubiServerURL;
				ubi.isLocalFile = true;
				ubi.jrfFileDir = jrfFileDir;
				ubi.jrfFileName = jrfFileName;
				ubi.arg = arg;
				ubi.dataSource = ds;
				ubi.setExportParams(exportfileType, exportFilePath);
				//ubi.setFontPath("C:/UbiService/fonts/");
				ubi.setFontPath(FwkMessageUtil.getMessage("UBI.FONT"));
				ubi.exectype = "TYPE6";
				ubi.fontRevision = true;
				ubi.isDebug = true;
				

				boolean isSuccess = ubi.loadReport();
				ubi.exportFileName = exportFilePath+".pdf";
				ubi.exportReport("PDF");		
				
					
				System.out.println(">>>>>>>>>>>>>>>>> isSuccess ::: "+isSuccess);
				
				if( !isSuccess ){
					rtnMap.put("result", "fail");
				}else{
					rtnMap.put("result", "succ");
					//rtnMap.put("atfi_dir", export_dir.replace("/", "\\"));
					rtnMap.put("export_dir", exportPath);
					rtnMap.put("export_file", exportFileName);
					rtnMap.put("temp_file", temp);
				}
			}else{
				rtnMap.put("result", "fail");
			}
		}
		catch(CustomException e) {
			rtnMap.put("result", "fail");
		}
		
		return rtnMap;
	}
	/**
	 * 전자결재 HTML 파일 생성
	 * 
	 * @param filePath
	 * @param fileName
	 */
	/**
	 * 전자결재 HTML 파일 생성
	 * 
	 * @param filePath
	 * @param fileName
	 */
	public static String ebidMSGBodyCreate(final FwkParameterMap parameterMap) throws Exception {
		String orgString = parameterMap.getString("P_MSG_CNTN");
		String rsltString = "";
		
		FwkDataEntity fde = (FwkDataEntity) parameterMap.get("P_MSG_ENTITY");
		
		rsltString = createContractText(orgString, fde);

		return rsltString;
	}	
	
	public static FwkParameterMap createZip(final FwkParameterMap parameterMap){
		String[] fileLctnSvFileNm = (String[])parameterMap.get("fileLctnSvFileNm"); 
		String[] sysFileNm = (String[])parameterMap.get("sysFileNm");
		String zipPath = parameterMap.getString("zipPath");
				
		byte[] buf = new byte[4096];
		
		try{
			ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(new FileOutputStream(zipPath));
		
			zipOut.setLevel(9);
			String enc = new java.io.OutputStreamWriter(System.out).getEncoding();
            if("UTF8".equals(enc)){
            	enc = "UTF-8";
            }
			zipOut.setEncoding(enc);
			
			for(int i=0; i<fileLctnSvFileNm.length; i++ ){
				FileInputStream in = new FileInputStream(fileLctnSvFileNm[i]);
				
				zipOut.putArchiveEntry(new ZipArchiveEntry(sysFileNm[i]));
				
				int len;
				while((len = in.read(buf)) > 0){
					zipOut.write(buf, 0, len);
				}

				zipOut.closeArchiveEntry();
				in.close();
				
			}
			zipOut.close();
		}catch(IOException e){ 
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 암호화 파일 생성
	 * 
	 * @param filePath
	 * @param fileName
	 * @throws IOException 
	 */
	public static void createAndReplaceEncryptFile(final FwkParameterMap parameterMap) throws IOException {
		String dir = (String) parameterMap.get("P_FILE_LCTN");
		String bidFileSysFileName = (String) parameterMap.get("P_SV_FILE_NM");
		File bidSendFile= new File(dir+File.separator+bidFileSysFileName);
		
		byte[] fileByte = FileUtils.readFileToByteArray(bidSendFile);
		Enveloper enveloper = new Enveloper(); 
		byte[] envelopFile = enveloper.envelope(fileByte);
		
		FileUtils.writeByteArrayToFile(new File(dir + File.separator + bidFileSysFileName), envelopFile);
	}
	
	
	/**
	 * List 형식 StringBuffer 형식으로 변환
	 * @param List
	 * @return StringBuffer
	 */
	public static StringBuffer jsonToSbByList2( List list ) throws Exception {
		StringBuffer sb = new StringBuffer();
		if(!list.isEmpty()){
			sb.append( "{");
			for (int i = 0; i < list.size(); i++) {
				if (i > 0) {
					sb.append( ",");
				}
				HashMap model = (HashMap) list.get(i);
				boolean isFirst = true ;
				if (isFirst) {
					isFirst = false;
				} else {
					sb.append( ",");
				}
				sb.append( "\"" + model.get("CD_VALUE") + "\":" + "\"" + model.get("CD_VALUE_NM") + "\"");
			}
			sb.append( "}");
		}
		return sb;
	}
	
	// 파일 복사
	public static void FileCopy2(String sourceName, String erpFileName, String sourcePath, String cofyPath) throws Exception {
		InputStream source; 
		OutputStream copy;
			  
		// 저장될 파일명 생성
		//UUID uuid = UUID.randomUUID();
		//String temp = uuid.toString();
		try {   
			
			   source = new FileInputStream(sourcePath + sourceName);
			   File CopyDir = new File(cofyPath); 
			   if (!CopyDir.exists())
			   CopyDir.mkdirs();
			     
			   copy = new FileOutputStream(cofyPath + erpFileName);
			   while (true) { 
			    int data = source.read(); 
			    if (data < 0) break;
			    copy.write(data); 
			   }
			   
			   source.close(); 
			   copy.close(); 
			   
			   
		} catch (FileNotFoundException fnfe) {
			//return null;
		} catch (IOException ioe) {
			//return null;
		}
		  	//return temp;
	}
	
	
	public static void FileCopy(String sourceName, String erpFileName, String sourcePath, String cofyPath) throws Exception {
		FileInputStream source = null;
		FileOutputStream copy = null; 
		
		FileChannel fcout = null; 
		FileChannel fcin = null; 
		
		//InputStream source; 
		//OutputStream copy;
			  
		// 저장될 파일명 생성
		//UUID uuid = UUID.randomUUID();
		//String temp = uuid.toString();
		try {   
			   source = new FileInputStream(sourcePath + sourceName);
			   File CopyDir = new File(cofyPath); 
			   
			   if (!CopyDir.exists())
			   CopyDir.mkdirs();
			   
			   copy = new FileOutputStream(cofyPath + erpFileName);
			  
			   fcin = source.getChannel();
			   fcout = copy.getChannel();
			 
			   long size = fcin.size();
			   fcin.transferTo(0, size, fcout);
			 
			   
			   
		} catch (FileNotFoundException fnfe) {
			//return null;
		} catch (IOException ioe) {
			//return null;
		}finally {
			if(fcout != null) {
				fcout.close();
			}
			if(fcin != null) {
				fcin.close();
			}
			if(copy != null) {
				copy.close();
			}
			if(source != null) {
				source.close();
			}
		}
		  	//return temp;
	}
	
    public static String cleanXSS(String value) {
		
		if(value == null) {
			return "";
		}
		value = value.replaceAll("", "");
		Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
		value = scriptPattern.matcher(value).replaceAll("");
		
		scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		value = scriptPattern.matcher(value).replaceAll("");
		
		value = HtmlUtils.htmlEscape(value);
		value = StringEscapeUtils.escapeSql(value);
		
		return value;
		
	}
	
   	public static ArrayList paramSet(Object obj) throws Exception{
   		ArrayList returnList = new ArrayList<>();
   		
   		if (obj != null) {
			if (obj instanceof String) {
				
				returnList.add(obj.toString());
			} else if (obj instanceof ArrayList) {
				ArrayList arrayList = (ArrayList) obj;
				
			} else if (obj instanceof String[]) {
				String[] arrayList = (String[]) obj;
				for (int idx = 0; idx < arrayList.length; idx++) {
					returnList.add((String) arrayList[idx]);
				}
			}
			
   		}
   		return returnList;
    }
	public static FwkParameterMap fileMultiUploads(MultipartHttpServletRequest multipartRequest, String fileId, String contextPath ) throws Exception{
		FwkParameterMap parameterMap = new FwkParameterMap();
		List<MultipartFile> mFileList = multipartRequest.getFiles(fileId);
		
		if(mFileList != null && mFileList.size() > 0){
			String root = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");
			String fileDirPath = root + FwkDateUtil.getCurrentDateAsString("yyyy") + File.separator + FwkDateUtil.getCurrentDateAsString("MM") + File.separator + FwkDateUtil.getCurrentDateAsString("dd") + File.separator + contextPath; // 파일 경로
			//File fileDir = new File(fileDirPath);
			File fileDir = null;			
			
			if(fileDirPath != null){
				fileDir = new File(FilenameUtils.getFullPath(fileDirPath), FilenameUtils.getName(fileDirPath));
			}
			
			boolean mkdirSuccess = true;
			if(fileDir != null && !fileDir.isDirectory()){
				fileDir.setExecutable(true);
				fileDir.setReadable(true);
				fileDir.setWritable(true);
				mkdirSuccess = fileDir.mkdirs();
			}
			// 파일을 저장할 폴더가 생성되었을 경우만 처리
			if(mkdirSuccess){
				
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				
				for (int i = 0; i < mFileList.size(); i++) {
					MultipartFile mFile = mFileList.get(i);
					String originalFilename = mFile.getOriginalFilename();
					String systemFileName = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
					String ext = originalFilename.substring(originalFilename.lastIndexOf('.')+1).toLowerCase();
					String atchmnflExtsnNm = "";
					
					if("jpg".equals(ext) || "jpeg".equals(ext) || "png".equals(ext) || "gif".equals(ext)){
						atchmnflExtsnNm = "image/"+ext;
					}else{
						atchmnflExtsnNm = "application/"+ext;
					}
					
					String stFileFullName = fileDirPath+File.separator+systemFileName+"."+ext;
					
					try {
							
							mFile.transferTo(new File(FilenameUtils.getFullPath(stFileFullName), FilenameUtils.getName(stFileFullName)));	//NOPMD
							//mFile.transferTo(new File(stFileFullName));
							
							//try {
								//EwCipher.seedFileEncrypt(stFileFullName, FwkMessageUtil.getMessageAdd("ESTT.SYMM.KEY"));
//								EcCipher.seedFileEncrypt(stFileFullName);
							//} catch (Exception e) {			//NOPMD    라이브러리 내부 처리
							//	Log.info(e.getMessage());
							//	throw e;
							//}
						
					} catch (IllegalStateException | IOException e ) {
						//Log.debug(">>>>>파일 업로드 상태 에러<<<<<");
					}
					
					FwkParameterMap map = new FwkParameterMap();		//NOPMD
					map.put("P_FILE_SZ", mFile.getSize()); 										//용량
					map.put("P_SV_FILE_NM",  systemFileName+"."+ext); 					//파일변환명
					map.put("P_SYS_FILE_NM",   FwkStringUtil.castEuc2Iso(mFile.getOriginalFilename())); 			//파일명
					map.put("P_FILE_LCTN",  fileDirPath );										//경로
					map.put("P_FILE_GRP_NO",  FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "") );										//경로
//					map.put("P_ATCHMNFL_EXTSN_NM",  "application/octet-stream");	//확장자 ?
					
					if(mFile.getSize() > 0){
						map.put("P_FILE_YN","Y"); 	
					}else{
						map.put("P_FILE_YN","N"); 	
					}
					list.add(map);
				}
				parameterMap.put("list", list);
			}
		}else{
			parameterMap = null;
		}
		
		return parameterMap;
	} 	
   	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 파일구분에 따른 업로드
	 *	</pre>
	 *
	 * @method Name : fileGbnMultiUpload
	 * @Author : joo
	 * @Date   : 2020. 9. 22.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 22.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param multipartRequest
	 * @param fileId
	 * @param contextPath
	 * @param parameterMap
	 * @return
	 *
	 */
	public static FwkParameterMap fileGbnMultiUpload(MultipartHttpServletRequest multipartRequest, String fileId, String contextPath, FwkParameterMap parameterMap){
		List<MultipartFile> mFileList = multipartRequest.getFiles(fileId);
		String atchFileGroupNo ="";
		if(parameterMap.getString("P_FILE_GRP_NO") != null && !"".equals(parameterMap.getString("P_FILE_GRP_NO"))){
			atchFileGroupNo =  parameterMap.getString("P_FILE_GRP_NO"); 
		}else{
			atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", ""); 
		}
		
		if(mFileList != null && mFileList.size() > 0){
			String root = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");	// D:\\edata1\\
			String fileDirPath = root + FwkDateUtil.getCurrentDateAsString("yyyy") + File.separator + FwkDateUtil.getCurrentDateAsString("MM") + File.separator + FwkDateUtil.getCurrentDateAsString("dd") + File.separator + contextPath; // 파일 경로
			File fileDir = new File(fileDirPath);
			boolean mkdirSuccess = true;
			if(!fileDir.isDirectory()){
				fileDir.setExecutable(true);
				fileDir.setReadable(true);
				fileDir.setWritable(true);
				mkdirSuccess = fileDir.mkdirs();
			}
			// 파일을 저장할 폴더가 생성되었을 경우만 처리
			if(mkdirSuccess){
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				Object fileDocSecd = parameterMap.get("P_FILE_DOC_SECD");
				Object fileViewYn = parameterMap.get("P_VIEW_YN");
				
				for (int i = 0; i < mFileList.size(); i++) {
					MultipartFile mFile = mFileList.get(i);
					if(!mFile.isEmpty()){
						String originalFilename = mFile.getOriginalFilename();
						String systemFileName = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
						String ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
						String atchmnflExtsnNm = "";
						
						if("jpg".equals(ext) || "jpeg".equals(ext) || "png".equals(ext) || "gif".equals(ext)){
							atchmnflExtsnNm = "image/"+ext;
						}else{
							atchmnflExtsnNm = "application/"+ext;
						}
						
						try {
							mFile.transferTo(new File(fileDirPath+File.separator+systemFileName+"."+ext));
						} catch (IllegalStateException e) {
							System.out.println(">>>>>파일 업로드 상태 에러<<<<<");
						} catch (IOException e) {
							System.out.println(">>>>>파일 업로드 에러<<<<<");
						}
						
						FwkParameterMap map = new FwkParameterMap();
						
						if(fileViewYn != null){
							if(fileViewYn instanceof String){
								map.put("P_TSK_VKEY3", fileViewYn); 
							}else if(fileViewYn instanceof String[]){ 
								String[] fileViewYnList = (String[]) fileViewYn;
								map.put("P_TSK_VKEY3", fileViewYnList[i]);
							}else if(fileViewYn instanceof ArrayList){
								ArrayList fileViewYnList = (ArrayList) fileViewYn;
								map.put("P_TSK_VKEY3", fileViewYnList.get(i));
							}
						}
						
						if(fileDocSecd != null){
							if(fileDocSecd instanceof String){
								map.put("P_FILE_DOC_SECD", fileDocSecd); 
							}else if(fileDocSecd instanceof String[]){ 
								String[] fileDocSecdList = (String[]) fileDocSecd;
								map.put("P_FILE_DOC_SECD", fileDocSecdList[i]);
							}else if(fileDocSecd instanceof ArrayList){
								ArrayList fileDocSecdList = (ArrayList) fileDocSecd;
								map.put("P_FILE_DOC_SECD", fileDocSecdList.get(i));
							}
						}
						 
						map.put("P_FILE_SZ", mFile.getSize()); 										//용량
						map.put("P_SV_FILE_NM",  systemFileName+"."+ext); 					//파일변환명
						map.put("P_SYS_FILE_NM",   mFile.getOriginalFilename()); 			//파일명
						map.put("P_FILE_LCTN",  fileDirPath );										//경로
						list.add(map);
					}
				}
				
				if(list.size() > 0){
					parameterMap.put("atchFileGroupNo", atchFileGroupNo);
					parameterMap.put("list", list);
				}else{
					parameterMap = null;
				}
			}
		}else{
			parameterMap = null;
		}
		
		return parameterMap;
	}

	public static FwkParameterMap fileGbnUpload(MultipartHttpServletRequest multipartRequest, String fileId, String contextPath, FwkParameterMap parameterMap){
		List<MultipartFile> mFileList = new ArrayList<MultipartFile>();
		if(!multipartRequest.getFiles(fileId).isEmpty()){
			mFileList = multipartRequest.getFiles(fileId); 
		}else{
			mFileList = null;
		}
		String atchFileGroupNo ="";
		if(parameterMap.getString("P_FILE_GRP_NO") != null && !"".equals(parameterMap.getString("P_FILE_GRP_NO"))){
			atchFileGroupNo =  parameterMap.getString("P_FILE_GRP_NO"); 
		}else{
			atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", ""); 
		}
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Object fileDocSecd = parameterMap.get("P_FILE_DOC_SECD");
		Object fileSz = parameterMap.get("P_FILE_SZ");
		Object svFileNm = parameterMap.get("P_SV_FILE_NM");
		Object sysFileNm = parameterMap.get("P_SYS_FILE_NM");
		Object fileLctn = parameterMap.get("P_FILE_LCTN");
		
		MultipartFile mFile = null;
		FwkParameterMap map = new FwkParameterMap();
		
		if(!"".equals(parameterMap.get("P_NO"))){
			mFile = mFileList.get(parameterMap.getInt("P_NO"));
		}
		
		if(mFile != null){
			String root = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");	// D:\\edata1\\
			String fileDirPath = root + FwkDateUtil.getCurrentDateAsString("yyyy") + File.separator + FwkDateUtil.getCurrentDateAsString("MM") + File.separator + FwkDateUtil.getCurrentDateAsString("dd") + File.separator + contextPath; // 파일 경로
			File fileDir = new File(fileDirPath);
			boolean mkdirSuccess = true;
			if(!fileDir.isDirectory()){
				fileDir.setExecutable(true);
				fileDir.setReadable(true);
				fileDir.setWritable(true);
				mkdirSuccess = fileDir.mkdirs();
			}
			// 파일을 저장할 폴더가 생성되었을 경우만 처리
			if(mkdirSuccess){
				if(!mFile.isEmpty()){
					String originalFilename = mFile.getOriginalFilename();
					String systemFileName = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
					String ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
					String atchmnflExtsnNm = "";
					
					if("jpg".equals(ext) || "jpeg".equals(ext) || "png".equals(ext) || "gif".equals(ext)){
						atchmnflExtsnNm = "image/"+ext;
					}else{
						atchmnflExtsnNm = "application/"+ext;
					}
					
					try {
						mFile.transferTo(new File(fileDirPath+File.separator+systemFileName+"."+ext));
					} catch (IllegalStateException e) {
						System.out.println(">>>>>파일 업로드 상태 에러<<<<<");
					} catch (IOException e) {
						System.out.println(">>>>>파일 업로드 에러<<<<<");
					}
					
					map.put("P_FILE_DOC_SECD", fileDocSecd);
					map.put("P_FILE_SZ", mFile.getSize()); 										//용량
					map.put("P_SV_FILE_NM",  systemFileName+"."+ext); 					//파일변환명
					map.put("P_SYS_FILE_NM",   mFile.getOriginalFilename()); 			//파일명
					map.put("P_FILE_LCTN",  fileDirPath );										//경로
					
					list.add(map);
				}
			}
		}else{
			map.put("P_FILE_DOC_SECD", fileDocSecd);
			map.put("P_FILE_SZ", fileSz); 										//용량
			map.put("P_SV_FILE_NM",  svFileNm); 					//파일변환명
			map.put("P_SYS_FILE_NM",   sysFileNm); 			//파일명
			map.put("P_FILE_LCTN",  fileLctn );	
			
			list.add(map);
		}
		
		if(list.size() > 0){
			parameterMap.put("atchFileGroupNo", atchFileGroupNo);
			parameterMap.put("list", list);
		}else{
			parameterMap = null;
		}
		
		return parameterMap;
	}
	
   	
	
	public static FwkParameterMap fileContMultiUpload(MultipartHttpServletRequest multipartRequest, String fileId, String contextPath, FwkParameterMap parameterMap){
		List<MultipartFile> mFileList = multipartRequest.getFiles(fileId);
		String atchFileGroupNo ="";
		if(parameterMap.getString("P_FILE_GRP_NO")!= null && !"".equals(parameterMap.get("P_FILE_GRP_NO"))){
			atchFileGroupNo =  parameterMap.getString("P_FILE_GRP_NO"); 
		}else{
			atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		}
		if(mFileList != null && mFileList.size() > 0){
			String root = FwkMessageUtil.getMessage("EW.SYS.FILE.DIR");	// D:\\edata1\\
			String fileDirPath = root + FwkDateUtil.getCurrentDateAsString("yyyy") + File.separator + FwkDateUtil.getCurrentDateAsString("MM") + File.separator + FwkDateUtil.getCurrentDateAsString("dd") + File.separator + contextPath; // 파일 경로
			File fileDir = new File(fileDirPath);
			boolean mkdirSuccess = true;
			if(!fileDir.isDirectory()){
				fileDir.setExecutable(true);
				fileDir.setReadable(true);
				fileDir.setWritable(true);
				mkdirSuccess = fileDir.mkdirs();
			}
			// 파일을 저장할 폴더가 생성되었을 경우만 처리
			if(mkdirSuccess){
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				//Object fileSn = parameterMap.get("P_FILE_SN");
				Object fileDocSecd = parameterMap.get("P_CONT_FILE_DOC_SECD");
				Object fileViewYn = parameterMap.get("P_VIEW_YN");
				
				for (int i = 0; i < mFileList.size(); i++) {
					MultipartFile mFile = mFileList.get(i);
					if(!mFile.isEmpty()){
						String originalFilename = mFile.getOriginalFilename();
						String systemFileName = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
						String ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
						String docNm = originalFilename.substring(0,originalFilename.lastIndexOf(".")).toLowerCase();
						String atchmnflExtsnNm = "";
						
						if("jpg".equals(ext) || "jpeg".equals(ext) || "png".equals(ext) || "gif".equals(ext)){
							atchmnflExtsnNm = "image/"+ext;
						}else{
							atchmnflExtsnNm = "application/"+ext;
						}
						
						try {
							mFile.transferTo(new File(fileDirPath+File.separator+systemFileName+"."+ext));
						} catch (IllegalStateException e) {
							System.out.println(">>>>>파일 업로드 상태 에러<<<<<");
						} catch (IOException e) {
							System.out.println(">>>>>파일 업로드 에러<<<<<");
						}
						
						FwkParameterMap map = new FwkParameterMap();
						
						if(fileViewYn != null){
							if(fileViewYn instanceof String){
								map.put("P_TSK_VKEY3", fileViewYn); 
							}else if(fileViewYn instanceof String[]){ 
								String[] fileViewYnList = (String[]) fileViewYn;
								map.put("P_TSK_VKEY3", fileViewYnList[i]);
							}else if(fileViewYn instanceof ArrayList){
								ArrayList fileViewYnList = (ArrayList) fileViewYn;
								map.put("P_TSK_VKEY3", fileViewYnList.get(i));
							}
						}
						
						if(fileDocSecd != null){
							if(fileDocSecd instanceof String){
								map.put("P_CONT_FILE_DOC_SECD", fileDocSecd); 
							}else if(fileDocSecd instanceof String[]){ 
								String[] fileDocSecdList = (String[]) fileDocSecd;
								map.put("P_CONT_FILE_DOC_SECD", fileDocSecdList[i]);
							}else if(fileDocSecd instanceof ArrayList){
								ArrayList fileDocSecdList = (ArrayList) fileDocSecd;
								map.put("P_CONT_FILE_DOC_SECD", fileDocSecdList.get(i));
							}
						}
						map.put("P_FILE_SZ", mFile.getSize()); 								//용량
						map.put("P_SV_FILE_NM",  systemFileName+"."+ext); 					//파일변환명
						map.put("P_SYS_FILE_NM",   mFile.getOriginalFilename()); 			//파일명
						map.put("P_FILE_LCTN",  fileDirPath );								//경로
						map.put("P_FILE_DOC_NM", docNm);
						list.add(map);
					}
				}
				
				if(list.size() > 0){
					parameterMap.put("atchFileGroupNo", atchFileGroupNo);
					parameterMap.put("list", list);
				}else{
					parameterMap = null;
				}
			}
		}else{
			parameterMap = null;
		}
		return parameterMap;
	}
	
	public static String getToday(String pattern) throws Exception {
		Calendar calendar = Calendar.getInstance();
		return formatDate(calendar.getTime(), pattern);
	}	
	
	/**
	 * 날짜를 원하는 포맷으로 변경
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static String formatDate(Date date, String pattern) throws Exception {
		if(date == null)
			return "";
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}	
}
 