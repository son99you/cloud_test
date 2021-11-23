package com.eunwoosoft.ipro.g2b.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.g2b.dao
 * IproG2bDao.java
 *
 * @Author : 하성윤
 * @Date   : 2019. 8. 16.
 *
 */
public interface IproG2bDao {
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰공고 G2B 해더정보조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.g2b.dao.IproG2bDao.java
	 * @Method : selectG2bHeaderInfoBid
	 * @author : 하성윤
	 * @date : 2019. 8. 16.
	 * @param parameterMap
	 * @return
	 */
	//FwkDataEntity selectG2bHeaderInfoBid(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰공고 G2B 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.g2b.dao.IproG2bDao.java
	 * @Method : selectG2bCreateBid
	 * @author : 하성윤
	 * @date : 2019. 8. 16.
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectG2bCreateBid(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 개찰정보 G2B - 기본정보 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.g2b.dao.IproG2bDao.java
	 * @Method : selectG2bCreateOpng
	 * @author : JanDi_Eun
	 * @date : 2019. 9. 20. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectG2bCreateOpng(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 개찰정보 G2B - 업체 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.g2b.dao.IproG2bDao.java
	 * @Method : selectLwetScsbidPrearngerList
	 * @author : JanDi_Eun
	 * @date : 2019. 9. 20. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectLwetScsbidPrearngerList(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 낙찰정보 G2B
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.g2b.dao.IproG2bDao.java
	 * @Method : selectG2bCreateSucc
	 * @author : JanDi_Eun
	 * @date : 2019. 9. 23. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectG2bCreateSucc(final FwkParameterMap parameterMap);

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 계약통보 G2B
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.g2b.dao.IproG2bDao.java
	 * @Method : selectG2bCreateCont
	 * @author : JanDi_Eun
	 * @date : 2019. 9. 24. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectG2bCreateCont(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 계약통보 G2B - 계약업체 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.g2b.dao.IproG2bDao.java
	 * @Method : selectCntrctEntrpsInqire
	 * @author : JanDi_Eun
	 * @date : 2019. 9. 24. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectCntrctEntrpsInqire(final FwkParameterMap parameterMap);

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 계약통보 G2B - 계약물품
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.g2b.dao.IproG2bDao.java
	 * @Method : selectThngDetailListInqire
	 * @author : JanDi_Eun
	 * @date : 2019. 9. 25. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectThngDetailListInqire(final FwkParameterMap parameterMap);
	
}