package com.eunwoosoft.ipro.ebid.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproEbidBfStndOpenComplService;

/**
 * 
 * com.eunwoosoft.ipro.ebid.controller
 * IproEbidBfStndOpenComplController.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 2. 27.
 *
 */
@RequestMapping(value = "/ebid")
@Controller
public class IproEbidBfStndOpenComplController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";
	String prefixExcelUrl = "/ipro/ebid/";
	
	@Resource(name="iproEbidBfStndOpenComplService")
	private IproEbidBfStndOpenComplService iproEbidBfStndOpenComplService;
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 사전규격요청진행현황 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.cont.controller.IproPrpoBfStndRqstComplController.java
	 * @Method : bfStndRqstComplList
	 * @author : 
	 * @date : 2020. 8. 26. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bfStndOpenComplList")
	public String bfStndOpenComplList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		FwkTransferObject trans = new FwkTransferObject();
		if(!"Y".equals(parameterMap.get("P_SEARCH"))){
			if("1".equals(user.get("AUTH_ID"))){	//관리자
				//전체조회 가능
			}else if("4".equals(user.get("AUTH_ID"))){	//계약담당자
				trans.put("P_ARA_DEPT_CD_S", user.get("ARA_DEPT_CD"));
				parameterMap.put("P_ARA_DEPT_CD_S", user.get("ARA_DEPT_CD"));
			}else{	
				trans.put("P_ARA_DEPT_CD_S", user.get("ARA_DEPT_CD"));
				trans.put("P_RQR_DEPT_NO_S", user.get("DEPT_NO")); 
				trans.put("P_RQR_DEPT_NM_S", user.get("DEPT_NM"));  
				
				parameterMap.put("P_ARA_DEPT_CD_S", user.get("ARA_DEPT_CD"));
				parameterMap.put("P_RQR_DEPT_NO_S", user.get("DEPT_NO")); 
				parameterMap.put("P_RQR_DEPT_NM_S", user.get("DEPT_NM")); 
			}
			parameterMap.put("P_RQR_BEGIN_DT_S", FwkDateUtil.convertToString(FwkDateUtil.addDate(new Date(), -7), "yyyyMMdd"));
			parameterMap.put("P_RQR_END_DT_S", FwkDateUtil.getCurrentDateAsString("yyyyMMdd"));
		}
		
		
		trans = iproEbidBfStndOpenComplService.bfStndOpenPrcnList(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 사전규격요청진행현황 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.cont.controller.IproPrpoBfStndRqstComplController.java
	 * @Method : bfStndRqstComplDetail
	 * @author : 
	 * @date : 2020.8.26. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bfStndOpenComplDetail" , method={RequestMethod.POST, RequestMethod.GET})
	public String bfStndOpenComplDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproEbidBfStndOpenComplService.bfStndOpenComplDetail(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사전규격요청진행현황 엑셀 다운로드
	 * 2. 처리내용 : 
	 * </pre>
	 */
	@RequestMapping(value="/bfanComplExcelDwld")
	public String bfanComplExcelDwld(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEbidBfStndOpenComplService.bfStndOpenComplExcelList(parameterMap);
		
		model.addAttribute("dataList",trans.get(iproEbidBfStndOpenComplService.BF_STND_OPEN_LIST));
		model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("destFileName","사전규격공개완료현황 목록");		
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
}
