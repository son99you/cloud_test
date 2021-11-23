package com.eunwoosoft.ipro.ebid.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.ebid.dao
 * IproEbidPlanDao.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2018. 2. 20.
 *
 */
public interface IproEbidProconDao {
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidProconDao.java
	 * @Method : selectBidPlanAprvlListWithPgng
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 2. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectBidPlanAprvlListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 목록 총개수
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidProconDao.java
	 * @Method : selectBidPlanListTotcnt
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 2. 
	 * @param parameterMap
	 * @return
	 */
	int selectBidPlanAprvlListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 정보 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidProconDao.java
	 * @Method : selectBidPlanAprvlInfoDetail
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 2. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectBidPlanAprvlInfoDetail(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 품목 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidProconDao.java
	 * @Method : selectBidPlanAprvlPrdlstList
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 2. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectBidPlanAprvlPrdlstList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 참여 업체 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidProconDao.java
	 * @Method : selectBidPlanAprvlNmfpcEntrpsList
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 2. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectBidPlanAprvlNmfpcEntrpsList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 첨부문서 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidProconDao.java
	 * @Method : selectBidPlanAprvlAtchDocList
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 2. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectBidPlanAprvlAtchDocList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 마스터 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : updateBidPlanUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void updateBidPlanAprvlUpdt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 시간 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : updateBidSchdulUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void updateBidPlanAprvlSchdulUpdt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 참여 업체 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidNmfpcEntrps
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteBidPlanAprvlNmfpcEntrps(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 품목 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidPrdlst
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteBidPlanAprvlPrdlst(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 첨부문서 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidAtchDoc
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteBidPlanAprvlAtchDoc(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 참여 업체 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : insertBidNmfpcEntrpsRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertBidPlanAprvlNmfpcEntrpsRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 품목 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : insertBidPrdlstRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertBidPlanAprvlPrdlstRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 첨부문서 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : insertBidAtchDocRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertBidPlanAprvlAtchDocRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 예가 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deletePrdprc
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deletePlanAprvlPrdprc(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 복수예가 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteCompnoPrdprc
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deletePlanAprvlCompnoPrdprc(final FwkParameterMap parameterMap);
}