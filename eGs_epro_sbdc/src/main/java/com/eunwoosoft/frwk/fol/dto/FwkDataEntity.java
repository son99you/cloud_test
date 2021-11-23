/*
 * <pre>
 * Copyright (c) 2014 KOICA.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 *
 * </pre>
 */
package com.eunwoosoft.frwk.fol.dto; 

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Maybatis Select 결과를 담는 Map 형태의 데이터셋 구조  인터페이스
 * 
 * @author : 
 * @date : 2014. 11. 10.
 * @version : 1.0
 */
public interface FwkDataEntity extends Map<String, Object>, Serializable {
	
	/**
	 * Key Name을 List형태로 반환한다.
	 * 
	 * @Method Name : getKeyNames	  
	 * @return List형태의 key names
	 * 
	 * @author :  
	 * @date : 2014. 11. 10.
	 */
	List<String> getKeyNames();
	
	/**
	 * key에 해당하는 값을 String 형태로 반환한다.
	 * 
	 * @Method Name : getString
	 * @param key - key
	 * @return key
	 *
	 * @author :  
	 * @date : 2014. 11. 10. 
	 
	 */
	String getString(String key);
	
	/**
	 * key에 해당하는 값을 Trim 처리된 String 형태로 반환한다.
	 * 
	 * @Method Name : getTrimString	  
	 * @param key - key
	 * @return String
	 * @author :  
	 * @date : 2014. 11. 10.
	 */
	String getTrimString(String key);
	
	/**
	 * key에 해당하는 값을 int 형태로 반환한다.
	 * 
	 * @Method Name : getInt
	 * @param key - key
	 * @return int
	 * @author :  
	 * @date : 2014. 11. 10.	 
	 */
	int getInt(String key);
	
	/**
	 * key에 해당하는 값을 Long 형태로 반환한다.
	 * 
	 * @Method Name : getLong
	 * @param key - key
	 * @return long
	 * @author :  
	 * @date : 2014. 11. 10.
	 */
	long getLong(String key);
	
	/**
	 * 
	 *  key에 해당하는 값을 Double 형태로 반환한다.
	 * 
	 * @Method Name : getDouble 
	 * @param key
	 * @return Double
	 * @author :  
	 * @date : 2014. 11. 10.
	 */
	double getDouble(String key);

}
