package com.eunwoosoft.ipro.appr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.appr.dao.IproApprMngDao;
import com.eunwoosoft.ipro.appr.service.IproApprMngService;


/**
 * 결재관리
 * 
 * <pre>
 * com.eunwoosoft.ipro.appr.service.impl 
 *    |_ IproApprMngServiceImpl.java
 * 
 * </pre>
 */
@Service("iproApprMngService")
public class IproApprMngServiceImpl extends AbstractFwkService implements IproApprMngService {

	@Resource(name="iproApprMngDao")
	private IproApprMngDao iproApprMngDao;
	
	public FwkTransferObject apprRegList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		// 결재선정보
		trans.put("applMstList", iproApprMngDao.selectApplMstList(parameterMap));   // T_MM_APPL_MST SELECT
		trans.put("applMstListTotCnt", iproApprMngDao.selectApplMstListTotCnt(parameterMap));
		
		// 결재자정보
		if(parameterMap.get("P_APPL_NO") != null && !"".equals(parameterMap.getString("P_APPL_NO"))){
			
			trans.put("apprAprpList", iproApprMngDao.selectApprAprpList(parameterMap));   // T_MM_APPL_APRP SELECT
			trans.put("apprAprpListTotCnt", iproApprMngDao.selectApprAprpListTotCnt(parameterMap));
			
			trans.put("P_APPL_NO", parameterMap.get("P_APPL_NO"));
			trans.put("P_APPL_SE", parameterMap.get("P_APPL_SE"));

		}

