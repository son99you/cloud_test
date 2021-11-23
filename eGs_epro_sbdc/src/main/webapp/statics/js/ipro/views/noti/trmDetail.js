/**
 * 알림마당 > 일반공지사항 상세
 *
 * <pre>
 * noti
 *    |_ trmDetail.js
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
	
	pageObj.updtForm = function() {
		FwkCmmnUtil.submitForm("updtFrm", "/noti/trmUpdtForm.do");
	};


	pageObj.trmList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/noti/trmList.do");
	};


	pageObj.trmDelete = function() {
		var jsonData = $("#updtFrm").serializeObject();
		var actionUrl = "/noti/trmDelete";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
			, function(res) {
				alert(FwkMssageUtil.getMessage("COM.INF.002", "삭제"));
				pageObj.trmList();
			}
		);
		//FwkCmmnUtil.submitForm("updtFrm", "/noti/notiDelete.do");
	};

	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */
	pageObj.setEventHandler = function() {

		// 수정버튼
		$("#updtBtn").on("click", function() {
			pageObj.updtForm();
		});

		// 삭제버튼
		$("#deleteBtn").on("click", function() {

			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "삭제"))){
				return false;
			}

			pageObj.trmDelete();
		});

		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.trmList();
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