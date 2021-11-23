package com.eunwoosoft.ipro.sytm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sytm.dao.IproSytmEstmSeMngDao;
import com.eunwoosoft.ipro.sytm.service.IproSytmEstmSeMngService;

/**
 * 
 * com.eunwoosoft.ipro.sytm.service.impl
 * IproSytmEstmSeMngServiceImpl.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 3. 26.
 *
 */
@Service(value="iproSytmEstmSeMngService")
public class IproSytmEstmSeMngServiceImpl extends AbstractFwkService implements IproSytmEstmSeMngService {
	private static final Logger LOG = LoggerFactory.getLogger(IproSytmEstmSeMngServiceImpl.class);
	
	@Resource(name="iproSytmEstmSeMngDao") 
	private IproSytmEstmSeMngDao iproSytmEstmSeMngDao;
	
	@Resource(name = "comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao;

	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;
	
	
	/**
	 * 평가구분 목록
	 */
	@Override
	public FwkTransferObject estmSeMngListWithPgng(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("estmSeMngList", iproSytmEstmSeMngDao.selectEstmSeMngList(parameterMap));
		trans.put("estmSeMngListTotCnt", iproSytmEstmSeMngDao.selectEstmSeMngListTotCnt(parameterMap));
		
		return trans;
	}
	
	@Override
	public FwkTransferObject estmSeMngListExcelDwld(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("dataList", iproSytmEstmSeMngDao.selectEstmSeMngExcelList(parameterMap));
		
		return trans;
	}


	/**
	 * 평가구분 저장
	 */
	@Override
	public FwkTransferObject estmSeMngRegist(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		String estmSecdMax = iproSytmEstmSeMngDao.selectEstmSecdMax(parameterMap);
		
		
		
		parameterMap.put("P_CD_DTL_ID", estmSecdMax);
		parameterMap.put("P_ESTM_SECD", estmSecdMax);
		
		parameterMap.put("P_CD_ID", "ESTM_SECD");
		parameterMap.put("P_CD_NM", "평가구분코드");
		
		//parameterMap.get("loginResult");
		parameterMap.put("P_USE_YN", "Y");
		parameterMap.put("P_ETC_VAL1", "ESTM_PROG");
		parameterMap.put("P_DEL_AT", "N");
		
		iproSytmEstmSeMngDao.insertCodeMstEstmSecd(parameterMap);
		

		parameterMap.put("P_DEL_AT", "N");
		
		
		/********** 심사위원 서명파일 등록 start **********/ // 정보관리 파일 마스터로 파일그룹번호를 넣어주기 위한 존재 
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)parameterMap.get("multipartRequest");
		String contextPath = "sytm";
		FwkParameterMap signFileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_SIGN_FILE", contextPath);   // 심사위원 서명파일
		
		String SIGN_FILE_GRP_NO = ""; //파일그룹번호 
		
		if(signFileParameterMap != null) {
			
			SIGN_FILE_GRP_NO = signFileParameterMap.getString("atchFileGroupNo");
			System.err.println("@@@ SIGN_FILE_GRP_NO ==> " + SIGN_FILE_GRP_NO);

			List<Map<String, Object>> list = (List<Map<String, Object>>)signFileParameterMap.get("list");
			List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
			
			Object P_FILE_DOC_NM =  parameterMap.get("P_FILE_DOC_NM");
			
			if(P_FILE_DOC_NM instanceof String) { //파일문서명 
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					map.put("P_FILE_DOC_NM", P_FILE_DOC_NM.toString());
					newList.add(map);
				}
			}else if(P_FILE_DOC_NM instanceof String[]) {
				System.err.println("@@@ String[] @@@");
				String[] fileDocNmArray = (String[])P_FILE_DOC_NM;
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					map.put("P_FILE_DOC_NM", fileDocNmArray[i]);
					newList.add(map);
				}
			}else if(P_FILE_DOC_NM instanceof ArrayList) {
				System.err.println("@@@ ArrayList @@@");
				ArrayList<String> fileDocNmList = (ArrayList<String>)P_FILE_DOC_NM;
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					map.put("P_FILE_DOC_NM", fileDocNmList.get(i));
					newList.add(map);
				}
			}

			// 공통 파일정보 저장
			parameterMap.put("atchFileGroupNo", SIGN_FILE_GRP_NO);
			parameterMap.put("fileList", newList);
			parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
			comAtmaAtchFileDao.insertMMFileRegist(parameterMap);   // T_MM_FILE_MST INSERT
			
			parameterMap.put("P_FILE_GRP_NO", SIGN_FILE_GRP_NO); //파일그룹번호 
			parameterMap.put("P_RMK", ""); //비고
		}
		/********** 심사위원 서명파일 등록 end **********/
		
