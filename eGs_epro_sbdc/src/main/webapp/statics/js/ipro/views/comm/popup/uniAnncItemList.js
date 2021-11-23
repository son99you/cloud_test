(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "searchFrm";
	
	pageObj.prdlstListRegist = function() {
		
		var arr_bid_wait_no = $([]);				//품목코드
		var itemCnt = 0;
		
		$("input[name='anncChoiseCbx']").each(function(index) {
			if($(this).prop("checked")){
//				arr_bid_wait_no.push($("input[name='P_BID_WAIT_NO']").eq(index).val());
				arr_bid_wait_no.push($(this).next().val());
				
				itemCnt++;
				isChoice = true;
			}
		});
		
		if(!isChoice){
			alert("품목을 선택해 주시기 바랍니다.") ;
			return ;
		}

		//선택품목수, 품목코드, 대분류코드, 중분류코드, 소분류코드, 품목명, 규격명 , 단위코드, 단위명
		window.opener.prdlstListAdd(itemCnt, arr_bid_wait_no);
	
		self.close();
	};	
	
	
	pageObj.itemListInqire = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/uniAnncItemList.do");
	};
	
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.itemListInqire();
	};
	
	pageObj.setEventHandler = function() {
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.itemListInqire();			
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_ITMS_NAME_S"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
		});
		
		//선택버튼
		$("#choiceBtn").on("click", function() {
			pageObj.prdlstListRegist();
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