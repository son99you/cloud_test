/**
 * 평가관리 > 평가위원관리 > 평가위원평가진행현황 상세 - 기본정보
 *
 * <pre>
 * estm
 *    |_ cmtmMngProgDetail.js
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

	
	// 평가진행현황 목록
	pageObj.cmtmMngProgList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/cmtmMngProgList.do"); 
	};
	
	// 평가수정
	pageObj.cmtmMngProgUpdtForm = function() {
		FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgUpdtForm.do"); 
	};
	
	// 평가삭제
	pageObj.estmDelete = function(){
		
		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/estm/estmDelete";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			pageObj.cmtmMngProgList();
		});
		
	};
	
	
	// 평가진행상태 update
	pageObj.estmPscdUpdt = function(){

		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/estm/estmPscdUpdt";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			pageObj.cmtmMngProgList();
		});
		
	};
	
	// 평가종료
	pageObj.cmtmMngProgEstmEnd = function(){
		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/estm/cmtmMngProgEstmEnd";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			pageObj.cmtmMngProgList();
		});
	};
	
	
	// 평가시작가능여부 조회
	pageObj.estmStPsblYnChck = function(){
		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/estm/estmStPsblYnChck";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			
			var psblYn = res.psblYn;
			
			if(psblYn == "Y"){
				$("#detailFrm input[name='P_ESTM_PSCD']").val("A005");   // 평가시작
				pageObj.estmPscdUpdt();
			}else{
				alert("평가위원선정이 되어있지 않습니다.");
				FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgCmtmDetail.do");
			}
		});
		
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
	
	
	// 평가절차
	pageObj.estmProcdPscdUpdt = function(estmProcdSeq, pscd, estmProcdNm){
		
		$("#detailFrm input[name='P_ESTM_PROCD_SEQ']").val(estmProcdSeq);   // 평가절차순번
		$("#detailFrm input[name='P_ESTM_PROCD_PSCD']").val(pscd);   // 평가절차진행상태코드
		$("#detailFrm input[name='P_ESTM_PROCD_NM']").val(estmProcdNm);   // 평가절차명
		
		
		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/estm/estmProcdPscdUpdt";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
				alert("저장되었습니다.");
			FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgDetail.do");
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
	
	
	/**  
	 * <pre>
	 * 1. 개요 : 파일 다운로드
	 * 2. 처리내용 : 
	 * 		공고게시
	 *  	
	 * </pre>
	 */
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
			pageObj.cmtmMngProgList();
		});
		
		// 수정버튼
		$("#updtBtn").on("click", function() {
			pageObj.cmtmMngProgUpdtForm();
		});
		
		// 진행이력 버튼
		$("#estmHistBtn").on("click", function() {
			pageObj.estmHistListPopup();
		});

		// 삭제 버튼
		$("#delBtn").on("click", function() {
			
			if(!confirm("삭제하시겠습니까?")){
				return false; 
			}
			
			pageObj.estmDelete();
		});
		
		
		// 평가공고 버튼
		$("#estmAnncBtn").on("click", function() {
			
			$("#detailFrm input[name='P_ESTM_PSCD']").val("A002");   // 평가공고
			
			if(!confirm("평가를 공고하시겠습니까?")){
				return false; 
			}
			
			pageObj.estmPscdUpdt();
		});
		
		// 평가시작 버튼
		$("#estmStBtn").on("click", function() {
			
			if(!confirm("평가를 시작하시겠습니까?")){
				return false; 
			}
			
			pageObj.estmStPsblYnChck();
		});
		

		// 평가종료 버튼
		$("#estmEndBtn").on("click", function() {
			
			$("#detailFrm input[name='P_ESTM_PSCD']").val("C001");   // 평가종료
			
			if(!confirm("평가를 종료하시겠습니까?")){
				return false; 
			}
			
			//pageObj.estmPscdUpdt();
			//2021-05-14 은잔디 : 평가종료 시, pool 선정여부 update
			pageObj.cmtmMngProgEstmEnd();
			
		});
		
		// 평가취소 버튼
		$("#estmCnclBtn").on("click", function() {
			
			$("#detailFrm input[name='P_ESTM_PSCD']").val("C002");   // 평가취소
			
			if(!confirm("평가를 취소하시겠습니까?")){
				return false; 
			}
			
			pageObj.estmPscdUpdt();
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
		
		if($("#resultCode").val() == 'Success'){
			var estmNo = $("#P_ESTM_NO_TRANS").val();
			$("#detailFrm input[name='P_ESTM_NO_TRANS']").val(estmNo);
			$("#resultCode").val("");
			FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgDetail.do");
		}
		
	});
})();