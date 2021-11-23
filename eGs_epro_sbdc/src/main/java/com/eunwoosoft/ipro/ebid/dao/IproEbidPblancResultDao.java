package com.eunwoosoft.ipro.ebid.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

public interface IproEbidPblancResultDao {
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 :
	 *	</pre>
	 *
	 * @method Name : selectBidPblancResultListWithPgng
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
	List<FwkDataEntity> selectBidPblancResultListWithPgng(final FwkParameterMap parameterMap);
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 :
	 *	</pre>
	 *
	 * @method Name : selectBidPblancResultListTotcnt
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
	int selectBidPblancResultListTotcnt(final FwkParameterMap parameterMap);
	
	//엑셀다운로드
	List<FwkDataEntity> selectBidPblancResultExcelList(final FwkParameterMap parameterMap);
}