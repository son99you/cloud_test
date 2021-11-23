/**
 * 알림마당 > 계약자료실 등록
 *
 * <pre>
 * noti
 *    |_rssRegistForm.js
 * 
 * </pre>
 * @date : 2017.06.14
 * @version : 1.0a
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
	
	
	pageObj.qnaList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/noti/qnaList.do");  
	}; 
	
	pageObj.qnaReplyRegist = function() {
		fileUploadStart();
	}; 
	pageObj.registFrmInqire = function() { 
		fn_getBodyValue('editor1');
		
	};
	
	fn_getBodyValue = function(editorID) {
		var rtnValue;
	    var fn_callback = function (paramObj) {
	    	rtnValue = paramObj.strData;
			$("#P_BBS_CNTN").val(rtnValue);
			fileUploadStart();
	    };

	    RAONKEDITOR.GetHtmlContents({
	        type: 'body',
	        callback: fn_callback
	    }, editorID);
	};
	
	
	// 파일 업로드
	fileUploadStart = function() {
		// 첨부파일이 존재하는 경우만 업로드
		// 첨부파일이 존재하는 경우만 업로드
		if (RAONKUPLOAD.GetTotalFileCount("upload") == 0) {
			FwkCmmnUtil.submitForm("registFrm", "/noti/qnaReplyRegist.do");
        } else {
            //업로드에서 추가된 파일이 정상으로 넘어오면 파일전송 이벤트를 발생시킵니다.
        	RAONKUPLOAD.Transfer("upload");
        }
	};	
	

	ppupYnEvent = function(obj) {
		
		if($(obj).prop("checked")){
			$(obj).val("Y");
		}else{
			$(obj).val("N");
		}
		
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 등록버튼
		$("#registBtn").on("click", function() {
			//FwkCmmnUtil.submitForm("registFrm", "/noti/qnaReplyRegistForm.do");
			if($("#P_TTL_NM").val() == ''){
				alert("제목을 입력하여 주시기 바랍니다.");
				$("#P_TTL_NM").focus();
			return false;
			}
//			if(!confirm("저장하시겠습니까?")){
//				return false;
//			}
//			pageObj.qnaReplyRegist();
			pageObj.registFrmInqire();
		});
		
			/*if($("#P_BBS_CNTN").text() == ''){
				alert("내용을 입력하여 주시기 바랍니다.");
				$("#P_BBS_CNTN").focus();
				return false;
			}*/

			
		
		// 목록버튼 
		$("#listBtn").on("click", function() {
			pageObj.qnaList(); 
		});
		
	};

	
	/**
	 * window load
	 * 
	 */
	$(function() {		 
		pageObj.setEventHandler();
		
		fileUpload("registFrm", "P_FILE_GRP_NO", "/noti/qnaReplyRegist.do");
	});
})();