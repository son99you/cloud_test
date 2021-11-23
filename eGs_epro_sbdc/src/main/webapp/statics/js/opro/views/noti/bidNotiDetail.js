/**
 * 알림마당 > 입찰공지사항 상세
 *
 * <pre>
 * noti
 *    |_bidNotiDetail.js
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

	var defaultFrm = "searchFrm";
	
	pageObj.bidNotiList = function() {
		FwkCmmnUtil.submitForm("listFrm", "/opro/noti/bidNotiList.do");  
	}; 
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
	pageObj.setEventHandler = function() {

		// 목록버튼 
		$("#listBtn").on("click", function() {
			pageObj.bidNotiList(); 
		});
	};

	
	/**
	 * window load
	 * 
	 */
	$(function() {		 
		pageObj.setEventHandler();
		
		fileView($("#P_FILE_GRP_NO").val());
	});
})();


