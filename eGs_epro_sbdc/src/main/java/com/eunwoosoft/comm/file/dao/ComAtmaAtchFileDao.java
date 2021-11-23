package com.eunwoosoft.comm.file.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.comm.file.dao
 * ComAtmaAtchFileDao.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2018. 2. 21.
 *
 */
public interface ComAtmaAtchFileDao {	
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 그룹 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : insertAtchFileGroupRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertAtchFileGroupRegist(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : insertAtchFileRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertAtchFileRegist(FwkParameterMap parameterMap);

	void insertAtchFileRegistA(FwkParameterMap parameterMap);
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 첨부파일 del_yn ='Y'처리
	 *	</pre>
	 *
	 * @method Name : deleteAtchFileUpdt
	 * @Author : joo
	 * @Date   : 2020. 9. 23.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 23.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param parameterMap
	 *
	 */
	void deleteAtchFileUpdt(FwkParameterMap parameterMap);
	void insertMMFileRegist(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일목록조회_페이징
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : selectAtchFileListInqireWithPgng
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	List<FwkDataEntity> selectAtchFileListInqireWithPgng(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일목록조회_총건수
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : selectAtchFileListInqireTotcnt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	long selectAtchFileListInqireTotcnt(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일상세조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : selectAtchFileDetailInqire
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	FwkDataEntity selectAtchFileDetailInqire(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일그룹번호에의한 첨부파일목록조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : selectAtchFileListInqireByAtchFileGroupNo
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	List<FwkDataEntity> selectAtchFileListInqireByAtchFileGroupNo(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : deleteAtchFileDelete
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteAtchFileDelete(FwkParameterMap parameterMap);

	void deleteEducFileDelete(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일삭제(Not Update - Delete)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : deleteAtchFileRealDelete
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteAtchFileRealDelete(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일삭제 (파일이름으로)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : deleteAtchFileDeleteByNm
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteAtchFileDeleteByNm(FwkParameterMap parameterMap);
	
	/**	
	 * <pre>
	 * 1. 개요 : 첨부파일 등록 ( 내부 )
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : insertAtchFileRegistLocal
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertAtchFileRegistLocal(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일삭제(외부)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : deleteAtchFileDeleteExtern
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteAtchFileDeleteExtern(FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 첨부파일 그룹 등록 외부
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : insertAtchFileGroupRegistLocal
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertAtchFileGroupRegistLocal(FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 첨부파일 삭제 외부
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : deleteAtchFileUpdtLocal
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteAtchFileUpdtLocal(FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : updateFileDocNm
	 * @author : sanghoon_joen
	 * @date : 2018. 7. 23. 
	 * @param parameterMap
	 */
	void updateFileDocNm(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 첨부파일 등록(이미 존재) 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : insertAtchFileRegistExist
	 * @author : 맹경열
	 * @date : 2018. 08. 22. 
	 * @param parameterMap
	 */	
	void insertAtchFileRegistExist(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 첨부파일 등록(이미 존재) - FILE_SN 기준 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : insertAtchSnFileRegistExist
	 * @author : 맹경열
	 * @date : 2018. 10. 15. 
	 * @param parameterMap
	 */	
	void insertAtchSnFileRegistExist(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 첨부파일 등록 GROUP NO - BID
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : insertAtchBidDocRegist
	 * @author : 맹경열
	 * @date : 2019. 01. 28. 
	 * @param parameterMap
	 */	
	void insertAtchBidDocRegist(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 첨부파일 등록 GROUP NO - BID - ESTC
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : insertAtchBidEstcDocRegist
	 * @author : 맹경열
	 * @date : 2019. 02. 13. 
	 * @param parameterMap
	 */	
	void insertAtchBidEstcDocRegist(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 첨부파일 등록 GROUP NO - CONT 
	 * 2.처리내용 : 
	 * </pre>
	 * @Method : insertAtchContCoatfi
	 * @author : 맹경열
	 * @date : 2019. 01. 28. 
	 * @param parameterMap
	 */	
	void insertAtchContCoatfi(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일 그룹번호 여부
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : selectAtchFileGroupNoCnt
	 * @author : 맹경열
	 * @date : 2019. 01. 30. 
	 * @param parameterMap
	 */
	int selectAtchFileGroupNoCnt(FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 실적증명 첨부파일 그룹번호 여부
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : selectAcprAtchFileGroupNoCnt
	 * @author : JanDi_Eun
	 * @date : 2019. 9. 9. 
	 * @param parameterMap
	 * @return
	 */
	int selectAcprAtchFileGroupNoCnt(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 투찰 첨부파일 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : insertAtchBidTndrDocRegist
	 * @author : 맹경열
	 * @date : 2019. 02. 19. 
	 * @param parameterMap
	 */
	int insertAtchBidTndrDocRegist(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 투찰 보증 그룹번호 업데이트
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : updateTndrGrntGroupNo
	 * @author : 맹경열
	 * @date : 2019. 02. 20. 
	 * @param parameterMap
	 */
	void updateTndrGrntGroupNo(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 투찰 협상파일
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : updateNegoBiopGroupNo
	 * @author : 맹경열
	 * @date : 2019. 02. 27. 
	 * @param parameterMap
	 */
	void updateNegoBiopGroupNo(FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 연간발주계획 첨부파일 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : updateYearOrderPlanFileUpdt
	 * @author : JanDi_Eun
	 * @date : 2019. 2. 27. 
	 * @param parameterMap
	 */
	void updateYearOrderPlanFileUpdt(FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 사전공고 첨부파일 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : updateBereNotiMngeFileUpdt
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 13. 
	 * @param parameterMap
	 */
	void updateBereNotiMngeFileUpdt(FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 대금지급 첨부파일 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : insertAtchPayFileRegist
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 13. 
	 * @param parameterMap
	 */
	void insertAtchPayFileRegist(FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 대금지급 기타 첨부파일 삭제 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : deleteAtchPayFileETC
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 15. 
	 * @param parameterMap
	 */
	void deleteAtchPayFileETC(FwkParameterMap parameterMap);
	
	
	void insertAtchFileGrpNoRegistExist(FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 사전규격공개 상세 - 의견등록 첨부파일
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : updateBereNotiOpnnFileUpdt
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 18. 
	 * @param parameterMap
	 */
	void updateBereNotiOpnnFileUpdt(FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 구매접수대기현황 상세 - 첨부파일 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : updatePrchAcptFileUpdt
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 20. 
	 * @param parameterMap
	 */
	void updatePrchAcptFileUpdt(FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 구매접수대기현황 상세 - 첨부파일 연계
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : selectAcptWaitAtchFileListByFileGrpNo
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 21. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity>  selectAcptWaitAtchFileListByFileGrpNo(FwkParameterMap parameterMap);

	void insertPrchAcptFileRegist(FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 계약이행보증 - 첨부파일 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : updateGrntIssFileUpdt
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 28. 
	 * @param parameterMap
	 */
	void updateGrntIssFileUpdt(FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 협약계약 - 파일 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : insertAgreFileGrpNoRegistExist
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 2. 
	 * @param parameterMap
	 */
	void insertAgreFileGrpNoRegistExist(FwkParameterMap parameterMap);

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 인지세 - 첨부파일 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : updateStptxFileUpdt
	 * @author : JanDi_Eun
	 * @date : 2019. 4. 19. 
	 * @param parameterMap
	 */
	void updateStptxFileUpdt(FwkParameterMap parameterMap);

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 알림마당 - 첨부파일 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : updateNotiFileUpdt
	 * @author : JanDi_Eun
	 * @date : 2019. 5. 20. 
	 * @param parameterMap
	 */
	void updateNotiFileUpdt(FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 계약파일 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : selectContFileInfo
	 * @author : sungyoon_ha
	 * @date : 2019. 6. 13. 
	 * @param parameterMap
	 */
	List<FwkDataEntity> selectContFileInfo(FwkParameterMap parameterMap);

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 실적증명 - 첨부파일 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao.java
	 * @Method : updateAcprFileUpdt
	 * @author : JanDi_Eun
	 * @date : 2019. 9. 9. 
	 * @param parameterMap
	 */
	void updateAcprFileUpdt(FwkParameterMap parameterMap);
	
	void insertDextAtchFileRegist(FwkParameterMap parameterMap);
	
	void insertEtcAtchFileRegist(FwkParameterMap parameterMap);

	void selectInsertDextAtchFile(FwkParameterMap parameterMap);

	void deleteDextFileDelete(FwkParameterMap parameterMap);
	
	void deleteEtcFileDelete(FwkParameterMap parameterMap);

	List<FwkDataEntity> selectFileListInqireByFileGrpNo(FwkParameterMap parameterMap);

	List<FwkDataEntity> selectFileListInqireByFileGrpNoAll(FwkParameterMap parameterMap);
	
	void insertAtchFileRegistOne(FwkParameterMap parameterMap);
	
	void insertAtchFileRegistCmtm(FwkParameterMap parameterMap);
	
	
	void deleteAtchFileDelByGrpNo(FwkParameterMap parameterMap);
	
	void deleteAtchFileDelByGrpNoB(FwkParameterMap parameterMap);
	
	//void insertAtchFileCopy(FwkParameterMap parameterMap);
	
	FwkDataEntity selectAtchFileDetail(FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectAtchFileList(FwkParameterMap parameterMap);
	List<FwkDataEntity> selectAtchFileList2(FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectAtchFileCDetail(FwkParameterMap parameterMap);
	List<FwkDataEntity> selectAtchFileCDetail2(FwkParameterMap parameterMap);
	List<FwkDataEntity> selectFileEtcListByFileGrpNo(FwkParameterMap parameterMap);
	
	//파일복사할 그룹번호 조회
	List<FwkDataEntity> selectCopyAtchFile(FwkParameterMap parameterMap);
	
}
