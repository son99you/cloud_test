package com.eunwoosoft.ipro.comm.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.comm.service
 * IproCommPopupService.java
 *
 * @Author : SungYoon_Ha
 * @Date   : 2017. 5. 30.
 *
 */
public interface IproCommPopupService {

	/**
	 * 담당자목록 Key
	 */
	static final String CHARGER_LIST = "chargerList";		
	/**
	 * 담당자목록 총건수
	 */
	static final String CHARGER_LIST_TOTCNT = "chargerListTotcnt";
	
	/**
	 * 협력업체목록 Key
	 */
	static final String CCPY_LIST = "ccpyList";		
	/**
	 * 협력업체목록 총건수
	 */
	static final String CCPY_LIST_TOTCNT = "ccpyListTotcnt";
	
	/**
	 * 사업부서 목록 Key
	 */
	static final String BSNSDEPT_LIST = "bsnsDeptList";		
	/**
	 * 사업부서목록 총건수
	 */
	static final String BSNSDEPT_LIST_TOTCNT = "bsnsDeptListTotcnt";
	
	/**
	 * 품목 목록 Key
	 */
	static final String PRDLST_LIST = "prdlstList";		
	/**
	 * 품목 목록 총건수
	 */
	static final String PRDLST_LIST_TOTCNT = "prdlstListTotcnt";
	
	/**
	 * 품목 대분류코드 목록
	 */
	static final String THSYCL_LIST_A = "thsyclListA";		
	/**
	 * 품목 중분류코드 목록
	 */
	static final String THSYCL_LIST_B = "thsyclListB";	
	static final String LAWORD_INQIRE_LIST = "lawordInqireList";
	
	static final String LAWORD_INQIRE_LIST_TOTCNT = "lawordInqireListTotcnt";
	
	/**
	 * 품목 소분류코드 목록
	 */
	static final String THSYCL_LIST_C = "thsyclListC";		
	
	/**
	 * 품목 분류코드별 목록
	 */
	static final String THSYCL_LIST = "thsyclList";		
	
	/**
	 * 품목 분류코드별 목록
	 */
	static final String SANCTN_INFO_INQIRE = "sanctnInfoInqire";		
	/**
	 * 계약목록 Key
	 */
	static final String CNTRCT_LIST = "cntrctList";		
	/**
	 * 계약목록 총건수
	 */
	static final String CNTRCT_LIST_TOTCNT = "cntrctListTotcnt";
	/**
	 * 첨부파일 정보 목록
	 */
	static final String ATCHMNFL_INFO_LIST = "atchmnflInfoList";
	/**
	 * 공통 첨부파일 정보(단건)
	 */
	static final String CMMN_FILE_INFO = "cmmnFileInfo";
	/**
	 * 유비레포트 상세 조회
	 *//*
	static final String UBI_RPT_DETAIL_INQIRE = "ubiRptDetailInqire";*/
	/**
	 * 공통 코드 목록
	 */
	static final String CMMN_CD_LIST = "cmmnCdList";
	/**
	 * 약정단체 목록 Key
	 */
	static final String ENGR_LIST = "engrList";		
	/**
	 * 약정단체 목록 총건수
	 */
	static final String ENGR_LIST_TOTCNT = "engrListTotcnt";

	static final String ENTRPS_INQIRE_LIST = "entrpsInqireList";
	static final String ENTRPS_INQIRE_NOT_LIST = "entrpsInqireNotInList";
		 
	static final String ENTRPS_INQIRE_LIST_TOTCNT = "entrpsInqireListTotcnt";
	static final String ENTRPS_INQIRE_NOT_LIST_TOTCNT = "entrpsInqireNotInListTotcnt";
	
	static final String FILE_SAMPLE_LIST = "fileSampleList";
	
	//자동첨부내역
	static final String CONT_FORM_FILE_LIST = "fileList";
	/**
	 * 품목 대분류코드 목록
	 */
	static final String ITEM_LIST_A = "itemListA";		
	
	/**
	 * 품목 중분류코드 목록
	 */
	static final String ITEM_LIST_B = "itemListB";
	
	/**
	 * 품목 소분류코드 목록
	 */
	static final String ITEM_LIST_C = "itemListC";		
	
	/**
	 * 품목등록 목록
	 */
	static final String ITEM_LIST = "itemList";		
	
	/**
	 * 품목등록 총건수
	 */
	static final String ITEM_LIST_TOTCNT = "itemListTotcnt";
	
