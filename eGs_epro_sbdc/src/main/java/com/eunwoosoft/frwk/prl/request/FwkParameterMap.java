package com.eunwoosoft.frwk.prl.request; 

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.fol.util.SDBCryptoUtil;

import org.springframework.util.StringUtils;

import com.google.gson.Gson;

 
/**
 * HttpServletRequest 정보를 Map형태로 보관하는 클래스
 * 
 * @author : 
 * @date : 2014. 11. 11.
 * @version : 1.0
 */
@SuppressWarnings("serial")
public class FwkParameterMap extends HashMap<String, Object> implements Serializable {

//	private static final long serialVersionUID = 3065776288462871407L;
	

	/**
	 * contructor
	 */
	public FwkParameterMap() {		
		this.put("requestId", UUID.randomUUID().toString().replaceAll("-", ""));
	}
	
	
	/**
	 * View 이름을 반환한다.
	 * 
	 */
	public String getViewName(String prefixUrl) {		
		String[] arr = getTrimString("requestUrl").split("/");
		StringBuffer sb = new StringBuffer();
		if(arr != null) {
			for(int i=2; i<arr.length ; i++) {	
				if(i==2) {
					sb.append(arr[i]);
				}				
				else {
					sb.append(StringUtils.capitalize(arr[i]));
				} 
			}
		}
		String tmp = FwkStringUtil.replace(sb.toString(), ".do", "");
		tmp = tmp.replace(".dx", "");
		tmp = tmp.replace(".json", "");
		tmp = tmp.replace(".iframe", "");
		
		return prefixUrl + tmp;
	}
	

	public String getPopupViewName(String prefixUrl) {		
		String[] arr = getTrimString("requestUrl").split("/");
		StringBuffer sb = new StringBuffer();
		if(arr != null) {
			for(int i=3; i<arr.length ; i++) {	
				if(i==3) {
					sb.append(arr[i]);
				}				
				else {
					
					sb.append(StringUtils.capitalize(arr[i]));
				} 
			}
		}
		String tmp = FwkStringUtil.replace(sb.toString(), ".do", "");
		tmp = tmp.replace(".dx", "");
		tmp = tmp.replace(".iframe", "");
		
		return prefixUrl + tmp;		
	}
	
	public String getLastViewName(String prefixUrl) {		
		String[] arr = getTrimString("requestUrl").split("/");
		
		StringBuffer sb = new StringBuffer();
		if(arr != null) {
			sb.append(arr[arr.length-1]);
		}
		
		String tmp = FwkStringUtil.replace(sb.toString(), ".do", "");
		tmp = tmp.replace(".dx", "");
		tmp = tmp.replace(".json", "");
		tmp = tmp.replace(".iframe", "");
		
		return prefixUrl + tmp;
	}
	
	public String getOproPopupViewName(String prefixUrl) {		
		String[] arr = getTrimString("requestUrl").split("/");
		StringBuffer sb = new StringBuffer();
		if(arr != null) {
			for(int i=4; i<arr.length ; i++) {	
				if(i==4) {
					sb.append(arr[i]);
				}				
				else {
					sb.append(StringUtils.capitalize(arr[i]));
				} 
			}
		}
		
		String tmp = FwkStringUtil.replace(sb.toString(), ".do", "");
		tmp = tmp.replace(".dx", "");	
		
		return prefixUrl + tmp;
	}
	
	public String getOproViewName(String prefixUrl) {		
		String[] arr = getTrimString("requestUrl").split("/");
		StringBuffer sb = new StringBuffer();
		if(arr != null) {
			for(int i=3; i<arr.length ; i++) {	
				if(i==3) {
					sb.append(arr[i]);
				}				
				else {
					sb.append(StringUtils.capitalize(arr[i]));
				} 
			}
		}
		
		String tmp = FwkStringUtil.replace(sb.toString(), ".do", "");
		tmp = tmp.replace(".dx", "");	
		
		return prefixUrl + tmp;
	}
	
	public String getCmmnViewName() {		
		String[] arr = getTrimString("requestUrl").split("/");
		StringBuffer sb = new StringBuffer();
		if(arr != null) {
			for(int i=1; i<arr.length ; i++) {	
				if(i==1) {
					sb.append(arr[i]);
				}				
				else {
					sb.append(StringUtils.capitalize(arr[i]));
				} 
			}
		}

		String tmp = FwkStringUtil.replace(sb.toString(), ".do", "");
		tmp = tmp.replace(".dx", "");	
		
		return tmp;
	}
	
	/**
	 * ParameterMap의 Key 이름을 List형태로 반환한다.
	 * 
	 * @Method Name : getKeyNames 
	 * @author :  
	 * @date : 2014. 11. 11.
	 */
	public List<String> getKeyNames() {
		List<String> getKeyNames = new ArrayList<String>();		
		Iterator<String> iter = keySet().iterator();		
		while(iter.hasNext()) {
			getKeyNames.add(iter.next());
		}		
		return getKeyNames;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Key에 해당하는 값을 String 형태로 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getString
	 * @date : 2015. 1. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		Object o = get(key);
		return o != null ? o.toString() : "";
    }
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : Trim 처리된 문자열로 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getTrimString
	 * @date : 2015. 1. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param key
	 * @return
	 */
	public String getTrimString(String key) {
		return getString(key).trim();        
    }
	
