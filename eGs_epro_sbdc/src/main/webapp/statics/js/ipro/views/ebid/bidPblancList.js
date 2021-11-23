/**
 * 입찰관리 > 입찰공고 목록
 *
 * <pre>
 * ebid
 *    |_ bidPblancList.js
 * 
 * </pre>
 * @date : 2017. 06. 19
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
	var defaultFrm = "searchFrm";

	
	/**  
     * <pre>
     * 1. 개요 : 입찰공고 목록조회 Submit
     * 2. 처리내용 : 
     * 		입찰공고 목록 조회 Form 을 Sumit 한다.
     *  	
     * </pre>
     * @Function Name : bidPblancList
     * @date : 2015. 02. 10.
     * @author : 은우소프트 하성윤
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 02. 10.       은우소프트 하성윤              최초 작성 
     *  =======================================================   
     */
	pageObj.bidPblancList = function() {
		FwkCmmnUtil.addHiddenParam(defaultFrm, [{"id":"search", "name":"search", "value":"Y"}]);
		FwkCmmnUtil.submitForm(defaultFrm, "/ebid/bidPblancList.do");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 입찰공고 상세 조회 페이지 이동
	 * 2. 처리내용 : 
	 * 		입찰공고 상세 조회 페이지로 이동한다.
	 *  	
	 * </pre>
	 * @Function Name : bidPblancDetail
	 * @date : 2015. 02. 10
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 02. 10.       은우소프트 하성윤              최초 작성 
	 *  =======================================================   
	 */
	bidPblancDetail = function(pblancNo, pblancOdr, roundNo) {
		$("#viewFrm input[name='P_ANNC_NO']").val(pblancNo);
		$("#viewFrm input[name='P_ANNC_NGR']").val(pblancOdr);
		$("#viewFrm input[name='P_ROUND_NO']").val(roundNo);
		FwkCmmnUtil.submitForm("viewFrm", "/ebid/bidPblancDetail.do");
	};
	
	
	// 업체 열람 목록
	vendOpenList = function(anncNo, anncNgr, roundNo) {
		
		$("#popupFrm input[name='P_ANNC_NO']").val(anncNo);
		$("#popupFrm input[name='P_ANNC_NGR']").val(anncNgr);
		$("#popupFrm input[name='P_ROUND_NO']").val(roundNo);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "vendOpenListPopup", "width=740px,height=500px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=150");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/bidVendOpenList.do';
	        this.method = 'POST';
	        this.target = 'vendOpenListPopup';
	    }).trigger("submit");
		
	};
	
	// 투찰 열람 목록
	vendSbidList = function(anncNo, anncNgr, roundNo) {
		
		$("#popupFrm input[name='P_ANNC_NO']").val(anncNo);
		$("#popupFrm input[name='P_ANNC_NGR']").val(anncNgr);
		$("#popupFrm input[name='P_ROUND_NO']").val(roundNo);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "vendSbidListPopup", "width=740px,height=500px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=150");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/bidVendSbidList.do';
			this.method = 'POST';
			this.target = 'vendSbidListPopup';
		}).trigger("submit");
		
	};		

	/**  
     * <pre>
     * 1. 개요 : 페이지번호 클릭
     * 2. 처리내용 : 
     * 		- 선택된 페이지번호를 set 한다.
     *  	- 입찰공고 목록조회를 호출한다.
     * </pre>
     * @Function Name : clickPage
     * @date : 2015. 02. 10.
     * @author : 은우소프트 하성윤
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 02. 10.       은우소프트 하성윤              최초 작성 
     *  =======================================================   
     */
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.bidPblancList();
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 부서 조회 팝업
	 * 2. 처리내용 : 
	 * 		- 부서 조회 팝업화면으로 이동한다.
	 * </pre>
	 * @Function Name : deptInqirePopup
	 * @date : 2019. 02. 13.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2019. 02. 13.       은우소프트 맹경열                    최초 작성 
	 *  =======================================================   
	 */
	pageObj.deptInqirePopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "deptPopup", "width=800px,height=800px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=50");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/rqstDeptList.do';
	        this.method = 'POST';
	        this.target = 'deptPopup';
	    }).trigger("submit");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 담당자 조회 팝업
	 * 2. 처리내용 : 
	 * 		- 담당자 조회 팝업화면으로 이동한다.
	 * </pre>
	 * @Function Name : chargerInqirePopup
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2016. 01. 07.       은우소프트 전상훈                    최초 작성 
	 *  =======================================================   
	 */
	pageObj.chargerInqirePopup = function() {
		$("#popupFrm").one("submit", function() {
			window.open("", "chargerListPopup", "width=750px,height=760px,toolbar=no,status=no,scrollbars=yes,resizeable=no,menubar=no,left=500,top=50");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/chargerList.do';
	        this.method = 'POST';
	        this.target = 'chargerListPopup';
	    }).trigger("submit");
	};	
	
	/**  
	 * <pre>
	 * 1. 개요 : 담당자 삭제
	 * 2. 처리내용 : 
	 * </pre>
	 * @Function Name : chargerInqireDelete
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2019. 04. 10.       은우소프트 맹경열                    최초 작성 
	 *  =======================================================   
	 */
	pageObj.chargerInqireDelete = function() {
		$('#usrId').val("");
		$('#usrNm').val("");
	};			
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 입찰공고 목록조회 함수를 호출한다.
	 * 2. 입찰종류(#select2) 콤보박스의 change 이벤트를 biding한다.
	 *   2.1 change 이벤트 발생시 changeSelect2 함수를 호출한다.
	 * 3. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {

		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.bidPblancList();
		});
		
		// 부서조회버튼
		$("#searchDeptBtn").on("click", function() {
			pageObj.deptInqirePopup();
		});
		
		//구매담당자 버튼
		$("#chargerBtn").on("click", function() {
			$("#popupFrm input[name='P_USER_NM_S']").val($("#userNm").val());
			$("#popupFrm input[name='setMulti']").val("N");
			$("#popupFrm input[name='tchnChangerYn']").val("N");
			pageObj.chargerInqirePopup();
		});		
		
		//구매담당자 삭제 버튼
		$("#chargerDelBtn").on("click", function() {
			pageObj.chargerInqireDelete();
		});			

		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, [ "P_BID_NM_S", "P_ANNC_NO_S", "P_CONT_SECD_S", "P_SBID_MTCD_S", "P_ANNC_STDT_S", "P_ANNC_ENDT_S", "P_BID_PSCD_S", "P_CHRGR_NM_S"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
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