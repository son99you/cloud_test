package com.eunwoosoft.ipro.sanc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.exception.CustomException;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.sanc.service.IproSancService;

/**
 * 결재기안
 * 
 * 2019-01-15 
 * 
 * @author juyeon_Lee
 *
 */
@RequestMapping(value = "/sanc")
@Controller
public class IproSancController extends AbstractIproMenuController {
	static final String prefixUrl = "/ipro/views/sanc/";
	static final String prefixPopUrl = "/ipro/views/sanc/popup/";
	
	@Resource(name="iproSancService")
	private IproSancService iproSancService;
	
	/**
	 * 
	 * 
	 * 	<pre>
	 *  1. 개요 :
	 * 	2. 처리내용 : 결재요청
	 *	</pre>
	 *
	 * @method Name : sanctnRequst
	 * @Author : joo
	 * @Date   : 2020. 11. 24.
	 * @history : 
	 * ------------------------------------------------------------------------
	 * 변경일                                         작성자                                       변경내용
	 * ------------------------------------------------------------------------
	 * 2020. 11. 24.                 joo                최초작성
	 * ------------------------------------------------------------------------
	 *
	 *
	 *
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value="/sanctnRequst", method={RequestMethod.POST, RequestMethod.GET})
	public String sanctnRequst(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		try{
			parameterMap.put("P_REGR_ID", session.get("USR_ID"));
			parameterMap.put("P_REGR_NM", session.get("USR_NM"));
			parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
			trans = iproSancService.apprInfoRegist(parameterMap);
		
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
				
		}catch(CustomException ex){
			response.setStatus(999);
			throw new CustomException(ex.toString());
		}
		
		return JSON_VIEW;
	}
	
	@RequestMapping(value="/sanctnRequstAll", method={RequestMethod.POST, RequestMethod.GET})
	public String sanctnRequstAll(final HttpServletRequest request, final Model model, final HttpServletResponse response) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = new FwkTransferObject();
		FwkDataEntity session = (FwkDataEntity) parameterMap.get("loginResult");
		try{
			parameterMap.put("P_REGR_ID", session.get("USR_ID"));
			parameterMap.put("P_REGR_NM", session.get("USR_NM"));
			parameterMap.put("P_REG_DT", FwkDateUtil.getCurrentDateTimeAsString());
		
			trans = iproSancService.apprInfoRegistAll(parameterMap);
		
			model.addAllAttributes(trans);
			model.addAllAttributes(parameterMap);
				
		}catch(CustomException ex){
			response.setStatus(999);
			throw new CustomException(ex.toString());
		}
		
		return JSON_VIEW;
	}
}
