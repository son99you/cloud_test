package com.eunwoosoft.opro.ebid.controller; 

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.ParameterMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractOproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.opro.ebid.service.OproEbidMyPblancService;

/**
 * 전자입찰 > 나의 입찰공고 Controller 
 * <pre>
 * oda.oep.elbi.controller 
 *    |_ OepElbiMyBidPblancController.java
 * 
 * </pre>
 * @date : 2015. 03. 10. 오후 04:31:21
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
@Controller
@RequestMapping(value = "/opro/ebid")
public class OproEbidMyPblancController extends AbstractOproMenuController {
	String prefixUrl = "/opro/views/ebid/";
	String prefixPopUrl = "/opro/views/ebid/popup/";
	@Resource(name="oproEbidMyPblancService")
	private OproEbidMyPblancService oproEbidMyPblancService;

	/**
	 * <pre>
	 * 1. 개요 : 나의 입찰공고 목록 
	 * 2. 처리내용 : 
     *      - 나의 입찰골고 목록조회 서비스를 호출한다.
     *      - 나의 입찰공고 목록조회 결과를 Model에 담는다.
     *      - View(JSP명) 를 반환한다.(elbi/oepElbiMyBidPblancList.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : myBidPblancList
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/myBidPblancList
	 * @throws Exception 
	 */
	@RequestMapping(value = "/myBidPblancList")
	public String myBidPblancList(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 사업자등록번호 세션

		FwkTransferObject trans = oproEbidMyPblancService.myBidPblancListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		/*Map<String,Object> map = FwkIndigoServiceClient.requestExtToLocalHttp(OepConst.IfId.MY_BID_PBLANC_LIST, parameterMap);
		
		model.addAllAttributes(map);*/
		
		
		
		
		return parameterMap.getOproViewName(prefixUrl);
		
	}

	/**
	 * <pre>
	 * 1. 개요 : 나의 입찰공고 상세 페이지 이동
	 * 2. 처리내용 : 
     *      - 나의 입찰공고 상세 페이지로 이동한다.
     *      - View(JSP명) 를 반환한다.(elbi/oepElbiMyBidPblancDetail.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : myBidPblancDetail
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/oepElbiMyBidPblancDetail
	 * @throws Exception 
	 */
	@RequestMapping(value = "/myBidPblancDetail")
	public String myBidPblancDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidMyPblancService.myBidPblancDetailInqire(parameterMap);
		trans.put("serverTime", FwkDateUtil.getCurrentDateTimeAsString());
		model.addAllAttributes(trans);
		
		
		
		
		return parameterMap.getOproViewName(prefixUrl);
		
	}

	/**
	 * 
	 * <pre>
	 * 1. 개요 : 지문 인식 모의 공고 청렴이행각서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : fngprtRecogImtIntgtyFlflMmrdInqire
	 * @date : 2015. 08. 11.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 08. 11.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/fngprtRecogImtIntgtyFlflMmrdInqire")
	public String fngprtRecogImtIntgtyFlflMmrdInqire(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidMyPblancService.intgtyFlflMmrdInqire(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getOproPopupViewName(prefixPopUrl);
		
	}

	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 청렴이행각서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : intgtyFlflMmrdInqire
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/intgtyFlflMmrdInqire")
	public String intgtyFlflMmrdInqire(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproEbidMyPblancService.intgtyFlflMmrdInqire(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getOproPopupViewName(prefixPopUrl);
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰참가 등록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidPartcptRegist
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bidPartcptRegist")
	public String bidPartcptRegist(final HttpServletRequest request, final Model model) throws Exception{
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();
		}
		parameterMap.put("P_PARTCPTN_IP_ADRES", ip); // 접속아이피 주소
		
		oproEbidMyPblancService.bidPartcptRegist(parameterMap);
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰공고 의견 등록 팝업 이동
	 * 2. 처리내용 : 
     *      - 입찰공고 의견 등록 팝업 페이지로 이동한다.
     *      - View(JSP명) 를 반환한다.(elbi/popup/oepElbiPopupBidPblancOpinionRegistForm.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : bidPblancOpinionRegistForm
	 * @date : 2015. 03. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/popup/oepElbiPopupBidPblancOpinionRegistForm.jsp
	 */
	@RequestMapping(value = "/popup/bidDcRegistForm")
	public String bidDcRegistForm(final HttpServletRequest request, final Model model) throws Exception{		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 사업자등록번호 세션
		FwkTransferObject trans = oproEbidMyPblancService.bidDcRegistForm(parameterMap);
		trans.put("VEND_NM", user.get("USR_NM"));
		model.addAllAttributes(trans);
		return parameterMap.getOproPopupViewName(prefixPopUrl);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입찰공고 의견 수정
	 * 2. 처리내용 : 
    *      - 입찰공고 의견 수정 서비스를 호출한다.
     *     - 입찰공고 의견 결과를 Model에 담는다.
     *     - JSON_VIEW로 반환한다.
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : bidPblancOpinionUpdt
	 * @date : 2015. 02. 12.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 12.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bidDcRegist")
	public String bidDcRegist(final HttpServletRequest request, final Model model) throws Exception{		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 사업자등록번호 세션
		oproEbidMyPblancService.bidDcRegist(parameterMap);
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/bidDcDel")
	public String bidDcDel(final HttpServletRequest request, final Model model) throws Exception{		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
		parameterMap.put("P_VEND_REG_NO", user.get("USR_ID")); // 사업자등록번호 세션
		oproEbidMyPblancService.bidDcDel(parameterMap);
		return JSON_VIEW;
	}
}