	/**
	 * 품목등록 목록
	 */
	static final String POST_LIST = "postList";		
	
	/**
	 * 품목등록 총건수
	 */
	static final String POST_LIST_TOTCNT = "postListTotcnt";
	
	static final String DEPT_INQIRE_LIST = "deptInqireList";
	static final String DEPT_INQIRE_LIST_TOTCNT = "deptInqireListTotcnt";
	
	
	// 권한별메뉴관리 목록
	static final String MENU_AUTH_MGR_LIST = "menuAuthMgrList";

	// 권한별메뉴관리 목록 총 건수
	static final String MENU_AUTH_MGR_LIST_TOTCNT = "menuAuthMgrListTotcnt";
	
	static final String APPR_LINE_LIST = "apprLineList";
	
	static final String APPR_LINE_LIST_TOTCNT = "apprLineListTotcnt";	
	
	static final String CONT_FORM_LIST = "contFormList";
	
	static final String CONT_FORM_LIST_TOTCNT = "contFormListTotcnt";
	
	static final String CONT_FORM_DETAIL = "contFormDetail";
	
	static	final String PRCH_RQST_LIST = "prchRqstList";
	
	static	final String PRCH_RQST_LIST_TOTCNT = "prchRqstListTotcnt";
	
	static	final String PRCH_RQST_DETAIL = "prchRqstDetail";
	
	static	final String PRCH_RQST_ITEM_LIST = "prchRqstItemList";
	
	/*
	 * 수의계약사유 정보
	 */
	static final String PVRS_MST_LIST = "pvrsMstList";
	static final String PVRS_MST_LIST_TOTCNT = "pvrsMstListTotcnt";
	
	//업체그룹 정보
	static final String COMP_GROUP_DETAIL = "compGroupDetail";
	
	/*
	 * 프로그램 정보
	 */
	static final String PRGM_LIST = "prgmList";
	static final String PRGM_LIST_TOTCNT = "prgmListTotcnt";
	
	/*
	 * 디자인품명 목록
	 */
	static final String DSGN_LIST = "dsgnList";
	static final String DSGN_LIST_TOTCNT = "dsgnListTotcnt";
	
	/**
	 * 근로계약 대상자 목록 Key
	 */
	static final String LABORER_LIST = "laborerList";		
	/**
	 * 근로계약 대상자 목록 총건수
	 */
	static final String LABORER_LIST_TOTCNT = "laborerListTotcnt";	
	
	/**
	 * 주요취급품목 목록
	 */
	static final String MJR_HNDL_ITEM_LIST = "mjrHndlItemList";
	
	/**
	 * 주요취급품목 목록 총 건수
	 */
	static final String MJR_HNDL_ITEM_LIST_TOTCNT = "mjrHndlItemListTotcnt";
	
	/**
	 * 일괄처리가능공고대기 목록 
	 */
	static final String UNI_ANNC_PSBL_LIST = "uniAnncPsblList";
	
	/**
	 * 일괄처리가능공고대기 총 건수
	 */	
	static final String UNI_ANNC_PSBL_LIST_TOTCNT = "uniAnncPsblListTotcnt";
	
	/**
	 * 공급품목 아이템 공고 리스트 
	 */
	static final String VEND_ITEM_ANNC_LIST = "vendItemAnncList";
	
	/**
	 * 공급품목 아이템 공고 리스트 총건수
	 */	
	static final String VEND_ITEM_ANNC_LIST_TOTCNT = "vendItemAnncListTotcnt";
	
	/**
	 * 공급품목 아이템 공고 리스트 총건수
	 */	
	static final String VEND_ITEM_NO_LIST = "vendItemNoList";
	
	/**
	 * 열람업체 리스트
	 */	
	static final String BID_VEND_OPEN_LIST = "bidVendOpenList";
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 취소 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : canclCurstatRegist
	 * @date : 2016.11.15
	 * @author : 은우소프트 이주연
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016.11.15	은우소프트 이주연				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkTransferObject canclCurstatRegist(final FwkParameterMap parameterMap);
	
	//업체조회 목록
	FwkTransferObject entrpsInqireList(final FwkParameterMap parameterMap);
	
