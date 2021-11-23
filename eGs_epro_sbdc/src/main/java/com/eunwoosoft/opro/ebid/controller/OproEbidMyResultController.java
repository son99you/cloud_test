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
import com.eunwoosoft.opro.ebid.service.OproEbidMyResultService;

/**
 * 전자입찰 > 나의 입찰결과 Controller 
 * <pre>
 * com.eunwoosoft.opro.ebid.controller 
 *    |_ OproEbidMyResultController.java
 * 
 * </pre>
 * @date : 2015. 04. 02. 오후 13:52:31
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
@Controller
@RequestMapping(value = "/opro/ebid")
public class OproEbidMyResultController extends AbstractOproMenuController {
	String prefixUrl = "/opro/views/ebid/";
	
	@Resource(name="oproEbidMyResultService")
	private OproEbidMyResultService oproEbidMyResultService;
	

	/**
	 * <pre>
	 * 1. 개요 : 나의 입찰결과 목록 
	 * 2. 처리내용 : 
     *      - 나의 입찰결과 목록조회 서비스를 호출한다.
     *      - 나의 입찰결과 목록조회 결과를 Model에 담는다.
     *      - View(JSP명) 를 반환한다.(elbi/oepElbiMyBidResultList.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : myBidResultList
	 * @date : 2015. 04. 02.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 02.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/oepElbiMyBidResultList.jsp
	 * @throws Exception 
	 */
	@RequestMapping(value = "/myBidResultList")
	public String myBidResultList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidMyResultService.myBidResultListWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
	
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 나의 입찰결과 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : myBidResultDetail
	 * @date : 2015. 04. 03.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 03.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	
	
	@RequestMapping(value="/myBidResultDetail")
	public String myBidResultDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidMyResultService.myBidResultDetailInqire(parameterMap);
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getOproViewName(prefixUrl);
		
	}
	 
}
