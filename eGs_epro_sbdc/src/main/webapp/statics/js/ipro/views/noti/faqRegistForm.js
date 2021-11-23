/**
 * 알림마당 > 자주묻는질문 등록
 *
 * <pre>
 * noti
 *    |_faqRegistForm.js
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

	pageObj.faqList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/noti/faqList.do");  
	}; 
	
	pageObj.faqRegist = function() {
		
	/*	var jsonData = $("#" + defaultFrm).serializeObject();
		var actionUrl = "/noti/faqRegist";

		FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(res) {
			//alert("res ==> " + JSON.stringify(res));
			
			$("#detailFrm input[name='P_BBS_SECD']").val(res.trans.P_BBS_SECD);
			$("#detailFrm input[name='P_BBS_SN']").val(res.trans.P_BBS_SN);
			
			$("#registFrm input[name='P_BBS_SECD']").val(res.trans.P_BBS_SECD);
			$("#registFrm input[name='P_BBS_SN']").val(res.trans.P_BBS_SN);
			pageObj.fileUploadStart();
			
			//FwkCmmnUtil.submitForm("detailFrm", "/noti/faqDetail.do");
		});*/
		fileUploadStart();
	};
	
	
	pageObj.registFrmInqire = function() { 
		fn_getBodyValue('editor1'); //step2
		
	};
	
	fn_getBodyValue = function(editorID) { //step3
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
	fileUploadStart = function() { //step4
		// 첨부파일이 존재하는 경우만 업로드
		if (RAONKUPLOAD.GetTotalFileCount("upload") == 0) { // 첨부파일이 기존에 있고 추가가 없기 때문에 == 0을 사용  그냥 경로타고 가는거고 
			FwkCmmnUtil.submitForm("registFrm", "/noti/faqRegist.do");
        } else {
            //업로드에서 추가된 파일이 정상으로 넘어오면 파일전송 이벤트를 발생시킵니다.
        	RAONKUPLOAD.Transfer("upload"); // 첨부파일이 추가되면 첨부파일을 업로드를 시키기위해 있는 코드  
        }
	};	
	
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
			/*if($("#P_BBS_CNTN").text() == ''){
				alert("내용을 입력하여 주시기 바랍니다.");
				$("#P_BBS_CNTN").focus();
				return false;
			}*/

			if(!confirm("저장되었습니다.")){
				return false;
			}
			
			//pageObj.faqRegist();
			pageObj.registFrmInqire(); //step1
		});
		
		// 목록버튼 
		$("#listBtn").on("click", function() {
			pageObj.faqList(); 
		});
		
	};

	
	/**
	 * window load
	 * 
	 */
	$(function() {		 
		pageObj.setEventHandler();
		
		fileUpload("registFrm", "P_FILE_GRP_NO", "/noti/faqRegist.do");
		//fileView($("#P_VIEW_ATCHMNFL_GROUP_NO").val());   // 상세
		//fileUpload("registFrm", "P_ATCHMNFL_GROUP_NO", "/noti/faqRegist.do","detailFrm", "/noti/faqDetail.do");
	});
})();