//
		if (parameterMap.get("P_ESTM_SECD") != null) {
			// 평가서식 마스터 등록
			iproSytmEstmSeMngDao.insertEstmSeMngMstRegist(parameterMap);
			
			// 평가절차 등록
			if(parameterMap.get("P_ESTM_PROCD_SECD") != null) {
				
				//평가절차구분코드
				Object P_ESTM_PROCD_SECD = parameterMap.get("P_ESTM_PROCD_SECD");
				//평가절차명
				Object P_ESTM_PROCD_NM = parameterMap.get("P_ESTM_PROCD_NM");
				//평가서식번호
				Object P_ESTM_FRM_NO = parameterMap.get("P_ESTM_FRM_NO");
				//평가서식명
				Object P_ESTM_FRM_NM = parameterMap.get("P_ESTM_FRM_NM");
				
				if(P_ESTM_PROCD_SECD != null){
					
					System.err.println("@@@ P_ESTM_DTL_ITEM_NO != null @@@");
					
					if(P_ESTM_PROCD_SECD instanceof String){
						
						System.err.println("@@@ String @@@");
						
						// 평가서식항목 등록
						iproSytmEstmSeMngDao.insertEstmSeProcdRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
					}else if(P_ESTM_PROCD_SECD instanceof ArrayList){
					
						System.err.println("@@@ ArrayList @@@");
						
						//평가절차구분코드
						ArrayList<String> P_ESTM_PROCD_SECDList = (ArrayList<String>) P_ESTM_PROCD_SECD;
						//평가절차명
						ArrayList<String> P_ESTM_PROCD_NMList = (ArrayList<String>) P_ESTM_PROCD_NM;
						//평가서식번호
						ArrayList<String> P_ESTM_FRM_NOList = (ArrayList<String>) P_ESTM_FRM_NO;
						//평가서식명
						ArrayList<String> P_ESTM_FRM_NMList = (ArrayList<String>) P_ESTM_FRM_NM;
						
							for(int i =1; i < P_ESTM_PROCD_SECDList.size(); i++){
								
								parameterMap.put("P_ESTM_PROCD_SECD", P_ESTM_PROCD_SECDList.get(i));
								parameterMap.put("P_ESTM_PROCD_NM", P_ESTM_PROCD_NMList.get(i));
								parameterMap.put("P_ESTM_FRM_NO", P_ESTM_FRM_NOList.get(i));
								parameterMap.put("P_ESTM_FRM_NM", P_ESTM_FRM_NMList.get(i));
								
								// 평가서식항목 등록
								iproSytmEstmSeMngDao.insertEstmSeProcdRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
							}
					
					}else if(P_ESTM_PROCD_SECD instanceof String[]){
						System.err.println("@@@ String[] @@@");
						
						//평가항목번호
						String[] P_ESTM_PROCD_SECDList = (String[]) P_ESTM_PROCD_SECD;
						//평가항목명
						String[] P_ESTM_PROCD_NMList = (String[]) P_ESTM_PROCD_NM;
						//평가항목내용
						String[] P_ESTM_FRM_NOList = (String[]) P_ESTM_FRM_NO;
						//배점
						String[] P_ESTM_FRM_NMList = (String[]) P_ESTM_FRM_NM;
						
						for (int idx = 0; idx < P_ESTM_PROCD_SECDList.length; idx++) {
							parameterMap.put("P_ESTM_PROCD_SECD", P_ESTM_PROCD_SECDList[idx]);
							parameterMap.put("P_ESTM_PROCD_NM", P_ESTM_PROCD_NMList[idx]);
							parameterMap.put("P_ESTM_FRM_NO", P_ESTM_FRM_NOList[idx]);
							parameterMap.put("P_ESTM_FRM_NM", P_ESTM_FRM_NMList[idx]);

							// 평가서식항목 등록
							iproSytmEstmSeMngDao.insertEstmSeProcdRegist(parameterMap);
						}
					}
					
				}//P_ESTM_DTL_ITEM_NO != null
				
			}//P_ESTM_DTL_ITEM_NO != null
		}//parameterMap.get("P_ESTM_FRM_NO") != null
		
		trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));
		
		parameterMap.put("P_ESTM_SECD_TRANS", parameterMap.getString("P_ESTM_SECD"));
		parameterMap.put("resultCode", "Success");
		
		trans.put("P_ESTM_SECD_TRANS", parameterMap.getString("P_ESTM_SECD"));
		trans.put("resultCode", "Success");
		
		return trans;
	}
	
	/**
	 * 평가서식 상세
	 */
	//@Override
	public FwkTransferObject estmFrmDetail(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("estmFrmDetail", iproSytmEstmSeMngDao.selectEstmFrmDetail(parameterMap));
		trans.put("estmFrmItemList", iproSytmEstmSeMngDao.selectEstmFrmItemList(parameterMap));
		
		return trans;
	}

	
	/**
	 * 평가서식 수정
	 */
