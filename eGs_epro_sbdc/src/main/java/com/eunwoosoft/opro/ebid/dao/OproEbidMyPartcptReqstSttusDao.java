package com.eunwoosoft.opro.ebid.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 전자입찰 > 나의 참가신청 현황 DAO
 * <pre>
 * com.eunwoosoft.opro.ebid.dao 
 *    |_ OproEbidMyPartcptReqstSttusDao.java
 * 
 * </pre>
 * @date : 2015. 03. 10. 오후 04:31:21
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
public interface OproEbidMyPartcptReqstSttusDao {

	/**
	 * <pre>
	 * 1. 개요 : 나의 참가신청 현황 목록 조회_페이징 
	 * 2. 처리내용 : 
	 * 
	 * </pre>
	 * @Method Name : selectMyPartcptReqstSttusListWithPgng
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return 
	 */
	List<FwkDataEntity> selectMyPartcptReqstSttusListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 나의 참가신청 현황 목록 총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectMyPartcptReqstSttusListTotcnt
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int selectMyPartcptReqstSttusListTotcnt(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 나의 참가신청 현황 상세 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectMyPartcptReqstSttusDetail
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectMyPartcptReqstSttusDetail(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 물품 상세 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectBidPrdlstList
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectBidPrdlstList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰 첨부파일 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectBidAtchDoc
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectBidAtchDoc(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰계획 진행 상태 수정
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updatebidPlanProgrsSttusUpdt
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updatebidPlanProgrsSttusUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 공고알림정보 목록 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectPblancNtcnInfoList
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectPblancNtcnInfoList(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 업체정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectEntrpsinfoinqire
	  * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectEntrpsinfoinqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 업체 진행상태 코드 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateEntrpsPartcptnPrstCdUpdt
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updateEntrpsPartcptnPrstCdUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가포기신청서 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidPartcptAbandnReqstdocDatile
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectBidPartcptAbandnReqstdocDatile(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰설명회 정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectBidDcPeoAtndncInfoInqire
	 * @date : 2015. 04. 17.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 17.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap	
	 * 
	 * @return
	 */
	FwkDataEntity selectBidDcPeoAtndncInfoInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 업체 참여 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectEntrpsPartcptnDetailInqire
	 * @date : 2015. 04. 20.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 20.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap	
	 * 
	 * @return
	 */
	FwkDataEntity selectEntrpsPartcptnDetailInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가포기신청서 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertBidPartcptAbandnReqstdocRegist
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertBidPartcptAbandnReqstdocRegist(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 복수예가 목록 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectCompnoPrdprcList
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectCompnoPrdprcList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 참여정보 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateEntrpsPartcptnInfoUpdt
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updateEntrpsPartcptnInfoUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 참여정보 투찰금액 수정 - 수의용
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateEntrpsPartcptnInfoTndrAmtUpdt
	 * @date : 2019. 03. 14.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 14.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	void updateEntrpsPartcptnInfoTndrAmtUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 투찰 품목 정보 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertBddprPrdlstInfoRegist
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertBddprPrdlstInfoRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 업체추첨예가 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : insertEntrpsDrwtPrdprcRegist
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertEntrpsDrwtPrdprcRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰포기 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.opro.ebid.dao.OproEbidMyPartcptReqstSttusDao.java
	 * @Method : updateVendBidAbndUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 8. 27. 
	 * @param parameterMap
	 */
	void updateVendBidAbndUpdt(final FwkParameterMap parameterMap);
	
	void updateVendUserDlgtNUpdt(final FwkParameterMap parameterMap);
	FwkDataEntity selectVendUserSn(final FwkParameterMap parameterMap);
	void insertVendUserRegist(final FwkParameterMap parameterMap);
	void updateVendUserUpdt(final FwkParameterMap parameterMap);
	
	void deleteVendDtlDel(final FwkParameterMap parameterMap);
	void deleteVendEsseDel(final FwkParameterMap parameterMap);
	void deleteTndrItemDel(final FwkParameterMap parameterMap);
	void deleteVendGrntDel(final FwkParameterMap parameterMap);
	void deleteVendFileDel(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectDatpList(final FwkParameterMap parameterMap);
	
	void insertDatpMst(final FwkParameterMap parameterMap);
	
	int selectDatpRegistCnt(final FwkParameterMap parameterMap);
	
	void updateDatpTndrAmt(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectDatpLastAmt(final FwkParameterMap parameterMap);
	
	void updateBiRound(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 투찰내역 마스터 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMyPartcptTndrInfo
	 * @date : 2019. 02. 19.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 19.		은우소프트 멩경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectMyPartcptTndrInfo(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 예가선택 정보 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMyPartcptEsseInfo
	 * @date : 2019. 02. 19.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 19.		은우소프트 멩경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectMyPartcptEsseInfo(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 투찰품목정보조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMyPartcptTndrItemInfo
	 * @date : 2019. 02. 19.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 19.		은우소프트 멩경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectMyPartcptTndrItemInfo(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 업체 첨부파일 그룹번호 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMyPartcptVendFileInfo
	 * @date : 2019. 02. 20.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 20.		은우소프트 멩경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectMyPartcptVendFileInfo(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 보증정보 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : selectMyPartcptGrntInfo
	 * @date : 2019. 02. 21.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 21.		은우소프트 멩경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectMyPartcptGrntInfo(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 2단계경쟁 제안서/규격서 검토 적합/부적합 여부
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.ebid.dao.OproEbidMyPartcptReqstSttusDao.java
	 * @Method : selectBidEstmElcdYn
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 13. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectBidEstmElcdYn(final FwkParameterMap parameterMap);

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰서제출 - 신청인정보 기본세팅 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.ebid.dao.OproEbidMyPartcptReqstSttusDao.java
	 * @Method : selectVendChrgrInfoDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 9. 5. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectVendChrgrInfoDetail(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.ebid.dao.OproEbidMyPartcptReqstSttusDao.java
	 * @Method : selectSbjNoList
	 * @author : JanDi_Eun
	 * @date : 2019. 12. 26. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectSbjNoList(final FwkParameterMap parameterMap);	
	
}