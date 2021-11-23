package com.eunwoosoft.ipro.ebid.service; 

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 입찰관리 > 수의시감
 * com.eunwoosoft.ipro.ebid.service
 * IproEbidSttlmntService.java
 *
 * @Author : chanil_Hong
 * @Date   : 2018. 3. 19.
 *
 */
public interface IproEbidSttlmntService {

	/**
	 * 수의시담 목록 Key
	 */
	static final String VLTRN_PRVSTL_LIST = "vltrnPrvstlList";
	
	/**
	 * 수의시담 목록 총건수
	 */
	static final String VLTRN_PRVSTL_LIST_TOTCNT = "vltrnPrvstlListTotcnt";
	
	/**
	 * 수의시담 상세조회
	 */
	static final String VLTRN_PRVSTL_DETAIL = "vltrnPrvstlDetail";
	/**
	 * 수의시담 진행 목록 조회
	 */
	static final String VLTRN_PRVSTL_PROGRS_LIST = "vltrnPrvstlProgrsList";	
	
	/**
	 * 수의시담 견적완료 작성폼 상세조회
	 */
	static final String SELECT_TEPN_ESREMA_DETAIL = "selectTepnEsremaDetail";

	/**
	 * 
	 * <pre>
	 * 1.개요 :	수의시담 목록조회_페이징 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidSttlmntService.java
	 * @Method : vltrnPrvstlListInqireWithPgng
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject vltrnPrvstlListInqireWithPgng(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 :	수의시담 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidSttlmntService.java
	 * @Method : vltrnPrvstlDetail
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject vltrnPrvstlDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidSttlmntService.java
	 * @Method : vltrnPrvstlRegist
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject vltrnPrvstlRegist(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidSttlmntService.java
	 * @Method : vltrnPrvstlModify
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject vltrnPrvstlModify(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 요청
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidSttlmntService.java
	 * @Method : prvstlRequst
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject prvstlRequst(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 상세 - 시담진행
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidSttlmntService.java
	 * @Method : vltrnPrvstlProgrsDetail
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject vltrnPrvstlProgrsDetail(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 상세 - 내용등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidSttlmntService.java
	 * @Method : vltrnPrvstlCnRegist
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject vltrnPrvstlCnRegist(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 완료견적 작성폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidSttlmntService.java
	 * @Method : vltrnPrvstlRequstRegistForm
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject vltrnPrvstlRequstRegistForm(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 수의시담 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.service.IproEbidSttlmntService.java
	 * @Method : prvstlDelete
	 * @author : chanil_Hong
	 * @date : 2018. 3. 19. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject prvstlDelete(final FwkParameterMap parameterMap);
	

	
}
