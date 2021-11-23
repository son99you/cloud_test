package com.eunwoosoft.ipro.sytm.controller;

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

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.comm.vo.CommVo;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.ipro.sytm.service.IproSytmDsgnRecmService;


/**
 * 
 * com.eunwoosoft.ipro.sytm.controller
 * IproSytmContFormController.java
 *
 * @Author : 
 * @Date   : 2020.10.28
 *
 */
@RequestMapping(value = "/sytm")
@Controller
public class IproSytmDsgnRecmController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/sytm/";
	String popupPrefixUrl = "/ipro/views/sytm/popup/";
	
	@Resource(name="iproSytmDsgnRecmService")
	private IproSytmDsgnRecmService iproSytmDsgnRecmService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.controller.IproSytmDsgnRecmController.java
	 * @Method : dsgnRecmFileList
	 * @author : jane
	 * @date : 20201028 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dsgnRecmFileList")
	public String dsgnRecmFileList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmDsgnRecmService.dsgnRecmListWithPgng(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.controller.IproSytmDsgnRecmController.java
	 * @Method : dsgnRecmFileRegForm
	 * @author : jane
	 * @date : 20201028 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dsgnRecmFileRegForm")
	public String dsgnRecmFileRegForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
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
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.controller.IproSytmDsgnRecmController.java
	 * @Method : dsgnRecmFileRegist
	 * @author : jane
	 * @date : 2020. 10. 28. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/dsgnRecmFileRegist", method=RequestMethod.POST)
	public String dsgnRecmFileRegist(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		try{
			parameterMap.put("multipartRequest", multipartRequest);
			trans = iproSytmDsgnRecmService.dsgnRecmFileRegist(parameterMap);
			
			//Log 저장
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "SYTM");
			parameterMap.put("P_CONN_CNTN", "설계추천파일관리 작성");
			parameterMap.put("P_CONN_URL", "/sytm/dsgnRecmFileRegForm.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
					
			iproCommDefaultService.sendLOG(parameterMap);
			
			// redirect 이용 시 addFlashAttribute
			// url이 get방식 처럼 넘어가는 거 숨기면서 parameter넘기기 위함
			// 단, 일회성이므로 redirect한 이후에는 파라미터가 사라짐
			
			//기존에 사용한 세션이 있는경우 삭제
			if(request.getSession().getAttribute("resourceName") != null){
				request.getSession().removeAttribute("resourceName");
			}
			request.getSession().setAttribute("resourceName", (String)parameterMap.get("resourceName"));
			
//			CommVo commVo = new CommVo();
//			commVo.setpCdId(trans.get("P_CD_DTL_ID").toString());
//			
//			//기존에 사용한 세션이 있는경우 삭제
//			if(request.getSession().getAttribute("commVo") != null){
//				request.getSession().removeAttribute("commVo");
//			}
//			request.getSession().setAttribute("commVo", commVo);
//			
//			redirectAttributes.addFlashAttribute("parameterMap",parameterMap);
			
			request.getSession().setAttribute("parameterMap", parameterMap);
			
		}catch(Exception ex){
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}		 	
		return "redirect:/sytm/dsgnRecmFileDetail.do";
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.controller.IproSytmDsgnRecmController.java
	 * @Method : dsgnRecmFileDetail
	 * @author : jane
	 * @date : 20201028
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dsgnRecmFileDetail", method={RequestMethod.POST, RequestMethod.GET})
	public String dsgnRecmFileDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		/**
		 * request에 키값이 되는 값이 담겨있지 않으면 redirct로 넘어왔다고 판단하고
		 * request에 담겨있는 parameterMap값을 request.session에 담겨있는 parameterMap값으로  바꿔준다.
		 * 그리고 request.session에 담겨있는 requestUrl값을 먼저담겨있는 request.parameterMap 의 requestUrl으로 바꿔준다.
		 * 그래야지 jsp페이지를 정상적으로 찾을 수 있음. 
		 */
		if(parameterMap.get("P_CD_DTL_ID") == null || parameterMap.get("P_CD_DTL_ID").equals("")){
			String thisRequestUrl = parameterMap.get("requestUrl").toString();
			parameterMap = (FwkParameterMap) request.getSession().getAttribute(PARAMETER_MAP);
			parameterMap.put("requestUrl", thisRequestUrl);
			request.getSession().removeAttribute(PARAMETER_MAP);
		}
		
		trans = iproSytmDsgnRecmService.dsgnRecmFileDetail(parameterMap);
		
		
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
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.controller.IproSytmDsgnRecmController.java
	 * @Method : dsgnRecmFileUpdtForm
	 * @author : jane
	 * @date : 20201028
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dsgnRecmFileUpdtForm")
	public String dsgnRecmFileUpdtForm(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproSytmDsgnRecmService.dsgnRecmFileDetail(parameterMap);
		
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
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.controller.IproSytmDsgnRecmController.java
	 * @Method : dsgnRecmFileUpdate
	 * @author : jane
	 * @date : 20201028
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dsgnRecmFileUpdate", method=RequestMethod.POST)
	public String dsgnRecmFileUpdate(final HttpServletRequest request, final Model model, HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
			parameterMap.put("multipartRequest", multipartRequest);
			trans = iproSytmDsgnRecmService.dsgnRecmFileUpdate(parameterMap);
			 
			//Log 저장
			/**
			 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
			 * P_CONN_CNTN : 접속내용 (한글 코멘트)
			 * P_CONN_URL : 접속url
			 * P_MENU_ID : 메뉴ID (호출 함수명)
			 */
			parameterMap.put("P_SYS_CONN_SECD", "SYTM");
			parameterMap.put("P_CONN_CNTN", "설계추천파일관리 수정");
			parameterMap.put("P_CONN_URL", "/sytm/dsgnRecmFileUpdtForm.do");
			parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
					
			iproCommDefaultService.sendLOG(parameterMap);
			
			//기존에 사용한 세션이 있는경우 삭제
			if(request.getSession().getAttribute("resourceName") != null){
				request.getSession().removeAttribute("resourceName");
			}
			request.getSession().setAttribute("resourceName", (String)parameterMap.get("resourceName"));
			
//			CommVo commVo = new CommVo();
//			commVo.setpCdId(trans.get("P_CD_DTL_ID").toString());
//			
//			//기존에 사용한 세션이 있는경우 삭제
//			if(request.getSession().getAttribute("commVo") != null){
//				request.getSession().removeAttribute("commVo");
//			}
//			request.getSession().setAttribute("commVo", commVo);
//			
//			redirectAttributes.addFlashAttribute("parameterMap", parameterMap);
			
			request.getSession().setAttribute("parameterMap", parameterMap);
				
		}catch(Exception ex){
			response.setStatus(999); 
			throw new Exception(ex.toString());
		}		 	
		return "redirect:/sytm/dsgnRecmFileDetail.do";
		
	}
}
