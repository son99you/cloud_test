package com.eunwoosoft.comm.code.service.impl; 

import javax.annotation.Resource;


import com.eunwoosoft.comm.code.dao.ComCmcdLegaldongDao;
import com.eunwoosoft.comm.code.service.ComCmcdLegaldongService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

import org.springframework.stereotype.Service;

/**
 * 지역정보
 * <pre>
 * com.eunwoosoft.comm.code.service.impl 
 *    |_ ComCmcdLegaldongServiceImpl.java
 * 
 * </pre>
 * @date : 2015. 1. 30
 * @version : 1.0
 * @author : 
 */
@Service("comCmcdLegaldongService")
public class ComCmcdLegaldongServiceImpl extends AbstractFwkService implements ComCmcdLegaldongService {    

	@Resource(name="comCmcdLegaldongDao")
	private ComCmcdLegaldongDao comCmcdLegaldongDao;

	/**
	 * <pre>
	 * 1. 개요 : 시도목록조회  
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : legaldongBrtcListInqire
	 * @date : 2015. 02. 17.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 17.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.com.cmcd.service.comCmcdLegaldongService#legaldongBrtcListInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject legaldongBrtcListInqire(FwkParameterMap parameterMap) {
		FwkTransferObject transferObject = new FwkTransferObject();
		transferObject.put(ComCmcdLegaldongService.AREA_LIST, comCmcdLegaldongDao.legaldongBrtcListInqire(parameterMap));
		return transferObject;
	}

	/**
	 * <pre>
	 * 1. 개요 : 시군구목록조회  
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : legaldongSignguListInqire
	 * @date : 2015. 02. 17.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 17.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.com.cmcd.service.comCmcdLegaldongService#legaldongSignguListInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject legaldongSignguListInqire(FwkParameterMap parameterMap) {
		 FwkTransferObject transferObject = new FwkTransferObject();
		transferObject.put(ComCmcdLegaldongService.AREA_LIST, comCmcdLegaldongDao.legaldongSignguListInqire(parameterMap));
		return transferObject;
	}

	/**
	 * <pre>
	 * 1. 개요 : 읍면동목록조회  
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : legaldongEmdListInqire
	 * @date : 2015. 02. 17.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 17.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.com.cmcd.service.comCmcdLegaldongService#legaldongEmdListInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject legaldongEmdListInqire(FwkParameterMap parameterMap) {
		FwkTransferObject transferObject = new FwkTransferObject();
		transferObject.put(ComCmcdLegaldongService.AREA_LIST, comCmcdLegaldongDao.legaldongEmdListInqire(parameterMap));
		return transferObject;
	}

}
