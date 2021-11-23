/**
 * 평가관리 > 평가완료현황 상세 - 평가위원
 *
 * <pre>
 * estm
 *    |_ estmCmplCmtmDetail.js
 * 
 * </pre>
 * @date : 2021. 03. 22.
 * @version : 1.0
 * @author : 은우소프트 
 */

(function() {
	
	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "detailFrm";
	

	// 목록
	pageObj.estmCmplList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/estmCmplList.do"); 
	};
	
	
	// 선정차수 상세
	estmCmtmSlctNgrDetail = function(estmCmtmSlctNgr){
		$("#P_ESTM_CMTM_SLCT_NGR_CLICK").val(estmCmtmSlctNgr);
		$("#P_CMPL_YN").val("");
		FwkCmmnUtil.submitForm("detailFrm", "/estm/estmCmplCmtmDetail.do");
	};
	
	// 평가위원선정최종 상세
	estmCmtmSlctCmplDetail = function(){
		$("#P_CMPL_YN").val("Y");
		FwkCmmnUtil.submitForm("detailFrm", "/estm/estmCmplCmtmDetail.do");
	};
	
	// 2021-05-06 추가 : 평가위원첨부 상세 팝업
	pageObj.estmCmtmFileView = function(estmCmtmNo, fileGrpNo){
		
		$("#popupFrm input[name='P_ESTM_CMTM_NO']").val(estmCmtmNo);
		$("#popupFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "deptPopup", "width=750px,height=400px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=600,top=300");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmCmtmFileView.do';
	        this.method = 'POST';
	        this.target = 'deptPopup';
	    }).trigger("submit");
	};

	pageObj.estmCmtmScrUpdtFrm = function(estmNo, estmCmtmNo) { 
		$("#popupFrm input[name='P_ESTM_NO']").val(estmNo);
		$("#popupFrm input[name='P_ESTM_CMTM_NO']").val(estmCmtmNo);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "cmtmScrUpdtFrm", "width=1024px,height=400px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmCmtmScrUpdtFrm.do';
	        this.method = 'POST';
	        this.target = 'cmtmScrUpdtFrm';
	    }).trigger("submit");
		
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.estmCmplList();
		});
		
		
		$('.list-tab li').click(function(){
            var $tabLineIdx = $(this).index();
            $(this).addClass('is-selected');
            $(this).siblings().removeClass('is-selected');
            $('.tab-contents-in').eq($tabLineIdx).show();
            $('.tab-contents-in').eq($tabLineIdx).siblings().hide();
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