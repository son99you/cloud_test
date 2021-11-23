(function() {

	/**
	 * Default Constructor
	 */
	pageObj = {};
	fnDetailEvalView = function( dyyyy, ev_seq, vendor_code, sg_code, groupNo ) {
		
		$("#detailFrm input[name='dyyyy']").val(dyyyy);
		$("#detailFrm input[name='ev_seq']").val(ev_seq);
		$("#detailFrm input[name='vendor_code']").val(vendor_code);
		$("#detailFrm input[name='sg_code']").val(sg_code);
		$("#detailFrm input[name='P_ATCHMNFL_GROUP_NO']").val(groupNo);
		
		FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalDetailEvalView.do"); 
	};
	addSave = function() {
	
	
		if(!confirm("하반기평가 업체 추가저장을 하시겠습니까?")){
			return false;
		}
		
		var istVenCode = "";
		var istSgCode = "";
		var istSgName = "";
		
		$("input[name='ev_chk']").each(function(inx){
			if($(this).is(":checked")){
				if(istVenCode == ""){
					istVenCode = $(this).next().val();
				}else{
					istVenCode = istVenCode + "#" + $(this).next().val();
				}
				
				if(istSgCode == ""){
					istSgCode = $(this).next().next().val();
				}else{
					istSgCode = istSgCode + "#" + $(this).next().next().val();
				}
				
				if(istSgName == ""){
					istSgName = $(this).next().next().next().val();
				}else{
					istSgName = istSgName + "#" + $(this).next().next().next().val();
				}
			}
		});
		
		$("input[name='istVenCode']").val(istVenCode);
		$("input[name='istSgCode']").val(istSgCode);
		$("input[name='istSgName']").val(istSgName);
		
		$("#detailFrm input[name='add']")
		FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalProceed.do"); 
	
	};

	search = function() {
		FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalVenComp2Add.do"); 
	};

	// 평가완료
	fnComplate = function(gb,title) {
		var cfmsg =title;
		
		if(!confirm(cfmsg+" 하시겠습니까?")){
			return false;
		}
		
		$("#detailFrm input[name='ev_state']").val(gb);
		
		FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalComplate.do"); 
	};

	fnProceed = function(){
		if(!confirm("평가진행 하시겠습니까?")){
			return false;
		}
		
		var istVenCode = "";
		var istSgCode = "";
		var istSgName = "";
		
		$("input[name='ev_chk']").each(function(inx){
			if($(this).is(":checked")){
				if(istVenCode == ""){
					istVenCode = $(this).next().val();
				}else{
					istVenCode = istVenCode + "#" + $(this).next().val();
				}
				
				if(istSgCode == ""){
					istSgCode = $(this).next().next().val();
				}else{
					istSgCode = istSgCode + "#" + $(this).next().next().val();
				}
				
				if(istSgName == ""){
					istSgName = $(this).next().next().next().val();
				}else{
					istSgName = istSgName + "#" + $(this).next().next().next().val();
				}
			}
		});
		
		$("#detailFrm input[name='istVenCode']").val(istVenCode);
		$("#detailFrm input[name='istSgCode']").val(istSgCode);
		$("#detailFrm input[name='istSgName']").val(istSgName);
		
		FwkCmmnUtil.submitForm("detailFrm", "/vend/vendEvalProceed.do"); 
	};
	
	movePage = function(url,obj) {
		$("#detailFrm input[name='dept']").val($(obj).children().eq(1).html());
		$("#detailFrm input[name='name']").val($(obj).children().eq(2).html());
		$("#detailFrm input[name='type']").val($(obj).children().eq(3).html());
		FwkCmmnUtil.submitForm("detailFrm", url);  
	};
	pageObj.setEventHandler = function() {
		/**
		 * 1. 목록버튼 클릭 이벤트
		 */
		$("#listBtn").on("click", function() {
			movePage("/vend/vendEvalList.do");
			return false;
		});
		/**
		 * 1. 삭제버튼 클릭 이벤트
		 */
		$("#deleteBtn").on("click", function() {
			if(confirm("삭제하시겠습니까?")){
				movePage("/vend/vendEvalList.do");
				return false;
			}
		});
		/**
		 * 1.평가진행 버튼
		 */
		$("#btn1").on("click", function() {
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "평가진행을 "))){
				return false;
			}
			movePage("/vend/vendEvalList.do");
		});
		/**
		 * 1. 상반기완료 버튼
		 */
		$("#btn2").on("click", function() {
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.002", "상반기현업평가", "완료"))){
				return false;
			}
			movePage("/vend/vendEvalList.do");
		});
		/**
		 * 1. 하반기평가업체추가 버튼
		 */
		$("#btn3").on("click", function() {
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "하반기평가업체추가를 "))){
				return false;
			}
			movePage("/vend/vendEvalList.do");
		});
		/**
		 * 1. 하반기평가업체저장 버튼
		 */
		$("#btn4").on("click", function() {
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "하반기평가업체저장을 "))){
				return false;
			}
			movePage("/vend/vendEvalList.do");
		});
		/**
		 * 1. 하반기현업평가완료 버튼
		 */
		$("#btn5").on("click", function() {
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "하반기현업평가완료를 "))){
				return false;
			}
			movePage("/vend/vendEvalList.do");
		});
		/**
		 * 1. 현업평가완료취소 버튼
		 */
		$("#btn6").on("click", function() {
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "현업평가완료취소를 "))){
				return false;
			}
			movePage("/vend/vendEvalList.do");
		});
		/**
		 * 1. 현업평가완료취소 버튼
		 */
		$("#btn7").on("click", function() {
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "현업평가완료취소를 "))){
				return false;
			}
			movePage("/vend/vendEvalList.do");
		});
		/**
		 * 1. 평가완료 버튼
		 */
		$("#btn8").on("click", function() {
			if(!confirm(FwkMssageUtil.getMessage("COM.CON.001", "평가완료를 "))){
				return false;
			}
			movePage("/vend/vendEvalList.do");
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