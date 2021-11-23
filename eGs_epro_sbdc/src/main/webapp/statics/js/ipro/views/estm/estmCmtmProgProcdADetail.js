/**
 * 평가관리 > 평가진행현황_평가위원 상세 - 서류평가, 품평회
 *
 * <pre>
 * estm
 *    |_ estmCmtmProgProcdADetail.js
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

	pageObj.estmCmtmProgList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/estmCmtmProgList.do"); 
	};
	
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		reloadDetail(); 
	};
	
	// 평가점수 저장 후 닫기 클릭 시 화면 호출
	reloadDetail = function(){
		FwkCmmnUtil.submitForm("detailFrm", "/estm/estmCmtmProgProcdADetail.do"); 
	};
	

	/**
	 * 평가위원이 평가대상을 평가한 점수 확인 팝업
	 */
	detailInqirePopup = function(ESTM_OBJ_SEQ,ESTM_CMTM_NO,ESTR_SECD) {
		$("#popupFrm input[name='P_ESTM_OBJ_SEQ']").val(ESTM_OBJ_SEQ);
		$("#popupFrm input[name='P_ESTM_CMTM_NO']").val(ESTM_CMTM_NO);
		$("#popupFrm input[name='P_ESTR_SECD']").val(ESTR_SECD);
		$("#popupFrm input[name='reloadURL']").val("/estm/estmCmtmProgProcdADetail.do");
		
		$("#popupFrm").one("submit", function() {
			window.open("", "estmCmtmEstmFrm", "width=1024px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmCmtmEstmFrmDetail.do';
	        this.method = 'POST';
	        this.target = 'estmCmtmEstmFrm';
	    }).trigger("submit");
	};
	
	pageObj.estmObjFileView = function(estmObjSeq, rsdnNo, bizrno, fileGrpNo){
		var P_ESTM_PSCD = $("#registFrm input[name='P_ESTM_PSCD']").val();
		
		$("#popupFrm input[name='P_ESTM_OBJ_SEQ']").val(estmObjSeq);
		$("#popupFrm input[name='P_RSDN_NO']").val(estmObjSeq);
		$("#popupFrm input[name='P_BIZRNO']").val(bizrno);
		$("#popupFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
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
			pageObj.estmCmtmProgList();
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