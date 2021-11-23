package com.eunwoosoft.ipro.info.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.info.dao.IproInfoApprMngeDao;
import com.eunwoosoft.ipro.info.service.IproInfoApprMngeService;

/**
 * 결재관리
 *
 * @author : 은우소프트 맹경열
 * @date : 2018. 02. 26.
 * @version : 1.0
 */
@Service("iproInfoApprMngeService")
public class IproInfoApprMngeServiceImpl extends AbstractFwkService implements IproInfoApprMngeService {

	@Resource(name="iproInfoApprMngeDao")
	private IproInfoApprMngeDao iproInfoApprMngeDao;
	
//	@Resource(name="etcFormService")
//	private EtcFormService etcFormService;

	/**
	 * <pre>
	 * 1. 개요 : 결재선조회_페이징
	 * 2. 처리내용 :
	 *
	 * </pre>
	 * @Method Name : getMmApplMstList
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 08.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	public FwkTransferObject getMmApplMstList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		// 결재선정보
		trans.put(IproInfoApprMngeService.T_MM_APPL_LIST, iproInfoApprMngeDao.getMmApplMstListWithPgng(parameterMap));   // T_MM_APPL_MST SELECT
		
		trans.put(IproInfoApprMngeService.T_MM_APPL_LIST_TOTCNT, iproInfoApprMngeDao.getMmApplMstListTotcnt(parameterMap));
		
		
		// 결재자정보
		if(parameterMap.get("P_APPL_NO") != null && !"".equals(parameterMap.getString("P_APPL_NO"))){
			
			trans.put(IproInfoApprMngeService.T_MM_APRP_LIST, iproInfoApprMngeDao.getMmAprpList(parameterMap));   // T_MM_APRP_MST SELECT
			
			trans.put(IproInfoApprMngeService.T_MM_APRP_LIST_TOTCNT, iproInfoApprMngeDao.getMmAprpListTotcnt(parameterMap));
			
			trans.put("P_APPL_NO", parameterMap.get("P_APPL_NO"));
			trans.put("P_APPL_SE", parameterMap.get("P_APPL_SE"));
			
		}

		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 결재선등록
	 * 2. 처리내용 :
	 *
	 * </pre>
	 * @Method Name : infoApprlineRegist
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 08.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	public void infoApprlineRegist(final FwkParameterMap parameterMap) {
		
		iproInfoApprMngeDao.infoApprlineRegistMaster(parameterMap);
		
		Object P_APPR_USER_ID = parameterMap.get("P_APPR_USER_ID");

		if(P_APPR_USER_ID != null){
			
			System.err.println("@@@ P_APPR_USER_ID NOT NULL @@@");
			
			if(P_APPR_USER_ID instanceof String){
				
				System.err.println("########################");

				parameterMap.put("P_APRP_SN", parameterMap.get("P_TR_NUM"));
				parameterMap.put("P_APPR_USER_ID", parameterMap.get("P_APPR_USER_ID"));
				parameterMap.put("P_APPR_USER_NM", FwkStringUtil.castEuc2Iso(parameterMap.get("P_APPR_USER_NM")));
				parameterMap.put("P_APPR_DEPT_NM", FwkStringUtil.castEuc2Iso(parameterMap.get("P_ORG_NM")));
				parameterMap.put("P_APRP_ORD_SN", parameterMap.get("P_APRP_ORD_SN"));
				parameterMap.put("P_APPR_AUCD", parameterMap.get("P_APPR_AUCD"));

				iproInfoApprMngeDao.infoApprlineRegistUser(parameterMap);

			}else {
				
				System.err.println("@@@@@@@@@@@@@@@@@@@@@");

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
				
				System.err.println("@@@ emplNoList.size() ==> " + emplNoList.size());
				
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

					iproInfoApprMngeDao.infoApprlineRegistUser(parameterMap);
				}
			}
		}
	}

	/**
	 * <pre>
	 * 1. 개요 : 결재선삭제
	 * 2. 처리내용 :
	 *
	 * </pre>
	 * @Method Name : infoApprlineDelete
	 * @date : 2018. 03. 09.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 09.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	public void infoApprlineDelete(final FwkParameterMap parameterMap) {

		// 결재자 삭제
		// iproInfoApprMngeDao.infoApprlineDeleteUser(parameterMap);   // T_MM_APPL_APRP
		
		// 결재선 삭제
		iproInfoApprMngeDao.updateApprlineDelAt(parameterMap);   // T_MM_APPL_MST
	}

	/**
	 * <pre>
	 * 1. 개요 : 결재선조회_상세
	 * 2. 처리내용 :
	 *
	 * </pre>
	 * @Method Name : getMmAprpList
	 * @date : 2018. 03. 09.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 09.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	public FwkTransferObject getMmAprpList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
//		FwkTransferObject trans_mst = new FwkTransferObject();
		//FwkTransferObject transMst = new FwkTransferObject();

		//trans_mst.put(IproInfoApprMngeService.T_MM_APPL_LIST, iproInfoApprMngeDao.getMmApplMstListWithPgng(parameterMap));
		if(parameterMap.get("P_APPL_SE").equals('B')){
			trans.put(IproInfoApprMngeService.T_MM_APRP_LIST, iproInfoApprMngeDao.getBlosMmAprpList(parameterMap));
			trans.put(IproInfoApprMngeService.T_MM_APRP_LIST_TOTCNT, iproInfoApprMngeDao.getBlosMmAprpListTotcnt(parameterMap));
		}else{
			trans.put(IproInfoApprMngeService.T_MM_APRP_LIST, iproInfoApprMngeDao.getMmAprpList(parameterMap));
			trans.put(IproInfoApprMngeService.T_MM_APRP_LIST_TOTCNT, iproInfoApprMngeDao.getMmAprpListTotcnt(parameterMap));
		}
		//trans.put("P_APPL_NM_OBJ", trans_mst.get("tMmApplList"));

		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 결재선수정
	 * 2. 처리내용 :
	 *
	 * </pre>
	 * @Method Name : infoApprlineUpdate
	 * @date : 2018. 03. 08.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 08.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	public void infoApprlineUpdate(final FwkParameterMap parameterMap) {

		// 결재선 마스터 수정
		iproInfoApprMngeDao.infoApprlineUpdateMaster(parameterMap);

		Object P_APPR_USER_ID = parameterMap.get("P_APPR_USER_ID");
		
		if(P_APPR_USER_ID != null){
			
			System.err.println("@@@ P_APPR_USER_ID IS NOT NULL @@@");
			
			if(P_APPR_USER_ID instanceof String){
				
				System.err.println("@@@@@@@@@@@@@@@@@@@@@@@");
				
				if(parameterMap.get("P_APRP_SN").equals("")) {
					parameterMap.put("P_APRP_SN", parameterMap.get("P_TR_NUM"));
					parameterMap.put("P_APPR_USER_ID", parameterMap.get("P_APPR_USER_ID"));
					parameterMap.put("P_APPR_USER_NM", FwkStringUtil.castEuc2Iso(parameterMap.get("P_APPR_USER_NM")));
					parameterMap.put("P_APPR_DEPT_NM", FwkStringUtil.castEuc2Iso(parameterMap.get("P_ORG_NM")));
					parameterMap.put("P_APRP_ORD_SN", parameterMap.get("P_APRP_ORD_SN"));
					parameterMap.put("P_APPR_AUCD", parameterMap.get("P_APPR_AUCD"));

					iproInfoApprMngeDao.infoApprlineRegistUser(parameterMap);
					
				}else {
					parameterMap.put("P_APRP_SN", parameterMap.get("P_APRP_SN"));
					//parameterMap.put("P_APRP_ORD_SN", paramerM)

					//if(parameterMap.get("P_DEL_AT").equals("Y")){
						parameterMap.put("P_DEL_AT", parameterMap.get("P_DEL_AT"));
						iproInfoApprMngeDao.infoApprlineUpdateUser(parameterMap);
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

				System.err.println("@@@ emplNoList.size() ==> " + emplNoList.size());
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
					
					System.err.println("@@@ pAprpSn ==> " + pAprpSn);


					// if(parameterMap.get("resourceName") == null || ((String)parameterMap.get("resourceName")).equals("")){
					//if(pAprpSn.equals("") || pAprpSn == null || pAprpSn == "") {
					//if(pAprpSn == "no" || "no".equals(pAprpSn)){
					if(pAprpSn.equals("")){
						iproInfoApprMngeDao.infoApprlineRegistUser(parameterMap);
					}else{
						/*if(parameterMap.get("P_DEL_AT").equals("Y")) {
							parameterMap.put("P_DEL_AT", "Y");
						}else {
							parameterMap.put("P_DEL_AT", "N");
						}*/

