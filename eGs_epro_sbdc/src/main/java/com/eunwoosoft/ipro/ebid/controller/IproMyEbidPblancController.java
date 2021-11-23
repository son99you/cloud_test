package com.eunwoosoft.ipro.ebid.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproMyEbidPblancService;

/**
 * 입찰현황
 * com.eunwoosoft.ipro.ebid.controller
 * IproEbidProconController.java
 *
 * @Author : sanghoon_joen
 * @Date   : 2017. 6. 13.
 *
 */
@RequestMapping(value = "/ebid")
@Controller
public class IproMyEbidPblancController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";
	String prefixPopUrl = "/ipro/views/ebid/popup/";
	@Resource(name="iproMyEbidPblancService")
	private IproMyEbidPblancService iproMyEbidPblancService;
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 나의공고현황
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : bidPlanList
	 * @author : sanghoon_joen
	 * @date : 2018. 2. 20. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/myBidPblancList")
	public String bidPlanList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_USR_ID", user.getString("USR_ID"));
		FwkTransferObject trans = iproMyEbidPblancService.myBidPblancListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
//		
		return parameterMap.getViewName(prefixUrl);
	}
}
