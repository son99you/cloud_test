/**
 * 조달공통 > 업체 조회
 *
 * <pre>
 * elbi
 *    |_ iepPrcmPopupCcpyList.js
 * 
 * </pre>
 * @date : 2018. 02. 20.
 * @version : 1.0
 * @author : 은우소프트 맹경열
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

	pageObj.bsnsListInqire = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/prcm/popup/bsnsList.do");
	};	
	
	pageObj.bsnsListRegist = function() {
	
		var arr_bsns_no = $([]);				//사업번호
		var arr_bsns_nm = $([]);			//사업명
		var isChoice = false;				//선택구분
		var bsnsCnt = 0;						//선택업체수
		
		$("#bsnsChoiseAllCbx").remove();
		
		$("input[name='bsnsChoiseCbx']").each(function(index) {
			if($(this).prop("checked")){
				arr_bsns_no.push($("input[name='P_BSNS_NO']").eq(index).val());
				arr_bsns_nm.push($("input[name='P_BSNS_NM']").eq(index).val());
				
				bsnsCnt++;
				isChoice = true;
			}
		});
		
		if(!isChoice){
			alert("사업을 선택해 주시기 바랍니다.") ;
			return ;
		}
		//선택사업수, 사업번호, 사업명
		window.opener.bsnsListAdd(bsnsCnt, arr_bsns_no, arr_bsns_nm);
		//$(opener.location).attr("href","javascript:ccpyListAdd(ccpyCnt, arr_entrps_regist_no, arr_entrps_se_nm, arr_entrps_nm, arr_rprsntv_nm, arr_bizr_no);");
	
		self.close();
	};
	
	bsnsRegist = function(obj) {
		var bsns_no = $(obj).children().find("input[name='P_BSNS_NO']").val();
		var bsns_nm = $(obj).children().find("input[name='P_BSNS_NM']").val();
		// 사업번호, 사업명
		window.opener.bsnsAdd(bsns_no, bsns_nm);
		
		self.close();
	};
	
	/**  
     * <pre>
     * 1. 개요 : 페이지번호 클릭
     * 2. 처리내용 : 
     * 		- 선택된 페이지번호를 set 한다.
     *  	- 입찰설명회 목록조회를 호출한다.
     * </pre>
     * @Function Name : clickPage
     * @date : 2018. 02. 20
     * @author : 은우소프트 맹경열
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2018. 02. 20.       은우소프트 맹경열              최초 작성 
     *  =======================================================   
     */
	pageObj.clickPage = function(pageNo) {
		
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.bsnsListInqire();
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 해당 함수를 호출한다.
	 * 2. 선택버튼(#choiceBtn) 의 click 이벤트를 binding 한다.
	 *   - 선택 버튼에 click 이벤트 발생시 팝업을 닫는다.
	 * 3. 닫기버튼(#closeBtn) 의 click 이벤트를 binding 한다.
	 *   - 닫기버튼에 click 이벤트 발생시 팝업을 닫는다.
	 * 4. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {

		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.bsnsListInqire();
		});
		//선택버튼
		$("#choiceBtn").on("click", function() {
			pageObj.bsnsListRegist();
		});
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();		
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_BSNS_NO_S","select2"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
		});
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_BSNS_NM_S","select2"], function() {
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