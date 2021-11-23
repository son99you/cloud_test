package com.eunwoosoft.opro.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.comm.vo.CommVo;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.user.service.OproUserVendInfoService;


/**
 * 
 * com.eunwoosoft.opro.user.controller
 * OproUserVendInfoController.java
 *
 * @Author : JanDi_Eun
 * @Date   : 2019. 3. 8.
 *
 */
@RequestMapping(value = "/opro/user")
@Controller
public class OproUserVendInfoController extends AbstractOproMenuController {
	String prefixUrl = "/opro/views/user/";
	String prefixUrlPopup = "/opro/views/user/popup/";
	
	@Resource(name="oproUserVendInfoService")
	private OproUserVendInfoService oproUserVendInfoService;
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 자사정보 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.user.controller.OproUserVendInfoController.java
	 * @Method : vendInfoDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 8. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/vendInfoDetail")
	public String vendInfoDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = oproUserVendInfoService.vendInfoDetail(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	
	@RequestMapping(value = "/bakFilePage")
	public String bakFilePage(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		model.addAllAttributes(parameterMap); 
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	@RequestMapping(value = "/bakFileSave")
	public String bakFileSave(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		try{
			parameterMap.put("multipartRequest", multipartRequest);
			trans = oproUserVendInfoService.bakFileSave(parameterMap);
			
			// redirect 이용 시 addFlashAttribute
			// url이 get방식 처럼 넘어가는 거 숨기면서 parameter넘기기 위함
			// 단, 일회성이므로 redirect한 이후에는 파라미터가 사라짐
			
			redirectAttributes.addFlashAttribute("parameterMap",parameterMap);
		}catch(Exception ex){
			response.setStatus(999);  
			throw new Exception(ex.toString());
		}		 	
		return "redirect:/opro/user/bakFilePage.do";
	}
	 
	
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 자사정보 수정
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.user.controller.OproUserVendInfoController.java
	 * @Method : vendInfoUpdt
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 8. 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/vendInfoUpdt")
	public String vendInfoUpdt(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {
		FwkTransferObject trans = new FwkTransferObject();
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
		parameterMap.put("multipartRequest", multipartRequest);
		trans = oproUserVendInfoService.vendInfoUpdt(parameterMap);
		
		parameterMap.put("P_TRANS_RESULT", "SUCC");
		model.addAllAttributes(parameterMap);
		
		if(trans.get("P_VEND_STCD").equals("E")) {
			trans.put("P_VEND_STCD_MSG", "신규 가입 또는 정보 수정 후에는\n한국전기연구원 구매자산실 담당자의\n승인 후에 입찰/견적 참여 가능.\n승인 후 별도의 알림메시지 발송.\n(문의 : 055-280-1234)");
			model.addAllAttributes(trans);
			return "forward:/opro/main/emgncLoginForm.do";			
		}else {
			return "forward:/opro/user/vendInfoDetail.do";	
		}
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 비밀번호 변경 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.user.controller.OproUserVendInfoController.java
	 * @Method : vendPwdChngRegistForm
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 22. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/popup/vendPwdChngRegistForm")
	public String vendPwdChngRegistForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproPopupViewName(prefixUrlPopup);		
	}
	

	/**
	 * 
	 * <pre>
	 * 1.개요 : 비밀번호 변경 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.opro.user.controller.OproUserVendInfoController.java
	 * @Method : vendPwdChngRegist
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 22. 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/vendPwdChngRegist")
	public String vendPwdChngRegist(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);

		try{
			
			FwkTransferObject trans = oproUserVendInfoService.vendPwdChngRegist(parameterMap);
			
			model.addAttribute("trans", trans);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
			
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return JSON_VIEW;
	}
	

}
