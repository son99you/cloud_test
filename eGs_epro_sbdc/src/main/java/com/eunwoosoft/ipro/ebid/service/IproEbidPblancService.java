package com.eunwoosoft.ipro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 전자입찰 > 입찰공고 서비스
 * <pre>
 * com.eunwoosoft.ipro.ebid.service 
 *    |_ IproEbidPblancService.java
 * 
 * </pre>
 * @date : 2015. 01. 14. 오전 11:39:21
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
public interface IproEbidPblancService {

	/**
	 * 입찰공고목록 Key
	 */
	static final String BID_PBLANC_LIST = "bidPblancList";
	
	/**
	 * 입찰공고목록 총건수
	 */
	static final String BID_PBLANC_LIST_TOTCNT = "bidPblancListTotcnt";

	/**
	 * 입찰공고상세
	 */
	static final String BID_PBLANC_DETAIL = "bidPblancDetail";

	/**
	 * 입찰품목 목록 조회
	 */
	static final String BID_PRDLS_LIST = "bidPrdlsList";
	
	/**
	 * 입찰지명업체 조회
	 */
	static final String BID_NMFPC_ENTRPS_LIST = "bidNmfpcEntrpsList";
	
	/**
	 * 입찰첨부문서 조회
	 */
	static final String BID_ATCH_DOC_LIST = "bidAtchDocList";
	
	/**
	 * 정정공고 사유 조회
	 */
	static final String PBLANC_PF30_HIST_LIST = "pblancPf30HistList";
	
	static final String PBLANC_PF40_HIST_LIST = "pblancPf40HistList";
	
	/**
	 * 입찰공고의견정보 조회
	 */
	static final String BID_NOTI_LIST = "bidNotiList";
	
	/**
	 * 입찰공고의견정보 조회
	 */
	static final String BID_PBLANC_OPINION_INFO_LIST_TOTCNT = "bidPblancOpinionInfoListTotcnt";
	
	/**
	 * 입찰공고의견정보 상세
	 */
	static final String BID_NOTI_DETAIL = "bidNotiDetail";

	/**
	 * 업체 입찰 기본 정보 조회
	 */
	static final String ENTRPS_BID_BASS_INFO_INQIRE = "entrpsBidBassInfoInqire";
	
	/**
	 * 공동 수급 업체 목록 조회
	 */
	static final String COPERTN_SDEN_LIST = "copertnSdenList";
	
	/**
	 * 관심 입찰 업체 목록 조회
	 */
	static final String INTRST_BID_ENTRPS_LIST = "intrstBidEntrpsList";
	/**
	 * <pre>
	 * 1. 개요 : 입찰공고목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPblancListInqireWithPgng
	 * @date : 2015. 02. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject bidPblancListInqireWithPgng(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 입찰공고 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPblancDetailInqire
	 * @date : 2015. 02. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 * @throws SDBException 
	 */
	FwkTransferObject bidPblancDetailInqire(final FwkParameterMap parameterMap) ;
	
	/**
	 * <pre>
	 * 1. 개요 : 정정공고 등록 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updtPblancRegistForm
	 * @date : 2015. 02. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject updtPblancRegistForm(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 정정공고 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updtPblancRegist
	 * @date : 2015. 02. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject updtPblancRegist(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 취소공고 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : canclPblancRegist
	 * @date : 2015. 02. 11.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 11.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	void canclPblancRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 취소공고 등록 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : canclPblancRegistForm
	 * @date : 2015. 02. 11.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 11.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject canclPblancRegistForm(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰공고 의견답변 등록 폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidNotiRegistForm
	 * @date : 2015. 02. 12.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 12.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 * @throws SDBException 
	 */
	FwkTransferObject bidNotiRegistForm(final FwkParameterMap parameterMap) ;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.service.IproEbidPblancService.java
	 * @Method : bidNotiUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 10. 12. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject bidNotiUpdt(final FwkParameterMap parameterMap) ;
	void bidNotiDel(final FwkParameterMap parameterMap) ;
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  공동수급협정서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfeInqire
	 * @date : 2015. 6. 26.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 6. 26.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject copertnSpldmdTrtyOfeInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  관심입찰 등록한 업체 목록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : intrstBidEntrpsList
	 * @date : 2016. 01. 05.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 01. 05.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject intrstBidEntrpsList(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 유찰 등록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidPblancService.java
	 * @Method : fibRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 9. 
	 * @param parameterMap
	 */
	FwkTransferObject fibRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.service.IproEbidPblancService.java
	 * @Method : roundUpateForm
	 * @author : sanghoon_joen
	 * @date : 2018. 10. 5. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject roundUpdtForm(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.service.IproEbidPblancService.java
	 * @Method : roundUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 10. 5. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject roundUpdt(final FwkParameterMap parameterMap);
	
	void updtAnncSttusChange(final FwkParameterMap parameterMap);
	
	void updtAnncDelete(final FwkParameterMap parameterMap);
	
	FwkTransferObject vendTndrList(final FwkParameterMap parameterMap);
	
	void reBid(final FwkParameterMap parameterMap);
	
	FwkTransferObject ebidApprSendCrrc(final FwkParameterMap parameterMap);
	
	FwkTransferObject ebidApprSendCncl(final FwkParameterMap parameterMap);
	
	FwkTransferObject canclPblancDel(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 :  관심입찰 등록한 업체 목록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : tndrTimeUpdt
	 * @date : 2019. 03. 15.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 15.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject tndrTimeUpdt(final FwkParameterMap parameterMap);	
}
