/**
 * 통합게시판 > 일반 공지사항 상세
 *
 * <pre>
 * noti
 *    |_ noticePopup.js
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

	pageObj.notiListPopup = function(){
		FwkCmmnUtil.submitForm("listFrm", "/main/popup/noticeListPopup.do");
	};
	
	RAONKEDITOR_CreationComplete = function(editorId) {	 
		var html = $("#P_BBS_CNTN").val();
		RAONKEDITOR.SetHtmlContents(html, 'editor1');
	 };
	 
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		$("#listBtn").on("click", function() {
			pageObj.notiListPopup();
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