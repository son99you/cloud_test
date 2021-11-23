package com.eunwoosoft.comm.code.tag; 

import java.io.IOException;
import java.util.List;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.eunwoosoft.comm.code.service.ComCmcdDetailCdInqireService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * <pre>
 * com.eunwoosoft.comm.code.tag 
 *    |_ ComCmcdCdValueComboBoxTag.java
 * 
 * </pre>
 * @date : 2014. 12. 10. 오후 3:02:21
 * @version : 1.0
 * @author : 
 */
@SuppressWarnings("serial")
public class ComTableValueComboBoxTag extends RequestContextAwareTag {

//	private static final long serialVersionUID = -1507311605981088600L;


	private ComCmcdDetailCdInqireService comCmcdDetailCdInqireService;

	private String id = "";
	private String name = "";
	private String width = "";
	private String height = "";
	private String display = "";
	private String headerKey = "";
	private String headerValue = "";
	private String selectKey = "";

	private String tableId = "";		//테이블명
	private String columnId = "";		//컬럼명
	private String useAt = "";		//사용여부
	private String dateAt = "";		//코드 시작~종료일 체크 여부 (Y : 현재일자 에 유효한 코드만 조회)

	private String whereId1 = "";
	private String whereVal1 = "";
	
	private String cond1 = "";
	private String cond2 = "";
	private String cond3 = "";
	private String cond4 = "";
	private String cond5 = "";

	private String title = "";
	
	private String className = "";
	@Override
	protected int doStartTagInternal() throws Exception {

		StringBuffer sb = new StringBuffer();		

		if( FwkStringUtil.isNull((String) this.id) ){
			sb.append("<select name=\"" + this.name + "\" title=\""+this.title+"\" class=\""+this.className+"\" style=\"width:" + this.width + "px; height:" + this.height + "px; display:"+this.display+"; \">");
		}else{
			sb.append("<select id=\"" + this.id + "\" name=\"" + this.name + "\" title=\""+this.title+"\" class=\""+this.className+"\" style=\"width:" + this.width + "px;height:" + this.height + "px; display:"+this.display+";\">");
		}
		
		sb.append(createOption(cdValueListInqire()));		
		sb.append("</select>");

//		sb.append(createCssplugins());

		pageContext.getOut().write(sb.toString());

		return SKIP_BODY;
	}

	private List<FwkDataEntity> cdValueListInqire() throws Exception {
		comCmcdDetailCdInqireService = getRequestContext().getWebApplicationContext().getBean(ComCmcdDetailCdInqireService.class);
		FwkParameterMap parameterMap = new FwkParameterMap();
		parameterMap.put("P_TABLE_ID", tableId);
		parameterMap.put("P_COLUMN_ID", columnId);
		

		if(!FwkStringUtil.isNull(this.useAt)){
			parameterMap.put("P_USE_AT", this.useAt);
		}
		
		if(!FwkStringUtil.isNull(this.dateAt)){
			parameterMap.put("P_DATE_AT", this.dateAt);
		}
		
		if(!FwkStringUtil.isNull(this.cond1)){
			parameterMap.put("P_ETC_VAL1", this.cond1);
		}

		if(!FwkStringUtil.isNull(this.cond2)){
			parameterMap.put("P_ETC_VAL2", this.cond2);
		}

		if(!FwkStringUtil.isNull(this.cond3)){
			parameterMap.put("P_ETC_VAL3", this.cond3);
		}
		
		if(!FwkStringUtil.isNull(this.cond4)){
			parameterMap.put("P_ETC_VAL4", this.cond4);
		}
		
		if(!FwkStringUtil.isNull(this.cond5)){
			parameterMap.put("P_ETC_VAL5", this.cond5);
		}

		if(!FwkStringUtil.isNull(this.cond4)){
			parameterMap.put("P_USE_CD_FOUR_VALUE", this.cond4);
		}

		if(!FwkStringUtil.isNull(this.cond5)){
			parameterMap.put("P_USE_CD_FIVE_VALUE", this.cond5);
		}
		
		if(!FwkStringUtil.isNull(this.whereId1)){
			parameterMap.put("P_WHERE_ID_1", this.whereId1);
		}
		
		if(!FwkStringUtil.isNull(this.whereVal1)){
			parameterMap.put("P_WHERE_VAL_1", this.whereVal1);
		}

		//T_ESTM_SPHE_MST
//		if("T_ESTM_SPHE_MST".equals(parameterMap.get("P_CD_ID"))) {
		
			//ESTM_SPHE_SE
			parameterMap.put("P_COLUMN_NM", parameterMap.get("P_COLUMN_ID")+ "NM");
			parameterMap.put("P_COLUMN_ID", parameterMap.get("P_COLUMN_ID")+ "CD");
		
			FwkTransferObject trans = comCmcdDetailCdInqireService.cdTableValueListInqireByCdId(parameterMap);
			@SuppressWarnings({ "unchecked", "static-access" })
			List<FwkDataEntity> cdValueList = (List<FwkDataEntity>) trans.get(comCmcdDetailCdInqireService.CD_VALUE_LIST);		
			return cdValueList;
//		}else {
//			FwkTransferObject trans = comCmcdDetailCdInqireService.cdValueListInqireByCdId(parameterMap);
//			@SuppressWarnings({ "unchecked", "static-access" })
//			List<FwkDataEntity> cdValueList = (List<FwkDataEntity>) trans.get(comCmcdDetailCdInqireService.CD_VALUE_LIST);		
//			return cdValueList;
//		}
		
		
		
		
				
	}

