package com.eunwoosoft.opro.ebid.service.impl;

import java.math.BigDecimal;
import java.security.cert.CertificateException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import signgate.crypto.util.CertUtil;

import com.eunwoosoft.comm.file.dao.ComAtmaAtchFileDao;
import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.frwk.bul.AbstractFwkService;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.fol.util.SDBCryptoUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproEbidSttlmntService;
import com.eunwoosoft.opro.ebid.dao.OproEbidSttlmntDao;
import com.eunwoosoft.opro.ebid.service.OproEbidSttlmntService;
import com.eunwoosoft.opro.main.service.OproMainLoginFormService;

/**
 * 
 * com.eunwoosoft.opro.main.service.impl
 * OproEbidSttlmntServiceImpl.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2018. 2. 20.
 *
 */
@Service(value="oproEbidSttlmntService")
public class OproEbidSttlmntServiceImpl extends AbstractFwkService implements OproEbidSttlmntService{
	private static final Logger LOG = LoggerFactory.getLogger(OproEbidSttlmntServiceImpl.class);

	@Resource(name="oproEbidSttlmntDao")
	private OproEbidSttlmntDao oproEbidSttlmntDao;
	
	@Resource(name="comAtmaAtchFileDao")
	private ComAtmaAtchFileDao comAtmaAtchFileDao; 
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 나의수의시담목록 조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.opro.ebid.service.OproEbidSttlmntService.java
	 * @Method : vltrnPrvstlListInqireWithPgng
	 * @author : chanil_Hong
	 * @date : 2018. 3. 20. 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject vltrnPrvstlListInqireWithPgng(FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		
		if(parameterMap.get("P_BEFFAT_PBLANC_BEGIN_DE_S") == null ){
			parameterMap.put("P_BEFFAT_PBLANC_BEGIN_DE_S", "");
		}else{
			parameterMap.put("P_BEFFAT_PBLANC_BEGIN_DE_S",parameterMap.getString("P_BEFFAT_PBLANC_BEGIN_DE_S").replaceAll("-", ""));
		}
		if(parameterMap.get("P_BEFFAT_PBLANC_END_DE_S") == null){
			parameterMap.put("P_BEFFAT_PBLANC_END_DE_S", "");
		}else{
			parameterMap.put("P_BEFFAT_PBLANC_END_DE_S", parameterMap.getString("P_BEFFAT_PBLANC_END_DE_S").replaceAll("-", ""));
		}

		if(parameterMap.get("P_PRVSTL_BEGIN_DT_S") == null ){
			parameterMap.put("P_PRVSTL_BEGIN_DT_S", "");
		}else{
			parameterMap.put("P_PRVSTL_BEGIN_DT_S", parameterMap.getString("P_PRVSTL_BEGIN_DT_S").replaceAll("-", ""));
		}
		if(parameterMap.get("P_PRVSTL_BEGIN_END_DT_S") == null){
			parameterMap.put("P_PRVSTL_BEGIN_END_DT_S", "");
		}else{
			parameterMap.put("P_PRVSTL_BEGIN_END_DT_S", parameterMap.getString("P_PRVSTL_BEGIN_END_DT_S").replaceAll("-", ""));
		}
		
		parameterMap.put("P_LOGIN_ID", session.get("LOGIN_ID"));
		
			trans.put(OproEbidSttlmntService.VLTRN_PRVSTL_LIST, 
					oproEbidSttlmntDao.selectVltrnPrvstlListWithPgng(parameterMap));
			trans.put(OproEbidSttlmntService.VLTRN_PRVSTL_LIST_TOTCNT, 
					oproEbidSttlmntDao.selectVltrnPrvstlListTotcnt(parameterMap));
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 상세 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlDetailInqire
	 * @date : 2015. 3. 02.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 02.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject vltrnPrvstlDetailInqire(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		//TELNO
		FwkDataEntity dataEntity = (FwkDataEntity) oproEbidSttlmntDao.selectVltrnPrvstlDetail(parameterMap);
		
		System.err.println("dataEntity before:::: " + dataEntity);
		
		trans.put(OproEbidSttlmntService.VLTRN_PRVSTL_DETAIL ,dataEntity);
		
		
		return trans;
	}

	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 상세-시담진행 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlProgrsDetailInqire
	 * @date : 2015. 3. 10.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 10.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.oep.vlpr.service.OproEbidSttlmntService#vltrnPrvstlProgrsDetail(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject vltrnPrvstlProgrsDetailInqire(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_LOGIN_ID", session.get("LOGIN_ID"));
		
		FwkDataEntity dataEntity = (FwkDataEntity) oproEbidSttlmntDao.selectVltrnPrvstlDetail(parameterMap);
		
		if(dataEntity != null){
			parameterMap.put("P_FILE_GRP_NO", dataEntity.get("FILE_GRP_NO"));
			trans.put(ComAtmaAtchFileService.ATCH_FILE_LIST, comAtmaAtchFileDao.selectAtchFileListInqireByAtchFileGroupNo(parameterMap));
		}
		trans.put(IproEbidSttlmntService.VLTRN_PRVSTL_DETAIL ,dataEntity);
		trans.put(OproEbidSttlmntService.VLTRN_PRVSTL_PROGRS_LIST,oproEbidSttlmntDao.selectVltrnPrvstlProgrsList(parameterMap));
		
		
		FwkDataEntity plnprcAmount = (FwkDataEntity) trans.get(OproEbidSttlmntService.VLTRN_PRVSTL_DETAIL); 
		
		List<FwkDataEntity> ntatAmountList = oproEbidSttlmntDao.selectVltrnPrvstlNtatAmountList(parameterMap);
		
		StringBuffer sb = new StringBuffer();

		BigDecimal plnprcAmountValue = null;
		DecimalFormat formatter = new DecimalFormat("####################");
		
		if(plnprcAmount.get("PLNPRC_AMOUNT") != null && !"".equals(plnprcAmount.get("PLNPRC_AMOUNT")))
		{
			plnprcAmountValue = new BigDecimal(formatter.format(plnprcAmount.get("PLNPRC_AMOUNT")));
		
			/*
			 *  HTML 형태로 생성
			 */
			
			sb.append("<table border='0' cellspacing='0' cellpadding='0' style='overflow-y: scroll;' >");
			for(int i= 0; i < ntatAmountList.size(); i++)
			{
				BigDecimal ntatAmountValue;
				//null인경우 빈칸으로
				if(ntatAmountList.get(i).get("NTAT_AMOUNT") == null || "".equals(ntatAmountList.get(i).get("NTAT_AMOUNT")))				{					ntatAmountList.get(i).put("NTAT_AMOUNT","");				}
				if(ntatAmountList.get(i).get("REGIST_DT") == null || "".equals(ntatAmountList.get(i).get("REGIST_DT")))			{					ntatAmountList.get(i).put("REGIST_DT","");				}
				
				System.err.println("NTAT_AMOUNT  :::::  " + ntatAmountList.get(i).get("NTAT_AMOUNT"));
				
				
				ntatAmountValue = new BigDecimal(formatter.format(ntatAmountList.get(i).get("NTAT_AMOUNT")));
				System.err.println("ntatAmountValue  :::::  " + ntatAmountValue);
				System.err.println("plnprcAmountValue  :::::  " + plnprcAmountValue);
				sb.append("<tr>");
					if(plnprcAmountValue.compareTo(ntatAmountValue) > 0 || plnprcAmountValue.compareTo(ntatAmountValue) == 0)
					{
						sb.append("<td width='170px' style='text-align: left; border:0px;'>예가 이하</td>");
						
					}else if(plnprcAmountValue.compareTo(ntatAmountValue) < 0)
					{
						sb.append("<td width='170px' style='text-align: left; border:0px;'>예가 초과</td>");
					}
					sb.append("<td width='170px' style='text-align: right; border:0px;'>" +  FwkFormatUtil.formatDate((String) ntatAmountList.get(i).get("REGIST_DT"), "yyyyMMddHHmmss","yyyy-MM-dd HH:mm:ss") +"<br>"+ FwkFormatUtil.formatMoney(ntatAmountList.get(i).getString("NTAT_AMOUNT")) + "<br><br> </td>");
				sb.append("</tr>");
			}
			sb.append("</table>");
		}	
		
		trans.put(OproEbidSttlmntService.VLTRN_PRVSTL_PLNPRC_AMOUNT_LIST, sb.toString());
		
		parameterMap.put("P_PRDPRC_BELOW_AT","Y");
		trans.put("entrpsPrdprcBelowInputAmount", oproEbidSttlmntDao.selectEntrpsPrdprcBelowInputAmount(parameterMap));
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 내용 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlCnRegist
	 * @date : 2015. 3. 10.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 10.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.oep.vlpr.service.OproEbidSttlmntService#vltrnPrvstlCnRegist(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject vltrnPrvstlCnRegist(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		
		

		parameterMap.put("P_ENTRPS_CHARGER_ID", parameterMap.get("P_LOGIN_ID"));
		parameterMap.put("P_INNER_CHARGER_ID", "");
		parameterMap.put("P_CN_REGIST_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
		System.err.println("P_PRVSTL_CN :: " + parameterMap.get("P_PRVSTL_CN"));
		if(!"".equals(parameterMap.get("P_PRVSTL_CN")) && parameterMap.get("P_PRVSTL_CN") != null)
		{

		
			// refresh 될때 insert 되는 것을 방지 하기 위해서 
			if("normal".equals(parameterMap.get("P_TYPE_SE")))
			{
				oproEbidSttlmntDao.insertVltrnPrvstlCnRegist(parameterMap);
			}else if("refresh".equals(parameterMap.get("P_TYPE_SE")))
			{}
		
		}
		
		//시담내용
		List<FwkDataEntity> progrsList = oproEbidSttlmntDao.selectVltrnPrvstlProgrsList(parameterMap);
		
		
		/*
		 *  HTML 형태로 생성
		 */
		
		StringBuffer sb = new StringBuffer();
		sb.append("<table border='0' cellspacing='0' cellpadding='0' style='overflow-y: scroll;' >");
		for(int i= 0; i < progrsList.size(); i++)
		{
			//null인경우 빈칸으로
			if(progrsList.get(i).get("PRVSTL_CN") == null || "".equals(progrsList.get(i).get("PRVSTL_CN")))				{					progrsList.get(i).put("PRVSTL_CN","");				}
			if(progrsList.get(i).get("CN_REGIST_DT") == null || "".equals(progrsList.get(i).get("CN_REGIST_DT")))				{					progrsList.get(i).put("CN_REGIST_DT","");				}
			
			sb.append("<tr>");
			if("".equals(progrsList.get(i).get("ENTRPS_CHARGER_ID")) || progrsList.get(i).get("ENTRPS_CHARGER_ID") == null)
			{
				sb.append("<td width='300px' style='text-align: left; border:0px;'>" + progrsList.get(i).get("PRVSTL_CN") + "<br>"+ FwkFormatUtil.formatDate((String) progrsList.get(i).get("CN_REGIST_DT"), "yyyyMMddHHmmss","yyyy-MM-dd HH:mm:ss") +"<br></td>");
				sb.append("<td width='300px' style='text-align: left; border:0px;'>&nbsp;</td>");
				
			}else if("".equals(progrsList.get(i).get("INNER_CHARGER_ID")) || progrsList.get(i).get("INNER_CHARGER_ID") == null)
			{
				sb.append("<td width='300px' style='text-align: left; border:0px;'>&nbsp;</td>");
				sb.append("<td width='300px' style='text-align: right; border:0px;''>"+ progrsList.get(i).get("PRVSTL_CN") +"<br>" +FwkFormatUtil.formatDate((String) progrsList.get(i).get("CN_REGIST_DT"), "yyyyMMddHHmmss","yyyy-MM-dd HH:mm:ss")+"<br></td>");
				
			}
			sb.append("</tr>");
			
		}
		sb.append("</table>");

		
		System.err.println(sb.toString());
		
		trans.put(OproEbidSttlmntService.VLTRN_PRVSTL_PROGRS_LIST, sb.toString());
		
		return trans;
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 협상 금액 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlNtatAmountRegist
	 * @date : 2015. 6. 17.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 6. 17.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.oep.vlpr.service.OproEbidSttlmntService#vltrnPrvstlNtatAmountRegist(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject vltrnPrvstlNtatAmountRegist(final FwkParameterMap parameterMap) {
		
		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_REGISTER_ID", parameterMap.get("P_LOGIN_ID"));
		parameterMap.put("P_REGIST_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
		@SuppressWarnings("unchecked")
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_LOGIN_ID", session.get("LOGIN_ID"));
		
		// refresh 될때 insert 되는 것을 방지 하기 위해서 
					if("normal".equals(parameterMap.get("P_TYPE_SE")))
					{
						oproEbidSttlmntDao.insertVltrnPrvstlNtatAmountRegist(parameterMap);
					}else if("refresh".equals(parameterMap.get("P_TYPE_SE")))
					{}
		
		
		//예정가격금액
		FwkDataEntity plnprcAmount = oproEbidSttlmntDao.selectVltrnPrvstlDetail(parameterMap);
		
		//협상가격금액
		parameterMap.put("MAX_HIST_SN", "Y"); //가장 최근에 입력한 협상가격금액
		//parameterMap.put("P_PRDPRC_BELOW_AT","Y");
		FwkDataEntity pNtatAmount = oproEbidSttlmntDao.selectEntrpsPrdprcBelowInputAmount(parameterMap);
		
		BigDecimal plnprcAmountValue = null;
		BigDecimal pNtatAmountValue = null;
		
		DecimalFormat formatter = new DecimalFormat("################.####");
		
		if(plnprcAmount.get("PLNPRC_AMOUNT") != null || !"".equals(plnprcAmount.get("PLNPRC_AMOUNT")))
		{
			plnprcAmountValue = new BigDecimal(formatter.format(plnprcAmount.get("PLNPRC_AMOUNT")));
		}
		
		
		if(pNtatAmount != null)
		{
			if(pNtatAmount.get("NTAT_AMOUNT") != null || !"".equals(pNtatAmount.get("NTAT_AMOUNT")))
			{
				
				pNtatAmountValue = new BigDecimal(formatter.format(pNtatAmount.get("NTAT_AMOUNT")));
				
				System.err.println("plnprcAmountValue ::: " +plnprcAmountValue + " pNtatAmountValue ::: " + pNtatAmountValue);
				
				//PRDPRC_BELOW_AT 설정
				
				if(plnprcAmountValue.compareTo(pNtatAmountValue) > 0 || plnprcAmountValue.compareTo(pNtatAmountValue) == 0)
				{
					
					parameterMap.put("P_PRDPRC_BELOW_AT","Y");
					
					trans.put("ntatAmount", pNtatAmount.get("NTAT_AMOUNT"));
				}else
				{
					
					parameterMap.put("P_PRDPRC_BELOW_AT","N");
				}
				
				
				
				parameterMap.put("P_HIST_SN", pNtatAmount.get("HIST_SN"));
				oproEbidSttlmntDao.updateVltrnPrvstlPrdprcBelowAtRegist(parameterMap);
				
			}
		
		}
		
		// 수의시담 협상금액 목록
		List<FwkDataEntity> ntatAmountList = oproEbidSttlmntDao.selectVltrnPrvstlNtatAmountList(parameterMap);
		
		// 업체 예가 이하 입력 금액
		parameterMap.put("P_PRDPRC_BELOW_AT","Y");
		FwkDataEntity entrpsPrdprcBelowInputAmount = oproEbidSttlmntDao.selectEntrpsPrdprcBelowInputAmount(parameterMap);
		
		
		StringBuffer sb = new StringBuffer();
		
		/*
		 *  HTML 형태로 생성
		 */
		
		sb.append("<table border='0' cellspacing='0' cellpadding='0' style='overflow-y: scroll;' >");
		for(int i= 0; i < ntatAmountList.size(); i++)
		{
			BigDecimal ntatAmountValue;
			
			//null인경우 빈칸으로
			if(ntatAmountList.get(i).get("NTAT_AMOUNT") == null || "".equals(ntatAmountList.get(i).get("NTAT_AMOUNT")))				{					ntatAmountList.get(i).put("NTAT_AMOUNT","");				}
			if(ntatAmountList.get(i).get("REGIST_DT") == null || "".equals(ntatAmountList.get(i).get("REGIST_DT")))			{					ntatAmountList.get(i).put("REGIST_DT","");				}
			
			ntatAmountValue = new BigDecimal(formatter.format(ntatAmountList.get(i).get("NTAT_AMOUNT")));
			sb.append("<tr>");
				if("Y".equals(ntatAmountList.get(i).get("PRDPRC_BELOW_AT")))
				{
					sb.append("<td width='170px' style='text-align: left; border:0px;'>예가 이하</td>");
					if(ntatAmountList.get(i).get("HIST_SN").equals(entrpsPrdprcBelowInputAmount.get("HIST_SN")))
					{
						sb.append("<input type='hidden' id='P_ENTRPS_PRDPRC_BELOW_INPUT_AMOUNT'  value='"+ entrpsPrdprcBelowInputAmount.get("NTAT_AMOUNT")+"' >");
					}
				}else
				{
					sb.append("<td width='170px' style='text-align: left; border:0px;'>예가 초과</td>");
				}
				sb.append("<td width='170px' style='text-align: right; border:0px;'>" +  FwkFormatUtil.formatDate((String) ntatAmountList.get(i).get("REGIST_DT"), "yyyyMMddHHmmss","yyyy-MM-dd HH:mm:ss") +"<br>"+ FwkFormatUtil.formatMoney(ntatAmountList.get(i).getString("NTAT_AMOUNT")) + "<br><br> </td>");
			sb.append("</tr>");
		}
		sb.append("</table>");

		trans.put(OproEbidSttlmntService.VLTRN_PRVSTL_PLNPRC_AMOUNT_LIST, sb.toString());
		
		System.err.println("trans :::: "+trans);
		
		return trans;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 최종 금액 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlLastAmountRegist
	 * @date : 2015. 6. 19.
	 * @author : 은우소프트 홍찬일
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 6. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @see oda.oep.vlpr.service.OproEbidSttlmntService#vltrnPrvstlLastAmountRegist(oda.fwk.prl.request.FwkParameterMap)
	 * @param parameterMap
	 * @return
	 */
	
	@Override
	public FwkTransferObject vltrnPrvstlLastAmountRegist(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		parameterMap.put("P_CRTFCT_ESNTL_VALUE",  parameterMap.get("P_CRTFCT_ESNTL_VALUE")); // 인증서고유값
		parameterMap.put("P_PRVSTL_PRST_CD", "PO60");
		//최종 협상가격 등록
		oproEbidSttlmntDao.updatePrvstlLastAmountRegist(parameterMap);
		//시담 전자서명 등록
		oproEbidSttlmntDao.insertPrvstlSignRegist(parameterMap);
		//수의시담 진행이력 등록
		oproEbidSttlmntDao.insertVltrnPrvstlHistRegist(parameterMap);
		
		FwkDataEntity vltrnPrvstlDetail = oproEbidSttlmntDao.selectVltrnPrvstlDetail(parameterMap);
		
		//${comFn:formatMoney(vltrnPrvstlDetail.LAST_NTAT_AMOUNT) }(원)
		StringBuffer sb = new StringBuffer();
		
		sb.append("<table class='table' style='width: 600px;'>");
		sb.append("<caption>결정금액</caption>");
		sb.append("<colgroup>");
		sb.append("<col width='100px'>");
		sb.append("<col width='500px'>");
		sb.append("</colgroup>");
		sb.append("<tbody>");
		sb.append("<tr class='line' style='width:600px;'>");
		sb.append("<th style='width: 100px;'><font color='red'>결정금액</font></th>");
		sb.append("<td style='width: 500px;'> ");
		sb.append(FwkFormatUtil.formatMoney(vltrnPrvstlDetail.getString("LAST_NTAT_AMOUNT")) + " (원) </td>");
		sb.append("</tr>");
		sb.append("</tbody>");
		sb.append("</table>");
		
		
		trans.put("lastNtatAmount", sb.toString());
		
		System.err.println("lastNtatAmount  :::  " + trans);
		
		
		//lastNtatAmount
		
		return trans;
		
	}

	/**
	 * <pre>
	 * 1. 개요 : 전체 수의시담 목록조회_페이징 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : allVltrnPrvstlListInqireWithPgng
	 * @date : 2016. 02. 19.
	 * @author : 은우소프트 홍찬일
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 19.		은우소프트 홍찬일				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 * @return
	 */
	@Override
	public FwkTransferObject allVltrnPrvstlListInqireWithPgng(final FwkParameterMap parameterMap) {
		FwkTransferObject trans = new FwkTransferObject();
		if(parameterMap.get("P_BEFFAT_PBLANC_BEGIN_DE_S") == null ){
			parameterMap.put("P_BEFFAT_PBLANC_BEGIN_DE_S", "");
		}else{
			parameterMap.put("P_BEFFAT_PBLANC_BEGIN_DE_S",parameterMap.getString("P_BEFFAT_PBLANC_BEGIN_DE_S").replaceAll("-", ""));
		}
		if(parameterMap.get("P_BEFFAT_PBLANC_END_DE_S") == null){
			parameterMap.put("P_BEFFAT_PBLANC_END_DE_S", "");
		}else{
			parameterMap.put("P_BEFFAT_PBLANC_END_DE_S", parameterMap.getString("P_BEFFAT_PBLANC_END_DE_S").replaceAll("-", ""));
		}

		if(parameterMap.get("P_PRVSTL_BEGIN_DT_S") == null ){
			parameterMap.put("P_PRVSTL_BEGIN_DT_S", "");
		}else{
			parameterMap.put("P_PRVSTL_BEGIN_DT_S", parameterMap.getString("P_PRVSTL_BEGIN_DT_S").replaceAll("-", ""));
		}
		if(parameterMap.get("P_PRVSTL_BEGIN_END_DT_S") == null){
			parameterMap.put("P_PRVSTL_BEGIN_END_DT_S", "");
		}else{
			parameterMap.put("P_PRVSTL_BEGIN_END_DT_S", parameterMap.getString("P_PRVSTL_BEGIN_END_DT_S").replaceAll("-", ""));
		}
		
		trans.put(OproEbidSttlmntService.VLTRN_PRVSTL_LIST, oproEbidSttlmntDao.selectAllVltrnPrvstlListWithPgng(parameterMap));
		trans.put(OproEbidSttlmntService.VLTRN_PRVSTL_LIST_TOTCNT, oproEbidSttlmntDao.selectAllVltrnPrvstlListTotcnt(parameterMap));
		return trans;
	}
	

}
