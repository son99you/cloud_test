/**
 * 알림마당 > 입찰공지사항 등록
 *
 * <pre>
 * noti
 *    |_notiRegistForm.js
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

	var defaultFrm = "registFrm";

	RAONKEDITOR_CreationComplete = function(editorId) {	   
		var html = $("#P_BBS_CNTN").val();
	 	RAONKEDITOR.SetHtmlContents(html, 'editor1');
	 };
	
	pageObj.trmList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/noti/trmList.do");
	};

	pageObj.trmRegist = function() {

		//동일한 이름 존재 여부
		$("#detailFrm input[name='P_TTL_NM_S']").val($("#P_TTL_NM").val());
		var jsonData = $("#detailFrm").serializeObject();
		var actionUrl = "/noti/trmCnt"; 
		FwkCmmnUtil.submitAjax (actionUrl, jsonData 
				, function(res) {
			if(res.trmCnt > 0){
				alert("존재하는 용어입니다.");
			}else{
				fileUploadStart();
			}
		});
	};


	// 파일 업로드
	fileUploadStart = function() {
		// 첨부파일이 존재하는 경우만 업로드
		if (RAONKUPLOAD.GetTotalFileCount("upload") == 0) {
			FwkCmmnUtil.submitForm("registFrm", "/noti/trmRegist.do");
        } else {
            //업로드에서 추가된 파일이 정상으로 넘어오면 파일전송 이벤트를 발생시킵니다.
        	RAONKUPLOAD.Transfer("upload");
        }
	};

	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */
	pageObj.setEventHandler = function() {

		// 등록버튼

		$("#registBtn").on("click", function() {
			if($("#P_TTL_NM").val() == ''){
				alert("제목을 입력하여 주시기 바랍니다.");
				$("#P_TTL_NM").focus();
				return false;
			}
			/*if($("#TRM_CNTN").text() == ''){
				alert("내용을 입력하여 주시기 바랍니다.");
				$("#TRM_CNTN").focus();
				return false;
			}*/

			if(!confirm("저장하시겠습니까?")){
				return false;
			}

			pageObj.trmRegist();
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
	$(function() {
		pageObj.setEventHandler();
		fileUpload("registFrm", "P_FILE_GRP_NO", "/noti/trmRegist.do");
	});
})();