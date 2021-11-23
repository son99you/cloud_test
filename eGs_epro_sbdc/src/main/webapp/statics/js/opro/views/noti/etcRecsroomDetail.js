/**
 * 통합게시판 > 기타자료실 상세
 *
 * <pre>
 * unbb
 *    |_ oepUnbbEtcRecsroomDetail.js
 * 
 * </pre>
 * @date : 2015. 06. 01. 오후 01 : 56 : 00                                                                                                                                                                                                                                                                                      1:38:00
 * @version : 1.0
 * @author : 은우소프트 전상훈
 */

(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};

	/**
	 * Default Form Name
	 */
	var defaultFrm = "listFrm";

	/**  
     * <pre>
     * 1. 개요 : 목록조회 Submit
     * 2. 처리내용 : 
     * 		기타자료실 목록 조회 Form 을 Sumit 한다.
     *  	
     * </pre>
     * @Function Name : listInqire
     * @date : 2015. 06. 01.
     * @author : 은우소프트 전상훈
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 06. 01.       은우소프트 전상훈              최초 작성 
     *  =======================================================   
     */
	pageObj.listInqire = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/opro/noti/etcRecsroomList.do");
	};
	
	download = function(sn) {
		$("#downloadFrm input[name='P_ATCHMNFL_SN']").val(sn);
		FwkCmmnUtil.submitForm("downloadFrm", "/comm/download.do");
	}; 
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 */	
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
	$(function(){
		pageObj.setEventHandler();
	});
})();