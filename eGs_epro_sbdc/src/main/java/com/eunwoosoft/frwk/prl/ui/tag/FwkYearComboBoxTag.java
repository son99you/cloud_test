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

import com.eunwoosoft.frwk.fol.util.FwkStringUtil;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import egovframework.rte.fdl.string.EgovDateUtil;

@SuppressWarnings("serial")
public final class FwkYearComboBoxTag extends RequestContextAwareTag {

//	private static final long serialVersionUID = 6581523124811594739L;

	private String id="";
	private String name="";
	private String width = "";
	private String height = "";
	private String cssClass 	= "";
	private String headerKey = "";
	private String headerValue = "";
	private int selectKey = 0;
	private String sortGubun = "";
	private int startYear = 0;
	private int upYearCnt = 0;



	public void setId(String id) {
		this.id = id;
	}

	public void setWidth(String width) {
		this.width = width;
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
	
	public void setName(String name) {
		this.name = name;
	}

	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public void setSelectKey(int selectKey) {
		this.selectKey = selectKey;
	}

	public void setSortGubun(String sortGubun) {
		this.sortGubun = sortGubun;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public void setUpYearCnt(int upYearCnt) {
		this.upYearCnt = upYearCnt;
	}

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
//		sb.append(createCssplugins());
		pageContext.getOut().write(sb.toString());

		return SKIP_BODY;
	}

	private String createOption() {

		StringBuffer sb = new StringBuffer();

		if(FwkStringUtil.isNull(this.headerValue) == false) {
			sb.append("<option value=\"" + this.headerKey + "\">" + this.headerValue + "</option>");
		}
		String isSelected = "";	
		int endYear = EgovDateUtil.getCurrentYearAsInt() + this.upYearCnt;

		if("D".equalsIgnoreCase(sortGubun)){

			for (int i = endYear; i >= this.startYear; i--) {
				if(this.selectKey == i) {
					isSelected = "selected";
				} else {
					isSelected = "";
				}

				sb.append("<option value=\"" + i + "\" " + isSelected + ">" + i + "</option>");
			}

		}else{

			for (int i = this.startYear; i <= endYear; i++) {
				if(this.selectKey == i) {
					isSelected = "selected";
				} else {
					isSelected = "";
				}

				sb.append("<option value=\"" + i + "\" " + isSelected + ">" + i + "</option>");
			}
		}

		return sb.toString();

	}

	private String createCssplugins(){

		StringBuffer sb = new StringBuffer();	

		sb.append("<style><!--");
		sb.append(".ddcommon {position:relative; display:-moz-inline-stack; zoom:1; display:inline-block; cursor:pointer;} 																															\r\n");
		sb.append(".ddcommon ul{padding:0;margin:0;}																																																					\r\n");
		sb.append(".ddcommon ul li{list-style-type:none;}																																																					\r\n");
		sb.append(".ddcommon .disabled img, .ddcommon .disabled span, .ddcommon.disabledAll{																																								\r\n");
		sb.append("opacity: .5; /* standard: ff gt 1.5, opera, safari */																																																\r\n");
		sb.append("-ms-filter:\"alpha(opacity=50)\"; /* ie 8 */																																																			\r\n");
		sb.append("filter:alpha(opacity=50); /* ie lt 7 */																																																					\r\n");
		sb.append("-khtml-opacity:.5; /* safari 1.x */																																																						\r\n");
		sb.append("-moz-opacity:.5; /* ff lt 1.5, netscape */																																																				\r\n");
		sb.append("color:#999999;																																																												\r\n");
		sb.append("}																																																																	\r\n");
		sb.append(".ddcommon .clear{clear:both}																																																							\r\n");
		sb.append(".ddcommon .divider{width:0; height:100%; position:absolute;}																																													\r\n");
		sb.append(".ddcommon .ddArrow{display:inline-block; position:absolute; top:50%; right:4px;}																																						\r\n");
		sb.append(".ddcommon .ddTitle .ddTitleText{display:block;}																																																	\r\n");
		sb.append(".ddcommon .ddChild{position:absolute;display:none;width:100%;overflow-y:auto; overflow-x:hidden; zoom:1; z-index:9999}																								\r\n");
		sb.append(".dd{border:1px solid #c3c3c3;}																																																							\r\n");
		sb.append(".dd .divider{border-left:1px solid #c3c3c3; border-right:1px solid #fff;; right:24px;}																																						\r\n");
		sb.append(".dd .ddArrow{width:16px;height:16px; margin-top:-8px; background:url(" + super.getRequestContext().getContextPath() + "/statics/images/select_down_arrow.gif) no-repeat;}						\r\n");
		sb.append(".dd .ddTitle{  height: 26px;text-indent: 11px;background: none repeat scroll 0 0 transparent;color: #666;left: 0;overflow: visible;position: relative;text-align: left;top: 0;z-index: 2;}					\r\n");
		sb.append(".dd .ddTitle:hover , .dd .ddTitle:focus{background-color:#ecf8ff}																																												\r\n");
		sb.append(".dd .ddTitle .ddTitleText{padding:5px 20px 5px 5px;}																																																\r\n");
		sb.append(".dd .ddTitle .ddTitleText .ddTitleText{padding:0;}																																																	\r\n");
		sb.append(".dd .ddChild{border:1px solid #c3c3c3; background-color:#f5f5f5; left:-1px;}																																								\r\n");
		sb.append(".dd .ddChild li{padding:5px; background-color:#f5f5f5; text-indent: 11px;}																																									\r\n");
		sb.append(".dd .ddChild li.hover{background-color:#e2e2e2}																																																	\r\n");
		sb.append("--></style>																																																														\r\n");
		sb.append("<script type=\"text/javascript\" src=\"" + super.getRequestContext().getContextPath() + "/statics/libs/jquery.select.min.js\"></script>																				\r\n");
		sb.append("<script type=\"text/javascript\">																																																							\r\n");
		sb.append("$(document).ready(function(){																																																							\r\n");
		sb.append("$(\"#" + this.id + "\").msDropdown({roundedCorner: \"false\"});																																												\r\n");
		sb.append("});																																																																	\r\n");
		sb.append("</script>																																																														\r\n");

		return sb.toString();
	}

}
