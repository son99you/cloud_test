package com.eunwoosoft.opro.main.service;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.opro.main.service
 * OproMainLoginFormService.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2018. 2. 20.
 *
 */
public interface OproMainLoginFormService {
	
	/**
	 * 로그인 Value Object Key
	 */
	static final String LOGIN_RESULT = "loginResult";

	static final String RESULT_CODE = "resultCode";
	static final String RESULT_MESSAGE = "resultMessage";
	static final String RESULT_DATA = "resultData";
	
	/**
	 * 달력 생성 HTML
	 */
	static final String CLDR_CREAT_HTML = "cldrCreatHtml";
	
	/**
	 * 오늘 할 일
	 */
	static final String TO_DO_LIST = "toDoList";

	/**
	 * 
	 * <pre>
	 * 1.개요 : 사업자번호 로그인
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.service.OproMainLoginFormService.java
	 * @Method : loginByBizrno
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject loginByBizrno(final FwkParameterMap parameterMap );
	
	/**
	 * 주민등록번호로 로그인
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject loginByRsdnNo(final FwkParameterMap parameterMap );
	
	/**
	 * 평가위원 비상용 주민등록번호로 로그인
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject emgncCmtmEmplyrLoginByRsdnNo(final FwkParameterMap parameterMap);
	
	FwkTransferObject joinRegist(final FwkParameterMap parameterMap ) throws Exception;
	FwkTransferObject joinEnpaCheck(final FwkParameterMap parameterMap ) throws Exception;
	
	FwkTransferObject loginByIdPw_dev(final FwkParameterMap parameterMap );
	
	/**
	 * 
	 * <pre> 
	 * 1.개요 : ID PW 로그인
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.service.OproMainLoginFormService.java
	 * @Method : loginByIdPw
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject loginByIdPw(final FwkParameterMap parameterMap );
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 업무요약 카운트 _사업자번호별
	 *	</pre>
	 *
	 * @method Name : getMainInfoCnt
	 * @Author : joo
	 * @Date   : 2020. 10. 21.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 10. 21.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param parameterMap
	 * @return
	 *
	 */
	FwkTransferObject getMainInfoCnt(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 오늘 할 일
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.service.OproMainLoginFormService.java
	 * @Method : toDoList
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 14. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject toDoList(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 메인 - 평가공고
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.main.service.OproMainLoginFormService.java
	 * @Method : estmAnncList
	 * @author : JanDi_Eun
	 * @date : 2021. 5. 06. 
	 * @param parameterMap
	 * @return
	 */
	FwkTransferObject estmAnncList(final FwkParameterMap parameterMap);

	
}
