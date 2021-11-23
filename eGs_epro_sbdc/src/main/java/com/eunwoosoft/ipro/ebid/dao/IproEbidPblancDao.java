package com.eunwoosoft.ipro.ebid.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 전자입찰 > 입찰공고 DAO
 * <pre>
 * com.eunwoosoft.ipro.ebid.dao 
 *    |_ IproEbidPblancDao.java
 * 
 * </pre>
 * @date : 2015. 01. 14. 오전 11:40:10
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
public interface IproEbidPblancDao {

	/**
	 * <pre>
	 * 1. 개요 : 입찰공고록조회_페이징 
	 * 2. 처리내용 : 
	 * 
	 * </pre>
	 * @Method Name : selectBidPblancListWithPgng
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
	 * @return 
	 */
	List<FwkDataEntity> selectBidPblancListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰공고목록총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectBidPblancListTotcnt
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
	 * @return
	 */
	int selectBidPblancListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 공고알림정보 등록
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : insertPblancNtcnInfoRegist
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
	 * @return
	 */
	void insertPblancNtcnInfoRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰계획 진행 상태 수정
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updatebidPlanProgrsSttusUpdt
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
	 * @return
	 */
	void updatebidPlanProgrsSttusUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 이력조회 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectBiProgHistList
	 * @date : 2015. 02. 11.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 11.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectBiProgHistList(final FwkParameterMap parameterMap);
	FwkDataEntity selectBiProgHist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰공고의견정보 목록 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectBidPblancOpinionInfoList
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
	 * @return
	 */
	List<FwkDataEntity> selectBidNotiList(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 입찰공고의견정보 목록 총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectBidPblancOpinionInfoListTotcnt
	 * @date : 2015. 02. 13.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 13.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int selectBidPblancOpinionInfoListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰공고 의견정보 상세
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectBidPblancOpinionInfoDetail
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
	 * @return
	 */
	FwkDataEntity selectBidNotiDetail(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰공고 의견답변 수정
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updateBidPblancOpinionAnswerUpdt
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
	 * @return
	 */
	void updateBidNotiUpdt(final FwkParameterMap parameterMap);
	void deleteBidNotiDel(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 업체 입찰 기본 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectEntrpsBidBassInfoInqire
	 * @date : 2015. 4. 16.
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 16.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectEntrpsBidBassInfoInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 공동수급업체 목록
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectCopertnSdenList
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
	 * @return
	 */
	List<FwkDataEntity> selectCopertnSdenList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 첨부문서 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectEntrpsAtchDoc
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
	 * @return
	 */
	FwkDataEntity selectEntrpsAtchDoc(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 관심입찰 등록한 업체 목록
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectIntrstBidEntrpsList
	 * @date : 2016. 01. 05.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 01. 05.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectIntrstBidEntrpsList(final FwkParameterMap parameterMap);

	FwkDataEntity selectBiMstDetail(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectBiItemList(final FwkParameterMap parameterMap);
	FwkDataEntity selectBiFileDetail(final FwkParameterMap parameterMap);
	
	void insertBiMstRegist(final FwkParameterMap parameterMap);
	void insertBiRoundRegist(final FwkParameterMap parameterMap);
	void deleteBiItme(final FwkParameterMap parameterMap);
	void insertBiItmeRegist(final FwkParameterMap parameterMap);
	void insertIfBiItmeRegist(final FwkParameterMap parameterMap);
	void insertBiFileRegist(final FwkParameterMap parameterMap);
	void deleteBiProgHistDel(final FwkParameterMap parameterMap);
	void insertBiProgHistRegist(final FwkParameterMap parameterMap);
	
	void deleteBiMst(final FwkParameterMap parameterMap);
	
	void updateBiMstCmpl(final FwkParameterMap parameterMap);
	void updateBiMstBiMst(final FwkParameterMap parameterMap);
	void updateBiRoundBiRound(final FwkParameterMap parameterMap);
	void insertBiItemBiItem(final FwkParameterMap parameterMap);
	void updateBiFileBiFile(final FwkParameterMap parameterMap);
	void insertBiProgHistBiProgHist(final FwkParameterMap parameterMap);
	
	void updateBiMstStChng(final FwkParameterMap parameterMap);
	void updateBiRoundStChng(final FwkParameterMap parameterMap);
	
	void updateBiVendMstReTndr(final FwkParameterMap parameterMap);
	
	void insertBiMstNextNgr(final FwkParameterMap parameterMap);
	void insertBiRoundNextNgr(final FwkParameterMap parameterMap);
	void insertBiItemNextNgr(final FwkParameterMap parameterMap);
	void insertBiFileNextNgr(final FwkParameterMap parameterMap);
	void insertBiEstcMstNextNgr(final FwkParameterMap parameterMap);
	void insertBiPlrlEstcNextNgr(final FwkParameterMap parameterMap);
	void insertBiVendMstNextNgr(final FwkParameterMap parameterMap);
	void insertBiBrfsVendNextNgr(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectVendMstHistList(final FwkParameterMap parameterMap);
	
	void updateVendMstSbidN(final FwkParameterMap parameterMap);
	void updateBiopVendSbidN(final FwkParameterMap parameterMap);
	void updateDatpVendSbidN(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 제안서 파일 복사 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertBiVendFileSelectInsert
	 * @date : 2019. 02. 28.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 28.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	void insertBiVendFileSelectInsert(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰업체 복사
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertBiVendMstSelectInsert
	 * @date : 2019. 03. 04.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 04.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	void insertBiVendMstSelectInsert(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가마스터 복사
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertBiEstcMstSelectInsert
	 * @date : 2019. 03. 04.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 04.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	void insertBiEstcMstSelectInsert(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 복수예가 복사
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertBiPlrlEstcSelectInsert
	 * @date : 2019. 03. 20.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 20.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	void insertBiPlrlEstcSelectInsert(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 수의전환 입찰마스터 복사
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertBiMstToPrvt
	 * @date : 2019. 03. 19.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 19.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	void insertBiMstToPrvt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 수의전환 입찰라운드 복사
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertBiRoundToPrvt
	 * @date : 2019. 03. 19.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 19.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	void insertBiRoundToPrvt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 수의전환 입찰예가 복사
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertBiEstcToPrvt
	 * @date : 2019. 03. 19.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 19.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	void insertBiEstcToPrvt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 수의전환 입찰물품 복사
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertBiItemToPrvt
	 * @date : 2019. 03. 19.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 19.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	void insertBiItemToPrvt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 수의전환 입찰파일 복사
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertBiFileToPrvt
	 * @date : 2019. 03. 19.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 19.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */		
	void insertBiFileToPrvt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체입찰/제안서 담당자정보
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVendDtlUsrInfo
	 * @date : 2019. 05. 13.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 05. 13.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return 
	 */
	List<FwkDataEntity> selectVendDtlUsrInfo(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 지명업체 담당자정보
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectSelectVendUsrInfo
	 * @date : 2019. 05. 13.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 05. 13.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return 
	 */
	List<FwkDataEntity> selectSelectVendUsrInfo(final FwkParameterMap parameterMap);	
	
	
}