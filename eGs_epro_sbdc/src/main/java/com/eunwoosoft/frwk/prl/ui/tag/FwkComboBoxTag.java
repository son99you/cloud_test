/*
 * <pre>
 * Copyright (c) 2014 KOICA.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 *
 * </pre>
 */
package com.eunwoosoft.frwk.prl.ui.tag; 

import java.util.Iterator;

import java.util.Map;

import com.eunwoosoft.frwk.fol.util.FwkStringUtil;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public final class FwkComboBoxTag extends RequestContextAwareTag {

//	private static final long serialVersionUID = 6581523124811594739L;

	private String id			= "";
	private String name			= "";
	private String width 		= "";
	private String height 		= "";
	private String cssClass 	= "";
	private String headerKey 	= "";
	private String headerValue 	= "";
	private String selectKey 	= "";
	
	
	private String list 		= "";


	@Override
	protected int doStartTagInternal() throws Exception {

		StringBuffer sb = new StringBuffer();		
		
		if( FwkStringUtil.isNull((String) this.id) ){
			sb.append("<select  name=\"" + this.name + "\" ");			
		}else{
			sb.append("<select id=\"" + this.id + "\" name=\"" + this.name + "\" ");
		}
		
		sb.append("style=\"");
		if(FwkStringUtil.isNull(this.width) == false) {
			sb.append("width :" + this.width + "px;");
		}
		if(FwkStringUtil.isNull(this.height) == false) {
			sb.append("height:" + this.height + "px;");
		}
		sb.append("\"");		
		if(FwkStringUtil.isNull(this.cssClass) == false) {
			sb.append(" class =\"" + this.cssClass + "\"");
		}
		sb.append(">");		
		sb.append(createOption());		
		sb.append("</select>");
		pageContext.getOut().write(sb.toString());

		return SKIP_BODY;
	}

	private String createOption() {

		StringBuffer sb = new StringBuffer();

		Gson gson = new Gson();		
		@SuppressWarnings("unchecked")
		Map<String, String> map = gson.fromJson(list, Map.class);
		Iterator<String> iter = map.keySet().iterator();

		if(FwkStringUtil.isNull(this.headerValue) == false) {
			sb.append("<option value=\"" + this.headerKey + "\">" + this.headerValue + "</option>");
		}

		String isSelected = "";
		while(iter.hasNext()) {
			String key = iter.next();

			if(selectKey.equalsIgnoreCase(key)) {
				isSelected = "selected";
			} else {
				isSelected = "";
			}

			sb.append("<option value=\"" + key + "\" " + isSelected + ">" + map.get(key) + "</option>");
		}

		return sb.toString();

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getHeaderKey() {
		return headerKey;
	}

	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public String getSelectKey() {
		return selectKey;
	}

	public void setSelectKey(String selectKey) {
		this.selectKey = selectKey;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

}
