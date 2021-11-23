package com.eunwoosoft.opro.ebid.service.impl; 

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.dto.simple.FwkSimpleDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkCertUtil;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.frwk.prl.request.FwkParameterMapInterceptor;
import com.eunwoosoft.opro.ebid.dao.OproEbidMyPartcptReqstSttusDao;
import com.eunwoosoft.opro.ebid.dao.OproEbidMyPblancDao;
import com.eunwoosoft.opro.ebid.dao.OproEbidMyResultDao;
import com.eunwoosoft.opro.ebid.dao.OproEbidPblancDao;
import com.eunwoosoft.opro.ebid.service.OproEbidMyPartcptReqstSttusService;
import com.eunwoosoft.opro.ebid.service.OproEbidPblancService;
import com.eunwoosoft.opro.main.dao.OproMainLoginFormDao;

/**
 * 나의 참가신청 현황 서비스 구현 클래스
 * 
 * @author : 은우소프트 하성윤
 * @date : 2015. 03. 10.
 * @version : 1.0
 */
@Service("oproEbidMyPartcptReqstSttusService")
public class OproEbidMyPartcptReqstSttusServiceImpl extends AbstractFwkService implements OproEbidMyPartcptReqstSttusService {
	
	@Resource(name="oproEbidMyPartcptReqstSttusDao")
	private OproEbidMyPartcptReqstSttusDao oproEbidMyPartcptReqstSttusDao;
	
	@Resource(name="oproEbidMyPblancDao")
	private OproEbidMyPblancDao oproEbidMyPblancDao;
	
	@Resource(name="oproEbidPblancDao")
	private OproEbidPblancDao oproEbidPblancDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="oproMainLoginFormDao")
	private OproMainLoginFormDao oproMainLoginFormDao;
	
	@Resource(name="oproEbidMyResultDao")
	private OproEbidMyResultDao oproEbidMyResultDao;

	private static final Logger LOG = LoggerFactory.getLogger(FwkParameterMapInterceptor.class);
	
	/**
	 * <pre>
	 * 1. 개요 : 나의 참가신청 현황 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myPartcptReqstSttusListInqireWithPgng
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject myPartcptReqstSttusListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		if(parameterMap.get("P_PBLANC_BEGIN_DT_S") != null && !"".equals(parameterMap.get("P_PBLANC_BEGIN_DT_S"))){ // 공고일자 시작
			parameterMap.put("P_PBLANC_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_PBLANC_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_PBLANC_END_DT_S") != null && !"".equals(parameterMap.get("P_PBLANC_END_DT_S"))){ // 공고일자 종료
			parameterMap.put("P_PBLANC_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_PBLANC_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		// 세션에서 정보가져오기
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID"));
		trans.put(OproEbidMyPartcptReqstSttusService.MY_PARTCPT_REQST_STTUS_LIST, 
		        oproEbidMyPartcptReqstSttusDao.selectMyPartcptReqstSttusListWithPgng(parameterMap));
		trans.put(OproEbidMyPartcptReqstSttusService.MY_PARTCPT_REQST_STTUS_LIST_TOTCNT, 
		        oproEbidMyPartcptReqstSttusDao.selectMyPartcptReqstSttusListTotcnt(parameterMap));
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 나의 참가신청 현황 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myPartcptReqstSttusDetailInqire
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject myPartcptReqstSttusDetailInqire(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		FwkDataEntity dataEntity = (FwkDataEntity) oproEbidMyPartcptReqstSttusDao.selectMyPartcptReqstSttusDetail(parameterMap);
		
		/************ DB복호화 시작 *****************/
