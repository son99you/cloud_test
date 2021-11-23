package com.eunwoosoft.comm.code.service.impl; 

import javax.annotation.Resource;


import com.eunwoosoft.comm.code.dao.ComCmcdMastrCdDao;
import com.eunwoosoft.comm.code.service.ComCmcdCmmnCdInqireService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

import org.springframework.stereotype.Service;

/**
 * 공통마스터코드조회 서비스
 * <pre>
 * com.eunwoosoft.comm.code.service.impl 
 *    |_ ComCmcdMasterCdInqireServiceImpl.java
 * 
 * </pre>
 * @date : 2014. 12. 9. 오후 1:50:10
 * @version : 1.0
 * @author : 
 */
@Service("comCmcdMasterCdInqireService")
public class ComCmcdMasterCdInqireServiceImpl extends AbstractFwkService implements ComCmcdCmmnCdInqireService {    
    
    @Resource(name="comCmcdMastrCdDao")
    private ComCmcdMastrCdDao comCmcdMastrCdDao;
    
    /**
     * <pre>
     * 1. 개요 : 공통마스터코드목록조회_페이징
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : cmmnCdListInqireWithPgng
     * @date : 2014. 12. 9.
     * @author : 
     * @history : 
     *  -----------------------------------------------------------------------
     *  변경일             작성자                     변경내용  
     *  ----------- ------------------- ---------------------------------------
     *  2014. 12. 9.                      최초 작성 
     *  -----------------------------------------------------------------------
     * 
     * @see oda.com.cmcd.service.ComCmcdCmmnCdInqireService#cmmnCdListInqireWithPgng(oda.framework.web.param.ParameterMap)
     * @param parameterMap
     * @return
     */
    @Override
    public FwkTransferObject cmmnCdListInqireWithPgng(FwkParameterMap parameterMap) {
    	FwkTransferObject transferObject = new FwkTransferObject();
        transferObject.put(ComCmcdCmmnCdInqireService.CMMN_CD_LIST, comCmcdMastrCdDao.selectMastrCdListWithPgng(parameterMap));
        transferObject.put(ComCmcdCmmnCdInqireService.CMMN_CD_LIST_TOTCNT, comCmcdMastrCdDao.selectMastrCdListTotcnt(parameterMap));
        return transferObject;
    }

    
    /**
     * <pre>
     * 1. 개요 : 마스터코드목록조회 
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : cmmnCdListInqire
     * @date : 2014. 12. 9.
     * @author : 
     * @history : 
     *	-----------------------------------------------------------------------
     *	변경일				작성자						변경내용  
     *	----------- ------------------- ---------------------------------------
     *	2014. 12. 9.						최초 작성 
     *	-----------------------------------------------------------------------
     * 
     * @see oda.com.cmcd.service.ComCmcdCmmnCdInqireService#cmmnCdListInqire(oda.framework.web.param.ParameterMap)
     * @param parameterMap
     * @return
     */
    @Override
    public FwkTransferObject cmmnCdListInqire(FwkParameterMap parameterMap) {
    	FwkTransferObject transferObject = new FwkTransferObject();
        transferObject.put(ComCmcdCmmnCdInqireService.CMMN_CD_LIST, comCmcdMastrCdDao.selectMastrCdList(parameterMap));
        return transferObject;
    }
    
    /**
     * <pre>
     * 1. 개요 : 마스터코드상세조회 
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : cmmnCdDetailInqire
     * @date : 2014. 12. 9.
     * @author : 
     * @history : 
     *	-----------------------------------------------------------------------
     *	변경일				작성자						변경내용  
     *	----------- ------------------- ---------------------------------------
     *	2014. 12. 9.						최초 작성 
     *	-----------------------------------------------------------------------
     * 
     * @see oda.com.cmcd.service.ComCmcdCmmnCdInqireService#cmmnCdDetailInqire(oda.framework.web.param.ParameterMap)
     * @param parameterMap
     * @return
     */
    @Override
    public FwkTransferObject cmmnCdDetailInqire(FwkParameterMap parameterMap) {
    	FwkTransferObject transferObject = new FwkTransferObject();
        transferObject.put(ComCmcdCmmnCdInqireService.CMMN_CD_DETAIL, comCmcdMastrCdDao.selectMastrCdDetail(parameterMap));
        return transferObject;
    }
    
    
}
