package com.eunwoosoft.ipro.appr.dao;

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
public interface IproApprMngDao {


	// 결재등록 목록
	List<FwkDataEntity> selectApplMstList(final FwkParameterMap parameterMap);
  	int selectApplMstListTotCnt(final FwkParameterMap parameterMap);

  	// 결재등록
  	void insertApplMst(final FwkParameterMap parameterMap);
  	void insertApplAprp(final FwkParameterMap parameterMap);
  	
  	List<FwkDataEntity> selectApprAprpList(final FwkParameterMap parameterMap);
  	int selectApprAprpListTotCnt(final FwkParameterMap parameterMap);

  	void updateApplMst(final FwkParameterMap parameterMap);
  	void updateApplMstDelAt(final FwkParameterMap parameterMap);
  	
  	
  	void updateApplAprp(final FwkParameterMap parameterMap);
  	
  	
  	// 결재대상
  	List<FwkDataEntity> selectApprObjList(final FwkParameterMap parameterMap);
	int selectApprObjListTotCnt(final FwkParameterMap parameterMap);
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	void infoApprlineDeleteUser(final FwkParameterMap parameterMap);


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

	List<FwkDataEntity> selectApprObjAprpList(final FwkParameterMap parameterMap);
//	int selectApprAprpListTotCnt(final FwkParameterMap parameterMap);

	void updateApprMst(final FwkParameterMap parameterMap);

	void apprMstUpdate(final FwkParameterMap parameterMap);

	void apprMstUpdateRsn(final FwkParameterMap parameterMap);
	
	void updateApprAprpYn(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 결재마스터 조회
	 * 2. 처리내용 : 결재대상자 업데이트
	 * </pre>
	 * @Method Name : selectApprObjMstDetail
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
	FwkDataEntity selectApprObjMstDetail(final FwkParameterMap parameterMap);

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
	 * 
	 * <pre>
	 * 1.개요 : 기준정보 > 결재관리 > 결재완료내역 목록 조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_SkHyEng.com.eunwoosoft.ipro.info.dao.IproInfoApprMngeDao.java
	 * @Method : selectApprCmplList
	 * @author : JanDi_Eun
	 * @date : 2020. 8. 6. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectApprCmplList(final FwkParameterMap parameterMap);
	int selectApprCmplListTotCnt(final FwkParameterMap parameterMap);
}
