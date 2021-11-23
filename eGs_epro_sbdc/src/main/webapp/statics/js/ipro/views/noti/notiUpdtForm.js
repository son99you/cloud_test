/**
 * 알림마당 > 일반공지사항 수정
 *
 * <pre>
 * noti
 *    |_notiUpdtForm.js
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
	
	var defaultFrm = "updtFrm";

	//상세
	pageObj.notiDetail = function(){
		FwkCmmnUtil.submitForm("updtFrm","/noti/notiDetail.do" );
	}
	
	RAONKEDITOR_CreationComplete = function(editorId) {	   
		var html = $("#P_BBS_CNTN").val();
	 	RAONKEDITOR.SetHtmlContents(html, 'editor1');
	 };
	
	
	/**  
     * <pre>
     * 1. 개요 : 수정 폼 조회 
     * 2. 처리내용 : 
     * 		일반 공지사항 수정 폼 조회 
     *  	
     * </pre>
     * @Function Name : registFrmInqire
     * @date : 2017.06.13
     * @author : 은우소프트 이주연
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017.06.13       은우소프트 이주연           최초 작성 
     *  =======================================================   
     */
	 pageObj.notiList = function() {
		 FwkCmmnUtil.submitForm("listFrm", "/noti/notiList.do");
	 };
	 
	
	pageObj.notiUpdt = function() {
		fileUploadStart();
	};
	
	pageObj.notiUpdt = function() { 
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
		RAONKUPLOAD.Transfer("uploadView1");
	};	
	
//	fileUploadStart = function() { //step4
//		// 첨부파일이 존재하는 경우만 업로드
//		if (RAONKUPLOAD.GetTotalFileCount("upload") == 0) { // 첨부파일이 기존에 있고 추가가 없기 때문에 == 0을 사용  그냥 경로타고 가는거고 
//			alert("1");
//			FwkCmmnUtil.submitForm("registFrm", "/noti/notiRegist.do");
//        } else {
//            //업로드에서 추가된 파일이 정상으로 넘어오면 파일전송 이벤트를 발생시킵니다.
//        	RAONKUPLOAD.Transfer("upload"); // 첨부파일이 추가되면 첨부파일을 업로드를 시키기위해 있는 코드  
//        }
//	};	
	
	
//	RAONKEDITOR_CreationComplete = function(editorId) {
//	   	var html = $("#P_BBS_CNTN").val();
//	 	RAONKEDITOR.SetHtmlContents(html, editorId);
//	 };
	 
	 
		ppupYnEvent = function(obj) {
			
			if($(obj).prop("checked")){
				$(obj).val("Y");
			}else{
				$(obj).val("N");
			}
			
		};
		




	// 파일 업로드
//	fileUploadStart = function() {
//		RAONKUPLOAD.Transfer("uploadView1");
//	};

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

			if(!confirm("수정되었습니다.")){
				return false;
			}
			pageObj.notiUpdt();
//			pageObj.registFrmInqire();
			
		});

		// 목록버튼
		$("#cnclBtn").on("click", function() {
			if(!confirm("취소되었습니다.")){
				return false;
			}
			pageObj.notiDetail();
		});

	};


	/**
	 * window load
	 *
	 */
	$(function() {
		pageObj.setEventHandler();
		fileModify("updtFrm", "P_FILE_GRP_NO", "P_FILE_GRP_NO_NEW", "/noti/notiUpdt.do");
	});
})();