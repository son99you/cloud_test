package com.eunwoosoft.ipro.comm.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.main.dao
 * IproMainLoginFormDao.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2018. 2. 12.
 *
 */
public interface IproCommDefaultDao {

	/**
	 * <pre>
	 * 1. 개요 : 공통 첨부 파일 조회(단건)
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 */
	FwkDataEntity selectCmmnFileInfoInqire(final FwkParameterMap parameterMap);
	
	
	/**
	 * <pre>
	 * 1. 개요 : 공통 첨부파일 정보(단건) - 아임스타즈 연계
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 */
	FwkDataEntity selectCmmnImstarsFileInfo(final FwkParameterMap parameterMap);
	FwkDataEntity selectCmmnImstarsDetailFileInfo(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 공통 첨부 파일 조회(다건)
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 */
	List<FwkDataEntity> selectCmmnFileListInqire(final FwkParameterMap parameterMap);
	
	//낙찰자 선정 방식
	List<FwkDataEntity> selectcomCmcdScsCdInqireByCdId(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 결재대상 Form Html
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectApplFormHtml
	 * @date : 2018. 10. 05.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 10. 05.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	FwkDataEntity selectApplFormHtml(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 인터페이스 LEGACY MST 저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertIfLegacyMst
	 * @date : 2018. 10. 05.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 10. 05.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */	
	void insertIfLegacyMst(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 인터페이스 LEGACY DOC 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao.java
	 * @Method : insertIfLegacyDoc
	 * @author : sanghoon_joen
	 * @date : 2018. 10. 29. 
	 * @param parameterMap
	 */
	void insertIfLegacyDoc(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 인터페이스 LEGACY FILE 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao.java
	 * @Method : insertIfLegacyFile
	 * @author : sanghoon_joen
	 * @date : 2018. 10. 29. 
	 * @param parameterMap
	 */
	void insertIfLegacyFile(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 인터페이스 LEGACY MST 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao.java
	 * @Method : selectIfLegacyMst
	 * @author : sanghoon_joen
	 * @date : 2018. 10. 29. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectIfLegacyMst(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체정보 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao.java
	 * @Method : vendDetail
	 * @author : sanghoon_joen
	 * @date : 2018. 11. 29. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity vendDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 메일 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao.java
	 * @Method : insertMail
	 * @author : sanghoon_joen
	 * @date : 2018. 12. 18. 
	 * @param parameterMap
	 */
	void insertMail(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 업체 메일 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao.java
	 * @Method : selectVendMail
	 * @author : sanghoon_joen
	 * @date : 2018. 12. 18. 
	 * @param parameterMap
	 */
	FwkDataEntity selectVendMail(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 근로계약자 메일 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : selectVendLabMail.com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao.java
	 * @Method : selectVendMail
	 * @author : 맹경열
	 * @date : 2018. 12. 31. 
	 * @param parameterMap
	 */
	FwkDataEntity selectVendLabMail(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 로그 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : selectVendLabMail.com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao.java
	 * @Method : insertMmLogInfo
	 * @author : 맹경열
	 * @date : 2019. 03. 28. 
	 * @param parameterMap
	 */
	void insertMmLogInfo(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 메일 템플릿 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : selectVendLabMail.com.eunwoosoft.ipro.comm.dao.IproCommDefaultDao.java
	 * @Method : selectMailFormHtml
	 * @author : 맹경열
	 * @date : 2019. 05. 29. 
	 * @param parameterMap
	 */
	FwkDataEntity selectMailFormHtml(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectCodeList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 :
	 *	</pre>
	 *
	 * @method Name : sendLOG
	 * @Author : joo
	 * @Date   : 2020. 11. 17.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 11. 17.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param parameterMap
	 *
	 */
	void sendLOG(final FwkParameterMap parameterMap);
	
	
	List<FwkDataEntity> selectExcelTHList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectExcelTHColList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectExcelTHRowList(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectExcelTDList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectExcelXList(final FwkParameterMap parameterMap);
	List<FwkDataEntity> selectExcelYList(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectMsgContents(final FwkParameterMap parameterMap);
	
	
}

