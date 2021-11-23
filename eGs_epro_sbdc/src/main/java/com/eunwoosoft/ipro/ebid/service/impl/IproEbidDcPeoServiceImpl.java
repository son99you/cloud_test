package com.eunwoosoft.ipro.ebid.service.impl; 

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.dao.IproEbidDcPeoDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPartcptSttusDao;
import com.eunwoosoft.ipro.ebid.dao.IproEbidPlanDao;
import com.eunwoosoft.ipro.ebid.service.IproEbidDcPeoService;

/**
 * 입찰설명회 서비스 구현 클래스
 * 
 * @author : 은우소프트 손연우
 * @date : 2015. 02. 13.
 * @version : 1.0
 */
@Service("iproEbidDcPeoService")
public class IproEbidDcPeoServiceImpl extends AbstractFwkService implements IproEbidDcPeoService {
	
	@Resource(name="iproEbidDcPeoDao")
	private IproEbidDcPeoDao iproEbidDcPeoDao;
	
	@Resource(name="iproEbidPartcptSttusDao")
	private IproEbidPartcptSttusDao iproEbidPartcptSttusDao;
	
	@Resource(name="iproEbidPlanDao")
	private IproEbidPlanDao iproEbidPlanDao;
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰설명회 목록조회_페이징 
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
	public FwkTransferObject bidDcPeoListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		if(parameterMap.get("P_BRFS_STDT_S") != null && !"".equals(parameterMap.get("P_BRFS_STDT_S"))){ 
			parameterMap.put("P_BRFS_STDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_BRFS_STDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		if(parameterMap.get("P_BRFS_ENDT_S") != null && !"".equals(parameterMap.get("P_BRFS_ENDT_S"))){ 
			parameterMap.put("P_BRFS_ENDT_S", FwkFormatUtil.formatDate(parameterMap.get("P_BRFS_ENDT_S").toString(), "yyyy-MM-dd", "yyyyMMdd"));
		}
		
		trans.put(IproEbidDcPeoService.BID_DC_PEO_LIST, 
				iproEbidDcPeoDao.selectBidDcPeoListWithPgng(parameterMap));
		trans.put(IproEbidDcPeoService.BID_DC_PEO_LIST_TOTCNT, 
				iproEbidDcPeoDao.selectBidDcPeoListTotcnt(parameterMap));
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰설명회 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidDcPeoDetailInqire
	 * @date : 2015. 2. 13.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 13.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see IproEbidDcPeoService.iep.elbi.service.IepElbiBidDcPeoService#bidDcPeoDetailInqire(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject bidDcPeoDetailInqire(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		trans.put(IproEbidDcPeoService.BID_DC_PEO_DETAIL,
				iproEbidDcPeoDao.selectBidInfoDetail(parameterMap));
		trans.put(IproEbidDcPeoService.BID_DC_PEO_PARTCPT_ENTRPS_LIST,
				iproEbidDcPeoDao.selectBidDcPeoPartcptEntrpsList(parameterMap));
		
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가업체 등록폼
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptEntrpsRegistForm
	 * @date : 2015. 2. 16.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 16.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see IproEbidDcPeoService.iep.elbi.service.IepElbiBidDcPeoService#bidPartcptEntrpsRegistForm(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject bidPartcptEntrpsRegistForm(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
			trans.put(IproEbidDcPeoService.BID_DC_PEO_PARTCPT_ENTRPS_LIST,
					iproEbidDcPeoDao.selectBidDcPeoPartcptEntrpsListWithPgng(parameterMap));
			trans.put(IproEbidDcPeoService.BID_DC_PEO_PARTCPT_ENTRPS_LIST_TOTCNT,
					iproEbidDcPeoDao.selectBidDcPeoPartcptEntrpsListTotcnt(parameterMap));
		
