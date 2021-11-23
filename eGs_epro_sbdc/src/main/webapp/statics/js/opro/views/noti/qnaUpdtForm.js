/**
 * 알림마당 > 자주묻는질문 수정
 *
 * <pre>
 * noti
 *    |_faqUpdtForm.js
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
	
	RAONKEDITOR_CreationComplete = function(editorId) {	   
		var html = $("#P_BBS_CNTN").val();
	 	RAONKEDITOR.SetHtmlContents(html, 'editor1');
	 };
	
	
	pageObj.qnaList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/opro/noti/qnaList.do");  
	}; 
	
	pageObj.qnaUpdt = function() {
		console.log("큐앤에이 수정2");
		fileUploadStart();
	}; 
	
	pageObj.registFrmInqire = function() { 
		fn_getBodyValue('editor1');//2
	};
	
	fn_getBodyValue = function(editorID) {//3
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
//	fileUploadStart = function() {
//		RAONKUPLOAD.Transfer("uploadView1");
//	};	
	
	
	// 파일 업로드
	fileUploadStart = function() { //step4
		// 첨부파일이 존재하는 경우만 업로드
		if (RAONKUPLOAD.GetTotalFileCount("upload") == 0) { // 첨부파일이 기존에 있고 추가가 없기 때문에 == 0을 사용  그냥 경로타고 가는거고 
			FwkCmmnUtil.submitForm("registFrm", "/noti/qnaRegist.do");
		} else {
			//업로드에서 추가된 파일이 정상으로 넘어오면 파일전송 이벤트를 발생시킵니다.
			RAONKUPLOAD.Transfer("upload"); // 첨부파일이 추가되면 첨부파일을 업로드를 시키기위해 있는 코드  
		}
	};		
//	
//	RAONKEDITOR_CreationComplete = function(editorId) {
//	   	var html = $("#P_BBS_CNTN").val();
//	 	RAONKEDITOR.SetHtmlContents(html, editorId);
//	 };
//	
	
	
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

		// 저장버튼
		$("#updtBtn").on("click", function() {
			console.log("큐앤에이 수정1");
			pageObj.registFrmInqire();//1
		});
		
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
		
//		/** 
//		 * 라온시큐어
//		 * 화면캡쳐, 키보드보안 적용
//		 */
//		TouchEnNxConfig.installPage.tos = TouchEnNxConfig.installPage.nxkey;
//		//화면캡쳐 보안 실행시
//		
//		nxwebhtml5_load(0);	//0으로 설정 할 경우 화면캡쳐 작동 안함
//		//화면캡쳐 보안 실행 안할경우  개발시에는 0으로 하고 진행
//		//nxwebhtml5_load(0);	//0으로 설정 할 경우 화면캡쳐 작동 안함
		
		fileModify("updtFrm", "P_FILE_GRP_NO", "P_FILE_GRP_NO_NEW", "/opro/noti/qnaUpdt.do");
	});
})();