						iproInfoApprMngeDao.infoApprlineUpdateUser(parameterMap);

					}
				}
			}
		}
	}

	/**
	 * <pre>
	 * 1. 개요 : 결재대상 목록 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : getApplTargetList
	 * @date : 2018. 03. 15.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 15.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	public FwkTransferObject getApplTargetList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		parameterMap.put("P_APPR_TRGET", "Y");
		parameterMap.put("P_APPR_STCD_S", "APP");
		trans.put(IproInfoApprMngeService.T_APPR_MST_LIST, iproInfoApprMngeDao.getApprMstListWithPgng(parameterMap));
		trans.put(IproInfoApprMngeService.T_APPR_MST_LIST_TOTCNT, iproInfoApprMngeDao.getApprMstListTotcnt(parameterMap));

		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 결재대상_상세_리스트
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : getApprTargetDocu
	 * @date : 2018. 03. 21.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 21.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	public FwkTransferObject getApprTargetDocu(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		trans.put(IproInfoApprMngeService.T_MM_APPL_DETAIL, iproInfoApprMngeDao.getApprMstDetail(parameterMap));
		trans.put(IproInfoApprMngeService.T_APPR_MST_LIST, iproInfoApprMngeDao.getApprMstListWithPgng(parameterMap));
		trans.put(IproInfoApprMngeService.T_APPR_TGL_LIST, iproInfoApprMngeDao.getMmApprTglInfo(parameterMap));
		trans.put(IproInfoApprMngeService.T_APPR_TGL_LIST_TOTCNT, iproInfoApprMngeDao.getMmApprTglInfoTotcnt(parameterMap));

		return trans;
	}

	// 이마트 본사인지 아닌지 체크
	public FwkTransferObject getBizrnoChk(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		trans.put(IproInfoApprMngeService.T_APPR_EMART, iproInfoApprMngeDao.getBizrnoChk(parameterMap));
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

		trans.put(IproInfoApprMngeService.T_APPR_TGL, iproInfoApprMngeDao.getMmApprTglInfo(parameterMap));

		return trans;
	}

	/**
	 * <pre>
	 * 1. 개요 : 결재대상_의견등록
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : infoApprRsnUpdate
	 * @date : 2018. 03. 23.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 23.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 * @throws Exception 
	 */
	public void infoApprRsnUpdate(final FwkParameterMap parameterMap) throws Exception {
		
		iproInfoApprMngeDao.infoApprTglUpdate(parameterMap);
		System.err.println("@@@ infoApprTglUpdate end @@@ ");
		
		iproInfoApprMngeDao.infoApprMstUpdate(parameterMap);
		System.err.println("@@@ infoApprMstUpdate end @@@ ");
		
		//iproInfoApprMngeDao.apprMstUpdate(parameterMap);
		
		if(!parameterMap.get("P_APPR_YN").equals("Y")){
			//iproInfoApprMngeDao.apprMstUpdateRsn(parameterMap);
			System.err.println("@@@ apprMstUpdateRsn end @@@ ");
		}
		
		parameterMap.put("P_KEY1", parameterMap.get("P_APPR_NO"));
		if(!parameterMap.get("P_APPR_YN").equals("R")){//반려아닐경우 전송
			parameterMap.put("P_JOBGB", "AP");
			parameterMap.put("P_APPR_GBN", "AP");
		}else{
			parameterMap.put("P_JOBGB", "RTN");
			parameterMap.put("P_APPR_GBN", "RTN");
		}
		
		System.err.println("##### END #####");
		// 2019-12-13 SMS EMAIL SEND
		// 2020-01-14 결재승인 시 다음결재자에게 SMS 발송 처리
//		etcFormService.sendSMS(parameterMap);
		// 2020-01-23 결재승인 시 다음결재자에게 EMAIL 발송 처리
//		etcFormService.sendEMAIL(parameterMap);
		
	}
	
	
	/**
	 * <pre>
	 * 1. 개요 :결재완료내역 목록 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : getApplResultList
	 * @date : 2018. 03. 15.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자								변경내용
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 03. 15.		은우소프트 맹경열			최초 작성
	 *	-----------------------------------------------------------------------
	 *
	 * @param parameterMap
	 * @return {@link FwkTransferObject}
	 */
	public FwkTransferObject getApplResultList(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		/*parameterMap.put("P_APPR_TRGET", "N"); //결재가 끝난 경우
		
		trans.put(IproInfoApprMngeService.T_APPR_MST_LIST, iproInfoApprMngeDao.getApprMstListWithPgng(parameterMap));
		trans.put(IproInfoApprMngeService.T_APPR_MST_LIST_TOTCNT, iproInfoApprMngeDao.getApprMstListTotcnt(parameterMap));*/
		
		trans.put(IproInfoApprMngeService.T_APPR_MST_LIST, iproInfoApprMngeDao.selectApplResultListWithPgng(parameterMap));
		trans.put(IproInfoApprMngeService.T_APPR_MST_LIST_TOTCNT, iproInfoApprMngeDao.selectApplResultListTotcnt(parameterMap));

		return trans;
	}
}