/**
 * 통합게시판 > 자료실등록
 *
 * <pre>
 * noti
 *    |_qnaRegistForm.js
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
//	var file = null;

	/**  
     * <pre>
     * 1. 개요 : 목록조회 
     * 2. 처리내용 : 
     * 		일반 공지사항 목록 조회
     *  	
     * </pre>
     * @Function Name : listInqire
     * @date : 2017.06.13
     * @author : 은우소프트 이주연
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017.06.13      은우소프트 이주연              최초 작성 
     *  =======================================================   
     */
	
	var defaultFrm = "registFrm";
	
	RAONKEDITOR_CreationComplete = function(editorId) {	   
		var html = $("#P_BBS_CNTN").val();
//		alert("전" + html);
	 	RAONKEDITOR.SetHtmlContents(html, 'editor1');
//	 	alert("후" + html);
	 };
	
	pageObj.qnaList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/opro/noti/qnaList.do");
	};
	
	/**  
     * <pre>
     * 1. 개요 : 수정 폼 조회 
     * 2. 처리내용 : 
     * 		일반 공지사항 수정 폼 조회 
     *  	
     * </pre>
     * @Function Name : updtFrmInqire
     * @date : 2017.06.13
     * @author : 은우소프트 이주연
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017.06.13       은우소프트 이주연           최초 작성 
     *  =======================================================   
     */
	pageObj.qnaRegist = function() {
		fileUploadStart();
		//FwkCmmnUtil.submitForm("registFrm", "/opro/noti/qnaRegist.do");
	};
	
	pageObj.registFrmInqire = function() { 
		fn_getBodyValue('editor1'); //step2
		
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
		if (RAONKUPLOAD.GetTotalFileCount("upload") == 0) {
			FwkCmmnUtil.submitForm("registFrm", "/opro/noti/qnaRegist.do");
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
			if($("#P_TTL_NM").val() == ''){
				alert("제목을 입력하여 주시기 바랍니다.");
				$("#P_TTL_NM").focus();
				return false;
			}
		
//			pageObj.qnaRegist();
			pageObj.registFrmInqire();
		}); 
		
		// 취소버튼
//		$("#listBtn").on("click", function() {
//			if(!confirm("목록으로 이동하시겠습니까?")){
//				return false;
//			}
//			pageObj.listInqire(); 
//		});
		
		
		// 목록버튼 
		$("#listBtn").on("click", function() {
			pageObj.qnaList(); 
		});
		
	};
 
	/** 
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
		
		/** 
		 * 라온시큐어
		 * 화면캡쳐, 키보드보안 적용
		 */
//		TouchEnNxConfig.installPage.tos = TouchEnNxConfig.installPage.nxkey;
		//화면캡쳐 보안 실행시
		
//		nxwebhtml5_load(0);	//0으로 설정 할 경우 화면캡쳐 작동 안함
		//화면캡쳐 보안 실행 안할경우  개발시에는 0으로 하고 진행
		//nxwebhtml5_load(0);	//0으로 설정 할 경우 화면캡쳐 작동 안함

		
		fileUpload("registFrm", "P_FILE_GRP_NO", "/opro/noti/qnaRegist.do");
	});
})();