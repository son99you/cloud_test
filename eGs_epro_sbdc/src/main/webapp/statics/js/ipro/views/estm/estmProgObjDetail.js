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

	var defaultFrm = "registFrm";

	pageObj.estmProgList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/estmProgList.do"); 
	};
	
	
	// 저장
	pageObj.estmProgObjRegist = function(){
		
		var jsonData = $("#registFrm").serializeObject();
		var actionUrl = "/estm/estmProgObjRegist.do";
		
		FwkCmmnUtil.submitAjaxNoLoading (actionUrl, jsonData
				, function(res) {
				alert("저장되었습니다.");
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmProgObjDetail.do");
		});
		
	};
	
	// 삭제
	pageObj.estmProgObjDelete = function(){
		
		var jsonData = $("#registFrm").serializeObject();
		var actionUrl = "/estm/estmProgObjDelete";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
				alert("처리되었습니다.");
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmProgObjDetail.do");
		});
		
	};
	
	// 평가대상불러오기
	pageObj.estmCntcObj = function(){
		
		var jsonData = $("#registFrm").serializeObject();
		var actionUrl = "/estm/estmCntcObjList";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
				alert("처리되었습니다.");
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmProgObjDetail.do");
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
	
	// 대상추가
	pageObj.obj_1_add = function(){
		var row = $("#obj_1_HidTbdy").children().clone();
		$("#obj_1_ShowTbdy").append(row);
	};
	
	// 대상삭제
	pageObj.obj_1_del = function(){
		$("#obj_1_ShowTbdy input[name='objCbk']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().parent().remove();
			}
		});
	};
	
	// 대상추가
	pageObj.obj_2_add = function(){
		var row = $("#obj_2_HidTbdy").children().clone();
		$("#obj_2_ShowTbdy").append(row);
	};
	
	// 대상삭제
	pageObj.obj_2_del = function(){
		$("#obj_2_ShowTbdy input[name='objCbk']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().parent().remove();
			}
		});
	};
	
	// 대상삭제
	objRowDel = function(obj){
		$(obj).parent().parent().remove();
	};
	
	// 대상추가
	pageObj.obj_3_add = function(){
		var row = $("#obj_3_HidTbdy").children().clone();
		$("#obj_3_ShowTbdy").append(row);
	};
	
	// 대상삭제
	pageObj.obj_3_del = function(){
		$("#obj_3_ShowTbdy input[name='objCbk']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().parent().remove();
			}
		});
	};
	
	// 대상추가
	pageObj.obj_4_add = function(){
		var row = $("#obj_4_HidTbdy").children().clone();
		$("#obj_4_ShowTbdy").append(row);
	};
	
	// 대상삭제
	pageObj.obj_4_del = function(){
		$("#obj_4_ShowTbdy input[name='objCbk']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().parent().remove();
			}
		});
	};
	
	// 업체 엑셀양식 다운로드
	pageObj.obj_3_excelUploadBtn = function() {
		$("input[name='excelFileUpload']").click();	 
	};
	
	// 대상추가
	pageObj.obj_5_add = function(){
		var row = $("#obj_5_HidTbdy").children().clone();
		$("#obj_5_ShowTbdy").append(row);
	};
	
	// 대상삭제
	pageObj.obj_5_del = function(){
		$("#obj_5_ShowTbdy input[name='objCbk']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().parent().remove();
			}
		});
	};
	
	// 대상추가
	pageObj.obj_6_add = function(){
		var row = $("#obj_6_HidTbdy").children().clone();
		$("#obj_6_ShowTbdy").append(row);
	};
	
	// 대상삭제
	pageObj.obj_6_del = function(){
		$("#obj_6_ShowTbdy input[name='objCbk']").each(function(inx){
			if(this.checked) {
				$(this).parent().parent().parent().remove();
			}
		});
	};
	
	
	excelUploadRegist = function(){
		if($("#excelFileUpload").val()==""){
			alert("파일을 선택해주세요."); 
			return false;
		}
		
		if($("#excelFileUpload").val() !=""){
			var extName = $("#excelFileUpload").val().substring($("#excelFileUpload").val().lastIndexOf(".")+1).toUpperCase();   // 업로드한 파일의 확장자
			
			if(extName != "xlsx" && extName != "XLSX" && extName != "xls" && extName != "XLS"){
				alert("엑셀파일 형식이 아닙니다.");
				return false;
			}

			if(confirm("평가대상이 존재시 초기화 될 수 있습니다.\n엑셀업로드를 하시겠니까?") == true){
				//loading();   
				FwkCmmnUtil.submitForm("excelUploadForm", "/estm/excelObjUpload.do");
			}else{ 
				return false;   
			}
			
		}
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
			$("#obj_5_HidTbdy").remove();
			$("#obj_6_HidTbdy").remove();
			
			pageObj.estmProgObjRegist();
			
		});

		// 삭제
		$("#delBtn").on("click", function() {
			
			if(!confirm("삭제하시겠습니까?")){
				return false;
			}
			
			$("#obj_2_HidTbdy").remove();
			$("#obj_3_HidTbdy").remove();
			$("#obj_4_HidTbdy").remove();
			$("#obj_5_HidTbdy").remove();
			$("#obj_6_HidTbdy").remove();
			
			pageObj.estmProgObjDelete();
			
		});
		
		
		
		// 대상추가 버튼
		$("#obj_1_addBtn").on("click", function() {
			pageObj.obj_1_add();
		});
		
		// 대상삭제 버튼
		$("#obj_1_delBtn").on("click", function() {
			pageObj.obj_1_del();
		});
		
		// 대상추가 버튼
		$("#obj_2_addBtn").on("click", function() {
			pageObj.obj_2_add();
		});
		
		// 평가대상-엑셀업로드(통합)
		$("a[id*='obj_3_excelUploadBtn']").on("click", function() {
			pageObj.obj_3_excelUploadBtn();
		});
		
		// 대상삭제 버튼
		$("#obj_2_delBtn").on("click", function() {
			pageObj.obj_2_del();
		});
		
		// 대상추가 버튼
		$("#obj_3_addBtn").on("click", function() {
			pageObj.obj_3_add();
		});
		
		// 대상추가 버튼
		$("#obj_4_addBtn").on("click", function() {
			pageObj.obj_4_add();
		});

		// 대상삭제 버튼
		$("#obj_3_delBtn").on("click", function() {
			pageObj.obj_3_del();
		});
		
		// 대상삭제 버튼
		$("#obj_4_delBtn").on("click", function() {
			pageObj.obj_4_del();
		});
		
		// 대상추가 버튼
		$("#obj_5_addBtn").on("click", function() {
			pageObj.obj_5_add();
		});
		
		// 대상삭제 버튼
		$("#obj_5_delBtn").on("click", function() {
			pageObj.obj_5_del();
		});
		
		// 대상추가 버튼
		$("#obj_6_addBtn").on("click", function() {
			pageObj.obj_6_add();
		});
		
		// 대상삭제 버튼
		$("#obj_6_delBtn").on("click", function() {
			pageObj.obj_6_del();
		});
		
		
		
	};

	 
	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
		
		var excelResultCode = $("#excelResultCode").val();
		
		if(excelResultCode == "fail"){
			alert("오류가 발생하였습니다. 엑셀 양식과 형식이 맞는지 확인해주세요.");
			FwkCmmnUtil.submitForm("detailFrm", "/estm/estmProgObjDetail.do");
		}
		
	});
})();