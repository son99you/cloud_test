package com.eunwoosoft.comm.file.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.eunwoosoft.comm.file.service.ComAtmaAtchFileService;
import com.eunwoosoft.comm.file.vo.DEXTUploadNXRequest;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.util.FwkStringUtil;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

/**
 * <pre>
 * com.eunwoosoft.comm.file.controller
 *    |_ ComAtmaAtchFileController.java
 * 
 * </pre>
 * @date : 2015. 4. 28. 오후 3:48:18
 * @version : 1.0
 * @author : 야긴스텍 정윤교
 */
@RequestMapping(value = "/com/atma")
@Controller
public class ComAtmaAtchFileController extends AbstractFwkController {

	private static final Logger LOG = LoggerFactory.getLogger(ComAtmaAtchFileController.class);

	@Resource(name="comAtmaAtchFileService")
	private ComAtmaAtchFileService comAtmaAtchFileService;

	@RequestMapping(value = "/fileUpload")
	public void fileUpload( DEXTUploadNXRequest nx, MultipartHttpServletRequest request, HttpServletResponse response, final Model model) throws IOException {

	}

	/**
	 * <pre>
	 * 1. 개요 : 파일다운로드 
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : fileDwld
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
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	private void createParameterMap(final HttpServletRequest request) {

		String key;
		FwkParameterMap parameterMap = new FwkParameterMap();

		Enumeration<String> e1 = (Enumeration<String>)request.getParameterNames();
		while (e1.hasMoreElements()) {
			key = (String)e1.nextElement();

			// DEXTUploadNX로 시작하는 폼 이름을 제외한 나머지
			if (!key.startsWith("DEXTUploadNX")) {
				if ((request.getParameterValues(key)).length > 1) {				
					String[] values = request.getParameterValues(key);
					parameterMap.put(key, values);
				} else {				
					parameterMap.put(key, request.getParameter(key));
				}
			}
		}

		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		parameterMap.put("requestId", ip);
		parameterMap.put("requestUrl", request.getRequestURI());
		parameterMap.put("contextName", FwkStringUtil.replace(request.getContextPath(), "/", ""));

		FwkDataEntity dataEntity = (FwkDataEntity) request.getSession().getAttribute("loginResult");

		if(dataEntity != null) {
			parameterMap.put("loginResult", dataEntity);
		}

		request.setAttribute(PARAMETER_MAP, parameterMap);

		LOG.info(parameterMap.toString());

	}
}
