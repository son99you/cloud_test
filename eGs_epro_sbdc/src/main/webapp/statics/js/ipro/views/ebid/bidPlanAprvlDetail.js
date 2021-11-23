/**
 * 전자입찰 > 입찰계약품의 목록
 *
 * <pre>
 * ebid
 *    |_ bidPlanAprvlDetail.js
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
	var defaultFrm = "updtFrm";

	
	/**  
     * <pre>
     * 1. 개요 : 입찰계획목록 페이지 이동
     * 2. 처리내용 : 
     * 		입찰계획목록 조회 페이지로 이동 한다.
     *  	
     * </pre>
     * @Function Name : bidPlanListInqire
     * @date : 2015. 01. 30.
     * @author : 은우소프트 하성윤
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 01. 30.       은우소프트 하성윤              최초 작성 
     *  =======================================================   
     */
	pageObj.bidPlanAprvlListInqire = function() {
		FwkCmmnUtil.submitForm("menuMoveFrm", "/ebid/bidPlanAprvlList.do");
	};
	
	/**  
	 * <pre>
	 * 1. 개요 : 입찰계획수정 페이지 이동
	 * 2. 처리내용 : 
	 * 		입찰계획 수정 페이지로 이동한다.
	 *  	
	 * </pre>
	 * @Function Name : bidPlanUpdt
	 * @date : 2015. 01. 30.
	 * @author : 은우소프트 하성윤
	 * @history : 
	 *  =======================================================
	 *  변경일             		작성자                     		변경내용  
	 *  =======================================================
	 *  2015. 01. 30.       은우소프트 하성윤              최초 작성 
	 *  =======================================================   
	 */
	pageObj.bidPlanAprvlUpdtForm = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/ebid/bidPlanAprvlUpdtForm.do");
	};
	
	// 임시 (공고)
	pblancSttu = function() {
		$("input[name='P_BID_PROGRS_STTUS_CD']").val("PF20");
		var bidDetailPrstCd = "";
		if($("P_BID_DC_PEO_CD").val() != 'BPNN'){
			bidDetailPrstCd = "PG10";
		}else{
			bidDetailPrstCd = "PH10";
		}
		$("input[name='P_BID_DETAIL_PRST_CD']").val(bidDetailPrstCd);
		
		var jsonData = $("#sttusFrm").serializeObject();
		var actionUrl = "/ebid/bidSttusChange";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			pageObj.bidPlanAprvlListInqire();
		});
		
	};
	
	// 반려 PF10/PE92, 완료 PF10/PE94 (공고)
	pblancSttu2 = function(progrsSttusCd, detailPrstCd) {
		$("input[name='P_BID_PROGRS_STTUS_CD']").val(progrsSttusCd);
		$("input[name='P_BID_DETAIL_PRST_CD']").val(detailPrstCd);
		
		var jsonData = $("#sttusFrm").serializeObject();
		var actionUrl = "/ebid/bidSttusChange";

		FwkCmmnUtil.submitAjax (actionUrl, jsonData
				, function(res) {
			FwkCmmnUtil.submitForm(defaultFrm, "/ebid/bidPlanAprvlDetail.do");
		});
		
	};
	
	
	pageObj.download = function(atchmnflSn){
		$("#downFrm input[name='P_ATCHMNFL_SN']").val(atchmnflSn);
		FwkCmmnUtil.submitForm("downFrm", "/comm/download.do");
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 입찰계획목록조회 함수를 호출한다.
	 * 2. 입찰종류(#select2) 콤보박스의 change 이벤트를 biding한다.
	 *   2.1 change 이벤트 발생시 changeSelect2 함수를 호출한다.
	 * 3. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {
		
		// 목록버튼
		$("#listBtn").on("click", function() {
			pageObj.bidPlanAprvlListInqire();
		});
		
		// 입찰계획 수정 버튼
		$("#updateBtn").on("click", function() {
			pageObj.bidPlanAprvlUpdtForm();
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
