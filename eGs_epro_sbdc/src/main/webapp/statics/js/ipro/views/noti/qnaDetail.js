/**
 * 알림마당 > 일반공지사항 상세
 *
 * <pre>
 * noti
 *    |_ notiDetail.js
 * 
 * </pre>
 * @date : 2017.06.14
 * @version : 1.0
 * @author : 은우소프트 이주연
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "searchFrm";

	RAONKEDITOR_CreationComplete = function(editorId) {	   
		var html = $("#P_BBS_CNTN").val();
	 	RAONKEDITOR.SetHtmlContents(html, 'editor1');
	 };
	 //목록
	 pageObj.qnaList = function() {
		 FwkCmmnUtil.submitForm("listFrm", "/noti/qnaList.do");
	 };
	//수정
	pageObj.updtQnaForm = function() {
		FwkCmmnUtil.submitForm("updtFrm", "/noti/qnaReplyUpdtForm.do");
	};
	
	//답글
	pageObj.qnaReply = function() {
		FwkCmmnUtil.submitForm("registFrm", "/noti/qnaReplyRegistForm.do");
	};
	
	
	
	pageObj.unityNttDelete = function() {
		var jsonData = $("#updtFrm").serializeObject();
		var actionUrl = "/noti/qnaReplyDelete";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
			, function(res) {
				alert(FwkMssageUtil.getMessage("COM.INF.002", "삭제"));
				pageObj.qnaList();
			}
		);
	};
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 수정버튼
		$("#modifyBtn").on("click", function() {
			if(!confirm("수정화면으로 이동하시겠습니다.")){
				return false;
			}
			pageObj.updtQnaForm();
		});
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.qnaList();
		});
		
		//답글버튼
		$("#replyBtn").on("click", function() {
			//console.log("답글버튼");
			pageObj.qnaReply();
		});
		

		// 삭제버튼
		$("#deleteBtn").on("click", function() {
			if(!confirm("해당 답변이 삭제됩니다.\n삭제하시겠습니까?")){
				return false;
			}
			
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "삭제"))){
				return false;
			}
			
			pageObj.unityNttDelete();
		});
	};

	/**
	 * window load
	 *
	 */ 
	$(function(){
		pageObj.setEventHandler();
		
		fileView($("#P_FILE_GRP_NO").val());
	});
})();