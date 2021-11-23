package com.eunwoosoft.opro.comm.service.impl;

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
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.dao.IproCommPopupDao;
import com.eunwoosoft.opro.comm.dao.OproCommPopupDao;
import com.eunwoosoft.opro.comm.service.OproCommPopupService;
import com.eunwoosoft.opro.estm.dao.OproEstmCmtmProgDao;

/**
 * 
 * com.eunwoosoft.opro.comm.service.impl
 * OproCommPopupServiceImpl.java
 *
 * @Author : JuYeon_Lee
 * @Date   : 2018. 3. 26.
 * 
 */
  
@Service(value="oproCommPopupService")
public class OproCommPopupServiceImpl extends AbstractFwkService implements OproCommPopupService {
	private static final Logger LOG = LoggerFactory.getLogger(OproCommPopupServiceImpl.class); 
	
	@Resource(name="oproCommPopupDao")   
	private OproCommPopupDao oproCommPopupDao;
	
	@Resource(name="comAtmaAtchFileDao")   
	private ComAtmaAtchFileDao comAtmaAtchFileDao;
	
	@Resource(name="iproCommPopupDao")
	private IproCommPopupDao iproCommPopupDao;
	
	@Resource(name="oproEstmCmtmProgDao")
	private OproEstmCmtmProgDao oproEstmCmtmProgDao;
	
	//업체 목록 조회
	public FwkTransferObject entrpsInqireList(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(OproCommPopupService.ENTRPS_INQIRE_LIST, oproCommPopupDao.selectEntrpsInqireList(parameterMap));		//mainGnrlNoticeList
		trans.put(OproCommPopupService.ENTRPS_INQIRE_LIST_TOTCNT, oproCommPopupDao.selectEntrpsInqireListTotcnt(parameterMap));	//unityNttListTotcnt
		return trans; 
	} 
	
	
	//부서 목록 조회
	public FwkTransferObject deptInqireList(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(OproCommPopupService.DEPT_INQIRE_LIST			, oproCommPopupDao.selectDeptInqireList(parameterMap));		//mainGnrlNoticeList
		trans.put(OproCommPopupService.DEPT_INQIRE_LIST_TOTCNT	, oproCommPopupDao.selectDeptInqireListTotcnt(parameterMap));	//unityNttListTotcnt
		return trans;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 담당자조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : chargerListInqireWithPgng
	 * @date : 2018. 02. 20.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 02. 20.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return {@link TransferObject}
	 */
	@Override
	public FwkTransferObject chargerListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(OproCommPopupService.CHARGER_LIST , oproCommPopupDao.selectChargerListWithPgng(parameterMap));
		trans.put(OproCommPopupService.CHARGER_LIST_TOTCNT, oproCommPopupDao.selectChargerListTotcnt(parameterMap));
		return trans;
	}
	
	/**
	 * 
	 */
	@Override
	public FwkTransferObject mjrHndlItemListWithPgng( FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		trans.put(OproCommPopupService.MJR_HNDL_ITEM_LIST, iproCommPopupDao.selectMjrHndlItemListWithPgng(parameterMap));
		trans.put(OproCommPopupService.MJR_HNDL_ITEM_LIST_TOTCNT, iproCommPopupDao.selectMjrHndlItemListTotcnt(parameterMap));
		
		return trans;	
	}

