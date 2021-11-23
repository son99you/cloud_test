package com.eunwoosoft.comm.code.dao; 

import java.util.List;


import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 공통마스터코드 DAO
 * <pre>
 * com.eunwoosoft.comm.code.dao 
 *    |_ ComCmcdMastrCdDao.java
 * 
 * </pre>
 * @date : 2014. 12. 9. 오전 11:20:08
 * @version : 1.0
 * @author : 
 */
public interface ComCmcdMastrCdDao {
    
    /**
     * <pre>
     * 1. 개요 : 공통코드목록조회_페이징
     * 2. 처리내용 : 
     *     
     * </pre>
     * @Method Name : selectMastrCdListWithPgng
     * @date : 2014. 12. 9.
     * @author : 
     * @history : 
     *  -----------------------------------------------------------------------
     *  변경일             작성자                     변경내용  
     *  ----------- ------------------- ---------------------------------------
     *  2014. 12. 9.                      최초 작성 
     *  -----------------------------------------------------------------------
     * 
     * @param parameterMap
     * @return
     */
    List<FwkDataEntity> selectMastrCdListWithPgng(final FwkParameterMap parameterMap);
    
    /**
     * <pre>
     * 1. 개요 : 공통코드목록조회_총건수
     * 2. 처리내용 : 
     *     
     * </pre>
     * @Method Name : selectMastrCdListTotcnt
     * @date : 2014. 12. 9.
     * @author : 
     * @history : 
     *  -----------------------------------------------------------------------
     *  변경일             작성자                     변경내용  
     *  ----------- ------------------- ---------------------------------------
     *  2014. 12. 9.                      최초 작성 
     *  -----------------------------------------------------------------------
     * 
     * @param parameterMap
     * @return
     */
    int selectMastrCdListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 마스터코드목록조회 
	 * 2. 처리내용 : 
	 *     SELECT :
	 *         T1.CD_ID
	 *         , T1.CD_ID_NM
	 *     FROM TB_MDM001 T1
	 *     WHERE T1.CD_ID LIKE '%#{P_CD_ID}%'
	 *         AND T1.CD_ID_NM LIKE '%#{P_CD_ID_NM}%'
	 * </pre>
	 * @Method Name : selectMastrCdList
	 * @date : 2014. 12. 9.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 9.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectMastrCdList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 마스터코드 상세조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMastrCdDetail
	 * @date : 2014. 12. 9.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 9.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectMastrCdDetail(final FwkParameterMap parameterMap);

}
