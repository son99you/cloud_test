package com.eunwoosoft.ipro.comm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.comm.dao
 * IproCommPopupDao.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2018. 2. 12.
 *
 */
public interface IproCommPopupDao {

	/**
	 * <pre>
	 * 1. 개요 : 업체 목록조회 
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectEntrpsInqireList
	 * @date : 2016. 10. 25.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 10. 25.		은우소프트 손연우			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap 
	 * @return 
	 */
	 List<FwkDataEntity> selectEntrpsInqireList(final FwkParameterMap parameterMap);
	 List<FwkDataEntity> selectEntrpsInqireNotInList(final FwkParameterMap parameterMap);
	 
	 List<FwkDataEntity> selectFileSampleInqireList(final FwkParameterMap parameterMap);
	 FwkDataEntity selectFileSampleRecmCdId(final FwkParameterMap parameterMap);
	 
	 //자동첨부파일 내역 조회
	 List<FwkDataEntity> selectContFormFileList(final FwkParameterMap parameterMap);
	 
	 /**
	  * <pre>
	  * 1. 개요 : 견적업체 목록조회 
	  * 2. 처리내용 : 
	  *         
	  * </pre>
	  * @Method Name : selectPrpoEntrpsInqireList
	  * @date : 2016. 10. 25.
	  * @author : 은우소프트 홍찬일
	  * @history : 
	  *	-----------------------------------------------------------------------
	  *	변경일				작성자								변경내용  
	  *	----------- ------------------- ---------------------------------------
	  *	2016. 10. 25.		은우소프트 홍찬일			최초 작성 
	  *	-----------------------------------------------------------------------
	  * 
	  * @param parameterMap 
	  * @return 
	  */
	 List<FwkDataEntity> selectPrpoEntrpsInqireList(final FwkParameterMap parameterMap);
	 
	 /**
	  * <pre>
	  * 1. 개요 : 견적업체 목록 총건수
	  * 2. 처리내용 : 
	  *         
	  * </pre>
	  * @Method Name : selectPrpoEntrpsInqireListTotcnt
	  * @date : 2016. 10. 25.
	  * @author : 은우소프트 홍찬일
	  * @history : 
	  *	-----------------------------------------------------------------------
	  *	변경일				작성자								변경내용  
	  *	----------- ------------------- ---------------------------------------
	  *	2016. 10. 25.		은우소프트 홍찬일			최초 작성 
	  *	-----------------------------------------------------------------------
	  * 
	  * @param parameterMap 
	  * @return 
	  */
	 int selectPrpoEntrpsInqireListTotcnt(final FwkParameterMap parameterMap);
	 
	 /**
	 * <pre>
	 * 1. 개요 : 업체 목록총건수조회 
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectEntrpsInqireListTotcnt
	 * @date : 2016. 10. 25.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 1. 7.		은우소프트 손연우			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap 
	 * @return  
	 */
	int selectEntrpsInqireListTotcnt(final FwkParameterMap parameterMap);
	int selectEntrpsInqireNotInListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 담당자목록조회_페이징 
	 * 2. 처리내용 : 
	 *     SELECT :
	 *         T1.XX
	 *         , T1.X2
	 *     FROM TB_XX T1
	 *     WHERE T1.XX LIKE '%#{P_XX}%'
	 *         AND T1.XX2_NM LIKE '%#{P_XX2}%' 
	 * </pre>
	 * @Method Name : selectChargerListWithPgng
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
	 * @return
	 */
	List<FwkDataEntity> selectChargerListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 담당자목록총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectChargerListTotcnt
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
	 * @return
	 */
	int selectChargerListTotcnt(final FwkParameterMap parameterMap);	
	
	/**
	 * <pre>
	 * 1. 개요 : 근거법령 목록조회 
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectLawordInqireList
	 * @date : 2016. 10. 25.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 10. 25.		은우소프트 손연우			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap 
	 * @return 
	 */
	
	 List<FwkDataEntity> selectLawordInqireList(final FwkParameterMap parameterMap);
	
	
	
