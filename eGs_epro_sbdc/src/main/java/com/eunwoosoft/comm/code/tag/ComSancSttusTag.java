package com.eunwoosoft.comm.code.tag; 

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.eunwoosoft.comm.code.service.ComSancSttusService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * <pre>
 * com.eunwoosoft.comm.code.tag 
 *    |_ ComSancSttusTag.java
 * 
 * </pre>
 * @date : 2014. 12. 10. 오후 3:02:21
 * @version : 1.0
 * @author : 
 */
@SuppressWarnings("serial")
public class ComSancSttusTag extends RequestContextAwareTag {
	private ComSancSttusService comSancSttusService;

	private String docId = "";
	private String gb = "";
	private String sttusNm = "";
	private String sttusCd = "";


	@Override
	protected int doStartTagInternal() throws Exception {

		StringBuffer sb = new StringBuffer();
		int chk = 0;
		if(!FwkStringUtil.isNull(this.docId)){
			
			// A : 구매요구, BA : 입찰공고, BI : 입찰시담, CA : 계약, CD : 계약통보, DA : 변경계약, DD : 변경계약통보, E : 검수, F : 실적증명
			/**
			 *  화면별 상태값
			 *  A - 0	구매접수대기
					1	상신전
					2	결재상신  +
					3	결재완료
					4	결재반려
					8	구매접수
					9	구매반려
				BA - 0	등록완료
					A	결재요청  +
					B	결재완료
					C	결재반려
					Z	등록대기
				BI - H	시담등록완료
					I	시담결재요청  +
					K	시담결재반려
					G	시담등록대기
				C - A	계약작성
					B	결재상신  +
					C	결재반려
					D	알림대기
					F	계약해지
					G	알림완료
					Z	계약대기
				D - A	계약작성
					B	결재상신  +
					C	결재반려
					D	알림대기
					F	계약해지
					G	알림완료
				E - 0 - 검수중
					1 - 결재요청
					2 - 부분검수
					Z - 미검수
				F - 0	작성
					1	업체신청
					2	접수
					3	접수반려
					4	결재요청  +
					5	결재완료
					6	결재반려
					7	발급완료
			 */
			if("A".equals(gb)){
				if("2".equals(sttusCd)){
					if(!sttusNm()){
						sb.append("상신전");
						chk = 1;
					}
				}
			}else if("BA".equals(gb)){
				if("A".equals(sttusCd)){
					if(!sttusNm()){
						sb.append("등록완료");
						chk = 1;
					}
				}
			}else if("BI".equals(gb)){
				if("I".equals(sttusCd)){
					if(!sttusNm()){
						sb.append("시담등록완료");
						chk = 1;
					}
				}
			}else if("CA".equals(gb)){
				if("B".equals(sttusCd)){
					if(!sttusNm()){
						sb.append("계약작성");
						chk = 1;
					}
				}
			}else if("CD".equals(gb)){
				if("G".equals(sttusCd)){
					if(!sttusNm()){
						sb.append("알림대기");
						chk = 1;
					}
				}
			}else if("DA".equals(gb)){
				if("B".equals(sttusCd)){
					if(!sttusNm()){
						sb.append("계약작성");
						chk = 1;
					}
				}
			}else if("DD".equals(gb)){
				if("G".equals(sttusCd)){
					if(!sttusNm()){
						sb.append("알림대기");
						chk = 1;
					}
				}
			}else if("E".equals(gb)){
				if("1".equals(sttusCd)){
					if(!sttusNm()){
						sb.append("검수중");
						chk = 1;
					}
				}
			}else if("F".equals(gb)){
				if("4".equals(sttusCd)){
					if(!sttusNm()){
						sb.append("접수");
						chk = 1;
					}
				}
			}
		}
		
		if(chk == 0){
			sb.append(sttusNm);
		}
		
		pageContext.getOut().write(sb.toString());

		return SKIP_BODY;
	}
	
	private boolean sttusNm(){
		boolean flag = true;
		
		comSancSttusService = getRequestContext().getWebApplicationContext().getBean(ComSancSttusService.class);
		
		//StringBuffer sb = new StringBuffer();
		
		FwkParameterMap parameterMap = new FwkParameterMap();
		parameterMap.put("P_SANCTN_DOC_ID", docId);
		FwkTransferObject trans = comSancSttusService.sancSttusInfo(parameterMap);
		if(trans != null){
			FwkDataEntity info = (FwkDataEntity) trans.get("sancInfo");
			if(info != null){
				if(info.get("GW_APP_STAT_CD") == null || "".equals(info.get("GW_APP_STAT_CD"))){
					flag = false;
				}
			}
		}
		
		return flag;
	}

	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getGb() {
		return gb;
	}
	public void setGb(String gb) {
		this.gb = gb;
	}
	public String getSttusNm() {
		return sttusNm;
	}
	public void setSttusNm(String sttusNm) {
		this.sttusNm = sttusNm;
	}
	public String getSttusCd() {
		return sttusCd;
	}
	public void setSttusCd(String sttusCd) {
		this.sttusCd = sttusCd;
	}
	
}