	private String createOption(final List<FwkDataEntity> cdValuedList) throws IOException {
		StringBuffer sb = new StringBuffer();		
		if(FwkStringUtil.isNull(this.headerValue) == false) {
			sb.append("<option value=\"" + this.headerKey + "\">" + this.headerValue + "</option>");
		}
		String isSelected = "";		
		for(FwkDataEntity dataEntity : cdValuedList) {

			if(dataEntity.getTrimString("CD_VALUE").equalsIgnoreCase(this.selectKey)) {
				isSelected = "selected";
			} else {
				isSelected = "";
			}

			sb.append("<option value=\"" + dataEntity.getTrimString("CD_VALUE")+ "\" " + isSelected + ">" + dataEntity.getTrimString("CD_VALUE_NM") + "</option>");
		}		
		return sb.toString();
	}

	private String createCssplugins(){

		StringBuffer sb = new StringBuffer();	

		sb.append("<style><!--");
		sb.append(".ddcommon {position:relative; display:-moz-inline-stack; zoom:1; display:inline-block; cursor:pointer; height:26px;} 																															\r\n");
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
		sb.append("$(\"#" + this.id + "\").msDropdown();																																												\r\n");
		sb.append("});																																																																	\r\n");
		sb.append("</script>																																																														\r\n");
		
		return sb.toString();
	}

	public ComCmcdDetailCdInqireService getComCmcdDetailCdInqireService() {
		return comCmcdDetailCdInqireService;
	}

	public void setComCmcdDetailCdInqireService(
			ComCmcdDetailCdInqireService comCmcdDetailCdInqireService) {
		this.comCmcdDetailCdInqireService = comCmcdDetailCdInqireService;
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

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	
	public String getDateAt() {
		return dateAt;
	}
	
	public void setDateAt(String dateAt) {
		this.dateAt = dateAt;
	}

	public String getCond1() {
		return cond1;
	}

	public void setCond1(String cond1) {
		this.cond1 = cond1;
	}

	public String getCond2() {
		return cond2;
	}

	public void setCond2(String cond2) {
		this.cond2 = cond2;
	}

	public String getCond3() {
		return cond3;
	}

	public void setCond3(String cond3) {
		this.cond3 = cond3;
	}

	public String getCond4() {
		return cond4;
	}

	public void setCond4(String cond4) {
		this.cond4 = cond4;
	}

	public String getCond5() {
		return cond5;
	}

	public void setCond5(String cond5) {
		this.cond5 = cond5;
	}

	public String getDisplay() {
		return display;
	}

	public String getWhereId1() {
		return whereId1;
	}

	public void setWhereId1(String whereId1) {
		this.whereId1 = whereId1;
	}

	public String getWhereVal1() {
		return whereVal1;
	}

	public void setWhereVal1(String whereVal1) {
		this.whereVal1 = whereVal1;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
	
}
