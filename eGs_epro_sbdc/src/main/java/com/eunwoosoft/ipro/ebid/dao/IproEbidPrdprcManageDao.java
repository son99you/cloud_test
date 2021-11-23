package com.eunwoosoft.ipro.ebid.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;



/**
 * 예정가격 DAO
 * <pre>
 * com.eunwoosoft.ipro.ebid.dao 
 *    |_ IproEbidPrdprcManageDao.java
 * 
 * </pre>
 * @date : 2015. 01. 23. 오후 08:30:10
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
public interface IproEbidPrdprcManageDao {

	/**
	 * <pre>
	 * 1. 개요 : 예가등록요청 목록조회_페이징 
	 * 2. 처리내용 : 
	 * 
	 * </pre>
	 * @Method Name : selectPrdprcRegReqManageListWithPgng
	 * @date : 2019. 03. 07.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 07.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return 
	 */
	List<FwkDataEntity> selectPrdprcRegReqManageListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가등록요청 목록총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectPrdprcRegReqManageListTotcnt
	 * @date : 2019. 03. 07.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 07.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	int selectPrdprcRegReqManageListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가관리 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectPrdprcManageListWithPgng
	 * @date : 2019. 03. 07.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 07.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return 
	 */
	List<FwkDataEntity> selectPrdprcManageListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가관리 목록총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectPrdprcManageListTotcnt
	 * @date : 2019. 03. 07.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 07.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	int selectPrdprcManageListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 복수예가목록 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectCompnoPrdprcList
	 * @date : 2015. 02. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectCompnoPrdprcList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가생성 등록
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : insertPrdprcCreatRegist
	 * @date : 2015. 02. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertPrdprcCreatRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 복수예가 기준정보 목록 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectcompnoPrdprcStdrInfoList
	 * @date : 2015. 02. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectcompnoPrdprcStdrInfoList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 등록 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectPrdprcRegistInqire
	 * @date : 2015. 02. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectPrdprcRegistInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 등록
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : insertPrdprcRegist
	 * @date : 2015. 02. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertPrdprcRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의 예가 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateVltrrnPrdprcRegist
	 * @date : 2015. 6. 18.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 6. 18.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void updateVltrrnPrdprcRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의 시담 입찰 정보 상세 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVltrnPrvstlBidInfoDetail
	 * @date : 2015. 6. 18.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 6. 18.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectVltrnPrvstlBidInfoDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의 시담 예가 등록 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVltrnPrdprcRegistInqire
	 * @date : 2015. 6. 18.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 6. 18.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectVltrnPrdprcRegistInqire(final FwkParameterMap parameterMap);
	

	/**
	 * <pre>
	 * 1. 개요 : 예가항목 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectPrdprcIemInqire
	 * @date : 2015. 06. 30.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 06. 30.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectPrdprcIemInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 정보 등록
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : insertPrdprcInfoRegist
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertPrdprcInfoRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 정보 수정
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updatePrdprcInfoUpdt
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updatePrdprcInfoUpdt(final FwkParameterMap parameterMap);
	
	void updatePrdprcPrceEncUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 상세 등록
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : insertPrdprcDetailRegist
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertPrdprcDetailRegist(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 예가 상세 삭제
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : deletePrdprcDetail
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void deletePrdprcDetail(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 정보 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectPrdprcInfoInqire
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectPrdprcInfoInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 상세 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectPrdprcDetailInqire
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectPrdprcDetailInqire(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 예가 정보 등록
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : insertPrdprcInfoRegist
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertVltrnPrdprcInfoRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 정보 수정
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updatePrdprcInfoUpdt
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updateVltrnPrdprcInfoUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 상세 등록
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : insertPrdprcDetailRegist
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertVltrnPrdprcDetailRegist(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 예가 상세 삭제
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : deletePrdprcDetail
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void deleteVltrnPrdprcDetail(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 정보 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectPrdprcInfoInqire
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectVltrnPrdprcInfoInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 상세 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectPrdprcDetailInqire
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectVltrnPrdprcDetailInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 등록 삭제
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : deletePrdprcRegist
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void deletePrdprcRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 등록 삭제
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : deletePrdprcRegist
	 * @date : 2015. 07. 01.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 07. 01.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void deleteCompnoPrdprc(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 등록 삭제
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectBidEstcInfoDetail
	 * @date : 2019. 03. 07.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 07.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectBidEstcInfoDetail(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 기초금액 업데이트
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updateBidBaseAmt
	 * @date : 2019. 03. 15.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 15.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	void updateBidBaseAmt(final FwkParameterMap parameterMap);
	
}