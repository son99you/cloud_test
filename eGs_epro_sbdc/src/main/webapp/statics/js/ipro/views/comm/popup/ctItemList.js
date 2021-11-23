/**
 * 공통 > 계약품목 목록 (팝업)
 *
 * <pre>
 * comm
 *    |_ ctItemList.js
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

	
	// 목록
	pageObj.ctItemList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/ctItemList.do");
	};	
	
	
	// 선택
	pageObj.ctItemListRegist = function() {
	
		var arr_item_no = $([]);	//아이템코드
		var arr_llf_cd = $([]);	    //대분류
		var arr_mlf_cd = $([]);	    //중분류
		var arr_slf_cd = $([]);	    //소분류
		var arr_item_nm = $([]);	//품명
		var arr_stnd_nm = $([]);	//규격
		var arr_item_uncd = $([]);//단위
		
		var ccpyCnt = 0;
		var isChoice = false;
		
		$("input[name='chargerCbx']").each(function(index) {
			if($(this).prop("checked")){
				arr_item_no.push($("input[name='P_ITEM_NO']").eq(index).val());
				arr_llf_cd.push($("input[name='P_LLF_CD_S']").eq(index).val());
				arr_mlf_cd.push($("input[name='P_MLF_CD_S']").eq(index).val());
				arr_slf_cd.push($("input[name='P_SLF_CD_S']").eq(index).val());
				arr_item_nm.push($("input[name='P_ITEM_NM']").eq(index).val());
				arr_stnd_nm.push($("input[name='P_STND_NM_S']").eq(index).val());
				arr_item_uncd.push($("input[name='P_ITEM_UNCD_S']").eq(index).val());
				
				ccpyCnt++;
				isChoice = true;
			}
		});
		
		if(!isChoice){
			alert("[계약품목]을 선택해주세요.") ;
			return ;
		}

		//선택업체수, 업체등록번호, 사업자번호, 업체명, 담당자명, 이메일, 전화번호, 휴대폰번호, 대표자
		window.opener.ccpyCtItemListAdd(ccpyCnt, arr_item_no, arr_llf_cd, arr_mlf_cd, arr_slf_cd, arr_item_nm, arr_stnd_nm, arr_item_uncd);
	
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
		pageObj.ctItemList();
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
			pageObj.ctItemList();
		});
		
		
		// 선택버튼
		$("#choiceBtn").on("click", function() {
			pageObj.ctItemListRegist();
		});
		
		
		// 닫기버튼
		$("#closeBtn").on("click", function() {
			self.close();		
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_ITEM_NO_S", "P_ITEM_NM_S"], function() {
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