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
import com.eunwoosoft.opro.ebid.service.OproEbidResultService;

/**
 * 입찰관리 > 입찰결과현황현황 Controller 
 * <pre>
 * com.eunwoosoft.opro.ebid.controller 
 *    |_ OproEbidResultController.java
 * 
 * </pre>
 * @date : 2015. 04. 02. 오후 13:52:31
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
@Controller
@RequestMapping(value = "/opro/ebid")
public class OproEbidResultController extends AbstractOproMenuController {
	String prefixUrl = "/opro/views/ebid/";
	String prefixPopUrl = "/opro/views/ebid/popup/";
	String prefixExcelUrl = "/opro/bid/";
	
	@Resource(name="oproEbidResultService")
	private OproEbidResultService oproEbidResultService;
	

	/**
	 * <pre>
	 * 1. 개요 : 입찰결과현황현황 목록 
	 * 2. 처리내용 : 
     *      - 입찰결과현황 목록조회 서비스를 호출한다.
     *      - 입찰결과현황 목록조회 결과를 Model에 담는다.
     *      - View(JSP명) 를 반환한다.(ebid/bidResultList.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : bidResultList
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
     * @return JSP 화면명- ebid/bidResultList
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bidResultList")
	public String bidResultList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidResultService.bidResultListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);

		return parameterMap.getOproViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰결과현황 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidResultDetail
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
	@RequestMapping(value="/bidResultDetail")
	public String bidResultDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidResultService.bidResultDetailInqire(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getOproViewName(prefixUrl);
	}
	
}
