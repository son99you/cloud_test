package com.eunwoosoft.comm.code.tag; 

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.google.gson.Gson;

/**
 * <pre>
 * com.eunwoosoft.comm.code.tag 
 *    |_ ComCdValueComboBoxTag.java
 * 
 * </pre>
 * @date : 2015. 07. 02. 오전 11:21:21
 * @version : 1.0
 * @author : 은우소프트 김봉수
 */
public class ComCdValueComboBoxTag extends RequestContextAwareTag {
	
	private static final long serialVersionUID = -6831965403914199924L;

    private String id = "";
    private String name = "";
	private String selectKey = "";	
	private String list = "";
	private String width = "";
	private String height = "";
	private String display = "";
	private String className = "";
	private String headerKey = "";
	private String headerValue = "";
	private String title = "";
	
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

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		
		StringBuffer sb = new StringBuffer();		

		if( FwkStringUtil.isNull((String) this.id) ){
			sb.append("<select name=\"" + this.name + "\" title=\""+this.title+"\" class=\""+this.className+"\" style=\"width:" + this.width + "px; height:" + this.height + "px; display:"+this.display+"; \">");
		}else{
			sb.append("<select id=\"" + this.id + "\" name=\"" + this.name + "\" title=\""+this.title+"\" class=\""+this.className+"\" style=\"width:" + this.width + "px;height:" + this.height + "px; display:"+this.display+";\">");
		}
		
		sb.append(createOption());		
		sb.append("</select>");

//		sb.append(createCssplugins());

		pageContext.getOut().write(sb.toString());

		return SKIP_BODY;
	}
	
	private String createOption() throws IOException {
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
			if(this.selectKey.equalsIgnoreCase(key)) {
				isSelected = "selected=\"selected\"";
			} else {
				isSelected = "";
			}
			
			sb.append("<option value=\"" + key+ "\" " + isSelected + ">" + map.get(key) + "</option>");
		}
		
		return sb.toString();
	}
}
