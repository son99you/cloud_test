package com.eunwoosoft.opro.ebid.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 전자입찰 > 나의 입찰결과 DAO
 * <pre>
 * com.eunwoosoft.opro.ebid.dao 
 *    |_ OproEbidMyResultDao.java
 * 
 * </pre>
 * @date : 2015. 04. 02. 오후 02:01:30
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
public interface OproEbidMyResultDao {

	/**
	 * <pre>
	 * 1. 개요 : 나의 입찰결과 목록조회_페이징 
	 * 2. 처리내용 : 
	 * 
	 * </pre>
	 * @Method Name : selectMyBidResultListWithPgng
	 * @date : 2015. 03. 12.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 12.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return 
	 */
	List<FwkDataEntity> selectMyBidResultListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 나의 입찰결과 목록총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectMyBidResultListTotcnt
	 * @date : 2015. 03. 12.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 12.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int selectMyBidResultListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 입찰결과 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMyBidResultDetail
	 * @date : 2015. 2. 13.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 13.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectMyBidResultDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  복수예가 목록 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCompnoPrdprcList
	 * @date : 2015. 3. 12.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 12.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectCompnoPrdprcList(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 협상 낙찰 예정자 목록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectNtatScsbidPrearngerList
	 * @date : 2015. 3. 16.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 16.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectNtatScsbidPrearngerList(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 역격매 업체 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.opro.ebid.dao.OproEbidMyResultDao.java
	 * @Method : selectDatpVendList
	 * @author : sanghoon_joen
	 * @date : 2018. 12. 27. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectDatpVendList(final FwkParameterMap parameterMap);
	
}