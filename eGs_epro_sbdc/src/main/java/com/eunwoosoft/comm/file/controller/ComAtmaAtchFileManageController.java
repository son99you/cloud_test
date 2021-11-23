package com.eunwoosoft.comm.file.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.comm.util.ComAtmaAtchFileUtil;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * <pre>
 * com.eunwoosoft.comm.file.controller 
 *    |_ ComAtmaAtchFileManageController.java
 * 
 * </pre>
 * @date : 2015. 4. 28. 오전 10:12:12
 * @version : 1.0
 * @author : 야긴스텍 정윤교
 */
@RequestMapping("/com/atma")
@Controller
public class ComAtmaAtchFileManageController extends AbstractFwkController {
	
	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;
	
	/**
	 * <pre>
	 * 1. 개요 : 업로드파일 정보 DB 저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : fileInfoRegist
	 * @date : 2015. 4. 28. 오후 3:48:18
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 28.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/fileInfoRegist")
	public String fileInfoRegist(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		String atchFileGroupNo = FwkStringUtil.replace(UUID.randomUUID().toString(), "-", "");
		String fileGroupFlag = parameterMap.getString("P_FILE_GROUP_FLAG");
		
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("fileGroupFlag", fileGroupFlag);
		parameterMap.put("fileList", ComAtmaAtchFileUtil.getAtchFileInfo(parameterMap.getListOfMap("ATCH_FILE")));
		comAtmaAtchFileService.atchFileRegist(parameterMap);

		FwkTransferObject trans = new FwkTransferObject();
		trans.put("atchFileGroupNo", atchFileGroupNo);
		model.addAllAttributes(trans);

		return JSON_VIEW;
	}

	/**
	 * <pre>
	 * 1. 개요 : 업로드파일 수정 정보 DB 저장
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : fileInfoUpt
	 * @date : 2015. 4. 28. 오후 3:48:18
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 5. 04.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/fileInfoUpt")
	public String fileInfoUpt(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		String fileGroupFlag = parameterMap.getString("P_FILE_GROUP_FLAG");
		
		String atchFileGroupNo = "";
		if(parameterMap.get("P_FILE_GRP_NO") != null && !parameterMap.get("P_FILE_GRP_NO").equals("")) {
			atchFileGroupNo = parameterMap.get("P_FILE_GRP_NO").toString();
		}else if(parameterMap.get("P_VEND_FILE_GRP_NO") != null && !parameterMap.get("P_VEND_FILE_GRP_NO").equals("")){
			atchFileGroupNo = parameterMap.get("P_VEND_FILE_GRP_NO").toString();
		}
		
		parameterMap.put("fileGroupFlag", fileGroupFlag);
		parameterMap.put("atchFileGroupNo", atchFileGroupNo);
		parameterMap.put("newFileList", ComAtmaAtchFileUtil.getAtchFileInfo(parameterMap.getListOfMap("ATCH_FILE")));
		parameterMap.put("deleteFileList", ComAtmaAtchFileUtil.getAtchFileInfo(parameterMap.getListOfMap("DELETE_ATCH_FILE")));
		comAtmaAtchFileService.atchFileUpt(parameterMap);
		
		FwkTransferObject trans = new FwkTransferObject();
		trans.put("atchFileGroupNo", atchFileGroupNo);
		model.addAllAttributes(trans);

		return JSON_VIEW;
	}

	
	
	/**
	 * <pre>
	 * 1. 개요 : 첨부파일그룹번호에 의한 첨부파일목록 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : atchFileListInqireByAtchFileGroupNo
	 * @date : 2015. 4. 28.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 28.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/atchFileListInqireByAtchFileGroupNo")
	public String atchFileListInqireByAtchFileGroupNo(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);		
		FwkTransferObject trans = comAtmaAtchFileService.atchFileListInqireByAtchFileGroupNo(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}

	/**
	 * <pre>
	 * 1. 개요 : 첨부파일그룹번호에 의한 첨부파일목록 조회 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : atchFileListInqireByAtchFileGroupNo
	 * @date : 2015. 4. 28.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 28.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */ 
	@RequestMapping(value = "/fileInfoDelete")
	public String fileInfoDelete(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);		
		comAtmaAtchFileService.atchFileDelete(parameterMap.getListOfMap("DELETE_ATCH_FILE"));

		return JSON_VIEW;
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 구매접수대기현황 상세 - 첨부파일 연계
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.comm.file.controller.ComAtmaAtchFileManageController.java
	 * @Method : acptWaitAtchFileListByFileGrpNo
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 21. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/acptWaitAtchFileListByFileGrpNo")
	public String acptWaitAtchFileListByFileGrpNo(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = comAtmaAtchFileService.acptWaitAtchFileListByFileGrpNo(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}

}
