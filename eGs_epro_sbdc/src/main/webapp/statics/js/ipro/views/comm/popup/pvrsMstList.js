/**
 * 조달공통 > 업체 조회
 *
 * <pre>
 * elbi
 *    |_ iepPrcmPopupCcpyList.js
 * 
 * </pre>
 * @date : 2015. 02. 26. 오후 02:36:51
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

	pageObj.ccpyListInqire = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/pvrsMstList.do");
	};	
	
	pageObj.ccpyListRegist = function() {
		
		var arr_pvct_rsn_no_list = $([]);	//수의계약번호
		var arr_pvct_rsnm_list = $([]);	//수의계약사유명
		var arr_pvct_secd_list = $([]);	//수의계약사유명
		
		var isChoice = false;				//선택구분
		var ccpyCnt = 0;						//선택업체수
	
		$("input[name='chargerCbx']").each(function(index) {
			if($(this).prop("checked")){
				arr_pvct_rsn_no_list.push($("input[name='P_PVCT_RSN_NO']").eq(index).val());
				arr_pvct_rsnm_list.push($("input[name='P_PVCT_RSNM']").eq(index).val());
				arr_pvct_secd_list.push($("input[name='P_PVCT_RSN_SECD']").eq(index).val());
				
				ccpyCnt++;
				isChoice = true;
			}
		});
		
		if(!isChoice){
			alert("업체를 선택해 주시기 바랍니다.") ;
			return ;
		}

		
		//선택업체수, 업체등록번호, 업체구분명, 업체명, 대표자명, 사업자번호, 전화번호
		window.opener.ccpyPvrsListAdd(ccpyCnt, arr_pvct_rsn_no_list, arr_pvct_rsnm_list, arr_pvct_secd_list);
	
		self.close();
	};
	
	ccpyRegist = function(entrps_regist_no, entrps_nm, rprsntv_nm, bizr_no, telno, adres, bizr_no_F, detailAdres ) {
		// 업체등록번호, 업체명, 대표자명, 사업자번호, 업체전화번호, 주소, 사업자번호 포멧
		window.opener.ccpyAdd(entrps_regist_no, entrps_nm, rprsntv_nm, bizr_no, telno, adres, bizr_no_F, detailAdres);
		
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
     * @date : 2015. 02. 26
     * @author : 은우소프트 임동일
     * @history : 
     *  =======================================================
     *  변경일             		작성자                     		변경내용  
     *  =======================================================
     *  2015. 02. 26.       은우소프트 임동일              최초 작성 
     *  =======================================================   
     */
	pageObj.clickPage = function(pageNo) {
		
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.ccpyListInqire();
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
			pageObj.ccpyListInqire();
		});
		//선택버튼
		$("#choiceBtn").on("click", function() {
			pageObj.ccpyListRegist();
		});
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();		
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_VEND_NM_S"], function() {
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