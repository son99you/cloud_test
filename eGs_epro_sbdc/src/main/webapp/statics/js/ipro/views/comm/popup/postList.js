(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "searchFrm";
	//BUILD_MAN_N,ZIPCODE,BEOBJEONGDONG,JIBEON_NO_BON,DOROMYEONG,GEONMUL_NO_BON,BUILD_NAME,ADDRESS,ADDRESS1
	setPostInfo = function(buildManN, zipCode, beobjeongdong, jibeonNoBon, doromyeong, geonmulNoBon,buildName,address,address1){
		window.opener.postListAdd(buildManN, zipCode, beobjeongdong, jibeonNoBon, doromyeong, geonmulNoBon,buildName,address,address1);
		self.close();
	};
	
	
	//건물관리번호, 우편번호, 읍면동명, 지번본번_번지, 도로명, 건물본번, 건뭉명, 사용주소, 사용세부주소
	pageObj.postListRegist = function() {
		
		var arr_build_man_n = $([]);			
		var arr_zipcode = $([]);			
		var arr_beobjeongdong = $([]);			
		var arr_jibeon_no_bon = $([]);			
		var arr_doromyeong = $([]);			
		var arr_geonmul_no_bon = $([]);			
		var arr_build_name = $([]);			
		var arr_address = $([]);			
		var arr_address1 = $([]);			
		var isChoice = false;					//선택구분
		var postCnt = 0;							//선택품목수
				
		$("input[name='postCbx']").each(function(index) {
			if($(this).prop("checked")){
				arr_build_man_n.push($("input[name='P_BUILD_MAN_N']").eq(index).val());
				arr_zipcode.push($("input[name='P_ZIPCODE']").eq(index).val());
				arr_beobjeongdong.push($("input[name='P_BEOBJEONGDONG']").eq(index).val());
				arr_jibeon_no_bon.push($("input[name='P_JIBEON_NO_BON']").eq(index).val());
				arr_doromyeong.push($("input[name='P_DOROMYEONG']").eq(index).val());
				arr_geonmul_no_bon.push($("input[name='P_GEONMUL_NO_BON']").eq(index).val());
				arr_build_name.push($("input[name='P_BUILD_NAME']").eq(index).val());
				arr_address.push($("input[name='P_ADDRESS']").eq(index).val());
				arr_address1.push($("input[name='P_ADDRESS1']").eq(index).val());
				
				postCnt++;
				isChoice = true;
			}
		});
		
		if(!isChoice){
			alert("주소를 선택해 주시기 바랍니다.") ;
			return ;
		}

		window.opener.postListAdd(postCnt, arr_build_man_n,  arr_zipcode, arr_beobjeongdong, arr_jibeon_no_bon, arr_doromyeong, arr_geonmul_no_bon, arr_build_name, arr_address,arr_address1);
	
		self.close();
	};	
	
	
	pageObj.postListInqire = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/comm/popup/postList.do");
	};
	
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.postListInqire();
	};
	
	pageObj.setEventHandler = function() {
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.postListInqire();			
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_BEOBJEONGDONG_S","P_JIBEON_NO_BON_S","P_BUILD_NAME_S"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
		});
		
		//선택버튼
		$("#choiceBtn").on("click", function() {
			pageObj.postListRegist();
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