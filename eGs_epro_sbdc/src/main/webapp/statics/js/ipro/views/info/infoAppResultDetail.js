(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "detailFrm";
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	pageObj.contCsultList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/cont/contCsultList.do");
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
		//결재문서 정보 내용 채우기
		//FwkCmmnUtil.submitForm("detailFrm", "");
	};
	
	/**  
     * <pre>
     * 1. 개요 : iframe 내 요소 설정
     * 2. 처리내용 : 
     * 		- iframe 내 요소 설정
     * </pre>
     * @Function Name : iframeElementSet
     * @date : 2018. 03. 22.
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2018. 03. 22.       은우소프트 맹경열              최초 작성 
     *  =======================================================   
     */		
	iframeElementSet = function() {
		var $iframe = $('#doc_frame').contents();
		var $h3 = $iframe.find('h3');
		$.each($h3, function(idx, item) {
			$(item).remove();
		});
		var $button = $iframe.find('button');
		$.each($button, function(idx, item) {
			$(item).remove();
		});
		var $tab_div = $iframe.find('.tab_div');
		$.each($tab_div, function(idx, item) {
			$(item).remove();
		});		
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
	opinionInsertPopup = function(aprp_sn) {
		$('#popupFrm input[name=P_APRP_SN_S]').val(aprp_sn);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "rsnPopup", "width=740px,height=320px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=220");
			this.action = FwkCmmnUtil['contextPath']+'/info/popup/infoApprRsnForm.do';
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
	apprTglStatUpdate = function(aprp_sn, stat) {
		$('#statFrm input[name=P_APRP_SN]').val(aprp_sn);
		$('#statFrm input[name=P_APPR_STAT]').val(stat);
		
		var jsonData = $("#statFrm").serializeObject();
		var actionUrl = "/info/infoApprTglUpdate";
		
		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
					FwkCmmnUtil.submitForm("docuFrm", "/info/infoAppConsultDetail.do");
				}
		);	
	};
	
	pageObj.listInqire = function() {
		FwkCmmnUtil.submitForm("listFrm", "/info/infoAppResultList.do");
	};
	pageObj.setEventHandler = function() {
		
		$("#listBtn").on("click", function() {
			//movePage("/info/infoAppResultList.do");
			pageObj.listInqire();
		});
		
		$("#plusBtn").on("click", function() {
			pageObj.plusEvent();
		});
		
		$("#deleteBtn").on("click", function() {
			pageObj.deleteEvent();
		});
		
		
		$('#doc_frame').on('change', function() {
			iframeElementSet();
		});
	};

	/**
	 * window load
	 *
	 */
	$(function() {
		pageObj.setEventHandler();
		pageObj.infoApprDocuDetail();
	});
	
})();