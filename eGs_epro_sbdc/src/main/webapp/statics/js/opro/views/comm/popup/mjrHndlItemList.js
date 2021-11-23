/**
 * 공통 > 주요취급품목 조회
 *
 * <pre>
 * comm 
 *  |_ popup
 *    |_ mjrHndlItemList.js
 * 
 * </pre>
 * @date : 2018. 02. 20. 오후 02:36:51
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

	// 목록
	pageObj.mjrHndlItemList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/opro/comm/popup/mjrHndlItemList.do");
	};
	

	/**  
	 * <pre>
	 * 1. 개요 : 부모창에 선택된 사용자 값 전달
	 * 2. 처리내용 : 
	 * 		부모창에 선택된 사용자 값 전달한다
	 *  	
	 * </pre>
	 */
	setInfo = function(itemNo, itemNm, llfCd, mlfCd, slfCd, dlfCd){
		var btnId = $("#btnId").val();
		
		//console.log("btnId ==> " + btnId);
		//console.log("itemNo ==> " + itemNo);
		//console.log("itemNm ==> " + itemNm);
		//console.log("llfCd ==> " + llfCd);
		//console.log("mlfCd ==> " + mlfCd);
		//console.log("slfCd ==> " + slfCd);
		//console.log("dlfCd ==> " + dlfCd);

		if(btnId == ""){

			if($("#itemNo",opener.document)){
				$("#itemNo",opener.document).val(itemNo);
			};

			if($("#itemNm",opener.document)){
				$("#itemNm",opener.document).val(itemNm);
			};
			
			if($("#llfCd",opener.document)){
				$("#llfCd",opener.document).val(llfCd);
			};
			
			if($("#mlfCd",opener.document)){
				$("#mlfCd",opener.document).val(mlfCd);
			};
			
			if($("#slfCd",opener.document)){
				$("#slfCd",opener.document).val(slfCd);
			};
			
			if($("#dlfCd",opener.document)){
				$("#dlfCd",opener.document).val(dlfCd);
			};
			
		}else{
		
			if($("#itemNo"+btnId,opener.document)){
				$("#itemNo"+btnId,opener.document).val(itemNo);
			};
			
			if($("#itemNm"+btnId,opener.document)){
				$("#itemNm"+btnId,opener.document).val(itemNm);
			};
			
			if($("#llfCd"+btnId,opener.document)){
				$("#llfCd"+btnId,opener.document).val(llfCd);
			};
			
			if($("#mlfCd"+btnId,opener.document)){
				$("#mlfCd"+btnId,opener.document).val(mlfCd);
			};
			
			if($("#slfCd"+btnId,opener.document)){
				$("#slfCd"+btnId,opener.document).val(slfCd);
			};
			
			if($("#dlfCd"+btnId,opener.document)){
				$("#dlfCd"+btnId,opener.document).val(dlfCd);
			};
			
		}
		
		self.close();
	};
	
	
	/**  
	 * <pre>
	 * 1. 개요 : 부모창에 체크 선택된 사용자 값 전달
	 * 2. 처리내용 : 
	 * 		부모창에 체크 선택된 사용자 값 전달한다
	 * </pre>
	 */
	pageObj.mjrHndlItemRegist = function(){
		
		var arr_item_no = $([]);	// 품목번호
		var arr_item_nm = $([]);	// 품목명
		var arr_llf_cd = $([]);	    // 대분류
		var arr_mlf_cd = $([]);	    // 중분류
		var arr_slf_cd = $([]);	    // 소분류
		var arr_dlf_cd = $([]);	    // 세분류
		
		var ccpyCnt = 0;
		var isChoice = false;
		
		$("input[name='itemCbx']").each(function(index) {
			if($(this).prop("checked")){
				arr_item_no.push($("input[name='P_ITEM_NO']").eq(index).val());
				arr_item_nm.push($("input[name='P_ITEM_NM']").eq(index).val());
				arr_llf_cd.push($("input[name='P_LLF_CD']").eq(index).val());
				arr_mlf_cd.push($("input[name='P_MLF_CD']").eq(index).val());
				arr_slf_cd.push($("input[name='P_SLF_CD']").eq(index).val());
				arr_dlf_cd.push($("input[name='P_DLF_CD']").eq(index).val());
				
				ccpyCnt++;
				isChoice = true;
			}
		});
		
		
		if(!isChoice){
			alert("품목을 선택해주시기 바랍니다.") ;
			return ;
		}
		
		window.opener.mjrHndlItemListAdd(ccpyCnt, arr_item_no, arr_item_nm, arr_llf_cd, arr_mlf_cd, arr_slf_cd, arr_dlf_cd);	

		self.close();
	};
	
	
	/**  
     * <pre>
     * 1. 개요 : 페이지번호 클릭
     * 2. 처리내용 : 
     * 		- 선택된 페이지번호를 set 한다.
     *  	- 주요취급품목 목록조회를 호출한다.
     * </pre>
     */
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.mjrHndlItemList();
	};
	
	
	/**
	 * 페이지내에서 필요한 항목의 이벤트를 바인딩한다.
	 * 1. 조회버튼(#searchBtn) 의 click 이벤트를 binding 한다.
	 *   - 조회버튼에 click 이벤트 발생시 해당 함수를 호출한다.
	 * 2. 닫기버튼(#closeBtn) 의 click 이벤트를 binding 한다.
	 *   - 닫기버튼에 click 이벤트 발생시 팝업을 닫는다.
	 * 3. Form 안에서 필요한 필드에 대해 엔터키 이벤트를 바인딩한다.
	 *   - 엔터키 이벤트 발생시 pageObj.clickPage 함수를 호출한다.
	 */	
	pageObj.setEventHandler = function() {

		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.mjrHndlItemList();			
		});
		
		
		//선택버튼
		$("#choiceBtn").on("click", function() {
			pageObj.mjrHndlItemRegist();
		});
		
		
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();
		});
		
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_ITEM_NM_S"], function() {
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