package com.eunwoosoft.opro.ebid.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproEbidSttlmntService;
import com.eunwoosoft.opro.ebid.service.OproEbidSttlmntService;

@RequestMapping(value = "/opro/ebid")
@Controller
public class OproEbidSttlmntController extends AbstractOproMenuController{
	String prefixUrl = "/opro/views/ebid/";
	
	@Resource(name="oproEbidSttlmntService")
	private OproEbidSttlmntService oproEbidSttlmntService;
	/**
	 * 
	 * <pre>
	 * 1.개요 : 나의 수의시담 목록 
	 * 2.처리내용 : myVltrnPrvstlList.jsp
	 * </pre>
	 * @Location : eGs_eproDemo.com.eunwoosoft.opro.ebid.controller.OproEbidSttlmntController.java
	 * @Method : myVltrnPrvstlList
	 * @author : chanil_Hong
	 * @date : 2017. 6. 23. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/myVltrnPrvstlList")
	public String myVltrnPrvstlList(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			//trans = oproEbidSttlmntService.vltrnPrvstlListInqireWithPgng(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return parameterMap.getOproViewName(prefixUrl);
	}	
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 상세- 시담진행
	 * 2. 처리내용 :  
	 * </pre>
	 * @Method Name : vltrnPrvstlProgrsDetail
	 * @date : 2015. 3. 10.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 10.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	
	@RequestMapping(value="/vltrnPrvstlProgrsDetail")
	public String vltrnPrvstlProgrsDetail(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			trans = oproEbidSttlmntService.vltrnPrvstlProgrsDetailInqire(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return parameterMap.getOproViewName(prefixUrl);
	}	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 내용 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlCnRegist
	 * @date : 2015. 3. 10.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 10.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	
	@RequestMapping(value="/vltrnPrvstlCnRegist")
	public String vltrnPrvstlCnRegist(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		parameterMap.put("ControllerType", "Json");
		try{
			FwkTransferObject trans = oproEbidSttlmntService.vltrnPrvstlCnRegist(parameterMap);
			model.addAttribute("vltrnPrvstlProgrsList", trans.get(oproEbidSttlmntService.VLTRN_PRVSTL_PROGRS_LIST));
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 협상금액 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlNtatAmountRegist
	 * @date : 2015. 6. 17.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 6. 17.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	
	@RequestMapping(value="/vltrnPrvstlNtatAmountRegist")
	public String vltrnPrvstlNtatAmountRegist(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		parameterMap.put("ControllerType", "Json");
		try{
			FwkTransferObject trans = oproEbidSttlmntService.vltrnPrvstlNtatAmountRegist(parameterMap);
			model.addAttribute("vltrnPrvstlNtatAmountList", trans.get(oproEbidSttlmntService.VLTRN_PRVSTL_PLNPRC_AMOUNT_LIST));
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 수의시담 최종 금액 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : vltrnPrvstlLastAmountRegist
	 * @date : 2015. 6. 19.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 6. 19.		은우소프트 손연우			최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	
	@RequestMapping(value="/vltrnPrvstlLastAmountRegist")
	public String vltrnPrvstlLastAmountRegist(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		parameterMap.put("ControllerType", "Json");
		try{
			FwkTransferObject trans = oproEbidSttlmntService.vltrnPrvstlLastAmountRegist(parameterMap);
			model.addAttribute("lastNtatAmount", trans.get("lastNtatAmount"));
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		return JSON_VIEW;
	}

	/**
	 * <pre>
	 * 1. 개요 : 전체 수의시담 목록 
	 * 2. 처리내용 : 
     *      - 전체 수의시담 목록조회 서비스를 호출한다.
     *      - 전체 수의시담 목록조회 결과를 Model에 담는다.
     *      - View(JSP명) 를 반환한다.(vlpr/oepVlprAllVltrnPrvstlList.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : allVltrnPrvstlList
	 * @date : 2016. 02. 19.
	 * @author : 은우소프트 김봉수
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 02. 19.		은우소프트 김봉수				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- vlpr/oepVlprAllVltrnPrvstlList
	 * @throws Exception 
	 */
	@RequestMapping(value = "/allVltrnPrvstlList")
	public String allVltrnPrvstlList(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		try{
			//trans = oproEbidSttlmntService.allVltrnPrvstlListInqireWithPgng(parameterMap);
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
		}catch(Exception ex){
			response.setStatus(999);
			throw new Exception(ex.toString());
		}
		
		return parameterMap.getOproViewName(prefixUrl);
	}	
}
