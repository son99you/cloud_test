package com.eunwoosoft.comm.code.controller;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

import com.eunwoosoft.comm.code.service.ComCmcdLegaldongService;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;

import org.apache.ibatis.mapping.ParameterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * com.eunwoosoft.comm.code.controller 
 *    |_ ComCmcdLegaldongController.java
 * 
 * </pre>
 * @date : 2014. 12. 15. 오전 10:15:26
 * @version : 1.0
 * @author : 
 */
@Controller
@RequestMapping(value = "/com/cmcd")
public class ComCmcdLegaldongController extends AbstractFwkController {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(ComCmcdLegaldongController.class);

	@Resource(name="comCmcdLegaldongService")
	private ComCmcdLegaldongService comCmcdLegaldongService;

	/**
	 * <pre>
	 * 1. 개요 : 시도 목록 조회 
	 * 2. 처리내용 : 
	 * 		- 검색한 시도 목록 서비스를 호출한다.
	 * 3. 입출력 : 
	 *	-----------------------------------------------------------------------
	 * I/O	 항목	                              타입             설명                             참고사항
	 * -----------------------------------------------------------------------
	 *  I       P_AREA_CD                    String         지역코드			     required
	 *  
	 *  O      AREA_CD        				String         지역코드			     ~
	 *  		 AREA_NM        				String         지역명			     ~
	 * -----------------------------------------------------------------------
	 * 
	 * </pre>
	 * @Method Name : legaldongBrtcListInqire
	 * @date : 2015. 02. 13.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 13.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * 					요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSON_VIEW
	 */
	@RequestMapping(value = "/legaldongBrtcListInqire")
	public String legaldongBrtcListInqire(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		model.addAllAttributes(comCmcdLegaldongService.legaldongBrtcListInqire(parameterMap));
		return JSON_VIEW;
	}

	/**
	 * <pre>
	 * 1. 개요 : 시군구 목록 조회 
	 * 2. 처리내용 : 
	 * 		- 검색한 시군구 목록 서비스를 호출한다.
	 * 3. 입출력 : 
	 *	-----------------------------------------------------------------------
	 * I/O	 항목	                              타입             설명                             참고사항
	 * -----------------------------------------------------------------------
	 *  I       P_AREA_CD                    String         지역코드			     required
	 *  
	 *  O      AREA_CD        				String         지역코드			     ~
	 *  		 AREA_NM        				String         지역명			     ~
	 * -----------------------------------------------------------------------
	 * 
	 * </pre>
	 * @Method Name : legaldongSignguListInqire
	 * @date : 2015. 02. 13.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 13.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * 					요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSON_VIEW
	 */
	@RequestMapping(value = "/legaldongSignguListInqire")
	public String legaldongSignguListInqire(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		model.addAllAttributes(comCmcdLegaldongService.legaldongSignguListInqire(parameterMap));
		return JSON_VIEW;
	}


	/**
	 * <pre>
	 * 1. 개요 : 읍면동 목록 조회 
	 * 2. 처리내용 : 
	 * 		- 검색한 읍면동 목록 서비스를 호출한다.
	 * 3. 입출력 : 
	 *	-----------------------------------------------------------------------
	 * I/O	 항목	                              타입             설명                             참고사항
	 * -----------------------------------------------------------------------
	 *  I       P_AREA_CD                    String         지역코드			     required
	 *  
	 *  O      AREA_CD        				String         지역코드			     ~
	 *  		 AREA_NM        				String         지역명			     ~
	 * -----------------------------------------------------------------------
	 * 
	 * </pre>
	 * @Method Name : legaldongEmdListInqire
	 * @date : 2015. 02. 13.
	 * @author : 야긴스텍 정윤교
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 13.		야긴스텍 정윤교				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * 					요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSON_VIEW
	 */
	@RequestMapping(value = "/legaldongEmdListInqire")
	public String legaldongEmdListInqire(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		model.addAllAttributes(comCmcdLegaldongService.legaldongEmdListInqire(parameterMap));
		return JSON_VIEW;
	}
}
