package com.eunwoosoft.ipro.ebid.service.impl; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPartcptSttusDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidPartcptSttusService;
import com.eunwoosoft.ipro.ebid.service.IproEbidSucbidrSlctnService;

/**
 * 입찰참가현황 서비스 구현 클래스
 * 
 * @author : 은우소프트 손연우
 * @date : 2015. 02. 13.
 * @version : 1.0
 */
@Service("iproEbidPartcptSttusService")
public class IproEbidPartcptSttusServiceImpl extends AbstractFwkService implements IproEbidPartcptSttusService {
	
	@Resource(name="iproEbidPartcptSttusDao")
	private IproEbidPartcptSttusDao iproEbidPartcptSttusDao;
	
	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;
	
	@Resource(name="comAtmaAtchFileDao")
    private ComAtmaAtchFileDao comAtmaAtchFileDao;

	/**
	 * <pre>
	 * 1. 개요 : 입찰참가현황 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidDcPeoListInqireWithPgng
	 * @date : 2015. 02. 13.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 13.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject bidPartcptSttusListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("P_BIDC_SBMT_ENDT_STDE_S") != null && !"".equals(parameterMap.get("P_BIDC_SBMT_ENDT_STDE_S"))){ // 투찰마감일자 시작
			parameterMap.put("P_BIDC_SBMT_ENDT_STDE_S", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_ENDT_STDE_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_BIDC_SBMT_ENDT_ENDE_S") != null && !"".equals(parameterMap.get("P_BIDC_SBMT_ENDT_ENDE_S"))){ // 투찰마감일자 종료
			parameterMap.put("P_BIDC_SBMT_ENDT_ENDE_S", FwkFormatUtil.formatDate(parameterMap.get("P_BIDC_SBMT_ENDT_ENDE_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
			trans.put(IproEbidPartcptSttusService.BID_PARTCPT_STTUS_LIST, iproEbidPartcptSttusDao.selectBidPartcptSttusListWithPgng(parameterMap));
			trans.put(IproEbidPartcptSttusService.BID_PARTCPT_STTUS_LIST_TOTCNT, iproEbidPartcptSttusDao.selectBidPartcptSttusListTotcnt(parameterMap));
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가현황 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptSttusDetailInqire
	 * @date : 2015. 2. 23.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.iep.elbi.service.IepElbiBidDcPeoService#bidDcPeoDetailInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject bidPartcptSttusDetailInqire(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity biMst = iproEbidPartcptSttusDao.selectBidInfoDetail(parameterMap);
		trans.put(IproEbidPartcptSttusService.BID_PARTCPT_STTUS_DETAIL, biMst);
		
		List<FwkDataEntity> dataEntity = null;
		parameterMap.put("P_BID_TPI_SECD", "OP03");
		dataEntity = (List<FwkDataEntity>) iproEbidPartcptSttusDao.selectBidPartcptEntrpsList(parameterMap);
		
		trans.put(IproEbidPartcptSttusService.BID_PARTCPT_ENTRPS_LIST ,dataEntity);
				
		return trans;
	}

	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰포기신청서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidAbandnReqstdocInqire
	 * @date : 2015. 2. 26.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 26.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see IproEbidPartcptSttusService.iep.elbi.service.IepElbiBidPartcptSttusService#bidAbandnReqstdocInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject bidAbandnReqstdocInqire(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproEbidPartcptSttusService.BID_ABANDN_REQSTDOC_INQIRE,
				iproEbidPartcptSttusDao.selectBidAbandnReqstdocInqire(parameterMap));
		
		return trans;
	}

	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가신청서, 제안서/규격서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptReqstdocInqire
	 * @date : 2019. 02. 21.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 21.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @see IproEbidPartcptSttusService.iep.elbi.service.IepElbiBidPartcptSttusService#bidPartcptReqstdocInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 * @throws SDBException 
	 */
	@Override
	public FwkTransferObject bidPartcptReqstdocInqire(final FwkParameterMap parameterMap){
		
		FwkTransferObject trans = new FwkTransferObject();
		
		
		FwkDataEntity dataEntity = (FwkDataEntity) iproEbidPartcptSttusDao.selectBidPartcptReqstdocInqire(parameterMap);
		
		trans.put(IproEbidPartcptSttusService.BID_PARTCPT_REQSTDOC_INQIRE ,dataEntity);
		
		if(dataEntity != null){
			dataEntity.put("P_FILE_GRP_NO", dataEntity.getString("FILE_GRP_NO"));
			trans.put(IproEbidPartcptSttusService.BID_PARTCPT_REQSTDOC_INQIRE ,dataEntity);
			//trans.put(IproEbidPlanService.BID_ATCH_DOC_LIST, comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 청렴이행각서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : intgtyFlflMmrdInqire
	 * @date : 2015. 2. 27.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 27.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see IproEbidPartcptSttusService.iep.elbi.service.IepElbiBidPartcptSttusService#intgtyFlflMmrdInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject intgtyFlflMmrdInqire(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproEbidPartcptSttusService.INTGTY_FLFL_MMRD_INQIRE,
				iproEbidPartcptSttusDao.selectIntgtyFlflMmrdInqire(parameterMap));
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰보증정보 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidAssrncInfoInqire
	 * @date : 2015. 3. 26.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 26.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see IproEbidPartcptSttusService.iep.elbi.service.IepElbiBidPartcptSttusService#bidAssrncInfoInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject bidAssrncInfoInqire(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		//입찰 마스터 정보
		FwkDataEntity fd = iproEbidPartcptSttusDao.selectBidAssrncInfoInqire(parameterMap);
		//보증서 정보
		List<FwkDataEntity> list = iproEbidPartcptSttusDao.selectEntrpsGrntyInfoList(parameterMap);
		for (FwkDataEntity fde : list) {
			if(fde.get("FILE_GRP_NO") != null && !fde.get("FILE_GRP_NO").equals("") ) {
				fd.put("FILE_GRP_NO", fde.get("FILE_GRP_NO"));
			}
		}
		trans.put(IproEbidPartcptSttusService.BID_ASSRNC_INFO_INQIRE, fd);
		trans.put(IproEbidPartcptSttusService.ENTRPS_GRNTY_INFO_LIST, list);
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰보증정보 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidAssrncInfoRegist
	 * @date : 2015. 3. 30.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 30.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see IproEbidSucbidrSlctnService.iep.elbi.service.IepElbiSucbidrSlctnService#bidAssrncInfoRegist(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject bidAssrncInfoRegist(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		Object ob = parameterMap.get("P_BIDGR_SECD");
		if(ob instanceof String){
			
			iproEbidPartcptSttusDao.deleteBidAssrncInfo(parameterMap);
			parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
			FwkParameterMap[] fileArray = (FwkParameterMap[])parameterMap.get("P_FILE_ARRAY");
//			if(parameterMap.get("P_GRNTY_PD_BEGIN_DE") != null && !"".equals(parameterMap.getString("P_GRNTY_PD_BEGIN_DE"))){
//				parameterMap.put("P_GRNTY_PD_BEGIN_DE", FwkFormatUtil.formatDate(parameterMap.getString("P_GRNTY_PD_BEGIN_DE"), "yyyy-MM-dd", "yyyyMMdd"));
//			}else{
//				parameterMap.put("P_GRNTY_PD_BEGIN_DE","");
//			}
//			if(parameterMap.get("P_GRNTY_PD_END_DE") != null && !"".equals(parameterMap.getString("P_GRNTY_PD_END_DE"))){
//				parameterMap.put("P_GRNTY_PD_END_DE", FwkFormatUtil.formatDate(parameterMap.getString("P_GRNTY_PD_END_DE"), "yyyy-MM-dd", "yyyyMMdd"));
//			}else{
//				parameterMap.put("P_GRNTY_PD_END_DE","");
//			}
			if(fileArray[0] != null){
				parameterMap.put("P_FILE_GRP_NO", fileArray[0].getString("P_FILE_GRP_NO"));
			}else if(parameterMap.get("P_FILE_GRP_NO") != null && !"".equals(parameterMap.getString("P_FILE_GRP_NO"))){
				parameterMap.put("P_FILE_GRP_NO", parameterMap.getString("P_FILE_GRP_NO"));
			}else{
				parameterMap.put("P_FILE_GRP_NO", "");
			}
			iproEbidPartcptSttusDao.insertBidAssrncInfoRegist(parameterMap);
			
		}else
		{
			String[] grntyPaymthdCd = (String[])parameterMap.get("P_BIDGR_SECD");
			String[] bidGrntyNo = (String[])parameterMap.get("P_BIDGR_NO");
			String[] bidGtnAm = (String[])parameterMap.get("P_BIDGR_AMT");
//			String[] issuInsttNm = (String[])parameterMap.get("P_ISSU_INSTT_NM");
//			String[] bidAssurStDe = (String[])parameterMap.get("P_GRNTY_PD_BEGIN_DE");
//			String[] bidAssurEndDe = (String[])parameterMap.get("P_GRNTY_PD_END_DE");
			String[] atchmnflGroupNo = (String[])parameterMap.get("P_FILE_GRP_NO");
			FwkParameterMap[] fileArray = (FwkParameterMap[])parameterMap.get("P_FILE_ARRAY");
			
			iproEbidPartcptSttusDao.deleteBidAssrncInfo(parameterMap);
			
			
			
			if(grntyPaymthdCd != null){
				for(int i = 0; i< grntyPaymthdCd.length; i++) {
					
					if(fileArray[i] != null){
						FwkParameterMap pmap = new FwkParameterMap();
						
						pmap.put("P_FILE_CPCTY", fileArray[i].getString("P_FILE_CPCTY"));
				        pmap.put("P_STRE_FILE_NM", fileArray[i].getString("P_STRE_FILE_NM"));
				        pmap.put("P_ATCHMNFL_NM", fileArray[i].getString("P_ATCHMNFL_NM"));	
				        pmap.put("P_ATCHMNFL_COURS_NM", fileArray[i].getString("P_ATCHMNFL_COURS_NM"));
				        pmap.put("P_ATCHMNFL_EXTSN_NM", fileArray[i].getString("P_ATCHMNFL_EXTSN_NM"));
						
				        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
						list.add(pmap);
						//comAtmaAtchFileService.atchFileRegist(fileArray[i].getString("P_FILE_GRP_NO"), list);
					}
					
					
					parameterMap.put("P_BIDGR_SECD", grntyPaymthdCd[i]);
					parameterMap.put("P_BIDGR_NO", bidGrntyNo[i]);
					parameterMap.put("P_BIDGR_AMT", bidGtnAm[i]);
//					parameterMap.put("P_ISSU_INSTT_NM", issuInsttNm[i]);
//					if(bidAssurStDe[i] != null && !"".equals(bidAssurStDe[i])){
//						parameterMap.put("P_GRNTY_PD_BEGIN_DE", FwkFormatUtil.formatDate(bidAssurStDe[i], "yyyy-MM-dd", "yyyyMMdd"));
//					}else{
//						parameterMap.put("P_GRNTY_PD_BEGIN_DE","");
//					}
//					if(bidAssurEndDe[i] != null && !"".equals(bidAssurEndDe[i])){
//						parameterMap.put("P_GRNTY_PD_END_DE", FwkFormatUtil.formatDate(bidAssurEndDe[i], "yyyy-MM-dd", "yyyyMMdd"));
//					}else{
//						parameterMap.put("P_GRNTY_PD_END_DE","");
//					}
					if(fileArray[i] != null){
						parameterMap.put("P_FILE_GRP_NO", fileArray[i].getString("P_FILE_GRP_NO"));
					}else if(atchmnflGroupNo[i] != null && !"".equals(atchmnflGroupNo[i])){
						parameterMap.put("P_FILE_GRP_NO", atchmnflGroupNo[i]);
					}else{
						parameterMap.put("P_FILE_GRP_NO", "");
					}
					parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
					iproEbidPartcptSttusDao.insertBidAssrncInfoRegist(parameterMap);
				}
			}
		}
		
		
		
//		List<FwkDataEntity> bidAssrncInfoList   = 	iproEbidPartcptSttusDao.selectEntrpsGrntyInfoList(parameterMap);
//		
//		StringBuffer sb = new StringBuffer();
//		
//		
//		for(int i = 0; i < bidAssrncInfoList.size(); i++)
//		{
//			
//			//null인경우 빈칸으로
//			if(bidAssrncInfoList.get(i).get("BIDGR_NO") == null || bidAssrncInfoList.get(i).get("BIDGR_NO") == "")				{					bidAssrncInfoList.get(i).put("BIDGR_NO","");				}
//			if(bidAssrncInfoList.get(i).get("BIDGR_AMT") == null || bidAssrncInfoList.get(i).get("BIDGR_AMT") == "")				{					bidAssrncInfoList.get(i).put("BIDGR_AMT","");				}
//			
//			
//			sb.append("<tr class='line'>");
//			sb.append("<td width='120px'>납부방법</td>");
//			sb.append("<td width='400px'>");
//			sb.append("<div id='radioRow["+i+"]'>");
//			if("290000".equals(bidAssrncInfoList.get(i).get("BIDGR_SECD")))
//			{
//				sb.append("<label for='' class='mr_10'><input type='radio' name='P_BIDGR_SECD' value='290000' id='' class='mr_5' checked='checked'>지급각서</label>");
//				sb.append("<label for='' class='mr_10'><input type='radio' name='P_BIDGR_SECD' value='290001' id='' class='mr_5'>이행보증보험증권</label>");
//			}else if("290001".equals(bidAssrncInfoList.get(i).get("BIDGR_SECD")))
//			{
//				sb.append("<label for='' class='mr_10'><input type='radio' name='P_BIDGR_SECD' value='290000' id='' class='mr_5'>지급각서</label>");
//				sb.append("<label for='' class='mr_10'><input type='radio' name='P_BIDGR_SECD' value='290001' id='' class='mr_5' checked='checked'>이행보증보험증권</label>");
//				
//			}
//			sb.append("</div>");
//			sb.append("&nbsp;</td>");
//			sb.append("</tr>");
//			sb.append("<tr>");
//			sb.append("<th width='120px' >보증번호</th>");
//			sb.append("<td width='400px' >");
//	        sb.append("<input type='text' name='P_BIDGR_NO' value='" + bidAssrncInfoList.get(i).getString("BIDGR_NO") + "' size='50'/>");
//	        sb.append("&nbsp;</td>");
//	        sb.append("</tr>");
//	        sb.append("<tr>");
//	        sb.append("<th width='120px' >보증금</th>");
//	        sb.append("<td width='400px' >");
//	        sb.append("<input type='text' name='P_BIDGR_AMT' value='" + bidAssrncInfoList.get(i).getString("BIDGR_AMT") + "' size='50'/>");
//	        sb.append("&nbsp; ");
//	        sb.append("<div class='btn_before fr' id='changeBtn[${status.count}]' onclick='bidAssrncInfoDelete(this);' style='cursor: pointer;'>보증정보삭제</div>");
//	        sb.append("</td>");
//	        sb.append("</tr>");
//	        
//	        
//		}
//		
//		trans.put(IepElbiBidPartcptSttusService.ENTRPS_GRNTY_INFO_LIST, sb.toString());
		
		
		
		
		
		return trans;
	}
	
}