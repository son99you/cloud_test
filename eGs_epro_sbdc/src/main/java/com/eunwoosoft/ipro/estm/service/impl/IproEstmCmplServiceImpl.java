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
import com.eunwoosoft.ipro.estm.dao.IproEstmCmplDao;
import com.eunwoosoft.ipro.estm.service.IproEstmCmplService;

@Service(value="iproEstmCmplService")
public class IproEstmCmplServiceImpl extends AbstractFwkService implements IproEstmCmplService {
	
	private static final Logger LOG = LoggerFactory.getLogger(IproEstmCmplServiceImpl.class);
	
	@Resource(name="iproEstmCmplDao") 
	private IproEstmCmplDao iproEstmCmplDao;

	@Resource(name="comAtmaAtchFileDao") 
	private ComAtmaAtchFileDao comAtmaAtchFileDao;

	@Override
	public FwkTransferObject estmCmplList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}

		parameterMap.put("P_ESTM_PSCD_GBN_S", "ESTM_CMPL");
		
		trans.put("estmCmplList", iproEstmCmplDao.selectEstmCmplList(parameterMap));
		trans.put("estmCmplListTotCnt", iproEstmCmplDao.selectEstmCmplListTotCnt(parameterMap));
	
		return trans;
	}
	
	@Override
	public FwkTransferObject estmCmplListExcelDwld(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}

		parameterMap.put("P_ESTM_PSCD_GBN_S", "ESTM_CMPL");
		
		trans.put("estmCmplList", iproEstmCmplDao.selectEstmCmplListExcelDwld(parameterMap));
	
		return trans;
	}

	@Override
	public FwkTransferObject estmCmplDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가절차 조회
		List<FwkDataEntity> estmProcdList = iproEstmCmplDao.selectEstmProcdList(parameterMap);   // T_ESTM_PROCD
		trans.put("estmProcdList", estmProcdList);
		
		// 첨부파일 조회
		parameterMap.put("P_ESTM_FSCD", "MST");
		FwkDataEntity estmFile = (FwkDataEntity) iproEstmCmplDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmFile", estmFile);
		
		// 심사위원 서명파일 첨부파일 그룹번호 조회
		parameterMap.put("P_ESTM_FSCD", "SIGN");
		FwkDataEntity estmSignFile = (FwkDataEntity) iproEstmCmplDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmSignFile", estmSignFile);
		
		// 심사위원 서명파일 조회
		if (estmSignFile != null) {
			String ESTM_SIGN_FILE_GRP_NO = estmSignFile.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", ESTM_SIGN_FILE_GRP_NO);
			trans.put("estmSignFileList", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmplDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		return trans;
	}

	@Override
	public FwkTransferObject estmCmplObjDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가대상정보 조회
		List<FwkDataEntity> estmObjList = iproEstmCmplDao.selectEstmObjList(parameterMap);   // T_ESTM_OBJ
		trans.put("estmObjList", estmObjList);
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmplDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		return trans;
	}

	@Override
	public FwkTransferObject estmCmplCmtmDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		String P_CMPL_YN = parameterMap.getString("P_CMPL_YN");   // 최종여부
		parameterMap.put("P_CMPL_YN", P_CMPL_YN);

		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		int P_ESTM_CMTM_SLCT_NGR_CLICK = parameterMap.getInt("P_ESTM_CMTM_SLCT_NGR_CLICK");
		
		String OUT_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("OUT_CMTM_SLCT_MTHD_SECD");   // 외부평가위원선정방법
		System.err.println("@@@ 외부평가위원선정방법 ==> " + OUT_CMTM_SLCT_MTHD_SECD);
		
		List<FwkDataEntity> outEstmCmtmList = new ArrayList<FwkDataEntity>();
		List<FwkDataEntity> fixEstmCmtmList = new ArrayList<FwkDataEntity>();
		List<FwkDataEntity> innEstmCmtmList = new ArrayList<FwkDataEntity>();
		
		// 외부평가위원 조회
		parameterMap.put("P_INO_CMTM_SECD", "OUT");
		if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
			parameterMap.put("P_SLCT_SECD", "AUTO");   // 선정구분코드
		}else{
			parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
		}
		outEstmCmtmList = iproEstmCmplDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("outEstmCmtmList", outEstmCmtmList);
		
		// 지정평가위원정보 조회
		if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
			parameterMap.put("P_INO_CMTM_SECD", "OUT");
			parameterMap.put("P_SLCT_SECD", "HNDW_REG");   // 선정구분코드 : 수기입력			
			fixEstmCmtmList = iproEstmCmplDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			trans.put("fixEstmCmtmList", fixEstmCmtmList);
		}else{
			trans.put("fixEstmCmtmList", "");
		}
		
		// 내부평가위원 조회
		parameterMap.put("P_INO_CMTM_SECD", "INN");
		parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
		innEstmCmtmList = iproEstmCmplDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("innEstmCmtmList", innEstmCmtmList);
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmplDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		if(P_ESTM_CMTM_SLCT_NGR_CLICK > 0){
			
			outEstmCmtmList = null;
			fixEstmCmtmList = null;
			innEstmCmtmList = null;
			
			System.err.println("/********** 차수에 따른 평가위원정보 조회 **********/");
			parameterMap.put("P_ESTM_CMTM_SLCT_NGR", P_ESTM_CMTM_SLCT_NGR_CLICK);
			
			// 외부평가위원 조회
			parameterMap.put("P_INO_CMTM_SECD", "OUT");
			outEstmCmtmList = iproEstmCmplDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
			trans.put("outEstmCmtmList", outEstmCmtmList);
			
			// 지정평가위원정보 조회
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_INO_CMTM_SECD", "OUT");
				fixEstmCmtmList = iproEstmCmplDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
				trans.put("fixEstmCmtmList", fixEstmCmtmList);
			}else{
				trans.put("fixEstmCmtmList", "");
			}
			
			// 내부평가위원 조회
			parameterMap.put("P_INO_CMTM_SECD", "INN");
			innEstmCmtmList = iproEstmCmplDao.selectEstmCmtmAllList(parameterMap);   // T_ESTM_CMTM
			trans.put("innEstmCmtmList", innEstmCmtmList);
			
		}
		
		if("Y".equals(P_CMPL_YN)){   // 최종
			
			outEstmCmtmList = null;
			fixEstmCmtmList = null;
			innEstmCmtmList = null;
			
			System.err.println("========== [최종] ==========");
			
			parameterMap.put("P_SLCT_YN", "Y");
			parameterMap.put("P_ESTM_CMTM_SLCT_NGR", "");
			
			// 외부평가위원 조회
			parameterMap.put("P_INO_CMTM_SECD", "OUT");
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_SLCT_SECD", "AUTO");   // 선정구분코드
			}else{
				parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
			}
			outEstmCmtmList = iproEstmCmplDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			trans.put("outEstmCmtmList", outEstmCmtmList);
			
			// 지정평가위원정보 조회
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_INO_CMTM_SECD", "OUT");
				parameterMap.put("P_SLCT_SECD", "HNDW_REG");   // 선정구분코드 : 수기입력			
				fixEstmCmtmList = iproEstmCmplDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
				trans.put("fixEstmCmtmList", fixEstmCmtmList);
			}else{
				trans.put("fixEstmCmtmList", "");
			}
			
			// 내부평가위원 조회
			parameterMap.put("P_INO_CMTM_SECD", "INN");
			parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
			innEstmCmtmList = iproEstmCmplDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			trans.put("innEstmCmtmList", innEstmCmtmList);
		}
		
		return trans;
	}

	@Override
	public FwkTransferObject estmCmplProcdADetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// 평가절차 조회
		FwkDataEntity estmProcdDetail = iproEstmCmplDao.selectEstmProcdDetail(parameterMap);   // T_ESTM_PROCD
		
		// 평가대상 목록 조회
		List<FwkDataEntity> estmObjList = iproEstmCmplDao.selectEstmObjValueList(parameterMap);
		
		// 평가위원 목록 조회
		List<FwkDataEntity> estmCmtmLastList = iproEstmCmplDao.selectEstmCmtmLastList(parameterMap);   // T_ESTM_CMTM
		
		// 평가위원+평가대상 목록 조회
		List<FwkDataEntity> estmValueList = iproEstmCmplDao.selectEstmValueList(parameterMap);   // T_ESTM_CMTM
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmplDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		
		trans.put("estmProcdDetail", estmProcdDetail);
		trans.put("estmValueList", estmValueList);
		trans.put("estmObjList", estmObjList);
		trans.put("estmObjListTotCnt", iproEstmCmplDao.selectEstmObjValueListTotCnt(parameterMap));
		trans.put("estmCmtmLastList", estmCmtmLastList);
		trans.put("estmCmtmLastListCnt", estmCmtmLastList.size());
		trans.put("estmMngMstDetail", estmMngMstDetail);

		return trans;
	}

	@Override
	public FwkTransferObject estmCmplProcdBDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// 평가절차 조회
		FwkDataEntity estmProcdDetail = iproEstmCmplDao.selectEstmProcdDetail(parameterMap);   // T_ESTM_PROCD
		
		// 평가대상 목록 조회
		List<FwkDataEntity> estmObjList = iproEstmCmplDao.selectEstmObjValueList(parameterMap);
		
		// 평가담당자 조회
		List<FwkDataEntity> estmChrgrList = iproEstmCmplDao.selectEstmChrgrList(parameterMap);   // T_ESTM_CMTM
		
		// 평가담당자+평가대상 목록 조회
		List<FwkDataEntity> estmChrgrValueList = iproEstmCmplDao.selectEstmChrgrValueList(parameterMap);   // T_ESTM_CMTM
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmplDao.selectEstmTabList(parameterMap);
		
		
		trans.put("estmProcdDetail", estmProcdDetail);
		trans.put("estmObjList", estmObjList);
		trans.put("estmObjListTotCnt", iproEstmCmplDao.selectEstmObjValueListTotCnt(parameterMap));
		trans.put("estmChrgrList", estmChrgrList);
		trans.put("estmChrgrValueList", estmChrgrValueList);
		trans.put("estmMngMstDetail", estmMngMstDetail);
		trans.put("estmTabList", estmTabList);
		return trans;
	}

	@Override
	public FwkTransferObject estmCmplResultDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);

		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmplDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
			
		// 조회리스트 추가 //
		// 평가결과추가조회 리스트
		List<FwkDataEntity> searchColList = iproEstmCmplDao.selectEstmSearchColList(parameterMap); 
		trans.put("searchColList", searchColList);
			
		// 평가결과 ITEM MAX값 조회
		int maxSearchColLength = iproEstmCmplDao.selectEstmSearchColLength(parameterMap);   // T_ESTM_MNG_MST
		trans.put("maxSearchColLength", maxSearchColLength);
		// 조회리스트 종료//
		
		// 평가서식절차구분코드 가 적격인 경우 시작//
		List<FwkDataEntity> estmResultProcdObjSlctList = iproEstmCmplDao.selectEstmResultProcdObjSlctList(parameterMap);
		trans.put("estmResultProcdObjSlctList", estmResultProcdObjSlctList);
		// 평가서식절차구분코드 가 적격인 경우 종료//
		
		// 평가결과목록 리스트 시작//
		/**
		 *  평가대상 리스트
		 *  (모든 평가대상을 불러온다.)
		 */
		
		parameterMap.put("pageYn", "Y");
		
		List<FwkDataEntity> estmResultObjAllList = iproEstmCmplDao.selectEstmResultObjAllList(parameterMap); 
		trans.put("estmResultObjAllList", estmResultObjAllList);
		trans.put("estmResultObjAllListTotCnt", iproEstmCmplDao.selectEstmResultObjAllListTotCnt(parameterMap));
			
		/**
		 * 평가위원 리스트
		 */
		List<FwkDataEntity> estmResultCmtmAllList = iproEstmCmplDao.selectEstmResultCmtmAllList(parameterMap); 
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
			List<FwkDataEntity> estmResultSearchCheckList = iproEstmCmplDao.selectEstmResultSearchCheckList(parameterMap);
			trans.put("SEARCH_ITEM_CHECK_LIST", estmResultSearchCheckList);
			
		}
	
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultProcdSeqList = iproEstmCmplDao.selectEstmResultProcdSeqList(parameterMap);
		trans.put("estmResultProcdSeqList", estmResultProcdSeqList);
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 종료//
		
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultItemNoList = iproEstmCmplDao.selectEstmResultItemNoList(parameterMap);
		trans.put("estmResultItemNoList", estmResultItemNoList);
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 종료//
		
		
		/**
		 * 평가결과리스트
		 */
		List<FwkDataEntity> estmResultItemAllList = iproEstmCmplDao.selectEstmResultItemAllList(parameterMap); 
		trans.put("estmResultItemAllList", estmResultItemAllList);
		// 평가결과목록 리스트 종료//

		return trans;
	}
	
	
	@Override
	public FwkTransferObject estmProgResultDetailExcelDwld(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);

		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmplDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
			
		// 조회리스트 추가 //
		// 평가결과추가조회 리스트
		List<FwkDataEntity> searchColList = iproEstmCmplDao.selectEstmSearchColList(parameterMap); 
		trans.put("searchColList", searchColList);
			
		// 평가결과 ITEM MAX값 조회
		int maxSearchColLength = iproEstmCmplDao.selectEstmSearchColLength(parameterMap);   // T_ESTM_MNG_MST
		trans.put("maxSearchColLength", maxSearchColLength);
		// 조회리스트 종료//
		
		// 평가서식절차구분코드 가 적격인 경우 시작//
		List<FwkDataEntity> estmResultProcdObjSlctList = iproEstmCmplDao.selectEstmResultProcdObjSlctList(parameterMap);
		trans.put("estmResultProcdObjSlctList", estmResultProcdObjSlctList);
		// 평가서식절차구분코드 가 적격인 경우 종료//
		
		// 평가결과목록 리스트 시작//
		/**
		 *  평가대상 리스트
		 *  (모든 평가대상을 불러온다.)
		 */
		
		parameterMap.put("pageYn", "N");
		
		List<FwkDataEntity> estmResultObjAllList = iproEstmCmplDao.selectEstmResultObjAllList(parameterMap); 
		trans.put("estmResultObjAllList", estmResultObjAllList);
			
		/**
		 * 평가위원 리스트
		 */
		List<FwkDataEntity> estmResultCmtmAllList = iproEstmCmplDao.selectEstmResultCmtmAllList(parameterMap); 
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
			List<FwkDataEntity> estmResultSearchCheckList = iproEstmCmplDao.selectEstmResultSearchCheckList(parameterMap);
			trans.put("SEARCH_ITEM_CHECK_LIST", estmResultSearchCheckList);
			
		}
	
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultProcdSeqList = iproEstmCmplDao.selectEstmResultProcdSeqList(parameterMap);
		trans.put("estmResultProcdSeqList", estmResultProcdSeqList);
		//평가결과목록 조회 체크된 평가만 찾기 위한 로직 종료//
		
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 시작//
		List<FwkDataEntity> estmResultItemNoList = iproEstmCmplDao.selectEstmResultItemNoList(parameterMap);
		trans.put("estmResultItemNoList", estmResultItemNoList);
		//평가결과목록 조회 체크된 평가항목만 찾기 위한 로직 종료//
		
		
		/**
		 * 평가결과리스트
		 */
		List<FwkDataEntity> estmResultItemAllList = iproEstmCmplDao.selectEstmResultItemAllList(parameterMap); 
		trans.put("estmResultItemAllList", estmResultItemAllList);
		// 평가결과목록 리스트 종료//

		return trans;
	}
	
	
	

	@Override
	public FwkTransferObject estmCmplVidoMtngDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);

		
		
		//화상회의정보
		List<FwkDataEntity> estmVidoList = iproEstmCmplDao.selectEstmCmplVidoMtngList(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmVidoList", estmVidoList);
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmplDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);

		return trans;
	}

	@Override
	public FwkTransferObject estmCmplExbePayDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		// 평가관리 마스터 조회
		
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmplDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		
		// 평가절차리스트 조회(Tab)
		List<FwkDataEntity> estmTabList = iproEstmCmplDao.selectEstmTabList(parameterMap); 
		trans.put("estmTabList", estmTabList);
		
		
		List<FwkDataEntity> cmtmExbePayList = iproEstmCmplDao.selectCmtmExbePayList(parameterMap);
		trans.put("cmtmExbePayList", cmtmExbePayList);
		
		
		
		return trans;
	}
	
	@Override
	public FwkTransferObject estmCmplExbePaySave(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		// 평가위원 정보
		FwkDataEntity cmtmData = (FwkDataEntity) iproEstmCmplDao.selectEstmCmtmDetail(parameterMap);
		
		parameterMap.put("P_ESTM_CMTM_NM", cmtmData.getString("ESTM_CMTM_NM"));
		parameterMap.put("P_ZIP", cmtmData.getString("ZIP"));
		parameterMap.put("P_ADDR_1", cmtmData.getString("ADDR_1"));
		parameterMap.put("P_ADDR_2", cmtmData.getString("ADDR_2"));
		
		
		parameterMap.put("P_DEL_AT", "N");
		// 평가관리 마스터 조회

		if("".equals(parameterMap.getString("P_ASSESS_SEQ")) || parameterMap.getString("P_ASSESS_SEQ") == null) {
			if("".equals(parameterMap.getString("P_ASSESS_SEQ")) || parameterMap.getString("P_ASSESS_SEQ") == null) {
				// 지급요청 MAX값 가져오기
				FwkDataEntity payData = (FwkDataEntity) iproEstmCmplDao.selectEstmPayData(parameterMap);
				parameterMap.put("P_ASSESS_DT", payData.getString("ASSESS_DT"));
				parameterMap.put("P_ASSESS_SEQ", payData.getString("ASSESS_SEQ"));
			}
		}

		
		// 지급정보 UPDATE 하기
		iproEstmCmplDao.updateEstmCmtmExbePayUpdt(parameterMap);
		// 지급정보 UPDATE 하기
		
		
		
		//loginResult={USR_ID=kyy, USR_NM=김예은, EMPL_NO=kyy, DEPT_NO=381300, DEPT_NM=정보지원팀, ARA_DEPT_CD=38, OFPS_CD=3A, OFPS_NM=5급, EMAL_ADDR=kyy, AUTH_ID=1, APPR_AUTH_YN=Y, P_SSO_STATUS=OK, CONN_IP=0:0:0:0:0:0:0:1, roleList=[ABID_MNGR, GBID_MNGR], resourceName=IEP15002}
		parameterMap.put("P_DEPT_NO", session.get("DEPT_NO"));
		parameterMap.put("P_EMPL_NO", session.get("EMPL_NO"));

		parameterMap.put("P_STORE", session.get("STORE"));
		parameterMap.put("P_ORGAN", session.get("ORGAN"));
		
		iproEstmCmplDao.insertEstmCmplExbePaySave(parameterMap);
		
		return trans;
	}
	
	/*
	 * 평가종료취소버튼
	 */
	@Override
	public void estmCmplEndCnclUpdt(final FwkParameterMap parameterMap) throws Exception {
		iproEstmCmplDao.updateEstmPscd(parameterMap);   // T_ESTM_MNG_MST UPDATE
		iproEstmCmplDao.deleteEndCnclUpdt(parameterMap); //T_ESTM_SVY_FRM DELETE
		iproEstmCmplDao.deleteEndCnclScr(parameterMap);  //T_ESTM_SVY_SCR DELETE
		
		parameterMap.put("P_RMK", "평가종료취소");
		
		iproEstmCmplDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}
	
	
	
}
