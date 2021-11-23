/*
 * Copyright koica.go.kr.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of koica.go.kr.,LTD. ("Confidential Information").
 */
package com.eunwoosoft.frwk.fol.util;

import java.util.List;
import java.util.Map;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * com.eunwoosoft.frwk.fol.util 
 *    |_ FwkConversionServiceUtil.java
 * 
 * </pre>
 * @date : 2015. 3. 11. 오후 8:28:42
 * @version : 1.0
 * @author : 
 */
@Component
public class FwkConversionServiceUtil {
	
	private static ConversionService conversionService;
	
	@Autowired(required = true)
	public FwkConversionServiceUtil(@Qualifier("conversionService") ConversionService conversionService) {
		FwkConversionServiceUtil.conversionService = conversionService;
	}
	
	public static FwkTransferObject mapToTransferObject(Map<String, Object> map) {
		return conversionService.convert(map, FwkTransferObject.class);		
	}
	
	public static FwkDataEntity mapToDataEntity(Map<String, Object> map) {
		return conversionService.convert(map, FwkDataEntity.class);		
	}
	
	public static List<FwkDataEntity> mapToList(Map<String, Object> map) {
		return conversionService.convert(map, List.class);
	}
	
}