		return trans;
	}
	

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가업체 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptEntrpsRegist
	 * @date : 2015. 2. 17.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 17.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see IproEbidDcPeoService.iep.elbi.service.IepElbiBidDcPeoService#bidPartcptEntrpsRegist(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject bidPartcptEntrpsRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		
		iproEbidDcPeoDao.deleteBidPartcptEntrps(parameterMap);
		
		Object P_VEND_REG_NO = parameterMap.get("P_VEND_REG_NO");
		Object P_ATNPE_NM = parameterMap.get("P_ATNPE_NM");
		Object P_ATNPE_TEL_NO = parameterMap.get("P_ATNPE_TEL_NO");
		Object P_ATNPE_EMAL = parameterMap.get("P_ATNPE_EMAL");
		if(P_VEND_REG_NO instanceof String){
			iproEbidDcPeoDao.insertBidPartcptEntrpsRegist(parameterMap);
		}else if(P_VEND_REG_NO instanceof String[]){
			String[] entrpsRegistNo =  (String[]) parameterMap.get("P_VEND_REG_NO");
			String[] atnpeNmList =  (String[]) P_ATNPE_NM;
			String[] atnpeTelNoList =  (String[]) P_ATNPE_TEL_NO;
			String[] atnpeEmalList =  (String[]) P_ATNPE_EMAL;
			for (int i = 0; i < entrpsRegistNo.length; i++) {
				parameterMap.put("P_VEND_REG_NO",entrpsRegistNo[i]);
				parameterMap.put("P_ATNPE_NM",atnpeNmList[i]);
				parameterMap.put("P_ATNPE_TEL_NO",atnpeTelNoList[i]);
				parameterMap.put("P_ATNPE_EMAL",atnpeEmalList[i]);
				iproEbidDcPeoDao.insertBidPartcptEntrpsRegist(parameterMap);
			}
		}else if(P_VEND_REG_NO instanceof ArrayList){
			ArrayList<String> entrpsRegistNo =  (ArrayList<String>) parameterMap.get("P_VEND_REG_NO");
			ArrayList<String> atnpeNmList =  (ArrayList) P_ATNPE_NM;
			ArrayList<String> atnpeTelNoList =  (ArrayList) P_ATNPE_TEL_NO;
			ArrayList<String> atnpeEmalList =  (ArrayList) P_ATNPE_EMAL;
			for (int i = 0; i < entrpsRegistNo.size(); i++) {
				parameterMap.put("P_VEND_REG_NO",entrpsRegistNo.get(i));
				parameterMap.put("P_ATNPE_NM",atnpeNmList.get(i));
				parameterMap.put("P_ATNPE_TEL_NO",atnpeTelNoList.get(i));
				parameterMap.put("P_ATNPE_EMAL",atnpeEmalList.get(i));
				iproEbidDcPeoDao.insertBidPartcptEntrpsRegist(parameterMap);
			}
		}
		return trans;
	}

	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가업체 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptEntrpsDelete
	 * @date : 2015. 2. 23.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 2. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see IproEbidDcPeoService.iep.elbi.service.IepElbiBidDcPeoService#bidPartcptEntrpsDelete(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	
	@Override
	public FwkTransferObject bidPartcptEntrpsDelete(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		Object ob = parameterMap.get("entrpsChb");
		
		if(ob instanceof String){
			
			parameterMap.put("P_VEND_REG_NO", parameterMap.get("entrpsChb"));
			iproEbidDcPeoDao.deleteBidPartcptEntrps(parameterMap);
		}else{
			
			ArrayList entrpsRegistNo =  (ArrayList) parameterMap.get("entrpsChb");
			
			
			
			for(int i = 0; i< entrpsRegistNo.size(); i++)
			{
				parameterMap.put("P_VEND_REG_NO", entrpsRegistNo.get(i));
				iproEbidDcPeoDao.deleteBidPartcptEntrps(parameterMap);
			}
			
		}
		
		List<FwkDataEntity> entrpsList  = 	iproEbidDcPeoDao.selectBidDcPeoPartcptEntrpsList(parameterMap);
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < entrpsList.size(); i++)
		{
			
			//null인경우 빈칸으로
			if(entrpsList.get(i).get("VEND_NM") == null || "".equals(entrpsList.get(i).get("VEND_NM")))				{					entrpsList.get(i).put("VEND_NM","");				}
			if(entrpsList.get(i).get("RPRS_NM") == null || "".equals(entrpsList.get(i).get("RPRS_NM")))				{					entrpsList.get(i).put("RPRS_NM","");				}
			if(entrpsList.get(i).get("BIZRNO") == null || "".equals(entrpsList.get(i).get("BIZRNO")))				{					entrpsList.get(i).put("BIZRNO","");				}
			if(entrpsList.get(i).get("REG_DT") == null || "".equals(entrpsList.get(i).get("REG_DT")))				{					entrpsList.get(i).put("REG_DT","");				}
			
			sb.append("<tr class='row'>");
			sb.append("<td class='hid txtc'><input type='checkbox' name='entrpsChb' value='"+ entrpsList.get(i).get("VEND_REG_NO") +"'></td>");
			sb.append("<td>"+ entrpsList.get(i).get("VEND_NM") +"</td>");
			sb.append("<td class='txtc'>"+ entrpsList.get(i).get("RPRS_NM") +"</td>");
			sb.append("<td class='txtc'>"+ FwkFormatUtil.formatBizNumber((String) entrpsList.get(i).get("BIZRNO"))+"</td>");
			sb.append("<td class='txtc'>"+ FwkFormatUtil.formatDate((String) entrpsList.get(i).get("REG_DT"), "yyyyMMddHHmmss","yyyy-MM-dd")+"</td>");
			sb.append("</tr>");
		}

		trans.put(IproEbidDcPeoService.BID_DC_PEO_PARTCPT_ENTRPS_LIST, sb.toString());
		
		return trans;
	}
	
	
	
}