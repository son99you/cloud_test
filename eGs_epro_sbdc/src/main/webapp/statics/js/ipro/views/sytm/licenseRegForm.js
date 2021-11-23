(function() {

	function getRecentDate(){
	    var dt = new Date();
	 
	    var recentYear = dt.getFullYear();
	    var recentMonth = dt.getMonth() + 1;
	    var recentDay = dt.getDate();
	 
	    if(recentMonth < 10) recentMonth = "0" + recentMonth;
	    if(recentDay < 10) recentDay = "0" + recentDay;
	 
	    return recentYear + "-" + recentMonth + "-" + recentDay;
	}
	 
	function getPastDate(period){
	    var dt = new Date();
	 
	    dt.setMonth((dt.getMonth() + 1) + period);
	 
	    var year = dt.getFullYear();
	    var month = dt.getMonth();
	    var day = dt.getDate();
	    
	    if(month == 0) month="1";
	 
	    if(month < 10) month = "0" + month;
	    if(day < 10) day = "0" + day;
	 
	    return year + "-" + month + "-" + day;
	}
	
	// 저장
	gotoSave = function() {
		
		if($("#P_BIZRNO").val() == ''){
			alert("사업자등록번호를 입력하여 주시기 바랍니다.");
			$("#P_BIZRNO").focus();
			return false;
		}
		
		if($("#P_VERSION_NO").val() == ''){
			alert("버전을 선택하여 주시기 바랍니다.");
			$("#P_VERSION_NO").focus();
			return false;
		}
		
		if($("#P_VEND_NM").val() == ''){
			alert("업체명을 입력하여 주시기 바랍니다.");
			$("#P_VERSION_NO").focus();
			return false;
		}
		
		if($("#P_VEND_IP_ADDR").val() == ''){
			alert("IP주소를 입력하여 주시기 바랍니다.");
			$("#P_VERSION_NO").focus();
			return false;
		}
		
		if($("#P_LICENSE_PROCD_SECD").val() == '') {
			alert("발급구분을 선택하여 주시기 바랍니다.");
			$("#P_LICENSE_PROCD_SECD").focus();
			return false;
		}
		
		if($("#P_LICENSE_ST_DATE").val() == '') {
			alert("발급시작일을 선택하여 주시기 바랍니다.");
			$("#P_LICENSE_ST_DATE").focus();
			return false;
		}
		
		if($("#P_LICENSE_END_DATE").val() == '') {
			alert("발급종료일을 선택하여 주시기 바랍니다.");
			$("#P_LICENSE_END_DATE").focus();
			return false;
		}
		
		
		if(!confirm("저장 하시겠습니까?")){
			return false;
		}else {
			FwkCmmnUtil.submitForm("regFrm" , "/sytm/licenseRegist.do");	
		}
		
		 
		return false;
		
	};
	
	
	pageObj.setEventHandler = function() {
		
		/**
		 * 1. 목록버튼 클릭 이벤트
		 */
		$("#listBtn").on("click", function() {
			FwkCmmnUtil.submitForm("listFrm" , "/sytm/licenseList.do"); 
			return false;
		});
		
		// 저장
		$("#saveBtn").on("click", function() {
			gotoSave();
		});
		
		$("#P_LICENSE_PROCD_SECD").on("change", function() {
			
			if($("#P_LICENSE_PROCD_SECD").val() == "D") { //테스트
				var stDate = getRecentDate();
				var endDate = getPastDate(3);
				$("#P_LICENSE_ST_DATE").val(stDate);
				$("#P_LICENSE_END_DATE").val(endDate);
				
				
			}else if($("#P_LICENSE_PROCD_SECD").val() == "R"){ //운영
				$("#P_LICENSE_END_DATE").val("2999-12-31");
			}
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