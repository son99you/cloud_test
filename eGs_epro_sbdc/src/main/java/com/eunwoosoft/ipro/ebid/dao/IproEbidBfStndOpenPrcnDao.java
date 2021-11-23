package com.eunwoosoft.ipro.ebid.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 전자입찰 > 입찰설명회 DAO
 * <pre>
 * com.eunwoosoft.ipro.ebid.dao 
 *    |_ IproEbidBfStndOpenPrcnDao.java
 * 
 * </pre>
 * @date : 2015. 02. 13. 오후 1:27:30
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
public interface IproEbidBfStndOpenPrcnDao {

	/**
	 * <pre>
	 * 1. 개요 : 사전규격공개진행 목록조회_페이징 
	 * 2. 처리내용 : 
	 * 
	 * </pre>
	 * @Method Name : selectBfStndOpenPrcnListWithPgng
	 * @param parameterMap
	 * @return 
	 */
	List<FwkDataEntity> selectBfStndOpenPrcnListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 사전규격공개진행 목록총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectBfStndOpenPrcnListTotCnt
	 * @param parameterMap
	 * @return
	 */
	int selectBfStndOpenPrcnListTotCnt(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectBfStndOpenPrcnDetail(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectBfStndOpenItemList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectBfStndOpenFile(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectBfStndRtnRsn(final FwkParameterMap parameterMap);
	
	void updateBfanStat(final FwkParameterMap parameterMap);
	
	void insertBfStndOpenProgHist(final FwkParameterMap parameterMap);
	
	//UPDATE
	void updateBfStndOpen(final FwkParameterMap parameterMap);
	
	void deleteBfStndOpenItem(final FwkParameterMap parameterMap);
	
	void insertBfStndOpenItem(final FwkParameterMap parameterMap);
	
	void deleteBfStndOpenFile(final FwkParameterMap parameterMap);
	void deleteBfStndOpenEtcFile(final FwkParameterMap parameterMap);
	
	void insertBfStndOpenFile(final FwkParameterMap parameterMap);
	void insertBfStndOpenEtcFile(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectBfStndOpenExcelList(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectBfStndAuthYn(final FwkParameterMap parameterMap);
	
}