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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eunwoosoft.frwk.fol.util.FwkConversionServiceUtil;

/**
 * 서비스에서 데이터를 Map형태로  담는 클래스
 * 
 * @author : 
 * @date : 2014. 11. 14.
 * @version : 1.0
 */
@SuppressWarnings("serial")
public class FwkTransferObject extends HashMap<String, Object> implements Serializable {
	
//	private static final long serialVersionUID = 8877508980249938192L;
	

	
	/**
	 * 결과코드
	 */
	private String resultCode = "";
	
	/**
	 * Default Constructor
	 */
	public FwkTransferObject() {
		
	}
	
	/**
	 * 결과코드를 반환한다.
	 * 
	 * @Method Name : getResultCode 
	 * @return
	 * @author :  
	 * @date : 2014. 11. 27.
	 */
	public String getResultCode() {
		return resultCode;
	}
	
	/**
	 * 결과코드를 Set 한다.
	 * 
	 * @Method Name : setResultCode 
	 * @param resultCode
	 * @author :  
	 * @date : 2014. 11. 27.
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	public FwkDataEntity getDataEntity(String key) {				
		Map<String, Object> map = (Map<String, Object>) get(key);
		System.out.println("FwkTransferObject Map 담긴 값 :::" + map);
		return FwkConversionServiceUtil.mapToDataEntity(map);						
	}
	
	public List<FwkDataEntity> getListOfDataEntity(String key) {		
		List<FwkDataEntity> listOfDataEntity = new ArrayList<FwkDataEntity>();		
		
//		List<Map<String, Object>> list = (List<Map<String, Object>>) get(key);

		return listOfDataEntity;		
	}
}