	@Override
	public FwkTransferObject itemListInqireWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		trans.put(OproCommPopupService.ITEM_LIST, oproCommPopupDao.selectItemListWithPgng(parameterMap));
		trans.put(OproCommPopupService.ITEM_LIST_TOTCNT, oproCommPopupDao.selectItemListTotcnt(parameterMap));	
		return trans;
	}
	
	@Override
	public FwkTransferObject trmDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity trmDetail = oproCommPopupDao.trmDetail(parameterMap);
		
		trans.put("trmDetail" , trmDetail);

		return trans; 
	}

	/**
	 * 평가서식 상세
	 */
	public FwkTransferObject estmCmtmEstmFrmDetail(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put("estmCmtmEstmFrmDetail", oproCommPopupDao.selectEstmCmtmEstmFrmDetail(parameterMap));
		trans.put("estmObjDetail", oproCommPopupDao.selectEstmObjDetail(parameterMap));
		trans.put("estmCmtmEstmFrmItemList", oproCommPopupDao.selectEstmCmtmEstmFrmItemList(parameterMap));
		
		return trans;
	}

	public FwkTransferObject estmCmtmEstmFrmBSave(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		
		/**
		 * 평가위원 점수저장
		 * 1. 평가대상점수 : T_ESTM_OBJ_SCR 저장
		 * 2. 평가위원평가대상점수 : T_ESTM_CMTM_OBJ_SCR 저장
		 * 
		 */
		
		parameterMap.put("P_DEL_AT", "N");
		
		if(parameterMap.get("P_ESTM_NO") != null) {

			// 평가위원분야매핑 등록
			if(parameterMap.get("P_ESTM_CMTM_NO") != null) {
				
				//평가항목번호
				Object P_ESTM_DTL_ITEM_NO = parameterMap.get("P_ESTM_DTL_ITEM_NO");
				//평가점수
				Object P_ESTM_DTL_ITEM_POINT = parameterMap.get("P_ESTM_DTL_ITEM_POINT");
				
				if(P_ESTM_DTL_ITEM_NO != null){
					
					if(P_ESTM_DTL_ITEM_NO instanceof String){
						
						System.err.println("@@@ String @@@");
						
							// 평가위원분야매핑 등록
							//parameterMap.put("P_ESTM_CMTM_NO", P_ESTM_DTL_ITEM_NOList.get(i));
							parameterMap.put("P_ESTM_SCR", parameterMap.get("P_ESTM_DTL_ITEM_POINT"));
							oproCommPopupDao.mergeIntoEstmObjScr(parameterMap);
							oproCommPopupDao.insertEstmObjScrHist(parameterMap);
//							iproEstmCmtmSpheMpgDao.insertCmtmSpheMpgItemRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
					}else if(P_ESTM_DTL_ITEM_NO instanceof ArrayList){
					
						System.err.println("@@@ ArrayList @@@");
						
						//평가항목번호
						ArrayList<String> P_ESTM_DTL_ITEM_NOList = (ArrayList<String>) P_ESTM_DTL_ITEM_NO;
						//평가점수
						ArrayList<String> P_ESTM_DTL_ITEM_POINTList = (ArrayList<String>) P_ESTM_DTL_ITEM_POINT;
						
							for(int i =1; i < P_ESTM_DTL_ITEM_NOList.size(); i++){
								
								parameterMap.put("P_ESTM_ITEM_NO", P_ESTM_DTL_ITEM_NOList.get(i));
								parameterMap.put("P_ESTM_SCR", P_ESTM_DTL_ITEM_POINTList.get(i));
								
								// 평가위원분야매핑 등록
								oproCommPopupDao.mergeIntoEstmObjScr(parameterMap);
								oproCommPopupDao.insertEstmObjScrHist(parameterMap);	
							}
					
					}else if(P_ESTM_DTL_ITEM_NO instanceof String[]){
						System.err.println("@@@ String[] @@@");
						
						//평가항목번호
						String[] P_ESTM_DTL_ITEM_NOList = (String[]) P_ESTM_DTL_ITEM_NO;
						//평가점수
						String[] P_ESTM_DTL_ITEM_POINTList = (String[]) P_ESTM_DTL_ITEM_POINT;
						
						for (int idx = 0; idx < P_ESTM_DTL_ITEM_NOList.length; idx++) {
							parameterMap.put("P_ESTM_ITEM_NO", P_ESTM_DTL_ITEM_NOList[idx]);
							parameterMap.put("P_ESTM_SCR", P_ESTM_DTL_ITEM_POINTList[idx]);

							// 평가위원분야매핑 등록
							oproCommPopupDao.mergeIntoEstmObjScr(parameterMap);
							oproCommPopupDao.insertEstmObjScrHist(parameterMap);
						}
					}
					
				}//P_ESTM_CMTM_NO != null
				
			}//parameterMap.get("P_ESTM_FRM_NO") != null
		
		}//parameterMap.get("P_ESTM_SPHE_SECD")
		
		
		// 평가위원평가대상점수 merge Into
		oproCommPopupDao.mergeIntoEstmCmtmObjScr(parameterMap);
		
		//trans.put("P_ESTM_SPHE_SECD", parameterMap.get("P_ESTM_SPHE_SECD"));
		
		return trans;
		
	}

	@Override
	public FwkTransferObject estmCmtmEstmFrmSave(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		/**
		 * 평가위원 점수저장
		 * 1. 평가대상점수 : T_ESTM_OBJ_SCR 저장
		 * 2. 평가위원평가대상점수 : T_ESTM_CMTM_OBJ_SCR 저장
		 * 
		 */
		parameterMap.put("P_DEL_AT", "N");
		
		if(parameterMap.get("P_ESTM_NO") != null) {

			// 평가위원분야매핑 등록
			if(parameterMap.get("P_ESTM_CMTM_NO") != null) {
				//평가항목번호
				Object P_ESTM_DTL_ITEM_NO = parameterMap.get("P_ESTM_DTL_ITEM_NO");
				//평가점수
				Object P_ESTM_DTL_ITEM_POINT = parameterMap.get("P_ESTM_DTL_ITEM_POINT");
				
				if(P_ESTM_DTL_ITEM_NO != null){
					
					if(P_ESTM_DTL_ITEM_NO instanceof String){
							// 평가위원분야매핑 등록
							//parameterMap.put("P_ESTM_CMTM_NO", P_ESTM_DTL_ITEM_NOList.get(i));
						parameterMap.put("P_ESTM_ITEM_NO", parameterMap.get("P_ESTM_DTL_ITEM_NO"));
							parameterMap.put("P_ESTM_SCR", parameterMap.get("P_ESTM_DTL_ITEM_POINT"));
							oproCommPopupDao.mergeIntoEstmObjScr(parameterMap);
							oproCommPopupDao.insertEstmObjScrHist(parameterMap);
//							iproEstmCmtmSpheMpgDao.insertCmtmSpheMpgItemRegist(parameterMap);   // T_ESTM_FRM_ITEM INSERT
					}else if(P_ESTM_DTL_ITEM_NO instanceof ArrayList){
					
						//평가항목번호
						ArrayList<String> P_ESTM_DTL_ITEM_NOList = (ArrayList<String>) P_ESTM_DTL_ITEM_NO;
						//평가점수
						ArrayList<String> P_ESTM_DTL_ITEM_POINTList = (ArrayList<String>) P_ESTM_DTL_ITEM_POINT;
						
							for(int i =1; i < P_ESTM_DTL_ITEM_NOList.size(); i++){
								
								parameterMap.put("P_ESTM_ITEM_NO", P_ESTM_DTL_ITEM_NOList.get(i));
								parameterMap.put("P_ESTM_SCR", P_ESTM_DTL_ITEM_POINTList.get(i));
								
								// 평가위원분야매핑 등록
								oproCommPopupDao.mergeIntoEstmObjScr(parameterMap);
								oproCommPopupDao.insertEstmObjScrHist(parameterMap);
									
							}
					
					}else if(P_ESTM_DTL_ITEM_NO instanceof String[]){
						
						//평가항목번호
						String[] P_ESTM_DTL_ITEM_NOList = (String[]) P_ESTM_DTL_ITEM_NO;
						//평가점수
						String[] P_ESTM_DTL_ITEM_POINTList = (String[]) P_ESTM_DTL_ITEM_POINT;
						
						for (int idx = 0; idx < P_ESTM_DTL_ITEM_NOList.length; idx++) {
							parameterMap.put("P_ESTM_ITEM_NO", P_ESTM_DTL_ITEM_NOList[idx]);
							parameterMap.put("P_ESTM_SCR", P_ESTM_DTL_ITEM_POINTList[idx]);
							// 평가위원분야매핑 등록
							oproCommPopupDao.mergeIntoEstmObjScr(parameterMap);
							oproCommPopupDao.insertEstmObjScrHist(parameterMap);
						}
					}
					
				}//P_ESTM_CMTM_NO != null
				
			}//parameterMap.get("P_ESTM_FRM_NO") != null
		
		}//parameterMap.get("P_ESTM_SPHE_SECD")
		
		
		// 평가위원평가대상점수 merge Into
		oproCommPopupDao.mergeIntoEstmCmtmObjScr(parameterMap);
		
		//trans.put("P_ESTM_SPHE_SECD", parameterMap.get("P_ESTM_SPHE_SECD"));
		
		return trans;
	}
	
	public FwkTransferObject estmObjFileView(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		trans.put("estmObjFileView", oproCommPopupDao.selectEstmObjFileView(parameterMap));
		return trans;
	}
	
	@Override
	public FwkTransferObject cmtmMngCrtrDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity estmCmtmCrtrMstDetail = oproCommPopupDao.selectEstmCmtmCrtrMstDetail(parameterMap);   // T_ESTM_CMTM_POOL_MST
		trans.put("estmCmtmCrtrMstDetail", estmCmtmCrtrMstDetail);
		
		return trans;
	}
	
	
	@Override
	public FwkTransferObject estmObjImstarsMainView(final FwkParameterMap parameterMap) {
		// 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 기업정보
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity estmObjImstarsMainDetail = oproCommPopupDao.selectEstmObjImstarsMainDetail(parameterMap);   // T_ESTM_CMTM_POOL_MST
		trans.put("estmObjImstarsMainDetail", estmObjImstarsMainDetail);

		List<FwkDataEntity> estmObjImstarsCmpnySelng = oproCommPopupDao.selectEstmObjImstarsCmpnySelng(parameterMap);   // T_ESTM_CMTM_POOL_MST
		trans.put("estmObjImstarsCmpnySelng", estmObjImstarsCmpnySelng);
		
		List<FwkDataEntity> cmpnyFileList = null;
		if(estmObjImstarsMainDetail != null){
			String ATCH_FILE_ID = estmObjImstarsMainDetail.getString("ATCH_FILE_ID");   //  첨부 파일 아이디

			if(!"".equals(ATCH_FILE_ID)){
				parameterMap.put("P_FILE_ID", ATCH_FILE_ID);
				cmpnyFileList = oproCommPopupDao.selectEstmObjImstarsCmpnyFileView(parameterMap);   // SPORT_REQST_FILEDETAILINFO
			}
		}
		trans.put("cmpnyFileList", cmpnyFileList);
		
		return trans;
	}


	@Override
	public FwkTransferObject estmObjImstarsDetailView(final FwkParameterMap parameterMap) {
		// 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 상세내용
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity goodsDetail = oproCommPopupDao.selectEstmObjImstarsDetailView(parameterMap);   // V_ESTM_ANNC_TPI_INT2
		trans.put("goodsDetail", goodsDetail);
		
		List<FwkDataEntity> fileDetailList = null;
		
		if(goodsDetail != null){
			String IMG_DETAIL_FILE_ID = goodsDetail.getString("IMAGEFILEID");   //  이미지 파일 아이디
			
			parameterMap.put("P_FILE_ID", IMG_DETAIL_FILE_ID);
			fileDetailList = oproCommPopupDao.selectEstmObjImstarsDetailFileList(parameterMap);   // CMMN_FILEDETAILINFO
		}
		trans.put("fileDetailList", fileDetailList);
		
		// 상품인증
		List<FwkDataEntity> goodsCrtfcList = oproCommPopupDao.selectEstmObjImstarsGoodsCrtfcList(parameterMap);   // GOODS_CRTFC
		trans.put("goodsCrtfcList", goodsCrtfcList);
		
		// 상품매출액
		List<FwkDataEntity> goodsSelngList = oproCommPopupDao.selectEstmObjImstarsGoodssSelngList(parameterMap);   // GOODS_SELNG
		trans.put("goodsSelngList", goodsSelngList);
		
		return trans;
	}


	@Override
	public FwkTransferObject estmObjImstarsFileView(final FwkParameterMap parameterMap) {
		// 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 첨부파일
		FwkTransferObject trans = new FwkTransferObject();
		List<FwkDataEntity> fileDetailInfo =  null;
		
		if(parameterMap.get("P_REQST_NO") != null && !"".equals(parameterMap.get("P_REQST_NO"))){
			fileDetailInfo = oproCommPopupDao.selectEstmObjImstarsFileView(parameterMap);   // SPORT_REQST_FILEDETAILINFO			
		}
		
		trans.put("fileDetailInfo", fileDetailInfo);
		
		return trans;
	}
	
	
	@Override
	public FwkTransferObject estmCmtmSvyDetail(final FwkParameterMap parameterMap) {
		// 평가완료현황 - 상세 : 설문조사 팝업
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult"); 
		parameterMap.put("P_ESTM_CMTM_NO", session.get("USR_ID"));
		
		// 평가관리 마스터 조회
		FwkDataEntity estmMngMstDetail = (FwkDataEntity) oproEstmCmtmProgDao.selectEstmMngMstDetail(parameterMap);   // T_ESTM_MNG_MST
		trans.put("estmMngMstDetail", estmMngMstDetail);
		
		trans.put("estmCmtmSvyFrmItemList", iproCommPopupDao.selectEstmCmtmSvyFrmItemList(parameterMap));
		
		return trans;
	}


	@Override
	public FwkTransferObject estmCmtmSvySave(final FwkParameterMap parameterMap) {
		// TODO Auto-generated method stub
		FwkTransferObject trans = new FwkTransferObject();

		// 저장 : T_ESTM_SVY_SCR
		parameterMap.put("P_DEL_AT", "N");
		
				
		//평가항목번호
		Object P_ESTM_DTL_ITEM_NO = parameterMap.get("P_ESTM_DTL_ITEM_NO");
		
		//평가점수
		Object P_ESTM_DTL_ITEM_POINT = parameterMap.get("P_ESTM_DTL_ITEM_POINT");
		
		if(P_ESTM_DTL_ITEM_NO != null){
			
			if(P_ESTM_DTL_ITEM_NO instanceof String){
				
				System.err.println("@@@ String @@@");
				
					// 평가설문조사점수 등록
					parameterMap.put("P_ESTM_ITEM_NO", parameterMap.get("P_ESTM_DTL_ITEM_NO"));
					parameterMap.put("P_ESTM_SCR", parameterMap.get("P_ESTM_DTL_ITEM_POINT"));
					iproCommPopupDao.mergeIntoEstmSvyScr(parameterMap);   // T_ESTM_SVY_SCR
					
			}else if(P_ESTM_DTL_ITEM_NO instanceof ArrayList){
			
				System.err.println("@@@ ArrayList @@@");
				
				//평가항목번호
				ArrayList<String> P_ESTM_DTL_ITEM_NOList = (ArrayList<String>) P_ESTM_DTL_ITEM_NO;
				//평가점수
				ArrayList<String> P_ESTM_DTL_ITEM_POINTList = (ArrayList<String>) P_ESTM_DTL_ITEM_POINT;
				
					for(int i =1; i < P_ESTM_DTL_ITEM_NOList.size(); i++){
						
						parameterMap.put("P_ESTM_ITEM_NO", P_ESTM_DTL_ITEM_NOList.get(i));
						parameterMap.put("P_ESTM_SCR", P_ESTM_DTL_ITEM_POINTList.get(i));
						
						// 평가설문조사점수 등록
						iproCommPopupDao.mergeIntoEstmSvyScr(parameterMap);   // T_ESTM_SVY_SCR
						
					}
			
			}else if(P_ESTM_DTL_ITEM_NO instanceof String[]){
				System.err.println("@@@ String[] @@@");
				
				//평가항목번호
				String[] P_ESTM_DTL_ITEM_NOList = (String[]) P_ESTM_DTL_ITEM_NO;
				//평가점수
				String[] P_ESTM_DTL_ITEM_POINTList = (String[]) P_ESTM_DTL_ITEM_POINT;
				
				for (int idx = 0; idx < P_ESTM_DTL_ITEM_NOList.length; idx++) {
					parameterMap.put("P_ESTM_ITEM_NO", P_ESTM_DTL_ITEM_NOList[idx]);
					parameterMap.put("P_ESTM_SCR", P_ESTM_DTL_ITEM_POINTList[idx]);

					// 평가설문조사점수 등록
					iproCommPopupDao.mergeIntoEstmSvyScr(parameterMap);   // T_ESTM_SVY_SCR
				}
			}
			
		}//P_ESTM_CMTM_NO != null
		
		return trans;
	}
}
