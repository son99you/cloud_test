package com.eunwoosoft.comm.code.tag; 

import java.io.IOException;

import java.util.List;

import com.eunwoosoft.comm.code.service.ComCmcdDetailCdInqireService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * <pre>
 * com.eunwoosoft.comm.code.tag 
 *    |_ ComCmcdCdValueRadioTag.java
 * 
 * </pre>
 * @date : 2014. 12. 10. 오후 3:02:21
 * @version : 1.0
 * @author : 
 */
@SuppressWarnings("serial")
public class ComCmcdCdValueRadioTag extends RequestContextAwareTag {

	private ComCmcdDetailCdInqireService comCmcdDetailCdInqireService;

	private String id = "";
	private String name = "";
	private String selectKey = "";	
	private String cdId = "";	
	private String css = "";

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

	public String getCdId() {
		return cdId;
	}

	public String getSelectKey() {
		return selectKey;
	}

	public void setCdId(String cdId) {
		this.cdId = cdId;
	}

	public void setSelectKey(String selectKey) {		
		this.selectKey = selectKey;
	}	
	
	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	@Override
	protected int doStartTagInternal() throws Exception {

		StringBuffer sb = new StringBuffer();		
		sb.append(createOption(cdValueListInqire()));		
		pageContext.getOut().write(sb.toString());

		return SKIP_BODY;
	}

	private List<FwkDataEntity> cdValueListInqire() {		
		comCmcdDetailCdInqireService = getRequestContext().getWebApplicationContext().getBean(ComCmcdDetailCdInqireService.class);
		FwkParameterMap parameterMap = new FwkParameterMap();
		parameterMap.put("P_CD_ID", cdId);
		FwkTransferObject trans = comCmcdDetailCdInqireService.cdValueListInqireByCdId(parameterMap);
		@SuppressWarnings({ "unchecked", "static-access" })
		List<FwkDataEntity> cdValueList = (List<FwkDataEntity>) trans.get(comCmcdDetailCdInqireService.CD_VALUE_LIST);		
		return cdValueList;		
	}

	private String createOption(final List<FwkDataEntity> cdValuedList) throws IOException {
		StringBuffer sb = new StringBuffer();

		String isChecked = "";

		int idx = 1;

		for(FwkDataEntity dataEntity : cdValuedList) {

			if(dataEntity.getTrimString("CD_VALUE").equalsIgnoreCase(this.selectKey)) {
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

			if("opro".equals(this.css)){
				sb.append("<input type=\"radio\" name=\"" + this.name + "\" id=\"" + iDName + "\" value=\"" + dataEntity.getTrimString("CD_VALUE") + "\" " + isChecked + " class=\"mr_5\" />");
				sb.append("<label for=\"" + iDName + "\" class=\"mr_10\">" + dataEntity.getTrimString("CD_VALUE_NM") + "</label>");
			}else{
				sb.append("<div class=\"radio_box\">");
				sb.append("<input type=\"radio\" name=\"" + this.name + "\" id=\"" + iDName + "\" value=\"" + dataEntity.getTrimString("CD_VALUE") + "\" " + isChecked + " />");//class=\"mr_10\"
				sb.append("<label for=\"" + iDName + "\" class=\"mr_5\">" + dataEntity.getTrimString("CD_VALUE_NM") + "</label>&nbsp;&nbsp;");
				sb.append("</div>");
			}
			
			idx++;
		}		
		return sb.toString();
	}
}
