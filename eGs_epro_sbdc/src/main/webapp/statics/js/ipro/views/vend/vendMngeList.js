(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	/**
	 * Default Form Name
	 */
	var defaultFrm = "listFrm";
	

	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	
	//상세 
	vendMngeDetail = function (entrpsRegistNo){
		$("#detailFrm input[name='P_VEND_REG_NO']").val(entrpsRegistNo);
		FwkCmmnUtil.submitForm("detailFrm", "/vend/vendMngeDetail.do");
	};
	
	
	pageObj.vendMngeListInqire = function() {
		FwkCmmnUtil.submitForm("defaultFrm", "/vend/vendMngeList.do");
	};
	
	ccnSpheEvent = function(obj, ccnSphe){
		if($(obj).prop("checked")){
			if(ccnSphe == "P_CCN_SPHE_1_S"){
				$("input[name=P_CCN_SPHE_1_S]").val("Y");
			}
			
			if(ccnSphe == "P_CCN_SPHE_2_S"){
				$("input[name=P_CCN_SPHE_2_S]").val("Y");
			}
			
			if(ccnSphe == "P_CCN_SPHE_3_S"){
				$("input[name=P_CCN_SPHE_3_S]").val("Y");
			}
			
			if(ccnSphe == "P_CCN_SPHE_4_S"){
				$("input[name=P_CCN_SPHE_4_S]").val("Y");
			}
			
			if(ccnSphe == "P_CCN_SPHE_5_S"){
				$("input[name=P_CCN_SPHE_5_S]").val("Y");
			}
		}else{
			if(ccnSphe == "P_CCN_SPHE_1_S"){
				$("input[name=P_CCN_SPHE_1_S]").val("N");
			}
			
			if(ccnSphe == "P_CCN_SPHE_2_S"){
				$("input[name=P_CCN_SPHE_2_S]").val("N");
			}
			
			if(ccnSphe == "P_CCN_SPHE_3_S"){
				$("input[name=P_CCN_SPHE_3_S]").val("N");
			}
			
			if(ccnSphe == "P_CCN_SPHE_4_S"){
				$("input[name=P_CCN_SPHE_4_S]").val("N");
			}
			
			if(ccnSphe == "P_CCN_SPHE_5_S"){
				$("input[name=P_CCN_SPHE_5_S]").val("N");
			}
		}
			
	};
	
	
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.vendMngeListInqire();
	};
	
	pageObj.setEventHandler = function() {
		
		// 조회버튼
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			pageObj.vendMngeListInqire();			
		});
		
		// 1. Form 안에서 필요한 Fields 에 엔터키 이벤트 핸들러를 등록한다.
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["P_PRTNR_KOREAN_NM_S","P_BIZRNO_S"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
		});
		
	};

	/**
	 * window load
	 *
	 */
	$(function() {		
		pageObj.setEventHandler();//페이지 온로드(맨처음들어올 때 스크립트로 돌게끔)
		
		
		alert("12");
		
		
		if($("#CCN_SPHE_1").val() == 'Y'){
			$("#P_CCN_SPHE_1_S").prop("checked", true);
		}
		if($("#CCN_SPHE_2").val() == 'Y'){
			$("#P_CCN_SPHE_2_S").prop("checked", true);
		}
		if($("#CCN_SPHE_3").val() == 'Y'){
			$("#P_CCN_SPHE_3_S").prop("checked", true);
		}
		if($("#CCN_SPHE_4").val() == 'Y'){
			$("#P_CCN_SPHE_4_S").prop("checked", true);
		}
		if($("#CCN_SPHE_5").val() == 'Y'){
			$("#P_CCN_SPHE_5_S").prop("checked", true);
		}
		
	});
	
})();