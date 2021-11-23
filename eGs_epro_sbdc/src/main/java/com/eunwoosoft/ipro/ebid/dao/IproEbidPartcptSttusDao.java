package com.eunwoosoft.ipro.ebid.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 전자입찰 > 입찰참가현황 DAO
 * <pre>
 * com.eunwoosoft.ipro.ebid.dao 
 *    |_ IproEbidPartcptSttusDao.java
 * 
 * </pre>
 * @date : 2015. 02. 23. 오후 9:04:22
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
public interface IproEbidPartcptSttusDao {

	/**
	 * <pre>
	 * 1. 개요 : 입찰참가현황 목록조회_페이징 
	 * 2. 처리내용 : 
	 * 
	 * </pre>
	 * @Method Name : selectBidDcPeoListWithPgng
	 * @date : 2015. 02. 23.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return 
	 */
	List<FwkDataEntity> selectBidPartcptSttusListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰참가현황 목록총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectBidDcPeoListTotcnt
	 * @date : 2015. 02. 23.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int selectBidPartcptSttusListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰정보 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidInfoDetail
	 * @date : 2015. 2. 23.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectBidInfoDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 참가업체 목록 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidPartcptEntrpsList
	 * @date : 2015. 2. 23.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	List<FwkDataEntity> selectBidPartcptEntrpsList(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰포기신청서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidAbandnReqstdocInqire
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
	 */
	
	
	FwkDataEntity selectBidAbandnReqstdocInqire(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가신청서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidPartcptReqstdocInqire
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
	 */
	
	FwkDataEntity selectBidPartcptReqstdocInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 청렴이행각서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectIntgtyFlflMmrdInqire
	 * @date : 2015. 2. 27.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 27.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectIntgtyFlflMmrdInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰보증정보 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidAssrncInfoInqire
	 * @date : 2015. 3. 26.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 26.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	FwkDataEntity selectBidAssrncInfoInqire(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 :	업체보증정보 목록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectEntrpsGrntyInfoList
	 * @date : 2015. 3. 26.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 26.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	List<FwkDataEntity> selectEntrpsGrntyInfoList(final FwkParameterMap parameterMap);
	

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰보증정보 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertBidAssrncInfoRegist
	 * @date : 2015. 3. 30.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 30.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void insertBidAssrncInfoRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰보증정보 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteBidAssrncInfo
	 * @date : 2015. 3. 30.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 30.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	void deleteBidAssrncInfo(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 조달의뢰 담당자 정보
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidClientInfo
	 * @date : 2015. 3. 30.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 30.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	
	FwkDataEntity selectBidClientInfo(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 역경매 업체 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidPartcptSttusDao.java
	 * @Method : selectDatpVendList
	 * @author : sanghoon_joen
	 * @date : 2018. 12. 14. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectDatpVendList(final FwkParameterMap parameterMap);
}