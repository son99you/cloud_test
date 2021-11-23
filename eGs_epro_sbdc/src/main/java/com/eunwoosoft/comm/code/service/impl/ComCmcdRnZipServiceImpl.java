package com.eunwoosoft.comm.code.service.impl; 

import javax.annotation.Resource;


import com.eunwoosoft.comm.code.dao.ComCmcdRnZipDao;
import com.eunwoosoft.comm.code.service.ComCmcdZipService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

import org.springframework.stereotype.Service;

/**
 * 지번우편번호
 * <pre>
 * com.eunwoosoft.comm.code.service.impl 
 *    |_ ComCmcdRnZipServiceImpl.java
 * 
 * </pre>
 * @date : 2015. 1. 30
 * @version : 1.0
 * @author : 
 */
@Service("comCmcdRnZipService")
public class ComCmcdRnZipServiceImpl extends AbstractFwkService implements ComCmcdZipService {    

	@Resource(name="comCmcdRnZipDao")
	private ComCmcdRnZipDao comCmcdRnZipDao;

	@Override
	public FwkTransferObject lmnZipListInqireWithPgng(FwkParameterMap parameterMap) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * <pre>
	 * 1. 개요 : 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : rnZipListInqireWithPgng
	 * @date : 2015. 02. 13.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 13.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.com.cmcd.service.ComCmcdZipService#rnZipListInqireWithPgng(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject rnZipListInqireWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject transferObject = new FwkTransferObject();
		transferObject.put(ComCmcdZipService.ZIP_LIST, comCmcdRnZipDao.selectRnZipListWithPgng(parameterMap));
		transferObject.put(ComCmcdZipService.ZIP_LIST_TOTCNT, comCmcdRnZipDao.selectRnZipListTotcnt(parameterMap));
		return transferObject;
	}

}
