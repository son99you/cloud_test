/*
 * Copyright koica.go.kr.,LTD.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of koica.go.kr.,LTD. ("Confidential Information").
 */
package com.eunwoosoft.frwk.fol.util;

import java.util.Locale;
import java.util.Properties;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * <pre>
 * com.eunwoosoft.frwk.fol.util 
 *    |_ FwkReloadableResourceBundleMessageSource.java
 * 
 * </pre>
 * @date : 2015. 1. 14. 오후 4:38:41
 * @version : 1.0
 * @author : 
 */
public class FwkReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource  {
	
	public Properties getAllProperties(Locale locale) {
		clearCacheIncludingAncestors();
		PropertiesHolder propertiesHolder = getMergedProperties(locale);
		Properties properties = propertiesHolder.getProperties();
		return properties;		
	}
}
