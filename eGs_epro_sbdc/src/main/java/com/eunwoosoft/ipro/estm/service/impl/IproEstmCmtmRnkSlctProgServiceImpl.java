package com.eunwoosoft.ipro.estm.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.estm.dao.IproEstmCmtmRnkSlctProgDao;
import com.eunwoosoft.ipro.estm.dao.IproEstmProgDao;
import com.eunwoosoft.ipro.estm.service.IproEstmCmtmRnkSlctProgService;

@Service(value="iproEstmCmtmRnkSlctProgService")
public class IproEstmCmtmRnkSlctProgServiceImpl extends AbstractFwkService implements IproEstmCmtmRnkSlctProgService {
	private static final Logger LOG = LoggerFactory.getLogger(IproEstmCmtmRnkSlctProgServiceImpl.class);
	
	@Resource(name="iproEstmCmtmRnkSlctProgDao") 
	private IproEstmCmtmRnkSlctProgDao iproEstmCmtmRnkSlctProgDao;
	
	@Resource(name="iproEstmProgDao") 
	private IproEstmProgDao iproEstmProgDao;

	@Override
	public FwkTransferObject cmtmRnkSlctProgList(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		parameterMap.put("P_ESTM_PSCD_GBN_S", "RNK_SLCT_PROG");
		
		trans.put("cmtmRnkSlctProgList", iproEstmCmtmRnkSlctProgDao.selectCmtmRnkSlctProgList(parameterMap));
		trans.put("cmtmRnkSlctProgListTotCnt", iproEstmCmtmRnkSlctProgDao.selectCmtmRnkSlctProgListTotCnt(parameterMap));
	
		return trans;
		
	}
	
	@Override
	public FwkTransferObject cmtmRnkSlctProgListExcelDwld(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		parameterMap.put("P_ESTM_PSCD_GBN_S", "RNK_SLCT_PROG");
		
		trans.put("cmtmRnkSlctProgList", iproEstmCmtmRnkSlctProgDao.selectCmtmRnkSlctProgListExcelDwld(parameterMap));
	
		return trans;
		
	}
	
	

