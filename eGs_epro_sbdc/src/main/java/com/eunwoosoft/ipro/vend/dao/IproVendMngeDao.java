package com.eunwoosoft.ipro.vend.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * com.eunwoosoft.ipro.vend.dao
 * IproVendMngeDao.java
 *
 * @Author : jandi_Eun
 * @Date   : 2018. 2. 21.
 *
 */
public interface IproVendMngeDao {
	
	/**
	 * <pre>
	 * 1.개요 : 협력사현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : selectVendMngeListInqireWithPgng
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectVendMngeListInqireWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 협력사현황 목록 총 건수
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : selectVendMngeListTotcnt
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 */
	int selectVendMngeListTotcnt(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1.개요 : 업체등록번호 조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : selectEntrpsRegistNoMax
	 * @author : jandi_Eun
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectEntrpsRegistNoMax(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 소싱그룹명 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : selectSgCodeName
	 * @author : jandi_Eun
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectSgCodeName(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1.개요 : TMUR_ENPA 테이블 INSERT - 업체마스터 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : insertVendMngeRegist
	 * @author : jandi_Eun
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void insertVendMngeRegist(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1.개요 : T_CO_BANK 테이블 INSERT - 은행정보 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : insertVendMngeBankRegist
	 * @author : jandi_Eun
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void insertVendMngeBankRegist(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1.개요 : T_CO_VENDOR_SG 테이블 INSERT - 협력업체소싱그룹 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : insertVendMngeSgCodeRegist
	 * @author : jandi_Eun
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void insertVendMngeSgCodeRegist(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1.개요 : T_CO_VENDOR_USER 테이블 INSERT - 업체담당자 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : insertVendMngeUserRegist
	 * @author : jandi_Eun
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void insertVendMngeUserRegist(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1.개요 : T_CO_ITEM 테이블 INSERT - 업체품목 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : insertVendMngeItemRegist
	 * @author : jandi_Eun
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void insertVendMngeItemRegist(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1.개요 : T_CO_CTT_ACQS 테이블 INSERT - 인증자격취득정보 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : insertVendMngeCttAcqsRegist
	 * @author : jandi_Eun
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void insertVendMngeCttAcqsRegist(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1.개요 : T_CO_VENDOR_FILE 테이블 INSERT - 첨부파일 등록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : insertVendMngeFileRegist
	 * @author : jandi_Eun
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void insertVendMngeFileRegist(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1.개요 : 업체마스터, 은행정보 조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : selectVendMngeDetail
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectVendMngeDetail(final FwkParameterMap parameterMap);   // TMUR_ENPA, T_CO_BANK SELECT
	
	/**
	 * <pre>
	 * 1.개요 : 협력업체소싱그룹 조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : selectVendMngeSgCodeList
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectVendMngeSgCodeList(final FwkParameterMap parameterMap);  // T_CO_VENDOR_SG SELECT
	
	/**
	 * <pre>
	 * 1.개요 : 업체담당자 조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : selectVendMngeUserList
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectVendMngeUserList(final FwkParameterMap parameterMap);   // T_CO_VENDOR_USER SELECT
	
	/**
	 * <pre>
	 * 1.개요 : 업체품목 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : selectVendMngeItemList
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectVendMngeItemList(final FwkParameterMap parameterMap);   // T_CO_ITEM SELECT
	
	/**
	 * <pre>
	 * 1.개요 : 인증자격취득정보 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : selectVendMngeCttAcqsList
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectVendMngeCttAcqsList(final FwkParameterMap parameterMap);   // T_CO_CTT_ACQS SELECT
	
	/**
	 * <pre>
	 * 1.개요 : 업체파일 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : selectVendMngeFile
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectVendMngeFile(final FwkParameterMap parameterMap);   // T_CO_VENDOR_FILE SELECT

	/**
	 * <pre>
	 * 1.개요 : 계약실적 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : selectVendMngeCntrctList
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectVendMngeCntrctList(final FwkParameterMap parameterMap);   // TECT_COMA SELECT
	
	/**
	 * <pre>
	 * 1.개요 : 입찰정보 조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : selectVendMngeEbidList
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectVendMngeEbidList(final FwkParameterMap parameterMap);   // TEBI_BIPLMA SELECT
	
	/**
	 * <pre>
	 * 1.개요 : 업체마스터 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : updateVendMngeUpdt
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void updateVendMngeUpdt(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1.개요 : 은행정보 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : deleteVendMngeBank
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void deleteVendMngeBank(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1.개요 : 협력업체소싱그룹 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : deleteVendMngeSgCode
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void deleteVendMngeSgCode(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1.개요 : 업체담당자 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : deleteVendMngeUser
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void deleteVendMngeUser(final FwkParameterMap parameterMap) throws Exception;
	
	/**
	 * <pre>
	 * 1.개요 : 업체품목 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : deleteVendMngeItem
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void deleteVendMngeItem(final FwkParameterMap parameterMap) throws Exception;
	/**
	 * <pre>
	 * 1.개요 : 인증자격취득정보 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : deleteVendMngeCttAcqs
	 * @author : jandi_Eun
	 * @date : 2018. 2. 26. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void deleteVendMngeCttAcqs(final FwkParameterMap parameterMap) throws Exception;
	/**
	 * <pre>
	 * 1.개요 : 업체제재 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : insertVendSnctRegist
	 * @author : chanil_Hong
	 * @date : 2018. 6. 4. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void insertVendSnctRegist(final FwkParameterMap parameterMap) throws Exception;
	/**
	 * <pre>
	 * 1.개요 : 업체제재 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : deleteVendSnct
	 * @author : chanil_Hong
	 * @date : 2018. 6. 4. 
	 * @param parameterMap
	 * @throws Exception
	 */
	void deleteVendSnct(final FwkParameterMap parameterMap) throws Exception;
	/**
	 * <pre>
	 * 1.개요 : 업체제재 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.vend.dao.IproVendMngeDao.java
	 * @Method : selectVendSnctList
	 * @author : chanil_Hong
	 * @date : 2018. 6. 4. 
	 * @param parameterMap
	 * @throws Exception
	 */
	List<FwkDataEntity> selectVendSnctList(final FwkParameterMap parameterMap) throws Exception;
	
}