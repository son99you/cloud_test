(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	
	scoreSet = function(){
		var total_score = 0;
		$("input[name='total_score']").each(function(inx){
			total_score = 0;
			$("#evalField_"+inx +" ul input:text").each(function(i){
				total_score += Number($(this).val().trim());
			});
			$(this).val(total_score);
		});
	};
	movePage = function(url) {
		FwkCmmnUtil.submitForm("detailFrm", url);  
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
	pageObj.setEventHandler = function() {
		scoreSet();
		/**
		 * 1. 목록버튼 클릭 이벤트
		 */
		$("#listBtn").on("click", function() {
			movePage("/vend/vendEvalList.do");
			return false;
		});
		$("#modifyBtn").on("click", function() {
			FwkCmmnUtil.submitForm("modifyFrm", "/vend/vendEvalModifyForm.do");  
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