	/**
	 * <pre>
	 * 1. 개요 : Key에 해당하는 값을 int 형태로 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getInt
	 * @date : 2015. 1. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
		return (int) getDouble(key);
	}

	/**
	 * <pre>
	 * 1. 개요 : Key에 해당하는 값을 long 형태로 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getLong
	 * @date : 2015. 1. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param key
	 * @return
	 */
	public long getLong(String key) {
		return (long) getDouble(key);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Key에 해당하는 값을 double 형태로 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getDouble
	 * @date : 2015. 1. 16.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 1. 16.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param key
	 * @return
	 */
	public double getDouble(String key) {
		double rtnValue = 0.0;
		try {
			rtnValue = new Double(getTrimString(key)).doubleValue();
		} catch (NumberFormatException e) {			
			rtnValue = 0.0;
        }
		return rtnValue;
	}	
	
	/**
	 * <pre>
	 * 1. 개요 : 필수입력값 체크
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : essntlIputValueCeck
	 * @date : 2014. 12. 10.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 10.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param checkKeys - 필수입력값체크대상 키 목록
	 * @return
	 */
	public boolean essntlIputValueCeck(List<String> checkKeys) {		
	    return true;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 유효성체크
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : validate
	 * @date : 2014. 12. 10.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 10.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param checkKeys - 필수입력값체크대상 키 목록
	 * @return
	 */
	public void validate() {
		String message = FwkMessageUtil.getMessage("smpAcauEmplyrLoginForm.001");		
		Gson gson = new Gson();		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = gson.fromJson(message, List.class);		
		for(Map<String, Object> map : list) {			
			for(Map.Entry<String, Object> entry : map.entrySet()) {
				@SuppressWarnings("unchecked")
				Map<String, Object> valMap = (Map<String, Object>) entry.getValue();				
				for(Map.Entry<String, Object> valEntry : valMap.entrySet()) {
					String validKey = valEntry.getKey();					
					if("required".equalsIgnoreCase(validKey)) {						
						if(FwkStringUtil.isNull(getTrimString(entry.getKey()))) {
							
						}
					}					
				}
			}			
		}
	}
	
	@SuppressWarnings("unchecked")
	public String toString() {
		String buffGbn = "N";
		StringBuilder buff = new StringBuilder("[[ BEGIN :  param[" + getTrimString("requestId") + "]: ]]");
        String key;

        Iterator<String> i = keySet().iterator();

        while (i.hasNext()) {
            key = (String)i.next();
            
            Object obj = get(key);
            
            if (obj instanceof String[]) {
            	
            	StringBuilder listVal = new StringBuilder();
            	
            	int cnt = 0;
            	
            	for(String val : (String[]) obj) {
            		
            		if( cnt == 0) {
            			listVal.append(val) ;
            		} else {
            			listVal.append(", " + val) ;
            		}
            		cnt++;
    			}
            	if(!"P_LOGIN_PASSWD".equalsIgnoreCase(key)) {
            		buff.append("\n\t\t\t\t\t\t" + key + "=" + listVal.toString());
            	}
            	
            	
            } else {
            	
            	if("requestId".equalsIgnoreCase(key)) {
            		//buff.append("\n\t\t\t\t\t\t" + key + "=" + obj);
            	}else if("P_PGM_ID".equalsIgnoreCase(key)) {
            		buffGbn = "Y";
            		//buff.append("\n\t\t\t\t\t\t" + key + "=" + obj);
            	}else if("menuList".equalsIgnoreCase(key)) {
            		//buff.append("\n\t\t\t\t\t\t" + key + "=" + obj);
            	}else {
            		buff.append("\n\t\t\t\t\t\t" + key + "=" + obj);
            	}
            }            
        }

        buff.append("\n\t\t\t\t\t[[ END :  param[" + getTrimString("requestId") + "] ]]\n");

        
//        System.out.println("버파값 : " + buff.toString());
        
        if("Y".equals(buffGbn)){
        	return "";
        }else {
        	return buff.toString();
        }
        
        
    }
	
	/**
	 * <pre>
	 * 1. 개요 : 로그인세션결과값 반환
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getLoginResult
	 * @date : 2014. 12. 10.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 10.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param checkKeys - 필수입력값체크대상 키 목록
	 * @return
	 */
	public FwkDataEntity getLoginResult() {		
		return (FwkDataEntity) get("loginResult");
	}
	
	/**
	 * <pre>
	 * 1. 개요 : key값에 해당하는 값을 objName 암호화 하여 치환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : setEncryptEx
	 * @date : 2014. 12. 10.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 10.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param objName
	 * @param key
	 */
	
	public void setEncryptEx(String objName, String key) {		
		String plainText = getTrimString(key);		
		put(key, SDBCryptoUtil.encryptEx(objName, plainText));
	}
	
	/**
	 * <pre>
	 * 1. 개요 : key값에 해당하는 값을 objName 복호화 하여 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	* @Method Name : getEncryptEx
	 * @date : 2014. 12. 10.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 10.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param objName
	 * @param key
	 * @return
	 */
	public String getEncryptEx(String objName, String key) {		
		String plainText = getTrimString(key);		
		return SDBCryptoUtil.encryptEx(objName, plainText);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Key에 해당하는 값을 ListOfMap 형태로 반환한다.
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getEncryptEx
	 * @date : 2014. 12. 10.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 10.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getListOfMap(String key) {
		Object o = get(key);
		if( o != null) {
			return (List<Map<String, Object>>) o;
		}
		return null;
    }
	

}