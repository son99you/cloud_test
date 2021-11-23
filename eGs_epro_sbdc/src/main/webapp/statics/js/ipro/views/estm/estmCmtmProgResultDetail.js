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
		FwkCmmnUtil.submitForm("listFrm", "/estm/estmCmtmProgList.do"); 
	};
	
	
	pageObj.estmProgResultSearch = function() {
		
		$("input[name='P_SEARCH_CHECK_YN']").val("Y");
		
		FwkCmmnUtil.submitForm("detailFrm", "/estm/estmCmtmProgResultDetail.do"); 
	};
	
	pageObj.clickPage = function(pageNo) { 
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.estmProgResultSearch(); 
	};

	/**
	 * 평가위원이 평가대상을 평가한 점수 확인 팝업
	 */
	detailInqirePopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "estmCmtmEstmFrm", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmCmtmEstmFrmDetail.do';
	        this.method = 'POST';
	        this.target = 'estmCmtmEstmFrm';
	    }).trigger("submit");
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			pageObj.estmProgResultSearch();
		});
		
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
		
		// 상세검색필터
		$(".filter_sch .filter_btn").on("click",function(e){
			e.preventDefault();
			if($(this).parent().is(".view")){
				$("#filter_schDiv").css("display","none");
				$(this).text("상세검색필터 보기");
				$(this).parent().removeClass("view");
			} else {
				$("#filter_schDiv").css("display","");
				$(this).text("상세검색필터 닫기");
				$(this).parent().addClass("view");
			}
		});
		
		
		
		// 최종점수합계적용여부 선택시 비율 input 활성화 
		$("input[name*='P_SEARCH_ITEM_TOTSUMAT']").on("click", function() {
			if(this.checked) {
				$(this).parent().next().css("display","");
				$(this).next().next().next().css("display","");
			}else {
				$(this).parent().next().css("display","none");
				$(this).next().next().next().css("display","none");
				$(this).parent().next().val("");
			}
		});

		
	};

	 
	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		// 엑셀다운로드 공통
		excelDownSetting("detailFrm");
		
		
		if($("input[name='P_SEARCH_CHECK_YN']").val() == "Y" ){
		}else {
			$("input[name='P_SEARCH_ITEM']").prop({'checked':true});
		}
		
	});
})();