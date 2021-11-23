package com.eunwoosoft.ipro.ebid.controller; 

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.ParameterMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eunwoosoft.comm.menu.controller.AbstractIproMenuController;
import com.eunwoosoft.frwk.fol.dto.FwkDataEntity;
import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.fol.util.FwkDateUtil;
import com.eunwoosoft.frwk.fol.util.FwkFormatUtil;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.ebid.service.IproEbidPlanService;
import com.eunwoosoft.ipro.ebid.service.IproEbidResultService;

/**
 * 입찰결과조회 Controller 
 * <pre>
 * com.eunwoosoft.ipro.ebid.controller 
 *    |_ IproEbidResultController.java
 * 
 * </pre>
 * @date : 2015. 04. 02. 오후 13:52:31
 * @version : 1.0
 * @author : 은우소프트 손연우
 */
@Controller
@RequestMapping(value = "/ebid")
public class IproEbidResultController extends AbstractIproMenuController {
	String prefixUrl = "/ipro/views/ebid/";
	String prefixPopUrl = "/ipro/views/ebid/popup/";
	String prefixExcelUrl = "ipro/bid/";
	
	@Resource(name="iproEbidResultService")
	private IproEbidResultService iproEbidResultService;
	
	@Resource(name="iproEbidPlanService")
	private IproEbidPlanService iproEbidPlanService;
	

