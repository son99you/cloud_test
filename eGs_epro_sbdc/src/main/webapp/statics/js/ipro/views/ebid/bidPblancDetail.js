/**
 * 입찰관리 > 입찰공고 상세
 *
 * <pre>
 * ebid
 *    |_ bidPblancDetail.js
 * 
 * </pre>
 * @date : 2017. 06. 19
 * @version : 1.0
 * @author : 은우소프트 전상훈
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "updtFrm";

	refresh = function() {
		pageObj.bidPblancListInqire();
	};
	/**  
     * <pre>
     * 1. 개요 : 입찰공고목록 페이지 이동
     * 2. 처리내용 : 
     * 		입찰공고목록 조회 페이지로 이동 한다.
     *  	
     * </pre>
     * @Function Name : bidPblancListInqire
     * @date : 2015. 02. 10.
     * @author : 은우소프트 하성윤
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 02. 10.       은우소프트 하성윤              최초 작성 
     *  =======================================================   
     */
	pageObj.bidPblancListInqire = function() {
		FwkCmmnUtil.submitForm("menuMoveFrm", "/ebid/bidPblancList.do");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 정정공고 폼 이동
	 * 2. 처리내용 : 
	 * 		정정공고 화면으로 이동
	 *  	
	 * </pre>
	 * @Function Name : updtPblancRegistForm
	 * @date : 2015. 02. 10.
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 02. 10.       은우소프트 하성윤              최초 작성 
	 *  =======================================================   
	 */
	pageObj.updtPblancRegistForm = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/ebid/bidPblancUpdtForm.do");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 취소공고 팝업 화면 이동
	 * 2. 처리내용 : 
	 * 		취소공고 팝업 화면으로 이동
	 *  	
	 * </pre>
	 * @Function Name : canclPblancRegistForm
	 * @date : 2015. 02. 11.
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 02. 11.       은우소프트 하성윤              최초 작성 
	 *  =======================================================   
	 */
	pageObj.canclPblancRegistForm = function() {
		$("#canclpblancPopupFrm").one("submit", function() {
			window.open("", "canclPblancRegistFormPopup", "width=740px, height=450px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=300");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/canclPblancRegistForm.do';
	        this.method = 'POST';
	        this.target = 'canclPblancRegistFormPopup';
	    }).trigger("submit");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 취소공고 submit
	 * 2. 처리내용 : 
	 * 		취소공고 Submit 호출
	 *  	
	 * </pre>
	 * @Function Name : canclPblancRegist
	 * @date : 2015. 02. 11.
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 02. 11.       은우소프트 하성윤              최초 작성 
	 *  =======================================================   
	 */
	canclPblancRegist = function() {
//		FwkCmmnUtil.submitForm("canclPblancRegistFrm", "/ebid/canclPblancRegist.do");
		comAjaxSubmit("canclPblancRegistFrm", "/ebid/canclPblancRegist", "menuMoveFrm", "/ebid/bidPblancList.do");
	};
	
	// 임시 (공고)
	pblancSttu = function(progrsSttusCd, detailPrstCd) {
		$("#sttusFrm input[name='P_BID_PSCD']").val(progrsSttusCd);
		$("#sttusFrm input[name='P_APPR_STCD']").val(detailPrstCd);
		
		var jsonData = $("#sttusFrm").serializeObject();
		var actionUrl = "/ebid/bidSttusChange";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			pageObj.bidPblancListInqire();
		});
	};
	
	
	pageObj.roundUpdateForm = function() {
		$("#roundPopupFrm").one("submit", function() {
			window.open("", "roundPopupFrmPopup", "width=800px, height=450px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=300");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/roundUpdtForm.do';
	        this.method = 'POST';
	        this.target = 'roundPopupFrmPopup';
	    }).trigger("submit");
	};
	
	pageObj.returnList = function() {
		FwkCmmnUtil.submitForm("menuMoveFrm", "/ebid/bidPblancList.do");
	};
	
	pageObj.returnDetail = function() {
		FwkCmmnUtil.submitForm("updtFrm", "/ebid/bidPblancDetail.do");
	};
	
	pageObj.updtAnncSttusChange = function(bidPscd, apprStcd) {
		$("#sttusFrm input[name='P_BID_PSCD']").val(bidPscd);
		$("#sttusFrm input[name='P_APPR_STCD']").val(apprStcd);
		
		var jsonData = $("#sttusFrm").serializeObject();
		var actionUrl = "/ebid/updtAnncSttusChange";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			pageObj.bidPblancListInqire();
		});
	};
	
	pageObj.updtAnncDelete = function() {
		
		var jsonData = $("#delFrm").serializeObject();
		var actionUrl = "/ebid/updtAnncDelete";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			pageObj.bidPblancListInqire();
		});
	};
	
	pageObj.vendTndrListPopup = function() {
		$("#vendPopupFrm").one("submit", function() {
			window.open("", "vendTndrListPopup", "width=740px, height=450px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=300");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/vendTndrList.do';
	        this.method = 'POST';
	        this.target = 'vendTndrListPopup';
	    }).trigger("submit");
	};
	
	pageObj.bidNotiRegistPopup = function(sn) {
		$("#bidNotiRegistPopupFrm input[name='P_OPNN_SN']").val(sn);
		
		$("#bidNotiRegistPopupFrm").one("submit", function() {
			window.open("", "bidNotiRegistPopup", "width=740px, height=450px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=300");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/bidNotiRegistForm.do';
	        this.method = 'POST';
	        this.target = 'bidNotiRegistPopup';
	    }).trigger("submit");
	};
	
	pageObj.ebidApprSendCrrcRgst = function() {
		var jsonData = $("#apprCrrcFrm").serializeObject();
		var actionUrl = "/ebid/ebidApprSendCrrc";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			
			$("#apprCrrcFrm input[name='P_INTERFACE_ID']").val(res.P_INTERFACE_ID);
			$("#apprCrrcFrm input[name='P_STATUS']").val(res.P_STATUS);
			
			$("#apprCrrcFrm").one("submit", function() {
				window.open("", "applPopup", "width=740px,height=700px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=150");
				this.action = FwkCmmnUtil['contextPath']+'/comm/popup/apprSendPopup.do';
				this.method = 'POST';
				this.target = 'applPopup';
			}).trigger("submit");
			
//			FwkCmmnUtil.submitForm("updtFrm", "/ebid/bidPblancDetail.do" );			
		});		
	};
	
	pageObj.ebidApprSendCrrc = function(){
		$("#apprCrrcFrm").one("submit", function() {
			window.open("", "applPopup", "width=740px,height=700px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=150");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/apprSendPopup.do';
			this.method = 'POST';
			this.target = 'applPopup';
		}).trigger("submit");
	};
	
	pageObj.ebidApprSendCnclRgst = function() {
		var jsonData = $("#apprCnclFrm").serializeObject();
		var actionUrl = "/ebid/ebidApprSendCncl";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			
			$("#apprCnclFrm input[name='P_INTERFACE_ID']").val(res.P_INTERFACE_ID);
			$("#apprCnclFrm input[name='P_STATUS']").val(res.P_STATUS);
			
			$("#apprCnclFrm").one("submit", function() {
				window.open("", "applPopup", "width=740px,height=700px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=150");
				this.action = FwkCmmnUtil['contextPath']+'/comm/popup/apprSendPopup.do';
				this.method = 'POST';
				this.target = 'applPopup';
			}).trigger("submit");
			
//			FwkCmmnUtil.submitForm("updtFrm", "/ebid/bidPblancDetail.do" );			
		});		
	};
	
	pageObj.ebidApprSendCncl = function(){
		$("#apprCnclFrm").one("submit", function() {
			window.open("", "applPopup", "width=740px,height=700px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=150");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/apprSendPopup.do';
			this.method = 'POST';
			this.target = 'applPopup';
		}).trigger("submit");
	};
	
	pageObj.canclPblancDel = function(){
		var jsonData = $("#sttusFrm").serializeObject();
		var actionUrl = "/ebid/canclPblancDel";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			FwkCmmnUtil.submitForm("updtFrm", "/ebid/bidPblancDetail.do" );	
		});
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 입찰계획수정 페이지 이동
	 * 2. 처리내용 : 
	 * 		입찰계획 수정 페이지로 이동한다.
	 *  	
	 * </pre>
	 * @Function Name : bidPlanUpdt
	 * @date : 2015. 01. 30.
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 01. 30.       은우소프트 하성윤              최초 작성 
	 *  =======================================================   
	 */
	pageObj.bidPlanUpdtForm = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/ebid/bidPlanUpdtForm.do");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 입찰공고 삭제 submit
	 * 2. 처리내용 : 
	 * 		입찰공고를 삭제하는 Submit 호출
	 *  	
	 * </pre>
	 * @Function Name : bidPlanUpdt
	 * @date : 2015. 01. 30.
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 01. 30.       은우소프트 하성윤              최초 작성 
	 *  =======================================================   
	 */
	pageObj.bidPlanDelete = function() {
		comAjaxSubmit(defaultFrm, "/ebid/bidPlanDelete", "menuMoveFrm", "/ebid/bidPblancList.do");
	};
	
	/**  
     * <pre>
     * 1. 개요 : 입찰공고문 상세 팝업 화면 submit
     * 2. 처리내용 : 
     * 		입찰공고문 상세 조회 Form 을 Sumit 한다.
     * </pre>
     * @Function Name : bidWrtancDetailPopup
     * @date : 2020. 05. 11.
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2020. 05. 11.       은우소프트 맹경열              최초 작성 
     *  =======================================================   
     */
	pageObj.bidWrtancDetailPopup = function() {
		$("#bidWrtancDetailPopupFrm").one("submit", function() {
			window.open("", "bidWrtancDetailPopup", "width=1060px, height=780px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/bidWrtancDetail.do';
	        this.method = 'POST';
	        this.target = 'bidWrtancDetailPopup';
	    }).trigger("submit");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 사전공고문 상세 팝업 화면 submit
	 * 2. 처리내용 : 
	 * 		사전공고문 상세 조회 Form 을 Sumit 한다.
	 * </pre>
	 * @Function Name : bereWrtancDetailPopup
	 * @date : 2020. 05. 11.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2020. 05. 11.       은우소프트 맹경열              최초 작성 
	 *  =======================================================   
	 */
	pageObj.bereWrtancDetailPopup = function() {
		$("#bidWrtancDetailPopupFrm").one("submit", function() {
			window.open("", "bidWrtancDetailPopup", "width=1060px, height=780px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/bereNotiMngePopupDetail.do';
			this.method = 'POST';
			this.target = 'bidWrtancDetailPopup';
		}).trigger("submit");
	};	

	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 입찰공고목록조회 함수를 호출한다.
	 * 2. 입찰종류(#select2) 콤보박스의 change 이벤트를 biding한다.
	 *   2.1 change 이벤트 발생시 changeSelect2 함수를 호출한다.
	 * 3. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.bidPblancListInqire();
		});
		
		// 정정공고 버튼
		$("#updtPblancBtn").on("click", function() {
			$("#updtFrm input[name='P_APPR_YN']").val("N");
			pageObj.updtPblancRegistForm();
		});
		
		$("#updtPblancApprBtn").on("click", function() {
			$("#updtFrm input[name='P_APPR_YN']").val("Y");
			pageObj.updtPblancRegistForm();
		});
		
		// 취소공고 버튼
		$("#canclPblancBtn").on("click", function() {
			pageObj.canclPblancRegistForm();
		});
		
		// 입찰계획 수정 버튼
		$("#updateBtn").on("click", function() {
			pageObj.bidPlanUpdtForm();
		});
		
		// 입찰계획 삭제 버튼
		$("#deleteBtn").on("click", function() {
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.003", "입찰계획", "삭제"))){
				return false;
			}
			pageObj.bidPlanDelete();
		});		
		
		// 공고게시 버튼
		$("#ntfyBtn").on("click", function(){
			pageObj.roundUpdateForm();
		});
		
		$("#updtPblancDelBtn").on("click", function(){
			pageObj.updtAnncDelete();
		});
		
		$("#vendBtn").on("click", function(){
			pageObj.vendTndrListPopup();
		});
		
		$("#notiBtn").on("click", function(){
			pageObj.bidNotiRegistPopup();
		});
		
		$("#apprCrrcBtn").on("click", function(){
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.002", "정정공고", "결재"))){
				return false;
			}
			pageObj.ebidApprSendCrrcRgst();
		});
		
		$("#apprCrrcViewBtn").on("click", function(){
			pageObj.ebidApprSendCrrc();
		});
		
		$("#apprCnclBtn").on("click", function(){
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.002", "공고취소", "결재"))){
				return false;
			}
			pageObj.ebidApprSendCnclRgst();
		});
		
		$("#apprCnclViewBtn").on("click", function(){
			pageObj.ebidApprSendCncl();
		});

		$("#canclPblancDelBtn").on("click", function(){
			pageObj.canclPblancDel();
		});
		
		//현장설명회 이동
		$("#bidBrfsDetailBtn").on("click", function(){
			$("#updtFrm input[name='resourceName']").val("ipm303");
			FwkCmmnUtil.submitForm("updtFrm", "/ebid/bidDcPeoDetail.do");
		});
		//입찰참가검토 이동
		$("#bidPartcptDetailBtn").on("click", function(){
			$("#updtFrm input[name='resourceName']").val("ipm304");
			FwkCmmnUtil.submitForm("updtFrm", "/ebid/bidPartcptSttusDetail.do");
		});
		//제안/규격서검토 이동
		$("#bidPrprcDetailBtn").on("click", function(){
			$("#updtFrm input[name='resourceName']").val("ipm305");
			FwkCmmnUtil.submitForm("updtFrm", "/ebid/prprcManageDetail.do");
		});
		//개찰대기현황 이동
		$("#bidOpengDetailBtn").on("click", function(){
			$("#updtFrm input[name='resourceName']").val("ipm306");
			FwkCmmnUtil.submitForm("updtFrm", "/ebid/opengManageDetail.do");
		});
		
		// 입찰공고문 상세 버튼
		$("#detailBtn").on("click", function() {
			pageObj.bidWrtancDetailPopup();
		});
		
		// 사전공고문 상세 버튼
		$("#detailBereBtn").on("click", function() {
			pageObj.bereWrtancDetailPopup();
		});		
		
	};

	/**
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
		
		fileView($("#P_FILE_GRP_NO").val());
	});
})();