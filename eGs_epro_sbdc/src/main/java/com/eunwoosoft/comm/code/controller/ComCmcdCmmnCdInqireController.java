package com.eunwoosoft.comm.code.controller; 

import java.util.Arrays;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.eunwoosoft.comm.code.service.ComCmcdCmmnCdInqireService;
import com.eunwoosoft.comm.code.service.ComCmcdDetailCdInqireService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

import org.apache.ibatis.mapping.ParameterMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 공통코드조회 Controller
 * <pre>
 * com.eunwoosoft.comm.code.controller 
 *    |_ ComCmcdCmmnCdInqireController.java
 * 
 * </pre>
 * @date : 2014. 12. 8. 오후 7:02:39
 * @version : 1.0
 * @author : 
 */
@Controller
@RequestMapping(value = "/com/cmcd")
public class ComCmcdCmmnCdInqireController extends AbstractFwkController {
	
	@Resource(name="comCmcdMasterCdInqireService")
	private ComCmcdCmmnCdInqireService comCmcdMasterCdInqireService;
	
	@Resource(name="comCmcdDetailCdInqireService")
	private ComCmcdDetailCdInqireService comCmcdDetailCdInqireService;
	
	/**
	 * <pre>
	 * 1. 개요 : 코드값목록조회
	 * 2. 처리내용 : 
	 * 		- 입력값에 대한 유효성체크를 한다.
	 * 		- 공통상세코드목록조회 서비스를 호출한다.
	 * 		- 공통상세코드목록 결과를 JsonView형태로 반환한다.
	 * 3. 입출력 : 
	 *	-----------------------------------------------------------------------
	 * I/O	 항목	                              타입             설명                             참고사항
	 * -----------------------------------------------------------------------
	 *  I       P_CD_ID                    String         코드ID				     required
	 *  O      #CD_VALUE_LIST        List            #코드값목록			     ~
	 *            CD_VALUE                String           코드값				     ~
	 *            CD_VALUE_NM		    String           코드값명			     ~
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : cdValueListInqire
	 * @date : 2014. 12. 8.
	 * @author : 
	 * 
	 * @history :           
	 *	-----------------------------------------------------------------------
	 *	변경일						작성자							변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 8.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * 					요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JsonView
	 */
	@RequestMapping(value = "/cdValueListInqire")
	public String cdValueListInqire(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);		
		inputValueValidCeck(parameterMap);		
		model.addAllAttributes(comCmcdDetailCdInqireService.cdValueListInqireByCdId(parameterMap));
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/mastrCdList")
	public String mastrCdList(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		String viewName = parameterMap.getTrimString("returnUrl");
		
		FwkTransferObject trans = comCmcdMasterCdInqireService.cmmnCdListInqireWithPgng(parameterMap);
		
		
		model.addAllAttributes(trans);
		return viewName;
	}
	
	@RequestMapping(value = "/detailCdList")
	public String detailCdList(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		String viewName = parameterMap.getTrimString("returnUrl");
		
		FwkTransferObject trans = comCmcdDetailCdInqireService.cmmnCdListInqireWithPgng(parameterMap);
		
		
		model.addAllAttributes(trans);
		return viewName;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입력값유효성체크 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : inputValueValidCeck
	 * @date : 2014. 12. 10.
	 * @author : 
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2014. 12. 10.						최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param parameterMap
	 */
	private void inputValueValidCeck(final FwkParameterMap parameterMap) {
	    List<String> ceckKeys = Arrays.asList("P_CD_ID");
	    parameterMap.essntlIputValueCeck(ceckKeys);
	}
}
