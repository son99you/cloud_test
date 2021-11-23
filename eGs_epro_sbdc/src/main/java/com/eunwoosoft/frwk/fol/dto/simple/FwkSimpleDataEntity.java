/*
 * <pre>
 * Copyright (c) 2014 KOICA.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 *
 * </pre>
 */
package com.eunwoosoft.frwk.fol.dto.simple; 

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;

/**
 * DataEntity 구현 클래스
 * 
 * @author : 
 * @date : 2014. 11. 12.
 * @version : 1.0
 */
@SuppressWarnings("serial")
public class FwkSimpleDataEntity extends LinkedHashMap<String, Object> implements FwkDataEntity {

//	private static final long serialVersionUID = -9146767265030728735L;
	
	public FwkSimpleDataEntity() {
		super();
	}
	
	public FwkSimpleDataEntity(Map<String, Object> map) {
		super(map);
	}
	
	public FwkSimpleDataEntity(FwkDataEntity dataEntity) {
		super(dataEntity);
	}

	//@Override
	public List<String> getKeyNames() {
		List<String> getKeyNames = new ArrayList<String>();
		
		Iterator<String> iter = keySet().iterator();
		
		while(iter.hasNext()) {
			getKeyNames.add(iter.next());
		}
		
		return getKeyNames;
	}

	//@Override
	public String getString(String key) {
		Object o = get(key);
		return o != null ? o.toString() : "";
	}

	//@Override
	public String getTrimString(String key) {
		return getString(key).trim();
	}

	//@Override
	public int getInt(String key) {
		return (int) getDouble(key);
	}

	//@Override
	public long getLong(String key) {
		return (long) getDouble(key);
	}

	//@Override
	public double getDouble(String key) {
		double rtnValue = 0.0;
		try {
			rtnValue = new Double(getTrimString(key)).doubleValue();
		} catch (NumberFormatException e) {			
			rtnValue = 0.0;
        }
		return rtnValue;
	}	
}
