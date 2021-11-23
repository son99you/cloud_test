(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	var defaultFrm = "searchFrm";
	
	fnDetailView = function(dyyyy, evSeq){
		$("#detailFrm input[name='dyyyy']").val(dyyyy);
		$("#detailFrm input[name='ev_seq']").val(evSeq);
		FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalDeptDetail.do");  
	};
	pageObj.vendEvalDeptList = function() {
		FwkCmmnUtil.submitForm(defaultFrm, "/vend/vendEvalDeptList.do");
		
	};
	pageObj.clickPage = function(pageNo) {
		$("#" + defaultFrm + " #P_PAGE_NO").val(pageNo);
		pageObj.vendEvalDeptList();
	};
	/**
	 * 1. 현재년도 기본셋팅
	 */
	pageObj.yearSet = function(){
		var now = new Date();
		
		var yyyy = now.getFullYear(); // 현재년도
		
		var yyyy5m = Number(yyyy) - 5;
		
		if($("#dyyyy_sch_hid").val() != ""){
			yyyy = $("#dyyyy_sch_hid").val();
		}
		
		for(var i=0; i<11; i++){
			if(yyyy == yyyy5m){
				$("select[name='dyyyy_sch']").append("<option value='"+yyyy5m+"' selected>"+yyyy5m+"</option>");
			}else{
				$("select[name='dyyyy_sch']").append("<option value='"+yyyy5m+"'>"+yyyy5m+"</option>");
			}
			yyyy5m = Number(yyyy5m) + 1;
		}
	};
	/**
	 * 1. 해당년도 선택시 ±5 해주기
	 * @param obj
	 */
	yyyySet = function(obj){
		var yyyy = obj.value;
		
		// 목록 검색시
		if($("select[name='dyyyy_sch']")){
			// 셋팅된 년도 지우기
			$("select[name='dyyyy_sch'] option").remove();
			
			// ±5된 년도로 셋팅하기
			var yyyy5m = Number(yyyy) - 5;
			for(var i=0; i<11; i++){
				if(yyyy == yyyy5m){
					$("select[name='dyyyy_sch']").append("<option value='"+yyyy5m+"' selected>"+yyyy5m+"</option>");
				}else{
					$("select[name='dyyyy_sch']").append("<option value='"+yyyy5m+"'>"+yyyy5m+"</option>");
				}
				yyyy5m = Number(yyyy5m) + 1;
			}
		}
		
		// 등록 시 
		if($("select[name='dyyyy']")){
			// 셋팅된 년도 지우기
			$("select[name='dyyyy'] option").remove();
			
			// ±5된 년도로 셋팅하기
			var yyyy5m = Number(yyyy) - 5;
			for(var i=0; i<11; i++){
				if(yyyy == yyyy5m){
					$("select[name='dyyyy']").append("<option value='"+yyyy5m+"' selected>"+yyyy5m+"</option>");
				}else{
					$("select[name='dyyyy']").append("<option value='"+yyyy5m+"'>"+yyyy5m+"</option>");
				}
				yyyy5m = Number(yyyy5m) + 1;
			}
		}
	};
	pageObj.setEventHandler = function() {
		$("#searchBtn").on("click", function() {
			$("#P_PAGE_NO").val("1");
			$("#P_PAGE_SIZE").val("10");
			removeComma();
			pageObj.vendEvalDeptList();
		});
		FwkCmmnUtil.setEnterKeyBinding(defaultFrm, ["dyyyy_sch","ev_name_sch"], function() {
			// EnteKey 이벤트 발생시 현재페이지 다시 조회를 한다.
			pageObj.clickPage(1);
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