	FwkTransferObject fileSampleLIst(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 이전 자동첨부파일 내역 보기
	 *	</pre>
	 *
	 * @method Name : contFormFileList
	 * @Author : joo
	 * @Date   : 2020. 9. 2.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 2.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param parameterMap
	 * @return
	 *
	 */
	FwkTransferObject contFormFileList(final FwkParameterMap parameterMap);
	
	//견적업체조회 목록
	FwkTransferObject prpoEntrpsInqireList(final FwkParameterMap parameterMap);

	// 해당계약의 계약업체를 제외한 업체 목록 조회 팝업
	FwkTransferObject entrpsInqireNotInList(final FwkParameterMap parameterMap);
 	/**
	 * <pre>
	 * 1. 개요 : 담당자목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : chargerListInqireWithPgng
	 * @date : 2018. 02. 20.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 20.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject chargerListInqireWithPgng(final FwkParameterMap parameterMap);

	//근거법령조회 목록
	FwkTransferObject lawordInqireList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 품목등록 목록 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.service.IproCommPopupService.java
	 * @Method : itemListInqireWithPgng
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject itemListInqireWithPgng(final FwkParameterMap parameterMap);
	
	//부서조회 목록
	FwkTransferObject deptInqireList(final FwkParameterMap parameterMap);
	
	FwkTransferObject dataBaseList(final FwkParameterMap parameterMap);
	
	
	// 평가메일 보내기
	FwkTransferObject meetSendMail(final FwkParameterMap parameterMap);
	
	//월별부서조회 목록
	FwkTransferObject deptYMList(final FwkParameterMap parameterMap);
	
	
	
	//반려사유 등록
	FwkTransferObject returnRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 권한별 메뉴관리 목록 팝업 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.service.IproCommPopupService.java
	 * @Method : menuAuthMgrListWithPgng
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 28. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject menuAuthMgrListWithPgng(final FwkParameterMap parameterMap);
	
 	/**
	 * <pre>
	 * 1. 개요 : 결재선조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : apprLineListInqireWithPgng
	 * @date : 2018. 03. 15.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 15.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject apprLineListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 계약서식 상세 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.service.IproCommPopupService.java
	 * @Method : contFormDetail
	 * @author : chanil_Hong
	 * @date : 2018. 5. 11. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject contFormDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.service.IproCommPopupService.java
	 * @Method : prchRqstList
	 * @author : sanghoon_joen
	 * @date : 2018. 7. 19. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject prchRqstList(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.service.IproCommPopupService.java
	 * @Method : prchRqstDetail
	 * @author : sanghoon_joen
	 * @date : 2018. 7. 20. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject prchRqstDetail(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 수의계약사유정보 조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.service.IproCommPopupService.java
	 * @Method : getPvrsMstList
	 * @author : 맹경열
	 * @date : 2018. 08. 02. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject getPvrsMstList(final FwkParameterMap parameterMap);
	/**
	 * <pre>
	 * 1.개요 : 업체그룹관리 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.service.IproCommPopupService.java
	 * @Method : compGroupRegist
	 * @author : hci
	 * @date : 2018. 08. 02. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject compGroupRegist(final FwkParameterMap parameterMap);
	/**
	 * <pre>
	 * 1.개요 : 업체그룹관리 상세 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.service.IproCommPopupService.java
	 * @Method : compGroupDetail
	 * @author : hci
	 * @date : 2018. 08. 02. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject compGroupDetail(final FwkParameterMap parameterMap);
	/**
	 * <pre>
	 * 1.개요 : 업체그룹관리 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.service.IproCommPopupService.java
	 * @Method : compGroupUpdate
	 * @author : hci
	 * @date : 2018. 08. 02. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject compGroupUpdate(final FwkParameterMap parameterMap);
	/**
	 * <pre>
	 * 1.개요 : 업체그룹관리 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.service.IproCommPopupService.java
	 * @Method : compGroupDelete
	 * @author : hci
	 * @date : 2018. 08. 02. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject compGroupDelete(final FwkParameterMap parameterMap);
	
 	/**
	 * <pre>
	 * 1. 개요 : 프로그램목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prgmInqireList
	 * @date : 2018. 09. 13.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 09. 13.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject prgmInqireList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 :  BIS 발주 디자인 목록_페이징
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : dngnListInqireWithPgng
	 * @date : 2018. 11. 21.
	 * @author : 은우소프트 맹경열	
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 11. 21.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 협력업체목록 조회조건
	 * @return - 협력업체목록
	 */
	FwkTransferObject dngnListInqireWithPgng(final FwkParameterMap parameterMap);
	
 	/**
	 * <pre>
	 * 1. 개요 : 근로계약 대상자 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : laborerListInqireWithPgng
	 * @date : 2018. 12. 28.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 12. 28.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	FwkTransferObject laborerListInqireWithPgng(final FwkParameterMap parameterMap);

	// 주요취급품목 목록
	FwkTransferObject mjrHndlItemListWithPgng(final FwkParameterMap parameterMap);
	
 	/**
	 * <pre>
	 * 1. 개요 : 일괄공고 대상 공고대기 목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : uniAnncItemListWithPgng
	 * @date : 2019. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 08.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */	
	FwkTransferObject uniAnncItemListWithPgng(final FwkParameterMap parameterMap);
	
 	/**
	 * <pre>
	 * 1. 개요 : 공급품목관리 대상 입찰공고 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidVendItemRefListWithPgng
	 * @date : 2019. 05. 28.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 05. 28.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */	
	FwkTransferObject bidVendItemRefListWithPgng(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 업체 열람 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.service.IproCommPopupService.java
	 * @Method : bidVendOpenList
	 * @author : JanDi_Eun
	 * @date : 2019. 7. 22. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject bidVendOpenList(final FwkParameterMap parameterMap);
	
	FwkTransferObject postListInqireWithPgng(final FwkParameterMap parameterMap);

	FwkTransferObject estmInfoList(final FwkParameterMap parameterMap);

	FwkTransferObject estmHistList(final FwkParameterMap parameterMap);
	
	// 평가서식목록
	FwkTransferObject estmFrmPopupList(final FwkParameterMap parameterMap);

	// 평가대상정보 첨부파일 상세 팝업
	FwkTransferObject estmObjFileView(final FwkParameterMap parameterMap);
	
	// 화상회의정보 첨부파일 상세 팝업
	FwkTransferObject estmVidoFileView(final FwkParameterMap parameterMap);

	//평가위원 위원평가 팝업
	FwkTransferObject estmCmtmScrUpdtFrm(final FwkParameterMap parameterMap);

	//평가위원 위원평가 팝업> 수정
	FwkTransferObject estmCmtmScrUpdt(final FwkParameterMap parameterMap);
	
	// 평가위원평가표 팝업
	FwkTransferObject estmCmtmEstmFrmDetail(final FwkParameterMap parameterMap);
	
	// 평가위원평가표 팝업
	FwkTransferObject estmCmtmEstmFrmSave(final FwkParameterMap parameterMap);
	
	// 평가위원평가표 팝업
	FwkTransferObject estmCmtmEstmFrmBDetail(final FwkParameterMap parameterMap);
	
	// 평가위원평가표 팝업에서 평가담당자가 저장
	FwkTransferObject estmCmtmEstmFrmBSave(final FwkParameterMap parameterMap);
	
	FwkTransferObject estmFrmDetail(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmSeMngFrmDetail(final FwkParameterMap parameterMap) throws Exception;
	
	FwkTransferObject estmFrmUpdt(final FwkParameterMap parameterMap) throws Exception;
	

	FwkTransferObject estmCmtmList(final FwkParameterMap parameterMap);
	
	//내부 로그인화면 공지사항 팝업
	FwkTransferObject noticeListPopup(final FwkParameterMap parameterMap);
	FwkTransferObject noticePopup(final FwkParameterMap parameterMap);
	
	FwkTransferObject mtngPrtcRqstList(final FwkParameterMap parameterMap);

	// 평가위원 첨부파일 상세 팝업
	FwkTransferObject estmCmtmFileView(final FwkParameterMap parameterMap);

	FwkTransferObject cmtmMngCrtrDetail(final FwkParameterMap parameterMap);

	
	// 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 기업정보
	FwkTransferObject estmObjImstarsMainView(final FwkParameterMap parameterMap);

	// 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 상세내용
	FwkTransferObject estmObjImstarsDetailView(final FwkParameterMap parameterMap);

	// 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 첨부파일
	FwkTransferObject estmObjImstarsFileView(final FwkParameterMap parameterMap);

	// 평가완료현황_평가위원 - 상세 : 설문조사 팝업
	FwkTransferObject estmCmtmSvyDetail(final FwkParameterMap parameterMap);

	// 평가완료현황_평가위원 - 상세 : 설문조사 저장
	FwkTransferObject estmCmtmSvySave(final FwkParameterMap parameterMap);
}
