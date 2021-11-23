package com.eunwoosoft.ipro.ebid.service.impl; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.dao.IproEbidSttlmntDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidSttlmntService;

/**
 * 입찰계획 서비스 구현 클래스
 * 
 * @author : 은우소프트 하성윤
 * @date : 2015. 01. 14.
 * @version : 1.0
 */
@Service("iproEbidSttlmntService")
public class IproEbidSttlmntServiceImpl extends AbstractFwkService implements IproEbidSttlmntService {
	private static final Logger LOG = LoggerFactory.getLogger(IproEbidPlanServiceImpl.class);
	
	@Resource(name="iproEbidSttlmntDao")
	private IproEbidSttlmntDao iproEbidSttlmntDao;
	
	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao; 
	
	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;  
	
	/**
	 * <pre>
	 * 1. 개요 :수의시담 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlListInqireWithPgng
	 * @date : 2018. 3. 19. 
	 * @author : chanil_Hong
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject vltrnPrvstlListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		trans.put(IproEbidSttlmntService.VLTRN_PRVSTL_LIST, iproEbidSttlmntDao.selectVltrnPrvstlListInqireWithPgng(parameterMap));
		trans.put(IproEbidSttlmntService.VLTRN_PRVSTL_LIST_TOTCNT, iproEbidSttlmntDao.selectVltrnPrvstlListTotcnt(parameterMap));
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 :수의시담 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlDetail
	 * @date : 2018. 3. 19. 
	 * @author : chanil_Hong
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject vltrnPrvstlDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkDataEntity dataEntity = (FwkDataEntity) iproEbidSttlmntDao.selectVltrnPrvstlDetail(parameterMap);
		
		/*String PVST_DT = (String) dataEntity.get("PVST_DT"); 
		
		dataEntity.put("PVST_DT", PVST_DT.substring(0, 8));
		dataEntity.put("PVST_DT_HH", PVST_DT.substring(8, 10)); 
		dataEntity.put("PVST_DT_mm", PVST_DT.substring(10, 12)); */
		if(dataEntity != null){ 
			parameterMap.put("P_FILE_GRP_NO", dataEntity.get("FILE_GRP_NO")); 
			trans.put(ComAtmaAtchFileService.ATCH_FILE_LIST, comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		trans.put(IproEbidSttlmntService.VLTRN_PRVSTL_DETAIL ,dataEntity);
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 :수의시담 완료견적 작성폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlRequstRegistForm
	 * @date : 2018. 3. 19. 
	 * @author : chanil_Hong
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject vltrnPrvstlRequstRegistForm(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproEbidSttlmntService.SELECT_TEPN_ESREMA_DETAIL ,iproEbidSttlmntDao.selectTepnEsremaDetail(parameterMap));
		
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 :수의시담 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlRegist
	 * @date : 2018. 3. 19. 
	 * @author : chanil_Hong
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject vltrnPrvstlRegist(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		String atchFileGroupNo = parameterMap.getString("atchFileGroupNo");
		
		List<Map<String, Object>> fileList = null;
		if(parameterMap.get("fileList") != null){
			fileList = (List<Map<String, Object>>) parameterMap.get("fileList");
		}
		
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
		if(fileList != null){
			parameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
			if(fileList != null){
				if(fileList.size() > 0 ){
					comAtmaAtchFileService.atchFileMapRegist(atchFileGroupNo, fileList , parameterMap);
				}
			} 
		}
		/*=========================첨부파일 등록 END==========================*/
		
		String pPrvstlDt = (String) parameterMap.get("P_PVST_DT");
		pPrvstlDt = pPrvstlDt + (String) parameterMap.get("P_PVST_DT_HH");
		pPrvstlDt = pPrvstlDt + parameterMap.get("P_PVST_DT_mm");
		
		parameterMap.put("P_PVST_DT", pPrvstlDt);
/*		parameterMap.put("P_REGIST_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_UPDT_DT", FwkDateUtil.getCurrentDateTimeAsString());
*/		
		// 수기 등록시 사업번호 생성 ( 해당년도 - 0000 )
	/*	if(parameterMap.get("P_BSNS_NO") == null || "".equals(parameterMap.get("P_BSNS_NO"))){
			parameterMap.put("P_BSNS_NO", "B"+FwkDateUtil.getCurrentDateAsString().substring(0,4) + "-00000000");
		}																																  

*/		// 수기 등록시 조달의뢰번호 생성 ( H - 해당년도 - 0000 )
		if(parameterMap.get("P_PRCH_RQR_NO") == null || "".equals(parameterMap.get("P_PRCH_RQR_NO"))){
			parameterMap.put("P_PRCH_RQR_NO", "H"+FwkDateUtil.getCurrentDateAsString().substring(0,4) + "-0000");
		}
		parameterMap.put("P_RQST_DE", parameterMap.get("P_RQST_DE").toString().replaceAll("-", ""));
		parameterMap.put("P_PVST_DT", parameterMap.get("P_PVST_DT").toString().replaceAll("-", ""));
			 
		parameterMap.put("P_RMK","");
		iproEbidSttlmntDao.insertVltrnPrvstlNewRegist(parameterMap); //수의시담 마스터 등록
		iproEbidSttlmntDao.insertVltrnPrvstlEntrpsRegist(parameterMap); //수의시담 업체 등록
		//parameterMap.put("P_STTUS_DE", FwkDateUtil.getCurrentDateAsString());
		iproEbidSttlmntDao.insertVltrnPrvstlHistRegist(parameterMap); //수의시담 진행이력 등록
			
		//iproEbidSttlmntDao.insertVltrnPrvstlFileRegist(parameterMap);//수의시담 첨부파일그룹 등록
		parameterMap.put("REFRESH", "Y"); 
		
		return trans;
	}
	/**
	 * <pre>
	 * 1. 개요 :수의시담 수정
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlModify
	 * @date : 2018. 3. 19. 
	 * @author : chanil_Hong
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject vltrnPrvstlModify(final FwkParameterMap parameterMap){
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		String atchFileGroupNo = parameterMap.getString("atchFileGroupNo");
		
		List<Map<String, Object>> fileList = null;
		if(parameterMap.get("fileList") != null){
			fileList = (List<Map<String, Object>>) parameterMap.get("fileList");
		}
		
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
			if(fileList != null){
				parameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
				if(fileList != null){
					if(fileList.size() > 0 ){
						parameterMap.put("atchFileGroupNo", atchFileGroupNo);
						parameterMap.put("fileList", fileList);
						parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
						//parameterMap.put("USR_ID", parameterMap.getString("USR_ID"));
						
						comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
					}
				}
			}
		//=================================파일업로드 END==============================================
		
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
		
		
		String pPrvstlDt = (String) parameterMap.get("P_PVST_DT");
		pPrvstlDt = pPrvstlDt + (String) parameterMap.get("P_PVST_DT_HH");
		pPrvstlDt = pPrvstlDt + parameterMap.get("P_PVST_DT_mm");
		
		parameterMap.put("P_PVST_DT", pPrvstlDt);
		
		if(parameterMap.get("P_BID_LMT_AMT") == null)
		{
			parameterMap.put("P_BID_LMT_AMT", "0");
		}
		
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_MOD_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_PVST_DT", pPrvstlDt);
		parameterMap.put("P_RQST_DE", parameterMap.get("P_RQST_DE").toString().replaceAll("-", ""));
		parameterMap.put("P_PVST_DT", parameterMap.get("P_PVST_DT").toString().replaceAll("-", ""));
		
		iproEbidSttlmntDao.updateVltrnPrvstlUpdt(parameterMap); //수의시담 마스터 수정
		
		iproEbidSttlmntDao.deleteVltrnPrvstlEntrps(parameterMap); //수의시담 업체 삭제
		iproEbidSttlmntDao.insertVltrnPrvstlEntrpsRegist(parameterMap); //수의시담 업체 등록
		
		parameterMap.put("P_STTUS_DE", FwkDateUtil.getCurrentDateAsString());
		parameterMap.put("P_RM_CN","");
		
		iproEbidSttlmntDao.insertVltrnPrvstlHistRegist(parameterMap); //수의시담 진행이력 등록
		parameterMap.put("REFRESH", "Y");
		return trans; 
		
	}
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 시담 요청
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prvstlRequst
	 * @date : 2018. 3. 19. 
	 * @author : chanil_Hong
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject prvstlRequst(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		parameterMap.put("P_RQST_DE", parameterMap.get("P_RQST_DE").toString().replaceAll("-", ""));
		parameterMap.put("P_PVST_DT", parameterMap.get("P_PVST_DT").toString().replaceAll("-", ""));
		
		//수의시담 진행상태 수정
		iproEbidSttlmntDao.updateVltrnPrvstlPrstUpdt(parameterMap);

		
		//나중에 수정하기 P_WRTER_ID
		//parameterMap.put("P_WRTER_ID", (String) parameterMap.get("P_USR_ID"));
		//parameterMap.put("P_STTUS_DE", FwkDateUtil.getCurrentDateAsString());
		
		//parameterMap.put("P_RMk","");
		//parameterMap.put("P_VEND_REG_NO", ""); 
		 
		//수의시담 진행이력 등록
		iproEbidSttlmntDao.insertVltrnPrvstlHistRegist(parameterMap);
			
		return trans;
	}
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 상세 - 시담진행
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlProgrsDetail
	 * @date : 2018. 3. 19. 
	 * @author : chanil_Hong
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject vltrnPrvstlProgrsDetail(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		
		trans.put(IproEbidSttlmntService.VLTRN_PRVSTL_PROGRS_LIST,iproEbidSttlmntDao.selectVltrnPrvstlProgrsList(parameterMap));
		
		FwkDataEntity dataEntity = (FwkDataEntity) iproEbidSttlmntDao.selectVltrnPrvstlDetail(parameterMap);
		
		if(dataEntity != null){
			parameterMap.put("P_FILE_GRP_NO", dataEntity.get("FILE_GRP_NO"));
			trans.put(ComAtmaAtchFileService.ATCH_FILE_LIST, comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		trans.put(IproEbidSttlmntService.VLTRN_PRVSTL_DETAIL ,dataEntity);
		return trans;
	}
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 상세 - 내용등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlCnRegist
	 * @date : 2018. 3. 19. 
	 * @author : chanil_Hong
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject vltrnPrvstlCnRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		//parameterMap.put("P_ENTRPS_CHARGER_ID", "");
		parameterMap.put("P_CNTN_INP_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
		iproEbidSttlmntDao.insertVltrnPrvstlCnRegist(parameterMap); //시담 내용 등록
		
		//시담내용
		List<FwkDataEntity> progrsList = iproEbidSttlmntDao.selectVltrnPrvstlProgrsList(parameterMap);
				
		/*
		 *  HTML 형태로 생성
		 */
		StringBuffer sb = new StringBuffer();
		sb.append("<table border='0' cellspacing='0' cellpadding='0' style='overflow-y: scroll; height: 150px;  border-bottom: 0px;' >");
		//<table border="0" cellspacing="0" cellpadding="0" style="overflow-y: scroll;" >
		for(int i= 0; i < progrsList.size(); i++)
		{
			
			//null인경우 빈칸으로
			if(progrsList.get(i).get("PVST_CNTN") == null || "".equals(progrsList.get(i).get("PVST_CNTN")))				{					progrsList.get(i).put("PVST_CNTN","");				}
			if(progrsList.get(i).get("CNTN_INP_DT") == null || "".equals(progrsList.get(i).get("CNTN_INP_DT")))				{					progrsList.get(i).put("CNTN_INP_DT","");				}
			if(progrsList.get(i).get("CNTN_INP_DT") == null || "".equals(progrsList.get(i).get("CNTN_INP_DT")))				{					progrsList.get(i).put("CNTN_INP_DT","");				}
			
			sb.append("<tr>");
			//if("".equals(progrsList.get(i).get("ENTRPS_CHARGER_ID")) || progrsList.get(i).get("ENTRPS_CHARGER_ID") == null)
			//{
				sb.append("<td width='' style='text-align: left; border:0px; width:300px;'>" + progrsList.get(i).get("PVST_CNTN") + "<br>"+ FwkFormatUtil.formatDate((String) progrsList.get(i).get("CNTN_INP_DT"), "yyyyMMddHHmmss","yyyy-MM-dd HH:mm:ss") +"<br> </td>");
				sb.append("<td width='' style='text-align: right; border:0px;  width:300px;'>&nbsp;</td>");
				 
			//}else if("".equals(progrsList.get(i).get("INNER_CHARGER_ID")) || progrsList.get(i).get("INNER_CHARGER_ID") == null)
			//{
				//sb.append("<td width='300px' style='text-align: left; border:0px;'>&nbsp;</td>");
				//sb.append("<td width='300px' style='text-align: right; border:0px;'>"+ progrsList.get(i).get("PVST_CNTN") + "<br>"+ FwkFormatUtil.formatDate((String) progrsList.get(i).get("CNTN_INP_DT"), "yyyyMMddHHmmss","yyyy-MM-dd HH:mm:ss") +"<br> </td>");
			//}
			sb.append("</tr>"); 
			
		}
		sb.append("</table>");

		
		trans.put(IproEbidSttlmntService.VLTRN_PRVSTL_PROGRS_LIST, sb.toString());
		
		return trans;
	}
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prvstlDelete
	 * @date : 2018. 3. 19. 
	 * @author : chanil_Hong
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 3. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject prvstlDelete(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();

		
		//수의시담 삭제
		iproEbidSttlmntDao.deleteVltrnPrvstlUpdt(parameterMap);
		return trans;
	}
}	
