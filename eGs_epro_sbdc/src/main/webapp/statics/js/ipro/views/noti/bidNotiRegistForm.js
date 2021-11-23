/**
 * 알림마당 > 입찰공지사항 등록
 *
 * <pre>
 * noti
 *    |_bidNotiRegistForm.js
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
	

	pageObj.bidNotiList = function() {
		FwkCmmnUtil.submitForm("menuMoveFrm", "/noti/bidNotiList.do");  
	}; 
	
	pageObj.bidNotiRegist = function() {
		
		var jsonData = $("#" + defaultFrm).serializeObject();
		var actionUrl = "/noti/bidNotiRegist";

		FwkCmmnUtil.submitAjax(actionUrl, jsonData, function(res) {
			//alert("res ==> " + JSON.stringify(res));
			
			$("#detailFrm input[name='P_BBS_SECD']").val(res.trans.P_BBS_SECD);
			$("#detailFrm input[name='P_BBS_SN']").val(res.trans.P_BBS_SN);
			
			$("#registFrm input[name='P_BBS_SECD']").val(res.trans.P_BBS_SECD);
			$("#registFrm input[name='P_BBS_SN']").val(res.trans.P_BBS_SN);
			pageObj.fileUploadStart();
			
			//FwkCmmnUtil.submitForm("detailFrm", "/noti/bidNotiDetail.do");
		});
		
	}; 
	
	
	// 파일 업로드
	pageObj.fileUploadStart = function() {
		// 첨부파일이 존재하는 경우만 업로드
		if (DEXT5UPLOAD.GetTotalFileCount("upload") == 0) {
			FwkCmmnUtil.submitForm("detailFrm", "/noti/bidNotiDetail.do");
        } else {
            //업로드에서 추가된 파일이 정상으로 넘어오면 파일전송 이벤트를 발생시킵니다. 
            DEXT5UPLOAD.Transfer("upload");
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
			pageObj.bidNotiRegist();
		});
		
		// 목록버튼 
		$("#listBtn").on("click", function() {
			pageObj.bidNotiList(); 
		});
		
		// 팝업여부
		$("#P_PPUP_YN").on("click", function() {
			pageObj.ppupYnEvent(); 
		});
		
	};

	
	/**
	 * window load
	 * 
	 */
	$(function() {		 
		pageObj.setEventHandler();
		
		fileView($("#P_VIEW_ATCHMNFL_GROUP_NO").val());   // 상세
		noViewFileModify("registFrm", "P_ATCHMNFL_GROUP_NO", "P_ATCHMNFL_GROUP_NEW", "/noti/bidNotiUpdt.do","detailFrm", "/noti/bidNotiDetail.do");
	});
})();