package com.eunwoosoft.opro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 전자입찰 > 입찰공고 서비스
 * <pre>
 * com.eunwoosoft.opro.ebid.service 
 *    |_ OproEbidPblancService.java
 * 
 * </pre>
 * @date : 2015. 02. 16. 오후 04:31:21
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
public interface OproEbidPblancService {

	/**
	 * 진행중인 입찰공고 목록 Key
	 */
	static final String IN_PROGRS_BID_PBLANC_LIST = "inProgrsBidPblancList";
	
	/**
	 * 진행중인 입찰공고 목록 총건수
	 */
	static final String IN_PROGRS_BID_PBLANC_LIST_TOTCNT = "inProgrsBidPblancListTotcnt";

	/**
	 * 진행중인 입찰공고상세
	 */
	static final String IN_PROGRS_BID_PBLANC_DETAIL = "inProgrsBidPblancDetail";
	
	/**
	 * 입찰 설명회 참석 정보
	 */
	static final String BID_DC_PEO_ATNDNC_INFO = "bidDcPeoAtndncInfo";
	
	/**
	 * 입찰참여현황
	 */
	static final String BID_PARTCPTN_STTUS = "bidPartcptnSttus";
	
	/**
	 * 투찰현황
	 */
	static final String BDDPR_STTUS = "bddprSttus";

	/**
	 * 입찰품목 목록 조회
	 */
	static final String BID_PRDLS_LIST = "bidPrdlsList";
	
	/**
	 * 입찰첨부문서 조회
	 */
	static final String BID_ATCH_DOC_LIST = "bidAtchDocList";
	
	/**
	 * 정정공고 등록 목록 조회
	 */
	static final String PBLANC_NTCN_INFO_LIST = "pblancNtcnInfoList";
	
	/**
	 * 입찰공고의견정보 조회
	 */
	static final String BID_PBLANC_OPINION_INFO_LIST = "bidPblancOpinionInfoList";
	
	/**
	 * 입찰공고의견정보 조회
	 */
	static final String BID_PBLANC_OPINION_INFO_LIST_TOTCNT = "bidPblancOpinionInfoListTotcnt";
	
	/**
	 * 입찰공고의견정보 상세
	 */
	static final String BID_PBLANC_OPINION_INFO_DETAIL = "bidPblancOpinionInfoDetail";
	
	/**
	 * 업체 정보 조회
	 */
	static final String ENTRPS_INFO_INQIRE = "entrpsinfoinqire";
	
	/**
	 * 공통 코드 목록 조회
	 */
	static final String CMMN_CD_LIST = "cmmnCdList";
	
	/**
	 * 공동 수급 업체 목록 조회
	 */
	static final String COPERTN_SDEN_LIST = "copertnSdenList";
	
	/**
	 * 입찰참가신청서 상세 조회
	 */
	static final String BID_PARTCPT_REQSTDOC_INQIRE = "bidPartcptReqstdocInqire";
	
	/**
	 * 공동수급협정서 상세 조회
	 */
	static final String COPERTN_SPLDMD_TRTY_OFE_INQIRE = "copertnSpldmdTrtyOfeInqire";
	
	/**
	 * 업체 입찰 기본 정보 조회
	 */
	static final String ENTRPS_BID_BASS_INFO_INQIRE = "entrpsBidBassInfoInqire";
	
	/**
	 * 입찰첨부문서 조회
	 */
	static final String BID_ATCH_DOC = "bidAtchDoc";
	
	/**
	 * 업체첨부문서 조회
	 */
	static final String ENTRPS_ATCH_DOC = "entrpsAtchDoc";
	
	/**
	 * 공동 수급 여부 체크
	 */
	static final String COPERTN_SPLDMD_AT_CECK = "copertnSpldmdAtCeck";
	
	/**
	 * 업체제재 정보
	 */
	static final String VEND_SNCT_LIST = "vendSnctList";
	
	/**
	 * 업체제재 정보
	 */
	static final String VEND_INFO = "vendInfo";
	

	/**
	 * <pre>
	 * 1. 개요 : 진행중인 입찰공고 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : inProgrsBidPblancListInqireWithPgng
	 * @date : 2015. 02. 16.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 16.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject inProgrsBidPblancListInqireWithPgng(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 진행중인 입찰공고 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : inProgrsBidPblancDetailInqire
	 * @date : 2015. 02. 16.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 16.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 * @throws SDBException 
	 */
	FwkTransferObject inProgrsBidPblancDetailInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 관심입찰 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : intrstBidRegist
	 * @date : 2015. 02. 23.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 23.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject intrstBidRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 관심입찰 삭제 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : intrstBidDelete
	 * @date : 2015. 9. 4.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 9. 4.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject intrstBidDelete(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 지문인식 모의공고 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : fngprtRecogImtPblancDetailInqire
	 * @date : 2015. 02. 23.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 23.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject fngprtRecogImtPblancDetailInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰참가신청서 작성 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptReqstdocWritngForm
	 * @date : 2015. 04. 09.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 09.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject bidPartcptReqstdocWritngForm(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰참가신청 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptReqstRegist
	 * @date : 2015. 04. 09.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 09.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 * @throws SDBException 
	 */
	FwkTransferObject bidPartcptReqstRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 업체 제재 여부 확인 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : entrpsPunshAtCnfirm
	 * @date : 2015. 9. 2.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 9. 2.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException
	 */
	
	FwkTransferObject entrpsPunshAtCnfirm(final FwkParameterMap parameterMap) ;
	
	/**
	 * <pre>
	 * 1. 개요 : 공동수급협정서 작성 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfeWritngForm
	 * @date : 2015. 04. 15.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 15.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject copertnSpldmdTrtyOfeWritngForm(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 공동수급협정서 제출
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfePresentn
	 * @date : 2015. 04. 15.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 15.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject copertnSpldmdTrtyOfePresentn(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 공동수급협정서 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfeUpdt
	 * @date : 2015. 04. 15.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 15.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject copertnSpldmdTrtyOfeUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가신청서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptReqstdocInqire
	 * @date : 2015. 2. 26.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 26.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	
	FwkTransferObject bidPartcptReqstdocInqire(final FwkParameterMap parameterMap) ; 
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 공동수급협정서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfeInqire
	 * @date : 2015. 04. 16.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 16.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	
	FwkTransferObject copertnSpldmdTrtyOfeInqire(final FwkParameterMap parameterMap); 
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 조달청 연계 체크
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : g2bCeck
	 * @date : 2015. 04. 16.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 16.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	
	String g2bCeck(final FwkParameterMap parameterMap); 
	
	FwkTransferObject bidNotiList(final FwkParameterMap parameterMap);
	FwkTransferObject bidNotiDetail(final FwkParameterMap parameterMap);
	void chckOk(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 제안서 제출 폼 이동
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prprcPresentnForm
	 * @date : 2019. 02. 21
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 21.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	FwkTransferObject prprcPresentnForm(final FwkParameterMap parameterMap);
}
