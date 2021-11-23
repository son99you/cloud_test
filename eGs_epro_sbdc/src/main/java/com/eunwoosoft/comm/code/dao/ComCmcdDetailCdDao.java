package com.eunwoosoft.comm.code.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 공통상세코드 DAO
 * <pre>
 * com.eunwoosoft.comm.code.dao 
 *    |_ ComCmcdDetailCdDao.java
 * 
 * </pre>
 * @date : 2014. 12. 9. 오전 11:20:08
 * @version : 1.0
 * @author : 
 */
public interface ComCmcdDetailCdDao {
    
    /**
     * <pre>
     * 1. 개요 : 상세코드목록조회_페이징
     * 2. 처리내용 : 
     *     
     * </pre>
     * @Method Name : selectDetailCdListWithPgng
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
    List<FwkDataEntity> selectDetailCdListWithPgng(final FwkParameterMap parameterMap);
    
    /**
     * <pre>
     * 1. 개요 : 상세코드목록조회_총건수
     * 2. 처리내용 : 
     *     
     * </pre>
     * @Method Name : selectDetailCdListWithPgng
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
    int selectDetailCdListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 상세코드목록조회 
	 * 2. 처리내용 : 
	 *
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
	List<FwkDataEntity> selectDetailCdList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 상세코드 상세조회
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
	FwkDataEntity selectDetailCdDetail(final FwkParameterMap parameterMap);
	
	
	/**
     * <pre>
     * 1. 개요 : 코드ID에 의한 코드값목록조회 
     * 2. 처리내용 : 
     *
     * </pre>
     * @Method Name : selectMastrCdList
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
    List<FwkDataEntity> selectCdValueListByCdId(final FwkParameterMap parameterMap);
    
    List<FwkDataEntity> selectcomCmcdScsCdInqireByCdId(final FwkParameterMap parameterMap);
    
    List<FwkDataEntity> selectCdTableValueListByCdId(final FwkParameterMap parameterMap);
    
    
    

}
