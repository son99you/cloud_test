package com.eunwoosoft.opro.ebid.service.impl; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

















import org.apache.ibatis.annotations.Result;
import org.apache.poi.ss.formula.functions.Replace;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.util.CmmnUtil;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.dto.simple.FwkSimpleDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproEbidPlanService;
import com.eunwoosoft.opro.ebid.dao.OproEbidMyProperJdgmnDao;
import com.eunwoosoft.opro.ebid.dao.OproEbidPblancDao;
import com.eunwoosoft.opro.ebid.service.OproEbidMyNtatDspthService;
import com.eunwoosoft.opro.ebid.service.OproEbidMyProperJdgmnService;


/**
 * 나의 적격심사 서비스 구현 클래스
 * 
 * @author : 은우소프트 손연우
 * @date : 2015. 03. 24.
 * @version : 1.0
 */
@Service("oproEbidMyProperJdgmnService")
public class OproEbidMyProperJdgmnServiceImpl extends AbstractFwkService implements OproEbidMyProperJdgmnService {
	
	@Resource(name="oproEbidMyProperJdgmnDao")
	private OproEbidMyProperJdgmnDao oproEbidMyProperJdgmnDao;
	
	@Resource(name="oproEbidPblancDao")
	private OproEbidPblancDao oproEbidPblancDao;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;
	

