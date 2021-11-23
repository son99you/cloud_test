package com.eunwoosoft.comm.code.dao; 

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 결재상태 DAO
 * <pre>
 * com.eunwoosoft.comm.code.dao 
 *    |_ ComSancSttusDao.java
 * 
 * </pre>
 * @date : 2017. 02. 02. 오전 01:20:08
 * @version : 1.0
 * @author : 
 */
public interface ComSancSttusDao {
    
    /**
     * <pre>
     * 1. 개요 : 결재상태정보
     * 2. 처리내용 : 
     *     
     * </pre>
     * @Method Name : selectSancSttusInfo
     * @date : 2017. 02. 02.
     * @author : 
     * @history : 
     *  -----------------------------------------------------------------------
     *  변경일             작성자                     변경내용  
     *  ----------- ------------------- ---------------------------------------
     *  2017. 02. 02.    하성윤                    최초 작성 
     *  -----------------------------------------------------------------------
     * 
     * @param parameterMap
     * @return
     */
    FwkDataEntity selectSancSttusInfo(final FwkParameterMap parameterMap);

}
