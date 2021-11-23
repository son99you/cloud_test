/**
 * 평가관리 > 평가위원관리 > 평가위원평가완료현황 상세 - 평가위원
 *
 * <pre>
 * estm
 *    |_ cmtmMngCmplCmtmDetail.js
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
	
	// 선정차수 상세
	estmCmtmSlctNgrDetail = function(estmCmtmSlctNgr){
		$("#P_ESTM_CMTM_SLCT_NGR_CLICK").val(estmCmtmSlctNgr);
		$("#P_CMPL_YN").val("");
		FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngCmplCmtmDetail.do");
	};
	
	// 평가위원선정최종 상세
	estmCmtmSlctCmplDetail = function(){
		$("#P_CMPL_YN").val("Y");
		FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngCmplCmtmDetail.do");
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