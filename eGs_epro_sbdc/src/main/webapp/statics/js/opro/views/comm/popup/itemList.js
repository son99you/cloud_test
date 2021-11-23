(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "searchFrm";
	
	//{data.LLF_NM}','${data.LLF_CD }','${data.SLF_NM}','${data.SLF_CD }','${data.ITEM_NM}','${data.P_ITEM_UNCD }','${data.STND_NM }','${data.ITEM_NO}
	//대분류명, 대분류코드,소분류명, 소분류코드,품명, 품목단위코드,규격,품목코드
	setitemInfo = function(llfNm, llfCd, slfNm, slfCd, itemNm, itemUncd,stndNm,itemNo){
		window.opener.itemListAdd(llfNm, llfCd, slfNm, slfCd, itemNm, itemUncd,stndNm,itemNo);
		self.close();
	};
	
	
	//대분류명, 대분류코드,소분류명, 소분류코드,품명, 품목단위코드,규격,품목코드
	pageObj.itemListRegist = function() {
		
		var arr_llf_nm = $([]);			
		var arr_llf_cd = $([]);			
		var arr_slf_nm = $([]);			
		var arr_slf_cd = $([]);			
		var arr_item_nm = $([]);			
		var arr_item_uncd = $([]);			
		var arr_stnd_nm = $([]);			
		var arr_item_no = $([]);			
		var isChoice = false;					//선택구분
		var itemCnt = 0;							//선택품목수
				
		$("input[name='itemCbx']").each(function(index) {
			if($(this).prop("checked")){
				arr_llf_nm.push($("input[name='P_LLF_NM']").eq(index).val());
				arr_llf_cd.push($("input[name='P_LLF_CD']").eq(index).val());
				arr_slf_nm.push($("input[name='P_SLF_NM']").eq(index).val());
				arr_slf_cd.push($("input[name='P_SLF_CD']").eq(index).val());
				arr_item_nm.push($("input[name='P_ITEM_NM']").eq(index).val());
				arr_item_uncd.push($("input[name='P_ITEM_UNCD']").eq(index).val());
				arr_stnd_nm.push($("input[name='P_STND_NM']").eq(index).val());
				arr_item_no.push($("input[name='P_ITEM_NO']").eq(index).val());
				itemCnt++;
				isChoice = true;
			}
		});
		
		if(!isChoice){
			alert("품목을 선택해 주시기 바랍니다.") ;
			return ;
		}

		//선택품목수, 대분류명, 대분류코드,소분류명, 소분류코드,품명, 품목단위코드,규격,품목코드
		window.opener.itemListAdd(itemCnt, arr_llf_nm,  arr_llf_cd, arr_slf_nm, arr_slf_cd, arr_item_nm, arr_item_uncd, arr_stnd_nm, arr_item_no);
	
		self.close();
	};	
	
	
	pageObj.itemListInqire = function() {
		if($("#P_ITEM_NO_S").val().length >= 2 || $("#P_ITEM_NM_S").val().length >= 2){
			$("#" + defaultFrm + " #P_SEARCH_S").val("Y");
			FwkCmmnUtil.submitForm(defaultFrm, "/opro/comm/popup/itemList.do");
		}else{
			alert("2글자 이상 입력해주세요.");
		}
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
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_ITEM_NO_S","P_ITEM_NM_S"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
		});
		
		//선택버튼
		$("#choiceBtn").on("click", function() {
			pageObj.itemListRegist();
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