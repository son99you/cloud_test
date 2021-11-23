package com.eunwoosoft.ipro.ebid.controller; 

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.eunwoosoft.comm.itfc.service.CommItfcService;
import com.eunwoosoft.comm.mail.service.CommMailService;
import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.comm.vo.CommVo;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkMessageUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproEbidPblancPrcnService;

/**
 * 
 * @author joo
 *
 */
@Controller
@RequestMapping(value = "/ebid")
public class IproEbidPblancPrcnController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";
	String prefixPopUrl = "/ipro/views/ebid/popup/";
	String prefixExcelUrl = "ipro/ebid/";
	
	@Resource(name="iproEbidPblancPrcnService")
	private IproEbidPblancPrcnService iproEbidPblancPrcnService;
	
	@Resource(name="commItfcService")
	private CommItfcService commItfcService;
	
	@Resource(name = "commMailService")
	private CommMailService commMailService;
	
	
	@RequestMapping(value = "/bidPblancPrcnList")
	public String bidPblancPrcnList(final HttpServletRequest request, final Model model) throws Exception {
		
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

				
		trans = iproEbidPblancPrcnService.bidPblancPrcnListInqireWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}

	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 입찰공고진행현황
	 *	</pre>
	 *
	 * @method Name : bidPblancPrcnDetail
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
	@RequestMapping(value="/bidPblancPrcnDetail" , method={RequestMethod.POST, RequestMethod.GET})
	public String bidPblancPrcnDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		// 리다이렉트 상세화면 이동을 위해 분기처리
		Object obj = RequestContextUtils.getInputFlashMap(request);
		if(obj != null) {
			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
		}else {
			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		}
		
		if(parameterMap.get("P_ANNC_NO") == null || parameterMap.get("P_ANNC_NO").equals("")){
			if(request.getSession().getAttribute("commVo") != null){		//NOPMD
				CommVo commVo = (CommVo)request.getSession().getAttribute("commVo");			
				parameterMap.put("P_ANNC_NO", commVo.getpAnncNo()); 
				parameterMap.put("P_ANNC_NGR", commVo.getpAnncNgr());
				parameterMap.put("P_ROUND_NO", commVo.getpRoundNo());
			}
		}
		
