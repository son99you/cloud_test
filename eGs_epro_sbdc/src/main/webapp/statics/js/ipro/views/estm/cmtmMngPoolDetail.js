/**
 * 평가관리 > 평가위원관리 > 평가위원POOL현황 상세
 *
 * <pre>
 * estm
 *    |_ cmtmMngPoolDetail.js
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

	
	// 목록
	pageObj.cmtmMngPoolList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/estm/cmtmMngPoolList.do"); 
	};
	
	// 수정화면
	pageObj.cmtmMngPoolUpdtForm = function(){
		FwkCmmnUtil.submitForm(defaultFrm, "/estm/cmtmMngPoolUpdtForm.do");
	};
	
	// 삭제
	pageObj.cmtmMngPoolDelete = function(){
		
		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/estm/cmtmMngPoolDelete";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			alert("처리되었습니다.");
			pageObj.cmtmMngPoolList();
		});
	};
	
	
	
	pageObj.download = function(fileGrpNo, fileSn){
		$("#downloadFrm input[name='P_FILE_GRP_NO']").val(fileGrpNo);
		$("#downloadFrm input[name='P_FILE_SN']").val(fileSn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do");
	};

	pageObj.poolDownload = function(fileStreCours, wonFileNm){
		$("#downloadFrm input[name='P_FILE_STRE_COURS']").val(fileStreCours);
		$("#downloadFrm input[name='P_WON_FILE_NM']").val(wonFileNm);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/poolDownload.do");
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.cmtmMngPoolList();
		});
		
		// 수정버튼
		$("#updtBtn").on("click", function() {
			pageObj.cmtmMngPoolUpdtForm();
		});
		
		// 삭제버튼
		$("#delBtn").on("click", function() {
			
			if(!confirm("삭제하시겠습니까?")){
				return false; 
			}
			
			pageObj.cmtmMngPoolDelete();
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