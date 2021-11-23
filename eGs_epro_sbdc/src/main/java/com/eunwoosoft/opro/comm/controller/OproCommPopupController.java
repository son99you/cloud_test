package com.eunwoosoft.opro.comm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eunwoosoft.frwk.fol.dto.FwkTransferObject;
import com.eunwoosoft.frwk.prl.AbstractFwkController;
import com.eunwoosoft.frwk.prl.request.FwkParameterMap;
import com.eunwoosoft.ipro.comm.service.IproCommDefaultService;
import com.eunwoosoft.opro.comm.service.OproCommPopupService;
import com.eunwoosoft.opro.user.service.OproUserVendInfoService;

@RequestMapping(value = "/opro/comm/popup") 
@Controller
public class OproCommPopupController extends AbstractFwkController {
	String prefixUrl = "/opro/views/comm/popup/";

	@Resource(name="oproCommPopupService") 
	private OproCommPopupService oproCommPopupService;
	
	@Resource(name="oproUserVendInfoService") 
	private OproUserVendInfoService oproUserVendInfoService;
	
	@Resource(name="iproCommDefaultService")
	private IproCommDefaultService iproCommDefaultService;
	
	/**
	 * 사업부서 조회화면 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : bsnsDeptList
	 * @author : SungYoon_Ha
	 * @date : 2017. 6. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bsnsDeptList")
	public String bsnsDeptList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);

		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰참가신청서보기 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : bidPartcptReqstdocPopupInqire
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 20. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bidPartcptReqstdocPopupInqire")
	public String bidPartcptReqstdocPopupInqire(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);

		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 공동수급협정서 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : bidPartcptReqstdocPopupInqire
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 20. 
	 * @param request 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/copertnSpldmdTrtyOfePopupInqire")
	public String copertnSpldmdTrtyOfePopupInqire(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);

		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰포기신청 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : bidPartcptAbandnReqstdocRegistForm
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 20. 
	 * @param request  
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bidPartcptAbandnReqstdocRegistForm")
	public String bidPartcptAbandnReqstdocRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans); 

		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 업체 추가 부서 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : ccpyPopupList
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 21. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ccpyPopupList")
	public String ccpyPopupList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans); 

		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 :  의견등록 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : bidPblancOpinionRegistForm
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 23. 
	 * @param request 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bidPblancOpinionRegistForm")
	public String bidPblancOpinionRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans); 

		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 예가선택업체 팝업조회
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : bidResultPrdprcChoiseEntrpsInqire
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 23. 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/bidResultPrdprcChoiseEntrpsInqire")
	public String bidResultPrdprcChoiseEntrpsInqire(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans); 

		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : popupBidPblancDetail
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 23. 
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/popupBidPblancDetail")
	public String popupBidPblancDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans); 

		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 계약체결대상 상세 반려 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : contReturn
	 * @author : JanDi_Eun
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/contReturn")
	public String contReturn(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans); 
		
		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 입찰설명회 참가등록
	 * 2.처리내용 :  입찰설명회 참가등록
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : popupBidRegistForm
	 * @author : JuYeon_Lee
	 * @date : 2017. 6. 29. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/popupBidRegistForm")
	public String popupBidRegistForm(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans); 

		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	

	/**
	 * 업체목록 팝업
	 * <pre>
	 * 1.개요 : 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : entrpsList
	 * @author : SungYoon_Ha
	 * @date : 2017. 7. 3. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/entrpsList")
	public String entrpsList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans = oproCommPopupService.entrpsInqireList(parameterMap);
		
		model.addAllAttributes(trans);

		return parameterMap.getOproPopupViewName(prefixUrl);
	}

	/**
	 * <pre>
	 * 1.개요 : 전자세금계산서 인쇄 팝업
	 * 2.처리내용 : 스마트빌 참조
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : invoicePrint
	 * @author : SungYoon Ha
	 * @date : 2017. 8. 16. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/invoicePrint")
	public String invoicePrint(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		

		// 데이터 세팅
		String gbn = parameterMap.getString("gbn");
		
		String issue_id = "";

		String dem_regnum = "";
		String dem_vendor_name = "";
		String dem_owner_name = "";
		String dem_addr = "";
		String dem_biz_type = "";
		String dem_biz_kind = "";
		
		String sup_regnum = "";
		String sup_vendor_name = "";
		String sup_owner_name = "";
		String sup_addr = "";
		String sup_biz_type = "";
		String sup_biz_kind = "";
		
		String pubDate = "";
		String pubDateMM = "";
		String pubDateDD = "";
		String amtBlank = "";
		String amtSum = "";
		String surtax = "";
		String remark = "";
		
		String itemDateMM = "";
		String itemDateDD = "";
		
		String itemName = "";
		String itemStd = "";
		String itemQty = "";
		String itemPrice = "";
		String itemAmt = "";
		String itemStax = "";
		String itemRemark = "";

		String amtTotal = "";
		String amtCash = "";
		String amtCheck = "";
		String amtBill = "";
		String amtCredit = "";
		
		String recept_name = "";
		
		
		if("Z1".equals(gbn)){
			issue_id = "20170630041000008f2g009ft";

			dem_regnum = "402 - 85 - 13635";
			dem_vendor_name = "㈜휴비스(전주)";
			dem_owner_name = "신유동";
			dem_addr = "전북 전주시 덕진구 기린대로 787(팔복동2가)";
			dem_biz_type = "제조,부동산";
			dem_biz_kind = "합성섬유,재생섬유,임대";
			
			sup_regnum = "119 - 85 - 35038";
			sup_vendor_name = "코오롱글로벌(주)상사부문";
			sup_owner_name = "윤창운";
			sup_addr = "인천광역시 연수구 송도과학로 32(송도동, 송도IT센터S501호, S701호~S1304호)";
			sup_biz_type = "도매및소매업외";
			sup_biz_kind = "상품종합도소매외";
			
			pubDate = "2017";
			pubDateMM = "06";
			pubDateDD = "30";
			amtBlank = "3";
			amtSum = "975875947";
			surtax = "0";
			remark = "L0226706WS00267, USD 856.332";

			int a= 0 ;
			for(int i=12; i>0; i--){
				if( amtSum.length() >= i){
					parameterMap.put("amtSum"+i, amtSum.substring(a, a+1));
					a++;
				}else{
					parameterMap.put("amtSum"+i, "");
				}
			}
			
			int b= 0 ;
			for(int i=11; i>0; i--){
				if( surtax.length() >= i){
					parameterMap.put("surtax"+i, surtax.substring(b, b+1));
					b++;
				}else{
					parameterMap.put("surtax"+i, "");
				}
			}
			
			itemDateMM = "06";
			itemDateDD = "30";
			
			itemName = "전)CP-3,5,6 외 0건 외 0건";
			itemStd = "";
			itemQty = "";
			itemPrice = "";
			itemAmt = "975,875,947";
			itemStax = "0";
			itemRemark = "";

			amtTotal = "975,875,947";
			amtCash = "0";
			amtCheck = "0";
			amtBill = "0";
			amtCredit = "0";
			
			recept_name = "청구";
		}else if("Z2".equals(gbn)){
			issue_id = "2016053041000008f2g0002n";

			dem_regnum = "402 - 85 - 13635";
			dem_vendor_name = "㈜휴비스(전주)";
			dem_owner_name = "유배근";
			dem_addr = "전북 전주시 덕진구 기린대로 787(팔복동2가)";
			dem_biz_type = "제조,부동산";
			dem_biz_kind = "합성섬유,재생섬유,임대";
			
			sup_regnum = "120 - 86 - 49526";
			sup_vendor_name = "사우산업개발(주)";
			sup_owner_name = "고두현";
			sup_addr = "경기 성남시 중원구 상대원동 144-3 우림라이온스밸리";
			sup_biz_type = "제조업 외";
			sup_biz_kind = "철물공사 실내건축 공사업외";
			
			pubDate = "2016";
			pubDateMM = "05";
			pubDateDD = "30";
			amtBlank = "6";
			amtSum = "325000";
			surtax = "32500";
			remark = "";
			

			int a= 0 ;
			for(int i=12; i>0; i--){
				if( amtSum.length() >= i){
					parameterMap.put("amtSum"+i, amtSum.substring(a, a+1));
					a++;
				}else{
					parameterMap.put("amtSum"+i, "");
				}
			}
			
			int b= 0 ;
			for(int i=11; i>0; i--){
				if( surtax.length() >= i){
					parameterMap.put("surtax"+i, surtax.substring(b, b+1));
					b++;
				}else{
					parameterMap.put("surtax"+i, "");
				}
			}
			
			itemDateMM = "05";
			itemDateDD = "30";
			
			itemName = "J2 LMF 외 0건 외 0건";
			itemStd = "";
			itemQty = "";
			itemPrice = "";
			itemAmt = "325,000";
			itemStax = "32,500";
			itemRemark = "";

			amtTotal = "357,500";
			amtCash = "0";
			amtCheck = "0";
			amtBill = "0";
			amtCredit = "0";
			
			recept_name = "청구";
		}else if("Z3".equals(gbn)){
			issue_id = "2016113041000008f2g00491";

			dem_regnum = "402 - 85 - 13635";
			dem_vendor_name = "㈜휴비스(전주)";
			dem_owner_name = "유배근";
			dem_addr = "전북 전주시 덕진구 기린대로 787(팔복동2가)";
			dem_biz_type = "제조,부동산";
			dem_biz_kind = "합성섬유,재생섬유,임대";
			
			sup_regnum = "127 - 81 - 95058";
			sup_vendor_name = "(주)포스프";
			sup_owner_name = "김완수";
			sup_addr = "경기도 포천시 가산면 금현리 585-2 ***";
			sup_biz_type = "도매";
			sup_biz_kind = "합성섬유";
			
			pubDate = "2016";
			pubDateMM = "11";
			pubDateDD = "30";
			amtBlank = "3";
			amtSum = "185838000";
			surtax = "18583800";
			remark = "";
			

			int a= 0 ;
			for(int i=12; i>0; i--){
				if( amtSum.length() >= i){
					parameterMap.put("amtSum"+i, amtSum.substring(a, a+1));
					a++;
				}else{
					parameterMap.put("amtSum"+i, "");
				}
			}
			
			int b= 0 ;
			for(int i=11; i>0; i--){
				if( surtax.length() >= i){
					parameterMap.put("surtax"+i, surtax.substring(b, b+1));
					b++;
				}else{
					parameterMap.put("surtax"+i, "");
				}
			}
			
			itemDateMM = "11";
			itemDateDD = "30";
			
			itemName = "J3 MIC40 외 19건 외 0건";
			itemStd = "";
			itemQty = "";
			itemPrice = "";
			itemAmt = "185,838,000";
			itemStax = "18,583,800";
			itemRemark = "";

			amtTotal = "204,421,800";
			amtCash = "0";
			amtCheck = "0";
			amtBill = "0";
			amtCredit = "0";
			
			recept_name = "청구";
		}else if("Z4".equals(gbn)){
			issue_id = "2016093041000008f2g002rf";

			dem_regnum = "610 - 85 - 20213";
			dem_vendor_name = "㈜휴비스(울산)";
			dem_owner_name = "유배근";
			dem_addr = "울산광역시 남구 처용로 718(황성동)";
			dem_biz_type = "제조";
			dem_biz_kind = "합성섬유,재생섬유";
			
			sup_regnum = "503 - 82 - 03443";
			sup_vendor_name = "한국섬유개발연구원";
			sup_owner_name = "문혜강";
			sup_addr = "대구광역시 서구 국채보상로 136(중리동) ***";
			sup_biz_type = "써비스";
			sup_biz_kind = "기술검사서비스";
			
			pubDate = "2016";
			pubDateMM = "09";
			pubDateDD = "30";
			amtBlank = "3";
			amtSum = "6506000";
			surtax = "650600";
			remark = "";
			

			int a= 0 ;
			for(int i=12; i>0; i--){
				if( amtSum.length() >= i){
					parameterMap.put("amtSum"+i, amtSum.substring(a, a+1));
					a++;
				}else{
					parameterMap.put("amtSum"+i, "");
				}
			}
			
			int b= 0 ;
			for(int i=11; i>0; i--){
				if( surtax.length() >= i){
					parameterMap.put("surtax"+i, surtax.substring(b, b+1));
					b++;
				}else{
					parameterMap.put("surtax"+i, "");
				}
			}
			
			itemDateMM = "09";
			itemDateDD = "30";
			
			itemName = "U1 LMD51 외 1건 외 0건";
			itemStd = "";
			itemQty = "";
			itemPrice = "";
			itemAmt = "6,506,000";
			itemStax = "650,600";
			itemRemark = "";

			amtTotal = "7,156,600";
			amtCash = "0";
			amtCheck = "0";
			amtBill = "0";
			amtCredit = "0";
			
			recept_name = "청구";
		}else if("Z5".equals(gbn)){
			issue_id = "2016103141000008f2g003jy";

			dem_regnum = "610 - 85 - 20213";
			dem_vendor_name = "㈜휴비스(울산)";
			dem_owner_name = "유배근";
			dem_addr = "울산광역시 남구 처용로 718(황성동)";
			dem_biz_type = "제조";
			dem_biz_kind = "합성섬유,재생섬유";
			
			sup_regnum = "611 - 05 - 27537";
			sup_vendor_name = "태양실업";
			sup_owner_name = "최천수";
			sup_addr = "경남 거창군 위천면 당산농공길 65 ***";
			sup_biz_type = "제조업";
			sup_biz_kind = "부직포외";
			
			pubDate = "2016";
			pubDateMM = "10";
			pubDateDD = "30";
			amtBlank = "6";
			amtSum = "437000";
			surtax = "43700";
			remark = "";
			

			int a= 0 ;
			for(int i=12; i>0; i--){
				if( amtSum.length() >= i){
					parameterMap.put("amtSum"+i, amtSum.substring(a, a+1));
					a++;
				}else{
					parameterMap.put("amtSum"+i, "");
				}
			}
			
			int b= 0 ;
			for(int i=11; i>0; i--){
				if( surtax.length() >= i){
					parameterMap.put("surtax"+i, surtax.substring(b, b+1));
					b++;
				}else{
					parameterMap.put("surtax"+i, "");
				}
			}
			
			itemDateMM = "10";
			itemDateDD = "31";
			
			itemName = "U1 LMRED 외 0건 외 0건";
			itemStd = "";
			itemQty = "";
			itemPrice = "";
			itemAmt = "437,000";
			itemStax = "43,700";
			itemRemark = "";

			amtTotal = "480,700";
			amtCash = "0";
			amtCheck = "0";
			amtBill = "0";
			amtCredit = "0";
			
			recept_name = "청구";
		}else{
			issue_id = "";

			dem_regnum = "119 - 86 - 02801";
			dem_vendor_name = "(주)은우소프트";
			dem_owner_name = "정한규";
			dem_addr = "서울시 구로구 디지털로 33길 28, 1402(구로동, 우림EBIZ 1차)";
			dem_biz_type = "서비스,도매업";
			dem_biz_kind = "소프트웨어개발 및 공급, 유지보수";
			
			sup_regnum = "111 - 11 - 11119  ";
			sup_vendor_name = "케이소프트";
			sup_owner_name = "홍길동";
			sup_addr = "임시 임시구 임시로 5길 21";
			sup_biz_type = "통신";
			sup_biz_kind = "전자";
			
			pubDate = "2017";
			pubDateMM = "06";
			pubDateDD = "27";
			amtBlank = "4";
			amtSum = "3000000";
			surtax = "300000";
			remark = "";
			

			int a= 0 ;
			for(int i=12; i>0; i--){
				if( amtSum.length() >= i){
					parameterMap.put("amtSum"+i, amtSum.substring(a, a+1));
					a++;
				}else{
					parameterMap.put("amtSum"+i, "");
				}
			}
			
			int b= 0 ;
			for(int i=11; i>0; i--){
				if( surtax.length() >= i){
					parameterMap.put("surtax"+i, surtax.substring(b, b+1));
					b++;
				}else{
					parameterMap.put("surtax"+i, "");
				}
			}
			
			itemDateMM = "06";
			itemDateDD = "27";
			
			itemName = "솔루션 사업비";
			itemStd = "";
			itemQty = "";
			itemPrice = "";
			itemAmt = "3,000,000";
			itemStax = "300,000";
			itemRemark = "";

			amtTotal = "3,300,000";
			amtCash = "0";
			amtCheck = "0";
			amtBill = "0";
			amtCredit = "0";
			
			recept_name = "영수";
		}
		
		parameterMap.put("issue_id", issue_id);
		parameterMap.put("dem_regnum", dem_regnum);
		parameterMap.put("dem_vendor_name", dem_vendor_name);
		parameterMap.put("dem_owner_name", dem_owner_name);
		parameterMap.put("dem_addr", dem_addr);
		parameterMap.put("dem_biz_type", dem_biz_type);
		parameterMap.put("dem_biz_kind", dem_biz_kind);
		parameterMap.put("sup_regnum", sup_regnum);
		parameterMap.put("sup_vendor_name", sup_vendor_name);
		parameterMap.put("sup_owner_name", sup_owner_name);
		parameterMap.put("sup_addr", sup_addr);
		parameterMap.put("sup_biz_type", sup_biz_type);
		parameterMap.put("sup_biz_kind", sup_biz_kind);
		parameterMap.put("pubDate", pubDate);
		parameterMap.put("pubDateMM", pubDateMM);
		parameterMap.put("pubDateDD", pubDateDD);
		parameterMap.put("amtBlank", amtBlank);
		parameterMap.put("amtSum", amtSum);
		parameterMap.put("surtax", surtax);
		parameterMap.put("remark", remark);
		parameterMap.put("itemDateMM", itemDateMM);
		parameterMap.put("itemDateDD", itemDateDD);
		parameterMap.put("itemName", itemName);
		parameterMap.put("itemStd", itemStd);
		parameterMap.put("itemQty", itemQty);
		parameterMap.put("itemPrice", itemPrice);
		parameterMap.put("itemAmt", itemAmt);
		parameterMap.put("itemStax", itemStax);
		parameterMap.put("itemRemark", itemRemark);
		parameterMap.put("amtTotal", amtTotal);
		parameterMap.put("amtCash",amtCash );
		parameterMap.put("amtCheck", amtCheck);
		parameterMap.put("amtBill", amtBill);
		parameterMap.put("amtCredit", amtCredit);
		parameterMap.put("recept_name", recept_name);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproPopupViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 안내 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : notiPopup
	 * @author : sanghoon_joen
	 * @date : 2018. 12. 31. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/notiPopup")
	public String notiPopup(final HttpServletRequest request, final Model model) {				
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 부서 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : deptList
	 * @author : 
	 * @date : 2020.08.28
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/deptList")
	public String deptList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproCommPopupService.deptInqireList(parameterMap);
		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);  
		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 내부 담당자 조회 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : emgncLoginForm
	 * @author : SungYoon_Ha
	 * @date : 2017. 6. 15. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/chargerList")
	public String chargerList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = oproCommPopupService.chargerListInqireWithPgng(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 내부 담당자 조회 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : vendInfoViewPopup
	 * @author : 맹경열
	 * @date : 2019. 03. 19. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vendInfoViewPopup")
	public String vendInfoViewPopup(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = oproUserVendInfoService.vendInfoDetail(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		
		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 주요취급품목 조회 팝업 
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : mjrHndlItemList
	 * @author : JanDi_Eun
	 * @date : 2019. 3. 4. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mjrHndlItemList")
	public String mjrHndlItemList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap) request.getAttribute(PARAMETER_MAP);
		FwkTransferObject trans = oproCommPopupService.mjrHndlItemListWithPgng(parameterMap);
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);

		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 우편번호 목록 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : zipList
	 * @author : 맹경열
	 * @date : 2020. 02. 27. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/zipList")
	public String zipList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		model.addAllAttributes(trans);
		model.addAllAttributes(parameterMap);
		return parameterMap.getOproPopupViewName(prefixUrl);
	}	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 품목 목록 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : itemList
	 * @author : 
	 * @date : 2020.08.28 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/itemList")
	public String itemList(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		if(parameterMap.get("P_SEARCH_S") != null){
			trans = oproCommPopupService.itemListInqireWithPgng(parameterMap);
		}
		
		model.addAllAttributes(trans);
		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	@RequestMapping(value="/trmPopup")
	public String trmPopup(final HttpServletRequest request, final Model model,  RedirectAttributes redirectAttributes){
		
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproCommPopupService.trmDetail(parameterMap);
		
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans); 
		
		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 도움말
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : helpPopDetail
	 * @author : 손연우
	 * @date : 2021. 3. 22.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/helpPopDetail")
	public String helpPopDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		//FwkTransferObject trans = iproCommPopupService.deptInqireList(parameterMap);
		//model.addAllAttributes(trans);  
		model.addAllAttributes(parameterMap);  
		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원이 평가대상을 평가한 평가표 팝업 - 등록
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmCmtmEstmFrmRegForm
	 * @author : 손연우
	 * @date : 2021. 3. 22. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmCmtmEstmFrmRegForm")
	public String estmCmtmEstmFrmDetestmCmtmEstmFrmRegFormail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproCommPopupService.estmCmtmEstmFrmDetail(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가위원이 평가대상을 평가한 평가표 팝업 - 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmCmtmEstmFrmSave
	 * @author : 손연우
	 * @date : 2021. 3. 22. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmCmtmEstmFrmSave")
	public String estmCmtmEstmFrmSave(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproCommPopupService.estmCmtmEstmFrmSave(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "ESTM");
		parameterMap.put("P_CONN_CNTN", "외부평가위원 평가대상 배점 저장");
		parameterMap.put("P_CONN_URL", "/opro/comm/popup/estmCmtmEstmFrmRegForm.do");
		parameterMap.put("P_MENU_ID", "estmCmtmEstmFrmRegForm");
		
		iproCommDefaultService.sendLOG(parameterMap);
		
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		
		return "forward:/opro/comm/popup/estmCmtmEstmFrmRegForm.do?resultCode=Success";
	}
	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가대상정보 첨부파일 상세 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmObjFileView
	 * @date : 2021. 04. 02.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmObjFileView")
	public String estmObjFileView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= oproCommPopupService.estmObjFileView(parameterMap);

		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getOproPopupViewName(prefixUrl);
	}

		@RequestMapping(value="/estmCmtmMngCrtrDetail")
		public String estmCmtmMngCrtrDetail(final HttpServletRequest request, final Model model){
			FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
			
			FwkTransferObject trans = new FwkTransferObject();
			
			trans = oproCommPopupService.cmtmMngCrtrDetail(parameterMap);
			
			model.addAllAttributes(parameterMap);  
			model.addAllAttributes(trans);
			
			return parameterMap.getOproPopupViewName(prefixUrl);
	}
		
		
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 기업정보
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmObjImstarsMainView
	 * @date : 2021. 05. 21.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmObjImstarsMainView")
	public String estmObjImstarsMainView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= oproCommPopupService.estmObjImstarsMainView(parameterMap);

		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getOproPopupViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 상세내용
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.IproCommPopupController.java
	 * @Method : estmObjImstarsDetailView
	 * @date : 2021. 05. 21.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmObjImstarsDetailView")
	public String estmObjImstarsDetailView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= oproCommPopupService.estmObjImstarsDetailView(parameterMap);

		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getOproPopupViewName(prefixUrl);
	}

	
	/**
	 * 
	 * <pre>
	 * 1.개요 : 평가대상 탭 - 평가대상정보 아임스타즈 연계 상세 팝업 : 첨부파일
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.IproCommPopupController.java
	 * @Method : estmObjImstarsFileView
	 * @date : 2021. 05. 21.
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmObjImstarsFileView")
	public String estmObjImstarsFileView(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = new FwkTransferObject();
		
		trans= oproCommPopupService.estmObjImstarsFileView(parameterMap);

		model.addAllAttributes(parameterMap);  
		model.addAllAttributes(trans);
		
		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가완료현황 - 상세 : 설문조사 팝업
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.ipro.comm.controller.OproCommPopupController.java
	 * @Method : estmCmtmSvyDetail
	 * @date : 2021. 6. 01. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmCmtmSvyDetail")
	public String estmCmtmSvyDetail(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproCommPopupService.estmCmtmSvyDetail(parameterMap);
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		
		return parameterMap.getOproPopupViewName(prefixUrl);
	}
	
	/**
	 * <pre>
	 * 1.개요 : 평가완료현황 - 상세 : 설문조사 저장
	 * 2.처리내용 : 
	 * </pre>
	 * @Location : eGs_epro_sbdc.com.eunwoosoft.opro.comm.controller.OproCommPopupController.java
	 * @Method : estmCmtmSvySave
	 * @date : 2021. 6. 01. 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/estmCmtmSvySave")
	public String estmCmtmSvySave(final HttpServletRequest request, final Model model){
		FwkParameterMap parameterMap = (FwkParameterMap)request.getAttribute(PARAMETER_MAP);
		
		FwkTransferObject trans = oproCommPopupService.estmCmtmSvySave(parameterMap);
		
		/**
		 * P_SYS_CONN_SECD : 접속구분(LOGIN, BID, CONT, INFO, NOTI, MYPG, HELP)
		 * P_CONN_CNTN : 접속내용 (한글 코멘트)
		 * P_CONN_URL : 접속url
		 * P_MENU_ID : 메뉴ID (호출 함수명)
		 */
		parameterMap.put("P_SYS_CONN_SECD", "ESTM");
		parameterMap.put("P_CONN_CNTN", "외부평가위원 설문조사 배점 저장");
		parameterMap.put("P_CONN_URL", "/opro/comm/popup/estmCmtmSvyDetail.do");
		parameterMap.put("P_MENU_ID", "estmCmtmSvyDetail");
		
		iproCommDefaultService.sendLOG(parameterMap);
		
		model.addAllAttributes(parameterMap);
		model.addAllAttributes(trans);
		
		return "forward:/opro/comm/popup/estmCmtmSvyDetail.do?resultCode=Success";
	}
}