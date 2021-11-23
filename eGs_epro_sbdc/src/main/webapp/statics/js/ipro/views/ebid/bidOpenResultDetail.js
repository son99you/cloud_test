/**
 * 계약설계 > 입찰공고요청 등록
 *
 * <pre>
 * prpo 
 *    |_ bidReqDetail.js
 * 
 * </pre>
 * @date : 2020. 08. 25
 * @version : 1.0
 * @author : 은우소프트 joo
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "detailFrm";

	/**
	 * 1.개요 : 탭클릭이벤트 적용
	 * 2.처리내용 : 각 탭 클릭시 이동.
	 * 3. 처리일자 : 2018-11-08
	 * @param no
	 * @param obj
	 */
	tabEvent = function(no){
		
//		$(".tab_wrap01 > .on").attr('class','tapBtn');
//		$(".det_tabs > .on").attr('class','tapBtn');
		$('#'+no).attr("class","on");
		if(no == "1"){  	//공고상세
			FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidPblancResultDetail.do");
		}else if(no == "2"){	//기술평가TCHN_ESTM
//			FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidPartcptSttusDetail.do"); 
			FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidTchnEstmResultDetail.do"); 
		}else if(no == "3"){	//개찰OPNG
		//	FwkCmmnUtil.submitForm("detailFrm", "/ebid/opengManageDetail.do");
			FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidOpenResultDetail.do");
		}else if(no == "4"){	//기술협상TCHN_NEGO
			FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidTchnNegoResultDetail.do");
		}else if(no == "5"){	//낙찰SBID
			FwkCmmnUtil.submitForm("detailFrm", "/ebid/bidSbidResultDetail.do");
		}
		
	};
	
	
	// 목록
	pageObj.bidPblancResultList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/ebid/bidPblancResultList.do");
	};
	
	
	// 진행이력 팝업
	pageObj.bidReqProgHistPopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "bidReqProgPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/bidReqProgPopup.do';
	        this.method = 'POST';
	        this.target = 'bidReqProgPopup';
	    }).trigger("submit"); 
	};
	
	
	download = function(grpNo, sn) {
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(grpNo);
		$("#downloadFrm input[name='P_FILE_SN']").val(sn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
	};
	
	//기술평가팝업
	tchnEstmPopup = function (vendRegNo, bidSbmtFscd, gbn) {
		$("#tchnEstmFrm input[name='P_VEND_REG_NO']").val(vendRegNo);
		$("#tchnEstmFrm input[name='P_BID_SBMT_FSCD']").val(bidSbmtFscd);
		$("#tchnEstmFrm input[name='P_GBN']").val(gbn);
		// 팝업처리
		$("#tchnEstmFrm").one("submit", function() {
			window.open("", "tchnEstmFrmPopup", "width=740px, height=830px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=50");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/tchnEstmPopup.do';
	        this.method = 'POST';
	        this.target = 'tchnEstmFrmPopup'; 
	    }).trigger("submit");
	};
	
	
	filePopup = function (vendRegNo, bidSbmtFscd,gbn) {
		$("#tchnEstmFrm input[name='P_VEND_REG_NO']").val(vendRegNo);
		$("#tchnEstmFrm input[name='P_BID_SBMT_FSCD']").val(bidSbmtFscd);
		$("#tchnEstmFrm input[name='P_GBN']").val(gbn);
		// 팝업처리 
		$("#tchnEstmFrm").one("submit", function() {
			window.open("", "tchnEstmFrmPopup", "width=740px, height=400px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/tchnFilePopup.do';
	        this.method = 'POST';
	        this.target = 'tchnEstmFrmPopup';  
	    }).trigger("submit");
	};
	/**
	 * 산출내역서 등록
	 * @param vendRegNo
	 * @param bidSbmtFscd
	 */
	clcCntnFilePopup = function (vendRegNo, bidSbmtFscd, gbn) {
		$("#tchnEstmFrm input[name='P_VEND_REG_NO']").val(vendRegNo);
		$("#tchnEstmFrm input[name='P_BID_SBMT_FSCD']").val(bidSbmtFscd);
		$("#tchnEstmFrm input[name='P_GBN']").val(gbn);
		// 팝업처리 
		$("#tchnEstmFrm").one("submit", function() {
			window.open("", "tchnEstmFrmPopup", "width=740px, height=800px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/clcCntnFilePopup.do';
	        this.method = 'POST';
	        this.target = 'tchnEstmFrmPopup'; 
	    }).trigger("submit");
	};
	 
	//33	낙찰방법코드	규격가격분리동시경쟁
	//34	낙찰방법코드	2단계경쟁
	//40	낙찰방법코드	협상에 의한 계약
	//일 경우에만 기술평가, 기술협상 가능
	pageObj.sbidMtcdEvent = function (obj) {
		if($(obj).val() == "20" || $(obj).val() == "31"){// 제한적최저가 또는 적격심사일 경우
			$("#2").attr("href","javascript:;");
			$("#2").attr("class","disa");
			$("#4").attr("href","javascript:;");
			$("#4").attr("class","disa");
		}else if($(obj).val() == '40'){	//협상에 의한 계약
			$("#2").attr("href","javascript:tabEvent(2);");
			$("#2").attr("class",""); 
			$("#4").attr("href","javascript:tabEvent(4);");
			$("#4").attr("class",""); 
		}else if($(obj).val() == '34'|| $(obj).val() == '33'){	//2단계 경쟁 or 규격가격분리동시

			$("#2").attr("href","javascript:tabEvent(2);");
			$("#2").attr("class",""); 
			$("#4").attr("href","javascript:tabEvent(4);");
			$("#4").attr("class",""); 
			
		}else{// 그 외
			$("#2").attr("href","javascript:;");
			$("#2").attr("class","disa");
			$("#4").attr("href","javascript:;");
			$("#4").attr("class","disa");
		}
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록이동버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 *   - 목록이동버튼에 click 이벤트 발생시 사전공고현황 목록조회 함수를 호출한다.
	 * 2. 저장버튼(#saveBtn) 의 click 이벤트를 binding 한다.
	 *   - 저장버튼에 click 이벤트 발생시 사전공고현황 등록 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {
		// 진행이력버튼
		$("#progHistBtn").on("click", function() {
			pageObj.bidReqProgHistPopup();
		});
		
		//목록
		$("#listBtn").on("click", function() {
			pageObj.bidPblancResultList();
		});
		
	};
	
	/**
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
		
		pageObj.sbidMtcdEvent($("#sbidMtcd"));
	});
})();