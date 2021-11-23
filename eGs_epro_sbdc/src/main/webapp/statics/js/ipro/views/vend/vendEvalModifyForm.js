(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	deleteFn = function(obj){
		$(obj).parent().parent().remove();
	};
	// 평가시 총배점 구하기
	autoEvalSum = function(obj, inx){
		var total_score = 0;
		
		// 각 배점 합산
		$("#evalField_"+inx +" ul input:text").each(function(i){
			total_score += Number($(this).val().trim());
			//alert(i+" :: "+total_score);
		});
		
		// 100 초과 입력 불가
		if( Number(total_score) > 100 ){
			alert("총배점은 100점을 넘을 수 없습니다.");
			$(obj).val("");
			// 현 배점 0점으로 하여 각 배점 다시 합산
			total_score = 0;
			$("#evalField_"+inx +" ul input:text").each(function(i){
				total_score += Number($(this).val().trim());
			});
			$("input[name='total_score']").eq(inx).val(total_score);
		}else{
			$("input[name='total_score']").eq(inx).val(total_score);
		}
	};

	// 값이 0일때 focus 시 입력값 비우기
	hideZero = function(obj){
		if(obj.value == 0){
			obj.value = "";
		}
	};

	// 값이 빈값이면 blur 시 입력값 0으로 나타내기
	showZero = function(obj){
		if(obj.value == ""){
			obj.value = 0;
		}
	};
	/**
	 * 1. 평가분야 SG명 클릭이벤트.
	 */
	toggleTb = function(obj){
		// ▲, ▼ 조절
		if($(obj).parent().next().css("display") == "none"){
			$(obj).children("span").text("▲");
		}else{
			$(obj).children("span").text("▼");
		}
		// slideToggle
		$(obj).parent().next().slideToggle("slow");
		
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
				$("select[name='dyyyy']").append("<option value='"+yyyy5m+"' selected>"+yyyy5m+"</option>");
			}else{
				$("select[name='dyyyy']").append("<option value='"+yyyy5m+"'>"+yyyy5m+"</option>");
			}
			yyyy5m = Number(yyyy5m) + 1;
		}
	};
	/**
	 * 1. 해당년도 선택시 ±5 해주기
	 * @param obj
	 */
	pageObj.yyyySet = function(obj){
		//날짜 셋팅
		var now = new Date();
		
		var yyyy = now.getFullYear(); // 현재년도

		var yyyy5m = Number(yyyy) - 5;
		for(var i=0; i<11; i++){
			if($("input[name='dyyyy_hid']").val() == yyyy5m){
				$("select[name='dyyyy']").append("<option value='"+yyyy5m+"' selected>"+yyyy5m+"</option>");
			}else{
				$("select[name='dyyyy']").append("<option value='"+yyyy5m+"'>"+yyyy5m+"</option>");
			}
			yyyy5m = Number(yyyy5m) + 1;
		}
		
		// 총배점 셋팅
		var total_score = 0;
		$("input[name='total_score']").each(function(inx){
			total_score = 0;
			$("#evalField_"+inx +" ul input:text").each(function(i){
				total_score += Number($(this).val().trim());
			});
			$(this).val(total_score);
		});
			
	};
	pageObj.setEventHandler = function() {
		/**
		 * 1. 년도 설정.
		 */
		pageObj.yyyySet();
		/**
		 * 1. 목록버튼 클릭 이벤트
		 */
		$("#listBtn, #listBtn1").on("click", function() {
			movePage("/vend/vendEvalList.do");
			return false;
		});
		$("#saveBtn").on("click", function() {
			var jsonData = $("#modifyFrm").serializeObject();
			var actionUrl = "/vend/vendEvalModify";
		
			FwkCmmnUtil.submitAjax (actionUrl, jsonData
			, function(res) {	
				alert("저장되었습니다.");
				$("#detailFrm input[name='ev_seq']").val(res.ev_seq);
				$("#detailFrm input[name='dyyyy']").val(res.dyyyy);
				FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalView.do");
			});
			return false;
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