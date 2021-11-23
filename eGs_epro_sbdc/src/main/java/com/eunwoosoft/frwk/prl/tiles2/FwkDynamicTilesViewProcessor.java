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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.TilesException;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.JstlUtils;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.util.WebUtils;

import com.eunwoosoft.frwk.fol.util.FwkStringUtil;


/**
 * 클래스 설명
 * 
 * @author : 
 * @date : 2014. 11. 14.
 * @version : 1.0
 */
public class FwkDynamicTilesViewProcessor {
	
private String derivedDefinitionName = null; 
	
	private String tilesDefinitionName = "mainTemplate";
	private String tilesBodyAttributeName = "body";
	private String tilesDefinitionDelimiter = ".";
	
	
	public void setTilesDefinitionName(String tilesDefinitionName) {
		this.tilesDefinitionName = tilesDefinitionName;
	}
	
	public void setTilesBodyAttributeName(String tilesBodyAttributeName) {
		this.tilesBodyAttributeName = tilesBodyAttributeName;
	}
	
	public void setTilesDefinitionDelimiter(String tilesDefinitionDelimiter) {
		this.tilesDefinitionDelimiter = tilesDefinitionDelimiter;
	}
	
	protected void renderMergedOutputModel(String beanName, String url,
            ServletContext servletContext,
            HttpServletRequest request, HttpServletResponse response,
            TilesContainer container)  {

		JstlUtils.exposeLocalizationContext(new RequestContext(request, servletContext));

		if (!response.isCommitted() && checkVersion(servletContext)) {

			
				WebUtils.exposeForwardRequestAttributes(request);

		}
		
		String definitionName = startDynamicDefinition(beanName, url, request, response, container);
		/*
		 * 2018-03-22 : 맹경열
		 * iframe의 tiles layout 변경 위한 임시 변경 
		 */
		if(request.getRequestURI().indexOf(".iframe") > -1) {
			definitionName = ".iframeLayout";
		}		
//		System.out.println("++++++++++++++++++++++++ spring in renderMergedOutputModel definitionName " + definitionName);
//		System.out.println("++++++++++++++++++++++++ spring in renderMergedOutputModel request " + request.toString());
//		System.out.println("++++++++++++++++++++++++ spring in renderMergedOutputModel response " + response.toString());
		container.render(definitionName, request, response);        
//		System.out.println("++++++++++++++++++++++++ spring in renderMergedOutputModel 20 ");
		endDynamicDefinition(definitionName, beanName, request, response, container);
//		System.out.println("++++++++++++++++++++++++ spring in renderMergedOutputModel 20 ");
	}
	
	private static boolean checkVersion(ServletContext servletContext) {
		
		return servletContext.getMajorVersion() == 2 && servletContext.getMinorVersion() < 5;
		
	}
	
	/**
	 * Starts processing the dynamic Tiles definition by creating a temporary definition for rendering.
	 */
	protected String startDynamicDefinition(String beanName, String url,
											HttpServletRequest request, HttpServletResponse response,
                                            TilesContainer container) throws TilesException {
       
		String definitionName = processTilesDefinitionName(beanName, container, request, response);

		//create a temporary context and render using the incoming url as the
		// body attribute
		if (!definitionName.equals(beanName)) {
			
			Attribute attr = new Attribute();

			attr.setValue(url);

			AttributeContext attributeContext = container.startContext(request, response);
			attributeContext.putAttribute(tilesBodyAttributeName, attr);
		}
        
		return definitionName;
	}

	/**
	 * Closes the temporary Tiles definition.
	 */
	protected void endDynamicDefinition(String definitionName, String beanName, 
										HttpServletRequest request, HttpServletResponse response,
										TilesContainer container) {
		if (!definitionName.equals(beanName)) {
			container.endContext(request, response);
		}	    
	}
	   
	/**
	 * Processes values to get tiles template definition name.  First 
	 * a Tiles definition matching the url is checked, then a 
	 * url specific template is checked, and then just the 
	 * default root definition is used.
	 * 
	 * @throws 	TilesException		If no valid Tiles definition is found. 
	 */
	private String processTilesDefinitionName(String beanName,
												TilesContainer container, 
												HttpServletRequest request, 
												HttpServletResponse response) throws TilesException {
		
		if (container.isValidDefinition(beanName, request, response)) {
			derivedDefinitionName = beanName;
			return derivedDefinitionName;
		} else {
			derivedDefinitionName = replaceDefinitionName(beanName, container, request, response);
			return derivedDefinitionName;
		}
		
	}
	
	private String replaceDefinitionName(String beanName, TilesContainer container, HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		StringBuilder sb = new StringBuilder();
		
		
		
		int lastIndex =FwkStringUtil.nvl(beanName).lastIndexOf("/");
		boolean rootDefinition = false;
		
		// if delim, tiles def will start with it
		if (StringUtils.hasLength(tilesDefinitionDelimiter)) {
			sb.append(tilesDefinitionDelimiter);
		}
		
		// if no '/', then at context root
		if (lastIndex == -1) {
			rootDefinition = true;
		} else {
			String path = FwkStringUtil.nvl(beanName).substring(0, lastIndex);
			
			if (StringUtils.hasLength(tilesDefinitionDelimiter)) {
				path = StringUtils.replace(path, "/", tilesDefinitionDelimiter);
				
			}
			
			sb.append(path);
			
			if (StringUtils.hasLength(tilesDefinitionDelimiter)) {
				sb.append(tilesDefinitionDelimiter);
			}
		}
		
		sb.append(tilesDefinitionName);
		
		if (container.isValidDefinition(sb.toString(), request, response)) {
			result = sb.toString();
		} else if (!rootDefinition) {
			String root = null;
			
			if (StringUtils.hasLength(tilesDefinitionDelimiter)) {
				root = tilesDefinitionDelimiter;
			}
			
			root += tilesDefinitionName;

			if (container.isValidDefinition(root, request, response)) {
				result = root;
			} else {
				throw new TilesException("No defintion of found for " +
						"'" + root +"'" +
						" or '" + sb.toString() +"'");
			}
		}
		return result;
	}

}
