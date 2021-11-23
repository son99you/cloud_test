package com.eunwoosoft.opro.ebid.service.impl; 

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.dto.simple.FwkSimpleDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.frwk.prl.request.FwkParameterMapInterceptor;
import com.eunwoosoft.opro.ebid.dao.OproEbidMyPartcptReqstSttusDao;
import com.eunwoosoft.opro.ebid.dao.OproEbidMyPblancDao;
import com.eunwoosoft.opro.ebid.dao.OproEbidPblancDao;
import com.eunwoosoft.opro.ebid.service.OproEbidMyPartcptReqstSttusService;
import com.eunwoosoft.opro.ebid.service.OproEbidMyPblancService;
import com.eunwoosoft.opro.ebid.service.OproEbidPblancService;


/**
 * 나의 입찰공고 서비스 구현 클래스
 * 
 * @author : 은우소프트 하성윤
 * @date : 2015. 03. 10.
 * @version : 1.0
 */
@Service("oproEbidMyPblancService")
public class OproEbidMyPblancServiceImpl extends AbstractFwkService implements OproEbidMyPblancService {
	
	@Resource(name="oproEbidMyPblancDao")
	private OproEbidMyPblancDao oproEbidMyPblancDao;
	
	@Resource(name="oproEbidPblancDao")
	private OproEbidPblancDao oproEbidPblancDao;
	
	@Resource(name="oproEbidMyPartcptReqstSttusDao")
	private OproEbidMyPartcptReqstSttusDao oproEbidMyPartcptReqstSttusDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	private static final Logger LOG = LoggerFactory.getLogger(FwkParameterMapInterceptor.class);

