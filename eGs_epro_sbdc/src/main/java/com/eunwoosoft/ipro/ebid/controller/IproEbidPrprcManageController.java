package com.eunwoosoft.ipro.ebid.controller; 

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.ParameterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.frwk.prl.request.FwkParameterMapInterceptor;
import com.eunwoosoft.ipro.ebid.service.IproEbidPrprcManageService;

/**
 * 제안서관리 > 제안서관리 Controller 
 * <pre>
 *    |_ IproEbidPrprcManageController.java
 * 
 * </pre>
 * @date : 2019. 02. 12. 오후 06:02:42
 * @version : 1.0
 * @author : 은우소프트 맹경열
 */
@Controller
@RequestMapping(value = "/ebid")
public class IproEbidPrprcManageController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";
	String prefixPopUrl = "/ipro/views/ebid/popup/";
	
	@Resource(name="iproEbidPrprcManageService")
	private IproEbidPrprcManageService iproEbidPrprcManageService;
	
	private static final Logger LOG = LoggerFactory.getLogger(FwkParameterMapInterceptor.class);
	
	/**
	 * <pre>
	 * 1. 개요 : 제안서관리 목록
	 * 2. 처리내용 : 
     *      - 제안서관리 목록 조회 서비스를 호출한다.
     *      - 제안서관리 목록 조회 결과를 Model에 담는다.
	 * </pre>
	 * @Method Name : prprcManageList
	 * @date : 2019. 02. 12.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 12.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/prprcManageList")
	public String prprcManageList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPrprcManageService.prprcManageListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 제안서관리 상세
	 * 2. 처리내용 : 
     *      - 제안서관리 목록 조회 서비스를 호출한다.
     *      - 제안서관리 목록 조회 결과를 Model에 담는다.
	 * </pre>
	 * @Method Name : prprcManageDetail
	 * @date : 2019. 02. 19.
	 * @author : 은우소프트 맹경열
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 02. 19.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/prprcManageDetail")
	public String prprcManageDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEbidPrprcManageService.prprcManageDetailInqire(parameterMap);
		trans.put("toDayTime", FwkDateUtil.getCurrentDateTimeAsString());
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}	
	
}
