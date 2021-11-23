/**
 * 평가관리 > 평가진행현황 상세 - 기본정보
 *
 * <pre>
 * estm
 *    |_ estmCmtmCmplDetail.js
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

	pageObj.estmCmtmCmplList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/opro/estm/estmCmtmCmplList.do"); 
	};
	
	pageObj.download = function(fileGrpNo, fileSn){
		$("#downFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		$("#downFrm input[name='P_FILE_SN']").val(fileSn);
		FwkCmmnUtil.submitForm("downFrm", "/comm/download.do");
	};
	
	pageObj.estmCmtmSvyDetail = function() {
		
		$("#popupFrm").one("submit", function() {
			window.open("", "estmCmtmEstmFrm", "width=1024px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/opro/comm/popup/estmCmtmSvyDetail.do';
	        this.method = 'POST';
	        this.target = 'estmCmtmEstmFrm';
	    }).trigger("submit");
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.estmCmtmCmplList();
		});
		
		// 설문조사
		$("#svyBtn").on("click", function() {
			pageObj.estmCmtmSvyDetail();
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
		
		fileView($("#P_FILE_GRP_NO").val());
	});
})();