		return trans;
	}

	public void apprRegist(final FwkParameterMap parameterMap) {
		
		// 결재선
		iproApprMngDao.insertApplMst(parameterMap);   // T_MM_APPL_MST INSERT
		
		Object P_APPR_USER_ID = parameterMap.get("P_APPR_USER_ID");

		if(P_APPR_USER_ID != null){
			
			if(P_APPR_USER_ID instanceof String){

				parameterMap.put("P_APRP_SN", parameterMap.get("P_TR_NUM"));
				parameterMap.put("P_APPR_USER_ID", parameterMap.get("P_APPR_USER_ID"));
				parameterMap.put("P_APPR_USER_NM", FwkStringUtil.castEuc2Iso(parameterMap.get("P_APPR_USER_NM")));
				parameterMap.put("P_APPR_DEPT_NM", FwkStringUtil.castEuc2Iso(parameterMap.get("P_ORG_NM")));
				parameterMap.put("P_APRP_ORD_SN", parameterMap.get("P_APRP_ORD_SN"));
				parameterMap.put("P_APPR_AUCD", parameterMap.get("P_APPR_AUCD"));

				// 결재자정보
				iproApprMngDao.insertApplAprp(parameterMap);   // T_MM_APPL_APRP INSERT

			}else {

				ArrayList<String> arrayList = (ArrayList<String>)  parameterMap.get("P_APPR_USER_ID");
				List<String> emplNoList = arrayList;
				List<String> emplNmList = (ArrayList<String>) parameterMap.get("P_APPR_USER_NM");
				List<String> deptNmList = (ArrayList<String>) parameterMap.get("P_ORG_NM");
				List<String> aprpNoList = (ArrayList<String>) parameterMap.get("P_TR_NUM");
				List<String> aprpOrdSnList =  (ArrayList<String>) parameterMap.get("P_APRP_ORD_SN");
				List<String> apprAucdList =  (ArrayList<String>) parameterMap.get("P_APPR_AUCD");

				String  pAprpUserId="";
				String pAprpUserNm = "";
				String pAprpDeptNm="";
				String pAprpNo ="";
				String pAprpOrdSn ="";
				String pAprpAucd ="";
				
				for (int idx = 0; idx < emplNoList.size(); idx++) {
					pAprpUserId = emplNoList.get(idx);
					pAprpUserNm = emplNmList.get(idx);
					pAprpDeptNm = deptNmList.get(idx);
					pAprpNo =  aprpNoList.get(idx);
					pAprpOrdSn = aprpOrdSnList.get(idx);
					pAprpAucd = apprAucdList.get(idx);

					parameterMap.put("P_APPR_USER_ID", pAprpUserId);
					parameterMap.put("P_APPR_USER_NM", pAprpUserNm);
					parameterMap.put("P_APPR_DEPT_NM", pAprpDeptNm);
					parameterMap.put("P_APRP_SN", pAprpNo);
					parameterMap.put("P_APRP_ORD_SN", pAprpOrdSn);
					parameterMap.put("P_APPR_AUCD", pAprpAucd);

					iproApprMngDao.insertApplAprp(parameterMap);   // T_MM_APPL_APRP INSERT
				}
			}
		}
	}

	public void applMstDelete(final FwkParameterMap parameterMap) {

		// 결재자 삭제
		// iproApprMngDao.infoApprlineDeleteUser(parameterMap);   // T_MM_APPL_APRP
		
		// 결재선 삭제
		iproApprMngDao.updateApplMstDelAt(parameterMap);   // T_MM_APPL_MST UPDATE
	}

	
	// 결재선 - 결재자정보 목록
	public FwkTransferObject apprAprpList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("apprAprpList", iproApprMngDao.selectApprAprpList(parameterMap));
		trans.put("apprAprpListTotCnt", iproApprMngDao.selectApprAprpListTotCnt(parameterMap));
		

		return trans;
	}

	public void updateApplAprp(final FwkParameterMap parameterMap) {

		// 결재선마스터 수정
		iproApprMngDao.updateApplMst(parameterMap);   // T_MM_APPL_MST UPDATE

		Object P_APPR_USER_ID = parameterMap.get("P_APPR_USER_ID");
		
		if(P_APPR_USER_ID != null){
			
			if(P_APPR_USER_ID instanceof String){
				
				if(parameterMap.get("P_APRP_SN").equals("")) {
					parameterMap.put("P_APRP_SN", parameterMap.get("P_TR_NUM"));
					parameterMap.put("P_APPR_USER_ID", parameterMap.get("P_APPR_USER_ID"));
					parameterMap.put("P_APPR_USER_NM", FwkStringUtil.castEuc2Iso(parameterMap.get("P_APPR_USER_NM")));
					parameterMap.put("P_APPR_DEPT_NM", FwkStringUtil.castEuc2Iso(parameterMap.get("P_ORG_NM")));
					parameterMap.put("P_APRP_ORD_SN", parameterMap.get("P_APRP_ORD_SN"));
					parameterMap.put("P_APPR_AUCD", parameterMap.get("P_APPR_AUCD"));

					iproApprMngDao.insertApplAprp(parameterMap);   // T_MM_APPL_APRP INSERT
					
				}else {
					parameterMap.put("P_APRP_SN", parameterMap.get("P_APRP_SN"));
					//parameterMap.put("P_APRP_ORD_SN", paramerM)

					//if(parameterMap.get("P_DEL_AT").equals("Y")){
						parameterMap.put("P_DEL_AT", parameterMap.get("P_DEL_AT"));
						iproApprMngDao.updateApplAprp(parameterMap);   // T_MM_APPL_APRP UPDATE
					//}
				}
			}else {
				List<String> aprpSnList = (ArrayList<String>) parameterMap.get("P_APRP_SN");
				List<String> emplNoList = (ArrayList<String>) parameterMap.get("P_APPR_USER_ID");
				List<String> emplNmList = (ArrayList<String>) parameterMap.get("P_APPR_USER_NM");
				List<String> deptNmList = (ArrayList<String>) parameterMap.get("P_ORG_NM");
				List<String> apprAucdList = (ArrayList<String>) parameterMap.get("P_APPR_AUCD");
				List<String> aprpNoList = (ArrayList<String>) parameterMap.get("P_TR_NUM");
				List<String> delAtList = (ArrayList<String>) parameterMap.get("P_DEL_AT");
				List<String> aprpOrdSnList = (ArrayList<String>) parameterMap.get("P_APRP_ORD_SN");

				String pAprpSn;
				String pAprpUsrId;
				String pAprpUserNm;
				String pAprpDeptNm;
				String pAprpNo;
				String pDelAt;
				String pAprpOrdSn;
				String apprAucd;

				for (int idx = 0; idx < emplNoList.size(); idx++) {
					pAprpSn = aprpSnList.get(idx);						
					pAprpUsrId = emplNoList.get(idx);
					pAprpUserNm = emplNmList.get(idx);
					pAprpDeptNm = deptNmList.get(idx);
					pAprpNo =  aprpNoList.get(idx);
					pAprpOrdSn = aprpOrdSnList.get(idx);
					apprAucd = apprAucdList.get(idx);
					//p_aprp_no =  aprp_no_list.get(idx);
					pDelAt = delAtList.get(idx);

					parameterMap.put("P_APPR_USER_ID", pAprpUsrId);

					parameterMap.put("P_APPR_USER_NM", FwkStringUtil.castEuc2Iso(pAprpUserNm));
					parameterMap.put("P_APPR_DEPT_NM", FwkStringUtil.castEuc2Iso(pAprpDeptNm));
					
					parameterMap.put("P_APRP_SN", pAprpNo);
					parameterMap.put("P_APPR_AUCD", apprAucd);
					parameterMap.put("P_APRP_ORD_SN", pAprpOrdSn);
					parameterMap.put("P_DEL_AT", pDelAt);
					
					// if(parameterMap.get("resourceName") == null || ((String)parameterMap.get("resourceName")).equals("")){
					//if(pAprpSn.equals("") || pAprpSn == null || pAprpSn == "") {
					//if(pAprpSn == "no" || "no".equals(pAprpSn)){
					if(pAprpSn.equals("")){
						iproApprMngDao.insertApplAprp(parameterMap);   // T_MM_APPL_APRP INSERT
					}else{
						/*if(parameterMap.get("P_DEL_AT").equals("Y")) {
							parameterMap.put("P_DEL_AT", "Y");
						}else {
							parameterMap.put("P_DEL_AT", "N");
						}*/

						iproApprMngDao.updateApplAprp(parameterMap);   // T_MM_APPL_APRP UPDATE

					}
				}
			}
		}
	}

	
	public FwkTransferObject apprObjList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		parameterMap.put("P_APPR_TRGET", "Y");
		parameterMap.put("P_APPR_STCD_S", "APP");
		
		trans.put("apprObjList", iproApprMngDao.selectApprObjList(parameterMap));   // T_MM_APPR_MST
		trans.put("apprObjListTotCnt", iproApprMngDao.selectApprObjListTotCnt(parameterMap));

		return trans;
	}

	
	public FwkTransferObject apprObjDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		trans.put("apprObjMstDetail", iproApprMngDao.selectApprObjMstDetail(parameterMap));   // T_MM_APPR_MST
		trans.put("apprObjAprpList", iproApprMngDao.selectApprObjAprpList(parameterMap));   // T_MM_APPR_APRP

		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 결재대상_상세
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : getApprTarget
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
	public FwkTransferObject getApprTarget(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		String aprdcNo = (String) parameterMap.get("P_APRDC_NO_S");
		String aprpSn = (String) parameterMap.get("P_APRP_SN_S");

		parameterMap.put("P_APRDC_NO", aprdcNo);
		parameterMap.put("P_APRP_SN", aprpSn);

		trans.put("tApprTgl", iproApprMngDao.selectApprAprpList(parameterMap));

		return trans;
	}

	public void apprYnUpdate(final FwkParameterMap parameterMap) throws Exception {
		
		iproApprMngDao.updateApprAprpYn(parameterMap);   // T_MM_APPR_APRP UPDATE
		
		iproApprMngDao.updateApprMst(parameterMap);   // T_MM_APPR_MST
		
		//iproApprMngDao.apprMstUpdate(parameterMap);
		
		if(!parameterMap.get("P_APPR_YN").equals("Y")){
			//iproApprMngDao.apprMstUpdateRsn(parameterMap);
		}
		
		parameterMap.put("P_KEY1", parameterMap.get("P_APPR_NO"));
		if(!parameterMap.get("P_APPR_YN").equals("R")){//반려아닐경우 전송
			parameterMap.put("P_JOBGB", "AP");
			parameterMap.put("P_APPR_GBN", "AP");
		}else{
			parameterMap.put("P_JOBGB", "RTN");
			parameterMap.put("P_APPR_GBN", "RTN");
		}
		
		// 2019-12-13 SMS EMAIL SEND
		// 2020-01-14 결재승인 시 다음결재자에게 SMS 발송 처리
//		etcFormService.sendSMS(parameterMap);
		// 2020-01-23 결재승인 시 다음결재자에게 EMAIL 발송 처리
//		etcFormService.sendEMAIL(parameterMap);
		
	}
	
	
	/**
	 * <pre>
	 * 1. 개요 :결재완료 목록
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : apprCmplList
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	public FwkTransferObject apprCmplList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		// parameterMap.put("P_APPR_TRGET", "N"); //결재가 끝난 경우
		
		trans.put("apprCmplList", iproApprMngDao.selectApprCmplList(parameterMap));
		trans.put("apprCmplListTotCnt", iproApprMngDao.selectApprCmplListTotCnt(parameterMap));

		return trans;
	}
}