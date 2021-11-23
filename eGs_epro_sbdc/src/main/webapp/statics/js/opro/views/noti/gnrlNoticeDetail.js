(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**  
     * <pre>
     * 1. 개요 : 목록조회 
     * 2. 처리내용 : 
     * 		일반 공지사항 목록 조회
     *  	
     * </pre>
     * @Function Name : listInqire
     * @date : 2017.06.17
     * @author : 은우소프트 이주연
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2017.06.17      은우소프트 이주연              최초 작성 
     *  =======================================================   
     */
	pageObj.listInqire = function() {
		FwkCmmnUtil.submitForm("listFrm", "/opro/noti/gnrlNoticeList.do");
	}; 
	
	download = function(sn) {
		$("#downloadFrm input[name='P_ATCHMNFL_SN']").val(sn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do");
	}; 
	
	pageObj.setEventHandler = function() {
		// 목록버튼
		$("#listBtn").on("click", function() {
			if(!confirm("목록으로 이동하시겠습니까?")){
				return false;
			}
			pageObj.listInqire(); 
		});
		
	};
	

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();
	});
	
})();