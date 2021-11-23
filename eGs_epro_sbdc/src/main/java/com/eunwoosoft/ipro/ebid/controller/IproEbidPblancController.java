package com.eunwoosoft.ipro.ebid.controller; 

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.ParameterMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.itfc.service.CommItfcService;
import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproEbidPblancService;

/**
 * 전자입찰 > 입찰공고 Controller 
 * <pre>
 * oda.iep.elbi.controller 
 *    |_ IepEbiBidPblancController.java
 * 
 * </pre>
 * @date : 2015. 01. 14. 오전 11:33:33
 * @version : 1.0
 * @author : 은우소프트 하성윤
 */
@Controller
@RequestMapping(value = "/ebid")
public class IproEbidPblancController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";
	String prefixPopUrl = "/ipro/views/ebid/popup/";
	
	@Resource(name="iproEbidPblancService")
	private IproEbidPblancService iproEbidPblancService;
	
	@Resource(name="commItfcService")
	private CommItfcService commItfcService;

	/**
	 * <pre>
	 * 1. 개요 : 입찰공고목록 
	 * 2. 처리내용 : 
     *      - 입찰계획목록조회 서비스를 호출한다.
     *      - 입찰계획목록조회 결과를 Model에 담는다.
     *      - View(JSP명) 를 반환한다.(elbi/iepElbiBidPblancList.jsp)
	 * </pre>
	 * @Method Name : bidPblancList
	 * @date : 2015. 02. 09.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 09.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/iepElbiBidPblancList
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bidPblancList")
	public String bidPblancList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.bidPblancListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}

	/**
	 * <pre>
	 * 1. 개요 : 입찰공고 상세 페이지 이동
	 * 2. 처리내용 : 
     *      - 입찰공고 상세 페이지로 이동한다.
     *      - View(JSP명) 를 반환한다.(elbi/iepElbiBidPblancDetail.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : bidPblancDetail
	 * @date : 2015. 02. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/iepElbiBidPblancDetail
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bidPblancDetail")
	public String bidPblancDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkDataEntity user = parameterMap.getLoginResult();
		FwkTransferObject trans = iproEbidPblancService.bidPblancDetailInqire(parameterMap);
		trans.put("toDay", FwkDateUtil.getCurrentDateTimeAsString());
		trans.put("SABUN", user.getString("SABUN"));
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	

	/**
	 * <pre>
	 * 1. 개요 : 정정공고 폼 이동
	 * 2. 처리내용 : 
     *      - 정정공고 폼 서비스를 호출한다.
     *      - View(JSP명) 를 반환한다.(elbi/bidPblancUpdtForm.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : bidPblancUpdtForm
	 * @date : 2015. 02. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/bidPblancUpdtForm
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bidPblancUpdtForm")
	public String updtPblancRegistForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.updtPblancRegistForm(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}

	/**
	 * <pre>
	 * 1. 개요 : 정정공고 등록
	 * 2. 처리내용 : 
	 *      - 정정공고 등록한다.
	 *      - 입찰공고목록조회로 이동한다.
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : updtPblancRegist
	 * @date : 2015. 02. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/iepElbiBidPblancDetail.jsp
	 * @throws Exception 
	 */
	@RequestMapping(value = "/updtPblancRegist")
	public String updtPblancRegist(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.updtPblancRegist(parameterMap);
		model.addAllAttributes(trans);
				
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 취소공고 등록
	 * 2. 처리내용 : 
	 *      - 취소공고 등록한다.
	 *      - 입찰공고목록조회로 이동한다.
	 * 3. 입출력 : 
	 *  -----------------------------------------------------------------------
	 * I/O   항목                                              타입               설명                           참고사항
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : canclPblancRegist
	 * @date : 2015. 02. 11.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 11.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
	 * @param model
	 * @return JSP 화면명- elbi/iepElbiBidPblancDetail.jsp
	 * @throws Exception 
	 */
	@RequestMapping(value = "/canclPblancRegist")
	public String canclPblancRegist(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		iproEbidPblancService.canclPblancRegist(parameterMap);
		
		return JSON_VIEW;
	}
	

	/**
	 * <pre>
	 * 1. 개요 : 취소 공고 등록 팝업 이동
	 * 2. 처리내용 : 
     *      - 취소 공고 등록 팝업 페이지로 이동한다.
     *      - View(JSP명) 를 반환한다.(elbi/popup/iepElbiPopupCanclPblancRegistForm.jsp)
     * 3. 입출력 : 
     *  -----------------------------------------------------------------------
     * I/O   항목                                              타입               설명                           참고사항
     * -----------------------------------------------------------------------
     * 
     * -----------------------------------------------------------------------
	 * </pre>
	 * @Method Name : canclPblancRegistForm
	 * @date : 2015. 02. 10.
	 * @author : 은우소프트 하성윤
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 02. 10.		은우소프트 하성윤				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- elbi/popup/iepElbiPopupCanclPblancRegistForm.jsp
	 * @throws SDBException 
	 */
	@RequestMapping(value = "/popup/canclPblancRegistForm")
	public String canclPblancRegistForm(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.bidPblancDetailInqire(parameterMap);
		// 오늘 날짜 넘겨주기
		trans.put("canclProcessDt", FwkDateUtil.getCurrentDateTimeAsString());
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.controller.IproEbidPblancController.java
	 * @Method : bidNotiRegistForm
	 * @author : sanghoon_joen
	 * @date : 2018. 10. 12. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/popup/bidNotiRegistForm")
	public String bidNotiRegistForm(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.bidNotiRegistForm(parameterMap);
		trans.put("today", FwkDateUtil.getCurrentDateTimeAsString());
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.controller.IproEbidPblancController.java
	 * @Method : bidNotiUpdt
	 * @author : sanghoon_joen
	 * @date : 2018. 10. 12. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bidNotiUpdt")
	public String bidNotiUpdt(final HttpServletRequest request, final Model model)  {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		// 답변 등록일시
		iproEbidPblancService.bidNotiUpdt(parameterMap);
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_ebs.com.eunwoosoft.ipro.ebid.controller.IproEbidPblancController.java
	 * @Method : bidNotiDel
	 * @author : sanghoon_joen
	 * @date : 2018. 10. 12. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bidNotiDel")
	public String bidNotiDel(final HttpServletRequest request, final Model model)  {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		// 답변 등록일시
		iproEbidPblancService.bidNotiDel(parameterMap);
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 공동수급협정서 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : copertnSpldmdTrtyOfeInqire
	 * @date : 2015. 6. 26.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 06. 26.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/popup/copertnSpldmdTrtyOfeInqire")
	public String copertnSpldmdTrtyOfeInqire(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.copertnSpldmdTrtyOfeInqire(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 관심입찰 등록한 업체 목록
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : intrstBidEntrpsList
	 * @date : 2015. 01. 05.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 01. 06.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/popup/intrstBidEntrpsList")
	public String intrstBidEntrpsList(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.intrstBidEntrpsList(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 유찰 등록 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidPblancController.java
	 * @Method : fibRegistForm
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 9. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/fibRegistForm")
	public String fibRegistForm(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.canclPblancRegistForm(parameterMap);
		trans.put("today", FwkDateUtil.getCurrentDateAsString());
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 유찰 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidPblancController.java
	 * @Method : fibRegist
	 * @author : sanghoon_joen
	 * @date : 2018. 3. 9. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/fibRegist")
	public String fibRegist(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject rtnTrans = new FwkTransferObject();
		
		rtnTrans = iproEbidPblancService.fibRegist(parameterMap);
		
		// 연계 웹서비스 호출
		try {
			parameterMap.put("P_CHARSET", "UTF-8");                                //호출수행 인코딩
			parameterMap.put("P_CASE_CODE", "PRGS");                         //연계케이스코드
			parameterMap.put("P_KEY", rtnTrans.get("P_PCRQ_NO"));  //연계키값
			parameterMap.put("P_FILE_BRDN_NO", "");                                //연계파일번호
			
			//rtnTrans = commItfcService.callOtherSysURL(parameterMap);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 재입찰 등록 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidPblancController.java
	 * @Method : fibReBidForm
	 * @author : 맹경열
	 * @date : 2019. 02. 11. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/fibReBidForm")
	public String fibReBidForm(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.roundUpdtForm(parameterMap);
		trans.put("today", FwkDateUtil.getCurrentDateAsString());
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixPopUrl);
	}	
	
	/**
	 * <pre>
	 * 1.개요 : 재입찰 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidPblancController.java
	 * @Method : reBid
	 * @author : 맹경열
	 * @date : 2019. 02. 11. 
	 * @param request
	 * @param model
	 * @return
	 */	
	@RequestMapping(value="/reBid")
	public String reBid(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		iproEbidPblancService.reBid(parameterMap);		
		
		return JSON_VIEW;
	}
	
	
	@RequestMapping(value="/popup/roundUpdtForm")
	public String roundUpdtForm(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.roundUpdtForm(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}

	/**
	 * <pre>
	 * 1.개요 : 재입찰 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidPblancController.java
	 * @Method : roundUpdt
	 * @author : 맹경열
	 * @date : 2019. 02. 11. 
	 * @param request
	 * @param model
	 * @return
	 */	
	@RequestMapping(value="/roundUpdt")
	public String roundUpdt(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.roundUpdt(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/updtAnncSttusChange")
	public String updtAnncSttusChange(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		iproEbidPblancService.updtAnncSttusChange(parameterMap);	
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/updtAnncDelete")
	public String updtAnncDelete(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		iproEbidPblancService.updtAnncDelete(parameterMap);	
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/popup/vendTndrList")
	public String vendTndrList(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.vendTndrList(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	@RequestMapping(value = "/ebidApprSendCrrc")
	public String ebidApprSendCrrc(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.ebidApprSendCrrc(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/ebidApprSendCncl")
	public String ebidApprSendCncl(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.ebidApprSendCncl(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	@RequestMapping(value = "/canclPblancDel")
	public String canclPblancDel(final HttpServletRequest request, final Model model) {		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.canclPblancDel(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1.개요 : 입찰시간등록팝업 - 2단계경쟁
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidPblancController.java
	 * @Method : tndrBidForm
	 * @author : 맹경열
	 * @date : 2019. 03. 15. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/tndrBidForm")
	public String tndrBidForm(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.roundUpdtForm(parameterMap);
		trans.put("today", FwkDateUtil.getCurrentDateAsString());
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 입찰시간등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidPblancController.java
	 * @Method : tndrBidTimeRegist
	 * @author : 맹경열
	 * @date : 2019. 03. 15. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/tndrBidTimeRegist")
	public String tndrBidTimeRegist(final HttpServletRequest request, final Model model) {
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidPblancService.tndrTimeUpdt(parameterMap);
		trans.put("today", FwkDateUtil.getCurrentDateAsString());
		model.addAllAttributes(trans);
		
		return JSON_VIEW;
	}		
	
}
