(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "detailFrm";
	
	 pageReload = function() {
		 FwkCmmnUtil.submitForm("detailFrm", "/info/infoAppConsultDetail.do");  
	 };
	 
	pageObj.infoAppConsultList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/info/infoAppConsultList.do");
	};
	
	var cnt = 1;
	
	pageObj.plusEvent = function () {
		var copyRow = $("#apprList #apprHiddTbdy").children().clone(true);
		
		copyRow.find("input[name='name']").attr("id", "name"+cnt);
		copyRow.find("input[name='dept']").attr("id", "dept"+cnt);
		cnt++;
		copyRow.css({"display" : ""});
		
		$("#apprShowTbdy").append(copyRow);
		
	};
	
	pageObj.deleteEvent = function () {
		$("#apprShowTbdy input[name='apprCbx']").each(function(){
			if(this.checked){
				$(this).parent().parent().remove();
			}
		});
	};
	
	/**  
     * <pre>
     * 1. 개요 : 결재대상 페이지 상세 불러오기
     * 2. 처리내용 : 
     * 		- 결재대상 페이지 상세 불러오기
     * </pre>
     * @Function Name : infoApprDocuDetail
     * @date : 2018. 03. 21.
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2018. 03. 21.       은우소프트 맹경열              최초 작성 
     *  =======================================================   
     */	
	pageObj.infoApprDocuDetail = function() {
		FwkCmmnUtil.submitForm("detailFrm", "/comm/popup/bidWrtancDetail.iframe");
	};
	

	/**  
     * <pre>
     * 1. 개요 : 의견작성팝업
     * 2. 처리내용 : 
     * 		- 의견작성팝업
     * </pre>
     * @Function Name : opinionInsertPopup
     * @date : 2018. 03. 26.
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2018. 03. 26.       은우소프트 맹경열              최초 작성 
     *  =======================================================   
     */			
	returnPopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "rsnPopup", "width=740px,height=500px,toolbar=no,status=no,scrollbars=no,resizeable=no,menubar=no,left=500,top=220");
			this.action = FwkCmmnUtil['contextPath']+'/info/popup/apprRtnRsnForm.do';
	        this.method = 'POST';
	        this.target = 'rsnPopup';
	    }).trigger("submit");		
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 결재자 승인처리
	 * 2. 처리내용 : 
	 * 		- 의견작성팝업
	 * </pre>
	 * @Function Name : apprTglStatUpdate
	 * @date : 2018. 03. 26.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2018. 03. 26.       은우소프트 맹경열              최초 작성 
	 *  =======================================================   
	 */			
	apprTglStatUpdate = function(apprYn) {
		$("#apprFrm #P_APPR_YN").val(apprYn);
		var jsonData = $("#apprFrm").serializeObject();
		var actionUrl = "/info/infoApprTglUpdate";
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonData, function(res) {
			pageObj.infoAppConsultList();
		});
	};
	
	pageObj.setEventHandler = function() {
		
		// 목록
		$("#listBtn").on("click", function() {
			pageObj.infoAppConsultList();
		});
		
		// 승인
		$("#apprBtn").on("click", function() {
			if(!confirm("승인하시겠습니까?")){
				return false;
			}
			apprTglStatUpdate("Y");
		});
		
		// 반려
		$("#rtrnBtn").on("click", function() {
//			returnPopup();
			if(!confirm("반려하시겠습니까?")){
				return false;
			}
			apprTglStatUpdate("R");
		});

	};

	/**
	 * window load
	 *
	 */
	$(function() {
		pageObj.setEventHandler();
	});
	
})();