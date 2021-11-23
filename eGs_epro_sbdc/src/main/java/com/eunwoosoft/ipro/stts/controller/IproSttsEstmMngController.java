package com.eunwoosoft.ipro.stts.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.ipro.stts.service.IproSttsEstmMngService;

/**
 * 
 * com.eunwoosoft.ipro.stts.controller
 * IproSttsEstmMngController.java
 *
 * @Author : 손연우
 * @Date   : 2021. 3. 24.
 *
 */
@Controller
@RequestMapping(value = "/stts")
public class IproSttsEstmMngController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/stts/";
	String prefixUrl2 = "/ipro/views/stts/popup/";
	
	@Resource(name="iproSttsEstmMngService")
	private IproSttsEstmMngService iproSttsEstmMngService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	
	/** 
	 * <pre>
	 * 1.개요 : 통계대장 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : 
	 * @Method : sttsList
	 * @author : 손연우
	 * @date : 2021. 4. 30. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmSttsList")
	public String sttsList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproSttsEstmMngService.sttsList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/** 
	 * <pre>
	 * 1.개요 : 통계대장 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : 
	 * @Method : sttsList
	 * @author : 손연우
	 * @date : 2021. 4. 30. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/estmSttsListExcelDwld")
	public String estmSttsListExcelDwld(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproSttsEstmMngService.estmSttsListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		//model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("sttsListData",trans.get("sttsListData"));
		model.addAttribute("sttsKeyList",trans.get("sttsKeyList"));
		
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","통계관리 목록");		
		
		model.addAttribute("excelGbn","POI_STTS");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
	
	
	
	
	

	
}