//		dataEntity.put("BID_CHARGER_EMAIL_ADRES", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity.get("BID_CHARGER_EMAIL_ADRES")));
//		dataEntity.put("BID_CLIENT_EMAIL_ADRES", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity.get("BID_CLIENT_EMAIL_ADRES")));
		/************ DB복호화 종료 *****************/
		
		trans.put(OproEbidMyPartcptReqstSttusService.MY_PARTCPT_REQST_STTUS_DETAIL ,dataEntity);
		
		// 입찰품목 목록
		trans.put(OproEbidMyPartcptReqstSttusService.BID_PRDLS_LIST, 
				oproEbidMyPartcptReqstSttusDao.selectBidPrdlstList(parameterMap));
		
		// 입찰첨부문서 조회
		parameterMap.put("P_BID_FSCD", "DI01");
		FwkDataEntity dataEntityDI01 = oproEbidPblancDao.selectBidAtchDoc(parameterMap);
		trans.put(OproEbidPblancService.BID_ATCH_DOC, dataEntityDI01);
		
		if(dataEntityDI01 != null){
			parameterMap.put("P_FILE_GRP_NO", dataEntityDI01.getString("FILE_GRP_NO"));
			trans.put(OproEbidPblancService.BID_ATCH_DOC_LIST, 
					comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// 입찰첨부문서 조회
		parameterMap.put("P_BID_FSCD", "DI04");
		trans.put(OproEbidPblancService.BID_ATCH_DOC+2, 
				oproEbidPblancDao.selectBidAtchDoc(parameterMap));

		// 공고알림정보 목록
		parameterMap.put("P_BID_CHANGE_NTCN_CD", "NOT01");
		trans.put(OproEbidMyPartcptReqstSttusService.PBLANC_NTCN_INFO_LIST, 
				oproEbidMyPartcptReqstSttusDao.selectPblancNtcnInfoList(parameterMap));

		if(parameterMap.get("P_VEND_REG_NO") == null || "".equals(parameterMap.getString("P_VEND_REG_NO"))){
			parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		}

		// 입찰 설명회 참석 정보
		trans.put(OproEbidPblancService.BID_DC_PEO_ATNDNC_INFO, 
				oproEbidPblancDao.selectBidDcPeoAtndncInfoinqire(parameterMap));
		
		// 참가신청현황 조회 - 입찰참여 
		parameterMap.put("P_BID_PARTCPTN_SE_CD", "OP01");  // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
		trans.put(OproEbidPblancService.BID_PARTCPTN_STTUS, 
				oproEbidPblancDao.selectPartcptReqstSttusinqire(parameterMap));
		
		// 참가신청현황 조회 - 투찰 
		parameterMap.put("P_BID_PARTCPTN_SE_CD", "OP03");  // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
		trans.put(OproEbidPblancService.BDDPR_STTUS, 
				oproEbidPblancDao.selectPartcptReqstSttusinqire(parameterMap));
		
		// 포기신청유무
		parameterMap.put("P_BID_PARTCPTN_SE_CD", "OP02");  // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
		trans.put(OproEbidMyPartcptReqstSttusService.BID_PARTCPT_ABANDN_REQSTDOC, 
				oproEbidMyPartcptReqstSttusDao.selectBidPartcptAbandnReqstdocDatile(parameterMap));
		
		// 공동수급업체 유무
		trans.put(OproEbidPblancService.COPERTN_SDEN_LIST, 
				oproEbidPblancDao.selectCopertnSdenList(parameterMap));
		
		// 공동수급의무구분코드
		trans.put(OproEbidPblancService.ENTRPS_INFO_INQIRE, oproEbidPblancDao.selectEntrpsInfoInqire(parameterMap));
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰참가포기 신청서 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptAbandnReqstdocRegist
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public void bidPartcptAbandnReqstdocRegist(final FwkParameterMap parameterMap) {
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		// 세션에서 정보가져오기
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID"));
		
		// 업체참여마스터 진행코드 변경
		parameterMap.put("P_BID_VEND_PSCD", "OP03"); // OP003 입찰포기 ( 마스터와 진행 진행코드가 다름 )
		parameterMap.put("P_BID_ABND_YN", "Y");
		oproEbidMyPartcptReqstSttusDao.updateVendBidAbndUpdt(parameterMap);   // T_BI_VEND_MST UPDATE
		
		// 진행이력 등록
		oproEbidPblancDao.insertEntrpsProgrsHistRegist(parameterMap);   // T_BI_VEND_HIST INSERT
		
	}
	

	/**
	 * <pre>
	 * 1. 개요 : 입찰서 제출 폼 이동
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bipaPresentnForm
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject bipaPresentnForm(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션연동

		FwkDataEntity mypaRestDetail = (FwkDataEntity) oproEbidPblancDao.selectInProgrsBidPblancDetail(parameterMap);
		
		trans.put(OproEbidMyPartcptReqstSttusService.MY_PARTCPT_REQST_STTUS_DETAIL ,mypaRestDetail);
		
		// 입찰품목 목록
		trans.put(OproEbidMyPartcptReqstSttusService.BID_PRDLS_LIST, oproEbidMyPartcptReqstSttusDao.selectBidPrdlstList(parameterMap));
		
		//업체정보
		FwkDataEntity dataEntity = (FwkDataEntity) oproEbidMyPartcptReqstSttusDao.selectEntrpsinfoinqire(parameterMap);
		trans.put(OproEbidPblancService.BID_PARTCPTN_STTUS ,dataEntity);
		
		// 입찰참가포기신청서 조회
		trans.put(OproEbidMyPartcptReqstSttusService.BID_PARTCPT_ABANDN_REQSTDOC, oproEbidMyPartcptReqstSttusDao.selectBidPartcptAbandnReqstdocDatile(parameterMap));
		
		//입찰참여정보
		parameterMap.put("P_BID_TPI_SECD", "OP03");
		trans.put(OproEbidMyPartcptReqstSttusService.BID_PARTCPT_TNDR_INFO, oproEbidMyPartcptReqstSttusDao.selectMyPartcptTndrInfo(parameterMap));
		
		//예가선택정보
		trans.put(OproEbidMyPartcptReqstSttusService.BID_PARTCPT_ESSE_LIST, oproEbidMyPartcptReqstSttusDao.selectMyPartcptEsseInfo(parameterMap));
		
		//파일정보
		parameterMap.put("P_BID_TPI_SECD", "OP01");	//입찰참가서류
		parameterMap.put("P_BID_SBMT_FSCD", "DO01");	//입찰참가서류
		trans.put(OproEbidMyPartcptReqstSttusService.BID_DO01_FILE_INFO, oproEbidMyPartcptReqstSttusDao.selectMyPartcptVendFileInfo(parameterMap));
		//parameterMap.put("P_BID_TPI_SECD", "OP03");	//입찰서
		//parameterMap.put("P_BID_SBMT_FSCD", "DO04");	//입찰서
		//trans.put(OproEbidMyPartcptReqstSttusService.BID_DO04_FILE_INFO, oproEbidMyPartcptReqstSttusDao.selectMyPartcptVendFileInfo(parameterMap));
		
		//업체보증정보
		trans.put(OproEbidMyPartcptReqstSttusService.BID_PARTCPT_GRNT_INFO, oproEbidMyPartcptReqstSttusDao.selectMyPartcptGrntInfo(parameterMap));
		
		// 신청인정보 기본세팅
		parameterMap.put("P_USR_SN", "1");
		trans.put("bidVendChrgr", oproEbidMyPartcptReqstSttusDao.selectVendChrgrInfoDetail(parameterMap));   // T_CU_USER SELECT
		
		return trans;
	}
	

	/**
	 * <pre>
	 * 1. 개요 : 입찰서 제출 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bipaPresentnRegist
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 * @throws Exception 
	 */
	@Override
	public FwkTransferObject bipaPresentnRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		// 참가신청시간 체크
		FwkDataEntity bidInfo = oproEbidPblancDao.selectInProgrsBidPblancDetail(parameterMap);
		Long bipaPresentnEndDt = bidInfo.getLong("BIDC_SBMT_ENDT");
		Long nowDate = Long.parseLong( FwkDateUtil.getCurrentDateTimeAsString());

		if(nowDate > bipaPresentnEndDt){
			trans.put("msg", "입찰서제출시간이 종료되었습니다.");
			trans.put("resultCode", "ERR");
			return trans;
		}
		
		// 로그인한 업체 정보 조회
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID"));
		
		/****  전자서명 관련 ****/
		String certDn = (String) parameterMap.get("P_CERT_DN");
//		String strUserSignCert = (String) parameterMap.get("P_USER_SIGN_CERT"); // 사용자 인증서 정보

		byte[] serverKmCert;
		String strSignValue = (String) parameterMap.get("P_TNDR_AMT_ENC"); // 서명된 금액
		
		/***** 서버인증서 정보 ******/
		try {
			serverKmCert = FwkCertUtil.getServerCert().getBytes();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.debug("인증서 정보가 없습니다.");
			trans.put("msg", "서버인증서 정보를 불러오는데 실패하였습니다. 담당자에게 문의하세요.");
			trans.put("resultCode","ERR");
			return trans;
		}
		
		if ( certDn.equals("") ){
			// Exception
		    // 인증서가 없습니다.
			LOG.debug("인증서 정보가 없습니다.");
			trans.put("msg", "인증서 정보가 없습니다.");
			trans.put("resultCode","ERR");
			return trans;
		}
		if ( strSignValue.equals("") ){
			// Exception
		    // 전자서명이 없습니다.
			LOG.debug("전자서명이 없습니다.");
			trans.put("msg", "전자서명이 없습니다.");
			trans.put("resultCode","ERR");
			return trans;
		}
		
		// IP중복 체크
		/*
		parameterMap.put("P_BID_TPI_SECD", "OP03"); // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
		FwkDataEntity ipCheck =  oproEbidMyPblancDao.selectIpDplctCeck(parameterMap);
		if(Integer.parseInt(ipCheck.get("CNT").toString()) > 0){
			LOG.debug("현 입찰건에 동일한 IP에서 제출된 입찰서가 있어 제출할 수 없습니다." );
			trans.put("resultCode","ERR");
			trans.put("msg", "현 입찰건에 동일한 IP에서 제출된 입찰서가 있어 제출할 수 없습니다.");
			return trans;
		}
		*/
		
		/**** 업체 참여정보 수정 ****/
		parameterMap.put("P_BID_VEND_PSCD", "OP04"); // 업체진행상태코드   OP01 공고열람, OP02 입찰참가신청, OP03 입찰포기신청, OP04 가격서제출, OP05 기술평가중, OP06 개찰, OP07 적격점수입력, OP08 협상통보수신, OP09 입찰종료(낙찰), OP10 입찰종료(미선정), OP11 입찰종료(유찰)
		oproEbidMyPartcptReqstSttusDao.updateEntrpsPartcptnInfoUpdt(parameterMap);   // T_BI_VEND_MST UPDATE
		if(bidInfo.get("CONT_MTCD").equals("10003") || bidInfo.get("CONT_MTCD").equals("10006")) {
			//수의일 시 미암호화 금액 업데이트
			oproEbidMyPartcptReqstSttusDao.updateEntrpsPartcptnInfoTndrAmtUpdt(parameterMap);   // T_BI_VEND_MST UPDATE
		}
		
		/**** 업체 참여상세 등록 ****/
		parameterMap.put("P_BID_TPI_SECD", ""); // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
		parameterMap.put("P_BID_TPI_SECD_NOT", "OP05"); // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
		oproEbidMyPartcptReqstSttusDao.deleteVendDtlDel(parameterMap);   // T_BI_VEND_DTL DELETE
		
		parameterMap.put("P_BID_TPI_SECD", "OP03"); // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
		parameterMap.put("P_SIGN_DT",FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_SIGN_IP",parameterMap.getString("P_IP_ADRES"));
		oproEbidMyPblancDao.insertEntrpsPartcptnDetailRegist(parameterMap);   // T_BI_VEND_DTL INSERT
		
		if(!"".equals(parameterMap.getString("P_SIGN_VAL_CLEAN"))){
			parameterMap.put("P_BID_TPI_SECD", "OP0301"); // 청렴이행각서
			parameterMap.put("P_SIGN_DT",FwkDateUtil.getCurrentDateTimeAsString());
			parameterMap.put("P_SIGN_IP",parameterMap.getString("P_IP_ADRES"));
			parameterMap.put("P_ORG_VAL", parameterMap.getString("P_ORG_VAL_CLEAN"));
			parameterMap.put("P_SIGN_VAL", parameterMap.getString("P_SIGN_VAL_CLEAN"));
			parameterMap.put("P_ORG_HASH_VAL", parameterMap.getString("P_ORG_HASH_VAL_CLEAN"));
			oproEbidMyPblancDao.insertEntrpsPartcptnDetailRegist(parameterMap);   // T_BI_VEND_DTL INSERT
		}
		
		if(!"".equals(parameterMap.getString("P_SIGN_VAL_BID"))){
			parameterMap.put("P_BID_TPI_SECD", "OP0302"); // 입찰각서
			parameterMap.put("P_SIGN_DT",FwkDateUtil.getCurrentDateTimeAsString());
			parameterMap.put("P_SIGN_IP",parameterMap.getString("P_IP_ADRES"));
			parameterMap.put("P_ORG_VAL", parameterMap.getString("P_ORG_VAL_BID"));
			parameterMap.put("P_SIGN_VAL", parameterMap.getString("P_SIGN_VAL_BID"));
			parameterMap.put("P_ORG_HASH_VAL", parameterMap.getString("P_ORG_HASH_VAL_BID"));
			oproEbidMyPblancDao.insertEntrpsPartcptnDetailRegist(parameterMap);   // T_BI_VEND_DTL INSERT
		}
		
		if(!"".equals(parameterMap.getString("P_SIGN_VAL_GRNT"))){
			parameterMap.put("P_BID_TPI_SECD", "OP0303"); // 지급각서
			parameterMap.put("P_SIGN_DT",FwkDateUtil.getCurrentDateTimeAsString());
			parameterMap.put("P_SIGN_IP",parameterMap.getString("P_IP_ADRES"));
			parameterMap.put("P_ORG_VAL", parameterMap.getString("P_ORG_VAL_GRNT"));
			parameterMap.put("P_SIGN_VAL", parameterMap.getString("P_SIGN_VAL_GRNT"));
			parameterMap.put("P_ORG_HASH_VAL", parameterMap.getString("P_ORG_HASH_VAL_GRNT"));
			oproEbidMyPblancDao.insertEntrpsPartcptnDetailRegist(parameterMap);   // T_BI_VEND_DTL INSERT
		}
		
		/**** 업체 담당자 등록 (신청인정보정보) ****/
		parameterMap.put("P_USR_NM", parameterMap.getString("P_CHRGR_NM").replaceAll(" ", ""));
		oproEbidMyPartcptReqstSttusDao.updateVendUserDlgtNUpdt(parameterMap);   // T_CU_USER UPDATE
		FwkDataEntity usrSn = oproEbidMyPartcptReqstSttusDao.selectVendUserSn(parameterMap);   // T_CU_USER SELECT
		if(usrSn == null){
			oproEbidMyPartcptReqstSttusDao.insertVendUserRegist(parameterMap);   // T_CU_USER INSERT
		}else{
			parameterMap.put("P_USR_SN", usrSn.getInt("USR_SN"));
			oproEbidMyPartcptReqstSttusDao.updateVendUserUpdt(parameterMap);   // T_CU_USER UPDATE
		}
		
		/**** 투찰품목정보 저장 ****/
		/*180000 - 복수예가
		   180001 - 단일예가
		   180002 - 비예가*/
		String prdprcAtSe = (String) bidInfo.get("ESTPC_SECD");
		if("180000".equals(prdprcAtSe)){ // 복수예가 일 경우
			
			// 업체추첨예가 등록
			Object compnoPrdprcNo = parameterMap.get("P_PLR_ESTPC_NO");
			
			/*@SuppressWarnings("unchecked")
			List<FwkDataEntity> compnoPrdprcNo = new parameterMap.get("P_COMPNO_PRDPRC_NO"));*/
			
			if(compnoPrdprcNo != null){
				oproEbidMyPartcptReqstSttusDao.deleteVendEsseDel(parameterMap);   // T_BI_VEND_ESSE DELETE
				if(compnoPrdprcNo instanceof String[]){
					
					String[] compnoPrdprcNoList = (String[]) compnoPrdprcNo;
					for(int i =0; i < compnoPrdprcNoList.length; i++){
						parameterMap.put("P_PLR_ESTPC_NO", compnoPrdprcNoList[i]);
						oproEbidMyPartcptReqstSttusDao.insertEntrpsDrwtPrdprcRegist(parameterMap);   // T_BI_VEND_ESSE INSERT
					}
				}else if(compnoPrdprcNo instanceof ArrayList){
					ArrayList compnoPrdprcNoList = (ArrayList) compnoPrdprcNo;
					for(int i =0; i < compnoPrdprcNoList.size(); i++){
						parameterMap.put("P_PLR_ESTPC_NO", compnoPrdprcNoList.get(i));
						oproEbidMyPartcptReqstSttusDao.insertEntrpsDrwtPrdprcRegist(parameterMap);   // T_BI_VEND_ESSE INSERT
					}
				}
			}
		}
		
		// 투찰품목정보 등록
		Object P_ITEM_NO = parameterMap.get("P_ITEM_NO");
		if(P_ITEM_NO != null){
			oproEbidMyPartcptReqstSttusDao.deleteTndrItemDel(parameterMap);   // T_BI_TNDR_ITEM DELETE
			if(P_ITEM_NO instanceof String){
				oproEbidMyPartcptReqstSttusDao.insertBddprPrdlstInfoRegist(parameterMap);   // T_BI_TNDR_ITEM INSERT
			}else if(P_ITEM_NO instanceof String[]){
				String[] itemNoList = (String[]) P_ITEM_NO;
				String[] itemSnList = (String[]) parameterMap.get("P_ITEM_SN");
				String[] itemUnitEncList = (String[]) parameterMap.get("P_ITEM_UNIT_ENC");
				for(int i =0; i < itemNoList.length; i++){
					parameterMap.put("P_ITEM_NO", itemNoList[i]);
					parameterMap.put("P_ITEM_SN", itemSnList[i]);
					parameterMap.put("P_ITEM_UNIT_ENC", itemUnitEncList[i]);
					oproEbidMyPartcptReqstSttusDao.insertBddprPrdlstInfoRegist(parameterMap);   // T_BI_TNDR_ITEM INSERT
				}
			}else if(P_ITEM_NO instanceof ArrayList){
				ArrayList itemNoList = (ArrayList) P_ITEM_NO;
				ArrayList itemSnList = (ArrayList) parameterMap.get("P_ITEM_SN");
				ArrayList itemUnitEncList = (ArrayList) parameterMap.get("P_ITEM_UNIT_ENC");
				for(int i =0; i < itemNoList.size(); i++){
					parameterMap.put("P_ITEM_NO", itemNoList.get(i));
					parameterMap.put("P_ITEM_SN", itemSnList.get(i));
					parameterMap.put("P_ITEM_UNIT_ENC", itemUnitEncList.get(i));
					oproEbidMyPartcptReqstSttusDao.insertBddprPrdlstInfoRegist(parameterMap);   // T_BI_TNDR_ITEM INSERT
				}
			}
		}
		
		//입찰보증 저장
		Object P_BIDGR_SECD = parameterMap.get("P_BIDGR_SECD");
		if(P_BIDGR_SECD != null) {
			oproEbidMyPartcptReqstSttusDao.deleteVendGrntDel(parameterMap);   // T_BI_VEND_GRNT DELETE
			if(P_BIDGR_SECD instanceof String) {
				oproEbidPblancDao.insertBidGrntyInfoRegist(parameterMap);   //  T_BI_VEND_GRNT INSERT
			}else if(P_BIDGR_SECD instanceof String[]) {
				String[] bidgrSeCd = (String[]) P_BIDGR_SECD;
				String[] bidgrAmt = (String[]) parameterMap.get("P_BIDGR_AMT");
				String[] bidgrNo = (String[]) parameterMap.get("P_BIDGR_NO");
				String[] bidgrAgnm = (String[]) parameterMap.get("P_BIDGR_AGNM");
				String[] grntSn = (String[]) parameterMap.get("P_GRNT_SN");
				for(int i =0; i < bidgrSeCd.length; i++) {
					parameterMap.put("P_BIDGR_SECD", bidgrSeCd[i]);
					parameterMap.put("P_BIDGR_AMT", bidgrAmt[i]);
					parameterMap.put("P_BIDGR_NO", bidgrNo[i]);
					parameterMap.put("P_BIDGR_AGNM", bidgrAgnm[i]);
					parameterMap.put("P_GRNT_SN", grntSn[i]);
					oproEbidPblancDao.insertBidGrntyInfoRegist(parameterMap);   //  T_BI_VEND_GRNT INSERT
				}
			}else if(P_BIDGR_SECD instanceof ArrayList) {
				ArrayList bidgrSeCd = (ArrayList) P_BIDGR_SECD;
				ArrayList bidgrAmt = (ArrayList) parameterMap.get("P_BIDGR_AMT");
				ArrayList bidgrNo = (ArrayList) parameterMap.get("P_BIDGR_NO");
				ArrayList bidgrAgnm = (ArrayList) parameterMap.get("P_BIDGR_AGNM");
				ArrayList grntSn = (ArrayList) parameterMap.get("P_GRNT_SN");
				for(int i =0; i < bidgrSeCd.size(); i++) {
					parameterMap.put("P_BIDGR_SECD", bidgrSeCd.get(i));
					parameterMap.put("P_BIDGR_AMT", bidgrAmt.get(i));
					parameterMap.put("P_BIDGR_NO", bidgrNo.get(i));
					parameterMap.put("P_BIDGR_AGNM", bidgrAgnm.get(i));
					parameterMap.put("P_GRNT_SN", grntSn.get(i));
					oproEbidPblancDao.insertBidGrntyInfoRegist(parameterMap);   //  T_BI_VEND_GRNT INSERT
				}
			}
		}
		
		parameterMap.put("P_BID_TPI_SECD", "OP03"); // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
		parameterMap.put("P_BID_SBMT_FSCD", "DO02"); //제안서
		oproEbidMyPartcptReqstSttusDao.deleteVendFileDel(parameterMap);   // T_BI_VEND_FILE DELETE
		
		if(!bidInfo.get("CONT_MTCD").equals("10003") && !bidInfo.get("CONT_MTCD").equals("10006")) {
			parameterMap.put("P_BID_SBMT_FSCD", "DO04"); // 입찰서
			oproEbidMyPartcptReqstSttusDao.deleteVendFileDel(parameterMap);   // T_BI_VEND_FILE DELETE			
		}
		//제안서 저장
		if(parameterMap.get("P_FILE_GRP_NO") != null && !"".equals(parameterMap.get("P_FILE_GRP_NO"))){
			// 첨부파일 저장
			parameterMap.put("P_BID_SBMT_FSCD", "DO02"); //제안서
			oproEbidPblancDao.insertBidPareFileRegist(parameterMap);
		}
		
		// 투찰내역서 저장
		if(parameterMap.get("P_FILE_GRP_NO2") != null && !"".equals(parameterMap.get("P_FILE_GRP_NO2"))){
			// 첨부파일 저장
			parameterMap.put("P_FILE_GRP_NO", parameterMap.get("P_FILE_GRP_NO2"));
			//parameterMap.put("P_BID_SBMT_FSCD", "DO04"); // 입찰서
			//oproEbidPblancDao.insertBidPareFileRegist(parameterMap);
		}
		
		/**** 진행이력 등록 ****/
		parameterMap.put("P_BID_VEND_PSCD", "OP04");
		oproEbidPblancDao.insertEntrpsProgrsHistRegist(parameterMap);   // T_BI_VEND_HIST INSERT
		
		/**** 투찰이 완료 되었습니다. ****/
		LOG.debug("투찰이 완료 되었습니다.");
		trans.put("resultCode","SUCC");
		return trans;
	}
	

	/**
	 * <pre>
	 * 1. 개요 : 입찰 가능 여부 검증
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPosblAtVrify
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject bidPosblAtVrify(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		// 로그인한 업체 정보 조회
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID"));
		
		// 입찰 일정 조회
		FwkDataEntity bidSchdulDetail =  oproEbidMyPblancDao.selectBidSchdulDetail(parameterMap);
		
		FwkDataEntity bidDetail = (FwkDataEntity) oproEbidPblancDao.selectInProgrsBidPblancDetail(parameterMap);
		
		
		// 2019-08-13 은잔디 추가 : 2단계 경쟁일 경우 제안서 부적격일 때 입찰서제출 불가
		if("34".equals(bidDetail.get("SBID_MTCD"))){
			FwkDataEntity estmElcdYn = oproEbidMyPartcptReqstSttusDao.selectBidEstmElcdYn(parameterMap);
			
			if("NT_ELGB".equals(estmElcdYn.getString("ESTM_ELCD"))){
				LOG.debug("평가결과 부적격업체입니다. 입찰서 제출이 불가합니다.");
				trans.put("chkMsg", "평가결과 부적격업체입니다. 입찰서 제출이 불가합니다.");
				trans.setResultCode("ERR");
				return trans;
			}
		}

		// 2019-12-26 은잔디 추가 : 해당 공고의 입찰물품 구매요구번호가 V_RM_PCHDMNDRMS view 에 있을 경우 입찰서제출 불가 
		List<FwkDataEntity> sbjtNoList = oproEbidMyPartcptReqstSttusDao.selectSbjNoList(parameterMap);
		if(sbjtNoList.size() > 0 ){
			LOG.debug("해당 구매 입찰 공고건의 과제로 연구과제를 참여하여 수행하고 있으므로 입찰에 참여 하실 수 없습니다.\n자세한 사항은 구매담당자에 문의하여 주시기 바랍니다.");
			trans.put("chkMsg", "해당 구매 입찰 공고건의 과제로 연구과제를 참여하여 수행하고 있으므로 입찰에 참여 하실 수 없습니다.\n자세한 사항은 구매담당자에 문의하여 주시기 바랍니다.");
			trans.setResultCode("ERR");
			return trans;
		}
		
		String currentDate = FwkDateUtil.getCurrentDateTimeAsString();	// 현재 일시
		
		List<String> snctStcdList = new ArrayList<String>();
		snctStcdList.add("B");
		snctStcdList.add("C");
		snctStcdList.add("Z");
		parameterMap.put("snctStcdList", snctStcdList);
		List<FwkDataEntity> dataEntityList = oproEbidPblancDao.selectVendSnctInfo(parameterMap); 
		
		FwkDataEntity fileYn = oproMainLoginFormDao.selectVendFileYn(parameterMap);
		
		//재입찰 검사
		if(parameterMap.get("P_ROUND_NO") != null && parameterMap.getInt("P_ROUND_NO") > 1) {
			parameterMap.put("P_ROUND_ORG", parameterMap.get("P_ROUND_NO"));
			parameterMap.put("P_ROUND_NO", parameterMap.getInt("P_ROUND_NO")-1);
			parameterMap.put("P_BID_TPI_SECD", "OP03");
			FwkDataEntity tndrEntity = oproEbidMyPartcptReqstSttusDao.selectMyPartcptTndrInfo(parameterMap);
			if(tndrEntity == null && !bidDetail.get("BID_MTCD").equals("PRVT")) {
				LOG.debug("재입찰 전 투찰에 참여하지 않았습니다.");
				trans.put("chkMsg", "재입찰 전 투찰에 참여하지 않았습니다.\n재입찰 건은 투찰참여 업체만 참여 가능합니다.");
				trans.setResultCode("ERR");
				return trans;				
			}
			parameterMap.put("P_ROUND_NO", parameterMap.getInt("P_ROUND_ORG"));
		}
		
		if("180000".equals(bidDetail.get("ESTPC_SECD"))){
			// 복수예가 목록 조회
			List<FwkDataEntity> compnoPrdprcList = oproEbidMyResultDao.selectCompnoPrdprcList(parameterMap);

			if(compnoPrdprcList == null || compnoPrdprcList.size() == 0){
				LOG.debug("복수예가가 등록되지 않았습니다.");
				trans.put("chkMsg", "복수예가가 등록되지 않았습니다.");
				trans.setResultCode("ERR");
				return trans;
			}			
		}
			
		if(!"".equals(bidSchdulDetail.getString("BIDC_SBMT_STDT")) ){
			if(bidSchdulDetail.get("BIDC_SBMT_STDT").toString().compareTo( currentDate ) > 0){
				LOG.debug("입찰서 제출 시간이 아닙니다. 제출시간을 확인하시기 바랍니다." );
				trans.put("chkCode", "bipa01");
				trans.put("chkMsg", "입찰서 제출 시간이 아닙니다. 제출시간을 확인하시기 바랍니다.");
				return trans;
			}
		}
		if(bidSchdulDetail.get("BIDC_SBMT_ENDT").toString().compareTo( currentDate ) < 0){
			LOG.debug("입찰서 제출 시간이 지났습니다. 제출시간을 확인하시기 바랍니다." );
			trans.put("chkCode", "bipa02");
			trans.put("chkMsg", "입찰서 제출 시간이 지났습니다. 제출시간을 확인하시기 바랍니다.");
			return trans;
		}

		// 입찰설명회 필수 일 경우 체크
		if("Y".equals(bidDetail.getString("BID_BRFS_YN")) && "Y".equals(bidDetail.getString("BID_BRFS_ATND_YN"))){
			// 입찰 설명회 참석 정보 조회
			FwkDataEntity bidDcPeoInfo = oproEbidMyPartcptReqstSttusDao.selectBidDcPeoAtndncInfoInqire(parameterMap);
			if(bidDcPeoInfo == null){
				trans.put("chkCode", "bipa03");
				trans.put("chkMsg", "귀사는 본 입찰공고건에 대해 입찰설명회 미참석으로 인해 입찰참가를 하실 수 없습니다. 자세한 사항은 담당자에게 문의하시기 바랍니다. ");
				return trans;
			}
		}
		
		if(!"PF20".equals(bidDetail.get("BID_PSCD").toString()) && !"PF30".equals(bidDetail.get("BID_PSCD").toString())){
			LOG.debug("입찰이 진행중이 아닙니다." );
			trans.put("chkCode", "bipa04");
			trans.put("chkMsg", "입찰이 진행중이 아닙니다.");
			return trans;
		}
		
		if("BIDC_SBMT".equals(parameterMap.get("P_SECD"))){
			// IP중복 체크
			parameterMap.put("P_BID_TPI_SECD", "OP03"); // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
			FwkDataEntity ipCheck =  oproEbidMyPblancDao.selectIpDplctCeck(parameterMap);
			if(Integer.parseInt(ipCheck.get("CNT").toString()) > 0){
				LOG.debug("현 입찰건에 동일한 IP에서 제출된 입찰서가 있어 제출할 수 없습니다." );
				trans.put("chkCode", "bipa05");
				trans.put("chkMsg", "현 입찰건에 동일한 IP에서 제출된 입찰서가 있어 제출할 수 없습니다.");
				return trans;
			}
		}
		
		if(dataEntityList.size() != 0){
			FwkDataEntity data = dataEntityList.get(0);
			LOG.debug("해당 사유로 입찰에 참여하실 수 없습니다.\n"+data.getString("SNCT_RSN_CNTN"));
			trans.put("chkCode", "bipa06");
			trans.put("chkMsg", "해당 사유로 입찰에 참여하실 수 없습니다.\n"+data.getString("SNCT_RSN_CNTN"));
			return trans;
		}
		
		if("N".equals(fileYn.getString("FILE_YN"))){
			LOG.debug("필수 첨부파일이 등록되지 않아 입찰에 참여하실 수 없습니다.\n(마이페이지에서 필수 첨부파일을 등록해주세요.)");
			trans.put("chkCode", "bipa07");
			trans.put("chkMsg", "필수 첨부파일이 등록되지 않아 입찰에 참여하실 수 없습니다.\n(마이페이지에서 필수 첨부파일을 등록해주세요.)");
			return trans;
		}
		
		trans.put("chkCode", "succ");
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 제안서 가능 여부 검증
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPosblPrprcVrify
	 * @date : 2019. 02. 21.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 21.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link TransferObject}
	 * @throws SDBException 
	 */
	public FwkTransferObject bidPosblPrprcVrify(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		// 로그인한 업체 정보 조회
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID"));
		
		// 입찰 일정 조회
		FwkDataEntity bidSchdulDetail =  oproEbidMyPblancDao.selectBidSchdulDetail(parameterMap);
		
		FwkDataEntity bidDetail = (FwkDataEntity) oproEbidPblancDao.selectInProgrsBidPblancDetail(parameterMap);
		
		String currentDate = FwkDateUtil.getCurrentDateTimeAsString();	// 현재 일시
		
		List<String> snctStcdList = new ArrayList<String>();
		snctStcdList.add("B");
		snctStcdList.add("C");
		snctStcdList.add("Z");
		parameterMap.put("snctStcdList", snctStcdList);
		List<FwkDataEntity> dataEntityList = oproEbidPblancDao.selectVendSnctInfo(parameterMap); 
		
		FwkDataEntity fileYn = oproMainLoginFormDao.selectVendFileYn(parameterMap);
		
		// 입찰설명회 필수 일 경우 체크
		if("Y".equals(bidDetail.getString("BID_BRFS_YN")) && "Y".equals(bidDetail.getString("BID_BRFS_ATND_YN"))){
			// 입찰 설명회 참석 정보 조회
			FwkDataEntity bidDcPeoInfo = oproEbidMyPartcptReqstSttusDao.selectBidDcPeoAtndncInfoInqire(parameterMap);
			if(bidDcPeoInfo == null){
				trans.put("chkCode", "bipa03");
				trans.put("chkMsg", "귀사는 본 입찰공고건에 대해 입찰설명회 미참석으로 인해 제안서를 제출 하실 수 없습니다. 자세한 사항은 담당자에게 문의하시기 바랍니다. ");
				return trans;
			}
		}		
		
		// 제안서 제출 필수 일 경우 체크 
		if("Y".equals(bidDetail.getString("PRPDC_ESS_YN"))){
			if(bidSchdulDetail.get("PRPDC_SBMT_STDT").toString().compareTo( currentDate ) > 0){
				LOG.debug("제안서 제출 시간이 아닙니다. 제출시간을 확인하시기 바랍니다." );
				trans.put("chkCode", "bipa01");
				trans.put("chkMsg", "제안서 제출 시간이 아닙니다. 제출시간을 확인하시기 바랍니다.");
				return trans;
			}
			if(bidSchdulDetail.get("PRPDC_SBMT_ENDT").toString().compareTo( currentDate ) < 0){
				LOG.debug("제안서 제출 시간이 지났습니다. 제출시간을 확인하시기 바랍니다." );
				trans.put("chkCode", "bipa02");
				trans.put("chkMsg", "제안서 제출 시간이 지났습니다. 제출시간을 확인하시기 바랍니다.");
				return trans;
			}			
		}
		
		trans.put("chkCode", "succ");
		return trans;		
	}	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.opro.ebid.service.impl.OproEbidMyPartcptReqstSttusServiceImpl.java
	 * @Method : bidAbndRegistForm
	 * @author : sanghoon_joen
	 * @date : 2018. 8. 27. 
	 * @param parameterMap
	 * @return
	 */
	public FwkTransferObject bidAbndRegistForm(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(OproEbidMyPartcptReqstSttusService.MY_PARTCPT_REQST_STTUS_DETAIL
				, oproEbidPblancDao.selectInProgrsBidPblancDetail(parameterMap));
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1.개요 : 역경매 등록폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.opro.ebid.service.impl.OproEbidMyPartcptReqstSttusServiceImpl.java
	 * @Method : datpRegistForm
	 * @author : sanghoon_joen
	 * @date : 2018. 12. 14. 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject datpRegistForm(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션연동

		FwkDataEntity mypaRestDetail = (FwkDataEntity) oproEbidPblancDao.selectInProgrsBidPblancDetail(parameterMap);
		
		trans.put(OproEbidMyPartcptReqstSttusService.MY_PARTCPT_REQST_STTUS_DETAIL ,mypaRestDetail);
		
		// 입찰품목 목록
		trans.put(OproEbidMyPartcptReqstSttusService.BID_PRDLS_LIST, 
				oproEbidMyPartcptReqstSttusDao.selectBidPrdlstList(parameterMap));
		
		//업체정보
		FwkDataEntity dataEntity = (FwkDataEntity) oproEbidMyPartcptReqstSttusDao.selectEntrpsinfoinqire(parameterMap);
		trans.put(OproEbidPblancService.BID_PARTCPTN_STTUS ,dataEntity);
		
		// 입찰참가포기신청서 조회
		trans.put(OproEbidMyPartcptReqstSttusService.BID_PARTCPT_ABANDN_REQSTDOC, 
				oproEbidMyPartcptReqstSttusDao.selectBidPartcptAbandnReqstdocDatile(parameterMap));
		
		trans.put("datpList", oproEbidMyPartcptReqstSttusDao.selectDatpList(parameterMap));
		
		trans.put("datp", oproEbidMyPartcptReqstSttusDao.selectDatpLastAmt(parameterMap));
		
		String key = parameterMap.getString("P_ANNC_NO")+parameterMap.getString("P_ANNC_NGR")+"EBS"+parameterMap.getString("P_ROUND_NO");
		String iv = key.substring(0,16);
	    byte[] keyData = key.getBytes();
	    SecretKey secureKey = new SecretKeySpec(keyData, "AES");
		try {
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(iv.getBytes()));
		    byte[] encrypted = c.doFinal(dataEntity.getString("VEND_REG_NO").getBytes("UTF-8"));
		    String enStr = new String(Base64.encodeBase64(encrypted));
		    trans.put("encStr", enStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1.개요 : 역경매 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.opro.ebid.service.impl.OproEbidMyPartcptReqstSttusServiceImpl.java
	 * @Method : datpRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 12. 14. 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject datpRegist(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity biMst = (FwkDataEntity) oproEbidPblancDao.selectInProgrsBidPblancDetail(parameterMap);
		
		BigDecimal TNDR_AMT = new BigDecimal(parameterMap.getString("P_TNDR_AMT"));
		
		if(TNDR_AMT.compareTo(new BigDecimal("0")) == 0){
			oproEbidMyPartcptReqstSttusDao.updateBiRound(parameterMap);
		}
		
		if(!"".equals(biMst.getString("TNDR_PSBL_AMT"))){
			BigDecimal TNDR_PSBL_AMT = new BigDecimal(biMst.getString("TNDR_PSBL_AMT"));
			if(TNDR_PSBL_AMT.compareTo(TNDR_AMT) == -1){
				trans.put("errMsg", "투찰가능금액 초과");
				return trans;
			}
		}
			
		int cnt = oproEbidMyPartcptReqstSttusDao.selectDatpRegistCnt(parameterMap);
		
		if(cnt < biMst.getInt("DTAC_TNDR_LMT_CNT")){
			oproEbidMyPartcptReqstSttusDao.insertDatpMst(parameterMap);
		}else{
			trans.put("errMsg", "제한횟수 초과");
			return trans;
		}
		
		BigDecimal MIN_DN_RT = new BigDecimal(biMst.getString("MIN_DN_RT"));
		BigDecimal TNDR_PSBL_AMT = TNDR_AMT.multiply(new BigDecimal("1").subtract(MIN_DN_RT.divide(new BigDecimal("100"))));
		
		parameterMap.put("P_TNDR_PSBL_AMT", TNDR_PSBL_AMT.setScale(0, BigDecimal.ROUND_DOWN).toString());
		
		oproEbidMyPartcptReqstSttusDao.updateDatpTndrAmt(parameterMap);
		
		trans.put("errMsg", "succ");
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰서 제출 폼 이동
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : grntInfoUpdate
	 * @date : 2019. 02. 20.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 20.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link TransferObject}
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject grntInfoUpdate(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		//기존보증정보 삭제
		oproEbidMyPartcptReqstSttusDao.deleteVendGrntDel(parameterMap);   // T_BI_VEND_GRNT DELETE
		//입찰보증 저장
		oproEbidPblancDao.insertBidGrntyInfoRegist(parameterMap);   //  T_BI_VEND_GRNT INSERT		
		
		/**** 진행이력 등록 ****/
		parameterMap.put("P_BID_VEND_PSCD", "OP02");
		oproEbidPblancDao.insertEntrpsProgrsHistRegist(parameterMap);   // T_BI_VEND_HIST INSERT
		
		return trans;		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 제안서 정보 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prprcPresentnRegist
	 * @date : 2019. 03. 15.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 15.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link TransferObject}
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject prprcPresentnRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		/**** 업체 참여정보 수정 ****/
		//oproEbidMyPartcptReqstSttusDao.updateEntrpsPartcptnInfoUpdt(parameterMap);
		
		/**** 업체 참여상세 등록 ****/
		parameterMap.put("P_BID_TPI_SECD", "OP05"); // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
		parameterMap.put("P_SIGN_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_SIGN_IP", parameterMap.getString("P_IP_ADRES"));
		oproEbidMyPartcptReqstSttusDao.deleteVendDtlDel(parameterMap);   // T_BI_VEND_DTL DELETE
		oproEbidMyPblancDao.insertEntrpsPartcptnDetailRegist(parameterMap);   // T_BI_VEND_DTL INSERT		
		
		return trans;
	}	
	
}