	/**
	 * <pre>
	 * 1. 개요 : 법령근거 목록총건수조회 
	 * 2. 처리내용 : 
	 *         
	 * </pre>
	 * @Method Name : selectLawordInqireListTotcnt
	 * @date : 2016. 10. 25.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 1. 7.		은우소프트 손연우			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap 
	 * @return 
	 */
	int selectLawordInqireListTotcnt(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectDeptInqireList(final FwkParameterMap parameterMap);
	
	int selectDataBaseListTotcnt(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectDataBaseList(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmSendDetail(final FwkParameterMap parameterMap);
	
	
	
	int selectDeptInqireListTotcnt(final FwkParameterMap parameterMap);
	
	
	List<FwkDataEntity> selectDeptYMList(final FwkParameterMap parameterMap);
	int selectDeptYMListTotcnt(final FwkParameterMap parameterMap);
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectItemListWithPgng
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectItemListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 품목등록 목록 총건수
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectItemListTotcnt
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	int selectItemListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 권한별메뉴관리 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectMenuAuthMgrListWithPgng
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 28. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectMenuAuthMgrListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 권한별메뉴관리 목록 총 건수
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectMenuAuthMgrListTotcnt
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 28. 
	 * @param parameterMap
	 * @return
	 */
	int selectMenuAuthMgrListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 결재선조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectApprLineListWithPgng
	 * @author : 맹경열
	 * @date : 2018. 03. 15. 
	 * @param parameterMap
	 * @return
	 */	
	List<FwkDataEntity> selectApprLineListWithPgng(final FwkParameterMap parameterMap);
	int selectApprLineListTotcnt(final FwkParameterMap parameterMap);	
	
	/**
	 * <pre>
	 * 1.개요 : 계약서식조회상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectContFormDetail
	 * @author : 홍찬일
	 * @date : 2018. 03. 15. 
	 * @param parameterMap
	 * @return
	 */	
	FwkDataEntity selectContFormDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectPrchRqstListWithPang
	 * @author : sanghoon_joen
	 * @date : 2018. 7. 19. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectPrchRqstListWithPang(final FwkParameterMap parameterMap);
	int selectPrchRqstListTotCnt(final FwkParameterMap parameterMap);	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectPrchRqstDetail
	 * @author : sanghoon_joen
	 * @date : 2018. 7. 20. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectPrchRqstDetail(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectPrchRqstItemList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 :  수의계약사유 조회 페이징 및 카운트
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectPvrsMstListWithPgng
	 * @author : 맹경열
	 * @date : 2018. 08. 02. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectPvrsMstListWithPgng(final FwkParameterMap parameterMap);
	int selectPvrsMstListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : compGroupRegist
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : compGroupRegist
	 * @author : 홍찬일
	 * @date : 2018. 03. 15. 
	 * @param parameterMap
	 * @return
	 */	
	void compGroupRegist(final FwkParameterMap parameterMap);
	/**
	 * <pre>
	 * 1.개요 : compGroupDetail
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : compGroupDetail
	 * @author : 홍찬일
	 * @date : 2018. 03. 15. 
	 * @param parameterMap
	 * @return
	 */	
	FwkDataEntity compGroupDetail(final FwkParameterMap parameterMap);
	/**
	 * <pre>
	 * 1.개요 : compGroupUpdate
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : compGroupUpdate
	 * @author : 홍찬일
	 * @date : 2018. 03. 15. 
	 * @param parameterMap
	 * @return
	 */	
	void compGroupUpdate(final FwkParameterMap parameterMap);
	/**
	 * <pre>
	 * 1.개요 : compGroupMstDelete
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : compGroupDelete
	 * @author : 홍찬일
	 * @date : 2018. 03. 15. 
	 * @param parameterMap
	 * @return
	 */	
	void compGroupMstDelete(final FwkParameterMap parameterMap);
	/**
	 * <pre>
	 * 1.개요 : compGroupListDelete
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : compGroupDelete
	 * @author : 홍찬일
	 * @date : 2018. 03. 15. 
	 * @param parameterMap
	 * @return
	 */	
	void compGroupListDelete(final FwkParameterMap parameterMap);

 	/**
	 * <pre>
	 * 1. 개요 : 프로그램목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectPrgmListWithPgng
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
	List<FwkDataEntity> selectPrgmListWithPgng(final FwkParameterMap parameterMap);
	int selectPrgmListTotCnt(final FwkParameterMap parameterMap);		
	
	/**
	 * <pre>
	 * 1. 개요 :  BIS 발주 디자인 목록
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectBisDsgnListWithPgng
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
	List<FwkDataEntity> selectBisDsgnListWithPgng(final FwkParameterMap parameterMap);		
	int selectBisDsgnListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 :  근로계약대상자 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectLaborerListWithPgng
	 * @date : 2018. 12. 28.
	 * @author : 은우소프트 맹경열	
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 12. 28.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap - 협력업체목록 조회조건
	 * @return - 협력업체목록
	 */
	List<FwkDataEntity> selectLaborerListWithPgng(final FwkParameterMap parameterMap);		
	int selectLaborerListTotcnt(final FwkParameterMap parameterMap);

	// 주요취급품목 목록
	List<FwkDataEntity> selectMjrHndlItemListWithPgng(final FwkParameterMap parameterMap);

	// 주요취급품목 목록 총 건수
	int selectMjrHndlItemListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 :  일괄공고 대상 공고대기 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUniAnncItemListWithPgng
	 * @date : 2019. 03. 08.
	 * @author : 은우소프트 맹경열	
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 08.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap - 협력업체목록 조회조건
	 * @return - 협력업체목록
	 */
	List<FwkDataEntity> selectUniAnncItemListWithPgng(final FwkParameterMap parameterMap);		
	int selectUniAnncItemListTotcnt(final FwkParameterMap parameterMap);
	
	
	/**
	 * <pre>
	 * 1. 개요 :  일괄견적 대상 공고대기 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUniEstItemListWithPgng
	 * @date : 2019. 08. 06.
	 * @author : 은우소프트 하성윤	
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 08. 06.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap - 일괄견적 대상 공고대기 조회
	 * @return -일괄견적 대상 공고목록
	 */
	List<FwkDataEntity> selectUniEstItemListWithPgng(final FwkParameterMap parameterMap);		
	int selectUniEstItemListTotcnt(final FwkParameterMap parameterMap);
	
	
	/**
	 * <pre>
	 * 1. 개요 :  일괄수의시담 대상 공고대기 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectUniPrvtItemListWithPgng
	 * @date : 2019. 08. 06.
	 * @author : 은우소프트 하성윤	
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 08. 06.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap - 일괄수의시담 대상 공고대기 조회
	 * @return -일괄수의시담 대상 공고목록
	 */
	List<FwkDataEntity> selectUniPrvtItemListWithPgng(final FwkParameterMap parameterMap);		
	int selectUniPrvtItemListTotcnt(final FwkParameterMap parameterMap);
	
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
	 * @param parameterMap - 협력업체목록 조회조건
	 * @return - 협력업체목록
	 */
	List<FwkDataEntity> bidVendItemRefListWithPgng(final FwkParameterMap parameterMap);		
	int bidVendItemRefListTotcnt(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1.개요 : 입찰 업체 열람 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectBidVendOpenList
	 * @author : JanDi_Eun
	 * @date : 2019. 7. 22. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectBidVendOpenList(final FwkParameterMap parameterMap);
	
	
	/**
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectPostListWithPgng
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectPostListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 품목등록 목록 총건수
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectPostListTotcnt
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	int selectPostListTotcnt(final FwkParameterMap parameterMap);
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가정보 목록 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectEstmInfoList
	 * @author : JanDi_Eun
	 * @date : 2021. 3. 30. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectEstmInfoList(final FwkParameterMap parameterMap);
	int selectEstmInfoListTotCnt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 평가진행이력 목록 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.dao.IproCommPopupDao.java
	 * @Method : selectEstmHistList
	 * @author : JanDi_Eun
	 * @date : 2021. 3. 30. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectEstmHistList(final FwkParameterMap parameterMap);
	int selectEstmHistListTotCnt(final FwkParameterMap parameterMap);
	
	// 평가서식목록 조회
	List<FwkDataEntity> selectEstmFrmPopupList(final FwkParameterMap parameterMap);
	int selectEstmFrmPopupListTotcnt(final FwkParameterMap parameterMap);

	List<FwkDataEntity> selectVidoObjList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectVidoCmtmList(final FwkParameterMap parameterMap);

	FwkDataEntity selectEstmObjFileView(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmVidoFileView(final FwkParameterMap parameterMap);

	//평가위원 위원평가
	FwkDataEntity selectEstmCmtmScrDetail(final FwkParameterMap parameterMap);
	void selectEstmCmtmScrUpdt(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmCmtmEstmFrmDetail(final FwkParameterMap parameterMap);
	FwkDataEntity selectEstmObjDetail(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmCmtmEstmFrmItemList(final FwkParameterMap parameterMap);
	
	// 평가서식상세
	FwkDataEntity selectEstmFrmDetail(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmSeMngFrmDetail(final FwkParameterMap parameterMap);
	
	// 평가서식항목 목록
	List<FwkDataEntity> selectEstmFrmItemList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectEstmSeMngFrmItemList(final FwkParameterMap parameterMap);
	
	
	FwkDataEntity selectEstmSeProcdDetail(final FwkParameterMap parameterMap);
	
	// 평가서식항목 삭제
	void deleteEstmFrmItemDelt(final FwkParameterMap parameterMap);
	
	// 평가서식항목 등록
	void insertEstmFrmMstItemRegist(final FwkParameterMap parameterMap);
	

	// 평가위원평가대상점수 merge Into
	void mergeIntoEstmCmtmObjScr(final FwkParameterMap parameterMap);
	
	// 평가위원이 평가항목별 평가점수 merge Into
	void mergeIntoEstmObjScr(final FwkParameterMap parameterMap);

	void insertEstmObjScrHist(final FwkParameterMap parameterMap);	//평가대상 점수 이력
	
	List<FwkDataEntity> selectEstmCmtmMngPoolList(final FwkParameterMap parameterMap);
	
	
//	
//	FwkDataEntity selectEstmCmtmEstmFrmADetail(final FwkParameterMap parameterMap);
//	List<FwkDataEntity> selectEstmCmtmEstmFrmItemAList(final FwkParameterMap parameterMap);
//	
//	FwkDataEntity selectEstmCmtmEstmFrmBDetail(final FwkParameterMap parameterMap);
//	List<FwkDataEntity> selectEstmCmtmEstmFrmItemBList(final FwkParameterMap parameterMap);
//	
	
	//내부로그인_공지사항
	List<FwkDataEntity> selectNoticeList(final FwkParameterMap parameterMap);
	int selectNoticeListTotCnt(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectNoticeDetail(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmCmtmAllList(final FwkParameterMap parameterMap);	
	
	FwkDataEntity selectEstmObjImstarsMainDetail(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmObjImstarsCmpnySelng(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmObjImstarsCmpnyFileView(final FwkParameterMap parameterMap);

	FwkDataEntity selectEstmCmtmCrtrMstDetail(final FwkParameterMap parameterMap);
	FwkDataEntity selectEstmObjImstarsDetailView(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmObjImstarsFileView(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmObjImstarsDetailFileList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmObjImstarsGoodsCrtfcList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmObjImstarsGoodssSelngList(final FwkParameterMap parameterMap);
	
	
	List<FwkDataEntity> selectEstmCmtmSvyFrmItemList(final FwkParameterMap parameterMap);
	
	void mergeIntoEstmSvyScr(final FwkParameterMap parameterMap);
	
}
