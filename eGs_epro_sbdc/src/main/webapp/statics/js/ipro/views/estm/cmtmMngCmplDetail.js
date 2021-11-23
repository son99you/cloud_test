/**
 * 평가관리 > 평가위원관리 > 평가위원평가완료현황 상세 - 기본정보
 *
 * <pre>
 * estm
 *    |_ cmtmMngCmplDetail.js
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
	
	//평가종료취소 (2)
	pageObj.estmProgDetail = function(){
		$("#detailFrm input[name ='P_ESTM_PSCD']").val("A005");
		
		if(!confirm ("평가종료를 취소하겠습니다.")){
			return false;
		}
		pageObj.cmtmCmplEndCnclUpdt();
	};
	
	pageObj.cmtmCmplEndCnclUpdt = function(){
		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/estm/cmtmCmplEndCnclUpdt";
		
		FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(res){
			pageObj.cmtmMngCmplList();
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
			pageObj.cmtmMngCmplList();
		});
		
		//평가종료취소
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