	/**
	 * <pre>
	 * 1. 개요 : 나의 입찰공고 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myBidPblancListInqireWithPgng
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
	public FwkTransferObject myBidPblancListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		if(parameterMap.get("P_ANNC_STDT_S") != null && !"".equals(parameterMap.get("P_ANNC_STDT_S"))){ // 공고일자 시작
			parameterMap.put("P_ANNC_STDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_STDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_ANNC_ENDT_S") != null && !"".equals(parameterMap.get("P_ANNC_ENDT_S"))){ // 공고일자 종료
			parameterMap.put("P_ANNC_ENDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_ENDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		trans.put(OproEbidMyPblancService.MY_BID_PBLANC_LIST, 
				oproEbidMyPblancDao.selectMyBidPblancListWithPgng(parameterMap));
		trans.put(OproEbidMyPblancService.MY_BID_PBLANC_LIST_TOTCNT, 
				oproEbidMyPblancDao.selectMyBidPblancListTotcnt(parameterMap));
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 나의 입찰공고 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myBidPblancDetailInqire
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
	 * @ 
	 */
	@Override
	public FwkTransferObject myBidPblancDetailInqire(final FwkParameterMap parameterMap)  {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		
		// 진행중인 공고 상세
		FwkDataEntity dataEntity = oproEbidPblancDao.selectInProgrsBidPblancDetail(parameterMap);
		trans.put(OproEbidPblancService.IN_PROGRS_BID_PBLANC_DETAIL, dataEntity);
		
		// 입찰품목 목록
		trans.put(OproEbidPblancService.BID_PRDLS_LIST, oproEbidPblancDao.selectBidPrdlstList(parameterMap));
		
		// 입찰첨부문서 조회
		parameterMap.put("P_BID_FSCD", "DI01");
		FwkDataEntity fileDataEntity = oproEbidPblancDao.selectBidAtchDoc(parameterMap);
		if(fileDataEntity != null){
			dataEntity.put("FILE_GRP_NO", fileDataEntity.getString("FILE_GRP_NO"));
			trans.put(OproEbidPblancService.IN_PROGRS_BID_PBLANC_DETAIL, dataEntity);
		}
		
		// 공고알림정보 목록   NOT01 정정공고, NOT02 취소공고, NOT03 연기공고, NOT04 유찰
		parameterMap.put("P_BID_PSCD", "PF30");
		trans.put(OproEbidPblancService.PBLANC_NTCN_INFO_LIST, oproEbidPblancDao.selectPblancNtcnInfoList(parameterMap));

		// 입찰 설명회 참석 정보
		trans.put(OproEbidPblancService.BID_DC_PEO_ATNDNC_INFO, oproEbidPblancDao.selectBidDcPeoAtndncInfoinqire(parameterMap));
		
		// 참가신청현황 조회 - 투찰 
		parameterMap.put("P_BID_TPI_SECD", "OP03");  // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
		trans.put(OproEbidPblancService.BDDPR_STTUS, oproEbidPblancDao.selectPartcptReqstSttusinqire(parameterMap));
		
		// 포기신청유무
		trans.put(OproEbidMyPartcptReqstSttusService.BID_PARTCPT_ABANDN_REQSTDOC, oproEbidMyPartcptReqstSttusDao.selectBidPartcptAbandnReqstdocDatile(parameterMap));
		
		// 공동수급업체 유무
		trans.put(OproEbidPblancService.COPERTN_SDEN_LIST, oproEbidPblancDao.selectCopertnSdenList(parameterMap));
		
		trans.put(OproEbidPblancService.BID_PBLANC_OPINION_INFO_LIST, oproEbidPblancDao.selectBidPblancOpinionInfoList(parameterMap));
		
		parameterMap.put("P_BID_VEND_PSCD", "OP01");
		FwkDataEntity vendDataEntity = oproEbidPblancDao.selectEntrpsInfoInqire(parameterMap);
		if(vendDataEntity != null){
			if("".equals(vendDataEntity.getString("BID_VEND_PSCD"))){
				oproEbidMyPartcptReqstSttusDao.updateEntrpsPartcptnPrstCdUpdt(parameterMap);
				/**** 진행이력 등록 ****/
				oproEbidPblancDao.insertEntrpsProgrsHistRegist(parameterMap);
			}
		}else{
			oproEbidMyPblancDao.insertEntrpsPartcptnInfoRegist(parameterMap);
			/**** 진행이력 등록 ****/
			oproEbidPblancDao.insertEntrpsProgrsHistRegist(parameterMap);
		}
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 청렴이행각서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : intgtyFlflMmrdInqire
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
	public FwkTransferObject intgtyFlflMmrdInqire(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		//FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID"));
		
		 // 오늘 날짜(format : yyyy년 MM월 dd일)
		String todayFormat = FwkFormatUtil.formatDate(FwkDateUtil.getCurrentDateAsString(), "yyyyMMdd", "yyyy년 MM월 dd일");
		trans.put("todayFormat", todayFormat);
		
		// 나의 공고 상세
		trans.put(OproEbidMyPblancService.MY_BID_PBLANC_DETAIL, 
				oproEbidMyPblancDao.selectMyBidPblancDetail(parameterMap));
		
		// 로그인한 업체 정보 조회
		trans.put(OproEbidMyPblancService.ENTRPS_INFO,
				oproEbidMyPblancDao.selectEntrpsinfoinqire(parameterMap));	// 업체정보조회
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰참가 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptRegist
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
	public FwkTransferObject bidPartcptRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		//FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		
		// 등록일시
		parameterMap.put("P_REGIST_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
		// 로그인한 업체 정보 조회
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID"));
		FwkDataEntity entrpsInfo =  oproEbidMyPblancDao.selectEntrpsinfoinqire(parameterMap);
		
		// 업체 참여정보 등록
		parameterMap.put("P_ENTRPS_PRST_CD", "OP02"); // 업체진행상태코드   OP01 공고열람, OP02 입찰참가신청, OP03 입찰포기신청, OP04 가격서제출, OP05 기술평가중, OP06 개찰, OP07 적격점수입력, OP08 협상통보수신, OP09 입찰종료(낙찰), OP10 입찰종료(미선정), OP11 입찰종료(유찰)
		oproEbidMyPblancDao.insertEntrpsPartcptnInfoRegist(parameterMap);
		
		// 업체 참여상세 등록
		parameterMap.put("P_BID_PARTCPTN_SE_CD", "OP01"); // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
		parameterMap.put("P_PARTCPTN_DT", FwkDateUtil.getCurrentDateTimeAsString()); // 참여일시
		parameterMap.put("P_REGIST_DT", FwkDateUtil.getCurrentDateTimeAsString());  // 등록일시
		parameterMap.put("P_CHARGER_NM", entrpsInfo.get("ENTRPS_CHARGER_NM"));	// 담당자명
		parameterMap.put("P_MVMN_TELNO", entrpsInfo.get("MVMN_TELNO"));			// 이동전화번호
		parameterMap.put("P_EMAIL_ADRES", entrpsInfo.get("EMAIL_ADRES"));				// 이메일주소
		parameterMap.put("P_TELNO", entrpsInfo.get("TELNO"));									// 전화번호
		oproEbidMyPblancDao.insertEntrpsPartcptnDetailRegist(parameterMap);
		
		// 업체 서명정보 등록
		//parameterMap.put("P_BID_SIGN_SE_CD", "S01"); // 입찰서명구분코드  S01 입찰참가신청, S02 제안서제출, S03 가격서제출
		//oproEbidMyPblancDao.insertEntrpsSignInfoRegist(parameterMap);
		
		/**** 진행이력 등록 ****/
		parameterMap.put("P_ENTRPS_PRST_CD", "OP01"); // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
		oproEbidPblancDao.insertEntrpsProgrsHistRegist(parameterMap);
		
		/**** 입찰참여가 완료 되었습니다. ****/
		LOG.debug("입찰참여가 완료 되었습니다.");
		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 입찰공고 의견 등록 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPblancOpinionRegistForm
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
	public FwkTransferObject bidPblancOpinionRegistForm(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		//FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID"));// 세션연동0
		// 공고의견정보상세
		/*trans.put(OepElbiMyBidPblancService.BID_PBLANC_OPINION_INFO_DETAIL, 
				oproEbidMyPblancDao.selectBidPblancOpinionInfoDetail(parameterMap));*/
		
		FwkDataEntity dataEntity = (FwkDataEntity) oproEbidMyPblancDao.selectBidPblancOpinionInfoDetail(parameterMap);
		
		if(dataEntity != null)
		{
//			dataEntity.put("OPINION_REGISTER_EMAIL_ADRES", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity.get("OPINION_REGISTER_EMAIL_ADRES")));
//			dataEntity.put("ANSWER_REGISTER_EMAIL_ADRES", SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity.get("ANSWER_REGISTER_EMAIL_ADRES")));
			//ANSWER_REGISTER_EMAIL_ADRES
			/*SDBCryptoUtil.decrtypEx("DBSEC.TB_KEY.ARIA", (String) dataEntity.get("ANSWER_REGISTER_EMAIL_ADRES"));*/
			trans.put(OproEbidMyPblancService.BID_PBLANC_OPINION_INFO_DETAIL ,dataEntity);
		}
		
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰공고 의견 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPblancOpinionUpdt
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
	 * @ 
	 */
	@Override
	public FwkTransferObject bidPblancOpinionUpdt(final FwkParameterMap parameterMap)  {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		//FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		
//		SDBCrypto crypto = null;
		String P_OPINION_REGISTER_EMAIL_ADRES_ENC = ""; 
		
		
//		crypto = new SDBCrypto();
//		crypto = SDBCrypto.getInstanceDomain("devkoica","192.168.1.8", 9003);
		
		/************ DB암호화 시작 *****************/
		
//		P_OPINION_REGISTER_EMAIL_ADRES_ENC = crypto.encrypt("DBSEC", "TB_KEY", "ARIA", parameterMap.getString("P_OPINION_REGISTER_EMAIL_ADRES"));
		
//		P_OPINION_REGISTER_EMAIL_ADRES_ENC = SDBCryptoUtil.encryptEx("DBSEC.TB_KEY.ARIA", parameterMap.getString("P_OPINION_REGISTER_EMAIL_ADRES"));
		
//		parameterMap.put("P_OPINION_REGISTER_EMAIL_ADRES", P_OPINION_REGISTER_EMAIL_ADRES_ENC);
		
		
		/************ DB암호화 종료 *****************/
		
		
		
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID"));// 세션연동
		String opinNo = parameterMap.get("P_BID_OPINION_NO")+"";
		if(!opinNo.equals("")){
			oproEbidMyPblancDao.updateBidPblancOpinionUpdt(parameterMap);
		}else{
			oproEbidMyPblancDao.insertBidPblancOpinionRegist(parameterMap);
		}
		
		// 입찰공고의견정보
		trans.put("opinionInfo", oproEbidMyPblancDao.selectBidPblancOpinionInfoDetail(parameterMap));
		
		// 입찰공고의견정보 총건수
		trans.put("OpinionInfoCnt", oproEbidMyPblancDao.selectBidPblancOpinionInfoListTotcnt(parameterMap));
		return trans;
	}

	@Override
	public FwkTransferObject bidDcRegistForm(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		trans.put("bidDcDetail", oproEbidMyPblancDao.selectBidDcDetail(parameterMap)) ;
		return trans;
	}

	@Override
	public void bidDcRegist(FwkParameterMap parameterMap) {
		oproEbidMyPblancDao.insertBidDcRegist(parameterMap);
	}

	@Override
	public void bidDcDel(FwkParameterMap parameterMap) {
		oproEbidMyPblancDao.deleteBidDcDel(parameterMap);
	}
	
	
}