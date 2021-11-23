package com.eunwoosoft.opro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 전자입찰 > 나의 입찰공고 서비스
 * <pre>
 * com.eunwoosoft.opro.ebid.service 
 *    |_ OproEbidMyPblancService.java
 * 
 * </pre>
 * @date : 2015. 03. 10. 오전 10:47:11
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
public interface OproEbidMyPblancService {

	/**
	 * 나의 입찰공고 목록 Key
	 */
	static final String MY_BID_PBLANC_LIST = "myBidPblancList";
	
	/**
	 * 나의 입찰공고 목록 총건수
	 */
	static final String MY_BID_PBLANC_LIST_TOTCNT = "myBidPblancListTotcnt";

	/**
	 * 입찰공고상세
	 */
	static final String MY_BID_PBLANC_DETAIL = "myBidPblancDetail";

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
	 * 업체정보 조회
	 */
	static final String ENTRPS_INFO = "entrpsInfo";

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
	 * @return {@link TransferObject}
	 */
	FwkTransferObject myBidPblancListInqireWithPgng(final FwkParameterMap parameterMap);

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
	 * @return {@link TransferObject}
	 * @throws SDBException 
	 */
	FwkTransferObject myBidPblancDetailInqire(final FwkParameterMap parameterMap) ;
	
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
	 * @return {@link TransferObject}
	 */
	FwkTransferObject intgtyFlflMmrdInqire(final FwkParameterMap parameterMap);
	
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
	 * @return void
	 */
	FwkTransferObject bidPartcptRegist(final FwkParameterMap parameterMap);
	
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
	 * @return {@link TransferObject}
	 */
	FwkTransferObject bidPblancOpinionRegistForm(final FwkParameterMap parameterMap);

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
	 * @return {@link TransferObject}
	 * @throws SDBException 
	 */
	FwkTransferObject bidPblancOpinionUpdt(final FwkParameterMap parameterMap) ;
	
	FwkTransferObject bidDcRegistForm(final FwkParameterMap parameterMap) ;
	void bidDcRegist(final FwkParameterMap parameterMap) ;
	void bidDcDel(final FwkParameterMap parameterMap) ;
}
