package com.eunwoosoft.opro.user.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 
 * com.eunwoosoft.opro.user.service
 * OproUserSnctVendRegInfoService.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 3. 7.
 *
 */
public interface OproUserSnctVendRegInfoService {
	
	static final String SNCT_VEND_REG_INFO_LIST = "snctVendRegInfoList";
	static final String SNCT_VEND_REG_INFO_LIST_TOTCNT = "snctVendRegInfoListTotcnt";

	static final String SNCT_VEND_REG_INFO_DETAIL = "snctVendRegInfoDetail";

	/**
	 * 
	 * <pre>
	 * 1.개요 : 부정당업체등록정보 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.user.service.OproUserSnctVendRegInfoService.java
	 * @Method : snctVendRegInfoList
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 7. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject snctVendRegInfoList(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 부정당업체등록정보 상세 팝업 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.user.service.OproUserSnctVendRegInfoService.java
	 * @Method : snctVendRegInfoDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 7. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject snctVendRegInfoDetail(final FwkParameterMap parameterMap);


}
