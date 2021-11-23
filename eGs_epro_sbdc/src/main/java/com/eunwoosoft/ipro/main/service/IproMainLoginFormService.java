package com.eunwoosoft.ipro.main.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.main.service
 * IproMainLoginFormService.java
 *
 * @Author : SungYoon_Ha
 * @Date   : 2017. 5. 30.
 *
 */
public interface IproMainLoginFormService {
	
	/**
	 * 로그인 Value Object Key
	 */
	static final String LOGIN_RESULT = "loginResult";
	static final String RESULT_CODE = "resultCode";
	static final String RESULT_MESSAGE = "resultMessage";
	static final String RESULT_DATA = "resultData";
	
	static final String ESTM_CNT_RESULT = "estmCntResult";
	/**
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.main.service.IproMainLoginFormService.java
	 * @Method : login
	 * @author : SungYoon_Ha
	 * @date : 2017. 5. 30. 
	 * @param parameterMap
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
	 */
	FwkTransferObject login(final FwkParameterMap parameterMap ) throws SQLException, IOException, ParseException;
	FwkTransferObject loginByBizrno(final FwkParameterMap parameterMap );
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 업무요약 _부서별 
	 *	</pre>
	 *
	 * @method Name : getMainSummary
	 * @Author : joo
	 * @Date   : 2020. 10. 20.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 10. 20.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param parameterMap
	 * @return
	 *
	 */
	FwkTransferObject getMainSummary(final FwkParameterMap parameterMap);


	/**
	 * <pre>
	 * 1. 개요 : SSO 로그인 정보 받아오기
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : getSSOInfo
	 * @date : 2019. 03. 28.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 28.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */	
	FwkTransferObject getSSOInfo(final FwkParameterMap parameterMap);
	
	//평가진행현황
	public FwkTransferObject mainEstmCnt(final FwkParameterMap parameterMap);
	
	//평가진행상세
	public FwkTransferObject mainEstmList(final FwkParameterMap parameterMap);
	
	FwkTransferObject iproSBDC_sugarmanWorkConn(final FwkParameterMap parameterMap ) throws SQLException, IOException, ParseException;
}