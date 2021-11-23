/*
 * <pre>
 * Copyright (c) 2015 KOICA.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 *
 * </pre>
 */
package com.eunwoosoft.comm.code.tag; 

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.eunwoosoft.comm.code.service.ComCmcdLegaldongService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * <pre>
 * oda.com.cmcd.tag 
 *    |_ ComCmcdSidoComboBoxTag.java
 * 
 * </pre>
 * @date : 2015. 2. 17. 오후 3:02:21
 * @version : 1.0
 * @author : 야긴스텍 정윤교
 */
@SuppressWarnings("serial")
public class ComCmcdEmdComboBoxTag extends RequestContextAwareTag {

//	private static final long serialVersionUID = -9202778299779483697L;

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(ComCmcdEmdComboBoxTag.class);

	private ComCmcdLegaldongService comCmcdLegaldongService;

	private String id 					= "";												//combobox id
	private String name 			= "";												//combobox 명
	private String css 				= "";												//combobox css 명
	private String style 				= "";												//combobox style
	private String headerKey 		= "";												//combobox 옵션박스 초기 설정  value값
	private String headerValue 	= "";												//combobox 옵션박스 초기 설정 텍스트값 

	private String selected 		= "";												//combobox selected 될 값
	private String parentSelected= "";												//combobox 시/도의 선택된 값
	private String onchange 		= "";												//combobox 이벤트 발생 시 onchange 함수
	private String oncomplete 		= "";											//combobox 이벤트 발생 시 onchange 함수


	@Override
	protected int doStartTagInternal() throws Exception {

		StringBuffer sb = new StringBuffer();

		String onchange = "";
		if(FwkStringUtil.isNull(this.onchange) == false){
			onchange = "onchange='" + this.onchange + "'";
		}
		
		if( FwkStringUtil.isNull((String) this.id) ){
			sb.append("<select name=\"" + this.name + "\" class=\"" + this.css + "\" style=\"" +  this.style + "\"  " + onchange + " > \r\n");
		}else{
			sb.append("<select id=\"" + this.id + "\" name=\"" + this.name + "\" class=\"" + this.css + "\" style=\"" +  this.style + "\"  " + onchange + " > \r\n");
		}

		sb.append(createOption(cdValueListInqire()));		
		sb.append("</select> \r\n");

//		sb.append(createCssplugins());

		pageContext.getOut().write(sb.toString());

		return SKIP_BODY;
	}

	@SuppressWarnings("unchecked")
	private List<FwkDataEntity> cdValueListInqire(){

		FwkParameterMap parameterMap = new FwkParameterMap();
		FwkTransferObject trans = new FwkTransferObject();
		comCmcdLegaldongService = getRequestContext().getWebApplicationContext().getBean(ComCmcdLegaldongService.class);

		//Db 컬럼의 값에 따른 처리 필요 요망 .... 
		//지역코드가 각 해당값만 가져와서 처리해야한다면 코드값의 분리 잘라서 처리 , 그외 그냥 처리 

		parameterMap.put("P_AREA_NM", this.parentSelected);
		trans = comCmcdLegaldongService.legaldongEmdListInqire(parameterMap);		

		return (List<FwkDataEntity>) trans.get(ComCmcdLegaldongService.AREA_LIST);		
	}


	private String createOption(final List<FwkDataEntity> cdValuedList) throws IOException {

		StringBuffer sb = new StringBuffer();

		if(FwkStringUtil.isNull(this.headerValue) == false){
			sb.append("<option value=\"" + this.headerKey + "\">" + this.headerValue + "</option> \r\n");
		}

		if(cdValuedList != null ){
			String isSelected = "";		
			for(FwkDataEntity dataEntity : cdValuedList) {

				if(dataEntity.getTrimString("AREA_NM").equalsIgnoreCase(this.selected)){
					isSelected = "selected";
				}else{
					isSelected = "";
				}

				sb.append("<option value=\"" + dataEntity.getTrimString("AREA_NM") + "\" " + isSelected + ">" + dataEntity.getTrimString("AREA_NM") + "</option> \r\n");
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

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
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

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getParentSelected() {
		return parentSelected;
	}

	public void setParentSelected(String parentSelected) {
		this.parentSelected = parentSelected;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getOncomplete() {
		return oncomplete;
	}

	public void setOncomplete(String oncomplete) {
		this.oncomplete = oncomplete;
	}

}