	/**
	 * <pre>
	 * 1. 개요 : 입찰결과조회 목록 
	 * 2. 처리내용 : 
     *      - 입찰결과조회 목록조회 서비스를 호출한다.
     *      - 입찰결과조회 목록조회 결과를 Model에 담는다.
     *      - View(JSP명) 를 반환한다.(ebid/bidResultList.jsp)
	 * </pre>
	 * @Method Name : bidResultList
	 * @date : 2015. 04. 02.
	 * @author : 은우소프트 손연우
	 * @history :
	 *	-----------------------------------------------------------------------
	 *	변경일				      작성자						   변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 02.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
     *                  요청정보를 {@link ParameterMap} 형태로 담고 있는 {@link HttpServletRequest}
     * @param model
     * @return JSP 화면명- ebid/bidResultList
	 * @throws Exception 
	 */
	@RequestMapping(value = "/bidResultList")
	public String bidResultList(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		if(parameterMap.get("P_CHRGR_ID_S") == null) {
			FwkDataEntity user = (FwkDataEntity) parameterMap.get("loginResult");
			parameterMap.put("P_CHRGR_ID_S", user.getString("USR_ID"));
			parameterMap.put("P_CHRGR_NM_S", user.getString("USR_NM"));
		}
		FwkTransferObject trans = iproEbidResultService.bidResultListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 	
	 * <pre>
	 * 1. 개요 : 입찰결과 엑셀 다운로드
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidResultExcelDwld
	 * @date : 2015. 04. 02.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 04. 02.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/bidResultExcelDwld")
	public String bidResultExcelDwld(final HttpServletRequest request, final Model model) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		if(parameterMap.get("P_OPENG_BEGIN_DT_S") == null)			
		{
			parameterMap.put("P_OPENG_BEGIN_DT_S",FwkDateUtil.convertToString(FwkDateUtil.addMonths(FwkDateUtil.convertToDate(FwkDateUtil.getCurrentDateAsString()), -3), "yyyyMMdd")+"000000");
		}else if("".equals(parameterMap.get("P_OPENG_BEGIN_DT_S"))){
		}else
		{
			parameterMap.put("P_OPENG_BEGIN_DT_S", FwkFormatUtil.formatDate((String)parameterMap.get("P_OPENG_BEGIN_DT_S"), "yyyy-MM-dd", "yyyyMMdd")+"000000");
		}
		if(parameterMap.get("P_OPENG_END_DT_S") == null)
		{
			parameterMap.put("P_OPENG_END_DT_S",FwkDateUtil.convertToString(FwkDateUtil.addMonths(FwkDateUtil.convertToDate(FwkDateUtil.getCurrentDateAsString()), +1), "yyyyMMdd")+"235959");
		}else if("".equals(parameterMap.get("P_OPENG_END_DT_S"))){
		}else
		{
			parameterMap.put("P_OPENG_END_DT_S", FwkFormatUtil.formatDate((String)parameterMap.get("P_OPENG_END_DT_S"), "yyyy-MM-dd", "yyyyMMdd")+"235959");
		}
		
		FwkTransferObject trans = iproEbidResultService.bidResultListInqire(parameterMap);
		
		model.addAttribute("dataList",trans.get(IproEbidResultService.BID_RESULT_LIST));
		model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("destFileName","입찰결과 목록");
		return EXCEL_VIEW;
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰결과 상세
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidResultDetail
	 * @date : 2015. 04. 03.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 03. 03.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/bidResultDetail")
	public String bidResultDetail(final HttpServletRequest request, final Model model) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidResultService.bidResultDetailInqire(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	@RequestMapping(value="/bidResultRegistForm")
	public String bidResultRegistForm(final HttpServletRequest request, final Model model) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidResultService.bidResultDetailInqire(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 적격심사 평가결과 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : properJdgmnEvlInqire
	 * @date : 2015. 4. 6.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 6.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/properJdgmnEvlInqire")
	public String properJdgmnEvlInqire(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidResultService.properJdgmnEvlInqire(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰결과 상세 – 결격사유 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : bidResultBrdoResnInqire
	 * @date : 2015. 4. 6.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 4. 6.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/bidResultBrdoResnInqire")
	public String bidResultBrdoResnInqire(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans  = iproEbidResultService.bidResultBrdoResnInqire(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 개요 : 입찰결과 상세 - 품목별 세부입찰내역 조회
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : prdlstAcctoDetailBidDtlsInqire
	 * @date : 2015. 8. 18.
	 * @author : 은우소프트 손연우
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2015. 8. 18.		은우소프트 손연우				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/popup/prdlstAcctoDetailBidDtlsInqire")
	public String prdlstAcctoDetailBidDtlsInqire(final HttpServletRequest request, final Model model){
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidResultService.prdlstAcctoDetailBidDtlsInqire(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 	
	 * <pre>
	 * 1. 개요 : 유찰 복수예가 엑셀 다운로드
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : compnoPrdprcExcelDwld
	 * @date : 2016. 02. 29.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2016. 02. 29.		은우소프트 전상훈				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/compnoPrdprcExcelDwld")
	public String compnoPrdprcExcelDwld(final HttpServletRequest request, final Model model) throws Exception {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);

		FwkTransferObject trans = iproEbidResultService.compnoPrdprcListInqire(parameterMap);
		
		model.addAttribute("dataList",trans.get(IproEbidResultService.COMPNO_PRDPRC_LIST));
		model.addAttribute("templateFileName", parameterMap.getViewName(prefixExcelUrl) + ".xls");
		model.addAttribute("destFileName","복수예가");
		return EXCEL_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 유찰 복수예가 엑셀 다운로드
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : 
	 * @date : 2018. 10. 12.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 10. 12.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */	
	@RequestMapping(value="/insertG2BVendInfo")
	public String insertG2BVendInfo(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidResultService.insertG2BVendInfo(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 지급각서 보기 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.controller.IproEbidResultController.java
	 * @Method : insertG2BVendInfo
	 * @author : sanghoon_joen
	 * @date : 2019. 1. 2. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/popup/grntDetail")
	public String grntDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidResultService.grntDetail(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 청렴이행각서 보기 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.controller.IproEbidResultController.java
	 * @Method : cleanDetail
	 * @author : sanghoon_joen
	 * @date : 2019. 1. 2. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/popup/cleanDetail")
	public String cleanDetail(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidResultService.cleanDetail(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 재공고 작성폼
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : bidPblancReRegistForm
	 * @author : 맹경열
	 * @date : 2019. 03. 05. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bidPblancReRegistForm")
	public String bidPblancReRegistForm(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		// 로그인 사용자 정보 조회
		FwkTransferObject trans = iproEbidPlanService.bidPlanUpdtForm(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 현재 진행중인 재공고건이 있는지 조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri.com.eunwoosoft.ipro.ebid.controller.IproEbidProconController.java
	 * @Method : reAnnoCheck
	 * @author : 하성윤
	 * @date : 2019. 08. 08. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/reAnnoCheck")
	public String reAnnoCheck(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = iproEbidPlanService.reAnnoCheck(parameterMap);
		
		model.addAttribute("P_CNT", trans.get("P_CNT"));
		
		return JSON_VIEW;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 유찰 구매관리 이관
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : pvctSendUpdt
	 * @date : 2019. 03. 05.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 05.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */	
	@RequestMapping(value="/pvctSendUpdt")
	public String pvctSendUpdt(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidResultService.insertG2BVendInfo(parameterMap);
		model.addAllAttributes(trans);
		return JSON_VIEW;
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 라운드정보 확인
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.controller.IproEbidResultController.java
	 * @Method : roundInfoPopup
	 * @date : 2019. 03. 05.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 03. 05.		은우소프트 맹경열				최초 작성 
	 *	-----------------------------------------------------------------------
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/popup/roundInfoPopup")
	public String roundInfoPopup(final HttpServletRequest request, final Model model) throws Exception {
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidResultService.bidRoundList(parameterMap);
		model.addAllAttributes(trans);
		return parameterMap.getPopupViewName(prefixPopUrl);
	}	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 라운드 정보 상세
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.controller.IproEbidResultController.java
	 * @Method : roundInfoDetail
	 * @author : JanDi_Eun
	 * @date : 2019. 7. 5. 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/roundInfoDetail")
	public String roundInfoDetail(final HttpServletRequest request, final Model model) throws Exception {		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidResultService.roundInfoDetail(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return "forward:/ebid/popup/roundInfoPopup.do";
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 품목별 업체 투찰금액 상세 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_pri_keri.com.eunwoosoft.ipro.ebid.controller.IproEbidResultController.java
	 * @Method : tndrItemAmtList
	 * @author : JanDi_Eun
	 * @date : 2019. 8. 13. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popup/tndrItemAmtList")
	public String tndrItemAmtList(final HttpServletRequest request, final Model model) {
		
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = iproEbidResultService.tndrItemAmtList(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);			
		
		return parameterMap.getPopupViewName(prefixPopUrl);
	}
		
}
