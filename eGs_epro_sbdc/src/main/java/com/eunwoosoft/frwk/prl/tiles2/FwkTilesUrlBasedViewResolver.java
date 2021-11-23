/*
 * <pre>
 * Copyright (c) 2014 KOICA.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 *
 * </pre>
 */
package com.eunwoosoft.frwk.prl.tiles2; 

import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * 클래스 설명
 * 
 * @author : 
 * @date : 2014. 11. 14.
 * @version : 1.0
 */
public class FwkTilesUrlBasedViewResolver extends UrlBasedViewResolver implements Ordered{
	
	private String tilesDefinitionName = null;
	private String tilesBodyAttributeName = null;
	private String tilesDefinitionDelimiter = null;

	/**
	 * Main template name.
	 */
	public void setTilesDefinitionName(String tilesDefinitionName) {
		this.tilesDefinitionName = tilesDefinitionName;
	}
	
	
	public String getTilesDefinitionName() {
		return this.tilesDefinitionName;
	}

	/**
	 * Tiles body attribute name. 
	 */
	public void setTilesBodyAttributeName(String tilesBodyAttributeName) {
		this.tilesBodyAttributeName = tilesBodyAttributeName;
	}
	
	public String getTilesBodyAttributeName() {
		return this.tilesBodyAttributeName;
	}

	/**
	 * Sets Tiles definition delimiter.  
	 */
	public void setTilesDefinitionDelimiter(String tilesDefinitionDelimiter) {
		this.tilesDefinitionDelimiter = tilesDefinitionDelimiter;
	}
	
	public String getTilesDefinitionDelimiter() {
		return this.tilesDefinitionDelimiter;
	}
	
	/**
	 * Does everything the <code>UrlBasedViewResolver</code> does and 
	 * also sets some Tiles specific values on the view.
	 * 
	 * @param viewName the name of the view to build
	 * @return the View instance
	 * @throws Exception if the view couldn't be resolved
	 * @see #loadView(String, java.util.Locale)
	 */
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		
		AbstractUrlBasedView view = super.buildView(viewName);
		
		// if DynamicTilesView, set tiles specific values
		if (view instanceof FwkDynamicTilesView) {
			FwkDynamicTilesView dtv = (FwkDynamicTilesView)view;
			
			if (StringUtils.hasLength(tilesDefinitionName)) {
				dtv.setTilesDefinitionName(tilesDefinitionName);
			}
			
			if (StringUtils.hasLength(tilesBodyAttributeName)) {
				dtv.setTilesBodyAttributeName(tilesBodyAttributeName);
			}

			if (tilesDefinitionDelimiter != null) {
				dtv.setTilesDefinitionDelimiter(tilesDefinitionDelimiter);
			}
			
		}
		
		return view;
	}

}
