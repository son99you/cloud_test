package com.eunwoosoft.ipro.sytm.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommPopupService;
import com.eunwoosoft.ipro.sytm.dao.IproSytmAuthMngeDao;
import com.eunwoosoft.ipro.sytm.service.IproSytmAuthMngeService;

/**
 * com.eunwoosoft.ipro.sytm.service.impl
 * IproSytmAuthMngeServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2018. 2. 28.
 *
 */

@Transactional
@Service("iproSytmAuthMngeService")
public class IproSytmAuthMngeServiceImpl extends AbstractFwkService implements IproSytmAuthMngeService {
	
	@Resource(name="iproSytmAuthMngeDao")
	private IproSytmAuthMngeDao iproSytmAuthMngeDao;
	
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.sytm.service.IproSytmAuthMngeService#authMgrListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject authMgrListWithPgng(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproSytmAuthMngeService.AUTH_MGR_LIST, iproSytmAuthMngeDao.selectAuthMgrListWithPgng(parameterMap));
		trans.put(IproSytmAuthMngeService.AUTH_MGR_LIST_TOTCNT, iproSytmAuthMngeDao.selectAuthMgrListTotcnt(parameterMap));
		return trans;
		
	}

	@Override
	public FwkTransferObject authMgrListExcelDwld(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("dataList", iproSytmAuthMngeDao.selectAuthMgrExcelList(parameterMap));
		return trans;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eunwoosoft.ipro.comm.service.IproCommPopupService#menuAuthMgrListWithPgng(com.eunwoosoft.frwk.prl.request.FwkParameterMap)
	 */
	@Override
	public FwkTransferObject authMenuMgrListWithPgng(final FwkParameterMap parameterMap) {
		// ????????????????????? ??????
		FwkTransferObject trans = new FwkTransferObject();
		
		
		trans.put("menuAuthMgrDetail", iproSytmAuthMngeDao.selectAuthMenuMgrDetail(parameterMap));
		trans.put("menuAuthMgrList", iproSytmAuthMngeDao.selectAuthMenuMgrListWithPgng(parameterMap));
		trans.put("menuAuthMgrListTotcnt", iproSytmAuthMngeDao.selectAuthMenuMgrListTotcnt(parameterMap));
		return trans;
	}
	
	/**
	 * ???????????????????????? ??????(??????)
	 */
	@Override
	public FwkTransferObject authMgrUpdt(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();

		parameterMap.put("P_DEL_AT", "N");
		
		if(parameterMap.get("P_MENU_ID") != null) {
			//???????????????????????? ??????
			iproSytmAuthMngeDao.deleteAuthMgrDelt(parameterMap);
			
			// ???????????????????????? ??????
			if(parameterMap.get("P_MENU_ID") != null) {
				
				//??????ID
				Object P_MENU_ID = parameterMap.get("P_MENU_ID");
				//??????????????????
				Object P_SELECT_AT = parameterMap.get("P_SELECT_AT");
				
				if(P_MENU_ID != null){
					
					System.err.println("@@@ P_ESTM_CMTM_NO != null @@@");
					
					if(P_MENU_ID instanceof String){
						
						System.err.println("@@@ String @@@");
						
						if("Y".equals(parameterMap.get("P_SELECT_AT"))) {
							// ???????????????????????? ??????
							iproSytmAuthMngeDao.insertAuthMgrItemRegist(parameterMap); 
						}
					}else if(P_MENU_ID instanceof ArrayList){
					
						System.err.println("@@@ ArrayList @@@");
						
						//??????ID
						ArrayList<String> P_MENU_IDList = (ArrayList<String>) P_MENU_ID;
						//??????????????????
						ArrayList<String> P_SELECT_ATList = (ArrayList<String>) P_SELECT_AT;
						
							for(int i =1; i < P_MENU_IDList.size(); i++){
								
								parameterMap.put("P_MENU_ID", P_MENU_IDList.get(i));
								parameterMap.put("P_SELECT_AT", P_SELECT_ATList.get(i));
								
								if("Y".equals(parameterMap.get("P_SELECT_AT"))) {
									// ???????????????????????? ??????
									iproSytmAuthMngeDao.insertAuthMgrItemRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
									
								}
							}
					
					}else if(P_MENU_ID instanceof String[]){
						System.err.println("@@@ String[] @@@");
						
						//??????ID
						String[] P_MENU_IDList = (String[]) P_MENU_ID;
						//??????????????????
						String[] P_SELECT_ATList = (String[]) P_SELECT_AT;
						
						for (int idx = 0; idx < P_MENU_IDList.length; idx++) {
							parameterMap.put("P_MENU_ID", P_MENU_IDList[idx]);
							parameterMap.put("P_SELECT_AT", P_SELECT_ATList[idx]);

							if("Y".equals(parameterMap.get("P_SELECT_AT"))) {
								// ???????????????????????? ??????
								iproSytmAuthMngeDao.insertAuthMgrItemRegist(parameterMap);
							}
						}
					}
					
				}//P_ESTM_CMTM_NO != null
				
			}//parameterMap.get("P_ESTM_FRM_NO") != null
		
		}//parameterMap.get("P_ESTM_SPHE_SECD")
		//trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));
		trans.put("P_AUTH_ID", parameterMap.get("P_AUTH_ID"));
		
		return trans;
	}
	
	

}