	@Override
	public FwkTransferObject cmtmRnkSlctProgDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmRnkSlctProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);

		parameterMap.put("P_DEL_AT", "N");
		
		String OUT_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("OUT_CMTM_SLCT_MTHD_SECD");   // 외부평가위원선정방법
		System.err.println("@@@ 외부평가위원선정방법 ==> " + OUT_CMTM_SLCT_MTHD_SECD);
		
		// 외부평가위원 조회
		parameterMap.put("P_INO_CMTM_SECD", "OUT");
		if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
			parameterMap.put("P_SLCT_SECD", "AUTO");   // 선정구분코드
		}else{
			parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
		}
		List<FwkDataEntity> outEstmCmtmList = iproEstmCmtmRnkSlctProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("outEstmCmtmList", outEstmCmtmList);
		
		// 내부평가위원 조회
		parameterMap.put("P_INO_CMTM_SECD", "INN");
		List<FwkDataEntity> innEstmCmtmList = iproEstmCmtmRnkSlctProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("innEstmCmtmList", innEstmCmtmList);
		
		// 첨부파일 조회
		parameterMap.put("P_ESTM_FSCD", "MST");
		FwkDataEntity estmMstFile = (FwkDataEntity) iproEstmCmtmRnkSlctProgDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmMstFile", estmMstFile);
		
		
		return trans;		
	}

	@Override
	public void cmtmPrioRnkAutoSlct(final FwkParameterMap parameterMap) {
		
		iproEstmProgDao.updateEstmPscd(parameterMap);   // T_ESTM_MNG_MST UPDATE
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmRnkSlctProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// ESTM_CMTM_SLCT_MTHD A : 자체선정
		// ESTM_CMTM_SLCT_MTHD B	 : 우선순위
		// ESTM_CMTM_SLCT_MTHD C	 : 자체선정+우선순위

		String OUT_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("OUT_CMTM_SLCT_MTHD_SECD");   // 외부평가위원선정방법
		String INN_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("INN_CMTM_SLCT_MTHD_SECD");   // 내부평가위원선정방법
		
		int P_PRIO_RNK = 0;   //  순위
		Random random = new Random();
		
		if(!"A".equals(OUT_CMTM_SLCT_MTHD_SECD)){
			
			// 외부평가위원 조회
			parameterMap.put("P_INO_CMTM_SECD", "OUT");
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_SLCT_SECD", "AUTO");   // 선정구분코드
			}else{
				parameterMap.put("P_SLCT_SECD", "");   // 선정구분코드
			}
			List<FwkDataEntity> outEstmCmtmList = iproEstmCmtmRnkSlctProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			
			if(outEstmCmtmList != null){
				
				HashSet<Integer> randomHS= new HashSet<>();   // 난수를 저장할 HashSet
				ArrayList<Integer> resultList = new ArrayList<>();   // 결과를 저장할 ArrayList
				while(randomHS.size()<outEstmCmtmList.size()){   // HashSet의 크기가 outEstmCmtmList.size()보다 작을 동안 
				
					int randomMaxCnt = randomHS.size();   // 기존의 HashSet 크기
					int temp = random.nextInt(outEstmCmtmList.size())+1;   // 1 ~ outEstmCmtmList.size() 까지 난수 생성
					randomHS.add(temp); // HashSet에 추가 => 중복 되면 값이 추가 되지 않음
					if(randomHS.size() > randomMaxCnt){   // 크기가 변하면 중복되지 않았다는 뜻
						resultList.add(temp);   // ArrayList에 추가
					}
				}

				System.out.println("@@@ resultList ==> " + resultList);		
				
				for (int j = 0; j < outEstmCmtmList.size(); j++) {
					
					P_PRIO_RNK = resultList.get(j);
				
					FwkDataEntity estmCmtm = outEstmCmtmList.get(j);
					String estmCmtmNo = estmCmtm.getString("ESTM_CMTM_NO");
					int estmCmtmSlctNgr = estmCmtm.getInt("ESTM_CMTM_SLCT_NGR");
					
					parameterMap.put("P_ESTM_CMTM_NO", estmCmtmNo);
					parameterMap.put("P_ESTM_CMTM_SLCT_NGR", estmCmtmSlctNgr);
					parameterMap.put("P_PRIO_RNK", P_PRIO_RNK);
					
					//[8, 3, 2, 4, 5, 6, 7, 9, 1]
					//[5, 3, 7, 6, 4, 2, 8, 1, 9]
					//[4, 1, 5, 3, 7, 2, 8, 6, 9]
					//[9, 1, 5, 3, 4, 8, 2, 6, 7]

					System.err.println("@@@ " + j + "번째 평가위원번호 : " + estmCmtmNo + " | 우선순위 : " + P_PRIO_RNK);
					iproEstmCmtmRnkSlctProgDao.updateEstmCmtmPrioRnk(parameterMap);
							
				}
			}
		}
		
		if(!"A".equals(INN_CMTM_SLCT_MTHD_SECD)){
			// 내부평가위원 조회
			parameterMap.put("P_INO_CMTM_SECD", "INN");
			List<FwkDataEntity> innEstmCmtmList = iproEstmCmtmRnkSlctProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_SPHE_MST
			
			if(innEstmCmtmList != null){
				
				HashSet<Integer> randomHS= new HashSet<>();   // 난수를 저장할 HashSet
				ArrayList<Integer> resultList = new ArrayList<>();   // 결과를 저장할 ArrayList
				while(randomHS.size()<innEstmCmtmList.size()){   // HashSet의 크기가 innEstmCmtmList.size()보다 작을 동안 
				
					int randomMaxCnt = randomHS.size();   // 기존의 HashSet 크기
					int temp = random.nextInt(innEstmCmtmList.size())+1;   // 1 ~ innEstmCmtmList.size() 까지 난수 생성
					randomHS.add(temp); // HashSet에 추가 => 중복 되면 값이 추가 되지 않음
					if(randomHS.size() > randomMaxCnt){   // 크기가 변하면 중복되지 않았다는 뜻
						resultList.add(temp);   // ArrayList에 추가
					}
				}

				System.out.println("@@@ resultList ==> " + resultList);		
				
				for (int j = 0; j < innEstmCmtmList.size(); j++) {
					
					P_PRIO_RNK = resultList.get(j);
				
					FwkDataEntity estmCmtm = innEstmCmtmList.get(j);
					String estmCmtmNo = estmCmtm.getString("ESTM_CMTM_NO");
					int estmCmtmSlctNgr = estmCmtm.getInt("ESTM_CMTM_SLCT_NGR");
					
					parameterMap.put("P_ESTM_CMTM_NO", estmCmtmNo);
					parameterMap.put("P_ESTM_CMTM_SLCT_NGR", estmCmtmSlctNgr);
					parameterMap.put("P_PRIO_RNK", P_PRIO_RNK);

					System.err.println("@@@ " + j + "번째 평가위원번호 : " + estmCmtmNo + " | 우선순위 : " + P_PRIO_RNK);
					iproEstmCmtmRnkSlctProgDao.updateEstmCmtmPrioRnk(parameterMap);
							
				}
			}
		}

		parameterMap.put("P_RMK", "우선순위자동선별");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}

	@Override
	public void estmCmtmSlctNgrDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmRnkSlctProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		

		// 외부평가위원 조회
		parameterMap.put("P_INO_CMTM_SECD", "OUT");
		List<FwkDataEntity> outEstmCmtmList = iproEstmCmtmRnkSlctProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("outEstmCmtmList", outEstmCmtmList);
		
		// 내부평가위원 조회
		parameterMap.put("P_INO_CMTM_SECD", "INN");
		List<FwkDataEntity> innEstmCmtmList = iproEstmCmtmRnkSlctProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("innEstmCmtmList", innEstmCmtmList);
		
	}
}
