/**
 * 평가관리 > 평가위원관리 > 평가위원평가완료현황 상세 - 평가대상
 *
 * <pre>
 * estm
 *    |_ cmtmMngCmplObjDetail.js
 * 
 * </pre>
 * @date : 2021. 03. 23.
 * @version : 1.0
 * @author : 은우소프트
 */

(function() {
	
	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "detailFrm";

	pageObj.cmtmMngCmplList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/cmtmMngCmplList.do"); 
	};
	
	
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.cmtmMngCmplList(); 
	};
	
	// 평가대상정보 첨부파일 상세 팝업
	pageObj.estmObjFileView = function(estmObjSeq, rsdnNo, bizrno, fileGrpNo){
		$("#popupFrm input[name='P_ESTM_OBJ_SEQ']").val(estmObjSeq);
		$("#popupFrm input[name='P_RSDN_NO']").val(estmObjSeq);
		$("#popupFrm input[name='P_BIZRNO']").val(bizrno);
		$("#popupFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		
		$("#popupFrm input[name='P_UPDT_PSBL_YN']").val("N");   // 수정가능여부(평가완료현황에서는 상세만)

		$("#popupFrm").one("submit", function() {
			window.open("", "deptPopup", "width=750px,height=500px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=600,top=300");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmObjFileView.do';
	        this.method = 'POST';
	        this.target = 'deptPopup';
	    }).trigger("submit");
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.cmtmMngCmplList();
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