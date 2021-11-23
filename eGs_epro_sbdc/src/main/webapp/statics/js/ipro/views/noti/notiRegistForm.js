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

	pageObj.notiList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/noti/notiList.do");
	};

	pageObj.notiRegist = function() {

		fileUploadStart();//3
	};
	
	
	/**  
     * <pre>
     * 1. 개요 : 수정 폼 조회 
     * 2. 처리내용 : 
     * 		일반 공지사항 수정 폼 조회 
     *  	
     * </pre>
     * @Function Name : 
     * @date : 2017.06.13
     * @author : 은우소프트 이주연
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017.06.13       은우소프트 이주연           최초 작성 
     *  =======================================================   
     */
	pageObj.registFrmInqire = function() { 
		fn_getBodyValue('editor1');
		
	};
	
	fn_getBodyValue = function(editorID) {
		var rtnValue;
	    var fn_callback = function (paramObj) {
	    	rtnValue = paramObj.strData;
			$("#P_BBS_CNTN").val(rtnValue);
			fileUploadStart(); //2
	    };

	    RAONKEDITOR.GetHtmlContents({
	        type: 'body',
	        callback: fn_callback
	    }, editorID);
	};


	// 파일 업로드
	fileUploadStart = function() {
		// 첨부파일이 존재하는 경우만 업로드
		if (RAONKUPLOAD.GetTotalFileCount("upload") == 0) {
			FwkCmmnUtil.submitForm("registFrm", "/noti/notiRegist.do");
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
			
			if(!confirm("저장되었습니다.")){
				return false;
			}

//			pageObj.notiRegist();
			pageObj.registFrmInqire(); //1
		});

		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.notiList();
		});

	};


	/**
	 * window load
	 *
	 */
	$(function() {
		pageObj.setEventHandler();
		fileUpload("registFrm", "P_FILE_GRP_NO", "/noti/notiRegist.do");
	});
})();