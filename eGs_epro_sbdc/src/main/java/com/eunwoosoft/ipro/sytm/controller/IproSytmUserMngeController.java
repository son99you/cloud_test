package com.eunwoosoft.ipro.sytm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.ipro.sytm.service.IproSytmUserMngeService;


@Controller
@RequestMapping(value = "/sytm")
public class IproSytmUserMngeController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/sytm/";
	
	@Resource(name="iproSytmUserMngeService")
	private IproSytmUserMngeService iproSytmUserMngeService;

	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	/**
	 * <pre>
	 * 1.개요 : 사용자관리 목록
	 * 2.처리내용 : 정관장 참조 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.sytm.controller.IproSytmUserMngeController.java
	 * @Method : userMgr
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 14. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/userMgrList")
	public String userMgrList(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		if("".equals(parameterMap.getString("P_SEARCH"))){
			parameterMap.put("P_AUTH_ID_S", "6");			//처음 기본세팅 6(없음)
		}
		FwkTransferObject trans = iproSytmUserMngeService.userMgrListWithPgng(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 사용자 관리 상세
	 * 2.처리내용 : 정관장 참조 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.sytm.controller.IproSytmUserMngeController.java
	 * @Method : userMgr
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 14. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/userMgrDetail")
	public String userMgrDetail(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmUserMngeService.userMgrDetailInqire(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 사용자관리 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.sytm.controller.IproSytmUserMngeController.java
	 * @Method : userMgrUpdt
	 * @author : jandi_Eun
	 * @date : 2018. 2. 27. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/userMgrUpdt")
	public String userMgrUpdt(final HttpServletRequest request, final HttpServletResponse response, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		try {
			FwkTransferObject trans = iproSytmUserMngeService.userMgrUpdt(parameterMap);
			
			//Log 저장
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "AUTH_CHNG");
			//권한자 누가, 사용자 누구를 어떤 권한(기존권한)에서 어떤권한으로(변경된 권한) 변경하는지 구현
			parameterMap.put("P_CONN_CNTN", parameterMap.get("P_LOG_INSERT_USR_NM") + "님이  " + parameterMap.get("P_LOG_CHANGE_USR_NM")
											+ "님의 권한을 " + parameterMap.get("P_BEFORE_AUTH_NM") + "권한에서  " + parameterMap.get("P_AFTER_AUTH_NM") + "권한으로 변경");
			parameterMap.put("P_CONN_URL", "/sytm/userMgrDetail.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
					
			iproCommDefaultService.sendLOG(parameterMap);
			
			model.addAttribute("trans", trans);
			model.addAllAttributes(parameterMap);
		} catch (Exception ex) {
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}
	
	@RequestMapping(value="/userMgrListExcelDwld" )
	public String userMgrListExcelDwld(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproSytmUserMngeService.userMgrExcelList(parameterMap);
		
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("dataList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","사용자관리 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
}
