package com.eunwoosoft.ipro.ebid.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;



/**
 * 예가관리 > 예가관리 DAO
 * <pre>
 * oda.iep.elbi.dao 
 *    |_ IepElbiPrdprcManageDao.java
 * 
 * </pre>
 * @date : 2015. 01. 23. 오후 08:30:10
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
public interface IproEbidPrprcManageDao {

	/**
	 * <pre>
	 * 1. 개요 : 제안/규격서 검토 목록조회_페이징 
	 * 2. 처리내용 : 
	 * 
	 * </pre>
	 * @Method Name : selectPrprcManageListWithPgng
	 * @date : 2019. 02. 18.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 18.		은우소프트 멩경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return 
	 */
	List<FwkDataEntity> selectPrprcManageListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 제안/규격서 검토 목록총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectPrprcManageListTotcnt
	 * @date : 2019. 02. 18.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 18.		은우소프트 멩경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	int selectPrprcManageListTotcnt(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 제안/규격서 검토 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectPrprcManageListWithPgng
	 * @date : 2019. 02. 18.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 18.		은우소프트 멩경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return 
	 */
	FwkDataEntity selectBidPrprcInfoDetail(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 제안/규격서 검토 업체목록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidPrprcEntrpsList
	 * @date : 2019. 02. 19.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 19.		은우소프트 멩경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return 
	 */
	List<FwkDataEntity> selectBidPrprcEntrpsList(final FwkParameterMap parameterMap);
	
	
}