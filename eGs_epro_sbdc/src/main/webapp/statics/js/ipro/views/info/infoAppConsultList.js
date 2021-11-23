(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	var defaultFrm = "searchFrm";
	
	pageObj.listInqire = function() {
		FwkCmmnUtil.submitForm("searchFrm", "/info/infoAppConsultList.do");
	};
	
	/**  
     * <pre>
     * 1. 개요 : 페이지번호 클릭
     * 2. 처리내용 : 
     * 		- 선택된 페이지번호를 set 한다.
     *  	- 입찰공고등록대장목록조회를 호출한다.
     * </pre>
     * @Function Name : clickPage
     * @date : 2018. 02. 27.
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2018. 02. 27.       은우소프트 맹경열              최초 작성 
     *  =======================================================   
     */
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.listInqire();
	};

	/**  
     * <pre>
     * 1. 개요 : 결재대상 페이지 상세
     * 2. 처리내용 : 
     * 		- 결재대상 페이지 상세
     * </pre>
     * @Function Name : infoApprlineDetail
     * @date : 2018. 03. 21.
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2018. 03. 21.       은우소프트 맹경열              최초 작성 
     *  =======================================================   
     */
	infoApprlineDetail = function(appr_no) {
		$('#P_APPR_NO').val(appr_no);
		FwkCmmnUtil.submitForm("detailFrm", "/info/infoAppConsultDetail.do");
	};
	
	pageObj.setEventHandler = function() {
		
		//조회버튼
		$('#searchBtn').on('click', function() {
			pageObj.listInqire();		
		});
		
		$('input').on("keyup", function(evnt){
			if(evnt.keyCode == 13) {
				pageObj.listInqire();		
			}
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