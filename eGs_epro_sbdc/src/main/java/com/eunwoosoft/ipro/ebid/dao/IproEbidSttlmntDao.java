package com.eunwoosoft.ipro.ebid.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 *  입찰관리 > 수의시담
 * com.eunwoosoft.ipro.ebid.dao
 * IproEbidDcPeoDao.java
 *
 * @Author : chanil_Hong
 * @Date   : 2018. 3. 19.
 *
 */
public interface IproEbidSttlmntDao {

	/**
	 * 
	 * <pre>
	 * 1.개요 :수의시담 목록조회_페이징 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.iproEbidSttlmntDao.java
	 * @Method : selectVltrnPrvstlListInqireWithPgng
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectVltrnPrvstlListInqireWithPgng(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 :수의시담 상세조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.iproEbidSttlmntDao.java
	 * @Method : selectVltrnPrvstlDetail
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectVltrnPrvstlDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : :수의시담 목록 총건수
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.iproEbidSttlmntDao.java
	 * @Method : selectVltrnPrvstlListTotcnt
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 * @return
	 */
	int selectVltrnPrvstlListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 신규 마스터 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.iproEbidSttlmntDao.java
	 * @Method : insertVltrnPrvstlNewRegist
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 */
	void insertVltrnPrvstlNewRegist(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 업체등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.iproEbidSttlmntDao.java
	 * @Method : insertVltrnPrvstlEntrpsRegist
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 */
	void insertVltrnPrvstlEntrpsRegist(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 진행이력 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.iproEbidSttlmntDao.java
	 * @Method : insertVltrnPrvstlHistRegist
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 */
	void insertVltrnPrvstlHistRegist(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.iproEbidSttlmntDao.java
	 * @Method : insertVltrnPrvstlFileRegist
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 */
	void insertVltrnPrvstlFileRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.iproEbidSttlmntDao.java
	 * @Method : updateVltrnPrvstlUpdt
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 */
	void updateVltrnPrvstlUpdt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 업체삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.iproEbidSttlmntDao.java
	 * @Method : deleteVltrnPrvstlEntrps
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 */
	void deleteVltrnPrvstlEntrps(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 상태업데이트
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.iproEbidSttlmntDao.java
	 * @Method : updateVltrnPrvstlPrstUpdt
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 */
	void updateVltrnPrvstlPrstUpdt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 내용 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertVltrnPrvstlCnRegist
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 */
	
	void insertVltrnPrvstlCnRegist(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  수의시담 진행 목록 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectVltrnPrvstlProgrsList
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 */
	
	List<FwkDataEntity> selectVltrnPrvstlProgrsList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  수의시담 견적완료조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectTepnEsremaDetail
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 */
	
	FwkDataEntity selectTepnEsremaDetail(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1. 개요 :  수의시담 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : deleteVltrnPrvstlUpdt
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 */
	
	void deleteVltrnPrvstlUpdt(final FwkParameterMap parameterMap);
	
}