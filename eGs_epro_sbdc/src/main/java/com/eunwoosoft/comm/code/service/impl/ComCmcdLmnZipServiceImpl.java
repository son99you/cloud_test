package com.eunwoosoft.comm.code.service.impl; 

import javax.annotation.Resource;


import com.eunwoosoft.comm.code.dao.ComCmcdLmnZipDao;
import com.eunwoosoft.comm.code.service.ComCmcdZipService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

import org.springframework.stereotype.Service;

/**
 * 지번우편번호
 * <pre>
 * com.eunwoosoft.comm.code.service.impl 
 *    |_ ComCmcdLmnZipServiceImpl.java
 * 
 * </pre>
 * @date : 2015. 1. 30
 * @version : 1.0
 * @author : 
 */
@Service("comCmcdLmnZipService")
public class ComCmcdLmnZipServiceImpl extends AbstractFwkService implements ComCmcdZipService {    

	@Resource(name="comCmcdLmnZipDao")
	private ComCmcdLmnZipDao comCmcdLmnZipDao;

	/**
	 * <pre>
	 * 1. 개요 : 지번 우편번호목록조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : lmnZipListInqireWithPgng
	 * @date : 2015. 02. 13.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 13.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.com.cmcd.service.ComCmcdZipService#lmnZipListInqireWithPgng(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject lmnZipListInqireWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject transferObject = new FwkTransferObject();
		transferObject.put(ComCmcdZipService.ZIP_LIST, comCmcdLmnZipDao.selectLmnZipListWithPgng(parameterMap));
		transferObject.put(ComCmcdZipService.ZIP_LIST_TOTCNT, comCmcdLmnZipDao.selectLmnZipListTotcnt(parameterMap));
		return transferObject;
	}

	@Override
	public FwkTransferObject rnZipListInqireWithPgng(FwkParameterMap parameterMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
