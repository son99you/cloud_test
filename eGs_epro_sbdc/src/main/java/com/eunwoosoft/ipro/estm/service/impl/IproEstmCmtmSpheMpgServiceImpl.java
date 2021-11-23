package com.eunwoosoft.ipro.estm.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.estm.dao.IproEstmCmtmSpheMpgDao;
import com.eunwoosoft.ipro.estm.service.IproEstmCmtmSpheMpgService;

@Service(value="iproEstmCmtmSpheMpgService")
public class IproEstmCmtmSpheMpgServiceImpl extends AbstractFwkService implements IproEstmCmtmSpheMpgService {
	private static final Logger LOG = LoggerFactory.getLogger(IproEstmCmtmSpheMpgServiceImpl.class);
	
	@Resource(name="iproEstmCmtmSpheMpgDao") 
	private IproEstmCmtmSpheMpgDao iproEstmCmtmSpheMpgDao;
	
	@Override
	public FwkTransferObject estmCmtmSpheMpgList(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(!"".equals(parameterMap.getString("P_REG_BEGIN_DT_S"))){
			parameterMap.put("P_REG_BEGIN_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_BEGIN_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		if(!"".equals(parameterMap.getString("P_REG_END_DT_S"))){
			parameterMap.put("P_REG_END_DT_S", FwkFormatUtil.formatDate(parameterMap.get("P_REG_END_DT_S").toString(), "yyyy-MM-dd", "yyyyMMdd")); 
		}
		
		parameterMap.put("P_ESTM_PSCD_GBN_S", "ESTM_PROG");
		
		trans.put("estmCmtmSpheMpgList", iproEstmCmtmSpheMpgDao.selectEstmCmtmSpheMpgList(parameterMap));
		trans.put("estmCmtmSpheMpgListTotCnt", iproEstmCmtmSpheMpgDao.selectEstmCmtmSpheMpgListTotCnt(parameterMap));
		//trans.put("realEstmYn",iproEstmCmtmSpheMpgDao.selectRealEstmYn(parameterMap)	);
	
		return trans;
	}
	
	/**
	 * 
	 */
	@Override
	public FwkTransferObject estmCmtmSpheMpgItemList(final FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		//P_ESTM_SPHE_SECD
		trans.put("estmCmtmSpheMpgDetail", iproEstmCmtmSpheMpgDao.selectEstmCmtmSpheMpgDetail(parameterMap));   // T_ESTM_SPHE_MST
		
		
		trans.put("estmCmtmSpheMpgItemList", iproEstmCmtmSpheMpgDao.selectEstmCmtmSpheMpgItemList(parameterMap));
		//trans.put("estmCmtmSpheMpgListTotCnt", iproEstmCmtmSpheMpgDao.selectEstmCmtmSpheMpgListTotCnt(parameterMap));
	
		return trans;
	}
	
	
	/**
	 * 평가위원분야매핑 저장
	 */
	@Override
	public FwkTransferObject estmCmtmSpheMpgRegist(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(parameterMap.get("P_ESTM_SPHE_SECD") != null) {
			//평가위원매핑테이블 삭제
			iproEstmCmtmSpheMpgDao.deleteCmtmSpheMpgDelt(parameterMap);
			
			// 평가위원분야매핑 등록
			if(parameterMap.get("P_ESTM_CMTM_NO") != null) {
				
				//평가위원번호
				Object P_ESTM_CMTM_NO = parameterMap.get("P_ESTM_CMTM_NO");
				//평가선택여부
				Object P_SELECT_AT = parameterMap.get("P_SELECT_AT");
				
				if(P_ESTM_CMTM_NO != null){
					
					System.err.println("@@@ P_ESTM_CMTM_NO != null @@@");
					
					if(P_ESTM_CMTM_NO instanceof String){
						
						System.err.println("@@@ String @@@");
						
						if("Y".equals(parameterMap.get("P_SELECT_AT"))) {
							// 평가위원분야매핑 등록
							iproEstmCmtmSpheMpgDao.insertCmtmSpheMpgItemRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
						}
					}else if(P_ESTM_CMTM_NO instanceof ArrayList){
					
						System.err.println("@@@ ArrayList @@@");
						
						//평가위원번호
						ArrayList<String> P_ESTM_CMTM_NOList = (ArrayList<String>) P_ESTM_CMTM_NO;
						//평가선택여부
						ArrayList<String> P_SELECT_ATList = (ArrayList<String>) P_SELECT_AT;
						
							for(int i =1; i < P_ESTM_CMTM_NOList.size(); i++){
								
								parameterMap.put("P_ESTM_CMTM_NO", P_ESTM_CMTM_NOList.get(i));
								parameterMap.put("P_SELECT_AT", P_SELECT_ATList.get(i));
								
								if("Y".equals(parameterMap.get("P_SELECT_AT"))) {
									// 평가위원분야매핑 등록
									iproEstmCmtmSpheMpgDao.insertCmtmSpheMpgItemRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
									
								}
							}
					
					}else if(P_ESTM_CMTM_NO instanceof String[]){
						System.err.println("@@@ String[] @@@");
						
						//평가위원번호
						String[] P_ESTM_CMTM_NOList = (String[]) P_ESTM_CMTM_NO;
						//평가선택여부
						String[] P_SELECT_ATList = (String[]) P_SELECT_AT;
						
						for (int idx = 0; idx < P_ESTM_CMTM_NOList.length; idx++) {
							parameterMap.put("P_ESTM_CMTM_NO", P_ESTM_CMTM_NOList[idx]);
							parameterMap.put("P_SELECT_AT", P_SELECT_ATList[idx]);

							if("Y".equals(parameterMap.get("P_SELECT_AT"))) {
								// 평가위원분야매핑 등록
								iproEstmCmtmSpheMpgDao.insertCmtmSpheMpgItemRegist(parameterMap);
							}
						}
					}
					
				}//P_ESTM_CMTM_NO != null
				
			}//parameterMap.get("P_ESTM_FRM_NO") != null
		
		}//parameterMap.get("P_ESTM_SPHE_SECD")
		//trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));
		trans.put("P_ESTM_SPHE_SECD", parameterMap.get("P_ESTM_SPHE_SECD"));
		
		return trans;
	}
	
	
	@Override
	public FwkTransferObject estmCmtmSpheMpgDetail(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		//P_ESTM_SPHE_SECD
		trans.put("estmCmtmSpheMpgDetail", iproEstmCmtmSpheMpgDao.selectEstmCmtmSpheMpgDetail(parameterMap));
		
		
		parameterMap.put("P_SELECT_AT", "Y");
		
		trans.put("estmCmtmSpheMpgItemList", iproEstmCmtmSpheMpgDao.selectEstmCmtmSpheMpgItemList(parameterMap));
		//trans.put("estmCmtmSpheMpgListTotCnt", iproEstmCmtmSpheMpgDao.selectEstmCmtmSpheMpgListTotCnt(parameterMap));
	
		return trans;
	}
	
	
	/**
	 * 평가위원분야매핑 저장(수정)
	 */
	@Override
	public FwkTransferObject estmCmtmSpheMpgUpdt(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();

		parameterMap.put("P_DEL_AT", "N");
		
		if(parameterMap.get("P_ESTM_SPHE_SECD") != null) {
			//평가위원매핑테이블 삭제
			iproEstmCmtmSpheMpgDao.deleteCmtmSpheMpgDelt(parameterMap);
			
			// 평가위원분야매핑 등록
			if(parameterMap.get("P_ESTM_CMTM_NO") != null) {
				
				//평가위원번호
				Object P_ESTM_CMTM_NO = parameterMap.get("P_ESTM_CMTM_NO");
				//평가선택여부
				Object P_SELECT_AT = parameterMap.get("P_SELECT_AT");
				
				if(P_ESTM_CMTM_NO != null){
					
					System.err.println("@@@ P_ESTM_CMTM_NO != null @@@");
					
					if(P_ESTM_CMTM_NO instanceof String){
						
						System.err.println("@@@ String @@@");
						
						if("Y".equals(parameterMap.get("P_SELECT_AT"))) {
							// 평가위원분야매핑 등록
							iproEstmCmtmSpheMpgDao.insertCmtmSpheMpgItemRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
						}
					}else if(P_ESTM_CMTM_NO instanceof ArrayList){
					
						System.err.println("@@@ ArrayList @@@");
						
						//평가위원번호
						ArrayList<String> P_ESTM_CMTM_NOList = (ArrayList<String>) P_ESTM_CMTM_NO;
						//평가선택여부
						ArrayList<String> P_SELECT_ATList = (ArrayList<String>) P_SELECT_AT;
						
							for(int i =1; i < P_ESTM_CMTM_NOList.size(); i++){
								
								parameterMap.put("P_ESTM_CMTM_NO", P_ESTM_CMTM_NOList.get(i));
								parameterMap.put("P_SELECT_AT", P_SELECT_ATList.get(i));
								
								if("Y".equals(parameterMap.get("P_SELECT_AT"))) {
									// 평가위원분야매핑 등록
									iproEstmCmtmSpheMpgDao.insertCmtmSpheMpgItemRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
									
								}
							}
					
					}else if(P_ESTM_CMTM_NO instanceof String[]){
						System.err.println("@@@ String[] @@@");
						
						//평가위원번호
						String[] P_ESTM_CMTM_NOList = (String[]) P_ESTM_CMTM_NO;
						//평가선택여부
						String[] P_SELECT_ATList = (String[]) P_SELECT_AT;
						
						for (int idx = 0; idx < P_ESTM_CMTM_NOList.length; idx++) {
							parameterMap.put("P_ESTM_CMTM_NO", P_ESTM_CMTM_NOList[idx]);
							parameterMap.put("P_SELECT_AT", P_SELECT_ATList[idx]);

							if("Y".equals(parameterMap.get("P_SELECT_AT"))) {
								// 평가위원분야매핑 등록
								iproEstmCmtmSpheMpgDao.insertCmtmSpheMpgItemRegist(parameterMap);
							}
						}
					}
					
				}//P_ESTM_CMTM_NO != null
				
			}//parameterMap.get("P_ESTM_FRM_NO") != null
		
		}//parameterMap.get("P_ESTM_SPHE_SECD")
		//trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));
		trans.put("P_ESTM_SPHE_SECD", parameterMap.get("P_ESTM_SPHE_SECD"));
		
		return trans;
	}

	@Override
	public FwkTransferObject cmtmSpheMpgListExcelDwld(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_DEL_AT", "N");
		
		trans.put("estmCmtmSpheMpgList", iproEstmCmtmSpheMpgDao.selectCmtmSpheMpgListExcelDwld(parameterMap));
		System.out.println("trans>>>" + trans);
		return trans;
	}
	

}