//	public FwkTransferObject estmFrmUpdt(FwkParameterMap parameterMap) throws Exception {
//		FwkTransferObject trans = new FwkTransferObject();
//		
//		parameterMap.put("P_DEL_AT", "N");
//
//		if (parameterMap.get("P_ESTM_SECD") != null) {
//			// 평가서식 마스터 등록
//			iproSytmEstmSeMngDao.insertEstmSeMngMstRegist(parameterMap);
//			
//
//
//			// 평가절차 등록
//			if(parameterMap.get("P_ESTM_PROCD_SECD") != null) {
//				
//				//평가절차구분코드
//				Object P_ESTM_PROCD_SECD = parameterMap.get("P_ESTM_PROCD_SECD");
//				//평가절차명
//				Object P_ESTM_PROCD_NM = parameterMap.get("P_ESTM_PROCD_NM");
//				//평가서식번호
//				Object P_ESTM_FRM_NO = parameterMap.get("P_ESTM_FRM_NO");
//				//평가서식명
//				Object P_ESTM_FRM_NM = parameterMap.get("P_ESTM_FRM_NM");
//				
//				if(P_ESTM_PROCD_SECD != null){
//					
//					System.err.println("@@@ P_ESTM_DTL_ITEM_NO != null @@@");
//					
//					if(P_ESTM_PROCD_SECD instanceof String){
//						
//						System.err.println("@@@ String @@@");
//						
//						// 평가서식항목 등록
//						iproSytmEstmSeMngDao.insertEstmSeProcdRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
//					}else if(P_ESTM_PROCD_SECD instanceof ArrayList){
//					
//						System.err.println("@@@ ArrayList @@@");
//						
//						//평가절차구분코드
//						ArrayList<String> P_ESTM_PROCD_SECDList = (ArrayList<String>) P_ESTM_PROCD_SECD;
//						//평가절차명
//						ArrayList<String> P_ESTM_PROCD_NMList = (ArrayList<String>) P_ESTM_PROCD_NM;
//						//평가서식번호
//						ArrayList<String> P_ESTM_FRM_NOList = (ArrayList<String>) P_ESTM_FRM_NO;
//						//평가서식명
//						ArrayList<String> P_ESTM_FRM_NMList = (ArrayList<String>) P_ESTM_FRM_NM;
//						
//							for(int i =1; i < P_ESTM_PROCD_SECDList.size(); i++){
//								
//								parameterMap.put("P_ESTM_PROCD_SECD", P_ESTM_PROCD_SECDList.get(i));
//								parameterMap.put("P_ESTM_PROCD_NM", P_ESTM_PROCD_NMList.get(i));
//								parameterMap.put("P_ESTM_FRM_NO", P_ESTM_FRM_NOList.get(i));
//								parameterMap.put("P_ESTM_FRM_NM", P_ESTM_FRM_NMList.get(i));
//								
//								// 평가서식항목 등록
//								iproSytmEstmSeMngDao.insertEstmSeProcdRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
//							}
//					
//					}else if(P_ESTM_PROCD_SECD instanceof String[]){
//						System.err.println("@@@ String[] @@@");
//						
//						//평가항목번호
//						String[] P_ESTM_PROCD_SECDList = (String[]) P_ESTM_PROCD_SECD;
//						//평가항목명
//						String[] P_ESTM_PROCD_NMList = (String[]) P_ESTM_PROCD_NM;
//						//평가항목내용
//						String[] P_ESTM_FRM_NOList = (String[]) P_ESTM_FRM_NO;
//						//배점
//						String[] P_ESTM_FRM_NMList = (String[]) P_ESTM_FRM_NM;
//						
//						for (int idx = 1; idx < P_ESTM_PROCD_SECDList.length; idx++) {
//							parameterMap.put("P_ESTM_PROCD_SECD", P_ESTM_PROCD_SECDList[idx]);
//							parameterMap.put("P_ESTM_PROCD_NM", P_ESTM_PROCD_NMList[idx]);
//							parameterMap.put("P_ESTM_FRM_NO", P_ESTM_FRM_NOList[idx]);
//							parameterMap.put("P_ESTM_FRM_NM", P_ESTM_FRM_NMList[idx]);
//
//							// 평가서식항목 등록
//							iproSytmEstmSeMngDao.insertEstmSeProcdRegist(parameterMap);
//						}
//					}
//					
//				}//P_ESTM_DTL_ITEM_NO != null
//				
//			}//P_ESTM_DTL_ITEM_NO != null
//		}//parameterMap.get("P_ESTM_FRM_NO") != null
//		
//		trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));
//		
//		return trans;
//	}
	
	
	
	/**
	 * 평가서식 등록폼
	 */
	@Override
	public FwkTransferObject estmSeMngRegForm(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("estmSeMngDetail", iproSytmEstmSeMngDao.selectEstmSeMngDetail(parameterMap));
		//trans.put("estmSeProcdList", iproSytmEstmSeMngDao.selectEstmSeProcdList(parameterMap));
		
		return trans;
	}
	
	/**
	 * 평가구분관리 상세
	 */
	@Override
	public FwkTransferObject estmSeMngDetail(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity estmSeMngDetail = (FwkDataEntity) iproSytmEstmSeMngDao.selectEstmSeMngDetail(parameterMap);   // 
		trans.put("estmSeMngDetail", estmSeMngDetail);
		
		// 심사위원 서명파일 조회
		if (estmSeMngDetail != null) {
			String ESTM_SIGN_FILE_GRP_NO = estmSeMngDetail.getString("FILE_GRP_NO");
			parameterMap.put("P_FILE_GRP_NO", ESTM_SIGN_FILE_GRP_NO);
			parameterMap.put("P_TSK_VKEY1", "");
			trans.put("estmSignFileList", comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		List<FwkDataEntity> estmSeProcdList = iproSytmEstmSeMngDao.selectEstmSeProcdList(parameterMap);
		
		trans.put("estmSeProcdList", estmSeProcdList);
		trans.put("estmSeProcdListCnt", estmSeProcdList.size());
		
		return trans;
	}
	
	/**
	 * 평가구분관리 상세
	 */
	@Override
	public FwkTransferObject estmSeMngDelete(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		iproSytmEstmSeMngDao.updateTestmSeMstUpdt(parameterMap); //T_ESTM_SE_MST DEL_AT = 'Y' UDPATE
		iproSytmEstmSeMngDao.updateTestmSeProcdUpdt(parameterMap); //T_ESTM_SE_PROCD DEL_AT = 'Y' UDPATE
		iproSytmEstmSeMngDao.updateTestmSeFrmUpdt(parameterMap); //T_ESTM_SE_FRM DEL_AT = 'Y' UDPATE
		
		
		iproSytmEstmSeMngDao.updateTmmCodeMstEstmSecdUpdt(parameterMap); //T_MM_CODE_MST CD_ID = 'ESTM_SECD' , USE_YN = 'N' UDPATE
		
		
		return trans;
	}
	
	
	/**
	 * 평가구분관리 산술식 저장
	 */
	@Override
	public FwkTransferObject estmSeMngClcRulSave(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		iproSytmEstmSeMngDao.updateEstmSeMngClcRulSave(parameterMap);
		
		parameterMap.put("resultCode", "Success");
		
		return trans;
	}
	
	
	
	
	/**
	 * 평가구분 수정
	 */
	@Override
	public FwkTransferObject estmSeMngUpdt(FwkParameterMap parameterMap) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		

		parameterMap.put("P_DEL_AT", "N");
		
		
		/********** 심사위원 서명파일 등록 start **********/
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)parameterMap.get("multipartRequest");
		String contextPath = "sytm";
		FwkParameterMap signFileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_SIGN_FILE", contextPath);   // 심사위원 서명파일
		
		String SIGN_FILE_GRP_NO = "";
		
		if(signFileParameterMap != null) {
			
			System.err.println("@@@ SIGN_FILE_GRP_NO ==> " + SIGN_FILE_GRP_NO);
			
			if("".equals(parameterMap.getString("P_SIGN_FILE_GRP_NO"))){
				SIGN_FILE_GRP_NO = signFileParameterMap.getString("atchFileGroupNo");
				parameterMap.put("P_SIGN_FILE_GRP_NO", SIGN_FILE_GRP_NO);
			}else{
				SIGN_FILE_GRP_NO = parameterMap.getString("P_SIGN_FILE_GRP_NO");
				parameterMap.put("P_SIGN_FILE_GRP_NO", SIGN_FILE_GRP_NO);
			}
			

			List<Map<String, Object>> list = (List<Map<String, Object>>)signFileParameterMap.get("list");
			List<Map<String, Object>> newList = new ArrayList<Map<String,Object>>();
			
			Object P_FILE_DOC_NM =  parameterMap.get("P_FILE_DOC_NM");
			
			System.err.println("@@@ list ==> " + list.toString());
			
			if(P_FILE_DOC_NM instanceof String) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					map.put("P_FILE_DOC_NM", P_FILE_DOC_NM.toString());
					newList.add(map);
				}
			}else if(P_FILE_DOC_NM instanceof String[]) {
				System.err.println("@@@ String[] @@@");
				String[] fileDocNmArray = (String[])P_FILE_DOC_NM;
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					map.put("P_FILE_DOC_NM", fileDocNmArray[i]);
					newList.add(map);
				}
			}else if(P_FILE_DOC_NM instanceof ArrayList) {
				System.err.println("@@@ ArrayList @@@");
				ArrayList<String> fileDocNmList = (ArrayList<String>)P_FILE_DOC_NM;
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					map.put("P_FILE_DOC_NM", fileDocNmList.get(i));
					newList.add(map);
				}
			}

			// 공통 파일정보 저장
			parameterMap.put("atchFileGroupNo", SIGN_FILE_GRP_NO);
			parameterMap.put("fileList", newList);
			parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
			comAtmaAtchFileDao.insertMMFileRegist(parameterMap);   // T_MM_FILE_MST INSERT
			
			parameterMap.put("P_FILE_GRP_NO", SIGN_FILE_GRP_NO);
			parameterMap.put("P_RMK", "");
		}
		
		// 삭제한 파일 DELETE_AT = 'N' 처리
		String deleteFileSn = parameterMap.getString("P_DELETE_FILE_SN");
		if(!"".equals(deleteFileSn)){
			String[] arrDeleteFileSn = deleteFileSn.split(",");
			List<Map<String, Object>> deleteFileSnList = new ArrayList<Map<String,Object>>();
			
			for (int i = 0; i < arrDeleteFileSn.length; i++) {
				FwkParameterMap pmap = new FwkParameterMap();
				pmap.put("P_FILE_SN", arrDeleteFileSn[i]);
				deleteFileSnList.add(pmap);
			}
			comAtmaAtchFileService.atchFileDelete(deleteFileSnList);
		}
		/********** 심사위원 서명파일 등록 end **********/
		

		if (parameterMap.get("P_ESTM_SECD") != null) {
			// 평가서식 마스터 등록
			//iproSytmEstmSeMngDao.insertEstmSeMngMstRegist(parameterMap);
			
			
			parameterMap.put("P_FILE_GRP_NO", parameterMap.get("P_SIGN_FILE_GRP_NO"));
			iproSytmEstmSeMngDao.updateEstmSeMngMstUpdt(parameterMap);
			
			// 평가절차삭제
			iproSytmEstmSeMngDao.deleteEstmSeProcdDelt(parameterMap);
			
			// 평가절차 등록
			if(parameterMap.get("P_ESTM_PROCD_SECD") != null) {
				
				//평가절차구분코드
				Object P_ESTM_PROCD_SECD = parameterMap.get("P_ESTM_PROCD_SECD");
				//평가절차명
				Object P_ESTM_PROCD_NM = parameterMap.get("P_ESTM_PROCD_NM");
				//평가서식번호
				Object P_ESTM_FRM_NO = parameterMap.get("P_ESTM_FRM_NO");
				//평가서식명
				Object P_ESTM_FRM_NM = parameterMap.get("P_ESTM_FRM_NM");
				
				if(P_ESTM_PROCD_SECD != null){
					
					System.err.println("@@@ P_ESTM_DTL_ITEM_NO != null @@@");
					
					if(P_ESTM_PROCD_SECD instanceof String){
						
						System.err.println("@@@ String @@@");
						
						// 평가서식항목 등록
						iproSytmEstmSeMngDao.insertEstmSeProcdRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
					}else if(P_ESTM_PROCD_SECD instanceof ArrayList){
					
						System.err.println("@@@ ArrayList @@@");
						
						//평가절차구분코드
						ArrayList<String> P_ESTM_PROCD_SECDList = (ArrayList<String>) P_ESTM_PROCD_SECD;
						//평가절차명
						ArrayList<String> P_ESTM_PROCD_NMList = (ArrayList<String>) P_ESTM_PROCD_NM;
						//평가서식번호
						ArrayList<String> P_ESTM_FRM_NOList = (ArrayList<String>) P_ESTM_FRM_NO;
						//평가서식명
						ArrayList<String> P_ESTM_FRM_NMList = (ArrayList<String>) P_ESTM_FRM_NM;
						
							for(int i =1; i < P_ESTM_PROCD_SECDList.size(); i++){
								
								parameterMap.put("P_ESTM_PROCD_SECD", P_ESTM_PROCD_SECDList.get(i));
								parameterMap.put("P_ESTM_PROCD_NM", P_ESTM_PROCD_NMList.get(i));
								parameterMap.put("P_ESTM_FRM_NO", P_ESTM_FRM_NOList.get(i));
								parameterMap.put("P_ESTM_FRM_NM", P_ESTM_FRM_NMList.get(i));
								
								// 평가서식항목 등록
								iproSytmEstmSeMngDao.insertEstmSeProcdRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
							}
					
					}else if(P_ESTM_PROCD_SECD instanceof String[]){
						System.err.println("@@@ String[] @@@");
						
						//평가항목번호
						String[] P_ESTM_PROCD_SECDList = (String[]) P_ESTM_PROCD_SECD;
						//평가항목명
						String[] P_ESTM_PROCD_NMList = (String[]) P_ESTM_PROCD_NM;
						//평가항목내용
						String[] P_ESTM_FRM_NOList = (String[]) P_ESTM_FRM_NO;
						//배점
						String[] P_ESTM_FRM_NMList = (String[]) P_ESTM_FRM_NM;
						
						for (int idx = 0; idx < P_ESTM_PROCD_SECDList.length; idx++) {
							parameterMap.put("P_ESTM_PROCD_SECD", P_ESTM_PROCD_SECDList[idx]);
							parameterMap.put("P_ESTM_PROCD_NM", P_ESTM_PROCD_NMList[idx]);
							parameterMap.put("P_ESTM_FRM_NO", P_ESTM_FRM_NOList[idx]);
							parameterMap.put("P_ESTM_FRM_NM", P_ESTM_FRM_NMList[idx]);

							// 평가서식항목 등록
							iproSytmEstmSeMngDao.insertEstmSeProcdRegist(parameterMap);
						}
					}
					
				}//P_ESTM_DTL_ITEM_NO != null
				
			}//P_ESTM_DTL_ITEM_NO != null
		}//parameterMap.get("P_ESTM_FRM_NO") != null
		
		trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));
		
		parameterMap.put("P_ESTM_SECD_TRANS", parameterMap.getString("P_ESTM_SECD"));
		parameterMap.put("resultCode", "Success");
		
		trans.put("P_ESTM_SECD_TRANS", parameterMap.getString("P_ESTM_SECD"));
		trans.put("resultCode", "Success");
		
		return trans;
	}
	
	
}
