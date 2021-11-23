package com.eunwoosoft.comm.code.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 지번 우편번호 DAO
 * <pre>
 * com.eunwoosoft.comm.code.dao 
 *    |_ ComCmcdLmnZipDao.java
 * 
 * </pre>
 * @date : 2015. 1. 30.
 * @version : 1.0
 * @author : 
 */
public interface ComCmcdLmnZipDao {
    
    /**
     * <pre>
     * 1. 개요 : 지번 우편번호목록조회_페이징
     * 2. 처리내용 : 
     *     
     * </pre>
     * @Method Name : selectLmnZipListWithPgng
     * @date : 2015. 1. 30.
     * @author : 
     * @history : 
     *  -----------------------------------------------------------------------
     *  변경일             작성자                     변경내용  
     *  ----------- ------------------- ---------------------------------------
     *  2015. 1. 30.                      최초 작성 
     *  -----------------------------------------------------------------------
     * 
     * @param parameterMap
     * @return
     */
    List<FwkDataEntity> selectLmnZipListWithPgng(final FwkParameterMap parameterMap);
    
    /**
     * <pre>
     * 1. 개요 : 지번 우편번호목록조회_총건수
     * 2. 처리내용 : 
     *     
     * </pre>
     * @Method Name : selectLmnZipListTotcnt
     * @date : 2015. 1. 30.
     * @author : 
     * @history : 
     *  -----------------------------------------------------------------------
     *  변경일             작성자                     변경내용  
     *  ----------- ------------------- ---------------------------------------
     *  2015. 1. 30.                      최초 작성 
     *  -----------------------------------------------------------------------
     * 
     * @param parameterMap
     * @return
     */
    int selectLmnZipListTotcnt(final FwkParameterMap parameterMap);
	
	

}
