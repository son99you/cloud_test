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
 *    |_ ComCdValueRadioTag.java
 * 
 * </pre>
 * @date : 2015. 07. 02. 오전 11:21:21
 * @version : 1.0
 * @author : 은우소프트 김봉수
 */
public class ComCdValueRadioTag extends RequestContextAwareTag {
	
	private static final long serialVersionUID = -6831965403914199924L;

    private String id = "";
    private String name = "";
	private String selectKey = "";	
	private String list = "";
	private String width = "";
	private String height = "";
	private String display = "";
	private String className = "";
	
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

	@Override
	protected int doStartTagInternal() throws Exception {
		
		StringBuffer sb = new StringBuffer();		
		sb.append(createOption());		
		pageContext.getOut().write(sb.toString());
		
		return SKIP_BODY;
	}
	
	private String createOption() throws IOException {
		StringBuffer sb = new StringBuffer();
		
		Gson gson = new Gson();		
		@SuppressWarnings("unchecked")
		Map<String, String> map = gson.fromJson(list, Map.class);
		Iterator<String> iter = map.keySet().iterator();
		
		int idx = 1;
		String isChecked = "";
		while(iter.hasNext()) {
			String key = iter.next();
			if(selectKey.equalsIgnoreCase(key)) {
				isChecked = "checked=\"checked\"";
			} else {
				isChecked = "";
			}

			String iDName = "";
			if( FwkStringUtil.isNull(this.id) == false ){
				iDName = this.id;
			}else{
				iDName = this.name + "_" + idx;
			}

			sb.append("<label for=\"" + iDName + "\" class=\"component-radio\">");
			sb.append("<input type=\"radio\" name=\"" + this.name + "\" id=\"" + iDName + "\" value=\"" + key + "\" " + isChecked + ">");
			sb.append("<i></i>");
			sb.append("<span class=\"txt-radio\">" + map.get(key) + "</span>");
			sb.append("</label>");
			
			idx++;
		}
		return sb.toString();
	}
}
