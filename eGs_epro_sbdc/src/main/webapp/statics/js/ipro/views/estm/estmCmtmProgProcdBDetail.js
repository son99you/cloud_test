/**
 * 평가관리 > 평가진행현황 상세 - 평가대상
 *
 * <pre>
 * estm
 *    |_ estmProgObjDetail.js
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

	pageObj.estmProgList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/estmProgList.do"); 
	};
	

	/**
	 * 평가위원이 평가대상을 평가한 점수 확인 팝업
	 */
	detailInqirePopup = function() {
		$("#popupFrm").one("submit", function() {
			$("#popupFrm input[name='P_ESTM_OBJ_SEQ']").val(ESTM_OBJ_SEQ);
			$("#popupFrm input[name='P_ESTM_CMTM_NO']").val(ESTM_CMTM_NO);
			$("#popupFrm input[name='P_ESTR_SECD']").val(ESTR_SECD);
			
			window.open("", "estmCmtmEstmFrm", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmCmtmEstmFrmDetail.do';
	        this.method = 'POST';
	        this.target = 'estmCmtmEstmFrm';
	    }).trigger("submit");
	};
	
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.estmProgList(); 
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.estmProgList();
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