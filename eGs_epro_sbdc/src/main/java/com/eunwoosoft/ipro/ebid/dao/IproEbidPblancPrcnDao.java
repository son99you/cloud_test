package com.eunwoosoft.ipro.ebid.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface IproEbidPblancPrcnDao {
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 :
	 *	</pre>
	 *
	 * @method Name : selectBidPblancPrcnListWithPgng
	 * @Author : joo
	 * @Date   : 2020. 9. 14.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 14.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param parameterMap
	 * @return
	 *
	 */
	List<FwkDataEntity> selectBidPblancPrcnListWithPgng(final FwkParameterMap parameterMap);
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 :
	 *	</pre>
	 *
	 * @method Name : selectBidPblancPrcnListTotcnt
	 * @Author : joo
	 * @Date   : 2020. 9. 14.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 14.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param parameterMap
	 * @return
	 *
	 */
	int selectBidPblancPrcnListTotcnt(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectBidPblancPrcnExcelList(final FwkParameterMap parameterMap);
	
	void updateBidVendMst(final FwkParameterMap parameterMap);
	
	void insertBidVendFile(final FwkParameterMap parameterMap);
	
	void deleteBidVendFile(final FwkParameterMap parameterMap);
	//T_BI_VEND_BIOP UPDATE
	void updateBidBiopMst(final FwkParameterMap parameterMap);
	
	void updateBidStat(final FwkParameterMap parameterMap);
	
	void insertBidProgHist(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectBidReqPrcnListDetail(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectReceiver(final FwkParameterMap parameterMap);
}