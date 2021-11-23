/**
 * 입찰관리 > 입찰참가검토 상세
 *
 * <pre>
 * ebid
 *    |_ bidPartcptSttusDetail.js
 * 
 * </pre>
 * @date : 2015. 02. 16. 오전 11:50:24
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
	var defaultFrm = "menuMoveFrm";
	
	pageObj.returnList = function(){
		pageObj.bidPartcptSttusListInqire();
	};
	
	pageObj.returnDetail = function() {
		FwkCmmnUtil.submitForm("detailReloadFrm", "/ebid/bidPartcptSttusDetail.do");
	};
	
	returnDetail = function(){
		FwkCmmnUtil.submitForm("detailReloadFrm", "/ebid/bidPartcptSttusDetail.do");
	};
	
	/**  
     * <pre>
     * 1. 개요 : 입찰참가현황목록 페이지 이동
     * 2. 처리내용 : 
     * 		- 입찰참가현황목록 조회 페이지로 이동 한다.
     *  	
     * </pre>
     * @Function Name : bidPartcptSttusListInqire
     * @date : 2015. 02. 27.
     * @author : 은우소프트 손연우
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 01. 27.       은우소프트 손연우              최초 작성 
     *  =======================================================   
     */
	pageObj.bidPartcptSttusListInqire = function() {
		FwkCmmnUtil.submitForm("menuMoveFrm", "/ebid/bidPartcptSttusList.do");
	};
	/**  
	 * <pre>
	 * 1. 개요 : 입찰포기신청서 팝업 
	 * 2. 처리내용 : 
	 * 		- 입찰포기신청서 팝업 실행
	 *  	
	 * </pre>
	 * @Function Name : bidPartcptEntrpsRegistForm
	 * @date : 2015. 02. 16
	 * @author : 은우소프트 손연우
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 02. 16.       은우소프트 손연우              최초 작성 
	 *  =======================================================   
	 */

	bidAbandnReqstdocInqire = function(entrpsRegistNo) {
		$("#popupFrm input[name='P_VEND_REG_NO']").val(entrpsRegistNo);
		$("#popupFrm").one("submit", function() {
			window.open("", "bidAbandnReqstdocInqire", "width=740px, height=350px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/bidAbandnReqstdocInqire.do';
	        this.method = 'POST';
	        this.target = 'bidAbandnReqstdocInqire';
	    }).trigger("submit");
		
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 입찰보증정보조회 팝업
	 * 2. 처리내용 : 
	 * 		- 입찰보증정보조회 팝업 실행
	 *  	
	 * </pre>
	 * @Function Name : bidAssrncInfoInqire
	 * @date : 2015. 03. 26
	 * @author : 은우소프트 손연우
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 03. 26.       은우소프트 손연우              최초 작성 
	 *  =======================================================   
	 */
	
	bidAssrncInfoInqire = function(entrpsRegistNo) {
		$("#popupFrm input[name='P_VEND_REG_NO']").val(entrpsRegistNo);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "bidAssrncInfoInqire", "width=740px, height=620px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/bidAssrncInfoInqire.do';
	        this.method = 'POST';
	        this.target = 'bidAssrncInfoInqire';
	    }).trigger("submit");
		
	};
	
	
	// 보증정보 상세 팝업
	bidAssrncInfoDetail = function(entrpsRegistNo) {
		$("#popupFrm input[name='P_VEND_REG_NO']").val(entrpsRegistNo);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "bidAssrncInfoDetail", "width=740px, height=620px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/bidAssrncInfoDetail.do';
			this.method = 'POST';
			this.target = 'bidAssrncInfoDetail';
		}).trigger("submit");
		
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 입찰참가신청서 팝업 
	 * 2. 처리내용 : 
	 * 		- 입찰참가신청서 팝업 실행
	 *  	
	 * </pre>
	 * @Function Name : bidPartcptReqstdocInqire
	 * @date : 2015. 02. 27
	 * @author : 은우소프트 손연우
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 02. 27.       은우소프트 손연우              최초 작성 
	 *  =======================================================   
	 */
		
	bidPartcptReqstdocInqire = function(entrpsRegistNo) {
		$("#popupFrm input[name='P_BID_TPI_SECD']").val("OP01");
		$("#popupFrm input[name='P_BID_SBMT_FSCD']").val("DO01");
		$("#popupFrm input[name='P_VEND_REG_NO']").val(entrpsRegistNo);
		$("#popupFrm").one("submit", function() {
			window.open("", "bidPartcptReqstdocInqire", "width=880px, height=800px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=400,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/bidPartcptReqstdocInqire.do';
	        this.method = 'POST';
	        this.target = 'bidPartcptReqstdocInqire';
	    }).trigger("submit");
		
	}; 
	
	/**  
	 * <pre>
	 * 1. 개요 : 공동수급협정서 팝업 
	 * 2. 처리내용 : 
	 * 		- 공동수급협정서 팝업 실행
	 *  	
	 * </pre>
	 * @Function Name : copertnSpldmdTrtyOfeInqire
	 * @date : 2015. 04. 16
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 04. 16.       은우소프트 하성윤              최초작성
	 *  =======================================================   
	 */
	 
	copertnSpldmdTrtyOfeInqire = function(entrpsRegistNo) {
		
		$("#popupFrm input[name='P_VEND_REG_NO']").val(entrpsRegistNo);
		
		$("#popupFrm").one("submit", function() {
			window.open("", "copertnSpldmdTrtyOfeInqire", "width=740px, height=650px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=400,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/copertnSpldmdTrtyOfeInqire.do';
			this.method = 'POST';
			this.target = 'copertnSpldmdTrtyOfeInqire';
		}).trigger("submit");
		
	}; 
	
	
	/**  
	 * <pre>
	 * 1. 개요 : 입찰공고문 상세 팝업
	 * 2. 처리내용 : 
	 * 		- 입찰공고문 상세 팝업 페이지로 이동한다.
	 *  	
	 * </pre>
	 * @Function Name : bidWrtancDetailPopup
	 * @date : 2015. 06. 05.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 06. 05.       은우소프트 전상훈              최초 작성 
	 *  =======================================================   
	 */
	pageObj.bidWrtancDetailPopup = function() {
		$("#bidWrtancDetailPopupFrm").one("submit", function() {
			window.open("", "bidWrtancDetailPopup", "width=1060px, height=780px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/comm/popup/bidWrtancDetail.do';
	        this.method = 'POST';
	        this.target = 'bidWrtancDetailPopup';
	    }).trigger("submit");
	};
	
	/**  
     * <pre>
     * 1. 개요 : 유찰 사유 등록 팝업 페이지 이동
     * 2. 처리내용 : 
     * 		- 유찰 사유 등록 팝업 페이지로 이동 한다.
     *  	
     * </pre>
     * @Function Name : fibRegistFrom
     * @date : 2015. 06. 26.
     * @author : 은우소프트 전상훈
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 06. 26.       은우소프트 전상훈              최초 작성 
     *  =======================================================   
     */

	pageObj.fibRegistForm = function() {
		$("#fibFrm").one("submit", function() {
			window.open("", "fibRegistForm", "width=700px, height=450px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=200");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/fibRegistForm.do';
	        this.method = 'POST';
	        this.target = 'fibRegistForm';
	    }).trigger("submit");
		
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 관심입찰 업체 목록 팝업 화면 이동
	 * 2. 처리내용 : 
	 * 		관심입찰 업체 목록 팝업 화면으로 이동 한다.
	 * </pre>
	 * @Function Name : intrstBidEntrpsPopup
	 * @date : 2016. 01. 05.
	 * @author : 은우소프트 전상훈
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2016. 01. 05.       은우소프트 전상훈              최초 작성 
	 *  =======================================================   
	 */
	intrstBidEntrpsPopup = function() {
		$("#intrstBidEntrpsPopupFrm").one("submit", function() {
			window.open("", "intrstBidEntrpsPopupFrm", "width=540px, height=400px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/intrstBidEntrpsList.do';
	        this.method = 'POST';
	        this.target = 'intrstBidEntrpsPopupFrm';
	    }).trigger("submit");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 사전판단 팝업 이동
	 * 2. 처리내용 : 
	 * 		사전판단 팝업 이동
	 * </pre>
	 * @Function Name : intrstBidEntrpsPopup
	 * @date : 2019. 02. 18.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2019. 02. 18.       은우소프트 맹경열              최초 작성 
	 *  =======================================================   
	 */	
	dfDcdRegist = function(vendRegNo) {
		$("#popupFrm input[name='P_VEND_REG_NO']").val(vendRegNo);
		$("#popupFrm").one("submit", function() {
			window.open("", "dfDcdRegistPopup", "width=580px, height=400px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/entrpsProperExmntRegistForm.do';
	        this.method = 'POST';
	        this.target = 'dfDcdRegistPopup';
	    }).trigger("submit");
	};
	
	
	/**  
	 * <pre>
	 * 1. 개요 : 참가검토 상세 팝업 이동
	 * 2. 처리내용 : 
	 * 		참가검토 상세 팝업 이동
	 * </pre>
	 * @Function Name : dfDcdDetail
	 */
	dfDcdDetail = function(vendRegNo){
		$("#popupFrm input[name='P_BID_TPI_SECD']").val("OP05");
		$("#popupFrm input[name='P_BID_SBMT_FSCD']").val("DO02");		
		$("#popupFrm input[name='P_VEND_REG_NO']").val(vendRegNo);
		$("#popupFrm").one("submit", function() {
			window.open("", "dfDcdDetailPopup", "width=580, height=400px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			//this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/entrpsProperPrprcDetail.do';
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/entrpsProperExmntDetail.do';
	        this.method = 'POST';
	        this.target = 'dfDcdDetailPopup';
	    }).trigger("submit");
	};

	/**  
	 * <pre>
	 * 1. 개요 : 공동업체 보기 팝업
	 * 2. 처리내용 : 
	 * 		사전판단 팝업 이동
	 * </pre>
	 * @Function Name : bidAssoInfoInqire
	 * @date : 2019. 05. 09.
	 * @author : 은우소프트 맹경열
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2019. 05. 09.       은우소프트 맹경열              최초 작성 
	 *  =======================================================   
	 */		
	bidAssoInfoInqire = function(vendRegNo) {
		$("#popupFrm input[name='P_VEND_REG_NO']").val(vendRegNo);
		$("#popupFrm").one("submit", function() {
			window.open("", "entrpsAssoInfoPopup", "width=800px, height=600px, toolbar=no, status=no, scrollbars=yes, menubar=no,left=500,top=100");
			this.action = FwkCmmnUtil['contextPath']+'/ebid/popup/copertnSpldmdTrtyOfeInqire.do';
	        this.method = 'POST';
	        this.target = 'entrpsAssoInfoPopup';
	    }).trigger("submit");		
	};
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 목록버튼(#listBtn) 의 click 이벤트를 binding 한다.
	 *   - 목록버튼에 click 이벤트 발생시 입찰설명회목록조회 함수를 호출한다.
	 * 2. 유찰버튼(#fibBtn) 의 click 이벤트를 binding 한다.
	 * 	  - 유찰버튼에 click 이벤트 발생시 입찰설명회목록조회 함수를 호출한다.
	 *
	 */	
	pageObj.setEventHandler = function() {

		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.bidPartcptSttusListInqire();
		});
		// 유찰버튼
		$("#fibBtn").on("click", function() {
			pageObj.fibRegistForm();
		});
		
		//입찰공고문 팝업 버튼
		$("#detailBtn").on("click", function() {
			pageObj.bidWrtancDetailPopup();
		});
		
		// 관심입찰업체보기
		$("#intrstBidEntrpsBtn").on("click", function() {
			intrstBidEntrpsPopup();
		});

	};

	/**
	 * window load
	 *
	 */
	$(function(){
		pageObj.setEventHandler();
//		if($("#sevrTime").val() >= $("#P_BIDC_SBMT_ENDT").val()){
//			$("#sanctnBtn").css("display","");
//		}else{
//			$("#sanctnBtn").css("display","none");
//		}
	});
})();

