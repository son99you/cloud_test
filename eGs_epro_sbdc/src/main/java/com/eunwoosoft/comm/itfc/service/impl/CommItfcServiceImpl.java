package com.eunwoosoft.comm.itfc.service.impl; 

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.atfi.controller.ComAtfiAtchFileController;
import com.eunwoosoft.comm.itfc.dao.CommItfcDao;
import com.eunwoosoft.comm.itfc.service.CommItfcService;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.comm.util.JsFormUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 인터페이스 통신 관련 구현 클래스
 */
@Service("commItfcService")
public class CommItfcServiceImpl extends AbstractFwkService implements CommItfcService {
	private static final Logger LOG = LoggerFactory.getLogger(ComAtfiAtchFileController.class);
	
	@Resource(name="commItfcDao")
	private CommItfcDao commItfcDao; 

	/**
	 * <pre>
	 * 1. 개요 : 알림톡 테이블 내용 저장 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertTsmsMsgInfo
	 * @date : 2019. 04. 02.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 04. 02.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	@Override
	public FwkTransferObject insertTsmsMsgInfo(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		//FwkDataEntity msgEntity = commItfcDao.selectMsgForm(parameterMap);
		//parameterMap.put("P_MSG_CNTN", msgEntity.get("MSG_CNTN"));
		
		String msg = CmmnUtil.ebidMSGBodyCreate(parameterMap);
		
		LOG.debug("==================== SMS MSG INFO DETAIL START ====================");
		LOG.debug("msg : " + msg);
		LOG.debug("==================== SMS MSG INFO DETAIL END ====================");
		
		parameterMap.put("P_SEND_MESSAGE", msg);			//메시지내용
		parameterMap.put("P_MESSAGE_TYPE", "002");				//메시지타입
		parameterMap.put("P_CONTENTS_TYPE", "004");			//내용타입
		parameterMap.put("P_RECEIVE_MOBILE_NO", parameterMap.get("P_RECEIVE_MOBILE_NO"));	//수신핸드폰번호
		parameterMap.put("P_CALLBACK_NO", "");				//
		parameterMap.put("P_JOB_TYPE", "R00");					//단건 : R00, 다건 : B00
		parameterMap.put("P_SEND_RESERVE_DATE", "");	//예약발송시간 - 초까지 입력 시 DB에서 TO_DATE 입력
		parameterMap.put("P_TEMPLATE_CODE", parameterMap.get("P_TEMPLATE_CODE"));			//등록된 알림톡 템플릿 번호
		parameterMap.put("P_REGISTER_BY", session.get("USR_ID"));				//등록자
		parameterMap.put("P_IMG_ATTACH_FLAG", "N");		//이미지 첨부 플래그
		parameterMap.put("P_KKO_BTN_LINK1", "https://www.keri.re.kr/bid/");		//이미지 첨부 플래그
		if(parameterMap.get("P_RECEIVE_MOBILE_NO") != null && !parameterMap.get("P_RECEIVE_MOBILE_NO").equals("")) {
			commItfcDao.insertTsmsMsgRegist(parameterMap);
		}
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 낙찰정보 전송
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sendSbidInfoToMIS
	 * @date : 2019. 04. 05.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 04. 05.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	@Override
	public FwkTransferObject sendSbidInfoToMIS(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		commItfcDao.sendSbidInfoToMIS(parameterMap);
		
		return trans;
	}	

	/**
	 * <pre>
	 * 1. 개요 : 계약 생성정보 전송
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sendContInfoToMIS
	 * @date : 2019. 04. 05.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 04. 05.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */		
	@Override
	public FwkDataEntity sendContInfoToMIS(final FwkParameterMap parameterMap) {
		//FwkTransferObject trans = new FwkTransferObject();
		//FwkDataEntity fde = commItfcDao.sendContInfoToMIS(parameterMap);
		return commItfcDao.sendContInfoToMIS(parameterMap);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : ERP 첨부파일 번호 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectErpFileBrdnNo
	 * @date : 2019. 07. 11.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 07. 11.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */		
	@Override
	public FwkDataEntity selectErpFileBrdnNo(final FwkParameterMap parameterMap) {
		return commItfcDao.selectErpFileBrdnNo(parameterMap);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 계약 보증 정보 전송
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sendContGrntInfoToMIS
	 * @date : 2019. 04. 05.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 04. 05.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */		
	@Override
	public FwkTransferObject sendContGrntInfoToMIS(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		commItfcDao.sendContGrntInfoToMIS(parameterMap);
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 정보 전송
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sendVendInfoToMIS
	 * @date : 2019. 04. 09.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 04. 09.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */		
	@Override
	public FwkTransferObject sendVendInfoToMIS(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		commItfcDao.sendVendInfoToMIS(parameterMap);
		
		return trans;		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 프로시저 호출 후 타 시스템 주소 호출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : callOtherSysURL
	 * @date : 2019. 04. 22
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 04. 09.		은우소프트 맹경열				최초 작성 
	 * 2019. 07. 10.    은우소프트 하성윤                   연계 데이터 정리
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	@Override
	public FwkTransferObject callOtherSysURL(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		String RESULT_YN = "";
		
		/**
			순번 : 종류      : asCase    / arKey                   / fileBrdnNo 
			-------------------------------------------------------------------------------------------------------------
			    1 : 낙찰정보 : SCSBID   / BID_ANCM_NO       / PO_ATCHFILE_B_IF.FILE_BRDN_NO (파일내역번호: 있을시만)
			    2 : 계약정보 : CNTRCT  / CNTRCT_ADMN_NO / PO_ATCHFILE_B_IF.FILE_BRDN_NO (파일내역번호: 있을시만)
			    3 : 지급정보 : PAYREQ  / CLM_REQ_NO        / PO_ATCHFILE_B_IF.FILE_BRDN_NO (파일내역번호: 있을시만)
			    4 : 진행상태 : PRGS     / PCH_DMND_NO      / PO_ATCHFILE_B_IF.FILE_BRDN_NO (파일내역번호: 있을시만)
			    5 : 업체정보 : TRDPLC  / BUSIR_NO             / PO_ATCHFILE_B_IF.FILE_BRDN_NO (파일내역번호: 있을시만)
			    6 : 자산낙찰정보 : ASTDISP / PRCH_RQR_NO / PO_ATCHFILE_B_IF.FILE_BRDN_NO (파일내역번호: 있을시만)
		 */
		String arCase = parameterMap.get("P_CASE_CODE")+"";           // 연계케이스코드
		String arKey = parameterMap.get("P_KEY")+"";                         // 연계키값
		String fileBrdnNo = parameterMap.get("P_FILE_BRDN_NO")+""; // 연계파일번호
		String extraFileBrdnNo = FwkStringUtil.nvl(parameterMap.get("P_EXTRA_FILE_BRDN_NO")+""); // 연계파일번호
		String autcNo = FwkMessageUtil.getMessage("IPRO.ERP.AUTC_NO"); // 인증키값
		
		if("".equals(fileBrdnNo)){
			fileBrdnNo = "0";
		}
		if("".equals(arKey)){
			arKey = "0";
		}
		
		// 호출 URL
//		String reqUrl = FwkMessageUtil.getMessage("IPRO.ERP.CONN_URL")+"?autcNo="+autcNo+"&arCase="+arCase + amp("arKey", arKey) + amp("fileBrdnNo", fileBrdnNo);
		String reqUrl = "";
		if(parameterMap.get("P_CASE_CODE").toString().indexOf("TRDPLC") > -1) {
			reqUrl = FwkMessageUtil.getMessage("IPRO.ERP.CONN_URL")+"?autcNo="+autcNo+"&arCase="+arCase + "&arKey="+ arKey + "&fileBrdnNo="+ fileBrdnNo+"&extraFileBrdnNo="+extraFileBrdnNo;
		}else {
			reqUrl = FwkMessageUtil.getMessage("IPRO.ERP.CONN_URL")+"?autcNo="+autcNo+"&arCase="+arCase + "&arKey="+ arKey + "&fileBrdnNo="+ fileBrdnNo;
		}
		
		LOG.debug(">> Web ERP URL <<");
		LOG.debug("reqUrl :: " + reqUrl);
		LOG.debug("arCase :: " + arCase);
		LOG.debug("arKey :: " + arKey);
		LOG.debug("fileBrdnNo :: " + fileBrdnNo);
		LOG.debug("autcNo :: " + autcNo);
		LOG.debug(">>>>>>>>>>>>>END<<<<<<<<<<");

		//유틸 호출
//		JsFormUtil util = new JsFormUtil(parameterMap.getString("P_SEND_URL"), parameterMap.getString("P_CHARSET"));
		JsFormUtil util = new JsFormUtil(reqUrl, parameterMap.getString("P_CHARSET"), "PUT");
		//추가할 필드 있을 시 작성
		
		//전송
		Map<String, Object> map = util.finish();
		trans.put("rtnCode", map.get("rtnCode"));
		trans.put("rtnMessageCode", map.get("rtnMessageCode"));
		trans.put("rtnMessageMsg", map.get("rtnMessageMsg"));
        
        LOG.debug("웹 연동 호출 END =======================");
		return trans;
	}	
	
	public String amp(String key, String text){
		String rtn = "";
		
		if( text != null ){
			if(!"".equals(text)){
				rtn = "&"+key+"="+text;
			}
		}
		
		return rtn;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 단문 메시지 테이블 내용 저장 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertSsmsMsgInfo
	 * @date : 2020. 04. 27.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2020. 04. 27.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	@Override
	public FwkTransferObject insertSsmsMsgInfo(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		String msg = CmmnUtil.ebidMSGBodyCreate(parameterMap);
		
		LOG.debug("==================== SMS MSG INFO DETAIL START ====================");
		LOG.debug("msg : " + msg);
		LOG.debug("==================== SMS MSG INFO DETAIL END ====================");
		
		parameterMap.put("P_SEND_MESSAGE", msg);			//메시지내용
		parameterMap.put("P_RECEIVE_MOBILE_NO", parameterMap.get("P_RECEIVE_MOBILE_NO"));	//수신핸드폰번호
		parameterMap.put("P_CALLBACK_NO", "055-280-1114");				//
		if(parameterMap.get("P_RECEIVE_MOBILE_NO") != null && !parameterMap.get("P_RECEIVE_MOBILE_NO").equals("")) {
			commItfcDao.insertSsmsMsgRegist(parameterMap);
		}
		
		return trans;		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 프로시저 호출 후 타 시스템 주소 호출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : callOtherSysURLForVend
	 * @date : 2020. 06. 10
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2020. 06. 10.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */		
	@Override
	public FwkTransferObject callOtherSysURLForVend(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		String busirNo = parameterMap.get("bizrNo")+"";           // 사업자번호
		
		// 호출 URL
		String autcNo = FwkMessageUtil.getMessage("IPRO.ERP.AUTC_NO"); // 인증키값
		String reqUrl = FwkMessageUtil.getMessage("IPRO.ERP.CONN_URL_VEND")+"?autcNo="+autcNo+"&busirNo="+busirNo;
		LOG.debug(">> Web ERP URL <<");
		LOG.debug("autcNo :: " + autcNo);
		LOG.debug("reqUrl :: " + reqUrl);
		LOG.debug("busirNo :: " + busirNo);
		LOG.debug(">>>>>>>>>>>>>END<<<<<<<<<<");

		//유틸 호출
		JsFormUtil util = new JsFormUtil(reqUrl, parameterMap.getString("P_CHARSET"), "GET");
		//추가할 필드 있을 시 작성
		
		//전송
		Map<String, Object> map = util.finishGet();
		trans.put("rtnCode", map.get("rtnCode"));
		trans.put("rtnMessageCode", map.get("rtnMessageCode"));
		trans.put("rtnMessageMsg", map.get("rtnMessageMsg"));
		trans.put("trdplcCd", map.get("trdplcCd"));
		trans.put("busirNo", map.get("busirNo"));
        
        LOG.debug("웹 연동 호출 END =======================");
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 계약업체 TRDPLC_CD 업데이트 프로시저 호출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : sendContTrdplcToMIS
	 * @date : 2020. 06. 30
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2020. 06. 30.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */		
	@Override
	public void sendContTrdplcToMIS(final FwkParameterMap parameterMap) throws Exception {
		commItfcDao.sendTrdplcToMISCom(parameterMap);
	}	
	
}