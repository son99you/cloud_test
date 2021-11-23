package com.eunwoosoft.comm.atfi.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.atfi.service.ComAtfiAtchFileService;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * <pre>
 * com.eunwoosoft.comm.atfi.controller 
 *    |_ ComAtfiAtchFileManageController.java
 * 
 * </pre>
 * @date : 2015. 3. 12. 오전 10:12:12
 * @version : 1.0
 * @author : LIG시스템 김경용
 */
@Controller
@RequestMapping("/com/atfi")
public class ComAtfiAtchFileManageController extends AbstractFwkController {	
	
	@Resource(name="comAtfiAtchFileService")
	private ComAtfiAtchFileService comAtfiAtchFileService;
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일그룹번호에 의한 첨부파일목록 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : atchFileListInqireByAtchFileGroupNo
	 * @date : 2015. 3. 12.
	 * @author : LIG시스템 김경용
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 12.		LIG시스템 김경용				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/atchFileListInqireByAtchFileGroupNo.json")
	public String atchFileListInqireByAtchFileGroupNo(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);		
		FwkTransferObject trans = comAtfiAtchFileService.atchFileListInqireByAtchFileGroupNo(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일그룹번호에 의한 첨부파일목록 조회 (외부용)
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : atchFileListInqireByAtchFileGroupNoExtern
	 * @date : 2015. 3. 12.
	 * @author : LIG시스템 김경용
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 3. 12.		LIG시스템 김경용				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/atchFileListInqireByAtchFileGroupNoExtern.json")
	public String atchFileListInqireByAtchFileGroupNoExtern(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);		
		FwkTransferObject trans = comAtfiAtchFileService.atchFileListInqireByAtchFileGroupNoExtern(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
}
