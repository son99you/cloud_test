package com.eunwoosoft.ipro.estm.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.estm.dao.IproEstmCmtmMngCmplDao;
import com.eunwoosoft.ipro.estm.service.IproEstmCmtmMngCmplService;

@Service(value="iproEstmCmtmMngCmplService")
public class IproEstmCmtmMngCmplServiceImpl extends AbstractFwkService implements IproEstmCmtmMngCmplService {
	
	private static final Logger LOG = LoggerFactory.getLogger(IproEstmCmtmMngCmplServiceImpl.class);
	
	@Resource(name="iproEstmCmtmMngCmplDao")
	private IproEstmCmtmMngCmplDao iproEstmCmtmMngCmplDao;
	
	@Resource(name="comAtmaAtchFileDao") 
	private ComAtmaAtchFileDao comAtmaAtchFileDao;

	@Override
	public FwkTransferObject cmtmCmplList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}

		parameterMap.put("P_ESTM_PSCD_GBN_S", "ESTM_CMPL");
		
		trans.put("cmtmCmplList", iproEstmCmtmMngCmplDao.selectCmtmCmplList(parameterMap));
		trans.put("cmtmCmplListTotCnt", iproEstmCmtmMngCmplDao.selectCmtmCmplListTotCnt(parameterMap));
	
		return trans;
	}
	
	@Override
	public FwkTransferObject cmtmCmplListExcelDwld(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		parameterMap.put("P_ESTM_PSCD_GBN_S", "ESTM_CMPL");
		
		trans.put("cmtmCmplList", iproEstmCmtmMngCmplDao.selectCmtmCmplListExcelDwld(parameterMap));
	
		return trans;
	}
	

	@Override
	public FwkTransferObject cmtmMngCmplDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmMngCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가절차 조회
		List<FwkDataEntity> estmProcdList = iproEstmCmtmMngCmplDao.selectEstmProcdList(parameterMap);   // T_ESTM_PROCD
		trans.put("estmProcdList", estmProcdList);
		
		// 첨부파일 조회
		parameterMap.put("P_ESTM_FSCD", "MST");
		FwkDataEntity estmFile = (FwkDataEntity) iproEstmCmtmMngCmplDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmFile", estmFile);
		
		// 심사위원 서명파일 첨부파일 그룹번호 조회
		parameterMap.put("P_ESTM_FSCD", "SIGN");
		FwkDataEntity estmSignFile = (FwkDataEntity) iproEstmCmtmMngCmplDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmSignFile", estmSignFile);
		
		// 심사위원 서명파일 조회
		if (estmSignFile != null) {
			String ESTM_SIGN_FILE_GRP_NO = estmSignFile.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", ESTM_SIGN_FILE_GRP_NO);
			trans.put("estmSignFileList", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmtmMngCmplDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		return trans;
	}

	@Override
	public FwkTransferObject cmtmMngCmplObjDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmMngCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가대상정보 조회
		List<FwkDataEntity> estmObjList = iproEstmCmtmMngCmplDao.selectEstmObjList(parameterMap);   // T_ESTM_OBJ
		trans.put("estmObjList", estmObjList);
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmtmMngCmplDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		return trans;
	}

	@Override
	public FwkTransferObject cmtmMngCmplCmtmDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		String P_CMPL_YN = parameterMap.getString("P_CMPL_YN");   // 최종여부
		parameterMap.put("P_CMPL_YN", P_CMPL_YN);
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmMngCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		int P_ESTM_CMTM_SLCT_NGR_CLICK = parameterMap.getInt("P_ESTM_CMTM_SLCT_NGR_CLICK");
		
		List<FwkDataEntity> innEstmCmtmList = new ArrayList<FwkDataEntity>();
		
		// 내부평가위원 조회
		parameterMap.put("P_INO_CMTM_SECD", "INN");
		parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
		innEstmCmtmList = iproEstmCmtmMngCmplDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("innEstmCmtmList", innEstmCmtmList);
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmtmMngCmplDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		if(P_ESTM_CMTM_SLCT_NGR_CLICK > 0){
			
			innEstmCmtmList = null;
			
			System.err.println("/********** 차수에 따른 평가위원정보 조회 **********/");
			parameterMap.put("P_ESTM_CMTM_SLCT_NGR", P_ESTM_CMTM_SLCT_NGR_CLICK);
			
			// 내부평가위원 조회
			parameterMap.put("P_INO_CMTM_SECD", "INN");
			innEstmCmtmList = iproEstmCmtmMngCmplDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
			trans.put("innEstmCmtmList", innEstmCmtmList);
			
		}
		
		if("Y".equals(P_CMPL_YN)){   // 최종
			
			innEstmCmtmList = null;
			
			System.err.println("========== [최종] ==========");
			
			parameterMap.put("P_SLCT_YN", "Y");
			parameterMap.put("P_ESTM_CMTM_SLCT_NGR", "");
			
			
			// 내부평가위원 조회
			parameterMap.put("P_INO_CMTM_SECD", "INN");
			parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
			innEstmCmtmList = iproEstmCmtmMngCmplDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			trans.put("innEstmCmtmList", innEstmCmtmList);
		}

		return trans;
	}

	@Override
	public FwkTransferObject cmtmMngCmplProcdADetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmMngCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// 평가절차 조회
		FwkDataEntity estmProcdDetail = iproEstmCmtmMngCmplDao.selectEstmProcdDetail(parameterMap);   // T_ESTM_PROCD
		
		// 평가대상 목록 조회
		List<FwkDataEntity> estmObjList = iproEstmCmtmMngCmplDao.selectEstmObjValueList(parameterMap);
		
		// 평가위원 목록 조회
		List<FwkDataEntity> estmCmtmLastList = iproEstmCmtmMngCmplDao.selectEstmCmtmLastList(parameterMap);   // T_ESTM_CMTM
		
		// 평가위원+평가대상 목록 조회
		List<FwkDataEntity> estmValueList = iproEstmCmtmMngCmplDao.selectEstmValueList(parameterMap);   // T_ESTM_CMTM
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmtmMngCmplDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		
		trans.put("estmProcdDetail", estmProcdDetail);
		trans.put("estmValueList", estmValueList);
		trans.put("estmObjList", estmObjList);
		trans.put("estmObjListTotCnt", iproEstmCmtmMngCmplDao.selectEstmObjValueListTotCnt(parameterMap));
		trans.put("estmCmtmLastList", estmCmtmLastList);
		trans.put("estmCmtmLastListCnt", estmCmtmLastList.size());
		trans.put("estmMngMstDetail", estmMngMstDetail);

		return trans;
	}

	@Override
	public FwkTransferObject cmtmMngCmplProcdBDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmMngCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// 평가절차 조회
		FwkDataEntity estmProcdDetail = iproEstmCmtmMngCmplDao.selectEstmProcdDetail(parameterMap);   // T_ESTM_PROCD
		
		// 평가대상 목록 조회
		List<FwkDataEntity> estmObjList = iproEstmCmtmMngCmplDao.selectEstmObjValueList(parameterMap);
		
		// 평가담당자 조회
		List<FwkDataEntity> estmChrgrList = iproEstmCmtmMngCmplDao.selectEstmChrgrList(parameterMap);   // T_ESTM_CMTM
		
		// 평가담당자+평가대상 목록 조회
		List<FwkDataEntity> estmChrgrValueList = iproEstmCmtmMngCmplDao.selectEstmChrgrValueList(parameterMap);   // T_ESTM_CMTM
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmtmMngCmplDao.selectEstmTabList(parameterMap);
		
		
		trans.put("estmProcdDetail", estmProcdDetail);
		trans.put("estmObjList", estmObjList);
		trans.put("estmObjListTotCnt", iproEstmCmtmMngCmplDao.selectEstmObjValueListTotCnt(parameterMap));
		trans.put("estmChrgrList", estmChrgrList);
		trans.put("estmChrgrValueList", estmChrgrValueList);
		trans.put("estmMngMstDetail", estmMngMstDetail);
		trans.put("estmTabList", estmTabList);
		return trans;
	}

	@Override
	public FwkTransferObject cmtmMngCmplResultDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmMngCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);

		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmtmMngCmplDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
			
		// 조회리스트 추가 //
		// 평가결과추가조회 리스트
		List<FwkDataEntity> searchColList = iproEstmCmtmMngCmplDao.selectEstmSearchColList(parameterMap); 
		trans.put("searchColList", searchColList);
			
		// 평가결과 ITEM MAX값 조회
		int maxSearchColLength = iproEstmCmtmMngCmplDao.selectEstmSearchColLength(parameterMap);   // T_ESTM_MNG_MST
		trans.put("maxSearchColLength", maxSearchColLength);
		// 조회리스트 종료//
		
		// 평가서식절차구분코드 가 적격인 경우 시작//
		List<FwkDataEntity> estmResultProcdObjSlctList = iproEstmCmtmMngCmplDao.selectEstmResultProcdObjSlctList(parameterMap);
		trans.put("estmResultProcdObjSlctList", estmResultProcdObjSlctList);
		// 평가서식절차구분코드 가 적격인 경우 종료//
		
		// 평가결과목록 리스트 시작//
		/**
		 *  평가대상 리스트
		 *  (모든 평가대상을 불러온다.)
		 */
		
		parameterMap.put("pageYn", "Y");
		
		List<FwkDataEntity> estmResultObjAllList = iproEstmCmtmMngCmplDao.selectEstmResultObjAllList(parameterMap); 
		trans.put("estmResultObjAllList", estmResultObjAllList);
		trans.put("estmResultObjAllListTotCnt", iproEstmCmtmMngCmplDao.selectEstmResultObjAllListTotCnt(parameterMap));
			
		/**
		 * 평가위원 리스트
		 */
		List<FwkDataEntity> estmResultCmtmAllList = iproEstmCmtmMngCmplDao.selectEstmResultCmtmAllList(parameterMap); 
		trans.put("estmResultCmtmAllList", estmResultCmtmAllList);
		trans.put("estmResultCmtmAllListCnt", estmResultCmtmAllList.size());
		trans.put("estmResultObjAllListCnt", estmResultObjAllList.size());	
			
			//(평가항목)P_SEARCH_ITEM
			//(평가위원총점)P_SEARCH_ITEM_CMTMTOT
			//(평가총점)P_SEARCH_ESTM_PROCD_SEQ_TOT
			//(최종점수합계적용여부)P_SEARCH_ITEM_TOTSUMAT
			//(최종점수합계적용비율)P_ESTM_PROCD_SEQ_TOTSUM${ data.ESTM_PROCD_SEQ }
		Object SEARCH_ITEM = parameterMap.get("P_SEARCH_ITEM");
	//				Object SEARCH_ITEM_CMTMTOT = parameterMap.get("P_SEARCH_ITEM_CMTMTOT");
	//				Object SEARCH_ESTM_PROCD_SEQ_TOT = parameterMap.get("P_SEARCH_ESTM_PROCD_SEQ_TOT");
	//				Object SEARCH_ITEM_TOTSUMAT = parameterMap.get("P_SEARCH_ITEM_TOTSUMAT");
	
		ArrayList<String> P_SEARCH_ITEM_LIST = new ArrayList<String>();
		ArrayList<String> P_SEARCH_ITEM_CHECK_LIST = new ArrayList<String>();
	//				ArrayList<String> P_SEARCH_ITEM_CMTMTOT_LIST = new ArrayList<String>();
	//				ArrayList<String> P_SEARCH_ITEM_CMTMTOT_CHECK_LIST = new ArrayList<String>();
	//				ArrayList<String> P_SEARCH_ESTM_PROCD_SEQ_LIST = new ArrayList<String>();
	//				ArrayList<String> P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST = new ArrayList<String>();
			
			
		String SEARCH_CHECK = "";
		// 평가항목
		if(SEARCH_ITEM instanceof String) {
			P_SEARCH_ITEM_LIST.add((String) SEARCH_ITEM);
			SEARCH_CHECK = SEARCH_ITEM.toString();
			P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
		}else {
			if(SEARCH_ITEM instanceof ArrayList) {
				ArrayList<String> SEARCH_ITEMList = (ArrayList) SEARCH_ITEM;
				for (int idx = 0; idx < SEARCH_ITEMList.size(); idx++) {
					SEARCH_CHECK = SEARCH_ITEMList.get(idx).toString();
					P_SEARCH_ITEM_LIST.add(SEARCH_ITEMList.get(idx));
					P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
				}
			}else if(SEARCH_ITEM instanceof String[]) {
				String[] SEARCH_ITEMList = (String[]) SEARCH_ITEM;
				for (int idx = 0; idx < SEARCH_ITEMList.length; idx++) {
					SEARCH_CHECK = SEARCH_ITEMList[idx].toString();
					P_SEARCH_ITEM_LIST.add(SEARCH_ITEMList[idx]);
					P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
				}
			}
		}
		
		//평가항목 체크
		if(P_SEARCH_ITEM_LIST.size() > 0){
			parameterMap.put("P_SEARCH_ITEM_LIST", P_SEARCH_ITEM_LIST);
			parameterMap.put("P_CHECK_LIST", P_SEARCH_ITEM_CHECK_LIST);
			List<FwkDataEntity> estmResultSearchCheckList = iproEstmCmtmMngCmplDao.selectEstmResultSearchCheckList(parameterMap);
			trans.put("SEARCH_ITEM_CHECK_LIST", estmResultSearchCheckList);
			
		}
	
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultProcdSeqList = iproEstmCmtmMngCmplDao.selectEstmResultProcdSeqList(parameterMap);
		trans.put("estmResultProcdSeqList", estmResultProcdSeqList);
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 종료//
		
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultItemNoList = iproEstmCmtmMngCmplDao.selectEstmResultItemNoList(parameterMap);
		trans.put("estmResultItemNoList", estmResultItemNoList);
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 종료//
		
		
		/**
		 * 평가결과리스트
		 */
		List<FwkDataEntity> estmResultItemAllList = iproEstmCmtmMngCmplDao.selectEstmResultItemAllList(parameterMap); 
		trans.put("estmResultItemAllList", estmResultItemAllList);
		// 평가결과목록 리스트 종료//

		return trans;
	}

	
	
	@Override
	public FwkTransferObject cmtmMngCmplResultDetailExcelDwld(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmMngCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);

		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmtmMngCmplDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
			
		// 조회리스트 추가 //
		// 평가결과추가조회 리스트
		List<FwkDataEntity> searchColList = iproEstmCmtmMngCmplDao.selectEstmSearchColList(parameterMap); 
		trans.put("searchColList", searchColList);
			
		// 평가결과 ITEM MAX값 조회
		int maxSearchColLength = iproEstmCmtmMngCmplDao.selectEstmSearchColLength(parameterMap);   // T_ESTM_MNG_MST
		trans.put("maxSearchColLength", maxSearchColLength);
		// 조회리스트 종료//
		
		// 평가서식절차구분코드 가 적격인 경우 시작//
		List<FwkDataEntity> estmResultProcdObjSlctList = iproEstmCmtmMngCmplDao.selectEstmResultProcdObjSlctList(parameterMap);
		trans.put("estmResultProcdObjSlctList", estmResultProcdObjSlctList);
		// 평가서식절차구분코드 가 적격인 경우 종료//
		
		// 평가결과목록 리스트 시작//
		/**
		 *  평가대상 리스트
		 *  (모든 평가대상을 불러온다.)
		 */
		
		parameterMap.put("pageYn", "N");
		
		List<FwkDataEntity> estmResultObjAllList = iproEstmCmtmMngCmplDao.selectEstmResultObjAllList(parameterMap); 
		trans.put("estmResultObjAllList", estmResultObjAllList);
			
		/**
		 * 평가위원 리스트
		 */
		List<FwkDataEntity> estmResultCmtmAllList = iproEstmCmtmMngCmplDao.selectEstmResultCmtmAllList(parameterMap); 
		trans.put("estmResultCmtmAllList", estmResultCmtmAllList);
		trans.put("estmResultCmtmAllListCnt", estmResultCmtmAllList.size());
		trans.put("estmResultObjAllListCnt", estmResultObjAllList.size());	
			
			//(평가항목)P_SEARCH_ITEM
			//(평가위원총점)P_SEARCH_ITEM_CMTMTOT
			//(평가총점)P_SEARCH_ESTM_PROCD_SEQ_TOT
			//(최종점수합계적용여부)P_SEARCH_ITEM_TOTSUMAT
			//(최종점수합계적용비율)P_ESTM_PROCD_SEQ_TOTSUM${ data.ESTM_PROCD_SEQ }
		Object SEARCH_ITEM = parameterMap.get("P_SEARCH_ITEM");
	//				Object SEARCH_ITEM_CMTMTOT = parameterMap.get("P_SEARCH_ITEM_CMTMTOT");
	//				Object SEARCH_ESTM_PROCD_SEQ_TOT = parameterMap.get("P_SEARCH_ESTM_PROCD_SEQ_TOT");
	//				Object SEARCH_ITEM_TOTSUMAT = parameterMap.get("P_SEARCH_ITEM_TOTSUMAT");
	
		ArrayList<String> P_SEARCH_ITEM_LIST = new ArrayList<String>();
		ArrayList<String> P_SEARCH_ITEM_CHECK_LIST = new ArrayList<String>();
	//				ArrayList<String> P_SEARCH_ITEM_CMTMTOT_LIST = new ArrayList<String>();
	//				ArrayList<String> P_SEARCH_ITEM_CMTMTOT_CHECK_LIST = new ArrayList<String>();
	//				ArrayList<String> P_SEARCH_ESTM_PROCD_SEQ_LIST = new ArrayList<String>();
	//				ArrayList<String> P_SEARCH_ESTM_PROCD_SEQ_CHECK_LIST = new ArrayList<String>();
			
			
		String SEARCH_CHECK = "";
		// 평가항목
		if(SEARCH_ITEM instanceof String) {
			P_SEARCH_ITEM_LIST.add((String) SEARCH_ITEM);
			SEARCH_CHECK = SEARCH_ITEM.toString();
			P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
		}else {
			if(SEARCH_ITEM instanceof ArrayList) {
				ArrayList<String> SEARCH_ITEMList = (ArrayList) SEARCH_ITEM;
				for (int idx = 0; idx < SEARCH_ITEMList.size(); idx++) {
					SEARCH_CHECK = SEARCH_ITEMList.get(idx).toString();
					P_SEARCH_ITEM_LIST.add(SEARCH_ITEMList.get(idx));
					P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
				}
			}else if(SEARCH_ITEM instanceof String[]) {
				String[] SEARCH_ITEMList = (String[]) SEARCH_ITEM;
				for (int idx = 0; idx < SEARCH_ITEMList.length; idx++) {
					SEARCH_CHECK = SEARCH_ITEMList[idx].toString();
					P_SEARCH_ITEM_LIST.add(SEARCH_ITEMList[idx]);
					P_SEARCH_ITEM_CHECK_LIST.add(SEARCH_CHECK);
				}
			}
		}
		
		//평가항목 체크
		if(P_SEARCH_ITEM_LIST.size() > 0){
			parameterMap.put("P_SEARCH_ITEM_LIST", P_SEARCH_ITEM_LIST);
			parameterMap.put("P_CHECK_LIST", P_SEARCH_ITEM_CHECK_LIST);
			List<FwkDataEntity> estmResultSearchCheckList = iproEstmCmtmMngCmplDao.selectEstmResultSearchCheckList(parameterMap);
			trans.put("SEARCH_ITEM_CHECK_LIST", estmResultSearchCheckList);
			
		}
	
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultProcdSeqList = iproEstmCmtmMngCmplDao.selectEstmResultProcdSeqList(parameterMap);
		trans.put("estmResultProcdSeqList", estmResultProcdSeqList);
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 종료//
		
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultItemNoList = iproEstmCmtmMngCmplDao.selectEstmResultItemNoList(parameterMap);
		trans.put("estmResultItemNoList", estmResultItemNoList);
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 종료//
		
		
		/**
		 * 평가결과리스트
		 */
		List<FwkDataEntity> estmResultItemAllList = iproEstmCmtmMngCmplDao.selectEstmResultItemAllList(parameterMap); 
		trans.put("estmResultItemAllList", estmResultItemAllList);
		// 평가결과목록 리스트 종료//

		return trans;
	}
	
	
	
	
	
	
	@Override
	public FwkTransferObject cmtmMngCmplVidoMtngDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmMngCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);

		//화상회의정보
		List<FwkDataEntity> estmVidoList = iproEstmCmtmMngCmplDao.selectEstmCmplVidoMtngList(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmVidoList", estmVidoList);
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmtmMngCmplDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);

		return trans;
	}
/*
	@Override
	public FwkTransferObject estmCmplExbePayDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		parameterMap.put("P_DEL_AT", "N");
		
		return trans;
	}*/
}
