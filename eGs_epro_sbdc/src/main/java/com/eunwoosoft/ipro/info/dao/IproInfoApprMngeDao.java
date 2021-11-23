package com.eunwoosoft.ipro.info.dao;

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;


/**
 * 결재관리
 *
 * @author : 은우소프트 맹경열
 * @date : 2018. 02. 26.
 * @version : 1.0
 */
public interface IproInfoApprMngeDao {
	
	/**
	 * <pre>
	 * 1. 개요 : 결재선조회_페이징
	 * 2. 처리내용 :
	 *
	 * </pre>
	 * @Method Name : getMmApplMstListWithPgng
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 08.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	List<FwkDataEntity> getMmApplMstListWithPgng(final FwkParameterMap parameterMap);
  	int getMmApplMstListTotcnt(final FwkParameterMap parameterMap);

  	/**
  	 *
  	 * <pre>
  	 * 1.개요 : 결재선번호가 없을경우 맨 처음 조회된 목록 가져오기
  	 * 2.처리내용 :
  	 * </pre>
  	 * @Method : getMmApplMstListWithPgngTop
  	 * @author : sjKim
  	 * @date : 2018. 11. 9.
  	 * @history
  	 * =============================================
  	 *  date           name           description
  	 * ---------------------------------------------
  	 * 2018. 11. 9.        sjKim          최초 생성
  	 * =============================================
  	 */
  	FwkDataEntity getMmApplMstListWithPgngTop(final FwkParameterMap parameterMap);



	/**
	 * <pre>
	 * 1. 개요 : 결재선등록
	 * 2. 처리내용 :
	 *
	 * </pre>
	 * @Method Name : infoApprlineRegistMaster
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 08.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
  	void infoApprlineRegistMaster(final FwkParameterMap parameterMap);
  	void infoApprlineRegistUser(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 결재선삭제
	 * 2. 처리내용 :
	 *
	 * </pre>
	 * @Method Name : infoApprlineDeleteMaster
	 * @date : 2018. 03. 09.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 09.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
  	void updateApprlineDelAt(final FwkParameterMap parameterMap);
  	void infoApprlineDeleteUser(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 결재선조회_상세
	 * 2. 처리내용 :
	 *
	 * </pre>
	 * @Method Name : getMmAprpList
	 * @date : 2018. 03. 09.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 09.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	List<FwkDataEntity> getMmAprpList(final FwkParameterMap parameterMap);
  	int getMmAprpListTotcnt(final FwkParameterMap parameterMap);

  	/**
  	 *
  	 * <pre>
  	 * 1.개요 : blossom 결재선 결재자 정보
  	 * 2.처리내용 :
  	 * </pre>
  	 * @Method : getBlosMmAprpList
  	 * @author : sjKim
  	 * @date : 2018. 11. 14.
  	 * @history
  	 * =============================================
  	 *  date           name           description
  	 * ---------------------------------------------
  	 * 2018. 11. 14.        sjKim          최초 생성
  	 * =============================================
  	 */
  	List<FwkDataEntity> getBlosMmAprpList(final FwkParameterMap parameterMap);
  	int getBlosMmAprpListTotcnt(final FwkParameterMap parameterMap);
	/**
	 * <pre>
	 * 1. 개요 : 결재선수정
	 * 2. 처리내용 :
	 *
	 * </pre>
	 * @Method Name : infoApprlineUpdateMaster
	 * @date : 2018. 03. 09.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 09.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
  	void infoApprlineUpdateMaster(final FwkParameterMap parameterMap);
  	void infoApprlineUpdateUser(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 결재대상
	 * 2. 처리내용 :
	 *
	 * </pre>
	 * @Method Name : getApprMstListWithPgng
	 * @date : 2018. 03. 15.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 15.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
  	List<FwkDataEntity> getApprMstListWithPgng(final FwkParameterMap parameterMap);
	int getApprMstListTotcnt(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 결재마스터 저장
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : infoApprMasterRegist
	 * @date : 2018. 11. 23.
	 * @author : 은우소프트
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 11. 23.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	void infoApprMasterRegist(final FwkParameterMap parameterMap);


	/**
	 *
	 * <pre>
	 * 1.개요 :
	 * 2.처리내용 : 결재 프로시저 호출
	 * </pre>
	 * @Method : callApprPro
	 * @author : juyeon_Lee
	 * @date : 2018. 12. 11.
	 * @history
	 * =============================================
	 *  date           name           description
	 * ---------------------------------------------
	 * 2018. 12. 11.        juyeon_Lee          최초 생성
	 * =============================================
	 */
	void callApprPro(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 결재대상자 저장
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : infoApprUserRegist
	 * @date : 2018. 11. 23.
	 * @author : 은우소프트
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 11. 23.		은우소프트 			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	void infoApprUserRegist(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 결재마스터 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : getMmApprMstInfo
	 * @date : 2018. 03. 20.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 20.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	FwkDataEntity getMmApprMstInfo(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 결재대상자 조회
	 * 2. 처리내용 : 결재대상자 조회
	 * </pre>
	 * @Method Name : getMmApprTglInfo
	 * @date : 2018. 03. 20.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 20.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	List<FwkDataEntity> getMmApprTglInfo(final FwkParameterMap parameterMap);
	int getMmApprTglInfoTotcnt(final FwkParameterMap parameterMap);

	int getBizrnoChk(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 결재마스터 업데이트
	 * 2. 처리내용 : 결재마스터 업데이트
	 * </pre>
	 * @Method Name : infoApprMstUpdate
	 * @date : 2018. 03. 26.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 26.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	void infoApprMstUpdate(final FwkParameterMap parameterMap);

	void apprMstUpdate(final FwkParameterMap parameterMap);

	void apprMstUpdateRsn(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 결재대상자 업데이트
	 * 2. 처리내용 : 결재대상자 업데이트
	 * </pre>
	 * @Method Name : infoApprTglUpdate
	 * @date : 2018. 03. 26.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 26.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	void infoApprTglUpdate(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 결재마스터 조회
	 * 2. 처리내용 : 결재대상자 업데이트
	 * </pre>
	 * @Method Name : getApprMstDetail
	 * @date : 2018. 03. 26.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 26.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	FwkDataEntity getApprMstDetail(final FwkParameterMap parameterMap);

	/**
     * <pre>
     * 1. 개요 : 공통 업데이트
     * 2. 처리내용 :
     * </pre>
     * @Query ID : statusByCommUpdt
	 * @date : 2015. 06 15.
	 * @author : 은우소프트 김봉수
     * @history :
     *  =======================================================
     *  변경일             		작성자                     		변경내용
     *  =======================================================
	 *	2015. 06. 15.		  은우소프트 김봉수		  				최초 작성
     *  =======================================================
	 */
	void statusByCommUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 메인입찰 공통 리스트 정보
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_SkHyEng.com.eunwoosoft.ipro.info.dao.IproInfoApprMngeDao.java
	 * @Method : getApprMstListMain
	 * @author : 
	 * @date : 2018. 12. 03.
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> getApprMstListMain(final FwkParameterMap parameterMap);
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 기준정보 > 결재관리 > 결재완료내역 목록 조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_SkHyEng.com.eunwoosoft.ipro.info.dao.IproInfoApprMngeDao.java
	 * @Method : selectApplResultListWithPgng
	 * @author : JanDi_Eun
	 * @date : 2020. 8. 6. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectApplResultListWithPgng(final FwkParameterMap parameterMap);
	int selectApplResultListTotcnt(final FwkParameterMap parameterMap);
}
