/**
 * 알림마당 > 계약자료실 등록
 *
 * <pre>
 * noti
 *    |_rssRegistForm.js
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
	

	pageObj.rssList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/noti/rssList.do");  
	}; 
	
	pageObj.rssRegist = function() {
		
		/*var jsonData = $("#" + defaultFrm).serializeObject();
		var actionUrl = "/noti/rssRegist";

		FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(res) {
			//alert("res ==> " + JSON.stringify(res));
			
			$("#detailFrm input[name='P_BBS_SECD']").val(res.trans.P_BBS_SECD);
			$("#detailFrm input[name='P_BBS_SN']").val(res.trans.P_BBS_SN);
			
			$("#registFrm input[name='P_BBS_SECD']").val(res.trans.P_BBS_SECD);
			$("#registFrm input[name='P_BBS_SN']").val(res.trans.P_BBS_SN);
			pageObj.fileUploadStart();
			
			//FwkCmmnUtil.submitForm("detailFrm", "/noti/rssDetail.do");
		});*/
		fileUploadStart();
	}; 
	
	
	// 파일 업로드
	fileUploadStart = function() {
		// 첨부파일이 존재하는 경우만 업로드
		// 첨부파일이 존재하는 경우만 업로드
		if (RAONKUPLOAD.GetTotalFileCount("upload") == 0) {
			FwkCmmnUtil.submitForm("registFrm", "/noti/rssRegist.do");
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
			/*if($("#P_BBS_CNTN").text() == ''){
				alert("내용을 입력하여 주시기 바랍니다.");
				$("#P_BBS_CNTN").focus();
				return false;
			}*/

			if(!confirm("저장하시겠습니까?")){
				return false;
			}
			
			pageObj.rssRegist();
		});
		
		// 목록버튼 
		$("#listBtn").on("click", function() {
			pageObj.rssList(); 
		});
		
	};

	
	/**
	 * window load
	 * 
	 */
	$(function() {		 
		pageObj.setEventHandler();
		
		fileUpload("registFrm", "P_FILE_GRP_NO", "/noti/rssRegist.do");
		/*fileView($("#P_VIEW_ATCHMNFL_GROUP_NO").val());   // 상세
		fileUpload("registFrm", "P_ATCHMNFL_GROUP_NO", "/noti/rssRegist.do","detailFrm", "/noti/rssDetail.do");*/
	});
})();