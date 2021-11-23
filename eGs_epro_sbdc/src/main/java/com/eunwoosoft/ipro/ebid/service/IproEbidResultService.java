package com.eunwoosoft.ipro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;



/**
 * 전자입찰 > 입찰결과 서비스
 * <pre>
 * com.eunwoosoft.ipro.ebid.service 
 *    |_ IproEbidResultService.java
 * 
 * </pre>
 * @date : 2015. 04. 02. 오후 13:55:11
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
public interface IproEbidResultService {

	/**
	 * 입찰결과 목록 Key
	 */
	static final String BID_RESULT_LIST = "bidResultList";
	
	/**
	 * 입찰결과 목록 총건수
	 */
	static final String BID_RESULT_LIST_TOTCNT = "bidResultListTotcnt";
	
	/**
	 * 유찰 낙찰 예정자 목록
	 */
	
	static final String FIB_SCSBID_PREARNGER_LIST = "fibScsbidPrearngerList";
	
	/**
	 * 입찰 물품 목록
	 */
	
	static final String BID_THNG_LIST = "bidThngList";
	
	/**
	 * 복수 예가 목록
	 */
	
	static final String COMPNO_PRDPRC_LIST = "compnoPrdprcList";
	
	/**
	 * 제안서 조회
	 */
	static final String ENATPA_INQIRE = "enatpaInqire";
	
	/**
	 * 입찰라운드목록
	 */
	static final String BID_RESULT_ROUND_LIST = "bidResultRoundList";
	
	/**
	 * 입찰마스터 목록
	 */
	static final String BID_RESULT_VEND_MST_LIST = "bidResultVendMstList";	
	

	
	/**
	 * <pre>
	 * 1. 개요 : 입찰결과 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidResultListInqireWithPgng
	 * @date : 2015. 04. 02.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 02.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject bidResultListInqireWithPgng(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰결과 목록 엑셀 다운로드 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidResultListInqire
	 * @date : 2015. 4. 2.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 2.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject bidResultListInqire(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰결과 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidResultDetailInqire
	 * @date : 2015. 04. 02.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 02.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject bidResultDetailInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 적격심사 평가결과 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : properJdgmnEvlInqire
	 * @date : 2015. 4. 3.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 3.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject properJdgmnEvlInqire(final FwkParameterMap parameterMap);


	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰결과 상세 - 결격사유 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidResultBrdoResnInqire
	 * @date : 2015. 4. 6.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 6.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject bidResultBrdoResnInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰결과 상세 - 품목별 세부내역 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prdlstAcctoDetailBidDtlsInqire
	 * @date : 2015. 8. 18.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 8. 18.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject prdlstAcctoDetailBidDtlsInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 복수예가 목록 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : compnoPrdprcListInqire
	 * @date : 2016. 02. 29.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 02. 29.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject compnoPrdprcListInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체투찰정도 수기등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertG2BVendInfo
	 * @date : 2018. 10. 12.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 10. 12.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject insertG2BVendInfo(final FwkParameterMap parameterMap);	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 지급각서 보기 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.service.IproEbidResultService.java
	 * @Method : grntDetail
	 * @author : sanghoon_joen
	 * @date : 2019. 1. 2. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject grntDetail(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 청렴이행각서 보기 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.service.IproEbidResultService.java
	 * @Method : cleanDetail
	 * @author : sanghoon_joen
	 * @date : 2019. 1. 2. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject cleanDetail(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 구매관리이관
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.service.IproEbidResultService.java
	 * @Method : pvctSendUpdt
	 * @author : 맹경열
	 * @date : 2019. 03. 05. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject pvctSendUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 라운드 리스트 정보 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidRoundList
	 * @date : 2019. 03. 05.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
     *  2019. 03. 05.       은우소프트 맹경열              최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject bidRoundList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 라운드 마스터 정보 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : BidVendMstList
	 * @date : 2019. 03. 05.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *  2019. 03. 05.       은우소프트 맹경열              최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject BidVendMstList(final FwkParameterMap parameterMap);


	/**
	 * 
	 * <pre>
	 * 1.개요 : 라운드 정보 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.service.IproEbidResultService.java
	 * @Method : roundInfoDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 7. 5. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject roundInfoDetail(final FwkParameterMap parameterMap);

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 품목별 업체 투찰금액 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.service.IproEbidResultService.java
	 * @Method : tndrItemAmtList
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 13. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject tndrItemAmtList(final FwkParameterMap parameterMap);
}