	/**
	 * <pre>
	 * 1. 개요 : 나의 적격심사 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myProperJdgmnListInqireWithPgng
	 * @date : 2015. 03. 23.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject myProperJdgmnListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("P_ANNC_STDT_S") != null && !"".equals(parameterMap.get("P_ANNC_STDT_S"))){ // 공고일자 시작
			parameterMap.put("P_ANNC_STDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_STDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_ANNC_ENDT_S") != null && !"".equals(parameterMap.get("P_ANNC_ENDT_S"))){ // 공고일자 종료
			parameterMap.put("P_ANNC_ENDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_ANNC_ENDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		
		trans.put(OproEbidMyProperJdgmnService.MY_PROPER_JDGMN_LIST, 
				oproEbidMyProperJdgmnDao.selectMyProperJdgmnListWithPgng(parameterMap));
		trans.put(OproEbidMyProperJdgmnService.MY_PROPER_JDGMN_LIST_TOTCNT, 
				oproEbidMyProperJdgmnDao.selectMyProperJdgmnListTotcnt(parameterMap));
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 적격심사 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myProperJdgmnDetailInqire
	 * @date : 2015. 2. 23.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject myProperJdgmnDetailInqire(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		
		FwkDataEntity dataEntity = (FwkDataEntity) oproEbidMyProperJdgmnDao.selectMyProperJdgmnDetail(parameterMap);
		
		trans.put(OproEbidMyProperJdgmnService.MY_PROPER_JDGMN_DETAIL ,dataEntity);
		
		if(dataEntity != null){
			if(!"".equals(dataEntity.getString("FILE_GRP_NO"))){
				parameterMap.put("P_FILE_GRP_NO", dataEntity.getString("FILE_GRP_NO"));
				trans.put(IproEbidPlanService.BID_ATCH_DOC_LIST, 
						comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
			}
		}
		
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 적격심사 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myProperJdgmnRegist
	 * @date : 2015. 3. 24.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 24.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see OproEbidMyProperJdgmnService.OepElbiMyProperJdgmnService.elbi.service.OepElbiMyProperJdgmnService#myProperJdgmnRegist(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject myProperJdgmnRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		@SuppressWarnings("unchecked")
		FwkDataEntity user = new FwkSimpleDataEntity((Map<String,Object>)parameterMap.get("loginResult"));
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 세션 업체등록번호
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)parameterMap.get("multipartRequest");
		String contextPath = "bid";
		FwkParameterMap fileParameterMap =  CmmnUtil.fileMultiUpload(multipartRequest,"P_FILE", contextPath);
		
		String delFileSn = parameterMap.getString("P_DEL_SN");
		if(!"".equals(delFileSn)){
			String[] snArray = delFileSn.split(",");
			List<Map<String, Object>> delList = new ArrayList<Map<String,Object>>();
			for (int i = 0; i < snArray.length; i++) {
				FwkParameterMap delMap = new FwkParameterMap();
				delMap.put("P_FILE_SN", snArray[i]);
				delList.add(delMap);
			}
			parameterMap.put("fileList", delList);
			comAtmaAtchFileDao.deleteAtchFileDelete(parameterMap);
		}
		
		if(fileParameterMap != null){
			String atchFileGroupNo = ""; 
			if("".equals(parameterMap.getString("P_FILE_GRP_NO"))){
				atchFileGroupNo = fileParameterMap.getString("atchFileGroupNo");
			}else{
				atchFileGroupNo = parameterMap.getString("P_FILE_GRP_NO");	
			}
			List<Map<String, Object>> list = (List<Map<String, Object>>)fileParameterMap.get("list");
				
			// 공통 파일정보 저장
			parameterMap.put("atchFileGroupNo", atchFileGroupNo);
			parameterMap.put("fileList", list);
			parameterMap.put("P_SYSDATE", FwkDateUtil.getCurrentDateTimeAsString());
			comAtmaAtchFileDao.insertAtchFileRegist(parameterMap);
			
			if("".equals(parameterMap.getString("P_FILE_GRP_NO"))){
				parameterMap.put("P_FILE_GRP_NO", atchFileGroupNo);
				// 첨부파일 저장
				parameterMap.put("P_BID_TPI_SECD", "OP04"); // 입찰참여구분코드 (OP01 입찰참여신청, OP02 입찰포기신청, OP03 투찰, OP04 자가점수입력)
				parameterMap.put("P_BID_SBMT_FSCD", "DO06"); //적격심사
				oproEbidPblancDao.insertBidPareFileRegist(parameterMap);
			}
			
		}
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 적격심사 점수 통보
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myProperJdgmnScoreDspth
	 * @date : 2015. 3. 24.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 24.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see OproEbidMyProperJdgmnService.OepElbiMyProperJdgmnService.elbi.service.OepElbiMyProperJdgmnService#myProperJdgmnScoreDspth(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject myProperJdgmnScoreDspth(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("P_ENTRPS_EVL_SCORE") instanceof String){
			List<String>  evlSn = new ArrayList();
			evlSn = (ArrayList<String>)parameterMap.get("P_EVL_SN");
			parameterMap.put("P_EVL_SN", evlSn.get(0));
			oproEbidMyProperJdgmnDao.updateProperJdgmnEvlRegist(parameterMap);
		}else{
			List<String>  evlSn = new ArrayList();
			evlSn = (ArrayList<String>)parameterMap.get("P_EVL_SN");
			List<String> entrpsEvlScore = new ArrayList();
			entrpsEvlScore = (ArrayList<String>)parameterMap.get("P_ENTRPS_EVL_SCORE");
			
			for(int i = 0; i< entrpsEvlScore.size(); i++){
				parameterMap.put("P_EVL_SN", evlSn.get(i));
				parameterMap.put("P_ENTRPS_EVL_SCORE", entrpsEvlScore.get(i));
				oproEbidMyProperJdgmnDao.updateProperJdgmnEvlRegist(parameterMap);
			}
		}
		
		parameterMap.put("P_ENTRPS_PRST_CD", "OP07");
		
		oproEbidMyProperJdgmnDao.updateEntrpsPartcptnPrstUpdt(parameterMap);
		
		List<FwkDataEntity> evlList = oproEbidMyProperJdgmnDao.selectEntrpsJdgmnEvlList(parameterMap);
		
		StringBuffer sb = new StringBuffer();
		
		int koicaEvlTot =0;
		
		for(int i = 0; i < evlList.size(); i++)
		{
			koicaEvlTot = (int) (koicaEvlTot + evlList.get(i).getDouble("KOICA_EVL_SCORE")); 
		
			//null인경우 빈칸으로
			if(evlList.get(i).get("EVL_CN") == null || evlList.get(i).get("EVL_CN") == "")				{					evlList.get(i).put("EVL_CN","");				}
			if(evlList.get(i).get("EVL_SN") == null || evlList.get(i).get("EVL_SN") == "")				{					evlList.get(i).put("EVL_SN","");				}
			if(evlList.get(i).get("ALLOT_LMT_CN") == null || evlList.get(i).get("ALLOT_LMT_CN") == "")				{					evlList.get(i).put("ALLOT_LMT_CN","");				}
			if(evlList.get(i).get("ENTRPS_EVL_SCORE") == null || evlList.get(i).get("ENTRPS_EVL_SCORE") == "")				{					evlList.get(i).put("ENTRPS_EVL_SCORE","");				}
			if(evlList.get(i).get("KOICA_EVL_SCORE") == null || evlList.get(i).get("KOICA_EVL_SCORE") == "")				{					evlList.get(i).put("KOICA_EVL_SCORE","");				}
			
			sb.append("<tr class='row'>");
			sb.append("<td>" + evlList.get(i).get("RNUM") + "</td>");
			sb.append("<td>" + evlList.get(i).get("EVL_CN")+ 
							"<input type='hidden' name='P_EVL_SN' value="+ evlList.get(i).get("EVL_SN") +" /></td>");
			sb.append("<td>" + evlList.get(i).get("ALLOT_LMT_CN")+ 
							"<input type='hidden' id='allotLmtScore["+ i +"]' value="+ evlList.get(i).get("ALLOT_LMT_CN") +" /></td>");
			sb.append("<td>");
			if("입찰가격".equals(evlList.get(i).get("EVL_CN")))
			{
				sb.append("<input type=\"text\" readonly=\"readonly\" class=\"disabled\" style=\"text-align: center;\" id=\"entrpsEvlScore_"+i+"\" name=\"P_ENTRPS_EVL_SCORE_BID_PC\" onchange=\"entrpsEvlSm();\" value=\""+ evlList.get(i).get("ENTRPS_EVL_SCORE") +"\">");
			}
			if( "신인도".equals(evlList.get(i).get("EVL_CN")) || "결격사유".equals(evlList.get(i).get("EVL_CN")))
			{
				sb.append("&nbsp;");
			}else if(!"신인도".equals(evlList.get(i).get("EVL_CN")) && !"입찰가격".equals(evlList.get(i).get("EVL_CN")) && !"결격사유".equals(evlList.get(i).get("EVL_CN")))
			{
				sb.append("<input type='text' style='text-align: center;' id='entrpsEvlScore_"+i+"' name='P_ENTRPS_EVL_SCORE' onchange='entrpsEvlSm();' value="+ evlList.get(i).get("ENTRPS_EVL_SCORE") +" />");
			}
			sb.append("</td>");
			
			if(evlList.get(i).get("KOICA_EVL_SCORE") == null)
			{
				sb.append("&nbsp;");
			}else
			{
				sb.append("<td>"+ evlList.get(i).get("KOICA_EVL_SCORE") +"&nbsp;</td>");
				
			}
			
			
		}
		
		
		sb.append("<tr class='row'>");
		sb.append("<td colspan='2'>계</td>");
		sb.append("<td><input type='text' style='border:0px; text-align: center;' readonly='readonly' class='disabled' name='allotlmtTot' value='100'/></td>");
		sb.append("<td><input type='text' style='border:0px; text-align: center;' readonly='readonly' class='disabled' name='entrpsEvlTot'/></td>");
		sb.append("<td><input type='text' style='border:0px; text-align: center;' readonly='readonly' class='disabled' name='koicaEvlTot' value="+ koicaEvlTot +" /></td>");
		sb.append("</tr>");
		
		trans.put(OproEbidMyProperJdgmnService.ENTRPS_JDGMN_EVL_LIST, sb.toString());
		
		return trans;
	}
	
	
}