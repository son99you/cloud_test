package com.eunwoosoft.ipro.ebid.dao; 

import java.util.List;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * 
 * com.eunwoosoft.ipro.ebid.dao
 * IproEbidPlanDao.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2018. 2. 20.
 *
 */
public interface IproEbidPlanDao {
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectBidPlanListWithPgng
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectBidPlanListWithPgng(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰작성현황 목록 총개수
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectBidPlanListTotcnt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	int selectBidPlanListTotcnt(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰마스터 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : insertBidPlanRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertBidPlanRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 입찰시간 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : insertBidSchdulRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertBidSchdulRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 입찰라운드 등록 - SELECT TO INSERT
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : insertBidRndSelectRegist
	 * @author : 맹경열
	 * @date : 2019. 02. 11. 
	 * @param parameterMap
	 */
	void insertBidRndSelectRegist(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 입찰 참여 업체 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : insertBidNmfpcEntrpsRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertBidNmfpcEntrpsRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 품목 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : insertBidPrdlstRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertBidPrdlstRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 계약의뢰 품목 저장 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : insertIfBidPrdlstRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 9. 19. 
	 * @param parameterMap
	 */
	void insertIfBidPrdlstRegist(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 첨부문서 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : insertBidAtchDocRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertBidAtchDocRegist(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 공고 번호 생성
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectPblancNoCreat
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @return
	 */
	String selectPblancNoCreat(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 정보 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectBidInfoDetail
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectBidInfoDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 현재 진행중인 재공고건이 있는지 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectReAnnoCount
	 * @author : 하성윤
	 * @date : 2019. 8. 8. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectReAnnoCount(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 구매(조달) 정보 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectPudemaDetail
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectPudemaDetail(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 구매(조달) 품목 목록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectPudemaPrductList
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectPudemaPrductList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 구매(조달) 업체 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectPudemaCnprenList
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectPudemaCnprenList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 품목 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectBidPrdlstList
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectBidPrdlstList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 참여 업체 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectBidNmfpcEntrpsList
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectBidNmfpcEntrpsList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 첨부문서 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectBidAtchDocList
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectBidAtchDocList(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 :  업체 입찰 여부 카운트
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectEntrpsBddprAt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	int selectEntrpsBddprAt(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 마스터 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : updateBidPlanUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void updateBidPlanUpdt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 시간 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : updateBidSchdulUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void updateBidSchdulUpdt(final FwkParameterMap parameterMap);
	void updateBiMstBrfsDtUpdt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 참여 업체 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidNmfpcEntrps
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteBidNmfpcEntrps(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 지정된 입찰 참여 업체 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidNmfpcEntrpsOne
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteBidNmfpcEntrpsOne(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰품목 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidPrdlst
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteBidPrdlst(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 첨부문서 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidAtchDoc
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteBidAtchDoc(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 마스터 삭제 여부 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : updateBidPlanDeleteAtUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void updateBidPlanDeleteAtUpdt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 계획 이행 여부 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : updateBidPlanFlflAtUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void updateBidPlanFlflAtUpdt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 진행이력 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : insertBidProgrsHistRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertBidProgrsHistRegist(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 상태 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : updateBidSttusChangeUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void updateBidSttusChangeUpdt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 라운드 상태 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : updateBidRndSttusChangeUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void updateBidRndSttusChangeUpdt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 예가 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deletePrdprc
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deletePrdprc(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 복수예가 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteCompnoPrdprc
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteCompnoPrdprc(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 로그인 사용자 정보
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectLoginEmplyrInfoInqire
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectLoginEmplyrInfoInqire(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체 진행이력 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteEntrpsProgressHistory
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteEntrpsProgressHistory(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체 참여 상세 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteEntrpsPartcptnDetail
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteEntrpsPartcptnDetail(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체 첨부 문서 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteEntrpsAtchPapers
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteEntrpsAtchPapers(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체 보증 정보 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteEntrpsGrntyPapers
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void deleteEntrpsGrntyPapers(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 유찰 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectFibListWithPgng
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectFibListWithPgng(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 유찰 목록 총개수
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectFibListTotcnt
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	int selectFibListTotcnt(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 재공고 예가 등록 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : insertPrdprcRepblancRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertPrdprcRepblancRegist(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 재공고 예가 마스터 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : insertPrdprcMasterRepblancRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertPrdprcMasterRepblancRegist(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 재공고 예가 상세 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : insertPrdprcDetaiRepblancRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertPrdprcDetaiRepblancRegist(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 재공고 복수예가 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : insertCompnoPrdprcRepblancRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 */
	void insertCompnoPrdprcRepblancRegist(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰 참여 업체 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectPartcptnEntrpsInfo
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectPartcptnEntrpsInfo(final FwkParameterMap parameterMap);
	/**
	 * 
	 * <pre>
	 * 1.개요 : 관심 입찰 참여 업체 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectIntrstEntrpsInfo
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 21. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectIntrstEntrpsInfo(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : updateLtNgrN
	 * @author : sanghoon_joen
	 * @date : 2018. 8. 7. 
	 * @param parameterMap
	 */
	void updateLtNgr(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 의뢰 마스터 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectIfRqstMst
	 * @author : sanghoon_joen
	 * @date : 2018. 9. 18. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectIfRqstMst(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 의뢰 상세 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectIfRqstDtlList
	 * @author : sanghoon_joen
	 * @date : 2018. 9. 18. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity>selectIfRqstDtlList(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 의뢰 등록 여부
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : updateIfBidRegYn
	 * @author : sanghoon_joen
	 * @date : 2018. 9. 19. 
	 * @param parameterMap
	 */
	void updateIfBidRegYn(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 의뢰 열람 여부
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : updateIfCtrqMstOpen
	 * @author : sanghoon_joen
	 * @date : 2018. 10. 4. 
	 * @param parameterMap
	 */
	void updateIfCtrqMstOpen(final FwkParameterMap parameterMap);
	
	void insertBiApprCntc(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectBiApprCntc(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectBfanMst(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectBiApprCntcByKey(final FwkParameterMap parameterMap);
	
	FwkDataEntity selectElgbEstmKd(final FwkParameterMap parameterMap);
	
	int selectBidFibCnt(final FwkParameterMap parameterMap);
	
	void updateBidPvctCnvrAllN(final FwkParameterMap parameterMap);
	
	void updateBidPvctCnvrY(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 구매접수 마스터 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectPaMstInfo
	 * @author : 맹경열
	 * @date : 2019. 02. 14. 
	 * @param parameterMap
	 * @return
	 */
	FwkDataEntity selectPaMstInfo(final FwkParameterMap parameterMap);
	
	/**
	 * <pre>
	 * 1.개요 : 구매접수 물품 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : selectPaItemInfo
	 * @author : 맹경열
	 * @date : 2019. 02. 14. 
	 * @param parameterMap
	 * @return
	 */
	List<FwkDataEntity> selectPaItemInfo(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰결재연계 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidApprCntc
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 12. 
	 * @param parameterMap
	 */
	void deleteBidApprCntc(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰설명회업체 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidPartcptEntrps
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 12. 
	 * @param parameterMap
	 */
	void deleteBidPartcptEntrps(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰첨부파일 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidFile
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 12. 
	 * @param parameterMap
	 */
	void deleteBidFile(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰마스터 삭제 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidMst
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 12. 
	 * @param parameterMap
	 */
	void deleteBidMst(final FwkParameterMap parameterMap);	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰진행이력 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidProgHist
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 12. 
	 * @param parameterMap
	 */
	void deleteBidProgHist(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰라운드 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidRound
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 12. 
	 * @param parameterMap
	 */
	void deleteBidRound(final FwkParameterMap parameterMap);	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 투찰품목 삭제 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidTndrItem
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 12. 
	 * @param parameterMap
	 */
	void deleteBidTndrItem(final FwkParameterMap parameterMap);
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체공동 삭제 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidVendAsso
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 12. 
	 * @param parameterMap
	 */
	void deleteBidVendAsso(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 :  업체개찰 삭제 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidVendBiop
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 12. 
	 * @param parameterMap
	 */
	void deleteBidVendBiop(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체예가추첨 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidVendEsse
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 12. 
	 * @param parameterMap
	 */
	void deleteBidVendEsse(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰업체파일 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteVendFileDel
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 12. 
	 * @param parameterMap
	 */
	void deleteVendFileDel(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰업체보증 삭제
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteVendGrntDel
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 12. 
	 * @param parameterMap
	 */
	void deleteVendGrntDel(final FwkParameterMap parameterMap);

	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체진행이력 삭제 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteEntrpsProgrsHist
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 12. 
	 * @param parameterMap
	 */
	void deleteEntrpsProgrsHist(final FwkParameterMap parameterMap);	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰업체마스터 삭제 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao.java
	 * @Method : deleteBidVendMst
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 12. 
	 * @param parameterMap
	 */
	void deleteBidVendMst(final FwkParameterMap parameterMap);	
}