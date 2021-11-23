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

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eunwoosoft.frwk.fol.exception.FwkResourceNotFoundException;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.servlet.context.ServletUtil;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

/**
 * 클래스 설명
 * 
 * @author : 
 * @date : 2014. 11. 14.
 * @version : 1.0
 */
public class FwkDynamicTilesView extends AbstractUrlBasedView {
	
	private final FwkDynamicTilesViewProcessor dynamicTilesViewProcessor = new FwkDynamicTilesViewProcessor();
	
	/**
	 * Main template name.  The default is 'mainTemplate'.
	 * 
	 * @param 	tilesDefinitionName		Main template name used to lookup definitions.
	 */
	public void setTilesDefinitionName(String tilesDefinitionName) {
	    dynamicTilesViewProcessor.setTilesDefinitionName(tilesDefinitionName);
	}

	/**
	 * Tiles body attribute name.  The default is 'body'.
	 * 
	 * @param 	tilesBodyAttributeName		Tiles body attribute name.
	 */
	public void setTilesBodyAttributeName(String tilesBodyAttributeName) {
	    dynamicTilesViewProcessor.setTilesBodyAttributeName(tilesBodyAttributeName);
	}

	/**
	 * Sets Tiles definition delimiter.  For example, instead of using 
	 * the request 'info/about' to lookup the template definition 
	 * 'info/mainTemplate', the default delimiter of '.' 
	 * would look for '.info.mainTemplate' 
	 * 
	 * @param 	tilesDefinitionDelimiter	Optional delimiter to replace '/' in a url.
	 */
	public void setTilesDefinitionDelimiter(String tilesDefinitionDelimiter) {
	    dynamicTilesViewProcessor.setTilesDefinitionDelimiter(tilesDefinitionDelimiter);
	}

	/**
	 * Renders output using Tiles.
	 * @throws Exception 
	 */
	protected void renderMergedOutputModel(Map<String, Object> model,
	                                       HttpServletRequest request, HttpServletResponse response) throws Exception {

        ServletContext servletContext = getServletContext();
        TilesContainer container = ServletUtil.getContainer(servletContext);
        if (container == null) {
            throw new ServletException("Tiles container is not initialized. " + 
                                       "Have you added a TilesConfigurer to your web application context?");
        }
		
		exposeModelAsRequestAttributes(model, request);
		
		
		try {
			dynamicTilesViewProcessor.renderMergedOutputModel(getBeanName(), getUrl(), 
	                servletContext, request, response, container);
		} catch(Exception e) {
			throw new FwkResourceNotFoundException("");

		}        
    }
    

}
