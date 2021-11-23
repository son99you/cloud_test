package com.eunwoosoft.ipro.comm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.ipro.comm.service.IproCommPopupService;
import com.eunwoosoft.ipro.estm.service.IproEstmCmtmMngPoolService;
import com.eunwoosoft.ipro.noti.service.IproTrmService;

/**
 * 팝업
 * com.eunwoosoft.ipro.comm.controller
 * IproCommPopupController.java
 *
 * @Author : SungYoon_Ha
 * @Date   : 2017. 6. 15.
 *
 */
@RequestMapping(value = "/comm/popup")
@Controller
public class IproCommPopupController extends AbstractFwkController {
	String prefixUrl = "/ipro/views/comm/popup/";
	String prefixUrl2 = "/ipro/views/comm/";

	@Resource(name="iproCommPopupService")
	private IproCommPopupService iproCommPopupService;
	
	@Resource(name="iproTrmService")
	private IproTrmService iproTrmService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	@Resource(name="iproEstmCmtmMngPoolService")
	private IproEstmCmtmMngPoolService iproEstmCmtmMngPoolService;
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 반려팝업 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : returnPrpo
	 * @author : YeonWooSon
	 * @date : 2017. 6. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/return")
	public String returnPrpo(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);

		return parameterMap.getPopupViewName(prefixUrl);
	}

	/**
	 * <pre>
	 * 1.개요 : 평가위원POOL현황 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.estm.controller.IproEstmCmtmMngPoolController.java
	 * @Method : cmtmMngPoolDetail
	 * @author : 
	 * @date : 2021. 3. 23.
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/estmCmtmMngPoolDetail", method={RequestMethod.POST, RequestMethod.GET})
	public String estmCmtmMngPoolDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		/**
		 * request에 키값이 되는 값이 담겨있지 않으면 redirct로 넘어왔다고 판단하고
		 * request에 담겨있는 parameterMap값을 request.session에 담겨있는 parameterMap값으로  바꿔준다.
		 * 그리고 request.session에 담겨있는 requestUrl값을 먼저담겨있는 request.parameterMap 의 requestUrl으로 바꿔준다.
		 * 그래야지 jsp페이지를 정상적으로 찾을 수 있음. 
		 */
		if(parameterMap.get("P_ESTM_CMTM_NO") == null || parameterMap.get("P_ESTM_CMTM_NO").equals("")){
			String thisRequestUrl = parameterMap.get("requestUrl").toString();
			parameterMap = (FwkParameterMap) request.getSession().getAttribute(PARAMETER_MAP);
			parameterMap.put("requestUrl", thisRequestUrl);
			request.getSession().removeAttribute(PARAMETER_MAP);
		}
		
		trans = iproEstmCmtmMngPoolService.cmtmMngPoolDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getPopupViewName(prefixUrl);
	}

	@RequestMapping(value="/estmCmtmMngCrtrDetail", method={RequestMethod.POST, RequestMethod.GET})
	public String estmCmtmMngCrtrDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		/**
		 * request에 키값이 되는 값이 담겨있지 않으면 redirct로 넘어왔다고 판단하고
		 * request에 담겨있는 parameterMap값을 request.session에 담겨있는 parameterMap값으로  바꿔준다.
		 * 그리고 request.session에 담겨있는 requestUrl값을 먼저담겨있는 request.parameterMap 의 requestUrl으로 바꿔준다.
		 * 그래야지 jsp페이지를 정상적으로 찾을 수 있음. 
		 *//*
		if(parameterMap.get("P_ESTM_OBJ_PE_NO") == null || parameterMap.get("P_ESTM_OBJ_PE_NO").equals("")){
			String thisRequestUrl = parameterMap.get("requestUrl").toString();
			parameterMap = (FwkParameterMap) request.getSession().getAttribute(PARAMETER_MAP);
			parameterMap.put("requestUrl", thisRequestUrl);
			request.getSession().removeAttribute(PARAMETER_MAP);
		}*/
		
		trans = iproCommPopupService.cmtmMngCrtrDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 내부 담당자 조회 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : chargerList
	 * @author : SungYoon_Ha
	 * @date : 2017. 6. 15. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/chargerList")
	public String chargerList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.chargerListInqireWithPgng(parameterMap);
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);

		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업채 담당자 조회 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : vendChargerList
	 * @author : 맹경열
	 * @date : 2018. 10. 11. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendChargerList")
	public String vendChargerList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		parameterMap.put("P_USER_FLAG_S", "Y");
		FwkTransferObject trans = iproCommPopupService.entrpsInqireList(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 담당자 조회 Ajax
	 * 2. 처리내용 : 
     *      - 담당자 조회 서비스를 호출한다.
     *      - 담당자 조회 결과를 Model에 담는다.
     *      - JSON_VIEW로 반환한다.
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : chargerInqireAsync
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 22. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/chargerInqireAsync")
	public String chargerInqireAsync(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.chargerListInqireWithPgng(parameterMap);
		
		int cnt = (Integer) trans.get(IproCommPopupService.CHARGER_LIST_TOTCNT);
		
		if(cnt == 1){
			model.addAttribute("chargerList", trans.get(IproCommPopupService.CHARGER_LIST));
		}
		model.addAttribute("cnt", cnt);
		
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 구매요구접수 목록 팝업 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : purdmdList
	 * @author : YeonWooSon
	 * @date : 2017. 6. 27. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/purdmdList")
	public String purdmdList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);

		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	@RequestMapping(value = "/lawordInqireList")
	public String lawordInqireList(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.lawordInqireList(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 부서목록 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : deptList
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 16.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/deptList")
	public String deptList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.deptInqireList(parameterMap);
		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);  
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	@RequestMapping(value="/meetSendMail")
	public String meetSendMail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.meetSendMail(parameterMap);
		
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}
	
	
	
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 부서목록 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : deptYMList
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 16.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/deptYMList")
	public String deptYMList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.deptYMList(parameterMap);
		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);  
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가서식리스트 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmFrmPopupList
	 * @author : 손연우
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmFrmPopupList")
	public String estmFrmPopupList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.estmFrmPopupList(parameterMap);
		
		model.addAllAttributes(trans);  
		model.addAllAttributes(parameterMap);  
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 도움말
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : helpPopDetail
	 * @author : 손연우
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/helpPopDetail")
	public String helpPopDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		//FwkTransferObject trans = iproCommPopupService.deptInqireList(parameterMap);
		//model.addAllAttributes(trans);  
		model.addAllAttributes(parameterMap);  
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가관리시스템 - 회의참가요청
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : mtngPrtcRqstList
	 * @author : 손연우
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mtngPrtcRqstList")
	public String mtngPrtcRqstList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		/**
		 * request에 키값이 되는 값이 담겨있지 않으면 redirct로 넘어왔다고 판단하고
		 * request에 담겨있는 parameterMap값을 request.session에 담겨있는 parameterMap값으로  바꿔준다.
		 * 그리고 request.session에 담겨있는 requestUrl값을 먼저담겨있는 request.parameterMap 의 requestUrl으로 바꿔준다.
		 * 그래야지 jsp페이지를 정상적으로 찾을 수 있음. 
		 */
		if(parameterMap.get("P_ESTM_NO") == null || parameterMap.get("P_ESTM_NO").equals("")){
			String thisRequestUrl = parameterMap.get("requestUrl").toString();
			parameterMap = (FwkParameterMap) request.getSession().getAttribute(PARAMETER_MAP);
			parameterMap.put("requestUrl", thisRequestUrl);
			request.getSession().removeAttribute(PARAMETER_MAP);
		}
		
		
		
		FwkTransferObject trans = iproCommPopupService.mtngPrtcRqstList(parameterMap);
		
		model.addAllAttributes(trans);  
		model.addAllAttributes(parameterMap);  
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 부서 조회 Ajax 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bsnsDeptInqireAsync
	 * @date : 2015. 8. 27.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 8. 27.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/bsnsDeptInqireAsync")
	public String bsnsDeptInqireAsync(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.deptInqireList(parameterMap);
		
		int cnt = (Integer) trans.get(IproCommPopupService.DEPT_INQIRE_LIST_TOTCNT);
		
		if(cnt == 1){
			model.addAttribute("bsnsDeptList", trans.get(IproCommPopupService.DEPT_INQIRE_LIST));
		}
		model.addAttribute("cnt", cnt);
		
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 취소팝업 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : canclCurstatRegistForm
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 5. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/canclCurstatRegistForm")
	public String canclCurstatRegistForm(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		model.addAllAttributes(parameterMap);

		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 취소공고 저장
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : canclCurstatRegist
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 5. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/canclCurstatRegist")
	public String canclCurstatRegist(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.canclCurstatRegist(parameterMap);	
	
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return JSON_VIEW;
	}	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체목록 팝업 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : entrpsList
	 * @author : YeonWooSon
	 * @date : 2017. 6. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/entrpsList")
	public String entrpsList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject(); 
		if("Y".equals(parameterMap.getString("P_SEARCH_S"))){ 
			trans = iproCommPopupService.entrpsInqireList(parameterMap);
		} 
		model.addAllAttributes(trans);

		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	@RequestMapping(value="/fileSamplePopup")
	public String fileSamplePopup(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.fileSampleLIst(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	@RequestMapping(value="/tchnFileSamplePopup")
	public String tchnFileSamplePopup(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		//FwkTransferObject trans = iproCommPopupService.fileSampleLIst(parameterMap);
		//model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 :	  이전 자동 첨부파일 내역 보기
	 *	</pre>
	 *
	 * @method Name : contFormFileList
	 * @Author : joo
	 * @Date   : 2020. 9. 2.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 9. 2.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 * @param request
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value="/contFormFileList")
	public String contFormFileList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.contFormFileList(parameterMap);
		model.addAllAttributes(trans);

		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : entrpsInqireNotInList
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 30. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/entrpsInqireNotInList")
	public String entrpsInqireNotInList(final HttpServletRequest request, final Model model){
		// 해당계약의 계약업체를 제외한 업체 목록 조회 팝업
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.entrpsInqireNotInList(parameterMap);
//		FwkTransferObject trans = iproCommPopupService.entrpsInqireList(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 견적의뢰업체 팝업 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : prpoEntrpsList
	 * @author : YeonWooSon
	 * @date : 2017. 7. 6. 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/prpoEntrpsList")
	public String prpoEntrpsList(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		
		try{
			trans = iproCommPopupService.prpoEntrpsInqireList(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}

		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 세금계산서 불러오기 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : invoiceTaxSupList
	 * @author : SungYoon_Ha
	 * @date : 2017. 6. 22. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/invoiceTaxSupList")
	public String invoiceTaxSupList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 세금계산서 불러오기 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : invoiceTaxModiSupList
	 * @author : SungYoon_Ha
	 * @date : 2017. 6. 22. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/invoiceTaxModiSupList")
	public String invoiceTaxModiSupList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 계약첨부파일 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : cntrctFileList
	 * @author : YeonWooSon
	 * @date : 2017. 6. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cntrctFileList")
	public String cntrctFileList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);

		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원이 평가대상을 평가한 평가표 팝업 - 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmCmtmEstmFrmDetail
	 * @author : 손연우
	 * @date : 2021. 3. 22. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmCmtmEstmFrmDetail")
	public String estmCmtmEstmFrmDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.estmCmtmEstmFrmDetail(parameterMap);
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원이 평가대상을 평가한 평가표 팝업 - 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmCmtmEstmFrmSave
	 * @author : 손연우
	 * @date : 2021. 3. 22. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmCmtmEstmFrmSave")
	public String estmCmtmEstmFrmSave(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.estmCmtmEstmFrmSave(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "ESTM");
		parameterMap.put("P_CONN_CNTN", "내부평가위원 평가대상 배점 저장");
		parameterMap.put("P_CONN_URL", "/comm/popup/estmCmtmEstmFrmDetail.do");
		parameterMap.put("P_MENU_ID", "estmCmtmEstmFrmDetail");
		
		iproCommDefaultService.sendLOG(parameterMap);
		
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		
		return "forward:/comm/popup/estmCmtmEstmFrmDetail.do?resultCode=Success";
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원이 평가대상을 평가한 평가표 팝업 - 평가담당자 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmCmtmEstmFrmDetail
	 * @author : 손연우
	 * @date : 2021. 3. 22. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmCmtmEstmFrmBDetail")
	public String estmCmtmEstmFrmBDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.estmCmtmEstmFrmBDetail(parameterMap);
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}

	/**
	 * <pre>
	 * 1.개요 : 평가위원이 평가대상을 평가한 평가표 팝업 - 평가담당자 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmCmtmEstmFrmBSave
	 * @author : 손연우
	 * @date : 2021. 3. 22. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmCmtmEstmFrmBSave")
	public String estmCmtmEstmFrmBSave(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.estmCmtmEstmFrmBSave(parameterMap);
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		
		//return parameterMap.getPopupViewName(prefixUrl);
		
		return "forward:/comm/popup/estmCmtmEstmFrmBDetail.do?resultCode=Success";
				//?P_ESTM_NO=" + parameterMap.get("P_ESTM_NO") +
				/*"&P_ESTM_PROCD_SEQ=" + parameterMap.get("P_ESTM_PROCD_SEQ") +
				"&P_ESTM_OBJ_SEQ=" + parameterMap.get("P_ESTM_OBJ_SEQ") +
				"&P_ESTM_CMTM_NO=" + parameterMap.get("P_ESTM_CMTM_NO") +
				"&resultCode=Success";*/
	}
	
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원이 평가대상을 평가한 평가표 팝업 - 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmCmtmEstmFrmRegForm
	 * @author : 손연우
	 * @date : 2021. 3. 22. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmCmtmEstmFrmRegForm")
	public String estmCmtmEstmFrmDetestmCmtmEstmFrmRegFormail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가절차리스트에서  평가표 팝업 - 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmFrmDetail
	 * @author : 손연우
	 * @date : 2021. 3. 22. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/estmFrmDetail")// 팝업
	public String estmFrmDetail(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.estmFrmDetail(parameterMap);
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가구분에서  평가표 팝업 - 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmSeMngFrmDetail
	 * @author : 손연우
	 * @date : 2021. 3. 22. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/estmSeMngFrmDetail")// 팝업
	public String estmSeMngFrmDetail(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.estmSeMngFrmDetail(parameterMap);
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.controller.IproSytmContFormController.java
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
		
		trans = iproCommPopupService.estmFrmDetail(parameterMap);
		
		model.addAllAttributes(parameterMap); // 안넘기면 메뉴가 이상하게 나옴
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.sytm.controller.IproSytmContFormController.java
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
		
			
		trans = iproCommPopupService.estmFrmUpdt(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		/*parameterMap.put("P_SYS_CONN_SECD", "ESTM");
		parameterMap.put("P_CONN_CNTN", "평가서식 수정");
		parameterMap.put("P_CONN_URL", "/estm/estmFrmUpdt.do");
		parameterMap.put("P_MENU_ID", parameterMap.get("resourceName"));
				
		iproCommDefaultService.sendLOG(parameterMap);*/
		
		/*parameterMap.put("P_ESTM_FRM_NO", trans.get("P_ESTM_FRM_NO"));
		trans.put("P_ESTM_FRM_NO", parameterMap.get("P_ESTM_FRM_NO"));*/
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "forward:/comm/popup/estmFrmDetail.do";
		
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 계약진행현황 계약서보기 팝업
	 * 2.처리내용 : 광해참조
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : cntrctView
	 * @author : JanDi_Eun
	 * @date : 2017. 6. 27. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cntrctView")
	public String cntrctView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 실적증명신청현황 신청서출력 팝업
	 * 2.처리내용 : 광해참조
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : acprRqstDocView
	 * @author : JanDi_Eun
	 * @date : 2017. 6. 27. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/acprRqstDocView")
	public String acprRqstDocView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 업체 팝업 리스트 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : ordrCmpnyList
	 * @author : MKY
	 * @date : 2017. 6. 27. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ordrCmpnyList")
	public String ordrCmpnyList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		if(parameterMap.get("pageNo") == null || parameterMap.get("pageNo").equals("")) {
			model.addAttribute("pageNo", "1");
		}
		model.addAllAttributes(parameterMap);

		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 수신자 업체 팝업 리스트 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : mssgeCmpnyList
	 * @author : MKY
	 * @date : 2017. 7. 4. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mssgeCmpnyList")
	public String mssgeCmpnyList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		if(parameterMap.get("pageNo") == null || parameterMap.get("pageNo").equals("")) {
			model.addAttribute("pageNo", "1");
		}
		model.addAllAttributes(parameterMap);

		return parameterMap.getPopupViewName(prefixUrl);
	}	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 품목 팝업 리스트
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : ordrItemList
	 * @author : MKY
	 * @date : 2017. 6. 27. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ordrItemList")
	public String ordrItemList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		if(parameterMap.get("pageNo") == null || parameterMap.get("pageNo").equals("")) {
			model.addAttribute("pageNo", "1");
		}
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 반려팝업
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : returnRegistForm
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 6. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/returnRegistForm")
	public String returnRegistForm(final HttpServletRequest request, final Model model) throws Exception {		
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		model.addAllAttributes(parameterMap); 
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 반려사유 등록
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : returnRegist
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 6. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/returnRegist")
	public String returnRegist(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.returnRegist(parameterMap);	
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 계약관리 반려 팝업
	 * 2.처리내용 : 광해참조
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : contReturn
	 * @author : JanDi_Eun
	 * @date : 2017. 7. 7. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/contReturn")
	public String contReturn(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 전자세금계산서 인쇄 팝업
	 * 2.처리내용 : 스마트빌 참조
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : invoicePrint
	 * @author : SungYoon Ha
	 * @date : 2017. 8. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/invoicePrint")
	public String invoicePrint(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		

		// 데이터 세팅
		String gbn = parameterMap.getString("gbn");
		
		String issue_id = "";

		String dem_regnum = "";
		String dem_vendor_name = "";
		String dem_owner_name = "";
		String dem_addr = "";
		String dem_biz_type = "";
		String dem_biz_kind = "";
		
		String sup_regnum = "";
		String sup_vendor_name = "";
		String sup_owner_name = "";
		String sup_addr = "";
		String sup_biz_type = "";
		String sup_biz_kind = "";
		
		String pubDate = "";
		String pubDateMM = "";
		String pubDateDD = "";
		String amtBlank = "";
		String amtSum = "";
		String surtax = "";
		String remark = "";
		
		String itemDateMM = "";
		String itemDateDD = "";
		
		String itemName = "";
		String itemStd = "";
		String itemQty = "";
		String itemPrice = "";
		String itemAmt = "";
		String itemStax = "";
		String itemRemark = "";

		String amtTotal = "";
		String amtCash = "";
		String amtCheck = "";
		String amtBill = "";
		String amtCredit = "";
		
		String recept_name = "";
		
		
		if("Z1".equals(gbn)){
			issue_id = "20170630041000008f2g009ft";

			dem_regnum = "402 - 85 - 13635";
			dem_vendor_name = "㈜휴비스(전주)";
			dem_owner_name = "신유동";
			dem_addr = "전북 전주시 덕진구 기린대로 787(팔복동2가)";
			dem_biz_type = "제조,부동산";
			dem_biz_kind = "합성섬유,재생섬유,임대";
			
			sup_regnum = "119 - 85 - 35038";
			sup_vendor_name = "코오롱글로벌(주)상사부문";
			sup_owner_name = "윤창운";
			sup_addr = "인천광역시 연수구 송도과학로 32(송도동, 송도IT센터S501호, S701호~S1304호)";
			sup_biz_type = "도매및소매업외";
			sup_biz_kind = "상품종합도소매외";
			
			pubDate = "2017";
			pubDateMM = "06";
			pubDateDD = "30";
			amtBlank = "3";
			amtSum = "975875947";
			surtax = "0";
			remark = "L0226706WS00267, USD 856.332";

			int a= 0 ;
			for(int i=12; i>0; i--){
				if( amtSum.length() >= i){
					parameterMap.put("amtSum"+i, amtSum.substring(a, a+1));
					a++;
				}else{
					parameterMap.put("amtSum"+i, "");
				}
			}
			
			int b= 0 ;
			for(int i=11; i>0; i--){
				if( surtax.length() >= i){
					parameterMap.put("surtax"+i, surtax.substring(b, b+1));
					b++;
				}else{
					parameterMap.put("surtax"+i, "");
				}
			}
			
			itemDateMM = "";
			itemDateDD = "";
			
			itemName = "전)CP-3,5,6 외 0건";
			itemStd = "";
			itemQty = "";
			itemPrice = "";
			itemAmt = "975,875,947";
			itemStax = "0";
			itemRemark = "";

			amtTotal = "975,875,947";
			amtCash = "0";
			amtCheck = "0";
			amtBill = "0";
			amtCredit = "0";
			
			recept_name = "청구";
		}else if("Z2".equals(gbn)){
			issue_id = "2016053041000008f2g0002n";

			dem_regnum = "402 - 85 - 13635";
			dem_vendor_name = "㈜휴비스(전주)";
			dem_owner_name = "유배근";
			dem_addr = "전북 전주시 덕진구 기린대로 787(팔복동2가)";
			dem_biz_type = "제조,부동산";
			dem_biz_kind = "합성섬유,재생섬유,임대";
			
			sup_regnum = "120 - 86 - 49524";
			sup_vendor_name = "사우산업개발(주)";
			sup_owner_name = "고두헌";
			sup_addr = "경기 성남시 중원구 상대원동 144-3 우림라이온스밸리";
			sup_biz_type = "제조업 외";
			sup_biz_kind = "철물공사 실내건축 공사업외";
			
			pubDate = "2016";
			pubDateMM = "05";
			pubDateDD = "30";
			amtBlank = "6";
			amtSum = "325000";
			surtax = "32500";
			remark = "";
			

			int a= 0 ;
			for(int i=12; i>0; i--){
				if( amtSum.length() >= i){
					parameterMap.put("amtSum"+i, amtSum.substring(a, a+1));
					a++;
				}else{
					parameterMap.put("amtSum"+i, "");
				}
			}
			
			int b= 0 ;
			for(int i=11; i>0; i--){
				if( surtax.length() >= i){
					parameterMap.put("surtax"+i, surtax.substring(b, b+1));
					b++;
				}else{
					parameterMap.put("surtax"+i, "");
				}
			}
			
			itemDateMM = "";
			itemDateDD = "";
			
			itemName = "J2 LMF 외 0건";
			itemStd = "";
			itemQty = "";
			itemPrice = "";
			itemAmt = "325,000";
			itemStax = "32,500";
			itemRemark = "";

			amtTotal = "357,500";
			amtCash = "0";
			amtCheck = "0";
			amtBill = "0";
			amtCredit = "0";
			
			recept_name = "청구";
		}else if("Z3".equals(gbn)){
			issue_id = "2016113041000008f2g00491";

			dem_regnum = "402 - 85 - 13635";
			dem_vendor_name = "㈜휴비스(전주)";
			dem_owner_name = "유배근";
			dem_addr = "전북 전주시 덕진구 기린대로 787(팔복동2가)";
			dem_biz_type = "제조,부동산";
			dem_biz_kind = "합성섬유,재생섬유,임대";
			
			sup_regnum = "127 - 81 - 95058";
			sup_vendor_name = "(주)포스프";
			sup_owner_name = "김완수";
			sup_addr = "경기도 포천시 가산면 금현리 585-2 ***";
			sup_biz_type = "도매";
			sup_biz_kind = "합성섬유";
			
			pubDate = "2016";
			pubDateMM = "11";
			pubDateDD = "30";
			amtBlank = "3";
			amtSum = "185838000";
			surtax = "18583800";
			remark = "";
			

			int a= 0 ;
			for(int i=12; i>0; i--){
				if( amtSum.length() >= i){
					parameterMap.put("amtSum"+i, amtSum.substring(a, a+1));
					a++;
				}else{
					parameterMap.put("amtSum"+i, "");
				}
			}
			
			int b= 0 ;
			for(int i=11; i>0; i--){
				if( surtax.length() >= i){
					parameterMap.put("surtax"+i, surtax.substring(b, b+1));
					b++;
				}else{
					parameterMap.put("surtax"+i, "");
				}
			}
			
			itemDateMM = "";
			itemDateDD = "";
			
			itemName = "J3 MIC40 외 19건";
			itemStd = "";
			itemQty = "";
			itemPrice = "";
			itemAmt = "185,838,000";
			itemStax = "18,583,800";
			itemRemark = "";

			amtTotal = "204,421,800";
			amtCash = "0";
			amtCheck = "0";
			amtBill = "0";
			amtCredit = "0";
			
			recept_name = "청구";
		}else if("Z4".equals(gbn)){
			issue_id = "2016093041000008f2g002rf";

			dem_regnum = "610 - 85 - 20213";
			dem_vendor_name = "㈜휴비스(울산)";
			dem_owner_name = "유배근";
			dem_addr = "울산광역시 남구 처용로 718(황성동)";
			dem_biz_type = "제조";
			dem_biz_kind = "합성섬유,재생섬유";
			
			sup_regnum = "503 - 82 - 03443";
			sup_vendor_name = "한국섬유개발연구원";
			sup_owner_name = "문혜강";
			sup_addr = "대구광역시 서구 국채보상로 136(중리동) ***";
			sup_biz_type = "써비스";
			sup_biz_kind = "기술검사서비스";
			
			pubDate = "2016";
			pubDateMM = "09";
			pubDateDD = "30";
			amtBlank = "3";
			amtSum = "6506000";
			surtax = "650600";
			remark = "";
			

			int a= 0 ;
			for(int i=12; i>0; i--){
				if( amtSum.length() >= i){
					parameterMap.put("amtSum"+i, amtSum.substring(a, a+1));
					a++;
				}else{
					parameterMap.put("amtSum"+i, "");
				}
			}
			
			int b= 0 ;
			for(int i=11; i>0; i--){
				if( surtax.length() >= i){
					parameterMap.put("surtax"+i, surtax.substring(b, b+1));
					b++;
				}else{
					parameterMap.put("surtax"+i, "");
				}
			}
			
			itemDateMM = "";
			itemDateDD = "";
			
			itemName = "U1 LMD51 외 1건";
			itemStd = "";
			itemQty = "";
			itemPrice = "";
			itemAmt = "6,506,000";
			itemStax = "650,600";
			itemRemark = "";

			amtTotal = "7,156,600";
			amtCash = "0";
			amtCheck = "0";
			amtBill = "0";
			amtCredit = "0";
			
			recept_name = "청구";
		}else if("Z5".equals(gbn)){
			issue_id = "2016103141000008f2g003jy";

			dem_regnum = "610 - 85 - 20213";
			dem_vendor_name = "㈜휴비스(울산)";
			dem_owner_name = "유배근";
			dem_addr = "울산광역시 남구 처용로 718(황성동)";
			dem_biz_type = "제조";
			dem_biz_kind = "합성섬유,재생섬유";
			
			sup_regnum = "611 - 05 - 27537";
			sup_vendor_name = "태양실업";
			sup_owner_name = "최천수";
			sup_addr = "경남 거창군 위천면 당산농공길 65 ***";
			sup_biz_type = "제조업";
			sup_biz_kind = "부직포외";
			
			pubDate = "2016";
			pubDateMM = "10";
			pubDateDD = "31";
			amtBlank = "6";
			amtSum = "437000";
			surtax = "43700";
			remark = "";
			

			int a= 0 ;
			for(int i=12; i>0; i--){
				if( amtSum.length() >= i){
					parameterMap.put("amtSum"+i, amtSum.substring(a, a+1));
					a++;
				}else{
					parameterMap.put("amtSum"+i, "");
				}
			}
			
			int b= 0 ;
			for(int i=11; i>0; i--){
				if( surtax.length() >= i){
					parameterMap.put("surtax"+i, surtax.substring(b, b+1));
					b++;
				}else{
					parameterMap.put("surtax"+i, "");
				}
			}
			
			itemDateMM = "";
			itemDateDD = "";
			
			itemName = "U1 LMRED 외 0건";
			itemStd = "";
			itemQty = "";
			itemPrice = "";
			itemAmt = "437,000";
			itemStax = "43,700";
			itemRemark = "";

			amtTotal = "480,700";
			amtCash = "0";
			amtCheck = "0";
			amtBill = "0";
			amtCredit = "0";
			
			recept_name = "청구";
		}else{
			issue_id = "";

			dem_regnum = "119 - 86 - 02801";
			dem_vendor_name = "(주)은우소프트";
			dem_owner_name = "정한규";
			dem_addr = "서울시 구로구 디지털로 33길 28, 1402(구로동, 우림EBIZ 1차)";
			dem_biz_type = "서비스,도매업";
			dem_biz_kind = "소프트웨어개발 및 공급, 유지보수";
			
			sup_regnum = "111 - 11 - 11119  ";
			sup_vendor_name = "케이소프트";
			sup_owner_name = "홍길동";
			sup_addr = "임시 임시구 임시로 5길 21";
			sup_biz_type = "통신";
			sup_biz_kind = "전자";
			
			pubDate = "2017";
			pubDateMM = "06";
			pubDateDD = "27";
			amtBlank = "4";
			amtSum = "3000000";
			surtax = "300000";
			remark = "";
			

			int a= 0 ;
			for(int i=12; i>0; i--){
				if( amtSum.length() >= i){
					parameterMap.put("amtSum"+i, amtSum.substring(a, a+1));
					a++;
				}else{
					parameterMap.put("amtSum"+i, "");
				}
			}
			
			int b= 0 ;
			for(int i=11; i>0; i--){
				if( surtax.length() >= i){
					parameterMap.put("surtax"+i, surtax.substring(b, b+1));
					b++;
				}else{
					parameterMap.put("surtax"+i, "");
				}
			}
			
			itemDateMM = "";
			itemDateDD = "";
			
			itemName = "솔루션 사업비";
			itemStd = "";
			itemQty = "";
			itemPrice = "";
			itemAmt = "3,000,000";
			itemStax = "300,000";
			itemRemark = "";

			amtTotal = "3,300,000";
			amtCash = "0";
			amtCheck = "0";
			amtBill = "0";
			amtCredit = "0";
			
			recept_name = "영수";
		}
		
		parameterMap.put("issue_id", issue_id);
		parameterMap.put("dem_regnum", dem_regnum);
		parameterMap.put("dem_vendor_name", dem_vendor_name);
		parameterMap.put("dem_owner_name", dem_owner_name);
		parameterMap.put("dem_addr", dem_addr);
		parameterMap.put("dem_biz_type", dem_biz_type);
		parameterMap.put("dem_biz_kind", dem_biz_kind);
		parameterMap.put("sup_regnum", sup_regnum);
		parameterMap.put("sup_vendor_name", sup_vendor_name);
		parameterMap.put("sup_owner_name", sup_owner_name);
		parameterMap.put("sup_addr", sup_addr);
		parameterMap.put("sup_biz_type", sup_biz_type);
		parameterMap.put("sup_biz_kind", sup_biz_kind);
		parameterMap.put("pubDate", pubDate);
		parameterMap.put("pubDateMM", pubDateMM);
		parameterMap.put("pubDateDD", pubDateDD);
		parameterMap.put("amtBlank", amtBlank);
		parameterMap.put("amtSum", amtSum);
		parameterMap.put("surtax", surtax);
		parameterMap.put("remark", remark);
		parameterMap.put("itemDateMM", itemDateMM);
		parameterMap.put("itemDateDD", itemDateDD);
		parameterMap.put("itemName", itemName);
		parameterMap.put("itemStd", itemStd);
		parameterMap.put("itemQty", itemQty);
		parameterMap.put("itemPrice", itemPrice);
		parameterMap.put("itemAmt", itemAmt);
		parameterMap.put("itemStax", itemStax);
		parameterMap.put("itemRemark", itemRemark);
		parameterMap.put("amtTotal", amtTotal);
		parameterMap.put("amtCash",amtCash );
		parameterMap.put("amtCheck", amtCheck);
		parameterMap.put("amtBill", amtBill);
		parameterMap.put("amtCredit", amtCredit);
		parameterMap.put("recept_name", recept_name);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 품목 목록 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : itemList
	 * @author : 
	 * @date : 2020.08.28 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/itemList")
	public String itemList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject(); 
		if("Y".equals(parameterMap.getString("P_SEARCH_S"))){
			trans = iproCommPopupService.itemListInqireWithPgng(parameterMap);
		}else{
		}
		
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 우편번호 목록 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : postList
	 * @author : 
	 * @date : 2020.09.07
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/postList")
	public String postList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.postListInqireWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 우편번호 목록 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : zipList
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 22. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/zipList")
	public String zipList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 사용자관리 상세 > 비밀번호 변경 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : userPwdChangeView
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 27. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/userPwdChangeView")
	public String userPwdChangeView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	/**
	 * <pre>
	 * 1.개요 : 권한별 메뉴관리 팝업 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : menuAuthMgrList
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/menuAuthMgrList")
	public String menuAuthMgrList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.menuAuthMgrListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 결재선 검색
	 * 2.처리내용 : 결재선 검색
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : apprLineList
	 * @author : SungYoon_Ha
	 * @date : 2017. 6. 15. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/apprLineList")
	public String apprLineList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.apprLineListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getPopupViewName(prefixUrl);
				
	}	
	
	/**
	 * <pre>
	 * 1.개요 : 계약서식 상세 팝업
	 * 2.처리내용 : 계약서식 상세 팝업
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : contFormDetailPopup
	 * @author : chanil_Hong
	 * @date : 2018. 5. 11. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/contFormDetailPopup")
	public String contFormDetailPopup(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.contFormDetail(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixUrl);
		
	}	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 계약의뢰 팝업
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : prchRqstListPopup
	 * @author : sanghoon_joen
	 * @date : 2018. 7. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/prchRqstListPopup")
	public String prchRqstListPopup(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_DEPT_NO", session.get("DEPT_NO"));
		FwkTransferObject trans = iproCommPopupService.prchRqstList(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	@RequestMapping(value="/prchRqstDetail")
	public String prchRqstDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.prchRqstDetail(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 수의계약사유정보 조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : pvrsMstList
	 * @author : 맹경열
	 * @date : 2018. 08. 02. 
	 * @param parameterMap
	 * @return
	 */
	@RequestMapping(value="/pvrsMstList")
	public String pvrsMstList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.getPvrsMstList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 계약품목조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : ctItemList
	 * @author : 맹경열
	 * @date : 2018. 08. 03. 
	 * @param parameterMap
	 * @return
	 */
	@RequestMapping(value="/ctItemList")
	public String ctItemList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.itemListInqireWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 :프로그램명조회 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : prgmList
	 * @author : 맹경열
	 * @date : 2018. 08. 03. 
	 * @param parameterMap
	 * @return
	 */
	@RequestMapping(value="/prgmList")
	public String prgmList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.prgmInqireList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	/**
	 * <pre>
	 * 1.개요 : 협력사 제재등록 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : snctRegistPopup
	 * @author : chanil_Hong
	 * @date : 2017. 6. 13. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/snctRegistPopup")
	public String snctRegistPopup(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("PROCR_ID", session.get("USR_ID"));
		parameterMap.put("PROCR_NM", session.get("USR_NM"));
//		FwkTransferObject trans = iproVendMngeService.vendMngeDetailInqire(parameterMap);
//		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	/**
	 * <pre>
	 * 1.개요 : 제쟤 상세 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : snctDetailPopup
	 * @author : chanil_Hong
	 * @date : 2017. 6. 13. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/snctDetailPopup")
	public String snctDetailPopup(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
//		FwkTransferObject trans = iproVendMngeService.selectVendSnctDetail(parameterMap);
//		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	/**
	 * <pre>
	 * 1.개요 : 업체그룹관리 등록 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : compGroupRegistPopup
	 * @author : JanDi_Eun
	 * @date : 2018. 2. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/compGroupRegistPopup")
	public String compGroupRegistPopup(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
//		FwkTransferObject trans = iproCommPopupService.menuAuthMgrListInqireWithPgng(parameterMap);
		
//		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 업체그룹관리 등록
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : compGroupRegist
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 5. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/compGroupRegist")
	public String compGroupRegist(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.compGroupRegist(parameterMap);	
	
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return JSON_VIEW;
	}	
	/**
	 * <pre>
	 * 1.개요 : 업체그룹관리 수정 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : compGroupUpdatePopup
	 * @author : hci
	 * @date : 2018. 2. 28. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/compGroupUpdatePopup")
	public String compGroupUpdatePopup(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.compGroupDetail(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 업체그룹관리 수정
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : compGroupUpdate
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 5. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/compGroupUpdate")
	public String compGroupUpdate(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.compGroupUpdate(parameterMap);	
	
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return JSON_VIEW;
	}	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 업체그룹관리 삭제
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : compGroupDelete
	 * @author : JuYeon_Lee
	 * @date : 2018. 3. 5. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/compGroupDelete")
	public String compGroupDelete(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.compGroupDelete(parameterMap);	
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return JSON_VIEW;
	}
	
	/**
	* <pre>
	* 1.개요 : 결재기안 
	* 2.처리내용 : 계약의 결재를 요청한다.
	* </pre>
	* @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	* @Method : contApprSendPopup
	* @author : 맹경열
	* @date : 2018. 08. 09. 
	* @param request
	* @param model
	* @return
	*/
	@RequestMapping(value="/apprSendPopup")
	public String contApprSendPopup(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}	

	/**
	 * <pre>
	 * 1.개요 : 디자인 리스트 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : dsgnBisList
	 * @author : SungYoon_Ha
	 * @date : 2017. 6. 15. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/dsgnBisList")
	public String dsgnBisList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.dngnListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 근로계약대상자 조회 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : laborerList
	 * @author : 맹경열
	 * @date : 2018. 12. 28
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/laborerList")
	public String laborerList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.laborerListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getPopupViewName(prefixUrl);
	}	
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 주요취급품목 조회 팝업 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : mjrHndlItemList
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 4. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mjrHndlItemList")
	public String mjrHndlItemList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.mjrHndlItemListWithPgng(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 일괄공고위한 공고대기데이터 선택팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : uniAnncItemList
	 * @author : 맹경열
	 * @date : 2019. 03. 08. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/uniAnncItemList")
	public String uniAnncItemList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.uniAnncItemListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 공급품목관리를 위한 공고조회 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : bidVendItemRefList
	 * @author : 맹경열
	 * @date : 2019. 03. 08. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bidVendItemRefList")
	public String bidVendItemRefList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.bidVendItemRefListWithPgng(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 입찰 업체 열람 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : bidVendOpenList
	 * @author : JanDi_Eun
	 * @date : 2019. 7. 22. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bidVendOpenList")
	public String bidVendOpenList(final HttpServletRequest request, final Model model) {				
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproCommPopupService.bidVendOpenList(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	@RequestMapping(value="/trmPopup")
	public String trmPopup(final HttpServletRequest request, final Model model,  RedirectAttributes redirectAttributes){
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproTrmService.trmDetail(parameterMap);
		
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans); 
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가정보불러오기 목록 조회 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmInfoList
	 * @author : 
	 * @date : 2021. 3. 24. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmInfoList")
	public String estmInfoList(final HttpServletRequest request, final Model model){
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= iproCommPopupService.estmInfoList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가진행이력 목록 조회 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmHistList
	 * @author : 
	 * @date : 2021. 3. 30. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmHistList")
	public String estmHistList(final HttpServletRequest request, final Model model){
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= iproCommPopupService.estmHistList(parameterMap);
		
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : estmReviewPopup 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.ipro.vend.controller.IproVendMngeController.java
	 * @Method : vendEvalReviewPopup
	 * @author : chanil_Hong
	 * @date : 2017. 6. 15. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmReview")
	public String estmReview(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가대상정보 첨부파일 상세 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmObjFileView
	 * @date : 2021. 04. 02.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmObjFileView")
	public String estmObjFileView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= iproCommPopupService.estmObjFileView(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "ESTM");
		parameterMap.put("P_CONN_CNTN", "평가대상정보 첨부파일 보기");
		parameterMap.put("P_CONN_URL", "/comm/popup/estmObjFileView.do");
		parameterMap.put("P_MENU_ID", "estmObjFileView");
		
		iproCommDefaultService.sendLOG(parameterMap);
		

		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가대상정보 첨부파일 수정 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmObjFileUpdtForm
	 * @date : 2021. 04. 02.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmObjFileUpdtForm")
	public String estmObjFileUpdtForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= iproCommPopupService.estmObjFileView(parameterMap);
		
		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 화상회의정보 첨부파일 상세 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmVidoFileView
	 * @date : 2021. 04. 02.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmVidoFileView")
	public String estmVidoFileView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= iproCommPopupService.estmVidoFileView(parameterMap);

		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가대상정보 첨부파일 수정 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmVidoFileUpdtForm
	 * @date : 2021. 04. 02.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmVidoFileUpdtForm")
	public String estmVidoFileUpdtForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= iproCommPopupService.estmVidoFileView(parameterMap);
		
		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	

	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가위원평가등록팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : cmtmScrUpdtFrm
	 * @date : 2021. 04. 26.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmCmtmScrUpdtFrm")
	public String estmCmtmScrUpdtFrm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= iproCommPopupService.estmCmtmScrUpdtFrm(parameterMap);
		
		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}

	@RequestMapping(value="/estmCmtmScrUpdt")
	public String estmCmtmScrUpdt(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= iproCommPopupService.estmCmtmScrUpdt(parameterMap);
		
		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가위원 목록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmCmtmList
	 * @date : 2021. 04. 22.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmCmtmList")
	public String estmCmtmList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= iproCommPopupService.estmCmtmList(parameterMap);

		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가위원 탭 - 평가위원첨부 상세 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmCmtmFileView
	 * @date : 2021. 05. 06.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmCmtmFileView")
	public String estmCmtmFileView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= iproCommPopupService.estmCmtmFileView(parameterMap);

		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 기업정보
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmObjImstarsMainView
	 * @date : 2021. 05. 21.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmObjImstarsMainView")
	public String estmObjImstarsMainView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= iproCommPopupService.estmObjImstarsMainView(parameterMap);

		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 상세내용
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmObjImstarsDetailView
	 * @date : 2021. 05. 21.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmObjImstarsDetailView")
	public String estmObjImstarsDetailView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= iproCommPopupService.estmObjImstarsDetailView(parameterMap);

		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 첨부파일
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmObjImstarsFileView
	 * @date : 2021. 05. 21.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmObjImstarsFileView")
	public String estmObjImstarsFileView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= iproCommPopupService.estmObjImstarsFileView(parameterMap);

		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}

	/**
	 * 
	 * <pre>
	 * 1.개요 : 정보관리 > 업체정보관리 -  아임스타즈 연계 상세 팝업 : 정보관리
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmObjImstarsVendView
	 * @date : 2021. 05. 31.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmObjImstarsVendView")
	public String estmObjImstarsVendView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= iproCommPopupService.estmObjImstarsMainView(parameterMap);
		
		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 정보관리 > 상품관리 -  아임스타즈 연계 상세 팝업 : 상품관리
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmObjImstarsVendView
	 * @date : 2021. 05. 31.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmObjImstarsPrdsView")
	public String estmObjImstarsPrdsView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= iproCommPopupService.estmObjImstarsDetailView(parameterMap);
		
		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	
	/**
	 * <pre>
	 * 1.개요 : 평가완료현황_평가위원 - 상세 : 설문조사 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmCmtmSvyDetail
	 * @date : 2021. 6. 01. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmCmtmSvyDetail")
	public String estmCmtmSvyDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.estmCmtmSvyDetail(parameterMap);
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가완료현황_평가위원 - 상세 : 설문조사 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmCmtmSvySave
	 * @date : 2021. 6. 01. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmCmtmSvySave")
	public String estmCmtmSvySave(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.estmCmtmSvySave(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "ESTM");
		parameterMap.put("P_CONN_CNTN", "설문조사 배점 저장");
		parameterMap.put("P_CONN_URL", "/comm/popup/estmCmtmSvyDetail.do");
		parameterMap.put("P_MENU_ID", "estmCmtmSvyDetail");
		
		iproCommDefaultService.sendLOG(parameterMap);
		
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		
		return "forward:/comm/popup/estmCmtmSvyDetail.do?resultCode=Success";
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 데이터베이스목록 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : dataBaseList
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 16.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/dataBaseList")
	public String dataBaseList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproCommPopupService.dataBaseList(parameterMap);
		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);  
		return parameterMap.getPopupViewName(prefixUrl);
	}
	
	
	
	
	
	
	
	
	
	
}
