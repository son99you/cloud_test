package com.eunwoosoft.ipro.sytm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.ipro.sytm.service.IproSytmEstmFrmService;


/**
 * 
 * com.eunwoosoft.ipro.sytm.controller
 * IproSytmContFormController.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 1. 28.
 *
 */
@RequestMapping(value = "/sytm")
@Controller
public class IproSytmEstmFrmController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/sytm/";
	String popupPrefixUrl = "/ipro/views/sytm/popup/";
	
	@Resource(name="iproSytmEstmFrmService")
	private IproSytmEstmFrmService iproSytmEstmFrmService;

	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : estmFrmList
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmFrmList")
	public String estmFrmList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmEstmFrmService.estmFrmListWithPgng(parameterMap);  
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : licenseList
	 * @author : YeonWooSon
	 * @date : 2021. 09. 01 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/licenseList")
	public String licenseList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmEstmFrmService.licenseListWithPgng(parameterMap);  
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : licenseRegForm
	 * @author : YeonWooSon
	 * @date : 2021. 09. 01 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/licenseRegForm")
	public String licenseRegForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : estmFrmRegForm
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/licenseRegist")
	public String licenseRegist(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_APPC_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
			
		trans = iproSytmEstmFrmService.licenseRegist(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "SYTM");
		parameterMap.put("P_CONN_CNTN", "라이센스 작성");
		parameterMap.put("P_CONN_URL", "/sytm/licenseRegForm.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
		
		parameterMap.put("P_LICENSE_NO", trans.get("P_LICENSE_NO"));
		trans.put("P_LICENSE_NO", parameterMap.get("P_LICENSE_NO"));
		//trans.put("resultCode", "Success");
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "forward:/sytm/licenseDetail.do?P_LICENSE_NO=" + parameterMap.get("P_LICENSE_NO")+"&resultCode=Success";
		
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : licenseDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/licenseDetail", method={RequestMethod.POST, RequestMethod.GET})
	public String licenseDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		/**
		 * request에 키값이 되는 값이 담겨있지 않으면 redirct로 넘어왔다고 판단하고
		 * request에 담겨있는 parameterMap값을 request.session에 담겨있는 parameterMap값으로  바꿔준다.
		 * 그리고 request.session에 담겨있는 requestUrl값을 먼저담겨있는 request.parameterMap 의 requestUrl으로 바꿔준다.
		 * 그래야지 jsp페이지를 정상적으로 찾을 수 있음. 
		 */
		if(parameterMap.get("P_LICENSE_NO") == null || parameterMap.get("P_LICENSE_NO").equals("")){
			String thisRequestUrl = parameterMap.get("requestUrl").toString();
			parameterMap = (FwkParameterMap) request.getSession().getAttribute(PARAMETER_MAP);
			parameterMap.put("requestUrl", thisRequestUrl);
			request.getSession().removeAttribute(PARAMETER_MAP);
		}
		
		trans = iproSytmEstmFrmService.licenseDetail(parameterMap);
		
		model.addAllAttributes(parameterMap); // 안넘기면 메뉴가 이상하게 나옴
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : estmFrmUpdtForm
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/licenseUpdtForm")
	public String licenseUpdtForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = iproSytmEstmFrmService.licenseDetail(parameterMap);
		
		model.addAllAttributes(parameterMap); // 안넘기면 메뉴가 이상하게 나옴
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : licenseUpdt
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/licenseUpdt")
	public String licenseUpdt(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_APPC_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
			
		trans = iproSytmEstmFrmService.licenseUpdt(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "SYTM");
		parameterMap.put("P_CONN_CNTN", "라이센스 수정");
		parameterMap.put("P_CONN_URL", "/sytm/licenseUpdtForm.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
		
		iproCommDefaultService.sendLOG(parameterMap);
		
		parameterMap.put("P_LICENSE_NO", trans.get("P_LICENSE_NO"));
		trans.put("P_LICENSE_NO", parameterMap.get("P_LICENSE_NO"));
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "forward:/sytm/licenseDetail.do";
		
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : licenseIssue
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/licenseIssue")
	public String licenseIssue(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_APPC_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
			
		trans = iproSytmEstmFrmService.licenseIssue(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "SYTM");
		parameterMap.put("P_CONN_CNTN", "라이센스 발급");
		parameterMap.put("P_CONN_URL", "/sytm/licenseDetail.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		parameterMap.put("P_LICENSE_NO", trans.get("P_LICENSE_NO"));
		trans.put("P_LICENSE_NO", parameterMap.get("P_LICENSE_NO"));
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "forward:/sytm/licenseDetail.do";
		
	}
	
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : licenseDelete
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/licenseDelete")
	public String licenseDelete(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_APPC_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
			
		trans = iproSytmEstmFrmService.licenseDelete(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "SYTM");
		parameterMap.put("P_CONN_CNTN", "라이센스 삭제");
		parameterMap.put("P_CONN_URL", "/estm/licenseDetail.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "forward:/sytm/licenseList.do";
		
	}
	
	
	@RequestMapping(value="/licenseListExcelDwld")
	public String licenseListExcelDwld(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproSytmEstmFrmService.licenseListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("dataList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","라이센스발급 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : estmFrmRegForm
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmFrmRegForm")
	public String estmFrmRegForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = new FwkTransferObject();
		
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_APPC_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : estmFrmRegForm
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmFrmRegist")
	public String estmFrmRegist(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_APPC_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
			
		trans = iproSytmEstmFrmService.estmFrmRegist(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "SYTM");
		parameterMap.put("P_CONN_CNTN", "평가서식 작성");
		parameterMap.put("P_CONN_URL", "/estm/estmFrmRegForm.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
		
		parameterMap.put("P_ESTM_FRM_NO", trans.get("P_ESTM_FRM_NO"));
		trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));
		//trans.put("resultCode", "Success");
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "forward:/sytm/estmFrmDetail.do?P_ESTM_FRM_NO=" + parameterMap.get("P_ESTM_FRM_NO")+"&resultCode=Success";
		
	}
	
	
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : estmFrmResultRegForm
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmFrmResultRegForm")
	public String estmFrmResultRegForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_APPC_DT", FwkDateUtil.getCurrentDateTimeAsString());
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : estmFrmDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmFrmDetail", method={RequestMethod.POST, RequestMethod.GET})
	public String estmFrmDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		/**
		 * request에 키값이 되는 값이 담겨있지 않으면 redirct로 넘어왔다고 판단하고
		 * request에 담겨있는 parameterMap값을 request.session에 담겨있는 parameterMap값으로  바꿔준다.
		 * 그리고 request.session에 담겨있는 requestUrl값을 먼저담겨있는 request.parameterMap 의 requestUrl으로 바꿔준다.
		 * 그래야지 jsp페이지를 정상적으로 찾을 수 있음. 
		 */
		if(parameterMap.get("P_ESTM_FRM_NO") == null || parameterMap.get("P_ESTM_FRM_NO").equals("")){
			String thisRequestUrl = parameterMap.get("requestUrl").toString();
			parameterMap = (FwkParameterMap) request.getSession().getAttribute(PARAMETER_MAP);
			parameterMap.put("requestUrl", thisRequestUrl);
			request.getSession().removeAttribute(PARAMETER_MAP);
		}
		
		trans = iproSytmEstmFrmService.estmFrmDetail(parameterMap);
		
		model.addAllAttributes(parameterMap); // 안넘기면 메뉴가 이상하게 나옴
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	@RequestMapping(value="/dataBaseRegist", method={RequestMethod.POST, RequestMethod.GET})
	public String dataBaseRegist(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		/**
		 * request에 키값이 되는 값이 담겨있지 않으면 redirct로 넘어왔다고 판단하고
		 * request에 담겨있는 parameterMap값을 request.session에 담겨있는 parameterMap값으로  바꿔준다.
		 * 그리고 request.session에 담겨있는 requestUrl값을 먼저담겨있는 request.parameterMap 의 requestUrl으로 바꿔준다.
		 * 그래야지 jsp페이지를 정상적으로 찾을 수 있음. 
		 */
		if(parameterMap.get("P_DATABASE_NM") == null || parameterMap.get("P_DATABASE_NM").equals("")){
			String thisRequestUrl = parameterMap.get("requestUrl").toString();
			parameterMap = (FwkParameterMap) request.getSession().getAttribute(PARAMETER_MAP);
			parameterMap.put("requestUrl", thisRequestUrl);
			request.getSession().removeAttribute(PARAMETER_MAP);
		}
		
		trans = iproSytmEstmFrmService.dataBaseRegist(parameterMap);
		
		model.addAllAttributes(parameterMap); // 안넘기면 메뉴가 이상하게 나옴
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : estmFrmUpdtForm
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmFrmUpdtForm")
	public String estmFrmUpdtForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = iproSytmEstmFrmService.estmFrmDetail(parameterMap);
		
		model.addAllAttributes(parameterMap); // 안넘기면 메뉴가 이상하게 나옴
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : estmFrmUpdt
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmFrmUpdt")
	public String estmFrmUpdt(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_APPC_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
			
		trans = iproSytmEstmFrmService.estmFrmUpdt(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "SYTM");
		parameterMap.put("P_CONN_CNTN", "평가서식 수정");
		parameterMap.put("P_CONN_URL", "/estm/estmFrmUpdtForm.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
		
		iproCommDefaultService.sendLOG(parameterMap);
		
		parameterMap.put("P_ESTM_FRM_NO", trans.get("P_ESTM_FRM_NO"));
		trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "forward:/sytm/estmFrmDetail.do";
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.sytm.controller.IproSytmEstmFrmController.java
	 * @Method : estmFrmDelete
	 * @author : JanDi_Eun
	 * @date : 2019. 1. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmFrmDelete")
	public String estmFrmDelete(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();

		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		parameterMap.put("P_APPC_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
			
		trans = iproSytmEstmFrmService.estmFrmDelete(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, ESTM, SYTM, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "SYTM");
		parameterMap.put("P_CONN_CNTN", "평가서식 삭제");
		parameterMap.put("P_CONN_URL", "/estm/estmFrmDetail.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "forward:/sytm/estmFrmList.do";
		
	}

	
	@RequestMapping(value="/estmFrmListExcelDwld")
	public String estmFrmListExcelDwld(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproSytmEstmFrmService.estmFrmListExcelDwld(parameterMap);
		
		// 엑셀다운로드시 필수
		FwkTransferObject trans2 = iproCommDefaultService.excelCommSetting(parameterMap);
		
		model.addAttribute("EXCEL_TH_LIST",trans2.get("EXCEL_TH_LIST"));
		model.addAttribute("EXCEL_TH_COL_LIST",trans2.get("EXCEL_TH_COL_LIST"));
		model.addAttribute("EXCEL_TH_ROW_LIST",trans2.get("EXCEL_TH_ROW_LIST"));
		model.addAttribute("EXCEL_TD_LIST",trans2.get("EXCEL_TD_LIST"));
		
		//model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("dataList",trans.get("dataList"));
		model.addAttribute("templateFileName", "template.xls");
		model.addAttribute("destFileName","평가서식관리 목록");		
		
		model.addAttribute("excelGbn","POI");
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
	

}