//		FwkTransferObject trans = iproPrpoBidReqService.bidReqDetail(parameterMap);
//		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		 
		return parameterMap.getViewName(prefixUrl);
	}
	
	@RequestMapping(value = "/popup/bidRtnRsnPopup")
	public String bidRtnRsnPopup(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 입찰취소팝업
	 *	</pre>
	 *
	 * @method Name : bidReqCanclePopup
	 * @Author : joo
	 * @Date   : 2020. 11. 3.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 11. 3.                 joo                최초작성
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
	@RequestMapping(value = "/popup/bidReqCanclePopup")
	public String bidReqCanclePopup(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
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
	@RequestMapping(value="/bidTchnEstmDetail" , method={RequestMethod.POST, RequestMethod.GET})
	public String bidTchnEstmDetail(final HttpServletRequest request, final Model model) throws Exception {
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
	@RequestMapping(value="/bidOpenDetail" , method={RequestMethod.POST, RequestMethod.GET})
	public String bidOpenDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		// 리다이렉트 상세화면 이동을 위해 분기처리
		Object obj = RequestContextUtils.getInputFlashMap(request);
		if(obj != null) {
			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
		}else {
			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		}
		
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
	@RequestMapping(value="/bidTchnNegoDetail" , method={RequestMethod.POST, RequestMethod.GET})
	public String bidTchnNegoDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		// 리다이렉트 상세화면 이동을 위해 분기처리
		Object obj = RequestContextUtils.getInputFlashMap(request);
		if(obj != null) {
			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
		}else {
			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		}
		
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
	@RequestMapping(value="/bidSbidDetail" , method={RequestMethod.POST, RequestMethod.GET})
	public String bidSbidDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		// 리다이렉트 상세화면 이동을 위해 분기처리
		Object obj = RequestContextUtils.getInputFlashMap(request);
		if(obj != null) {
			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
		}else {
			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		}
		
//		FwkTransferObject trans = iproPrpoBidReqService.bidReqDetail(parameterMap);
//		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		  
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	//업체정보 등록
	@RequestMapping(value="/vendRegist", method=RequestMethod.POST)
	public String vendRegist(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
//			trans = iproPrpoBidReqService.vendRegist(parameterMap);
			if("succ".equals(trans.get("stateUpdt"))){
				commMailService.commSend(parameterMap,"vendRegist");
			}
			model.addAllAttributes(trans);
			
		}catch(Exception ex){ 
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
	}
	
	
	@RequestMapping(value = "/vendDelete", method=RequestMethod.POST)
	public String vendDelete(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {			
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		try{
//			iproPrpoBidReqService.vendDelete(parameterMap);
			
			
		}catch(Exception ex){ 
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		
		return JSON_VIEW;
		
	}
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 기술평가 팝업
	 *	</pre>
	 *
	 * @method Name : tchnEstmPopup
	 * @Author : joo
	 * @Date   : 2020. 9. 15.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 15.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param request
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/popup/tchnEstmPopup", method={RequestMethod.POST, RequestMethod.GET})
	public String tchnEstmPopup(final HttpServletRequest request, final Model model) {		
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		// 	리다이렉트 상세화면 이동을 위해 분기처리
		Object obj = RequestContextUtils.getInputFlashMap(request);
		if(obj != null) {
			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
		}else {
			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		}
		
//		FwkTransferObject trans = iproPrpoBidReqService.tchnEstmPopup(parameterMap);	
		
//		trans.put("P_REGIST",parameterMap.get("P_REGIST"));
//		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 기술평가등록
	 *	</pre>
	 *
	 * @method Name : tchnEstmRegist
	 * @Author : joo
	 * @Date   : 2020. 9. 18.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 18.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param request
	 * @param model 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value="/tchnEstmRegist", method=RequestMethod.POST)
	public String tchnEstmRegist(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);

			trans = iproEbidPblancPrcnService.tchnEstmRegist(parameterMap); 
			
			parameterMap.put("P_REGIST", "succ");
			redirectAttributes.addFlashAttribute("parameterMap",parameterMap);
		}catch(Exception ex){
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}		  
		
		return "redirect:/ebid/popup/tchnEstmPopup.do"; 
	}
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 적격심사결과를 등록한다. 
	 *	</pre>
	 *
	 * @method Name : elgbEstmRegist
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
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value="/elgbEstmRegist", method=RequestMethod.POST)
	public String elgbEstmRegist(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);

			trans = iproEbidPblancPrcnService.elgbEstmRegist(parameterMap); 
			
			parameterMap.put("P_REGIST", "succ");
			redirectAttributes.addFlashAttribute("parameterMap",parameterMap);
		}catch(Exception ex){
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}		  
		
		return "redirect:/ebid/popup/elgbEstmPopup.do"; 
	}
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 :적격심사 팝업
	 *	</pre>
	 *
	 * @method Name : elgbEstmPopup
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
	 *
	 */
	@RequestMapping(value = "/popup/elgbEstmPopup", method={RequestMethod.POST, RequestMethod.GET})
	public String elgbEstmPopup(final HttpServletRequest request, final Model model) {		
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		// 	리다이렉트 상세화면 이동을 위해 분기처리
		Object obj = RequestContextUtils.getInputFlashMap(request);
		if(obj != null) {
			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
		}else {
			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		}
		
//		FwkTransferObject trans = iproPrpoBidReqService.elgbEstmPopup(parameterMap);	
		
//		trans.put("P_REGIST" ,parameterMap.get("P_REGIST"));
//		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 제안/규격서 등록폼 화면
	 *	</pre>
	 *
	 * @method Name : tchnFilePopup
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
	 *
	 */
	@RequestMapping(value = "/popup/tchnFilePopup", method={RequestMethod.POST, RequestMethod.GET})
	public String tchnFilePopup(final HttpServletRequest request, final Model model) {		
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
//		FwkTransferObject trans = new FwkTransferObject();
		// 	리다이렉트 상세화면 이동을 위해 분기처리
		Object obj = RequestContextUtils.getInputFlashMap(request);
		if(obj != null) {
			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
		}else {
			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		}
		
//		trans = iproPrpoBidReqService.tchnFilePopup(parameterMap);	
//		trans.put("P_REGIST",parameterMap.get("P_REGIST"));
		
		model.addAllAttributes(parameterMap);
//		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 제안/규격서 서류 등록합니다. 
	 *	</pre>
	 *
	 * @method Name : tchnFileRegist
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
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value="/tchnFileRegist", method=RequestMethod.POST)
	public String tchnFileRegist(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);

			trans = iproEbidPblancPrcnService.tchnFileRegist(parameterMap); 
			
			parameterMap.put("P_REGIST", "succ");
			redirectAttributes.addFlashAttribute("parameterMap",parameterMap);
		}catch(Exception ex){
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}		  
		
		return "redirect:/ebid/popup/tchnFilePopup.do"; 
	}
	
	/** 
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 산출내역서 등록 팝업 
	 *	</pre>
	 *
	 * @method Name : clcCntnFilePopup
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
	 *
	 */
	@RequestMapping(value = "/popup/clcCntnFilePopup", method={RequestMethod.POST, RequestMethod.GET})
	public String clcCntnFilePopup(final HttpServletRequest request, final Model model) {		
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		// 	리다이렉트 상세화면 이동을 위해 분기처리
		Object obj = RequestContextUtils.getInputFlashMap(request);
		if(obj != null) {
			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
		}else {
			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		}
		
//		FwkTransferObject trans = iproPrpoBidReqService.clcCntnFilePopup(parameterMap);	
		
//		trans.put("P_REGIST", parameterMap.get("P_REGIST"));
//		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 산출내역서 등록
	 *	</pre>
	 *
	 * @method Name : clcCntnFileRegist
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
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value="/clcCntnFileRegist", method=RequestMethod.POST)
	public String clcCntnFileRegist(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);

			trans = iproEbidPblancPrcnService.clcCntnFileRegist(parameterMap); 
			
			parameterMap.put("P_REGIST", "succ");
			redirectAttributes.addFlashAttribute("parameterMap",parameterMap);
		}catch(Exception ex){
			response.setStatus(999); 
			throw new Exception(ex.toString()); 
		}		   
		 
		return "redirect:/ebid/popup/clcCntnFilePopup.do"; 
	}
	
	
	@RequestMapping(value = "/popup/negoFilePopup", method={RequestMethod.POST, RequestMethod.GET})
	public String negoFilePopup(final HttpServletRequest request, final Model model) {		
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		// 	리다이렉트 상세화면 이동을 위해 분기처리
		Object obj = RequestContextUtils.getInputFlashMap(request);
		if(obj != null) {
			parameterMap =  (FwkParameterMap) RequestContextUtils.getInputFlashMap(request).get("parameterMap");
			parameterMap.put("requestUrl",((FwkParameterMap)request.getAttribute(PARAMETER_MAP)).get("requestUrl"));
		}else {
			parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		}
		
//		FwkTransferObject trans = iproPrpoBidReqService.negoFilePopup(parameterMap);	
		
//		trans.put("P_REGIST", parameterMap.get("P_REGIST"));
//		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	@RequestMapping(value="/negoFileRegist", method=RequestMethod.POST)
	public String negoFileRegist(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);

			trans = iproEbidPblancPrcnService.negoFileRegist(parameterMap);  
			 
			parameterMap.put("P_REGIST", "succ");
			redirectAttributes.addFlashAttribute("parameterMap",parameterMap);
		}catch(Exception ex){
			response.setStatus(999);  
			throw new Exception(ex.toString());
		}		   
		 
		return "redirect:/ebid/popup/negoFilePopup.do"; 
	}
	
	
	//공고요청 반려, 요청접수 반려 처리 
	@RequestMapping(value = "/bidReqRtnStatUpdt")
	public String bidReqRtnStatUpdt(final HttpServletRequest request, final Model model, HttpServletResponse response) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = iproEbidPblancPrcnService.bidReqRtnStatUpdt(parameterMap);
			if("succ".equals(trans.get("stateUpdt"))){
				commMailService.commSend(parameterMap,"bidReqRtnStatUpdt");
			}
			model.addAllAttributes(trans);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}
	
	
	//엑셀다운로드 목록
	@RequestMapping(value="/bidPblancPrcnExcelDwld")
	public String bidPblancPrcnExcelDwld(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEbidPblancPrcnService.bidPblancPrcnExcelList(parameterMap);
		
		model.addAttribute("dataList",trans.get(iproEbidPblancPrcnService.BID_PBLANC_PRCN_LIST));
		model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("destFileName","입찰공고진행현황 목록");		
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);		
		
		return EXCEL_VIEW;
	}
	
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 개찰등록 저장완료
	 *	</pre>
	 *
	 * @method Name : openResultRegist
	 * @Author : joo
	 * @Date   : 2020. 10. 8.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 10. 8.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param request
	 * @param model
	 * @param response
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value="/openResultRegist", method=RequestMethod.POST)
	public String openResultRegist(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);

			trans = iproEbidPblancPrcnService.openResultRegist(parameterMap);  
			
			redirectAttributes.addFlashAttribute("parameterMap",parameterMap);
		}catch(Exception ex){
			response.setStatus(999);  
			throw new Exception(ex.toString());
		}		   
		 
		return "redirect:/ebid/bidOpenDetail.do"; 
	}
	
	@RequestMapping(value="/techResultRegist", method=RequestMethod.POST)
	public String techResultRegist(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
//		FwkTransferObject trans = new FwkTransferObject();
		try{
			MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);
			
//			trans = iproPrpoBidReqService.vendRegist(parameterMap);  
			
			redirectAttributes.addFlashAttribute("parameterMap",parameterMap);
		}catch(Exception ex){
			response.setStatus(999);  
			throw new Exception(ex.toString());
		}		   
		
		return "redirect:/ebid/bidTchnNegoDetail.do"; 
	}
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 :
	 *	</pre>
	 *
	 * @method Name : bidPblancPrcnUpdtForm
	 * @Author : joo
	 * @Date   : 2020. 10. 13.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 10. 13.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value="/bidPblancPrcnUpdtForm")
	public String bidPblancPrcnUpdtForm(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		System.err.println("<=============bidReqPrcnUpdtForm = = = = = = = >  " + parameterMap.getViewName(prefixUrl));
//		FwkTransferObject trans = iproPrpoBidReqService.bidReqDetail(parameterMap);
		
//		model.addAttribute("trans", trans);
//		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getViewName(prefixUrl);
	}
}
