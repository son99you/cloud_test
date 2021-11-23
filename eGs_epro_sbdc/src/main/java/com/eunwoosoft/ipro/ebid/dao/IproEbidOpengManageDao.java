package com.eunwoosoft.ipro.ebid.dao; 

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.ParameterMap;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;



/**
 * 개찰관리 > 개찰관리 DAO
 * <pre>
 * com.eunwoosoft.ipro.ebid.dao 
 *    |_ IproEbidOpengManageDao.java
 * 
 * </pre>
 * @date : 2015. 01. 23. 오후 08:30:10
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
public interface IproEbidOpengManageDao {

	/**
	 * <pre>
	 * 1. 개요 : 개찰관리 목록조회_페이징 
	 * 2. 처리내용 : 
	 * 
	 * </pre>
	 * @Method Name : selectOpengManageListWithPgng
	 * @date : 2015. 02. 27.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 27.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return 
	 */
	List<FwkDataEntity> selectOpengManageListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 개찰관리 목록총건수조회 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectOpengManageListTotcnt
	 * @date : 2015. 02. 27.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 27.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	int selectOpengManageListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예가 금액 수정
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updatePrdprcAmountUpdt
	 * @date : 2015. 03. 24.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 24.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updatePrdprcAmountUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 복수 예가 금액 수정
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updateCompnoPrdprcAmountUpdt
	 * @date : 2015. 03. 24.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 24.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updateCompnoPrdprcAmountUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 참여 업체 정보 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectPartcptnEntrpsInfoInqire
	 * @date : 2015. 03. 24.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 24.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectPartcptnEntrpsInfoInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 기술평가 완료 여부 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectTchqvlnComptAtInqire
	 * @date : 2015. 03. 24.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 24.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectTchqvlnComptAtInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 투찰 품목 목록 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectBddprPrdlstListInqire
	 * @date : 2015. 03. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectBddprPrdlstListInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 복수 예가 순위 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectCompnoPrdprcRankInqire
	 * @date : 2015. 03. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectCompnoPrdprcRankInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 미 투찰 업체 조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectUnBddprEntrpsInqire
	 * @date : 2015. 03. 30.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 30.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectUnBddprEntrpsInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 복호화 투찰 금액 저장
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updateDecdBddprAmountStre
	 * @date : 2015. 03. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updateDecdBddprAmountStre(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 복호화 품목 단가 금액 저장
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updateDecdPrdlstUntpcAmountStre
	 * @date : 2015. 03. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updateDecdPrdlstUntpcAmountStre(final FwkParameterMap parameterMap);
	

	/**
	 * <pre>
	 * 1. 개요 : 추첨 복수예가 저장
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updateDrwtCompnoPrdprcStre
	 * @date : 2015. 03. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updateDrwtCompnoPrdprcStre(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 복수예가 선택 저장
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updateCompnoPrdprcChoiseStre
	 * @date : 2015. 03. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updateCompnoPrdprcChoiseStre(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 예정가격금액 저장
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updatePrearngePcAmountStre
	 * @date : 2015. 03. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updatePrearngePcAmountStre(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 복수예가 순위 저장
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updateCompnoPrdprcRankStre
	 * @date : 2015. 03. 25.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 25.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updateCompnoPrdprcRankStre(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 공고 진행상태 코드 수정 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updatePblancPrstCdUpdt
	 * @date : 2015. 03. 30.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 30.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updatePblancPrstCdUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 진행 이력 등록 
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : insertEntrpsProgrsHistRegist
	 * @date : 2015. 03. 30.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 30.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertEntrpsProgrsHistRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 자동 입찰포기 등록
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : insertAtmcBiabRegist
	 * @date : 2015. 03. 30.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 30.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertAtmcBiabRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체 개찰 정보 등록
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : insertEntrpsOpengInfoRegist
	 * @date : 2015. 03. 31.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 31.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void insertEntrpsOpengInfoRegist(final FwkParameterMap parameterMap);

	/**
	 * <pre>
	 * 1. 개요 : 업체입찰정보조회
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectEntrpsBidInfoInqire
	 * @date : 2015. 03. 31.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 31.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectEntrpsBidInfoInqire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 :  업체 적격검토 등록
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updateEntrpsProperExmntRegist
	 * @date : 2015. 03. 31.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 31.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updateEntrpsProperExmntRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 :  가격점수 산출
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : selectPcScoreComput
	 * @date : 2015. 05. 07.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 05. 07.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	String selectPcScoreComput(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 :  가격점수등록
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updatePcScoreUpdt
	 * @date : 2015. 03. 31.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 31.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updatePcScoreUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 :  업체 진행 상태 수정
	 * 2. 처리내용 : 
	 * 		쿼리문 작성
	 * </pre>
	 * @Method Name : updateEntrpsProgrsSttusUpdt
	 * @date : 2015. 05. 01.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 05. 01.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	void updateEntrpsProgrsSttusUpdt(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 협력업체관리 상세 
	 * 2. 처리내용 : 
	 *     	쿼리문 작성
	 * </pre>
	 * @Method Name : selectCcpyManageDetail
	 * @date : 2015. 05. 12.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 05. 12.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 업체등록번호
	 * @return - 협력업체관리상세
	 */	
	FwkDataEntity selectCcpyManageDetail(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체신용평가정보 목록
	 * 2. 처리내용 : 
	 *     	쿼리문 작성
	 * </pre>
	 * @Method Name : selectEntrpsCdtlList
	 * @date : 2015. 05. 12.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 05. 12.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 업체등록번호
	 * @return - 업체신용평가정보 목록
	 */	
	List<FwkDataEntity> selectEntrpsCdtlList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체상태정보 목록
	 * 2. 처리내용 : 
	 *     	쿼리문 작성
	 * </pre>
	 * @Method Name : selectEntrpsSttusList
	 * @date : 2015. 05. 12.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 05. 12.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 업체등록번호
	 * @return - 업체상태정보 목록
	 */	
	List<FwkDataEntity> selectEntrpsSttusList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 공급물품정보 목록
	 * 2. 처리내용 : 
	 *     	쿼리문 작성
	 * </pre>
	 * @Method Name : selectSuplyThngList
	 * @date : 2015. 05. 12.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 05. 12.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 업체등록번호
	 * @return - 공급물품정보 목록
	 */	
	List<FwkDataEntity> selectSuplyThngList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 공장정보 목록
	 * 2. 처리내용 : 
	 *     	쿼리문 작성
	 * </pre>
	 * @Method Name : selectFctryList
	 * @date : 2015. 05. 12.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 05. 12.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 업체등록번호
	 * @return - 공장정보 목록
	 */	
	List<FwkDataEntity> selectFctryList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 제조물품정보 목록
	 * 2. 처리내용 : 
	 *     	쿼리문 작성
	 * </pre>
	 * @Method Name : selectProdctQlyList
	 * @date : 2015. 05. 12.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 05. 12.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 업체등록번호
	 * @return - 제조물품정보 목록
	 */	
	List<FwkDataEntity> selectProdctQlyList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업종정보 목록
	 * 2. 처리내용 : 
	 *     	쿼리문 작성
	 * </pre>
	 * @Method Name : selectIndutyList
	 * @date : 2015. 05. 12.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 05. 12.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 업체등록번호
	 * @return - 업종정보 목록
	 */	
	List<FwkDataEntity> selectIndutyList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰대리인정보 목록
	 * 2. 처리내용 : 
	 *     	쿼리문 작성
	 * </pre>
	 * @Method Name : selectBidAgentList
	 * @date : 2015. 05. 12.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 05. 12.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 업체등록번호
	 * @return - 입찰대리인정보 목록
	 */	
	List<FwkDataEntity> selectBidAgentList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 지문보안토큰정보 목록
	 * 2. 처리내용 : 
	 *     	쿼리문 작성
	 * </pre>
	 * @Method Name : selectFngprtScrtyTknList
	 * @date : 2015. 05. 12.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 05. 12.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 업체등록번호
	 * @return - 지문보안토큰정보 목록
	 */	
	List<FwkDataEntity> selectFngprtScrtyTknList(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 업체제재 등록
	 * 2. 처리내용 : 
	 *     	쿼리문 작성
	 * </pre>
	 * @Method Name : insertEntrpsPunishRegist
	 * @date : 2015. 05. 13.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 05. 13.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 업체제재 등록 정보
	 * @return - 
	 */	
	void insertEntrpsPunishRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 대표자 목록
	 * 2. 처리내용 : 
	 *     	쿼리문 작성
	 * </pre>
	 * @Method Name : selectEntrpsRprsntvList
	 * @date : 2015. 05. 12.
	 * @author : 은우소프트 전상훈
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 05. 12.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap - 업체등록번호
	 * @return - 대표자 목록
	 */	
	List<FwkDataEntity> selectEntrpsRprsntvList(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 기술점수 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidOpengManageDao.java
	 * @Method : insertTchnScr
	 * @author : sanghoon_joen
	 * @date : 2018. 9. 3. 
	 * @param parameterMap
	 */
	void insertTchnScr(final FwkParameterMap parameterMap);
	
	void updateTchnScrRegist(final FwkParameterMap parameterMap);
	
	void updateEstmElcdRegist(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectSmprBid(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectVendMstRank(final FwkParameterMap parameterMap);
	
	List<FwkDataEntity> selectDatpList(final FwkParameterMap parameterMap);
	
	int selectDatpCnt(final FwkParameterMap parameterMap);
	
	void updateDatpOpen(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 협상결과정보
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : negoRsltDetailInquire
	 * @date : 2019. 02. 27.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 27.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- 
	 */
	FwkDataEntity negoRsltDetailInquire(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1. 개요 : 협상결과 업데이트
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : updateNegoRsltInfo
	 * @date : 2019. 02. 27.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 27.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- 
	 */
	void updateNegoRsltInfo(final FwkParameterMap parameterMap);
	
}