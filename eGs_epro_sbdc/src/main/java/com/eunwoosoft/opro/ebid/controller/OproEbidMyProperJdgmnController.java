package com.eunwoosoft.opro.ebid.controller; 

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.ParameterMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.ebid.service.OproEbidMyProperJdgmnService;

/**
 * 전자입찰 > 나의 적격심사 Controller 
 * <pre>
 * com.eunwoosoft.opro.ebid.controller 
 *    |_ OproEbidMyProperJdgmnController.java
 * 
 * </pre>
 * @date : 2015. 03. 23. 오후 3:00:24
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
@Controller
@RequestMapping(value = "/opro/ebid")
public class OproEbidMyProperJdgmnController extends AbstractOproMenuController {
	String prefixUrl = "/opro/views/ebid/";
	
	@Resource(name="oproEbidMyProperJdgmnService")
	private OproEbidMyProperJdgmnService oproEbidMyProperJdgmnService;
	

	/**
	 * <pre>
	 * 1. 개요 : 나의 적격심사 목록 
	 * 2. 처리내용 : 
     *      - 나의 적격심사 목록조회 서비스를 호출한다.
     *      - 나의 적격심사 목록조회 결과를 Model에 담는다.
     *      - View(JSP명) 를 반환한다.(elbi/oepElbiMyProperJdgmnList.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : myProperJdgmnList
	 * @date : 2015. 03. 12.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 12.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/oepElbiMyProperJdgmnList
	 * @throws Exception 
	 */
	@RequestMapping(value = "/myProperJdgmnList")
	public String myProperJdgmnList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = oproEbidMyProperJdgmnService.myProperJdgmnListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		
		return parameterMap.getOproViewName(prefixUrl);
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 적격심사 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myProperJdgmnDetail
	 * @date : 2015. 03. 23.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 23.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	
	
	@RequestMapping(value="/myProperJdgmnDetail")
	public String myProperJdgmnDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = oproEbidMyProperJdgmnService.myProperJdgmnDetailInqire(parameterMap);
		model.addAllAttributes(trans);
		
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 적격심사 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myProperJdgmnRegist
	 * @date : 2015. 3. 24.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 24.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	
	@RequestMapping(value="/myProperJdgmnRegist")
	public String myProperJdgmnRegist(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;
		parameterMap.put("multipartRequest", multipartRequest);
		FwkTransferObject trans = oproEbidMyProperJdgmnService.myProperJdgmnRegist(parameterMap);
		model.addAllAttributes(trans);
		
		
		return "forward:/opro/ebid/myProperJdgmnList.do";
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 적격심사 점수 통보
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myProperJdgmnScoreDspth
	 * @date : 2015. 3. 24.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 24.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	
	@RequestMapping(value="/myProperJdgmnScoreDspth")
	public String myProperJdgmnScoreDspth(final HttpServletRequest request, final Model model) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidMyProperJdgmnService.myProperJdgmnScoreDspth(parameterMap);
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}
	
}
