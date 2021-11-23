package com.eunwoosoft.opro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;




/**
 * 전자입찰 > 나의 참가신청 현황 서비스
 * <pre>
 * com.eunwoosoft.opro.ebid.service 
 *    |_ OproEbidMyPartcptReqstSttusService.java
 * 
 * </pre>
 * @date : 2015. 03. 10. 오전 10:47:11
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
public interface OproEbidMyPartcptReqstSttusService {

	/**
	 * 나의 참가신청 현황 목록 Key
	 */
	static final String MY_PARTCPT_REQST_STTUS_LIST = "myPartcptReqstSttusList";
	
	/**
	 * 나의 참가신청 현황 목록 총건수
	 */
	static final String MY_PARTCPT_REQST_STTUS_LIST_TOTCNT = "myPartcptReqstSttusListTotcnt";

	/**
	 * 참가신청 현황상세
	 */
	static final String MY_PARTCPT_REQST_STTUS_DETAIL = "myPartcptReqstSttusDetail";

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
	 * 업체정보 조회
	 */
	static final String ENTRPS_INFO = "entrpsInfo";
	
	/**
	 * 입찰참가포기신청서 조회
	 */
	static final String BID_PARTCPT_ABANDN_REQSTDOC = "bidPartcptAbandnReqstdoc";
	
	/**
	 * 복수예가정보 목록 조회
	 */
	static final String COMPNO_PRDPRC_LIST = "compnoPrdprcList";
	
	/**
	 * 복수예가정보 목록 조회
	 */
	static final String ENTRPS_PARTCPTN_DETAIL_INQIRE = "entrpsPartcptnDetailInqire";

	/**
	 * 투찰 정보
	 */
	static final String BID_PARTCPT_TNDR_INFO = "bidPartcptTndrInfo";
	
	/**
	 * 투찰 복수예가 정보
	 */
	static final String BID_PARTCPT_ESSE_LIST = "bidPartcptEsseList";
	
	/**
	 * 업체 참가서류 파일 그룹 번호 정보
	 */
	static final String BID_DO01_FILE_INFO = "bidDo01FileInfo";
	
	/**
	 * 업체 입찰서 파일 그룹 번호 정보
	 */
	static final String BID_DO04_FILE_INFO = "bidDo04FileInfo";
	
	/**
	 * 업체 입찰서 파일 그룹 번호 정보
	 */
	static final String BID_PARTCPT_GRNT_INFO = "bidPartcptGrntInfo";	

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
	 * @return {@link TransferObject}
	 */
	FwkTransferObject myPartcptReqstSttusListInqireWithPgng(final FwkParameterMap parameterMap);

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
	 * @return {@link TransferObject}
	 * @throws SDBException 
	 */
	FwkTransferObject myPartcptReqstSttusDetailInqire(final FwkParameterMap parameterMap) ;
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰참가 포기 신청서 등록
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
	 * @return void
	 */
	void bidPartcptAbandnReqstdocRegist(final FwkParameterMap parameterMap);

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
	 * @return {@link TransferObject}
	 * @throws SDBException 
	 */
	FwkTransferObject bipaPresentnForm(final FwkParameterMap parameterMap) ;
	
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
	 * @return {@link TransferObject}
	 * @throws SDBException 
	 */
	FwkTransferObject bipaPresentnRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰 가능 여부 검증
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPosblAtVrify
	 * @date : 2015. 04. 17.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 17.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 * @throws SDBException 
	 */
	FwkTransferObject bidPosblAtVrify(final FwkParameterMap parameterMap) ;
	
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
	FwkTransferObject bidPosblPrprcVrify(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰포기등록폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.opro.ebid.service.OproEbidMyPartcptReqstSttusService.java
	 * @Method : bidAbndRegistForm
	 * @author : sanghoon_joen
	 * @date : 2018. 8. 27. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject bidAbndRegistForm(final FwkParameterMap parameterMap) ;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 역경매등록폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.opro.ebid.service.OproEbidMyPartcptReqstSttusService.java
	 * @Method : datpRegistForm
	 * @author : sanghoon_joen
	 * @date : 2018. 12. 14. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject datpRegistForm(final FwkParameterMap parameterMap) ;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 역경매등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.opro.ebid.service.OproEbidMyPartcptReqstSttusService.java
	 * @Method : datpRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 12. 14. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject datpRegist(final FwkParameterMap parameterMap) ;

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
	FwkTransferObject grntInfoUpdate(final FwkParameterMap parameterMap);
	
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
	FwkTransferObject prprcPresentnRegist(final FwkParameterMap parameterMap);	
	
}
