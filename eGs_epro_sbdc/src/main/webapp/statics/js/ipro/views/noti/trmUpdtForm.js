/**
 * 알림마당 > 용어사전 수정
 *
 * <pre>
 * noti
 *    |_ trmUpdtForm.js
 *
 * </pre>
 * @date : 2017. 06. 14.
 * @version : 1.0
 * @author : 은우소프트 이주연
 */
(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	var defaultFrm = "updtFrm";
	
	//상세
	pageObj.trmDetail = function(){
		FwkCmmnUtil.submitForm("updtFrm", "/noti/trmDetail.do");
	}
	RAONKEDITOR_CreationComplete = function(editorId) {	   
		var html = $("#P_BBS_CNTN").val();
	 	RAONKEDITOR.SetHtmlContents(html, 'editor1');
	 };

	pageObj.trmList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/noti/trmList.do");
	};

	pageObj.trmUpdt = function() {
		fileUploadStart();
	};


	// 파일 업로드
	fileUploadStart = function() {
		RAONKUPLOAD.Transfer("uploadView1");
	};

	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */
	pageObj.setEventHandler = function() {

		// 저장버튼
		$("#updtBtn").on("click", function() {

			if($("#P_TTL_NM").val() == ''){
				alert("제목을 입력하여 주시기 바랍니다.");
				$("#P_TTL_NM").focus();
				return false;
			}
			if($("#P_TRM_CNTN").text() == ''){
				alert("내용을 입력하여 주시기 바랍니다.");
				$("#P_TRM_CNTN").focus();
				return false;
			}

			if(!confirm("수정되었습니다.")){
				return false;
			}

			pageObj.trmUpdt();
		});

		// 목록버튼
		$("#cnclBtn").on("click", function() {
			if(!confirm("취소되었습니다.")){
				return false;
			}
			pageObj.trmDetail();
		});

	};


	/**
	 * window load
	 *
	 */
	$(function() {
		pageObj.setEventHandler();
		fileModify("updtFrm", "P_FILE_GRP_NO", "P_FILE_GRP_NO_NEW", "/noti/trmUpdt.do");
	});
})();