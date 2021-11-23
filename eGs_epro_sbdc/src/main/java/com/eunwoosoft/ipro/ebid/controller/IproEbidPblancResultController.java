package com.eunwoosoft.ipro.ebid.controller; 

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.eunwoosoft.comm.itfc.service.CommItfcService;
import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproEbidPblancResultService;

/**
 * 
 * @author joo
 *
 */
@Controller
@RequestMapping(value = "/ebid")
public class IproEbidPblancResultController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";
	String prefixPopUrl = "/ipro/views/ebid/popup/";
	String prefixExcelUrl = "ipro/ebid/";
	
	@Resource(name="iproEbidPblancResultService")
	private IproEbidPblancResultService iproEbidPblancResultService;
	
	@Resource(name="commItfcService")
	private CommItfcService commItfcService;
	
	
	@RequestMapping(value = "/bidPblancResultList")
	public String bidPblancResultList(final HttpServletRequest request, final Model model) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");

		if(!"Y".equals(parameterMap.get("P_SEARCH"))){ 
			if("1".equals(user.get("AUTH_ID"))){	//관리자
				//전체조회 가능
			}else if("4".equals(user.get("AUTH_ID"))){		//계약담당자
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
		}
		
		if(parameterMap.getString("P_RQR_BEGIN_DT_S") == null || parameterMap.getString("P_RQR_BEGIN_DT_S").equals("")){
			parameterMap.put("P_RQR_BEGIN_DT_S", FwkDateUtil.convertToString(FwkDateUtil.addDate(new Date(), -7), "yyyy-MM-dd"));
		}
	
		if(parameterMap.getString("P_RQR_END_DT_S") == null || parameterMap.getString("P_RQR_END_DT_S").equals("")){
			parameterMap.put("P_RQR_END_DT_S", FwkDateUtil.getCurrentDateAsString("yyyy-MM-dd"));
		}	
		trans = iproEbidPblancResultService.bidPblancResultListInqireWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}

	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 입찰공고상세화면으로 이동합니다. 
	 *	</pre>
	 *
	 * @method Name : bidPblancResultDetail
	 * @Author : joo
	 * @Date   : 2020. 9. 22.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 22.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */ 
	@RequestMapping(value="/bidPblancResultDetail" , method={RequestMethod.POST, RequestMethod.GET})
	public String bidPblancResultDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		// 리다이렉트 상세화면 이동을 위해 분기처리
		Object obj = RequestContextUtils.getInputFlashMap(request);
		if(obj != null) {
			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
		}else {
			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		}
		
		parameterMap.put("P_APPR_CNTC_KEY", "B");
//		FwkTransferObject trans = iproPrpoBidReqService.bidReqDetail(parameterMap);
//		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		 
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 기술평가 탭 
	 *	</pre>
	 *
	 * @method Name : bidTchnEstmDetail
	 * @Author : joo
	 * @Date   : 2020. 9. 14.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 14.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value="/bidTchnEstmResultDetail" , method={RequestMethod.POST, RequestMethod.GET})
	public String bidTchnEstmResultDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		// 리다이렉트 상세화면 이동을 위해 분기처리
		Object obj = RequestContextUtils.getInputFlashMap(request);
		if(obj != null) {
			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
		}else {
			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		}
		
		parameterMap.put("P_APPR_CNTC_KEY", "B");
//		FwkTransferObject trans = iproPrpoBidReqService.bidReqDetail(parameterMap);
//		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		model.addAttribute("APPR_URL", FwkMessageUtil.getMessage("APPR.URL"));
		 
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 개찰탭에서 개찰결과를 등록합니다. 
	 *	</pre>
	 *
	 * @method Name : bidOpenDetail
	 * @Author : joo
	 * @Date   : 2020. 9. 21.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 21.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value="/bidOpenResultDetail" , method={RequestMethod.POST, RequestMethod.GET})
	public String bidOpenResultDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		// 리다이렉트 상세화면 이동을 위해 분기처리
		Object obj = RequestContextUtils.getInputFlashMap(request);
		if(obj != null) {
			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
		}else {
			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		}
		
		parameterMap.put("P_APPR_CNTC_KEY", "B");
//		FwkTransferObject trans = iproPrpoBidReqService.bidReqDetail(parameterMap);
//		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		 
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 기술협상 탭에서 협상결과를 등록한다. 
	 *	</pre>
	 *
	 * @method Name : bidTchnNegoDetail
	 * @Author : joo
	 * @Date   : 2020. 9. 21.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 21.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value="/bidTchnNegoResultDetail" , method={RequestMethod.POST, RequestMethod.GET})
	public String bidTchnNegoResultDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		// 리다이렉트 상세화면 이동을 위해 분기처리
		Object obj = RequestContextUtils.getInputFlashMap(request);
		if(obj != null) {
			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
		}else {
			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		}
		
		parameterMap.put("P_APPR_CNTC_KEY", "B");
//		FwkTransferObject trans = iproPrpoBidReqService.bidReqDetail(parameterMap);
//		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		  
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 낙찰 탭에서 낙찰결과를 등록한다. 
	 *	</pre>
	 *
	 * @method Name : bidSbidDetail
	 * @Author : joo
	 * @Date   : 2020. 9. 22.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 22.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value="/bidSbidResultDetail" , method={RequestMethod.POST, RequestMethod.GET})
	public String bidSbidResultDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		// 리다이렉트 상세화면 이동을 위해 분기처리
		Object obj = RequestContextUtils.getInputFlashMap(request);
		if(obj != null) {
			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
		}else {
			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		}
		
		parameterMap.put("P_APPR_CNTC_KEY", "B");
//		FwkTransferObject trans = iproPrpoBidReqService.bidReqDetail(parameterMap);
//		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		   
		return parameterMap.getViewName(prefixUrl);
	}
	
	//엑셀다운로드 목록
	@RequestMapping(value="/bidPblancResultExcelDwld")
	public String bidPblancResultExcelDwld(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEbidPblancResultService.bidPblancResultExcelList(parameterMap);
		
		model.addAttribute("dataList",trans.get(iproEbidPblancResultService.BID_PBLANC_RESULT_LIST));
		model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("destFileName","입찰공고완료현황 목록");		
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
}
