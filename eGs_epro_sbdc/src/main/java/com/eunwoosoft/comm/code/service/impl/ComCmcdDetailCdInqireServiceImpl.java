package com.eunwoosoft.comm.code.service.impl; 

import javax.annotation.Resource;


import com.eunwoosoft.comm.code.dao.ComCmcdDetailCdDao;
import com.eunwoosoft.comm.code.service.ComCmcdCmmnCdInqireService;
import com.eunwoosoft.comm.code.service.ComCmcdDetailCdInqireService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

import org.springframework.stereotype.Service;

/**
 * 공통상세코드조회 서비스
 * <pre>
 * com.eunwoosoft.comm.code.service.impl 
 *    |_ ComCmcdDetailCdInqireServiceImpl.java
 * 
 * </pre>
 * @date : 2014. 12. 9. 오후 1:50:10
 * @version : 1.0
 * @author : 
 */
@Service("comCmcdDetailCdInqireService")
public class ComCmcdDetailCdInqireServiceImpl  extends AbstractFwkService implements ComCmcdDetailCdInqireService {

    
    @Resource(name="comCmcdDetailCdDao")
    private ComCmcdDetailCdDao comCmcdDetailCdDao;
    
    /**
     * <pre>
     * 1. 개요 : 상세코드목록조회_페이징 
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
        transferObject.put(ComCmcdCmmnCdInqireService.CMMN_CD_LIST, comCmcdDetailCdDao.selectDetailCdListWithPgng(parameterMap));
        transferObject.put(ComCmcdCmmnCdInqireService.CMMN_CD_LIST_TOTCNT, comCmcdDetailCdDao.selectDetailCdListTotcnt(parameterMap));
        return transferObject;
    }

    
    /**
     * <pre>
     * 1. 개요 : 공통상세코드목록조회 
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
        transferObject.put(ComCmcdCmmnCdInqireService.CMMN_CD_LIST, comCmcdDetailCdDao.selectDetailCdList(parameterMap));
        return transferObject;
    }
    
    /**
     * <pre>
     * 1. 개요 : 공통상세코드상세조회 
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
        transferObject.put(ComCmcdCmmnCdInqireService.CMMN_CD_LIST, comCmcdDetailCdDao.selectDetailCdDetail(parameterMap));
        return transferObject;
    }


    @Override
    public FwkTransferObject cdValueListInqireByCdId(FwkParameterMap parameterMap) {
    	FwkTransferObject transferObject = new FwkTransferObject();
        transferObject.put(ComCmcdDetailCdInqireService.CD_VALUE_LIST, comCmcdDetailCdDao.selectCdValueListByCdId(parameterMap));
        return transferObject;
    }
    
    @Override
    public FwkTransferObject comCmcdScsCdInqireByCdId(FwkParameterMap parameterMap) { 
    	FwkTransferObject transferObject = new FwkTransferObject();
    	
        transferObject.put(ComCmcdDetailCdInqireService.CD_VALUE_LIST, comCmcdDetailCdDao.selectcomCmcdScsCdInqireByCdId(parameterMap));
        return transferObject;
    }
    
    
    @Override
    public FwkTransferObject cdTableValueListInqireByCdId(FwkParameterMap parameterMap) throws Exception {
    	FwkTransferObject transferObject = new FwkTransferObject();
        transferObject.put(ComCmcdDetailCdInqireService.CD_VALUE_LIST, comCmcdDetailCdDao.selectCdTableValueListByCdId(parameterMap));
        return transferObject;
    }
}
