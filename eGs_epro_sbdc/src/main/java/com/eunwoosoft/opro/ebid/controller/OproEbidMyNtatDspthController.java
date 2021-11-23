package com.eunwoosoft.opro.ebid.controller; 

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.ParameterMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.ebid.service.OproEbidMyNtatDspthService;

/**
 * 전자입찰 > 나의 협상통보 Controller 
 * <pre>
 * com.eunwoosoft.opro.ebid.controller 
 *    |_ OproEbidMyNtatDspthController.java
 * 
 * </pre>
 * @date : 2015. 03. 24. 오후 7:11:34
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
@Controller
@RequestMapping(value = "/opro/ebid")
public class OproEbidMyNtatDspthController extends AbstractOproMenuController {
	String prefixUrl = "/opro/views/ebid/";
	
	@Resource(name="oproEbidMyNtatDspthService")
	private OproEbidMyNtatDspthService oproEbidMyNtatDspthService;
	

	/**
	 * <pre>
	 * 1. 개요 : 나의 협상통보 목록 
	 * 2. 처리내용 : 
     *      - 나의 협상통보 목록조회 서비스를 호출한다.
     *      - 나의 협상통보 목록조회 결과를 Model에 담는다.
     *      - View(JSP명) 를 반환한다.(elbi/oepElbiMyNtatDspthListList.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : myNtatDspthList
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
     * @return JSP 화면명- elbi/oepElbiMyNtatDspthListList
	 * @throws Exception 
	 */
	@RequestMapping(value = "/myNtatDspthList")
	public String myNtatDspthList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = oproEbidMyNtatDspthService.myNtatDspthListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 협상통보 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myNtatDspthDetail
	 * @date : 2015. 03. 24.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 24.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	
	
	@RequestMapping(value="/myNtatDspthDetail")
	public String myNtatDspthDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = oproEbidMyNtatDspthService.myNtatDspthDetailInqire(parameterMap);
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 협상 통보 응답 등록 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myNtatDspthRspnsRegist
	 * @date : 2015. 3. 25.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 25.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/myNtatDspthRspnsRegist")
	public String myNtatDspthRspnsRegist(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidMyNtatDspthService.myNtatDspthRspnsRegist(parameterMap);
		
		model.addAllAttributes(trans);
		
		
		
		
		return "forward:/opro/ebid/myNtatDspthList.do";
	}
	
	
}
