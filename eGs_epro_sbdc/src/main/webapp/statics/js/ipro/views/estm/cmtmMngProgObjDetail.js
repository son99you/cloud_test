/**
 * 평가관리 > 평가위원관리 > 평가위원평가진행현황 상세 - 평가대상
 *
 * <pre>
 * estm
 *    |_ cmtmMngProgObjDetail.js
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

	var defaultFrm = "registFrm";

	pageObj.cmtmMngProgList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/cmtmMngProgList.do"); 
	};
	
	
	pageObj.estmProgObjRegist = function(){
		
		var jsonData = $("#registFrm").serializeObject();
		var actionUrl = "/estm/estmProgObjRegist";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
				alert("저장되었습니다.");
			FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgObjDetail.do");
		});
		
	};
	
	
	// 평가대상불러오기
	pageObj.estmCntcObj = function(){
		
		var jsonData = $("#registFrm").serializeObject();
		var actionUrl = "/estm/estmCmtmCntcObjList";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
				alert("완료되었습니다.");
			FwkCmmnUtil.submitForm("detailFrm", "/estm/cmtmMngProgObjDetail.do");
			
			/*actionUrl = "/estm/estmCmtmCntcObjFileDown";
			
			FwkCmmnUtil.submitAjax (actionUrl, jsonData
					, function(res) {
				
				
			});*/
			
		});
		
	};
	
	// 평가대상정보 첨부파일 상세 팝업
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
	 * <pre>
	 * 1. 개요 : 평가대상정보 삭제
	 * 2. 처리내용 : 
	 */		
	objDelCheck = function(obj){
		var that = obj;
		if(that.checked){
	    	  $(that).closest('tr').find('input[name=P_DEL_AT]').val("Y");
	      }else{
	    	  $(that).closest('tr').find('input[name=P_DEL_AT]').val("N");
	      }
	 };
	
	// 대상삭제
	objRowDel = function(obj){
		$(obj).parent().parent().remove();
	};
	
	// 대상추가
	pageObj.obj_4_add = function(){
		var row = $("#obj_4_HidTbdy").children().clone();
		$("#obj_4_ShowTbdy").append(row);
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
		
		// 평가대상불러오기
		$("#estmCntcObjBtn").on("click", function() {
			
			if(!confirm("평가대상이 존재시 초기화 될 수 있습니다.\n평가대상을 불러오시겠습니까?")){
				return false;
			}
			
			pageObj.estmCntcObj();
		});
		
		// 저장
		$("#saveBtn").on("click", function() {
			
			if(!confirm("저장하시겠습니까?")){
				return false;
			}
			
			$("#obj_2_HidTbdy").remove();
			$("#obj_3_HidTbdy").remove();
			$("#obj_4_HidTbdy").remove();
			
			pageObj.estmProgObjRegist();
			
		});
		
		// 대상추가 버튼
		$("#obj_4_addBtn").on("click", function() {
			pageObj.obj_4_add();
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