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
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmRnkSlctProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);

		parameterMap.put("P_DEL_AT", "N");
		
		String OUT_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("OUT_CMTM_SLCT_MTHD_SECD");   // ??????????????????????????????
		System.err.println("@@@ ?????????????????????????????? ==> " + OUT_CMTM_SLCT_MTHD_SECD);
		
		// ?????????????????? ??????
		parameterMap.put("P_INO_CMTM_SECD", "OUT");
		if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
			parameterMap.put("P_SLCT_SECD", "AUTO");   // ??????????????????
		}else{
			parameterMap.put("P_SLCT_SECD", "");   // ??????????????????
		}
		List<FwkDataEntity> outEstmCmtmList = iproEstmCmtmRnkSlctProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("outEstmCmtmList", outEstmCmtmList);
		
		// ?????????????????? ??????
		parameterMap.put("P_INO_CMTM_SECD", "INN");
		List<FwkDataEntity> innEstmCmtmList = iproEstmCmtmRnkSlctProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("innEstmCmtmList", innEstmCmtmList);
		
		// ???????????? ??????
		parameterMap.put("P_ESTM_FSCD", "MST");
		FwkDataEntity estmMstFile = (FwkDataEntity) iproEstmCmtmRnkSlctProgDao.selectEstmFile(parameterMap);   // T_ESTM_FILE
		trans.put("estmMstFile", estmMstFile);
		
		
		return trans;		
	}

	@Override
	public void cmtmPrioRnkAutoSlct(final FwkParameterMap parameterMap) {
		
		iproEstmProgDao.updateEstmPscd(parameterMap);   // T_ESTM_MNG_MST UPDATE
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmRnkSlctProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		
		// ESTM_CMTM_SLCT_MTHD A : ????????????
		// ESTM_CMTM_SLCT_MTHD B	 : ????????????
		// ESTM_CMTM_SLCT_MTHD C	 : ????????????+????????????

		String OUT_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("OUT_CMTM_SLCT_MTHD_SECD");   // ??????????????????????????????
		String INN_CMTM_SLCT_MTHD_SECD = estmMngMstDetail.getString("INN_CMTM_SLCT_MTHD_SECD");   // ??????????????????????????????
		
		int P_PRIO_RNK = 0;   //  ??????
		Random random = new Random();
		
		if(!"A".equals(OUT_CMTM_SLCT_MTHD_SECD)){
			
			// ?????????????????? ??????
			parameterMap.put("P_INO_CMTM_SECD", "OUT");
			if("C".equals(OUT_CMTM_SLCT_MTHD_SECD)){
				parameterMap.put("P_SLCT_SECD", "AUTO");   // ??????????????????
			}else{
				parameterMap.put("P_SLCT_SECD", "");   // ??????????????????
			}
			List<FwkDataEntity> outEstmCmtmList = iproEstmCmtmRnkSlctProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
			
			if(outEstmCmtmList != null){
				
				HashSet<Integer> randomHS= new HashSet<>();   // ????????? ????????? HashSet
				ArrayList<Integer> resultList = new ArrayList<>();   // ????????? ????????? ArrayList
				while(randomHS.size()<outEstmCmtmList.size()){   // HashSet??? ????????? outEstmCmtmList.size()?????? ?????? ?????? 
				
					int randomMaxCnt = randomHS.size();   // ????????? HashSet ??????
					int temp = random.nextInt(outEstmCmtmList.size())+1;   // 1 ~ outEstmCmtmList.size() ?????? ?????? ??????
					randomHS.add(temp); // HashSet??? ?????? => ?????? ?????? ?????? ?????? ?????? ??????
					if(randomHS.size() > randomMaxCnt){   // ????????? ????????? ???????????? ???????????? ???
						resultList.add(temp);   // ArrayList??? ??????
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

					System.err.println("@@@ " + j + "?????? ?????????????????? : " + estmCmtmNo + " | ???????????? : " + P_PRIO_RNK);
					iproEstmCmtmRnkSlctProgDao.updateEstmCmtmPrioRnk(parameterMap);
							
				}
			}
		}
		
		if(!"A".equals(INN_CMTM_SLCT_MTHD_SECD)){
			// ?????????????????? ??????
			parameterMap.put("P_INO_CMTM_SECD", "INN");
			List<FwkDataEntity> innEstmCmtmList = iproEstmCmtmRnkSlctProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_SPHE_MST
			
			if(innEstmCmtmList != null){
				
				HashSet<Integer> randomHS= new HashSet<>();   // ????????? ????????? HashSet
				ArrayList<Integer> resultList = new ArrayList<>();   // ????????? ????????? ArrayList
				while(randomHS.size()<innEstmCmtmList.size()){   // HashSet??? ????????? innEstmCmtmList.size()?????? ?????? ?????? 
				
					int randomMaxCnt = randomHS.size();   // ????????? HashSet ??????
					int temp = random.nextInt(innEstmCmtmList.size())+1;   // 1 ~ innEstmCmtmList.size() ?????? ?????? ??????
					randomHS.add(temp); // HashSet??? ?????? => ?????? ?????? ?????? ?????? ?????? ??????
					if(randomHS.size() > randomMaxCnt){   // ????????? ????????? ???????????? ???????????? ???
						resultList.add(temp);   // ArrayList??? ??????
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

					System.err.println("@@@ " + j + "?????? ?????????????????? : " + estmCmtmNo + " | ???????????? : " + P_PRIO_RNK);
					iproEstmCmtmRnkSlctProgDao.updateEstmCmtmPrioRnk(parameterMap);
							
				}
			}
		}

		parameterMap.put("P_RMK", "????????????????????????");
		iproEstmProgDao.insertEstmProgHist(parameterMap);   // T_ESTM_PROG_HIST INSERT
		
	}

	@Override
	public void estmCmtmSlctNgrDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// ???????????? ????????? ??????
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) iproEstmCmtmRnkSlctProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		

		// ?????????????????? ??????
		parameterMap.put("P_INO_CMTM_SECD", "OUT");
		List<FwkDataEntity> outEstmCmtmList = iproEstmCmtmRnkSlctProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("outEstmCmtmList", outEstmCmtmList);
		
		// ?????????????????? ??????
		parameterMap.put("P_INO_CMTM_SECD", "INN");
		List<FwkDataEntity> innEstmCmtmList = iproEstmCmtmRnkSlctProgDao.selectEstmCmtmList(parameterMap);   // T_ESTM_CMTM
		trans.put("innEstmCmtmList", innEstmCmtmList);
		
	}
}
