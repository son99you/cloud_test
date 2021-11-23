package com.eunwoosoft.opro.comm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.opro.comm.dao
 * OproCommPopupDao.java
 *
 * @Author : JuYeon_Lee
 * @Date   : 2018. 3. 26.
 *
 */
public interface OproCommPopupDao {
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.dao.OproCommPopupDao.java
	 * @Method : selectEntrpsInqireList
	 * @author : sanghoon_joen
	 * @date : 2018. 4. 4. 
	 * @param parameterMap
	 * @return
	 */
	 List<FwkDataEntity> selectEntrpsInqireList(final FwkParameterMap parameterMap);
	 
	 /**
	  * 
	  * <pre>
	  * 1.개요 : 업체 목록
	  * 2.처리내용 : 
	  * </pre>
	  * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.dao.OproCommPopupDao.java
	  * @Method : selectEntrpsInqireListTotcnt
	  * @author : sanghoon_joen
	  * @date : 2018. 4. 4. 
	  * @param parameterMap
	  * @return
	  */
	 int selectEntrpsInqireListTotcnt(final FwkParameterMap parameterMap);
	 
	 List<FwkDataEntity> selectDeptInqireList(final FwkParameterMap parameterMap);
	 int selectDeptInqireListTotcnt(final FwkParameterMap parameterMap);
	 
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
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.dao.OproCommPopupDao.java
	 * @Method : selectItemListWithPgng
	 * @author : 
	 * @date : 2020.08.28
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectItemListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 품목등록 목록 총건수
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.dao.OproCommPopupDao.java
	 * @Method : selectItemListTotcnt
	 * @author : 
	 * @date : 2020.08.28
	 * @param parameterMap
	 * @return
	 */
	int selectItemListTotcnt(final FwkParameterMap parameterMap);
	
	FwkDataEntity trmDetail(final FwkParameterMap parameterMap);
	 
	FwkDataEntity selectEstmCmtmEstmFrmDetail(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmObjDetail(final FwkParameterMap parameterMap);
	
	
	List<FwkDataEntity> selectEstmCmtmEstmFrmItemList(final FwkParameterMap parameterMap);
	
	// 평가위원평가대상점수 merge Into
	void mergeIntoEstmCmtmObjScr(final FwkParameterMap parameterMap);
	
	// 평가위원이 평가항목별 평가점수 merge Into
	void mergeIntoEstmObjScr(final FwkParameterMap parameterMap);

	void insertEstmObjScrHist(final FwkParameterMap parameterMap); //평가대상 점수 이력
	
	FwkDataEntity selectEstmObjFileView(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmCmtmCrtrMstDetail(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmObjImstarsCmpnySelng(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmObjImstarsCmpnyFileView(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectEstmObjImstarsMainDetail(final FwkParameterMap parameterMap);
	FwkDataEntity selectEstmObjImstarsDetailView(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmObjImstarsFileView(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmObjImstarsDetailFileList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmObjImstarsGoodsCrtfcList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectEstmObjImstarsGoodssSelngList(final FwkParameterMap parameterMap);
}
  