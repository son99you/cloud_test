/**
 * 평가관리 > 평가완료현황 상세 - 기본정보
 *
 * <pre>
 * estm
 *    |_ estmCmplDetail.js
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

	pageObj.estmCmplList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/estmCmplList.do"); 
	};

	// 평가종료취소 (2)
	pageObj.estmProgDetail = function(){
		$("#detailFrm input[name='P_ESTM_PSCD']").val("A005");   // 평가시작
		
		if(!confirm("평가종료를 취소하겠습니다.")){
			return false; 
		}
	
		pageObj.estmCmplEndCnclUpdt();
	
	};
	
	// 평가종료취소 (3)
	pageObj.estmCmplEndCnclUpdt = function(){

		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/estm/estmCmplEndCnclUpdt";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			pageObj.estmCmplList();
		});
		
	};
	
	
	
	
	
	//평가표보기
	pageObj.frmViewPopup = function(ESTM_PROCD_SEQ, ESTM_SECD){
		$("#popupFrm input[name='P_ESTM_PROCD_SEQ']").val(ESTM_PROCD_SEQ);   // 평가절차진행상태코드
		$("#popupFrm input[name='P_ESTM_SECD']").val(ESTM_SECD);   // 평가구분코드
		
		$("#popupFrm").one("submit", function() {
			window.open("", "estmFrmViewPopup", "width=1024px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmFrmDetail.do';
	        this.method = 'POST';
	        this.target = 'estmFrmViewPopup';
	    }).trigger("submit");
	};
	
	// 평가진행이력 조회 팝업
	pageObj.estmHistListPopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "estmHistListPopup", "width=850px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/estmHistList.do';
	        this.method = 'POST';
	        this.target = 'estmHistListPopup';
	    }).trigger("submit");
	};
	
	download = function(fileGrpNo, fileSn) {
		$("#downloadFrm input[name='P_FILE_SN']").val(fileSn);
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do" );
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
		
		// 진행이력 버튼
		$("#estmHistBtn").on("click", function() {
			pageObj.estmHistListPopup();
		});
		
		//평가종료취소 버튼 (1)
		$("#estmEndCnclBtn").on("click", function(){
			pageObj.estmProgDetail();
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