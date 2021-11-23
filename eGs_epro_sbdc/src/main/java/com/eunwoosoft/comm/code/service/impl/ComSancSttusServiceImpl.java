package com.eunwoosoft.comm.code.service.impl; 

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.code.dao.ComSancSttusDao;
import com.eunwoosoft.comm.code.service.ComSancSttusService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 결재상태 서비스
 * <pre>
 * com.eunwoosoft.comm.code.service.impl 
 *    |_ ComSancSttusServiceImpl.java
 * 
 * </pre>
 * @date : 2017. 02. 02. 오후 1:50:10
 * @version : 1.0
 * @author : 
 */
@Service("comSancSttusService")
public class ComSancSttusServiceImpl  extends AbstractFwkService implements ComSancSttusService {

    
    @Resource(name="comSancSttusDao")
    private ComSancSttusDao comSancSttusDao;
    
    /**
     * <pre>
     * 1. 개요 : 결재상태정보 
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : sancSttusInfo
     * @date : 2017. 02. 02.
     * @author : 
     * @history : 
     *  -----------------------------------------------------------------------
     *  변경일             작성자                     변경내용  
     *  ----------- ------------------- ---------------------------------------
     *  2017. 02. 02.    하성윤                     최초 작성 
     *  -----------------------------------------------------------------------
     * 
     * @see oda.com.cmcd.service.ComSancSttusService#sancSttusInfo(oda.framework.web.param.ParameterMap)
     * @param parameterMap
     * @return
     */
    @Override
    public FwkTransferObject sancSttusInfo(FwkParameterMap parameterMap) {
    	FwkTransferObject transferObject = new FwkTransferObject();
//        transferObject.put(ComSancSttusService.SANC_INFO, comSancSttusDao.selectSancSttusInfo(parameterMap));
        transferObject.put("sancInfo", comSancSttusDao.selectSancSttusInfo(parameterMap));
        return transferObject;
    }
}
